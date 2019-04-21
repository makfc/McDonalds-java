package com.google.api.client.testing.http.javanet;

import com.google.api.client.util.Beta;
import com.mcdonalds.sdk.connectors.middleware.response.MWDeliveryStatusResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

@Beta
public class MockHttpURLConnection extends HttpURLConnection {
    @Deprecated
    public static final byte[] ERROR_BUF = new byte[5];
    @Deprecated
    public static final byte[] INPUT_BUF = new byte[1];
    private boolean doOutputCalled;
    private InputStream errorStream;
    private Map<String, List<String>> headers;
    private InputStream inputStream;
    private OutputStream outputStream;

    public void disconnect() {
    }

    public boolean usingProxy() {
        return false;
    }

    public void connect() throws IOException {
    }

    public int getResponseCode() throws IOException {
        return this.responseCode;
    }

    public void setDoOutput(boolean dooutput) {
        this.doOutputCalled = true;
    }

    public OutputStream getOutputStream() throws IOException {
        if (this.outputStream != null) {
            return this.outputStream;
        }
        return super.getOutputStream();
    }

    public InputStream getInputStream() throws IOException {
        if (this.responseCode < MWDeliveryStatusResponse.STATUS_ORDER_DELIVERED) {
            return this.inputStream;
        }
        throw new IOException();
    }

    public InputStream getErrorStream() {
        return this.errorStream;
    }

    public Map<String, List<String>> getHeaderFields() {
        return this.headers;
    }

    public String getHeaderField(String name) {
        List<String> values = (List) this.headers.get(name);
        return values == null ? null : (String) values.get(0);
    }
}
