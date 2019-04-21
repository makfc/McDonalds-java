package com.google.api.client.http.javanet;

import com.google.api.client.http.LowLevelHttpResponse;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

final class NetHttpResponse extends LowLevelHttpResponse {
    private final HttpURLConnection connection;
    private final ArrayList<String> headerNames = new ArrayList();
    private final ArrayList<String> headerValues = new ArrayList();
    private final int responseCode;
    private final String responseMessage;

    private final class SizeValidatingInputStream extends FilterInputStream {
        private long bytesRead = 0;

        public SizeValidatingInputStream(InputStream in) {
            super(in);
        }

        public int read(byte[] b, int off, int len) throws IOException {
            int n = this.in.read(b, off, len);
            if (n == -1) {
                throwIfFalseEOF();
            } else {
                this.bytesRead += (long) n;
            }
            return n;
        }

        public int read() throws IOException {
            int n = this.in.read();
            if (n == -1) {
                throwIfFalseEOF();
            } else {
                this.bytesRead++;
            }
            return n;
        }

        private void throwIfFalseEOF() throws IOException {
            long contentLength = NetHttpResponse.this.getContentLength();
            if (contentLength != -1 && this.bytesRead != 0 && this.bytesRead < contentLength) {
                throw new IOException("Connection closed prematurely: bytesRead = " + this.bytesRead + ", Content-Length = " + contentLength);
            }
        }
    }

    NetHttpResponse(HttpURLConnection connection) throws IOException {
        this.connection = connection;
        int responseCode = connection.getResponseCode();
        if (responseCode == -1) {
            responseCode = 0;
        }
        this.responseCode = responseCode;
        this.responseMessage = connection.getResponseMessage();
        List<String> headerNames = this.headerNames;
        List<String> headerValues = this.headerValues;
        for (Entry<String, List<String>> entry : connection.getHeaderFields().entrySet()) {
            String key = (String) entry.getKey();
            if (key != null) {
                for (String value : (List) entry.getValue()) {
                    if (value != null) {
                        headerNames.add(key);
                        headerValues.add(value);
                    }
                }
            }
        }
    }

    public int getStatusCode() {
        return this.responseCode;
    }

    public InputStream getContent() throws IOException {
        InputStream in;
        try {
            in = this.connection.getInputStream();
        } catch (IOException e) {
            in = this.connection.getErrorStream();
        }
        return in == null ? null : new SizeValidatingInputStream(in);
    }

    public String getContentEncoding() {
        return this.connection.getContentEncoding();
    }

    public long getContentLength() {
        String string = this.connection.getHeaderField(TransactionStateUtil.CONTENT_LENGTH_HEADER);
        return string == null ? -1 : Long.parseLong(string);
    }

    public String getContentType() {
        return this.connection.getHeaderField(TransactionStateUtil.CONTENT_TYPE_HEADER);
    }

    public String getReasonPhrase() {
        return this.responseMessage;
    }

    public String getStatusLine() {
        String result = this.connection.getHeaderField(0);
        return (result == null || !result.startsWith("HTTP/1.")) ? null : result;
    }

    public int getHeaderCount() {
        return this.headerNames.size();
    }

    public String getHeaderName(int index) {
        return (String) this.headerNames.get(index);
    }

    public String getHeaderValue(int index) {
        return (String) this.headerValues.get(index);
    }

    public void disconnect() {
        this.connection.disconnect();
    }
}
