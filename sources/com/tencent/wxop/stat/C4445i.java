package com.tencent.wxop.stat;

import android.content.Context;
import com.facebook.internal.ServerProtocol;
import com.facebook.stetho.common.Utf8Charset;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import com.tencent.p059a.p060a.p061a.p062a.C4343g;
import com.tencent.p059a.p060a.p061a.p062a.C4344h;
import com.tencent.wxop.stat.common.C4427e;
import com.tencent.wxop.stat.common.C4428f;
import com.tencent.wxop.stat.common.C4433k;
import com.tencent.wxop.stat.common.StatLogger;
import com.tencent.wxop.stat.p069a.C4377e;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPOutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.i */
class C4445i {
    /* renamed from: d */
    private static StatLogger f7174d = C4433k.m8111b();
    /* renamed from: e */
    private static C4445i f7175e = null;
    /* renamed from: f */
    private static Context f7176f = null;
    /* renamed from: a */
    DefaultHttpClient f7177a = null;
    /* renamed from: b */
    C4427e f7178b = null;
    /* renamed from: c */
    StringBuilder f7179c = new StringBuilder(4096);
    /* renamed from: g */
    private long f7180g = 0;

    private C4445i(Context context) {
        try {
            f7176f = context.getApplicationContext();
            this.f7180g = System.currentTimeMillis() / 1000;
            this.f7178b = new C4427e();
            if (StatConfig.isDebugEnable()) {
                try {
                    Logger.getLogger("org.apache.http.wire").setLevel(Level.FINER);
                    Logger.getLogger("org.apache.http.headers").setLevel(Level.FINER);
                    System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
                    System.setProperty("org.apache.commons.logging.simplelog.showdatetime", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                    System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire", "debug");
                    System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "debug");
                    System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.headers", "debug");
                } catch (Throwable th) {
                }
            }
            BasicHttpParams basicHttpParams = new BasicHttpParams();
            HttpConnectionParams.setStaleCheckingEnabled(basicHttpParams, false);
            HttpConnectionParams.setConnectionTimeout(basicHttpParams, 10000);
            HttpConnectionParams.setSoTimeout(basicHttpParams, 10000);
            this.f7177a = new DefaultHttpClient(basicHttpParams);
            this.f7177a.setKeepAliveStrategy(new C4446j(this));
        } catch (Throwable th2) {
            f7174d.mo33949e(th2);
        }
    }

    /* renamed from: a */
    static Context m8177a() {
        return f7176f;
    }

    /* renamed from: a */
    static void m8178a(Context context) {
        f7176f = context.getApplicationContext();
    }

    /* renamed from: a */
    private void m8179a(JSONObject jSONObject) {
        try {
            String optString = jSONObject.optString("mid");
            if (C4344h.m7875c(optString)) {
                if (StatConfig.isDebugEnable()) {
                    f7174d.mo33952i("update mid:" + optString);
                }
                C4343g.m7865E(f7176f).mo33763a(optString);
            }
            if (!jSONObject.isNull("cfg")) {
                StatConfig.m7924a(f7176f, jSONObject.getJSONObject("cfg"));
            }
            if (!jSONObject.isNull("ncts")) {
                int i = jSONObject.getInt("ncts");
                int currentTimeMillis = (int) (((long) i) - (System.currentTimeMillis() / 1000));
                if (StatConfig.isDebugEnable()) {
                    f7174d.mo33952i("server time:" + i + ", diff time:" + currentTimeMillis);
                }
                C4433k.m8143z(f7176f);
                C4433k.m8107a(f7176f, currentTimeMillis);
            }
        } catch (Throwable th) {
            f7174d.mo33956w(th);
        }
    }

    /* renamed from: b */
    static C4445i m8180b(Context context) {
        if (f7175e == null) {
            synchronized (C4445i.class) {
                if (f7175e == null) {
                    f7175e = new C4445i(context);
                }
            }
        }
        return f7175e;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo33980a(C4377e c4377e, C4407h c4407h) {
        mo33982b(Arrays.asList(new String[]{c4377e.mo33890g()}), c4407h);
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo33981a(List<?> list, C4407h c4407h) {
        int i = 0;
        if (list != null && !list.isEmpty()) {
            int size = list.size();
            list.get(0);
            Throwable th;
            try {
                String str;
                this.f7179c.delete(0, this.f7179c.length());
                this.f7179c.append("[");
                String str2 = "rc4";
                for (int i2 = 0; i2 < size; i2++) {
                    this.f7179c.append(list.get(i2).toString());
                    if (i2 != size - 1) {
                        this.f7179c.append(",");
                    }
                }
                this.f7179c.append("]");
                String stringBuilder = this.f7179c.toString();
                size = stringBuilder.length();
                String str3 = StatConfig.getStatReportUrl() + "/?index=" + this.f7180g;
                this.f7180g++;
                if (StatConfig.isDebugEnable()) {
                    f7174d.mo33952i("[" + str3 + "]Send request(" + size + "bytes), content:" + stringBuilder);
                }
                HttpPost httpPost = new HttpPost(str3);
                httpPost.addHeader("Accept-Encoding", "gzip");
                httpPost.setHeader("Connection", "Keep-Alive");
                httpPost.removeHeaders("Cache-Control");
                HttpHost a = C4389a.m7995a(f7176f).mo33898a();
                httpPost.addHeader("Content-Encoding", str2);
                if (a == null) {
                    this.f7177a.getParams().removeParameter("http.route.default-proxy");
                } else {
                    if (StatConfig.isDebugEnable()) {
                        f7174d.mo33946d("proxy:" + a.toHostString());
                    }
                    httpPost.addHeader("X-Content-Encoding", str2);
                    this.f7177a.getParams().setParameter("http.route.default-proxy", a);
                    httpPost.addHeader("X-Online-Host", StatConfig.f6897k);
                    httpPost.addHeader("Accept", "*/*");
                    httpPost.addHeader(TransactionStateUtil.CONTENT_TYPE_HEADER, "json");
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(size);
                byte[] bytes = stringBuilder.getBytes(Utf8Charset.NAME);
                int length = bytes.length;
                if (size > StatConfig.f6901o) {
                    i = 1;
                }
                if (i != 0) {
                    httpPost.removeHeaders("Content-Encoding");
                    str = str2 + ",gzip";
                    httpPost.addHeader("Content-Encoding", str);
                    if (a != null) {
                        httpPost.removeHeaders("X-Content-Encoding");
                        httpPost.addHeader("X-Content-Encoding", str);
                    }
                    byteArrayOutputStream.write(new byte[4]);
                    GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                    gZIPOutputStream.write(bytes);
                    gZIPOutputStream.close();
                    bytes = byteArrayOutputStream.toByteArray();
                    ByteBuffer.wrap(bytes, 0, 4).putInt(length);
                    if (StatConfig.isDebugEnable()) {
                        f7174d.mo33946d("before Gzip:" + length + " bytes, after Gzip:" + bytes.length + " bytes");
                    }
                }
                httpPost.setEntity(new ByteArrayEntity(C4428f.m8085a(bytes)));
                DefaultHttpClient defaultHttpClient = this.f7177a;
                HttpResponse execute = !(defaultHttpClient instanceof HttpClient) ? defaultHttpClient.execute(httpPost) : HttpInstrumentation.execute(defaultHttpClient, httpPost);
                HttpEntity entity = execute.getEntity();
                size = execute.getStatusLine().getStatusCode();
                long contentLength = entity.getContentLength();
                if (StatConfig.isDebugEnable()) {
                    f7174d.mo33952i("http recv response status code:" + size + ", content length:" + contentLength);
                }
                if (contentLength <= 0) {
                    f7174d.mo33948e((Object) "Server response no data.");
                    if (c4407h != null) {
                        c4407h.mo33924b();
                    }
                    EntityUtils.toString(entity);
                    return;
                }
                if (contentLength > 0) {
                    InputStream content = entity.getContent();
                    DataInputStream dataInputStream = new DataInputStream(content);
                    bytes = new byte[((int) entity.getContentLength())];
                    dataInputStream.readFully(bytes);
                    content.close();
                    dataInputStream.close();
                    Header firstHeader = execute.getFirstHeader("Content-Encoding");
                    if (firstHeader != null) {
                        if (firstHeader.getValue().equalsIgnoreCase("gzip,rc4")) {
                            bytes = C4428f.m8087b(C4433k.m8109a(bytes));
                        } else if (firstHeader.getValue().equalsIgnoreCase("rc4,gzip")) {
                            bytes = C4433k.m8109a(C4428f.m8087b(bytes));
                        } else if (firstHeader.getValue().equalsIgnoreCase("gzip")) {
                            bytes = C4433k.m8109a(bytes);
                        } else if (firstHeader.getValue().equalsIgnoreCase("rc4")) {
                            bytes = C4428f.m8087b(bytes);
                        }
                    }
                    str = new String(bytes, Utf8Charset.NAME);
                    if (StatConfig.isDebugEnable()) {
                        f7174d.mo33952i("http get response data:" + str);
                    }
                    JSONObject init = JSONObjectInstrumentation.init(str);
                    if (size == 200) {
                        m8179a(init);
                        if (c4407h != null) {
                            if (init.optInt("ret") == 0) {
                                c4407h.mo33923a();
                            } else {
                                f7174d.error((Object) "response error data.");
                                c4407h.mo33924b();
                            }
                        }
                    } else {
                        f7174d.error("Server response error code:" + size + ", error:" + new String(bytes, Utf8Charset.NAME));
                        if (c4407h != null) {
                            c4407h.mo33924b();
                        }
                    }
                    content.close();
                } else {
                    EntityUtils.toString(entity);
                }
                byteArrayOutputStream.close();
                th = null;
                if (th != null) {
                    f7174d.error(th);
                    if (c4407h != null) {
                        try {
                            c4407h.mo33924b();
                        } catch (Throwable th2) {
                            f7174d.mo33949e(th2);
                        }
                    }
                    if (th instanceof OutOfMemoryError) {
                        System.gc();
                        this.f7179c = null;
                        this.f7179c = new StringBuilder(2048);
                    }
                    C4389a.m7995a(f7176f).mo33902d();
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: b */
    public void mo33982b(List<?> list, C4407h c4407h) {
        if (this.f7178b != null) {
            this.f7178b.mo33966a(new C4447k(this, list, c4407h));
        }
    }
}
