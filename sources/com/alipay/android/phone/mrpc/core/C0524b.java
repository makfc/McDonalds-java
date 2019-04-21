package com.alipay.android.phone.mrpc.core;

import android.net.SSLCertificateSocketFactory;
import android.util.Log;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.Security;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.HttpsURLConnection;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HttpContext;

/* renamed from: com.alipay.android.phone.mrpc.core.b */
public final class C0524b implements HttpClient {
    /* renamed from: a */
    public static long f334a = 160;
    /* renamed from: b */
    private static String[] f335b = new String[]{"text/", "application/xml", "application/json"};
    /* renamed from: c */
    private static final HttpRequestInterceptor f336c = new C0525c();
    /* renamed from: d */
    private final HttpClient f337d;
    /* renamed from: e */
    private RuntimeException f338e = new IllegalStateException("AndroidHttpClient created and never closed");
    /* renamed from: f */
    private volatile C0523b f339f;

    /* renamed from: com.alipay.android.phone.mrpc.core.b$a */
    private class C0522a implements HttpRequestInterceptor {
        private C0522a() {
        }

        /* synthetic */ C0522a(C0524b c0524b, byte b) {
            this();
        }

        public final void process(HttpRequest httpRequest, HttpContext httpContext) {
            C0523b a = C0524b.this.f339f;
            if (a != null && Log.isLoggable(a.f332a, a.f333b) && (httpRequest instanceof HttpUriRequest)) {
                Log.println(a.f333b, a.f332a, C0524b.m559a((HttpUriRequest) httpRequest));
            }
        }
    }

    /* renamed from: com.alipay.android.phone.mrpc.core.b$b */
    private static class C0523b {
        /* renamed from: a */
        private final String f332a;
        /* renamed from: b */
        private final int f333b;
    }

    private C0524b(ClientConnectionManager clientConnectionManager, HttpParams httpParams) {
        this.f337d = new C0526d(this, clientConnectionManager, httpParams);
    }

    /* renamed from: a */
    public static C0524b m557a(String str) {
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUseExpectContinue(basicHttpParams, false);
        HttpConnectionParams.setStaleCheckingEnabled(basicHttpParams, true);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, 20000);
        HttpConnectionParams.setSoTimeout(basicHttpParams, 30000);
        HttpConnectionParams.setSocketBufferSize(basicHttpParams, 8192);
        HttpClientParams.setRedirecting(basicHttpParams, true);
        HttpClientParams.setAuthenticating(basicHttpParams, false);
        HttpProtocolParams.setUserAgent(basicHttpParams, str);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", SSLCertificateSocketFactory.getHttpSocketFactory(30000, null), 443));
        ThreadSafeClientConnManager threadSafeClientConnManager = new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry);
        ConnManagerParams.setTimeout(basicHttpParams, 60000);
        ConnManagerParams.setMaxConnectionsPerRoute(basicHttpParams, new ConnPerRouteBean(10));
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, 50);
        Security.setProperty("networkaddress.cache.ttl", "-1");
        HttpsURLConnection.setDefaultHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
        return new C0524b(threadSafeClientConnManager, basicHttpParams);
    }

    /* renamed from: a */
    public static InputStream m558a(HttpEntity httpEntity) {
        InputStream content = httpEntity.getContent();
        if (content == null) {
            return content;
        }
        Header contentEncoding = httpEntity.getContentEncoding();
        if (contentEncoding == null) {
            return content;
        }
        String value = contentEncoding.getValue();
        if (value == null) {
            return content;
        }
        return value.contains("gzip") ? new GZIPInputStream(content) : content;
    }

    /* renamed from: a */
    public static AbstractHttpEntity m561a(byte[] bArr) {
        if (((long) bArr.length) < f334a) {
            return new ByteArrayEntity(bArr);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gZIPOutputStream.write(bArr);
        gZIPOutputStream.close();
        ByteArrayEntity byteArrayEntity = new ByteArrayEntity(byteArrayOutputStream.toByteArray());
        byteArrayEntity.setContentEncoding("gzip");
        new StringBuilder("gzip size:").append(bArr.length).append("->").append(byteArrayEntity.getContentLength());
        return byteArrayEntity;
    }

    /* renamed from: a */
    public static void m562a(HttpRequest httpRequest) {
        httpRequest.addHeader("Accept-Encoding", "gzip");
    }

    /* renamed from: b */
    public static long m563b(String str) {
        return C0535k.m579a(str);
    }

    /* renamed from: b */
    public static void m564b(HttpRequest httpRequest) {
        httpRequest.addHeader("Connection", "Keep-Alive");
    }

    /* renamed from: b */
    private static boolean m565b(HttpUriRequest httpUriRequest) {
        Header[] headers = httpUriRequest.getHeaders("content-encoding");
        if (headers != null) {
            for (Header value : headers) {
                if ("gzip".equalsIgnoreCase(value.getValue())) {
                    return true;
                }
            }
        }
        Header[] headers2 = httpUriRequest.getHeaders("content-type");
        if (headers2 == null) {
            return true;
        }
        for (Header header : headers2) {
            for (String startsWith : f335b) {
                if (header.getValue().startsWith(startsWith)) {
                    return false;
                }
            }
        }
        return true;
    }

    /* renamed from: a */
    public final void mo7874a(HttpRequestRetryHandler httpRequestRetryHandler) {
        ((DefaultHttpClient) this.f337d).setHttpRequestRetryHandler(httpRequestRetryHandler);
    }

    public final <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler) {
        HttpClient httpClient = this.f337d;
        return !(httpClient instanceof HttpClient) ? httpClient.execute(httpHost, httpRequest, responseHandler) : HttpInstrumentation.execute(httpClient, httpHost, httpRequest, (ResponseHandler) responseHandler);
    }

    public final <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) {
        HttpClient httpClient = this.f337d;
        return !(httpClient instanceof HttpClient) ? httpClient.execute(httpHost, httpRequest, responseHandler, httpContext) : HttpInstrumentation.execute(httpClient, httpHost, httpRequest, responseHandler, httpContext);
    }

    public final <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler) {
        HttpClient httpClient = this.f337d;
        return !(httpClient instanceof HttpClient) ? httpClient.execute(httpUriRequest, responseHandler) : HttpInstrumentation.execute(httpClient, httpUriRequest, (ResponseHandler) responseHandler);
    }

    public final <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) {
        HttpClient httpClient = this.f337d;
        return !(httpClient instanceof HttpClient) ? httpClient.execute(httpUriRequest, responseHandler, httpContext) : HttpInstrumentation.execute(httpClient, httpUriRequest, (ResponseHandler) responseHandler, httpContext);
    }

    public final HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest) {
        HttpClient httpClient = this.f337d;
        return !(httpClient instanceof HttpClient) ? httpClient.execute(httpHost, httpRequest) : HttpInstrumentation.execute(httpClient, httpHost, httpRequest);
    }

    public final HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) {
        HttpClient httpClient = this.f337d;
        return !(httpClient instanceof HttpClient) ? httpClient.execute(httpHost, httpRequest, httpContext) : HttpInstrumentation.execute(httpClient, httpHost, httpRequest, httpContext);
    }

    public final HttpResponse execute(HttpUriRequest httpUriRequest) {
        HttpClient httpClient = this.f337d;
        return !(httpClient instanceof HttpClient) ? httpClient.execute(httpUriRequest) : HttpInstrumentation.execute(httpClient, httpUriRequest);
    }

    public final HttpResponse execute(HttpUriRequest httpUriRequest, HttpContext httpContext) {
        HttpClient httpClient = this.f337d;
        return !(httpClient instanceof HttpClient) ? httpClient.execute(httpUriRequest, httpContext) : HttpInstrumentation.execute(httpClient, httpUriRequest, httpContext);
    }

    public final ClientConnectionManager getConnectionManager() {
        return this.f337d.getConnectionManager();
    }

    public final HttpParams getParams() {
        return this.f337d.getParams();
    }
}
