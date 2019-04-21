package com.amap.api.mapcore.util;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.File;

/* renamed from: com.amap.api.mapcore.util.dh */
public class ResourcesUtil {
    /* renamed from: a */
    private static boolean f1744a;

    static {
        f1744a = false;
        f1744a = new File("/system/framework/amap.jar").exists();
    }

    /* renamed from: a */
    public static AssetManager m2327a(Context context) {
        if (context == null) {
            return null;
        }
        AssetManager assets = context.getAssets();
        if (!f1744a) {
            return assets;
        }
        try {
            assets.getClass().getDeclaredMethod("addAssetPath", new Class[]{String.class}).invoke(assets, new Object[]{"/system/framework/amap.jar"});
            return assets;
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "ResourcesUtil", "getSelfAssets");
            return assets;
        }
    }
}
