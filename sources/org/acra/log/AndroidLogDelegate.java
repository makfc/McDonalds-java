package org.acra.log;

import android.util.Log;

public final class AndroidLogDelegate implements ACRALog {
    /* renamed from: v */
    public final int mo23352v(String tag, String msg) {
        return Log.v(tag, msg);
    }

    /* renamed from: d */
    public final int mo23347d(String tag, String msg) {
        return Log.d(tag, msg);
    }

    /* renamed from: d */
    public final int mo23348d(String tag, String msg, Throwable tr) {
        return Log.d(tag, msg, tr);
    }

    /* renamed from: i */
    public final int mo23351i(String tag, String msg) {
        return Log.i(tag, msg);
    }

    /* renamed from: w */
    public final int mo23353w(String tag, String msg) {
        return Log.w(tag, msg);
    }

    /* renamed from: w */
    public final int mo23354w(String tag, String msg, Throwable tr) {
        return Log.w(tag, msg, tr);
    }

    /* renamed from: e */
    public final int mo23349e(String tag, String msg) {
        return Log.e(tag, msg);
    }

    /* renamed from: e */
    public final int mo23350e(String tag, String msg, Throwable tr) {
        return Log.e(tag, msg, tr);
    }
}
