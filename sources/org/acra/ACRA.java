package org.acra;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import org.acra.annotation.ReportsCrashes;
import org.acra.log.ACRALog;
import org.acra.log.AndroidLogDelegate;

public class ACRA {
    public static final boolean DEV_LOGGING = false;
    public static final String LOG_TAG = ACRA.class.getSimpleName();
    public static final String PREF_ALWAYS_ACCEPT = "acra.alwaysaccept";
    public static final String PREF_DISABLE_ACRA = "acra.disable";
    public static final String PREF_ENABLE_ACRA = "acra.enable";
    public static final String PREF_ENABLE_DEVICE_ID = "acra.deviceid.enable";
    public static final String PREF_ENABLE_SYSTEM_LOGS = "acra.syslog.enable";
    public static final String PREF_LAST_VERSION_NR = "acra.lastVersionNr";
    public static final String PREF_USER_EMAIL_ADDRESS = "acra.user.email";
    private static ACRAConfiguration configProxy;
    private static ErrorReporter errorReporterSingleton;
    public static ACRALog log = new AndroidLogDelegate();
    private static Application mApplication;
    private static OnSharedPreferenceChangeListener mPrefListener;

    /* renamed from: org.acra.ACRA$1 */
    static class C26301 implements OnSharedPreferenceChangeListener {
        C26301() {
        }

        public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (ACRA.PREF_DISABLE_ACRA.equals(key) || ACRA.PREF_ENABLE_ACRA.equals(key)) {
                ACRA.getErrorReporter().setEnabled(!ACRA.shouldDisableACRA(sharedPreferences));
            }
        }
    }

    public static void init(Application app) {
        init(app, new ACRAConfiguration());
    }

    public static void init(Application app, ACRAConfiguration config) {
        init(app, config, true);
    }

    public static void init(Application app, ACRAConfiguration config, boolean checkReportsOnApplicationStart) {
        boolean z = false;
        boolean z2 = VERSION.SDK_INT >= 8;
        if (!z2) {
            log.mo23353w(LOG_TAG, "ACRA 4.7.0+ requires Froyo or greater. ACRA is disabled and will NOT catch crashes or send messages.");
        }
        if (mApplication != null) {
            log.mo23353w(LOG_TAG, "ACRA#init called more than once. Won't do anything more.");
            return;
        }
        mApplication = app;
        if (config == null) {
            log.mo23349e(LOG_TAG, "ACRA#init called but no ACRAConfiguration provided");
            return;
        }
        configProxy = config;
        SharedPreferences aCRASharedPreferences = getACRASharedPreferences();
        try {
            checkCrashResources(config);
            if (z2 && !shouldDisableACRA(aCRASharedPreferences)) {
                z = true;
            }
            log.mo23347d(LOG_TAG, "ACRA is " + (z ? "enabled" : "disabled") + " for " + mApplication.getPackageName() + ", initializing...");
            ErrorReporter errorReporter = new ErrorReporter(mApplication, aCRASharedPreferences, z, z2);
            errorReporter.setDefaultReportSenders();
            errorReporterSingleton = errorReporter;
            if (checkReportsOnApplicationStart) {
                errorReporter.checkReportsOnApplicationStart();
            }
        } catch (ACRAConfigurationException e) {
            log.mo23354w(LOG_TAG, "Error : ", e);
        }
        mPrefListener = new C26301();
        aCRASharedPreferences.registerOnSharedPreferenceChangeListener(mPrefListener);
    }

    public static ErrorReporter getErrorReporter() {
        if (errorReporterSingleton != null) {
            return errorReporterSingleton;
        }
        throw new IllegalStateException("Cannot access ErrorReporter before ACRA#init");
    }

    private static boolean shouldDisableACRA(SharedPreferences prefs) {
        boolean z = true;
        try {
            boolean z2 = prefs.getBoolean(PREF_ENABLE_ACRA, true);
            String str = PREF_DISABLE_ACRA;
            if (z2) {
                z = false;
            }
            return prefs.getBoolean(str, z);
        } catch (Exception e) {
            return false;
        }
    }

    static void checkCrashResources(ReportsCrashes conf) throws ACRAConfigurationException {
        switch (conf.mode()) {
            case TOAST:
                if (conf.resToastText() == 0) {
                    throw new ACRAConfigurationException("TOAST mode: you have to define the resToastText parameter in your application @ReportsCrashes() annotation.");
                }
                return;
            case NOTIFICATION:
                if (conf.resNotifTickerText() == 0 || conf.resNotifTitle() == 0 || conf.resNotifText() == 0) {
                    throw new ACRAConfigurationException("NOTIFICATION mode: you have to define at least the resNotifTickerText, resNotifTitle, resNotifText parameters in your application @ReportsCrashes() annotation.");
                } else if (CrashReportDialog.class.equals(conf.reportDialogClass()) && conf.resDialogText() == 0) {
                    throw new ACRAConfigurationException("NOTIFICATION mode: using the (default) CrashReportDialog requires you have to define the resDialogText parameter in your application @ReportsCrashes() annotation.");
                } else {
                    return;
                }
            case DIALOG:
                if (CrashReportDialog.class.equals(conf.reportDialogClass()) && conf.resDialogText() == 0) {
                    throw new ACRAConfigurationException("DIALOG mode: using the (default) CrashReportDialog requires you to define the resDialogText parameter in your application @ReportsCrashes() annotation.");
                }
                return;
            default:
                return;
        }
    }

    public static SharedPreferences getACRASharedPreferences() {
        ACRAConfiguration config = getConfig();
        if ("".equals(config.sharedPreferencesName())) {
            return PreferenceManager.getDefaultSharedPreferences(mApplication);
        }
        return mApplication.getSharedPreferences(config.sharedPreferencesName(), config.sharedPreferencesMode());
    }

    public static ACRAConfiguration getConfig() {
        if (configProxy == null) {
            if (mApplication == null) {
                log.mo23353w(LOG_TAG, "Calling ACRA.getConfig() before ACRA.init() gives you an empty configuration instance. You might prefer calling ACRA.getNewDefaultConfig(Application) to get an instance with default values taken from a @ReportsCrashes annotation.");
            }
            configProxy = getNewDefaultConfig(mApplication);
        }
        return configProxy;
    }

    public static ACRAConfiguration getNewDefaultConfig(Application app) {
        if (app != null) {
            return new ACRAConfiguration((ReportsCrashes) app.getClass().getAnnotation(ReportsCrashes.class));
        }
        return new ACRAConfiguration(null);
    }

    static boolean isDebuggable() {
        try {
            if ((mApplication.getPackageManager().getApplicationInfo(mApplication.getPackageName(), 0).flags & 2) > 0) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    static Application getApplication() {
        return mApplication;
    }

    public static void setLog(ACRALog log) {
        if (log == null) {
            throw new NullPointerException("ACRALog cannot be null");
        }
        log = log;
    }
}
