package com.threatmetrix.TrustDefender;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.GeneralSecurityException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

class TLSSocketFactory extends SSLSocketFactory {
    private static final String TAG = C4549w.m8585a(TLSSocketFactory.class);
    private SSLSocketFactory delegate;

    public TLSSocketFactory() {
        try {
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, null, null);
            this.delegate = context.getSocketFactory();
        } catch (GeneralSecurityException e) {
            String str = TAG;
        }
    }

    public String[] getDefaultCipherSuites() {
        return this.delegate.getDefaultCipherSuites();
    }

    public String[] getSupportedCipherSuites() {
        return this.delegate.getSupportedCipherSuites();
    }

    public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
        return m8236a(this.delegate.createSocket(s, host, port, autoClose));
    }

    public Socket createSocket(String host, int port) throws IOException {
        return m8236a(this.delegate.createSocket(host, port));
    }

    public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException {
        return m8236a(this.delegate.createSocket(host, port, localHost, localPort));
    }

    public Socket createSocket(InetAddress host, int port) throws IOException {
        return m8236a(this.delegate.createSocket(host, port));
    }

    public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
        return m8236a(this.delegate.createSocket(address, port, localAddress, localPort));
    }

    /* renamed from: a */
    private static Socket m8236a(Socket socket) {
        if (socket != null && (socket instanceof SSLSocket)) {
            try {
                ((SSLSocket) socket).setEnabledProtocols(new String[]{"TLSv1.2"});
            } catch (IllegalArgumentException e) {
                String str = TAG;
            }
        }
        return socket;
    }
}
