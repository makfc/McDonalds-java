package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import com.facebook.internal.NativeProtocol;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.amap.api.mapcore.util.cf */
public class Utility {
    /* renamed from: a */
    public static void m2180a(String str) {
    }

    /* renamed from: a */
    public static long m2176a() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return ((long) statFs.getFreeBlocks()) * ((long) statFs.getBlockSize());
    }

    /* renamed from: b */
    public static List<OfflineMapProvince> m2183b(String str) throws JSONException {
        ArrayList arrayList = new ArrayList();
        if (str == null || "".equals(str)) {
            return arrayList;
        }
        JSONObject optJSONObject = JSONObjectInstrumentation.init(str).optJSONObject("result");
        if (optJSONObject == null) {
            return arrayList;
        }
        optJSONObject = optJSONObject.optJSONObject("offlinemap_with_province_vfour");
        if (optJSONObject == null) {
            return arrayList;
        }
        JSONObject optJSONObject2 = optJSONObject.optJSONObject("offlinemapinfo_with_province");
        if (optJSONObject2 == null) {
            return arrayList;
        }
        if (optJSONObject2.has("version")) {
            OfflineDownloadManager.f1382d = optJSONObject2.optString("version");
        }
        JSONArray optJSONArray = optJSONObject2.optJSONArray("provinces");
        if (optJSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject3 = optJSONArray.optJSONObject(i);
            if (optJSONObject3 != null) {
                arrayList.add(Utility.m2178a(optJSONObject3));
            }
        }
        JSONArray optJSONArray2 = optJSONObject2.optJSONArray("others");
        optJSONObject = null;
        if (optJSONArray2 != null && optJSONArray2.length() > 0) {
            optJSONObject = optJSONArray2.getJSONObject(0);
        }
        if (optJSONObject == null) {
            return arrayList;
        }
        arrayList.add(Utility.m2178a(optJSONObject));
        return arrayList;
    }

    /* renamed from: a */
    public static OfflineMapProvince m2178a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        OfflineMapProvince offlineMapProvince = new OfflineMapProvince();
        offlineMapProvince.setUrl(jSONObject.optString(NativeProtocol.IMAGE_URL_KEY));
        offlineMapProvince.setProvinceName(jSONObject.optString("name"));
        offlineMapProvince.setJianpin(jSONObject.optString("jianpin"));
        offlineMapProvince.setPinyin(jSONObject.optString("pinyin"));
        offlineMapProvince.setProvinceCode(jSONObject.optString("adcode"));
        offlineMapProvince.setVersion(jSONObject.optString("version"));
        offlineMapProvince.setSize(Long.parseLong(jSONObject.optString("size")));
        offlineMapProvince.setCityList(Utility.m2182b(jSONObject));
        return offlineMapProvince;
    }

    /* renamed from: b */
    public static ArrayList<OfflineMapCity> m2182b(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray("cities");
        ArrayList<OfflineMapCity> arrayList = new ArrayList();
        if (optJSONArray == null) {
            return arrayList;
        }
        if (optJSONArray.length() == 0) {
            arrayList.add(Utility.m2185c(jSONObject));
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(Utility.m2185c(optJSONObject));
            }
        }
        return arrayList;
    }

    /* renamed from: c */
    public static OfflineMapCity m2185c(JSONObject jSONObject) {
        OfflineMapCity offlineMapCity = new OfflineMapCity();
        offlineMapCity.setAdcode(jSONObject.optString("adcode"));
        offlineMapCity.setUrl(jSONObject.optString(NativeProtocol.IMAGE_URL_KEY));
        offlineMapCity.setCity(jSONObject.optString("name"));
        offlineMapCity.setCode(jSONObject.optString("citycode"));
        offlineMapCity.setPinyin(jSONObject.optString("pinyin"));
        offlineMapCity.setJianpin(jSONObject.optString("jianpin"));
        offlineMapCity.setVersion(jSONObject.optString("version"));
        offlineMapCity.setSize(Long.parseLong(jSONObject.optString("size")));
        return offlineMapCity;
    }

    /* renamed from: a */
    public static long m2177a(File file) {
        if (!file.isDirectory()) {
            return file.length();
        }
        long j = 0;
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return 0;
        }
        for (File file2 : listFiles) {
            long a;
            if (file2.isDirectory()) {
                a = Utility.m2177a(file2);
            } else {
                a = file2.length();
            }
            j += a;
        }
        return j;
    }

    /* renamed from: b */
    public static boolean m2184b(File file) throws IOException, Exception {
        if (file == null || !file.exists()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isFile()) {
                    if (!listFiles[i].delete()) {
                        return false;
                    }
                } else if (!Utility.m2184b(listFiles[i])) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    /* renamed from: a */
    public static String m2179a(Context context, String str) {
        try {
            return Util.m2352a(ResourcesUtil.m2327a(context).open(str));
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "MapDownloadManager", "readOfflineAsset");
            th.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:11:0x0023=Splitter:B:11:0x0023, B:38:0x005e=Splitter:B:38:0x005e} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x006a A:{SYNTHETIC, Splitter:B:41:0x006a} */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x006f A:{SYNTHETIC, Splitter:B:44:0x006f} */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x002f A:{SYNTHETIC, Splitter:B:14:0x002f} */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0034 A:{SYNTHETIC, Splitter:B:17:0x0034} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x006a A:{SYNTHETIC, Splitter:B:41:0x006a} */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x006f A:{SYNTHETIC, Splitter:B:44:0x006f} */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0083 A:{SYNTHETIC, Splitter:B:53:0x0083} */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0088 A:{SYNTHETIC, Splitter:B:56:0x0088} */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0083 A:{SYNTHETIC, Splitter:B:53:0x0083} */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0088 A:{SYNTHETIC, Splitter:B:56:0x0088} */
    /* renamed from: c */
    public static java.lang.String m2186c(java.io.File r6) {
        /*
        r0 = 0;
        r1 = new java.lang.StringBuffer;
        r1.<init>();
        r3 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x00a1, IOException -> 0x005b, all -> 0x007d }
        r3.<init>(r6);	 Catch:{ FileNotFoundException -> 0x00a1, IOException -> 0x005b, all -> 0x007d }
        r2 = new java.io.BufferedReader;	 Catch:{ FileNotFoundException -> 0x00a6, IOException -> 0x009c, all -> 0x0096 }
        r4 = new java.io.InputStreamReader;	 Catch:{ FileNotFoundException -> 0x00a6, IOException -> 0x009c, all -> 0x0096 }
        r5 = "utf-8";
        r4.<init>(r3, r5);	 Catch:{ FileNotFoundException -> 0x00a6, IOException -> 0x009c, all -> 0x0096 }
        r2.<init>(r4);	 Catch:{ FileNotFoundException -> 0x00a6, IOException -> 0x009c, all -> 0x0096 }
    L_0x0018:
        r4 = r2.readLine();	 Catch:{ FileNotFoundException -> 0x0022, IOException -> 0x009f }
        if (r4 == 0) goto L_0x0038;
    L_0x001e:
        r1.append(r4);	 Catch:{ FileNotFoundException -> 0x0022, IOException -> 0x009f }
        goto L_0x0018;
    L_0x0022:
        r1 = move-exception;
    L_0x0023:
        r4 = "MapDownloadManager";
        r5 = "readOfflineSD filenotfound";
        com.amap.api.mapcore.util.SDKLogHandler.m2563a(r1, r4, r5);	 Catch:{ all -> 0x009a }
        r1.printStackTrace();	 Catch:{ all -> 0x009a }
        if (r2 == 0) goto L_0x0032;
    L_0x002f:
        r2.close();	 Catch:{ IOException -> 0x0051 }
    L_0x0032:
        if (r3 == 0) goto L_0x0037;
    L_0x0034:
        r3.close();	 Catch:{ IOException -> 0x0056 }
    L_0x0037:
        return r0;
    L_0x0038:
        r0 = r1.toString();	 Catch:{ FileNotFoundException -> 0x0022, IOException -> 0x009f }
        if (r2 == 0) goto L_0x0041;
    L_0x003e:
        r2.close();	 Catch:{ IOException -> 0x004c }
    L_0x0041:
        if (r3 == 0) goto L_0x0037;
    L_0x0043:
        r3.close();	 Catch:{ IOException -> 0x0047 }
        goto L_0x0037;
    L_0x0047:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0037;
    L_0x004c:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0041;
    L_0x0051:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0032;
    L_0x0056:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0037;
    L_0x005b:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
    L_0x005e:
        r4 = "MapDownloadManager";
        r5 = "readOfflineSD io";
        com.amap.api.mapcore.util.SDKLogHandler.m2563a(r1, r4, r5);	 Catch:{ all -> 0x009a }
        r1.printStackTrace();	 Catch:{ all -> 0x009a }
        if (r2 == 0) goto L_0x006d;
    L_0x006a:
        r2.close();	 Catch:{ IOException -> 0x0078 }
    L_0x006d:
        if (r3 == 0) goto L_0x0037;
    L_0x006f:
        r3.close();	 Catch:{ IOException -> 0x0073 }
        goto L_0x0037;
    L_0x0073:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0037;
    L_0x0078:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x006d;
    L_0x007d:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r0 = r1;
    L_0x0081:
        if (r2 == 0) goto L_0x0086;
    L_0x0083:
        r2.close();	 Catch:{ IOException -> 0x008c }
    L_0x0086:
        if (r3 == 0) goto L_0x008b;
    L_0x0088:
        r3.close();	 Catch:{ IOException -> 0x0091 }
    L_0x008b:
        throw r0;
    L_0x008c:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0086;
    L_0x0091:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x008b;
    L_0x0096:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x0081;
    L_0x009a:
        r0 = move-exception;
        goto L_0x0081;
    L_0x009c:
        r1 = move-exception;
        r2 = r0;
        goto L_0x005e;
    L_0x009f:
        r1 = move-exception;
        goto L_0x005e;
    L_0x00a1:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        goto L_0x0023;
    L_0x00a6:
        r1 = move-exception;
        r2 = r0;
        goto L_0x0023;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.Utility.m2186c(java.io.File):java.lang.String");
    }

    /* renamed from: a */
    public static void m2181a(String str, Context context) throws IOException, Exception {
        File[] listFiles = new File(Util.m2369b(context)).listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.exists() && file.getName().contains(str)) {
                    Utility.m2184b(file);
                }
            }
            Utility.m2187c(Util.m2369b(context));
        }
    }

    /* renamed from: c */
    public static void m2187c(String str) {
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (file2.exists() && file2.isDirectory()) {
                        String[] list = file2.list();
                        if (list == null) {
                            file2.delete();
                        } else if (list.length == 0) {
                            file2.delete();
                        }
                    }
                }
            }
        }
    }
}
