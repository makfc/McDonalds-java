package com.alipay.android.phone.mrpc.core;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieManager;
import com.facebook.internal.ServerProtocol;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.Callable;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

/* renamed from: com.alipay.android.phone.mrpc.core.q */
public final class C0543q implements Callable<C0541u> {
    /* renamed from: e */
    private static final HttpRequestRetryHandler f381e = new C0521ad();
    /* renamed from: a */
    protected C0536l f382a;
    /* renamed from: b */
    protected Context f383b;
    /* renamed from: c */
    protected C0540o f384c;
    /* renamed from: d */
    String f385d;
    /* renamed from: f */
    private HttpUriRequest f386f;
    /* renamed from: g */
    private HttpContext f387g = new BasicHttpContext();
    /* renamed from: h */
    private CookieStore f388h = new BasicCookieStore();
    /* renamed from: i */
    private CookieManager f389i;
    /* renamed from: j */
    private AbstractHttpEntity f390j;
    /* renamed from: k */
    private HttpHost f391k;
    /* renamed from: l */
    private URL f392l;
    /* renamed from: m */
    private int f393m = 0;
    /* renamed from: n */
    private boolean f394n = false;
    /* renamed from: o */
    private boolean f395o = false;
    /* renamed from: p */
    private String f396p = null;
    /* renamed from: q */
    private String f397q;

    public C0543q(C0536l c0536l, C0540o c0540o) {
        this.f382a = c0536l;
        this.f383b = this.f382a.f355a;
        this.f384c = c0540o;
    }

    /* renamed from: a */
    private static long m611a(String[] strArr) {
        int i = 0;
        while (i < strArr.length) {
            if ("max-age".equalsIgnoreCase(strArr[i]) && strArr[i + 1] != null) {
                try {
                    return Long.parseLong(strArr[i + 1]);
                } catch (Exception e) {
                }
            }
            i++;
        }
        return 0;
    }

    /* renamed from: a */
    private static HttpUrlHeader m612a(HttpResponse httpResponse) {
        HttpUrlHeader httpUrlHeader = new HttpUrlHeader();
        for (Header header : httpResponse.getAllHeaders()) {
            httpUrlHeader.setHead(header.getName(), header.getValue());
        }
        return httpUrlHeader;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x00bf A:{SYNTHETIC, Splitter:B:21:0x00bf} */
    /* renamed from: a */
    private com.alipay.android.phone.mrpc.core.C0541u m613a(org.apache.http.HttpResponse r10, int r11, java.lang.String r12) {
        /*
        r9 = this;
        r0 = 0;
        r1 = new java.lang.StringBuilder;
        r2 = "开始handle，handleResponse-1,";
        r1.<init>(r2);
        r2 = java.lang.Thread.currentThread();
        r2 = r2.getId();
        r1.append(r2);
        r1 = r10.getEntity();
        if (r1 == 0) goto L_0x00d0;
    L_0x001a:
        r2 = r10.getStatusLine();
        r2 = r2.getStatusCode();
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r2 != r3) goto L_0x00d0;
    L_0x0026:
        r2 = new java.lang.StringBuilder;
        r3 = "200，开始处理，handleResponse-2,threadid = ";
        r2.<init>(r3);
        r3 = java.lang.Thread.currentThread();
        r4 = r3.getId();
        r2.append(r4);
        r3 = new java.io.ByteArrayOutputStream;	 Catch:{ all -> 0x00b9 }
        r3.<init>();	 Catch:{ all -> 0x00b9 }
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x00da }
        r9.m615a(r1, r3);	 Catch:{ all -> 0x00da }
        r1 = r3.toByteArray();	 Catch:{ all -> 0x00da }
        r2 = 0;
        r9.f395o = r2;	 Catch:{ all -> 0x00da }
        r2 = r9.f382a;	 Catch:{ all -> 0x00da }
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x00da }
        r4 = r6 - r4;
        r2.mo7900c(r4);	 Catch:{ all -> 0x00da }
        r2 = r9.f382a;	 Catch:{ all -> 0x00da }
        r4 = r1.length;	 Catch:{ all -> 0x00da }
        r4 = (long) r4;	 Catch:{ all -> 0x00da }
        r2.mo7898a(r4);	 Catch:{ all -> 0x00da }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00da }
        r4 = "res:";
        r2.<init>(r4);	 Catch:{ all -> 0x00da }
        r4 = r1.length;	 Catch:{ all -> 0x00da }
        r2.append(r4);	 Catch:{ all -> 0x00da }
        r2 = new com.alipay.android.phone.mrpc.core.p;	 Catch:{ all -> 0x00da }
        r4 = com.alipay.android.phone.mrpc.core.C0543q.m612a(r10);	 Catch:{ all -> 0x00da }
        r2.<init>(r4, r11, r12, r1);	 Catch:{ all -> 0x00da }
        r4 = com.alipay.android.phone.mrpc.core.C0543q.m616b(r10);	 Catch:{ all -> 0x00da }
        r1 = r10.getEntity();	 Catch:{ all -> 0x00da }
        r1 = r1.getContentType();	 Catch:{ all -> 0x00da }
        if (r1 == 0) goto L_0x00dd;
    L_0x007f:
        r0 = r1.getValue();	 Catch:{ all -> 0x00da }
        r1 = com.alipay.android.phone.mrpc.core.C0543q.m614a(r0);	 Catch:{ all -> 0x00da }
        r0 = "charset";
        r0 = r1.get(r0);	 Catch:{ all -> 0x00da }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x00da }
        r6 = "Content-Type";
        r1 = r1.get(r6);	 Catch:{ all -> 0x00da }
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x00da }
    L_0x0097:
        r2.mo7920b(r1);	 Catch:{ all -> 0x00da }
        r2.mo7924a(r0);	 Catch:{ all -> 0x00da }
        r0 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x00da }
        r2.mo7923a(r0);	 Catch:{ all -> 0x00da }
        r2.mo7925b(r4);	 Catch:{ all -> 0x00da }
        r3.close();	 Catch:{ IOException -> 0x00ac }
        r0 = r2;
    L_0x00ab:
        return r0;
    L_0x00ac:
        r0 = move-exception;
        r1 = new java.lang.RuntimeException;
        r2 = "ArrayOutputStream close error!";
        r0 = r0.getCause();
        r1.<init>(r2, r0);
        throw r1;
    L_0x00b9:
        r1 = move-exception;
        r8 = r1;
        r1 = r0;
        r0 = r8;
    L_0x00bd:
        if (r1 == 0) goto L_0x00c2;
    L_0x00bf:
        r1.close();	 Catch:{ IOException -> 0x00c3 }
    L_0x00c2:
        throw r0;
    L_0x00c3:
        r0 = move-exception;
        r1 = new java.lang.RuntimeException;
        r2 = "ArrayOutputStream close error!";
        r0 = r0.getCause();
        r1.<init>(r2, r0);
        throw r1;
    L_0x00d0:
        if (r1 != 0) goto L_0x00ab;
    L_0x00d2:
        r1 = r10.getStatusLine();
        r1.getStatusCode();
        goto L_0x00ab;
    L_0x00da:
        r0 = move-exception;
        r1 = r3;
        goto L_0x00bd;
    L_0x00dd:
        r1 = r0;
        goto L_0x0097;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.android.phone.mrpc.core.C0543q.m613a(org.apache.http.HttpResponse, int, java.lang.String):com.alipay.android.phone.mrpc.core.u");
    }

    /* renamed from: a */
    private static HashMap<String, String> m614a(String str) {
        HashMap hashMap = new HashMap();
        for (String str2 : str.split(";")) {
            String[] split = str2.indexOf(61) == -1 ? new String[]{TransactionStateUtil.CONTENT_TYPE_HEADER, str2} : str2.split("=");
            hashMap.put(split[0], split[1]);
        }
        return hashMap;
    }

    /* renamed from: a */
    private void m615a(HttpEntity httpEntity, OutputStream outputStream) {
        InputStream a = C0524b.m558a(httpEntity);
        long contentLength = httpEntity.getContentLength();
        try {
            byte[] bArr = new byte[2048];
            while (true) {
                int read = a.read(bArr);
                if (read == -1 || this.f384c.mo7905h()) {
                    outputStream.flush();
                } else {
                    outputStream.write(bArr, 0, read);
                    if (this.f384c.mo7903f() != null && contentLength > 0) {
                        this.f384c.mo7903f();
                        C0540o c0540o = this.f384c;
                    }
                }
            }
            outputStream.flush();
            C0544r.m626a(a);
        } catch (Exception e) {
            e.getCause();
            throw new IOException("HttpWorker Request Error!" + e.getLocalizedMessage());
        } catch (Throwable th) {
            C0544r.m626a(a);
        }
    }

    /* renamed from: b */
    private static long m616b(HttpResponse httpResponse) {
        long j = 0;
        Header firstHeader = httpResponse.getFirstHeader("Cache-Control");
        if (firstHeader != null) {
            String[] split = firstHeader.getValue().split("=");
            if (split.length >= 2) {
                try {
                    return C0543q.m611a(split);
                } catch (NumberFormatException e) {
                }
            }
        }
        firstHeader = httpResponse.getFirstHeader("Expires");
        return firstHeader != null ? C0524b.m563b(firstHeader.getValue()) - System.currentTimeMillis() : j;
    }

    /* renamed from: b */
    private URI m617b() {
        String a = this.f384c.mo7906a();
        if (this.f385d != null) {
            a = this.f385d;
        }
        if (a != null) {
            return new URI(a);
        }
        throw new RuntimeException("url should not be null");
    }

    /* renamed from: c */
    private HttpUriRequest m618c() {
        if (this.f386f != null) {
            return this.f386f;
        }
        if (this.f390j == null) {
            byte[] b = this.f384c.mo7913b();
            String b2 = this.f384c.mo7912b("gzip");
            if (b != null) {
                if (TextUtils.equals(b2, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) {
                    this.f390j = C0524b.m561a(b);
                } else {
                    this.f390j = new ByteArrayEntity(b);
                }
                this.f390j.setContentType(this.f384c.mo7914c());
            }
        }
        AbstractHttpEntity abstractHttpEntity = this.f390j;
        if (abstractHttpEntity != null) {
            HttpPost httpPost = new HttpPost(m617b());
            httpPost.setEntity(abstractHttpEntity);
            this.f386f = httpPost;
        } else {
            this.f386f = new HttpGet(m617b());
        }
        return this.f386f;
    }

    /* JADX WARNING: Removed duplicated region for block: B:84:0x0237 A:{SYNTHETIC, Splitter:B:84:0x0237} */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0151 A:{Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }} */
    /* JADX WARNING: Removed duplicated region for block: B:184:0x04f8  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x015b A:{Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }} */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0252 A:{Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }} */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0177 A:{Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }} */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0195 A:{Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }} */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x01a2 A:{Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }} */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0274 A:{Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }} */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025 A:{ExcHandler: HttpException (r2_88 'e' com.alipay.android.phone.mrpc.core.HttpException), Splitter:B:40:0x00a2} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0095 A:{ExcHandler: URISyntaxException (r2_89 'e' java.net.URISyntaxException), Splitter:B:1:0x0005} */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0206 A:{ExcHandler: SSLHandshakeException (r2_91 'e' javax.net.ssl.SSLHandshakeException), Splitter:B:1:0x0005} */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x0295 A:{ExcHandler: SSLPeerUnverifiedException (r2_93 'e' javax.net.ssl.SSLPeerUnverifiedException), Splitter:B:1:0x0005} */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x0320 A:{ExcHandler: SSLException (r2_95 'e' javax.net.ssl.SSLException), Splitter:B:1:0x0005} */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x0351 A:{ExcHandler: ConnectionPoolTimeoutException (r2_97 'e' org.apache.http.conn.ConnectionPoolTimeoutException), Splitter:B:1:0x0005} */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x0382 A:{ExcHandler: ConnectTimeoutException (r2_99 'e' org.apache.http.conn.ConnectTimeoutException), Splitter:B:1:0x0005} */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x03b3 A:{ExcHandler: SocketTimeoutException (r2_101 'e' java.net.SocketTimeoutException), Splitter:B:1:0x0005} */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x03e5 A:{ExcHandler: NoHttpResponseException (r2_103 'e' org.apache.http.NoHttpResponseException), Splitter:B:1:0x0005} */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x0417 A:{ExcHandler: HttpHostConnectException (r2_105 'e' org.apache.http.conn.HttpHostConnectException), Splitter:B:1:0x0005} */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x0442 A:{ExcHandler: UnknownHostException (r2_107 'e' java.net.UnknownHostException), Splitter:B:1:0x0005} */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x0475 A:{ExcHandler: IOException (r2_109 'e' java.io.IOException), Splitter:B:1:0x0005} */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x04a6 A:{ExcHandler: NullPointerException (r2_111 'e' java.lang.NullPointerException), Splitter:B:1:0x0005} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:8:0x0025, code skipped:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:9:0x0026, code skipped:
            m620e();
     */
    /* JADX WARNING: Missing block: B:10:0x002f, code skipped:
            if (r15.f384c.mo7903f() != null) goto L_0x0031;
     */
    /* JADX WARNING: Missing block: B:11:0x0031, code skipped:
            r15.f384c.mo7903f();
            r3 = r15.f384c;
            r2.getCode();
            r2.getMsg();
     */
    /* JADX WARNING: Missing block: B:12:0x003e, code skipped:
            new java.lang.StringBuilder().append(r2);
     */
    /* JADX WARNING: Missing block: B:13:0x0046, code skipped:
            throw r2;
     */
    /* JADX WARNING: Missing block: B:37:0x0095, code skipped:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:39:0x00a1, code skipped:
            throw new java.lang.RuntimeException("Url parser error!", r2.getCause());
     */
    /* JADX WARNING: Missing block: B:78:0x0206, code skipped:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:79:0x0207, code skipped:
            m620e();
     */
    /* JADX WARNING: Missing block: B:80:0x0210, code skipped:
            if (r15.f384c.mo7903f() != null) goto L_0x0212;
     */
    /* JADX WARNING: Missing block: B:81:0x0212, code skipped:
            r15.f384c.mo7903f();
            r3 = r15.f384c;
            new java.lang.StringBuilder().append(r2);
     */
    /* JADX WARNING: Missing block: B:82:0x0221, code skipped:
            new java.lang.StringBuilder().append(r2);
     */
    /* JADX WARNING: Missing block: B:83:0x0236, code skipped:
            throw new com.alipay.android.phone.mrpc.core.HttpException(java.lang.Integer.valueOf(2), java.lang.String.valueOf(r2));
     */
    /* JADX WARNING: Missing block: B:96:0x0295, code skipped:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:97:0x0296, code skipped:
            m620e();
     */
    /* JADX WARNING: Missing block: B:98:0x029f, code skipped:
            if (r15.f384c.mo7903f() != null) goto L_0x02a1;
     */
    /* JADX WARNING: Missing block: B:99:0x02a1, code skipped:
            r15.f384c.mo7903f();
            r3 = r15.f384c;
            new java.lang.StringBuilder().append(r2);
     */
    /* JADX WARNING: Missing block: B:100:0x02b0, code skipped:
            new java.lang.StringBuilder().append(r2);
     */
    /* JADX WARNING: Missing block: B:101:0x02c5, code skipped:
            throw new com.alipay.android.phone.mrpc.core.HttpException(java.lang.Integer.valueOf(2), java.lang.String.valueOf(r2));
     */
    /* JADX WARNING: Missing block: B:123:0x0320, code skipped:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:124:0x0321, code skipped:
            m620e();
     */
    /* JADX WARNING: Missing block: B:125:0x032a, code skipped:
            if (r15.f384c.mo7903f() != null) goto L_0x032c;
     */
    /* JADX WARNING: Missing block: B:126:0x032c, code skipped:
            r15.f384c.mo7903f();
            r3 = r15.f384c;
            new java.lang.StringBuilder().append(r2);
     */
    /* JADX WARNING: Missing block: B:127:0x033b, code skipped:
            new java.lang.StringBuilder().append(r2);
     */
    /* JADX WARNING: Missing block: B:128:0x0350, code skipped:
            throw new com.alipay.android.phone.mrpc.core.HttpException(java.lang.Integer.valueOf(6), java.lang.String.valueOf(r2));
     */
    /* JADX WARNING: Missing block: B:129:0x0351, code skipped:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:130:0x0352, code skipped:
            m620e();
     */
    /* JADX WARNING: Missing block: B:131:0x035b, code skipped:
            if (r15.f384c.mo7903f() != null) goto L_0x035d;
     */
    /* JADX WARNING: Missing block: B:132:0x035d, code skipped:
            r15.f384c.mo7903f();
            r3 = r15.f384c;
            new java.lang.StringBuilder().append(r2);
     */
    /* JADX WARNING: Missing block: B:133:0x036c, code skipped:
            new java.lang.StringBuilder().append(r2);
     */
    /* JADX WARNING: Missing block: B:134:0x0381, code skipped:
            throw new com.alipay.android.phone.mrpc.core.HttpException(java.lang.Integer.valueOf(3), java.lang.String.valueOf(r2));
     */
    /* JADX WARNING: Missing block: B:135:0x0382, code skipped:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:136:0x0383, code skipped:
            m620e();
     */
    /* JADX WARNING: Missing block: B:137:0x038c, code skipped:
            if (r15.f384c.mo7903f() != null) goto L_0x038e;
     */
    /* JADX WARNING: Missing block: B:138:0x038e, code skipped:
            r15.f384c.mo7903f();
            r3 = r15.f384c;
            new java.lang.StringBuilder().append(r2);
     */
    /* JADX WARNING: Missing block: B:139:0x039d, code skipped:
            new java.lang.StringBuilder().append(r2);
     */
    /* JADX WARNING: Missing block: B:140:0x03b2, code skipped:
            throw new com.alipay.android.phone.mrpc.core.HttpException(java.lang.Integer.valueOf(3), java.lang.String.valueOf(r2));
     */
    /* JADX WARNING: Missing block: B:141:0x03b3, code skipped:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:142:0x03b4, code skipped:
            m620e();
     */
    /* JADX WARNING: Missing block: B:143:0x03bd, code skipped:
            if (r15.f384c.mo7903f() != null) goto L_0x03bf;
     */
    /* JADX WARNING: Missing block: B:144:0x03bf, code skipped:
            r15.f384c.mo7903f();
            r3 = r15.f384c;
            new java.lang.StringBuilder().append(r2);
     */
    /* JADX WARNING: Missing block: B:145:0x03ce, code skipped:
            new java.lang.StringBuilder().append(r2);
     */
    /* JADX WARNING: Missing block: B:146:0x03e4, code skipped:
            throw new com.alipay.android.phone.mrpc.core.HttpException(java.lang.Integer.valueOf(4), java.lang.String.valueOf(r2));
     */
    /* JADX WARNING: Missing block: B:147:0x03e5, code skipped:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:148:0x03e6, code skipped:
            m620e();
     */
    /* JADX WARNING: Missing block: B:149:0x03ef, code skipped:
            if (r15.f384c.mo7903f() != null) goto L_0x03f1;
     */
    /* JADX WARNING: Missing block: B:150:0x03f1, code skipped:
            r15.f384c.mo7903f();
            r3 = r15.f384c;
            new java.lang.StringBuilder().append(r2);
     */
    /* JADX WARNING: Missing block: B:151:0x0400, code skipped:
            new java.lang.StringBuilder().append(r2);
     */
    /* JADX WARNING: Missing block: B:152:0x0416, code skipped:
            throw new com.alipay.android.phone.mrpc.core.HttpException(java.lang.Integer.valueOf(5), java.lang.String.valueOf(r2));
     */
    /* JADX WARNING: Missing block: B:153:0x0417, code skipped:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:154:0x0418, code skipped:
            m620e();
     */
    /* JADX WARNING: Missing block: B:155:0x0421, code skipped:
            if (r15.f384c.mo7903f() != null) goto L_0x0423;
     */
    /* JADX WARNING: Missing block: B:156:0x0423, code skipped:
            r15.f384c.mo7903f();
            r3 = r15.f384c;
            new java.lang.StringBuilder().append(r2);
     */
    /* JADX WARNING: Missing block: B:158:0x0441, code skipped:
            throw new com.alipay.android.phone.mrpc.core.HttpException(java.lang.Integer.valueOf(8), java.lang.String.valueOf(r2));
     */
    /* JADX WARNING: Missing block: B:159:0x0442, code skipped:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:160:0x0443, code skipped:
            m620e();
     */
    /* JADX WARNING: Missing block: B:161:0x044c, code skipped:
            if (r15.f384c.mo7903f() != null) goto L_0x044e;
     */
    /* JADX WARNING: Missing block: B:162:0x044e, code skipped:
            r15.f384c.mo7903f();
            r3 = r15.f384c;
            new java.lang.StringBuilder().append(r2);
     */
    /* JADX WARNING: Missing block: B:163:0x045d, code skipped:
            new java.lang.StringBuilder().append(r2);
     */
    /* JADX WARNING: Missing block: B:164:0x0474, code skipped:
            throw new com.alipay.android.phone.mrpc.core.HttpException(java.lang.Integer.valueOf(9), java.lang.String.valueOf(r2));
     */
    /* JADX WARNING: Missing block: B:165:0x0475, code skipped:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:166:0x0476, code skipped:
            m620e();
     */
    /* JADX WARNING: Missing block: B:167:0x047f, code skipped:
            if (r15.f384c.mo7903f() != null) goto L_0x0481;
     */
    /* JADX WARNING: Missing block: B:168:0x0481, code skipped:
            r15.f384c.mo7903f();
            r3 = r15.f384c;
            new java.lang.StringBuilder().append(r2);
     */
    /* JADX WARNING: Missing block: B:169:0x0490, code skipped:
            new java.lang.StringBuilder().append(r2);
     */
    /* JADX WARNING: Missing block: B:170:0x04a5, code skipped:
            throw new com.alipay.android.phone.mrpc.core.HttpException(java.lang.Integer.valueOf(6), java.lang.String.valueOf(r2));
     */
    /* JADX WARNING: Missing block: B:171:0x04a6, code skipped:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:172:0x04a7, code skipped:
            m620e();
     */
    /* JADX WARNING: Missing block: B:173:0x04ac, code skipped:
            if (r15.f393m <= 0) goto L_0x04ae;
     */
    /* JADX WARNING: Missing block: B:174:0x04ae, code skipped:
            r15.f393m++;
     */
    /* JADX WARNING: Missing block: B:175:0x04b6, code skipped:
            new java.lang.StringBuilder().append(r2);
     */
    /* JADX WARNING: Missing block: B:176:0x04cb, code skipped:
            throw new com.alipay.android.phone.mrpc.core.HttpException(java.lang.Integer.valueOf(0), java.lang.String.valueOf(r2));
     */
    /* renamed from: d */
    private com.alipay.android.phone.mrpc.core.C0541u m619d() {
        /*
        r15 = this;
        r14 = 6;
        r13 = 3;
        r12 = 2;
        r4 = 1;
        r5 = 0;
    L_0x0005:
        r2 = r15.f383b;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r3 = "connectivity";
        r2 = r2.getSystemService(r3);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = (android.net.ConnectivityManager) r2;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r3 = r2.getAllNetworkInfo();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        if (r3 != 0) goto L_0x0047;
    L_0x0015:
        r2 = r5;
    L_0x0016:
        if (r2 != 0) goto L_0x0060;
    L_0x0018:
        r2 = new com.alipay.android.phone.mrpc.core.HttpException;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r3 = 1;
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r6 = "The network is not available";
        r2.<init>(r3, r6);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        throw r2;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
    L_0x0025:
        r2 = move-exception;
        r15.m620e();
        r3 = r15.f384c;
        r3 = r3.mo7903f();
        if (r3 == 0) goto L_0x003e;
    L_0x0031:
        r3 = r15.f384c;
        r3.mo7903f();
        r3 = r15.f384c;
        r2.getCode();
        r2.getMsg();
    L_0x003e:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        throw r2;
    L_0x0047:
        r6 = r3.length;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = r5;
    L_0x0049:
        if (r2 >= r6) goto L_0x04fe;
    L_0x004b:
        r7 = r3[r2];	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        if (r7 == 0) goto L_0x005d;
    L_0x004f:
        r8 = r7.isAvailable();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        if (r8 == 0) goto L_0x005d;
    L_0x0055:
        r7 = r7.isConnectedOrConnecting();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        if (r7 == 0) goto L_0x005d;
    L_0x005b:
        r2 = r4;
        goto L_0x0016;
    L_0x005d:
        r2 = r2 + 1;
        goto L_0x0049;
    L_0x0060:
        r2 = r15.f384c;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = r2.mo7903f();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        if (r2 == 0) goto L_0x006f;
    L_0x0068:
        r2 = r15.f384c;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2.mo7903f();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = r15.f384c;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
    L_0x006f:
        r2 = r15.f384c;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = r2.mo7915d();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        if (r2 == 0) goto L_0x00a2;
    L_0x0077:
        r3 = r2.isEmpty();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        if (r3 != 0) goto L_0x00a2;
    L_0x007d:
        r3 = r2.iterator();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
    L_0x0081:
        r2 = r3.hasNext();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        if (r2 == 0) goto L_0x00a2;
    L_0x0087:
        r2 = r3.next();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = (org.apache.http.Header) r2;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r6 = r15.m618c();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r6.addHeader(r2);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        goto L_0x0081;
    L_0x0095:
        r2 = move-exception;
        r3 = new java.lang.RuntimeException;
        r4 = "Url parser error!";
        r2 = r2.getCause();
        r3.<init>(r4, r2);
        throw r3;
    L_0x00a2:
        r2 = r15.m618c();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        com.alipay.android.phone.mrpc.core.C0524b.m562a(r2);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = r15.m618c();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        com.alipay.android.phone.mrpc.core.C0524b.m564b(r2);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = r15.m618c();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r3 = "cookie";
        r6 = r15.m624i();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r7 = r15.f384c;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r7 = r7.mo7906a();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r6 = r6.getCookie(r7);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2.addHeader(r3, r6);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = r15.f387g;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r3 = "http.cookie-store";
        r6 = r15.f388h;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2.setAttribute(r3, r6);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = r15.f382a;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = r2.mo7897a();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r3 = f381e;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2.mo7874a(r3);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = new java.lang.StringBuilder;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r3 = "By Http/Https to request. operationType=";
        r2.<init>(r3);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r3 = r15.m621f();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = r2.append(r3);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r3 = " url=";
        r2 = r2.append(r3);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r3 = r15.f386f;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r3 = r3.getURI();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r3 = r3.toString();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2.append(r3);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = r15.f382a;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = r2.mo7897a();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r8 = r2.getParams();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r9 = "http.route.default-proxy";
        r2 = r15.f383b;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r3 = 0;
        r10 = "connectivity";
        r2 = r2.getSystemService(r10);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = (android.net.ConnectivityManager) r2;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = r2.getActiveNetworkInfo();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        if (r2 == 0) goto L_0x04fb;
    L_0x011e:
        r2 = r2.isAvailable();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        if (r2 == 0) goto L_0x04fb;
    L_0x0124:
        r10 = android.net.Proxy.getDefaultHost();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r11 = android.net.Proxy.getDefaultPort();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        if (r10 == 0) goto L_0x04fb;
    L_0x012e:
        r2 = new org.apache.http.HttpHost;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2.<init>(r10, r11);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
    L_0x0133:
        if (r2 == 0) goto L_0x014a;
    L_0x0135:
        r3 = r2.getHostName();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r10 = "127.0.0.1";
        r3 = android.text.TextUtils.equals(r3, r10);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        if (r3 == 0) goto L_0x014a;
    L_0x0141:
        r3 = r2.getPort();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r10 = 8087; // 0x1f97 float:1.1332E-41 double:3.9955E-320;
        if (r3 != r10) goto L_0x014a;
    L_0x0149:
        r2 = 0;
    L_0x014a:
        r8.setParameter(r9, r2);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = r15.f391k;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        if (r2 == 0) goto L_0x0237;
    L_0x0151:
        r2 = r15.f391k;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
    L_0x0153:
        r3 = r15.m622g();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r8 = 80;
        if (r3 != r8) goto L_0x04f8;
    L_0x015b:
        r2 = new org.apache.http.HttpHost;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r3 = r15.m623h();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r3 = r3.getHost();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2.<init>(r3);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r3 = r2;
    L_0x0169:
        r2 = r15.f382a;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = r2.mo7897a();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r8 = r15.f386f;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r9 = r15.f387g;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r10 = r2 instanceof org.apache.http.client.HttpClient;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        if (r10 != 0) goto L_0x0252;
    L_0x0177:
        r2 = r2.execute(r3, r8, r9);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r3 = r2;
    L_0x017c:
        r8 = java.lang.System.currentTimeMillis();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = r15.f382a;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r6 = r8 - r6;
        r2.mo7899b(r6);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = r15.f388h;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = r2.getCookies();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r6 = r15.f384c;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r6 = r6.mo7916e();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        if (r6 == 0) goto L_0x019c;
    L_0x0195:
        r6 = r15.m624i();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r6.removeAllCookie();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
    L_0x019c:
        r6 = r2.isEmpty();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        if (r6 != 0) goto L_0x025e;
    L_0x01a2:
        r6 = r2.iterator();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
    L_0x01a6:
        r2 = r6.hasNext();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        if (r2 == 0) goto L_0x025e;
    L_0x01ac:
        r2 = r6.next();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = (org.apache.http.cookie.Cookie) r2;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r7 = r2.getDomain();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        if (r7 == 0) goto L_0x01a6;
    L_0x01b8:
        r7 = new java.lang.StringBuilder;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r7.<init>();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r8 = r2.getName();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r7 = r7.append(r8);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r8 = "=";
        r7 = r7.append(r8);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r8 = r2.getValue();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r7 = r7.append(r8);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r8 = "; domain=";
        r7 = r7.append(r8);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r8 = r2.getDomain();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r7 = r7.append(r8);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = r2.isSecure();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        if (r2 == 0) goto L_0x025b;
    L_0x01e7:
        r2 = "; Secure";
    L_0x01e9:
        r2 = r7.append(r2);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = r2.toString();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r7 = r15.m624i();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r8 = r15.f384c;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r8 = r8.mo7906a();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r7.setCookie(r8, r2);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = android.webkit.CookieSyncManager.getInstance();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2.sync();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        goto L_0x01a6;
    L_0x0206:
        r2 = move-exception;
        r15.m620e();
        r3 = r15.f384c;
        r3 = r3.mo7903f();
        if (r3 == 0) goto L_0x0221;
    L_0x0212:
        r3 = r15.f384c;
        r3.mo7903f();
        r3 = r15.f384c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x0221:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r12);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x0237:
        r2 = r15.m623h();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r3 = new org.apache.http.HttpHost;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r8 = r2.getHost();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r9 = r15.m622g();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = r2.getProtocol();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r3.<init>(r8, r9, r2);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r15.f391k = r3;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = r15.f391k;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        goto L_0x0153;
    L_0x0252:
        r2 = (org.apache.http.client.HttpClient) r2;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = com.newrelic.agent.android.instrumentation.HttpInstrumentation.execute(r2, r3, r8, r9);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r3 = r2;
        goto L_0x017c;
    L_0x025b:
        r2 = "";
        goto L_0x01e9;
    L_0x025e:
        r2 = r15.f384c;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = r3.getStatusLine();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r6 = r2.getStatusCode();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = r3.getStatusLine();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r7 = r2.getReasonPhrase();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r6 == r2) goto L_0x02c8;
    L_0x0274:
        r2 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        if (r6 != r2) goto L_0x02c6;
    L_0x0278:
        r2 = r4;
    L_0x0279:
        if (r2 != 0) goto L_0x02c8;
    L_0x027b:
        r2 = new com.alipay.android.phone.mrpc.core.HttpException;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r6 = r3.getStatusLine();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r6 = r6.getStatusCode();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r6 = java.lang.Integer.valueOf(r6);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r3 = r3.getStatusLine();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r3 = r3.getReasonPhrase();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2.<init>(r6, r3);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        throw r2;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
    L_0x0295:
        r2 = move-exception;
        r15.m620e();
        r3 = r15.f384c;
        r3 = r3.mo7903f();
        if (r3 == 0) goto L_0x02b0;
    L_0x02a1:
        r3 = r15.f384c;
        r3.mo7903f();
        r3 = r15.f384c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x02b0:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r12);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x02c6:
        r2 = r5;
        goto L_0x0279;
    L_0x02c8:
        r3 = r15.m613a(r3, r6, r7);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r6 = -1;
        if (r3 == 0) goto L_0x02dc;
    L_0x02d0:
        r2 = r3.mo7921b();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        if (r2 == 0) goto L_0x02dc;
    L_0x02d6:
        r2 = r3.mo7921b();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = r2.length;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r6 = (long) r2;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
    L_0x02dc:
        r8 = -1;
        r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r2 != 0) goto L_0x02f7;
    L_0x02e2:
        r2 = r3 instanceof com.alipay.android.phone.mrpc.core.C0542p;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        if (r2 == 0) goto L_0x02f7;
    L_0x02e6:
        r0 = r3;
        r0 = (com.alipay.android.phone.mrpc.core.C0542p) r0;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = r0;
        r2 = r2.mo7922a();	 Catch:{ Exception -> 0x04f5, HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6 }
        r6 = "Content-Length";
        r2 = r2.getHead(r6);	 Catch:{ Exception -> 0x04f5, HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6 }
        java.lang.Long.parseLong(r2);	 Catch:{ Exception -> 0x04f5, HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6 }
    L_0x02f7:
        r2 = r15.f384c;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = r2.mo7906a();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        if (r2 == 0) goto L_0x031f;
    L_0x02ff:
        r6 = r15.m621f();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r6 = android.text.TextUtils.isEmpty(r6);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        if (r6 != 0) goto L_0x031f;
    L_0x0309:
        r6 = new java.lang.StringBuilder;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r6.<init>();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2 = r6.append(r2);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r6 = "#";
        r2 = r2.append(r6);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r6 = r15.m621f();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
        r2.append(r6);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0206, SSLPeerUnverifiedException -> 0x0295, SSLException -> 0x0320, ConnectionPoolTimeoutException -> 0x0351, ConnectTimeoutException -> 0x0382, SocketTimeoutException -> 0x03b3, NoHttpResponseException -> 0x03e5, HttpHostConnectException -> 0x0417, UnknownHostException -> 0x0442, IOException -> 0x0475, NullPointerException -> 0x04a6, Exception -> 0x04cc }
    L_0x031f:
        return r3;
    L_0x0320:
        r2 = move-exception;
        r15.m620e();
        r3 = r15.f384c;
        r3 = r3.mo7903f();
        if (r3 == 0) goto L_0x033b;
    L_0x032c:
        r3 = r15.f384c;
        r3.mo7903f();
        r3 = r15.f384c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x033b:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r14);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x0351:
        r2 = move-exception;
        r15.m620e();
        r3 = r15.f384c;
        r3 = r3.mo7903f();
        if (r3 == 0) goto L_0x036c;
    L_0x035d:
        r3 = r15.f384c;
        r3.mo7903f();
        r3 = r15.f384c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x036c:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r13);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x0382:
        r2 = move-exception;
        r15.m620e();
        r3 = r15.f384c;
        r3 = r3.mo7903f();
        if (r3 == 0) goto L_0x039d;
    L_0x038e:
        r3 = r15.f384c;
        r3.mo7903f();
        r3 = r15.f384c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x039d:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r13);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x03b3:
        r2 = move-exception;
        r15.m620e();
        r3 = r15.f384c;
        r3 = r3.mo7903f();
        if (r3 == 0) goto L_0x03ce;
    L_0x03bf:
        r3 = r15.f384c;
        r3.mo7903f();
        r3 = r15.f384c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x03ce:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = 4;
        r4 = java.lang.Integer.valueOf(r4);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x03e5:
        r2 = move-exception;
        r15.m620e();
        r3 = r15.f384c;
        r3 = r3.mo7903f();
        if (r3 == 0) goto L_0x0400;
    L_0x03f1:
        r3 = r15.f384c;
        r3.mo7903f();
        r3 = r15.f384c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x0400:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = 5;
        r4 = java.lang.Integer.valueOf(r4);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x0417:
        r2 = move-exception;
        r15.m620e();
        r3 = r15.f384c;
        r3 = r3.mo7903f();
        if (r3 == 0) goto L_0x0432;
    L_0x0423:
        r3 = r15.f384c;
        r3.mo7903f();
        r3 = r15.f384c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x0432:
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = 8;
        r4 = java.lang.Integer.valueOf(r4);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x0442:
        r2 = move-exception;
        r15.m620e();
        r3 = r15.f384c;
        r3 = r3.mo7903f();
        if (r3 == 0) goto L_0x045d;
    L_0x044e:
        r3 = r15.f384c;
        r3.mo7903f();
        r3 = r15.f384c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x045d:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = 9;
        r4 = java.lang.Integer.valueOf(r4);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x0475:
        r2 = move-exception;
        r15.m620e();
        r3 = r15.f384c;
        r3 = r3.mo7903f();
        if (r3 == 0) goto L_0x0490;
    L_0x0481:
        r3 = r15.f384c;
        r3.mo7903f();
        r3 = r15.f384c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x0490:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r14);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x04a6:
        r2 = move-exception;
        r15.m620e();
        r3 = r15.f393m;
        if (r3 > 0) goto L_0x04b6;
    L_0x04ae:
        r2 = r15.f393m;
        r2 = r2 + 1;
        r15.f393m = r2;
        goto L_0x0005;
    L_0x04b6:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r5);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x04cc:
        r2 = move-exception;
        r15.m620e();
        r3 = r15.f384c;
        r3 = r3.mo7903f();
        if (r3 == 0) goto L_0x04e7;
    L_0x04d8:
        r3 = r15.f384c;
        r3.mo7903f();
        r3 = r15.f384c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x04e7:
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r5);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x04f5:
        r2 = move-exception;
        goto L_0x02f7;
    L_0x04f8:
        r3 = r2;
        goto L_0x0169;
    L_0x04fb:
        r2 = r3;
        goto L_0x0133;
    L_0x04fe:
        r2 = r5;
        goto L_0x0016;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.android.phone.mrpc.core.C0543q.m619d():com.alipay.android.phone.mrpc.core.u");
    }

    /* renamed from: e */
    private void m620e() {
        if (this.f386f != null) {
            this.f386f.abort();
        }
    }

    /* renamed from: f */
    private String m621f() {
        if (!TextUtils.isEmpty(this.f397q)) {
            return this.f397q;
        }
        this.f397q = this.f384c.mo7912b("operationType");
        return this.f397q;
    }

    /* renamed from: g */
    private int m622g() {
        URL h = m623h();
        return h.getPort() == -1 ? h.getDefaultPort() : h.getPort();
    }

    /* renamed from: h */
    private URL m623h() {
        if (this.f392l != null) {
            return this.f392l;
        }
        this.f392l = new URL(this.f384c.mo7906a());
        return this.f392l;
    }

    /* renamed from: i */
    private CookieManager m624i() {
        if (this.f389i != null) {
            return this.f389i;
        }
        this.f389i = CookieManager.getInstance();
        return this.f389i;
    }

    /* renamed from: a */
    public final C0540o mo7926a() {
        return this.f384c;
    }

    public final /* synthetic */ Object call() {
        return m619d();
    }
}
