package com.admaster.square.utils;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/* compiled from: ConnectUtil */
/* renamed from: com.admaster.square.utils.d */
class C0488d implements X509TrustManager {
    /* renamed from: a */
    final /* synthetic */ C0487c f254a;

    C0488d(C0487c c0487c) {
        this.f254a = c0487c;
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
    }

    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
    }
}
