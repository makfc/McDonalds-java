package com.facebook.stetho.inspector.elements.android;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import com.facebook.stetho.common.ThreadBound;
import com.facebook.stetho.common.UncheckedCallable;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.common.android.HandlerUtil;
import com.facebook.stetho.inspector.elements.DocumentProvider;
import com.facebook.stetho.inspector.elements.DocumentProviderFactory;

public final class AndroidDocumentProviderFactory implements ThreadBound, DocumentProviderFactory {
    private final Application mApplication;
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    public AndroidDocumentProviderFactory(Application application) {
        this.mApplication = (Application) Util.throwIfNull(application);
    }

    public DocumentProvider create() {
        return new AndroidDocumentProvider(this.mApplication, this);
    }

    public boolean checkThreadAccess() {
        return HandlerUtil.checkThreadAccess(this.mHandler);
    }

    public void verifyThreadAccess() {
        HandlerUtil.verifyThreadAccess(this.mHandler);
    }

    public <V> V postAndWait(UncheckedCallable<V> c) {
        return HandlerUtil.postAndWait(this.mHandler, (UncheckedCallable) c);
    }

    public void postAndWait(Runnable r) {
        HandlerUtil.postAndWait(this.mHandler, r);
    }

    public void postDelayed(Runnable r, long delayMillis) {
        if (!this.mHandler.postDelayed(r, delayMillis)) {
            throw new RuntimeException("Handler.postDelayed() returned false");
        }
    }

    public void removeCallbacks(Runnable r) {
        this.mHandler.removeCallbacks(r);
    }
}
