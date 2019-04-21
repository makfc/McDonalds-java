package com.admaster.square.utils;

import com.facebook.stetho.common.Utf8Charset;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;

/* renamed from: com.admaster.square.utils.b */
public class ConnectUtil {
    /* renamed from: a */
    private static ConnectUtil f251a = null;
    /* renamed from: b */
    private String f252b = null;

    private ConnectUtil() {
    }

    /* renamed from: a */
    public static ConnectUtil m398a() {
        if (f251a == null) {
            f251a = new ConnectUtil();
        }
        return f251a;
    }

    /* renamed from: a */
    public void mo7804a(String str) {
        this.f252b = str;
    }

    /* renamed from: b */
    public String mo7805b() {
        return this.f252b;
    }

    /* renamed from: a */
    public String mo7803a(String str, String str2) {
        try {
            HttpPost httpPost = new HttpPost(str);
            httpPost.addHeader(TransactionStateUtil.CONTENT_TYPE_HEADER, Constant.f274p);
            StringEntity stringEntity = new StringEntity(str2);
            stringEntity.setContentType(Constant.f275q);
            stringEntity.setContentEncoding(new BasicHeader(TransactionStateUtil.CONTENT_TYPE_HEADER, Constant.f274p));
            httpPost.setEntity(stringEntity);
            return m399a(mo7806c(), httpPost, false, false, true);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public String mo7802a(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 8192);
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuilder.append(new StringBuilder(String.valueOf(readLine)).append("\n").toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    /* renamed from: c */
    public DefaultHttpClient mo7806c() {
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        ConnManagerParams.setTimeout(basicHttpParams, (long) Constant.f270l);
        ConnManagerParams.setMaxConnectionsPerRoute(basicHttpParams, new ConnPerRouteBean(Constant.f269k));
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, Constant.f269k);
        HttpConnectionParams.setSoTimeout(basicHttpParams, Constant.f270l);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, Constant.f270l);
        HttpConnectionParams.setTcpNoDelay(basicHttpParams, true);
        HttpConnectionParams.setSocketBufferSize(basicHttpParams, Constant.f271m);
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(basicHttpParams, Utf8Charset.NAME);
        HttpProtocolParams.setUserAgent(basicHttpParams, this.f252b);
        HttpProtocolParams.setUseExpectContinue(basicHttpParams, false);
        try {
            KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
            instance.load(null, null);
            new C0487c(instance).setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            schemeRegistry.register(new Scheme("https", C0489e.m405a(), 443));
            ThreadSafeClientConnManager threadSafeClientConnManager = new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry);
            basicHttpParams.setParameter("http.protocol.content-charset", Utf8Charset.NAME);
            return new DefaultHttpClient(threadSafeClientConnManager, basicHttpParams);
        } catch (Exception e) {
            e.printStackTrace();
            return new DefaultHttpClient(basicHttpParams);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x0090 A:{SYNTHETIC, Splitter:B:55:0x0090} */
    /* renamed from: a */
    private java.lang.String m399a(org.apache.http.client.HttpClient r6, org.apache.http.client.methods.HttpUriRequest r7, boolean r8, boolean r9, boolean r10) {
        /*
        r5 = this;
        r0 = 0;
        r1 = "";
        r3 = 0;
        if (r9 == 0) goto L_0x000d;
    L_0x0006:
        r1 = "Accept-Encoding";
        r2 = "gzip";
        r7.addHeader(r1, r2);	 Catch:{ Exception -> 0x00a5, all -> 0x00a1 }
    L_0x000d:
        r1 = r6 instanceof org.apache.http.client.HttpClient;	 Catch:{ Exception -> 0x00a5, all -> 0x00a1 }
        if (r1 != 0) goto L_0x001e;
    L_0x0011:
        r1 = r6.execute(r7);	 Catch:{ Exception -> 0x00a5, all -> 0x00a1 }
        r2 = r1;
    L_0x0016:
        if (r2 != 0) goto L_0x002b;
    L_0x0018:
        if (r0 == 0) goto L_0x001d;
    L_0x001a:
        r3.close();	 Catch:{ Exception -> 0x0026 }
    L_0x001d:
        return r0;
    L_0x001e:
        r6 = (org.apache.http.client.HttpClient) r6;	 Catch:{ Exception -> 0x00a5, all -> 0x00a1 }
        r1 = com.newrelic.agent.android.instrumentation.HttpInstrumentation.execute(r6, r7);	 Catch:{ Exception -> 0x00a5, all -> 0x00a1 }
        r2 = r1;
        goto L_0x0016;
    L_0x0026:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x001d;
    L_0x002b:
        r4 = r2.getEntity();	 Catch:{ Exception -> 0x00a5, all -> 0x00a1 }
        r1 = r2.getStatusLine();	 Catch:{ Exception -> 0x00a5, all -> 0x00a1 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x00a5, all -> 0x00a1 }
        if (r10 == 0) goto L_0x0045;
    L_0x0039:
        if (r0 == 0) goto L_0x003e;
    L_0x003b:
        r3.close();	 Catch:{ Exception -> 0x0040 }
    L_0x003e:
        r0 = r1;
        goto L_0x001d;
    L_0x0040:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x003e;
    L_0x0045:
        r3 = "Content-Encoding";
        r3 = r2.getFirstHeader(r3);	 Catch:{ Exception -> 0x00a5, all -> 0x00a1 }
        if (r4 == 0) goto L_0x0094;
    L_0x004d:
        r2 = r4.getContent();	 Catch:{ Exception -> 0x00a5, all -> 0x00a1 }
        if (r3 == 0) goto L_0x0070;
    L_0x0053:
        r1 = r3.getValue();	 Catch:{ Exception -> 0x0078 }
        r4 = "gzip";
        r1 = r1.equalsIgnoreCase(r4);	 Catch:{ Exception -> 0x0078 }
        if (r1 == 0) goto L_0x0070;
    L_0x005f:
        r1 = new java.util.zip.GZIPInputStream;	 Catch:{ Exception -> 0x0078 }
        r1.<init>(r2);	 Catch:{ Exception -> 0x0078 }
        r2 = r1;
    L_0x0065:
        r1 = r5.mo7802a(r2);	 Catch:{ Exception -> 0x0078 }
    L_0x0069:
        if (r2 == 0) goto L_0x00a8;
    L_0x006b:
        r2.close();	 Catch:{ Exception -> 0x009b }
        r0 = r1;
        goto L_0x001d;
    L_0x0070:
        if (r3 != 0) goto L_0x0087;
    L_0x0072:
        r1 = "contentEncoding == null";
        com.admaster.square.api.Logger.m364b(r1);	 Catch:{ Exception -> 0x0078 }
        goto L_0x0065;
    L_0x0078:
        r1 = move-exception;
    L_0x0079:
        r1.printStackTrace();	 Catch:{ all -> 0x008d }
        if (r2 == 0) goto L_0x001d;
    L_0x007e:
        r2.close();	 Catch:{ Exception -> 0x0082 }
        goto L_0x001d;
    L_0x0082:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x001d;
    L_0x0087:
        r1 = "equalsIgnoreCase isn't gzip mode ";
        com.admaster.square.api.Logger.m364b(r1);	 Catch:{ Exception -> 0x0078 }
        goto L_0x0065;
    L_0x008d:
        r0 = move-exception;
    L_0x008e:
        if (r2 == 0) goto L_0x0093;
    L_0x0090:
        r2.close();	 Catch:{ Exception -> 0x0096 }
    L_0x0093:
        throw r0;
    L_0x0094:
        r2 = r0;
        goto L_0x0069;
    L_0x0096:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0093;
    L_0x009b:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x001d;
    L_0x00a1:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x008e;
    L_0x00a5:
        r1 = move-exception;
        r2 = r0;
        goto L_0x0079;
    L_0x00a8:
        r0 = r1;
        goto L_0x001d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.admaster.square.utils.ConnectUtil.m399a(org.apache.http.client.HttpClient, org.apache.http.client.methods.HttpUriRequest, boolean, boolean, boolean):java.lang.String");
    }
}
