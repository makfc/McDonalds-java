package com.tencent.wxop.stat.common;

import android.util.Log;
import com.tencent.wxop.stat.C4444g;
import com.tencent.wxop.stat.StatConfig;

public final class StatLogger {
    /* renamed from: a */
    private String f7086a = "default";
    /* renamed from: b */
    private boolean f7087b = true;
    /* renamed from: c */
    private int f7088c = 2;

    public StatLogger(String str) {
        this.f7086a = str;
    }

    /* renamed from: a */
    private String m8069a() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace == null) {
            return null;
        }
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (!stackTraceElement.isNativeMethod() && !stackTraceElement.getClassName().equals(Thread.class.getName()) && !stackTraceElement.getClassName().equals(getClass().getName())) {
                return "[" + Thread.currentThread().getName() + "(" + Thread.currentThread().getId() + "): " + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + "]";
            }
        }
        return null;
    }

    /* renamed from: d */
    public final void mo33946d(Object obj) {
        if (isDebugEnable()) {
            debug(obj);
        }
    }

    public final void debug(Object obj) {
        if (this.f7088c <= 3) {
            String a = m8069a();
            a = a == null ? obj.toString() : a + " - " + obj;
            Log.d(this.f7086a, a);
            C4444g customLogger = StatConfig.getCustomLogger();
            if (customLogger != null) {
                customLogger.mo33979e(a);
            }
        }
    }

    /* renamed from: e */
    public final void mo33948e(Object obj) {
        if (isDebugEnable()) {
            error(obj);
        }
    }

    /* renamed from: e */
    public final void mo33949e(Throwable th) {
        if (isDebugEnable()) {
            error(th);
        }
    }

    public final void error(Object obj) {
        if (this.f7088c <= 6) {
            String a = m8069a();
            a = a == null ? obj.toString() : a + " - " + obj;
            Log.e(this.f7086a, a);
            C4444g customLogger = StatConfig.getCustomLogger();
            if (customLogger != null) {
                customLogger.mo33978d(a);
            }
        }
    }

    public final void error(Throwable th) {
        if (this.f7088c <= 6) {
            Log.e(this.f7086a, "", th);
            C4444g customLogger = StatConfig.getCustomLogger();
            if (customLogger != null) {
                customLogger.mo33978d(th);
            }
        }
    }

    /* renamed from: i */
    public final void mo33952i(Object obj) {
        if (isDebugEnable()) {
            info(obj);
        }
    }

    public final void info(Object obj) {
        if (this.f7088c <= 4) {
            String a = m8069a();
            a = a == null ? obj.toString() : a + " - " + obj;
            Log.i(this.f7086a, a);
            C4444g customLogger = StatConfig.getCustomLogger();
            if (customLogger != null) {
                customLogger.mo33976a(a);
            }
        }
    }

    public final boolean isDebugEnable() {
        return this.f7087b;
    }

    public final void setDebugEnable(boolean z) {
        this.f7087b = z;
    }

    /* renamed from: w */
    public final void mo33956w(Object obj) {
        if (isDebugEnable()) {
            warn(obj);
        }
    }

    public final void warn(Object obj) {
        if (this.f7088c <= 5) {
            String a = m8069a();
            a = a == null ? obj.toString() : a + " - " + obj;
            Log.w(this.f7086a, a);
            C4444g customLogger = StatConfig.getCustomLogger();
            if (customLogger != null) {
                customLogger.mo33977c(a);
            }
        }
    }
}
