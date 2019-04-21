package p041io.fabric.sdk.android;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import p041io.fabric.sdk.android.services.common.ApiKey;
import p041io.fabric.sdk.android.services.common.CommonUtils;
import p041io.fabric.sdk.android.services.common.DataCollectionArbiter;
import p041io.fabric.sdk.android.services.common.DeliveryMechanism;
import p041io.fabric.sdk.android.services.network.DefaultHttpRequestFactory;
import p041io.fabric.sdk.android.services.network.HttpRequestFactory;
import p041io.fabric.sdk.android.services.settings.AppRequestData;
import p041io.fabric.sdk.android.services.settings.AppSettingsData;
import p041io.fabric.sdk.android.services.settings.CreateAppSpiCall;
import p041io.fabric.sdk.android.services.settings.IconRequest;
import p041io.fabric.sdk.android.services.settings.Settings;
import p041io.fabric.sdk.android.services.settings.SettingsData;
import p041io.fabric.sdk.android.services.settings.UpdateAppSpiCall;

/* renamed from: io.fabric.sdk.android.Onboarding */
class Onboarding extends Kit<Boolean> {
    private String applicationLabel;
    private String installerPackageName;
    private final Future<Map<String, KitInfo>> kitsFinder;
    private PackageInfo packageInfo;
    private PackageManager packageManager;
    private String packageName;
    private final Collection<Kit> providedKits;
    private final HttpRequestFactory requestFactory = new DefaultHttpRequestFactory();
    private String targetAndroidSdkVersion;
    private String versionCode;
    private String versionName;

    public Onboarding(Future<Map<String, KitInfo>> kitsFinder, Collection<Kit> providedKits) {
        this.kitsFinder = kitsFinder;
        this.providedKits = providedKits;
    }

    public String getVersion() {
        return "1.4.8.32";
    }

    /* Access modifiers changed, original: protected */
    public boolean onPreExecute() {
        try {
            this.installerPackageName = getIdManager().getInstallerPackageName();
            this.packageManager = getContext().getPackageManager();
            this.packageName = getContext().getPackageName();
            this.packageInfo = this.packageManager.getPackageInfo(this.packageName, 0);
            this.versionCode = Integer.toString(this.packageInfo.versionCode);
            this.versionName = this.packageInfo.versionName == null ? "0.0" : this.packageInfo.versionName;
            this.applicationLabel = this.packageManager.getApplicationLabel(getContext().getApplicationInfo()).toString();
            this.targetAndroidSdkVersion = Integer.toString(getContext().getApplicationInfo().targetSdkVersion);
            return true;
        } catch (NameNotFoundException e) {
            Fabric.getLogger().mo34406e("Fabric", "Failed init", e);
            return false;
        }
    }

    /* Access modifiers changed, original: protected */
    public Boolean doInBackground() {
        String iconHash = CommonUtils.getAppIconHashOrNull(getContext());
        boolean appConfigured = false;
        SettingsData settingsData = retrieveSettingsData();
        if (settingsData != null) {
            try {
                Map<String, KitInfo> scannedKits;
                if (this.kitsFinder != null) {
                    scannedKits = (Map) this.kitsFinder.get();
                } else {
                    scannedKits = new HashMap();
                }
                appConfigured = performAutoConfigure(iconHash, settingsData.appData, mergeKits(scannedKits, this.providedKits).values());
            } catch (Exception e) {
                Fabric.getLogger().mo34406e("Fabric", "Error performing auto configuration.", e);
            }
        }
        return Boolean.valueOf(appConfigured);
    }

    private SettingsData retrieveSettingsData() {
        try {
            Settings.getInstance().initialize(this, this.idManager, this.requestFactory, this.versionCode, this.versionName, getOverridenSpiEndpoint(), DataCollectionArbiter.getInstance(getContext())).loadSettingsData();
            return Settings.getInstance().awaitSettingsData();
        } catch (Exception e) {
            Fabric.getLogger().mo34406e("Fabric", "Error dealing with settings", e);
            return null;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public Map<String, KitInfo> mergeKits(Map<String, KitInfo> scannedKits, Collection<Kit> providedKits) {
        for (Kit kit : providedKits) {
            if (!scannedKits.containsKey(kit.getIdentifier())) {
                scannedKits.put(kit.getIdentifier(), new KitInfo(kit.getIdentifier(), kit.getVersion(), "binary"));
            }
        }
        return scannedKits;
    }

    public String getIdentifier() {
        return "io.fabric.sdk.android:fabric";
    }

    private boolean performAutoConfigure(String iconHash, AppSettingsData appSettings, Collection<KitInfo> sdkKits) {
        if ("new".equals(appSettings.status)) {
            if (performCreateApp(iconHash, appSettings, sdkKits)) {
                return Settings.getInstance().loadSettingsSkippingCache();
            }
            Fabric.getLogger().mo34406e("Fabric", "Failed to create app with Crashlytics service.", null);
            return false;
        } else if ("configured".equals(appSettings.status)) {
            return Settings.getInstance().loadSettingsSkippingCache();
        } else {
            if (!appSettings.updateRequired) {
                return true;
            }
            Fabric.getLogger().mo34403d("Fabric", "Server says an update is required - forcing a full App update.");
            performUpdateApp(iconHash, appSettings, (Collection) sdkKits);
            return true;
        }
    }

    private boolean performCreateApp(String iconHash, AppSettingsData appSettings, Collection<KitInfo> sdkKits) {
        return new CreateAppSpiCall(this, getOverridenSpiEndpoint(), appSettings.url, this.requestFactory).invoke(buildAppRequest(IconRequest.build(getContext(), iconHash), sdkKits));
    }

    private boolean performUpdateApp(String iconHash, AppSettingsData appSettings, Collection<KitInfo> sdkKits) {
        return performUpdateApp(appSettings, IconRequest.build(getContext(), iconHash), (Collection) sdkKits);
    }

    private boolean performUpdateApp(AppSettingsData appSettings, IconRequest iconRequest, Collection<KitInfo> sdkKits) {
        return new UpdateAppSpiCall(this, getOverridenSpiEndpoint(), appSettings.url, this.requestFactory).invoke(buildAppRequest(iconRequest, sdkKits));
    }

    private AppRequestData buildAppRequest(IconRequest iconRequest, Collection<KitInfo> sdkKits) {
        return new AppRequestData(new ApiKey().getValue(getContext()), getIdManager().getAppIdentifier(), this.versionName, this.versionCode, CommonUtils.createInstanceIdFrom(CommonUtils.resolveBuildId(context)), this.applicationLabel, DeliveryMechanism.determineFrom(this.installerPackageName).getId(), this.targetAndroidSdkVersion, "0", iconRequest, sdkKits);
    }

    /* Access modifiers changed, original: 0000 */
    public String getOverridenSpiEndpoint() {
        return CommonUtils.getStringsFileValue(getContext(), "com.crashlytics.ApiEndpoint");
    }
}
