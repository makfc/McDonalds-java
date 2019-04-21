package p041io.fabric.sdk.android.services.events;

import java.io.IOException;

/* renamed from: io.fabric.sdk.android.services.events.EventTransform */
public interface EventTransform<T> {
    byte[] toBytes(T t) throws IOException;
}
