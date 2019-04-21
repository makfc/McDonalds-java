package com.ensighten;

import android.util.Log;
import java.util.HashMap;

/* renamed from: com.ensighten.j */
public final class C1846j {
    /* renamed from: a */
    private static boolean f5916a = false;
    /* renamed from: b */
    private static boolean f5917b = false;
    /* renamed from: c */
    private static boolean f5918c = false;
    /* renamed from: d */
    private static boolean f5919d = false;
    /* renamed from: e */
    private static boolean f5920e = false;
    /* renamed from: f */
    private static boolean f5921f = false;
    /* renamed from: g */
    private static HashMap<String, Long> f5922g = new HashMap();

    /* renamed from: a */
    public static void m7368a(boolean z) {
        f5916a = z;
    }

    /* renamed from: a */
    public static boolean m7369a() {
        return f5916a;
    }

    /* renamed from: b */
    public static boolean m7371b() {
        return f5916a;
    }

    /* renamed from: c */
    public static boolean m7372c() {
        return f5916a;
    }

    /* renamed from: d */
    public static boolean m7373d() {
        return f5916a;
    }

    /* renamed from: a */
    public static void m7366a(String str) {
        Log.d("EnsightenInstrumentation", str);
    }

    /* renamed from: a */
    public static void m7367a(String str, long j) {
        f5922g.put(str, Long.valueOf(j));
    }

    /* renamed from: b */
    public static long m7370b(String str) {
        return ((Long) f5922g.get(str)).longValue();
    }
}
