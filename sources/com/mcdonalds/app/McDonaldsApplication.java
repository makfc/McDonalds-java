package com.mcdonalds.app;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import com.crashlytics.android.Crashlytics;
import com.ensighten.Ensighten;
import com.ensighten.model.activity.EnsightenActivityHandler;
import com.facebook.stetho.Stetho;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.analytics.datalayer.view.CustomViewInterceptor;
import com.mcdonalds.app.home.dashboard.DashboardViewModel;
import com.mcdonalds.app.p043ui.font.FontManager;
import com.mcdonalds.app.startup.SplashActivity;
import com.mcdonalds.app.storelocator.StoresManager;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.McDonalds;
import com.mcdonalds.sdk.services.analytics.datalayer.DataLayer.Listener;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.notifications.NotificationCenter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Map;
import p041io.fabric.sdk.android.Fabric;
import p041io.github.inflationx.calligraphy3.CalligraphyConfig.Builder;
import p041io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import p041io.github.inflationx.viewpump.ViewPump;

public class McDonaldsApplication extends MultiDexApplication implements Listener {
    private static final String LOG_TAG = McDonaldsApplication.class.getSimpleName();
    private static String VERSION_CODE = "VERSION_CODE";
    private static UncaughtExceptionHandler mCaughtExceptionHandler = new C23561();
    private static UncaughtExceptionHandler mDefaultUEH;
    private static McDonaldsApplication sInstance;
    private boolean mColdStart = false;

    /* renamed from: com.mcdonalds.app.McDonaldsApplication$1 */
    static class C23561 implements UncaughtExceptionHandler {
        C23561() {
        }

        public void uncaughtException(Thread thread, Throwable ex) {
            Ensighten.evaluateEvent(this, "uncaughtException", new Object[]{thread, ex});
            if (VERSION.SDK_INT >= 19) {
                ((ActivityManager) McDonaldsApplication.getInstance().getSystemService("activity")).clearApplicationUserData();
            }
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.McDonaldsApplication", "access$000", null).uncaughtException(thread, ex);
        }
    }

    private static class NotificationListener extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            Ensighten.evaluateEvent(this, "onReceive", new Object[]{context, intent});
            if (Configuration.MCD_NOTIFICATION_CONFIGURATION_CHANGED.equals(intent.getAction())) {
                FontManager.initializeFonts();
                McDonaldsApplication.access$102(McDonaldsApplication.getInstance(), true);
            }
        }
    }

    public void onLowMemory() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onLowMemory", null);
        super.onLowMemory();
    }

    public void onTerminate() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onTerminate", null);
        super.onTerminate();
    }

    static /* synthetic */ boolean access$102(McDonaldsApplication x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.McDonaldsApplication", "access$102", new Object[]{x0, new Boolean(x1)});
        x0.mColdStart = x1;
        return x1;
    }

    public static McDonaldsApplication getInstance() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.McDonaldsApplication", "getInstance", null);
        return sInstance;
    }

    public static boolean isDebug() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.McDonaldsApplication", "isDebug", null);
        return false;
    }

    /* Access modifiers changed, original: protected */
    public void attachBaseContext(Context base) {
        Ensighten.evaluateEvent(this, "attachBaseContext", new Object[]{base});
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public void onCreate() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreate", null);
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        if (isDebug()) {
            Stetho.initializeWithDefaults(this);
        }
        this.mColdStart = true;
        String configJson = getConfigJson();
        if (VERSION.SDK_INT >= 21) {
            McDonalds.initialize(this, getReloadIntent(), configJson);
        } else {
            McDonalds.initialize(this, null, configJson);
        }
        NotificationCenter.getSharedInstance().addObserver(Configuration.MCD_NOTIFICATION_CONFIGURATION_CHANGED, new NotificationListener());
        DashboardViewModel.destroy();
        sInstance = this;
        if (DataLayerManager.isEnabled(Configuration.getSharedInstance())) {
            DataLayerManager.init(this, Configuration.getSharedInstance());
            ViewPump.init(ViewPump.builder().addInterceptor(new CustomViewInterceptor()).addInterceptor(new CalligraphyInterceptor(new Builder().setDefaultFontPath(FontManager.getFontSpecifications()).setFontAttrId(C2658R.attr.fontPath).build())).build());
            DataLayerManager.getInstance().setListener(this);
        } else {
            FontManager.initializeFonts();
        }
        UIUtils.init(this);
        StoresManager.getInstance().setContext(this);
    }

    public Intent getReloadIntent() {
        Ensighten.evaluateEvent(this, "getReloadIntent", null);
        return new Intent(this, SplashActivity.class);
    }

    public boolean isColdStart() {
        Ensighten.evaluateEvent(this, "isColdStart", null);
        boolean ret = this.mColdStart;
        this.mColdStart = false;
        return ret;
    }

    private String getConfigJson() {
        Editor editor;
        Ensighten.evaluateEvent(this, "getConfigJson", null);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("config", 0);
        SharedPreferences version = getApplicationContext().getSharedPreferences("version", 0);
        if (version.getInt(VERSION_CODE, -1) != 14) {
            editor = sharedPreferences.edit();
            editor.remove(Configuration.MERGED_CONFIG);
            editor.remove(Configuration.PREF_CURRENT_CONFIG);
            editor.remove(Configuration.PREF_CONFIG_KEY);
            editor.apply();
        }
        editor = version.edit();
        editor.putInt(VERSION_CODE, 14);
        editor.apply();
        String configKey = sharedPreferences.getString(Configuration.PREF_CONFIG_KEY, getString(C2658R.string.default_configuration));
        if (configKey.equals(Configuration.MERGED_CONFIG)) {
            return sharedPreferences.getString(Configuration.PREF_CURRENT_CONFIG, (String) AutoLoadedConfigurations.getSharedInstance().get(getString(C2658R.string.default_configuration)));
        }
        return (String) AutoLoadedConfigurations.getSharedInstance().get(configKey);
    }

    public void dataLayerLoggedEvent(String event, Map<String, Object> dataPoints, Map<String, Object> fullState) {
        Ensighten.evaluateEvent(this, "dataLayerLoggedEvent", new Object[]{event, dataPoints, fullState});
        DataLayerManager.getInstance().getAnalytics().log(event, dataPoints);
    }
}
