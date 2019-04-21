package com.crashlytics.android.answers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import java.io.File;
import p041io.fabric.sdk.android.Fabric;
import p041io.fabric.sdk.android.Kit;
import p041io.fabric.sdk.android.services.common.CommonUtils;
import p041io.fabric.sdk.android.services.common.Crash.FatalException;
import p041io.fabric.sdk.android.services.common.Crash.LoggedException;
import p041io.fabric.sdk.android.services.common.DataCollectionArbiter;
import p041io.fabric.sdk.android.services.common.FirebaseInfo;
import p041io.fabric.sdk.android.services.settings.Settings;
import p041io.fabric.sdk.android.services.settings.SettingsData;

public class Answers extends Kit<Boolean> {
    SessionAnalyticsManager analyticsManager;
    boolean firebaseEnabled = false;

    public void onException(LoggedException exception) {
        if (this.analyticsManager != null) {
            this.analyticsManager.onError(exception.getSessionId());
        }
    }

    public void onException(FatalException exception) {
        if (this.analyticsManager != null) {
            this.analyticsManager.onCrash(exception.getSessionId(), exception.getExceptionName());
        }
    }

    /* Access modifiers changed, original: protected */
    @SuppressLint({"NewApi"})
    public boolean onPreExecute() {
        String googlePlaySdkVersionString = "!SDK-VERSION-STRING!:com.crashlytics.sdk.android:answers:1.4.7.32";
        try {
            long installedAt;
            Context context = getContext();
            PackageManager packageManager = context.getPackageManager();
            String packageName = context.getPackageName();
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            String versionCode = Integer.toString(packageInfo.versionCode);
            String versionName = packageInfo.versionName == null ? "0.0" : packageInfo.versionName;
            if (VERSION.SDK_INT >= 9) {
                installedAt = packageInfo.firstInstallTime;
            } else {
                installedAt = new File(packageManager.getApplicationInfo(packageName, 0).sourceDir).lastModified();
            }
            this.analyticsManager = SessionAnalyticsManager.build(this, context, getIdManager(), versionCode, versionName, installedAt);
            this.analyticsManager.enable();
            this.firebaseEnabled = new FirebaseInfo().isFirebaseCrashlyticsEnabled(context);
            return true;
        } catch (Exception e) {
            Fabric.getLogger().mo34406e("Answers", "Error retrieving app properties", e);
            return false;
        }
    }

    /* Access modifiers changed, original: protected */
    public Boolean doInBackground() {
        if (DataCollectionArbiter.getInstance(getContext()).isDataCollectionEnabled()) {
            try {
                SettingsData settingsData = Settings.getInstance().awaitSettingsData();
                if (settingsData == null) {
                    Fabric.getLogger().mo34405e("Answers", "Failed to retrieve settings");
                    return Boolean.valueOf(false);
                } else if (settingsData.featuresData.collectAnalytics) {
                    Fabric.getLogger().mo34403d("Answers", "Analytics collection enabled");
                    this.analyticsManager.setAnalyticsSettingsData(settingsData.analyticsSettingsData, getOverridenSpiEndpoint());
                    return Boolean.valueOf(true);
                } else {
                    Fabric.getLogger().mo34403d("Answers", "Analytics collection disabled");
                    this.analyticsManager.disable();
                    return Boolean.valueOf(false);
                }
            } catch (Exception e) {
                Fabric.getLogger().mo34406e("Answers", "Error dealing with settings", e);
                return Boolean.valueOf(false);
            }
        }
        Fabric.getLogger().mo34403d("Fabric", "Analytics collection disabled, because data collection is disabled by Firebase.");
        this.analyticsManager.disable();
        return Boolean.valueOf(false);
    }

    public String getIdentifier() {
        return "com.crashlytics.sdk.android:answers";
    }

    public String getVersion() {
        return "1.4.7.32";
    }

    /* Access modifiers changed, original: 0000 */
    public String getOverridenSpiEndpoint() {
        return CommonUtils.getStringsFileValue(getContext(), "com.crashlytics.ApiEndpoint");
    }
}
