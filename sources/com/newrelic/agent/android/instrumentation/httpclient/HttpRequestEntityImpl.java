package com.newrelic.agent.android.instrumentation.httpclient;

import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.instrumentation.TransactionState;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import com.newrelic.agent.android.instrumentation.p053io.CountingInputStream;
import com.newrelic.agent.android.instrumentation.p053io.CountingOutputStream;
import com.newrelic.agent.android.instrumentation.p053io.StreamCompleteEvent;
import com.newrelic.agent.android.instrumentation.p053io.StreamCompleteListener;
import com.newrelic.agent.android.instrumentation.p053io.StreamCompleteListenerSource;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

public final class HttpRequestEntityImpl implements StreamCompleteListener, HttpEntity {
    private final HttpEntity impl;
    private final TransactionState transactionState;

    public HttpRequestEntityImpl(HttpEntity impl, TransactionState transactionState) {
        this.impl = impl;
        this.transactionState = transactionState;
    }

    public void consumeContent() throws IOException {
        try {
            this.impl.consumeContent();
        } catch (IOException e) {
            handleException(e);
            throw e;
        }
    }

    public InputStream getContent() throws IOException, IllegalStateException {
        try {
            if (this.transactionState.isSent()) {
                return this.impl.getContent();
            }
            CountingInputStream stream = new CountingInputStream(this.impl.getContent());
            stream.addStreamCompleteListener(this);
            return stream;
        } catch (IOException e) {
            handleException(e);
            throw e;
        } catch (IllegalStateException e2) {
            handleException(e2);
            throw e2;
        }
    }

    public Header getContentEncoding() {
        return this.impl.getContentEncoding();
    }

    public long getContentLength() {
        return this.impl.getContentLength();
    }

    public Header getContentType() {
        return this.impl.getContentType();
    }

    public boolean isChunked() {
        return this.impl.isChunked();
    }

    public boolean isRepeatable() {
        return this.impl.isRepeatable();
    }

    public boolean isStreaming() {
        return this.impl.isStreaming();
    }

    public void writeTo(OutputStream outstream) throws IOException {
        try {
            if (this.transactionState.isSent()) {
                this.impl.writeTo(outstream);
                return;
            }
            CountingOutputStream stream = new CountingOutputStream(outstream);
            this.impl.writeTo(stream);
            this.transactionState.setBytesSent(stream.getCount());
        } catch (IOException e) {
            handleException(e);
            throw e;
        }
    }

    public void streamComplete(StreamCompleteEvent e) {
        ((StreamCompleteListenerSource) e.getSource()).removeStreamCompleteListener(this);
        this.transactionState.setBytesSent(e.getBytes());
    }

    public void streamError(StreamCompleteEvent e) {
        ((StreamCompleteListenerSource) e.getSource()).removeStreamCompleteListener(this);
        handleException(e.getException(), Long.valueOf(e.getBytes()));
    }

    private void handleException(Exception e) {
        handleException(e, null);
    }

    private void handleException(Exception e, Long streamBytes) {
        TransactionStateUtil.setErrorCodeFromException(this.transactionState, e);
        if (!this.transactionState.isComplete()) {
            if (streamBytes != null) {
                this.transactionState.setBytesSent(streamBytes.longValue());
            }
            TransactionData transactionData = this.transactionState.end();
            if (transactionData != null) {
                TaskQueue.queue(new HttpTransactionMeasurement(transactionData.getUrl(), transactionData.getHttpMethod(), transactionData.getStatusCode(), transactionData.getErrorCode(), transactionData.getTimestamp(), (double) transactionData.getTime(), transactionData.getBytesSent(), transactionData.getBytesReceived(), transactionData.getAppData()));
            }
        }
    }
}
