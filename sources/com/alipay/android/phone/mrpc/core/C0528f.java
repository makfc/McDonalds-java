package com.alipay.android.phone.mrpc.core;

import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.protocol.HttpContext;

/* renamed from: com.alipay.android.phone.mrpc.core.f */
final class C0528f implements ConnectionKeepAliveStrategy {
    /* renamed from: a */
    final /* synthetic */ C0526d f343a;

    C0528f(C0526d c0526d) {
        this.f343a = c0526d;
    }

    public final long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
        return 180000;
    }
}
