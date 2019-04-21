package com.google.api.client.http;

import com.google.api.client.util.Charsets;
import com.google.api.client.util.IOUtils;
import com.google.api.client.util.StringUtils;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class HttpResponse {
    private InputStream content;
    private final String contentEncoding;
    private int contentLoggingLimit;
    private boolean contentRead;
    private final String contentType;
    private boolean loggingEnabled;
    private final HttpMediaType mediaType;
    private final HttpRequest request;
    LowLevelHttpResponse response;
    private final int statusCode;
    private final String statusMessage;

    HttpResponse(HttpRequest request, LowLevelHttpResponse response) throws IOException {
        boolean loggable;
        StringBuilder stringBuilder;
        HttpMediaType httpMediaType = null;
        this.request = request;
        this.contentLoggingLimit = request.getContentLoggingLimit();
        this.loggingEnabled = request.isLoggingEnabled();
        this.response = response;
        this.contentEncoding = response.getContentEncoding();
        int code = response.getStatusCode();
        if (code < 0) {
            code = 0;
        }
        this.statusCode = code;
        String message = response.getReasonPhrase();
        this.statusMessage = message;
        Logger logger = HttpTransport.LOGGER;
        if (this.loggingEnabled && logger.isLoggable(Level.CONFIG)) {
            loggable = true;
        } else {
            loggable = false;
        }
        StringBuilder logbuf = null;
        if (loggable) {
            logbuf = new StringBuilder();
            logbuf.append("-------------- RESPONSE --------------").append(StringUtils.LINE_SEPARATOR);
            String statusLine = response.getStatusLine();
            if (statusLine != null) {
                logbuf.append(statusLine);
            } else {
                logbuf.append(this.statusCode);
                if (message != null) {
                    logbuf.append(SafeJsonPrimitive.NULL_CHAR).append(message);
                }
            }
            logbuf.append(StringUtils.LINE_SEPARATOR);
        }
        HttpHeaders responseHeaders = request.getResponseHeaders();
        if (loggable) {
            stringBuilder = logbuf;
        } else {
            stringBuilder = null;
        }
        responseHeaders.fromHttpResponse(response, stringBuilder);
        String contentType = response.getContentType();
        if (contentType == null) {
            contentType = request.getResponseHeaders().getContentType();
        }
        this.contentType = contentType;
        if (contentType != null) {
            httpMediaType = new HttpMediaType(contentType);
        }
        this.mediaType = httpMediaType;
        if (loggable) {
            logger.config(logbuf.toString());
        }
    }

    public String getContentType() {
        return this.contentType;
    }

    public HttpHeaders getHeaders() {
        return this.request.getResponseHeaders();
    }

    public boolean isSuccessStatusCode() {
        return HttpStatusCodes.isSuccess(this.statusCode);
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    public HttpRequest getRequest() {
        return this.request;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x004d  */
    public java.io.InputStream getContent() throws java.io.IOException {
        /*
        r7 = this;
        r5 = r7.contentRead;
        if (r5 != 0) goto L_0x0040;
    L_0x0004:
        r5 = r7.response;
        r3 = r5.getContent();
        if (r3 == 0) goto L_0x003d;
    L_0x000c:
        r1 = 0;
        r0 = r7.contentEncoding;	 Catch:{ EOFException -> 0x0043, all -> 0x004a }
        if (r0 == 0) goto L_0x0059;
    L_0x0011:
        r5 = "gzip";
        r5 = r0.contains(r5);	 Catch:{ EOFException -> 0x0043, all -> 0x004a }
        if (r5 == 0) goto L_0x0059;
    L_0x0019:
        r4 = new java.util.zip.GZIPInputStream;	 Catch:{ EOFException -> 0x0043, all -> 0x004a }
        r4.<init>(r3);	 Catch:{ EOFException -> 0x0043, all -> 0x004a }
    L_0x001e:
        r2 = com.google.api.client.http.HttpTransport.LOGGER;	 Catch:{ EOFException -> 0x0054, all -> 0x0051 }
        r5 = r7.loggingEnabled;	 Catch:{ EOFException -> 0x0054, all -> 0x0051 }
        if (r5 == 0) goto L_0x0057;
    L_0x0024:
        r5 = java.util.logging.Level.CONFIG;	 Catch:{ EOFException -> 0x0054, all -> 0x0051 }
        r5 = r2.isLoggable(r5);	 Catch:{ EOFException -> 0x0054, all -> 0x0051 }
        if (r5 == 0) goto L_0x0057;
    L_0x002c:
        r3 = new com.google.api.client.util.LoggingInputStream;	 Catch:{ EOFException -> 0x0054, all -> 0x0051 }
        r5 = java.util.logging.Level.CONFIG;	 Catch:{ EOFException -> 0x0054, all -> 0x0051 }
        r6 = r7.contentLoggingLimit;	 Catch:{ EOFException -> 0x0054, all -> 0x0051 }
        r3.<init>(r4, r2, r5, r6);	 Catch:{ EOFException -> 0x0054, all -> 0x0051 }
    L_0x0035:
        r7.content = r3;	 Catch:{ EOFException -> 0x0043, all -> 0x004a }
        r1 = 1;
        if (r1 != 0) goto L_0x003d;
    L_0x003a:
        r3.close();
    L_0x003d:
        r5 = 1;
        r7.contentRead = r5;
    L_0x0040:
        r5 = r7.content;
        return r5;
    L_0x0043:
        r5 = move-exception;
    L_0x0044:
        if (r1 != 0) goto L_0x003d;
    L_0x0046:
        r3.close();
        goto L_0x003d;
    L_0x004a:
        r5 = move-exception;
    L_0x004b:
        if (r1 != 0) goto L_0x0050;
    L_0x004d:
        r3.close();
    L_0x0050:
        throw r5;
    L_0x0051:
        r5 = move-exception;
        r3 = r4;
        goto L_0x004b;
    L_0x0054:
        r5 = move-exception;
        r3 = r4;
        goto L_0x0044;
    L_0x0057:
        r3 = r4;
        goto L_0x0035;
    L_0x0059:
        r4 = r3;
        goto L_0x001e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.client.http.HttpResponse.getContent():java.io.InputStream");
    }

    public void ignore() throws IOException {
        InputStream content = getContent();
        if (content != null) {
            content.close();
        }
    }

    public void disconnect() throws IOException {
        ignore();
        this.response.disconnect();
    }

    public <T> T parseAs(Class<T> dataClass) throws IOException {
        if (hasMessageBody()) {
            return this.request.getParser().parseAndClose(getContent(), getContentCharset(), dataClass);
        }
        return null;
    }

    private boolean hasMessageBody() throws IOException {
        int statusCode = getStatusCode();
        if (!getRequest().getRequestMethod().equals("HEAD") && statusCode / 100 != 1 && statusCode != 204 && statusCode != 304) {
            return true;
        }
        ignore();
        return false;
    }

    public String parseAsString() throws IOException {
        InputStream content = getContent();
        if (content == null) {
            return "";
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        IOUtils.copy(content, out);
        return out.toString(getContentCharset().name());
    }

    public Charset getContentCharset() {
        return (this.mediaType == null || this.mediaType.getCharsetParameter() == null) ? Charsets.ISO_8859_1 : this.mediaType.getCharsetParameter();
    }
}
