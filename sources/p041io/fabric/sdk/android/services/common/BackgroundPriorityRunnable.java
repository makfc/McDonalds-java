package p041io.fabric.sdk.android.services.common;

import android.os.Process;

/* renamed from: io.fabric.sdk.android.services.common.BackgroundPriorityRunnable */
public abstract class BackgroundPriorityRunnable implements Runnable {
    public abstract void onRun();

    public final void run() {
        Process.setThreadPriority(10);
        onRun();
    }
}
