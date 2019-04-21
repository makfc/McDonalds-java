package com.kochava.base;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

@Deprecated
public interface AttributionListener {
    @Deprecated
    @MainThread
    void onAttributionReceived(@NonNull String str);
}
