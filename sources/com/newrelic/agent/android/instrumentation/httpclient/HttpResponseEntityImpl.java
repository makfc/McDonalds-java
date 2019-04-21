package com.newrelic.agent.android.instrumentation.httpclient;

import com.newrelic.agent.android.Measurements;
import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.instrumentation.TransactionState;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import com.newrelic.agent.android.instrumentation.p053io.CountingInputStream;
import com.newrelic.agent.android.instrumentation.p053io.StreamCompleteEvent;
import com.newrelic.agent.android.instrumentation.p053io.StreamCompleteListener;
import com.newrelic.agent.android.instrumentation.p053io.StreamCompleteListenerSource;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

public final class HttpResponseEntityImpl implements StreamCompleteListener, HttpEntity {
    private static final String ENCODING_CHUNKED = "chunked";
    private static final String TRANSFER_ENCODING_HEADER = "Transfer-Encoding";
    private static final AgentLog log = AgentLogManager.getAgentLog();
    private final long contentLengthFromHeader;
    private CountingInputStream contentStream;
    private final HttpEntity impl;
    private final TransactionState transactionState;

    public HttpResponseEntityImpl(HttpEntity impl, TransactionState transactionState, long contentLengthFromHeader) {
        this.impl = impl;
        this.transactionState = transactionState;
        this.contentLengthFromHeader = contentLengthFromHeader;
    }

    public void consumeContent() throws IOException {
        try {
            this.impl.consumeContent();
        } catch (IOException e) {
            handleException(e);
            throw e;
        }
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:23:0x0050, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:24:0x0051, code skipped:
            log.error("HttpResponseEntityImpl: " + r0.toString());
     */
    public java.io.InputStream getContent() throws java.io.IOException, java.lang.IllegalStateException {
        /*
        r8 = this;
        r5 = r8.contentStream;
        if (r5 == 0) goto L_0x0007;
    L_0x0004:
        r5 = r8.contentStream;
    L_0x0006:
        return r5;
    L_0x0007:
        r3 = 1;
        r5 = r8.impl;	 Catch:{ IOException -> 0x006e }
        r5 = r5 instanceof org.apache.http.message.AbstractHttpMessage;	 Catch:{ IOException -> 0x006e }
        if (r5 == 0) goto L_0x003c;
    L_0x000e:
        r2 = r8.impl;	 Catch:{ IOException -> 0x006e }
        r2 = (org.apache.http.message.AbstractHttpMessage) r2;	 Catch:{ IOException -> 0x006e }
        r5 = "Transfer-Encoding";
        r4 = r2.getLastHeader(r5);	 Catch:{ IOException -> 0x006e }
        if (r4 == 0) goto L_0x0027;
    L_0x001a:
        r5 = "chunked";
        r6 = r4.getValue();	 Catch:{ IOException -> 0x006e }
        r5 = r5.equalsIgnoreCase(r6);	 Catch:{ IOException -> 0x006e }
        if (r5 == 0) goto L_0x0027;
    L_0x0026:
        r3 = 0;
    L_0x0027:
        r5 = new com.newrelic.agent.android.instrumentation.io.CountingInputStream;	 Catch:{ IllegalArgumentException -> 0x0050 }
        r6 = r8.impl;	 Catch:{ IllegalArgumentException -> 0x0050 }
        r6 = r6.getContent();	 Catch:{ IllegalArgumentException -> 0x0050 }
        r5.<init>(r6, r3);	 Catch:{ IllegalArgumentException -> 0x0050 }
        r8.contentStream = r5;	 Catch:{ IllegalArgumentException -> 0x0050 }
        r5 = r8.contentStream;	 Catch:{ IllegalArgumentException -> 0x0050 }
        r5.addStreamCompleteListener(r8);	 Catch:{ IllegalArgumentException -> 0x0050 }
    L_0x0039:
        r5 = r8.contentStream;	 Catch:{ IOException -> 0x006e }
        goto L_0x0006;
    L_0x003c:
        r5 = r8.impl;	 Catch:{ IOException -> 0x006e }
        r5 = r5 instanceof org.apache.http.entity.HttpEntityWrapper;	 Catch:{ IOException -> 0x006e }
        if (r5 == 0) goto L_0x0027;
    L_0x0042:
        r1 = r8.impl;	 Catch:{ IOException -> 0x006e }
        r1 = (org.apache.http.entity.HttpEntityWrapper) r1;	 Catch:{ IOException -> 0x006e }
        r5 = r1.isChunked();	 Catch:{ IOException -> 0x006e }
        if (r5 != 0) goto L_0x004e;
    L_0x004c:
        r3 = 1;
    L_0x004d:
        goto L_0x0027;
    L_0x004e:
        r3 = 0;
        goto L_0x004d;
    L_0x0050:
        r0 = move-exception;
        r5 = log;	 Catch:{ IOException -> 0x006e }
        r6 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x006e }
        r6.<init>();	 Catch:{ IOException -> 0x006e }
        r7 = "HttpResponseEntityImpl: ";
        r6 = r6.append(r7);	 Catch:{ IOException -> 0x006e }
        r7 = r0.toString();	 Catch:{ IOException -> 0x006e }
        r6 = r6.append(r7);	 Catch:{ IOException -> 0x006e }
        r6 = r6.toString();	 Catch:{ IOException -> 0x006e }
        r5.error(r6);	 Catch:{ IOException -> 0x006e }
        goto L_0x0039;
    L_0x006e:
        r0 = move-exception;
        r8.handleException(r0);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newrelic.agent.android.instrumentation.httpclient.HttpResponseEntityImpl.getContent():java.io.InputStream");
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

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0033  */
    public void writeTo(java.io.OutputStream r9) throws java.io.IOException {
        /*
        r8 = this;
        r3 = r8.transactionState;
        r3 = r3.isComplete();
        if (r3 != 0) goto L_0x004c;
    L_0x0008:
        r1 = 0;
        r2 = new com.newrelic.agent.android.instrumentation.io.CountingOutputStream;	 Catch:{ IOException -> 0x0030 }
        r2.<init>(r9);	 Catch:{ IOException -> 0x0030 }
        r3 = r8.impl;	 Catch:{ IOException -> 0x0052 }
        r3.writeTo(r2);	 Catch:{ IOException -> 0x0052 }
        r3 = r8.transactionState;
        r3 = r3.isComplete();
        if (r3 != 0) goto L_0x002f;
    L_0x001b:
        r4 = r8.contentLengthFromHeader;
        r6 = 0;
        r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r3 < 0) goto L_0x0042;
    L_0x0023:
        r3 = r8.transactionState;
        r4 = r8.contentLengthFromHeader;
        r3.setBytesReceived(r4);
    L_0x002a:
        r3 = r8.transactionState;
        r8.addTransactionAndErrorData(r3);
    L_0x002f:
        return;
    L_0x0030:
        r0 = move-exception;
    L_0x0031:
        if (r1 == 0) goto L_0x003e;
    L_0x0033:
        r4 = r1.getCount();
        r3 = java.lang.Long.valueOf(r4);
        r8.handleException(r0, r3);
    L_0x003e:
        r0.printStackTrace();
        throw r0;
    L_0x0042:
        r3 = r8.transactionState;
        r4 = r2.getCount();
        r3.setBytesReceived(r4);
        goto L_0x002a;
    L_0x004c:
        r3 = r8.impl;
        r3.writeTo(r9);
        goto L_0x002f;
    L_0x0052:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0031;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newrelic.agent.android.instrumentation.httpclient.HttpResponseEntityImpl.writeTo(java.io.OutputStream):void");
    }

    public void streamComplete(StreamCompleteEvent e) {
        ((StreamCompleteListenerSource) e.getSource()).removeStreamCompleteListener(this);
        if (!this.transactionState.isComplete()) {
            if (this.contentLengthFromHeader >= 0) {
                this.transactionState.setBytesReceived(this.contentLengthFromHeader);
            } else {
                this.transactionState.setBytesReceived(e.getBytes());
            }
            addTransactionAndErrorData(this.transactionState);
        }
    }

    public void streamError(StreamCompleteEvent e) {
        ((StreamCompleteListenerSource) e.getSource()).removeStreamCompleteListener(this);
        TransactionStateUtil.setErrorCodeFromException(this.transactionState, e.getException());
        if (!this.transactionState.isComplete()) {
            this.transactionState.setBytesReceived(e.getBytes());
        }
    }

    private void addTransactionAndErrorData(TransactionState transactionState) {
        TransactionData transactionData = transactionState.end();
        if (transactionData != null) {
            TaskQueue.queue(new HttpTransactionMeasurement(transactionData.getUrl(), transactionData.getHttpMethod(), transactionData.getStatusCode(), transactionData.getErrorCode(), transactionData.getTimestamp(), (double) transactionData.getTime(), transactionData.getBytesSent(), transactionData.getBytesReceived(), transactionData.getAppData()));
            if (((long) transactionState.getStatusCode()) >= 400) {
                StringBuilder responseBody = new StringBuilder();
                try {
                    InputStream errorStream = getContent();
                    if (errorStream instanceof CountingInputStream) {
                        responseBody.append(((CountingInputStream) errorStream).getBufferAsString());
                    }
                } catch (Exception e) {
                    log.error(e.toString());
                }
                Header contentType = this.impl.getContentType();
                Map<String, String> params = new TreeMap();
                if (!(contentType == null || contentType.getValue() == null || "".equals(contentType.getValue()))) {
                    params.put("content_type", contentType.getValue());
                }
                params.put("content_length", transactionState.getBytesReceived() + "");
                Measurements.addHttpError(transactionData, responseBody.toString(), (Map) params);
            }
        }
    }

    private void handleException(Exception e) {
        handleException(e, null);
    }

    private void handleException(Exception e, Long streamBytes) {
        TransactionStateUtil.setErrorCodeFromException(this.transactionState, e);
        if (!this.transactionState.isComplete()) {
            if (streamBytes != null) {
                this.transactionState.setBytesReceived(streamBytes.longValue());
            }
            TransactionData transactionData = this.transactionState.end();
            if (transactionData != null) {
                TaskQueue.queue(new HttpTransactionMeasurement(transactionData.getUrl(), transactionData.getHttpMethod(), transactionData.getStatusCode(), transactionData.getErrorCode(), transactionData.getTimestamp(), (double) transactionData.getTime(), transactionData.getBytesSent(), transactionData.getBytesReceived(), transactionData.getAppData()));
            }
        }
    }
}
