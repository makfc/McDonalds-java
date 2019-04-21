package p041io.fabric.sdk.android.services.concurrency.internal;

/* renamed from: io.fabric.sdk.android.services.concurrency.internal.RetryPolicy */
public interface RetryPolicy {
    boolean shouldRetry(int i, Throwable th);
}
