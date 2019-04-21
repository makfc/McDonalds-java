package com.ensighten;

import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.protocol.HttpContext;

/* renamed from: com.ensighten.p */
final class C1857p implements Runnable {
    /* renamed from: a */
    private final AbstractHttpClient f5972a;
    /* renamed from: b */
    private final HttpContext f5973b;
    /* renamed from: c */
    private final HttpUriRequest f5974c;
    /* renamed from: d */
    private final C1730q f5975d;
    /* renamed from: e */
    private boolean f5976e;
    /* renamed from: f */
    private int f5977f;

    public C1857p(AbstractHttpClient abstractHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, C1730q c1730q) {
        this.f5972a = abstractHttpClient;
        this.f5973b = httpContext;
        this.f5974c = httpUriRequest;
        this.f5975d = c1730q;
        if (c1730q instanceof C1748r) {
            this.f5976e = true;
        }
    }

    public final void run() {
        Throwable e;
        int i;
        try {
            if (this.f5975d != null) {
                this.f5975d.mo15070a();
            }
            HttpRequestRetryHandler httpRequestRetryHandler = this.f5972a.getHttpRequestRetryHandler();
            boolean z = true;
            e = null;
            while (z) {
                try {
                    m7416a();
                } catch (UnknownHostException e2) {
                    if (this.f5975d != null) {
                        this.f5975d.mo15074a(e2, "can't resolve host");
                    }
                } catch (SocketException e22) {
                    if (this.f5975d != null) {
                        this.f5975d.mo15074a(e22, "can't resolve host");
                    }
                } catch (SocketTimeoutException e222) {
                    if (this.f5975d != null) {
                        this.f5975d.mo15074a(e222, "socket time out");
                    }
                } catch (IOException e3) {
                    e222 = e3;
                    i = this.f5977f + 1;
                    this.f5977f = i;
                    z = httpRequestRetryHandler.retryRequest(e222, i, this.f5973b);
                } catch (NullPointerException e4) {
                    e222 = new IOException("NPE in HttpClient" + e4.getMessage());
                    i = this.f5977f + 1;
                    this.f5977f = i;
                    z = httpRequestRetryHandler.retryRequest(e222, i, this.f5973b);
                }
                if (this.f5975d != null) {
                    this.f5975d.mo15077b();
                    return;
                }
                return;
            }
            ConnectException connectException = new ConnectException();
            connectException.initCause(e222);
            throw connectException;
        } catch (IOException e2222) {
            if (this.f5975d != null) {
                this.f5975d.mo15077b();
                if (this.f5976e) {
                    this.f5975d.mo15075a(e2222, null);
                } else {
                    this.f5975d.mo15074a(e2222, null);
                }
            }
        }
    }

    /* renamed from: a */
    private void m7416a() throws IOException {
        if (!Thread.currentThread().isInterrupted()) {
            try {
                AbstractHttpClient abstractHttpClient = this.f5972a;
                HttpUriRequest httpUriRequest = this.f5974c;
                HttpContext httpContext = this.f5973b;
                HttpResponse execute = !(abstractHttpClient instanceof HttpClient) ? abstractHttpClient.execute(httpUriRequest, httpContext) : HttpInstrumentation.execute((HttpClient) abstractHttpClient, httpUriRequest, httpContext);
                if (!Thread.currentThread().isInterrupted() && this.f5975d != null) {
                    this.f5975d.mo15076a(execute);
                }
            } catch (IOException e) {
                if (!Thread.currentThread().isInterrupted()) {
                    throw e;
                }
            }
        }
    }
}
