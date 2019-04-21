package com.crashlytics.android.core;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p041io.fabric.sdk.android.Fabric;

class BinaryImagesConverter {
    private final Context context;
    private final FileIdStrategy fileIdStrategy;

    interface FileIdStrategy {
        String createId(File file) throws IOException;
    }

    BinaryImagesConverter(Context context, FileIdStrategy fileIdStrategy) {
        this.context = context;
        this.fileIdStrategy = fileIdStrategy;
    }

    /* Access modifiers changed, original: 0000 */
    public byte[] convert(String raw) throws IOException {
        return generateBinaryImagesJsonString(parseProcMapsJsonFromString(raw));
    }

    /* Access modifiers changed, original: 0000 */
    public byte[] convert(BufferedReader reader) throws IOException {
        return generateBinaryImagesJsonString(parseProcMapsJsonFromStream(reader));
    }

    private JSONArray parseProcMapsJsonFromStream(BufferedReader reader) throws IOException {
        JSONArray binaryImagesJson = new JSONArray();
        while (true) {
            String mapEntryString = reader.readLine();
            if (mapEntryString == null) {
                return binaryImagesJson;
            }
            JSONObject mapJson = jsonFromMapEntryString(mapEntryString);
            if (mapJson != null) {
                binaryImagesJson.put(mapJson);
            }
        }
    }

    private JSONArray parseProcMapsJsonFromString(String rawProcMapsString) {
        JSONArray binaryImagesJson = new JSONArray();
        try {
            String[] mapsEntries = joinMapsEntries(JSONObjectInstrumentation.init(rawProcMapsString).getJSONArray("maps")).split("\\|");
            for (String mapEntryString : mapsEntries) {
                JSONObject mapJson = jsonFromMapEntryString(mapEntryString);
                if (mapJson != null) {
                    binaryImagesJson.put(mapJson);
                }
            }
        } catch (JSONException e) {
            Fabric.getLogger().mo34412w("CrashlyticsCore", "Unable to parse proc maps string", e);
        }
        return binaryImagesJson;
    }

    private JSONObject jsonFromMapEntryString(String mapEntryString) {
        JSONObject jSONObject = null;
        ProcMapEntry mapInfo = ProcMapEntryParser.parse(mapEntryString);
        if (mapInfo == null || !isRelevant(mapInfo)) {
            return jSONObject;
        }
        try {
            try {
                return createBinaryImageJson(this.fileIdStrategy.createId(getLibraryFile(mapInfo.path)), mapInfo);
            } catch (JSONException e) {
                Fabric.getLogger().mo34404d("CrashlyticsCore", "Could not create a binary image json string", e);
                return jSONObject;
            }
        } catch (IOException e2) {
            Fabric.getLogger().mo34404d("CrashlyticsCore", "Could not generate ID for file " + mapInfo.path, e2);
            return jSONObject;
        }
    }

    private File getLibraryFile(String path) {
        File libFile = new File(path);
        if (libFile.exists()) {
            return libFile;
        }
        return correctDataPath(libFile);
    }

    private File correctDataPath(File missingFile) {
        if (VERSION.SDK_INT < 9) {
            return missingFile;
        }
        if (missingFile.getAbsolutePath().startsWith("/data")) {
            try {
                missingFile = new File(this.context.getPackageManager().getApplicationInfo(this.context.getPackageName(), 0).nativeLibraryDir, missingFile.getName());
            } catch (NameNotFoundException e) {
                Fabric.getLogger().mo34406e("CrashlyticsCore", "Error getting ApplicationInfo", e);
            }
        }
        return missingFile;
    }

    private static byte[] generateBinaryImagesJsonString(JSONArray binaryImages) {
        JSONObject binaryImagesObject = new JSONObject();
        try {
            binaryImagesObject.put("binary_images", binaryImages);
            return (!(binaryImagesObject instanceof JSONObject) ? binaryImagesObject.toString() : JSONObjectInstrumentation.toString(binaryImagesObject)).getBytes();
        } catch (JSONException e) {
            Fabric.getLogger().mo34412w("CrashlyticsCore", "Binary images string is null", e);
            return new byte[0];
        }
    }

    private static JSONObject createBinaryImageJson(String uuid, ProcMapEntry mapEntry) throws JSONException {
        JSONObject binaryImage = new JSONObject();
        binaryImage.put("base_address", mapEntry.address);
        binaryImage.put("size", mapEntry.size);
        binaryImage.put("name", mapEntry.path);
        binaryImage.put(AnalyticAttribute.UUID_ATTRIBUTE, uuid);
        return binaryImage;
    }

    private static String joinMapsEntries(JSONArray array) throws JSONException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length(); i++) {
            sb.append(array.getString(i));
        }
        return sb.toString();
    }

    private static boolean isRelevant(ProcMapEntry mapEntry) {
        return (mapEntry.perms.indexOf(120) == -1 || mapEntry.path.indexOf(47) == -1) ? false : true;
    }
}
