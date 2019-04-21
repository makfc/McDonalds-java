package p041io.fabric.sdk.android.services.concurrency;

/* renamed from: io.fabric.sdk.android.services.concurrency.PriorityProvider */
public interface PriorityProvider<T> extends Comparable<T> {
    Priority getPriority();
}
