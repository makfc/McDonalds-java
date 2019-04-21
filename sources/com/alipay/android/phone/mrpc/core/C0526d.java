package com.alipay.android.phone.mrpc.core;

import com.alipay.android.phone.mrpc.core.C0524b.C0522a;
import org.apache.http.client.RedirectHandler;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.HttpContext;

/* renamed from: com.alipay.android.phone.mrpc.core.d */
final class C0526d extends DefaultHttpClient {
    /* renamed from: a */
    final /* synthetic */ C0524b f340a;

    C0526d(C0524b c0524b, ClientConnectionManager clientConnectionManager, HttpParams httpParams) {
        this.f340a = c0524b;
        super(clientConnectionManager, httpParams);
    }

    /* Access modifiers changed, original: protected|final */
    public final ConnectionKeepAliveStrategy createConnectionKeepAliveStrategy() {
        return new C0528f(this);
    }

    /* Access modifiers changed, original: protected|final */
    public final HttpContext createHttpContext() {
        BasicHttpContext basicHttpContext = new BasicHttpContext();
        basicHttpContext.setAttribute("http.authscheme-registry", getAuthSchemes());
        basicHttpContext.setAttribute("http.cookiespec-registry", getCookieSpecs());
        basicHttpContext.setAttribute("http.auth.credentials-provider", getCredentialsProvider());
        return basicHttpContext;
    }

    /* Access modifiers changed, original: protected|final */
    public final BasicHttpProcessor createHttpProcessor() {
        BasicHttpProcessor createHttpProcessor = super.createHttpProcessor();
        createHttpProcessor.addRequestInterceptor(C0524b.f336c);
        createHttpProcessor.addRequestInterceptor(new C0522a(this.f340a, (byte) 0));
        return createHttpProcessor;
    }

    /* Access modifiers changed, original: protected|final */
    public final RedirectHandler createRedirectHandler() {
        return new C0527e(this);
    }
}
