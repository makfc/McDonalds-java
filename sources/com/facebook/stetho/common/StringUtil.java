package com.facebook.stetho.common;

public final class StringUtil {
    private StringUtil() {
    }

    public static String removePrefix(String string, String prefix, String previousAttempt) {
        return string != previousAttempt ? previousAttempt : removePrefix(string, prefix);
    }

    public static String removePrefix(String string, String prefix) {
        if (string.startsWith(prefix)) {
            return string.substring(prefix.length());
        }
        return string;
    }
}
