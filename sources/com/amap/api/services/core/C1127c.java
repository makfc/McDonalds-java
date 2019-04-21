package com.amap.api.services.core;

import com.amap.api.services.core.C1081ac.C1080a;

/* compiled from: ConfigableConst */
/* renamed from: com.amap.api.services.core.c */
public class C1127c {
    /* renamed from: a */
    public static final String[] f3787a = new String[]{"com.amap.api.services"};

    /* renamed from: a */
    public static String m4969a() {
        if (ServiceSettings.getInstance().getProtocol() == 1) {
            return "http://restapi.amap.com/v3";
        }
        return "https://restapi.amap.com/v3";
    }

    /* renamed from: a */
    public static C1081ac m4968a(boolean z) {
        String str = "getSDKInfo";
        C1081ac c1081ac = null;
        try {
            return new C1080a("sea", "2.4.0", "AMAP SDK Android Search 2.4.0").mo11986a(f3787a).mo11985a(z).mo11987a();
        } catch (C1133u e) {
            C1128d.m4975a(e, "ConfigableConst", str);
            return c1081ac;
        }
    }
}
