package com.amap.api.services.core;

import android.os.Build.VERSION;
import com.facebook.stetho.common.Utf8Charset;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

/* compiled from: HttpUrlUtil */
/* renamed from: com.amap.api.services.core.bp */
public class C1120bp {
    /* renamed from: a */
    private static C1097bq f3772a;
    /* renamed from: g */
    private static TrustManager f3773g = new C1125bu();
    /* renamed from: b */
    private int f3774b;
    /* renamed from: c */
    private int f3775c;
    /* renamed from: d */
    private boolean f3776d;
    /* renamed from: e */
    private SSLContext f3777e;
    /* renamed from: f */
    private Proxy f3778f;

    /* renamed from: a */
    public static void m4945a(C1097bq c1097bq) {
        f3772a = c1097bq;
    }

    C1120bp(int i, int i2, Proxy proxy, boolean z) {
        this.f3774b = i;
        this.f3775c = i2;
        this.f3778f = proxy;
        this.f3776d = z;
        if (z) {
            try {
                SSLContext instance = SSLContext.getInstance("TLS");
                instance.init(null, new TrustManager[]{f3773g}, null);
                this.f3777e = instance;
            } catch (NoSuchAlgorithmException e) {
                C1099ax.m4800a(e, "HttpUrlUtil", "HttpUrlUtil");
                e.printStackTrace();
            } catch (KeyManagementException e2) {
                C1099ax.m4800a(e2, "HttpUrlUtil", "HttpUrlUtil");
                e2.printStackTrace();
            } catch (Throwable e22) {
                C1099ax.m4800a(e22, "HttpUtil", "HttpUtil");
                e22.printStackTrace();
            }
        }
    }

    C1120bp(int i, int i2, Proxy proxy) {
        this(i, i2, proxy, false);
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public byte[] mo12089a(String str, Map<String, String> map, Map<String, String> map2) throws C1133u {
        try {
            String a = m4943a((Map) map2);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str);
            if (a != null) {
                stringBuffer.append("?").append(a);
            }
            HttpURLConnection a2 = m4944a(new URL(stringBuffer.toString()));
            m4946a(map, a2);
            a2.setRequestMethod("GET");
            a2.setDoInput(true);
            a2.connect();
            return m4947a(a2);
        } catch (MalformedURLException e) {
            C1099ax.m4800a(e, "HttpUrlUtil", "getRequest");
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            C1099ax.m4800a(e2, "HttpUrlUtil", "getRequest");
            e2.printStackTrace();
            return null;
        } catch (Throwable e22) {
            C1099ax.m4800a(e22, "HttpUrlUtil", "getRequest");
            e22.printStackTrace();
            return null;
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public byte[] mo12091a(String str, Map<String, String> map, byte[] bArr) throws C1133u {
        try {
            HttpURLConnection a = m4944a(new URL(str));
            m4946a(map, a);
            a.setRequestMethod("POST");
            a.setUseCaches(false);
            a.setDoInput(true);
            a.setDoOutput(true);
            if (bArr != null && bArr.length > 0) {
                DataOutputStream dataOutputStream = new DataOutputStream(a.getOutputStream());
                dataOutputStream.write(bArr);
                dataOutputStream.close();
            }
            a.connect();
            return m4947a(a);
        } catch (MalformedURLException e) {
            C1099ax.m4800a(e, "HttpUrlUtil", "postRequest");
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            C1099ax.m4800a(e2, "HttpUrlUtil", "postRequest");
            e2.printStackTrace();
            return null;
        } catch (Throwable e22) {
            C1099ax.m4800a(e22, "HttpUrlUtil", "postRequest");
            e22.printStackTrace();
            return null;
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: b */
    public byte[] mo12092b(String str, Map<String, String> map, Map<String, String> map2) throws C1133u {
        String a = m4943a((Map) map2);
        if (a == null) {
            return mo12091a(str, (Map) map, new byte[0]);
        }
        try {
            return mo12091a(str, (Map) map, a.getBytes(Utf8Charset.NAME));
        } catch (UnsupportedEncodingException e) {
            C1099ax.m4800a(e, "HttpUrlUtil", "postRequest1");
            e.printStackTrace();
            return mo12091a(str, (Map) map, a.getBytes());
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:35:0x0052=Splitter:B:35:0x0052, B:47:0x0073=Splitter:B:47:0x0073, B:13:0x001b=Splitter:B:13:0x001b} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x005e A:{SYNTHETIC, Splitter:B:38:0x005e} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0063 A:{SYNTHETIC, Splitter:B:41:0x0063} */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x007f A:{SYNTHETIC, Splitter:B:50:0x007f} */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0084 A:{SYNTHETIC, Splitter:B:53:0x0084} */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0097 A:{SYNTHETIC, Splitter:B:60:0x0097} */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x009c A:{SYNTHETIC, Splitter:B:63:0x009c} */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0027 A:{SYNTHETIC, Splitter:B:16:0x0027} */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x002c A:{SYNTHETIC, Splitter:B:19:0x002c} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x005e A:{SYNTHETIC, Splitter:B:38:0x005e} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0063 A:{SYNTHETIC, Splitter:B:41:0x0063} */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x007f A:{SYNTHETIC, Splitter:B:50:0x007f} */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0084 A:{SYNTHETIC, Splitter:B:53:0x0084} */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0097 A:{SYNTHETIC, Splitter:B:60:0x0097} */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x009c A:{SYNTHETIC, Splitter:B:63:0x009c} */
    /* renamed from: a */
    public byte[] mo12090a(java.lang.String r7, java.util.Map<java.lang.String, java.lang.String> r8, org.apache.http.HttpEntity r9) throws com.amap.api.services.core.C1133u {
        /*
        r6 = this;
        r0 = 0;
        r3 = new java.io.ByteArrayOutputStream;	 Catch:{ IllegalStateException -> 0x0108, IOException -> 0x004f, Throwable -> 0x0070, all -> 0x0091 }
        r3.<init>();	 Catch:{ IllegalStateException -> 0x0108, IOException -> 0x004f, Throwable -> 0x0070, all -> 0x0091 }
        r2 = r9.getContent();	 Catch:{ IllegalStateException -> 0x010d, IOException -> 0x0101, Throwable -> 0x00fa, all -> 0x00f4 }
        r1 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r1 = new byte[r1];	 Catch:{ IllegalStateException -> 0x001a, IOException -> 0x0105, Throwable -> 0x00fe }
    L_0x000e:
        r4 = r2.read(r1);	 Catch:{ IllegalStateException -> 0x001a, IOException -> 0x0105, Throwable -> 0x00fe }
        r5 = -1;
        if (r4 == r5) goto L_0x0030;
    L_0x0015:
        r5 = 0;
        r3.write(r1, r5, r4);	 Catch:{ IllegalStateException -> 0x001a, IOException -> 0x0105, Throwable -> 0x00fe }
        goto L_0x000e;
    L_0x001a:
        r1 = move-exception;
    L_0x001b:
        r4 = "HttpUrlUtil";
        r5 = "postRequest2";
        com.amap.api.services.core.C1099ax.m4800a(r1, r4, r5);	 Catch:{ all -> 0x00f8 }
        r1.printStackTrace();	 Catch:{ all -> 0x00f8 }
        if (r3 == 0) goto L_0x002a;
    L_0x0027:
        r3.close();	 Catch:{ IOException -> 0x00b8 }
    L_0x002a:
        if (r2 == 0) goto L_0x002f;
    L_0x002c:
        r2.close();	 Catch:{ Exception -> 0x00c5 }
    L_0x002f:
        return r0;
    L_0x0030:
        r1 = r3.toByteArray();	 Catch:{ IllegalStateException -> 0x001a, IOException -> 0x0105, Throwable -> 0x00fe }
        r0 = r6.mo12091a(r7, r8, r1);	 Catch:{ IllegalStateException -> 0x001a, IOException -> 0x0105, Throwable -> 0x00fe }
        if (r3 == 0) goto L_0x003d;
    L_0x003a:
        r3.close();	 Catch:{ IOException -> 0x00e7 }
    L_0x003d:
        if (r2 == 0) goto L_0x002f;
    L_0x003f:
        r2.close();	 Catch:{ Exception -> 0x0043 }
        goto L_0x002f;
    L_0x0043:
        r1 = move-exception;
        r2 = "HttpUrlUtil";
        r3 = "postRequest2";
        com.amap.api.services.core.C1099ax.m4800a(r1, r2, r3);
    L_0x004b:
        r1.printStackTrace();
        goto L_0x002f;
    L_0x004f:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
    L_0x0052:
        r4 = "HttpUrlUtil";
        r5 = "postRequest2";
        com.amap.api.services.core.C1099ax.m4800a(r1, r4, r5);	 Catch:{ all -> 0x00f8 }
        r1.printStackTrace();	 Catch:{ all -> 0x00f8 }
        if (r3 == 0) goto L_0x0061;
    L_0x005e:
        r3.close();	 Catch:{ IOException -> 0x00cf }
    L_0x0061:
        if (r2 == 0) goto L_0x002f;
    L_0x0063:
        r2.close();	 Catch:{ Exception -> 0x0067 }
        goto L_0x002f;
    L_0x0067:
        r1 = move-exception;
        r2 = "HttpUrlUtil";
        r3 = "postRequest2";
        com.amap.api.services.core.C1099ax.m4800a(r1, r2, r3);
        goto L_0x004b;
    L_0x0070:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
    L_0x0073:
        r4 = "HttpUrlUtil";
        r5 = "postRequest2";
        com.amap.api.services.core.C1099ax.m4800a(r1, r4, r5);	 Catch:{ all -> 0x00f8 }
        r1.printStackTrace();	 Catch:{ all -> 0x00f8 }
        if (r3 == 0) goto L_0x0082;
    L_0x007f:
        r3.close();	 Catch:{ IOException -> 0x00db }
    L_0x0082:
        if (r2 == 0) goto L_0x002f;
    L_0x0084:
        r2.close();	 Catch:{ Exception -> 0x0088 }
        goto L_0x002f;
    L_0x0088:
        r1 = move-exception;
        r2 = "HttpUrlUtil";
        r3 = "postRequest2";
        com.amap.api.services.core.C1099ax.m4800a(r1, r2, r3);
        goto L_0x004b;
    L_0x0091:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r0 = r1;
    L_0x0095:
        if (r3 == 0) goto L_0x009a;
    L_0x0097:
        r3.close();	 Catch:{ IOException -> 0x00a0 }
    L_0x009a:
        if (r2 == 0) goto L_0x009f;
    L_0x009c:
        r2.close();	 Catch:{ Exception -> 0x00ac }
    L_0x009f:
        throw r0;
    L_0x00a0:
        r1 = move-exception;
        r3 = "HttpUrlUtil";
        r4 = "postRequest2";
        com.amap.api.services.core.C1099ax.m4800a(r1, r3, r4);
        r1.printStackTrace();
        goto L_0x009a;
    L_0x00ac:
        r1 = move-exception;
        r2 = "HttpUrlUtil";
        r3 = "postRequest2";
        com.amap.api.services.core.C1099ax.m4800a(r1, r2, r3);
        r1.printStackTrace();
        goto L_0x009f;
    L_0x00b8:
        r1 = move-exception;
        r3 = "HttpUrlUtil";
        r4 = "postRequest2";
        com.amap.api.services.core.C1099ax.m4800a(r1, r3, r4);
        r1.printStackTrace();
        goto L_0x002a;
    L_0x00c5:
        r1 = move-exception;
        r2 = "HttpUrlUtil";
        r3 = "postRequest2";
        com.amap.api.services.core.C1099ax.m4800a(r1, r2, r3);
        goto L_0x004b;
    L_0x00cf:
        r1 = move-exception;
        r3 = "HttpUrlUtil";
        r4 = "postRequest2";
        com.amap.api.services.core.C1099ax.m4800a(r1, r3, r4);
        r1.printStackTrace();
        goto L_0x0061;
    L_0x00db:
        r1 = move-exception;
        r3 = "HttpUrlUtil";
        r4 = "postRequest2";
        com.amap.api.services.core.C1099ax.m4800a(r1, r3, r4);
        r1.printStackTrace();
        goto L_0x0082;
    L_0x00e7:
        r1 = move-exception;
        r3 = "HttpUrlUtil";
        r4 = "postRequest2";
        com.amap.api.services.core.C1099ax.m4800a(r1, r3, r4);
        r1.printStackTrace();
        goto L_0x003d;
    L_0x00f4:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x0095;
    L_0x00f8:
        r0 = move-exception;
        goto L_0x0095;
    L_0x00fa:
        r1 = move-exception;
        r2 = r0;
        goto L_0x0073;
    L_0x00fe:
        r1 = move-exception;
        goto L_0x0073;
    L_0x0101:
        r1 = move-exception;
        r2 = r0;
        goto L_0x0052;
    L_0x0105:
        r1 = move-exception;
        goto L_0x0052;
    L_0x0108:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        goto L_0x001b;
    L_0x010d:
        r1 = move-exception;
        r2 = r0;
        goto L_0x001b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.core.C1120bp.mo12090a(java.lang.String, java.util.Map, org.apache.http.HttpEntity):byte[]");
    }

    /* renamed from: a */
    private void m4946a(Map<String, String> map, HttpURLConnection httpURLConnection) {
        if (map != null) {
            for (String str : map.keySet()) {
                httpURLConnection.addRequestProperty(str, (String) map.get(str));
            }
        }
        httpURLConnection.setConnectTimeout(this.f3774b);
        httpURLConnection.setReadTimeout(this.f3775c);
    }

    /* renamed from: a */
    private HttpURLConnection m4944a(URL url) throws IOException {
        URLConnection openConnectionWithProxy;
        HttpURLConnection openConnectionWithProxy2;
        if (this.f3778f != null) {
            openConnectionWithProxy2 = HttpInstrumentation.openConnectionWithProxy(url.openConnection(this.f3778f));
        } else {
            openConnectionWithProxy2 = (HttpURLConnection) HttpInstrumentation.openConnection(url.openConnection());
        }
        if (this.f3776d) {
            openConnectionWithProxy2 = (HttpsURLConnection) openConnectionWithProxy2;
            ((HttpsURLConnection) openConnectionWithProxy2).setSSLSocketFactory(this.f3777e.getSocketFactory());
        } else {
            openConnectionWithProxy2 = (HttpURLConnection) openConnectionWithProxy2;
        }
        if (VERSION.SDK != null && VERSION.SDK_INT > 13) {
            openConnectionWithProxy2.setRequestProperty("Connection", "close");
        }
        return openConnectionWithProxy2;
    }

    /* renamed from: a */
    private byte[] m4947a(HttpURLConnection httpURLConnection) throws C1133u, IOException {
        IOException e;
        Throwable th;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != 200) {
                throw new C1133u("网络异常原因：" + httpURLConnection.getResponseMessage() + " 网络异常状态码：" + responseCode);
            }
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                InputStream gZIPInputStream;
                inputStream = httpURLConnection.getInputStream();
                InputStream pushbackInputStream = new PushbackInputStream(inputStream, 2);
                byte[] bArr = new byte[2];
                pushbackInputStream.read(bArr);
                pushbackInputStream.unread(bArr);
                if (bArr[0] == (byte) 31 && bArr[1] == (byte) -117) {
                    gZIPInputStream = new GZIPInputStream(pushbackInputStream);
                } else {
                    gZIPInputStream = pushbackInputStream;
                }
                byte[] bArr2 = new byte[1024];
                while (true) {
                    int read = gZIPInputStream.read(bArr2);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr2, 0, read);
                }
                if (f3772a != null) {
                    f3772a.mo12038a();
                }
                bArr = byteArrayOutputStream.toByteArray();
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e2) {
                        C1099ax.m4800a(e2, "HttpUrlUtil", "parseResult");
                        e2.printStackTrace();
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e3) {
                        C1099ax.m4800a(e3, "HttpUrlUtil", "parseResult");
                        e3.printStackTrace();
                    }
                }
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect();
                    } catch (Throwable e32) {
                        C1099ax.m4800a(e32, "HttpUrlUtil", "parseResult");
                        e32.printStackTrace();
                    }
                }
                return bArr;
            } catch (IOException e4) {
                e = e4;
                try {
                    throw e;
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        } catch (IOException e5) {
            e = e5;
            byteArrayOutputStream = inputStream;
            throw e;
        } catch (Throwable th3) {
            th = th3;
            Object obj = inputStream;
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e22) {
                    C1099ax.m4800a(e22, "HttpUrlUtil", "parseResult");
                    e22.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e322) {
                    C1099ax.m4800a(e322, "HttpUrlUtil", "parseResult");
                    e322.printStackTrace();
                }
            }
            if (httpURLConnection != null) {
                try {
                    httpURLConnection.disconnect();
                } catch (Throwable e3222) {
                    C1099ax.m4800a(e3222, "HttpUrlUtil", "parseResult");
                    e3222.printStackTrace();
                }
            }
            throw th;
        }
    }

    /* renamed from: a */
    private String m4943a(Map<String, String> map) {
        LinkedList linkedList = new LinkedList();
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                linkedList.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
            }
        }
        if (linkedList.size() > 0) {
            return URLEncodedUtils.format(linkedList, Utf8Charset.NAME);
        }
        return null;
    }
}
