package com.mcdonalds.sdk.services.log;

import android.util.Log;
import com.mcdonalds.sdk.services.configuration.Configuration;

public class MCDLog {
    private static final String LOG_CONFIG_PREFIX = "log";
    private static final String LOG_TAG_DEBUG = "debug";
    private static final String LOG_TAG_ERROR = "error";
    private static final String LOG_TAG_FATAL = "FATAL";
    private static final String LOG_TAG_FORCE = "LOG";
    private static final String LOG_TAG_INFO = "info";
    private static final String LOG_TAG_TEMP = "TEMP";
    private static final String LOG_TAG_WARNING = "warning";

    private MCDLog() {
    }

    public static void debug(String msg) {
        log(3, LOG_TAG_DEBUG, msg);
    }

    public static void info(String msg) {
        log(4, LOG_TAG_INFO, msg);
    }

    public static void warning(String msg) {
        log(5, LOG_TAG_WARNING, msg);
    }

    public static void error(String tag, String msg) {
        log(6, tag, msg);
    }

    public static void fatal(String msg) {
        log(6, LOG_TAG_FATAL, msg);
    }

    public static void force(String msg) {
        log(3, LOG_TAG_FORCE, msg);
    }

    public static void custom(String tag, String msg) {
        log(2, tag, msg);
    }

    public static void temp(String msg) {
        Log.d(LOG_TAG_TEMP, msg);
    }

    private static void log(int level, String tag, String msg) {
        if ((Configuration.getSharedInstance().getBooleanForKey("log." + tag) || LOG_TAG_FORCE.equals(tag) || LOG_TAG_FATAL.equals(tag)) && msg != null) {
            SafeLog.println(level, tag, msg);
        }
    }

    public static void error(String tag, String message, Throwable throwable) {
        SafeLog.m7746e(tag, message, throwable);
    }
}
