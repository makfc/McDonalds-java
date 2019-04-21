package com.admaster.square.utils;

import android.os.Build.VERSION;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;

/* compiled from: ConnectUtil */
/* renamed from: com.admaster.square.utils.e */
public class C0489e extends SSLSocketFactory {
    /* renamed from: b */
    private static C0489e f255b = null;
    /* renamed from: a */
    private javax.net.ssl.SSLSocketFactory f256a = null;

    private C0489e() throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException {
        super(null);
        SSLContext instance = SSLContext.getInstance("TLS");
        instance.init(null, new TrustManager[]{new C0491g(this)}, null);
        this.f256a = instance.getSocketFactory();
        setHostnameVerifier(new C0490f(this));
    }

    /* renamed from: a */
    public static C0489e m405a() {
        if (f255b == null) {
            try {
                f255b = new C0489e();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            } catch (UnrecoverableKeyException e2) {
                e2.printStackTrace();
            } catch (NoSuchAlgorithmException e3) {
                e3.printStackTrace();
            } catch (KeyStoreException e4) {
                e4.printStackTrace();
            }
        }
        return f255b;
    }

    public Socket createSocket() throws IOException {
        return this.f256a.createSocket();
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException, UnknownHostException {
        if (VERSION.SDK_INT < 11) {
            m406a(socket, str);
        }
        return this.f256a.createSocket(socket, str, i, z);
    }

    /* renamed from: a */
    private void m406a(Socket socket, String str) {
        try {
            Field declaredField = InetAddress.class.getDeclaredField("hostName");
            declaredField.setAccessible(true);
            declaredField.set(socket.getInetAddress(), str);
        } catch (Exception e) {
        }
    }
}
