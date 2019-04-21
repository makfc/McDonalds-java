package com.admaster.jice.p006c;

import android.content.Context;

/* renamed from: com.admaster.jice.c.e */
public class Reflection {
    /* renamed from: a */
    public static boolean m192a(Context context) {
        try {
            if (Class.forName("android.support.v4.content.ContextCompat") != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: a */
    public static int m191a(Context context, String str) {
        try {
            Class cls = Class.forName("android.support.v4.content.ContextCompat");
            return ((Integer) cls.getDeclaredMethod("checkSelfPermission", new Class[]{Context.class, String.class}).invoke(cls, new Object[]{context, str})).intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    /* renamed from: b */
    public static boolean m193b(Context context) {
        try {
            if (Class.forName("android.support.v4.util.Pair") != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
