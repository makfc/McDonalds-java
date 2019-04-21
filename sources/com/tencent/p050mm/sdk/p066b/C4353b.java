package com.tencent.p050mm.sdk.p066b;

import android.os.Build;
import android.os.Build.VERSION;
import android.os.Looper;
import android.os.Process;

/* renamed from: com.tencent.mm.sdk.b.b */
public final class C4353b {
    /* renamed from: aG */
    private static C4352a f6786aG;
    /* renamed from: aH */
    private static C4352a f6787aH;
    /* renamed from: aI */
    private static final String f6788aI;
    private static int level = 6;

    /* renamed from: com.tencent.mm.sdk.b.b$a */
    public interface C4352a {
        /* renamed from: f */
        void mo33766f(String str, String str2);

        /* renamed from: g */
        void mo33767g(String str, String str2);

        int getLogLevel();

        /* renamed from: h */
        void mo33769h(String str, String str2);

        /* renamed from: i */
        void mo33770i(String str, String str2);
    }

    static {
        C4354c c4354c = new C4354c();
        f6786aG = c4354c;
        f6787aH = c4354c;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("VERSION.RELEASE:[" + VERSION.RELEASE);
            stringBuilder.append("] VERSION.CODENAME:[" + VERSION.CODENAME);
            stringBuilder.append("] VERSION.INCREMENTAL:[" + VERSION.INCREMENTAL);
            stringBuilder.append("] BOARD:[" + Build.BOARD);
            stringBuilder.append("] DEVICE:[" + Build.DEVICE);
            stringBuilder.append("] DISPLAY:[" + Build.DISPLAY);
            stringBuilder.append("] FINGERPRINT:[" + Build.FINGERPRINT);
            stringBuilder.append("] HOST:[" + Build.HOST);
            stringBuilder.append("] MANUFACTURER:[" + Build.MANUFACTURER);
            stringBuilder.append("] MODEL:[" + Build.MODEL);
            stringBuilder.append("] PRODUCT:[" + Build.PRODUCT);
            stringBuilder.append("] TAGS:[" + Build.TAGS);
            stringBuilder.append("] TYPE:[" + Build.TYPE);
            stringBuilder.append("] USER:[" + Build.USER + "]");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        f6788aI = stringBuilder.toString();
    }

    /* renamed from: a */
    public static void m7888a(String str, String str2, Object... objArr) {
        if (f6787aH != null && f6787aH.getLogLevel() <= 4) {
            String format = objArr == null ? str2 : String.format(str2, objArr);
            if (format == null) {
                format = "";
            }
            C4352a c4352a = f6787aH;
            Process.myPid();
            Thread.currentThread().getId();
            Looper.getMainLooper().getThread().getId();
            c4352a.mo33770i(str, format);
        }
    }

    /* renamed from: b */
    public static void m7889b(String str, String str2) {
        C4353b.m7888a(str, str2, null);
    }

    /* renamed from: c */
    public static void m7890c(String str, String str2) {
        if (f6787aH != null && f6787aH.getLogLevel() <= 3) {
            C4352a c4352a = f6787aH;
            Process.myPid();
            Thread.currentThread().getId();
            Looper.getMainLooper().getThread().getId();
            c4352a.mo33769h(str, str2);
        }
    }

    /* renamed from: d */
    public static void m7891d(String str, String str2) {
        if (f6787aH != null && f6787aH.getLogLevel() <= 2) {
            C4352a c4352a = f6787aH;
            Process.myPid();
            Thread.currentThread().getId();
            Looper.getMainLooper().getThread().getId();
            c4352a.mo33766f(str, str2);
        }
    }

    /* renamed from: e */
    public static void m7892e(String str, String str2) {
        if (f6787aH != null && f6787aH.getLogLevel() <= 1) {
            if (str2 == null) {
                str2 = "";
            }
            C4352a c4352a = f6787aH;
            Process.myPid();
            Thread.currentThread().getId();
            Looper.getMainLooper().getThread().getId();
            c4352a.mo33767g(str, str2);
        }
    }
}
