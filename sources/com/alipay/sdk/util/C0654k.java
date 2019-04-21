package com.alipay.sdk.util;

import com.alipay.sdk.app.C0589k;
import com.alipay.sdk.app.statistic.C0590a;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.alipay.sdk.util.k */
public class C0654k {
    /* renamed from: a */
    public static Map<String, String> m1043a(String str) {
        Map<String, String> a = C0654k.m1042a();
        try {
            return C0654k.m1044b(str);
        } catch (Throwable th) {
            C0590a.m802a("biz", "FormatResultEx", th);
            return a;
        }
    }

    /* renamed from: a */
    private static Map<String, String> m1042a() {
        C0589k b = C0589k.m796b(C0589k.CANCELED.mo8007a());
        HashMap hashMap = new HashMap();
        hashMap.put("resultStatus", Integer.toString(b.mo8007a()));
        hashMap.put("memo", b.mo8008b());
        hashMap.put("result", "");
        return hashMap;
    }

    /* renamed from: b */
    public static Map<String, String> m1044b(String str) {
        String[] split = str.split(";");
        HashMap hashMap = new HashMap();
        for (String str2 : split) {
            String substring = str2.substring(0, str2.indexOf("={"));
            hashMap.put(substring, C0654k.m1041a(str2, substring));
        }
        return hashMap;
    }

    /* renamed from: a */
    private static String m1041a(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str3.length() + str.indexOf(str3), str.lastIndexOf("}"));
    }
}
