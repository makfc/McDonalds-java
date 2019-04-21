package p041io.fabric.sdk.android.services.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import p041io.fabric.sdk.android.Fabric;

/* renamed from: io.fabric.sdk.android.services.common.DataCollectionArbiter */
public class DataCollectionArbiter {
    private static DataCollectionArbiter instance;
    private static Object instanceLock = new Object();
    private volatile boolean crashlyticsDataCollectionEnabled;
    private volatile boolean crashlyticsDataCollectionExplicitlySet;
    private final FirebaseApp firebaseApp;
    private boolean isUnity = false;
    private final SharedPreferences sharedPreferences;

    public static DataCollectionArbiter getInstance(Context applicationContext) {
        DataCollectionArbiter dataCollectionArbiter;
        synchronized (instanceLock) {
            if (instance == null) {
                instance = new DataCollectionArbiter(applicationContext);
            }
            dataCollectionArbiter = instance;
        }
        return dataCollectionArbiter;
    }

    private DataCollectionArbiter(Context applicationContext) {
        boolean z = true;
        if (applicationContext == null) {
            throw new RuntimeException("null context");
        }
        this.sharedPreferences = applicationContext.getSharedPreferences("com.google.firebase.crashlytics.prefs", 0);
        this.firebaseApp = FirebaseAppImpl.getInstance(applicationContext);
        boolean enabled = true;
        boolean explicitlySet = false;
        if (this.sharedPreferences.contains("firebase_crashlytics_collection_enabled")) {
            enabled = this.sharedPreferences.getBoolean("firebase_crashlytics_collection_enabled", true);
            explicitlySet = true;
        } else {
            try {
                PackageManager packageManager = applicationContext.getPackageManager();
                if (packageManager != null) {
                    ApplicationInfo applicationInfo = packageManager.getApplicationInfo(applicationContext.getPackageName(), 128);
                    if (!(applicationInfo == null || applicationInfo.metaData == null || !applicationInfo.metaData.containsKey("firebase_crashlytics_collection_enabled"))) {
                        enabled = applicationInfo.metaData.getBoolean("firebase_crashlytics_collection_enabled");
                        explicitlySet = true;
                    }
                }
            } catch (NameNotFoundException e) {
                Fabric.getLogger().mo34404d("Fabric", "Unable to get PackageManager. Falling through", e);
            }
        }
        this.crashlyticsDataCollectionEnabled = enabled;
        this.crashlyticsDataCollectionExplicitlySet = explicitlySet;
        if (CommonUtils.resolveUnityEditorVersion(applicationContext) == null) {
            z = false;
        }
        this.isUnity = z;
    }

    public boolean isDataCollectionEnabled() {
        if (this.isUnity && this.crashlyticsDataCollectionExplicitlySet) {
            return this.crashlyticsDataCollectionEnabled;
        }
        if (this.firebaseApp != null) {
            return this.firebaseApp.isDataCollectionDefaultEnabled();
        }
        return true;
    }

    public boolean shouldAutoInitialize() {
        return this.crashlyticsDataCollectionEnabled;
    }
}
