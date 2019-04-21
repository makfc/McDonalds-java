package p041io.fabric.sdk.android.services.cache;

import android.content.Context;

/* renamed from: io.fabric.sdk.android.services.cache.MemoryValueCache */
public class MemoryValueCache<T> extends AbstractValueCache<T> {
    private T value;

    public MemoryValueCache() {
        this(null);
    }

    public MemoryValueCache(ValueCache<T> childCache) {
        super(childCache);
    }

    /* Access modifiers changed, original: protected */
    public T getCached(Context context) {
        return this.value;
    }

    /* Access modifiers changed, original: protected */
    public void cacheValue(Context context, T value) {
        this.value = value;
    }
}
