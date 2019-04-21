package com.crashlytics.android.answers;

import android.content.Context;
import java.util.Map;
import java.util.UUID;
import p041io.fabric.sdk.android.services.common.CommonUtils;
import p041io.fabric.sdk.android.services.common.IdManager;
import p041io.fabric.sdk.android.services.common.IdManager.DeviceIdentifierType;

class SessionMetadataCollector {
    private final Context context;
    private final IdManager idManager;
    private final String versionCode;
    private final String versionName;

    public SessionMetadataCollector(Context context, IdManager idManager, String versionCode, String versionName) {
        this.context = context;
        this.idManager = idManager;
        this.versionCode = versionCode;
        this.versionName = versionName;
    }

    public SessionEventMetadata getMetadata() {
        Map<DeviceIdentifierType, String> deviceIdentifiers = this.idManager.getDeviceIdentifiers();
        return new SessionEventMetadata(this.idManager.getAppIdentifier(), UUID.randomUUID().toString(), this.idManager.getAppInstallIdentifier(), this.idManager.isLimitAdTrackingEnabled(), (String) deviceIdentifiers.get(DeviceIdentifierType.FONT_TOKEN), CommonUtils.resolveBuildId(this.context), this.idManager.getOsVersionString(), this.idManager.getModelName(), this.versionCode, this.versionName);
    }
}
