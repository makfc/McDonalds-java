package com.amap.api.services.core;

import android.content.Context;
import android.net.Proxy;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpHost;

/* compiled from: ProxyUtil */
/* renamed from: com.amap.api.services.core.ab */
public class C1078ab {
    /* renamed from: a */
    public static HttpHost m4690a(Context context) {
        HttpHost httpHost = null;
        try {
            URI uri = new URI("http://restapi.amap.com");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            return C1078ab.m4693b(context);
        } catch (Throwable th) {
            C1099ax.m4800a(th, "ProxyUtil", "getProxy");
            th.printStackTrace();
            return httpHost;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0062 A:{SYNTHETIC, Splitter:B:28:0x0062} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0062 A:{SYNTHETIC, Splitter:B:28:0x0062} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x0157 A:{SYNTHETIC, Splitter:B:104:0x0157} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x0157 A:{SYNTHETIC, Splitter:B:104:0x0157} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x013b A:{SYNTHETIC, Splitter:B:94:0x013b} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x01b3  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00c2 A:{Catch:{ all -> 0x0170 }} */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00f2 A:{SYNTHETIC, Splitter:B:71:0x00f2} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x013b A:{SYNTHETIC, Splitter:B:94:0x013b} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00c2 A:{Catch:{ all -> 0x0170 }} */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x01b3  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00f2 A:{SYNTHETIC, Splitter:B:71:0x00f2} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x013b A:{SYNTHETIC, Splitter:B:94:0x013b} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x01b3  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00c2 A:{Catch:{ all -> 0x0170 }} */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00f2 A:{SYNTHETIC, Splitter:B:71:0x00f2} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x013b A:{SYNTHETIC, Splitter:B:94:0x013b} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00c2 A:{Catch:{ all -> 0x0170 }} */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x01b3  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00f2 A:{SYNTHETIC, Splitter:B:71:0x00f2} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x013b A:{SYNTHETIC, Splitter:B:94:0x013b} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006b  */
    /* renamed from: b */
    private static org.apache.http.HttpHost m4693b(android.content.Context r12) {
        /*
        r10 = 0;
        r6 = 80;
        r9 = 1;
        r8 = -1;
        r7 = 0;
        r0 = com.amap.api.services.core.C1138y.m5109g(r12);
        if (r0 != 0) goto L_0x016d;
    L_0x000c:
        r0 = "content://telephony/carriers/preferapn";
        r1 = android.net.Uri.parse(r0);
        r0 = r12.getContentResolver();
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r2 = r0.query(r1, r2, r3, r4, r5);	 Catch:{ SecurityException -> 0x00b1, Exception -> 0x012a, all -> 0x0153 }
        if (r2 == 0) goto L_0x01c3;
    L_0x0020:
        r0 = r2.moveToFirst();	 Catch:{ SecurityException -> 0x0193, Exception -> 0x0175 }
        if (r0 == 0) goto L_0x01c3;
    L_0x0026:
        r0 = "apn";
        r0 = r2.getColumnIndex(r0);	 Catch:{ SecurityException -> 0x0193, Exception -> 0x0175 }
        r0 = r2.getString(r0);	 Catch:{ SecurityException -> 0x0193, Exception -> 0x0175 }
        if (r0 == 0) goto L_0x0038;
    L_0x0032:
        r1 = java.util.Locale.US;	 Catch:{ SecurityException -> 0x0193, Exception -> 0x0175 }
        r0 = r0.toLowerCase(r1);	 Catch:{ SecurityException -> 0x0193, Exception -> 0x0175 }
    L_0x0038:
        if (r0 == 0) goto L_0x0074;
    L_0x003a:
        r1 = "ctwap";
        r1 = r0.contains(r1);	 Catch:{ SecurityException -> 0x0193, Exception -> 0x0175 }
        if (r1 == 0) goto L_0x0074;
    L_0x0042:
        r3 = com.amap.api.services.core.C1078ab.m4689a();	 Catch:{ SecurityException -> 0x0193, Exception -> 0x0175 }
        r0 = com.amap.api.services.core.C1078ab.m4692b();	 Catch:{ SecurityException -> 0x0193, Exception -> 0x0175 }
        r1 = android.text.TextUtils.isEmpty(r3);	 Catch:{ SecurityException -> 0x0198, Exception -> 0x017b }
        if (r1 != 0) goto L_0x01ca;
    L_0x0050:
        r1 = "null";
        r1 = r3.equals(r1);	 Catch:{ SecurityException -> 0x0198, Exception -> 0x017b }
        if (r1 != 0) goto L_0x01ca;
    L_0x0058:
        r1 = r9;
    L_0x0059:
        if (r1 != 0) goto L_0x01c7;
    L_0x005b:
        r1 = "10.0.0.200";
    L_0x005d:
        if (r0 != r8) goto L_0x0060;
    L_0x005f:
        r0 = r6;
    L_0x0060:
        if (r2 == 0) goto L_0x0065;
    L_0x0062:
        r2.close();	 Catch:{ Throwable -> 0x009f }
    L_0x0065:
        r2 = com.amap.api.services.core.C1078ab.m4691a(r1, r0);
        if (r2 == 0) goto L_0x016d;
    L_0x006b:
        r3 = "http";
        r2 = new org.apache.http.HttpHost;
        r2.<init>(r1, r0, r3);
        r0 = r2;
    L_0x0073:
        return r0;
    L_0x0074:
        if (r0 == 0) goto L_0x01c3;
    L_0x0076:
        r1 = "wap";
        r0 = r0.contains(r1);	 Catch:{ SecurityException -> 0x0193, Exception -> 0x0175 }
        if (r0 == 0) goto L_0x01c3;
    L_0x007f:
        r3 = com.amap.api.services.core.C1078ab.m4689a();	 Catch:{ SecurityException -> 0x0193, Exception -> 0x0175 }
        r1 = com.amap.api.services.core.C1078ab.m4692b();	 Catch:{ SecurityException -> 0x0193, Exception -> 0x0175 }
        r0 = android.text.TextUtils.isEmpty(r3);	 Catch:{ SecurityException -> 0x01a5, Exception -> 0x0186 }
        if (r0 != 0) goto L_0x01bf;
    L_0x008d:
        r0 = "null";
        r0 = r3.equals(r0);	 Catch:{ SecurityException -> 0x01a5, Exception -> 0x0186 }
        if (r0 != 0) goto L_0x01bf;
    L_0x0095:
        r0 = r9;
    L_0x0096:
        if (r0 != 0) goto L_0x01bc;
    L_0x0098:
        r0 = "10.0.0.172";
    L_0x009a:
        if (r1 != r8) goto L_0x01b7;
    L_0x009c:
        r1 = r0;
        r0 = r6;
        goto L_0x0060;
    L_0x009f:
        r2 = move-exception;
        r3 = com.amap.api.services.core.C1099ax.m4801b();
        if (r3 == 0) goto L_0x00ad;
    L_0x00a6:
        r4 = "ProxyUtil";
        r5 = "getHostProxy2";
        r3.mo12039b(r2, r4, r5);
    L_0x00ad:
        r2.printStackTrace();
        goto L_0x0065;
    L_0x00b1:
        r0 = move-exception;
        r2 = r7;
        r1 = r8;
        r3 = r7;
    L_0x00b5:
        r4 = "ProxyUtil";
        r5 = "getHostProxy";
        com.amap.api.services.core.C1099ax.m4800a(r0, r4, r5);	 Catch:{ all -> 0x0170 }
        r0 = com.amap.api.services.core.C1138y.m5111i(r12);	 Catch:{ all -> 0x0170 }
        if (r0 == 0) goto L_0x01b3;
    L_0x00c2:
        r1 = java.util.Locale.US;	 Catch:{ all -> 0x0170 }
        r4 = r0.toLowerCase(r1);	 Catch:{ all -> 0x0170 }
        r1 = com.amap.api.services.core.C1078ab.m4689a();	 Catch:{ all -> 0x0170 }
        r0 = com.amap.api.services.core.C1078ab.m4692b();	 Catch:{ all -> 0x0170 }
        r5 = "ctwap";
        r5 = r4.indexOf(r5);	 Catch:{ all -> 0x0170 }
        if (r5 == r8) goto L_0x010a;
    L_0x00d8:
        r4 = android.text.TextUtils.isEmpty(r1);	 Catch:{ all -> 0x0170 }
        if (r4 != 0) goto L_0x00e8;
    L_0x00de:
        r4 = "null";
        r4 = r1.equals(r4);	 Catch:{ all -> 0x0170 }
        if (r4 != 0) goto L_0x00e8;
    L_0x00e6:
        r10 = r9;
        r3 = r1;
    L_0x00e8:
        if (r10 != 0) goto L_0x00ec;
    L_0x00ea:
        r3 = "10.0.0.200";
    L_0x00ec:
        if (r0 != r8) goto L_0x01b0;
    L_0x00ee:
        r0 = r6;
        r1 = r3;
    L_0x00f0:
        if (r2 == 0) goto L_0x0065;
    L_0x00f2:
        r2.close();	 Catch:{ Throwable -> 0x00f7 }
        goto L_0x0065;
    L_0x00f7:
        r2 = move-exception;
        r3 = com.amap.api.services.core.C1099ax.m4801b();
        if (r3 == 0) goto L_0x0105;
    L_0x00fe:
        r4 = "ProxyUtil";
        r5 = "getHostProxy2";
        r3.mo12039b(r2, r4, r5);
    L_0x0105:
        r2.printStackTrace();
        goto L_0x0065;
    L_0x010a:
        r5 = "wap";
        r4 = r4.indexOf(r5);	 Catch:{ all -> 0x0170 }
        if (r4 == r8) goto L_0x01b0;
    L_0x0113:
        r0 = android.text.TextUtils.isEmpty(r1);	 Catch:{ all -> 0x0170 }
        if (r0 != 0) goto L_0x01ac;
    L_0x0119:
        r0 = "null";
        r0 = r1.equals(r0);	 Catch:{ all -> 0x0170 }
        if (r0 != 0) goto L_0x01ac;
    L_0x0121:
        r0 = r1;
        r1 = r9;
    L_0x0123:
        if (r1 != 0) goto L_0x0127;
    L_0x0125:
        r0 = "10.0.0.200";
    L_0x0127:
        r1 = r0;
        r0 = r6;
        goto L_0x00f0;
    L_0x012a:
        r0 = move-exception;
        r2 = r0;
        r3 = r7;
        r1 = r7;
        r0 = r8;
    L_0x012f:
        r4 = "ProxyUtil";
        r5 = "getHostProxy1";
        com.amap.api.services.core.C1099ax.m4800a(r2, r4, r5);	 Catch:{ all -> 0x0172 }
        r2.printStackTrace();	 Catch:{ all -> 0x0172 }
        if (r3 == 0) goto L_0x0065;
    L_0x013b:
        r3.close();	 Catch:{ Throwable -> 0x0140 }
        goto L_0x0065;
    L_0x0140:
        r2 = move-exception;
        r3 = com.amap.api.services.core.C1099ax.m4801b();
        if (r3 == 0) goto L_0x014e;
    L_0x0147:
        r4 = "ProxyUtil";
        r5 = "getHostProxy2";
        r3.mo12039b(r2, r4, r5);
    L_0x014e:
        r2.printStackTrace();
        goto L_0x0065;
    L_0x0153:
        r0 = move-exception;
        r2 = r7;
    L_0x0155:
        if (r2 == 0) goto L_0x015a;
    L_0x0157:
        r2.close();	 Catch:{ Throwable -> 0x015b }
    L_0x015a:
        throw r0;
    L_0x015b:
        r1 = move-exception;
        r2 = com.amap.api.services.core.C1099ax.m4801b();
        if (r2 == 0) goto L_0x0169;
    L_0x0162:
        r3 = "ProxyUtil";
        r4 = "getHostProxy2";
        r2.mo12039b(r1, r3, r4);
    L_0x0169:
        r1.printStackTrace();
        goto L_0x015a;
    L_0x016d:
        r0 = r7;
        goto L_0x0073;
    L_0x0170:
        r0 = move-exception;
        goto L_0x0155;
    L_0x0172:
        r0 = move-exception;
        r2 = r3;
        goto L_0x0155;
    L_0x0175:
        r0 = move-exception;
        r3 = r2;
        r1 = r7;
        r2 = r0;
        r0 = r8;
        goto L_0x012f;
    L_0x017b:
        r1 = move-exception;
        r3 = r2;
        r2 = r1;
        r1 = r7;
        goto L_0x012f;
    L_0x0180:
        r1 = move-exception;
        r11 = r1;
        r1 = r3;
        r3 = r2;
        r2 = r11;
        goto L_0x012f;
    L_0x0186:
        r0 = move-exception;
        r3 = r2;
        r2 = r0;
        r0 = r1;
        r1 = r7;
        goto L_0x012f;
    L_0x018c:
        r0 = move-exception;
        r11 = r0;
        r0 = r1;
        r1 = r3;
        r3 = r2;
        r2 = r11;
        goto L_0x012f;
    L_0x0193:
        r0 = move-exception;
        r1 = r8;
        r3 = r7;
        goto L_0x00b5;
    L_0x0198:
        r1 = move-exception;
        r3 = r7;
        r11 = r0;
        r0 = r1;
        r1 = r11;
        goto L_0x00b5;
    L_0x019f:
        r1 = move-exception;
        r11 = r1;
        r1 = r0;
        r0 = r11;
        goto L_0x00b5;
    L_0x01a5:
        r0 = move-exception;
        r3 = r7;
        goto L_0x00b5;
    L_0x01a9:
        r0 = move-exception;
        goto L_0x00b5;
    L_0x01ac:
        r1 = r10;
        r0 = r3;
        goto L_0x0123;
    L_0x01b0:
        r1 = r3;
        goto L_0x00f0;
    L_0x01b3:
        r0 = r1;
        r1 = r3;
        goto L_0x00f0;
    L_0x01b7:
        r11 = r1;
        r1 = r0;
        r0 = r11;
        goto L_0x0060;
    L_0x01bc:
        r0 = r3;
        goto L_0x009a;
    L_0x01bf:
        r0 = r10;
        r3 = r7;
        goto L_0x0096;
    L_0x01c3:
        r0 = r8;
        r1 = r7;
        goto L_0x0060;
    L_0x01c7:
        r1 = r3;
        goto L_0x005d;
    L_0x01ca:
        r1 = r10;
        r3 = r7;
        goto L_0x0059;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.core.C1078ab.m4693b(android.content.Context):org.apache.http.HttpHost");
    }

    /* renamed from: a */
    private static boolean m4691a(String str, int i) {
        return (str == null || str.length() <= 0 || i == -1) ? false : true;
    }

    /* renamed from: a */
    private static String m4689a() {
        String defaultHost;
        try {
            defaultHost = Proxy.getDefaultHost();
        } catch (Throwable th) {
            th.printStackTrace();
            C1099ax.m4800a(th, "ProxyUtil", "getDefHost");
            defaultHost = null;
        }
        if (defaultHost == null) {
            return SafeJsonPrimitive.NULL_STRING;
        }
        return defaultHost;
    }

    /* renamed from: b */
    private static int m4692b() {
        int i = -1;
        try {
            return Proxy.getDefaultPort();
        } catch (Throwable th) {
            C1099ax.m4800a(th, "ProxyUtil", "getDefPort");
            th.printStackTrace();
            return i;
        }
    }
}
