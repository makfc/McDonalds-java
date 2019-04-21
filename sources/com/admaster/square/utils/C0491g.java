package com.admaster.square.utils;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/* compiled from: ConnectUtil */
/* renamed from: com.admaster.square.utils.g */
public class C0491g implements X509TrustManager {
    /* renamed from: a */
    final /* synthetic */ C0489e f258a;

    public C0491g(C0489e c0489e) {
        this.f258a = c0489e;
    }

    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
    }

    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
