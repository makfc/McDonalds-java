package com.crashlytics.android.core;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.atomic.AtomicBoolean;
import p041io.fabric.sdk.android.Fabric;
import p041io.fabric.sdk.android.services.settings.SettingsData;

class CrashlyticsUncaughtExceptionHandler implements UncaughtExceptionHandler {
    private final CrashListener crashListener;
    private final UncaughtExceptionHandler defaultHandler;
    private final boolean firebaseCrashlyticsClientFlag;
    private final AtomicBoolean isHandlingException = new AtomicBoolean(false);
    private final SettingsDataProvider settingsDataProvider;

    interface CrashListener {
        void onUncaughtException(SettingsDataProvider settingsDataProvider, Thread thread, Throwable th, boolean z);
    }

    interface SettingsDataProvider {
        SettingsData getSettingsData();
    }

    public CrashlyticsUncaughtExceptionHandler(CrashListener crashListener, SettingsDataProvider settingsDataProvider, boolean firebaseCrashlyticsClientFlag, UncaughtExceptionHandler defaultHandler) {
        this.crashListener = crashListener;
        this.settingsDataProvider = settingsDataProvider;
        this.firebaseCrashlyticsClientFlag = firebaseCrashlyticsClientFlag;
        this.defaultHandler = defaultHandler;
    }

    public void uncaughtException(Thread thread, Throwable ex) {
        this.isHandlingException.set(true);
        try {
            this.crashListener.onUncaughtException(this.settingsDataProvider, thread, ex, this.firebaseCrashlyticsClientFlag);
        } catch (Exception e) {
            Fabric.getLogger().mo34406e("CrashlyticsCore", "An error occurred in the uncaught exception handler", e);
        } finally {
            Fabric.getLogger().mo34403d("CrashlyticsCore", "Crashlytics completed exception processing. Invoking default exception handler.");
            this.defaultHandler.uncaughtException(thread, ex);
            this.isHandlingException.set(false);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isHandlingException() {
        return this.isHandlingException.get();
    }
}
