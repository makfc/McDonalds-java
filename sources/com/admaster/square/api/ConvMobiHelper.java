package com.admaster.square.api;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.admaster.square.utils.AndroidUtil;
import com.admaster.square.utils.C0493n;
import com.admaster.square.utils.Constant;
import com.admaster.square.utils.CustomPreferenceUtil;
import com.admaster.square.utils.FailedMessage;
import com.admaster.square.utils.Order;
import com.admaster.square.utils.UdidInfoUtil;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;

/* renamed from: com.admaster.square.api.c */
public class ConvMobiHelper {
    /* renamed from: h */
    private static ConvMobiHelper f164h;
    /* renamed from: a */
    private DeviceInfo f165a = null;
    /* renamed from: b */
    private DeviceParamBuilder f166b = null;
    /* renamed from: c */
    private Context f167c = null;
    /* renamed from: d */
    private ConcurrentHashMap<String, String> f168d;
    /* renamed from: e */
    private ConcurrentHashMap<String, String> f169e;
    /* renamed from: f */
    private String f170f = null;
    /* renamed from: g */
    private String f171g = null;
    /* renamed from: i */
    private String f172i = "";

    private ConvMobiHelper(Context context, DeviceInfo deviceInfo) {
        this.f165a = deviceInfo;
        this.f166b = new DeviceParamBuilder(deviceInfo);
        this.f167c = context;
        this.f169e = this.f166b.mo7773a();
        this.f170f = CustomPreferenceUtil.m409a(context, "sp_store", "m2id");
        this.f171g = CustomPreferenceUtil.m409a(context, "sp_store", "ch");
        this.f168d = new ConcurrentHashMap();
    }

    /* renamed from: a */
    public static synchronized ConvMobiHelper m299a(Context context, DeviceInfo deviceInfo) {
        ConvMobiHelper convMobiHelper;
        synchronized (ConvMobiHelper.class) {
            if (f164h == null) {
                f164h = new ConvMobiHelper(context, deviceInfo);
            }
            convMobiHelper = f164h;
        }
        return convMobiHelper;
    }

    /* renamed from: a */
    private void m301a() {
        if (this.f168d != null && this.f168d.size() > 0) {
            this.f168d.clear();
        }
        m306b();
    }

    /* renamed from: b */
    private void m306b() {
        AndroidUtil.m393a(this.f168d, "os", this.f165a.f221o, true);
        AndroidUtil.m393a(this.f168d, "sdkv", this.f165a.f222p, true);
        AndroidUtil.m393a(this.f168d, "appv", this.f165a.f223q, true);
        AndroidUtil.m393a(this.f168d, "package_name", this.f165a.f224r, true);
        AndroidUtil.m393a(this.f168d, "deviceID", AndroidUtil.m392a(this.f169e), false);
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:6:0x0013, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:7:0x0014, code skipped:
            com.admaster.square.api.Logger.m364b(r0.getMessage());
     */
    /* renamed from: a */
    public void mo7751a(java.lang.String r7, java.lang.Object r8, com.admaster.square.api.CustomEvent r9, long r10) {
        /*
        r6 = this;
        r0 = java.util.concurrent.TimeUnit.SECONDS;	 Catch:{ InterruptedException -> 0x0013 }
        r0.sleep(r10);	 Catch:{ InterruptedException -> 0x0013 }
    L_0x0005:
        r0 = r6.f170f;	 Catch:{ Exception -> 0x001c }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x001c }
        if (r0 == 0) goto L_0x0025;
    L_0x000d:
        r0 = "m2id cann't be null or empty!";
        com.admaster.square.api.Logger.m364b(r0);	 Catch:{ Exception -> 0x001c }
    L_0x0012:
        return;
    L_0x0013:
        r0 = move-exception;
        r0 = r0.getMessage();	 Catch:{ Exception -> 0x001c }
        com.admaster.square.api.Logger.m364b(r0);	 Catch:{ Exception -> 0x001c }
        goto L_0x0005;
    L_0x001c:
        r0 = move-exception;
        r0 = r0.getMessage();
        com.admaster.square.api.Logger.m364b(r0);
        goto L_0x0012;
    L_0x0025:
        r0 = r6.m307c();	 Catch:{ Exception -> 0x001c }
        r6.f172i = r0;	 Catch:{ Exception -> 0x001c }
        r0 = r6.f169e;	 Catch:{ Exception -> 0x001c }
        r1 = "adm_id";
        r2 = r6.f172i;	 Catch:{ Exception -> 0x001c }
        r0.put(r1, r2);	 Catch:{ Exception -> 0x001c }
        r6.m301a();	 Catch:{ Exception -> 0x001c }
        r0 = r6.f168d;	 Catch:{ Exception -> 0x001c }
        r1 = "m2id";
        r2 = r6.f170f;	 Catch:{ Exception -> 0x001c }
        r3 = 1;
        com.admaster.square.utils.AndroidUtil.m393a(r0, r1, r2, r3);	 Catch:{ Exception -> 0x001c }
        r0 = 0;
        if (r8 == 0) goto L_0x0048;
    L_0x0044:
        r0 = r8.toString();	 Catch:{ Exception -> 0x001c }
    L_0x0048:
        r1 = r9.name();	 Catch:{ Exception -> 0x001c }
        r1 = r1.toLowerCase();	 Catch:{ Exception -> 0x001c }
        r2 = r6.f171g;	 Catch:{ Exception -> 0x001c }
        r2 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x001c }
        if (r2 != 0) goto L_0x0062;
    L_0x0058:
        r2 = r6.f168d;	 Catch:{ Exception -> 0x001c }
        r3 = "ch";
        r4 = r6.f171g;	 Catch:{ Exception -> 0x001c }
        r5 = 1;
        com.admaster.square.utils.AndroidUtil.m393a(r2, r3, r4, r5);	 Catch:{ Exception -> 0x001c }
    L_0x0062:
        r2 = r6.f168d;	 Catch:{ Exception -> 0x001c }
        r3 = "type";
        r4 = 1;
        com.admaster.square.utils.AndroidUtil.m393a(r2, r3, r1, r4);	 Catch:{ Exception -> 0x001c }
        r1 = android.text.TextUtils.isEmpty(r7);	 Catch:{ Exception -> 0x001c }
        if (r1 != 0) goto L_0x007a;
    L_0x0071:
        r1 = r6.f168d;	 Catch:{ Exception -> 0x001c }
        r2 = "uid";
        r3 = 1;
        com.admaster.square.utils.AndroidUtil.m393a(r1, r2, r7, r3);	 Catch:{ Exception -> 0x001c }
    L_0x007a:
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x001c }
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x001c }
        r2 = java.lang.String.valueOf(r2);	 Catch:{ Exception -> 0x001c }
        r1.<init>(r2);	 Catch:{ Exception -> 0x001c }
        r1 = r1.toString();	 Catch:{ Exception -> 0x001c }
        r2 = r6.f167c;	 Catch:{ Exception -> 0x001c }
        r3 = "it";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x001c }
        r5 = 0;
        r4[r5] = r1;	 Catch:{ Exception -> 0x001c }
        r2 = com.admaster.square.utils.UdidInfoUtil.m480a(r2, r3, r4);	 Catch:{ Exception -> 0x001c }
        r3 = r6.f168d;	 Catch:{ Exception -> 0x001c }
        r4 = "it";
        r5 = 1;
        com.admaster.square.utils.AndroidUtil.m393a(r3, r4, r2, r5);	 Catch:{ Exception -> 0x001c }
        r2 = r6.f168d;	 Catch:{ Exception -> 0x001c }
        r3 = "at";
        r4 = 1;
        com.admaster.square.utils.AndroidUtil.m393a(r2, r3, r1, r4);	 Catch:{ Exception -> 0x001c }
        r2 = com.admaster.square.api.CustomEvent.ADORDER;	 Catch:{ Exception -> 0x001c }
        if (r9 == r2) goto L_0x00b1;
    L_0x00ad:
        r2 = com.admaster.square.api.CustomEvent.ADPURCHASE;	 Catch:{ Exception -> 0x001c }
        if (r9 != r2) goto L_0x00d7;
    L_0x00b1:
        if (r8 == 0) goto L_0x00d7;
    L_0x00b3:
        r2 = r6.f167c;	 Catch:{ Exception -> 0x001c }
        r8 = (com.admaster.square.utils.Order) r8;	 Catch:{ Exception -> 0x001c }
        r2 = r6.m300a(r2, r1, r8, r7);	 Catch:{ Exception -> 0x001c }
        r3 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x001c }
        if (r3 != 0) goto L_0x00c9;
    L_0x00c1:
        r3 = r6.f168d;	 Catch:{ Exception -> 0x001c }
        r4 = "orderSign";
        r5 = 1;
        com.admaster.square.utils.AndroidUtil.m393a(r3, r4, r2, r5);	 Catch:{ Exception -> 0x001c }
    L_0x00c9:
        r2 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x001c }
        if (r2 != 0) goto L_0x00d7;
    L_0x00cf:
        r2 = r6.f168d;	 Catch:{ Exception -> 0x001c }
        r3 = "orderMessages";
        r4 = 0;
        com.admaster.square.utils.AndroidUtil.m393a(r2, r3, r0, r4);	 Catch:{ Exception -> 0x001c }
    L_0x00d7:
        r0 = r6.f168d;	 Catch:{ Exception -> 0x001c }
        r0 = com.admaster.square.utils.AndroidUtil.m392a(r0);	 Catch:{ Exception -> 0x001c }
        r6.m305a(r0, r9, r1);	 Catch:{ Exception -> 0x001c }
        goto L_0x0012;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.admaster.square.api.ConvMobiHelper.mo7751a(java.lang.String, java.lang.Object, com.admaster.square.api.CustomEvent, long):void");
    }

    /* renamed from: c */
    private String m307c() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(UdidInfoUtil.m482a(Constant.f266h)).append('|').append(UdidInfoUtil.m482a(this.f165a.f207a)).append('|').append(UdidInfoUtil.m482a(this.f165a.f208b)).append('|').append(UdidInfoUtil.m482a(this.f165a.f209c)).append('|').append(UdidInfoUtil.m482a(this.f165a.f210d)).append('|').append(UdidInfoUtil.m482a(this.f165a.f211e));
            return C0493n.m447a(stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: a */
    public String mo7749a(String str, String str2, String str3, CustomEvent customEvent) {
        try {
            if (TextUtils.isEmpty(this.f170f)) {
                Logger.m364b("m2id cann't be null or empty!");
                return null;
            }
            m301a();
            AndroidUtil.m393a(this.f168d, "m2id", this.f170f, true);
            String toLowerCase = customEvent.name().toLowerCase();
            if (!TextUtils.isEmpty(this.f171g)) {
                AndroidUtil.m393a(this.f168d, "ch", this.f171g, true);
            }
            AndroidUtil.m393a(this.f168d, "type", toLowerCase, true);
            if (!TextUtils.isEmpty(str3)) {
                AndroidUtil.m393a(this.f168d, "uid", str3, true);
            }
            AndroidUtil.m393a(this.f168d, "it", UdidInfoUtil.m480a(this.f167c, "it", new StringBuilder(String.valueOf(System.currentTimeMillis())).toString()), true);
            AndroidUtil.m393a(this.f168d, "at", toLowerCase, true);
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            AndroidUtil.m393a(this.f168d, "exception_message", str, true);
            AndroidUtil.m393a(this.f168d, "exception_time", str2, true);
            return AndroidUtil.m392a(this.f168d);
        } catch (Exception e) {
            Logger.m364b(e.getMessage());
            return null;
        }
    }

    /* renamed from: a */
    private String m300a(Context context, String str, Order order, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append(str.substring(0, 8));
        } catch (Exception e) {
            stringBuilder.append(str);
            Logger.m364b(e.getMessage());
        }
        try {
            stringBuilder.append("|");
            stringBuilder.append(this.f172i);
            stringBuilder.append("|");
            if (order != null) {
                try {
                    if (!TextUtils.isEmpty(order.getString(Order.od_orderid))) {
                        stringBuilder.append(order.getString(Order.od_orderid));
                        stringBuilder.append("|");
                    }
                    if (!TextUtils.isEmpty(order.getString(Order.od_orderAcount))) {
                        stringBuilder.append(order.getString(Order.od_orderAcount));
                        stringBuilder.append("|");
                    }
                } catch (JSONException e2) {
                    Logger.m364b(e2.getMessage());
                    return null;
                }
            }
            stringBuilder.append(str2);
            return C0493n.m447a(stringBuilder.toString());
        } catch (Exception e3) {
            Logger.m364b(e3.getMessage());
            return null;
        }
    }

    /* renamed from: a */
    private void m305a(String str, CustomEvent customEvent, String str2) {
        new Thread(new C0480d(this, str, customEvent, str2)).start();
    }

    /* renamed from: a */
    public static void m302a(Context context) {
        CustomPreferenceUtil.m412a(context, "sp_store", "is_install", true);
    }

    /* renamed from: a */
    public void mo7750a(Context context, String str, String str2) {
        Object failedMessage = new FailedMessage();
        try {
            failedMessage.mo7828a(Long.parseLong(str));
            failedMessage.mo7831b(Long.parseLong(str));
        } catch (Exception e) {
            Logger.m364b(e.getMessage());
        }
        failedMessage.mo7827a(1);
        failedMessage.mo7829a(str2);
        CustomPreferenceUtil.m410a(context, "com.admaster.convmobisdk.falied", Base64.encodeToString(str2.getBytes(), 2), failedMessage);
    }

    /* renamed from: a */
    private void m303a(Context context, String str) {
        try {
            CustomPreferenceUtil.m411a(context, "com.admaster.convmobisdk.failed.active", Base64.encodeToString(str.getBytes(), 2), new StringBuilder(String.valueOf(System.currentTimeMillis())).toString());
        } catch (Exception e) {
            Logger.m364b(e.getMessage());
        }
    }
}
