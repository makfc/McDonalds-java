package com.kochava.base;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.AnyThread;
import android.support.annotation.CheckResult;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import com.autonavi.amap.mapcore.MapTilsCacheAndResManager;
import com.facebook.internal.ServerProtocol;
import com.facebook.stetho.common.Utf8Charset;
import com.kochava.base.Tracker.ConsentPartner;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.jetbrains.annotations.Contract;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.kochava.base.d */
final class C2901d extends SQLiteOpenHelper {
    @NonNull
    /* renamed from: c */
    private static final Object f6586c = new Object();
    @NonNull
    /* renamed from: d */
    private static final Object f6587d = new Object();
    @VisibleForTesting
    @NonNull
    /* renamed from: a */
    final Map<String, Object> f6588a = new HashMap();
    @Nullable
    /* renamed from: b */
    SQLiteDatabase f6589b = null;
    @NonNull
    /* renamed from: e */
    private final List<String> f6590e = Arrays.asList(new String[]{"consent", "init_last_sent", "kvinit_staleness", "kvinit_wait", "consent_last_prompt", "deeplink_ran"});
    @NonNull
    /* renamed from: f */
    private final SharedPreferences f6591f;
    /* renamed from: g */
    private int f6592g = -1;
    /* renamed from: h */
    private int f6593h = -1;
    /* renamed from: i */
    private boolean f6594i = false;

    @AnyThread
    C2901d(@NonNull Context context, boolean z) {
        super(context, "kodb", null, 5);
        Tracker.m7517a(5, "DAB", "Database", new Object[0]);
        this.f6591f = context.getSharedPreferences("kosp", 0);
        m7634a(context, z);
    }

    @AnyThread
    @Contract(pure = true)
    @CheckResult
    /* renamed from: a */
    static double m7622a(double d, double d2, double d3) {
        return d < d2 ? d2 : d > d3 ? d3 : d;
    }

    @AnyThread
    @Contract(pure = true)
    @CheckResult
    /* renamed from: a */
    static double m7623a(@Nullable Object obj, double d) {
        Double e = C2901d.m7660e(obj);
        return e != null ? e.doubleValue() : d;
    }

    @AnyThread
    @Contract(pure = true)
    @CheckResult
    /* renamed from: a */
    static int m7624a(int i, int i2, int i3) {
        return i < i2 ? i2 : i > i3 ? i3 : i;
    }

    @AnyThread
    @Contract(pure = true)
    /* renamed from: a */
    static int m7625a(@Nullable Object obj, int i) {
        if (!(obj instanceof Integer)) {
            return obj instanceof String ? "NONE".equalsIgnoreCase((String) obj) ? 0 : "ERROR".equalsIgnoreCase((String) obj) ? 1 : "WARN".equalsIgnoreCase((String) obj) ? 2 : "INFO".equalsIgnoreCase((String) obj) ? 3 : Configuration.DEBUG_CONFIG_KEY.equalsIgnoreCase((String) obj) ? 4 : "TRACE".equalsIgnoreCase((String) obj) ? 5 : i : i;
        } else {
            int intValue = ((Integer) obj).intValue();
            return (intValue < 0 || intValue > 5) ? i : intValue;
        }
    }

    /* renamed from: a */
    static long m7626a() {
        return System.currentTimeMillis();
    }

    @AnyThread
    @Contract(pure = true)
    @CheckResult
    /* renamed from: a */
    public static long m7627a(@Nullable Object obj, long j) {
        Long d = C2901d.m7659d(obj);
        return d != null ? d.longValue() : j;
    }

    @AnyThread
    @Contract(pure = true, value = "null -> null")
    @CheckResult
    @Nullable
    /* renamed from: a */
    static String m7628a(@Nullable Object obj) {
        return obj instanceof String ? (String) obj : ((obj instanceof JSONObject) || (obj instanceof JSONArray)) ? obj.toString() : null;
    }

    @AnyThread
    @Contract(pure = true)
    @CheckResult
    @NonNull
    /* renamed from: a */
    static String m7629a(@Nullable Object obj, @NonNull String str) {
        String a = C2901d.m7628a(obj);
        return a != null ? a : str;
    }

    @WorkerThread
    @NonNull
    /* renamed from: a */
    static String m7630a(@NonNull String str, @NonNull String str2) {
        String str3 = "post";
        HttpURLConnection httpURLConnection = (HttpURLConnection) HttpInstrumentation.openConnection(new URL(str).openConnection());
        String str4 = null;
        try {
            str4 = System.getProperty("http.agent");
        } catch (Exception e) {
            Tracker.m7517a(4, "DAB", "post", e);
        }
        if (!(str4 == null || str4.trim().isEmpty())) {
            httpURLConnection.setRequestProperty("User-Agent", str4);
        }
        String str5 = "DAB";
        String str6 = "post";
        Object[] objArr = new Object[5];
        objArr[0] = "SEND>";
        objArr[1] = str4 != null ? str4 : "Unable to gather UserAgent";
        objArr[2] = str;
        objArr[3] = str2;
        objArr[4] = "<SEND";
        Tracker.m7517a(4, str5, str6, objArr);
        httpURLConnection.setRequestProperty(TransactionStateUtil.CONTENT_TYPE_HEADER, "application/json; charset=UTF-8");
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setConnectTimeout(30000);
        httpURLConnection.setReadTimeout(30000);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.connect();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream(), Utf8Charset.NAME);
        outputStreamWriter.write(str2);
        outputStreamWriter.close();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), Utf8Charset.NAME));
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            str6 = bufferedReader.readLine();
            if (str6 != null) {
                stringBuilder.append(str6);
            } else {
                bufferedReader.close();
                Tracker.m7517a(4, "DAB", "post", "RECEIVE>", str4, str, stringBuilder.toString(), "<RECEIVE");
                httpURLConnection.disconnect();
                return stringBuilder.toString();
            }
        }
    }

    @AnyThread
    @NonNull
    /* renamed from: a */
    static String m7631a(@NonNull JSONArray jSONArray) {
        String jSONArray2;
        try {
            jSONArray2 = !(jSONArray instanceof JSONArray) ? jSONArray.toString() : JSONArrayInstrumentation.toString(jSONArray);
        } catch (Throwable th) {
            Tracker.m7517a(2, "DAB", "jsonArrayToSt", th);
            jSONArray2 = null;
        }
        return jSONArray2 == null ? "[]" : jSONArray2;
    }

    @AnyThread
    @NonNull
    /* renamed from: a */
    static String m7632a(@NonNull JSONObject jSONObject) {
        String jSONObject2;
        try {
            jSONObject2 = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
        } catch (Throwable th) {
            Tracker.m7517a(2, "DAB", "jsonObjectToS", th);
            jSONObject2 = null;
        }
        return jSONObject2 == null ? "{}" : jSONObject2;
    }

    /* renamed from: a */
    static Map<String, String> m7633a(@Nullable String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (str == null || str.isEmpty()) {
            return linkedHashMap;
        }
        try {
            for (String str2 : str.split("&")) {
                Object decode;
                int indexOf = str2.indexOf("=");
                if (indexOf > 0) {
                    decode = URLDecoder.decode(str2.substring(0, indexOf), Utf8Charset.NAME);
                } else {
                    String decode2 = str2;
                }
                if (!linkedHashMap.containsKey(decode2)) {
                    Object decode3 = (indexOf <= 0 || str2.length() <= indexOf + 1) ? null : URLDecoder.decode(str2.substring(indexOf + 1), Utf8Charset.NAME);
                    linkedHashMap.put(decode2, decode3);
                }
            }
            return linkedHashMap;
        } catch (Throwable th) {
            return null;
        }
    }

    @AnyThread
    /* renamed from: a */
    private void m7634a(@NonNull Context context, boolean z) {
        String str = "upgradeSdk";
        if (C2901d.m7644a(mo26572c("has_upgraded"), false)) {
            Tracker.m7517a(4, "DAB", "upgradeSdk", "Skip");
            return;
        }
        SharedPreferences sharedPreferences;
        boolean z2;
        String string;
        String string2;
        String string3;
        mo26565a("has_upgraded", Boolean.valueOf(true));
        try {
            if (context.getPackageManager().getReceiverInfo(new ComponentName(context, "com.kochava.android.tracker.ReferralCapture"), 0) != null) {
                Tracker.m7517a(1, "DAB", "upgradeSdk", "Legacy Broadcast Receiver found. Remove the following from your manifest!", "<receiver android:name=\"com.kochava.android.tracker.ReferralCapture\"\n    android:exported=\"true\">\n    <intent-filter>\n        <action android:name=\"com.android.vending.INSTALL_REFERRER\" />\n    </intent-filter>\n</receiver>");
            }
        } catch (Throwable th) {
            Tracker.m7517a(5, "DAB", "upgradeSdk", "Legacy broadcast receiver correctly removed");
        }
        try {
            sharedPreferences = context.getSharedPreferences("ko.tr", 0);
            SharedPreferences sharedPreferences2 = context.getSharedPreferences("ko.dt.pt", 0);
            z2 = sharedPreferences.getBoolean("initial_sent", false) && !sharedPreferences.contains("initial");
            string = sharedPreferences.getString("attribution_data", null);
            string2 = sharedPreferences2.getString("kochava_device_id", null);
            if (string2 != null) {
                Tracker.m7517a(4, "DAB", "upgradeSdk", "2017");
                m7640a(z2, string2.replace("STR::", ""), string);
                if (z) {
                    sharedPreferences.edit().clear().apply();
                    sharedPreferences2.edit().clear().apply();
                    context.deleteDatabase("ko.db");
                    return;
                }
                return;
            }
        } catch (Exception e) {
            Tracker.m7517a(2, "DAB", "upgradeSdk", "2017", e);
        }
        try {
            SharedPreferences sharedPreferences3 = context.getSharedPreferences("initPrefs", 0);
            sharedPreferences = context.getSharedPreferences("attributionPref", 0);
            boolean equalsIgnoreCase = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equalsIgnoreCase(sharedPreferences3.getString("initBool", ""));
            string = sharedPreferences.getString("attributionData", null);
            string2 = sharedPreferences3.getString("kochava_app_id_generated", null);
            if (string2 != null) {
                Tracker.m7517a(4, "DAB", "upgradeSdk", "2016");
                m7640a(equalsIgnoreCase, string2, string);
                if (z) {
                    sharedPreferences3.edit().clear().apply();
                    sharedPreferences.edit().clear().apply();
                    context.deleteDatabase("KochavaFeatureTracker");
                    return;
                }
                return;
            }
        } catch (Exception e2) {
            Tracker.m7517a(2, "DAB", "upgradeSdk", "2016", e2);
        }
        try {
            sharedPreferences = context.getSharedPreferences(context.getPackageName(), 0);
            z2 = sharedPreferences.contains("watchlistProperties");
            string3 = sharedPreferences.getString("kochava_queue_storage", null);
            z2 = z2 && (string3 == null || !string3.contains("initial"));
            string3 = sharedPreferences.getString("attribution", null);
            string = sharedPreferences.getString("kochava_device_id", null);
            if (string != null) {
                Tracker.m7517a(4, "DAB", "upgradeSdk", "unityV1_1");
                m7640a(z2, string, string3);
                if (z) {
                    sharedPreferences.edit().clear().apply();
                    return;
                }
                return;
            }
        } catch (Exception e22) {
            Tracker.m7517a(2, "DAB", "upgradeSdk", "unityV1_1", e22);
        }
        try {
            sharedPreferences = context.getSharedPreferences(context.getPackageName() + ".v2.playerprefs", 0);
            z2 = sharedPreferences.contains("watchlistProperties");
            string3 = sharedPreferences.getString("kochava_queue_storage", null);
            z2 = z2 && (string3 == null || !string3.contains("initial"));
            string3 = sharedPreferences.getString("attribution", null);
            string = sharedPreferences.getString("kochava_device_id", null);
            if (string != null) {
                Tracker.m7517a(4, "DAB", "upgradeSdk", "unityV1_2");
                m7640a(z2, string, string3);
                if (z) {
                    sharedPreferences.edit().clear().apply();
                }
            }
        } catch (Exception e222) {
            Tracker.m7517a(2, "DAB", "upgradeSdk", "unityV1_2", e222);
        }
    }

    @AnyThread
    /* renamed from: a */
    static void m7635a(@Nullable Object obj, @NonNull JSONArray jSONArray, boolean z) {
        if (obj != null) {
            if (!z) {
                int i = 0;
                while (i < jSONArray.length()) {
                    try {
                        if (!obj.equals(jSONArray.opt(i))) {
                            i++;
                        } else {
                            return;
                        }
                    } catch (Throwable th) {
                        Tracker.m7517a(4, "DAB", "putJsonObject", obj, th);
                        return;
                    }
                }
            }
            jSONArray.put(obj);
        }
    }

    @AnyThread
    /* renamed from: a */
    static void m7636a(@Nullable String str, @Nullable Object obj, @NonNull JSONObject jSONObject) {
        C2901d.m7637a(str, obj, jSONObject, 2);
    }

    @AnyThread
    /* renamed from: a */
    static void m7637a(@Nullable String str, @Nullable Object obj, @NonNull JSONObject jSONObject, int i) {
        if (str == null || obj == null || str.trim().isEmpty()) {
            Tracker.m7517a(i, "DAB", "putJsonObject", "Invalid: " + str + " " + obj);
            return;
        }
        try {
            if ((obj instanceof Boolean) || (obj instanceof Number) || (obj instanceof JSONObject) || (obj instanceof JSONArray) || (obj instanceof String)) {
                jSONObject.put(str, obj);
            } else if (obj instanceof Date) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                jSONObject.put(str, simpleDateFormat.format((Date) obj));
            } else if ((obj instanceof Bundle) || (obj instanceof Map)) {
                jSONObject.put(str, C2901d.m7661f(obj));
            } else if ((obj instanceof Collection) || obj.getClass().isArray()) {
                jSONObject.put(str, C2901d.m7662g(obj));
            } else {
                jSONObject.put(str, obj.toString());
            }
        } catch (Throwable th) {
            Tracker.m7517a(i, "DAB", "putJsonObject", str + " " + obj, th);
        }
    }

    @AnyThread
    /* renamed from: a */
    static void m7638a(@NonNull JSONArray jSONArray, @NonNull JSONArray jSONArray2) {
        for (int i = 0; i < jSONArray2.length(); i++) {
            try {
                jSONArray.put(jSONArray2.opt(i));
            } catch (Throwable th) {
                Tracker.m7517a(5, "DAB", "mergeJsonArra", th);
            }
        }
    }

    @AnyThread
    /* renamed from: a */
    static void m7639a(@NonNull JSONObject jSONObject, boolean z) {
        int a = (int) (C2901d.m7626a() / 1000);
        C2901d.m7636a(ConsentPartner.KEY_GRANTED, Boolean.valueOf(z), jSONObject);
        C2901d.m7636a(ConsentPartner.KEY_RESPONSE_TIME, Integer.valueOf(a), jSONObject);
        JSONArray c = C2901d.m7657c(jSONObject.opt(ConsentPartner.KEY_PARTNERS), true);
        for (int i = 0; i < c.length(); i++) {
            JSONObject b = C2901d.m7649b(c.opt(i), true);
            if (!z || !C2901d.m7644a(b.opt(ConsentPartner.KEY_GRANTED), false)) {
                C2901d.m7636a(ConsentPartner.KEY_GRANTED, Boolean.valueOf(z), b);
                C2901d.m7636a(ConsentPartner.KEY_RESPONSE_TIME, Integer.valueOf(a), b);
            }
        }
    }

    @AnyThread
    /* renamed from: a */
    private void m7640a(boolean z, @NonNull String str, @Nullable String str2) {
        Tracker.m7517a(4, "DAB", "applySdkUpgra", Boolean.valueOf(z), str, str2);
        if (z) {
            mo26565a("initial_ever_sent", Boolean.valueOf(true));
            mo26565a("initial_needs_sent", Boolean.valueOf(false));
        }
        mo26565a("kochava_device_id", (Object) str);
        if (str2 != null) {
            mo26565a("attribution", C2911l.m7728a(str2));
        }
    }

    /* renamed from: a */
    static boolean m7641a(@NonNull Context context) {
        String packageName = context.getPackageName();
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null || packageName == null) {
            return true;
        }
        List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        if (runningAppProcesses == null || runningAppProcesses.size() == 0) {
            return true;
        }
        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo != null && runningAppProcessInfo.importance == 100) {
                for (Object equals : runningAppProcessInfo.pkgList) {
                    if (packageName.equals(equals)) {
                        return true;
                    }
                }
                continue;
            }
        }
        return false;
    }

    @AnyThread
    @Contract(pure = true)
    /* renamed from: a */
    static boolean m7642a(@NonNull Context context, @NonNull String str) {
        return !C2901d.m7653b(context, str) ? false : VERSION.SDK_INT < 23 || context.checkSelfPermission(str) == 0;
    }

    @AnyThread
    @Contract(pure = true, value = "null, _ -> false; _ , null -> false")
    @CheckResult
    /* renamed from: a */
    static boolean m7643a(@Nullable Object obj, @Nullable Object obj2) {
        return ((obj instanceof Boolean) && (obj2 instanceof Boolean)) ? obj.equals(obj2) : ((obj instanceof Integer) && (obj2 instanceof Integer)) ? obj.equals(obj2) : ((obj instanceof Long) && (obj2 instanceof Long)) ? obj.equals(obj2) : ((obj instanceof Float) && (obj2 instanceof Float)) ? obj.equals(obj2) : ((obj instanceof Double) && (obj2 instanceof Double)) ? Double.compare(((Double) obj).doubleValue(), ((Double) obj2).doubleValue()) == 0 : ((obj instanceof String) && (obj2 instanceof String)) ? obj.equals(obj2) : ((obj instanceof JSONObject) && (obj2 instanceof JSONObject)) ? C2901d.m7658c((JSONObject) obj, (JSONObject) obj2) : ((obj instanceof JSONArray) && (obj2 instanceof JSONArray)) ? C2901d.m7654b((JSONArray) obj, (JSONArray) obj2) : false;
    }

    @AnyThread
    @Contract(pure = true)
    @CheckResult
    /* renamed from: a */
    static boolean m7644a(@Nullable Object obj, boolean z) {
        Boolean b = C2901d.m7648b(obj);
        return b != null ? b.booleanValue() : z;
    }

    @AnyThread
    @Contract(pure = true, value = "null, _ -> false; _ , null -> false")
    @CheckResult
    /* renamed from: a */
    static boolean m7645a(@Nullable JSONArray jSONArray, @Nullable String str) {
        if (jSONArray == null || str == null) {
            return false;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            if (str.equalsIgnoreCase(C2901d.m7628a(jSONArray.opt(i)))) {
                return true;
            }
        }
        return false;
    }

    @AnyThread
    /* renamed from: a */
    static boolean m7646a(@NonNull JSONObject jSONObject, @NonNull JSONObject jSONObject2) {
        Iterator keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            if (!C2901d.m7643a(jSONObject2.opt(str), jSONObject.opt(str))) {
                return false;
            }
        }
        return true;
    }

    @AnyThread
    @Contract(pure = true)
    @CheckResult
    /* renamed from: b */
    static int m7647b(@Nullable Object obj, int i) {
        Integer c = C2901d.m7656c(obj);
        return c != null ? c.intValue() : i;
    }

    @AnyThread
    @Contract(pure = true, value = "null -> null")
    @CheckResult
    @Nullable
    /* renamed from: b */
    static Boolean m7648b(@Nullable Object obj) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof String) {
            if (Boolean.toString(true).equalsIgnoreCase((String) obj) || Integer.toString(1).equalsIgnoreCase((String) obj)) {
                return Boolean.valueOf(true);
            }
            if (Boolean.toString(false).equalsIgnoreCase((String) obj) || Integer.toString(0).equalsIgnoreCase((String) obj)) {
                return Boolean.valueOf(false);
            }
        }
        if (obj instanceof Integer) {
            if (1 == ((Integer) obj).intValue()) {
                return Boolean.valueOf(true);
            }
            if (((Integer) obj).intValue() == 0) {
                return Boolean.valueOf(false);
            }
        }
        return null;
    }

    @AnyThread
    @Contract(pure = true, value = "_,true -> !null; null,false -> null")
    @CheckResult
    @Nullable
    /* renamed from: b */
    static JSONObject m7649b(@Nullable Object obj, boolean z) {
        JSONObject f = C2901d.m7661f(obj);
        return (f == null && z) ? new JSONObject() : f;
    }

    @Nullable
    @AnyThread
    @Contract(pure = true)
    /* renamed from: b */
    static JSONObject m7650b(@NonNull JSONArray jSONArray, @NonNull String str) {
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject b = C2901d.m7649b(jSONArray.opt(i), true);
            if (str.equals(C2901d.m7628a(b.opt(ConsentPartner.KEY_NAME)))) {
                return b;
            }
        }
        return null;
    }

    @AnyThread
    /* renamed from: b */
    static void m7651b(@NonNull JSONObject jSONObject, @NonNull JSONObject jSONObject2) {
        Iterator keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            Object opt = jSONObject2.opt(str);
            if (opt != null) {
                try {
                    jSONObject.put(str, opt);
                } catch (Throwable th) {
                    Tracker.m7517a(5, "DAB", "mergeJsonObje", th);
                }
            }
        }
    }

    @AnyThread
    /* renamed from: b */
    static boolean m7652b(@NonNull Context context) {
        if (!C2901d.m7653b(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return true;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            boolean z = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
            return z;
        } catch (Throwable th) {
            Tracker.m7517a(4, "DAB", "hasNetworkCon", th);
            return true;
        }
    }

    @AnyThread
    @Contract(pure = true)
    /* renamed from: b */
    static boolean m7653b(@NonNull Context context, @NonNull String str) {
        return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
    }

    @AnyThread
    @Contract(pure = true, value = "null, _ -> false; _ , null -> false")
    @CheckResult
    /* renamed from: b */
    static boolean m7654b(@Nullable JSONArray jSONArray, @Nullable JSONArray jSONArray2) {
        if (jSONArray == null || jSONArray2 == null || jSONArray.length() != jSONArray2.length()) {
            return false;
        }
        if (jSONArray.length() == 0) {
            return true;
        }
        boolean[] zArr = new boolean[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            boolean z;
            Object opt = jSONArray.opt(i);
            int i2 = 0;
            while (i2 < jSONArray2.length()) {
                if (!zArr[i2] && C2901d.m7643a(opt, jSONArray2.opt(i2))) {
                    zArr[i2] = true;
                    z = true;
                    break;
                }
                i2++;
            }
            z = false;
            if (!z) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: c */
    static int m7655c(@NonNull JSONArray jSONArray, @NonNull JSONArray jSONArray2) {
        boolean z = false;
        for (int i = 0; i < jSONArray2.length(); i++) {
            JSONObject b = C2901d.m7649b(jSONArray2.opt(i), true);
            JSONObject b2 = C2901d.m7650b(jSONArray, C2901d.m7629a(b.opt(ConsentPartner.KEY_NAME), ""));
            if (b2 == null) {
                C2901d.m7636a(ConsentPartner.KEY_GRANTED, Boolean.valueOf(false), b);
                C2901d.m7636a(ConsentPartner.KEY_RESPONSE_TIME, Long.valueOf(0), b);
                z = true;
            } else {
                C2901d.m7636a(ConsentPartner.KEY_GRANTED, Boolean.valueOf(C2901d.m7644a(b2.opt(ConsentPartner.KEY_GRANTED), false)), b);
                C2901d.m7636a(ConsentPartner.KEY_RESPONSE_TIME, Long.valueOf(C2901d.m7627a(b2.opt(ConsentPartner.KEY_RESPONSE_TIME), 0)), b);
            }
        }
        return z ? 2 : jSONArray.length() == jSONArray2.length() ? 0 : 1;
    }

    @AnyThread
    @Contract(pure = true, value = "null -> null")
    @CheckResult
    @Nullable
    /* renamed from: c */
    static Integer m7656c(@Nullable Object obj) {
        if (obj instanceof Number) {
            return Integer.valueOf(((Number) obj).intValue());
        }
        if (obj instanceof String) {
            try {
                return Integer.valueOf(Integer.parseInt((String) obj));
            } catch (Throwable th) {
            }
        }
        return null;
    }

    @AnyThread
    @Contract(pure = true, value = "_,true -> !null; null,false -> null")
    @CheckResult
    @Nullable
    /* renamed from: c */
    static JSONArray m7657c(@Nullable Object obj, boolean z) {
        JSONArray g = C2901d.m7662g(obj);
        return (g == null && z) ? new JSONArray() : g;
    }

    @AnyThread
    @Contract(pure = true, value = "null, _ -> false; _ , null -> false")
    @CheckResult
    /* renamed from: c */
    static boolean m7658c(@Nullable JSONObject jSONObject, @Nullable JSONObject jSONObject2) {
        if (jSONObject == null || jSONObject2 == null || jSONObject.length() != jSONObject2.length()) {
            return false;
        }
        if (jSONObject.length() == 0) {
            return true;
        }
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            if (!C2901d.m7643a(jSONObject.opt(str), jSONObject2.opt(str))) {
                return false;
            }
        }
        return true;
    }

    @AnyThread
    @Contract(pure = true, value = "null -> null")
    @CheckResult
    @Nullable
    /* renamed from: d */
    public static Long m7659d(@Nullable Object obj) {
        if (obj instanceof Number) {
            return Long.valueOf(((Number) obj).longValue());
        }
        if (obj instanceof String) {
            try {
                return Long.valueOf(Long.parseLong((String) obj));
            } catch (Throwable th) {
            }
        }
        return null;
    }

    @AnyThread
    @Contract(pure = true, value = "null -> null")
    @CheckResult
    @Nullable
    /* renamed from: e */
    static Double m7660e(@Nullable Object obj) {
        if (obj instanceof Number) {
            return Double.valueOf(((Number) obj).doubleValue());
        }
        if (obj instanceof String) {
            try {
                return Double.valueOf(Double.parseDouble((String) obj));
            } catch (Throwable th) {
            }
        }
        return null;
    }

    @AnyThread
    @Contract(pure = true, value = "null -> null")
    @CheckResult
    @Nullable
    /* renamed from: f */
    static JSONObject m7661f(@Nullable Object obj) {
        if (obj instanceof JSONObject) {
            return (JSONObject) obj;
        }
        if (obj instanceof Bundle) {
            Bundle bundle = (Bundle) obj;
            JSONObject jSONObject = new JSONObject();
            for (String str : bundle.keySet()) {
                C2901d.m7636a(str, bundle.get(str), jSONObject);
            }
            return jSONObject;
        }
        try {
            if (obj instanceof String) {
                return JSONObjectInstrumentation.init((String) obj);
            }
            if (obj instanceof Map) {
                return new JSONObject((Map) obj);
            }
            return null;
        } catch (Throwable th) {
        }
    }

    @AnyThread
    @Contract(pure = true, value = "null -> null")
    @CheckResult
    @Nullable
    /* renamed from: g */
    static JSONArray m7662g(@Nullable Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof JSONArray) {
            return (JSONArray) obj;
        }
        if (obj instanceof Collection) {
            return new JSONArray((Collection) obj);
        }
        try {
            if (obj instanceof String) {
                return JSONArrayInstrumentation.init((String) obj);
            }
            if (obj.getClass().isArray()) {
                JSONArray jSONArray = new JSONArray();
                int length = Array.getLength(obj);
                for (int i = 0; i < length; i++) {
                    jSONArray.put(Array.get(obj, i));
                }
                return jSONArray;
            }
            return null;
        } catch (Throwable th) {
        }
    }

    @WorkerThread
    @CheckResult
    @NonNull
    /* renamed from: i */
    private SQLiteDatabase m7663i() {
        String str = "openDb";
        if (this.f6589b == null || !this.f6589b.isOpen()) {
            Tracker.m7517a(4, "DAB", "openDb", "Opening");
            this.f6589b = getWritableDatabase();
            if (VERSION.SDK_INT <= 16) {
                this.f6589b.setLockingEnabled(false);
            }
            this.f6593h = (int) DatabaseUtils.queryNumEntries(this.f6589b, "events");
            this.f6592g = (int) DatabaseUtils.queryNumEntries(this.f6589b, "updates");
        } else {
            Tracker.m7517a(5, "DAB", "openDb", "Already Open");
        }
        return this.f6589b;
    }

    /* Access modifiers changed, original: final */
    @WorkerThread
    /* renamed from: a */
    public final void mo26564a(@IntRange int i) {
        synchronized (f6586c) {
            String str = "removeEvent";
            Tracker.m7517a(4, "DAB", "removeEvent", Integer.toString(i));
            if (this.f6593h > 0) {
                try {
                    int b = C2901d.m7647b(mo26572c("batch_max_quantity"), 25);
                    SQLiteDatabase i2 = m7663i();
                    String str2 = "events";
                    String str3 = "_id IN (SELECT _id FROM events ORDER BY _id ASC LIMIT ?)";
                    String[] strArr = new String[]{Integer.toString(C2901d.m7624a(i, 1, b))};
                    int delete = !(i2 instanceof SQLiteDatabase) ? i2.delete(str2, str3, strArr) : SQLiteInstrumentation.delete(i2, str2, str3, strArr);
                    if (delete > 0) {
                        this.f6593h -= delete;
                    }
                } catch (Throwable th) {
                    Tracker.m7517a(4, "DAB", "removeEvent", th);
                }
            } else {
                Tracker.m7517a(2, "DAB", "removeEvent", "No events to remove");
            }
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final void mo26565a(@NonNull String str, @NonNull Object obj) {
        mo26566a(str, obj, this.f6594i);
    }

    /* Access modifiers changed, original: final */
    @AnyThread
    /* renamed from: a */
    public final void mo26566a(@NonNull String str, @NonNull Object obj, boolean z) {
        synchronized (f6587d) {
            if (obj instanceof Boolean) {
                if (z) {
                    this.f6591f.edit().putBoolean(str, ((Boolean) obj).booleanValue()).apply();
                } else {
                    this.f6588a.put(str, (Boolean) obj);
                }
            } else if (obj instanceof Integer) {
                if (z) {
                    this.f6591f.edit().putInt(str, ((Integer) obj).intValue()).apply();
                } else {
                    this.f6588a.put(str, (Integer) obj);
                }
            } else if (obj instanceof Float) {
                if (z) {
                    this.f6591f.edit().putFloat(str, ((Float) obj).floatValue()).apply();
                } else {
                    this.f6588a.put(str, (Float) obj);
                }
            } else if (obj instanceof Double) {
                if (z) {
                    this.f6591f.edit().putLong(str, Double.doubleToRawLongBits(((Double) obj).doubleValue())).apply();
                } else {
                    this.f6588a.put(str, Long.valueOf(Double.doubleToRawLongBits(((Double) obj).doubleValue())));
                }
            } else if (obj instanceof String) {
                if (z) {
                    this.f6591f.edit().putString(str, "STR::" + obj).apply();
                } else {
                    this.f6588a.put(str, "STR::" + obj);
                }
            } else if (obj instanceof JSONObject) {
                if (z) {
                    this.f6591f.edit().putString(str, "JSO::" + obj.toString()).apply();
                } else {
                    this.f6588a.put(str, "JSO::" + obj.toString());
                }
            } else if (!(obj instanceof JSONArray)) {
                Tracker.m7517a(2, "DAB", "put", str + " Unrecognized Type");
            } else if (z) {
                this.f6591f.edit().putString(str, "JSA::" + obj.toString()).apply();
            } else {
                this.f6588a.put(str, "JSA::" + obj.toString());
            }
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final void mo26567a(boolean z) {
        int i = 0;
        Object[] objArr = new Object[this.f6590e.size()];
        for (int i2 = 0; i2 < this.f6590e.size(); i2++) {
            objArr[i2] = mo26572c((String) this.f6590e.get(i2));
        }
        mo26573c();
        mo26571b(z);
        while (i < this.f6590e.size()) {
            mo26566a((String) this.f6590e.get(i), objArr[i], true);
            i++;
        }
    }

    /* Access modifiers changed, original: final */
    @WorkerThread
    /* renamed from: b */
    public final void mo26568b() {
        synchronized (f6586c) {
            String str = "closeDb";
            try {
                if (this.f6589b == null || !this.f6589b.isOpen()) {
                    Tracker.m7517a(5, "DAB", "closeDb", "Already Closed");
                } else {
                    Tracker.m7517a(4, "DAB", "closeDb", new Object[0]);
                    SQLiteDatabase sQLiteDatabase = this.f6589b;
                    String str2 = "VACUUM";
                    if (sQLiteDatabase instanceof SQLiteDatabase) {
                        SQLiteInstrumentation.execSQL(sQLiteDatabase, str2);
                    } else {
                        sQLiteDatabase.execSQL(str2);
                    }
                    close();
                    this.f6589b = null;
                }
            } catch (Throwable th) {
                Tracker.m7517a(5, "DAB", "closeDb", th);
            }
        }
    }

    /* Access modifiers changed, original: final */
    @AnyThread
    /* renamed from: b */
    public final void mo26569b(@NonNull String str) {
        synchronized (f6587d) {
            this.f6591f.edit().remove(str).apply();
            this.f6588a.remove(str);
        }
    }

    /* Access modifiers changed, original: final */
    @WorkerThread
    /* renamed from: b */
    public final void mo26570b(@NonNull JSONObject jSONObject) {
        synchronized (f6586c) {
            String str = "putEvent";
            Tracker.m7517a(5, "DAB", "putEvent", jSONObject);
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put(MapTilsCacheAndResManager.AUTONAVI_DATA_PATH, C2901d.m7632a(jSONObject));
                SQLiteDatabase i = m7663i();
                String str2 = "events";
                if ((!(i instanceof SQLiteDatabase) ? i.insert(str2, null, contentValues) : SQLiteInstrumentation.insert(i, str2, null, contentValues)) != -1) {
                    this.f6593h++;
                }
            } catch (Throwable th) {
                Tracker.m7517a(4, "DAB", "putEvent", th);
            }
        }
    }

    /* Access modifiers changed, original: final */
    @AnyThread
    /* renamed from: b */
    public final void mo26571b(boolean z) {
        synchronized (f6587d) {
            this.f6591f.edit().clear().apply();
            if (!z) {
                this.f6588a.clear();
            }
        }
    }

    /* Access modifiers changed, original: final */
    @Nullable
    @AnyThread
    @Contract(pure = true)
    /* renamed from: c */
    public final Object mo26572c(@NonNull String str) {
        Object obj;
        synchronized (f6587d) {
            obj = this.f6588a.get(str);
            if (obj == null) {
                obj = this.f6591f.getAll().get(str);
            }
            if (obj instanceof Boolean) {
            } else if (obj instanceof Integer) {
            } else if (obj instanceof Float) {
            } else if (obj instanceof Long) {
                obj = Double.valueOf(Double.longBitsToDouble(((Long) obj).longValue()));
            } else {
                if (obj instanceof String) {
                    String str2 = (String) obj;
                    if (str2.startsWith("STR::")) {
                        obj = str2.substring("STR::".length());
                    } else {
                        try {
                            if (str2.startsWith("JSO::")) {
                                obj = JSONObjectInstrumentation.init(str2.substring("JSO::".length()));
                            } else if (str2.startsWith("JSA::")) {
                                obj = JSONArrayInstrumentation.init(str2.substring("JSA::".length()));
                            }
                        } catch (JSONException e) {
                            Tracker.m7517a(4, "DAB", "get", e);
                        }
                    }
                }
                if (obj != null) {
                    Tracker.m7517a(2, "DAB", "get", str + " Unrecognized Type");
                }
                obj = null;
            }
        }
        return obj;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: c */
    public final void mo26573c() {
        synchronized (f6586c) {
            while (mo26579g().length() != 0) {
                try {
                    mo26578f();
                } catch (Throwable th) {
                    Tracker.m7517a(4, "DAB", "deleteDb", th);
                }
            }
            while (mo26576d().length() != 0) {
                mo26564a(25);
            }
            mo26568b();
        }
    }

    /* Access modifiers changed, original: final */
    @WorkerThread
    /* renamed from: c */
    public final void mo26574c(@NonNull JSONObject jSONObject) {
        synchronized (f6586c) {
            String str = "putUpdate";
            Tracker.m7517a(5, "DAB", "putUpdate", jSONObject);
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put(MapTilsCacheAndResManager.AUTONAVI_DATA_PATH, C2901d.m7632a(jSONObject));
                SQLiteDatabase i = m7663i();
                String str2 = "updates";
                if ((!(i instanceof SQLiteDatabase) ? i.insert(str2, null, contentValues) : SQLiteInstrumentation.insert(i, str2, null, contentValues)) != -1) {
                    this.f6592g++;
                }
            } catch (Throwable th) {
                Tracker.m7517a(4, "DAB", "putUpdate", th);
            }
        }
    }

    /* Access modifiers changed, original: final */
    /* JADX WARNING: Missing block: B:56:?, code skipped:
            return;
     */
    @android.support.annotation.AnyThread
    /* renamed from: c */
    public final void mo26575c(boolean r9) {
        /*
        r8 = this;
        r2 = f6587d;
        monitor-enter(r2);
        r0 = r8.f6594i;	 Catch:{ all -> 0x003f }
        if (r0 != r9) goto L_0x0009;
    L_0x0007:
        monitor-exit(r2);	 Catch:{ all -> 0x003f }
    L_0x0008:
        return;
    L_0x0009:
        r8.f6594i = r9;	 Catch:{ all -> 0x003f }
        r0 = r8.f6594i;	 Catch:{ all -> 0x003f }
        if (r0 == 0) goto L_0x0080;
    L_0x000f:
        r0 = r8.f6591f;	 Catch:{ all -> 0x003f }
        r3 = r0.edit();	 Catch:{ all -> 0x003f }
        r0 = r8.f6588a;	 Catch:{ all -> 0x003f }
        r0 = r0.keySet();	 Catch:{ all -> 0x003f }
        r4 = r0.iterator();	 Catch:{ all -> 0x003f }
    L_0x001f:
        r0 = r4.hasNext();	 Catch:{ all -> 0x003f }
        if (r0 == 0) goto L_0x0076;
    L_0x0025:
        r0 = r4.next();	 Catch:{ all -> 0x003f }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x003f }
        r1 = r8.f6588a;	 Catch:{ all -> 0x003f }
        r1 = r1.get(r0);	 Catch:{ all -> 0x003f }
        r5 = r1 instanceof java.lang.Boolean;	 Catch:{ all -> 0x003f }
        if (r5 == 0) goto L_0x0042;
    L_0x0035:
        r1 = (java.lang.Boolean) r1;	 Catch:{ all -> 0x003f }
        r1 = r1.booleanValue();	 Catch:{ all -> 0x003f }
        r3.putBoolean(r0, r1);	 Catch:{ all -> 0x003f }
        goto L_0x001f;
    L_0x003f:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x003f }
        throw r0;
    L_0x0042:
        r5 = r1 instanceof java.lang.Float;	 Catch:{ all -> 0x003f }
        if (r5 == 0) goto L_0x0050;
    L_0x0046:
        r1 = (java.lang.Float) r1;	 Catch:{ all -> 0x003f }
        r1 = r1.floatValue();	 Catch:{ all -> 0x003f }
        r3.putFloat(r0, r1);	 Catch:{ all -> 0x003f }
        goto L_0x001f;
    L_0x0050:
        r5 = r1 instanceof java.lang.Integer;	 Catch:{ all -> 0x003f }
        if (r5 == 0) goto L_0x005e;
    L_0x0054:
        r1 = (java.lang.Integer) r1;	 Catch:{ all -> 0x003f }
        r1 = r1.intValue();	 Catch:{ all -> 0x003f }
        r3.putInt(r0, r1);	 Catch:{ all -> 0x003f }
        goto L_0x001f;
    L_0x005e:
        r5 = r1 instanceof java.lang.Long;	 Catch:{ all -> 0x003f }
        if (r5 == 0) goto L_0x006c;
    L_0x0062:
        r1 = (java.lang.Long) r1;	 Catch:{ all -> 0x003f }
        r6 = r1.longValue();	 Catch:{ all -> 0x003f }
        r3.putLong(r0, r6);	 Catch:{ all -> 0x003f }
        goto L_0x001f;
    L_0x006c:
        r5 = r1 instanceof java.lang.String;	 Catch:{ all -> 0x003f }
        if (r5 == 0) goto L_0x001f;
    L_0x0070:
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x003f }
        r3.putString(r0, r1);	 Catch:{ all -> 0x003f }
        goto L_0x001f;
    L_0x0076:
        r3.apply();	 Catch:{ all -> 0x003f }
        r0 = r8.f6588a;	 Catch:{ all -> 0x003f }
        r0.clear();	 Catch:{ all -> 0x003f }
    L_0x007e:
        monitor-exit(r2);	 Catch:{ all -> 0x003f }
        goto L_0x0008;
    L_0x0080:
        r0 = r8.f6591f;	 Catch:{ all -> 0x003f }
        r1 = r0.getAll();	 Catch:{ all -> 0x003f }
        r0 = r1.keySet();	 Catch:{ all -> 0x003f }
        r3 = r0.iterator();	 Catch:{ all -> 0x003f }
    L_0x008e:
        r0 = r3.hasNext();	 Catch:{ all -> 0x003f }
        if (r0 == 0) goto L_0x007e;
    L_0x0094:
        r0 = r3.next();	 Catch:{ all -> 0x003f }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x003f }
        r4 = r8.f6590e;	 Catch:{ all -> 0x003f }
        r4 = r4.contains(r0);	 Catch:{ all -> 0x003f }
        if (r4 != 0) goto L_0x008e;
    L_0x00a2:
        r4 = r1.get(r0);	 Catch:{ all -> 0x003f }
        if (r4 == 0) goto L_0x008e;
    L_0x00a8:
        r5 = r8.f6588a;	 Catch:{ all -> 0x003f }
        r5.put(r0, r4);	 Catch:{ all -> 0x003f }
        goto L_0x008e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kochava.base.C2901d.mo26575c(boolean):void");
    }

    /* Access modifiers changed, original: final */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x008f A:{SYNTHETIC, Splitter:B:38:0x008f} */
    @org.jetbrains.annotations.Contract(pure = true)
    @android.support.annotation.CheckResult
    @android.support.annotation.NonNull
    @android.support.annotation.WorkerThread
    /* renamed from: d */
    public final org.json.JSONArray mo26576d() {
        /*
        r10 = this;
        r3 = f6586c;
        monitor-enter(r3);
        r0 = "takeEvent";
        r0 = 5;
        r1 = "DAB";
        r2 = "takeEvent";
        r4 = 0;
        r4 = new java.lang.Object[r4];	 Catch:{ all -> 0x0089 }
        com.kochava.base.Tracker.m7517a(r0, r1, r2, r4);	 Catch:{ all -> 0x0089 }
        r1 = new org.json.JSONArray;	 Catch:{ all -> 0x0089 }
        r1.<init>();	 Catch:{ all -> 0x0089 }
        r0 = r10.f6593h;	 Catch:{ all -> 0x0089 }
        if (r0 != 0) goto L_0x001c;
    L_0x0019:
        monitor-exit(r3);	 Catch:{ all -> 0x0089 }
        r0 = r1;
    L_0x001b:
        return r0;
    L_0x001c:
        r2 = 0;
        r0 = "batch_max_quantity";
        r0 = r10.mo26572c(r0);	 Catch:{ Throwable -> 0x0097 }
        r4 = 25;
        r4 = com.kochava.base.C2901d.m7647b(r0, r4);	 Catch:{ Throwable -> 0x0097 }
        r0 = r10.m7663i();	 Catch:{ Throwable -> 0x0097 }
        r5 = "SELECT data FROM events ORDER BY _id ASC LIMIT ?";
        r6 = 1;
        r6 = new java.lang.String[r6];	 Catch:{ Throwable -> 0x0097 }
        r7 = 0;
        r4 = java.lang.Integer.toString(r4);	 Catch:{ Throwable -> 0x0097 }
        r6[r7] = r4;	 Catch:{ Throwable -> 0x0097 }
        r4 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Throwable -> 0x0097 }
        if (r4 != 0) goto L_0x007c;
    L_0x003d:
        r0 = r0.rawQuery(r5, r6);	 Catch:{ Throwable -> 0x0097 }
    L_0x0041:
        r2 = r0.moveToNext();	 Catch:{ Throwable -> 0x0054, all -> 0x0093 }
        if (r2 == 0) goto L_0x0083;
    L_0x0047:
        r2 = 0;
        r2 = r0.getString(r2);	 Catch:{ Throwable -> 0x0054, all -> 0x0093 }
        r2 = com.kochava.base.C2901d.m7661f(r2);	 Catch:{ Throwable -> 0x0054, all -> 0x0093 }
        r1.put(r2);	 Catch:{ Throwable -> 0x0054, all -> 0x0093 }
        goto L_0x0041;
    L_0x0054:
        r2 = move-exception;
        r9 = r2;
        r2 = r0;
        r0 = r9;
    L_0x0058:
        r4 = 4;
        r5 = "DAB";
        r6 = "takeEvent";
        r7 = 1;
        r7 = new java.lang.Object[r7];	 Catch:{ all -> 0x008c }
        r8 = 0;
        r7[r8] = r0;	 Catch:{ all -> 0x008c }
        com.kochava.base.Tracker.m7517a(r4, r5, r6, r7);	 Catch:{ all -> 0x008c }
        if (r2 == 0) goto L_0x006b;
    L_0x0068:
        r2.close();	 Catch:{ all -> 0x0089 }
    L_0x006b:
        r0 = 5;
        r2 = "DAB";
        r4 = "takeEvent";
        r5 = 1;
        r5 = new java.lang.Object[r5];	 Catch:{ all -> 0x0089 }
        r6 = 0;
        r5[r6] = r1;	 Catch:{ all -> 0x0089 }
        com.kochava.base.Tracker.m7517a(r0, r2, r4, r5);	 Catch:{ all -> 0x0089 }
        monitor-exit(r3);	 Catch:{ all -> 0x0089 }
        r0 = r1;
        goto L_0x001b;
    L_0x007c:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Throwable -> 0x0097 }
        r0 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.rawQuery(r0, r5, r6);	 Catch:{ Throwable -> 0x0097 }
        goto L_0x0041;
    L_0x0083:
        if (r0 == 0) goto L_0x006b;
    L_0x0085:
        r0.close();	 Catch:{ all -> 0x0089 }
        goto L_0x006b;
    L_0x0089:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0089 }
        throw r0;
    L_0x008c:
        r0 = move-exception;
    L_0x008d:
        if (r2 == 0) goto L_0x0092;
    L_0x008f:
        r2.close();	 Catch:{ all -> 0x0089 }
    L_0x0092:
        throw r0;	 Catch:{ all -> 0x0089 }
    L_0x0093:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x008d;
    L_0x0097:
        r0 = move-exception;
        goto L_0x0058;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kochava.base.C2901d.mo26576d():org.json.JSONArray");
    }

    /* Access modifiers changed, original: final */
    @SuppressLint({"CheckResult"})
    @Contract(pure = true)
    @CheckResult
    @WorkerThread
    /* renamed from: e */
    public final int mo26577e() {
        int i = 0;
        synchronized (f6586c) {
            try {
                m7663i();
                Tracker.m7517a(4, "DAB", "getEventCount", Integer.valueOf(this.f6593h));
                i = this.f6593h;
            } catch (Throwable th) {
                Tracker.m7517a(4, "DAB", "getEventCount", th);
            }
        }
        return i;
    }

    /* Access modifiers changed, original: final */
    @WorkerThread
    /* renamed from: f */
    public final void mo26578f() {
        synchronized (f6586c) {
            String str = "removeUpdate";
            Tracker.m7517a(4, "DAB", "removeUpdate", new Object[0]);
            if (this.f6592g > 0) {
                try {
                    SQLiteDatabase i = m7663i();
                    String str2 = "updates";
                    String str3 = "_id IN (SELECT _id FROM updates ORDER BY _id ASC LIMIT 1)";
                    if ((!(i instanceof SQLiteDatabase) ? i.delete(str2, str3, null) : SQLiteInstrumentation.delete(i, str2, str3, null)) > 0) {
                        this.f6592g--;
                    }
                } catch (Throwable th) {
                    Tracker.m7517a(4, "DAB", "removeUpdate", th);
                }
            } else {
                Tracker.m7517a(2, "DAB", "removeUpdate", "No updates to remove");
            }
        }
    }

    /* Access modifiers changed, original: final */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0074 A:{SYNTHETIC, Splitter:B:38:0x0074} */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x006d A:{Catch:{ Throwable -> 0x0052, all -> 0x0069 }} */
    @org.jetbrains.annotations.Contract(pure = true)
    @android.support.annotation.CheckResult
    @android.support.annotation.NonNull
    @android.support.annotation.WorkerThread
    /* renamed from: g */
    public final org.json.JSONObject mo26579g() {
        /*
        r9 = this;
        r2 = 0;
        r3 = f6586c;
        monitor-enter(r3);
        r0 = "takeUpdate";
        r0 = 5;
        r1 = "DAB";
        r4 = "takeUpdate";
        r5 = 0;
        r5 = new java.lang.Object[r5];	 Catch:{ all -> 0x0071 }
        com.kochava.base.Tracker.m7517a(r0, r1, r4, r5);	 Catch:{ all -> 0x0071 }
        r0 = r9.f6592g;	 Catch:{ all -> 0x0071 }
        if (r0 <= 0) goto L_0x0082;
    L_0x0015:
        r0 = r9.m7663i();	 Catch:{ Throwable -> 0x0052, all -> 0x0069 }
        r1 = "SELECT data FROM updates ORDER BY _id ASC LIMIT 1";
        r4 = 0;
        r5 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Throwable -> 0x0052, all -> 0x0069 }
        if (r5 != 0) goto L_0x004b;
    L_0x0020:
        r1 = r0.rawQuery(r1, r4);	 Catch:{ Throwable -> 0x0052, all -> 0x0069 }
    L_0x0024:
        r0 = r1.moveToFirst();	 Catch:{ Throwable -> 0x007c }
        if (r0 == 0) goto L_0x0033;
    L_0x002a:
        r0 = 0;
        r0 = r1.getString(r0);	 Catch:{ Throwable -> 0x007c }
        r2 = com.kochava.base.C2901d.m7661f(r0);	 Catch:{ Throwable -> 0x007c }
    L_0x0033:
        if (r1 == 0) goto L_0x0080;
    L_0x0035:
        r1.close();	 Catch:{ all -> 0x0071 }
        r0 = r2;
    L_0x0039:
        r1 = 5;
        r2 = "DAB";
        r4 = "takeUpdate";
        r5 = 1;
        r5 = new java.lang.Object[r5];	 Catch:{ all -> 0x0071 }
        r6 = 0;
        r5[r6] = r0;	 Catch:{ all -> 0x0071 }
        com.kochava.base.Tracker.m7517a(r1, r2, r4, r5);	 Catch:{ all -> 0x0071 }
    L_0x0047:
        if (r0 == 0) goto L_0x0074;
    L_0x0049:
        monitor-exit(r3);	 Catch:{ all -> 0x0071 }
        return r0;
    L_0x004b:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Throwable -> 0x0052, all -> 0x0069 }
        r1 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.rawQuery(r0, r1, r4);	 Catch:{ Throwable -> 0x0052, all -> 0x0069 }
        goto L_0x0024;
    L_0x0052:
        r0 = move-exception;
        r1 = r2;
    L_0x0054:
        r4 = 4;
        r5 = "DAB";
        r6 = "takeUpdate";
        r7 = 1;
        r7 = new java.lang.Object[r7];	 Catch:{ all -> 0x007a }
        r8 = 0;
        r7[r8] = r0;	 Catch:{ all -> 0x007a }
        com.kochava.base.Tracker.m7517a(r4, r5, r6, r7);	 Catch:{ all -> 0x007a }
        if (r1 == 0) goto L_0x007e;
    L_0x0064:
        r1.close();	 Catch:{ all -> 0x0071 }
        r0 = r2;
        goto L_0x0039;
    L_0x0069:
        r0 = move-exception;
        r1 = r2;
    L_0x006b:
        if (r1 == 0) goto L_0x0070;
    L_0x006d:
        r1.close();	 Catch:{ all -> 0x0071 }
    L_0x0070:
        throw r0;	 Catch:{ all -> 0x0071 }
    L_0x0071:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0071 }
        throw r0;
    L_0x0074:
        r0 = new org.json.JSONObject;	 Catch:{ all -> 0x0071 }
        r0.<init>();	 Catch:{ all -> 0x0071 }
        goto L_0x0049;
    L_0x007a:
        r0 = move-exception;
        goto L_0x006b;
    L_0x007c:
        r0 = move-exception;
        goto L_0x0054;
    L_0x007e:
        r0 = r2;
        goto L_0x0039;
    L_0x0080:
        r0 = r2;
        goto L_0x0039;
    L_0x0082:
        r0 = r2;
        goto L_0x0047;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kochava.base.C2901d.mo26579g():org.json.JSONObject");
    }

    /* Access modifiers changed, original: final */
    @SuppressLint({"CheckResult"})
    @Contract(pure = true)
    @CheckResult
    @WorkerThread
    /* renamed from: h */
    public final int mo26580h() {
        int i = 0;
        synchronized (f6586c) {
            try {
                m7663i();
                Tracker.m7517a(4, "DAB", "getUpdateCoun", Integer.valueOf(this.f6592g));
                i = this.f6592g;
            } catch (Throwable th) {
                Tracker.m7517a(4, "DAB", "getUpdateCoun", th);
            }
        }
        return i;
    }

    @WorkerThread
    public final void onCreate(@NonNull SQLiteDatabase sQLiteDatabase) {
        synchronized (f6586c) {
            Tracker.m7517a(5, "DAB", "onCreate", new Object[0]);
            try {
                String str = "CREATE TABLE events (_id INTEGER PRIMARY KEY AUTOINCREMENT, data TEXT NOT NULL);";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                String str2 = "CREATE TABLE updates (_id INTEGER PRIMARY KEY AUTOINCREMENT, data TEXT NOT NULL);";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str2);
                } else {
                    sQLiteDatabase.execSQL(str2);
                }
            } catch (Throwable th) {
                Tracker.m7517a(5, "DAB", "onCreate", th);
            }
        }
    }

    @WorkerThread
    public final void onUpgrade(@NonNull SQLiteDatabase sQLiteDatabase, @IntRange int i, @IntRange int i2) {
        synchronized (f6586c) {
            Tracker.m7517a(5, "DAB", "onUpgrade", Integer.toString(i) + "," + Integer.toString(i2));
            try {
                String str = "DROP TABLE IF EXISTS events";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "DROP TABLE IF EXISTS updates";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                onCreate(sQLiteDatabase);
                mo26568b();
            } catch (Throwable th) {
                Tracker.m7517a(5, "DAB", "onUpgrade", th);
            }
        }
    }
}
