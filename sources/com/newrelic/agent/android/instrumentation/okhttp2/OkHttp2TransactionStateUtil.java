package com.newrelic.agent.android.instrumentation.okhttp2;

import com.autonavi.amap.mapcore.VTMCDataCache;
import com.newrelic.agent.android.Measurements;
import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.instrumentation.TransactionState;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import java.util.Map;
import java.util.TreeMap;
import okio.Buffer;
import okio.BufferedSource;

public class OkHttp2TransactionStateUtil extends TransactionStateUtil {
    private static final AgentLog log = AgentLogManager.getAgentLog();

    public static void inspectAndInstrument(TransactionState transactionState, Request request) {
        if (request == null) {
            log.warning("Missing request");
        } else {
            TransactionStateUtil.inspectAndInstrument(transactionState, request.urlString(), request.method());
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
                contentLength = response.body().contentLength();
            } catch (Exception e) {
                log.warning("Missing body or content length");
            }
        }
        TransactionStateUtil.inspectAndInstrumentResponse(transactionState, appData, (int) contentLength, statusCode);
        return addTransactionAndErrorData(transactionState, response);
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
                    ResponseBody body = response.body();
                    responseBodyString = body.string();
                    final ResponseBody responseBody = body;
                    final Buffer write = new Buffer().write(responseBodyString.getBytes());
                    response = response.newBuilder().body(new ResponseBody() {
                        public MediaType contentType() {
                            return responseBody.contentType();
                        }

                        public long contentLength() {
                            return write.size();
                        }

                        public BufferedSource source() {
                            return write;
                        }
                    }).build();
                } catch (Exception e) {
                    if (response.message() != null) {
                        log.warning("Missing response body, using response message");
                        responseBodyString = response.message();
                    }
                }
                if (transactionData.getErrorCode() != 0) {
                    Measurements.addHttpError(transactionData.getUrl(), transactionData.getHttpMethod(), transactionData.getStatusCode(), transactionData.getErrorCode(), responseBodyString, params);
                } else {
                    Measurements.addHttpError(transactionData.getUrl(), transactionData.getHttpMethod(), transactionData.getStatusCode(), responseBodyString, params);
                }
            }
        }
        return response;
    }
}
