package p041io.fabric.sdk.android.services.settings;

import org.json.JSONObject;

/* renamed from: io.fabric.sdk.android.services.settings.SettingsSpiCall */
public interface SettingsSpiCall {
    JSONObject invoke(SettingsRequest settingsRequest);
}
