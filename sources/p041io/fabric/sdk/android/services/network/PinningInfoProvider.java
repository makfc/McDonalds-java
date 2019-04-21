package p041io.fabric.sdk.android.services.network;

import java.io.InputStream;

/* renamed from: io.fabric.sdk.android.services.network.PinningInfoProvider */
public interface PinningInfoProvider {
    String getKeyStorePassword();

    InputStream getKeyStoreStream();

    long getPinCreationTimeInMillis();

    String[] getPins();
}
