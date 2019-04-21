package com.alipay.apmobilesecuritysdk.p016d;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.p015c.C0552b;
import com.alipay.security.mobile.module.p021b.C0692b;
import com.alipay.security.mobile.module.p021b.C0694d;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.alipay.apmobilesecuritysdk.d.d */
public final class C0557d {
    /* renamed from: a */
    public static synchronized Map<String, String> m651a() {
        HashMap hashMap;
        synchronized (C0557d.class) {
            hashMap = new HashMap();
            try {
                C0552b c0552b = new C0552b();
                hashMap.put("AE16", "");
            } catch (Throwable th) {
            }
        }
        return hashMap;
    }

    /* renamed from: a */
    public static synchronized Map<String, String> m652a(Context context) {
        HashMap hashMap;
        synchronized (C0557d.class) {
            C0694d.m1230a();
            C0692b.m1182a();
            hashMap = new HashMap();
            hashMap.put("AE1", C0694d.m1233b());
            hashMap.put("AE2", (C0694d.m1234c() ? "1" : "0"));
            hashMap.put("AE3", (C0694d.m1232a(context) ? "1" : "0"));
            hashMap.put("AE4", C0694d.m1235d());
            hashMap.put("AE5", C0694d.m1236e());
            hashMap.put("AE6", C0694d.m1237f());
            hashMap.put("AE7", C0694d.m1238g());
            hashMap.put("AE8", C0694d.m1239h());
            hashMap.put("AE9", C0694d.m1240i());
            hashMap.put("AE10", C0694d.m1241j());
            hashMap.put("AE11", C0694d.m1242k());
            hashMap.put("AE12", C0694d.m1243l());
            hashMap.put("AE13", C0694d.m1244m());
            hashMap.put("AE14", C0694d.m1245n());
            hashMap.put("AE15", C0694d.m1246o());
            hashMap.put("AE21", C0692b.m1197h());
        }
        return hashMap;
    }
}
