package com.kochava.base;

import android.support.annotation.MainThread;

public interface ConsentStatusChangeListener {
    @MainThread
    void onConsentStatusChange();
}
