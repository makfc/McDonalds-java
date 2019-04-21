package android.support.p000v4.content;

import android.os.Build.VERSION;
import java.util.concurrent.Executor;

/* renamed from: android.support.v4.content.ParallelExecutorCompat */
public final class ParallelExecutorCompat {
    public static Executor getParallelExecutor() {
        if (VERSION.SDK_INT >= 11) {
            return ExecutorCompatHoneycomb.getParallelExecutor();
        }
        return ModernAsyncTask.THREAD_POOL_EXECUTOR;
    }

    private ParallelExecutorCompat() {
    }
}
