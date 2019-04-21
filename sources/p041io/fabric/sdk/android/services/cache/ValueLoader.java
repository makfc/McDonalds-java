package p041io.fabric.sdk.android.services.cache;

import android.content.Context;

/* renamed from: io.fabric.sdk.android.services.cache.ValueLoader */
public interface ValueLoader<T> {
    T load(Context context) throws Exception;
}
