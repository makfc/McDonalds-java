package com.newrelic.agent.android.instrumentation;

import com.newrelic.agent.android.Measurements;
import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.instrumentation.p053io.CountingInputStream;
import com.newrelic.agent.android.instrumentation.p053io.CountingOutputStream;
import com.newrelic.agent.android.instrumentation.p053io.StreamCompleteEvent;
import com.newrelic.agent.android.instrumentation.p053io.StreamCompleteListener;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.Permission;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class HttpURLConnectionExtension extends HttpURLConnection {
    private static final AgentLog log = AgentLogManager.getAgentLog();
    private HttpURLConnection impl;
    private TransactionState transactionState;

    public HttpURLConnectionExtension(HttpURLConnection impl) {
        super(impl.getURL());
        this.impl = impl;
        TransactionStateUtil.setCrossProcessHeader(impl);
    }

    public void addRequestProperty(String field, String newValue) {
        this.impl.addRequestProperty(field, newValue);
    }

    public void disconnect() {
        if (!(this.transactionState == null || this.transactionState.isComplete())) {
            addTransactionAndErrorData(this.transactionState);
        }
        this.impl.disconnect();
    }

    public boolean usingProxy() {
        return this.impl.usingProxy();
    }

    public void connect() throws IOException {
        getTransactionState();
        try {
            this.impl.connect();
        } catch (IOException e) {
            error(e);
            throw e;
        }
    }

    public boolean getAllowUserInteraction() {
        return this.impl.getAllowUserInteraction();
    }

    public int getConnectTimeout() {
        return this.impl.getConnectTimeout();
    }

    public Object getContent() throws IOException {
        getTransactionState();
        try {
            Object object = this.impl.getContent();
            int contentLength = this.impl.getContentLength();
            if (contentLength >= 0) {
                TransactionState transactionState = getTransactionState();
                if (!transactionState.isComplete()) {
                    transactionState.setBytesReceived((long) contentLength);
                    addTransactionAndErrorData(transactionState);
                }
            }
            return object;
        } catch (IOException e) {
            error(e);
            throw e;
        }
    }

    public Object getContent(Class[] types) throws IOException {
        getTransactionState();
        try {
            Object object = this.impl.getContent(types);
            checkResponse();
            return object;
        } catch (IOException e) {
            error(e);
            throw e;
        }
    }

    public String getContentEncoding() {
        getTransactionState();
        String contentEncoding = this.impl.getContentEncoding();
        checkResponse();
        return contentEncoding;
    }

    public int getContentLength() {
        getTransactionState();
        int contentLength = this.impl.getContentLength();
        checkResponse();
        return contentLength;
    }

    public String getContentType() {
        getTransactionState();
        String contentType = this.impl.getContentType();
        checkResponse();
        return contentType;
    }

    public long getDate() {
        getTransactionState();
        long date = this.impl.getDate();
        checkResponse();
        return date;
    }

    public InputStream getErrorStream() {
        getTransactionState();
        try {
            return new CountingInputStream(this.impl.getErrorStream(), true);
        } catch (Exception e) {
            log.error("HttpURLConnectionExtension: " + e.toString());
            return this.impl.getErrorStream();
        }
    }

    public long getHeaderFieldDate(String field, long defaultValue) {
        getTransactionState();
        long date = this.impl.getHeaderFieldDate(field, defaultValue);
        checkResponse();
        return date;
    }

    public boolean getInstanceFollowRedirects() {
        return this.impl.getInstanceFollowRedirects();
    }

    public Permission getPermission() throws IOException {
        return this.impl.getPermission();
    }

    public String getRequestMethod() {
        return this.impl.getRequestMethod();
    }

    public int getResponseCode() throws IOException {
        getTransactionState();
        try {
            int responseCode = this.impl.getResponseCode();
            checkResponse();
            return responseCode;
        } catch (IOException e) {
            error(e);
            throw e;
        }
    }

    public String getResponseMessage() throws IOException {
        getTransactionState();
        try {
            String message = this.impl.getResponseMessage();
            checkResponse();
            return message;
        } catch (IOException e) {
            error(e);
            throw e;
        }
    }

    public void setChunkedStreamingMode(int chunkLength) {
        this.impl.setChunkedStreamingMode(chunkLength);
    }

    public void setFixedLengthStreamingMode(int contentLength) {
        this.impl.setFixedLengthStreamingMode(contentLength);
    }

    public void setInstanceFollowRedirects(boolean followRedirects) {
        this.impl.setInstanceFollowRedirects(followRedirects);
    }

    public void setRequestMethod(String method) throws ProtocolException {
        getTransactionState();
        try {
            this.impl.setRequestMethod(method);
        } catch (ProtocolException e) {
            error(e);
            throw e;
        }
    }

    public boolean getDefaultUseCaches() {
        return this.impl.getDefaultUseCaches();
    }

    public boolean getDoInput() {
        return this.impl.getDoInput();
    }

    public boolean getDoOutput() {
        return this.impl.getDoOutput();
    }

    public long getExpiration() {
        getTransactionState();
        long expiration = this.impl.getExpiration();
        checkResponse();
        return expiration;
    }

    public String getHeaderField(int pos) {
        getTransactionState();
        String header = this.impl.getHeaderField(pos);
        checkResponse();
        return header;
    }

    public String getHeaderField(String key) {
        getTransactionState();
        String header = this.impl.getHeaderField(key);
        checkResponse();
        return header;
    }

    public int getHeaderFieldInt(String field, int defaultValue) {
        getTransactionState();
        int header = this.impl.getHeaderFieldInt(field, defaultValue);
        checkResponse();
        return header;
    }

    public String getHeaderFieldKey(int posn) {
        getTransactionState();
        String key = this.impl.getHeaderFieldKey(posn);
        checkResponse();
        return key;
    }

    public Map<String, List<String>> getHeaderFields() {
        getTransactionState();
        Map<String, List<String>> fields = this.impl.getHeaderFields();
        checkResponse();
        return fields;
    }

    public long getIfModifiedSince() {
        getTransactionState();
        long ifModifiedSince = this.impl.getIfModifiedSince();
        checkResponse();
        return ifModifiedSince;
    }

    public InputStream getInputStream() throws IOException {
        final TransactionState transactionState = getTransactionState();
        try {
            CountingInputStream in = new CountingInputStream(this.impl.getInputStream());
            TransactionStateUtil.inspectAndInstrumentResponse(transactionState, this.impl);
            in.addStreamCompleteListener(new StreamCompleteListener() {
                public void streamError(StreamCompleteEvent e) {
                    if (!transactionState.isComplete()) {
                        transactionState.setBytesReceived(e.getBytes());
                    }
                    HttpURLConnectionExtension.this.error(e.getException());
                }

                public void streamComplete(StreamCompleteEvent e) {
                    if (!transactionState.isComplete()) {
                        try {
                            transactionState.setStatusCode(HttpURLConnectionExtension.this.impl.getResponseCode());
                        } catch (IOException e2) {
                        }
                        long contentLength = (long) HttpURLConnectionExtension.this.impl.getContentLength();
                        long numBytes = e.getBytes();
                        if (contentLength >= 0) {
                            numBytes = contentLength;
                        }
                        transactionState.setBytesReceived(numBytes);
                        HttpURLConnectionExtension.this.addTransactionAndErrorData(transactionState);
                    }
                }
            });
            return in;
        } catch (IOException e) {
            error(e);
            throw e;
        }
    }

    public long getLastModified() {
        getTransactionState();
        long lastModified = this.impl.getLastModified();
        checkResponse();
        return lastModified;
    }

    public OutputStream getOutputStream() throws IOException {
        final TransactionState transactionState = getTransactionState();
        try {
            CountingOutputStream out = new CountingOutputStream(this.impl.getOutputStream());
            out.addStreamCompleteListener(new StreamCompleteListener() {
                public void streamError(StreamCompleteEvent e) {
                    if (!transactionState.isComplete()) {
                        transactionState.setBytesSent(e.getBytes());
                    }
                    HttpURLConnectionExtension.this.error(e.getException());
                }

                public void streamComplete(StreamCompleteEvent e) {
                    if (!transactionState.isComplete()) {
                        try {
                            transactionState.setStatusCode(HttpURLConnectionExtension.this.impl.getResponseCode());
                        } catch (IOException e2) {
                        }
                        String header = HttpURLConnectionExtension.this.impl.getRequestProperty("content-length");
                        long numBytes = e.getBytes();
                        if (header != null) {
                            try {
                                numBytes = Long.parseLong(header);
                            } catch (NumberFormatException e3) {
                            }
                        }
                        transactionState.setBytesSent(numBytes);
                        HttpURLConnectionExtension.this.addTransactionAndErrorData(transactionState);
                    }
                }
            });
            return out;
        } catch (IOException e) {
            error(e);
            throw e;
        }
    }

    public int getReadTimeout() {
        return this.impl.getReadTimeout();
    }

    public Map<String, List<String>> getRequestProperties() {
        return this.impl.getRequestProperties();
    }

    public String getRequestProperty(String field) {
        return this.impl.getRequestProperty(field);
    }

    public URL getURL() {
        return this.impl.getURL();
    }

    public boolean getUseCaches() {
        return this.impl.getUseCaches();
    }

    public void setAllowUserInteraction(boolean newValue) {
        this.impl.setAllowUserInteraction(newValue);
    }

    public void setConnectTimeout(int timeoutMillis) {
        this.impl.setConnectTimeout(timeoutMillis);
    }

    public void setDefaultUseCaches(boolean newValue) {
        this.impl.setDefaultUseCaches(newValue);
    }

    public void setDoInput(boolean newValue) {
        this.impl.setDoInput(newValue);
    }

    public void setDoOutput(boolean newValue) {
        this.impl.setDoOutput(newValue);
    }

    public void setIfModifiedSince(long newValue) {
        this.impl.setIfModifiedSince(newValue);
    }

    public void setReadTimeout(int timeoutMillis) {
        this.impl.setReadTimeout(timeoutMillis);
    }

    public void setRequestProperty(String field, String newValue) {
        this.impl.setRequestProperty(field, newValue);
    }

    public void setUseCaches(boolean newValue) {
        this.impl.setUseCaches(newValue);
    }

    public String toString() {
        return this.impl.toString();
    }

    private void checkResponse() {
        if (!getTransactionState().isComplete()) {
            TransactionStateUtil.inspectAndInstrumentResponse(getTransactionState(), this.impl);
        }
    }

    private TransactionState getTransactionState() {
        if (this.transactionState == null) {
            this.transactionState = new TransactionState();
            TransactionStateUtil.inspectAndInstrument(this.transactionState, this.impl);
        }
        return this.transactionState;
    }

    private void error(Exception e) {
        TransactionState transactionState = getTransactionState();
        TransactionStateUtil.setErrorCodeFromException(transactionState, e);
        if (!transactionState.isComplete()) {
            TransactionStateUtil.inspectAndInstrumentResponse(transactionState, this.impl);
            TransactionData transactionData = transactionState.end();
            if (transactionData != null) {
                TaskQueue.queue(new HttpTransactionMeasurement(transactionData.getUrl(), transactionData.getHttpMethod(), transactionData.getStatusCode(), transactionData.getErrorCode(), transactionData.getTimestamp(), (double) transactionData.getTime(), transactionData.getBytesSent(), transactionData.getBytesReceived(), transactionData.getAppData()));
            }
        }
    }

    private void addTransactionAndErrorData(TransactionState transactionState) {
        TransactionData transactionData = transactionState.end();
        if (transactionData != null) {
            TaskQueue.queue(new HttpTransactionMeasurement(transactionData.getUrl(), transactionData.getHttpMethod(), transactionData.getStatusCode(), transactionData.getErrorCode(), transactionData.getTimestamp(), (double) transactionData.getTime(), transactionData.getBytesSent(), transactionData.getBytesReceived(), transactionData.getAppData()));
            if (((long) transactionState.getStatusCode()) >= 400) {
                StringBuilder responseBody = new StringBuilder();
                try {
                    InputStream errorStream = getErrorStream();
                    if (errorStream instanceof CountingInputStream) {
                        responseBody.append(((CountingInputStream) errorStream).getBufferAsString());
                    }
                } catch (Exception e) {
                    log.error(e.toString());
                }
                Map params = new TreeMap();
                String contentType = this.impl.getContentType();
                if (!(contentType == null || "".equals(contentType))) {
                    params.put("content_type", contentType);
                }
                params.put("content_length", transactionState.getBytesReceived() + "");
                Measurements.addHttpError(transactionData.getUrl(), transactionData.getHttpMethod(), transactionData.getStatusCode(), transactionData.getErrorCode(), responseBody.toString(), params);
            }
        }
    }
}
