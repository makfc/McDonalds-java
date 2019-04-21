package com.facebook.stetho.inspector;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.net.Uri.Builder;
import com.autonavi.amap.mapcore.VTMCDataCache;
import com.facebook.stetho.common.ProcessUtil;
import com.facebook.stetho.common.Utf8Charset;
import com.facebook.stetho.server.SecureHttpRequestHandler;
import com.mcdonalds.sdk.modules.notification.PushConstants;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.annotation.Nullable;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandlerRegistry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChromeDiscoveryHandler extends SecureHttpRequestHandler {
    private static final String PAGE_ID = "1";
    private static final String PATH_ACTIVATE = "/json/activate/1";
    private static final String PATH_PAGE_LIST = "/json";
    private static final String PATH_VERSION = "/json/version";
    private static final String PROTOCOL_VERSION = "1.1";
    private static final String USER_AGENT = "Stetho";
    private static final String WEBKIT_REV = "@188492";
    private static final String WEBKIT_VERSION = "537.36 (@188492)";
    private final Context mContext;
    private final String mInspectorPath;
    @Nullable
    private StringEntity mPageListResponse;
    @Nullable
    private StringEntity mVersionResponse;

    public ChromeDiscoveryHandler(Context context, String inspectorPath) {
        super(context);
        this.mContext = context;
        this.mInspectorPath = inspectorPath;
    }

    public void register(HttpRequestHandlerRegistry registry) {
        registry.register(PATH_PAGE_LIST, this);
        registry.register(PATH_VERSION, this);
        registry.register("/json/activate/1*", this);
    }

    /* Access modifiers changed, original: protected */
    public void handleSecured(HttpRequest request, HttpResponse response, HttpContext context) throws HttpException, IOException {
        Uri uri = Uri.parse(request.getRequestLine().getUri());
        String path = uri.getPath();
        try {
            if (PATH_VERSION.equals(path)) {
                handleVersion(response);
            } else if (PATH_PAGE_LIST.equals(path)) {
                handlePageList(response);
            } else if (PATH_ACTIVATE.equals(path)) {
                handleActivate(response);
            } else {
                response.setStatusCode(501);
                response.setReasonPhrase("Not Implemented");
                response.setEntity(new StringEntity("No support for " + uri.getPath()));
            }
        } catch (JSONException e) {
            response.setStatusCode(VTMCDataCache.MAXSIZE);
            response.setReasonPhrase("Internal Server Error");
            response.setEntity(new StringEntity(e.toString(), Utf8Charset.NAME));
        }
    }

    private void handleVersion(HttpResponse response) throws JSONException, UnsupportedEncodingException {
        if (this.mVersionResponse == null) {
            JSONObject reply = new JSONObject();
            reply.put("WebKit-Version", WEBKIT_VERSION);
            reply.put("User-Agent", USER_AGENT);
            reply.put("Protocol-Version", PROTOCOL_VERSION);
            reply.put("Browser", getAppLabelAndVersion());
            reply.put("Android-Package", this.mContext.getPackageName());
            this.mVersionResponse = createStringEntity("application/json", !(reply instanceof JSONObject) ? reply.toString() : JSONObjectInstrumentation.toString(reply));
        }
        setSuccessfulResponse(response, this.mVersionResponse);
    }

    private void handlePageList(HttpResponse response) throws JSONException, UnsupportedEncodingException {
        if (this.mPageListResponse == null) {
            JSONArray reply = new JSONArray();
            JSONObject page = new JSONObject();
            page.put("type", "app");
            page.put(PushConstants.TITLE_KEY, makeTitle());
            page.put("id", "1");
            page.put("description", "");
            page.put("webSocketDebuggerUrl", "ws://" + this.mInspectorPath);
            page.put("devtoolsFrontendUrl", new Builder().scheme("http").authority("chrome-devtools-frontend.appspot.com").appendEncodedPath("serve_rev").appendEncodedPath(WEBKIT_REV).appendEncodedPath("devtools.html").appendQueryParameter("ws", this.mInspectorPath).build().toString());
            reply.put(page);
            this.mPageListResponse = createStringEntity("application/json", !(reply instanceof JSONArray) ? reply.toString() : JSONArrayInstrumentation.toString(reply));
        }
        setSuccessfulResponse(response, this.mPageListResponse);
    }

    private String makeTitle() {
        StringBuilder b = new StringBuilder();
        b.append(getAppLabel());
        b.append(" (powered by Stetho)");
        String processName = ProcessUtil.getProcessName();
        int colonIndex = processName.indexOf(58);
        if (colonIndex >= 0) {
            b.append(processName.substring(colonIndex));
        }
        return b.toString();
    }

    private void handleActivate(HttpResponse response) throws UnsupportedEncodingException {
        setSuccessfulResponse(response, createStringEntity("text/plain", "Target activation ignored"));
    }

    private static StringEntity createStringEntity(String contentType, String responseJson) throws UnsupportedEncodingException {
        StringEntity entity = new StringEntity(responseJson, Utf8Charset.NAME);
        entity.setContentType(contentType);
        return entity;
    }

    private static void setSuccessfulResponse(HttpResponse response, HttpEntity entity) {
        response.setStatusCode(200);
        response.setReasonPhrase("OK");
        response.setEntity(entity);
    }

    private String getAppLabelAndVersion() {
        StringBuilder b = new StringBuilder();
        PackageManager pm = this.mContext.getPackageManager();
        b.append(getAppLabel());
        b.append('/');
        try {
            b.append(pm.getPackageInfo(this.mContext.getPackageName(), 0).versionName);
            return b.toString();
        } catch (NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private CharSequence getAppLabel() {
        return this.mContext.getPackageManager().getApplicationLabel(this.mContext.getApplicationInfo());
    }
}
