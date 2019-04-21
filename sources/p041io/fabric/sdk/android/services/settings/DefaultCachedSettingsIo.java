package p041io.fabric.sdk.android.services.settings;

import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import org.json.JSONObject;
import p041io.fabric.sdk.android.Fabric;
import p041io.fabric.sdk.android.Kit;
import p041io.fabric.sdk.android.services.common.CommonUtils;
import p041io.fabric.sdk.android.services.persistence.FileStoreImpl;

/* renamed from: io.fabric.sdk.android.services.settings.DefaultCachedSettingsIo */
class DefaultCachedSettingsIo implements CachedSettingsIo {
    private final Kit kit;

    public DefaultCachedSettingsIo(Kit kit) {
        this.kit = kit;
    }

    public JSONObject readCachedSettings() {
        Exception e;
        Throwable th;
        Fabric.getLogger().mo34403d("Fabric", "Reading cached settings...");
        FileInputStream fis = null;
        JSONObject toReturn = null;
        try {
            File settingsFile = new File(new FileStoreImpl(this.kit).getFilesDir(), "com.crashlytics.settings.json");
            if (settingsFile.exists()) {
                FileInputStream fis2 = new FileInputStream(settingsFile);
                try {
                    toReturn = JSONObjectInstrumentation.init(CommonUtils.streamToString(fis2));
                    fis = fis2;
                } catch (Exception e2) {
                    e = e2;
                    fis = fis2;
                    try {
                        Fabric.getLogger().mo34406e("Fabric", "Failed to fetch cached settings", e);
                        CommonUtils.closeOrLog(fis, "Error while closing settings cache file.");
                        return toReturn;
                    } catch (Throwable th2) {
                        th = th2;
                        CommonUtils.closeOrLog(fis, "Error while closing settings cache file.");
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fis = fis2;
                    CommonUtils.closeOrLog(fis, "Error while closing settings cache file.");
                    throw th;
                }
            }
            Fabric.getLogger().mo34403d("Fabric", "No cached settings found.");
            CommonUtils.closeOrLog(fis, "Error while closing settings cache file.");
        } catch (Exception e3) {
            e = e3;
        }
        return toReturn;
    }

    public void writeCachedSettings(long expiresAtMillis, JSONObject settingsJson) {
        Exception e;
        Throwable th;
        Fabric.getLogger().mo34403d("Fabric", "Writing settings to cache file...");
        if (settingsJson != null) {
            FileWriter writer = null;
            try {
                settingsJson.put("expires_at", expiresAtMillis);
                FileWriter writer2 = new FileWriter(new File(new FileStoreImpl(this.kit).getFilesDir(), "com.crashlytics.settings.json"));
                try {
                    writer2.write(!(settingsJson instanceof JSONObject) ? settingsJson.toString() : JSONObjectInstrumentation.toString(settingsJson));
                    writer2.flush();
                    CommonUtils.closeOrLog(writer2, "Failed to close settings writer.");
                } catch (Exception e2) {
                    e = e2;
                    writer = writer2;
                } catch (Throwable th2) {
                    th = th2;
                    writer = writer2;
                    CommonUtils.closeOrLog(writer, "Failed to close settings writer.");
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    Fabric.getLogger().mo34406e("Fabric", "Failed to cache settings", e);
                    CommonUtils.closeOrLog(writer, "Failed to close settings writer.");
                } catch (Throwable th3) {
                    th = th3;
                    CommonUtils.closeOrLog(writer, "Failed to close settings writer.");
                    throw th;
                }
            }
        }
    }
}
