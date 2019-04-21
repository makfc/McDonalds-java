package com.alipay.sdk.tid;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.alipay.sdk.encrypt.C0620b;
import com.alipay.sdk.util.C0646c;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.Random;
import org.json.JSONObject;

/* renamed from: com.alipay.sdk.tid.b */
public class C0643b {
    /* renamed from: i */
    private static Context f622i = null;
    /* renamed from: o */
    private static C0643b f623o;
    /* renamed from: j */
    private String f624j;
    /* renamed from: k */
    private String f625k;
    /* renamed from: l */
    private long f626l;
    /* renamed from: m */
    private String f627m;
    /* renamed from: n */
    private String f628n;
    /* renamed from: p */
    private boolean f629p = false;

    /* renamed from: com.alipay.sdk.tid.b$a */
    public static class C0642a {
        /* renamed from: b */
        public static void m988b(String str, String str2) {
            if (C0643b.f622i != null) {
                C0643b.f622i.getSharedPreferences(str, 0).edit().remove(str2).apply();
            }
        }

        /* renamed from: d */
        public static boolean m989d(String str, String str2) {
            if (C0643b.f622i == null) {
                return false;
            }
            return C0643b.f622i.getSharedPreferences(str, 0).contains(str2);
        }

        /* renamed from: a */
        public static String m985a(String str, String str2, boolean z) {
            String str3 = null;
            if (C0643b.f622i != null) {
                String string = C0643b.f622i.getSharedPreferences(str, 0).getString(str2, null);
                if (TextUtils.isEmpty(string) || !z) {
                    str3 = string;
                } else {
                    str3 = C0620b.m901b(string, C0642a.m987b());
                    if (TextUtils.isEmpty(str3)) {
                        str3 = C0620b.m901b(string, C0642a.m984a());
                        if (!TextUtils.isEmpty(str3)) {
                            C0642a.m986a(str, str2, str3, true);
                        }
                    }
                    if (TextUtils.isEmpty(str3)) {
                        String.format("LocalPreference::getLocalPreferences failed %s，%s", new Object[]{string, r2});
                        C0646c.m1017b("TidStorage", "TidStorage.save LocalPreference::getLocalPreferences failed");
                    }
                }
                C0646c.m1017b("TidStorage", "TidStorage.save LocalPreference::getLocalPreferences value " + string);
            }
            return str3;
        }

        /* renamed from: a */
        public static void m986a(String str, String str2, String str3, boolean z) {
            if (C0643b.f622i != null) {
                SharedPreferences sharedPreferences = C0643b.f622i.getSharedPreferences(str, 0);
                if (z) {
                    String a = C0620b.m900a(str3, C0642a.m987b());
                    if (TextUtils.isEmpty(a)) {
                        String.format("LocalPreference::putLocalPreferences failed %s，%s", new Object[]{str3, r2});
                        str3 = a;
                    } else {
                        str3 = a;
                    }
                }
                sharedPreferences.edit().putString(str2, str3).apply();
            }
        }

        /* renamed from: a */
        private static String m984a() {
            return "!@#23457";
        }

        /* renamed from: b */
        private static String m987b() {
            String str = "";
            try {
                str = C0643b.f622i.getApplicationContext().getPackageName();
            } catch (Throwable th) {
                C0646c.m1016a(th);
            }
            if (TextUtils.isEmpty(str)) {
                str = "unknow";
            }
            return (str + "00000000").substring(0, 8);
        }
    }

    /* renamed from: a */
    public static synchronized C0643b m990a(Context context) {
        C0643b c0643b;
        synchronized (C0643b.class) {
            if (f623o == null) {
                C0646c.m1017b("TidStorage", "getInstance");
                f623o = new C0643b();
            }
            if (f622i == null) {
                f623o.m992b(context);
            }
            c0643b = f623o;
        }
        return c0643b;
    }

    /* renamed from: b */
    private void m992b(Context context) {
        if (context != null) {
            C0646c.m1017b("TidStorage", "TidStorage.initialize context != null");
            f622i = context.getApplicationContext();
        }
        if (!this.f629p) {
            this.f629p = true;
            m994k();
            m995l();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00bc  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00bc  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00a1  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0089  */
    /* renamed from: k */
    private void m994k() {
        /*
        r7 = this;
        r3 = f622i;
        if (r3 != 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r0 = "alipay_tid_storage";
        r1 = "upgraded_from_db";
        r0 = com.alipay.sdk.tid.C0643b.C0642a.m989d(r0, r1);
        if (r0 == 0) goto L_0x0019;
    L_0x0010:
        r0 = "TidStorage";
        r1 = "transferTidFromOldDb: already migrated. returning";
        com.alipay.sdk.util.C0646c.m1017b(r0, r1);
        goto L_0x0004;
    L_0x0019:
        r2 = 0;
        r0 = "TidStorage";
        r1 = "transferTidFromOldDb: tid from db: ";
        com.alipay.sdk.util.C0646c.m1017b(r0, r1);	 Catch:{ Throwable -> 0x009a, all -> 0x00a6 }
        r1 = new com.alipay.sdk.tid.a;	 Catch:{ Throwable -> 0x009a, all -> 0x00a6 }
        r1.<init>(r3);	 Catch:{ Throwable -> 0x009a, all -> 0x00a6 }
        r0 = com.alipay.sdk.util.C0644a.m1004a(r3);	 Catch:{ Throwable -> 0x00ca }
        r0 = r0.mo8104b();	 Catch:{ Throwable -> 0x00ca }
        r2 = com.alipay.sdk.util.C0644a.m1004a(r3);	 Catch:{ Throwable -> 0x00ca }
        r2 = r2.mo8102a();	 Catch:{ Throwable -> 0x00ca }
        r4 = r1.mo8091a(r0, r2);	 Catch:{ Throwable -> 0x00ca }
        r0 = r1.mo8093b(r0, r2);	 Catch:{ Throwable -> 0x00ca }
        r2 = android.text.TextUtils.isEmpty(r4);	 Catch:{ Throwable -> 0x00ca }
        if (r2 != 0) goto L_0x0071;
    L_0x0045:
        r2 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Throwable -> 0x00ca }
        if (r2 != 0) goto L_0x0071;
    L_0x004b:
        r2 = "TidStorage";
        r5 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00ca }
        r5.<init>();	 Catch:{ Throwable -> 0x00ca }
        r6 = "transferTidFromOldDb: tid from db is ";
        r5 = r5.append(r6);	 Catch:{ Throwable -> 0x00ca }
        r5 = r5.append(r4);	 Catch:{ Throwable -> 0x00ca }
        r6 = ", ";
        r5 = r5.append(r6);	 Catch:{ Throwable -> 0x00ca }
        r5 = r5.append(r0);	 Catch:{ Throwable -> 0x00ca }
        r5 = r5.toString();	 Catch:{ Throwable -> 0x00ca }
        com.alipay.sdk.util.C0646c.m1017b(r2, r5);	 Catch:{ Throwable -> 0x00ca }
        r7.mo8097a(r4, r0);	 Catch:{ Throwable -> 0x00ca }
    L_0x0071:
        if (r1 == 0) goto L_0x00cc;
    L_0x0073:
        r1.close();
        r2 = r1;
    L_0x0077:
        r0 = "TidStorage";
        r1 = "transferTidFromOldDb: removing database table";
        com.alipay.sdk.util.C0646c.m1017b(r0, r1);	 Catch:{ Throwable -> 0x00ae, all -> 0x00b9 }
        r1 = new com.alipay.sdk.tid.a;	 Catch:{ Throwable -> 0x00ae, all -> 0x00b9 }
        r1.<init>(r3);	 Catch:{ Throwable -> 0x00ae, all -> 0x00b9 }
        r1.mo8092a();	 Catch:{ Throwable -> 0x00c6, all -> 0x00c0 }
        if (r1 == 0) goto L_0x008c;
    L_0x0089:
        r1.close();
    L_0x008c:
        r0 = "alipay_tid_storage";
        r1 = "upgraded_from_db";
        r2 = "updated";
        r3 = 0;
        com.alipay.sdk.tid.C0643b.C0642a.m986a(r0, r1, r2, r3);
        goto L_0x0004;
    L_0x009a:
        r0 = move-exception;
        r1 = r2;
    L_0x009c:
        com.alipay.sdk.util.C0646c.m1016a(r0);	 Catch:{ all -> 0x00c8 }
        if (r1 == 0) goto L_0x00cc;
    L_0x00a1:
        r1.close();
        r2 = r1;
        goto L_0x0077;
    L_0x00a6:
        r0 = move-exception;
        r1 = r2;
    L_0x00a8:
        if (r1 == 0) goto L_0x00ad;
    L_0x00aa:
        r1.close();
    L_0x00ad:
        throw r0;
    L_0x00ae:
        r0 = move-exception;
        r1 = r2;
    L_0x00b0:
        com.alipay.sdk.util.C0646c.m1016a(r0);	 Catch:{ all -> 0x00c3 }
        if (r1 == 0) goto L_0x008c;
    L_0x00b5:
        r1.close();
        goto L_0x008c;
    L_0x00b9:
        r0 = move-exception;
    L_0x00ba:
        if (r2 == 0) goto L_0x00bf;
    L_0x00bc:
        r2.close();
    L_0x00bf:
        throw r0;
    L_0x00c0:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00ba;
    L_0x00c3:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00ba;
    L_0x00c6:
        r0 = move-exception;
        goto L_0x00b0;
    L_0x00c8:
        r0 = move-exception;
        goto L_0x00a8;
    L_0x00ca:
        r0 = move-exception;
        goto L_0x009c;
    L_0x00cc:
        r2 = r1;
        goto L_0x0077;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.tid.C0643b.m994k():void");
    }

    /* renamed from: a */
    public String mo8096a() {
        C0646c.m1017b("TidStorage", "TidStorage.getTid " + this.f624j);
        return this.f624j;
    }

    /* renamed from: b */
    public String mo8098b() {
        C0646c.m1017b("TidStorage", "TidStorage.getClientKey " + this.f625k);
        return this.f625k;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0094  */
    /* renamed from: l */
    private void m995l() {
        /*
        r8 = this;
        r0 = 0;
        r2 = java.lang.System.currentTimeMillis();
        r1 = java.lang.Long.valueOf(r2);
        r2 = "alipay_tid_storage";
        r3 = "tidinfo";
        r4 = 1;
        r2 = com.alipay.sdk.tid.C0643b.C0642a.m985a(r2, r3, r4);	 Catch:{ Exception -> 0x0098 }
        r3 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x0098 }
        if (r3 != 0) goto L_0x00c2;
    L_0x0018:
        r3 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0098 }
        r5 = com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation.init(r2);	 Catch:{ Exception -> 0x0098 }
        r2 = "tid";
        r3 = "";
        r4 = r5.optString(r2, r3);	 Catch:{ Exception -> 0x0098 }
        r2 = "client_key";
        r3 = "";
        r3 = r5.optString(r2, r3);	 Catch:{ Exception -> 0x00b1 }
        r2 = "timestamp";
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x00b7 }
        r6 = r5.optLong(r2, r6);	 Catch:{ Exception -> 0x00b7 }
        r2 = java.lang.Long.valueOf(r6);	 Catch:{ Exception -> 0x00b7 }
        r1 = "vimei";
        r6 = "";
        r1 = r5.optString(r1, r6);	 Catch:{ Exception -> 0x00bc }
        r6 = "vimsi";
        r7 = "";
        r0 = r5.optString(r6, r7);	 Catch:{ Exception -> 0x00c0 }
    L_0x004e:
        r5 = "TidStorage";
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r7 = "TidStorage.load ";
        r6 = r6.append(r7);
        r6 = r6.append(r4);
        r7 = " ";
        r6 = r6.append(r7);
        r6 = r6.append(r3);
        r7 = " ";
        r6 = r6.append(r7);
        r6 = r6.append(r2);
        r7 = " ";
        r6 = r6.append(r7);
        r6 = r6.append(r1);
        r7 = " ";
        r6 = r6.append(r7);
        r6 = r6.append(r0);
        r6 = r6.toString();
        com.alipay.sdk.util.C0646c.m1017b(r5, r6);
        r5 = r8.m991a(r4, r3, r1, r0);
        if (r5 == 0) goto L_0x00a2;
    L_0x0094:
        r8.m996m();
    L_0x0097:
        return;
    L_0x0098:
        r2 = move-exception;
        r5 = r2;
        r3 = r0;
        r4 = r0;
        r2 = r1;
        r1 = r0;
    L_0x009e:
        com.alipay.sdk.util.C0646c.m1016a(r5);
        goto L_0x004e;
    L_0x00a2:
        r8.f624j = r4;
        r8.f625k = r3;
        r2 = r2.longValue();
        r8.f626l = r2;
        r8.f627m = r1;
        r8.f628n = r0;
        goto L_0x0097;
    L_0x00b1:
        r2 = move-exception;
        r5 = r2;
        r3 = r0;
        r2 = r1;
        r1 = r0;
        goto L_0x009e;
    L_0x00b7:
        r2 = move-exception;
        r5 = r2;
        r2 = r1;
        r1 = r0;
        goto L_0x009e;
    L_0x00bc:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        goto L_0x009e;
    L_0x00c0:
        r5 = move-exception;
        goto L_0x009e;
    L_0x00c2:
        r2 = r1;
        r3 = r0;
        r4 = r0;
        r1 = r0;
        goto L_0x004e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.tid.C0643b.m995l():void");
    }

    /* renamed from: a */
    private boolean m991a(String str, String str2, String str3, String str4) {
        return TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4);
    }

    /* renamed from: m */
    private void m996m() {
        this.f624j = "";
        this.f625k = mo8099f();
        this.f626l = System.currentTimeMillis();
        this.f627m = m997n();
        this.f628n = m997n();
        C0642a.m988b("alipay_tid_storage", "tidinfo");
    }

    /* renamed from: n */
    private String m997n() {
        return Long.toHexString(System.currentTimeMillis()) + (new Random().nextInt(9000) + 1000);
    }

    /* renamed from: f */
    public String mo8099f() {
        String toHexString = Long.toHexString(System.currentTimeMillis());
        if (toHexString.length() > 10) {
            return toHexString.substring(toHexString.length() - 10);
        }
        return toHexString;
    }

    /* renamed from: a */
    public void mo8097a(String str, String str2) {
        C0646c.m1017b("TidStorage", "TidStorage.save " + ("tid=" + str + ",clientKey=" + str2));
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            this.f624j = str;
            this.f625k = str2;
            this.f626l = System.currentTimeMillis();
            m999p();
            m998o();
        }
    }

    /* renamed from: o */
    private void m998o() {
    }

    /* renamed from: p */
    private void m999p() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("tid", this.f624j);
            jSONObject.put("client_key", this.f625k);
            jSONObject.put(AnalyticAttribute.EVENT_TIMESTAMP_ATTRIBUTE, this.f626l);
            jSONObject.put("vimei", this.f627m);
            jSONObject.put("vimsi", this.f628n);
            C0642a.m986a("alipay_tid_storage", "tidinfo", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject), true);
        } catch (Exception e) {
            C0646c.m1016a(e);
        }
    }
}
