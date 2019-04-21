package p041io.fabric.sdk.android.services.common;

/* renamed from: io.fabric.sdk.android.services.common.SystemCurrentTimeProvider */
public class SystemCurrentTimeProvider implements CurrentTimeProvider {
    public long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }
}
