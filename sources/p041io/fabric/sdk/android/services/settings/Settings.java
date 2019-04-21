package p041io.fabric.sdk.android.services.settings;

import android.content.Context;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import p041io.fabric.sdk.android.Fabric;
import p041io.fabric.sdk.android.Kit;
import p041io.fabric.sdk.android.services.common.ApiKey;
import p041io.fabric.sdk.android.services.common.CommonUtils;
import p041io.fabric.sdk.android.services.common.CurrentTimeProvider;
import p041io.fabric.sdk.android.services.common.DataCollectionArbiter;
import p041io.fabric.sdk.android.services.common.DeliveryMechanism;
import p041io.fabric.sdk.android.services.common.IdManager;
import p041io.fabric.sdk.android.services.common.SystemCurrentTimeProvider;
import p041io.fabric.sdk.android.services.network.HttpRequestFactory;

/* renamed from: io.fabric.sdk.android.services.settings.Settings */
public class Settings {
    private boolean initialized;
    private SettingsController settingsController;
    private final AtomicReference<SettingsData> settingsData;
    private final CountDownLatch settingsDataLatch;

    /* renamed from: io.fabric.sdk.android.services.settings.Settings$LazyHolder */
    static class LazyHolder {
        private static final Settings INSTANCE = new Settings();

        LazyHolder() {
        }
    }

    /* renamed from: io.fabric.sdk.android.services.settings.Settings$SettingsAccess */
    public interface SettingsAccess<T> {
    }

    public static Settings getInstance() {
        return LazyHolder.INSTANCE;
    }

    private Settings() {
        this.settingsData = new AtomicReference();
        this.settingsDataLatch = new CountDownLatch(1);
        this.initialized = false;
    }

    public synchronized Settings initialize(Kit kit, IdManager idManager, HttpRequestFactory httpRequestFactory, String versionCode, String versionName, String urlEndpoint, DataCollectionArbiter dataCollectionArbiter) {
        Settings settings;
        if (this.initialized) {
            settings = this;
        } else {
            if (this.settingsController == null) {
                Context context = kit.getContext();
                String appIdentifier = idManager.getAppIdentifier();
                String apiKey = new ApiKey().getValue(context);
                String installerPackageName = idManager.getInstallerPackageName();
                CurrentTimeProvider currentTimeProvider = new SystemCurrentTimeProvider();
                SettingsJsonTransform settingsJsonTransform = new DefaultSettingsJsonTransform();
                CachedSettingsIo defaultCachedSettingsIo = new DefaultCachedSettingsIo(kit);
                String iconHash = CommonUtils.getAppIconHashOrNull(context);
                this.settingsController = new DefaultSettingsController(kit, new SettingsRequest(apiKey, idManager.getModelName(), idManager.getOsBuildVersionString(), idManager.getOsDisplayVersionString(), idManager.getAppInstallIdentifier(), CommonUtils.createInstanceIdFrom(CommonUtils.resolveBuildId(context)), versionName, versionCode, DeliveryMechanism.determineFrom(installerPackageName).getId(), iconHash), currentTimeProvider, settingsJsonTransform, defaultCachedSettingsIo, new DefaultSettingsSpiCall(kit, urlEndpoint, String.format(Locale.US, "https://settings.crashlytics.com/spi/v2/platforms/android/apps/%s/settings", new Object[]{appIdentifier}), httpRequestFactory), dataCollectionArbiter);
            }
            this.initialized = true;
            settings = this;
        }
        return settings;
    }

    public SettingsData awaitSettingsData() {
        try {
            this.settingsDataLatch.await();
            return (SettingsData) this.settingsData.get();
        } catch (InterruptedException e) {
            Fabric.getLogger().mo34405e("Fabric", "Interrupted while waiting for settings data.");
            return null;
        }
    }

    public synchronized boolean loadSettingsData() {
        SettingsData settingsData;
        settingsData = this.settingsController.loadSettingsData();
        setSettingsData(settingsData);
        return settingsData != null;
    }

    public synchronized boolean loadSettingsSkippingCache() {
        SettingsData settingsData;
        settingsData = this.settingsController.loadSettingsData(SettingsCacheBehavior.SKIP_CACHE_LOOKUP);
        setSettingsData(settingsData);
        if (settingsData == null) {
            Fabric.getLogger().mo34406e("Fabric", "Failed to force reload of settings from Crashlytics.", null);
        }
        return settingsData != null;
    }

    private void setSettingsData(SettingsData settingsData) {
        this.settingsData.set(settingsData);
        this.settingsDataLatch.countDown();
    }
}
