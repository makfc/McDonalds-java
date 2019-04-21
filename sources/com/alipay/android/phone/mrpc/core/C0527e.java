package com.alipay.android.phone.mrpc.core;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultRedirectHandler;
import org.apache.http.protocol.HttpContext;

/* renamed from: com.alipay.android.phone.mrpc.core.e */
final class C0527e extends DefaultRedirectHandler {
    /* renamed from: a */
    int f341a;
    /* renamed from: b */
    final /* synthetic */ C0526d f342b;

    C0527e(C0526d c0526d) {
        this.f342b = c0526d;
    }

    public final boolean isRedirectRequested(HttpResponse httpResponse, HttpContext httpContext) {
        this.f341a++;
        boolean isRedirectRequested = super.isRedirectRequested(httpResponse, httpContext);
        if (isRedirectRequested || this.f341a >= 5) {
            return isRedirectRequested;
        }
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        return (statusCode == 301 || statusCode == 302) ? true : isRedirectRequested;
    }
}
