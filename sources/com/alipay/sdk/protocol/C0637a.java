package com.alipay.sdk.protocol;

import android.text.TextUtils;

/* renamed from: com.alipay.sdk.protocol.a */
public enum C0637a {
    None("none"),
    WapPay("js://wappay"),
    Update("js://update"),
    OpenWeb("loc:openweb"),
    SetResult("loc:setResult"),
    Exit("loc:exit");
    
    /* renamed from: g */
    private String f612g;

    private C0637a(String str) {
        this.f612g = str;
    }

    /* renamed from: a */
    public static C0637a m959a(String str) {
        if (TextUtils.isEmpty(str)) {
            return None;
        }
        C0637a c0637a = None;
        for (C0637a c0637a2 : C0637a.values()) {
            if (str.startsWith(c0637a2.f612g)) {
                return c0637a2;
            }
        }
        return c0637a;
    }
}
