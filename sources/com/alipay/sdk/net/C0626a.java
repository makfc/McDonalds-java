package com.alipay.sdk.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieManager;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.List;
import java.util.Map;

/* renamed from: com.alipay.sdk.net.a */
public final class C0626a {
    /* renamed from: c */
    private static final CookieManager f596c = new CookieManager();

    /* renamed from: com.alipay.sdk.net.a$a */
    public static final class C0624a {
        /* renamed from: a */
        public final String f590a;
        /* renamed from: b */
        public final byte[] f591b;
        /* renamed from: c */
        public final Map<String, String> f592c;

        public C0624a(String str, Map<String, String> map, byte[] bArr) {
            this.f590a = str;
            this.f591b = bArr;
            this.f592c = map;
        }

        public String toString() {
            return String.format("<UrlConnectionConfigure url=%s requestBody=%s headers=%s>", new Object[]{this.f590a, this.f591b, this.f592c});
        }
    }

    /* renamed from: com.alipay.sdk.net.a$b */
    public static final class C0625b {
        /* renamed from: a */
        public final Map<String, List<String>> f593a;
        /* renamed from: b */
        public final String f594b;
        /* renamed from: c */
        public final byte[] f595c;

        public C0625b(Map<String, List<String>> map, String str, byte[] bArr) {
            this.f593a = map;
            this.f594b = str;
            this.f595c = bArr;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:51:0x012c A:{SYNTHETIC, Splitter:B:51:0x012c} */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0131 A:{SYNTHETIC, Splitter:B:54:0x0131} */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0136 A:{SYNTHETIC, Splitter:B:57:0x0136} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0105 A:{SYNTHETIC, Splitter:B:34:0x0105} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x010a A:{SYNTHETIC, Splitter:B:37:0x010a} */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x010f A:{SYNTHETIC, Splitter:B:40:0x010f} */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x012c A:{SYNTHETIC, Splitter:B:51:0x012c} */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0131 A:{SYNTHETIC, Splitter:B:54:0x0131} */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0136 A:{SYNTHETIC, Splitter:B:57:0x0136} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0105 A:{SYNTHETIC, Splitter:B:34:0x0105} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x010a A:{SYNTHETIC, Splitter:B:37:0x010a} */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x010f A:{SYNTHETIC, Splitter:B:40:0x010f} */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x012c A:{SYNTHETIC, Splitter:B:51:0x012c} */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0131 A:{SYNTHETIC, Splitter:B:54:0x0131} */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0136 A:{SYNTHETIC, Splitter:B:57:0x0136} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0105 A:{SYNTHETIC, Splitter:B:34:0x0105} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x010a A:{SYNTHETIC, Splitter:B:37:0x010a} */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x010f A:{SYNTHETIC, Splitter:B:40:0x010f} */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x012c A:{SYNTHETIC, Splitter:B:51:0x012c} */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0131 A:{SYNTHETIC, Splitter:B:54:0x0131} */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0136 A:{SYNTHETIC, Splitter:B:57:0x0136} */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x012c A:{SYNTHETIC, Splitter:B:51:0x012c} */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0131 A:{SYNTHETIC, Splitter:B:54:0x0131} */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0136 A:{SYNTHETIC, Splitter:B:57:0x0136} */
    /* renamed from: a */
    public static com.alipay.sdk.net.C0626a.C0625b m910a(android.content.Context r15, com.alipay.sdk.net.C0626a.C0624a r16) {
        /*
        r4 = 0;
        if (r15 != 0) goto L_0x0005;
    L_0x0003:
        r1 = r4;
    L_0x0004:
        return r1;
    L_0x0005:
        r1 = "msp";
        r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x021f, all -> 0x0209 }
        r2.<init>();	 Catch:{ Throwable -> 0x021f, all -> 0x0209 }
        r3 = "config : ";
        r2 = r2.append(r3);	 Catch:{ Throwable -> 0x021f, all -> 0x0209 }
        r0 = r16;
        r2 = r2.append(r0);	 Catch:{ Throwable -> 0x021f, all -> 0x0209 }
        r2 = r2.toString();	 Catch:{ Throwable -> 0x021f, all -> 0x0209 }
        com.alipay.sdk.util.C0646c.m1018c(r1, r2);	 Catch:{ Throwable -> 0x021f, all -> 0x0209 }
        r7 = new java.net.URL;	 Catch:{ Throwable -> 0x021f, all -> 0x0209 }
        r0 = r16;
        r1 = r0.f590a;	 Catch:{ Throwable -> 0x021f, all -> 0x0209 }
        r7.<init>(r1);	 Catch:{ Throwable -> 0x021f, all -> 0x0209 }
        r1 = com.alipay.sdk.net.C0626a.m911a(r15);	 Catch:{ Throwable -> 0x021f, all -> 0x0209 }
        r2 = "msp";
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x021f, all -> 0x0209 }
        r3.<init>();	 Catch:{ Throwable -> 0x021f, all -> 0x0209 }
        r5 = "proxy: ";
        r3 = r3.append(r5);	 Catch:{ Throwable -> 0x021f, all -> 0x0209 }
        r3 = r3.append(r1);	 Catch:{ Throwable -> 0x021f, all -> 0x0209 }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x021f, all -> 0x0209 }
        com.alipay.sdk.util.C0646c.m1018c(r2, r3);	 Catch:{ Throwable -> 0x021f, all -> 0x0209 }
        if (r1 == 0) goto L_0x0115;
    L_0x0046:
        r1 = r7.openConnection(r1);	 Catch:{ Throwable -> 0x021f, all -> 0x0209 }
        r1 = com.newrelic.agent.android.instrumentation.HttpInstrumentation.openConnectionWithProxy(r1);	 Catch:{ Throwable -> 0x021f, all -> 0x0209 }
        r1 = (java.net.HttpURLConnection) r1;	 Catch:{ Throwable -> 0x021f, all -> 0x0209 }
        r2 = r1;
    L_0x0051:
        r1 = "http.keepAlive";
        r3 = "false";
        java.lang.System.setProperty(r1, r3);	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        r1 = r2 instanceof javax.net.ssl.HttpsURLConnection;	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        if (r1 == 0) goto L_0x0060;
    L_0x005c:
        r0 = r2;
        r0 = (javax.net.ssl.HttpsURLConnection) r0;	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        r1 = r0;
    L_0x0060:
        r1 = f596c;	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        r1 = r1.getCookieStore();	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        r1 = r1.getCookies();	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        r1 = r1.size();	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        if (r1 <= 0) goto L_0x0085;
    L_0x0070:
        r1 = "Cookie";
        r3 = ";";
        r5 = f596c;	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        r5 = r5.getCookieStore();	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        r5 = r5.getCookies();	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        r3 = android.text.TextUtils.join(r3, r5);	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        r2.setRequestProperty(r1, r3);	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
    L_0x0085:
        r1 = 20000; // 0x4e20 float:2.8026E-41 double:9.8813E-320;
        r2.setConnectTimeout(r1);	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        r1 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r2.setReadTimeout(r1);	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        r1 = 1;
        r2.setInstanceFollowRedirects(r1);	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        r1 = "User-Agent";
        r3 = "msp";
        r2.setRequestProperty(r1, r3);	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        r0 = r16;
        r1 = r0.f591b;	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        if (r1 == 0) goto L_0x0122;
    L_0x00a0:
        r0 = r16;
        r1 = r0.f591b;	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        r1 = r1.length;	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        if (r1 <= 0) goto L_0x0122;
    L_0x00a7:
        r1 = "POST";
        r2.setRequestMethod(r1);	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        r1 = "Content-Type";
        r3 = "application/octet-stream;binary/octet-stream";
        r2.setRequestProperty(r1, r3);	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        r1 = "Accept-Charset";
        r3 = "UTF-8";
        r2.setRequestProperty(r1, r3);	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        r1 = "Connection";
        r3 = "Keep-Alive";
        r2.setRequestProperty(r1, r3);	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        r1 = "Keep-Alive";
        r3 = "timeout=180, max=100";
        r2.setRequestProperty(r1, r3);	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
    L_0x00c8:
        r0 = r16;
        r1 = r0.f592c;	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        if (r1 == 0) goto L_0x013a;
    L_0x00ce:
        r0 = r16;
        r1 = r0.f592c;	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        r1 = r1.entrySet();	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        r5 = r1.iterator();	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
    L_0x00da:
        r1 = r5.hasNext();	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        if (r1 == 0) goto L_0x013a;
    L_0x00e0:
        r1 = r5.next();	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        r1 = (java.util.Map.Entry) r1;	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        r3 = r1.getKey();	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        if (r3 == 0) goto L_0x00da;
    L_0x00ec:
        r3 = r1.getKey();	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        r3 = (java.lang.String) r3;	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        r1 = r1.getValue();	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        r1 = (java.lang.String) r1;	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        r2.setRequestProperty(r3, r1);	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        goto L_0x00da;
    L_0x00fc:
        r1 = move-exception;
        r3 = r4;
        r5 = r2;
        r2 = r4;
    L_0x0100:
        com.alipay.sdk.util.C0646c.m1016a(r1);	 Catch:{ all -> 0x0219 }
        if (r5 == 0) goto L_0x0108;
    L_0x0105:
        r5.disconnect();	 Catch:{ Throwable -> 0x01f7 }
    L_0x0108:
        if (r2 == 0) goto L_0x010d;
    L_0x010a:
        r2.close();	 Catch:{ Throwable -> 0x01fa }
    L_0x010d:
        if (r3 == 0) goto L_0x0112;
    L_0x010f:
        r3.close();	 Catch:{ Throwable -> 0x01fd }
    L_0x0112:
        r1 = r4;
        goto L_0x0004;
    L_0x0115:
        r1 = r7.openConnection();	 Catch:{ Throwable -> 0x021f, all -> 0x0209 }
        r1 = com.newrelic.agent.android.instrumentation.HttpInstrumentation.openConnection(r1);	 Catch:{ Throwable -> 0x021f, all -> 0x0209 }
        r1 = (java.net.HttpURLConnection) r1;	 Catch:{ Throwable -> 0x021f, all -> 0x0209 }
        r2 = r1;
        goto L_0x0051;
    L_0x0122:
        r1 = "GET";
        r2.setRequestMethod(r1);	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        goto L_0x00c8;
    L_0x0128:
        r1 = move-exception;
        r5 = r4;
    L_0x012a:
        if (r2 == 0) goto L_0x012f;
    L_0x012c:
        r2.disconnect();	 Catch:{ Throwable -> 0x0200 }
    L_0x012f:
        if (r4 == 0) goto L_0x0134;
    L_0x0131:
        r4.close();	 Catch:{ Throwable -> 0x0203 }
    L_0x0134:
        if (r5 == 0) goto L_0x0139;
    L_0x0136:
        r5.close();	 Catch:{ Throwable -> 0x0206 }
    L_0x0139:
        throw r1;
    L_0x013a:
        r1 = 1;
        r2.setDoInput(r1);	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        r1 = "POST";
        r3 = r2.getRequestMethod();	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        r1 = r1.equals(r3);	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        if (r1 == 0) goto L_0x014e;
    L_0x014a:
        r1 = 1;
        r2.setDoOutput(r1);	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
    L_0x014e:
        r1 = "POST";
        r3 = r2.getRequestMethod();	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        r1 = r1.equals(r3);	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        if (r1 == 0) goto L_0x0230;
    L_0x015a:
        r3 = new java.io.BufferedOutputStream;	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        r1 = r2.getOutputStream();	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        r3.<init>(r1);	 Catch:{ Throwable -> 0x00fc, all -> 0x0128 }
        r0 = r16;
        r1 = r0.f591b;	 Catch:{ Throwable -> 0x0225, all -> 0x020e }
        r3.write(r1);	 Catch:{ Throwable -> 0x0225, all -> 0x020e }
        r3.flush();	 Catch:{ Throwable -> 0x0225, all -> 0x020e }
        r5 = r3;
    L_0x016e:
        r3 = new java.io.BufferedInputStream;	 Catch:{ Throwable -> 0x022a, all -> 0x0212 }
        r1 = r2.getInputStream();	 Catch:{ Throwable -> 0x022a, all -> 0x0212 }
        r3.<init>(r1);	 Catch:{ Throwable -> 0x022a, all -> 0x0212 }
        r8 = com.alipay.sdk.net.C0626a.m912a(r3);	 Catch:{ Throwable -> 0x01d1, all -> 0x0215 }
        r9 = r2.getHeaderFields();	 Catch:{ Throwable -> 0x01d1, all -> 0x0215 }
        if (r9 == 0) goto L_0x01d8;
    L_0x0181:
        r1 = 0;
        r1 = r9.get(r1);	 Catch:{ Throwable -> 0x01d1, all -> 0x0215 }
        if (r1 == 0) goto L_0x01d8;
    L_0x0188:
        r6 = ",";
        r1 = 0;
        r1 = r9.get(r1);	 Catch:{ Throwable -> 0x01d1, all -> 0x0215 }
        r1 = (java.lang.Iterable) r1;	 Catch:{ Throwable -> 0x01d1, all -> 0x0215 }
        r1 = android.text.TextUtils.join(r6, r1);	 Catch:{ Throwable -> 0x01d1, all -> 0x0215 }
        r6 = r1;
    L_0x0196:
        r1 = "Set-Cookie";
        r1 = r9.get(r1);	 Catch:{ Throwable -> 0x01d1, all -> 0x0215 }
        r1 = (java.util.List) r1;	 Catch:{ Throwable -> 0x01d1, all -> 0x0215 }
        if (r1 == 0) goto L_0x01da;
    L_0x01a0:
        r10 = r1.iterator();	 Catch:{ Throwable -> 0x01d1, all -> 0x0215 }
    L_0x01a4:
        r1 = r10.hasNext();	 Catch:{ Throwable -> 0x01d1, all -> 0x0215 }
        if (r1 == 0) goto L_0x01da;
    L_0x01aa:
        r1 = r10.next();	 Catch:{ Throwable -> 0x01d1, all -> 0x0215 }
        r1 = (java.lang.String) r1;	 Catch:{ Throwable -> 0x01d1, all -> 0x0215 }
        r1 = java.net.HttpCookie.parse(r1);	 Catch:{ Throwable -> 0x01d1, all -> 0x0215 }
        if (r1 == 0) goto L_0x01a4;
    L_0x01b6:
        r11 = r1.isEmpty();	 Catch:{ Throwable -> 0x01d1, all -> 0x0215 }
        if (r11 != 0) goto L_0x01a4;
    L_0x01bc:
        r11 = f596c;	 Catch:{ Throwable -> 0x01d1, all -> 0x0215 }
        r11 = r11.getCookieStore();	 Catch:{ Throwable -> 0x01d1, all -> 0x0215 }
        r12 = r7.toURI();	 Catch:{ Throwable -> 0x01d1, all -> 0x0215 }
        r13 = 0;
        r1 = r1.get(r13);	 Catch:{ Throwable -> 0x01d1, all -> 0x0215 }
        r1 = (java.net.HttpCookie) r1;	 Catch:{ Throwable -> 0x01d1, all -> 0x0215 }
        r11.add(r12, r1);	 Catch:{ Throwable -> 0x01d1, all -> 0x0215 }
        goto L_0x01a4;
    L_0x01d1:
        r1 = move-exception;
        r14 = r3;
        r3 = r5;
        r5 = r2;
        r2 = r14;
        goto L_0x0100;
    L_0x01d8:
        r6 = r4;
        goto L_0x0196;
    L_0x01da:
        r1 = new com.alipay.sdk.net.a$b;	 Catch:{ Throwable -> 0x01d1, all -> 0x0215 }
        r1.<init>(r9, r6, r8);	 Catch:{ Throwable -> 0x01d1, all -> 0x0215 }
        if (r2 == 0) goto L_0x01e4;
    L_0x01e1:
        r2.disconnect();	 Catch:{ Throwable -> 0x01f3 }
    L_0x01e4:
        if (r3 == 0) goto L_0x01e9;
    L_0x01e6:
        r3.close();	 Catch:{ Throwable -> 0x01f5 }
    L_0x01e9:
        if (r5 == 0) goto L_0x0004;
    L_0x01eb:
        r5.close();	 Catch:{ Throwable -> 0x01f0 }
        goto L_0x0004;
    L_0x01f0:
        r2 = move-exception;
        goto L_0x0004;
    L_0x01f3:
        r2 = move-exception;
        goto L_0x01e4;
    L_0x01f5:
        r2 = move-exception;
        goto L_0x01e9;
    L_0x01f7:
        r1 = move-exception;
        goto L_0x0108;
    L_0x01fa:
        r1 = move-exception;
        goto L_0x010d;
    L_0x01fd:
        r1 = move-exception;
        goto L_0x0112;
    L_0x0200:
        r2 = move-exception;
        goto L_0x012f;
    L_0x0203:
        r2 = move-exception;
        goto L_0x0134;
    L_0x0206:
        r2 = move-exception;
        goto L_0x0139;
    L_0x0209:
        r1 = move-exception;
        r5 = r4;
        r2 = r4;
        goto L_0x012a;
    L_0x020e:
        r1 = move-exception;
        r5 = r3;
        goto L_0x012a;
    L_0x0212:
        r1 = move-exception;
        goto L_0x012a;
    L_0x0215:
        r1 = move-exception;
        r4 = r3;
        goto L_0x012a;
    L_0x0219:
        r1 = move-exception;
        r4 = r2;
        r2 = r5;
        r5 = r3;
        goto L_0x012a;
    L_0x021f:
        r1 = move-exception;
        r2 = r4;
        r3 = r4;
        r5 = r4;
        goto L_0x0100;
    L_0x0225:
        r1 = move-exception;
        r5 = r2;
        r2 = r4;
        goto L_0x0100;
    L_0x022a:
        r1 = move-exception;
        r3 = r5;
        r5 = r2;
        r2 = r4;
        goto L_0x0100;
    L_0x0230:
        r5 = r4;
        goto L_0x016e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.net.C0626a.m910a(android.content.Context, com.alipay.sdk.net.a$a):com.alipay.sdk.net.a$b");
    }

    /* renamed from: a */
    private static Proxy m911a(Context context) {
        String c = C0626a.m914c(context);
        if (c != null && !c.contains("wap")) {
            return null;
        }
        Proxy proxy;
        try {
            String property = System.getProperty("https.proxyHost");
            String property2 = System.getProperty("https.proxyPort");
            if (TextUtils.isEmpty(property)) {
                return null;
            }
            proxy = new Proxy(Type.HTTP, new InetSocketAddress(property, Integer.parseInt(property2)));
            return proxy;
        } catch (Throwable th) {
            proxy = null;
        }
    }

    /* renamed from: b */
    private static NetworkInfo m913b(Context context) {
        if (context == null) {
            return null;
        }
        NetworkInfo activeNetworkInfo;
        try {
            activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception e) {
            activeNetworkInfo = null;
        }
        return activeNetworkInfo;
    }

    /* renamed from: c */
    private static String m914c(Context context) {
        try {
            NetworkInfo b = C0626a.m913b(context);
            if (b == null || !b.isAvailable()) {
                return "none";
            }
            if (b.getType() == 1) {
                return "wifi";
            }
            return b.getExtraInfo().toLowerCase();
        } catch (Exception e) {
            return "none";
        }
    }

    /* renamed from: a */
    private static byte[] m912a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr, 0, bArr.length);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byteArrayOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }
}
