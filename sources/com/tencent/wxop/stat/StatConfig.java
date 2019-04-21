package com.tencent.wxop.stat;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.tencent.p059a.p060a.p061a.p062a.C4343g;
import com.tencent.wxop.stat.common.C4433k;
import com.tencent.wxop.stat.common.C4438p;
import com.tencent.wxop.stat.common.C4439q;
import com.tencent.wxop.stat.common.StatLogger;
import java.net.URI;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class StatConfig {
    /* renamed from: A */
    private static String f6868A = null;
    /* renamed from: B */
    private static String f6869B;
    /* renamed from: C */
    private static String f6870C;
    /* renamed from: D */
    private static String f6871D = "mta_channel";
    /* renamed from: E */
    private static int f6872E = 180;
    /* renamed from: F */
    private static int f6873F = 1024;
    /* renamed from: G */
    private static long f6874G = 0;
    /* renamed from: H */
    private static long f6875H = 300000;
    /* renamed from: I */
    private static volatile String f6876I = "http://pingma.qq.com:80/mstat/report";
    /* renamed from: J */
    private static int f6877J = 0;
    /* renamed from: K */
    private static volatile int f6878K = 0;
    /* renamed from: L */
    private static int f6879L = 20;
    /* renamed from: M */
    private static int f6880M = 0;
    /* renamed from: N */
    private static boolean f6881N = false;
    /* renamed from: O */
    private static int f6882O = 4096;
    /* renamed from: P */
    private static boolean f6883P = false;
    /* renamed from: Q */
    private static String f6884Q = null;
    /* renamed from: R */
    private static boolean f6885R = false;
    /* renamed from: S */
    private static C4444g f6886S = null;
    /* renamed from: a */
    static C4443f f6887a = new C4443f(2);
    /* renamed from: b */
    static C4443f f6888b = new C4443f(1);
    /* renamed from: c */
    static String f6889c = "__HIBERNATE__";
    /* renamed from: d */
    static String f6890d = "__HIBERNATE__TIME";
    /* renamed from: e */
    static String f6891e = "__MTA_KILL__";
    /* renamed from: f */
    static String f6892f = "";
    /* renamed from: g */
    static boolean f6893g = false;
    /* renamed from: h */
    static int f6894h = 100;
    /* renamed from: i */
    static long f6895i = 10000;
    public static boolean isAutoExceptionCaught = true;
    /* renamed from: j */
    static boolean f6896j = true;
    /* renamed from: k */
    static volatile String f6897k = "pingma.qq.com:80";
    /* renamed from: l */
    static boolean f6898l = true;
    /* renamed from: m */
    static int f6899m = 0;
    /* renamed from: n */
    static long f6900n = 10000;
    /* renamed from: o */
    static int f6901o = 512;
    /* renamed from: p */
    private static StatLogger f6902p = C4433k.m8111b();
    /* renamed from: q */
    private static StatReportStrategy f6903q = StatReportStrategy.APP_LAUNCH;
    /* renamed from: r */
    private static boolean f6904r = false;
    /* renamed from: s */
    private static boolean f6905s = true;
    /* renamed from: t */
    private static int f6906t = 30000;
    /* renamed from: u */
    private static int f6907u = 100000;
    /* renamed from: v */
    private static int f6908v = 30;
    /* renamed from: w */
    private static int f6909w = 10;
    /* renamed from: x */
    private static int f6910x = 100;
    /* renamed from: y */
    private static int f6911y = 30;
    /* renamed from: z */
    private static int f6912z = 1;

    /* renamed from: a */
    static int m7916a() {
        return f6908v;
    }

    /* renamed from: a */
    static String m7917a(Context context) {
        return C4439q.m8157a(C4438p.m8152a(context, "_mta_ky_tag_", null));
    }

    /* renamed from: a */
    static String m7918a(String str, String str2) {
        try {
            String string = f6888b.f7171b.getString(str);
            return string != null ? string : str2;
        } catch (Throwable th) {
            f6902p.mo33956w("can't find custom key:" + str);
            return str2;
        }
    }

    /* renamed from: a */
    static synchronized void m7919a(int i) {
        synchronized (StatConfig.class) {
            f6878K = i;
        }
    }

    /* renamed from: a */
    static void m7920a(long j) {
        C4438p.m8154b(C4445i.m8177a(), f6889c, j);
        setEnableStatService(false);
        f6902p.warn("MTA is disable for current SDK version");
    }

    /* renamed from: a */
    static void m7921a(Context context, C4443f c4443f) {
        if (c4443f.f7170a == f6888b.f7170a) {
            f6888b = c4443f;
            m7925a(c4443f.f7171b);
            if (!f6888b.f7171b.isNull("iplist")) {
                C4389a.m7995a(context).mo33899a(f6888b.f7171b.getString("iplist"));
            }
        } else if (c4443f.f7170a == f6887a.f7170a) {
            f6887a = c4443f;
        }
    }

    /* renamed from: a */
    static void m7922a(Context context, C4443f c4443f, JSONObject jSONObject) {
        Object obj = null;
        try {
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                if (str.equalsIgnoreCase("v")) {
                    int i = jSONObject.getInt(str);
                    Object obj2 = c4443f.f7173d != i ? 1 : obj;
                    c4443f.f7173d = i;
                    obj = obj2;
                } else if (str.equalsIgnoreCase("c")) {
                    str = jSONObject.getString("c");
                    if (str.length() > 0) {
                        c4443f.f7171b = JSONObjectInstrumentation.init(str);
                    }
                } else if (str.equalsIgnoreCase("m")) {
                    c4443f.f7172c = jSONObject.getString("m");
                }
            }
            if (obj == 1) {
                C4411au a = C4411au.m8029a(C4445i.m8177a());
                if (a != null) {
                    a.mo33928a(c4443f);
                }
                if (c4443f.f7170a == f6888b.f7170a) {
                    m7925a(c4443f.f7171b);
                    m7932b(c4443f.f7171b);
                }
            }
            m7921a(context, c4443f);
        } catch (JSONException e) {
            f6902p.mo33949e(e);
        } catch (Throwable e2) {
            f6902p.mo33949e(e2);
        }
    }

    /* renamed from: a */
    static void m7923a(Context context, String str) {
        if (str != null) {
            C4438p.m8155b(context, "_mta_ky_tag_", C4439q.m8162b(str));
        }
    }

    /* renamed from: a */
    static void m7924a(Context context, JSONObject jSONObject) {
        try {
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                if (str.equalsIgnoreCase(Integer.toString(f6888b.f7170a))) {
                    m7922a(context, f6888b, jSONObject.getJSONObject(str));
                } else if (str.equalsIgnoreCase(Integer.toString(f6887a.f7170a))) {
                    m7922a(context, f6887a, jSONObject.getJSONObject(str));
                } else if (str.equalsIgnoreCase("rs")) {
                    StatReportStrategy statReportStrategy = StatReportStrategy.getStatReportStrategy(jSONObject.getInt(str));
                    if (statReportStrategy != null) {
                        f6903q = statReportStrategy;
                        if (isDebugEnable()) {
                            f6902p.mo33946d("Change to ReportStrategy:" + statReportStrategy.name());
                        }
                    }
                } else {
                    return;
                }
            }
        } catch (JSONException e) {
            f6902p.mo33949e(e);
        }
    }

    /* renamed from: a */
    static void m7925a(JSONObject jSONObject) {
        try {
            StatReportStrategy statReportStrategy = StatReportStrategy.getStatReportStrategy(jSONObject.getInt("rs"));
            if (statReportStrategy != null) {
                setStatSendStrategy(statReportStrategy);
            }
        } catch (JSONException e) {
            if (isDebugEnable()) {
                f6902p.mo33952i("rs not found.");
            }
        }
    }

    /* renamed from: a */
    static boolean m7926a(int i, int i2, int i3) {
        return i >= i2 && i <= i3;
    }

    /* renamed from: a */
    private static boolean m7927a(String str) {
        if (str == null) {
            return false;
        }
        if (f6869B == null) {
            f6869B = str;
            return true;
        } else if (f6869B.contains(str)) {
            return false;
        } else {
            f6869B += "|" + str;
            return true;
        }
    }

    /* renamed from: a */
    static boolean m7928a(JSONObject jSONObject, String str, String str2) {
        if (!jSONObject.isNull(str)) {
            String optString = jSONObject.optString(str);
            if (C4433k.m8115c(str2) && C4433k.m8115c(optString) && str2.equalsIgnoreCase(optString)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    static void m7929b() {
        f6880M++;
    }

    /* renamed from: b */
    static void m7930b(int i) {
        if (i >= 0) {
            f6880M = i;
        }
    }

    /* renamed from: b */
    static void m7931b(Context context, JSONObject jSONObject) {
        try {
            String optString = jSONObject.optString(f6891e);
            if (C4433k.m8115c(optString)) {
                JSONObject init = JSONObjectInstrumentation.init(optString);
                if (init.length() != 0) {
                    Object obj;
                    if (!init.isNull("sm")) {
                        obj = init.get("sm");
                        int intValue = obj instanceof Integer ? ((Integer) obj).intValue() : obj instanceof String ? Integer.valueOf((String) obj).intValue() : 0;
                        if (intValue > 0) {
                            if (isDebugEnable()) {
                                f6902p.mo33952i("match sleepTime:" + intValue + " minutes");
                            }
                            C4438p.m8154b(context, f6890d, System.currentTimeMillis() + ((long) ((intValue * 60) * 1000)));
                            setEnableStatService(false);
                            f6902p.warn("MTA is disable for current SDK version");
                        }
                    }
                    if (m7928a(init, "sv", "2.0.3")) {
                        f6902p.mo33952i("match sdk version:2.0.3");
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    if (m7928a(init, "md", Build.MODEL)) {
                        f6902p.mo33952i("match MODEL:" + Build.MODEL);
                        obj = 1;
                    }
                    if (m7928a(init, "av", C4433k.m8127j(context))) {
                        f6902p.mo33952i("match app version:" + C4433k.m8127j(context));
                        obj = 1;
                    }
                    if (m7928a(init, "mf", Build.MANUFACTURER)) {
                        f6902p.mo33952i("match MANUFACTURER:" + Build.MANUFACTURER);
                        obj = 1;
                    }
                    if (m7928a(init, "osv", VERSION.SDK_INT)) {
                        f6902p.mo33952i("match android SDK version:" + VERSION.SDK_INT);
                        obj = 1;
                    }
                    if (m7928a(init, "ov", VERSION.SDK_INT)) {
                        f6902p.mo33952i("match android SDK version:" + VERSION.SDK_INT);
                        obj = 1;
                    }
                    if (m7928a(init, "ui", C4411au.m8029a(context).mo33931b(context).mo33960b())) {
                        f6902p.mo33952i("match imei:" + C4411au.m8029a(context).mo33931b(context).mo33960b());
                        obj = 1;
                    }
                    if (m7928a(init, "mid", getLocalMidOnly(context))) {
                        f6902p.mo33952i("match mid:" + getLocalMidOnly(context));
                        obj = 1;
                    }
                    if (obj != null) {
                        m7920a(C4433k.m8110b("2.0.3"));
                    }
                }
            }
        } catch (Exception e) {
            f6902p.mo33949e(e);
        }
    }

    /* renamed from: b */
    static void m7932b(JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.length() != 0) {
            try {
                m7931b(C4445i.m8177a(), jSONObject);
                String string = jSONObject.getString(f6889c);
                if (isDebugEnable()) {
                    f6902p.mo33946d("hibernateVer:" + string + ", current version:2.0.3");
                }
                long b = C4433k.m8110b(string);
                if (C4433k.m8110b("2.0.3") <= b) {
                    m7920a(b);
                }
            } catch (JSONException e) {
                f6902p.mo33946d("__HIBERNATE__ not found.");
            }
        }
    }

    /* renamed from: c */
    static int m7933c() {
        return f6880M;
    }

    public static synchronized String getAppKey(Context context) {
        String str;
        synchronized (StatConfig.class) {
            if (f6869B != null) {
                str = f6869B;
            } else {
                if (context != null) {
                    if (f6869B == null) {
                        f6869B = C4433k.m8121f(context);
                    }
                }
                if (f6869B == null || f6869B.trim().length() == 0) {
                    f6902p.error((Object) "AppKey can not be null or empty, please read Developer's Guide first!");
                }
                str = f6869B;
            }
        }
        return str;
    }

    public static C4444g getCustomLogger() {
        return f6886S;
    }

    public static String getCustomUserId(Context context) {
        if (context == null) {
            f6902p.error((Object) "Context for getCustomUid is null.");
            return null;
        }
        if (f6884Q == null) {
            f6884Q = C4438p.m8152a(context, "MTA_CUSTOM_UID", "");
        }
        return f6884Q;
    }

    public static synchronized String getInstallChannel(Context context) {
        String str;
        synchronized (StatConfig.class) {
            if (f6870C != null) {
                str = f6870C;
            } else {
                str = C4438p.m8152a(context, f6871D, "");
                f6870C = str;
                if (str == null || f6870C.trim().length() == 0) {
                    f6870C = C4433k.m8122g(context);
                }
                if (f6870C == null || f6870C.trim().length() == 0) {
                    f6902p.mo33956w("installChannel can not be null or empty, please read Developer's Guide first!");
                }
                str = f6870C;
            }
        }
        return str;
    }

    public static String getLocalMidOnly(Context context) {
        return context != null ? C4343g.m7865E(context).mo33764p().mo33761a() : "0";
    }

    public static int getMaxBatchReportCount() {
        return f6911y;
    }

    public static int getMaxDaySessionNumbers() {
        return f6879L;
    }

    public static int getMaxImportantDataSendRetryCount() {
        return f6910x;
    }

    public static int getMaxParallelTimmingEvents() {
        return f6873F;
    }

    public static int getMaxReportEventLength() {
        return f6882O;
    }

    public static int getMaxSendRetryCount() {
        return f6909w;
    }

    public static int getMaxStoreEventCount() {
        return f6907u;
    }

    public static int getNumEventsCommitPerSec() {
        return f6912z;
    }

    public static String getQQ(Context context) {
        return C4438p.m8152a(context, "mta.acc.qq", f6892f);
    }

    public static int getSendPeriodMinutes() {
        return f6872E;
    }

    public static int getSessionTimoutMillis() {
        return f6906t;
    }

    public static String getStatReportUrl() {
        return f6876I;
    }

    public static StatReportStrategy getStatSendStrategy() {
        return f6903q;
    }

    public static boolean isAutoExceptionCaught() {
        return isAutoExceptionCaught;
    }

    public static boolean isDebugEnable() {
        return f6904r;
    }

    public static boolean isEnableConcurrentProcess() {
        return f6883P;
    }

    public static boolean isEnableStatService() {
        return f6905s;
    }

    public static void setAppKey(Context context, String str) {
        if (context == null) {
            f6902p.error((Object) "ctx in StatConfig.setAppKey() is null");
        } else if (str == null || str.length() > 256) {
            f6902p.error((Object) "appkey in StatConfig.setAppKey() is null or exceed 256 bytes");
        } else {
            if (f6869B == null) {
                f6869B = m7917a(context);
            }
            if ((m7927a(str) | m7927a(C4433k.m8121f(context))) != 0) {
                m7923a(context, f6869B);
            }
        }
    }

    public static void setEnableSmartReporting(boolean z) {
        f6896j = z;
    }

    public static void setEnableStatService(boolean z) {
        f6905s = z;
        if (!z) {
            f6902p.warn("!!!!!!MTA StatService has been disabled!!!!!!");
        }
    }

    public static void setInstallChannel(Context context, String str) {
        if (str.length() > 128) {
            f6902p.error((Object) "the length of installChannel can not exceed the range of 128 bytes.");
            return;
        }
        f6870C = str;
        C4438p.m8155b(context, f6871D, str);
    }

    public static void setInstallChannel(String str) {
        if (str.length() > 128) {
            f6902p.error((Object) "the length of installChannel can not exceed the range of 128 bytes.");
        } else {
            f6870C = str;
        }
    }

    public static void setQQ(Context context, String str) {
        C4438p.m8155b(context, "mta.acc.qq", str);
        f6892f = str;
    }

    public static void setSendPeriodMinutes(int i) {
        if (m7926a(i, 1, 10080)) {
            f6872E = i;
        } else {
            f6902p.error((Object) "setSendPeriodMinutes can not exceed the range of [1, 7*24*60] minutes.");
        }
    }

    public static void setStatReportUrl(String str) {
        if (str == null || str.length() == 0) {
            f6902p.error((Object) "statReportUrl cannot be null or empty.");
            return;
        }
        f6876I = str;
        try {
            f6897k = new URI(f6876I).getHost();
        } catch (Exception e) {
            f6902p.mo33956w(e);
        }
        if (isDebugEnable()) {
            f6902p.mo33952i("url:" + f6876I + ", domain:" + f6897k);
        }
    }

    public static void setStatSendStrategy(StatReportStrategy statReportStrategy) {
        f6903q = statReportStrategy;
        if (statReportStrategy != StatReportStrategy.PERIOD) {
            StatServiceImpl.f6920c = 0;
        }
        if (isDebugEnable()) {
            f6902p.mo33946d("Change to statSendStrategy: " + statReportStrategy);
        }
    }
}
