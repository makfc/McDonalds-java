package p041io.fabric.sdk.android.services.settings;

/* renamed from: io.fabric.sdk.android.services.settings.SettingsController */
public interface SettingsController {
    SettingsData loadSettingsData();

    SettingsData loadSettingsData(SettingsCacheBehavior settingsCacheBehavior);
}
