package com.tencent.p050mm.sdk.p066b;

import android.os.Debug;
import android.os.Handler;
import android.os.Process;

/* renamed from: com.tencent.mm.sdk.b.g */
public final class C4360g implements Runnable {
    /* renamed from: bf */
    private static final String f6796bf;
    /* renamed from: bg */
    private static final String f6797bg;
    /* renamed from: aQ */
    final Runnable f6798aQ;
    /* renamed from: aR */
    final String f6799aR;
    /* renamed from: aS */
    final Object f6800aS;
    /* renamed from: aT */
    final Thread f6801aT;
    /* renamed from: aU */
    String f6802aU;
    /* renamed from: aV */
    long f6803aV;
    /* renamed from: aW */
    final C4357a f6804aW;
    /* renamed from: aX */
    long f6805aX;
    /* renamed from: aY */
    long f6806aY;
    /* renamed from: aZ */
    long f6807aZ;
    /* renamed from: ba */
    long f6808ba;
    /* renamed from: bb */
    long f6809bb;
    /* renamed from: bc */
    long f6810bc;
    /* renamed from: bd */
    long f6811bd;
    /* renamed from: be */
    float f6812be = -1.0f;
    final Handler handler;
    int priority;
    boolean started = false;

    /* renamed from: com.tencent.mm.sdk.b.g$a */
    public interface C4357a {
        /* renamed from: c */
        void mo33775c(Runnable runnable, C4360g c4360g);
    }

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("taskName = %s");
        stringBuilder.append("|token = %s");
        stringBuilder.append("|handler = %s");
        stringBuilder.append("|threadName = %s");
        stringBuilder.append("|threadId = %d");
        stringBuilder.append("|priority = %d");
        stringBuilder.append("|addTime = %d");
        stringBuilder.append("|delayTime = %d");
        stringBuilder.append("|usedTime = %d");
        stringBuilder.append("|cpuTime = %d");
        stringBuilder.append("|started = %b");
        f6796bf = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append("taskName = %s");
        stringBuilder.append(" | addTime = %s");
        stringBuilder.append(" | endTime = %s");
        stringBuilder.append(" | usedTime = %d");
        stringBuilder.append(" | cpuTime = %d");
        stringBuilder.append(" | threadCpuTime = %d");
        stringBuilder.append(" | totalCpuTime = %d");
        stringBuilder.append(" | threadCpuRate = %.1f");
        f6797bg = stringBuilder.toString();
    }

    C4360g(Thread thread, Handler handler, Runnable runnable, Object obj, C4357a c4357a) {
        this.f6801aT = thread;
        if (thread != null) {
            this.f6802aU = thread.getName();
            this.f6803aV = thread.getId();
            this.priority = thread.getPriority();
        }
        this.handler = handler;
        this.f6798aQ = runnable;
        String name = runnable.getClass().getName();
        String obj2 = runnable.toString();
        if (!C4361h.m7904h(obj2)) {
            int indexOf = obj2.indexOf(124);
            if (indexOf > 0) {
                name = name + "_" + obj2.substring(indexOf + 1);
            }
        }
        this.f6799aR = name;
        this.f6800aS = obj;
        this.f6804aW = c4357a;
        this.f6805aX = System.currentTimeMillis();
    }

    public final void run() {
        new StringBuilder("/proc/self/task/").append(Process.myTid()).append("/stat");
        this.f6808ba = System.currentTimeMillis();
        this.f6809bb = Debug.threadCpuTimeNanos();
        this.f6810bc = -1;
        this.f6811bd = -1;
        this.started = true;
        this.f6798aQ.run();
        this.f6810bc = -1 - this.f6810bc;
        this.f6811bd = -1 - this.f6811bd;
        this.f6807aZ = System.currentTimeMillis();
        this.f6808ba = this.f6807aZ - this.f6808ba;
        this.f6809bb = (Debug.threadCpuTimeNanos() - this.f6809bb) / 1000000;
        if (this.f6811bd != 0) {
            this.f6812be = ((float) (100 * this.f6810bc)) / ((float) this.f6811bd);
        }
        if (this.f6804aW != null) {
            this.f6804aW.mo33775c(this.f6798aQ, this);
        }
    }
}
