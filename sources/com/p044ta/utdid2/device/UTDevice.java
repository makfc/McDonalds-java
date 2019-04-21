package com.p044ta.utdid2.device;

import android.content.Context;
import com.p044ta.utdid2.p055a.p056a.C4321f;

/* renamed from: com.ta.utdid2.device.UTDevice */
public class UTDevice {
    @Deprecated
    public static String getUtdid(Context context) {
        return UTDevice.m7479a(context);
    }

    @Deprecated
    public static String getUtdidForUpdate(Context context) {
        return UTDevice.m7480b(context);
    }

    /* renamed from: a */
    private static String m7479a(Context context) {
        C4332a b = C4333b.m7826b(context);
        if (b == null || C4321f.isEmpty(b.getUtdid())) {
            return "ffffffffffffffffffffffff";
        }
        return b.getUtdid();
    }

    /* renamed from: b */
    private static String m7480b(Context context) {
        String d = C4334c.m7827a(context).mo33748d();
        return (d == null || C4321f.isEmpty(d)) ? "ffffffffffffffffffffffff" : d;
    }
}
