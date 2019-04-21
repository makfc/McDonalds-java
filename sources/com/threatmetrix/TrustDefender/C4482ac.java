package com.threatmetrix.TrustDefender;

import com.newrelic.agent.android.instrumentation.okhttp2.OkHttp2Instrumentation;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Request.Builder;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.SSLPeerUnverifiedException;

/* renamed from: com.threatmetrix.TrustDefender.ac */
class C4482ac implements C4475aq {
    /* renamed from: a */
    private static final String f7348a = C4549w.m8585a(C4482ac.class);
    /* renamed from: b */
    private THMStatusCode f7349b = THMStatusCode.THM_NotYet;
    /* renamed from: c */
    private C4483e f7350c;
    /* renamed from: d */
    private Map<String, String> f7351d = new HashMap();
    /* renamed from: e */
    private Request f7352e = null;
    /* renamed from: f */
    private C4481ab f7353f = null;
    /* renamed from: g */
    private OkHttpClient f7354g = null;
    /* renamed from: h */
    private Response f7355h = null;
    /* renamed from: i */
    private Call f7356i = null;

    C4482ac(C4481ab client, C4483e state) {
        this.f7350c = state;
        this.f7353f = client;
        this.f7354g = client.mo34112a();
    }

    /* renamed from: a */
    public final void mo34103a(Map<String, String> headers) {
        if (headers != null) {
            this.f7351d.putAll(headers);
        }
    }

    /* renamed from: a */
    public final long mo34100a(String url) {
        m8293b(url, null);
        if (this.f7355h == null || this.f7349b != THMStatusCode.THM_OK) {
            return -1;
        }
        return (long) this.f7355h.code();
    }

    /* renamed from: a */
    public final long mo34101a(String url, C4540n params) {
        m8293b(url, params);
        if (this.f7355h == null || this.f7349b != THMStatusCode.THM_OK) {
            return -1;
        }
        return (long) this.f7355h.code();
    }

    /* renamed from: b */
    private void m8293b(String url, C4540n params) {
        Builder builder = new Builder().url(url);
        this.f7351d.put("User-Agent", this.f7353f.mo34113b());
        for (Entry<String, String> entry : this.f7351d.entrySet()) {
            if (entry.getValue() == null) {
                C4549w.m8594c(f7348a, "null value for " + ((String) entry.getKey()));
            } else {
                builder.addHeader((String) entry.getKey(), (String) entry.getValue());
            }
        }
        if (params != null) {
            FormEncodingBuilder bodyBuilder = new FormEncodingBuilder();
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
            this.f7352e = !(builder instanceof Builder) ? builder.build() : OkHttp2Instrumentation.build(builder);
        }
        try {
            OkHttpClient okHttpClient = this.f7354g;
            Request request = this.f7352e;
            this.f7356i = !(okHttpClient instanceof OkHttpClient) ? okHttpClient.newCall(request) : OkHttp2Instrumentation.newCall(okHttpClient, request);
            this.f7355h = this.f7356i.execute();
            this.f7349b = THMStatusCode.THM_OK;
        } catch (IOException e) {
            if (e.getCause() instanceof CertificateException) {
                this.f7349b = THMStatusCode.THM_HostVerification_Error;
            } else if (e instanceof SSLPeerUnverifiedException) {
                this.f7349b = THMStatusCode.THM_HostVerification_Error;
            } else if (e instanceof UnknownHostException) {
                this.f7349b = THMStatusCode.THM_HostNotFound_Error;
            } else if (e instanceof SocketTimeoutException) {
                this.f7349b = THMStatusCode.THM_NetworkTimeout_Error;
            } else if (this.f7349b == THMStatusCode.THM_NotYet) {
                this.f7349b = THMStatusCode.THM_Connection_Error;
            }
            if (this.f7350c == null || !this.f7350c.mo34114a()) {
                C4549w.m8588a(f7348a, "Failed to retrieve URI", e);
                return;
            }
            C4549w.m8594c(f7348a, "Connection interrupted due to cancel!");
            mo34105c();
        } catch (RuntimeException e2) {
            C4549w.m8588a(f7348a, "Caught runtime exception:", e2);
            this.f7349b = THMStatusCode.THM_Connection_Error;
        }
    }

    /* renamed from: a */
    public final String mo34102a() {
        if (this.f7352e != null) {
            return this.f7352e.url().toString();
        }
        return null;
    }

    /* renamed from: b */
    public final String mo34104b() {
        if (this.f7352e != null) {
            return this.f7352e.url().getHost();
        }
        return null;
    }

    /* renamed from: c */
    public final void mo34105c() {
        if (this.f7356i != null) {
            this.f7356i.cancel();
        }
    }

    /* renamed from: d */
    public final InputStream mo34106d() throws IOException {
        if (this.f7355h == null) {
            return null;
        }
        return this.f7355h.body().byteStream();
    }

    /* renamed from: e */
    public final void mo34107e() {
        if (this.f7355h != null) {
            try {
                this.f7355h.body().close();
            } catch (IOException e) {
                String str = f7348a;
            }
        }
    }

    /* renamed from: f */
    public final THMStatusCode mo34108f() {
        return this.f7349b;
    }

    /* renamed from: g */
    public final int mo34109g() {
        if (this.f7355h != null) {
            return this.f7355h.code();
        }
        return -1;
    }
}
