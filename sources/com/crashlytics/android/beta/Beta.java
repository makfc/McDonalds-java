package com.crashlytics.android.beta;

import java.util.Collections;
import java.util.Map;
import p041io.fabric.sdk.android.Fabric;
import p041io.fabric.sdk.android.Kit;
import p041io.fabric.sdk.android.services.common.DeviceIdentifierProvider;
import p041io.fabric.sdk.android.services.common.IdManager.DeviceIdentifierType;

public class Beta extends Kit<Boolean> implements DeviceIdentifierProvider {
    /* Access modifiers changed, original: protected */
    public Boolean doInBackground() {
        Fabric.getLogger().mo34403d("Beta", "Beta kit initializing...");
        return Boolean.valueOf(true);
    }

    public Map<DeviceIdentifierType, String> getDeviceIdentifiers() {
        return Collections.emptyMap();
    }

    public String getIdentifier() {
        return "com.crashlytics.sdk.android:beta";
    }

    public String getVersion() {
        return "1.2.10.27";
    }
}
