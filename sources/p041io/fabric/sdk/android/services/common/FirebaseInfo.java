package p041io.fabric.sdk.android.services.common;

import android.content.Context;
import android.text.TextUtils;
import p041io.fabric.sdk.android.Fabric;

/* renamed from: io.fabric.sdk.android.services.common.FirebaseInfo */
public class FirebaseInfo {
    /* Access modifiers changed, original: 0000 */
    public String getApiKeyFromFirebaseAppId(Context context) {
        int id = CommonUtils.getResourcesIdentifier(context, "google_app_id", "string");
        if (id == 0) {
            return null;
        }
        Fabric.getLogger().mo34403d("Fabric", "Generating Crashlytics ApiKey from google_app_id in Strings");
        return createApiKeyFromFirebaseAppId(context.getResources().getString(id));
    }

    /* Access modifiers changed, original: 0000 */
    public String createApiKeyFromFirebaseAppId(String appId) {
        return CommonUtils.sha256(appId).substring(0, 40);
    }

    public boolean isFirebaseCrashlyticsEnabled(Context context) {
        if (CommonUtils.getBooleanResourceValue(context, "com.crashlytics.useFirebaseAppId", false)) {
            return true;
        }
        if (!hasGoogleAppId(context) || hasApiKey(context)) {
            return false;
        }
        return true;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean hasApiKey(Context context) {
        if (TextUtils.isEmpty(new ApiKey().getApiKeyFromManifest(context)) && TextUtils.isEmpty(new ApiKey().getApiKeyFromStrings(context))) {
            return false;
        }
        return true;
    }

    public boolean isAutoInitializeFlagEnabled(Context context) {
        int id = CommonUtils.getResourcesIdentifier(context, "io.fabric.auto_initialize", "bool");
        if (id == 0) {
            return false;
        }
        boolean autoInitialize = context.getResources().getBoolean(id);
        if (!autoInitialize) {
            return autoInitialize;
        }
        Fabric.getLogger().mo34403d("Fabric", "Found Fabric auto-initialization flag for joint Firebase/Fabric customers");
        return autoInitialize;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean hasGoogleAppId(Context context) {
        int id = CommonUtils.getResourcesIdentifier(context, "google_app_id", "string");
        if (id == 0 || TextUtils.isEmpty(context.getResources().getString(id))) {
            return false;
        }
        return true;
    }
}
