package p041io.fabric.sdk.android.services.settings;

import android.annotation.SuppressLint;
import android.content.SharedPreferences.Editor;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONException;
import org.json.JSONObject;
import p041io.fabric.sdk.android.Fabric;
import p041io.fabric.sdk.android.Kit;
import p041io.fabric.sdk.android.services.common.CommonUtils;
import p041io.fabric.sdk.android.services.common.CurrentTimeProvider;
import p041io.fabric.sdk.android.services.common.DataCollectionArbiter;
import p041io.fabric.sdk.android.services.persistence.PreferenceStore;
import p041io.fabric.sdk.android.services.persistence.PreferenceStoreImpl;

/* renamed from: io.fabric.sdk.android.services.settings.DefaultSettingsController */
class DefaultSettingsController implements SettingsController {
    private final CachedSettingsIo cachedSettingsIo;
    private final CurrentTimeProvider currentTimeProvider;
    private final DataCollectionArbiter dataCollectionArbiter;
    private final Kit kit;
    private final PreferenceStore preferenceStore = new PreferenceStoreImpl(this.kit);
    private final SettingsJsonTransform settingsJsonTransform;
    private final SettingsRequest settingsRequest;
    private final SettingsSpiCall settingsSpiCall;

    public DefaultSettingsController(Kit kit, SettingsRequest settingsRequest, CurrentTimeProvider currentTimeProvider, SettingsJsonTransform settingsJsonTransform, CachedSettingsIo cachedSettingsIo, SettingsSpiCall settingsSpiCall, DataCollectionArbiter dataCollectionArbiter) {
        this.kit = kit;
        this.settingsRequest = settingsRequest;
        this.currentTimeProvider = currentTimeProvider;
        this.settingsJsonTransform = settingsJsonTransform;
        this.cachedSettingsIo = cachedSettingsIo;
        this.settingsSpiCall = settingsSpiCall;
        this.dataCollectionArbiter = dataCollectionArbiter;
    }

    public SettingsData loadSettingsData() {
        return loadSettingsData(SettingsCacheBehavior.USE_CACHE);
    }

    public SettingsData loadSettingsData(SettingsCacheBehavior cacheBehavior) {
        if (this.dataCollectionArbiter.isDataCollectionEnabled()) {
            SettingsData toReturn = null;
            try {
                if (!(Fabric.isDebuggable() || buildInstanceIdentifierChanged())) {
                    toReturn = getCachedSettingsData(cacheBehavior);
                }
                if (toReturn == null) {
                    JSONObject settingsJson = this.settingsSpiCall.invoke(this.settingsRequest);
                    if (settingsJson != null) {
                        toReturn = this.settingsJsonTransform.buildFromJson(this.currentTimeProvider, settingsJson);
                        this.cachedSettingsIo.writeCachedSettings(toReturn.expiresAtMillis, settingsJson);
                        logSettings(settingsJson, "Loaded settings: ");
                        setStoredBuildInstanceIdentifier(getBuildInstanceIdentifierFromContext());
                    }
                }
                if (toReturn == null) {
                    return getCachedSettingsData(SettingsCacheBehavior.IGNORE_CACHE_EXPIRATION);
                }
                return toReturn;
            } catch (Exception e) {
                Fabric.getLogger().mo34406e("Fabric", "Unknown error while loading Crashlytics settings. Crashes will be cached until settings can be retrieved.", e);
                return null;
            }
        }
        Fabric.getLogger().mo34403d("Fabric", "Not fetching settings, because data collection is disabled by Firebase.");
        return null;
    }

    private SettingsData getCachedSettingsData(SettingsCacheBehavior cacheBehavior) {
        try {
            if (SettingsCacheBehavior.SKIP_CACHE_LOOKUP.equals(cacheBehavior)) {
                return null;
            }
            JSONObject settingsJson = this.cachedSettingsIo.readCachedSettings();
            if (settingsJson != null) {
                SettingsData settingsData = this.settingsJsonTransform.buildFromJson(this.currentTimeProvider, settingsJson);
                if (settingsData != null) {
                    logSettings(settingsJson, "Loaded cached settings: ");
                    long currentTimeMillis = this.currentTimeProvider.getCurrentTimeMillis();
                    if (SettingsCacheBehavior.IGNORE_CACHE_EXPIRATION.equals(cacheBehavior) || !settingsData.isExpired(currentTimeMillis)) {
                        SettingsData toReturn = settingsData;
                        Fabric.getLogger().mo34403d("Fabric", "Returning cached settings.");
                        return toReturn;
                    }
                    Fabric.getLogger().mo34403d("Fabric", "Cached settings have expired.");
                    return null;
                }
                Fabric.getLogger().mo34406e("Fabric", "Failed to transform cached settings data.", null);
                return null;
            }
            Fabric.getLogger().mo34403d("Fabric", "No cached settings data found.");
            return null;
        } catch (Exception e) {
            Fabric.getLogger().mo34406e("Fabric", "Failed to get cached settings", e);
            return null;
        }
    }

    private void logSettings(JSONObject json, String message) throws JSONException {
        Fabric.getLogger().mo34403d("Fabric", message + (!(json instanceof JSONObject) ? json.toString() : JSONObjectInstrumentation.toString(json)));
    }

    /* Access modifiers changed, original: 0000 */
    public String getBuildInstanceIdentifierFromContext() {
        return CommonUtils.createInstanceIdFrom(CommonUtils.resolveBuildId(this.kit.getContext()));
    }

    /* Access modifiers changed, original: 0000 */
    public String getStoredBuildInstanceIdentifier() {
        return this.preferenceStore.get().getString("existing_instance_identifier", "");
    }

    /* Access modifiers changed, original: 0000 */
    @SuppressLint({"CommitPrefEdits"})
    public boolean setStoredBuildInstanceIdentifier(String buildInstanceIdentifier) {
        Editor editor = this.preferenceStore.edit();
        editor.putString("existing_instance_identifier", buildInstanceIdentifier);
        return this.preferenceStore.save(editor);
    }

    /* Access modifiers changed, original: 0000 */
    public boolean buildInstanceIdentifierChanged() {
        return !getStoredBuildInstanceIdentifier().equals(getBuildInstanceIdentifierFromContext());
    }
}
