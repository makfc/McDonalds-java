package p041io.fabric.sdk.android.services.concurrency;

/* renamed from: io.fabric.sdk.android.services.concurrency.Task */
public interface Task {
    boolean isFinished();

    void setError(Throwable th);

    void setFinished(boolean z);
}
