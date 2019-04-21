package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Build.VERSION;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.ProxySelector;
import java.net.URI;
import java.util.List;

/* renamed from: com.amap.api.mapcore.util.dt */
public class ProxyUtil {
    /* renamed from: a */
    public static Proxy m2471a(Context context) {
        try {
            if (VERSION.SDK_INT >= 11) {
                return ProxyUtil.m2472a(context, new URI("http://restapi.amap.com"));
            }
            return ProxyUtil.m2475b(context);
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "ProxyUtil", "getProxy");
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0063 A:{SYNTHETIC, Splitter:B:29:0x0063} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006c A:{Catch:{ Throwable -> 0x0154 }} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0063 A:{SYNTHETIC, Splitter:B:29:0x0063} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006c A:{Catch:{ Throwable -> 0x0154 }} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006c A:{Catch:{ Throwable -> 0x0154 }} */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00ee A:{SYNTHETIC, Splitter:B:70:0x00ee} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006c A:{Catch:{ Throwable -> 0x0154 }} */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00ee A:{SYNTHETIC, Splitter:B:70:0x00ee} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006c A:{Catch:{ Throwable -> 0x0154 }} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006c A:{Catch:{ Throwable -> 0x0154 }} */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0144 A:{SYNTHETIC, Splitter:B:97:0x0144} */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00bf A:{Catch:{ all -> 0x0164 }} */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00ee A:{SYNTHETIC, Splitter:B:70:0x00ee} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006c A:{Catch:{ Throwable -> 0x0154 }} */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x012e A:{SYNTHETIC, Splitter:B:90:0x012e} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006c A:{Catch:{ Throwable -> 0x0154 }} */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00bf A:{Catch:{ all -> 0x0164 }} */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00ee A:{SYNTHETIC, Splitter:B:70:0x00ee} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006c A:{Catch:{ Throwable -> 0x0154 }} */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x012e A:{SYNTHETIC, Splitter:B:90:0x012e} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006c A:{Catch:{ Throwable -> 0x0154 }} */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x012e A:{SYNTHETIC, Splitter:B:90:0x012e} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006c A:{Catch:{ Throwable -> 0x0154 }} */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0144 A:{SYNTHETIC, Splitter:B:97:0x0144} */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00bf A:{Catch:{ all -> 0x0164 }} */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00ee A:{SYNTHETIC, Splitter:B:70:0x00ee} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006c A:{Catch:{ Throwable -> 0x0154 }} */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x012e A:{SYNTHETIC, Splitter:B:90:0x012e} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006c A:{Catch:{ Throwable -> 0x0154 }} */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00bf A:{Catch:{ all -> 0x0164 }} */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00ee A:{SYNTHETIC, Splitter:B:70:0x00ee} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006c A:{Catch:{ Throwable -> 0x0154 }} */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x012e A:{SYNTHETIC, Splitter:B:90:0x012e} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006c A:{Catch:{ Throwable -> 0x0154 }} */
    /* renamed from: b */
    private static java.net.Proxy m2475b(android.content.Context r12) {
        /*
        r10 = 0;
        r6 = 80;
        r9 = 1;
        r8 = -1;
        r7 = 0;
        r0 = com.amap.api.mapcore.util.ProxyUtil.m2476c(r12);
        if (r0 == 0) goto L_0x015f;
    L_0x000c:
        r0 = "content://telephony/carriers/preferapn";
        r1 = android.net.Uri.parse(r0);
        r0 = r12.getContentResolver();
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r2 = r0.query(r1, r2, r3, r4, r5);	 Catch:{ SecurityException -> 0x00ae, Throwable -> 0x011f, all -> 0x0140 }
        if (r2 == 0) goto L_0x01aa;
    L_0x0020:
        r0 = r2.moveToFirst();	 Catch:{ SecurityException -> 0x017a, Throwable -> 0x0167 }
        if (r0 == 0) goto L_0x01aa;
    L_0x0026:
        r0 = "apn";
        r0 = r2.getColumnIndex(r0);	 Catch:{ SecurityException -> 0x017a, Throwable -> 0x0167 }
        r0 = r2.getString(r0);	 Catch:{ SecurityException -> 0x017a, Throwable -> 0x0167 }
        if (r0 == 0) goto L_0x0038;
    L_0x0032:
        r1 = java.util.Locale.US;	 Catch:{ SecurityException -> 0x017a, Throwable -> 0x0167 }
        r0 = r0.toLowerCase(r1);	 Catch:{ SecurityException -> 0x017a, Throwable -> 0x0167 }
    L_0x0038:
        if (r0 == 0) goto L_0x0078;
    L_0x003a:
        r1 = "ctwap";
        r1 = r0.contains(r1);	 Catch:{ SecurityException -> 0x017a, Throwable -> 0x0167 }
        if (r1 == 0) goto L_0x0078;
    L_0x0042:
        r3 = com.amap.api.mapcore.util.ProxyUtil.m2470a();	 Catch:{ SecurityException -> 0x017a, Throwable -> 0x0167 }
        r0 = com.amap.api.mapcore.util.ProxyUtil.m2474b();	 Catch:{ SecurityException -> 0x017a, Throwable -> 0x0167 }
        r1 = android.text.TextUtils.isEmpty(r3);	 Catch:{ SecurityException -> 0x0180, Throwable -> 0x016a }
        if (r1 != 0) goto L_0x01ad;
    L_0x0050:
        r1 = "null";
        r1 = r3.equals(r1);	 Catch:{ SecurityException -> 0x0180, Throwable -> 0x016a }
        if (r1 != 0) goto L_0x01ad;
    L_0x0058:
        r1 = r9;
    L_0x0059:
        if (r1 != 0) goto L_0x005d;
    L_0x005b:
        r3 = "10.0.0.200";
    L_0x005d:
        if (r0 != r8) goto L_0x0060;
    L_0x005f:
        r0 = r6;
    L_0x0060:
        r8 = r0;
    L_0x0061:
        if (r2 == 0) goto L_0x0066;
    L_0x0063:
        r2.close();	 Catch:{ Throwable -> 0x00a2 }
    L_0x0066:
        r0 = com.amap.api.mapcore.util.ProxyUtil.m2473a(r3, r8);	 Catch:{ Throwable -> 0x0154 }
        if (r0 == 0) goto L_0x015f;
    L_0x006c:
        r0 = new java.net.Proxy;	 Catch:{ Throwable -> 0x0154 }
        r1 = java.net.Proxy.Type.HTTP;	 Catch:{ Throwable -> 0x0154 }
        r2 = java.net.InetSocketAddress.createUnresolved(r3, r8);	 Catch:{ Throwable -> 0x0154 }
        r0.<init>(r1, r2);	 Catch:{ Throwable -> 0x0154 }
    L_0x0077:
        return r0;
    L_0x0078:
        if (r0 == 0) goto L_0x01aa;
    L_0x007a:
        r1 = "wap";
        r0 = r0.contains(r1);	 Catch:{ SecurityException -> 0x017a, Throwable -> 0x0167 }
        if (r0 == 0) goto L_0x01aa;
    L_0x0083:
        r3 = com.amap.api.mapcore.util.ProxyUtil.m2470a();	 Catch:{ SecurityException -> 0x017a, Throwable -> 0x0167 }
        r1 = com.amap.api.mapcore.util.ProxyUtil.m2474b();	 Catch:{ SecurityException -> 0x017a, Throwable -> 0x0167 }
        r0 = android.text.TextUtils.isEmpty(r3);	 Catch:{ SecurityException -> 0x018f, Throwable -> 0x0173 }
        if (r0 != 0) goto L_0x01a6;
    L_0x0091:
        r0 = "null";
        r0 = r3.equals(r0);	 Catch:{ SecurityException -> 0x018f, Throwable -> 0x0173 }
        if (r0 != 0) goto L_0x01a6;
    L_0x0099:
        r0 = r9;
    L_0x009a:
        if (r0 != 0) goto L_0x009e;
    L_0x009c:
        r3 = "10.0.0.172";
    L_0x009e:
        if (r1 != r8) goto L_0x01a3;
    L_0x00a0:
        r8 = r6;
        goto L_0x0061;
    L_0x00a2:
        r0 = move-exception;
        r1 = "ProxyUtil";
        r2 = "getHostProxy2";
        com.amap.api.mapcore.util.BasicLogHandler.m2542a(r0, r1, r2);
        r0.printStackTrace();
        goto L_0x0066;
    L_0x00ae:
        r0 = move-exception;
        r1 = r7;
        r2 = r8;
        r3 = r7;
    L_0x00b2:
        r4 = "ProxyUtil";
        r5 = "getHostProxy";
        com.amap.api.mapcore.util.BasicLogHandler.m2542a(r0, r4, r5);	 Catch:{ all -> 0x0164 }
        r0 = com.amap.api.mapcore.util.C0820dq.m2441o(r12);	 Catch:{ all -> 0x0164 }
        if (r0 == 0) goto L_0x01a0;
    L_0x00bf:
        r2 = java.util.Locale.US;	 Catch:{ all -> 0x0164 }
        r4 = r0.toLowerCase(r2);	 Catch:{ all -> 0x0164 }
        r0 = com.amap.api.mapcore.util.ProxyUtil.m2470a();	 Catch:{ all -> 0x0164 }
        r2 = com.amap.api.mapcore.util.ProxyUtil.m2474b();	 Catch:{ all -> 0x0164 }
        r5 = "ctwap";
        r5 = r4.indexOf(r5);	 Catch:{ all -> 0x0164 }
        if (r5 == r8) goto L_0x0100;
    L_0x00d5:
        r4 = android.text.TextUtils.isEmpty(r0);	 Catch:{ all -> 0x0164 }
        if (r4 != 0) goto L_0x00e5;
    L_0x00db:
        r4 = "null";
        r4 = r0.equals(r4);	 Catch:{ all -> 0x0164 }
        if (r4 != 0) goto L_0x00e5;
    L_0x00e3:
        r10 = r9;
        r3 = r0;
    L_0x00e5:
        if (r10 != 0) goto L_0x00e9;
    L_0x00e7:
        r3 = "10.0.0.200";
    L_0x00e9:
        if (r2 != r8) goto L_0x01a0;
    L_0x00eb:
        r8 = r6;
    L_0x00ec:
        if (r1 == 0) goto L_0x0066;
    L_0x00ee:
        r1.close();	 Catch:{ Throwable -> 0x00f3 }
        goto L_0x0066;
    L_0x00f3:
        r0 = move-exception;
        r1 = "ProxyUtil";
        r2 = "getHostProxy2";
        com.amap.api.mapcore.util.BasicLogHandler.m2542a(r0, r1, r2);
        r0.printStackTrace();
        goto L_0x0066;
    L_0x0100:
        r5 = "wap";
        r4 = r4.indexOf(r5);	 Catch:{ all -> 0x0164 }
        if (r4 == r8) goto L_0x01a0;
    L_0x0109:
        r2 = android.text.TextUtils.isEmpty(r0);	 Catch:{ all -> 0x0164 }
        if (r2 != 0) goto L_0x019c;
    L_0x010f:
        r2 = "null";
        r2 = r0.equals(r2);	 Catch:{ all -> 0x0164 }
        if (r2 != 0) goto L_0x019c;
    L_0x0117:
        r2 = r9;
    L_0x0118:
        if (r2 != 0) goto L_0x011c;
    L_0x011a:
        r0 = "10.0.0.200";
    L_0x011c:
        r8 = r6;
        r3 = r0;
        goto L_0x00ec;
    L_0x011f:
        r0 = move-exception;
        r2 = r7;
        r3 = r7;
    L_0x0122:
        r1 = "ProxyUtil";
        r4 = "getHostProxy1";
        com.amap.api.mapcore.util.BasicLogHandler.m2542a(r0, r1, r4);	 Catch:{ all -> 0x0162 }
        r0.printStackTrace();	 Catch:{ all -> 0x0162 }
        if (r2 == 0) goto L_0x0066;
    L_0x012e:
        r2.close();	 Catch:{ Throwable -> 0x0133 }
        goto L_0x0066;
    L_0x0133:
        r0 = move-exception;
        r1 = "ProxyUtil";
        r2 = "getHostProxy2";
        com.amap.api.mapcore.util.BasicLogHandler.m2542a(r0, r1, r2);
        r0.printStackTrace();
        goto L_0x0066;
    L_0x0140:
        r0 = move-exception;
        r2 = r7;
    L_0x0142:
        if (r2 == 0) goto L_0x0147;
    L_0x0144:
        r2.close();	 Catch:{ Throwable -> 0x0148 }
    L_0x0147:
        throw r0;
    L_0x0148:
        r1 = move-exception;
        r2 = "ProxyUtil";
        r3 = "getHostProxy2";
        com.amap.api.mapcore.util.BasicLogHandler.m2542a(r1, r2, r3);
        r1.printStackTrace();
        goto L_0x0147;
    L_0x0154:
        r0 = move-exception;
        r1 = "ProxyUtil";
        r2 = "getHostProxy2";
        com.amap.api.mapcore.util.BasicLogHandler.m2542a(r0, r1, r2);
        r0.printStackTrace();
    L_0x015f:
        r0 = r7;
        goto L_0x0077;
    L_0x0162:
        r0 = move-exception;
        goto L_0x0142;
    L_0x0164:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0142;
    L_0x0167:
        r0 = move-exception;
        r3 = r7;
        goto L_0x0122;
    L_0x016a:
        r1 = move-exception;
        r8 = r0;
        r3 = r7;
        r0 = r1;
        goto L_0x0122;
    L_0x016f:
        r1 = move-exception;
        r8 = r0;
        r0 = r1;
        goto L_0x0122;
    L_0x0173:
        r0 = move-exception;
        r8 = r1;
        r3 = r7;
        goto L_0x0122;
    L_0x0177:
        r0 = move-exception;
        r8 = r1;
        goto L_0x0122;
    L_0x017a:
        r0 = move-exception;
        r1 = r2;
        r3 = r7;
        r2 = r8;
        goto L_0x00b2;
    L_0x0180:
        r1 = move-exception;
        r3 = r7;
        r11 = r2;
        r2 = r0;
        r0 = r1;
        r1 = r11;
        goto L_0x00b2;
    L_0x0188:
        r1 = move-exception;
        r11 = r1;
        r1 = r2;
        r2 = r0;
        r0 = r11;
        goto L_0x00b2;
    L_0x018f:
        r0 = move-exception;
        r3 = r7;
        r11 = r1;
        r1 = r2;
        r2 = r11;
        goto L_0x00b2;
    L_0x0196:
        r0 = move-exception;
        r11 = r2;
        r2 = r1;
        r1 = r11;
        goto L_0x00b2;
    L_0x019c:
        r2 = r10;
        r0 = r3;
        goto L_0x0118;
    L_0x01a0:
        r8 = r2;
        goto L_0x00ec;
    L_0x01a3:
        r8 = r1;
        goto L_0x0061;
    L_0x01a6:
        r0 = r10;
        r3 = r7;
        goto L_0x009a;
    L_0x01aa:
        r3 = r7;
        goto L_0x0061;
    L_0x01ad:
        r1 = r10;
        r3 = r7;
        goto L_0x0059;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.ProxyUtil.m2475b(android.content.Context):java.net.Proxy");
    }

    /* renamed from: a */
    private static boolean m2473a(String str, int i) {
        return (str == null || str.length() <= 0 || i == -1) ? false : true;
    }

    /* renamed from: a */
    private static String m2470a() {
        String defaultHost;
        try {
            defaultHost = android.net.Proxy.getDefaultHost();
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "ProxyUtil", "getDefHost");
            defaultHost = null;
        }
        if (defaultHost == null) {
            return SafeJsonPrimitive.NULL_STRING;
        }
        return defaultHost;
    }

    /* renamed from: a */
    private static Proxy m2472a(Context context, URI uri) {
        if (ProxyUtil.m2476c(context)) {
            try {
                List select = ProxySelector.getDefault().select(uri);
                if (select == null || select.isEmpty()) {
                    return null;
                }
                Proxy proxy = (Proxy) select.get(0);
                return (proxy == null || proxy.type() == Type.DIRECT) ? null : proxy;
            } catch (Throwable th) {
                BasicLogHandler.m2542a(th, "ProxyUtil", "getProxySelectorCfg");
            }
        }
        return null;
    }

    /* renamed from: c */
    private static boolean m2476c(Context context) {
        return C0820dq.m2439m(context) == 0;
    }

    /* renamed from: b */
    private static int m2474b() {
        int i = -1;
        try {
            return android.net.Proxy.getDefaultPort();
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "ProxyUtil", "getDefPort");
            return i;
        }
    }
}
