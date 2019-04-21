package com.alipay.sdk.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

/* renamed from: com.alipay.sdk.util.a */
public class C0644a {
    /* renamed from: e */
    private static C0644a f630e = null;
    /* renamed from: b */
    private String f631b;
    /* renamed from: c */
    private String f632c;
    /* renamed from: d */
    private String f633d;

    /* renamed from: a */
    public static C0644a m1004a(Context context) {
        if (f630e == null) {
            f630e = new C0644a(context);
        }
        return f630e;
    }

    /* JADX WARNING: Failed to extract finally block: empty outs */
    private C0644a(android.content.Context r3) {
        /*
        r2 = this;
        r2.<init>();
        r0 = r3.getApplicationContext();	 Catch:{ Exception -> 0x0041 }
        r1 = "phone";
        r0 = r0.getSystemService(r1);	 Catch:{ Exception -> 0x0041 }
        r0 = (android.telephony.TelephonyManager) r0;	 Catch:{ Exception -> 0x0041 }
        r1 = r0.getDeviceId();	 Catch:{ Exception -> 0x0041 }
        r2.mo8105b(r1);	 Catch:{ Exception -> 0x0041 }
        r0 = r0.getSubscriberId();	 Catch:{ Exception -> 0x0041 }
        r2.mo8103a(r0);	 Catch:{ Exception -> 0x0041 }
        r0 = r3.getApplicationContext();	 Catch:{ Exception -> 0x0041 }
        r1 = "wifi";
        r0 = r0.getSystemService(r1);	 Catch:{ Exception -> 0x0041 }
        r0 = (android.net.wifi.WifiManager) r0;	 Catch:{ Exception -> 0x0041 }
        r0 = r0.getConnectionInfo();	 Catch:{ Exception -> 0x0041 }
        r0 = r0.getMacAddress();	 Catch:{ Exception -> 0x0041 }
        r2.f633d = r0;	 Catch:{ Exception -> 0x0041 }
        r0 = r2.f633d;
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 == 0) goto L_0x0040;
    L_0x003c:
        r0 = "00:00:00:00:00:00";
        r2.f633d = r0;
    L_0x0040:
        return;
    L_0x0041:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0052 }
        r0 = r2.f633d;
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 == 0) goto L_0x0040;
    L_0x004d:
        r0 = "00:00:00:00:00:00";
        r2.f633d = r0;
        goto L_0x0040;
    L_0x0052:
        r0 = move-exception;
        r1 = r2.f633d;
        r1 = android.text.TextUtils.isEmpty(r1);
        if (r1 == 0) goto L_0x005f;
    L_0x005b:
        r1 = "00:00:00:00:00:00";
        r2.f633d = r1;
    L_0x005f:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.util.C0644a.<init>(android.content.Context):void");
    }

    /* renamed from: a */
    public String mo8102a() {
        if (TextUtils.isEmpty(this.f631b)) {
            this.f631b = "000000000000000";
        }
        return this.f631b;
    }

    /* renamed from: b */
    public String mo8104b() {
        if (TextUtils.isEmpty(this.f632c)) {
            this.f632c = "000000000000000";
        }
        return this.f632c;
    }

    /* renamed from: a */
    public void mo8103a(String str) {
        if (str != null) {
            str = (str + "000000000000000").substring(0, 15);
        }
        this.f631b = str;
    }

    /* renamed from: b */
    public void mo8105b(String str) {
        if (str != null) {
            byte[] bytes = str.getBytes();
            int i = 0;
            while (i < bytes.length) {
                if (bytes[i] < (byte) 48 || bytes[i] > (byte) 57) {
                    bytes[i] = (byte) 48;
                }
                i++;
            }
            str = (new String(bytes) + "000000000000000").substring(0, 15);
        }
        this.f632c = str;
    }

    /* renamed from: c */
    public String mo8106c() {
        String str = mo8104b() + "|";
        String a = mo8102a();
        if (TextUtils.isEmpty(a)) {
            return str + "000000000000000";
        }
        return str + a;
    }

    /* renamed from: d */
    public String mo8107d() {
        return this.f633d;
    }

    /* renamed from: b */
    public static C0647d m1005b(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.getType() == 0) {
                return C0647d.m1020a(activeNetworkInfo.getSubtype());
            }
            if (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) {
                return C0647d.NONE;
            }
            return C0647d.WIFI;
        } catch (Exception e) {
            return C0647d.NONE;
        }
    }

    /* renamed from: c */
    public static String m1006c(Context context) {
        return C0644a.m1004a(context).mo8106c().substring(0, 8);
    }

    /* renamed from: d */
    public static String m1007d(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return context.getResources().getConfiguration().locale.toString();
        } catch (Throwable th) {
            return "";
        }
    }
}
