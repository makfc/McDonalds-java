package com.facebook.stetho.inspector.network;

import android.os.SystemClock;
import com.facebook.stetho.common.Utf8Charset;
import com.facebook.stetho.inspector.console.CLog;
import com.facebook.stetho.inspector.network.NetworkEventReporter.InspectorHeaders;
import com.facebook.stetho.inspector.network.NetworkEventReporter.InspectorRequest;
import com.facebook.stetho.inspector.network.NetworkEventReporter.InspectorResponse;
import com.facebook.stetho.inspector.protocol.module.Console.CallFrame;
import com.facebook.stetho.inspector.protocol.module.Console.MessageLevel;
import com.facebook.stetho.inspector.protocol.module.Console.MessageSource;
import com.facebook.stetho.inspector.protocol.module.Network.DataReceivedParams;
import com.facebook.stetho.inspector.protocol.module.Network.Initiator;
import com.facebook.stetho.inspector.protocol.module.Network.InitiatorType;
import com.facebook.stetho.inspector.protocol.module.Network.LoadingFailedParams;
import com.facebook.stetho.inspector.protocol.module.Network.LoadingFinishedParams;
import com.facebook.stetho.inspector.protocol.module.Network.Request;
import com.facebook.stetho.inspector.protocol.module.Network.RequestWillBeSentParams;
import com.facebook.stetho.inspector.protocol.module.Network.Response;
import com.facebook.stetho.inspector.protocol.module.Network.ResponseReceivedParams;
import com.facebook.stetho.inspector.protocol.module.Page.ResourceType;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class NetworkEventReporterImpl implements NetworkEventReporter {
    private static NetworkEventReporter sInstance;
    @Nullable
    private ResourceTypeHelper mResourceTypeHelper;

    private NetworkEventReporterImpl() {
    }

    public static synchronized NetworkEventReporter get() {
        NetworkEventReporter networkEventReporter;
        synchronized (NetworkEventReporterImpl.class) {
            if (sInstance == null) {
                sInstance = new NetworkEventReporterImpl();
            }
            networkEventReporter = sInstance;
        }
        return networkEventReporter;
    }

    public boolean isEnabled() {
        return getPeerManagerIfEnabled() != null;
    }

    @Nullable
    private NetworkPeerManager getPeerManagerIfEnabled() {
        NetworkPeerManager peerManager = NetworkPeerManager.getInstanceOrNull();
        return (peerManager == null || !peerManager.hasRegisteredPeers()) ? null : peerManager;
    }

    public void requestWillBeSent(InspectorRequest request) {
        NetworkPeerManager peerManager = getPeerManagerIfEnabled();
        if (peerManager != null) {
            int intValue;
            Request requestJSON = new Request();
            requestJSON.url = request.url();
            requestJSON.method = request.method();
            requestJSON.headers = formatHeadersAsJSON(request);
            requestJSON.postData = readBodyAsString(peerManager, request);
            String requestFriendlyName = request.friendlyName();
            Integer requestPriority = request.friendlyNameExtra();
            Initiator initiatorJSON = new Initiator();
            initiatorJSON.type = InitiatorType.SCRIPT;
            initiatorJSON.stackTrace = new ArrayList();
            List list = initiatorJSON.stackTrace;
            if (requestPriority != null) {
                intValue = requestPriority.intValue();
            } else {
                intValue = 0;
            }
            list.add(new CallFrame(requestFriendlyName, requestFriendlyName, intValue, 0));
            RequestWillBeSentParams params = new RequestWillBeSentParams();
            params.requestId = request.mo16730id();
            params.frameId = "1";
            params.loaderId = "1";
            params.documentURL = request.url();
            params.request = requestJSON;
            params.timestamp = ((double) stethoNow()) / 1000.0d;
            params.initiator = initiatorJSON;
            params.redirectResponse = null;
            params.type = ResourceType.OTHER;
            peerManager.sendNotificationToPeers("Network.requestWillBeSent", params);
        }
    }

    @Nullable
    private static String readBodyAsString(NetworkPeerManager peerManager, InspectorRequest request) {
        try {
            byte[] body = request.body();
            if (body != null) {
                return new String(body, Utf8Charset.INSTANCE);
            }
        } catch (IOException | OutOfMemoryError e) {
            CLog.writeToConsole(peerManager, MessageLevel.WARNING, MessageSource.NETWORK, "Could not reproduce POST body: " + e);
        }
        return null;
    }

    public void responseHeadersReceived(InspectorResponse response) {
        NetworkPeerManager peerManager = getPeerManagerIfEnabled();
        if (peerManager != null) {
            Response responseJSON = new Response();
            responseJSON.url = response.url();
            responseJSON.status = response.statusCode();
            responseJSON.statusText = response.reasonPhrase();
            responseJSON.headers = formatHeadersAsJSON(response);
            String contentType = getContentType(response);
            responseJSON.mimeType = contentType != null ? getResourceTypeHelper().stripContentExtras(contentType) : "application/octet-stream";
            responseJSON.connectionReused = response.connectionReused();
            responseJSON.connectionId = response.connectionId();
            responseJSON.fromDiskCache = Boolean.valueOf(response.fromDiskCache());
            ResponseReceivedParams receivedParams = new ResponseReceivedParams();
            receivedParams.requestId = response.requestId();
            receivedParams.frameId = "1";
            receivedParams.loaderId = "1";
            receivedParams.timestamp = ((double) stethoNow()) / 1000.0d;
            receivedParams.response = responseJSON;
            receivedParams.type = determineResourceType(initAsyncPrettyPrinterForResponse(response, peerManager), contentType, getResourceTypeHelper());
            peerManager.sendNotificationToPeers("Network.responseReceived", receivedParams);
        }
    }

    @Nullable
    private static AsyncPrettyPrinter initAsyncPrettyPrinterForResponse(InspectorResponse response, NetworkPeerManager peerManager) {
        AsyncPrettyPrinter asyncPrettyPrinter = createPrettyPrinterForResponse(response, peerManager.getAsyncPrettyPrinterRegistry());
        if (asyncPrettyPrinter != null) {
            peerManager.getResponseBodyFileManager().associateAsyncPrettyPrinterWithId(response.requestId(), asyncPrettyPrinter);
        }
        return asyncPrettyPrinter;
    }

    private static ResourceType determineResourceType(AsyncPrettyPrinter asyncPrettyPrinter, String contentType, ResourceTypeHelper resourceTypeHelper) {
        if (asyncPrettyPrinter != null) {
            return asyncPrettyPrinter.getPrettifiedType().getResourceType();
        }
        return contentType != null ? resourceTypeHelper.determineResourceType(contentType) : ResourceType.OTHER;
    }

    @Nullable
    static AsyncPrettyPrinter createPrettyPrinterForResponse(InspectorResponse response, @Nullable AsyncPrettyPrinterRegistry registry) {
        if (registry != null) {
            int count = response.headerCount();
            for (int i = 0; i < count; i++) {
                AsyncPrettyPrinterFactory factory = registry.lookup(response.headerName(i));
                if (factory != null) {
                    return factory.getInstance(response.headerName(i), response.headerValue(i));
                }
            }
        }
        return null;
    }

    public InputStream interpretResponseStream(String requestId, @Nullable String contentType, @Nullable String contentEncoding, @Nullable InputStream availableInputStream, ResponseHandler responseHandler) {
        NetworkPeerManager peerManager = getPeerManagerIfEnabled();
        if (peerManager == null) {
            return availableInputStream;
        }
        if (availableInputStream == null) {
            responseHandler.onEOF();
            return null;
        }
        ResourceType resourceType;
        if (contentType != null) {
            resourceType = getResourceTypeHelper().determineResourceType(contentType);
        } else {
            resourceType = null;
        }
        boolean base64Encode = false;
        if (resourceType != null && resourceType == ResourceType.IMAGE) {
            base64Encode = true;
        }
        try {
            return DecompressionHelper.teeInputWithDecompression(peerManager, requestId, availableInputStream, peerManager.getResponseBodyFileManager().openResponseBodyFile(requestId, base64Encode), contentEncoding, responseHandler);
        } catch (IOException e) {
            CLog.writeToConsole(peerManager, MessageLevel.ERROR, MessageSource.NETWORK, "Error writing response body data for request #" + requestId);
            return availableInputStream;
        }
    }

    public void httpExchangeFailed(String requestId, String errorText) {
        loadingFailed(requestId, errorText);
    }

    public void responseReadFinished(String requestId) {
        loadingFinished(requestId);
    }

    private void loadingFinished(String requestId) {
        NetworkPeerManager peerManager = getPeerManagerIfEnabled();
        if (peerManager != null) {
            LoadingFinishedParams finishedParams = new LoadingFinishedParams();
            finishedParams.requestId = requestId;
            finishedParams.timestamp = ((double) stethoNow()) / 1000.0d;
            peerManager.sendNotificationToPeers("Network.loadingFinished", finishedParams);
        }
    }

    public void responseReadFailed(String requestId, String errorText) {
        loadingFailed(requestId, errorText);
    }

    private void loadingFailed(String requestId, String errorText) {
        NetworkPeerManager peerManager = getPeerManagerIfEnabled();
        if (peerManager != null) {
            LoadingFailedParams failedParams = new LoadingFailedParams();
            failedParams.requestId = requestId;
            failedParams.timestamp = ((double) stethoNow()) / 1000.0d;
            failedParams.errorText = errorText;
            failedParams.type = ResourceType.OTHER;
            peerManager.sendNotificationToPeers("Network.loadingFailed", failedParams);
        }
    }

    public void dataSent(String requestId, int dataLength, int encodedDataLength) {
        dataReceived(requestId, dataLength, encodedDataLength);
    }

    public void dataReceived(String requestId, int dataLength, int encodedDataLength) {
        NetworkPeerManager peerManager = getPeerManagerIfEnabled();
        if (peerManager != null) {
            DataReceivedParams dataReceivedParams = new DataReceivedParams();
            dataReceivedParams.requestId = requestId;
            dataReceivedParams.timestamp = ((double) stethoNow()) / 1000.0d;
            dataReceivedParams.dataLength = dataLength;
            dataReceivedParams.encodedDataLength = encodedDataLength;
            peerManager.sendNotificationToPeers("Network.dataReceived", dataReceivedParams);
        }
    }

    @Nullable
    private String getContentType(InspectorHeaders headers) {
        return headers.firstHeaderValue(TransactionStateUtil.CONTENT_TYPE_HEADER);
    }

    private static JSONObject formatHeadersAsJSON(InspectorHeaders headers) {
        JSONObject json = new JSONObject();
        int i = 0;
        while (i < headers.headerCount()) {
            String name = headers.headerName(i);
            String value = headers.headerValue(i);
            try {
                if (json.has(name)) {
                    json.put(name, json.getString(name) + "\n" + value);
                } else {
                    json.put(name, value);
                }
                i++;
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        return json;
    }

    @Nonnull
    private ResourceTypeHelper getResourceTypeHelper() {
        if (this.mResourceTypeHelper == null) {
            this.mResourceTypeHelper = new ResourceTypeHelper();
        }
        return this.mResourceTypeHelper;
    }

    private static long stethoNow() {
        return SystemClock.elapsedRealtime();
    }
}
