package com.crashlytics.android.core;

import com.facebook.stetho.common.Utf8Charset;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import p041io.fabric.sdk.android.Fabric;
import p041io.fabric.sdk.android.services.common.CommonUtils;

class MetaDataStore {
    private static final Charset UTF_8 = Charset.forName(Utf8Charset.NAME);
    private final File filesDir;

    public MetaDataStore(File filesDir) {
        this.filesDir = filesDir;
    }

    public void writeUserData(String sessionId, UserMetaData data) {
        Exception e;
        Throwable th;
        File f = getUserDataFileForSession(sessionId);
        Writer writer = null;
        try {
            String userDataString = userDataToJson(data);
            Writer writer2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), UTF_8));
            try {
                writer2.write(userDataString);
                writer2.flush();
                CommonUtils.closeOrLog(writer2, "Failed to close user metadata file.");
                writer = writer2;
            } catch (Exception e2) {
                e = e2;
                writer = writer2;
                try {
                    Fabric.getLogger().mo34406e("CrashlyticsCore", "Error serializing user metadata.", e);
                    CommonUtils.closeOrLog(writer, "Failed to close user metadata file.");
                } catch (Throwable th2) {
                    th = th2;
                    CommonUtils.closeOrLog(writer, "Failed to close user metadata file.");
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                writer = writer2;
                CommonUtils.closeOrLog(writer, "Failed to close user metadata file.");
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            Fabric.getLogger().mo34406e("CrashlyticsCore", "Error serializing user metadata.", e);
            CommonUtils.closeOrLog(writer, "Failed to close user metadata file.");
        }
    }

    public UserMetaData readUserData(String sessionId) {
        Exception e;
        Throwable th;
        File f = getUserDataFileForSession(sessionId);
        if (!f.exists()) {
            return UserMetaData.EMPTY;
        }
        InputStream is = null;
        try {
            InputStream is2 = new FileInputStream(f);
            try {
                UserMetaData jsonToUserData = jsonToUserData(CommonUtils.streamToString(is2));
                CommonUtils.closeOrLog(is2, "Failed to close user metadata file.");
                return jsonToUserData;
            } catch (Exception e2) {
                e = e2;
                is = is2;
                try {
                    Fabric.getLogger().mo34406e("CrashlyticsCore", "Error deserializing user metadata.", e);
                    CommonUtils.closeOrLog(is, "Failed to close user metadata file.");
                    return UserMetaData.EMPTY;
                } catch (Throwable th2) {
                    th = th2;
                    CommonUtils.closeOrLog(is, "Failed to close user metadata file.");
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                is = is2;
                CommonUtils.closeOrLog(is, "Failed to close user metadata file.");
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            Fabric.getLogger().mo34406e("CrashlyticsCore", "Error deserializing user metadata.", e);
            CommonUtils.closeOrLog(is, "Failed to close user metadata file.");
            return UserMetaData.EMPTY;
        }
    }

    public void writeKeyData(String sessionId, Map<String, String> keyData) {
        Exception e;
        Throwable th;
        File f = getKeysFileForSession(sessionId);
        Writer writer = null;
        try {
            String keyDataString = keysDataToJson(keyData);
            Writer writer2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), UTF_8));
            try {
                writer2.write(keyDataString);
                writer2.flush();
                CommonUtils.closeOrLog(writer2, "Failed to close key/value metadata file.");
                writer = writer2;
            } catch (Exception e2) {
                e = e2;
                writer = writer2;
                try {
                    Fabric.getLogger().mo34406e("CrashlyticsCore", "Error serializing key/value metadata.", e);
                    CommonUtils.closeOrLog(writer, "Failed to close key/value metadata file.");
                } catch (Throwable th2) {
                    th = th2;
                    CommonUtils.closeOrLog(writer, "Failed to close key/value metadata file.");
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                writer = writer2;
                CommonUtils.closeOrLog(writer, "Failed to close key/value metadata file.");
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            Fabric.getLogger().mo34406e("CrashlyticsCore", "Error serializing key/value metadata.", e);
            CommonUtils.closeOrLog(writer, "Failed to close key/value metadata file.");
        }
    }

    public File getUserDataFileForSession(String sessionId) {
        return new File(this.filesDir, sessionId + "user" + ".meta");
    }

    public File getKeysFileForSession(String sessionId) {
        return new File(this.filesDir, sessionId + "keys" + ".meta");
    }

    private static UserMetaData jsonToUserData(String json) throws JSONException {
        JSONObject dataObj = JSONObjectInstrumentation.init(json);
        return new UserMetaData(valueOrNull(dataObj, AnalyticAttribute.USER_ID_ATTRIBUTE), valueOrNull(dataObj, "userName"), valueOrNull(dataObj, "userEmail"));
    }

    private static String userDataToJson(final UserMetaData userData) throws JSONException {
        return new JSONObject() {
        }.toString();
    }

    private static String keysDataToJson(Map<String, String> keyData) throws JSONException {
        JSONObject jSONObject = new JSONObject(keyData);
        return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
    }

    private static String valueOrNull(JSONObject json, String key) {
        return !json.isNull(key) ? json.optString(key, null) : null;
    }
}
