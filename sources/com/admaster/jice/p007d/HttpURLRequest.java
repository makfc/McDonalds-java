package com.admaster.jice.p007d;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.admaster.jice.p006c.DeviceInfoUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.admaster.jice.d.b */
public class HttpURLRequest {
    /* renamed from: a */
    private static boolean f129a = true;
    /* renamed from: b */
    private String f130b = null;

    public HttpURLRequest(Context context) {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            new Handler(Looper.getMainLooper()).post(new C0478c(this, context));
        } else {
            m219b(context);
        }
    }

    @SuppressLint({"NewApi"})
    /* renamed from: b */
    private void m219b(Context context) {
        try {
            if (VERSION.SDK_INT < 19) {
                this.f130b = new WebView(context).getSettings().getUserAgentString();
            } else {
                this.f130b = WebSettings.getDefaultUserAgent(context);
            }
        } catch (Exception e) {
            LOG.m225a("JiceSDK.HttpURLRequest", "can`t get default UserAgent", e);
        }
    }

    /* renamed from: a */
    public boolean mo7713a(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                String j = DeviceInfoUtil.m186j(context);
                if (!(TextUtils.isEmpty(j) || j.equals("2g"))) {
                    return true;
                }
            }
        } catch (Exception e) {
            LOG.m225a("JiceSDK.HttpURLRequest", "isNetWorkEnabled exception", e);
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x00b9 A:{SYNTHETIC, Splitter:B:43:0x00b9} */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00be A:{SYNTHETIC, Splitter:B:46:0x00be} */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00d4 A:{SYNTHETIC, Splitter:B:55:0x00d4} */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00d9 A:{SYNTHETIC, Splitter:B:58:0x00d9} */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00de  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00e8 A:{SYNTHETIC, Splitter:B:65:0x00e8} */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00ed A:{SYNTHETIC, Splitter:B:68:0x00ed} */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00b9 A:{SYNTHETIC, Splitter:B:43:0x00b9} */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00be A:{SYNTHETIC, Splitter:B:46:0x00be} */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00d4 A:{SYNTHETIC, Splitter:B:55:0x00d4} */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00d9 A:{SYNTHETIC, Splitter:B:58:0x00d9} */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00de  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00e8 A:{SYNTHETIC, Splitter:B:65:0x00e8} */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00ed A:{SYNTHETIC, Splitter:B:68:0x00ed} */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x013c A:{ExcHandler: EOFException (r2_36 'e' java.lang.Throwable), Splitter:B:15:0x006a, PHI: r4 } */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00d4 A:{SYNTHETIC, Splitter:B:55:0x00d4} */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00d9 A:{SYNTHETIC, Splitter:B:58:0x00d9} */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00de  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00e8 A:{SYNTHETIC, Splitter:B:65:0x00e8} */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00ed A:{SYNTHETIC, Splitter:B:68:0x00ed} */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00e8 A:{SYNTHETIC, Splitter:B:65:0x00e8} */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00ed A:{SYNTHETIC, Splitter:B:68:0x00ed} */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00e8 A:{SYNTHETIC, Splitter:B:65:0x00e8} */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00ed A:{SYNTHETIC, Splitter:B:68:0x00ed} */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00b9 A:{SYNTHETIC, Splitter:B:43:0x00b9} */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00be A:{SYNTHETIC, Splitter:B:46:0x00be} */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00d4 A:{SYNTHETIC, Splitter:B:55:0x00d4} */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00d9 A:{SYNTHETIC, Splitter:B:58:0x00d9} */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00de  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00e8 A:{SYNTHETIC, Splitter:B:65:0x00e8} */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00ed A:{SYNTHETIC, Splitter:B:68:0x00ed} */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00f2  */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:83:0x010c, code skipped:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:84:0x010d, code skipped:
            r5 = null;
            r4 = r1;
            r1 = r2;
     */
    /* JADX WARNING: Missing block: B:94:0x0127, code skipped:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:95:0x0128, code skipped:
            r5 = null;
            r4 = r1;
            r1 = r2;
     */
    /* JADX WARNING: Missing block: B:102:0x013c, code skipped:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:103:0x013d, code skipped:
            r5 = r6;
            r8 = r1;
            r1 = r2;
            r2 = r8;
     */
    /* renamed from: a */
    public boolean mo7714a(java.lang.String r10, java.lang.String r11, javax.net.ssl.SSLSocketFactory r12) {
        /*
        r9 = this;
        r7 = 1;
        r4 = 0;
        r3 = 0;
        r1 = new java.net.URL;	 Catch:{ EOFException -> 0x00ad, IOException -> 0x00c8, all -> 0x00e3 }
        r1.<init>(r10);	 Catch:{ EOFException -> 0x00ad, IOException -> 0x00c8, all -> 0x00e3 }
        r1 = r1.openConnection();	 Catch:{ EOFException -> 0x00ad, IOException -> 0x00c8, all -> 0x00e3 }
        r1 = com.newrelic.agent.android.instrumentation.HttpInstrumentation.openConnection(r1);	 Catch:{ EOFException -> 0x00ad, IOException -> 0x00c8, all -> 0x00e3 }
        r1 = (java.net.HttpURLConnection) r1;	 Catch:{ EOFException -> 0x00ad, IOException -> 0x00c8, all -> 0x00e3 }
        if (r12 == 0) goto L_0x002b;
    L_0x0014:
        r2 = r1 instanceof javax.net.ssl.HttpsURLConnection;	 Catch:{ EOFException -> 0x0135, IOException -> 0x0121, all -> 0x0106 }
        if (r2 == 0) goto L_0x002b;
    L_0x0018:
        r0 = r1;
        r0 = (javax.net.ssl.HttpsURLConnection) r0;	 Catch:{ EOFException -> 0x0135, IOException -> 0x0121, all -> 0x0106 }
        r2 = r0;
        r2.setSSLSocketFactory(r12);	 Catch:{ EOFException -> 0x0135, IOException -> 0x0121, all -> 0x0106 }
        r0 = r1;
        r0 = (javax.net.ssl.HttpsURLConnection) r0;	 Catch:{ EOFException -> 0x0135, IOException -> 0x0121, all -> 0x0106 }
        r2 = r0;
        r5 = new com.admaster.jice.b.c;	 Catch:{ EOFException -> 0x0135, IOException -> 0x0121, all -> 0x0106 }
        r5.<init>();	 Catch:{ EOFException -> 0x0135, IOException -> 0x0121, all -> 0x0106 }
        r2.setHostnameVerifier(r5);	 Catch:{ EOFException -> 0x0135, IOException -> 0x0121, all -> 0x0106 }
    L_0x002b:
        r2 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r1.setConnectTimeout(r2);	 Catch:{ EOFException -> 0x0135, IOException -> 0x0121, all -> 0x0106 }
        r2 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r1.setReadTimeout(r2);	 Catch:{ EOFException -> 0x0135, IOException -> 0x0121, all -> 0x0106 }
        r2 = 1;
        r1.setDoOutput(r2);	 Catch:{ EOFException -> 0x0135, IOException -> 0x0121, all -> 0x0106 }
        r2 = "POST";
        r1.setRequestMethod(r2);	 Catch:{ EOFException -> 0x0135, IOException -> 0x0121, all -> 0x0106 }
        r2 = "Content-Type";
        r5 = "application/json";
        r1.setRequestProperty(r2, r5);	 Catch:{ EOFException -> 0x0135, IOException -> 0x0121, all -> 0x0106 }
        r2 = "AcceptEncoding";
        r5 = "gzip, deflate";
        r1.setRequestProperty(r2, r5);	 Catch:{ EOFException -> 0x0135, IOException -> 0x0121, all -> 0x0106 }
        r2 = r9.f130b;	 Catch:{ EOFException -> 0x0135, IOException -> 0x0121, all -> 0x0106 }
        r2 = android.text.TextUtils.isEmpty(r2);	 Catch:{ EOFException -> 0x0135, IOException -> 0x0121, all -> 0x0106 }
        if (r2 != 0) goto L_0x005b;
    L_0x0054:
        r2 = "User-Agent";
        r5 = r9.f130b;	 Catch:{ EOFException -> 0x0135, IOException -> 0x0121, all -> 0x0106 }
        r1.setRequestProperty(r2, r5);	 Catch:{ EOFException -> 0x0135, IOException -> 0x0121, all -> 0x0106 }
    L_0x005b:
        r2 = f129a;	 Catch:{ EOFException -> 0x0135, IOException -> 0x0121, all -> 0x0106 }
        if (r2 == 0) goto L_0x0066;
    L_0x005f:
        r2 = "Content-Encoding";
        r5 = "gzip";
        r1.setRequestProperty(r2, r5);	 Catch:{ EOFException -> 0x0135, IOException -> 0x0121, all -> 0x0106 }
    L_0x0066:
        r6 = r1.getOutputStream();	 Catch:{ EOFException -> 0x0135, IOException -> 0x0121, all -> 0x0106 }
        r2 = f129a;	 Catch:{ EOFException -> 0x013c, IOException -> 0x0127, all -> 0x010c }
        if (r2 == 0) goto L_0x0098;
    L_0x006e:
        r2 = "UTF-8";
        r2 = r11.getBytes(r2);	 Catch:{ EOFException -> 0x013c, IOException -> 0x0127, all -> 0x010c }
        r2 = com.admaster.jice.p007d.HttpURLRequest.m218a(r2);	 Catch:{ EOFException -> 0x013c, IOException -> 0x0127, all -> 0x010c }
        r6.write(r2);	 Catch:{ EOFException -> 0x013c, IOException -> 0x0127, all -> 0x010c }
        r6.flush();	 Catch:{ EOFException -> 0x013c, IOException -> 0x0127, all -> 0x010c }
    L_0x007e:
        r2 = r1.getResponseCode();	 Catch:{ EOFException -> 0x013c, IOException -> 0x0130, all -> 0x0115 }
        r5 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r2 != r5) goto L_0x00ab;
    L_0x0086:
        r2 = r7;
    L_0x0087:
        if (r4 == 0) goto L_0x008c;
    L_0x0089:
        r4.close();	 Catch:{ IOException -> 0x0102 }
    L_0x008c:
        if (r6 == 0) goto L_0x0091;
    L_0x008e:
        r6.close();	 Catch:{ IOException -> 0x0104 }
    L_0x0091:
        if (r1 == 0) goto L_0x014b;
    L_0x0093:
        r1.disconnect();
        r1 = r2;
    L_0x0097:
        return r1;
    L_0x0098:
        r5 = new java.io.BufferedOutputStream;	 Catch:{ EOFException -> 0x013c, IOException -> 0x0127, all -> 0x010c }
        r5.<init>(r6);	 Catch:{ EOFException -> 0x013c, IOException -> 0x0127, all -> 0x010c }
        r2 = "UTF-8";
        r2 = r11.getBytes(r2);	 Catch:{ EOFException -> 0x0143, IOException -> 0x012c, all -> 0x0111 }
        r5.write(r2);	 Catch:{ EOFException -> 0x0143, IOException -> 0x012c, all -> 0x0111 }
        r5.flush();	 Catch:{ EOFException -> 0x0143, IOException -> 0x012c, all -> 0x0111 }
        r4 = r5;
        goto L_0x007e;
    L_0x00ab:
        r2 = r3;
        goto L_0x0087;
    L_0x00ad:
        r1 = move-exception;
        r2 = r4;
        r5 = r4;
    L_0x00b0:
        r6 = "JiceSDK.HttpURLRequest";
        r7 = "Failure to connect,likely caused by stream end of file\n";
        com.admaster.jice.p007d.LOG.m225a(r6, r7, r1);	 Catch:{ all -> 0x011a }
        if (r4 == 0) goto L_0x00bc;
    L_0x00b9:
        r4.close();	 Catch:{ IOException -> 0x00f6 }
    L_0x00bc:
        if (r5 == 0) goto L_0x00c1;
    L_0x00be:
        r5.close();	 Catch:{ IOException -> 0x00f8 }
    L_0x00c1:
        if (r2 == 0) goto L_0x014e;
    L_0x00c3:
        r2.disconnect();
        r1 = r3;
        goto L_0x0097;
    L_0x00c8:
        r1 = move-exception;
        r5 = r4;
        r6 = r4;
    L_0x00cb:
        r2 = "JiceSDK.HttpURLRequest";
        r7 = "Failure to connect,caused by IO Exception\n";
        com.admaster.jice.p007d.LOG.m225a(r2, r7, r1);	 Catch:{ all -> 0x011f }
        if (r5 == 0) goto L_0x00d7;
    L_0x00d4:
        r5.close();	 Catch:{ IOException -> 0x00fa }
    L_0x00d7:
        if (r6 == 0) goto L_0x00dc;
    L_0x00d9:
        r6.close();	 Catch:{ IOException -> 0x00fc }
    L_0x00dc:
        if (r4 == 0) goto L_0x014e;
    L_0x00de:
        r4.disconnect();
        r1 = r3;
        goto L_0x0097;
    L_0x00e3:
        r1 = move-exception;
        r5 = r4;
        r6 = r4;
    L_0x00e6:
        if (r5 == 0) goto L_0x00eb;
    L_0x00e8:
        r5.close();	 Catch:{ IOException -> 0x00fe }
    L_0x00eb:
        if (r6 == 0) goto L_0x00f0;
    L_0x00ed:
        r6.close();	 Catch:{ IOException -> 0x0100 }
    L_0x00f0:
        if (r4 == 0) goto L_0x00f5;
    L_0x00f2:
        r4.disconnect();
    L_0x00f5:
        throw r1;
    L_0x00f6:
        r1 = move-exception;
        goto L_0x00bc;
    L_0x00f8:
        r1 = move-exception;
        goto L_0x00c1;
    L_0x00fa:
        r1 = move-exception;
        goto L_0x00d7;
    L_0x00fc:
        r1 = move-exception;
        goto L_0x00dc;
    L_0x00fe:
        r2 = move-exception;
        goto L_0x00eb;
    L_0x0100:
        r2 = move-exception;
        goto L_0x00f0;
    L_0x0102:
        r3 = move-exception;
        goto L_0x008c;
    L_0x0104:
        r3 = move-exception;
        goto L_0x0091;
    L_0x0106:
        r2 = move-exception;
        r5 = r4;
        r6 = r4;
        r4 = r1;
        r1 = r2;
        goto L_0x00e6;
    L_0x010c:
        r2 = move-exception;
        r5 = r4;
        r4 = r1;
        r1 = r2;
        goto L_0x00e6;
    L_0x0111:
        r2 = move-exception;
        r4 = r1;
        r1 = r2;
        goto L_0x00e6;
    L_0x0115:
        r2 = move-exception;
        r5 = r4;
        r4 = r1;
        r1 = r2;
        goto L_0x00e6;
    L_0x011a:
        r1 = move-exception;
        r6 = r5;
        r5 = r4;
        r4 = r2;
        goto L_0x00e6;
    L_0x011f:
        r1 = move-exception;
        goto L_0x00e6;
    L_0x0121:
        r2 = move-exception;
        r5 = r4;
        r6 = r4;
        r4 = r1;
        r1 = r2;
        goto L_0x00cb;
    L_0x0127:
        r2 = move-exception;
        r5 = r4;
        r4 = r1;
        r1 = r2;
        goto L_0x00cb;
    L_0x012c:
        r2 = move-exception;
        r4 = r1;
        r1 = r2;
        goto L_0x00cb;
    L_0x0130:
        r2 = move-exception;
        r5 = r4;
        r4 = r1;
        r1 = r2;
        goto L_0x00cb;
    L_0x0135:
        r2 = move-exception;
        r5 = r4;
        r8 = r1;
        r1 = r2;
        r2 = r8;
        goto L_0x00b0;
    L_0x013c:
        r2 = move-exception;
        r5 = r6;
        r8 = r1;
        r1 = r2;
        r2 = r8;
        goto L_0x00b0;
    L_0x0143:
        r2 = move-exception;
        r4 = r5;
        r5 = r6;
        r8 = r2;
        r2 = r1;
        r1 = r8;
        goto L_0x00b0;
    L_0x014b:
        r1 = r2;
        goto L_0x0097;
    L_0x014e:
        r1 = r3;
        goto L_0x0097;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.admaster.jice.p007d.HttpURLRequest.mo7714a(java.lang.String, java.lang.String, javax.net.ssl.SSLSocketFactory):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:75:0x0101 A:{SYNTHETIC, Splitter:B:75:0x0101} */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0106 A:{SYNTHETIC, Splitter:B:78:0x0106} */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x010b A:{SYNTHETIC, Splitter:B:81:0x010b} */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0110  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0101 A:{SYNTHETIC, Splitter:B:75:0x0101} */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0106 A:{SYNTHETIC, Splitter:B:78:0x0106} */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x010b A:{SYNTHETIC, Splitter:B:81:0x010b} */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0110  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00e2 A:{SYNTHETIC, Splitter:B:60:0x00e2} */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00e7 A:{SYNTHETIC, Splitter:B:63:0x00e7} */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00ec A:{SYNTHETIC, Splitter:B:66:0x00ec} */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00f1  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0101 A:{SYNTHETIC, Splitter:B:75:0x0101} */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0106 A:{SYNTHETIC, Splitter:B:78:0x0106} */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x010b A:{SYNTHETIC, Splitter:B:81:0x010b} */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0110  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00bb A:{SYNTHETIC, Splitter:B:43:0x00bb} */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00c0 A:{SYNTHETIC, Splitter:B:46:0x00c0} */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00c5 A:{SYNTHETIC, Splitter:B:49:0x00c5} */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00e2 A:{SYNTHETIC, Splitter:B:60:0x00e2} */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00e7 A:{SYNTHETIC, Splitter:B:63:0x00e7} */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00ec A:{SYNTHETIC, Splitter:B:66:0x00ec} */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00f1  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0101 A:{SYNTHETIC, Splitter:B:75:0x0101} */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0106 A:{SYNTHETIC, Splitter:B:78:0x0106} */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x010b A:{SYNTHETIC, Splitter:B:81:0x010b} */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0110  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00bb A:{SYNTHETIC, Splitter:B:43:0x00bb} */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00c0 A:{SYNTHETIC, Splitter:B:46:0x00c0} */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00c5 A:{SYNTHETIC, Splitter:B:49:0x00c5} */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00e2 A:{SYNTHETIC, Splitter:B:60:0x00e2} */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00e7 A:{SYNTHETIC, Splitter:B:63:0x00e7} */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00ec A:{SYNTHETIC, Splitter:B:66:0x00ec} */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00f1  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0101 A:{SYNTHETIC, Splitter:B:75:0x0101} */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0106 A:{SYNTHETIC, Splitter:B:78:0x0106} */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x010b A:{SYNTHETIC, Splitter:B:81:0x010b} */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0110  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00e2 A:{SYNTHETIC, Splitter:B:60:0x00e2} */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00e7 A:{SYNTHETIC, Splitter:B:63:0x00e7} */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00ec A:{SYNTHETIC, Splitter:B:66:0x00ec} */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00f1  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0101 A:{SYNTHETIC, Splitter:B:75:0x0101} */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0106 A:{SYNTHETIC, Splitter:B:78:0x0106} */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x010b A:{SYNTHETIC, Splitter:B:81:0x010b} */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0110  */
    /* renamed from: b */
    public byte[] mo7716b(java.lang.String r11, java.lang.String r12, javax.net.ssl.SSLSocketFactory r13) {
        /*
        r10 = this;
        r3 = 0;
        r1 = new java.net.URL;	 Catch:{ EOFException -> 0x00ad, IOException -> 0x00d4, all -> 0x00fb }
        r1.<init>(r11);	 Catch:{ EOFException -> 0x00ad, IOException -> 0x00d4, all -> 0x00fb }
        r1 = r1.openConnection();	 Catch:{ EOFException -> 0x00ad, IOException -> 0x00d4, all -> 0x00fb }
        r1 = com.newrelic.agent.android.instrumentation.HttpInstrumentation.openConnection(r1);	 Catch:{ EOFException -> 0x00ad, IOException -> 0x00d4, all -> 0x00fb }
        r1 = (java.net.HttpURLConnection) r1;	 Catch:{ EOFException -> 0x00ad, IOException -> 0x00d4, all -> 0x00fb }
        if (r13 == 0) goto L_0x0029;
    L_0x0012:
        r2 = r1 instanceof javax.net.ssl.HttpsURLConnection;	 Catch:{ EOFException -> 0x0167, IOException -> 0x014b, all -> 0x0130 }
        if (r2 == 0) goto L_0x0029;
    L_0x0016:
        r0 = r1;
        r0 = (javax.net.ssl.HttpsURLConnection) r0;	 Catch:{ EOFException -> 0x0167, IOException -> 0x014b, all -> 0x0130 }
        r2 = r0;
        r2.setSSLSocketFactory(r13);	 Catch:{ EOFException -> 0x0167, IOException -> 0x014b, all -> 0x0130 }
        r0 = r1;
        r0 = (javax.net.ssl.HttpsURLConnection) r0;	 Catch:{ EOFException -> 0x0167, IOException -> 0x014b, all -> 0x0130 }
        r2 = r0;
        r4 = new com.admaster.jice.b.c;	 Catch:{ EOFException -> 0x0167, IOException -> 0x014b, all -> 0x0130 }
        r4.<init>();	 Catch:{ EOFException -> 0x0167, IOException -> 0x014b, all -> 0x0130 }
        r2.setHostnameVerifier(r4);	 Catch:{ EOFException -> 0x0167, IOException -> 0x014b, all -> 0x0130 }
    L_0x0029:
        r2 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r1.setConnectTimeout(r2);	 Catch:{ EOFException -> 0x0167, IOException -> 0x014b, all -> 0x0130 }
        r2 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r1.setReadTimeout(r2);	 Catch:{ EOFException -> 0x0167, IOException -> 0x014b, all -> 0x0130 }
        r2 = 1;
        r1.setDoOutput(r2);	 Catch:{ EOFException -> 0x0167, IOException -> 0x014b, all -> 0x0130 }
        r2 = 1;
        r1.setDoInput(r2);	 Catch:{ EOFException -> 0x0167, IOException -> 0x014b, all -> 0x0130 }
        r2 = "POST";
        r1.setRequestMethod(r2);	 Catch:{ EOFException -> 0x0167, IOException -> 0x014b, all -> 0x0130 }
        r2 = "Content-Type";
        r4 = "application/json";
        r1.setRequestProperty(r2, r4);	 Catch:{ EOFException -> 0x0167, IOException -> 0x014b, all -> 0x0130 }
        r2 = r10.f130b;	 Catch:{ EOFException -> 0x0167, IOException -> 0x014b, all -> 0x0130 }
        r2 = android.text.TextUtils.isEmpty(r2);	 Catch:{ EOFException -> 0x0167, IOException -> 0x014b, all -> 0x0130 }
        if (r2 != 0) goto L_0x0056;
    L_0x004f:
        r2 = "User-Agent";
        r4 = r10.f130b;	 Catch:{ EOFException -> 0x0167, IOException -> 0x014b, all -> 0x0130 }
        r1.setRequestProperty(r2, r4);	 Catch:{ EOFException -> 0x0167, IOException -> 0x014b, all -> 0x0130 }
    L_0x0056:
        r6 = r1.getOutputStream();	 Catch:{ EOFException -> 0x0167, IOException -> 0x014b, all -> 0x0130 }
        r5 = new java.io.BufferedOutputStream;	 Catch:{ EOFException -> 0x016f, IOException -> 0x0153, all -> 0x0136 }
        r5.<init>(r6);	 Catch:{ EOFException -> 0x016f, IOException -> 0x0153, all -> 0x0136 }
        r2 = "UTF-8";
        r2 = r12.getBytes(r2);	 Catch:{ EOFException -> 0x0176, IOException -> 0x015a, all -> 0x013b }
        r5.write(r2);	 Catch:{ EOFException -> 0x0176, IOException -> 0x015a, all -> 0x013b }
        r5.flush();	 Catch:{ EOFException -> 0x0176, IOException -> 0x015a, all -> 0x013b }
        r2 = r1.getResponseCode();	 Catch:{ EOFException -> 0x0176, IOException -> 0x015a, all -> 0x013b }
        r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r2 != r4) goto L_0x0186;
    L_0x0073:
        r2 = r1.getContentEncoding();	 Catch:{ EOFException -> 0x0176, IOException -> 0x015a, all -> 0x013b }
        r4 = android.text.TextUtils.isEmpty(r2);	 Catch:{ EOFException -> 0x0176, IOException -> 0x015a, all -> 0x013b }
        if (r4 != 0) goto L_0x00a8;
    L_0x007d:
        r4 = "gzip";
        r2 = r2.contains(r4);	 Catch:{ EOFException -> 0x0176, IOException -> 0x015a, all -> 0x013b }
        if (r2 == 0) goto L_0x00a8;
    L_0x0085:
        r4 = new java.util.zip.GZIPInputStream;	 Catch:{ EOFException -> 0x0176, IOException -> 0x015a, all -> 0x013b }
        r2 = r1.getInputStream();	 Catch:{ EOFException -> 0x0176, IOException -> 0x015a, all -> 0x013b }
        r4.<init>(r2);	 Catch:{ EOFException -> 0x0176, IOException -> 0x015a, all -> 0x013b }
    L_0x008e:
        r2 = com.admaster.jice.p007d.HttpURLRequest.m217a(r4);	 Catch:{ EOFException -> 0x017c, IOException -> 0x0161, all -> 0x013f }
    L_0x0092:
        if (r5 == 0) goto L_0x0097;
    L_0x0094:
        r5.close();	 Catch:{ IOException -> 0x012a }
    L_0x0097:
        if (r6 == 0) goto L_0x009c;
    L_0x0099:
        r6.close();	 Catch:{ IOException -> 0x012d }
    L_0x009c:
        if (r4 == 0) goto L_0x00a1;
    L_0x009e:
        r4.close();	 Catch:{ IOException -> 0x0119 }
    L_0x00a1:
        if (r1 == 0) goto L_0x00a6;
    L_0x00a3:
        r1.disconnect();
    L_0x00a6:
        r1 = r2;
    L_0x00a7:
        return r1;
    L_0x00a8:
        r4 = r1.getInputStream();	 Catch:{ EOFException -> 0x0176, IOException -> 0x015a, all -> 0x013b }
        goto L_0x008e;
    L_0x00ad:
        r1 = move-exception;
        r2 = r3;
        r4 = r3;
        r5 = r3;
        r6 = r3;
    L_0x00b2:
        r7 = "JiceSDK.HttpURLRequest";
        r8 = "Failure to connect,likely caused by stream end of file\n";
        com.admaster.jice.p007d.LOG.m225a(r7, r8, r1);	 Catch:{ all -> 0x0144 }
        if (r5 == 0) goto L_0x00be;
    L_0x00bb:
        r5.close();	 Catch:{ IOException -> 0x011e }
    L_0x00be:
        if (r6 == 0) goto L_0x00c3;
    L_0x00c0:
        r6.close();	 Catch:{ IOException -> 0x0120 }
    L_0x00c3:
        if (r2 == 0) goto L_0x00c8;
    L_0x00c5:
        r2.close();	 Catch:{ IOException -> 0x00cf }
    L_0x00c8:
        if (r4 == 0) goto L_0x0183;
    L_0x00ca:
        r4.disconnect();
        r1 = r3;
        goto L_0x00a7;
    L_0x00cf:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00c8;
    L_0x00d4:
        r1 = move-exception;
        r4 = r3;
        r2 = r3;
        r5 = r3;
        r6 = r3;
    L_0x00d9:
        r7 = "JiceSDK.HttpURLRequest";
        r8 = "Failure to connect,caused by IO Exception\n";
        com.admaster.jice.p007d.LOG.m225a(r7, r8, r1);	 Catch:{ all -> 0x0147 }
        if (r5 == 0) goto L_0x00e5;
    L_0x00e2:
        r5.close();	 Catch:{ IOException -> 0x0122 }
    L_0x00e5:
        if (r6 == 0) goto L_0x00ea;
    L_0x00e7:
        r6.close();	 Catch:{ IOException -> 0x0124 }
    L_0x00ea:
        if (r4 == 0) goto L_0x00ef;
    L_0x00ec:
        r4.close();	 Catch:{ IOException -> 0x00f6 }
    L_0x00ef:
        if (r2 == 0) goto L_0x0183;
    L_0x00f1:
        r2.disconnect();
        r1 = r3;
        goto L_0x00a7;
    L_0x00f6:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00ef;
    L_0x00fb:
        r1 = move-exception;
        r4 = r3;
        r5 = r3;
        r6 = r3;
    L_0x00ff:
        if (r5 == 0) goto L_0x0104;
    L_0x0101:
        r5.close();	 Catch:{ IOException -> 0x0126 }
    L_0x0104:
        if (r6 == 0) goto L_0x0109;
    L_0x0106:
        r6.close();	 Catch:{ IOException -> 0x0128 }
    L_0x0109:
        if (r3 == 0) goto L_0x010e;
    L_0x010b:
        r3.close();	 Catch:{ IOException -> 0x0114 }
    L_0x010e:
        if (r4 == 0) goto L_0x0113;
    L_0x0110:
        r4.disconnect();
    L_0x0113:
        throw r1;
    L_0x0114:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x010e;
    L_0x0119:
        r3 = move-exception;
        r3.printStackTrace();
        goto L_0x00a1;
    L_0x011e:
        r1 = move-exception;
        goto L_0x00be;
    L_0x0120:
        r1 = move-exception;
        goto L_0x00c3;
    L_0x0122:
        r1 = move-exception;
        goto L_0x00e5;
    L_0x0124:
        r1 = move-exception;
        goto L_0x00ea;
    L_0x0126:
        r2 = move-exception;
        goto L_0x0104;
    L_0x0128:
        r2 = move-exception;
        goto L_0x0109;
    L_0x012a:
        r3 = move-exception;
        goto L_0x0097;
    L_0x012d:
        r3 = move-exception;
        goto L_0x009c;
    L_0x0130:
        r2 = move-exception;
        r4 = r1;
        r5 = r3;
        r6 = r3;
        r1 = r2;
        goto L_0x00ff;
    L_0x0136:
        r2 = move-exception;
        r4 = r1;
        r5 = r3;
        r1 = r2;
        goto L_0x00ff;
    L_0x013b:
        r2 = move-exception;
        r4 = r1;
        r1 = r2;
        goto L_0x00ff;
    L_0x013f:
        r2 = move-exception;
        r3 = r4;
        r4 = r1;
        r1 = r2;
        goto L_0x00ff;
    L_0x0144:
        r1 = move-exception;
        r3 = r2;
        goto L_0x00ff;
    L_0x0147:
        r1 = move-exception;
        r3 = r4;
        r4 = r2;
        goto L_0x00ff;
    L_0x014b:
        r2 = move-exception;
        r4 = r3;
        r5 = r3;
        r6 = r3;
        r9 = r2;
        r2 = r1;
        r1 = r9;
        goto L_0x00d9;
    L_0x0153:
        r2 = move-exception;
        r4 = r3;
        r5 = r3;
        r9 = r1;
        r1 = r2;
        r2 = r9;
        goto L_0x00d9;
    L_0x015a:
        r2 = move-exception;
        r4 = r3;
        r9 = r2;
        r2 = r1;
        r1 = r9;
        goto L_0x00d9;
    L_0x0161:
        r2 = move-exception;
        r9 = r2;
        r2 = r1;
        r1 = r9;
        goto L_0x00d9;
    L_0x0167:
        r2 = move-exception;
        r4 = r1;
        r5 = r3;
        r6 = r3;
        r1 = r2;
        r2 = r3;
        goto L_0x00b2;
    L_0x016f:
        r2 = move-exception;
        r4 = r1;
        r5 = r3;
        r1 = r2;
        r2 = r3;
        goto L_0x00b2;
    L_0x0176:
        r2 = move-exception;
        r4 = r1;
        r1 = r2;
        r2 = r3;
        goto L_0x00b2;
    L_0x017c:
        r2 = move-exception;
        r9 = r2;
        r2 = r4;
        r4 = r1;
        r1 = r9;
        goto L_0x00b2;
    L_0x0183:
        r1 = r3;
        goto L_0x00a7;
    L_0x0186:
        r4 = r3;
        r2 = r3;
        goto L_0x0092;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.admaster.jice.p007d.HttpURLRequest.mo7716b(java.lang.String, java.lang.String, javax.net.ssl.SSLSocketFactory):byte[]");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0038 A:{SYNTHETIC, Splitter:B:25:0x0038} */
    /* renamed from: a */
    private static byte[] m218a(byte[] r3) {
        /*
        r1 = 0;
        r0 = new java.io.ByteArrayOutputStream;	 Catch:{ Exception -> 0x0024, all -> 0x0035 }
        r0.<init>();	 Catch:{ Exception -> 0x0024, all -> 0x0035 }
        r2 = new java.util.zip.GZIPOutputStream;	 Catch:{ Exception -> 0x0024, all -> 0x0035 }
        r2.<init>(r0);	 Catch:{ Exception -> 0x0024, all -> 0x0035 }
        r2.write(r3);	 Catch:{ Exception -> 0x0044 }
        r2.finish();	 Catch:{ Exception -> 0x0044 }
        r2.close();	 Catch:{ Exception -> 0x0044 }
        r2 = 0;
        r0 = r0.toByteArray();	 Catch:{ Exception -> 0x0024, all -> 0x0035 }
        if (r1 == 0) goto L_0x001e;
    L_0x001b:
        r2.close();	 Catch:{ IOException -> 0x001f }
    L_0x001e:
        return r0;
    L_0x001f:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x001e;
    L_0x0024:
        r0 = move-exception;
        r2 = r1;
    L_0x0026:
        r0.printStackTrace();	 Catch:{ all -> 0x0041 }
        if (r2 == 0) goto L_0x002e;
    L_0x002b:
        r2.close();	 Catch:{ IOException -> 0x0030 }
    L_0x002e:
        r0 = r1;
        goto L_0x001e;
    L_0x0030:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x002e;
    L_0x0035:
        r0 = move-exception;
    L_0x0036:
        if (r1 == 0) goto L_0x003b;
    L_0x0038:
        r1.close();	 Catch:{ IOException -> 0x003c }
    L_0x003b:
        throw r0;
    L_0x003c:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x003b;
    L_0x0041:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0036;
    L_0x0044:
        r0 = move-exception;
        goto L_0x0026;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.admaster.jice.p007d.HttpURLRequest.m218a(byte[]):byte[]");
    }

    /* renamed from: a */
    private static byte[] m217a(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[8192];
        while (true) {
            int read = inputStream.read(bArr, 0, bArr.length);
            if (read == -1) {
                byteArrayOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:47:0x00f8=Splitter:B:47:0x00f8, B:56:0x010e=Splitter:B:56:0x010e} */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0117 A:{SYNTHETIC, Splitter:B:59:0x0117} */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x011c  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0117 A:{SYNTHETIC, Splitter:B:59:0x0117} */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x011c  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0125 A:{SYNTHETIC, Splitter:B:66:0x0125} */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x012a  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0125 A:{SYNTHETIC, Splitter:B:66:0x0125} */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x012a  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0101 A:{SYNTHETIC, Splitter:B:50:0x0101} */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0106  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0117 A:{SYNTHETIC, Splitter:B:59:0x0117} */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x011c  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0125 A:{SYNTHETIC, Splitter:B:66:0x0125} */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x012a  */
    /* renamed from: a */
    public byte[] mo7715a(java.lang.String r8, javax.net.ssl.SSLSocketFactory r9) throws java.io.IOException {
        /*
        r7 = this;
        r3 = 0;
        r1 = new java.net.URL;	 Catch:{ EOFException -> 0x00f5, IOException -> 0x010b, all -> 0x0121 }
        r1.<init>(r8);	 Catch:{ EOFException -> 0x00f5, IOException -> 0x010b, all -> 0x0121 }
        r1 = r1.openConnection();	 Catch:{ EOFException -> 0x00f5, IOException -> 0x010b, all -> 0x0121 }
        r1 = com.newrelic.agent.android.instrumentation.HttpInstrumentation.openConnection(r1);	 Catch:{ EOFException -> 0x00f5, IOException -> 0x010b, all -> 0x0121 }
        r1 = (java.net.HttpURLConnection) r1;	 Catch:{ EOFException -> 0x00f5, IOException -> 0x010b, all -> 0x0121 }
        if (r9 == 0) goto L_0x001d;
    L_0x0012:
        r2 = r1 instanceof javax.net.ssl.HttpsURLConnection;	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
        if (r2 == 0) goto L_0x001d;
    L_0x0016:
        r0 = r1;
        r0 = (javax.net.ssl.HttpsURLConnection) r0;	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
        r2 = r0;
        r2.setSSLSocketFactory(r9);	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
    L_0x001d:
        r2 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r1.setConnectTimeout(r2);	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
        r2 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r1.setReadTimeout(r2);	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
        r2 = "GET";
        r1.setRequestMethod(r2);	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
        r2 = "Content-Type";
        r4 = "application/json";
        r1.setRequestProperty(r2, r4);	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
        r2 = "AcceptEncoding";
        r4 = "gzip,deflate";
        r1.setRequestProperty(r2, r4);	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
        r2 = r7.f130b;	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
        r2 = android.text.TextUtils.isEmpty(r2);	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
        if (r2 != 0) goto L_0x0049;
    L_0x0042:
        r2 = "User-Agent";
        r4 = r7.f130b;	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
        r1.setRequestProperty(r2, r4);	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
    L_0x0049:
        r2 = r1.getResponseCode();	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
        r4 = 301; // 0x12d float:4.22E-43 double:1.487E-321;
        if (r2 == r4) goto L_0x0055;
    L_0x0051:
        r4 = 302; // 0x12e float:4.23E-43 double:1.49E-321;
        if (r2 != r4) goto L_0x0158;
    L_0x0055:
        r2 = "Location";
        r2 = r1.getHeaderField(r2);	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
        r4 = new java.net.URL;	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
        r4.<init>(r2);	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
        r2 = r4.openConnection();	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
        r2 = com.newrelic.agent.android.instrumentation.HttpInstrumentation.openConnection(r2);	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
        r0 = r2;
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
        r1 = r0;
        if (r9 == 0) goto L_0x0085;
    L_0x006e:
        r2 = r1 instanceof javax.net.ssl.HttpsURLConnection;	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
        if (r2 == 0) goto L_0x0085;
    L_0x0072:
        r0 = r1;
        r0 = (javax.net.ssl.HttpsURLConnection) r0;	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
        r2 = r0;
        r2.setSSLSocketFactory(r9);	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
        r0 = r1;
        r0 = (javax.net.ssl.HttpsURLConnection) r0;	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
        r2 = r0;
        r4 = new com.admaster.jice.b.c;	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
        r4.<init>();	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
        r2.setHostnameVerifier(r4);	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
    L_0x0085:
        r2 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r1.setConnectTimeout(r2);	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
        r2 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r1.setReadTimeout(r2);	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
        r2 = "GET";
        r1.setRequestMethod(r2);	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
        r2 = "Content-Type";
        r4 = "application/json";
        r1.setRequestProperty(r2, r4);	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
        r2 = "AcceptEncoding";
        r4 = "gzip,deflate";
        r1.setRequestProperty(r2, r4);	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
        r2 = r7.f130b;	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
        r2 = android.text.TextUtils.isEmpty(r2);	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
        if (r2 != 0) goto L_0x00b1;
    L_0x00aa:
        r2 = "User-Agent";
        r4 = r7.f130b;	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
        r1.setRequestProperty(r2, r4);	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
    L_0x00b1:
        r2 = f129a;	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
        if (r2 == 0) goto L_0x00bc;
    L_0x00b5:
        r2 = "Content-Encoding";
        r4 = "gzip";
        r1.setRequestProperty(r2, r4);	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
    L_0x00bc:
        r2 = r1.getResponseCode();	 Catch:{ EOFException -> 0x0149, IOException -> 0x013f, all -> 0x0136 }
        r4 = r1;
        r1 = r2;
    L_0x00c2:
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r1 != r2) goto L_0x0155;
    L_0x00c6:
        r1 = r4.getContentEncoding();	 Catch:{ EOFException -> 0x014e, IOException -> 0x0144, all -> 0x013a }
        r2 = android.text.TextUtils.isEmpty(r1);	 Catch:{ EOFException -> 0x014e, IOException -> 0x0144, all -> 0x013a }
        if (r2 != 0) goto L_0x00f0;
    L_0x00d0:
        r2 = "gzip";
        r1 = r1.contains(r2);	 Catch:{ EOFException -> 0x014e, IOException -> 0x0144, all -> 0x013a }
        if (r1 == 0) goto L_0x00f0;
    L_0x00d8:
        r2 = new java.util.zip.GZIPInputStream;	 Catch:{ EOFException -> 0x014e, IOException -> 0x0144, all -> 0x013a }
        r1 = r4.getInputStream();	 Catch:{ EOFException -> 0x014e, IOException -> 0x0144, all -> 0x013a }
        r2.<init>(r1);	 Catch:{ EOFException -> 0x014e, IOException -> 0x0144, all -> 0x013a }
    L_0x00e1:
        r1 = com.admaster.jice.p007d.HttpURLRequest.m217a(r2);	 Catch:{ EOFException -> 0x0151, IOException -> 0x0147 }
    L_0x00e5:
        if (r2 == 0) goto L_0x00ea;
    L_0x00e7:
        r2.close();	 Catch:{ IOException -> 0x0134 }
    L_0x00ea:
        if (r4 == 0) goto L_0x00ef;
    L_0x00ec:
        r4.disconnect();
    L_0x00ef:
        return r1;
    L_0x00f0:
        r2 = r4.getInputStream();	 Catch:{ EOFException -> 0x014e, IOException -> 0x0144, all -> 0x013a }
        goto L_0x00e1;
    L_0x00f5:
        r1 = move-exception;
        r2 = r3;
        r4 = r3;
    L_0x00f8:
        r5 = "JiceSDK.HttpURLRequest";
        r6 = "Failure to connect,likely caused by stream end of file\n";
        com.admaster.jice.p007d.LOG.m225a(r5, r6, r1);	 Catch:{ all -> 0x013c }
        if (r2 == 0) goto L_0x0104;
    L_0x0101:
        r2.close();	 Catch:{ IOException -> 0x012e }
    L_0x0104:
        if (r4 == 0) goto L_0x0153;
    L_0x0106:
        r4.disconnect();
        r1 = r3;
        goto L_0x00ef;
    L_0x010b:
        r1 = move-exception;
        r2 = r3;
        r4 = r3;
    L_0x010e:
        r5 = "JiceSDK.HttpURLRequest";
        r6 = "Failure to connect,caused by IO Exception\n";
        com.admaster.jice.p007d.LOG.m225a(r5, r6, r1);	 Catch:{ all -> 0x013c }
        if (r2 == 0) goto L_0x011a;
    L_0x0117:
        r2.close();	 Catch:{ IOException -> 0x0130 }
    L_0x011a:
        if (r4 == 0) goto L_0x0153;
    L_0x011c:
        r4.disconnect();
        r1 = r3;
        goto L_0x00ef;
    L_0x0121:
        r1 = move-exception;
        r4 = r3;
    L_0x0123:
        if (r3 == 0) goto L_0x0128;
    L_0x0125:
        r3.close();	 Catch:{ IOException -> 0x0132 }
    L_0x0128:
        if (r4 == 0) goto L_0x012d;
    L_0x012a:
        r4.disconnect();
    L_0x012d:
        throw r1;
    L_0x012e:
        r1 = move-exception;
        goto L_0x0104;
    L_0x0130:
        r1 = move-exception;
        goto L_0x011a;
    L_0x0132:
        r2 = move-exception;
        goto L_0x0128;
    L_0x0134:
        r2 = move-exception;
        goto L_0x00ea;
    L_0x0136:
        r2 = move-exception;
        r4 = r1;
        r1 = r2;
        goto L_0x0123;
    L_0x013a:
        r1 = move-exception;
        goto L_0x0123;
    L_0x013c:
        r1 = move-exception;
        r3 = r2;
        goto L_0x0123;
    L_0x013f:
        r2 = move-exception;
        r4 = r1;
        r1 = r2;
        r2 = r3;
        goto L_0x010e;
    L_0x0144:
        r1 = move-exception;
        r2 = r3;
        goto L_0x010e;
    L_0x0147:
        r1 = move-exception;
        goto L_0x010e;
    L_0x0149:
        r2 = move-exception;
        r4 = r1;
        r1 = r2;
        r2 = r3;
        goto L_0x00f8;
    L_0x014e:
        r1 = move-exception;
        r2 = r3;
        goto L_0x00f8;
    L_0x0151:
        r1 = move-exception;
        goto L_0x00f8;
    L_0x0153:
        r1 = r3;
        goto L_0x00ef;
    L_0x0155:
        r2 = r3;
        r1 = r3;
        goto L_0x00e5;
    L_0x0158:
        r4 = r1;
        r1 = r2;
        goto L_0x00c2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.admaster.jice.p007d.HttpURLRequest.mo7715a(java.lang.String, javax.net.ssl.SSLSocketFactory):byte[]");
    }
}
