package com.facebook.stetho.common;

import java.util.Locale;

public class LogUtil {
    private static final String TAG = "stetho";

    /* renamed from: e */
    public static void m7448e(String format, Object... args) {
        m7447e(format(format, args));
    }

    /* renamed from: e */
    public static void m7450e(Throwable t, String format, Object... args) {
        m7449e(t, format(format, args));
    }

    /* renamed from: e */
    public static void m7447e(String message) {
        if (isLoggable(6)) {
            LogRedirector.m7435e(TAG, message);
        }
    }

    /* renamed from: e */
    public static void m7449e(Throwable t, String message) {
        if (isLoggable(6)) {
            LogRedirector.m7436e(TAG, message, t);
        }
    }

    /* renamed from: w */
    public static void m7460w(String format, Object... args) {
        m7459w(format(format, args));
    }

    /* renamed from: w */
    public static void m7462w(Throwable t, String format, Object... args) {
        m7461w(t, format(format, args));
    }

    /* renamed from: w */
    public static void m7459w(String message) {
        if (isLoggable(5)) {
            LogRedirector.m7441w(TAG, message);
        }
    }

    /* renamed from: w */
    public static void m7461w(Throwable t, String message) {
        if (isLoggable(5)) {
            LogRedirector.m7442w(TAG, message, t);
        }
    }

    /* renamed from: i */
    public static void m7452i(String format, Object... args) {
        m7451i(format(format, args));
    }

    /* renamed from: i */
    public static void m7454i(Throwable t, String format, Object... args) {
        m7453i(t, format(format, args));
    }

    /* renamed from: i */
    public static void m7451i(String message) {
        if (isLoggable(4)) {
            LogRedirector.m7437i(TAG, message);
        }
    }

    /* renamed from: i */
    public static void m7453i(Throwable t, String message) {
        if (isLoggable(4)) {
            LogRedirector.m7438i(TAG, message, t);
        }
    }

    /* renamed from: d */
    public static void m7444d(String format, Object... args) {
        m7443d(format(format, args));
    }

    /* renamed from: d */
    public static void m7446d(Throwable t, String format, Object... args) {
        m7445d(t, format(format, args));
    }

    /* renamed from: d */
    public static void m7443d(String message) {
        if (isLoggable(3)) {
            LogRedirector.m7433d(TAG, message);
        }
    }

    /* renamed from: d */
    public static void m7445d(Throwable t, String message) {
        if (isLoggable(3)) {
            LogRedirector.m7434d(TAG, message, t);
        }
    }

    /* renamed from: v */
    public static void m7456v(String format, Object... args) {
        m7455v(format(format, args));
    }

    /* renamed from: v */
    public static void m7458v(Throwable t, String format, Object... args) {
        m7457v(t, format(format, args));
    }

    /* renamed from: v */
    public static void m7455v(String message) {
        if (isLoggable(2)) {
            LogRedirector.m7439v(TAG, message);
        }
    }

    /* renamed from: v */
    public static void m7457v(Throwable t, String message) {
        if (isLoggable(2)) {
            LogRedirector.m7440v(TAG, message, t);
        }
    }

    private static String format(String format, Object... args) {
        return String.format(Locale.US, format, args);
    }

    public static boolean isLoggable(int priority) {
        switch (priority) {
            case 5:
            case 6:
                return true;
            default:
                return LogRedirector.isLoggable(TAG, priority);
        }
    }
}
