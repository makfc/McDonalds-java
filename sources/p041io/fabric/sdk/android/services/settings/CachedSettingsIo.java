package p041io.fabric.sdk.android.services.settings;

import org.json.JSONObject;

/* renamed from: io.fabric.sdk.android.services.settings.CachedSettingsIo */
public interface CachedSettingsIo {
    JSONObject readCachedSettings();

    void writeCachedSettings(long j, JSONObject jSONObject);
}
