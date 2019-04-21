package com.kochava.base;

import android.support.annotation.AnyThread;
import android.support.annotation.NonNull;

interface LogListener {
    @AnyThread
    void onLog(int i, @NonNull String str, @NonNull String str2);
}
