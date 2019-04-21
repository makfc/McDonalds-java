package com.kochava.base;

import android.support.annotation.NonNull;
import org.jetbrains.annotations.Contract;

public final class InstallReferrer {
    @NonNull
    public static final String KEY_ATTEMPT_COUNT = "attempt_count";
    @NonNull
    public static final String KEY_DURATION = "duration";
    public static final int STATUS_DEVELOPER_ERROR = 3;
    public static final int STATUS_FEATURE_NOT_SUPPORTED = 2;
    public static final int STATUS_MISSING_DEPENDENCY = 5;
    public static final int STATUS_NOT_GATHERED = 6;
    public static final int STATUS_OK = 0;
    public static final int STATUS_SERVICE_DISCONNECTED = -1;
    public static final int STATUS_SERVICE_UNAVAILABLE = 1;
    public static final int STATUS_TIMED_OUT = 4;
    public final int attemptCount;
    public final double duration;
    public final long installBeginTime;
    public final boolean isLegacy;
    @NonNull
    public final String referrer;
    public final long referrerClickTime;
    public final int status;

    InstallReferrer(@NonNull String str, long j, long j2, int i, boolean z, int i2, double d) {
        this.referrer = str;
        this.installBeginTime = j;
        this.referrerClickTime = j2;
        this.status = i;
        this.isLegacy = z;
        this.attemptCount = i2;
        this.duration = d;
    }

    @Contract(pure = true)
    public final boolean isGathered() {
        return this.status != 6;
    }

    @Contract(pure = true)
    public final boolean isSupported() {
        return (this.status == 2 || this.status == 5) ? false : true;
    }

    @Contract(pure = true)
    public final boolean isValid() {
        return (this.referrer.isEmpty() || this.installBeginTime == -1 || this.referrerClickTime == -1 || this.status != 0) ? false : true;
    }
}
