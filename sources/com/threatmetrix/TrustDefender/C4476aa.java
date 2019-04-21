package com.threatmetrix.TrustDefender;

import com.newrelic.agent.android.instrumentation.okhttp3.OkHttp3Instrumentation;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.SSLPeerUnverifiedException;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;

/* renamed from: com.threatmetrix.TrustDefender.aa */
class C4476aa implements C4475aq {
    /* renamed from: a */
    private static final String f7330a = C4549w.m8585a(C4476aa.class);
    /* renamed from: b */
    private THMStatusCode f7331b = THMStatusCode.THM_NotYet;
    /* renamed from: c */
    private C4483e f7332c;
    /* renamed from: d */
    private Map<String, String> f7333d = new HashMap();
    /* renamed from: e */
    private Request f7334e = null;
    /* renamed from: f */
    private C4556z f7335f = null;
    /* renamed from: g */
    private OkHttpClient f7336g = null;
    /* renamed from: h */
    private Response f7337h = null;
    /* renamed from: i */
    private Call f7338i = null;

    C4476aa(C4556z client, C4483e state) {
        this.f7332c = state;
        this.f7335f = client;
        this.f7336g = client.mo34271a();
    }

    /* renamed from: a */
    public final void mo34103a(Map<String, String> headers) {
        if (headers != null) {
            this.f7333d.putAll(headers);
        }
    }

    /* renamed from: a */
    public final long mo34100a(String url) {
        m8276b(url, null);
        if (this.f7337h == null || this.f7331b != THMStatusCode.THM_OK) {
            return -1;
        }
        return (long) this.f7337h.code();
    }

    /* renamed from: a */
    public final long mo34101a(String url, C4540n params) {
        m8276b(url, params);
        if (this.f7337h == null || this.f7331b != THMStatusCode.THM_OK) {
            return -1;
        }
        return (long) this.f7337h.code();
    }

    /* renamed from: b */
    private void m8276b(String url, C4540n params) {
        Builder builder = new Builder().url(url);
        this.f7333d.put("User-Agent", this.f7335f.mo34272b());
        for (Entry<String, String> entry : this.f7333d.entrySet()) {
            if (entry.getValue() == null) {
                C4549w.m8594c(f7330a, "null value for " + ((String) entry.getKey()));
            } else {
                builder.addHeader((String) entry.getKey(), (String) entry.getValue());
            }
        }
        if (params != null) {
            FormBody.Builder bodyBuilder = new FormBody.Builder();
            for (String key : params.keySet()) {
                String value = (String) params.get(key);
                if (C4491ai.m8349f(value)) {
                    Integer length = params.mo34252a(key);
                    if (length != null && value.length() > length.intValue()) {
                        value = value.substring(0, length.intValue());
                    }
                    if (length == null && params.mo34248a() != 0 && value.length() > params.mo34248a()) {
                        value = value.substring(0, params.mo34248a());
                    }
                    bodyBuilder.add(key, value);
                }
            }
            builder.post(bodyBuilder.build());
        }
        synchronized (this) {
            this.f7334e = !(builder instanceof Builder) ? builder.build() : OkHttp3Instrumentation.build(builder);
        }
        try {
            OkHttpClient okHttpClient = this.f7336g;
            Request request = this.f7334e;
            this.f7338i = !(okHttpClient instanceof OkHttpClient) ? okHttpClient.newCall(request) : OkHttp3Instrumentation.newCall(okHttpClient, request);
            this.f7337h = this.f7338i.execute();
            this.f7331b = THMStatusCode.THM_OK;
        } catch (IOException e) {
            if (e.getCause() instanceof CertificateException) {
                this.f7331b = THMStatusCode.THM_HostVerification_Error;
            } else if (e instanceof SSLPeerUnverifiedException) {
                this.f7331b = THMStatusCode.THM_HostVerification_Error;
            } else if (e instanceof UnknownHostException) {
                this.f7331b = THMStatusCode.THM_HostNotFound_Error;
            } else if (e instanceof SocketTimeoutException) {
                this.f7331b = THMStatusCode.THM_NetworkTimeout_Error;
            } else if (this.f7331b == THMStatusCode.THM_NotYet) {
                this.f7331b = THMStatusCode.THM_Connection_Error;
            }
            if (this.f7332c == null || !this.f7332c.mo34114a()) {
                C4549w.m8588a(f7330a, "Failed to retrieve URI", e);
                return;
            }
            C4549w.m8594c(f7330a, "Connection interrupted due to cancel!");
            mo34105c();
        } catch (RuntimeException e2) {
            C4549w.m8588a(f7330a, "Caught runtime exception:", e2);
            this.f7331b = THMStatusCode.THM_Connection_Error;
        }
    }

    /* renamed from: a */
    public final String mo34102a() {
        if (this.f7334e != null) {
            return this.f7334e.url().toString();
        }
        return null;
    }

    /* renamed from: b */
    public final String mo34104b() {
        if (this.f7334e != null) {
            return this.f7334e.url().host();
        }
        return null;
    }

    /* renamed from: c */
    public final void mo34105c() {
        if (this.f7338i != null) {
            this.f7338i.cancel();
        }
    }

    /* renamed from: d */
    public final InputStream mo34106d() throws IOException {
        if (this.f7337h == null) {
            return null;
        }
        return this.f7337h.body().byteStream();
    }

    /* renamed from: e */
    public final void mo34107e() {
        if (this.f7337h != null) {
            this.f7337h.body().close();
        }
    }

    /* renamed from: f */
    public final THMStatusCode mo34108f() {
        return this.f7331b;
    }

    /* renamed from: g */
    public final int mo34109g() {
        if (this.f7337h != null) {
            return this.f7337h.code();
        }
        return -1;
    }
}
