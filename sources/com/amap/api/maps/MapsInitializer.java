package com.amap.api.maps;

import android.content.Context;
import android.os.RemoteException;
import com.amap.api.mapcore.util.C0811dm;
import com.amap.api.mapcore.util.MapFragmentDelegateImp;

public final class MapsInitializer {
    public static String KEY = null;
    /* renamed from: a */
    private static boolean f3109a = true;
    public static String sdcardDir = "";

    public static void initialize(Context context) throws RemoteException {
        MapFragmentDelegateImp.f974a = context.getApplicationContext();
    }

    public static void setNetWorkEnable(boolean z) {
        f3109a = z;
    }

    public static boolean getNetWorkEnable() {
        return f3109a;
    }

    public static void setApiKey(String str) {
        if (str != null && str.trim().length() > 0) {
            KEY = str;
            C0811dm.m2391a(str);
        }
    }

    public static String getVersion() {
        return "3.3.2";
    }
}
