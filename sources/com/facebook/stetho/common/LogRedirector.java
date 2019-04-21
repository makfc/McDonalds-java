package com.facebook.stetho.common;

import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;

public class LogRedirector {
    private static volatile Logger sLogger;

    public interface Logger {
        boolean isLoggable(String str, int i);

        void log(int i, String str, String str2);
    }

    public static void setLogger(Logger logger) {
        Util.throwIfNull(logger);
        Util.throwIfNotNull(sLogger);
        sLogger = logger;
    }

    /* renamed from: e */
    public static void m7436e(String tag, String message, Throwable t) {
        m7435e(tag, message + "\n" + formatThrowable(t));
    }

    /* renamed from: e */
    public static void m7435e(String tag, String message) {
        log(6, tag, message);
    }

    /* renamed from: w */
    public static void m7442w(String tag, String message, Throwable t) {
        m7441w(tag, message + "\n" + formatThrowable(t));
    }

    /* renamed from: w */
    public static void m7441w(String tag, String message) {
        log(5, tag, message);
    }

    /* renamed from: i */
    public static void m7438i(String tag, String message, Throwable t) {
        m7437i(tag, message + "\n" + formatThrowable(t));
    }

    /* renamed from: i */
    public static void m7437i(String tag, String message) {
        log(4, tag, message);
    }

    /* renamed from: d */
    public static void m7434d(String tag, String message, Throwable t) {
        m7433d(tag, message + "\n" + formatThrowable(t));
    }

    /* renamed from: d */
    public static void m7433d(String tag, String message) {
        log(3, tag, message);
    }

    /* renamed from: v */
    public static void m7440v(String tag, String message, Throwable t) {
        m7439v(tag, message + "\n" + formatThrowable(t));
    }

    /* renamed from: v */
    public static void m7439v(String tag, String message) {
        log(2, tag, message);
    }

    private static String formatThrowable(Throwable t) {
        StringWriter buf = new StringWriter();
        PrintWriter writer = new PrintWriter(buf);
        t.printStackTrace();
        writer.flush();
        return buf.toString();
    }

    private static void log(int priority, String tag, String message) {
        Logger logger = sLogger;
        if (logger != null) {
            logger.log(priority, tag, message);
        } else {
            Log.println(priority, tag, message);
        }
    }

    public static boolean isLoggable(String tag, int priority) {
        Logger logger = sLogger;
        if (logger != null) {
            return logger.isLoggable(tag, priority);
        }
        return Log.isLoggable(tag, priority);
    }
}
