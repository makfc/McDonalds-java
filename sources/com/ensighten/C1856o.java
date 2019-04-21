package com.ensighten;

import android.content.Context;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.SyncBasicHttpContext;

/* renamed from: com.ensighten.o */
public final class C1856o {
    /* renamed from: a */
    private static int f5965a = 10;
    /* renamed from: b */
    private static int f5966b = 10000;
    /* renamed from: c */
    private final DefaultHttpClient f5967c;
    /* renamed from: d */
    private final HttpContext f5968d = new SyncBasicHttpContext(new BasicHttpContext());
    /* renamed from: e */
    private final Map<Context, List<WeakReference<Future<?>>>> f5969e;
    /* renamed from: f */
    private final Map<String, String> f5970f;
    /* renamed from: g */
    private ThreadPoolExecutor f5971g;

    /* renamed from: com.ensighten.o$1 */
    class C18531 implements HttpRequestInterceptor {
        C18531() {
        }

        public final void process(HttpRequest request, HttpContext context) {
            if (!request.containsHeader("Accept-Encoding")) {
                request.addHeader("Accept-Encoding", "gzip");
            }
            for (String str : C1856o.this.f5970f.keySet()) {
                request.addHeader(str, (String) C1856o.this.f5970f.get(str));
            }
        }
    }

    /* renamed from: com.ensighten.o$2 */
    class C18542 implements HttpResponseInterceptor {
        C18542() {
        }

        public final void process(HttpResponse response, HttpContext context) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                Header contentEncoding = entity.getContentEncoding();
                if (contentEncoding != null) {
                    for (HeaderElement name : contentEncoding.getElements()) {
                        if (name.getName().equalsIgnoreCase("gzip")) {
                            response.setEntity(new C1855a(response.getEntity()));
                            return;
                        }
                    }
                }
            }
        }
    }

    /* renamed from: com.ensighten.o$a */
    static class C1855a extends HttpEntityWrapper {
        public C1855a(HttpEntity httpEntity) {
            super(httpEntity);
        }

        public final InputStream getContent() throws IOException {
            return new GZIPInputStream(this.wrappedEntity.getContent());
        }

        public final long getContentLength() {
            return -1;
        }
    }

    public C1856o() {
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        ConnManagerParams.setTimeout(basicHttpParams, (long) f5966b);
        ConnManagerParams.setMaxConnectionsPerRoute(basicHttpParams, new ConnPerRouteBean(f5965a));
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, 10);
        HttpConnectionParams.setSoTimeout(basicHttpParams, f5966b);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, f5966b);
        HttpConnectionParams.setTcpNoDelay(basicHttpParams, true);
        HttpConnectionParams.setSocketBufferSize(basicHttpParams, 8192);
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUserAgent(basicHttpParams, String.format("android-async-http/%s (http://loopj.com/android-async-http)", new Object[]{"1.4.3"}));
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        ThreadSafeClientConnManager threadSafeClientConnManager = new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry);
        this.f5967c = new DefaultHttpClient(threadSafeClientConnManager, basicHttpParams);
        this.f5967c.addRequestInterceptor(new C18531());
        this.f5967c.addResponseInterceptor(new C18542());
        this.f5967c.setHttpRequestRetryHandler(new C1861v(5));
        this.f5971g = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        this.f5969e = new WeakHashMap();
        this.f5970f = new HashMap();
    }

    /* renamed from: a */
    public final void mo15510a(String str, C1730q c1730q) {
        C1860u c1860u = null;
        DefaultHttpClient defaultHttpClient = this.f5967c;
        HttpContext httpContext = this.f5968d;
        if (c1860u != null) {
            String b = c1860u.mo15520b();
            if (str.indexOf("?") == -1) {
                str = str + "?" + b;
            } else {
                str = str + "&" + b;
            }
        }
        m7413a(defaultHttpClient, httpContext, new HttpGet(str), c1860u, c1730q, c1860u);
    }

    /* renamed from: a */
    public final void mo15509a(Context context, String str, HttpEntity httpEntity, String str2, C1730q c1730q) {
        DefaultHttpClient defaultHttpClient = this.f5967c;
        HttpContext httpContext = this.f5968d;
        HttpPost httpPost = new HttpPost(str);
        if (httpEntity != null) {
            httpPost.setEntity(httpEntity);
        }
        m7413a(defaultHttpClient, httpContext, httpPost, null, c1730q, context);
    }

    /* renamed from: a */
    private void m7413a(DefaultHttpClient defaultHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, String str, C1730q c1730q, Context context) {
        if (str != null) {
            httpUriRequest.addHeader(TransactionStateUtil.CONTENT_TYPE_HEADER, str);
        }
        Future submit = this.f5971g.submit(new C1857p(defaultHttpClient, httpContext, httpUriRequest, c1730q));
        if (context != null) {
            List list = (List) this.f5969e.get(context);
            if (list == null) {
                list = new LinkedList();
                this.f5969e.put(context, list);
            }
            list.add(new WeakReference(submit));
        }
    }

    /* renamed from: a */
    public static HttpEntity m7412a(C1860u c1860u) {
        if (c1860u != null) {
            return c1860u.mo15517a();
        }
        return null;
    }
}
