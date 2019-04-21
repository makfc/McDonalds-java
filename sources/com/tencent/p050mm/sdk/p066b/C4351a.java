package com.tencent.p050mm.sdk.p066b;

import android.os.Bundle;

/* renamed from: com.tencent.mm.sdk.b.a */
public final class C4351a {
    /* renamed from: a */
    public static int m7882a(Bundle bundle, String str) {
        int i = -1;
        if (bundle == null) {
            return i;
        }
        try {
            return bundle.getInt(str, -1);
        } catch (Exception e) {
            C4353b.m7888a("MicroMsg.IntentUtil", "getIntExtra exception:%s", e.getMessage());
            return i;
        }
    }

    /* renamed from: b */
    public static String m7883b(Bundle bundle, String str) {
        String str2 = null;
        if (bundle == null) {
            return str2;
        }
        try {
            return bundle.getString(str);
        } catch (Exception e) {
            C4353b.m7888a("MicroMsg.IntentUtil", "getStringExtra exception:%s", e.getMessage());
            return str2;
        }
    }
}
