package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzaa;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class zzpt implements ThreadFactory {
    private final int mPriority;
    private final String zzauv;
    private final AtomicInteger zzauw;
    private final ThreadFactory zzaux;

    public zzpt(String str) {
        this(str, 0);
    }

    public zzpt(String str, int i) {
        this.zzauw = new AtomicInteger();
        this.zzaux = Executors.defaultThreadFactory();
        this.zzauv = (String) zzaa.zzb((Object) str, (Object) "Name must not be null");
        this.mPriority = i;
    }

    public Thread newThread(Runnable runnable) {
        Thread newThread = this.zzaux.newThread(new zzpu(runnable, this.mPriority));
        String str = this.zzauv;
        newThread.setName(new StringBuilder(String.valueOf(str).length() + 13).append(str).append("[").append(this.zzauw.getAndIncrement()).append("]").toString());
        return newThread;
    }
}
