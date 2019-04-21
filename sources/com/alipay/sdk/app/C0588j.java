package com.alipay.sdk.app;

/* renamed from: com.alipay.sdk.app.j */
public class C0588j {
    /* renamed from: a */
    private static boolean f499a = false;
    /* renamed from: b */
    private static String f500b;

    /* renamed from: a */
    public static void m790a(String str) {
        f500b = str;
    }

    /* renamed from: a */
    public static String m788a() {
        return f500b;
    }

    /* renamed from: b */
    public static boolean m792b() {
        return f499a;
    }

    /* renamed from: a */
    public static void m791a(boolean z) {
        f499a = z;
    }

    /* renamed from: c */
    public static String m793c() {
        C0589k b = C0589k.m796b(C0589k.CANCELED.mo8007a());
        return C0588j.m789a(b.mo8007a(), b.mo8008b(), "");
    }

    /* renamed from: d */
    public static String m794d() {
        C0589k b = C0589k.m796b(C0589k.DOUBLE_REQUEST.mo8007a());
        return C0588j.m789a(b.mo8007a(), b.mo8008b(), "");
    }

    /* renamed from: e */
    public static String m795e() {
        C0589k b = C0589k.m796b(C0589k.PARAMS_ERROR.mo8007a());
        return C0588j.m789a(b.mo8007a(), b.mo8008b(), "");
    }

    /* renamed from: a */
    public static String m789a(int i, String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("resultStatus={").append(i).append("};memo={").append(str).append("};result={").append(str2).append("}");
        return stringBuilder.toString();
    }
}
