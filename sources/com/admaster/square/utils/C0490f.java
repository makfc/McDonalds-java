package com.admaster.square.utils;

import java.io.IOException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import org.apache.http.conn.ssl.X509HostnameVerifier;

/* compiled from: ConnectUtil */
/* renamed from: com.admaster.square.utils.f */
class C0490f implements X509HostnameVerifier {
    /* renamed from: a */
    final /* synthetic */ C0489e f257a;

    C0490f(C0489e c0489e) {
        this.f257a = c0489e;
    }

    public void verify(String str, String[] strArr, String[] strArr2) throws SSLException {
    }

    public void verify(String str, X509Certificate x509Certificate) throws SSLException {
    }

    public void verify(String str, SSLSocket sSLSocket) throws IOException {
    }

    public boolean verify(String str, SSLSession sSLSession) {
        return true;
    }
}
