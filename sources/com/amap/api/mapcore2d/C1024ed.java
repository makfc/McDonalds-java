package com.amap.api.mapcore2d;

import android.os.Build.VERSION;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

/* compiled from: HttpUrlUtil */
/* renamed from: com.amap.api.mapcore2d.ed */
public class C1024ed {
    /* renamed from: a */
    private static C0988ee f2899a;
    /* renamed from: b */
    private int f2900b;
    /* renamed from: c */
    private int f2901c;
    /* renamed from: d */
    private boolean f2902d;
    /* renamed from: e */
    private SSLContext f2903e;
    /* renamed from: f */
    private Proxy f2904f;
    /* renamed from: g */
    private volatile boolean f2905g;
    /* renamed from: h */
    private long f2906h;
    /* renamed from: i */
    private long f2907i;
    /* renamed from: j */
    private HostnameVerifier f2908j;

    /* compiled from: HttpUrlUtil */
    /* renamed from: com.amap.api.mapcore2d.ed$1 */
    class C10231 implements HostnameVerifier {
        C10231() {
        }

        public boolean verify(String str, SSLSession sSLSession) {
            return HttpsURLConnection.getDefaultHostnameVerifier().verify("*.amap.com", sSLSession);
        }
    }

    /* renamed from: a */
    public static void m4268a(C0988ee c0988ee) {
        f2899a = c0988ee;
    }

    C1024ed(int i, int i2, Proxy proxy, boolean z) {
        this.f2905g = false;
        this.f2906h = -1;
        this.f2907i = 0;
        this.f2908j = new C10231();
        this.f2900b = i;
        this.f2901c = i2;
        this.f2904f = proxy;
        this.f2902d = z;
        if (z) {
            try {
                SSLContext instance = SSLContext.getInstance("TLS");
                instance.init(null, null, null);
                this.f2903e = instance;
            } catch (Throwable th) {
                C0982da.m4076a(th, "HttpUtil", "HttpUtil");
            }
        }
    }

    C1024ed(int i, int i2, Proxy proxy) {
        this(i, i2, proxy, false);
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo10277a(long j) {
        this.f2907i = j;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: b */
    public void mo10279b(long j) {
        this.f2906h = j;
    }

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0124 A:{SYNTHETIC, Splitter:B:64:0x0124} */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0129 A:{SYNTHETIC, Splitter:B:67:0x0129} */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0121 A:{ExcHandler: all (th java.lang.Throwable), Splitter:B:19:0x0058} */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0124 A:{SYNTHETIC, Splitter:B:64:0x0124} */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0129 A:{SYNTHETIC, Splitter:B:67:0x0129} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:62:0x0121, code skipped:
            r0 = th;
     */
    /* JADX WARNING: Missing block: B:65:?, code skipped:
            r1.close();
     */
    /* JADX WARNING: Missing block: B:68:?, code skipped:
            r2.disconnect();
     */
    /* JADX WARNING: Missing block: B:92:0x0182, code skipped:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:93:0x0183, code skipped:
            r1.printStackTrace();
            com.amap.api.mapcore2d.C0982da.m4076a(r1, "HttpUrlUtil", "makeDownloadGetRequest");
     */
    /* JADX WARNING: Missing block: B:94:0x018e, code skipped:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:95:0x018f, code skipped:
            r1.printStackTrace();
            com.amap.api.mapcore2d.C0982da.m4076a(r1, "HttpUrlUtil", "makeDownloadGetRequest");
     */
    /* JADX WARNING: Missing block: B:96:0x019a, code skipped:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:97:0x019b, code skipped:
            r1.printStackTrace();
            com.amap.api.mapcore2d.C0982da.m4076a(r1, "HttpUrlUtil", "makeDownloadGetRequest");
     */
    /* JADX WARNING: Missing block: B:104:0x01b4, code skipped:
            r0 = th;
     */
    /* JADX WARNING: Missing block: B:105:0x01b5, code skipped:
            r9 = r2;
            r2 = r1;
            r1 = r9;
     */
    /* renamed from: a */
    public void mo10278a(java.lang.String r11, java.util.Map<java.lang.String, java.lang.String> r12, java.util.Map<java.lang.String, java.lang.String> r13, com.amap.api.mapcore2d.C1022ec.C0973a r14) {
        /*
        r10 = this;
        r1 = 0;
        r0 = 1;
        r8 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r3 = 0;
        r2 = 0;
        r4 = 0;
        if (r14 != 0) goto L_0x0038;
    L_0x0009:
        if (r1 == 0) goto L_0x000e;
    L_0x000b:
        r2.close();	 Catch:{ IOException -> 0x0014, Throwable -> 0x0020 }
    L_0x000e:
        if (r1 == 0) goto L_0x0013;
    L_0x0010:
        r4.disconnect();	 Catch:{ Throwable -> 0x002c }
    L_0x0013:
        return;
    L_0x0014:
        r0 = move-exception;
        r0.printStackTrace();
        r2 = "HttpUrlUtil";
        r3 = "makeDownloadGetRequest";
        com.amap.api.mapcore2d.C0982da.m4076a(r0, r2, r3);
        goto L_0x000e;
    L_0x0020:
        r0 = move-exception;
        r0.printStackTrace();
        r2 = "HttpUrlUtil";
        r3 = "makeDownloadGetRequest";
        com.amap.api.mapcore2d.C0982da.m4076a(r0, r2, r3);
        goto L_0x000e;
    L_0x002c:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "HttpUrlUtil";
        r2 = "makeDownloadGetRequest";
        com.amap.api.mapcore2d.C0982da.m4076a(r0, r1, r2);
        goto L_0x0013;
    L_0x0038:
        r2 = com.amap.api.mapcore2d.C1024ed.m4267a(r13);	 Catch:{ Throwable -> 0x01b0, all -> 0x01a6 }
        r4 = new java.lang.StringBuffer;	 Catch:{ Throwable -> 0x01b0, all -> 0x01a6 }
        r4.<init>();	 Catch:{ Throwable -> 0x01b0, all -> 0x01a6 }
        r4.append(r11);	 Catch:{ Throwable -> 0x01b0, all -> 0x01a6 }
        if (r2 == 0) goto L_0x004f;
    L_0x0046:
        r5 = "?";
        r5 = r4.append(r5);	 Catch:{ Throwable -> 0x01b0, all -> 0x01a6 }
        r5.append(r2);	 Catch:{ Throwable -> 0x01b0, all -> 0x01a6 }
    L_0x004f:
        r2 = r4.toString();	 Catch:{ Throwable -> 0x01b0, all -> 0x01a6 }
        r4 = 0;
        r2 = r10.mo10276a(r2, r12, r4);	 Catch:{ Throwable -> 0x01b0, all -> 0x01a6 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x01b4, all -> 0x0121 }
        r4.<init>();	 Catch:{ Throwable -> 0x01b4, all -> 0x0121 }
        r5 = "bytes=";
        r4 = r4.append(r5);	 Catch:{ Throwable -> 0x01b4, all -> 0x0121 }
        r6 = r10.f2907i;	 Catch:{ Throwable -> 0x01b4, all -> 0x0121 }
        r4 = r4.append(r6);	 Catch:{ Throwable -> 0x01b4, all -> 0x0121 }
        r5 = "-";
        r4 = r4.append(r5);	 Catch:{ Throwable -> 0x01b4, all -> 0x0121 }
        r4 = r4.toString();	 Catch:{ Throwable -> 0x01b4, all -> 0x0121 }
        r5 = "RANGE";
        r2.setRequestProperty(r5, r4);	 Catch:{ Throwable -> 0x01b4, all -> 0x0121 }
        r2.connect();	 Catch:{ Throwable -> 0x01b4, all -> 0x0121 }
        r5 = r2.getResponseCode();	 Catch:{ Throwable -> 0x01b4, all -> 0x0121 }
        r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r5 == r4) goto L_0x010e;
    L_0x0083:
        r4 = r0;
    L_0x0084:
        r6 = 206; // 0xce float:2.89E-43 double:1.02E-321;
        if (r5 == r6) goto L_0x0111;
    L_0x0088:
        r0 = r0 & r4;
        if (r0 == 0) goto L_0x00b5;
    L_0x008b:
        r0 = new com.amap.api.mapcore2d.cl;	 Catch:{ Throwable -> 0x01b4, all -> 0x0121 }
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x01b4, all -> 0x0121 }
        r3.<init>();	 Catch:{ Throwable -> 0x01b4, all -> 0x0121 }
        r4 = "网络异常原因：";
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x01b4, all -> 0x0121 }
        r4 = r2.getResponseMessage();	 Catch:{ Throwable -> 0x01b4, all -> 0x0121 }
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x01b4, all -> 0x0121 }
        r4 = " 网络异常状态码：";
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x01b4, all -> 0x0121 }
        r3 = r3.append(r5);	 Catch:{ Throwable -> 0x01b4, all -> 0x0121 }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x01b4, all -> 0x0121 }
        r0.<init>(r3);	 Catch:{ Throwable -> 0x01b4, all -> 0x0121 }
        r14.mo10164a(r0);	 Catch:{ Throwable -> 0x01b4, all -> 0x0121 }
    L_0x00b5:
        r1 = r2.getInputStream();	 Catch:{ Throwable -> 0x01b4, all -> 0x0121 }
        r0 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r0 = new byte[r0];	 Catch:{ Throwable -> 0x00ee, all -> 0x0121 }
    L_0x00bd:
        r3 = java.lang.Thread.interrupted();	 Catch:{ Throwable -> 0x00ee, all -> 0x0121 }
        if (r3 != 0) goto L_0x012d;
    L_0x00c3:
        r3 = r10.f2905g;	 Catch:{ Throwable -> 0x00ee, all -> 0x0121 }
        if (r3 != 0) goto L_0x012d;
    L_0x00c7:
        r3 = 0;
        r4 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r3 = r1.read(r0, r3, r4);	 Catch:{ Throwable -> 0x00ee, all -> 0x0121 }
        if (r3 <= 0) goto L_0x012d;
    L_0x00d0:
        r4 = r10.f2906h;	 Catch:{ Throwable -> 0x00ee, all -> 0x0121 }
        r6 = -1;
        r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r4 == 0) goto L_0x00e0;
    L_0x00d8:
        r4 = r10.f2907i;	 Catch:{ Throwable -> 0x00ee, all -> 0x0121 }
        r6 = r10.f2906h;	 Catch:{ Throwable -> 0x00ee, all -> 0x0121 }
        r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r4 >= 0) goto L_0x012d;
    L_0x00e0:
        if (r3 != r8) goto L_0x0114;
    L_0x00e2:
        r4 = r10.f2907i;	 Catch:{ Throwable -> 0x00ee, all -> 0x0121 }
        r14.mo10165a(r0, r4);	 Catch:{ Throwable -> 0x00ee, all -> 0x0121 }
    L_0x00e7:
        r4 = r10.f2907i;	 Catch:{ Throwable -> 0x00ee, all -> 0x0121 }
        r6 = (long) r3;	 Catch:{ Throwable -> 0x00ee, all -> 0x0121 }
        r4 = r4 + r6;
        r10.f2907i = r4;	 Catch:{ Throwable -> 0x00ee, all -> 0x0121 }
        goto L_0x00bd;
    L_0x00ee:
        r0 = move-exception;
        r9 = r2;
        r2 = r1;
        r1 = r9;
    L_0x00f2:
        r14.mo10164a(r0);	 Catch:{ all -> 0x01aa }
        if (r2 == 0) goto L_0x00fa;
    L_0x00f7:
        r2.close();	 Catch:{ IOException -> 0x0169, Throwable -> 0x0175 }
    L_0x00fa:
        if (r1 == 0) goto L_0x0013;
    L_0x00fc:
        r1.disconnect();	 Catch:{ Throwable -> 0x0101 }
        goto L_0x0013;
    L_0x0101:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "HttpUrlUtil";
        r2 = "makeDownloadGetRequest";
        com.amap.api.mapcore2d.C0982da.m4076a(r0, r1, r2);
        goto L_0x0013;
    L_0x010e:
        r4 = r3;
        goto L_0x0084;
    L_0x0111:
        r0 = r3;
        goto L_0x0088;
    L_0x0114:
        r4 = new byte[r3];	 Catch:{ Throwable -> 0x00ee, all -> 0x0121 }
        r5 = 0;
        r6 = 0;
        java.lang.System.arraycopy(r0, r5, r4, r6, r3);	 Catch:{ Throwable -> 0x00ee, all -> 0x0121 }
        r6 = r10.f2907i;	 Catch:{ Throwable -> 0x00ee, all -> 0x0121 }
        r14.mo10165a(r4, r6);	 Catch:{ Throwable -> 0x00ee, all -> 0x0121 }
        goto L_0x00e7;
    L_0x0121:
        r0 = move-exception;
    L_0x0122:
        if (r1 == 0) goto L_0x0127;
    L_0x0124:
        r1.close();	 Catch:{ IOException -> 0x0182, Throwable -> 0x018e }
    L_0x0127:
        if (r2 == 0) goto L_0x012c;
    L_0x0129:
        r2.disconnect();	 Catch:{ Throwable -> 0x019a }
    L_0x012c:
        throw r0;
    L_0x012d:
        r0 = r10.f2905g;	 Catch:{ Throwable -> 0x00ee, all -> 0x0121 }
        if (r0 == 0) goto L_0x014d;
    L_0x0131:
        r14.mo10166b();	 Catch:{ Throwable -> 0x00ee, all -> 0x0121 }
    L_0x0134:
        if (r1 == 0) goto L_0x0139;
    L_0x0136:
        r1.close();	 Catch:{ IOException -> 0x0151, Throwable -> 0x015d }
    L_0x0139:
        if (r2 == 0) goto L_0x0013;
    L_0x013b:
        r2.disconnect();	 Catch:{ Throwable -> 0x0140 }
        goto L_0x0013;
    L_0x0140:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "HttpUrlUtil";
        r2 = "makeDownloadGetRequest";
        com.amap.api.mapcore2d.C0982da.m4076a(r0, r1, r2);
        goto L_0x0013;
    L_0x014d:
        r14.mo10167c();	 Catch:{ Throwable -> 0x00ee, all -> 0x0121 }
        goto L_0x0134;
    L_0x0151:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "HttpUrlUtil";
        r3 = "makeDownloadGetRequest";
        com.amap.api.mapcore2d.C0982da.m4076a(r0, r1, r3);
        goto L_0x0139;
    L_0x015d:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "HttpUrlUtil";
        r3 = "makeDownloadGetRequest";
        com.amap.api.mapcore2d.C0982da.m4076a(r0, r1, r3);
        goto L_0x0139;
    L_0x0169:
        r0 = move-exception;
        r0.printStackTrace();
        r2 = "HttpUrlUtil";
        r3 = "makeDownloadGetRequest";
        com.amap.api.mapcore2d.C0982da.m4076a(r0, r2, r3);
        goto L_0x00fa;
    L_0x0175:
        r0 = move-exception;
        r0.printStackTrace();
        r2 = "HttpUrlUtil";
        r3 = "makeDownloadGetRequest";
        com.amap.api.mapcore2d.C0982da.m4076a(r0, r2, r3);
        goto L_0x00fa;
    L_0x0182:
        r1 = move-exception;
        r1.printStackTrace();
        r3 = "HttpUrlUtil";
        r4 = "makeDownloadGetRequest";
        com.amap.api.mapcore2d.C0982da.m4076a(r1, r3, r4);
        goto L_0x0127;
    L_0x018e:
        r1 = move-exception;
        r1.printStackTrace();
        r3 = "HttpUrlUtil";
        r4 = "makeDownloadGetRequest";
        com.amap.api.mapcore2d.C0982da.m4076a(r1, r3, r4);
        goto L_0x0127;
    L_0x019a:
        r1 = move-exception;
        r1.printStackTrace();
        r2 = "HttpUrlUtil";
        r3 = "makeDownloadGetRequest";
        com.amap.api.mapcore2d.C0982da.m4076a(r1, r2, r3);
        goto L_0x012c;
    L_0x01a6:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0122;
    L_0x01aa:
        r0 = move-exception;
        r9 = r1;
        r1 = r2;
        r2 = r9;
        goto L_0x0122;
    L_0x01b0:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00f2;
    L_0x01b4:
        r0 = move-exception;
        r9 = r2;
        r2 = r1;
        r1 = r9;
        goto L_0x00f2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore2d.C1024ed.mo10278a(java.lang.String, java.util.Map, java.util.Map, com.amap.api.mapcore2d.ec$a):void");
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public C1031ei mo10274a(String str, Map<String, String> map, Map<String, String> map2) throws C0956cl {
        try {
            String a = C1024ed.m4267a((Map) map2);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str);
            if (a != null) {
                stringBuffer.append("?").append(a);
            }
            HttpURLConnection a2 = mo10276a(stringBuffer.toString(), (Map) map, false);
            a2.connect();
            return m4266a(a2);
        } catch (ConnectException e) {
            throw new C0956cl("http连接失败 - ConnectionException");
        } catch (MalformedURLException e2) {
            throw new C0956cl("url异常 - MalformedURLException");
        } catch (UnknownHostException e3) {
            throw new C0956cl("未知主机 - UnKnowHostException");
        } catch (SocketException e4) {
            throw new C0956cl("socket 连接异常 - SocketException");
        } catch (SocketTimeoutException e5) {
            throw new C0956cl("socket 连接超时 - SocketTimeoutException");
        } catch (IOException e6) {
            throw new C0956cl("IO 操作异常 - IOException");
        } catch (Throwable th) {
            th.printStackTrace();
            C0956cl c0956cl = new C0956cl("未知的错误");
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public C1031ei mo10275a(String str, Map<String, String> map, byte[] bArr) throws C0956cl {
        try {
            HttpURLConnection a = mo10276a(str, (Map) map, true);
            if (bArr != null && bArr.length > 0) {
                DataOutputStream dataOutputStream = new DataOutputStream(a.getOutputStream());
                dataOutputStream.write(bArr);
                dataOutputStream.close();
            }
            a.connect();
            return m4266a(a);
        } catch (ConnectException e) {
            e.printStackTrace();
            throw new C0956cl("http连接失败 - ConnectionException");
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
            throw new C0956cl("url异常 - MalformedURLException");
        } catch (UnknownHostException e3) {
            e3.printStackTrace();
            throw new C0956cl("未知主机 - UnKnowHostException");
        } catch (SocketException e4) {
            e4.printStackTrace();
            throw new C0956cl("socket 连接异常 - SocketException");
        } catch (SocketTimeoutException e5) {
            e5.printStackTrace();
            throw new C0956cl("socket 连接超时 - SocketTimeoutException");
        } catch (IOException e6) {
            e6.printStackTrace();
            throw new C0956cl("IO 操作异常 - IOException");
        } catch (Throwable th) {
            C0982da.m4076a(th, "HttpUrlUtil", "makePostReqeust");
            C0956cl c0956cl = new C0956cl("未知的错误");
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public HttpURLConnection mo10276a(String str, Map<String, String> map, boolean z) throws IOException {
        URLConnection openConnectionWithProxy;
        HttpURLConnection openConnectionWithProxy2;
        C0968cq.m3955a();
        URL url = new URL(str);
        if (this.f2904f != null) {
            openConnectionWithProxy2 = HttpInstrumentation.openConnectionWithProxy(url.openConnection(this.f2904f));
        } else {
            openConnectionWithProxy2 = (HttpURLConnection) HttpInstrumentation.openConnection(url.openConnection());
        }
        if (this.f2902d) {
            openConnectionWithProxy2 = (HttpsURLConnection) openConnectionWithProxy2;
            ((HttpsURLConnection) openConnectionWithProxy2).setSSLSocketFactory(this.f2903e.getSocketFactory());
            ((HttpsURLConnection) openConnectionWithProxy2).setHostnameVerifier(this.f2908j);
        } else {
            openConnectionWithProxy2 = (HttpURLConnection) openConnectionWithProxy2;
        }
        if (VERSION.SDK != null && VERSION.SDK_INT > 13) {
            openConnectionWithProxy2.setRequestProperty("Connection", "close");
        }
        m4269a(map, openConnectionWithProxy2);
        if (z) {
            openConnectionWithProxy2.setRequestMethod("POST");
            openConnectionWithProxy2.setUseCaches(false);
            openConnectionWithProxy2.setDoInput(true);
            openConnectionWithProxy2.setDoOutput(true);
        } else {
            openConnectionWithProxy2.setRequestMethod("GET");
            openConnectionWithProxy2.setDoInput(true);
        }
        return openConnectionWithProxy2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003d A:{SYNTHETIC, Splitter:B:12:0x003d} */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0042 A:{SYNTHETIC, Splitter:B:15:0x0042} */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0047 A:{SYNTHETIC, Splitter:B:18:0x0047} */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x004c A:{SYNTHETIC, Splitter:B:21:0x004c} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0051 A:{SYNTHETIC, Splitter:B:24:0x0051} */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003d A:{SYNTHETIC, Splitter:B:12:0x003d} */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0042 A:{SYNTHETIC, Splitter:B:15:0x0042} */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0047 A:{SYNTHETIC, Splitter:B:18:0x0047} */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x004c A:{SYNTHETIC, Splitter:B:21:0x004c} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0051 A:{SYNTHETIC, Splitter:B:24:0x0051} */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003d A:{SYNTHETIC, Splitter:B:12:0x003d} */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0042 A:{SYNTHETIC, Splitter:B:15:0x0042} */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0047 A:{SYNTHETIC, Splitter:B:18:0x0047} */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x004c A:{SYNTHETIC, Splitter:B:21:0x004c} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0051 A:{SYNTHETIC, Splitter:B:24:0x0051} */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003d A:{SYNTHETIC, Splitter:B:12:0x003d} */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0042 A:{SYNTHETIC, Splitter:B:15:0x0042} */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0047 A:{SYNTHETIC, Splitter:B:18:0x0047} */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x004c A:{SYNTHETIC, Splitter:B:21:0x004c} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0051 A:{SYNTHETIC, Splitter:B:24:0x0051} */
    /* renamed from: a */
    private com.amap.api.mapcore2d.C1031ei m4266a(java.net.HttpURLConnection r10) throws com.amap.api.mapcore2d.C0956cl, java.io.IOException {
        /*
        r9 = this;
        r1 = 0;
        r5 = r10.getHeaderFields();	 Catch:{ IOException -> 0x0035, all -> 0x0127 }
        r0 = r10.getResponseCode();	 Catch:{ IOException -> 0x0035, all -> 0x0127 }
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r0 == r2) goto L_0x0055;
    L_0x000d:
        r2 = new com.amap.api.mapcore2d.cl;	 Catch:{ IOException -> 0x0035, all -> 0x0127 }
        r3 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0035, all -> 0x0127 }
        r3.<init>();	 Catch:{ IOException -> 0x0035, all -> 0x0127 }
        r4 = "网络异常原因：";
        r3 = r3.append(r4);	 Catch:{ IOException -> 0x0035, all -> 0x0127 }
        r4 = r10.getResponseMessage();	 Catch:{ IOException -> 0x0035, all -> 0x0127 }
        r3 = r3.append(r4);	 Catch:{ IOException -> 0x0035, all -> 0x0127 }
        r4 = " 网络异常状态码：";
        r3 = r3.append(r4);	 Catch:{ IOException -> 0x0035, all -> 0x0127 }
        r0 = r3.append(r0);	 Catch:{ IOException -> 0x0035, all -> 0x0127 }
        r0 = r0.toString();	 Catch:{ IOException -> 0x0035, all -> 0x0127 }
        r2.<init>(r0);	 Catch:{ IOException -> 0x0035, all -> 0x0127 }
        throw r2;	 Catch:{ IOException -> 0x0035, all -> 0x0127 }
    L_0x0035:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
        r4 = r1;
    L_0x0039:
        throw r0;	 Catch:{ all -> 0x003a }
    L_0x003a:
        r0 = move-exception;
    L_0x003b:
        if (r4 == 0) goto L_0x0040;
    L_0x003d:
        r4.close();	 Catch:{ Throwable -> 0x00c8 }
    L_0x0040:
        if (r3 == 0) goto L_0x0045;
    L_0x0042:
        r3.close();	 Catch:{ Throwable -> 0x00d2 }
    L_0x0045:
        if (r1 == 0) goto L_0x004a;
    L_0x0047:
        r1.close();	 Catch:{ Throwable -> 0x00dc }
    L_0x004a:
        if (r2 == 0) goto L_0x004f;
    L_0x004c:
        r2.close();	 Catch:{ Throwable -> 0x00e6 }
    L_0x004f:
        if (r10 == 0) goto L_0x0054;
    L_0x0051:
        r10.disconnect();	 Catch:{ Throwable -> 0x00f0 }
    L_0x0054:
        throw r0;
    L_0x0055:
        r4 = new java.io.ByteArrayOutputStream;	 Catch:{ IOException -> 0x0035, all -> 0x0127 }
        r4.<init>();	 Catch:{ IOException -> 0x0035, all -> 0x0127 }
        r3 = r10.getInputStream();	 Catch:{ IOException -> 0x0142, all -> 0x012d }
        r2 = new java.io.PushbackInputStream;	 Catch:{ IOException -> 0x0147, all -> 0x0132 }
        r0 = 2;
        r2.<init>(r3, r0);	 Catch:{ IOException -> 0x0147, all -> 0x0132 }
        r0 = 2;
        r0 = new byte[r0];	 Catch:{ IOException -> 0x014b, all -> 0x0136 }
        r2.read(r0);	 Catch:{ IOException -> 0x014b, all -> 0x0136 }
        r2.unread(r0);	 Catch:{ IOException -> 0x014b, all -> 0x0136 }
        r6 = 0;
        r6 = r0[r6];	 Catch:{ IOException -> 0x014b, all -> 0x0136 }
        r7 = 31;
        if (r6 != r7) goto L_0x0096;
    L_0x0074:
        r6 = 1;
        r0 = r0[r6];	 Catch:{ IOException -> 0x014b, all -> 0x0136 }
        r6 = -117; // 0xffffffffffffff8b float:NaN double:NaN;
        if (r0 != r6) goto L_0x0096;
    L_0x007b:
        r0 = new java.util.zip.GZIPInputStream;	 Catch:{ IOException -> 0x014b, all -> 0x0136 }
        r0.<init>(r2);	 Catch:{ IOException -> 0x014b, all -> 0x0136 }
        r1 = r0;
    L_0x0081:
        r0 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r0 = new byte[r0];	 Catch:{ IOException -> 0x0091, all -> 0x013c }
    L_0x0085:
        r6 = r1.read(r0);	 Catch:{ IOException -> 0x0091, all -> 0x013c }
        r7 = -1;
        if (r6 == r7) goto L_0x0098;
    L_0x008c:
        r7 = 0;
        r4.write(r0, r7, r6);	 Catch:{ IOException -> 0x0091, all -> 0x013c }
        goto L_0x0085;
    L_0x0091:
        r0 = move-exception;
        r8 = r2;
        r2 = r1;
        r1 = r8;
        goto L_0x0039;
    L_0x0096:
        r1 = r2;
        goto L_0x0081;
    L_0x0098:
        r0 = f2899a;	 Catch:{ IOException -> 0x0091, all -> 0x013c }
        if (r0 == 0) goto L_0x00a1;
    L_0x009c:
        r0 = f2899a;	 Catch:{ IOException -> 0x0091, all -> 0x013c }
        r0.mo10186a();	 Catch:{ IOException -> 0x0091, all -> 0x013c }
    L_0x00a1:
        r0 = new com.amap.api.mapcore2d.ei;	 Catch:{ IOException -> 0x0091, all -> 0x013c }
        r0.<init>();	 Catch:{ IOException -> 0x0091, all -> 0x013c }
        r6 = r4.toByteArray();	 Catch:{ IOException -> 0x0091, all -> 0x013c }
        r0.f2916a = r6;	 Catch:{ IOException -> 0x0091, all -> 0x013c }
        r0.f2917b = r5;	 Catch:{ IOException -> 0x0091, all -> 0x013c }
        if (r4 == 0) goto L_0x00b3;
    L_0x00b0:
        r4.close();	 Catch:{ Throwable -> 0x00fa }
    L_0x00b3:
        if (r3 == 0) goto L_0x00b8;
    L_0x00b5:
        r3.close();	 Catch:{ Throwable -> 0x0103 }
    L_0x00b8:
        if (r2 == 0) goto L_0x00bd;
    L_0x00ba:
        r2.close();	 Catch:{ Throwable -> 0x010c }
    L_0x00bd:
        if (r1 == 0) goto L_0x00c2;
    L_0x00bf:
        r1.close();	 Catch:{ Throwable -> 0x0115 }
    L_0x00c2:
        if (r10 == 0) goto L_0x00c7;
    L_0x00c4:
        r10.disconnect();	 Catch:{ Throwable -> 0x011e }
    L_0x00c7:
        return r0;
    L_0x00c8:
        r4 = move-exception;
        r5 = "HttpUrlUtil";
        r6 = "parseResult";
        com.amap.api.mapcore2d.C0982da.m4076a(r4, r5, r6);
        goto L_0x0040;
    L_0x00d2:
        r3 = move-exception;
        r4 = "HttpUrlUtil";
        r5 = "parseResult";
        com.amap.api.mapcore2d.C0982da.m4076a(r3, r4, r5);
        goto L_0x0045;
    L_0x00dc:
        r1 = move-exception;
        r3 = "HttpUrlUtil";
        r4 = "parseResult";
        com.amap.api.mapcore2d.C0982da.m4076a(r1, r3, r4);
        goto L_0x004a;
    L_0x00e6:
        r1 = move-exception;
        r2 = "HttpUrlUtil";
        r3 = "parseResult";
        com.amap.api.mapcore2d.C0982da.m4076a(r1, r2, r3);
        goto L_0x004f;
    L_0x00f0:
        r1 = move-exception;
        r2 = "HttpUrlUtil";
        r3 = "parseResult";
        com.amap.api.mapcore2d.C0982da.m4076a(r1, r2, r3);
        goto L_0x0054;
    L_0x00fa:
        r4 = move-exception;
        r5 = "HttpUrlUtil";
        r6 = "parseResult";
        com.amap.api.mapcore2d.C0982da.m4076a(r4, r5, r6);
        goto L_0x00b3;
    L_0x0103:
        r3 = move-exception;
        r4 = "HttpUrlUtil";
        r5 = "parseResult";
        com.amap.api.mapcore2d.C0982da.m4076a(r3, r4, r5);
        goto L_0x00b8;
    L_0x010c:
        r2 = move-exception;
        r3 = "HttpUrlUtil";
        r4 = "parseResult";
        com.amap.api.mapcore2d.C0982da.m4076a(r2, r3, r4);
        goto L_0x00bd;
    L_0x0115:
        r1 = move-exception;
        r2 = "HttpUrlUtil";
        r3 = "parseResult";
        com.amap.api.mapcore2d.C0982da.m4076a(r1, r2, r3);
        goto L_0x00c2;
    L_0x011e:
        r1 = move-exception;
        r2 = "HttpUrlUtil";
        r3 = "parseResult";
        com.amap.api.mapcore2d.C0982da.m4076a(r1, r2, r3);
        goto L_0x00c7;
    L_0x0127:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
        r4 = r1;
        goto L_0x003b;
    L_0x012d:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
        goto L_0x003b;
    L_0x0132:
        r0 = move-exception;
        r2 = r1;
        goto L_0x003b;
    L_0x0136:
        r0 = move-exception;
        r8 = r2;
        r2 = r1;
        r1 = r8;
        goto L_0x003b;
    L_0x013c:
        r0 = move-exception;
        r8 = r2;
        r2 = r1;
        r1 = r8;
        goto L_0x003b;
    L_0x0142:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
        goto L_0x0039;
    L_0x0147:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0039;
    L_0x014b:
        r0 = move-exception;
        r8 = r2;
        r2 = r1;
        r1 = r8;
        goto L_0x0039;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore2d.C1024ed.m4266a(java.net.HttpURLConnection):com.amap.api.mapcore2d.ei");
    }

    /* renamed from: a */
    private void m4269a(Map<String, String> map, HttpURLConnection httpURLConnection) {
        if (map != null) {
            for (String str : map.keySet()) {
                httpURLConnection.addRequestProperty(str, (String) map.get(str));
            }
        }
        try {
            httpURLConnection.addRequestProperty("csid", UUID.randomUUID().toString().replaceAll("-", "").toLowerCase());
        } catch (Throwable th) {
            C0982da.m4076a(th, "HttpUrlUtil", "addHeaders");
        }
        httpURLConnection.setConnectTimeout(this.f2900b);
        httpURLConnection.setReadTimeout(this.f2901c);
    }

    /* renamed from: a */
    static String m4267a(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            if (str2 == null) {
                str2 = "";
            }
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append(URLEncoder.encode(str));
            stringBuilder.append("=");
            stringBuilder.append(URLEncoder.encode(str2));
        }
        return stringBuilder.toString();
    }
}
