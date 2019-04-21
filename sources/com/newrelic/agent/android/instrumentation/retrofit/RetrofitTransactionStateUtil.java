package com.newrelic.agent.android.instrumentation.retrofit;

import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.Measurements;
import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.instrumentation.TransactionState;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import retrofit.client.Header;
import retrofit.client.Request;
import retrofit.client.Response;

public class RetrofitTransactionStateUtil extends TransactionStateUtil {
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final AgentLog log = AgentLogManager.getAgentLog();

    public static void inspectAndInstrument(TransactionState transactionState, Request request) {
        transactionState.setUrl(request.getUrl());
        transactionState.setHttpMethod(request.getMethod());
        transactionState.setCarrier(Agent.getActiveNetworkCarrier());
        transactionState.setWanType(Agent.getActiveNetworkWanType());
    }

    public static void inspectAndInstrumentResponse(TransactionState transactionState, Response response) {
        String appData = getAppDataHeader(response.getHeaders(), TransactionStateUtil.APP_DATA_HEADER);
        if (!(appData == null || "".equals(appData))) {
            transactionState.setAppData(appData);
        }
        transactionState.setStatusCode(response.getStatus());
        long contentLength = response.getBody().length();
        if (contentLength >= 0) {
            transactionState.setBytesReceived(contentLength);
        }
        addTransactionAndErrorData(transactionState, response);
    }

    private static String getAppDataHeader(List<Header> headers, String headerName) {
        if (headers != null) {
            for (Header header : headers) {
                if (header.getName() != null && header.getName().equalsIgnoreCase(headerName)) {
                    return header.getValue();
                }
            }
        }
        return null;
    }

    private static void addTransactionAndErrorData(TransactionState transactionState, Response response) {
        TransactionData transactionData = transactionState.end();
        if (transactionData != null) {
            TaskQueue.queue(new HttpTransactionMeasurement(transactionData.getUrl(), transactionData.getHttpMethod(), transactionData.getStatusCode(), transactionData.getErrorCode(), transactionData.getTimestamp(), (double) transactionData.getTime(), transactionData.getBytesSent(), transactionData.getBytesReceived(), transactionData.getAppData()));
            if (((long) transactionState.getStatusCode()) >= 400) {
                String contentTypeHeader = getAppDataHeader(response.getHeaders(), "Content-Type");
                Map params = new TreeMap();
                if (!(contentTypeHeader == null || contentTypeHeader.length() <= 0 || "".equals(contentTypeHeader))) {
                    params.put("content_type", null);
                }
                params.put("content_length", transactionState.getBytesReceived() + "");
                Measurements.addHttpError(transactionData.getUrl(), transactionData.getHttpMethod(), transactionData.getStatusCode(), transactionData.getErrorCode(), response.getReason(), params);
            }
        }
    }
}
