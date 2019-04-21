package com.threatmetrix.TrustDefender;

import android.util.Log;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: com.threatmetrix.TrustDefender.w */
final class C4549w {
    /* renamed from: a */
    private static final boolean f7837a;
    /* renamed from: b */
    private static final Pattern f7838b = Pattern.compile("\\{\\}");
    /* renamed from: c */
    private static boolean f7839c = false;

    static {
        boolean z;
        if (TrustDefenderVersion.numeric.intValue() == -1) {
            z = true;
        } else {
            z = false;
        }
        f7837a = z;
    }

    /* renamed from: a */
    public static void m8587a(String tag, String message) {
        Log.e(tag, message);
    }

    /* renamed from: a */
    public static void m8588a(String tag, String message, Throwable throwable) {
        Log.e(tag, message, throwable);
    }

    /* renamed from: b */
    public static void m8592b(String tag, String message) {
        Log.w(tag, message);
    }

    /* renamed from: a */
    public static void m8589a(String tag, String message, String... values) {
        Log.w(tag, C4549w.m8586a(message, (Object[]) values));
    }

    /* renamed from: c */
    public static void m8594c(String tag, String message) {
        if (f7837a || f7839c) {
            Log.i(tag, message);
        }
    }

    /* renamed from: b */
    public static void m8593b(String tag, String message, String... values) {
        if (f7837a || f7839c) {
            Log.i(tag, C4549w.m8586a(message, (Object[]) values));
        }
    }

    /* renamed from: c */
    public static void m8595c(String tag, String message, Throwable throwable) {
        if (f7837a || f7839c) {
            Log.i(tag, message, throwable);
        }
    }

    /* renamed from: a */
    static String m8585a(Class c) {
        String tag = "c.t.tdm." + c.getSimpleName();
        if (tag.length() > 23) {
            return tag.substring(0, 23);
        }
        return tag;
    }

    /* renamed from: a */
    static boolean m8590a() {
        return f7837a;
    }

    /* renamed from: a */
    static String m8586a(String message, Object... values) {
        if (message == null || values == null) {
            return "";
        }
        Matcher matcher = f7838b.matcher(message);
        StringBuffer result = new StringBuffer();
        Object[] arr$ = values;
        int len$ = values.length;
        int i$ = 0;
        while (i$ < len$) {
            Object value = arr$[i$];
            if (matcher.find()) {
                if (value != null) {
                    matcher.appendReplacement(result, String.valueOf(value));
                } else {
                    matcher.appendReplacement(result, SafeJsonPrimitive.NULL_STRING);
                }
                i$++;
            } else {
                throw new InvalidParameterException("Incorrect number of arguments for this format string");
            }
        }
        if (!matcher.find()) {
            return matcher.appendTail(result).toString();
        }
        throw new InvalidParameterException("Incorrect number of arguments for this format string");
    }

    /* renamed from: b */
    static int m8591b() {
        if (Log.isLoggable("TrustDefender", 2)) {
            f7839c = true;
            NativeGatherer.m8207a().mo34052b(1);
            return 1;
        }
        f7839c = false;
        NativeGatherer.m8207a().mo34052b(0);
        return 0;
    }
}
