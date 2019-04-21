package com.amap.api.mapcore.util;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/* compiled from: HttpUrlUtil */
/* renamed from: com.amap.api.mapcore.util.ft */
class C0847ft implements HostnameVerifier {
    /* renamed from: a */
    final /* synthetic */ HttpUrlUtil f2003a;

    C0847ft(HttpUrlUtil httpUrlUtil) {
        this.f2003a = httpUrlUtil;
    }

    public boolean verify(String str, SSLSession sSLSession) {
        return HttpsURLConnection.getDefaultHostnameVerifier().verify("*.amap.com", sSLSession);
    }
}
