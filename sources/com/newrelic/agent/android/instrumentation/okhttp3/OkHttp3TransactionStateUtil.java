package com.newrelic.agent.android.instrumentation.okhttp3;

import com.autonavi.amap.mapcore.VTMCDataCache;
import com.newrelic.agent.android.Measurements;
import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.instrumentation.TransactionState;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import java.util.Map;
import java.util.TreeMap;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttp3TransactionStateUtil extends TransactionStateUtil {
    private static final long CONTENTLENGTH_UNKNOWN = -1;
    private static final AgentLog log = AgentLogManager.getAgentLog();

    public static void inspectAndInstrument(TransactionState transactionState, Request request) {
        if (request == null) {
            log.warning("Missing request");
        } else {
            TransactionStateUtil.inspectAndInstrument(transactionState, request.url().toString(), request.method());
        }
    }

    public static Response inspectAndInstrumentResponse(TransactionState transactionState, Response response) {
        int statusCode;
        String appData = "";
        long contentLength = 0;
        if (response == null) {
            statusCode = VTMCDataCache.MAXSIZE;
            log.warning("Missing response");
        } else {
            appData = response.header(TransactionStateUtil.APP_DATA_HEADER);
            statusCode = response.code();
            try {
                contentLength = exhaustiveContentLength(response);
            } catch (Exception e) {
            }
            if (contentLength < 0) {
                log.warning("Missing body or content length");
            }
        }
        TransactionStateUtil.inspectAndInstrumentResponse(transactionState, appData, (int) contentLength, statusCode);
        return addTransactionAndErrorData(transactionState, response);
    }

    private static long exhaustiveContentLength(Response response) {
        long contentLength = -1;
        if (response == null) {
            return -1;
        }
        if (response.body() != null) {
            contentLength = response.body().contentLength();
        }
        if (contentLength >= 0) {
            return contentLength;
        }
        String responseBodyString = response.header(TransactionStateUtil.CONTENT_LENGTH_HEADER);
        if (responseBodyString == null || responseBodyString.length() <= 0) {
            return contentLength;
        }
        try {
            return Long.parseLong(responseBodyString);
        } catch (NumberFormatException var10) {
            log.warning("Failed to parse content length: " + var10.toString());
            return contentLength;
        }
    }

    private static Response addTransactionAndErrorData(TransactionState transactionState, Response response) {
        TransactionData transactionData = transactionState.end();
        if (transactionData != null) {
            TaskQueue.queue(new HttpTransactionMeasurement(transactionData.getUrl(), transactionData.getHttpMethod(), transactionData.getStatusCode(), transactionData.getErrorCode(), transactionData.getTimestamp(), (double) transactionData.getTime(), transactionData.getBytesSent(), transactionData.getBytesReceived(), transactionData.getAppData()));
            if (((long) transactionState.getStatusCode()) >= 400 && response != null) {
                String contentTypeHeader = response.header(TransactionStateUtil.CONTENT_TYPE_HEADER);
                Map params = new TreeMap();
                if (!(contentTypeHeader == null || contentTypeHeader.length() <= 0 || "".equals(contentTypeHeader))) {
                    params.put("content_type", null);
                }
                params.put("content_length", transactionState.getBytesReceived() + "");
                String responseBodyString = "";
                try {
                    long contentLength = exhaustiveContentLength(response);
                    if (contentLength > 0) {
                        responseBodyString = response.peekBody(contentLength).string();
                    }
                } catch (Exception e) {
                    if (response.message() != null) {
                        log.warning("Missing response body, using response message");
                        responseBodyString = response.message();
                    }
                }
                Measurements.addHttpError(transactionData.getUrl(), transactionData.getHttpMethod(), transactionData.getStatusCode(), transactionData.getErrorCode(), responseBodyString, params);
            }
        }
        return response;
    }
}
