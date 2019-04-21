package com.alipay.sdk.util;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.cons.C0611a;

/* renamed from: com.alipay.sdk.util.l */
public class C0655l {
    /* renamed from: a */
    public static String m1045a(Context context) {
        if (EnvUtils.isSandBox()) {
            return "https://mobilegw.alipaydev.com/mgw.htm";
        }
        if (context == null) {
            return C0611a.f566a;
        }
        String str = C0611a.f566a;
        if (TextUtils.isEmpty(str)) {
            return C0611a.f566a;
        }
        return str;
    }
}
