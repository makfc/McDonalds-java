package com.admaster.square.utils;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;

/* compiled from: ConnectUtil */
/* renamed from: com.admaster.square.utils.c */
public class C0487c extends SSLSocketFactory {
    /* renamed from: a */
    final SSLContext f253a = SSLContext.getInstance("TLS");

    public C0487c(KeyStore keyStore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
        super(keyStore);
        C0488d c0488d = new C0488d(this);
        this.f253a.init(null, new TrustManager[]{c0488d}, null);
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException, UnknownHostException {
        return this.f253a.getSocketFactory().createSocket(socket, str, i, z);
    }

    public boolean isSecure(Socket socket) throws IllegalArgumentException {
        return true;
    }

    public Socket createSocket() throws IOException {
        return this.f253a.getSocketFactory().createSocket();
    }
}
