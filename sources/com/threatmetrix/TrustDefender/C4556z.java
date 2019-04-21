package com.threatmetrix.TrustDefender;

import com.threatmetrix.TrustDefender.C4532g.C4518b.C4516a;
import com.threatmetrix.TrustDefender.C4532g.C4518b.C4517b;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Protocol;
import okhttp3.RequestBody;

/* renamed from: com.threatmetrix.TrustDefender.z */
class C4556z implements C4480am {
    /* renamed from: c */
    private static final C4555a f7854c = new C4555a();
    /* renamed from: d */
    private static final String f7855d = C4549w.m8585a(C4556z.class);
    /* renamed from: a */
    OkHttpClient f7856a;
    /* renamed from: b */
    String f7857b;

    /* renamed from: com.threatmetrix.TrustDefender.z$a */
    static final class C4555a implements Interceptor {

        /* renamed from: com.threatmetrix.TrustDefender.z$a$1 */
        class C45531 extends RequestBody {
        }

        /* renamed from: com.threatmetrix.TrustDefender.z$a$2 */
        class C45542 extends RequestBody {
        }

        C4555a() {
        }
    }

    C4556z() {
    }

    /* renamed from: a */
    public final void mo34111a(int timeout, String userAgent, boolean enablePostCompression, boolean enableCustomTLSFactory) {
        C4549w.m8594c(f7855d, "Creating OkHttpClient instance");
        this.f7857b = userAgent;
        Builder builder = new OkHttpClient().newBuilder();
        builder.connectTimeout((long) timeout, TimeUnit.MILLISECONDS).writeTimeout((long) timeout, TimeUnit.MILLISECONDS).readTimeout((long) timeout, TimeUnit.MILLISECONDS).followRedirects(true).followSslRedirects(true).connectionPool(new ConnectionPool(3, 30, TimeUnit.MICROSECONDS));
        if (C4516a.f7584c >= C4517b.f7594i && C4516a.f7584c < C4517b.f7599n && enableCustomTLSFactory) {
            builder.sslSocketFactory(new TLSSocketFactory());
        }
        C4486ae proxyWrapper = new C4486ae();
        if (proxyWrapper.mo34128a() != null) {
            builder.proxy(new Proxy(Type.HTTP, new InetSocketAddress(proxyWrapper.mo34128a(), proxyWrapper.mo34129b())));
        }
        builder.interceptors().add(f7854c);
        ArrayList<Protocol> plist = new ArrayList();
        plist.add(Protocol.HTTP_1_1);
        builder.protocols(plist).retryOnConnectionFailure(true);
        this.f7856a = builder.build();
    }

    /* renamed from: a */
    public final OkHttpClient mo34271a() {
        return this.f7856a;
    }

    /* renamed from: b */
    public final String mo34272b() {
        return this.f7857b;
    }

    /* renamed from: a */
    public final C4475aq mo34110a(C4483e state) {
        return new C4476aa(this, state);
    }
}
