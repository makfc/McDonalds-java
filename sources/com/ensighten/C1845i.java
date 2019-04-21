package com.ensighten;

import android.util.Log;

/* renamed from: com.ensighten.i */
public final class C1845i {
    /* renamed from: a */
    private static boolean f5903a = false;
    /* renamed from: b */
    private static boolean f5904b = false;
    /* renamed from: c */
    private static boolean f5905c = false;
    /* renamed from: d */
    private static boolean f5906d = false;
    /* renamed from: e */
    private static boolean f5907e = false;
    /* renamed from: f */
    private static boolean f5908f = false;
    /* renamed from: g */
    private static boolean f5909g = false;
    /* renamed from: h */
    private static boolean f5910h = false;
    /* renamed from: i */
    private static boolean f5911i = false;
    /* renamed from: j */
    private static boolean f5912j = false;
    /* renamed from: k */
    private static boolean f5913k = false;
    /* renamed from: l */
    private static boolean f5914l = false;
    /* renamed from: m */
    private static boolean f5915m = false;

    /* renamed from: com.ensighten.i$a */
    public static class C1844a {
        /* renamed from: a */
        public static final String f5902a = ("Starting Ensighten Mobile Version " + Version.getLabel() + ".");
    }

    /* renamed from: a */
    public static void m7347a(boolean z) {
        f5903a = z;
    }

    /* renamed from: a */
    public static boolean m7348a() {
        return f5903a;
    }

    /* renamed from: b */
    public static boolean m7352b() {
        return f5903a;
    }

    /* renamed from: c */
    public static boolean m7355c() {
        return f5903a;
    }

    /* renamed from: d */
    public static boolean m7357d() {
        return f5903a;
    }

    /* renamed from: e */
    public static boolean m7358e() {
        return f5903a;
    }

    /* renamed from: f */
    public static boolean m7359f() {
        return f5903a;
    }

    /* renamed from: g */
    public static boolean m7360g() {
        return f5903a;
    }

    /* renamed from: h */
    public static boolean m7361h() {
        return f5903a;
    }

    /* renamed from: i */
    public static boolean m7362i() {
        return f5903a;
    }

    /* renamed from: j */
    public static boolean m7363j() {
        return f5903a;
    }

    /* renamed from: k */
    public static boolean m7364k() {
        return f5903a;
    }

    /* renamed from: l */
    public static boolean m7365l() {
        return f5903a;
    }

    /* renamed from: a */
    public static void m7345a(String str) {
        Log.d("Ensighten", str);
    }

    /* renamed from: a */
    public static void m7344a(Exception exception) {
        Log.e("Ensighten", Log.getStackTraceString(exception));
    }

    /* renamed from: b */
    public static void m7350b(String str) {
        Log.v("Ensighten", str);
    }

    /* renamed from: a */
    public static void m7346a(String str, Exception exception) {
        Log.e("EnsightenError", str, exception);
    }

    /* renamed from: c */
    public static void m7354c(String str) {
        Log.w("Ensighten", str);
    }

    /* renamed from: b */
    public static void m7349b(Exception exception) {
        Log.e("Ensighten", Log.getStackTraceString(exception));
    }

    /* renamed from: b */
    public static void m7351b(String str, Exception exception) {
        Log.e("EnsightenError", str, exception);
    }

    /* renamed from: d */
    public static void m7356d(String str) {
        Log.e("EnsightenError", str);
    }

    /* renamed from: c */
    public static void m7353c(Exception exception) {
        Log.e("EnsightenError", Log.getStackTraceString(exception));
    }
}
