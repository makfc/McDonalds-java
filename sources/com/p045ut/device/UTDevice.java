package com.p045ut.device;

import android.content.Context;

/* renamed from: com.ut.device.UTDevice */
public class UTDevice {
    public static String getUtdid(Context context) {
        return com.p044ta.utdid2.device.UTDevice.getUtdid(context);
    }

    @Deprecated
    public static String getUtdidForUpdate(Context context) {
        return com.p044ta.utdid2.device.UTDevice.getUtdidForUpdate(context);
    }

    @Deprecated
    public static String getAid(String str, String str2, Context context) {
        return "";
    }

    @Deprecated
    public static void getAidAsync(String str, String str2, Context context, AidCallback aidCallback) {
    }
}
