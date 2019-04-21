package com.mcdonalds.app.util;

import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.LocaleList;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.ensighten.Ensighten;
import com.mcdonalds.app.McDonaldsApplication;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import java.util.Locale;

public class LanguageUtil {
    public static void changeAppLanguage(Resources resources, String type) {
        Locale locale;
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.LanguageUtil", "changeAppLanguage", new Object[]{resources, type});
        if (TextUtils.isEmpty(type)) {
            type = Configuration.getSharedInstance().getCurrentLanguage();
        }
        DataLayerManager.getInstance().setState("content.language", type);
        android.content.res.Configuration config = resources.getConfiguration();
        DisplayMetrics dm = resources.getDisplayMetrics();
        if (type.equals("zh")) {
            locale = new Locale("zh", "HK");
            changeAppLang("zh", true);
        } else if (type.equals("en")) {
            locale = new Locale("en", "HK");
            changeAppLang("en", true);
        } else {
            locale = new Locale("en", "HK");
            changeAppLang("en", true);
        }
        Locale.setDefault(locale);
        McDonaldsApplication.getInstance().getResources().getConfiguration().locale = locale;
        if (VERSION.SDK_INT >= 17) {
            config.setLocale(locale);
        } else {
            config.locale = locale;
        }
        if (VERSION.SDK_INT >= 24) {
            LocaleList localeList = new LocaleList(new Locale[]{locale});
            config.setLocales(localeList);
            McDonaldsApplication.getInstance().getResources().getConfiguration().setLocales(localeList);
        }
        resources.updateConfiguration(config, dm);
    }

    public static void changeAppLang(String type, boolean isLocal) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.LanguageUtil", "changeAppLang", new Object[]{type, new Boolean(isLocal)});
        Editor editor = McDonaldsApplication.getInstance().getSharedPreferences("config_language", 0).edit();
        editor.putString("language", type);
        editor.commit();
        if (isLocal) {
            LocalDataManager.getSharedInstance().setDeviceLanguage(type);
        }
    }

    public static String getAppLanguage() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.LanguageUtil", "getAppLanguage", null);
        return McDonaldsApplication.getInstance().getSharedPreferences("config_language", 0).getString("language", "");
    }

    public static void setPrefRememberLogin(boolean type) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.LanguageUtil", "setPrefRememberLogin", new Object[]{new Boolean(type)});
        Editor editor = McDonaldsApplication.getInstance().getSharedPreferences("config_language", 0).edit();
        editor.putBoolean("rememberLogin", type);
        editor.commit();
    }

    public static boolean getPrefRememberLogin() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.LanguageUtil", "getPrefRememberLogin", null);
        return McDonaldsApplication.getInstance().getSharedPreferences("config_language", 0).getBoolean("rememberLogin", false);
    }

    public static void setClear(boolean isClear) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.LanguageUtil", "setClear", new Object[]{new Boolean(isClear)});
        Editor editor = McDonaldsApplication.getInstance().getSharedPreferences("config_language", 0).edit();
        editor.putBoolean("is_clear", isClear);
        editor.commit();
    }

    public static boolean isClear() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.LanguageUtil", "isClear", null);
        return McDonaldsApplication.getInstance().getSharedPreferences("config_language", 0).getBoolean("is_clear", false);
    }
}
