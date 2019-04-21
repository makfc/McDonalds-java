package com.newrelic.agent.android.instrumentation.httpclient;

import com.newrelic.agent.android.instrumentation.TransactionState;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

public final class ResponseHandlerImpl<T> implements ResponseHandler<T> {
    private final ResponseHandler<T> impl;
    private final TransactionState transactionState;

    private ResponseHandlerImpl(ResponseHandler<T> impl, TransactionState transactionState) {
        this.impl = impl;
        this.transactionState = transactionState;
    }

    public T handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
        TransactionStateUtil.inspectAndInstrument(this.transactionState, response);
        return this.impl.handleResponse(response);
    }

    public static <T> ResponseHandler<? extends T> wrap(ResponseHandler<? extends T> impl, TransactionState transactionState) {
        return new ResponseHandlerImpl(impl, transactionState);
    }
}
