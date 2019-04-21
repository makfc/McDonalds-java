package com.tencent.wxop.stat;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.protocol.HttpContext;

/* renamed from: com.tencent.wxop.stat.j */
class C4446j extends DefaultConnectionKeepAliveStrategy {
    /* renamed from: a */
    final /* synthetic */ C4445i f7181a;

    C4446j(C4445i c4445i) {
        this.f7181a = c4445i;
    }

    public long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
        long keepAliveDuration = super.getKeepAliveDuration(httpResponse, httpContext);
        return keepAliveDuration == -1 ? 30000 : keepAliveDuration;
    }
}
