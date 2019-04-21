package com.google.api.client.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

public final class SslUtils {

    /* renamed from: com.google.api.client.util.SslUtils$1 */
    static class C27711 implements X509TrustManager {
        C27711() {
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

    /* renamed from: com.google.api.client.util.SslUtils$2 */
    static class C27722 implements HostnameVerifier {
        C27722() {
        }

        public boolean verify(String arg0, SSLSession arg1) {
            return true;
        }
    }

    private SslUtils() {
    }
}
