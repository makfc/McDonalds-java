package com.aps;

import android.content.Context;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.amap.api.location.core.ClientInfoUtil;
import com.facebook.internal.ServerProtocol;
import com.mcdonalds.sdk.connectors.autonavi.AutoNavi.Parameters;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.ProxySelector;
import java.net.URI;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.aps.n */
public class NetManager {
    /* renamed from: a */
    private static NetManager f4491a = null;

    private NetManager() {
    }

    /* renamed from: a */
    public static NetManager m5684a() {
        if (f4491a == null) {
            f4491a = new NetManager();
        }
        return f4491a;
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x0116  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x011b  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0124 A:{SYNTHETIC, Splitter:B:54:0x0124} */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0129 A:{SYNTHETIC, Splitter:B:57:0x0129} */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x012e A:{SYNTHETIC, Splitter:B:60:0x012e} */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0133 A:{SYNTHETIC, Splitter:B:63:0x0133} */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0116  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x011b  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0124 A:{SYNTHETIC, Splitter:B:54:0x0124} */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0129 A:{SYNTHETIC, Splitter:B:57:0x0129} */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x012e A:{SYNTHETIC, Splitter:B:60:0x012e} */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0133 A:{SYNTHETIC, Splitter:B:63:0x0133} */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x020e A:{ExcHandler: SocketException (e java.net.SocketException), Splitter:B:12:0x0031, PHI: r4 r5 } */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x0209 A:{ExcHandler: SocketTimeoutException (e java.net.SocketTimeoutException), Splitter:B:12:0x0031} */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x0204 A:{ExcHandler: ConnectTimeoutException (e org.apache.http.conn.ConnectTimeoutException), Splitter:B:12:0x0031} */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x01fd A:{ExcHandler: Throwable (th java.lang.Throwable), Splitter:B:12:0x0031} */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x020e A:{ExcHandler: SocketException (e java.net.SocketException), Splitter:B:12:0x0031, PHI: r4 r5 } */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x0209 A:{ExcHandler: SocketTimeoutException (e java.net.SocketTimeoutException), Splitter:B:12:0x0031} */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x0204 A:{ExcHandler: ConnectTimeoutException (e org.apache.http.conn.ConnectTimeoutException), Splitter:B:12:0x0031} */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x01fd A:{ExcHandler: Throwable (th java.lang.Throwable), Splitter:B:12:0x0031} */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x020e A:{ExcHandler: SocketException (e java.net.SocketException), Splitter:B:12:0x0031, PHI: r4 r5 } */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x0209 A:{ExcHandler: SocketTimeoutException (e java.net.SocketTimeoutException), Splitter:B:12:0x0031} */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x0204 A:{ExcHandler: ConnectTimeoutException (e org.apache.http.conn.ConnectTimeoutException), Splitter:B:12:0x0031} */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x01fd A:{ExcHandler: Throwable (th java.lang.Throwable), Splitter:B:12:0x0031} */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x019a A:{ExcHandler: SocketException (e java.net.SocketException), Splitter:B:8:0x0024} */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x01a7 A:{ExcHandler: SocketTimeoutException (e java.net.SocketTimeoutException), Splitter:B:8:0x0024, Catch:{ UnknownHostException -> 0x0220, SocketException -> 0x019a, SocketTimeoutException -> 0x01a7, ConnectTimeoutException -> 0x01b1, Throwable -> 0x01bb, all -> 0x01f1, all -> 0x01a4 }} */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x01b1 A:{ExcHandler: ConnectTimeoutException (e org.apache.http.conn.ConnectTimeoutException), Splitter:B:8:0x0024, Catch:{ UnknownHostException -> 0x0220, SocketException -> 0x019a, SocketTimeoutException -> 0x01a7, ConnectTimeoutException -> 0x01b1, Throwable -> 0x01bb, all -> 0x01f1, all -> 0x01a4 }} */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x01bb A:{ExcHandler: Throwable (r7_15 'th' java.lang.Throwable A:{Catch:{  }}), Splitter:B:8:0x0024, Catch:{ UnknownHostException -> 0x0220, SocketException -> 0x019a, SocketTimeoutException -> 0x01a7, ConnectTimeoutException -> 0x01b1, Throwable -> 0x01bb, all -> 0x01f1, all -> 0x01a4 }} */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x01f1 A:{ExcHandler: all (r7_17 'th' java.lang.Throwable), Splitter:B:8:0x0024} */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0116  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x011b  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0124 A:{SYNTHETIC, Splitter:B:54:0x0124} */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0129 A:{SYNTHETIC, Splitter:B:57:0x0129} */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x012e A:{SYNTHETIC, Splitter:B:60:0x012e} */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0133 A:{SYNTHETIC, Splitter:B:63:0x0133} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:50:0x0116, code skipped:
            r7.abort();
     */
    /* JADX WARNING: Missing block: B:52:0x011b, code skipped:
            r2.getConnectionManager().shutdown();
     */
    /* JADX WARNING: Missing block: B:55:?, code skipped:
            r5.close();
     */
    /* JADX WARNING: Missing block: B:58:?, code skipped:
            r6.close();
     */
    /* JADX WARNING: Missing block: B:61:?, code skipped:
            r4.close();
     */
    /* JADX WARNING: Missing block: B:64:?, code skipped:
            r3.close();
     */
    /* JADX WARNING: Missing block: B:100:0x0191, code skipped:
            r1 = null;
            r3 = null;
            r5 = r7;
            r15 = r6;
            r6 = r2;
            r2 = null;
            r4 = r15;
     */
    /* JADX WARNING: Missing block: B:102:0x019b, code skipped:
            r7 = null;
     */
    /* JADX WARNING: Missing block: B:109:0x01a8, code skipped:
            r7 = null;
     */
    /* JADX WARNING: Missing block: B:113:0x01b2, code skipped:
            r7 = null;
     */
    /* JADX WARNING: Missing block: B:116:0x01bb, code skipped:
            r7 = move-exception;
     */
    /* JADX WARNING: Missing block: B:117:0x01bc, code skipped:
            r15 = r7;
            r7 = null;
            r1 = r15;
     */
    /* JADX WARNING: Missing block: B:121:0x01ce, code skipped:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:122:0x01cf, code skipped:
            r2.printStackTrace();
     */
    /* JADX WARNING: Missing block: B:123:0x01d4, code skipped:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:124:0x01d5, code skipped:
            r2.printStackTrace();
     */
    /* JADX WARNING: Missing block: B:125:0x01da, code skipped:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:126:0x01db, code skipped:
            r2.printStackTrace();
     */
    /* JADX WARNING: Missing block: B:134:0x01f1, code skipped:
            r7 = move-exception;
     */
    /* JADX WARNING: Missing block: B:135:0x01f2, code skipped:
            r15 = r7;
            r7 = null;
            r1 = r15;
     */
    /* JADX WARNING: Missing block: B:138:0x01fd, code skipped:
            r1 = th;
     */
    /* JADX WARNING: Missing block: B:151:0x0214, code skipped:
            r3 = null;
            r5 = null;
            r1 = null;
            r16 = r6;
            r6 = r2;
            r2 = null;
            r4 = r16;
     */
    /* JADX WARNING: Missing block: B:155:0x022e, code skipped:
            r1 = null;
            r3 = null;
            r5 = r7;
            r15 = r6;
            r6 = r2;
            r2 = null;
            r4 = r15;
     */
    /* JADX WARNING: Missing block: B:157:0x0238, code skipped:
            r1 = null;
            r3 = r5;
            r5 = r7;
            r15 = r6;
            r6 = r2;
            r2 = null;
            r4 = r15;
     */
    /* renamed from: a */
    public java.lang.String mo13279a(android.content.Context r18, java.lang.String r19, byte[] r20, java.lang.String r21) throws com.amap.api.location.core.AMapLocException {
        /*
        r17 = this;
        r1 = android.text.TextUtils.isEmpty(r19);
        if (r1 != 0) goto L_0x0008;
    L_0x0006:
        if (r20 != 0) goto L_0x000a;
    L_0x0008:
        r1 = 0;
    L_0x0009:
        return r1;
    L_0x000a:
        r7 = com.aps.C1269v.m5738b(r18);
        r1 = com.aps.NetManager.m5683a(r7);
        r2 = -1;
        if (r1 != r2) goto L_0x0017;
    L_0x0015:
        r1 = 0;
        goto L_0x0009;
    L_0x0017:
        r2 = 0;
        r1 = 0;
        r6 = 0;
        r5 = 0;
        r4 = 0;
        r3 = 0;
        r10 = new java.lang.StringBuffer;
        r10.<init>();
        r8 = "";
        r0 = r18;
        r2 = com.aps.NetManager.m5687a(r0, r7);	 Catch:{ UnknownHostException -> 0x0213, SocketException -> 0x019a, SocketTimeoutException -> 0x01a7, ConnectTimeoutException -> 0x01b1, Throwable -> 0x01bb, all -> 0x01f1 }
        r7 = new org.apache.http.client.methods.HttpPost;	 Catch:{ UnknownHostException -> 0x0220, SocketException -> 0x019a, SocketTimeoutException -> 0x01a7, ConnectTimeoutException -> 0x01b1, Throwable -> 0x01bb, all -> 0x01f1 }
        r0 = r19;
        r7.<init>(r0);	 Catch:{ UnknownHostException -> 0x0220, SocketException -> 0x019a, SocketTimeoutException -> 0x01a7, ConnectTimeoutException -> 0x01b1, Throwable -> 0x01bb, all -> 0x01f1 }
        r1 = new org.apache.http.entity.ByteArrayEntity;	 Catch:{ UnknownHostException -> 0x0190, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r0 = r20;
        r1.<init>(r0);	 Catch:{ UnknownHostException -> 0x0190, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r9 = "Content-Type";
        r11 = "application/x-www-form-urlencoded";
        r7.addHeader(r9, r11);	 Catch:{ UnknownHostException -> 0x0190, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r9 = "User-Agent";
        r11 = "AMAP_Location_SDK_Android 1.4.1";
        r7.addHeader(r9, r11);	 Catch:{ UnknownHostException -> 0x0190, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r9 = "Accept-Encoding";
        r11 = "gzip";
        r7.addHeader(r9, r11);	 Catch:{ UnknownHostException -> 0x0190, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r9 = "Connection";
        r11 = "Keep-Alive";
        r7.addHeader(r9, r11);	 Catch:{ UnknownHostException -> 0x0190, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r9 = "X-INFO";
        r11 = 0;
        r11 = com.amap.api.location.core.ClientInfoUtil.m1421a(r11);	 Catch:{ UnknownHostException -> 0x0190, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r0 = r21;
        r11 = r11.mo8404a(r0);	 Catch:{ UnknownHostException -> 0x0190, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r7.addHeader(r9, r11);	 Catch:{ UnknownHostException -> 0x0190, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r9 = "platinfo";
        r11 = "platform=Android&sdkversion=%s&product=%s";
        r12 = 2;
        r12 = new java.lang.Object[r12];	 Catch:{ UnknownHostException -> 0x0190, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r13 = 0;
        r14 = "1.4.1";
        r12[r13] = r14;	 Catch:{ UnknownHostException -> 0x0190, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r13 = 1;
        r14 = "loc";
        r12[r13] = r14;	 Catch:{ UnknownHostException -> 0x0190, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r11 = java.lang.String.format(r11, r12);	 Catch:{ UnknownHostException -> 0x0190, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r7.addHeader(r9, r11);	 Catch:{ UnknownHostException -> 0x0190, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r9 = "logversion";
        r11 = "2.0";
        r7.addHeader(r9, r11);	 Catch:{ UnknownHostException -> 0x0190, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r9 = 0;
        r11 = r10.length();	 Catch:{ UnknownHostException -> 0x0190, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r10.delete(r9, r11);	 Catch:{ UnknownHostException -> 0x0190, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r7.setEntity(r1);	 Catch:{ UnknownHostException -> 0x0190, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r1 = r2 instanceof org.apache.http.client.HttpClient;	 Catch:{ UnknownHostException -> 0x0190, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        if (r1 != 0) goto L_0x0137;
    L_0x0092:
        r1 = r2.execute(r7);	 Catch:{ UnknownHostException -> 0x0190, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r9 = r1;
    L_0x0097:
        r1 = r9.getStatusLine();	 Catch:{ UnknownHostException -> 0x0190, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r1 = r1.getStatusCode();	 Catch:{ UnknownHostException -> 0x0190, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r11 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r1 != r11) goto L_0x0183;
    L_0x00a3:
        r1 = r9.getEntity();	 Catch:{ UnknownHostException -> 0x0190, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r6 = r1.getContent();	 Catch:{ UnknownHostException -> 0x0190, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r1 = r9.getEntity();	 Catch:{ UnknownHostException -> 0x022d, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r1 = r1.getContentType();	 Catch:{ UnknownHostException -> 0x022d, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r8 = r1.getValue();	 Catch:{ UnknownHostException -> 0x022d, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r1 = "";
        r11 = "charset=";
        r11 = r8.indexOf(r11);	 Catch:{ UnknownHostException -> 0x022d, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r12 = -1;
        if (r11 == r12) goto L_0x00c8;
    L_0x00c2:
        r1 = r11 + 8;
        r1 = r8.substring(r1);	 Catch:{ UnknownHostException -> 0x022d, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
    L_0x00c8:
        r8 = android.text.TextUtils.isEmpty(r1);	 Catch:{ UnknownHostException -> 0x022d, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        if (r8 == 0) goto L_0x024e;
    L_0x00ce:
        r1 = "UTF-8";
        r8 = r1;
    L_0x00d1:
        r1 = com.aps.NetManager.m5689a(r9);	 Catch:{ UnknownHostException -> 0x022d, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        if (r1 == 0) goto L_0x00dd;
    L_0x00d7:
        r1 = new java.util.zip.GZIPInputStream;	 Catch:{ UnknownHostException -> 0x022d, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r1.<init>(r6);	 Catch:{ UnknownHostException -> 0x022d, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r5 = r1;
    L_0x00dd:
        if (r5 == 0) goto L_0x0142;
    L_0x00df:
        r1 = new java.io.InputStreamReader;	 Catch:{ UnknownHostException -> 0x0237, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r1.<init>(r5, r8);	 Catch:{ UnknownHostException -> 0x0237, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r4 = r1;
    L_0x00e5:
        r1 = new java.io.BufferedReader;	 Catch:{ UnknownHostException -> 0x0241, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r8 = 2048; // 0x800 float:2.87E-42 double:1.0118E-320;
        r1.<init>(r4, r8);	 Catch:{ UnknownHostException -> 0x0241, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r3 = "";
    L_0x00ee:
        r3 = r1.readLine();	 Catch:{ UnknownHostException -> 0x00f8, SocketException -> 0x0210, SocketTimeoutException -> 0x020b, ConnectTimeoutException -> 0x0206, Throwable -> 0x01ff, all -> 0x01f7 }
        if (r3 == 0) goto L_0x0149;
    L_0x00f4:
        r10.append(r3);	 Catch:{ UnknownHostException -> 0x00f8, SocketException -> 0x0210, SocketTimeoutException -> 0x020b, ConnectTimeoutException -> 0x0206, Throwable -> 0x01ff, all -> 0x01f7 }
        goto L_0x00ee;
    L_0x00f8:
        r3 = move-exception;
        r3 = r5;
        r5 = r7;
        r15 = r6;
        r6 = r2;
        r2 = r4;
        r4 = r15;
    L_0x00ff:
        r7 = new com.amap.api.location.core.AMapLocException;	 Catch:{ all -> 0x0108 }
        r8 = "未知主机 - UnKnowHostException";
        r7.<init>(r8);	 Catch:{ all -> 0x0108 }
        throw r7;	 Catch:{ all -> 0x0108 }
    L_0x0108:
        r7 = move-exception;
        r15 = r7;
        r7 = r5;
        r5 = r3;
        r3 = r1;
        r1 = r15;
        r16 = r4;
        r4 = r2;
        r2 = r6;
        r6 = r16;
    L_0x0114:
        if (r7 == 0) goto L_0x0119;
    L_0x0116:
        r7.abort();
    L_0x0119:
        if (r2 == 0) goto L_0x0122;
    L_0x011b:
        r2 = r2.getConnectionManager();
        r2.shutdown();
    L_0x0122:
        if (r5 == 0) goto L_0x0127;
    L_0x0124:
        r5.close();	 Catch:{ Throwable -> 0x01cb }
    L_0x0127:
        if (r6 == 0) goto L_0x012c;
    L_0x0129:
        r6.close();	 Catch:{ Throwable -> 0x01ce }
    L_0x012c:
        if (r4 == 0) goto L_0x0131;
    L_0x012e:
        r4.close();	 Catch:{ Throwable -> 0x01d4 }
    L_0x0131:
        if (r3 == 0) goto L_0x0136;
    L_0x0133:
        r3.close();	 Catch:{ Throwable -> 0x01da }
    L_0x0136:
        throw r1;
    L_0x0137:
        r0 = r2;
        r0 = (org.apache.http.client.HttpClient) r0;	 Catch:{ UnknownHostException -> 0x0190, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r1 = r0;
        r1 = com.newrelic.agent.android.instrumentation.HttpInstrumentation.execute(r1, r7);	 Catch:{ UnknownHostException -> 0x0190, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r9 = r1;
        goto L_0x0097;
    L_0x0142:
        r1 = new java.io.InputStreamReader;	 Catch:{ UnknownHostException -> 0x0237, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r1.<init>(r6, r8);	 Catch:{ UnknownHostException -> 0x0237, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r4 = r1;
        goto L_0x00e5;
    L_0x0149:
        r3 = r10.toString();	 Catch:{ UnknownHostException -> 0x00f8, SocketException -> 0x0210, SocketTimeoutException -> 0x020b, ConnectTimeoutException -> 0x0206, Throwable -> 0x01ff, all -> 0x01f7 }
        r8 = 0;
        r9 = r10.length();	 Catch:{ UnknownHostException -> 0x00f8, SocketException -> 0x0210, SocketTimeoutException -> 0x020b, ConnectTimeoutException -> 0x0206, Throwable -> 0x01ff, all -> 0x01f7 }
        r10.delete(r8, r9);	 Catch:{ UnknownHostException -> 0x00f8, SocketException -> 0x0210, SocketTimeoutException -> 0x020b, ConnectTimeoutException -> 0x0206, Throwable -> 0x01ff, all -> 0x01f7 }
        r15 = r3;
        r3 = r1;
        r1 = r15;
    L_0x0158:
        if (r7 == 0) goto L_0x015d;
    L_0x015a:
        r7.abort();
    L_0x015d:
        if (r2 == 0) goto L_0x0166;
    L_0x015f:
        r2 = r2.getConnectionManager();
        r2.shutdown();
    L_0x0166:
        if (r5 == 0) goto L_0x016b;
    L_0x0168:
        r5.close();	 Catch:{ Throwable -> 0x01e0 }
    L_0x016b:
        if (r6 == 0) goto L_0x0170;
    L_0x016d:
        r6.close();	 Catch:{ Throwable -> 0x01e2 }
    L_0x0170:
        if (r4 == 0) goto L_0x0175;
    L_0x0172:
        r4.close();	 Catch:{ Throwable -> 0x01e7 }
    L_0x0175:
        if (r3 == 0) goto L_0x017a;
    L_0x0177:
        r3.close();	 Catch:{ Throwable -> 0x01ec }
    L_0x017a:
        r2 = android.text.TextUtils.isEmpty(r1);
        if (r2 == 0) goto L_0x0009;
    L_0x0180:
        r1 = 0;
        goto L_0x0009;
    L_0x0183:
        r9 = 404; // 0x194 float:5.66E-43 double:1.996E-321;
        if (r1 != r9) goto L_0x024b;
    L_0x0187:
        r1 = new com.amap.api.location.core.AMapLocException;	 Catch:{ UnknownHostException -> 0x0190, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        r8 = "服务器连接失败 - UnknownServiceException";
        r1.<init>(r8);	 Catch:{ UnknownHostException -> 0x0190, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
        throw r1;	 Catch:{ UnknownHostException -> 0x0190, SocketException -> 0x020e, SocketTimeoutException -> 0x0209, ConnectTimeoutException -> 0x0204, Throwable -> 0x01fd }
    L_0x0190:
        r1 = move-exception;
        r1 = r3;
        r3 = r5;
        r5 = r7;
        r15 = r6;
        r6 = r2;
        r2 = r4;
        r4 = r15;
        goto L_0x00ff;
    L_0x019a:
        r7 = move-exception;
        r7 = r1;
    L_0x019c:
        r1 = new com.amap.api.location.core.AMapLocException;	 Catch:{ all -> 0x01a4 }
        r8 = "socket 连接异常 - SocketException";
        r1.<init>(r8);	 Catch:{ all -> 0x01a4 }
        throw r1;	 Catch:{ all -> 0x01a4 }
    L_0x01a4:
        r1 = move-exception;
        goto L_0x0114;
    L_0x01a7:
        r7 = move-exception;
        r7 = r1;
    L_0x01a9:
        r1 = new com.amap.api.location.core.AMapLocException;	 Catch:{ all -> 0x01a4 }
        r8 = "socket 连接超时 - SocketTimeoutException";
        r1.<init>(r8);	 Catch:{ all -> 0x01a4 }
        throw r1;	 Catch:{ all -> 0x01a4 }
    L_0x01b1:
        r7 = move-exception;
        r7 = r1;
    L_0x01b3:
        r1 = new com.amap.api.location.core.AMapLocException;	 Catch:{ all -> 0x01a4 }
        r8 = "http连接失败 - ConnectionException";
        r1.<init>(r8);	 Catch:{ all -> 0x01a4 }
        throw r1;	 Catch:{ all -> 0x01a4 }
    L_0x01bb:
        r7 = move-exception;
        r15 = r7;
        r7 = r1;
        r1 = r15;
    L_0x01bf:
        r1.printStackTrace();	 Catch:{ all -> 0x01a4 }
        r1 = new com.amap.api.location.core.AMapLocException;	 Catch:{ all -> 0x01a4 }
        r8 = "服务器异常";
        r1.<init>(r8);	 Catch:{ all -> 0x01a4 }
        throw r1;	 Catch:{ all -> 0x01a4 }
    L_0x01cb:
        r2 = move-exception;
        goto L_0x0127;
    L_0x01ce:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x012c;
    L_0x01d4:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x0131;
    L_0x01da:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x0136;
    L_0x01e0:
        r2 = move-exception;
        goto L_0x016b;
    L_0x01e2:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x0170;
    L_0x01e7:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x0175;
    L_0x01ec:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x017a;
    L_0x01f1:
        r7 = move-exception;
        r15 = r7;
        r7 = r1;
        r1 = r15;
        goto L_0x0114;
    L_0x01f7:
        r3 = move-exception;
        r15 = r3;
        r3 = r1;
        r1 = r15;
        goto L_0x0114;
    L_0x01fd:
        r1 = move-exception;
        goto L_0x01bf;
    L_0x01ff:
        r3 = move-exception;
        r15 = r3;
        r3 = r1;
        r1 = r15;
        goto L_0x01bf;
    L_0x0204:
        r1 = move-exception;
        goto L_0x01b3;
    L_0x0206:
        r3 = move-exception;
        r3 = r1;
        goto L_0x01b3;
    L_0x0209:
        r1 = move-exception;
        goto L_0x01a9;
    L_0x020b:
        r3 = move-exception;
        r3 = r1;
        goto L_0x01a9;
    L_0x020e:
        r1 = move-exception;
        goto L_0x019c;
    L_0x0210:
        r3 = move-exception;
        r3 = r1;
        goto L_0x019c;
    L_0x0213:
        r7 = move-exception;
        r15 = r3;
        r3 = r5;
        r5 = r1;
        r1 = r15;
        r16 = r6;
        r6 = r2;
        r2 = r4;
        r4 = r16;
        goto L_0x00ff;
    L_0x0220:
        r7 = move-exception;
        r15 = r3;
        r3 = r5;
        r5 = r1;
        r1 = r15;
        r16 = r6;
        r6 = r2;
        r2 = r4;
        r4 = r16;
        goto L_0x00ff;
    L_0x022d:
        r1 = move-exception;
        r1 = r3;
        r3 = r5;
        r5 = r7;
        r15 = r6;
        r6 = r2;
        r2 = r4;
        r4 = r15;
        goto L_0x00ff;
    L_0x0237:
        r1 = move-exception;
        r1 = r3;
        r3 = r5;
        r5 = r7;
        r15 = r6;
        r6 = r2;
        r2 = r4;
        r4 = r15;
        goto L_0x00ff;
    L_0x0241:
        r1 = move-exception;
        r1 = r3;
        r3 = r5;
        r5 = r7;
        r15 = r6;
        r6 = r2;
        r2 = r4;
        r4 = r15;
        goto L_0x00ff;
    L_0x024b:
        r1 = r8;
        goto L_0x0158;
    L_0x024e:
        r8 = r1;
        goto L_0x00d1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aps.NetManager.mo13279a(android.content.Context, java.lang.String, byte[], java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:139:0x024a A:{ExcHandler: SocketException (e java.net.SocketException), Splitter:B:9:0x0033, PHI: r4 r5 } */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x0245 A:{ExcHandler: SocketTimeoutException (e java.net.SocketTimeoutException), Splitter:B:9:0x0033} */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x0240 A:{ExcHandler: ConnectTimeoutException (e org.apache.http.conn.ConnectTimeoutException), Splitter:B:9:0x0033} */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x023b A:{ExcHandler: Throwable (th java.lang.Throwable), Splitter:B:9:0x0033} */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0163  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0168  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0171 A:{SYNTHETIC, Splitter:B:56:0x0171} */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0176 A:{SYNTHETIC, Splitter:B:59:0x0176} */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x017b  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0180  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x024a A:{ExcHandler: SocketException (e java.net.SocketException), Splitter:B:9:0x0033, PHI: r4 r5 } */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x0245 A:{ExcHandler: SocketTimeoutException (e java.net.SocketTimeoutException), Splitter:B:9:0x0033} */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x0240 A:{ExcHandler: ConnectTimeoutException (e org.apache.http.conn.ConnectTimeoutException), Splitter:B:9:0x0033} */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x023b A:{ExcHandler: Throwable (th java.lang.Throwable), Splitter:B:9:0x0033} */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0163  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0168  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0171 A:{SYNTHETIC, Splitter:B:56:0x0171} */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0176 A:{SYNTHETIC, Splitter:B:59:0x0176} */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x017b  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0180  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x01ea A:{ExcHandler: SocketException (e java.net.SocketException), Splitter:B:5:0x0020} */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x01f7 A:{ExcHandler: SocketTimeoutException (e java.net.SocketTimeoutException), Splitter:B:5:0x0020, Catch:{ UnknownHostException -> 0x025e, SocketException -> 0x01ea, SocketTimeoutException -> 0x01f7, ConnectTimeoutException -> 0x0201, Throwable -> 0x020b, all -> 0x022b, all -> 0x01f4 }} */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0201 A:{ExcHandler: ConnectTimeoutException (e org.apache.http.conn.ConnectTimeoutException), Splitter:B:5:0x0020, Catch:{ UnknownHostException -> 0x025e, SocketException -> 0x01ea, SocketTimeoutException -> 0x01f7, ConnectTimeoutException -> 0x0201, Throwable -> 0x020b, all -> 0x022b, all -> 0x01f4 }} */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x020b A:{ExcHandler: Throwable (th java.lang.Throwable), Splitter:B:5:0x0020, Catch:{ UnknownHostException -> 0x025e, SocketException -> 0x01ea, SocketTimeoutException -> 0x01f7, ConnectTimeoutException -> 0x0201, Throwable -> 0x020b, all -> 0x022b, all -> 0x01f4 }} */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x022b A:{ExcHandler: all (r7_17 'th' java.lang.Throwable), Splitter:B:5:0x0020} */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0163  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0168  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0171 A:{SYNTHETIC, Splitter:B:56:0x0171} */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0176 A:{SYNTHETIC, Splitter:B:59:0x0176} */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x017b  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0180  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x024a A:{ExcHandler: SocketException (e java.net.SocketException), Splitter:B:9:0x0033, PHI: r4 r5 } */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x0245 A:{ExcHandler: SocketTimeoutException (e java.net.SocketTimeoutException), Splitter:B:9:0x0033} */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x0240 A:{ExcHandler: ConnectTimeoutException (e org.apache.http.conn.ConnectTimeoutException), Splitter:B:9:0x0033} */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x023b A:{ExcHandler: Throwable (th java.lang.Throwable), Splitter:B:9:0x0033} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:98:0x01df, code skipped:
            r1 = null;
            r3 = null;
            r5 = r7;
            r16 = r6;
            r6 = r2;
            r2 = null;
            r4 = r16;
     */
    /* JADX WARNING: Missing block: B:100:0x01eb, code skipped:
            r7 = null;
     */
    /* JADX WARNING: Missing block: B:107:0x01f8, code skipped:
            r7 = null;
     */
    /* JADX WARNING: Missing block: B:111:0x0202, code skipped:
            r7 = null;
     */
    /* JADX WARNING: Missing block: B:115:0x020c, code skipped:
            r7 = null;
     */
    /* JADX WARNING: Missing block: B:126:0x022b, code skipped:
            r7 = move-exception;
     */
    /* JADX WARNING: Missing block: B:127:0x022c, code skipped:
            r16 = r7;
            r7 = null;
            r1 = r16;
     */
    /* JADX WARNING: Missing block: B:143:0x0250, code skipped:
            r3 = null;
            r5 = null;
            r1 = null;
            r17 = r6;
            r6 = r2;
            r2 = null;
            r4 = r17;
     */
    /* JADX WARNING: Missing block: B:147:0x026e, code skipped:
            r1 = null;
            r3 = null;
            r5 = r7;
            r16 = r6;
            r6 = r2;
            r2 = null;
            r4 = r16;
     */
    /* JADX WARNING: Missing block: B:151:0x0286, code skipped:
            r1 = null;
            r3 = r5;
            r5 = r7;
            r16 = r6;
            r6 = r2;
            r2 = r4;
            r4 = r16;
     */
    /* renamed from: a */
    public java.lang.String mo13281a(byte[] r19, android.content.Context r20, org.json.JSONObject r21) throws java.lang.Exception {
        /*
        r18 = this;
        r7 = com.aps.C1269v.m5738b(r20);
        r1 = com.aps.NetManager.m5683a(r7);
        r2 = -1;
        if (r1 != r2) goto L_0x0013;
    L_0x000b:
        r1 = new com.amap.api.location.core.AMapLocException;
        r2 = "http连接失败 - ConnectionException";
        r1.<init>(r2);
        throw r1;
    L_0x0013:
        r2 = 0;
        r1 = 0;
        r6 = 0;
        r5 = 0;
        r4 = 0;
        r3 = 0;
        r11 = new java.lang.StringBuffer;
        r11.<init>();
        r8 = "";
        r0 = r20;
        r2 = com.aps.NetManager.m5687a(r0, r7);	 Catch:{ UnknownHostException -> 0x024f, SocketException -> 0x01ea, SocketTimeoutException -> 0x01f7, ConnectTimeoutException -> 0x0201, Throwable -> 0x020b, all -> 0x022b }
        r10 = com.aps.NetManager.m5690a(r21);	 Catch:{ UnknownHostException -> 0x025e, SocketException -> 0x01ea, SocketTimeoutException -> 0x01f7, ConnectTimeoutException -> 0x0201, Throwable -> 0x020b, all -> 0x022b }
        r7 = new org.apache.http.client.methods.HttpPost;	 Catch:{ UnknownHostException -> 0x025e, SocketException -> 0x01ea, SocketTimeoutException -> 0x01f7, ConnectTimeoutException -> 0x0201, Throwable -> 0x020b, all -> 0x022b }
        r9 = com.amap.api.location.core.ClientInfoUtil.m1444k();	 Catch:{ UnknownHostException -> 0x025e, SocketException -> 0x01ea, SocketTimeoutException -> 0x01f7, ConnectTimeoutException -> 0x0201, Throwable -> 0x020b, all -> 0x022b }
        r7.<init>(r9);	 Catch:{ UnknownHostException -> 0x025e, SocketException -> 0x01ea, SocketTimeoutException -> 0x01f7, ConnectTimeoutException -> 0x0201, Throwable -> 0x020b, all -> 0x022b }
        r9 = "UTF-8";
        r1 = com.aps.C1269v.m5734a(r19);	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r12 = new org.apache.http.entity.ByteArrayEntity;	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r12.<init>(r1);	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r1 = "application/octet-stream";
        r12.setContentType(r1);	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r1 = "Accept-Encoding";
        r13 = "gzip";
        r7.addHeader(r1, r13);	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r1 = "gzipped";
        r13 = "1";
        r7.addHeader(r1, r13);	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r1 = "X-INFO";
        r13 = 2;
        r13 = r10[r13];	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r7.addHeader(r1, r13);	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r1 = "X-BIZ";
        r13 = 3;
        r13 = r10[r13];	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r7.addHeader(r1, r13);	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r1 = "KEY";
        r13 = 1;
        r13 = r10[r13];	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r7.addHeader(r1, r13);	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r1 = "enginever";
        r13 = "4.2";
        r7.addHeader(r1, r13);	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r1 = 4;
        r1 = r10[r1];	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        if (r1 == 0) goto L_0x0086;
    L_0x0075:
        r1 = 4;
        r1 = r10[r1];	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r1 = r1.length();	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        if (r1 <= 0) goto L_0x0086;
    L_0x007e:
        r1 = "User-Agent";
        r13 = 4;
        r13 = r10[r13];	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r7.addHeader(r1, r13);	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
    L_0x0086:
        r1 = com.amap.api.location.core.CoreUtil.m1455a();	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r13 = new java.lang.StringBuilder;	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r13.<init>();	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r14 = "key=";
        r13 = r13.append(r14);	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r14 = 1;
        r10 = r10[r14];	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r10 = r13.append(r10);	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r10 = r10.toString();	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r10 = com.amap.api.location.core.CoreUtil.m1456a(r1, r10);	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r13 = "ts";
        r7.addHeader(r13, r1);	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r1 = "scode";
        r7.addHeader(r1, r10);	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r1 = "platinfo";
        r10 = "platform=Android&sdkversion=%s&product=%s";
        r13 = 2;
        r13 = new java.lang.Object[r13];	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r14 = 0;
        r15 = "1.4.1";
        r13[r14] = r15;	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r14 = 1;
        r15 = "loc";
        r13[r14] = r15;	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r10 = java.lang.String.format(r10, r13);	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r7.addHeader(r1, r10);	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r1 = "logversion";
        r10 = "2.0";
        r7.addHeader(r1, r10);	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r1 = 0;
        r10 = r11.length();	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r11.delete(r1, r10);	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r7.setEntity(r12);	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r1 = r2 instanceof org.apache.http.client.HttpClient;	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        if (r1 != 0) goto L_0x0184;
    L_0x00dd:
        r1 = r2.execute(r7);	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r10 = r1;
    L_0x00e2:
        r1 = r10.getStatusLine();	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r1 = r1.getStatusCode();	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r12 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r1 != r12) goto L_0x01d1;
    L_0x00ee:
        r1 = r10.getEntity();	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r6 = r1.getContent();	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r1 = r10.getEntity();	 Catch:{ UnknownHostException -> 0x026d, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r1 = r1.getContentType();	 Catch:{ UnknownHostException -> 0x026d, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r8 = r1.getValue();	 Catch:{ UnknownHostException -> 0x026d, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r1 = "";
        r12 = "charset=";
        r12 = r8.indexOf(r12);	 Catch:{ UnknownHostException -> 0x026d, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r13 = -1;
        if (r12 == r13) goto L_0x0113;
    L_0x010d:
        r1 = r12 + 8;
        r1 = r8.substring(r1);	 Catch:{ UnknownHostException -> 0x026d, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
    L_0x0113:
        r8 = android.text.TextUtils.isEmpty(r1);	 Catch:{ UnknownHostException -> 0x026d, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        if (r8 == 0) goto L_0x0294;
    L_0x0119:
        r8 = r9;
    L_0x011a:
        r1 = com.aps.NetManager.m5689a(r10);	 Catch:{ UnknownHostException -> 0x026d, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        if (r1 == 0) goto L_0x0126;
    L_0x0120:
        r1 = new java.util.zip.GZIPInputStream;	 Catch:{ UnknownHostException -> 0x026d, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r1.<init>(r6);	 Catch:{ UnknownHostException -> 0x026d, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r5 = r1;
    L_0x0126:
        if (r5 == 0) goto L_0x018f;
    L_0x0128:
        r1 = new java.io.InputStreamReader;	 Catch:{ UnknownHostException -> 0x0279, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r1.<init>(r5, r8);	 Catch:{ UnknownHostException -> 0x0279, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r4 = r1;
    L_0x012e:
        r1 = new java.io.BufferedReader;	 Catch:{ UnknownHostException -> 0x0285, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r8 = 2048; // 0x800 float:2.87E-42 double:1.0118E-320;
        r1.<init>(r4, r8);	 Catch:{ UnknownHostException -> 0x0285, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r3 = "";
    L_0x0137:
        r3 = r1.readLine();	 Catch:{ UnknownHostException -> 0x0141, SocketException -> 0x024c, SocketTimeoutException -> 0x0247, ConnectTimeoutException -> 0x0242, Throwable -> 0x023d, all -> 0x0233 }
        if (r3 == 0) goto L_0x0196;
    L_0x013d:
        r11.append(r3);	 Catch:{ UnknownHostException -> 0x0141, SocketException -> 0x024c, SocketTimeoutException -> 0x0247, ConnectTimeoutException -> 0x0242, Throwable -> 0x023d, all -> 0x0233 }
        goto L_0x0137;
    L_0x0141:
        r3 = move-exception;
        r3 = r5;
        r5 = r7;
        r16 = r6;
        r6 = r2;
        r2 = r4;
        r4 = r16;
    L_0x014a:
        r7 = new com.amap.api.location.core.AMapLocException;	 Catch:{ all -> 0x0153 }
        r8 = "未知主机 - UnKnowHostException";
        r7.<init>(r8);	 Catch:{ all -> 0x0153 }
        throw r7;	 Catch:{ all -> 0x0153 }
    L_0x0153:
        r7 = move-exception;
        r16 = r7;
        r7 = r5;
        r5 = r3;
        r3 = r1;
        r1 = r16;
        r17 = r4;
        r4 = r2;
        r2 = r6;
        r6 = r17;
    L_0x0161:
        if (r7 == 0) goto L_0x0166;
    L_0x0163:
        r7.abort();
    L_0x0166:
        if (r2 == 0) goto L_0x016f;
    L_0x0168:
        r2 = r2.getConnectionManager();
        r2.shutdown();
    L_0x016f:
        if (r5 == 0) goto L_0x0174;
    L_0x0171:
        r5.close();	 Catch:{ Throwable -> 0x0215 }
    L_0x0174:
        if (r6 == 0) goto L_0x0179;
    L_0x0176:
        r6.close();	 Catch:{ Throwable -> 0x021b }
    L_0x0179:
        if (r4 == 0) goto L_0x017e;
    L_0x017b:
        r4.close();
    L_0x017e:
        if (r3 == 0) goto L_0x0183;
    L_0x0180:
        r3.close();
    L_0x0183:
        throw r1;
    L_0x0184:
        r0 = r2;
        r0 = (org.apache.http.client.HttpClient) r0;	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r1 = r0;
        r1 = com.newrelic.agent.android.instrumentation.HttpInstrumentation.execute(r1, r7);	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r10 = r1;
        goto L_0x00e2;
    L_0x018f:
        r1 = new java.io.InputStreamReader;	 Catch:{ UnknownHostException -> 0x0279, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r1.<init>(r6, r8);	 Catch:{ UnknownHostException -> 0x0279, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r4 = r1;
        goto L_0x012e;
    L_0x0196:
        r3 = r11.toString();	 Catch:{ UnknownHostException -> 0x0141, SocketException -> 0x024c, SocketTimeoutException -> 0x0247, ConnectTimeoutException -> 0x0242, Throwable -> 0x023d, all -> 0x0233 }
        r8 = 0;
        r9 = r11.length();	 Catch:{ UnknownHostException -> 0x0141, SocketException -> 0x024c, SocketTimeoutException -> 0x0247, ConnectTimeoutException -> 0x0242, Throwable -> 0x023d, all -> 0x0233 }
        r11.delete(r8, r9);	 Catch:{ UnknownHostException -> 0x0141, SocketException -> 0x024c, SocketTimeoutException -> 0x0247, ConnectTimeoutException -> 0x0242, Throwable -> 0x023d, all -> 0x0233 }
        r16 = r3;
        r3 = r1;
        r1 = r16;
    L_0x01a7:
        if (r7 == 0) goto L_0x01ac;
    L_0x01a9:
        r7.abort();
    L_0x01ac:
        if (r2 == 0) goto L_0x01b5;
    L_0x01ae:
        r2 = r2.getConnectionManager();
        r2.shutdown();
    L_0x01b5:
        if (r5 == 0) goto L_0x01ba;
    L_0x01b7:
        r5.close();	 Catch:{ Throwable -> 0x0221 }
    L_0x01ba:
        if (r6 == 0) goto L_0x01bf;
    L_0x01bc:
        r6.close();	 Catch:{ Throwable -> 0x0226 }
    L_0x01bf:
        if (r4 == 0) goto L_0x01c4;
    L_0x01c1:
        r4.close();
    L_0x01c4:
        if (r3 == 0) goto L_0x01c9;
    L_0x01c6:
        r3.close();
    L_0x01c9:
        r2 = android.text.TextUtils.isEmpty(r1);
        if (r2 == 0) goto L_0x01d0;
    L_0x01cf:
        r1 = 0;
    L_0x01d0:
        return r1;
    L_0x01d1:
        r9 = 404; // 0x194 float:5.66E-43 double:1.996E-321;
        if (r1 != r9) goto L_0x0291;
    L_0x01d5:
        r1 = new com.amap.api.location.core.AMapLocException;	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        r8 = "服务器连接失败 - UnknownServiceException";
        r1.<init>(r8);	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
        throw r1;	 Catch:{ UnknownHostException -> 0x01de, SocketException -> 0x024a, SocketTimeoutException -> 0x0245, ConnectTimeoutException -> 0x0240, Throwable -> 0x023b }
    L_0x01de:
        r1 = move-exception;
        r1 = r3;
        r3 = r5;
        r5 = r7;
        r16 = r6;
        r6 = r2;
        r2 = r4;
        r4 = r16;
        goto L_0x014a;
    L_0x01ea:
        r7 = move-exception;
        r7 = r1;
    L_0x01ec:
        r1 = new com.amap.api.location.core.AMapLocException;	 Catch:{ all -> 0x01f4 }
        r8 = "socket 连接异常 - SocketException";
        r1.<init>(r8);	 Catch:{ all -> 0x01f4 }
        throw r1;	 Catch:{ all -> 0x01f4 }
    L_0x01f4:
        r1 = move-exception;
        goto L_0x0161;
    L_0x01f7:
        r7 = move-exception;
        r7 = r1;
    L_0x01f9:
        r1 = new com.amap.api.location.core.AMapLocException;	 Catch:{ all -> 0x01f4 }
        r8 = "socket 连接超时 - SocketTimeoutException";
        r1.<init>(r8);	 Catch:{ all -> 0x01f4 }
        throw r1;	 Catch:{ all -> 0x01f4 }
    L_0x0201:
        r7 = move-exception;
        r7 = r1;
    L_0x0203:
        r1 = new com.amap.api.location.core.AMapLocException;	 Catch:{ all -> 0x01f4 }
        r8 = "http连接失败 - ConnectionException";
        r1.<init>(r8);	 Catch:{ all -> 0x01f4 }
        throw r1;	 Catch:{ all -> 0x01f4 }
    L_0x020b:
        r7 = move-exception;
        r7 = r1;
    L_0x020d:
        r1 = new com.amap.api.location.core.AMapLocException;	 Catch:{ all -> 0x01f4 }
        r8 = "http连接失败 - ConnectionException";
        r1.<init>(r8);	 Catch:{ all -> 0x01f4 }
        throw r1;	 Catch:{ all -> 0x01f4 }
    L_0x0215:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x0174;
    L_0x021b:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x0179;
    L_0x0221:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x01ba;
    L_0x0226:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x01bf;
    L_0x022b:
        r7 = move-exception;
        r16 = r7;
        r7 = r1;
        r1 = r16;
        goto L_0x0161;
    L_0x0233:
        r3 = move-exception;
        r16 = r3;
        r3 = r1;
        r1 = r16;
        goto L_0x0161;
    L_0x023b:
        r1 = move-exception;
        goto L_0x020d;
    L_0x023d:
        r3 = move-exception;
        r3 = r1;
        goto L_0x020d;
    L_0x0240:
        r1 = move-exception;
        goto L_0x0203;
    L_0x0242:
        r3 = move-exception;
        r3 = r1;
        goto L_0x0203;
    L_0x0245:
        r1 = move-exception;
        goto L_0x01f9;
    L_0x0247:
        r3 = move-exception;
        r3 = r1;
        goto L_0x01f9;
    L_0x024a:
        r1 = move-exception;
        goto L_0x01ec;
    L_0x024c:
        r3 = move-exception;
        r3 = r1;
        goto L_0x01ec;
    L_0x024f:
        r7 = move-exception;
        r16 = r3;
        r3 = r5;
        r5 = r1;
        r1 = r16;
        r17 = r6;
        r6 = r2;
        r2 = r4;
        r4 = r17;
        goto L_0x014a;
    L_0x025e:
        r7 = move-exception;
        r16 = r3;
        r3 = r5;
        r5 = r1;
        r1 = r16;
        r17 = r6;
        r6 = r2;
        r2 = r4;
        r4 = r17;
        goto L_0x014a;
    L_0x026d:
        r1 = move-exception;
        r1 = r3;
        r3 = r5;
        r5 = r7;
        r16 = r6;
        r6 = r2;
        r2 = r4;
        r4 = r16;
        goto L_0x014a;
    L_0x0279:
        r1 = move-exception;
        r1 = r3;
        r3 = r5;
        r5 = r7;
        r16 = r6;
        r6 = r2;
        r2 = r4;
        r4 = r16;
        goto L_0x014a;
    L_0x0285:
        r1 = move-exception;
        r1 = r3;
        r3 = r5;
        r5 = r7;
        r16 = r6;
        r6 = r2;
        r2 = r4;
        r4 = r16;
        goto L_0x014a;
    L_0x0291:
        r1 = r8;
        goto L_0x01a7;
    L_0x0294:
        r8 = r1;
        goto L_0x011a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aps.NetManager.mo13281a(byte[], android.content.Context, org.json.JSONObject):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:91:0x015c  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0129  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00fc A:{Catch:{ all -> 0x0162 }} */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0129  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x015c  */
    /* JADX WARNING: Missing block: B:40:0x00c8, code skipped:
            if (r2 != null) goto L_0x00ca;
     */
    /* JADX WARNING: Missing block: B:41:0x00ca, code skipped:
            r2.close();
     */
    /* JADX WARNING: Missing block: B:87:0x0154, code skipped:
            if (r2 == null) goto L_0x0030;
     */
    /* renamed from: a */
    public static org.apache.http.client.HttpClient m5687a(android.content.Context r13, android.net.NetworkInfo r14) throws java.lang.Exception {
        /*
        r6 = -1;
        r8 = 80;
        r9 = 1;
        r10 = 0;
        r7 = 0;
        r11 = new org.apache.http.params.BasicHttpParams;
        r11.<init>();
        r0 = r14.getType();
        if (r0 != 0) goto L_0x0189;
    L_0x0011:
        r0 = com.aps.C1269v.m5740c();
        r1 = 11;
        if (r0 < r1) goto L_0x006b;
    L_0x0019:
        r0 = com.aps.NetManager.m5686a(r13);
        if (r0 == 0) goto L_0x0185;
    L_0x001f:
        r0 = r0.address();	 Catch:{ Exception -> 0x0068 }
        r0 = (java.net.InetSocketAddress) r0;	 Catch:{ Exception -> 0x0068 }
    L_0x0025:
        if (r0 == 0) goto L_0x0185;
    L_0x0027:
        r1 = r0.getHostName();
        r0 = r0.getPort();
    L_0x002f:
        r6 = r0;
    L_0x0030:
        r0 = com.aps.NetManager.m5688a(r1, r6);
        if (r0 == 0) goto L_0x0042;
    L_0x0036:
        r0 = "http";
        r2 = new org.apache.http.HttpHost;
        r2.<init>(r1, r6, r0);
        r0 = "http.route.default-proxy";
        r11.setParameter(r0, r2);
    L_0x0042:
        r0 = com.aps.Const.f4440g;
        com.aps.C1269v.m5729a(r11, r0);
        org.apache.http.params.HttpProtocolParams.setUseExpectContinue(r11, r10);
        r0 = new org.apache.http.conn.scheme.SchemeRegistry;
        r0.<init>();
        r1 = org.apache.http.conn.scheme.PlainSocketFactory.getSocketFactory();
        r2 = new org.apache.http.conn.scheme.Scheme;
        r3 = "http";
        r2.<init>(r3, r1, r8);
        r0.register(r2);
        r1 = new org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
        r1.<init>(r11, r0);
        r0 = new org.apache.http.impl.client.DefaultHttpClient;
        r0.<init>(r1, r11);
        return r0;
    L_0x0068:
        r0 = move-exception;
        r0 = r7;
        goto L_0x0025;
    L_0x006b:
        r0 = "content://telephony/carriers/preferapn";
        r1 = android.net.Uri.parse(r0);
        r0 = r13.getContentResolver();
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r2 = r0.query(r1, r2, r3, r4, r5);	 Catch:{ SecurityException -> 0x00f4, Exception -> 0x014e, all -> 0x0158 }
        if (r2 == 0) goto L_0x017e;
    L_0x007f:
        r0 = r2.moveToFirst();	 Catch:{ SecurityException -> 0x016d, Exception -> 0x0165 }
        if (r0 == 0) goto L_0x017e;
    L_0x0085:
        r0 = "apn";
        r0 = r2.getColumnIndex(r0);	 Catch:{ SecurityException -> 0x016d, Exception -> 0x0165 }
        r0 = r2.getString(r0);	 Catch:{ SecurityException -> 0x016d, Exception -> 0x0165 }
        if (r0 == 0) goto L_0x00a5;
    L_0x0091:
        r1 = java.util.Locale.US;	 Catch:{ SecurityException -> 0x016d, Exception -> 0x0165 }
        r0 = r0.toLowerCase(r1);	 Catch:{ SecurityException -> 0x016d, Exception -> 0x0165 }
        r1 = 2;
        r1 = new java.lang.Object[r1];	 Catch:{ SecurityException -> 0x016d, Exception -> 0x0165 }
        r3 = 0;
        r4 = "nm|found apn:";
        r1[r3] = r4;	 Catch:{ SecurityException -> 0x016d, Exception -> 0x0165 }
        r3 = 1;
        r1[r3] = r0;	 Catch:{ SecurityException -> 0x016d, Exception -> 0x0165 }
        com.aps.C1269v.m5730a(r1);	 Catch:{ SecurityException -> 0x016d, Exception -> 0x0165 }
    L_0x00a5:
        if (r0 == 0) goto L_0x00cf;
    L_0x00a7:
        r1 = "ctwap";
        r1 = r0.contains(r1);	 Catch:{ SecurityException -> 0x016d, Exception -> 0x0165 }
        if (r1 == 0) goto L_0x00cf;
    L_0x00af:
        r0 = com.aps.NetManager.m5691b();	 Catch:{ SecurityException -> 0x016d, Exception -> 0x0165 }
        r1 = android.text.TextUtils.isEmpty(r0);	 Catch:{ SecurityException -> 0x016d, Exception -> 0x0165 }
        if (r1 != 0) goto L_0x0181;
    L_0x00b9:
        r1 = "null";
        r1 = r0.equals(r1);	 Catch:{ SecurityException -> 0x016d, Exception -> 0x0165 }
        if (r1 != 0) goto L_0x0181;
    L_0x00c1:
        r1 = r9;
    L_0x00c2:
        if (r1 != 0) goto L_0x00c6;
    L_0x00c4:
        r0 = "10.0.0.200";
    L_0x00c6:
        r6 = r8;
        r1 = r0;
    L_0x00c8:
        if (r2 == 0) goto L_0x0030;
    L_0x00ca:
        r2.close();
        goto L_0x0030;
    L_0x00cf:
        if (r0 == 0) goto L_0x017e;
    L_0x00d1:
        r1 = "wap";
        r0 = r0.contains(r1);	 Catch:{ SecurityException -> 0x016d, Exception -> 0x0165 }
        if (r0 == 0) goto L_0x017e;
    L_0x00da:
        r0 = com.aps.NetManager.m5691b();	 Catch:{ SecurityException -> 0x016d, Exception -> 0x0165 }
        r1 = android.text.TextUtils.isEmpty(r0);	 Catch:{ SecurityException -> 0x016d, Exception -> 0x0165 }
        if (r1 != 0) goto L_0x017a;
    L_0x00e4:
        r1 = "null";
        r1 = r0.equals(r1);	 Catch:{ SecurityException -> 0x016d, Exception -> 0x0165 }
        if (r1 != 0) goto L_0x017a;
    L_0x00ec:
        r1 = r9;
    L_0x00ed:
        if (r1 != 0) goto L_0x00f1;
    L_0x00ef:
        r0 = "10.0.0.172";
    L_0x00f1:
        r6 = r8;
        r1 = r0;
        goto L_0x00c8;
    L_0x00f4:
        r0 = move-exception;
        r0 = r7;
    L_0x00f6:
        r1 = r14.getExtraInfo();	 Catch:{ all -> 0x0162 }
        if (r1 == 0) goto L_0x0176;
    L_0x00fc:
        r1 = r14.getExtraInfo();	 Catch:{ all -> 0x0162 }
        r2 = java.util.Locale.US;	 Catch:{ all -> 0x0162 }
        r2 = r1.toLowerCase(r2);	 Catch:{ all -> 0x0162 }
        r1 = com.aps.NetManager.m5691b();	 Catch:{ all -> 0x0162 }
        r3 = "ctwap";
        r3 = r2.indexOf(r3);	 Catch:{ all -> 0x0162 }
        if (r3 == r6) goto L_0x012e;
    L_0x0112:
        r2 = android.text.TextUtils.isEmpty(r1);	 Catch:{ all -> 0x0162 }
        if (r2 != 0) goto L_0x0178;
    L_0x0118:
        r2 = "null";
        r2 = r1.equals(r2);	 Catch:{ all -> 0x0162 }
        if (r2 != 0) goto L_0x0178;
    L_0x0120:
        r0 = r1;
    L_0x0121:
        if (r9 != 0) goto L_0x0125;
    L_0x0123:
        r0 = "10.0.0.200";
    L_0x0125:
        r6 = r8;
        r1 = r0;
    L_0x0127:
        if (r7 == 0) goto L_0x0030;
    L_0x0129:
        r7.close();
        goto L_0x0030;
    L_0x012e:
        r3 = "wap";
        r2 = r2.indexOf(r3);	 Catch:{ all -> 0x0162 }
        if (r2 == r6) goto L_0x0176;
    L_0x0137:
        r2 = android.text.TextUtils.isEmpty(r1);	 Catch:{ all -> 0x0162 }
        if (r2 != 0) goto L_0x0174;
    L_0x013d:
        r2 = "null";
        r2 = r1.equals(r2);	 Catch:{ all -> 0x0162 }
        if (r2 != 0) goto L_0x0174;
    L_0x0145:
        r0 = r1;
        r1 = r9;
    L_0x0147:
        if (r1 != 0) goto L_0x014b;
    L_0x0149:
        r0 = "10.0.0.200";
    L_0x014b:
        r6 = r8;
        r1 = r0;
        goto L_0x0127;
    L_0x014e:
        r0 = move-exception;
        r2 = r7;
        r1 = r7;
    L_0x0151:
        com.aps.C1269v.m5728a(r0);	 Catch:{ all -> 0x0160 }
        if (r2 == 0) goto L_0x0030;
    L_0x0156:
        goto L_0x00ca;
    L_0x0158:
        r0 = move-exception;
        r2 = r7;
    L_0x015a:
        if (r2 == 0) goto L_0x015f;
    L_0x015c:
        r2.close();
    L_0x015f:
        throw r0;
    L_0x0160:
        r0 = move-exception;
        goto L_0x015a;
    L_0x0162:
        r0 = move-exception;
        r2 = r7;
        goto L_0x015a;
    L_0x0165:
        r0 = move-exception;
        r1 = r7;
        goto L_0x0151;
    L_0x0168:
        r1 = move-exception;
        r12 = r1;
        r1 = r0;
        r0 = r12;
        goto L_0x0151;
    L_0x016d:
        r0 = move-exception;
        r0 = r7;
        r7 = r2;
        goto L_0x00f6;
    L_0x0171:
        r1 = move-exception;
        r7 = r2;
        goto L_0x00f6;
    L_0x0174:
        r1 = r10;
        goto L_0x0147;
    L_0x0176:
        r1 = r0;
        goto L_0x0127;
    L_0x0178:
        r9 = r10;
        goto L_0x0121;
    L_0x017a:
        r1 = r10;
        r0 = r7;
        goto L_0x00ed;
    L_0x017e:
        r1 = r7;
        goto L_0x00c8;
    L_0x0181:
        r1 = r10;
        r0 = r7;
        goto L_0x00c2;
    L_0x0185:
        r0 = r6;
        r1 = r7;
        goto L_0x002f;
    L_0x0189:
        r1 = r7;
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aps.NetManager.m5687a(android.content.Context, android.net.NetworkInfo):org.apache.http.client.HttpClient");
    }

    /* renamed from: a */
    private static Proxy m5686a(Context context) {
        List select;
        try {
            select = ProxySelector.getDefault().select(new URI(ClientInfoUtil.m1444k()));
        } catch (Exception e) {
            select = null;
        }
        if (select == null || select.isEmpty()) {
            return null;
        }
        Proxy proxy = (Proxy) select.get(0);
        if (proxy == null || proxy.type() == Type.DIRECT) {
            return null;
        }
        return proxy;
    }

    /* renamed from: a */
    private static boolean m5688a(String str, int i) {
        return (str == null || str.length() <= 0 || i == -1) ? false : true;
    }

    /* renamed from: a */
    public static int m5683a(NetworkInfo networkInfo) {
        if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
            return networkInfo.getType();
        }
        return -1;
    }

    /* renamed from: a */
    public static String m5685a(TelephonyManager telephonyManager) {
        int i = 0;
        if (telephonyManager != null) {
            i = telephonyManager.getNetworkType();
        }
        return (String) Const.f4446m.get(i, "UNKNOWN");
    }

    /* renamed from: a */
    private static boolean m5689a(HttpResponse httpResponse) {
        Header firstHeader = httpResponse.getFirstHeader("Content-Encoding");
        if (firstHeader == null || !firstHeader.getValue().equalsIgnoreCase("gzip")) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public static String[] m5690a(JSONObject jSONObject) {
        String[] strArr = new String[]{null, null, null, null, null};
        if (jSONObject == null || ClientInfoUtil.m1444k().length() == 0) {
            strArr[0] = "false";
        } else {
            try {
                String string = jSONObject.getString(Parameters.API_KEY);
                String string2 = jSONObject.getString("X-INFO");
                String string3 = jSONObject.getString("X-BIZ");
                String string4 = jSONObject.getString("User-Agent");
                if (!(TextUtils.isEmpty(string) || TextUtils.isEmpty(string4))) {
                    strArr[0] = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE;
                    strArr[1] = string;
                    strArr[2] = string2;
                    strArr[3] = string3;
                    strArr[4] = string4;
                }
            } catch (JSONException e) {
            }
            if (strArr[0] == null || !strArr[0].equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) {
                strArr[0] = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE;
            }
        }
        return strArr;
    }

    /* renamed from: b */
    private static String m5691b() {
        String defaultHost;
        try {
            defaultHost = android.net.Proxy.getDefaultHost();
        } catch (Throwable th) {
            th.printStackTrace();
            defaultHost = null;
        }
        if (defaultHost == null) {
            return SafeJsonPrimitive.NULL_STRING;
        }
        return defaultHost;
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x02c9  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x015d  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x02c6  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0163  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x02c3  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x016d  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0173  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0179  */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x02c0  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x018e  */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x02bd  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0194  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x02ba  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x019e  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x01a4  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x01aa  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x02b7  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x01bf  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x02b4  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x01c5  */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x02b1  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x01cf  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x01d5  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01db  */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x01f0  */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x01f5  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x01fe  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0203  */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0208  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x015d  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x02c9  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0163  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x02c6  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x016d  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x02c3  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0173  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0179  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x018e  */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x02c0  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0194  */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x02bd  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x019e  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x02ba  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x01a4  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x01aa  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x01bf  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x02b7  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x01c5  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x02b4  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x01cf  */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x02b1  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x01d5  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01db  */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x01f0  */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x01f5  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x01fe  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0203  */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0208  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x02c9  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x015d  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x02c6  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0163  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x02c3  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x016d  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0173  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0179  */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x02c0  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x018e  */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x02bd  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0194  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x02ba  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x019e  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x01a4  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x01aa  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x02b7  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x01bf  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x02b4  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x01c5  */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x02b1  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x01cf  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x01d5  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01db  */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x01f0  */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x01f5  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x01fe  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0203  */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0208  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x015d  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x02c9  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0163  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x02c6  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x016d  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x02c3  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0173  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0179  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x018e  */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x02c0  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0194  */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x02bd  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x019e  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x02ba  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x01a4  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x01aa  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x01bf  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x02b7  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x01c5  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x02b4  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x01cf  */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x02b1  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x01d5  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01db  */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x01f0  */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x01f5  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x01fe  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0203  */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0208  */
    /* renamed from: a */
    public java.lang.String mo13280a(byte[] r19, android.content.Context r20) throws java.lang.Exception {
        /*
        r18 = this;
        r9 = "";
        r12 = com.aps.C1269v.m5738b(r20);
        r1 = com.aps.NetManager.m5683a(r12);
        r2 = -1;
        if (r1 != r2) goto L_0x000f;
    L_0x000d:
        r1 = 0;
    L_0x000e:
        return r1;
    L_0x000f:
        r5 = 0;
        r2 = 0;
        r8 = 0;
        r7 = 0;
        r6 = 0;
        r4 = new java.lang.StringBuffer;
        r4.<init>();
        r13 = new java.lang.StringBuffer;
        r13.<init>();
        r1 = "http://cgicol.amap.com/collection/writedata?ver=v1.0_ali&";
        r13.append(r1);
        r1 = "zei=";
        r1 = r13.append(r1);
        r3 = com.aps.Const.f4434a;
        r1.append(r3);
        r1 = "&zsi=";
        r1 = r13.append(r1);
        r3 = com.aps.Const.f4435b;
        r1.append(r3);
        r3 = 0;
        r1 = 0;
        r11 = r1;
        r1 = r2;
        r2 = r5;
        r5 = r9;
    L_0x0040:
        r9 = 1;
        if (r11 >= r9) goto L_0x0045;
    L_0x0043:
        if (r3 == 0) goto L_0x0057;
    L_0x0045:
        r1 = 0;
        r2 = r13.length();
        r13.delete(r1, r2);
        r1 = "";
        r1 = r5.equals(r1);
        if (r1 == 0) goto L_0x020c;
    L_0x0055:
        r1 = 0;
        goto L_0x000e;
    L_0x0057:
        r0 = r20;
        r2 = com.aps.NetManager.m5687a(r0, r12);	 Catch:{ UnknownHostException -> 0x0278, SocketException -> 0x0258, SocketTimeoutException -> 0x0238, ConnectTimeoutException -> 0x021c, all -> 0x020f }
        r10 = new org.apache.http.client.methods.HttpPost;	 Catch:{ UnknownHostException -> 0x0278, SocketException -> 0x0258, SocketTimeoutException -> 0x0238, ConnectTimeoutException -> 0x021c, all -> 0x020f }
        r9 = r13.toString();	 Catch:{ UnknownHostException -> 0x0278, SocketException -> 0x0258, SocketTimeoutException -> 0x0238, ConnectTimeoutException -> 0x021c, all -> 0x020f }
        r10.<init>(r9);	 Catch:{ UnknownHostException -> 0x0278, SocketException -> 0x0258, SocketTimeoutException -> 0x0238, ConnectTimeoutException -> 0x021c, all -> 0x020f }
        r1 = 0;
        r9 = r4.length();	 Catch:{ UnknownHostException -> 0x0288, SocketException -> 0x0261, SocketTimeoutException -> 0x0241, ConnectTimeoutException -> 0x0224, all -> 0x0213 }
        r4.delete(r1, r9);	 Catch:{ UnknownHostException -> 0x0288, SocketException -> 0x0261, SocketTimeoutException -> 0x0241, ConnectTimeoutException -> 0x0224, all -> 0x0213 }
        r1 = "application/soap+xml;charset=";
        r4.append(r1);	 Catch:{ UnknownHostException -> 0x0288, SocketException -> 0x0261, SocketTimeoutException -> 0x0241, ConnectTimeoutException -> 0x0224, all -> 0x0213 }
        r1 = "UTF-8";
        r4.append(r1);	 Catch:{ UnknownHostException -> 0x0288, SocketException -> 0x0261, SocketTimeoutException -> 0x0241, ConnectTimeoutException -> 0x0224, all -> 0x0213 }
        r1 = 0;
        r9 = r4.length();	 Catch:{ UnknownHostException -> 0x0288, SocketException -> 0x0261, SocketTimeoutException -> 0x0241, ConnectTimeoutException -> 0x0224, all -> 0x0213 }
        r4.delete(r1, r9);	 Catch:{ UnknownHostException -> 0x0288, SocketException -> 0x0261, SocketTimeoutException -> 0x0241, ConnectTimeoutException -> 0x0224, all -> 0x0213 }
        r1 = "gzipped";
        r9 = "1";
        r10.addHeader(r1, r9);	 Catch:{ UnknownHostException -> 0x0288, SocketException -> 0x0261, SocketTimeoutException -> 0x0241, ConnectTimeoutException -> 0x0224, all -> 0x0213 }
        r1 = com.aps.C1269v.m5734a(r19);	 Catch:{ UnknownHostException -> 0x0288, SocketException -> 0x0261, SocketTimeoutException -> 0x0241, ConnectTimeoutException -> 0x0224, all -> 0x0213 }
        r9 = new org.apache.http.entity.ByteArrayEntity;	 Catch:{ UnknownHostException -> 0x0288, SocketException -> 0x0261, SocketTimeoutException -> 0x0241, ConnectTimeoutException -> 0x0224, all -> 0x0213 }
        r9.<init>(r1);	 Catch:{ UnknownHostException -> 0x0288, SocketException -> 0x0261, SocketTimeoutException -> 0x0241, ConnectTimeoutException -> 0x0224, all -> 0x0213 }
        r1 = "application/octet-stream";
        r9.setContentType(r1);	 Catch:{ UnknownHostException -> 0x0288, SocketException -> 0x0261, SocketTimeoutException -> 0x0241, ConnectTimeoutException -> 0x0224, all -> 0x0213 }
        r10.setEntity(r9);	 Catch:{ UnknownHostException -> 0x0288, SocketException -> 0x0261, SocketTimeoutException -> 0x0241, ConnectTimeoutException -> 0x0224, all -> 0x0213 }
        r1 = r2 instanceof org.apache.http.client.HttpClient;	 Catch:{ UnknownHostException -> 0x0288, SocketException -> 0x0261, SocketTimeoutException -> 0x0241, ConnectTimeoutException -> 0x0224, all -> 0x0213 }
        if (r1 != 0) goto L_0x0112;
    L_0x009c:
        r1 = r2.execute(r10);	 Catch:{ UnknownHostException -> 0x0288, SocketException -> 0x0261, SocketTimeoutException -> 0x0241, ConnectTimeoutException -> 0x0224, all -> 0x0213 }
    L_0x00a0:
        r9 = r1.getStatusLine();	 Catch:{ UnknownHostException -> 0x0288, SocketException -> 0x0261, SocketTimeoutException -> 0x0241, ConnectTimeoutException -> 0x0224, all -> 0x0213 }
        r9 = r9.getStatusCode();	 Catch:{ UnknownHostException -> 0x0288, SocketException -> 0x0261, SocketTimeoutException -> 0x0241, ConnectTimeoutException -> 0x0224, all -> 0x0213 }
        r14 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r9 != r14) goto L_0x02cc;
    L_0x00ac:
        r1 = r1.getEntity();	 Catch:{ UnknownHostException -> 0x0288, SocketException -> 0x0261, SocketTimeoutException -> 0x0241, ConnectTimeoutException -> 0x0224, all -> 0x0213 }
        r9 = r1.getContent();	 Catch:{ UnknownHostException -> 0x0288, SocketException -> 0x0261, SocketTimeoutException -> 0x0241, ConnectTimeoutException -> 0x0224, all -> 0x0213 }
        r8 = new java.io.InputStreamReader;	 Catch:{ UnknownHostException -> 0x0295, SocketException -> 0x0269, SocketTimeoutException -> 0x0249, ConnectTimeoutException -> 0x022b, all -> 0x0215 }
        r1 = "UTF-8";
        r8.<init>(r9, r1);	 Catch:{ UnknownHostException -> 0x0295, SocketException -> 0x0269, SocketTimeoutException -> 0x0249, ConnectTimeoutException -> 0x022b, all -> 0x0215 }
        r1 = new java.io.BufferedReader;	 Catch:{ UnknownHostException -> 0x029f, SocketException -> 0x0270, SocketTimeoutException -> 0x0250, ConnectTimeoutException -> 0x0231, all -> 0x0218 }
        r7 = 2048; // 0x800 float:2.87E-42 double:1.0118E-320;
        r1.<init>(r8, r7);	 Catch:{ UnknownHostException -> 0x029f, SocketException -> 0x0270, SocketTimeoutException -> 0x0250, ConnectTimeoutException -> 0x0231, all -> 0x0218 }
        r6 = "";
    L_0x00c4:
        r6 = r1.readLine();	 Catch:{ UnknownHostException -> 0x00ce, SocketException -> 0x0156, SocketTimeoutException -> 0x0187, ConnectTimeoutException -> 0x01b8, all -> 0x01e9 }
        if (r6 == 0) goto L_0x011b;
    L_0x00ca:
        r4.append(r6);	 Catch:{ UnknownHostException -> 0x00ce, SocketException -> 0x0156, SocketTimeoutException -> 0x0187, ConnectTimeoutException -> 0x01b8, all -> 0x01e9 }
        goto L_0x00c4;
    L_0x00ce:
        r6 = move-exception;
        r6 = r10;
        r7 = r2;
        r2 = r8;
        r8 = r5;
        r5 = r9;
    L_0x00d4:
        if (r6 == 0) goto L_0x00da;
    L_0x00d6:
        r6.abort();
        r6 = 0;
    L_0x00da:
        if (r7 == 0) goto L_0x00e4;
    L_0x00dc:
        r7 = r7.getConnectionManager();
        r7.shutdown();
        r7 = 0;
    L_0x00e4:
        if (r5 == 0) goto L_0x00ea;
    L_0x00e6:
        r5.close();
        r5 = 0;
    L_0x00ea:
        if (r2 == 0) goto L_0x00f0;
    L_0x00ec:
        r2.close();
        r2 = 0;
    L_0x00f0:
        if (r1 == 0) goto L_0x00f6;
    L_0x00f2:
        r1.close();
        r1 = 0;
    L_0x00f6:
        r15 = r3;
        r3 = r1;
        r1 = r15;
        r16 = r4;
        r4 = r2;
        r2 = r16;
    L_0x00fe:
        r9 = r11 + 1;
        r11 = r9;
        r15 = r1;
        r1 = r6;
        r6 = r3;
        r3 = r15;
        r16 = r4;
        r4 = r2;
        r2 = r7;
        r7 = r16;
        r17 = r5;
        r5 = r8;
        r8 = r17;
        goto L_0x0040;
    L_0x0112:
        r0 = r2;
        r0 = (org.apache.http.client.HttpClient) r0;	 Catch:{ UnknownHostException -> 0x0288, SocketException -> 0x0261, SocketTimeoutException -> 0x0241, ConnectTimeoutException -> 0x0224, all -> 0x0213 }
        r1 = r0;
        r1 = com.newrelic.agent.android.instrumentation.HttpInstrumentation.execute(r1, r10);	 Catch:{ UnknownHostException -> 0x0288, SocketException -> 0x0261, SocketTimeoutException -> 0x0241, ConnectTimeoutException -> 0x0224, all -> 0x0213 }
        goto L_0x00a0;
    L_0x011b:
        r5 = r4.toString();	 Catch:{ UnknownHostException -> 0x00ce, SocketException -> 0x0156, SocketTimeoutException -> 0x0187, ConnectTimeoutException -> 0x01b8, all -> 0x01e9 }
        r6 = 0;
        r7 = r4.length();	 Catch:{ UnknownHostException -> 0x00ce, SocketException -> 0x0156, SocketTimeoutException -> 0x0187, ConnectTimeoutException -> 0x01b8, all -> 0x01e9 }
        r4.delete(r6, r7);	 Catch:{ UnknownHostException -> 0x00ce, SocketException -> 0x0156, SocketTimeoutException -> 0x0187, ConnectTimeoutException -> 0x01b8, all -> 0x01e9 }
        r4 = 0;
        r3 = 1;
        r15 = r3;
        r3 = r4;
        r4 = r8;
        r8 = r5;
        r5 = r9;
        r9 = r1;
        r1 = r15;
    L_0x0130:
        if (r10 == 0) goto L_0x02ae;
    L_0x0132:
        r10.abort();
        r6 = 0;
    L_0x0136:
        if (r2 == 0) goto L_0x02ab;
    L_0x0138:
        r2 = r2.getConnectionManager();
        r2.shutdown();
        r7 = 0;
    L_0x0140:
        if (r5 == 0) goto L_0x0146;
    L_0x0142:
        r5.close();
        r5 = 0;
    L_0x0146:
        if (r4 == 0) goto L_0x014c;
    L_0x0148:
        r4.close();
        r4 = 0;
    L_0x014c:
        if (r9 == 0) goto L_0x02a8;
    L_0x014e:
        r9.close();
        r2 = 0;
    L_0x0152:
        r15 = r3;
        r3 = r2;
        r2 = r15;
        goto L_0x00fe;
    L_0x0156:
        r6 = move-exception;
        r15 = r8;
        r8 = r5;
        r5 = r2;
        r2 = r15;
    L_0x015b:
        if (r10 == 0) goto L_0x02c9;
    L_0x015d:
        r10.abort();
        r6 = 0;
    L_0x0161:
        if (r5 == 0) goto L_0x02c6;
    L_0x0163:
        r5 = r5.getConnectionManager();
        r5.shutdown();
        r7 = 0;
    L_0x016b:
        if (r9 == 0) goto L_0x02c3;
    L_0x016d:
        r9.close();
        r5 = 0;
    L_0x0171:
        if (r2 == 0) goto L_0x0177;
    L_0x0173:
        r2.close();
        r2 = 0;
    L_0x0177:
        if (r1 == 0) goto L_0x017d;
    L_0x0179:
        r1.close();
        r1 = 0;
    L_0x017d:
        r15 = r3;
        r3 = r1;
        r1 = r15;
        r16 = r4;
        r4 = r2;
        r2 = r16;
        goto L_0x00fe;
    L_0x0187:
        r6 = move-exception;
        r15 = r8;
        r8 = r5;
        r5 = r2;
        r2 = r15;
    L_0x018c:
        if (r10 == 0) goto L_0x02c0;
    L_0x018e:
        r10.abort();
        r6 = 0;
    L_0x0192:
        if (r5 == 0) goto L_0x02bd;
    L_0x0194:
        r5 = r5.getConnectionManager();
        r5.shutdown();
        r7 = 0;
    L_0x019c:
        if (r9 == 0) goto L_0x02ba;
    L_0x019e:
        r9.close();
        r5 = 0;
    L_0x01a2:
        if (r2 == 0) goto L_0x01a8;
    L_0x01a4:
        r2.close();
        r2 = 0;
    L_0x01a8:
        if (r1 == 0) goto L_0x01ae;
    L_0x01aa:
        r1.close();
        r1 = 0;
    L_0x01ae:
        r15 = r3;
        r3 = r1;
        r1 = r15;
        r16 = r4;
        r4 = r2;
        r2 = r16;
        goto L_0x00fe;
    L_0x01b8:
        r6 = move-exception;
        r15 = r8;
        r8 = r5;
        r5 = r2;
        r2 = r15;
    L_0x01bd:
        if (r10 == 0) goto L_0x02b7;
    L_0x01bf:
        r10.abort();
        r6 = 0;
    L_0x01c3:
        if (r5 == 0) goto L_0x02b4;
    L_0x01c5:
        r5 = r5.getConnectionManager();
        r5.shutdown();
        r7 = 0;
    L_0x01cd:
        if (r9 == 0) goto L_0x02b1;
    L_0x01cf:
        r9.close();
        r5 = 0;
    L_0x01d3:
        if (r2 == 0) goto L_0x01d9;
    L_0x01d5:
        r2.close();
        r2 = 0;
    L_0x01d9:
        if (r1 == 0) goto L_0x01df;
    L_0x01db:
        r1.close();
        r1 = 0;
    L_0x01df:
        r15 = r3;
        r3 = r1;
        r1 = r15;
        r16 = r4;
        r4 = r2;
        r2 = r16;
        goto L_0x00fe;
    L_0x01e9:
        r3 = move-exception;
        r6 = r1;
        r7 = r8;
        r1 = r3;
        r8 = r9;
    L_0x01ee:
        if (r10 == 0) goto L_0x01f3;
    L_0x01f0:
        r10.abort();
    L_0x01f3:
        if (r2 == 0) goto L_0x01fc;
    L_0x01f5:
        r2 = r2.getConnectionManager();
        r2.shutdown();
    L_0x01fc:
        if (r8 == 0) goto L_0x0201;
    L_0x01fe:
        r8.close();
    L_0x0201:
        if (r7 == 0) goto L_0x0206;
    L_0x0203:
        r7.close();
    L_0x0206:
        if (r6 == 0) goto L_0x020b;
    L_0x0208:
        r6.close();
    L_0x020b:
        throw r1;
    L_0x020c:
        r1 = r5;
        goto L_0x000e;
    L_0x020f:
        r3 = move-exception;
        r10 = r1;
        r1 = r3;
        goto L_0x01ee;
    L_0x0213:
        r1 = move-exception;
        goto L_0x01ee;
    L_0x0215:
        r1 = move-exception;
        r8 = r9;
        goto L_0x01ee;
    L_0x0218:
        r1 = move-exception;
        r7 = r8;
        r8 = r9;
        goto L_0x01ee;
    L_0x021c:
        r9 = move-exception;
        r9 = r8;
        r10 = r1;
        r1 = r6;
        r8 = r5;
        r5 = r2;
        r2 = r7;
        goto L_0x01bd;
    L_0x0224:
        r1 = move-exception;
        r1 = r6;
        r9 = r8;
        r8 = r5;
        r5 = r2;
        r2 = r7;
        goto L_0x01bd;
    L_0x022b:
        r1 = move-exception;
        r1 = r6;
        r8 = r5;
        r5 = r2;
        r2 = r7;
        goto L_0x01bd;
    L_0x0231:
        r1 = move-exception;
        r1 = r6;
        r15 = r8;
        r8 = r5;
        r5 = r2;
        r2 = r15;
        goto L_0x01bd;
    L_0x0238:
        r9 = move-exception;
        r9 = r8;
        r10 = r1;
        r1 = r6;
        r8 = r5;
        r5 = r2;
        r2 = r7;
        goto L_0x018c;
    L_0x0241:
        r1 = move-exception;
        r1 = r6;
        r9 = r8;
        r8 = r5;
        r5 = r2;
        r2 = r7;
        goto L_0x018c;
    L_0x0249:
        r1 = move-exception;
        r1 = r6;
        r8 = r5;
        r5 = r2;
        r2 = r7;
        goto L_0x018c;
    L_0x0250:
        r1 = move-exception;
        r1 = r6;
        r15 = r8;
        r8 = r5;
        r5 = r2;
        r2 = r15;
        goto L_0x018c;
    L_0x0258:
        r9 = move-exception;
        r9 = r8;
        r10 = r1;
        r1 = r6;
        r8 = r5;
        r5 = r2;
        r2 = r7;
        goto L_0x015b;
    L_0x0261:
        r1 = move-exception;
        r1 = r6;
        r9 = r8;
        r8 = r5;
        r5 = r2;
        r2 = r7;
        goto L_0x015b;
    L_0x0269:
        r1 = move-exception;
        r1 = r6;
        r8 = r5;
        r5 = r2;
        r2 = r7;
        goto L_0x015b;
    L_0x0270:
        r1 = move-exception;
        r1 = r6;
        r15 = r8;
        r8 = r5;
        r5 = r2;
        r2 = r15;
        goto L_0x015b;
    L_0x0278:
        r9 = move-exception;
        r15 = r6;
        r6 = r1;
        r1 = r15;
        r16 = r8;
        r8 = r5;
        r5 = r16;
        r17 = r2;
        r2 = r7;
        r7 = r17;
        goto L_0x00d4;
    L_0x0288:
        r1 = move-exception;
        r1 = r6;
        r6 = r10;
        r15 = r8;
        r8 = r5;
        r5 = r15;
        r16 = r2;
        r2 = r7;
        r7 = r16;
        goto L_0x00d4;
    L_0x0295:
        r1 = move-exception;
        r1 = r6;
        r8 = r5;
        r5 = r9;
        r6 = r10;
        r15 = r2;
        r2 = r7;
        r7 = r15;
        goto L_0x00d4;
    L_0x029f:
        r1 = move-exception;
        r1 = r6;
        r7 = r2;
        r6 = r10;
        r2 = r8;
        r8 = r5;
        r5 = r9;
        goto L_0x00d4;
    L_0x02a8:
        r2 = r9;
        goto L_0x0152;
    L_0x02ab:
        r7 = r2;
        goto L_0x0140;
    L_0x02ae:
        r6 = r10;
        goto L_0x0136;
    L_0x02b1:
        r5 = r9;
        goto L_0x01d3;
    L_0x02b4:
        r7 = r5;
        goto L_0x01cd;
    L_0x02b7:
        r6 = r10;
        goto L_0x01c3;
    L_0x02ba:
        r5 = r9;
        goto L_0x01a2;
    L_0x02bd:
        r7 = r5;
        goto L_0x019c;
    L_0x02c0:
        r6 = r10;
        goto L_0x0192;
    L_0x02c3:
        r5 = r9;
        goto L_0x0171;
    L_0x02c6:
        r7 = r5;
        goto L_0x016b;
    L_0x02c9:
        r6 = r10;
        goto L_0x0161;
    L_0x02cc:
        r1 = r3;
        r9 = r6;
        r3 = r4;
        r4 = r7;
        r15 = r8;
        r8 = r5;
        r5 = r15;
        goto L_0x0130;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aps.NetManager.mo13280a(byte[], android.content.Context):java.lang.String");
    }
}
