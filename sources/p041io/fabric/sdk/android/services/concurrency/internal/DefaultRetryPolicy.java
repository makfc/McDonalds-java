package p041io.fabric.sdk.android.services.concurrency.internal;

/* renamed from: io.fabric.sdk.android.services.concurrency.internal.DefaultRetryPolicy */
public class DefaultRetryPolicy implements RetryPolicy {
    private final int maxRetries;

    public DefaultRetryPolicy() {
        this(1);
    }

    public DefaultRetryPolicy(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public boolean shouldRetry(int retries, Throwable e) {
        return retries < this.maxRetries;
    }
}
