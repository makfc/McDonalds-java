package com.google.api.client.http;

import com.google.api.client.util.Beta;
import com.google.api.client.util.ObjectParser;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sleeper;
import java.util.concurrent.Callable;

public final class HttpRequest {
    @Beta
    @Deprecated
    private BackOffPolicy backOffPolicy;
    private int connectTimeout = 20000;
    private HttpContent content;
    private int contentLoggingLimit = 16384;
    private boolean curlLoggingEnabled = true;
    private HttpEncoding encoding;
    private HttpExecuteInterceptor executeInterceptor;
    private boolean followRedirects = true;
    private HttpHeaders headers = new HttpHeaders();
    @Beta
    private HttpIOExceptionHandler ioExceptionHandler;
    private boolean loggingEnabled = true;
    private int numRetries = 10;
    private ObjectParser objectParser;
    private int readTimeout = 20000;
    private String requestMethod;
    private HttpHeaders responseHeaders = new HttpHeaders();
    private HttpResponseInterceptor responseInterceptor;
    @Beta
    @Deprecated
    private boolean retryOnExecuteIOException = false;
    private Sleeper sleeper = Sleeper.DEFAULT;
    private boolean suppressUserAgentSuffix;
    private boolean throwExceptionOnExecuteError = true;
    private final HttpTransport transport;
    private HttpUnsuccessfulResponseHandler unsuccessfulResponseHandler;
    private GenericUrl url;

    /* renamed from: com.google.api.client.http.HttpRequest$1 */
    class C23551 implements Callable<HttpResponse> {
        final /* synthetic */ HttpRequest this$0;

        public HttpResponse call() throws Exception {
            return this.this$0.execute();
        }
    }

    HttpRequest(HttpTransport transport, String requestMethod) {
        this.transport = transport;
        setRequestMethod(requestMethod);
    }

    public String getRequestMethod() {
        return this.requestMethod;
    }

    public HttpRequest setRequestMethod(String requestMethod) {
        boolean z = requestMethod == null || HttpMediaType.matchesToken(requestMethod);
        Preconditions.checkArgument(z);
        this.requestMethod = requestMethod;
        return this;
    }

    public GenericUrl getUrl() {
        return this.url;
    }

    public HttpRequest setUrl(GenericUrl url) {
        this.url = (GenericUrl) Preconditions.checkNotNull(url);
        return this;
    }

    public HttpContent getContent() {
        return this.content;
    }

    public HttpRequest setContent(HttpContent content) {
        this.content = content;
        return this;
    }

    public int getContentLoggingLimit() {
        return this.contentLoggingLimit;
    }

    public boolean isLoggingEnabled() {
        return this.loggingEnabled;
    }

    public HttpHeaders getHeaders() {
        return this.headers;
    }

    public HttpHeaders getResponseHeaders() {
        return this.responseHeaders;
    }

    public HttpExecuteInterceptor getInterceptor() {
        return this.executeInterceptor;
    }

    public HttpRequest setInterceptor(HttpExecuteInterceptor interceptor) {
        this.executeInterceptor = interceptor;
        return this;
    }

    public HttpRequest setUnsuccessfulResponseHandler(HttpUnsuccessfulResponseHandler unsuccessfulResponseHandler) {
        this.unsuccessfulResponseHandler = unsuccessfulResponseHandler;
        return this;
    }

    public HttpRequest setParser(ObjectParser parser) {
        this.objectParser = parser;
        return this;
    }

    public final ObjectParser getParser() {
        return this.objectParser;
    }

    public boolean getFollowRedirects() {
        return this.followRedirects;
    }

    public HttpRequest setThrowExceptionOnExecuteError(boolean throwExceptionOnExecuteError) {
        this.throwExceptionOnExecuteError = throwExceptionOnExecuteError;
        return this;
    }

    /* JADX WARNING: Removed duplicated region for block: B:89:0x02fa A:{SYNTHETIC, Splitter:B:89:0x02fa} */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x0460  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x045a  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x046c  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0348  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x02fa A:{SYNTHETIC, Splitter:B:89:0x02fa} */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x045a  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x0460  */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x0346 A:{SYNTHETIC} */
    public com.google.api.client.http.HttpResponse execute() throws java.io.IOException {
        /*
        r40 = this;
        r30 = 0;
        r0 = r40;
        r0 = r0.numRetries;
        r34 = r0;
        if (r34 < 0) goto L_0x0349;
    L_0x000a:
        r34 = 1;
    L_0x000c:
        com.google.api.client.util.Preconditions.checkArgument(r34);
        r0 = r40;
        r0 = r0.numRetries;
        r29 = r0;
        r0 = r40;
        r0 = r0.backOffPolicy;
        r34 = r0;
        if (r34 == 0) goto L_0x0026;
    L_0x001d:
        r0 = r40;
        r0 = r0.backOffPolicy;
        r34 = r0;
        r34.reset();
    L_0x0026:
        r25 = 0;
        r0 = r40;
        r0 = r0.requestMethod;
        r34 = r0;
        com.google.api.client.util.Preconditions.checkNotNull(r34);
        r0 = r40;
        r0 = r0.url;
        r34 = r0;
        com.google.api.client.util.Preconditions.checkNotNull(r34);
    L_0x003a:
        if (r25 == 0) goto L_0x003f;
    L_0x003c:
        r25.ignore();
    L_0x003f:
        r25 = 0;
        r16 = 0;
        r0 = r40;
        r0 = r0.executeInterceptor;
        r34 = r0;
        if (r34 == 0) goto L_0x0058;
    L_0x004b:
        r0 = r40;
        r0 = r0.executeInterceptor;
        r34 = r0;
        r0 = r34;
        r1 = r40;
        r0.intercept(r1);
    L_0x0058:
        r0 = r40;
        r0 = r0.url;
        r34 = r0;
        r33 = r34.build();
        r0 = r40;
        r0 = r0.transport;
        r34 = r0;
        r0 = r40;
        r0 = r0.requestMethod;
        r35 = r0;
        r0 = r34;
        r1 = r35;
        r2 = r33;
        r22 = r0.buildRequest(r1, r2);
        r20 = com.google.api.client.http.HttpTransport.LOGGER;
        r0 = r40;
        r0 = r0.loggingEnabled;
        r34 = r0;
        if (r34 == 0) goto L_0x034d;
    L_0x0082:
        r34 = java.util.logging.Level.CONFIG;
        r0 = r20;
        r1 = r34;
        r34 = r0.isLoggable(r1);
        if (r34 == 0) goto L_0x034d;
    L_0x008e:
        r19 = 1;
    L_0x0090:
        r18 = 0;
        r13 = 0;
        if (r19 == 0) goto L_0x00fa;
    L_0x0095:
        r18 = new java.lang.StringBuilder;
        r18.<init>();
        r34 = "-------------- REQUEST  --------------";
        r0 = r18;
        r1 = r34;
        r34 = r0.append(r1);
        r35 = com.google.api.client.util.StringUtils.LINE_SEPARATOR;
        r34.append(r35);
        r0 = r40;
        r0 = r0.requestMethod;
        r34 = r0;
        r0 = r18;
        r1 = r34;
        r34 = r0.append(r1);
        r35 = 32;
        r34 = r34.append(r35);
        r0 = r34;
        r1 = r33;
        r34 = r0.append(r1);
        r35 = com.google.api.client.util.StringUtils.LINE_SEPARATOR;
        r34.append(r35);
        r0 = r40;
        r0 = r0.curlLoggingEnabled;
        r34 = r0;
        if (r34 == 0) goto L_0x00fa;
    L_0x00d2:
        r13 = new java.lang.StringBuilder;
        r34 = "curl -v --compressed";
        r0 = r34;
        r13.<init>(r0);
        r0 = r40;
        r0 = r0.requestMethod;
        r34 = r0;
        r35 = "GET";
        r34 = r34.equals(r35);
        if (r34 != 0) goto L_0x00fa;
    L_0x00e9:
        r34 = " -X ";
        r0 = r34;
        r34 = r13.append(r0);
        r0 = r40;
        r0 = r0.requestMethod;
        r35 = r0;
        r34.append(r35);
    L_0x00fa:
        r0 = r40;
        r0 = r0.headers;
        r34 = r0;
        r24 = r34.getUserAgent();
        r0 = r40;
        r0 = r0.suppressUserAgentSuffix;
        r34 = r0;
        if (r34 != 0) goto L_0x0119;
    L_0x010c:
        if (r24 != 0) goto L_0x0351;
    L_0x010e:
        r0 = r40;
        r0 = r0.headers;
        r34 = r0;
        r35 = "Google-HTTP-Java-Client/1.20.0 (gzip)";
        r34.setUserAgent(r35);
    L_0x0119:
        r0 = r40;
        r0 = r0.headers;
        r34 = r0;
        r0 = r34;
        r1 = r18;
        r2 = r20;
        r3 = r22;
        com.google.api.client.http.HttpHeaders.serializeHeaders(r0, r1, r13, r2, r3);
        r0 = r40;
        r0 = r0.suppressUserAgentSuffix;
        r34 = r0;
        if (r34 != 0) goto L_0x013f;
    L_0x0132:
        r0 = r40;
        r0 = r0.headers;
        r34 = r0;
        r0 = r34;
        r1 = r24;
        r0.setUserAgent(r1);
    L_0x013f:
        r0 = r40;
        r0 = r0.content;
        r31 = r0;
        if (r31 == 0) goto L_0x0153;
    L_0x0147:
        r0 = r40;
        r0 = r0.content;
        r34 = r0;
        r34 = r34.retrySupported();
        if (r34 == 0) goto L_0x0399;
    L_0x0153:
        r9 = 1;
    L_0x0154:
        if (r31 == 0) goto L_0x027d;
    L_0x0156:
        r0 = r40;
        r0 = r0.content;
        r34 = r0;
        r12 = r34.getType();
        if (r19 == 0) goto L_0x017d;
    L_0x0162:
        r32 = new com.google.api.client.util.LoggingStreamingContent;
        r34 = com.google.api.client.http.HttpTransport.LOGGER;
        r35 = java.util.logging.Level.CONFIG;
        r0 = r40;
        r0 = r0.contentLoggingLimit;
        r36 = r0;
        r0 = r32;
        r1 = r31;
        r2 = r34;
        r3 = r35;
        r4 = r36;
        r0.<init>(r1, r2, r3, r4);
        r31 = r32;
    L_0x017d:
        r0 = r40;
        r0 = r0.encoding;
        r34 = r0;
        if (r34 != 0) goto L_0x039c;
    L_0x0185:
        r8 = 0;
        r0 = r40;
        r0 = r0.content;
        r34 = r0;
        r10 = r34.getLength();
    L_0x0190:
        if (r19 == 0) goto L_0x025e;
    L_0x0192:
        if (r12 == 0) goto L_0x01e3;
    L_0x0194:
        r34 = "Content-Type: ";
        r35 = java.lang.String.valueOf(r12);
        r36 = r35.length();
        if (r36 == 0) goto L_0x03c4;
    L_0x01a0:
        r17 = r34.concat(r35);
    L_0x01a4:
        r0 = r18;
        r1 = r17;
        r34 = r0.append(r1);
        r35 = com.google.api.client.util.StringUtils.LINE_SEPARATOR;
        r34.append(r35);
        if (r13 == 0) goto L_0x01e3;
    L_0x01b3:
        r34 = java.lang.String.valueOf(r17);
        r34 = java.lang.String.valueOf(r34);
        r35 = new java.lang.StringBuilder;
        r36 = r34.length();
        r36 = r36 + 6;
        r35.<init>(r36);
        r36 = " -H '";
        r35 = r35.append(r36);
        r0 = r35;
        r1 = r34;
        r34 = r0.append(r1);
        r35 = "'";
        r34 = r34.append(r35);
        r34 = r34.toString();
        r0 = r34;
        r13.append(r0);
    L_0x01e3:
        if (r8 == 0) goto L_0x0234;
    L_0x01e5:
        r34 = "Content-Encoding: ";
        r35 = java.lang.String.valueOf(r8);
        r36 = r35.length();
        if (r36 == 0) goto L_0x03cf;
    L_0x01f1:
        r17 = r34.concat(r35);
    L_0x01f5:
        r0 = r18;
        r1 = r17;
        r34 = r0.append(r1);
        r35 = com.google.api.client.util.StringUtils.LINE_SEPARATOR;
        r34.append(r35);
        if (r13 == 0) goto L_0x0234;
    L_0x0204:
        r34 = java.lang.String.valueOf(r17);
        r34 = java.lang.String.valueOf(r34);
        r35 = new java.lang.StringBuilder;
        r36 = r34.length();
        r36 = r36 + 6;
        r35.<init>(r36);
        r36 = " -H '";
        r35 = r35.append(r36);
        r0 = r35;
        r1 = r34;
        r34 = r0.append(r1);
        r35 = "'";
        r34 = r34.append(r35);
        r34 = r34.toString();
        r0 = r34;
        r13.append(r0);
    L_0x0234:
        r34 = 0;
        r34 = (r10 > r34 ? 1 : (r10 == r34 ? 0 : -1));
        if (r34 < 0) goto L_0x025e;
    L_0x023a:
        r34 = new java.lang.StringBuilder;
        r35 = 36;
        r34.<init>(r35);
        r35 = "Content-Length: ";
        r34 = r34.append(r35);
        r0 = r34;
        r34 = r0.append(r10);
        r17 = r34.toString();
        r0 = r18;
        r1 = r17;
        r34 = r0.append(r1);
        r35 = com.google.api.client.util.StringUtils.LINE_SEPARATOR;
        r34.append(r35);
    L_0x025e:
        if (r13 == 0) goto L_0x0267;
    L_0x0260:
        r34 = " -d '@-'";
        r0 = r34;
        r13.append(r0);
    L_0x0267:
        r0 = r22;
        r0.setContentType(r12);
        r0 = r22;
        r0.setContentEncoding(r8);
        r0 = r22;
        r0.setContentLength(r10);
        r0 = r22;
        r1 = r31;
        r0.setStreamingContent(r1);
    L_0x027d:
        if (r19 == 0) goto L_0x02bb;
    L_0x027f:
        r34 = r18.toString();
        r0 = r20;
        r1 = r34;
        r0.config(r1);
        if (r13 == 0) goto L_0x02bb;
    L_0x028c:
        r34 = " -- '";
        r0 = r34;
        r13.append(r0);
        r34 = "'";
        r35 = "'\"'\"'";
        r34 = r33.replaceAll(r34, r35);
        r0 = r34;
        r13.append(r0);
        r34 = "'";
        r0 = r34;
        r13.append(r0);
        if (r31 == 0) goto L_0x02b0;
    L_0x02a9:
        r34 = " << $$$";
        r0 = r34;
        r13.append(r0);
    L_0x02b0:
        r34 = r13.toString();
        r0 = r20;
        r1 = r34;
        r0.config(r1);
    L_0x02bb:
        if (r9 == 0) goto L_0x03da;
    L_0x02bd:
        if (r29 <= 0) goto L_0x03da;
    L_0x02bf:
        r30 = 1;
    L_0x02c1:
        r0 = r40;
        r0 = r0.connectTimeout;
        r34 = r0;
        r0 = r40;
        r0 = r0.readTimeout;
        r35 = r0;
        r0 = r22;
        r1 = r34;
        r2 = r35;
        r0.setTimeout(r1, r2);
        r23 = r22.execute();	 Catch:{ IOException -> 0x03eb }
        r27 = 0;
        r26 = new com.google.api.client.http.HttpResponse;	 Catch:{ all -> 0x03de }
        r0 = r26;
        r1 = r40;
        r2 = r23;
        r0.<init>(r1, r2);	 Catch:{ all -> 0x03de }
        r27 = 1;
        if (r27 != 0) goto L_0x02f4;
    L_0x02eb:
        r21 = r23.getContent();	 Catch:{ IOException -> 0x04a1 }
        if (r21 == 0) goto L_0x02f4;
    L_0x02f1:
        r21.close();	 Catch:{ IOException -> 0x04a1 }
    L_0x02f4:
        r25 = r26;
    L_0x02f6:
        r28 = 0;
        if (r25 == 0) goto L_0x0458;
    L_0x02fa:
        r34 = r25.isSuccessStatusCode();	 Catch:{ all -> 0x0463 }
        if (r34 != 0) goto L_0x0458;
    L_0x0300:
        r15 = 0;
        r0 = r40;
        r0 = r0.unsuccessfulResponseHandler;	 Catch:{ all -> 0x0463 }
        r34 = r0;
        if (r34 == 0) goto L_0x031b;
    L_0x0309:
        r0 = r40;
        r0 = r0.unsuccessfulResponseHandler;	 Catch:{ all -> 0x0463 }
        r34 = r0;
        r0 = r34;
        r1 = r40;
        r2 = r25;
        r3 = r30;
        r15 = r0.handleResponse(r1, r2, r3);	 Catch:{ all -> 0x0463 }
    L_0x031b:
        if (r15 != 0) goto L_0x0332;
    L_0x031d:
        r34 = r25.getStatusCode();	 Catch:{ all -> 0x0463 }
        r35 = r25.getHeaders();	 Catch:{ all -> 0x0463 }
        r0 = r40;
        r1 = r34;
        r2 = r35;
        r34 = r0.handleRedirect(r1, r2);	 Catch:{ all -> 0x0463 }
        if (r34 == 0) goto L_0x0420;
    L_0x0331:
        r15 = 1;
    L_0x0332:
        r30 = r30 & r15;
        if (r30 == 0) goto L_0x0339;
    L_0x0336:
        r25.ignore();	 Catch:{ all -> 0x0463 }
    L_0x0339:
        r29 = r29 + -1;
        r28 = 1;
        if (r25 == 0) goto L_0x0344;
    L_0x033f:
        if (r28 != 0) goto L_0x0344;
    L_0x0341:
        r25.disconnect();
    L_0x0344:
        if (r30 != 0) goto L_0x003a;
    L_0x0346:
        if (r25 != 0) goto L_0x046c;
    L_0x0348:
        throw r16;
    L_0x0349:
        r34 = 0;
        goto L_0x000c;
    L_0x034d:
        r19 = 0;
        goto L_0x0090;
    L_0x0351:
        r0 = r40;
        r0 = r0.headers;
        r34 = r0;
        r35 = java.lang.String.valueOf(r24);
        r35 = java.lang.String.valueOf(r35);
        r36 = "Google-HTTP-Java-Client/1.20.0 (gzip)";
        r36 = java.lang.String.valueOf(r36);
        r36 = java.lang.String.valueOf(r36);
        r37 = new java.lang.StringBuilder;
        r38 = r35.length();
        r38 = r38 + 1;
        r39 = r36.length();
        r38 = r38 + r39;
        r37.<init>(r38);
        r0 = r37;
        r1 = r35;
        r35 = r0.append(r1);
        r37 = " ";
        r0 = r35;
        r1 = r37;
        r35 = r0.append(r1);
        r35 = r35.append(r36);
        r35 = r35.toString();
        r34.setUserAgent(r35);
        goto L_0x0119;
    L_0x0399:
        r9 = 0;
        goto L_0x0154;
    L_0x039c:
        r0 = r40;
        r0 = r0.encoding;
        r34 = r0;
        r8 = r34.getName();
        r32 = new com.google.api.client.http.HttpEncodingStreamingContent;
        r0 = r40;
        r0 = r0.encoding;
        r34 = r0;
        r0 = r32;
        r1 = r31;
        r2 = r34;
        r0.<init>(r1, r2);
        if (r9 == 0) goto L_0x03c1;
    L_0x03b9:
        r10 = com.google.api.client.util.IOUtils.computeLength(r32);
    L_0x03bd:
        r31 = r32;
        goto L_0x0190;
    L_0x03c1:
        r10 = -1;
        goto L_0x03bd;
    L_0x03c4:
        r17 = new java.lang.String;
        r0 = r17;
        r1 = r34;
        r0.<init>(r1);
        goto L_0x01a4;
    L_0x03cf:
        r17 = new java.lang.String;
        r0 = r17;
        r1 = r34;
        r0.<init>(r1);
        goto L_0x01f5;
    L_0x03da:
        r30 = 0;
        goto L_0x02c1;
    L_0x03de:
        r34 = move-exception;
        if (r27 != 0) goto L_0x03ea;
    L_0x03e1:
        r21 = r23.getContent();	 Catch:{ IOException -> 0x03eb }
        if (r21 == 0) goto L_0x03ea;
    L_0x03e7:
        r21.close();	 Catch:{ IOException -> 0x03eb }
    L_0x03ea:
        throw r34;	 Catch:{ IOException -> 0x03eb }
    L_0x03eb:
        r14 = move-exception;
    L_0x03ec:
        r0 = r40;
        r0 = r0.retryOnExecuteIOException;
        r34 = r0;
        if (r34 != 0) goto L_0x040f;
    L_0x03f4:
        r0 = r40;
        r0 = r0.ioExceptionHandler;
        r34 = r0;
        if (r34 == 0) goto L_0x040e;
    L_0x03fc:
        r0 = r40;
        r0 = r0.ioExceptionHandler;
        r34 = r0;
        r0 = r34;
        r1 = r40;
        r2 = r30;
        r34 = r0.handleIOException(r1, r2);
        if (r34 != 0) goto L_0x040f;
    L_0x040e:
        throw r14;
    L_0x040f:
        r16 = r14;
        r34 = java.util.logging.Level.WARNING;
        r35 = "exception thrown while executing request";
        r0 = r20;
        r1 = r34;
        r2 = r35;
        r0.log(r1, r2, r14);
        goto L_0x02f6;
    L_0x0420:
        if (r30 == 0) goto L_0x0332;
    L_0x0422:
        r0 = r40;
        r0 = r0.backOffPolicy;	 Catch:{ all -> 0x0463 }
        r34 = r0;
        if (r34 == 0) goto L_0x0332;
    L_0x042a:
        r0 = r40;
        r0 = r0.backOffPolicy;	 Catch:{ all -> 0x0463 }
        r34 = r0;
        r35 = r25.getStatusCode();	 Catch:{ all -> 0x0463 }
        r34 = r34.isBackOffRequired(r35);	 Catch:{ all -> 0x0463 }
        if (r34 == 0) goto L_0x0332;
    L_0x043a:
        r0 = r40;
        r0 = r0.backOffPolicy;	 Catch:{ all -> 0x0463 }
        r34 = r0;
        r6 = r34.getNextBackOffMillis();	 Catch:{ all -> 0x0463 }
        r34 = -1;
        r34 = (r6 > r34 ? 1 : (r6 == r34 ? 0 : -1));
        if (r34 == 0) goto L_0x0332;
    L_0x044a:
        r0 = r40;
        r0 = r0.sleeper;	 Catch:{ InterruptedException -> 0x049f }
        r34 = r0;
        r0 = r34;
        r0.sleep(r6);	 Catch:{ InterruptedException -> 0x049f }
    L_0x0455:
        r15 = 1;
        goto L_0x0332;
    L_0x0458:
        if (r25 != 0) goto L_0x0460;
    L_0x045a:
        r34 = 1;
    L_0x045c:
        r30 = r30 & r34;
        goto L_0x0339;
    L_0x0460:
        r34 = 0;
        goto L_0x045c;
    L_0x0463:
        r34 = move-exception;
        if (r25 == 0) goto L_0x046b;
    L_0x0466:
        if (r28 != 0) goto L_0x046b;
    L_0x0468:
        r25.disconnect();
    L_0x046b:
        throw r34;
    L_0x046c:
        r0 = r40;
        r0 = r0.responseInterceptor;
        r34 = r0;
        if (r34 == 0) goto L_0x0481;
    L_0x0474:
        r0 = r40;
        r0 = r0.responseInterceptor;
        r34 = r0;
        r0 = r34;
        r1 = r25;
        r0.interceptResponse(r1);
    L_0x0481:
        r0 = r40;
        r0 = r0.throwExceptionOnExecuteError;
        r34 = r0;
        if (r34 == 0) goto L_0x049e;
    L_0x0489:
        r34 = r25.isSuccessStatusCode();
        if (r34 != 0) goto L_0x049e;
    L_0x048f:
        r34 = new com.google.api.client.http.HttpResponseException;	 Catch:{ all -> 0x0499 }
        r0 = r34;
        r1 = r25;
        r0.<init>(r1);	 Catch:{ all -> 0x0499 }
        throw r34;	 Catch:{ all -> 0x0499 }
    L_0x0499:
        r34 = move-exception;
        r25.disconnect();
        throw r34;
    L_0x049e:
        return r25;
    L_0x049f:
        r34 = move-exception;
        goto L_0x0455;
    L_0x04a1:
        r14 = move-exception;
        r25 = r26;
        goto L_0x03ec;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.client.http.HttpRequest.execute():com.google.api.client.http.HttpResponse");
    }

    public boolean handleRedirect(int statusCode, HttpHeaders responseHeaders) {
        String redirectLocation = responseHeaders.getLocation();
        if (!getFollowRedirects() || !HttpStatusCodes.isRedirect(statusCode) || redirectLocation == null) {
            return false;
        }
        setUrl(new GenericUrl(this.url.toURL(redirectLocation)));
        if (statusCode == 303) {
            setRequestMethod("GET");
            setContent(null);
        }
        this.headers.setAuthorization((String) null);
        this.headers.setIfMatch((String) null);
        this.headers.setIfNoneMatch((String) null);
        this.headers.setIfModifiedSince((String) null);
        this.headers.setIfUnmodifiedSince((String) null);
        this.headers.setIfRange((String) null);
        return true;
    }
}
