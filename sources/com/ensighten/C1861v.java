package com.ensighten;

import android.os.SystemClock;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Iterator;
import javax.net.ssl.SSLException;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;

/* renamed from: com.ensighten.v */
final class C1861v implements HttpRequestRetryHandler {
    /* renamed from: a */
    private static HashSet<Class<?>> f5996a = new HashSet();
    /* renamed from: b */
    private static HashSet<Class<?>> f5997b = new HashSet();
    /* renamed from: c */
    private final int f5998c = 5;

    static {
        f5996a.add(NoHttpResponseException.class);
        f5996a.add(UnknownHostException.class);
        f5996a.add(SocketException.class);
        f5997b.add(InterruptedIOException.class);
        f5997b.add(SSLException.class);
    }

    public C1861v(int i) {
    }

    public final boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
        boolean z = true;
        Boolean bool = (Boolean) context.getAttribute("http.request_sent");
        boolean z2 = bool != null && bool.booleanValue();
        if (executionCount > this.f5998c) {
            z2 = false;
        } else if (C1861v.m7427a(f5997b, exception)) {
            z2 = false;
        } else if (C1861v.m7427a(f5996a, exception)) {
            z2 = true;
        } else if (z2) {
            z2 = true;
        } else {
            z2 = true;
        }
        if (!z2) {
            z = z2;
        } else if (((HttpUriRequest) context.getAttribute("http.request")).getMethod().equals("POST")) {
            z = false;
        }
        if (z) {
            SystemClock.sleep(1500);
        } else {
            exception.printStackTrace();
        }
        return z;
    }

    /* renamed from: a */
    private static boolean m7427a(HashSet<Class<?>> hashSet, Throwable th) {
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            if (((Class) it.next()).isInstance(th)) {
                return true;
            }
        }
        return false;
    }
}
