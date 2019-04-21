package p041io.fabric.sdk.android.services.common;

import java.util.Map;
import p041io.fabric.sdk.android.services.common.IdManager.DeviceIdentifierType;

/* renamed from: io.fabric.sdk.android.services.common.DeviceIdentifierProvider */
public interface DeviceIdentifierProvider {
    Map<DeviceIdentifierType, String> getDeviceIdentifiers();
}
