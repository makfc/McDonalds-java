package com.amap.api.maps2d;

import android.content.Context;
import android.os.RemoteException;
import com.amap.api.mapcore2d.C0893aq;
import com.amap.api.mapcore2d.C0963co;
import com.amap.api.mapcore2d.C1042p;

public final class MapsInitializer {
    /* renamed from: a */
    private static boolean f3371a = true;
    public static String sdcardDir = "";

    public static void initialize(Context context) throws RemoteException {
        C0893aq.f2276a = context.getApplicationContext();
    }

    public static void setNetworkEnable(boolean z) {
        f3371a = z;
    }

    public static String getVersion() {
        return "2.9.0";
    }

    public static boolean getNetworkEnable() {
        return f3371a;
    }

    public static void setApiKey(String str) {
        C0963co.m3931a(str);
    }

    public static void replaceURL(String str, String str2) {
        if (str != null && !str.equals("")) {
            C1042p.f3038h = str;
            C1042p.f3037g = str2 + "DIY";
            if (str.contains("openstreetmap")) {
                C1042p.f3033c = 19;
            }
        }
    }
}
