package p041io.fabric.sdk.android.services.events;

import java.io.IOException;

/* renamed from: io.fabric.sdk.android.services.events.FileRollOverManager */
public interface FileRollOverManager {
    void cancelTimeBasedFileRollOver();

    boolean rollFileOver() throws IOException;
}
