package com.baidu.android.pushservice.util;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import com.autonavi.amap.mapcore.VTMCDataCache;
import com.baidu.android.pushservice.C1328a;
import com.baidu.android.pushservice.C1463j;
import com.baidu.android.pushservice.PushSettings;
import com.baidu.android.pushservice.config.ModeConfig;
import com.baidu.android.pushservice.jni.PushSocket;
import com.baidu.android.pushservice.message.C1512k;
import com.baidu.android.pushservice.message.PublicMsg;
import com.baidu.android.pushservice.message.p040a.C1486e;
import com.baidu.android.pushservice.p031c.C1332b;
import com.baidu.android.pushservice.p031c.C1339h;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p036h.C1426b;
import com.baidu.android.pushservice.p037i.C1437c;
import com.baidu.android.pushservice.p037i.C1449n;
import com.baidu.android.pushservice.p039k.C1465b;
import com.baidu.android.pushservice.p039k.C1471e;
import com.baidu.android.pushservice.p039k.C1472f;
import com.baidu.android.pushservice.p039k.C1473g;
import com.facebook.internal.NativeProtocol;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONObject;

@SuppressLint({"WorldReadableFiles"})
/* renamed from: com.baidu.android.pushservice.util.v */
public final class C1578v {
    /* renamed from: a */
    private static final String[] f5539a = new String[]{"android.permission.INTERNET", "android.permission.READ_PHONE_STATE", "android.permission.ACCESS_NETWORK_STATE", "android.permission.WRITE_SETTINGS", "android.permission.VIBRATE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_WIFI_STATE"};
    /* renamed from: b */
    private static int f5540b = -1;
    /* renamed from: c */
    private static boolean f5541c = false;

    /* renamed from: A */
    public static void m7052A(Context context) {
        Intent intent = new Intent("com.baidu.android.pushservice.action.METHOD");
        intent.putExtra("method", "com.baidu.android.pushservice.action.SEND_APPSTAT");
        C1463j.m6642a(context.getApplicationContext()).mo13940a(intent);
    }

    /* renamed from: B */
    public static void m7053B(Context context) {
        C1486e.m6743a(context);
        Intent intent = new Intent("com.baidu.android.pushservice.action.METHOD");
        intent.putExtra("method", "com.baidu.android.pushservice.action.SEND_LBS");
        C1463j.m6642a(context.getApplicationContext()).mo13940a(intent);
    }

    /* renamed from: C */
    public static String m7054C(Context context) {
        String str = "";
        str = "";
        String toUpperCase = Build.MANUFACTURER.toUpperCase();
        if (toUpperCase.contains("XIAOMI")) {
            str = "ro.build.version.incremental";
        } else if (toUpperCase.contains("HUAWEI")) {
            str = "ro.build.version.emui";
        }
        try {
            Class cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getDeclaredMethod("get", new Class[]{String.class}).invoke(cls, new Object[]{str});
        } catch (Exception e) {
            C1425a.m6440a("Utility", e);
            return "";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0080  */
    /* renamed from: D */
    public static java.lang.String m7055D(android.content.Context r9) {
        /*
        r1 = "";
        r0 = com.baidu.android.pushservice.util.C1578v.m7118f();
        if (r0 == 0) goto L_0x0095;
    L_0x0008:
        r0 = "";
        r2 = android.os.Build.MANUFACTURER;
        r2 = r2.toUpperCase();
        r3 = "XIAOMI";
        r3 = r2.contains(r3);
        if (r3 == 0) goto L_0x0069;
    L_0x0018:
        r0 = "ro.miui.ui.version.code";
    L_0x001a:
        r3 = "android.os.SystemProperties";
        r3 = java.lang.Class.forName(r3);	 Catch:{ Exception -> 0x0074 }
        r4 = "get";
        r5 = 1;
        r5 = new java.lang.Class[r5];	 Catch:{ Exception -> 0x0074 }
        r6 = 0;
        r7 = java.lang.String.class;
        r5[r6] = r7;	 Catch:{ Exception -> 0x0074 }
        r4 = r3.getDeclaredMethod(r4, r5);	 Catch:{ Exception -> 0x0074 }
        r5 = 1;
        r5 = new java.lang.Object[r5];	 Catch:{ Exception -> 0x0074 }
        r6 = 0;
        r5[r6] = r0;	 Catch:{ Exception -> 0x0074 }
        r0 = r4.invoke(r3, r5);	 Catch:{ Exception -> 0x0074 }
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x0074 }
        r1 = "HUAWEI";
        r1 = r2.contains(r1);	 Catch:{ Exception -> 0x0093 }
        if (r1 == 0) goto L_0x0068;
    L_0x0042:
        r1 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x0093 }
        if (r1 != 0) goto L_0x0068;
    L_0x0048:
        r1 = "_";
        r1 = r0.indexOf(r1);	 Catch:{ Exception -> 0x0093 }
        r1 = r1 + 1;
        r3 = r0.length();	 Catch:{ Exception -> 0x0093 }
        r1 = r0.substring(r1, r3);	 Catch:{ Exception -> 0x0093 }
        r3 = "\\d+\\.\\d+$";
        r3 = r1.matches(r3);	 Catch:{ Exception -> 0x0093 }
        if (r3 != 0) goto L_0x0095;
    L_0x0060:
        r3 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x0093 }
        r4 = 21;
        if (r3 < r4) goto L_0x0095;
    L_0x0066:
        r0 = "3.1";
    L_0x0068:
        return r0;
    L_0x0069:
        r3 = "HUAWEI";
        r3 = r2.contains(r3);
        if (r3 == 0) goto L_0x001a;
    L_0x0071:
        r0 = "ro.build.version.emui";
        goto L_0x001a;
    L_0x0074:
        r0 = move-exception;
        r8 = r0;
        r0 = r1;
        r1 = r8;
    L_0x0078:
        r3 = "HUAWEI";
        r3 = r2.contains(r3);
        if (r3 == 0) goto L_0x0088;
    L_0x0080:
        r0 = "3.1";
    L_0x0082:
        r2 = "Utility";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x0068;
    L_0x0088:
        r3 = "XIAOMI";
        r2 = r2.contains(r3);
        if (r2 == 0) goto L_0x0082;
    L_0x0090:
        r0 = "4.0";
        goto L_0x0082;
    L_0x0093:
        r1 = move-exception;
        goto L_0x0078;
    L_0x0095:
        r0 = r1;
        goto L_0x0068;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1578v.m7055D(android.content.Context):java.lang.String");
    }

    /* renamed from: E */
    public static boolean m7056E(Context context) {
        try {
            PackageInfo packageInfo = context.getApplicationContext().getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if ((packageInfo != null ? packageInfo.applicationInfo.targetSdkVersion : 0) >= 24 && VERSION.SDK_INT >= 24) {
                return false;
            }
        } catch (Exception e) {
            C1425a.m6440a("Utility", e);
        }
        return true;
    }

    /* renamed from: F */
    private static boolean m7057F(Context context) {
        if (!C1578v.m7058G(context)) {
            return C1578v.m7083a(context, "com.baidu.android.pushservice.action.PUSH_SERVICE", "com.baidu.android.pushservice.PushService", false);
        }
        C1425a.m6444e("Utility", "xiaomi service is not found!!!");
        return false;
    }

    /* renamed from: G */
    private static boolean m7058G(Context context) {
        try {
            boolean z = C1578v.m7112d() && PushSettings.m5894m(context);
            if (z) {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4);
                String str = "com.xiaomi.mipush.sdk.PushMessageHandler";
                String str2 = "com.xiaomi.mipush.sdk.MessageHandleService";
                if (packageInfo.services != null) {
                    z = false;
                    boolean z2 = false;
                    for (ServiceInfo serviceInfo : packageInfo.services) {
                        int x;
                        if (serviceInfo.name.equals(str)) {
                            x = C1578v.m7153x(context, str);
                            if (serviceInfo.exported && (x == 1 || (x == 0 && serviceInfo.enabled))) {
                                z2 = true;
                            }
                        }
                        if (serviceInfo.name.equals(str2)) {
                            x = C1578v.m7153x(context, str);
                            if (x == 1 || (x == 0 && serviceInfo.enabled)) {
                                z = true;
                            }
                        }
                    }
                    if (!(z && z2)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            C1425a.m6440a("Utility", e);
        }
        return false;
    }

    /* renamed from: H */
    private static boolean m7059H(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            ComponentName componentName = new ComponentName(context, "com.baidu.android.pushservice.PushInfoProvider");
            ProviderInfo providerInfo = new ProviderInfo();
            String str = "";
            ProviderInfo providerInfo2 = packageManager.getProviderInfo(componentName, 128);
            String str2 = providerInfo2.name;
            C1426b.m6448c("Utility", "provider name  = " + str2 + "  export  = " + providerInfo2.exported, context.getApplicationContext());
            if (TextUtils.isEmpty(str2)) {
                Log.e("BDPushSDK-Utility", "com.baidu.android.pushservice.util.PushADProvider did not declared ");
                return false;
            } else if (providerInfo2.exported) {
                return !TextUtils.isEmpty(providerInfo2.writePermission);
            } else {
                Log.e("BDPushSDK-Utility", "com.baidu.android.pushservice.PushInfoProvider exported declared wrong ");
                return false;
            }
        } catch (Exception e) {
            C1425a.m6440a("Utility", e);
            return false;
        }
    }

    /* renamed from: I */
    private static boolean m7060I(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            ComponentName componentName = new ComponentName(context, "com.baidu.android.pushservice.util.PushADProvider");
            ProviderInfo providerInfo = new ProviderInfo();
            String str = "";
            ProviderInfo providerInfo2 = packageManager.getProviderInfo(componentName, 128);
            String str2 = providerInfo2.name;
            C1426b.m6448c("Utility", "provider name  = " + str2 + "  export  = " + providerInfo2.exported, context.getApplicationContext());
            if (TextUtils.isEmpty(str2)) {
                C1425a.m6444e("Utility", "com.baidu.android.pushservice.util.PushADProvider did not declared ");
                return false;
            } else if (providerInfo2.exported) {
                return true;
            } else {
                C1425a.m6444e("Utility", "com.baidu.android.pushservice.util.PushADProvider exported declared wrong ");
                return false;
            }
        } catch (Exception e) {
            C1425a.m6440a("Utility", e);
            return false;
        }
    }

    /* renamed from: J */
    private static boolean m7061J(Context context) {
        if (f5540b == -1) {
            f5540b = C1578v.m7142r(context, "android.permission.WRITE_EXTERNAL_STORAGE") ? 0 : 1;
        }
        return f5540b == 0;
    }

    /* renamed from: K */
    private static boolean m7062K(Context context) {
        try {
            String str = Build.DISPLAY;
            if (!TextUtils.isEmpty(str) && str.contains("VIBEUI_V3.1_1614_5.294.1_ST_K50-T5")) {
                return true;
            }
            str = Build.MODEL;
            if (!TextUtils.isEmpty(str) && (str.contains("Lenovo K50-t5") || str.contains("Lenovo_K50-t5") || str.contains("Lenovo X3c50") || str.contains("Lenovo_X3c50"))) {
                return true;
            }
            return false;
        } catch (Exception e) {
            C1425a.m6440a("Utility", e);
        }
    }

    /* renamed from: a */
    public static int m7063a(Context context, Intent intent, String str, String str2) {
        intent.setFlags(32);
        if (C1578v.m7127k(context, str2) >= 50) {
            if (!TextUtils.isEmpty(str)) {
                intent.setAction(str);
            }
            if (!TextUtils.isEmpty(str2)) {
                intent.setPackage(str2);
                intent.setClassName(str2, "com.baidu.android.pushservice.CommandService");
            }
            intent.putExtra("bd.cross.request.COMMAND_TYPE", "bd.cross.command.MESSAGE_DELIVER");
            intent.putExtra("bd.cross.request.SOURCE_SERVICE", "com.baidu.android.pushservice.PushService");
            return new C1539f(context, intent).mo14063b().mo13990a();
        }
        C1578v.m7094b(context, intent, str, str2);
        return 0;
    }

    /* renamed from: a */
    public static ComponentName m7064a(Context context, Intent intent, String str) {
        String v = C1578v.m7149v(context);
        if (TextUtils.isEmpty(v)) {
            return null;
        }
        String valueOf;
        Parcelable parcelableExtra = intent.getParcelableExtra("ad_msg");
        String str2 = "";
        if (parcelableExtra != null) {
            try {
                if (parcelableExtra instanceof PublicMsg) {
                    valueOf = String.valueOf(((PublicMsg) parcelableExtra).mAdvertiseStyle);
                    str2 = valueOf;
                    intent.setClassName(v, "com.baidu.android.pushservice.PushService");
                    intent.setAction("com.baidu.android.pushservice.action.adnotification.ADSHOW");
                    intent.putExtra("action_type", str);
                    intent.putExtra("advertisestyle", str2);
                    C1425a.m6442c("Utility", "CLICK  intent  =" + intent.toString() + "  intent_extra = " + intent.getExtras());
                    return context.startService(intent);
                }
            } catch (Exception e) {
                C1425a.m6440a("Utility", e);
            }
        }
        valueOf = str2;
        str2 = valueOf;
        intent.setClassName(v, "com.baidu.android.pushservice.PushService");
        intent.setAction("com.baidu.android.pushservice.action.adnotification.ADSHOW");
        intent.putExtra("action_type", str);
        intent.putExtra("advertisestyle", str2);
        C1425a.m6442c("Utility", "CLICK  intent  =" + intent.toString() + "  intent_extra = " + intent.getExtras());
        return context.startService(intent);
    }

    /* renamed from: a */
    public static PackageInfo m7065a(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return packageManager == null ? null : packageManager.getPackageInfo(str, 64);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    public static C1449n m7066a(C1449n c1449n, Context context, String str) {
        PackageInfo a = C1578v.m7065a(context, str);
        if (a != null) {
            c1449n.mo13863e(a.applicationInfo.loadLabel(context.getPackageManager()).toString());
            c1449n.mo13867g(a.versionName);
            c1449n.mo13853a(a.versionCode);
            c1449n.mo13865f(C1578v.m7129l(context, str));
            c1449n.mo13856b(C1578v.m7127k(context, str));
        }
        return c1449n;
    }

    /* renamed from: a */
    public static String m7067a(long j) {
        StringBuffer stringBuffer = new StringBuffer();
        long currentTimeMillis = System.currentTimeMillis() - j;
        long ceil = (long) Math.ceil((((double) currentTimeMillis) * 1.0d) / 1000.0d);
        long ceil2 = (long) Math.ceil((double) (((float) (currentTimeMillis / 60)) / 1000.0f));
        long ceil3 = (long) Math.ceil((double) (((float) ((currentTimeMillis / 60) / 60)) / 1000.0f));
        currentTimeMillis = (long) Math.ceil((double) (((float) (((currentTimeMillis / 24) / 60) / 60)) / 1000.0f));
        if (currentTimeMillis - 1 > 3) {
            stringBuffer.append(new SimpleDateFormat("MM月dd日").format(new Date(j)));
        } else if (currentTimeMillis - 1 > 0) {
            stringBuffer.append(currentTimeMillis + "天前");
        } else if (ceil3 - 1 > 0) {
            if (ceil3 >= 24) {
                stringBuffer.append("1天前");
            } else {
                stringBuffer.append(ceil3 + "小时前");
            }
        } else if (ceil2 - 1 > 0) {
            if (ceil2 == 60) {
                stringBuffer.append("1小时前");
            } else {
                stringBuffer.append(ceil2 + "分钟前");
            }
        } else if (ceil - 1 <= 0) {
            stringBuffer.append("刚刚");
        } else if (ceil == 60) {
            stringBuffer.append("1分钟前");
        } else {
            stringBuffer.append(ceil + "秒前");
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public static String m7068a(Context context, String str, String str2) {
        if (context == null) {
            C1425a.m6441b("Utility", "getMetaData context == null");
            return null;
        }
        ApplicationInfo applicationInfo;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return null;
            }
            applicationInfo = packageManager.getApplicationInfo(str, 128);
            return (applicationInfo == null || applicationInfo.metaData == null) ? null : applicationInfo.metaData.getString(str2);
        } catch (Exception e) {
            C1425a.m6439a("getMetaDataString", "--- " + str + " GetMetaData Exception:\r\n", e);
            applicationInfo = null;
        }
    }

    /* renamed from: a */
    public static String m7069a(String str) {
        if (!TextUtils.isDigitsOnly(str)) {
            return "0";
        }
        BigInteger bigInteger = new BigInteger(str);
        if (bigInteger.and(new BigInteger("0800000000000000", 16)).equals(BigInteger.ZERO)) {
            bigInteger = bigInteger.xor(new BigInteger("282335"));
            bigInteger = bigInteger.and(new BigInteger("00ff0000", 16)).shiftLeft(8).add(bigInteger.and(new BigInteger("000000ff", 16)).shiftLeft(16)).add(bigInteger.and(new BigInteger("ff000000", 16)).shiftRight(16).and(new BigInteger("0000ff00", 16))).add(bigInteger.and(new BigInteger("0000ff00", 16)).shiftRight(8));
        } else {
            System.out.println("encode =  1");
            bigInteger = bigInteger.xor(new BigInteger("22727017042830095"));
            bigInteger = bigInteger.and(new BigInteger("000000ff00000000", 16)).shiftLeft(16).add(bigInteger.and(new BigInteger("000000000000ffff", 16)).shiftLeft(32)).add(bigInteger.and(new BigInteger("00ffff0000000000", 16)).shiftRight(24).and(new BigInteger("00000000ffff0000", 16))).add(bigInteger.and(new BigInteger("00000000ffff0000", 16)).shiftRight(16)).add(bigInteger.and(new BigInteger("ff00000000000000", 16)));
        }
        return bigInteger.toString();
    }

    /* renamed from: a */
    public static String m7070a(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter, true);
        th.printStackTrace(printWriter);
        printWriter.flush();
        stringWriter.flush();
        return stringWriter.toString();
    }

    /* renamed from: a */
    public static void m7071a(Context context, int i, String str, String str2, String str3, short s) {
        String v = C1578v.m7149v(context);
        if (!TextUtils.isEmpty(v)) {
            Intent intent = new Intent();
            intent.setClassName(v, "com.baidu.android.pushservice.PushService");
            intent.setAction("com.baidu.android.pushservice.action.setadswitch.ADFAILED");
            intent.putExtra("ad_status", i);
            intent.putExtra("app_id", str);
            intent.putExtra("channel_id", str2);
            intent.putExtra("cuid", str3);
            intent.putExtra("sdkversion", s);
            C1425a.m6442c("Utility", "CLICK  intent  =" + intent.toString() + "  intent_extra = " + intent.getExtras());
            context.startService(intent);
        }
    }

    /* renamed from: a */
    public static void m7072a(Context context, long j) {
        C1425a.m6442c("Utility", ">>> setAlarmForRestart");
        Context applicationContext = context.getApplicationContext();
        Intent c = C1577u.m7050c(applicationContext);
        c.setClassName(applicationContext.getPackageName(), C1578v.m7110d(applicationContext, applicationContext.getPackageName(), c.getAction()));
        C1578v.m7073a(applicationContext, c, j);
    }

    /* renamed from: a */
    public static void m7073a(Context context, Intent intent, long j) {
        C1425a.m6442c("Utility", ">>> setAlarmForSendInent : \r\n" + intent);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent, 268435456);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        alarmManager.cancel(broadcast);
        alarmManager.set(3, SystemClock.elapsedRealtime() + j, broadcast);
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    public static void m7074a(Context context, C1512k c1512k, byte[] bArr) {
        int i = 0;
        C1437c c1437c = new C1437c();
        c1437c.f5057a = c1512k.mo14009g();
        c1437c.f5058b = c1512k.mo14001b();
        c1437c.f5059c = c1512k.mo14003c();
        c1437c.f5060d = c1512k.mo14005d();
        c1437c.f5061e = 1;
        c1437c.f5062f = 1;
        C1568q.m6985a(context, c1437c);
        try {
            i = Long.valueOf(System.currentTimeMillis()).intValue();
        } catch (Exception e) {
            C1425a.m6440a("Utility", e);
        }
        Intent b = C1578v.m7092b(context, new Intent(), "com.baidu.android.pushservice.action.alarm.message");
        b.putExtra("tinyMessageHead", c1512k);
        b.putExtra("msgBody", bArr);
        PendingIntent service = PendingIntent.getService(context, i, b, 134217728);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        try {
            if (VERSION.SDK_INT < 19) {
                alarmManager.set(0, c1437c.f5059c, service);
            } else if (VERSION.SDK_INT >= 19) {
                alarmManager.setExact(0, c1437c.f5059c, service);
            }
            C1425a.m6442c("Utility", "setMessageAlarm  showtime = " + c1437c.f5059c + " expiretime = " + c1437c.f5060d);
        } catch (Exception e2) {
            C1425a.m6440a("Utility", e2);
        }
    }

    /* renamed from: a */
    public static void m7075a(Context context, String str, String str2, String str3, String str4, int i, String str5) {
        String v = C1578v.m7149v(context);
        if (!TextUtils.isEmpty(v)) {
            Intent intent = new Intent();
            intent.setClassName(v, "com.baidu.android.pushservice.PushService");
            intent.setAction("com.baidu.android.pushservice.action.ADACKERROR");
            intent.putExtra("app_id", str);
            intent.putExtra("channel_id", str2);
            intent.putExtra("cuid", str3);
            intent.putExtra("ad_id", str4);
            intent.putExtra(NativeProtocol.BRIDGE_ARG_ERROR_CODE, i);
            intent.putExtra("error_msg", str5);
            C1425a.m6442c("Utility", "CLICK  intent  =" + intent.toString() + "  intent_extra = " + intent.getExtras());
            context.startService(intent);
        }
    }

    /* renamed from: a */
    public static void m7076a(Context context, boolean z, boolean z2) {
        C1426b.m6445a("Utility", context.getPackageName() + ": updateServiceInfo isForce = " + z + ",isSend = " + z2, context.getApplicationContext());
        SharedPreferences sharedPreferences = context.getSharedPreferences("pst", 0);
        int d = C1578v.m7108d(context, context.getPackageName());
        if (sharedPreferences.getInt("pr_app_v", 0) < d || z) {
            if (C1578v.m7105c(context) || ModeConfig.isProxyMode(context)) {
                C1574s.m7018a(context, 0);
            } else {
                C1574s.m7018a(context, C1578v.m7123i(context));
            }
            if (C1578v.m7056E(context)) {
                Editor edit = context.getSharedPreferences(context.getPackageName() + ".push_sync", 5).edit();
                if (C1578v.m7105c(context) || ModeConfig.isProxyMode(context)) {
                    edit.putLong("priority2", 0);
                } else {
                    edit.putLong("priority2", C1578v.m7123i(context));
                }
                edit.putInt("version2", C1328a.m6003a());
                edit.commit();
            }
            C1574s.m7021b(context, (long) C1328a.m6003a());
            Editor edit2 = sharedPreferences.edit();
            edit2.putInt("pr_app_v", d);
            edit2.commit();
        }
        if (z2) {
            C1577u.m7051d(context);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x0107 A:{SYNTHETIC, Splitter:B:40:0x0107} */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0115 A:{SYNTHETIC, Splitter:B:47:0x0115} */
    /* renamed from: a */
    private static synchronized void m7077a(java.lang.String r14, java.lang.String r15) {
        /*
        r3 = com.baidu.android.pushservice.util.C1578v.class;
        monitor-enter(r3);
        r1 = 0;
        r0 = new java.text.SimpleDateFormat;	 Catch:{ Throwable -> 0x00ff }
        r2 = "yyyy-MM-dd HH:mm:ss";
        r4 = java.util.Locale.getDefault();	 Catch:{ Throwable -> 0x00ff }
        r0.<init>(r2, r4);	 Catch:{ Throwable -> 0x00ff }
        r2 = new java.util.Date;	 Catch:{ Throwable -> 0x00ff }
        r2.<init>();	 Catch:{ Throwable -> 0x00ff }
        r4 = r0.format(r2);	 Catch:{ Throwable -> 0x00ff }
        r0 = new java.lang.String;	 Catch:{ Throwable -> 0x00ff }
        r0.<init>();	 Catch:{ Throwable -> 0x00ff }
        r2 = r4.length();	 Catch:{ Throwable -> 0x00ff }
        if (r2 <= 0) goto L_0x0128;
    L_0x0024:
        r0 = 0;
        r2 = 4;
        r0 = r4.substring(r0, r2);	 Catch:{ Throwable -> 0x00ff }
        r2 = 5;
        r5 = 7;
        r2 = r4.substring(r2, r5);	 Catch:{ Throwable -> 0x00ff }
        r0 = r0.concat(r2);	 Catch:{ Throwable -> 0x00ff }
        r2 = 8;
        r5 = 10;
        r2 = r4.substring(r2, r5);	 Catch:{ Throwable -> 0x00ff }
        r0 = r0.concat(r2);	 Catch:{ Throwable -> 0x00ff }
        r2 = r0;
    L_0x0041:
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00ff }
        r0.<init>();	 Catch:{ Throwable -> 0x00ff }
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x00ff }
        r4 = " ";
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x00ff }
        r0 = r0.append(r15);	 Catch:{ Throwable -> 0x00ff }
        r4 = "\n\r";
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x00ff }
        r4 = r0.toString();	 Catch:{ Throwable -> 0x00ff }
        r0 = android.os.Environment.getExternalStorageDirectory();	 Catch:{ Throwable -> 0x00ff }
        r5 = r0.getAbsolutePath();	 Catch:{ Throwable -> 0x00ff }
        r0 = new java.io.File;	 Catch:{ Throwable -> 0x00ff }
        r6 = "baidu/pushservice/files";
        r0.<init>(r5, r6);	 Catch:{ Throwable -> 0x00ff }
        r6 = r0.exists();	 Catch:{ Throwable -> 0x00ff }
        if (r6 != 0) goto L_0x00b7;
    L_0x0073:
        r0.mkdirs();	 Catch:{ Throwable -> 0x00ff }
    L_0x0076:
        r0 = new java.io.File;	 Catch:{ Throwable -> 0x00ff }
        r6 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00ff }
        r6.<init>();	 Catch:{ Throwable -> 0x00ff }
        r7 = "baidu/pushservice/files/";
        r6 = r6.append(r7);	 Catch:{ Throwable -> 0x00ff }
        r6 = r6.append(r14);	 Catch:{ Throwable -> 0x00ff }
        r2 = r6.append(r2);	 Catch:{ Throwable -> 0x00ff }
        r6 = ".log";
        r2 = r2.append(r6);	 Catch:{ Throwable -> 0x00ff }
        r2 = r2.toString();	 Catch:{ Throwable -> 0x00ff }
        r0.<init>(r5, r2);	 Catch:{ Throwable -> 0x00ff }
        r2 = r0.exists();	 Catch:{ Throwable -> 0x00ff }
        if (r2 == 0) goto L_0x0126;
    L_0x009e:
        r2 = new java.io.FileOutputStream;	 Catch:{ Throwable -> 0x00ff }
        r5 = 1;
        r2.<init>(r0, r5);	 Catch:{ Throwable -> 0x00ff }
        if (r2 == 0) goto L_0x00b0;
    L_0x00a6:
        r0 = r4.getBytes();	 Catch:{ Throwable -> 0x0123, all -> 0x0120 }
        r2.write(r0);	 Catch:{ Throwable -> 0x0123, all -> 0x0120 }
        r2.close();	 Catch:{ Throwable -> 0x0123, all -> 0x0120 }
    L_0x00b0:
        if (r2 == 0) goto L_0x00b5;
    L_0x00b2:
        r2.close();	 Catch:{ IOException -> 0x00f5 }
    L_0x00b5:
        monitor-exit(r3);
        return;
    L_0x00b7:
        r6 = new java.text.SimpleDateFormat;	 Catch:{ Throwable -> 0x00ff }
        r7 = "yyyyMMdd";
        r8 = java.util.Locale.getDefault();	 Catch:{ Throwable -> 0x00ff }
        r6.<init>(r7, r8);	 Catch:{ Throwable -> 0x00ff }
        r7 = r0.listFiles();	 Catch:{ Throwable -> 0x00ff }
        r8 = r7.length;	 Catch:{ Throwable -> 0x00ff }
        r0 = 0;
    L_0x00c9:
        if (r0 >= r8) goto L_0x0076;
    L_0x00cb:
        r9 = r7[r0];	 Catch:{ Throwable -> 0x00ff }
        r10 = r9.getName();	 Catch:{ Throwable -> 0x00ff }
        r10 = r10.startsWith(r14);	 Catch:{ Throwable -> 0x00ff }
        if (r10 == 0) goto L_0x00f2;
    L_0x00d7:
        r10 = java.lang.Integer.parseInt(r2);	 Catch:{ Throwable -> 0x00ff }
        r12 = r9.lastModified();	 Catch:{ Throwable -> 0x00ff }
        r11 = java.lang.Long.valueOf(r12);	 Catch:{ Throwable -> 0x00ff }
        r11 = r6.format(r11);	 Catch:{ Throwable -> 0x00ff }
        r11 = java.lang.Integer.parseInt(r11);	 Catch:{ Throwable -> 0x00ff }
        r10 = r10 - r11;
        r11 = 7;
        if (r10 < r11) goto L_0x00f2;
    L_0x00ef:
        r9.delete();	 Catch:{ Throwable -> 0x00ff }
    L_0x00f2:
        r0 = r0 + 1;
        goto L_0x00c9;
    L_0x00f5:
        r0 = move-exception;
        r1 = "Utility";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r1, r0);	 Catch:{ all -> 0x00fc }
        goto L_0x00b5;
    L_0x00fc:
        r0 = move-exception;
        monitor-exit(r3);
        throw r0;
    L_0x00ff:
        r0 = move-exception;
    L_0x0100:
        r2 = "Utility";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r0);	 Catch:{ all -> 0x0112 }
        if (r1 == 0) goto L_0x00b5;
    L_0x0107:
        r1.close();	 Catch:{ IOException -> 0x010b }
        goto L_0x00b5;
    L_0x010b:
        r0 = move-exception;
        r1 = "Utility";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r1, r0);	 Catch:{ all -> 0x00fc }
        goto L_0x00b5;
    L_0x0112:
        r0 = move-exception;
    L_0x0113:
        if (r1 == 0) goto L_0x0118;
    L_0x0115:
        r1.close();	 Catch:{ IOException -> 0x0119 }
    L_0x0118:
        throw r0;	 Catch:{ all -> 0x00fc }
    L_0x0119:
        r1 = move-exception;
        r2 = "Utility";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);	 Catch:{ all -> 0x00fc }
        goto L_0x0118;
    L_0x0120:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0113;
    L_0x0123:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0100;
    L_0x0126:
        r2 = r1;
        goto L_0x00b0;
    L_0x0128:
        r2 = r0;
        goto L_0x0041;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1578v.m7077a(java.lang.String, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x004f A:{SYNTHETIC, Splitter:B:25:0x004f} */
    /* JADX WARNING: Removed duplicated region for block: B:59:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0054 A:{SYNTHETIC, Splitter:B:28:0x0054} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x006a A:{SYNTHETIC, Splitter:B:37:0x006a} */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x006f A:{SYNTHETIC, Splitter:B:40:0x006f} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x006a A:{SYNTHETIC, Splitter:B:37:0x006a} */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x006f A:{SYNTHETIC, Splitter:B:40:0x006f} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004f A:{SYNTHETIC, Splitter:B:25:0x004f} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0054 A:{SYNTHETIC, Splitter:B:28:0x0054} */
    /* JADX WARNING: Removed duplicated region for block: B:59:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x006a A:{SYNTHETIC, Splitter:B:37:0x006a} */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x006f A:{SYNTHETIC, Splitter:B:40:0x006f} */
    /* renamed from: a */
    public static void m7078a(java.util.HashMap<java.lang.String, java.lang.Integer> r4) {
        /*
        r1 = 0;
        r0 = android.os.Environment.getExternalStorageDirectory();	 Catch:{ Exception -> 0x0046, all -> 0x0066 }
        r0 = r0.getAbsolutePath();	 Catch:{ Exception -> 0x0046, all -> 0x0066 }
        r2 = new java.io.File;	 Catch:{ Exception -> 0x0046, all -> 0x0066 }
        r3 = "baidu/hybrid";
        r2.<init>(r0, r3);	 Catch:{ Exception -> 0x0046, all -> 0x0066 }
        r0 = r2.exists();	 Catch:{ Exception -> 0x0046, all -> 0x0066 }
        if (r0 != 0) goto L_0x0019;
    L_0x0016:
        r2.mkdirs();	 Catch:{ Exception -> 0x0046, all -> 0x0066 }
    L_0x0019:
        r0 = new java.io.File;	 Catch:{ Exception -> 0x0046, all -> 0x0066 }
        r3 = "notimap";
        r0.<init>(r2, r3);	 Catch:{ Exception -> 0x0046, all -> 0x0066 }
        r3 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x0046, all -> 0x0066 }
        r3.<init>(r0);	 Catch:{ Exception -> 0x0046, all -> 0x0066 }
        r2 = new java.io.ObjectOutputStream;	 Catch:{ Exception -> 0x0089, all -> 0x0081 }
        r2.<init>(r3);	 Catch:{ Exception -> 0x0089, all -> 0x0081 }
        r2.writeObject(r4);	 Catch:{ Exception -> 0x008c, all -> 0x0083 }
        if (r2 == 0) goto L_0x0032;
    L_0x002f:
        r2.close();	 Catch:{ Exception -> 0x0038 }
    L_0x0032:
        if (r3 == 0) goto L_0x0037;
    L_0x0034:
        r3.close();	 Catch:{ Exception -> 0x003f }
    L_0x0037:
        return;
    L_0x0038:
        r0 = move-exception;
        r1 = "Utility";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r1, r0);
        goto L_0x0032;
    L_0x003f:
        r0 = move-exception;
        r1 = "Utility";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r1, r0);
        goto L_0x0037;
    L_0x0046:
        r0 = move-exception;
        r2 = r1;
    L_0x0048:
        r3 = "Utility";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r3, r0);	 Catch:{ all -> 0x0086 }
        if (r1 == 0) goto L_0x0052;
    L_0x004f:
        r1.close();	 Catch:{ Exception -> 0x005f }
    L_0x0052:
        if (r2 == 0) goto L_0x0037;
    L_0x0054:
        r2.close();	 Catch:{ Exception -> 0x0058 }
        goto L_0x0037;
    L_0x0058:
        r0 = move-exception;
        r1 = "Utility";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r1, r0);
        goto L_0x0037;
    L_0x005f:
        r0 = move-exception;
        r1 = "Utility";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r1, r0);
        goto L_0x0052;
    L_0x0066:
        r0 = move-exception;
        r3 = r1;
    L_0x0068:
        if (r1 == 0) goto L_0x006d;
    L_0x006a:
        r1.close();	 Catch:{ Exception -> 0x0073 }
    L_0x006d:
        if (r3 == 0) goto L_0x0072;
    L_0x006f:
        r3.close();	 Catch:{ Exception -> 0x007a }
    L_0x0072:
        throw r0;
    L_0x0073:
        r1 = move-exception;
        r2 = "Utility";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x006d;
    L_0x007a:
        r1 = move-exception;
        r2 = "Utility";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x0072;
    L_0x0081:
        r0 = move-exception;
        goto L_0x0068;
    L_0x0083:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0068;
    L_0x0086:
        r0 = move-exception;
        r3 = r2;
        goto L_0x0068;
    L_0x0089:
        r0 = move-exception;
        r2 = r3;
        goto L_0x0048;
    L_0x008c:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
        goto L_0x0048;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1578v.m7078a(java.util.HashMap):void");
    }

    /* renamed from: a */
    public static boolean m7079a() {
        boolean z = false;
        try {
            return "mounted".equals(Environment.getExternalStorageState());
        } catch (Exception e) {
            C1425a.m6440a("Utility", e);
            return z;
        }
    }

    /* renamed from: a */
    private static boolean m7080a(int i, int i2, int i3, int i4) {
        int i5 = Calendar.getInstance(Locale.CHINA).get(11);
        int i6 = Calendar.getInstance(Locale.CHINA).get(12);
        if (i < i3) {
            if (i < i5 && i5 < i3) {
                return true;
            }
            if (i5 == i && i6 >= i2) {
                return true;
            }
            if (i5 == i3 && i6 <= i4) {
                return true;
            }
        } else if (i > i3) {
            if ((i5 > i && i5 < 24) || i5 < i3) {
                return true;
            }
            if (i5 == i && i6 >= i2) {
                return true;
            }
            if (i5 == i3 && i6 <= i4) {
                return true;
            }
        } else if (i == i5 && i6 >= i2 && i4 >= i6) {
            return true;
        }
        return false;
    }

    @SuppressLint({"SdCardPath"})
    /* renamed from: a */
    public static boolean m7081a(Context context) {
        File file = new File("/data/data/root");
        try {
            file.createNewFile();
            if (!file.exists()) {
                return true;
            }
            file.delete();
            return true;
        } catch (IOException e) {
            return (C1578v.m7065a(context, "com.noshufou.android.su") == null && C1578v.m7065a(context, "com.miui.uac") == null) ? false : true;
        }
    }

    /* renamed from: a */
    private static boolean m7082a(Context context, PackageManager packageManager, String[] strArr) {
        boolean z = C1578v.m7112d() && PushSettings.m5894m(context);
        if (z) {
            try {
                String str = context.getPackageName() + ".permission.MIPUSH_RECEIVE";
                if (C1578v.m7088a(str, strArr)) {
                    PermissionInfo[] permissionInfoArr = packageManager.getPackageInfo(context.getPackageName(), 4096).permissions;
                    if (permissionInfoArr != null && permissionInfoArr.length > 0) {
                        z = false;
                        for (PermissionInfo permissionInfo : permissionInfoArr) {
                            if (permissionInfo.name.equals(str) && permissionInfo.protectionLevel == 2) {
                                z = true;
                            }
                        }
                        if (!z) {
                            return true;
                        }
                    }
                }
                C1426b.m6447b("Utility", "the permission [ " + str + " ] for " + "xiaomi proxy need is not exist, please check!", context);
                return true;
            } catch (Exception e) {
                C1425a.m6440a("Utility", e);
            }
        }
        return false;
    }

    /* renamed from: a */
    private static boolean m7083a(Context context, String str, String str2, boolean z) {
        Intent intent = new Intent(str);
        intent.setPackage(context.getPackageName());
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            List<ResolveInfo> queryBroadcastReceivers;
            if (z) {
                queryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 544);
                if (queryBroadcastReceivers.size() < 1) {
                    return false;
                }
                for (ResolveInfo resolveInfo : queryBroadcastReceivers) {
                    if (str2.equals(resolveInfo.activityInfo.name)) {
                        return true;
                    }
                }
            }
            queryBroadcastReceivers = packageManager.queryIntentServices(intent, 544);
            if (queryBroadcastReceivers.size() < 1) {
                return false;
            }
            for (ResolveInfo resolveInfo2 : queryBroadcastReceivers) {
                if (str2.equals(resolveInfo2.serviceInfo.name)) {
                    int x = C1578v.m7153x(context, str2);
                    if (!resolveInfo2.serviceInfo.exported) {
                        return false;
                    }
                    if (x == 1 || (x == 0 && resolveInfo2.serviceInfo.enabled)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            C1426b.m6447b("Utility", "error  " + e.getMessage(), context);
        }
    }

    /* renamed from: a */
    public static boolean m7084a(Context context, String str, boolean z) {
        if (!PushSocket.m6672a(context)) {
            Log.e("BDPushSDK-Utility", "check socket library failed");
            C1425a.m6444e("Utility", "check socket library failed");
            return false;
        } else if (!C1578v.m7098b(context, z)) {
            Log.e("BDPushSDK-Utility", "check SDK AndroidManifest failed");
            C1425a.m6444e("Utility", "check SDK AndroidManifest failed");
            return false;
        } else if (!C1578v.m7087a(str, context)) {
            Log.e("BDPushSDK-Utility", "check Apikey failed");
            C1425a.m6444e("Utility", "check Apikey failed");
            return false;
        } else if (!C1578v.m7130l(context)) {
            Log.e("BDPushSDK-Utility", "check SelfConfiged Receiver failed");
            C1425a.m6444e("Utility", "check SelfConfiged Receiver failed");
            return false;
        } else if (C1578v.m7131m(context)) {
            if (!C1578v.m7059H(context)) {
                Log.e("BDPushSDK-Utility", "check PushInfoProvider Enable failed");
                return false;
            } else if (!z || C1578v.m7060I(context)) {
                return true;
            } else {
                Log.e("BDPushSDK-Utility", "check PushADProvider Enable failed");
                return false;
            }
        } else if (C1578v.m7062K(context)) {
            return true;
        } else {
            Log.e("BDPushSDK-Utility", "check CommandService Enable failed");
            C1425a.m6444e("Utility", "check CommandService Enable failed");
            return false;
        }
    }

    /* renamed from: a */
    static boolean m7085a(Context context, boolean z) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            String[] strArr = packageManager.getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            if (strArr == null) {
                Log.e("BDPushSDK-Utility", "Permissions Push-SDK need are not exist !");
                return false;
            }
            if (z) {
                if (!C1578v.m7088a("android.permission.EXPAND_STATUS_BAR", strArr)) {
                    Log.e("BDPushSDK-Utility", "android.permission.EXPAND_STATUS_BAR permission Push-SDK  EXPAND_STATUS_BAR need is not exist !");
                    return false;
                }
            }
            if (C1578v.m7082a(context, packageManager, strArr)) {
                Log.e("BDPushSDK-Utility", " permission Push-SDK for xiaomi proxy need is not exist !");
                return false;
            }
            int i = 0;
            while (i < f5539a.length) {
                if (C1578v.m7088a(f5539a[i], strArr)) {
                    i++;
                } else {
                    Log.e("BDPushSDK-Utility", f5539a[i] + " permission Push-SDK need is not exist !");
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            C1425a.m6440a("Utility", e);
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m7086a(Context context, byte[] bArr, String str, String str2, byte[] bArr2) {
        try {
            CharSequence b = PushSettings.m5878b(context);
            if (TextUtils.isEmpty(b)) {
                C1339h d = C1332b.m6020a(context).mo13600d(str);
                if (d != null) {
                    b = d.mo13584a();
                }
            }
            if (TextUtils.isEmpty(b) || !b.equals(str)) {
                C1426b.m6445a("Utility", "walnutShellVerify error, appId not equal: " + str + "  stored: " + b, context);
                return false;
            }
            byte[] a = C1473g.m6717a(bArr, "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAtadv7/MPKp+9Djta\r/DIEt15755s3h1KYA4Lbej2GL2Mx0mdk4wzmjMCzfvNh+v4R7/mF8kfN8Kzowuaa\rFjAzfwIDAQABAkBVYQxguFoxi4DddpJuJMhIs3UDR9YSmYRvagrkapRuIqJmj3hO\rk+EsDQUtNA7JYJdiv/hrPrH0UACDV/Whb1MJAiEA8Rw37/dC157fsxasiTDz9bMQ\reAq9F8GudeH8oT5j8r0CIQDA30JBzOmu7CpPWbsTFh9YuL9wujJdiAdcBVHqmmfg\r6wIhAJbQIMkPr5axgJlDqH5TyXU5IScFCIwwkNCZn2y4Wso9AiBmMydhxJojFYNJ\r7stBTtynX6YZrqBXjWgQ68S/YrgepwIgdIQpvO4xNCT1j/mGIRcM/dqTGwiPOi/x\r/YLmfF2zQkM=\r");
            String a2 = C1472f.m6716a(C1578v.m7090a(C1578v.m7090a(str.getBytes(), str2.getBytes()), bArr2), false);
            String str3 = new String(a);
            return (str3 == null || a2 == null || !a2.equals(str3)) ? false : true;
        } catch (Exception e) {
            C1425a.m6440a("Utility", e);
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m7087a(String str, Context context) {
        if (!TextUtils.isEmpty(str) && !str.contains(" ")) {
            return true;
        }
        Log.e("BDPushSDK-Utility", "api_key incorrect ");
        return false;
    }

    /* renamed from: a */
    static boolean m7088a(String str, String[] strArr) {
        for (Object equals : strArr) {
            if (str.equals(equals)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public static byte[] m7089a(Context context, String str, byte[] bArr, byte[] bArr2, String str2) {
        int k = C1578v.m7127k(context, str2);
        C1425a.m6442c("Utility", " handleSecureInfo getIntergratedPushVersion from: " + str2 + " : " + k + " msgid: " + str);
        if (k <= 45 || k >= 50) {
            return bArr2;
        }
        try {
            return C1473g.m6719b(C1472f.m6716a(C1578v.m7090a(str.getBytes(), bArr), false).getBytes(), "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAMPGuiFnHoDFak4nw1ipCr6EiCA2gSBJ\rtUKSfL41Goz+h4oX2Fs6uNvc0XNPlowZw1Np1AFKGwRgVLuLvot6XnkCAwEAAQ==");
        } catch (Exception e) {
            C1425a.m6440a("Utility", e);
            return bArr2;
        }
    }

    /* renamed from: a */
    public static byte[] m7090a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    /* renamed from: b */
    public static int m7091b(String str) {
        int i = 0;
        try {
            return (int) Long.parseLong(str);
        } catch (Exception e) {
            try {
                if (str.length() > 0) {
                    str = str.substring(1);
                }
                return (int) Long.parseLong(str);
            } catch (Exception e2) {
                C1425a.m6443d("Utility", "exception " + e2.getMessage());
                return i;
            }
        }
    }

    /* renamed from: b */
    public static Intent m7092b(Context context, Intent intent, String str) {
        if (C1328a.m6003a() >= (short) 32) {
            intent.setFlags(32);
            intent.setAction(str);
            intent.setClassName(context.getPackageName(), "com.baidu.android.pushservice.PushService");
        }
        return intent;
    }

    /* JADX WARNING: Removed duplicated region for block: B:62:0x00a8 A:{SYNTHETIC, Splitter:B:62:0x00a8} */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00ad A:{SYNTHETIC, Splitter:B:65:0x00ad} */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x008b A:{SYNTHETIC, Splitter:B:49:0x008b} */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0090 A:{SYNTHETIC, Splitter:B:52:0x0090} */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00a8 A:{SYNTHETIC, Splitter:B:62:0x00a8} */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00ad A:{SYNTHETIC, Splitter:B:65:0x00ad} */
    /* renamed from: b */
    public static java.util.HashMap<java.lang.String, java.lang.Integer> m7093b() {
        /*
        r1 = 0;
        r0 = 0;
        r2 = 0;
        r3 = android.os.Environment.getExternalStorageDirectory();	 Catch:{ Exception -> 0x0081, all -> 0x00a4 }
        r3 = r3.getAbsolutePath();	 Catch:{ Exception -> 0x0081, all -> 0x00a4 }
        r4 = new java.io.File;	 Catch:{ Exception -> 0x0081, all -> 0x00a4 }
        r5 = "baidu/hybrid";
        r4.<init>(r3, r5);	 Catch:{ Exception -> 0x0081, all -> 0x00a4 }
        r3 = r4.exists();	 Catch:{ Exception -> 0x0081, all -> 0x00a4 }
        if (r3 != 0) goto L_0x0031;
    L_0x0018:
        if (r1 == 0) goto L_0x001d;
    L_0x001a:
        r2.close();	 Catch:{ Exception -> 0x0023 }
    L_0x001d:
        if (r1 == 0) goto L_0x0022;
    L_0x001f:
        r0.close();	 Catch:{ Exception -> 0x002a }
    L_0x0022:
        return r1;
    L_0x0023:
        r2 = move-exception;
        r3 = "Utility";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r3, r2);
        goto L_0x001d;
    L_0x002a:
        r0 = move-exception;
        r2 = "Utility";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r0);
        goto L_0x0022;
    L_0x0031:
        r5 = new java.io.File;	 Catch:{ Exception -> 0x0081, all -> 0x00a4 }
        r3 = "notimap";
        r5.<init>(r4, r3);	 Catch:{ Exception -> 0x0081, all -> 0x00a4 }
        r3 = r5.exists();	 Catch:{ Exception -> 0x0081, all -> 0x00a4 }
        if (r3 != 0) goto L_0x0057;
    L_0x003e:
        if (r1 == 0) goto L_0x0043;
    L_0x0040:
        r2.close();	 Catch:{ Exception -> 0x0050 }
    L_0x0043:
        if (r1 == 0) goto L_0x0022;
    L_0x0045:
        r0.close();	 Catch:{ Exception -> 0x0049 }
        goto L_0x0022;
    L_0x0049:
        r0 = move-exception;
        r2 = "Utility";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r0);
        goto L_0x0022;
    L_0x0050:
        r2 = move-exception;
        r3 = "Utility";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r3, r2);
        goto L_0x0043;
    L_0x0057:
        r3 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x0081, all -> 0x00a4 }
        r3.<init>(r5);	 Catch:{ Exception -> 0x0081, all -> 0x00a4 }
        r2 = new java.io.ObjectInputStream;	 Catch:{ Exception -> 0x00c4, all -> 0x00bf }
        r2.<init>(r3);	 Catch:{ Exception -> 0x00c4, all -> 0x00bf }
        r0 = r2.readObject();	 Catch:{ Exception -> 0x00c7 }
        r0 = (java.util.HashMap) r0;	 Catch:{ Exception -> 0x00c7 }
        if (r2 == 0) goto L_0x006c;
    L_0x0069:
        r2.close();	 Catch:{ Exception -> 0x0073 }
    L_0x006c:
        if (r3 == 0) goto L_0x0071;
    L_0x006e:
        r3.close();	 Catch:{ Exception -> 0x007a }
    L_0x0071:
        r1 = r0;
        goto L_0x0022;
    L_0x0073:
        r1 = move-exception;
        r2 = "Utility";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x006c;
    L_0x007a:
        r1 = move-exception;
        r2 = "Utility";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x0071;
    L_0x0081:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
    L_0x0084:
        r4 = "Utility";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r4, r0);	 Catch:{ all -> 0x00c1 }
        if (r2 == 0) goto L_0x008e;
    L_0x008b:
        r2.close();	 Catch:{ Exception -> 0x0095 }
    L_0x008e:
        if (r3 == 0) goto L_0x00c9;
    L_0x0090:
        r3.close();	 Catch:{ Exception -> 0x009c }
        r0 = r1;
        goto L_0x0071;
    L_0x0095:
        r0 = move-exception;
        r2 = "Utility";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r0);
        goto L_0x008e;
    L_0x009c:
        r0 = move-exception;
        r2 = "Utility";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r0);
        r0 = r1;
        goto L_0x0071;
    L_0x00a4:
        r0 = move-exception;
        r3 = r1;
    L_0x00a6:
        if (r1 == 0) goto L_0x00ab;
    L_0x00a8:
        r1.close();	 Catch:{ Exception -> 0x00b1 }
    L_0x00ab:
        if (r3 == 0) goto L_0x00b0;
    L_0x00ad:
        r3.close();	 Catch:{ Exception -> 0x00b8 }
    L_0x00b0:
        throw r0;
    L_0x00b1:
        r1 = move-exception;
        r2 = "Utility";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x00ab;
    L_0x00b8:
        r1 = move-exception;
        r2 = "Utility";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x00b0;
    L_0x00bf:
        r0 = move-exception;
        goto L_0x00a6;
    L_0x00c1:
        r0 = move-exception;
        r1 = r2;
        goto L_0x00a6;
    L_0x00c4:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0084;
    L_0x00c7:
        r0 = move-exception;
        goto L_0x0084;
    L_0x00c9:
        r0 = r1;
        goto L_0x0071;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1578v.m7093b():java.util.HashMap");
    }

    /* renamed from: b */
    public static void m7094b(Context context, Intent intent, String str, String str2) {
        intent.setFlags(32);
        int k = C1578v.m7127k(context, str2);
        if (k >= 32) {
            if (!TextUtils.isEmpty(str)) {
                intent.setAction(str);
            }
            if (!TextUtils.isEmpty(str2)) {
                intent.setPackage(str2);
                intent.setClassName(str2, "com.baidu.android.pushservice.CommandService");
            }
            intent.putExtra("command_type", "reflect_receiver");
            try {
                if (context.startService(intent) == null) {
                    C1578v.m7095b("sendRedirecctionIntent#intergratedPushVersion=" + k + ", packageName=" + str2 + ", intent=" + intent.toUri(0), context);
                } else {
                    return;
                }
            } catch (Exception e) {
                C1425a.m6440a("Utility", e);
            }
        }
        if (!TextUtils.isEmpty(str)) {
            intent.setAction(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            intent.setPackage(str2);
        }
        String d = C1578v.m7110d(context, str2, str);
        if (!TextUtils.isEmpty(d)) {
            intent.setClassName(str2, d);
        }
        context.sendBroadcast(intent);
    }

    /* renamed from: b */
    public static synchronized void m7095b(String str, Context context) {
        synchronized (C1578v.class) {
            if (C1578v.m7061J(context)) {
                if (C1328a.m6006b() > 0) {
                    C1578v.m7104c(str, context);
                } else if (C1328a.m6005a(context)) {
                    try {
                        String str2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " " + str + "\n\r";
                        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
                        File file = new File(absolutePath, "baidu/pushservice/files");
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                        Date date = new Date();
                        File file2 = new File(absolutePath, "baidu/pushservice/files/msg" + simpleDateFormat.format(date) + ".log");
                        if (!file2.exists()) {
                            for (File file3 : file.listFiles()) {
                                String name = file3.getName();
                                if (name.startsWith("msg") && name.length() > 0 && ((int) Math.abs((simpleDateFormat.parse(name.substring(3, 11)).getTime() - date.getTime()) / 86400000)) >= 7) {
                                    file3.delete();
                                }
                            }
                            file2.createNewFile();
                        }
                        if (file2.exists()) {
                            FileWriter fileWriter = new FileWriter(file2, true);
                            if (fileWriter != null) {
                                fileWriter.write(str2);
                                fileWriter.close();
                            }
                        }
                    } catch (Throwable th) {
                        C1425a.m6440a("Utility", th);
                    }
                }
            }
        }
        return;
    }

    /* renamed from: b */
    public static boolean m7096b(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return (packageManager == null || (packageManager.getApplicationInfo(str, 0).flags & 1) == 0) ? false : true;
        } catch (Exception e) {
            C1425a.m6440a("Utility", e);
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m7097b(Context context, String str, String str2) {
        ApplicationInfo applicationInfo;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            applicationInfo = packageManager.getApplicationInfo(str, 128);
            return (applicationInfo == null || applicationInfo.metaData == null || !applicationInfo.metaData.containsKey(str2)) ? false : applicationInfo.metaData.getBoolean(str2);
        } catch (Exception e) {
            C1425a.m6439a("getMetaDataBoolean", "--- " + str + " GetMetaData Exception:\r\n", e);
            applicationInfo = null;
        }
    }

    /* renamed from: b */
    public static boolean m7098b(Context context, boolean z) {
        C1425a.m6442c("Utility", "check PushService AndroidManifest declearation !");
        return (C1578v.m7085a(context, z) && C1578v.m7107c(context, z)) ? !C1578v.m7057F(context) ? C1578v.m7062K(context) : true : false;
    }

    /* renamed from: b */
    public static int[] m7099b(Context context) {
        int[] iArr = new int[2];
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        iArr[0] = displayMetrics.heightPixels;
        iArr[1] = displayMetrics.widthPixels;
        return iArr;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x009d  */
    /* renamed from: c */
    private static int m7100c(java.lang.String r10) {
        /*
        r4 = 0;
        r9 = 2;
        r2 = 1;
        r0 = 0;
        r1 = java.lang.Runtime.getRuntime();	 Catch:{ Exception -> 0x0077, all -> 0x008f }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0077, all -> 0x008f }
        r3.<init>();	 Catch:{ Exception -> 0x0077, all -> 0x008f }
        r5 = "/system/bin/ping -w 1 ";
        r3 = r3.append(r5);	 Catch:{ Exception -> 0x0077, all -> 0x008f }
        r3 = r3.append(r10);	 Catch:{ Exception -> 0x0077, all -> 0x008f }
        r3 = r3.toString();	 Catch:{ Exception -> 0x0077, all -> 0x008f }
        r6 = r1.exec(r3);	 Catch:{ Exception -> 0x0077, all -> 0x008f }
        r3 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x00ac, all -> 0x00a1 }
        r1 = r6.getInputStream();	 Catch:{ Exception -> 0x00ac, all -> 0x00a1 }
        r3.<init>(r1);	 Catch:{ Exception -> 0x00ac, all -> 0x00a1 }
        r5 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x00b0, all -> 0x00a4 }
        r5.<init>(r3);	 Catch:{ Exception -> 0x00b0, all -> 0x00a4 }
        r1 = new java.lang.String;	 Catch:{ Exception -> 0x00b3, all -> 0x00a6 }
        r1.<init>();	 Catch:{ Exception -> 0x00b3, all -> 0x00a6 }
        r1 = r0;
    L_0x0033:
        r4 = r5.readLine();	 Catch:{ Exception -> 0x00b3, all -> 0x00a6 }
        if (r4 == 0) goto L_0x0066;
    L_0x0039:
        r1 = r1 + 1;
        r7 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00b3, all -> 0x00a6 }
        r7.<init>();	 Catch:{ Exception -> 0x00b3, all -> 0x00a6 }
        r8 = "64 bytes from ";
        r7 = r7.append(r8);	 Catch:{ Exception -> 0x00b3, all -> 0x00a6 }
        r7 = r7.append(r10);	 Catch:{ Exception -> 0x00b3, all -> 0x00a6 }
        r7 = r7.toString();	 Catch:{ Exception -> 0x00b3, all -> 0x00a6 }
        r4 = r4.contains(r7);	 Catch:{ Exception -> 0x00b3, all -> 0x00a6 }
        if (r4 == 0) goto L_0x0063;
    L_0x0054:
        r1 = new java.io.Closeable[r9];
        r1[r0] = r5;
        r1[r2] = r3;
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r1);
        if (r6 == 0) goto L_0x0062;
    L_0x005f:
        r6.destroy();
    L_0x0062:
        return r0;
    L_0x0063:
        r4 = 3;
        if (r1 <= r4) goto L_0x0033;
    L_0x0066:
        r1 = -1;
        r4 = new java.io.Closeable[r9];
        r4[r0] = r5;
        r4[r2] = r3;
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r4);
        if (r6 == 0) goto L_0x0075;
    L_0x0072:
        r6.destroy();
    L_0x0075:
        r0 = r1;
        goto L_0x0062;
    L_0x0077:
        r1 = move-exception;
        r3 = r4;
        r5 = r4;
    L_0x007a:
        r6 = "Utility";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r6, r1);	 Catch:{ all -> 0x00a9 }
        r1 = new java.io.Closeable[r9];
        r1[r0] = r4;
        r1[r2] = r3;
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r1);
        if (r5 == 0) goto L_0x008d;
    L_0x008a:
        r5.destroy();
    L_0x008d:
        r0 = r2;
        goto L_0x0062;
    L_0x008f:
        r1 = move-exception;
        r3 = r4;
        r6 = r4;
    L_0x0092:
        r5 = new java.io.Closeable[r9];
        r5[r0] = r4;
        r5[r2] = r3;
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r5);
        if (r6 == 0) goto L_0x00a0;
    L_0x009d:
        r6.destroy();
    L_0x00a0:
        throw r1;
    L_0x00a1:
        r1 = move-exception;
        r3 = r4;
        goto L_0x0092;
    L_0x00a4:
        r1 = move-exception;
        goto L_0x0092;
    L_0x00a6:
        r1 = move-exception;
        r4 = r5;
        goto L_0x0092;
    L_0x00a9:
        r1 = move-exception;
        r6 = r5;
        goto L_0x0092;
    L_0x00ac:
        r1 = move-exception;
        r3 = r4;
        r5 = r6;
        goto L_0x007a;
    L_0x00b0:
        r1 = move-exception;
        r5 = r6;
        goto L_0x007a;
    L_0x00b3:
        r1 = move-exception;
        r4 = r5;
        r5 = r6;
        goto L_0x007a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1578v.m7100c(java.lang.String):int");
    }

    /* renamed from: c */
    public static long m7101c() {
        long j = 0;
        try {
            Calendar instance = Calendar.getInstance();
            instance.set(11, 0);
            instance.set(12, 0);
            instance.set(13, 0);
            instance.set(14, 0);
            return instance.getTime().getTime();
        } catch (Exception e) {
            C1425a.m6440a("Utility", e);
            return j;
        }
    }

    /* renamed from: c */
    public static ComponentName m7102c(Context context, Intent intent, String str, String str2) {
        intent.setFlags(32);
        ComponentName componentName = null;
        if (C1578v.m7127k(context, str2) < 36) {
            return componentName;
        }
        if (!TextUtils.isEmpty(str)) {
            intent.setAction(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            intent.setClassName(str2, "com.baidu.android.pushservice.CommandService");
        }
        intent.putExtra("command_type", "reflect_receiver");
        try {
            return context.startService(intent);
        } catch (Exception e) {
            C1425a.m6440a("Utility", e);
            return componentName;
        }
    }

    /* renamed from: c */
    public static String m7103c(Context context, String str, String str2) {
        String a = C1471e.m6687a(context);
        if (TextUtils.isEmpty(a) || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(a).append("#");
        stringBuilder.append(str2).append("#");
        stringBuilder.append(str);
        String stringBuilder2 = stringBuilder.toString();
        if (!TextUtils.isEmpty(stringBuilder2)) {
            try {
                a = new String(C1465b.m6678a(stringBuilder2.getBytes(), "utf-8"));
            } catch (Exception e) {
                C1425a.m6440a("Utility", e);
            }
            a = URLEncoder.encode(a, "utf-8");
            C1425a.m6442c("Utility", "+++++++++++++++++++++ push token is " + a);
            return a;
        }
        a = stringBuilder2;
        try {
            a = URLEncoder.encode(a, "utf-8");
            C1425a.m6442c("Utility", "+++++++++++++++++++++ push token is " + a);
            return a;
        } catch (UnsupportedEncodingException e2) {
            C1425a.m6440a("Utility", e2);
            return "";
        }
    }

    /* renamed from: c */
    private static synchronized void m7104c(String str, Context context) {
        synchronized (C1578v.class) {
            String str2 = "samonitor";
            if (context != null) {
                String str3 = str2 + C1471e.m6699b(context);
                String s = C1578v.m7144s(context);
                int t = C1578v.m7145t(context);
                if (!f5541c) {
                    ArrayList q = C1578v.m7139q(context);
                    StringBuffer stringBuffer = new StringBuffer();
                    Iterator it = q.iterator();
                    while (it.hasNext()) {
                        stringBuffer.append(((String) it.next()) + ";");
                    }
                    C1578v.m7077a(str3, "#AllPackagesUsingPush:" + stringBuffer.toString());
                    f5541c = true;
                }
                C1578v.m7077a(str3, "#IMEI:" + null + "#networkType:" + s + "#mobileType:" + t + "#" + str);
            }
        }
    }

    /* renamed from: c */
    public static boolean m7105c(Context context) {
        String c = C1328a.m6009c(context);
        boolean b = "enabled".equals(c) ? false : "disabled".equals(c) ? true : C1578v.m7097b(context, context.getPackageName(), "DisableService");
        C1425a.m6441b("Utility", "--- isDisableService : " + b);
        return b;
    }

    /* renamed from: c */
    public static boolean m7106c(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            context.getPackageManager().getApplicationInfo(str, 8192);
            return true;
        } catch (Exception e) {
            C1425a.m6440a("Utility", e);
            return false;
        }
    }

    /* renamed from: c */
    private static boolean m7107c(Context context, boolean z) {
        if (z) {
            if (!C1578v.m7083a(context, "com.baidu.android.pushservice.action.advertise.notification.SHOW", "com.baidu.android.pushservice.PushServiceReceiver", true)) {
                Log.e("BDPushSDK-Utility", "com.baidu.android.pushservice.PushServiceReceiver did not declaredcom.baidu.android.pushservice.action.advertise.notification.SHOW");
                return false;
            } else if (!C1578v.m7083a(context, "com.baidu.android.pushservice.action.adnotification.ADCLICK", "com.baidu.android.pushservice.PushServiceReceiver", true)) {
                Log.e("BDPushSDK-Utility", "com.baidu.android.pushservice.PushServiceReceiver did not declaredcom.baidu.android.pushservice.action.adnotification.ADCLICK");
                return false;
            } else if (!C1578v.m7083a(context, "com.baidu.android.pushservice.action.adnotification.ADDELETE", "com.baidu.android.pushservice.PushServiceReceiver", true)) {
                Log.e("BDPushSDK-Utility", "com.baidu.android.pushservice.PushServiceReceiver did not declaredcom.baidu.android.pushservice.action.adnotification.ADDELETE");
                return false;
            }
        }
        if (!C1578v.m7083a(context, "com.baidu.android.pushservice.action.notification.SHOW", "com.baidu.android.pushservice.PushServiceReceiver", true)) {
            Log.e("BDPushSDK-Utility", "com.baidu.android.pushservice.PushServiceReceiver did not declaredcom.baidu.android.pushservice.action.notification.SHOW");
            return false;
        } else if (!C1578v.m7083a(context, "android.net.conn.CONNECTIVITY_CHANGE", "com.baidu.android.pushservice.PushServiceReceiver", true)) {
            Log.e("BDPushSDK-Utility", "com.baidu.android.pushservice.PushServiceReceiver did not declaredandroid.net.conn.CONNECTIVITY_CHANGE");
            return false;
        } else if (!C1578v.m7083a(context, "com.baidu.android.pushservice.action.BIND_SYNC", "com.baidu.android.pushservice.RegistrationReceiver", true)) {
            Log.e("BDPushSDK-Utility", "com.baidu.android.pushservice.RegistrationReceiver did not declaredcom.baidu.android.pushservice.action.BIND_SYNC");
            return false;
        } else if (C1578v.m7083a(context, "com.baidu.android.pushservice.action.METHOD", "com.baidu.android.pushservice.RegistrationReceiver", true)) {
            return true;
        } else {
            Log.e("BDPushSDK-Utility", "com.baidu.android.pushservice.PushServiceReceiver did not declaredcom.baidu.android.pushservice.action.notification.SHOW");
            return false;
        }
    }

    /* renamed from: d */
    public static int m7108d(Context context, String str) {
        PackageInfo a = C1578v.m7065a(context, str);
        return a != null ? a.versionCode : 0;
    }

    /* renamed from: d */
    public static Intent m7109d(Context context) {
        Intent intent = new Intent("com.baidu.android.pushservice.action.METHOD");
        intent.addFlags(32);
        intent.putExtra("pkg_name", context.getPackageName());
        intent.putExtra("method_version", "V2");
        return intent;
    }

    /* renamed from: d */
    public static String m7110d(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        Intent intent = new Intent(str2);
        intent.setPackage(str);
        List queryBroadcastReceivers;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return null;
            }
            queryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 544);
            return (queryBroadcastReceivers == null || queryBroadcastReceivers.size() <= 0) ? null : ((ResolveInfo) queryBroadcastReceivers.get(0)).activityInfo.name;
        } catch (Exception e) {
            C1425a.m6440a("Utility", e);
            queryBroadcastReceivers = null;
        }
    }

    /* renamed from: d */
    private static String m7111d(String str) {
        String str2 = "";
        try {
            if (str.startsWith("http://")) {
                str = str.replace("http://", "");
            }
            InetAddress[] allByName = InetAddress.getAllByName(str);
            if (allByName == null || allByName.length <= 0) {
                return str2;
            }
            int length = allByName.length;
            int i = 0;
            while (i < length) {
                i++;
                str2 = str2 + ":" + allByName[i].getHostAddress();
            }
            return str2.length() > 1 ? str2.substring(1) : str2;
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: d */
    public static boolean m7112d() {
        try {
            if (Build.MANUFACTURER.toUpperCase().contains("XIAOMI")) {
                return true;
            }
        } catch (Exception e) {
            C1425a.m6440a("Utility", e);
        }
        return false;
    }

    /* renamed from: e */
    public static Intent m7113e(Context context) {
        Intent intent = new Intent("com.baidu.android.pushservice.action.METHOD");
        intent.addFlags(32);
        intent.putExtra("method_version", "V2");
        return intent;
    }

    /* renamed from: e */
    public static void m7114e(Context context, String str, String str2) {
        if (context != null) {
            try {
                C1425a.m6442c("Utility", "requestXiaomiRegId--------");
                Context applicationContext = context.getApplicationContext();
                C1551o.m6968b(applicationContext, "");
                C1551o.m6969c(applicationContext);
                MiPushClient.registerPush(applicationContext, str, str2);
            } catch (Throwable th) {
                C1425a.m6440a("Utility", th);
            }
        }
    }

    /* renamed from: e */
    public static boolean m7115e() {
        try {
            if (Build.MANUFACTURER.toUpperCase().contains("HUAWEI")) {
                return true;
            }
        } catch (Exception e) {
            C1425a.m6440a("Utility", e);
        }
        return false;
    }

    /* renamed from: e */
    public static boolean m7116e(Context context, String str) {
        ApplicationInfo applicationInfo;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            applicationInfo = packageManager.getApplicationInfo(str, 128);
            return (applicationInfo == null || applicationInfo.metaData == null) ? false : applicationInfo.metaData.getBoolean("IsBaiduApp");
        } catch (Exception e) {
            C1425a.m6439a("isBaiduApp", "--- " + str + " GetMetaData Exception:\r\n", e);
            applicationInfo = null;
        }
    }

    /* renamed from: f */
    public static void m7117f(Context context, String str) {
        Intent c = C1577u.m7050c(context);
        c.putExtra("method", "pushservice_restart_v2");
        if (TextUtils.isEmpty(str) || !str.equals(C1578v.m7147u(context))) {
            c.putExtra("priority2", C1578v.m7128k(context));
        } else {
            c.putExtra("priority2", Long.MAX_VALUE);
        }
        if (!TextUtils.isEmpty(str)) {
            c.setPackage(str);
            c.setClassName(str, "com.baidu.android.pushservice.CommandService");
        }
        c.putExtra("command_type", "reflect_receiver");
        try {
            if (context.startService(c) != null) {
                return;
            }
        } catch (Exception e) {
            C1425a.m6440a("Utility", e);
        }
        if (!TextUtils.isEmpty(str)) {
            c.setPackage(str);
        }
        String d = C1578v.m7110d(context, str, "com.baidu.android.pushservice.action.METHOD");
        if (!TextUtils.isEmpty(d)) {
            c.setClassName(str, d);
        }
        context.sendBroadcast(c);
        C1328a.m6004a(context, false);
    }

    /* renamed from: f */
    public static boolean m7118f() {
        return C1578v.m7112d() || C1578v.m7115e();
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003f A:{SYNTHETIC, Splitter:B:14:0x003f} */
    /* renamed from: g */
    public static long m7119g(android.content.Context r7, java.lang.String r8) {
        /*
        r0 = 0;
        r2 = -1;
        if (r7 == 0) goto L_0x000c;
    L_0x0006:
        r4 = android.text.TextUtils.isEmpty(r8);
        if (r4 == 0) goto L_0x000d;
    L_0x000c:
        return r0;
    L_0x000d:
        r0 = com.baidu.android.pushservice.util.C1578v.m7056E(r7);	 Catch:{ Exception -> 0x0045 }
        if (r0 == 0) goto L_0x006d;
    L_0x0013:
        r0 = com.baidu.android.pushservice.util.C1578v.m7143s(r7, r8);	 Catch:{ Exception -> 0x0045 }
        if (r0 == 0) goto L_0x006d;
    L_0x0019:
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0045 }
        r1.<init>();	 Catch:{ Exception -> 0x0045 }
        r1 = r1.append(r8);	 Catch:{ Exception -> 0x0045 }
        r4 = ".push_sync";
        r1 = r1.append(r4);	 Catch:{ Exception -> 0x0045 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0045 }
        r4 = 5;
        r0 = r0.getSharedPreferences(r1, r4);	 Catch:{ Exception -> 0x0045 }
        if (r0 == 0) goto L_0x006d;
    L_0x0033:
        r1 = "priority2";
        r4 = 0;
        r0 = r0.getLong(r1, r4);	 Catch:{ Exception -> 0x0045 }
    L_0x003b:
        r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r2 != 0) goto L_0x000c;
    L_0x003f:
        r0 = com.baidu.android.pushservice.util.C1575t.m7036c(r7, r8);	 Catch:{ Exception -> 0x006b }
        r0 = (long) r0;
        goto L_0x000c;
    L_0x0045:
        r0 = move-exception;
        r6 = r0;
        r0 = r2;
        r2 = r6;
    L_0x0049:
        r3 = "Utility";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "create packagecontext exception: ";
        r4 = r4.append(r5);
        r5 = r2.getMessage();
        r4 = r4.append(r5);
        r4 = r4.toString();
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r3, r4);
        r3 = "Utility";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r3, r2);
        goto L_0x000c;
    L_0x006b:
        r2 = move-exception;
        goto L_0x0049;
    L_0x006d:
        r0 = r2;
        goto L_0x003b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1578v.m7119g(android.content.Context, java.lang.String):long");
    }

    /* renamed from: g */
    public static Intent m7120g(Context context) {
        return C1578v.m7109d(context);
    }

    /* renamed from: h */
    public static void m7121h(Context context) {
        C1425a.m6442c("Utility", ">>> setAlarmForPeriodRestart");
        C1578v.m7072a(context, 300000);
    }

    /* renamed from: h */
    public static boolean m7122h(Context context, String str) {
        Throwable e;
        Intent intent = new Intent("com.baidu.android.pushservice.action.PUSH_SERVICE");
        intent.setPackage(str);
        boolean z;
        int componentEnabledSetting;
        boolean z2;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            List queryIntentServices = packageManager.queryIntentServices(intent, 544);
            if (!(queryIntentServices == null || queryIntentServices.isEmpty())) {
                int i = 0;
                while (i < queryIntentServices.size()) {
                    if ("com.baidu.android.pushservice.PushService".equals(((ResolveInfo) queryIntentServices.get(i)).serviceInfo.name) && ((ResolveInfo) queryIntentServices.get(i)).serviceInfo.exported) {
                        z = ((ResolveInfo) queryIntentServices.get(i)).serviceInfo.enabled;
                        break;
                    }
                    i++;
                }
            }
            z = false;
            try {
                componentEnabledSetting = packageManager.getComponentEnabledSetting(new ComponentName(str, "com.baidu.android.pushservice.PushService"));
            } catch (Exception e2) {
                e = e2;
            }
            z2 = componentEnabledSetting != 1 || (componentEnabledSetting == 0 && z);
            return z2;
        } catch (Exception e3) {
            e = e3;
            z = false;
            C1425a.m6440a("Utility", e);
            componentEnabledSetting = 2;
            if (componentEnabledSetting != 1) {
            }
            return z2;
        }
    }

    /* renamed from: i */
    public static long m7123i(Context context) {
        int i = 5;
        long a = (long) C1328a.m6003a();
        int b = C1328a.m6006b();
        if (b > 0) {
            if (b <= 5) {
                i = b;
            }
            return ((long) i) + ((a << 4) + 10);
        }
        long j = a << 2;
        if (C1578v.m7116e(context, context.getPackageName())) {
            C1425a.m6442c("Utility", "--- get " + context + " PriorityVersion, baidu app");
            j++;
        }
        j <<= 1;
        if (C1578v.m7096b(context, context.getPackageName())) {
            C1425a.m6442c("Utility", "--- get " + context + " PriorityVersion, system app");
            j++;
        }
        return (j << 1) + ((long) C1578v.m7133n(context));
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0024  */
    /* renamed from: i */
    public static boolean m7124i(android.content.Context r8, java.lang.String r9) {
        /*
        r1 = 0;
        r4 = 1;
        r3 = 0;
        r0 = new android.content.Intent;
        r2 = "com.baidu.android.pushservice.action.notification.CLICK";
        r0.<init>(r2);
        r2 = r8.getPackageName();
        r0.setPackage(r2);
        r2 = r8.getPackageManager();	 Catch:{ Exception -> 0x0085 }
        if (r2 != 0) goto L_0x0018;
    L_0x0017:
        return r3;
    L_0x0018:
        r5 = 544; // 0x220 float:7.62E-43 double:2.69E-321;
        r0 = r2.queryBroadcastReceivers(r0, r5);	 Catch:{ Exception -> 0x00b8 }
    L_0x001e:
        r5 = r0.size();
        if (r5 < r4) goto L_0x0017;
    L_0x0024:
        r0 = r0.iterator();
        r5 = r0.hasNext();
        if (r5 == 0) goto L_0x00bd;
    L_0x002e:
        r0 = r0.next();
        r0 = (android.content.pm.ResolveInfo) r0;
        r1 = r0.activityInfo;
        r1 = r1.name;
        r0 = r0.activityInfo;
        r0 = r0.enabled;
    L_0x003c:
        r5 = "com.baidu.android.pushservice.action.MESSAGE";
        r5 = com.baidu.android.pushservice.util.C1578v.m7083a(r8, r5, r1, r4);
        if (r5 == 0) goto L_0x0017;
    L_0x0044:
        r5 = "com.baidu.android.pushservice.action.RECEIVE";
        r5 = com.baidu.android.pushservice.util.C1578v.m7083a(r8, r5, r1, r4);
        if (r5 == 0) goto L_0x0017;
    L_0x004c:
        r5 = com.baidu.android.pushservice.util.C1578v.m7115e();
        if (r5 == 0) goto L_0x0090;
    L_0x0052:
        r5 = com.baidu.android.pushservice.PushSettings.m5895n(r8);
        if (r5 == 0) goto L_0x0090;
    L_0x0058:
        r5 = "com.huawei.intent.action.PUSH";
        r5 = com.baidu.android.pushservice.util.C1578v.m7083a(r8, r5, r1, r4);
        if (r5 == 0) goto L_0x0017;
    L_0x0060:
        r5 = "com.huawei.android.push.intent.RECEIVE";
        r5 = com.baidu.android.pushservice.util.C1578v.m7083a(r8, r5, r1, r4);
        if (r5 == 0) goto L_0x0017;
    L_0x0068:
        r5 = "com.huawei.android.push.intent.REGISTRATION";
        r5 = com.baidu.android.pushservice.util.C1578v.m7083a(r8, r5, r1, r4);
        if (r5 == 0) goto L_0x0017;
    L_0x0070:
        r5 = new android.content.ComponentName;
        r6 = r8.getPackageName();
        r5.<init>(r6, r1);
        r1 = r2.getComponentEnabledSetting(r5);
        if (r1 == r4) goto L_0x0083;
    L_0x007f:
        if (r1 != 0) goto L_0x0017;
    L_0x0081:
        if (r0 == 0) goto L_0x0017;
    L_0x0083:
        r3 = r4;
        goto L_0x0017;
    L_0x0085:
        r0 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x0088:
        r5 = "Utility";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r5, r2);
        r2 = r0;
        r0 = r1;
        goto L_0x001e;
    L_0x0090:
        r5 = com.baidu.android.pushservice.util.C1578v.m7112d();
        if (r5 == 0) goto L_0x0070;
    L_0x0096:
        r5 = com.baidu.android.pushservice.PushSettings.m5894m(r8);
        if (r5 == 0) goto L_0x0070;
    L_0x009c:
        r5 = "com.baidu.android.pushservice.PushPatchMessageReceiver";
        r6 = "com.xiaomi.mipush.RECEIVE_MESSAGE";
        r6 = com.baidu.android.pushservice.util.C1578v.m7083a(r8, r6, r5, r4);
        if (r6 == 0) goto L_0x0017;
    L_0x00a6:
        r6 = "com.xiaomi.mipush.MESSAGE_ARRIVED";
        r6 = com.baidu.android.pushservice.util.C1578v.m7083a(r8, r6, r5, r4);
        if (r6 == 0) goto L_0x0017;
    L_0x00ae:
        r6 = "com.xiaomi.mipush.ERROR";
        r5 = com.baidu.android.pushservice.util.C1578v.m7083a(r8, r6, r5, r4);
        if (r5 != 0) goto L_0x0070;
    L_0x00b6:
        goto L_0x0017;
    L_0x00b8:
        r0 = move-exception;
        r7 = r0;
        r0 = r2;
        r2 = r7;
        goto L_0x0088;
    L_0x00bd:
        r0 = r3;
        goto L_0x003c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1578v.m7124i(android.content.Context, java.lang.String):boolean");
    }

    /* renamed from: j */
    public static void m7125j(Context context) {
        C1578v.m7076a(context, false, false);
    }

    /* renamed from: j */
    public static boolean m7126j(Context context, String str) {
        try {
            if (C1578v.m7056E(context) && context.getSharedPreferences(context.getPackageName() + ".push_sync", 5).getInt("version2", 0) < 29) {
                return true;
            }
            ComponentName componentName = new ComponentName(context, "com.baidu.android.pushservice.CommandService");
            PackageManager packageManager = context.getPackageManager();
            ServiceInfo serviceInfo = new ServiceInfo();
            String str2 = "";
            ServiceInfo serviceInfo2 = packageManager.getServiceInfo(componentName, 128);
            if (TextUtils.isEmpty(serviceInfo2.name)) {
                Log.e("BDPushSDK-Utility", "com.baidu.android.pushservice.CommandService did not declared ");
                return false;
            } else if (serviceInfo2.exported) {
                return true;
            } else {
                Log.e("BDPushSDK-Utility", "com.baidu.android.pushservice.CommandService  exported declared wrong");
                return false;
            }
        } catch (Exception e) {
            C1425a.m6440a("Utility", e);
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x003c A:{SYNTHETIC, Splitter:B:13:0x003c} */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x004c A:{Catch:{ Exception -> 0x005e }} */
    /* renamed from: k */
    public static int m7127k(android.content.Context r5, java.lang.String r6) {
        /*
        r0 = 0;
        r1 = -1;
        r2 = 0;
        if (r5 == 0) goto L_0x000b;
    L_0x0005:
        r3 = android.text.TextUtils.isEmpty(r6);
        if (r3 == 0) goto L_0x000c;
    L_0x000b:
        return r0;
    L_0x000c:
        r0 = com.baidu.android.pushservice.util.C1578v.m7056E(r5);	 Catch:{ Exception -> 0x0054 }
        if (r0 == 0) goto L_0x0064;
    L_0x0012:
        r0 = com.baidu.android.pushservice.util.C1578v.m7143s(r5, r6);	 Catch:{ Exception -> 0x0054 }
        if (r0 == 0) goto L_0x0066;
    L_0x0018:
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0054 }
        r2.<init>();	 Catch:{ Exception -> 0x0054 }
        r2 = r2.append(r6);	 Catch:{ Exception -> 0x0054 }
        r3 = ".push_sync";
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0054 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x0054 }
        r3 = 5;
        r0 = r0.getSharedPreferences(r2, r3);	 Catch:{ Exception -> 0x0054 }
    L_0x0030:
        if (r0 == 0) goto L_0x0064;
    L_0x0032:
        r2 = "version2";
        r3 = 0;
        r2 = r0.getInt(r2, r3);	 Catch:{ Exception -> 0x0054 }
    L_0x003a:
        if (r2 != r1) goto L_0x0062;
    L_0x003c:
        r0 = r5.getPackageName();	 Catch:{ Exception -> 0x005e }
        r0 = r6.equals(r0);	 Catch:{ Exception -> 0x005e }
        if (r0 == 0) goto L_0x004f;
    L_0x0046:
        r0 = com.baidu.android.pushservice.C1328a.m6003a();	 Catch:{ Exception -> 0x005e }
    L_0x004a:
        if (r0 != 0) goto L_0x000b;
    L_0x004c:
        r0 = 50;
        goto L_0x000b;
    L_0x004f:
        r0 = com.baidu.android.pushservice.util.C1575t.m7037d(r5, r6);	 Catch:{ Exception -> 0x005e }
        goto L_0x004a;
    L_0x0054:
        r0 = move-exception;
        r4 = r0;
        r0 = r1;
        r1 = r4;
    L_0x0058:
        r2 = "Utility";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x000b;
    L_0x005e:
        r0 = move-exception;
        r1 = r0;
        r0 = r2;
        goto L_0x0058;
    L_0x0062:
        r0 = r2;
        goto L_0x004a;
    L_0x0064:
        r2 = r1;
        goto L_0x003a;
    L_0x0066:
        r0 = r2;
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1578v.m7127k(android.content.Context, java.lang.String):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0037 A:{SYNTHETIC, Splitter:B:10:0x0037} */
    /* renamed from: k */
    public static long m7128k(android.content.Context r7) {
        /*
        r0 = 0;
        r2 = -1;
        if (r7 != 0) goto L_0x0007;
    L_0x0006:
        return r0;
    L_0x0007:
        r0 = com.baidu.android.pushservice.util.C1578v.m7056E(r7);	 Catch:{ Exception -> 0x003d }
        if (r0 == 0) goto L_0x0049;
    L_0x000d:
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x003d }
        r0.<init>();	 Catch:{ Exception -> 0x003d }
        r1 = r7.getPackageName();	 Catch:{ Exception -> 0x003d }
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x003d }
        r1 = ".push_sync";
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x003d }
        r0 = r0.toString();	 Catch:{ Exception -> 0x003d }
        r1 = 5;
        r0 = r7.getSharedPreferences(r0, r1);	 Catch:{ Exception -> 0x003d }
        if (r0 == 0) goto L_0x0049;
    L_0x002b:
        r1 = "priority2";
        r4 = 0;
        r0 = r0.getLong(r1, r4);	 Catch:{ Exception -> 0x003d }
    L_0x0033:
        r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r2 != 0) goto L_0x0006;
    L_0x0037:
        r0 = com.baidu.android.pushservice.util.C1574s.m7020b(r7);	 Catch:{ Exception -> 0x0047 }
        r0 = (long) r0;
        goto L_0x0006;
    L_0x003d:
        r0 = move-exception;
        r6 = r0;
        r0 = r2;
        r2 = r6;
    L_0x0041:
        r3 = "Utility";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r3, r2);
        goto L_0x0006;
    L_0x0047:
        r2 = move-exception;
        goto L_0x0041;
    L_0x0049:
        r0 = r2;
        goto L_0x0033;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1578v.m7128k(android.content.Context):long");
    }

    /* renamed from: l */
    public static String m7129l(Context context, String str) {
        return TextUtils.isEmpty(str) ? "" : C1578v.m7068a(context, str, "BaiduPush_CHANNEL");
    }

    /* renamed from: l */
    static boolean m7130l(Context context) {
        return C1578v.m7124i(context, context.getPackageName());
    }

    /* renamed from: m */
    static boolean m7131m(Context context) {
        return C1578v.m7126j(context, context.getPackageName());
    }

    /* renamed from: m */
    public static boolean m7132m(Context context, String str) {
        return C1578v.m7065a(context, str) != null;
    }

    /* renamed from: n */
    static int m7133n(Context context) {
        int i = 0;
        if (C1578v.m7083a(context, "android.intent.action.USER_PRESENT", "com.baidu.android.pushservice.PushServiceReceiver", true)) {
            i = 1;
        }
        if (C1578v.m7083a(context, "android.intent.action.MEDIA_MOUNTED", "com.baidu.android.pushservice.PushServiceReceiver", true)) {
            i++;
        }
        if (C1578v.m7083a(context, "android.intent.action.ACTION_POWER_CONNECTED", "com.baidu.android.pushservice.PushServiceReceiver", true)) {
            i++;
        }
        return C1578v.m7083a(context, "android.intent.action.ACTION_POWER_DISCONNECTED", "com.baidu.android.pushservice.PushServiceReceiver", true) ? i + 1 : i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x00c1  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x013b  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00d4  */
    /* renamed from: n */
    public static java.lang.String m7134n(android.content.Context r11, java.lang.String r12) {
        /*
        r0 = com.baidu.android.pushservice.config.ModeConfig.getInstance(r11);
        r0 = r0.getCurrentMode();
        r1 = com.baidu.android.pushservice.config.ModeConfig.MODE_C_H;
        if (r0 == r1) goto L_0x0014;
    L_0x000c:
        r1 = com.baidu.android.pushservice.config.ModeConfig.MODE_C_C;
        if (r0 == r1) goto L_0x0014;
    L_0x0010:
        r1 = com.baidu.android.pushservice.config.ModeConfig.MODE_C;
        if (r0 != r1) goto L_0x0024;
    L_0x0014:
        r0 = com.baidu.android.pushservice.config.ModeConfig.getInstance(r11);
        r0 = r0.getHostPackageName();
        r1 = android.text.TextUtils.isEmpty(r0);
        if (r1 != 0) goto L_0x0024;
    L_0x0022:
        r12 = r0;
    L_0x0023:
        return r12;
    L_0x0024:
        r0 = r11.getApplicationContext();
        r2 = com.baidu.android.pushservice.util.C1578v.m7135o(r0);
        r0 = r2.size();
        r1 = 1;
        if (r0 > r1) goto L_0x0038;
    L_0x0033:
        r12 = r11.getPackageName();
        goto L_0x0023;
    L_0x0038:
        r0 = -1;
        r3 = com.baidu.android.pushservice.util.C1578v.m7056E(r11);
        if (r3 == 0) goto L_0x0066;
    L_0x0040:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = r11.getPackageName();
        r3 = r3.append(r4);
        r4 = ".push_sync";
        r3 = r3.append(r4);
        r3 = r3.toString();
        r4 = 5;
        r3 = r11.getSharedPreferences(r3, r4);
        if (r3 == 0) goto L_0x0066;
    L_0x005e:
        r0 = "priority2";
        r4 = 0;
        r0 = r3.getLong(r0, r4);
    L_0x0066:
        r4 = -1;
        r3 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r3 != 0) goto L_0x0071;
    L_0x006c:
        r0 = com.baidu.android.pushservice.util.C1574s.m7020b(r11);
        r0 = (long) r0;
    L_0x0071:
        r5 = com.baidu.android.pushservice.util.C1578v.m7147u(r11);
        r8 = r2.iterator();
        r2 = r0;
    L_0x007a:
        r0 = r8.hasNext();
        if (r0 == 0) goto L_0x015d;
    L_0x0080:
        r0 = r8.next();
        r0 = (android.content.pm.ResolveInfo) r0;
        r6 = -1;
        r0 = r0.activityInfo;
        r4 = r0.packageName;
        r9 = com.baidu.android.pushservice.util.C1578v.m7143s(r11, r4);
        r0 = com.baidu.android.pushservice.util.C1578v.m7056E(r11);
        if (r0 == 0) goto L_0x0179;
    L_0x0096:
        r0 = 0;
        if (r9 == 0) goto L_0x00b1;
    L_0x0099:
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0114 }
        r1.<init>();	 Catch:{ Exception -> 0x0114 }
        r1 = r1.append(r4);	 Catch:{ Exception -> 0x0114 }
        r10 = ".push_sync";
        r1 = r1.append(r10);	 Catch:{ Exception -> 0x0114 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0114 }
        r10 = 5;
        r0 = r9.getSharedPreferences(r1, r10);	 Catch:{ Exception -> 0x0114 }
    L_0x00b1:
        if (r0 == 0) goto L_0x0179;
    L_0x00b3:
        r1 = "priority2";
        r6 = 0;
        r0 = r0.getLong(r1, r6);
    L_0x00bb:
        r6 = -1;
        r6 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1));
        if (r6 != 0) goto L_0x00d0;
    L_0x00c1:
        r0 = r11.getPackageName();
        r0 = r4.equals(r0);
        if (r0 == 0) goto L_0x011b;
    L_0x00cb:
        r0 = com.baidu.android.pushservice.util.C1574s.m7020b(r11);
        r0 = (long) r0;
    L_0x00d0:
        r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r6 <= 0) goto L_0x013b;
    L_0x00d4:
        r6 = "Utility";
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r10 = "Find more higher priority pkg : ";
        r7 = r7.append(r10);
        r7 = r7.append(r4);
        r10 = " priority = ";
        r7 = r7.append(r10);
        r7 = r7.append(r0);
        r10 = ",Current highest priority pkg : ";
        r7 = r7.append(r10);
        r7 = r7.append(r12);
        r10 = " priority = ";
        r7 = r7.append(r10);
        r7 = r7.append(r2);
        r7 = r7.toString();
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r6, r7);
        r6 = com.baidu.android.pushservice.util.C1578v.m7122h(r9, r4);
        if (r6 == 0) goto L_0x0121;
    L_0x0110:
        r12 = r4;
    L_0x0111:
        r2 = r0;
        goto L_0x007a;
    L_0x0114:
        r1 = move-exception;
        r10 = "Utility";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r10, r1);
        goto L_0x00b1;
    L_0x011b:
        r0 = com.baidu.android.pushservice.util.C1575t.m7036c(r11, r4);
        r0 = (long) r0;
        goto L_0x00d0;
    L_0x0121:
        r0 = "Utility";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r1 = r1.append(r4);
        r4 = "push service is disabled";
        r1 = r1.append(r4);
        r1 = r1.toString();
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r0, r1);
        r0 = r2;
        goto L_0x0111;
    L_0x013b:
        r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r6 != 0) goto L_0x0177;
    L_0x013f:
        r6 = r4.equals(r5);
        if (r6 == 0) goto L_0x014b;
    L_0x0145:
        r6 = com.baidu.android.pushservice.util.C1578v.m7122h(r9, r4);
        if (r6 != 0) goto L_0x015b;
    L_0x014b:
        r6 = r11.getPackageName();
        r6 = r4.equals(r6);
        if (r6 == 0) goto L_0x0177;
    L_0x0155:
        r6 = com.baidu.android.pushservice.util.C1578v.m7056E(r11);
        if (r6 != 0) goto L_0x0177;
    L_0x015b:
        r12 = r4;
        goto L_0x0111;
    L_0x015d:
        r0 = "Utility";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Current highest priority Push PackageName: ";
        r1 = r1.append(r2);
        r1 = r1.append(r12);
        r1 = r1.toString();
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r0, r1);
        goto L_0x0023;
    L_0x0177:
        r0 = r2;
        goto L_0x0111;
    L_0x0179:
        r0 = r6;
        goto L_0x00bb;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1578v.m7134n(android.content.Context, java.lang.String):java.lang.String");
    }

    /* renamed from: o */
    public static List<ResolveInfo> m7135o(Context context) {
        ArrayList arrayList = new ArrayList();
        Intent intent = new Intent("com.baidu.android.pushservice.action.BIND_SYNC");
        try {
            PackageManager packageManager = context.getPackageManager();
            return packageManager == null ? null : packageManager.queryBroadcastReceivers(intent, 544);
        } catch (Exception e) {
            C1425a.m6440a("Utility", e);
            return arrayList;
        }
    }

    /* renamed from: o */
    public static boolean m7136o(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            C1425a.m6442c("Utility", "isNoDisturb parameters illegal : " + false);
            return false;
        }
        int[] f = C1568q.m7012f(context, str);
        if (f == null || 4 != f.length) {
            return false;
        }
        boolean a = C1578v.m7080a(f[0], f[1], f[2], f[3]);
        C1425a.m6442c("Utility", "isNoDisturb :" + a + " ret0 = " + f[0] + " ret1 = " + f[1] + " ret2 = " + f[2] + " ret[3] = " + f[3]);
        return a;
    }

    /* renamed from: p */
    public static String m7137p(Context context) {
        return C1472f.m6716a(("com.baidu.pushservice.singelinstancev2" + C1471e.m6699b(context)).getBytes(), false);
    }

    /* renamed from: p */
    public static String m7138p(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return packageManager == null ? null : C1472f.m6716a(packageManager.getPackageInfo(str, 64).signatures[0].toByteArray(), false);
        } catch (NameNotFoundException e) {
            C1425a.m6444e("Utility", " packageName not found: " + str);
            return null;
        } catch (Exception e2) {
            C1425a.m6440a("Utility", e2);
            return null;
        }
    }

    /* renamed from: q */
    public static ArrayList<String> m7139q(Context context) {
        List<ResolveInfo> o = C1578v.m7135o(context.getApplicationContext());
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : o) {
            if (!arrayList.contains(resolveInfo.activityInfo.packageName)) {
                arrayList.add(resolveInfo.activityInfo.packageName);
            }
        }
        return arrayList;
    }

    /* renamed from: q */
    public static boolean m7140q(Context context, String str) {
        String a = C1550n.m6955a(context, "com.baidu.android.pushservice.MESSAGE_IDS_RECEIVED");
        if (!TextUtils.isEmpty(a)) {
            if (a.contains(str)) {
                return true;
            }
            if (a.length() > 1000) {
                a = a.substring(VTMCDataCache.MAXSIZE);
            }
            str = a + ":" + str;
        }
        C1550n.m6958a(context, "com.baidu.android.pushservice.MESSAGE_IDS_RECEIVED", str);
        return false;
    }

    /* renamed from: r */
    public static List<String> m7141r(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager == null) {
                C1425a.m6444e("Utility", "ActivityManager is null !!!");
            }
            List<RunningServiceInfo> runningServices = activityManager.getRunningServices(VTMCDataCache.MAXSIZE);
            if (runningServices == null || runningServices.isEmpty()) {
                C1425a.m6444e("Utility", "runnServs is null !!!");
            }
            for (RunningServiceInfo runningServiceInfo : runningServices) {
                if (runningServiceInfo.service.getClassName().contains("com.baidu.android.pushservice.PushService")) {
                    arrayList.add(runningServiceInfo.service.getPackageName());
                }
            }
        } catch (Exception e) {
            C1578v.m7095b(C1425a.m6437a(e), context);
        }
        return arrayList;
    }

    /* renamed from: r */
    public static boolean m7142r(Context context, String str) {
        try {
            return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
        } catch (Exception e) {
            C1425a.m6440a("Utility", e);
            return false;
        }
    }

    /* renamed from: s */
    public static Context m7143s(Context context, String str) {
        Context context2 = null;
        if (TextUtils.isEmpty(str)) {
            return context2;
        }
        try {
            return context.createPackageContext(str, 2);
        } catch (Exception e) {
            C1425a.m6440a("Utility", e);
            return context2;
        }
    }

    /* renamed from: s */
    public static String m7144s(Context context) {
        NetworkInfo c = C1548l.m6950c(context);
        if (c == null || !c.isConnected() || c.getState() != State.CONNECTED) {
            return "unknown";
        }
        if (c.getTypeName().equals("WIFI")) {
            return c.getTypeName();
        }
        CharSequence charSequence = null;
        if (c.getExtraInfo() != null) {
            charSequence = c.getExtraInfo().toLowerCase();
        }
        return TextUtils.isEmpty(charSequence) ? "unknown" : charSequence;
    }

    /* renamed from: t */
    public static int m7145t(Context context) {
        String str = "";
        if (context != null) {
            NetworkInfo c = C1548l.m6950c(context);
            if (c != null && c.isConnectedOrConnecting()) {
                if (!c.getTypeName().toLowerCase().equals("wifi")) {
                    str = "2G";
                    switch (c.getSubtype()) {
                        case 3:
                            str = "3G";
                            break;
                        case 5:
                            str = "3G";
                            break;
                        case 6:
                            str = "3G";
                            break;
                        case 7:
                            str = "3G";
                            break;
                        case 8:
                            str = "3G";
                            break;
                        case 9:
                            str = "3G";
                            break;
                        case 10:
                            str = "3G";
                            break;
                        case 12:
                            str = "3G";
                            break;
                        case 13:
                            str = "4G";
                            break;
                        case 14:
                            str = "3G";
                            break;
                        case 15:
                            str = "3G";
                            break;
                    }
                }
                str = "WF";
            }
        }
        return str.equals("WF") ? 1 : str.equals("2G") ? 2 : str.equals("3G") ? 3 : str.equals("4G") ? 4 : 0;
    }

    /* renamed from: t */
    public static String m7146t(Context context, String str) {
        String str2 = "";
        try {
            PackageInfo a = C1578v.m7065a(context, str);
            return (a == null || a.firstInstallTime <= 0) ? str2 : String.valueOf(a.firstInstallTime);
        } catch (Throwable th) {
            C1425a.m6440a("Utility", th);
            return str2;
        }
    }

    /* renamed from: u */
    public static String m7147u(Context context) {
        String a = C1535c.m6900a(context, "com.baidu.push.cur_pkg");
        if (TextUtils.isEmpty(a)) {
            List<String> r = C1578v.m7141r(context);
            if (!r.isEmpty()) {
                for (String a2 : r) {
                    a2 = C1575t.m7035b(context, a2);
                    if (!TextUtils.isEmpty(a2) && C1578v.m7132m(context, a2)) {
                        if (C1578v.m7122h(context, a2)) {
                            return a2;
                        }
                        C1425a.m6442c("Utility", "The Highest priority Service: " + null + " is disabled,Maybe use setComponentEnabledSetting method");
                    }
                }
            }
        } else if (C1578v.m7132m(context, a2)) {
            if (C1578v.m7122h(context, a2)) {
                return a2;
            }
            C1425a.m6442c("Utility", "The Highest priority Service: " + a2 + " is disabled,Maybe use setComponentEnabledSetting method");
        }
        return null;
    }

    /* renamed from: u */
    public static boolean m7148u(Context context, String str) {
        boolean z = false;
        ArrayList arrayList = (ArrayList) C1332b.m6020a(context).f4721a.clone();
        if (arrayList != null && !arrayList.isEmpty()) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                if (str.equals(((C1339h) it.next()).mo13589c())) {
                    z = true;
                    break;
                }
            }
        }
        if (!z) {
            String g = C1574s.m7031g(context);
            if (TextUtils.isEmpty(g) && C1578v.m7056E(context)) {
                g = C1579w.m7157a(context, context.getPackageName() + ".push_sync", "r_v2");
            }
            g = C1332b.m6021a(g);
            if (!TextUtils.isEmpty(g) && g.contains(str)) {
                g = g.replace(" ", "");
                if (g.charAt(g.indexOf(str) + str.length()) == ',') {
                    return true;
                }
            }
        }
        return z;
    }

    /* renamed from: v */
    public static String m7149v(Context context) {
        return C1578v.m7134n(context, context.getPackageName());
    }

    /* renamed from: v */
    public static void m7150v(Context context, String str) {
        Intent intent = new Intent("com.baidu.android.pushservice.action.BIND_SYNC");
        intent.putExtra("r_sync_type", 0);
        intent.putExtra("r_sync_rdata_v2", str);
        intent.putExtra("r_sync_from", context.getPackageName());
        intent.setFlags(32);
        for (ResolveInfo resolveInfo : context.getPackageManager().queryBroadcastReceivers(new Intent("com.baidu.android.pushservice.action.BIND_SYNC"), 544)) {
            C1578v.m7094b(context, intent, "com.baidu.android.pushservice.action.BIND_SYNC", resolveInfo.activityInfo.packageName);
        }
    }

    /* renamed from: w */
    public static String m7151w(Context context) {
        int i = 1;
        int i2 = C1548l.m6948a(context) ? 0 : 1;
        NetworkInfo c = C1548l.m6950c(context);
        if (c != null && c.isAvailable()) {
            i = 0;
        }
        int c2 = C1578v.m7100c("220.181.112.244");
        int c3 = C1578v.m7100c("202.108.23.105");
        int c4 = C1578v.m7100c("202.108.23.109");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("frontia_avail", i2);
            jSONObject.put("network_avail", i);
            jSONObject.put("baidu_avail", c2);
            jSONObject.put("sa_avail", c3);
            jSONObject.put("logic_avail", c4);
        } catch (Exception e) {
            C1425a.m6440a("Utility", e);
        }
        return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
    }

    /* renamed from: w */
    public static boolean m7152w(Context context, String str) {
        Throwable e;
        boolean e2;
        boolean q;
        try {
            if (TextUtils.isEmpty(str)) {
                C1425a.m6444e("Utility", "msgid = null");
                return false;
            }
            e2 = C1568q.m7010e(context, str);
            try {
                q = C1578v.m7140q(context, str);
            } catch (Exception e3) {
                e = e3;
            }
            return !e2 && q;
        } catch (Exception e4) {
            e = e4;
            e2 = false;
            C1425a.m6440a("Utility", e);
            q = false;
            if (!e2) {
                return false;
            }
        }
    }

    /* renamed from: x */
    private static int m7153x(Context context, String str) {
        return context.getPackageManager().getComponentEnabledSetting(new ComponentName(context.getPackageName(), str));
    }

    /* renamed from: x */
    public static String m7154x(Context context) {
        String d = C1578v.m7111d("www.baidu.com");
        String d2 = C1578v.m7111d("sa.tuisong.baidu.com");
        String d3 = C1578v.m7111d("api.tuisong.baidu.com");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("baidu_ip", d);
            jSONObject.put("sa_ip", d2);
            jSONObject.put("logic_ip", d3);
        } catch (Exception e) {
            C1425a.m6440a("Utility", e);
        }
        if (C1328a.m6006b() > 0) {
            C1425a.m6442c("Utility", "getNetworkInfo json: " + (!(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject)));
        }
        return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
    }

    /* renamed from: y */
    public static boolean m7155y(Context context) {
        boolean z = false;
        try {
            z = context.getSharedPreferences("com.baidu.pushservice.BIND_CACHE", 0).getBoolean("bind_status", false);
        } catch (Exception e) {
            C1425a.m6440a("Utility", e);
        }
        C1425a.m6442c("Utility", "isbind = " + z);
        return z;
    }

    /* renamed from: z */
    public static void m7156z(Context context) {
        if (context != null) {
            C1425a.m6442c("Utility", "requestHuaweiToken--------");
            C1551o.m6965a(context, "");
            Context applicationContext = context.getApplicationContext();
            Intent intent = new Intent("com.huawei.android.push.intent.REGISTER");
            intent.putExtra("pkg_name", applicationContext.getPackageName());
            intent.setFlags(32);
            applicationContext.sendBroadcast(intent);
            C1551o.m6966a(applicationContext, "hasRequestToken", true);
        }
    }
}
