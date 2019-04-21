package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.common.C4427e;
import com.tencent.wxop.stat.common.C4433k;
import com.tencent.wxop.stat.common.C4438p;
import com.tencent.wxop.stat.common.StatLogger;
import com.tencent.wxop.stat.p069a.C4378a;
import com.tencent.wxop.stat.p069a.C4380c;
import com.tencent.wxop.stat.p069a.C4385i;
import com.tencent.wxop.stat.p069a.C4388l;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class StatServiceImpl {
    /* renamed from: a */
    static volatile int f6918a = 0;
    /* renamed from: b */
    static volatile long f6919b = 0;
    /* renamed from: c */
    static volatile long f6920c = 0;
    /* renamed from: d */
    private static C4427e f6921d;
    /* renamed from: e */
    private static volatile Map<C4380c, Long> f6922e = new ConcurrentHashMap();
    /* renamed from: f */
    private static volatile Map<String, Properties> f6923f = new ConcurrentHashMap();
    /* renamed from: g */
    private static volatile Map<Integer, Integer> f6924g = new ConcurrentHashMap(10);
    /* renamed from: h */
    private static volatile long f6925h = 0;
    /* renamed from: i */
    private static volatile long f6926i = 0;
    /* renamed from: j */
    private static volatile long f6927j = 0;
    /* renamed from: k */
    private static String f6928k = "";
    /* renamed from: l */
    private static volatile int f6929l = 0;
    /* renamed from: m */
    private static volatile String f6930m = "";
    /* renamed from: n */
    private static volatile String f6931n = "";
    /* renamed from: o */
    private static Map<String, Long> f6932o = new ConcurrentHashMap();
    /* renamed from: p */
    private static Map<String, Long> f6933p = new ConcurrentHashMap();
    /* renamed from: q */
    private static StatLogger f6934q = C4433k.m8111b();
    /* renamed from: r */
    private static UncaughtExceptionHandler f6935r = null;
    /* renamed from: s */
    private static volatile boolean f6936s = true;
    /* renamed from: t */
    private static Context f6937t = null;

    /* renamed from: a */
    static int m7935a(Context context, boolean z, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        int i = 1;
        long currentTimeMillis = System.currentTimeMillis();
        int i2 = (!z || currentTimeMillis - f6926i < ((long) StatConfig.getSessionTimoutMillis())) ? 0 : 1;
        f6926i = currentTimeMillis;
        if (f6927j == 0) {
            f6927j = C4433k.m8113c();
        }
        if (currentTimeMillis >= f6927j) {
            f6927j = C4433k.m8113c();
            if (C4411au.m8029a(context).mo33931b(context).mo33962d() != 1) {
                C4411au.m8029a(context).mo33931b(context).mo33959a(1);
            }
            StatConfig.m7930b(0);
            f6918a = 0;
            f6928k = C4433k.m8102a(0);
            i2 = 1;
        }
        Object obj = f6928k;
        if (C4433k.m8108a(statSpecifyReportedInfo)) {
            obj = statSpecifyReportedInfo.getAppKey() + f6928k;
        }
        if (f6933p.containsKey(obj)) {
            i = i2;
        }
        if (i != 0) {
            if (C4433k.m8108a(statSpecifyReportedInfo)) {
                m7940a(context, statSpecifyReportedInfo);
            } else if (StatConfig.m7933c() < StatConfig.getMaxDaySessionNumbers()) {
                C4433k.m8141x(context);
                m7940a(context, null);
            } else {
                f6934q.mo33948e((Object) "Exceed StatConfig.getMaxDaySessionNumbers().");
            }
            f6933p.put(obj, Long.valueOf(1));
        }
        if (f6936s) {
            testSpeed(context);
            f6936s = false;
        }
        return f6929l;
    }

    /* renamed from: a */
    static synchronized void m7938a(Context context) {
        synchronized (StatServiceImpl.class) {
            if (context != null) {
                if (f6921d == null && m7947b(context)) {
                    Context applicationContext = context.getApplicationContext();
                    f6937t = applicationContext;
                    f6921d = new C4427e();
                    f6928k = C4433k.m8102a(0);
                    f6925h = System.currentTimeMillis() + StatConfig.f6895i;
                    f6921d.mo33966a(new C4448l(applicationContext));
                }
            }
        }
    }

    /* renamed from: a */
    static void m7940a(Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (m7948c(context) != null) {
            if (StatConfig.isDebugEnable()) {
                f6934q.mo33946d("start new session.");
            }
            if (statSpecifyReportedInfo == null || f6929l == 0) {
                f6929l = C4433k.m8099a();
            }
            StatConfig.m7919a(0);
            StatConfig.m7929b();
            new C4406aq(new C4388l(context, f6929l, m7945b(), statSpecifyReportedInfo)).mo33922a();
        }
    }

    /* renamed from: a */
    static void m7941a(Context context, Throwable th) {
        if (StatConfig.isEnableStatService()) {
            Context context2 = getContext(context);
            if (context2 == null) {
                f6934q.error((Object) "The Context of StatService.reportSdkSelfException() can not be null!");
            } else if (m7948c(context2) != null) {
                f6921d.mo33966a(new C4453q(context2, th));
            }
        }
    }

    /* renamed from: a */
    static boolean m7942a() {
        if (f6918a < 2) {
            return false;
        }
        f6919b = System.currentTimeMillis();
        return true;
    }

    /* renamed from: a */
    static boolean m7943a(String str) {
        return str == null || str.length() == 0;
    }

    /* renamed from: b */
    static JSONObject m7945b() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            if (StatConfig.f6888b.f7173d != 0) {
                jSONObject2.put("v", StatConfig.f6888b.f7173d);
            }
            jSONObject.put(Integer.toString(StatConfig.f6888b.f7170a), jSONObject2);
            jSONObject2 = new JSONObject();
            if (StatConfig.f6887a.f7173d != 0) {
                jSONObject2.put("v", StatConfig.f6887a.f7173d);
            }
            jSONObject.put(Integer.toString(StatConfig.f6887a.f7170a), jSONObject2);
        } catch (JSONException e) {
            f6934q.mo33949e(e);
        }
        return jSONObject;
    }

    /* renamed from: b */
    private static void m7946b(Context context, StatAccount statAccount, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        try {
            new C4406aq(new C4378a(context, m7935a(context, false, statSpecifyReportedInfo), statAccount, statSpecifyReportedInfo)).mo33922a();
        } catch (Throwable th) {
            f6934q.mo33949e(th);
            m7941a(context, th);
        }
    }

    /* renamed from: b */
    static boolean m7947b(Context context) {
        boolean z = false;
        long a = C4438p.m8150a(context, StatConfig.f6889c, 0);
        long b = C4433k.m8110b("2.0.3");
        boolean z2 = true;
        if (b <= a) {
            f6934q.error("MTA is disable for current version:" + b + ",wakeup version:" + a);
            z2 = false;
        }
        a = C4438p.m8150a(context, StatConfig.f6890d, 0);
        if (a > System.currentTimeMillis()) {
            f6934q.error("MTA is disable for current time:" + System.currentTimeMillis() + ",wakeup time:" + a);
        } else {
            z = z2;
        }
        StatConfig.setEnableStatService(z);
        return z;
    }

    /* renamed from: c */
    static C4427e m7948c(Context context) {
        if (f6921d == null) {
            synchronized (StatServiceImpl.class) {
                if (f6921d == null) {
                    try {
                        m7938a(context);
                    } catch (Throwable th) {
                        f6934q.error(th);
                        StatConfig.setEnableStatService(false);
                    }
                }
            }
        }
        return f6921d;
    }

    /* renamed from: c */
    static void m7950c() {
        f6918a = 0;
        f6919b = 0;
    }

    public static void commitEvents(Context context, int i) {
        if (StatConfig.isEnableStatService()) {
            if (StatConfig.isDebugEnable()) {
                f6934q.mo33952i("commitEvents, maxNumber=" + i);
            }
            Context context2 = getContext(context);
            if (context2 == null) {
                f6934q.error((Object) "The Context of StatService.commitEvents() can not be null!");
            } else if (i < -1 || i == 0) {
                f6934q.error((Object) "The maxNumber of StatService.commitEvents() should be -1 or bigger than 0.");
            } else if (C4389a.m7995a(f6937t).mo33904f() && m7948c(context2) != null) {
                f6921d.mo33966a(new C4393ad(context2, i));
            }
        }
    }

    /* renamed from: d */
    static void m7951d() {
        f6918a++;
        f6919b = System.currentTimeMillis();
        flushDataToDB(f6937t);
    }

    /* renamed from: d */
    static void m7952d(Context context) {
        if (StatConfig.isEnableStatService()) {
            Context context2 = getContext(context);
            if (context2 == null) {
                f6934q.error((Object) "The Context of StatService.sendNetworkDetector() can not be null!");
                return;
            }
            try {
                C4445i.m8180b(context2).mo33980a(new C4385i(context2), new C4456t());
            } catch (Throwable th) {
                f6934q.mo33949e(th);
            }
        }
    }

    /* renamed from: e */
    static void m7954e(Context context) {
        f6920c = System.currentTimeMillis() + ((long) (60000 * StatConfig.getSendPeriodMinutes()));
        C4438p.m8154b(context, "last_period_ts", f6920c);
        commitEvents(context, -1);
    }

    public static void flushDataToDB(Context context) {
        if (StatConfig.isEnableStatService() && StatConfig.f6899m > 0) {
            Context context2 = getContext(context);
            if (context2 == null) {
                f6934q.error((Object) "The Context of StatService.testSpeed() can not be null!");
            } else {
                C4411au.m8029a(context2).mo33932c();
            }
        }
    }

    public static Properties getCommonKeyValueForKVEvent(String str) {
        return (Properties) f6923f.get(str);
    }

    public static Context getContext(Context context) {
        return context != null ? context : f6937t;
    }

    public static void onPause(Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.isEnableStatService() && m7948c(context) != null) {
            f6921d.mo33966a(new C4449m(context, statSpecifyReportedInfo));
        }
    }

    public static void onResume(Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.isEnableStatService() && m7948c(context) != null) {
            f6921d.mo33966a(new C4399aj(context, statSpecifyReportedInfo));
        }
    }

    public static boolean startStatService(Context context, String str, String str2, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        try {
            if (StatConfig.isEnableStatService()) {
                String str3 = "2.0.3";
                if (StatConfig.isDebugEnable()) {
                    f6934q.mo33946d("MTA SDK version, current: " + str3 + " ,required: " + str2);
                }
                if (context == null || str2 == null) {
                    f6934q.error((Object) "Context or mtaSdkVersion in StatService.startStatService() is null, please check it!");
                    StatConfig.setEnableStatService(false);
                    return false;
                } else if (C4433k.m8110b(str3) < C4433k.m8110b(str2)) {
                    f6934q.error(("MTA SDK version conflicted, current: " + str3 + ",required: " + str2) + ". please delete the current SDK and download the latest one. official website: http://mta.qq.com/ or http://mta.oa.com/");
                    StatConfig.setEnableStatService(false);
                    return false;
                } else {
                    str3 = StatConfig.getInstallChannel(context);
                    if (str3 == null || str3.length() == 0) {
                        StatConfig.setInstallChannel("-");
                    }
                    if (str != null) {
                        StatConfig.setAppKey(context, str);
                    }
                    if (m7948c(context) != null) {
                        f6921d.mo33966a(new C4403an(context, statSpecifyReportedInfo));
                    }
                    return true;
                }
            }
            f6934q.error((Object) "MTA StatService is disable.");
            return false;
        } catch (Throwable th) {
            f6934q.mo33949e(th);
            return false;
        }
    }

    public static void stopSession() {
        f6926i = 0;
    }

    public static void testSpeed(Context context) {
        if (StatConfig.isEnableStatService()) {
            Context context2 = getContext(context);
            if (context2 == null) {
                f6934q.error((Object) "The Context of StatService.testSpeed() can not be null!");
            } else if (m7948c(context2) != null) {
                f6921d.mo33966a(new C4394ae(context2));
            }
        }
    }

    public static void trackBeginPage(Context context, String str, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.isEnableStatService()) {
            Context context2 = getContext(context);
            if (context2 == null || str == null || str.length() == 0) {
                f6934q.error((Object) "The Context or pageName of StatService.trackBeginPage() can not be null or empty!");
                return;
            }
            String str2 = new String(str);
            if (m7948c(context2) != null) {
                f6921d.mo33966a(new C4459w(str2, context2, statSpecifyReportedInfo));
            }
        }
    }

    public static void trackCustomKVEvent(Context context, String str, Properties properties, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.isEnableStatService()) {
            Context context2 = getContext(context);
            if (context2 == null) {
                f6934q.error((Object) "The Context of StatService.trackCustomEvent() can not be null!");
            } else if (m7943a(str)) {
                f6934q.error((Object) "The event_id of StatService.trackCustomEvent() can not be null or empty.");
            } else {
                C4380c c4380c = new C4380c(str, null, properties);
                if (m7948c(context2) != null) {
                    f6921d.mo33966a(new C4457u(context2, statSpecifyReportedInfo, c4380c));
                }
            }
        }
    }

    public static void trackEndPage(Context context, String str, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.isEnableStatService()) {
            Context context2 = getContext(context);
            if (context2 == null || str == null || str.length() == 0) {
                f6934q.error((Object) "The Context or pageName of StatService.trackEndPage() can not be null or empty!");
                return;
            }
            String str2 = new String(str);
            if (m7948c(context2) != null) {
                f6921d.mo33966a(new C4397ah(context2, str2, statSpecifyReportedInfo));
            }
        }
    }
}
