package com.threatmetrix.TrustDefender;

import com.newrelic.agent.android.instrumentation.okhttp2.OkHttp2Instrumentation;
import com.squareup.okhttp.ConnectionPool;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Interceptor.Chain;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Request.Builder;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.threatmetrix.TrustDefender.C4532g.C4518b.C4516a;
import com.threatmetrix.TrustDefender.C4532g.C4518b.C4517b;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import okio.Buffer;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;

/* renamed from: com.threatmetrix.TrustDefender.ab */
class C4481ab implements C4480am {
    /* renamed from: c */
    private static final C4479a f7344c = new C4479a();
    /* renamed from: d */
    private static final String f7345d = C4549w.m8585a(C4481ab.class);
    /* renamed from: a */
    OkHttpClient f7346a;
    /* renamed from: b */
    String f7347b;

    /* renamed from: com.threatmetrix.TrustDefender.ab$a */
    static final class C4479a implements Interceptor {
        C4479a() {
        }

        public final Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            if (originalRequest.body() == null || originalRequest.header("Content-Encoding") != null) {
                return chain.proceed(originalRequest);
            }
            Builder header = originalRequest.newBuilder().header("Content-Encoding", "gzip");
            String method = originalRequest.method();
            final RequestBody body = originalRequest.body();
            final C44782 c44782 = new RequestBody() {
                public final MediaType contentType() {
                    return body.contentType();
                }

                public final long contentLength() {
                    return -1;
                }

                public final void writeTo(BufferedSink sink) throws IOException {
                    BufferedSink gzipSink = Okio.buffer(new GzipSink(sink));
                    body.writeTo(gzipSink);
                    gzipSink.close();
                }
            };
            final Buffer buffer = new Buffer();
            c44782.writeTo(buffer);
            header = header.method(method, new RequestBody() {
                public final MediaType contentType() {
                    return c44782.contentType();
                }

                public final long contentLength() {
                    return buffer.size();
                }

                public final void writeTo(BufferedSink sink) throws IOException {
                    sink.write(buffer.snapshot());
                }
            });
            return chain.proceed(!(header instanceof Builder) ? header.build() : OkHttp2Instrumentation.build(header));
        }
    }

    C4481ab() {
    }

    /* renamed from: a */
    public final void mo34111a(int timeout, String userAgent, boolean enablePostCompression, boolean enableCustomTLSFactory) {
        C4549w.m8594c(f7345d, "Creating OkHttpClient instance");
        this.f7346a = new OkHttpClient();
        if (C4516a.f7584c >= C4517b.f7594i && C4516a.f7584c < C4517b.f7599n && enableCustomTLSFactory) {
            this.f7346a.setSslSocketFactory(new TLSSocketFactory());
        }
        this.f7346a.setConnectTimeout((long) timeout, TimeUnit.MILLISECONDS);
        this.f7346a.setWriteTimeout((long) timeout, TimeUnit.MILLISECONDS);
        this.f7346a.setReadTimeout((long) timeout, TimeUnit.MILLISECONDS);
        this.f7346a.setFollowRedirects(true);
        this.f7346a.setFollowSslRedirects(true);
        this.f7346a.setConnectionPool(new ConnectionPool(3, 30000));
        this.f7347b = userAgent;
        C4486ae proxyWrapper = new C4486ae();
        if (proxyWrapper.mo34128a() != null) {
            this.f7346a.setProxy(new Proxy(Type.HTTP, new InetSocketAddress(proxyWrapper.mo34128a(), proxyWrapper.mo34129b())));
        }
        this.f7346a.interceptors().add(f7344c);
        ArrayList<Protocol> plist = new ArrayList();
        plist.add(Protocol.HTTP_1_1);
        this.f7346a.setProtocols(plist);
        this.f7346a.setRetryOnConnectionFailure(true);
    }

    /* renamed from: a */
    public final OkHttpClient mo34112a() {
        return this.f7346a;
    }

    /* renamed from: b */
    public final String mo34113b() {
        return this.f7347b;
    }

    /* renamed from: a */
    public final C4475aq mo34110a(C4483e state) {
        return new C4482ac(this, state);
    }
}
