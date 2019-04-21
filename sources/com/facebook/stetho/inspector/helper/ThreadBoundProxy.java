package com.facebook.stetho.inspector.helper;

import com.facebook.stetho.common.ThreadBound;
import com.facebook.stetho.common.UncheckedCallable;
import com.facebook.stetho.common.Util;

public abstract class ThreadBoundProxy implements ThreadBound {
    private final ThreadBound mEnforcer;

    public ThreadBoundProxy(ThreadBound enforcer) {
        this.mEnforcer = (ThreadBound) Util.throwIfNull(enforcer);
    }

    public final boolean checkThreadAccess() {
        return this.mEnforcer.checkThreadAccess();
    }

    public final void verifyThreadAccess() {
        this.mEnforcer.verifyThreadAccess();
    }

    public final <V> V postAndWait(UncheckedCallable<V> c) {
        return this.mEnforcer.postAndWait((UncheckedCallable) c);
    }

    public final void postAndWait(Runnable r) {
        this.mEnforcer.postAndWait(r);
    }

    public final void postDelayed(Runnable r, long delayMillis) {
        this.mEnforcer.postDelayed(r, delayMillis);
    }

    public final void removeCallbacks(Runnable r) {
        this.mEnforcer.removeCallbacks(r);
    }
}
