package com.admaster.jice.p006c;

import android.content.Context;
import android.text.TextUtils;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.admaster.jice.c.b */
public class DeviceInfoApi {
    /* renamed from: a */
    public static Map m168a(Context context) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        try {
            DeviceInfoApi.m169a(concurrentHashMap, "mac", StoreUtil.m197a(context, "mac", new String[0]), true);
            DeviceInfoApi.m169a(concurrentHashMap, "mac1", StoreUtil.m197a(context, "mac1", new String[0]), true);
            DeviceInfoApi.m169a(concurrentHashMap, "imei", StoreUtil.m197a(context, "imei", new String[0]), true);
            DeviceInfoApi.m169a(concurrentHashMap, "androidID", StoreUtil.m197a(context, "androidID", new String[0]), true);
            DeviceInfoApi.m169a(concurrentHashMap, "imsi", StoreUtil.m197a(context, "imsi", new String[0]), true);
            DeviceInfoApi.m169a(concurrentHashMap, "idfa", StoreUtil.m197a(context, "idfa", new String[0]), true);
            DeviceInfoApi.m169a(concurrentHashMap, "ap_mac", DeviceInfoUtil.m187k(context), true);
            DeviceInfoApi.m169a(concurrentHashMap, "ap_name", DeviceInfoUtil.m188l(context), true);
            DeviceInfoApi.m169a(concurrentHashMap, "model", DeviceInfoUtil.m176c(), true);
            DeviceInfoApi.m169a(concurrentHashMap, "brand", DeviceInfoUtil.m170a(), true);
            DeviceInfoApi.m169a(concurrentHashMap, "os", "android", true);
            DeviceInfoApi.m169a(concurrentHashMap, "osv", DeviceInfoUtil.m174b(), true);
            DeviceInfoApi.m169a(concurrentHashMap, "net", DeviceInfoUtil.m186j(context), true);
            DeviceInfoApi.m169a(concurrentHashMap, "screenpix", DeviceInfoUtil.m185i(context), true);
            DeviceInfoApi.m169a(concurrentHashMap, "mcc", DeviceInfoUtil.m181e(context), true);
            DeviceInfoApi.m169a(concurrentHashMap, "mnc", DeviceInfoUtil.m182f(context), true);
            DeviceInfoApi.m169a(concurrentHashMap, "package_name", DeviceInfoUtil.m175b(context), true);
            DeviceInfoApi.m169a(concurrentHashMap, "appv", DeviceInfoUtil.m177c(context), true);
            DeviceInfoApi.m169a(concurrentHashMap, "cid", DeviceInfoUtil.m189m(context), true);
            DeviceInfoApi.m169a(concurrentHashMap, "is_root", new StringBuilder(String.valueOf(DeviceInfoUtil.m178d())).toString(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return concurrentHashMap;
    }

    /* renamed from: a */
    private static void m169a(Map<String, String> map, String str, String str2, boolean z) {
        try {
            Object str22;
            if (TextUtils.isEmpty(str22)) {
                str22 = "";
            }
            map.put(str, str22);
        } catch (Exception e) {
        }
    }
}
