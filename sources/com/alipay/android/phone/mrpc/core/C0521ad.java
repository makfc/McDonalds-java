package com.alipay.android.phone.mrpc.core;

import java.io.IOException;
import java.net.SocketException;
import javax.net.ssl.SSLException;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.protocol.HttpContext;

/* renamed from: com.alipay.android.phone.mrpc.core.ad */
public class C0521ad implements HttpRequestRetryHandler {
    /* renamed from: a */
    static final String f330a = C0521ad.class.getSimpleName();

    public boolean retryRequest(IOException iOException, int i, HttpContext httpContext) {
        if (i >= 3) {
            return false;
        }
        String str;
        if (iOException instanceof NoHttpResponseException) {
            str = f330a;
            return true;
        } else if ((!(iOException instanceof SocketException) && !(iOException instanceof SSLException)) || iOException.getMessage() == null || !iOException.getMessage().contains("Broken pipe")) {
            return false;
        } else {
            str = f330a;
            return true;
        }
    }
}
