package p041io.fabric.sdk.android.services.common;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import p041io.fabric.sdk.android.Fabric;

/* renamed from: io.fabric.sdk.android.services.common.ApiKey */
public class ApiKey {
    public String getValue(Context context) {
        String apiKey = getApiKeyFromManifest(context);
        if (TextUtils.isEmpty(apiKey)) {
            apiKey = getApiKeyFromStrings(context);
        }
        if (TextUtils.isEmpty(apiKey)) {
            apiKey = getApiKeyFromFirebaseAppId(context);
        }
        if (TextUtils.isEmpty(apiKey)) {
            logErrorOrThrowException(context);
        }
        return apiKey;
    }

    /* Access modifiers changed, original: protected */
    public String getApiKeyFromFirebaseAppId(Context context) {
        return new FirebaseInfo().getApiKeyFromFirebaseAppId(context);
    }

    /* Access modifiers changed, original: protected */
    public String getApiKeyFromManifest(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle == null) {
                return null;
            }
            String apiKey = bundle.getString("io.fabric.ApiKey");
            if ("@string/twitter_consumer_secret".equals(apiKey)) {
                Fabric.getLogger().mo34403d("Fabric", "Ignoring bad default value for Fabric ApiKey set by FirebaseUI-Auth");
                apiKey = null;
            }
            if (apiKey != null) {
                return apiKey;
            }
            Fabric.getLogger().mo34403d("Fabric", "Falling back to Crashlytics key lookup from Manifest");
            return bundle.getString("com.crashlytics.ApiKey");
        } catch (Exception e) {
            Fabric.getLogger().mo34403d("Fabric", "Caught non-fatal exception while retrieving apiKey: " + e);
            return null;
        }
    }

    /* Access modifiers changed, original: protected */
    public String getApiKeyFromStrings(Context context) {
        int id = CommonUtils.getResourcesIdentifier(context, "io.fabric.ApiKey", "string");
        if (id == 0) {
            Fabric.getLogger().mo34403d("Fabric", "Falling back to Crashlytics key lookup from Strings");
            id = CommonUtils.getResourcesIdentifier(context, "com.crashlytics.ApiKey", "string");
        }
        if (id != 0) {
            return context.getResources().getString(id);
        }
        return null;
    }

    /* Access modifiers changed, original: protected */
    public void logErrorOrThrowException(Context context) {
        if (Fabric.isDebuggable() || CommonUtils.isAppDebuggable(context)) {
            throw new IllegalArgumentException(buildApiKeyInstructions());
        }
        Fabric.getLogger().mo34405e("Fabric", buildApiKeyInstructions());
    }

    /* Access modifiers changed, original: protected */
    public String buildApiKeyInstructions() {
        return "Fabric could not be initialized, API key missing from AndroidManifest.xml. Add the following tag to your Application element \n\t<meta-data android:name=\"io.fabric.ApiKey\" android:value=\"YOUR_API_KEY\"/>";
    }
}
