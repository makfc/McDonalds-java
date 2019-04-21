package com.newrelic.agent.android.instrumentation.retrofit;

import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.instrumentation.TransactionState;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import retrofit.client.Client;
import retrofit.client.Header;
import retrofit.client.Request;
import retrofit.client.Response;

public class ClientExtension implements Client {
    private static final AgentLog log = AgentLogManager.getAgentLog();
    private Client impl;
    private Request request;
    private TransactionState transactionState;

    public ClientExtension(Client impl) {
        this.impl = impl;
    }

    public Response execute(Request request) throws IOException {
        IOException ex;
        this.request = request;
        getTransactionState();
        try {
            Response response = this.impl.execute(setCrossProcessHeader(request));
            Response response2;
            try {
                response2 = new Response(response.getUrl(), response.getStatus(), response.getReason(), response.getHeaders(), new ContentBufferingTypedInput(response.getBody()));
                checkResponse(response2);
                return response2;
            } catch (IOException e) {
                ex = e;
                response2 = response;
                error(ex);
                throw ex;
            }
        } catch (IOException e2) {
            ex = e2;
        }
    }

    private Request setCrossProcessHeader(Request request) {
        String crossProcessId = Agent.getCrossProcessId();
        if (crossProcessId == null) {
            return request;
        }
        List<Header> headers = new ArrayList(request.getHeaders());
        headers.add(new Header(TransactionStateUtil.CROSS_PROCESS_ID_HEADER, crossProcessId));
        return new Request(request.getMethod(), request.getUrl(), headers, request.getBody());
    }

    private void checkResponse(Response response) {
        if (!getTransactionState().isComplete()) {
            RetrofitTransactionStateUtil.inspectAndInstrumentResponse(getTransactionState(), response);
        }
    }

    private TransactionState getTransactionState() {
        if (this.transactionState == null) {
            this.transactionState = new TransactionState();
            RetrofitTransactionStateUtil.inspectAndInstrument(this.transactionState, this.request);
        }
        return this.transactionState;
    }

    private void error(Exception e) {
        log.debug("handling exception: " + e.toString());
        TransactionState transactionState = getTransactionState();
        TransactionStateUtil.setErrorCodeFromException(transactionState, e);
        if (!transactionState.isComplete()) {
            TransactionData transactionData = transactionState.end();
            if (transactionData != null) {
                TaskQueue.queue(new HttpTransactionMeasurement(transactionData.getUrl(), transactionData.getHttpMethod(), transactionData.getStatusCode(), transactionData.getErrorCode(), transactionData.getTimestamp(), (double) transactionData.getTime(), transactionData.getBytesSent(), transactionData.getBytesReceived(), transactionData.getAppData()));
            }
        }
    }
}
