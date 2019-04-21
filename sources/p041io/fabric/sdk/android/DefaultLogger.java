package p041io.fabric.sdk.android;

import android.util.Log;

/* renamed from: io.fabric.sdk.android.DefaultLogger */
public class DefaultLogger implements Logger {
    private int logLevel;

    public DefaultLogger(int logLevel) {
        this.logLevel = logLevel;
    }

    public DefaultLogger() {
        this.logLevel = 4;
    }

    public boolean isLoggable(String tag, int level) {
        return this.logLevel <= level || Log.isLoggable(tag, level);
    }

    /* renamed from: d */
    public void mo34404d(String tag, String text, Throwable throwable) {
        if (isLoggable(tag, 3)) {
            Log.d(tag, text, throwable);
        }
    }

    /* renamed from: v */
    public void mo34415v(String tag, String text, Throwable throwable) {
        if (isLoggable(tag, 2)) {
            Log.v(tag, text, throwable);
        }
    }

    /* renamed from: i */
    public void mo34413i(String tag, String text, Throwable throwable) {
        if (isLoggable(tag, 4)) {
            Log.i(tag, text, throwable);
        }
    }

    /* renamed from: w */
    public void mo34412w(String tag, String text, Throwable throwable) {
        if (isLoggable(tag, 5)) {
            Log.w(tag, text, throwable);
        }
    }

    /* renamed from: e */
    public void mo34406e(String tag, String text, Throwable throwable) {
        if (isLoggable(tag, 6)) {
            Log.e(tag, text, throwable);
        }
    }

    /* renamed from: d */
    public void mo34403d(String tag, String text) {
        mo34404d(tag, text, null);
    }

    /* renamed from: v */
    public void mo34410v(String tag, String text) {
        mo34415v(tag, text, null);
    }

    /* renamed from: i */
    public void mo34407i(String tag, String text) {
        mo34413i(tag, text, null);
    }

    /* renamed from: w */
    public void mo34411w(String tag, String text) {
        mo34412w(tag, text, null);
    }

    /* renamed from: e */
    public void mo34405e(String tag, String text) {
        mo34406e(tag, text, null);
    }

    public void log(int priority, String tag, String msg) {
        log(priority, tag, msg, false);
    }

    public void log(int priority, String tag, String msg, boolean forceLog) {
        if (forceLog || isLoggable(tag, priority)) {
            Log.println(priority, tag, msg);
        }
    }
}
