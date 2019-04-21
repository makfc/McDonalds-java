package com.kochava.base;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

public interface AttributionUpdateListener {
    @MainThread
    void onAttributionUpdated(@NonNull String str);
}
