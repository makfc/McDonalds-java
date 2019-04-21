package org.acra.collector;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.text.TextUtils;
import com.facebook.internal.ServerProtocol;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.util.Installation;
import org.acra.util.PackageManagerWrapper;
import org.acra.util.ReportUtils;

public final class CrashReportDataFactory {
    private final Calendar appStartDate;
    private final Context context;
    private final Map<String, String> customParameters = new LinkedHashMap();
    private final String initialConfiguration;
    private final SharedPreferences prefs;

    public CrashReportDataFactory(Context context, SharedPreferences prefs, Calendar appStartDate, String initialConfiguration) {
        this.context = context;
        this.prefs = prefs;
        this.appStartDate = appStartDate;
        this.initialConfiguration = initialConfiguration;
    }

    public final CrashReportData createCrashData(String msg, Throwable th, Map<String, String> customData, boolean isSilentReport, Thread brokenThread) {
        CrashReportData crashReportData = new CrashReportData();
        List reportFields = ACRA.getConfig().getReportFields();
        try {
            crashReportData.put(ReportField.STACK_TRACE, getStackTrace(msg, th));
        } catch (RuntimeException e) {
            ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving STACK_TRACE data", e);
        }
        try {
            crashReportData.put(ReportField.USER_APP_START_DATE, ReportUtils.getTimeString(this.appStartDate));
        } catch (RuntimeException e2) {
            ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving USER_APP_START_DATE data", e2);
        }
        if (isSilentReport) {
            crashReportData.put(ReportField.IS_SILENT, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        }
        if (reportFields.contains(ReportField.STACK_TRACE_HASH)) {
            try {
                crashReportData.put(ReportField.STACK_TRACE_HASH, getStackTraceHash(th));
            } catch (RuntimeException e22) {
                ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving STACK_TRACE_HASH data", e22);
            }
        }
        try {
            Object obj;
            if (reportFields.contains(ReportField.REPORT_ID)) {
                try {
                    crashReportData.put(ReportField.REPORT_ID, UUID.randomUUID().toString());
                } catch (RuntimeException e222) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving REPORT_ID data", e222);
                }
            }
            if (reportFields.contains(ReportField.INSTALLATION_ID)) {
                try {
                    crashReportData.put(ReportField.INSTALLATION_ID, Installation.m7500id(this.context));
                } catch (RuntimeException e2222) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving INSTALLATION_ID data", e2222);
                }
            }
            if (reportFields.contains(ReportField.INITIAL_CONFIGURATION)) {
                try {
                    crashReportData.put(ReportField.INITIAL_CONFIGURATION, this.initialConfiguration);
                } catch (RuntimeException e22222) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving INITIAL_CONFIGURATION data", e22222);
                }
            }
            if (reportFields.contains(ReportField.CRASH_CONFIGURATION)) {
                try {
                    crashReportData.put(ReportField.CRASH_CONFIGURATION, ConfigurationCollector.collectConfiguration(this.context));
                } catch (RuntimeException e222222) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving CRASH_CONFIGURATION data", e222222);
                }
            }
            if (!(th instanceof OutOfMemoryError) && reportFields.contains(ReportField.DUMPSYS_MEMINFO)) {
                try {
                    crashReportData.put(ReportField.DUMPSYS_MEMINFO, DumpSysCollector.collectMemInfo());
                } catch (RuntimeException e2222222) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving DUMPSYS_MEMINFO data", e2222222);
                }
            }
            if (reportFields.contains(ReportField.PACKAGE_NAME)) {
                try {
                    crashReportData.put(ReportField.PACKAGE_NAME, this.context.getPackageName());
                } catch (RuntimeException e22222222) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving PACKAGE_NAME data", e22222222);
                }
            }
            if (reportFields.contains(ReportField.BUILD)) {
                try {
                    crashReportData.put(ReportField.BUILD, ReflectionCollector.collectConstants(Build.class) + ReflectionCollector.collectConstants(VERSION.class, "VERSION"));
                } catch (RuntimeException e222222222) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving BUILD data", e222222222);
                }
            }
            if (reportFields.contains(ReportField.PHONE_MODEL)) {
                try {
                    crashReportData.put(ReportField.PHONE_MODEL, Build.MODEL);
                } catch (RuntimeException e2222222222) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving PHONE_MODEL data", e2222222222);
                }
            }
            if (reportFields.contains(ReportField.ANDROID_VERSION)) {
                try {
                    crashReportData.put(ReportField.ANDROID_VERSION, VERSION.RELEASE);
                } catch (RuntimeException e22222222222) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving ANDROID_VERSION data", e22222222222);
                }
            }
            if (reportFields.contains(ReportField.BRAND)) {
                try {
                    crashReportData.put(ReportField.BRAND, Build.BRAND);
                } catch (RuntimeException e222222222222) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving BRAND data", e222222222222);
                }
            }
            if (reportFields.contains(ReportField.PRODUCT)) {
                try {
                    crashReportData.put(ReportField.PRODUCT, Build.PRODUCT);
                } catch (RuntimeException e2222222222222) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving PRODUCT data", e2222222222222);
                }
            }
            if (reportFields.contains(ReportField.TOTAL_MEM_SIZE)) {
                try {
                    crashReportData.put(ReportField.TOTAL_MEM_SIZE, Long.toString(ReportUtils.getTotalInternalMemorySize()));
                } catch (RuntimeException e22222222222222) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving TOTAL_MEM_SIZE data", e22222222222222);
                }
            }
            if (reportFields.contains(ReportField.AVAILABLE_MEM_SIZE)) {
                try {
                    crashReportData.put(ReportField.AVAILABLE_MEM_SIZE, Long.toString(ReportUtils.getAvailableInternalMemorySize()));
                } catch (RuntimeException e222222222222222) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving AVAILABLE_MEM_SIZE data", e222222222222222);
                }
            }
            if (reportFields.contains(ReportField.FILE_PATH)) {
                try {
                    crashReportData.put(ReportField.FILE_PATH, ReportUtils.getApplicationFilePath(this.context));
                } catch (RuntimeException e2222222222222222) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving FILE_PATH data", e2222222222222222);
                }
            }
            if (reportFields.contains(ReportField.DISPLAY)) {
                try {
                    crashReportData.put(ReportField.DISPLAY, DisplayManagerCollector.collectDisplays(this.context));
                } catch (RuntimeException e22222222222222222) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving DISPLAY data", e22222222222222222);
                }
            }
            if (reportFields.contains(ReportField.USER_CRASH_DATE)) {
                try {
                    crashReportData.put(ReportField.USER_CRASH_DATE, ReportUtils.getTimeString(new GregorianCalendar()));
                } catch (RuntimeException e222222222222222222) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving USER_CRASH_DATE data", e222222222222222222);
                }
            }
            if (reportFields.contains(ReportField.CUSTOM_DATA)) {
                try {
                    crashReportData.put(ReportField.CUSTOM_DATA, createCustomInfoString(customData));
                } catch (RuntimeException e2222222222222222222) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving CUSTOM_DATA data", e2222222222222222222);
                }
            }
            if (reportFields.contains(ReportField.BUILD_CONFIG)) {
                try {
                    crashReportData.put(ReportField.BUILD_CONFIG, ReflectionCollector.collectConstants(getBuildConfigClass()));
                } catch (ClassNotFoundException e3) {
                } catch (RuntimeException e22222222222222222222) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving BUILD_CONFIG data", e22222222222222222222);
                }
            }
            if (reportFields.contains(ReportField.USER_EMAIL)) {
                try {
                    crashReportData.put(ReportField.USER_EMAIL, this.prefs.getString(ACRA.PREF_USER_EMAIL_ADDRESS, "N/A"));
                } catch (RuntimeException e222222222222222222222) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving USER_EMAIL data", e222222222222222222222);
                }
            }
            if (reportFields.contains(ReportField.DEVICE_FEATURES)) {
                try {
                    crashReportData.put(ReportField.DEVICE_FEATURES, DeviceFeaturesCollector.getFeatures(this.context));
                } catch (RuntimeException e2222222222222222222222) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving DEVICE_FEATURES data", e2222222222222222222222);
                }
            }
            if (reportFields.contains(ReportField.ENVIRONMENT)) {
                try {
                    crashReportData.put(ReportField.ENVIRONMENT, ReflectionCollector.collectStaticGettersResults(Environment.class));
                } catch (RuntimeException e22222222222222222222222) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving ENVIRONMENT data", e22222222222222222222222);
                }
            }
            if (reportFields.contains(ReportField.SETTINGS_SYSTEM)) {
                try {
                    crashReportData.put(ReportField.SETTINGS_SYSTEM, SettingsCollector.collectSystemSettings(this.context));
                } catch (RuntimeException e222222222222222222222222) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving SETTINGS_SYSTEM data", e222222222222222222222222);
                }
            }
            if (reportFields.contains(ReportField.SETTINGS_SECURE)) {
                try {
                    crashReportData.put(ReportField.SETTINGS_SECURE, SettingsCollector.collectSecureSettings(this.context));
                } catch (RuntimeException e2222222222222222222222222) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving SETTINGS_SECURE data", e2222222222222222222222222);
                }
            }
            if (reportFields.contains(ReportField.SETTINGS_GLOBAL)) {
                try {
                    crashReportData.put(ReportField.SETTINGS_GLOBAL, SettingsCollector.collectGlobalSettings(this.context));
                } catch (RuntimeException e22222222222222222222222222) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving SETTINGS_GLOBAL data", e22222222222222222222222222);
                }
            }
            if (reportFields.contains(ReportField.SHARED_PREFERENCES)) {
                try {
                    crashReportData.put(ReportField.SHARED_PREFERENCES, SharedPreferencesCollector.collect(this.context));
                } catch (RuntimeException e222222222222222222222222222) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving SHARED_PREFERENCES data", e222222222222222222222222222);
                }
            }
            PackageManagerWrapper packageManagerWrapper = new PackageManagerWrapper(this.context);
            try {
                PackageInfo packageInfo = packageManagerWrapper.getPackageInfo();
                if (packageInfo != null) {
                    if (reportFields.contains(ReportField.APP_VERSION_CODE)) {
                        crashReportData.put(ReportField.APP_VERSION_CODE, Integer.toString(packageInfo.versionCode));
                    }
                    if (reportFields.contains(ReportField.APP_VERSION_NAME)) {
                        ReportField reportField = ReportField.APP_VERSION_NAME;
                        if (packageInfo.versionName != null) {
                            obj = packageInfo.versionName;
                        } else {
                            obj = "not set";
                        }
                        crashReportData.put(reportField, obj);
                    }
                } else {
                    crashReportData.put(ReportField.APP_VERSION_NAME, "Package info unavailable");
                }
            } catch (RuntimeException e2222222222222222222222222222) {
                ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving APP_VERSION_CODE and APP_VERSION_NAME data", e2222222222222222222222222222);
            }
            if (reportFields.contains(ReportField.DEVICE_ID) && this.prefs.getBoolean(ACRA.PREF_ENABLE_DEVICE_ID, true) && packageManagerWrapper.hasPermission("android.permission.READ_PHONE_STATE")) {
                try {
                    String deviceId = ReportUtils.getDeviceId(this.context);
                    if (deviceId != null) {
                        crashReportData.put(ReportField.DEVICE_ID, deviceId);
                    }
                } catch (RuntimeException e22222222222222222222222222222) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving DEVICE_ID data", e22222222222222222222222222222);
                }
            }
            obj = (packageManagerWrapper.hasPermission("android.permission.READ_LOGS") || Compatibility.getAPILevel() >= 16) ? 1 : null;
            if (!this.prefs.getBoolean(ACRA.PREF_ENABLE_SYSTEM_LOGS, true) || obj == null) {
                ACRA.log.mo23351i(ACRA.LOG_TAG, "READ_LOGS not allowed. ACRA will not include LogCat and DropBox data.");
            } else {
                ACRA.log.mo23351i(ACRA.LOG_TAG, "READ_LOGS granted! ACRA can include LogCat and DropBox data.");
                if (reportFields.contains(ReportField.LOGCAT)) {
                    try {
                        crashReportData.put(ReportField.LOGCAT, LogCatCollector.collectLogCat(null));
                    } catch (RuntimeException e222222222222222222222222222222) {
                        ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving LOGCAT data", e222222222222222222222222222222);
                    }
                }
                if (reportFields.contains(ReportField.EVENTSLOG)) {
                    try {
                        crashReportData.put(ReportField.EVENTSLOG, LogCatCollector.collectLogCat("events"));
                    } catch (RuntimeException e2222222222222222222222222222222) {
                        ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving EVENTSLOG data", e2222222222222222222222222222222);
                    }
                }
                if (reportFields.contains(ReportField.RADIOLOG)) {
                    try {
                        crashReportData.put(ReportField.RADIOLOG, LogCatCollector.collectLogCat("radio"));
                    } catch (RuntimeException e22222222222222222222222222222222) {
                        ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving RADIOLOG data", e22222222222222222222222222222222);
                    }
                }
                if (reportFields.contains(ReportField.DROPBOX)) {
                    try {
                        crashReportData.put(ReportField.DROPBOX, DropBoxCollector.read(this.context, ACRA.getConfig().additionalDropBoxTags()));
                    } catch (RuntimeException e222222222222222222222222222222222) {
                        ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving DROPBOX data", e222222222222222222222222222222222);
                    }
                }
            }
            if (reportFields.contains(ReportField.APPLICATION_LOG)) {
                try {
                    crashReportData.put(ReportField.APPLICATION_LOG, LogFileCollector.collectLogFile(this.context, ACRA.getConfig().applicationLogFile(), ACRA.getConfig().applicationLogFileLines()));
                } catch (IOException e4) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while reading application log file " + ACRA.getConfig().applicationLogFile(), e4);
                } catch (RuntimeException e2222222222222222222222222222222222) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving APPLICATION_LOG data", e2222222222222222222222222222222222);
                }
            }
            if (reportFields.contains(ReportField.MEDIA_CODEC_LIST)) {
                try {
                    crashReportData.put(ReportField.MEDIA_CODEC_LIST, MediaCodecListCollector.collecMediaCodecList());
                } catch (RuntimeException e22222222222222222222222222222222222) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving MEDIA_CODEC_LIST data", e22222222222222222222222222222222222);
                }
            }
            if (reportFields.contains(ReportField.THREAD_DETAILS)) {
                try {
                    crashReportData.put(ReportField.THREAD_DETAILS, ThreadCollector.collect(brokenThread));
                } catch (RuntimeException e222222222222222222222222222222222222) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving THREAD_DETAILS data", e222222222222222222222222222222222222);
                }
            }
            if (reportFields.contains(ReportField.USER_IP)) {
                try {
                    crashReportData.put(ReportField.USER_IP, ReportUtils.getLocalIpAddress());
                } catch (RuntimeException e2222222222222222222222222222222222222) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving USER_IP data", e2222222222222222222222222222222222222);
                }
            }
        } catch (RuntimeException e22222222222222222222222222222222222222) {
            ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while retrieving crash data", e22222222222222222222222222222222222222);
        }
        return crashReportData;
    }

    private String createCustomInfoString(Map<String, String> reportCustomData) {
        Map map;
        Map map2 = this.customParameters;
        if (reportCustomData != null) {
            HashMap hashMap = new HashMap(map2);
            hashMap.putAll(reportCustomData);
            map = hashMap;
        } else {
            map = map2;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : map.keySet()) {
            String str2 = (String) map.get(str);
            stringBuilder.append(str);
            stringBuilder.append(" = ");
            if (str2 != null) {
                str2 = str2.replaceAll("\n", "\\\\n");
            }
            stringBuilder.append(str2);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private String getStackTrace(String msg, Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        if (!(msg == null || TextUtils.isEmpty(msg))) {
            printWriter.println(msg);
        }
        while (th != null) {
            th.printStackTrace(printWriter);
            th = th.getCause();
        }
        String obj = stringWriter.toString();
        printWriter.close();
        return obj;
    }

    private String getStackTraceHash(Throwable th) {
        StringBuilder stringBuilder = new StringBuilder();
        while (th != null) {
            for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                stringBuilder.append(stackTraceElement.getClassName());
                stringBuilder.append(stackTraceElement.getMethodName());
            }
            th = th.getCause();
        }
        return Integer.toHexString(stringBuilder.toString().hashCode());
    }

    private Class<?> getBuildConfigClass() throws ClassNotFoundException {
        Class buildConfigClass = ACRA.getConfig().buildConfigClass();
        if (buildConfigClass != null && !buildConfigClass.equals(Object.class)) {
            return buildConfigClass;
        }
        String str = this.context.getClass().getPackage().getName() + ".BuildConfig";
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            ACRA.log.mo23349e(ACRA.LOG_TAG, "Not adding buildConfig to log. Class Not found : " + str + ". Please configure 'buildConfigClass' in your ACRA config");
            throw e;
        }
    }
}
