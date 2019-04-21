package com.baidu.android.pushservice;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.baidu.android.pushservice.config.ModeConfig;
import com.baidu.android.pushservice.config.ModeConfig.C1346a;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p036h.C1426b;
import com.baidu.android.pushservice.p037i.C1456u;
import com.baidu.android.pushservice.util.C1549m;
import com.baidu.android.pushservice.util.C1550n;
import com.baidu.android.pushservice.util.C1551o;
import com.baidu.android.pushservice.util.C1574s;
import com.baidu.android.pushservice.util.C1577u;
import com.baidu.android.pushservice.util.C1578v;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.android.pushservice.g */
public class C1420g {
    /* renamed from: a */
    public static int f4984a = -1;
    /* renamed from: b */
    public static String f4985b = null;
    /* renamed from: c */
    public static String f4986c = null;
    /* renamed from: d */
    public static String f4987d = null;
    /* renamed from: e */
    public static String f4988e = null;
    /* renamed from: f */
    public static Handler f4989f = null;
    /* renamed from: g */
    private static final ConcurrentLinkedQueue<Runnable> f4990g = new ConcurrentLinkedQueue();

    /* renamed from: com.baidu.android.pushservice.g$a */
    private static class C1406a extends Handler {
        public C1406a(Context context) {
            super(context.getMainLooper());
        }

        public void handleMessage(Message message) {
            if (message.what == 65553 && C1420g.f4989f != null && C1420g.f4990g != null && !C1420g.f4990g.isEmpty()) {
                C1420g.f4989f.removeCallbacks((C1407b) C1420g.f4990g.poll());
            }
        }
    }

    /* renamed from: com.baidu.android.pushservice.g$b */
    private static class C1407b implements Runnable {
        /* renamed from: a */
        private Context f4936a;

        public C1407b(Context context) {
            this.f4936a = context;
        }

        public void run() {
            CharSequence charSequence = null;
            if (ModeConfig.isXiaomiProxyMode(this.f4936a)) {
                charSequence = C1551o.m6967b(this.f4936a);
            } else if (ModeConfig.isHuaweiProxyMode(this.f4936a)) {
                charSequence = C1551o.m6964a(this.f4936a);
            }
            if (TextUtils.isEmpty(charSequence)) {
                C1420g.m6435j(this.f4936a);
                if (C1420g.f4990g != null && !C1420g.f4990g.isEmpty()) {
                    C1420g.f4990g.poll();
                }
            }
        }
    }

    /* renamed from: a */
    public static Intent m6417a(Context context) {
        if (C1420g.m6432g(context)) {
            return null;
        }
        int b = f4984a != -1 ? f4984a : C1550n.m6960b(context, "com.baidu.android.pushservice.PushManager.LOGIN_TYPE", 0);
        CharSequence a = !TextUtils.isEmpty(f4985b) ? f4985b : C1550n.m6955a(context, "com.baidu.android.pushservice.PushManager.LONGIN_VALUE");
        Intent g;
        if (TextUtils.isEmpty(a)) {
            C1426b.m6447b("PushManagerHandler", "Can not acquire loginValue, please check if there is a right loginValue", context);
            C1420g.m6430e(context);
            return null;
        } else if (b == 2) {
            g = C1578v.m7120g(context);
            g.putExtra("appid", a);
            String a2 = !TextUtils.isEmpty(f4986c) ? f4986c : C1550n.m6955a(context, "com.baidu.android.pushservice.PushManager.BDUSS");
            g.putExtra("bduss", a2);
            C1426b.m6445a("PushManagerHandler", "Bduss:" + a2, context.getApplicationContext());
            return g;
        } else {
            g = C1577u.m7050c(context);
            if (b == 1) {
                g.putExtra("access_token", a);
                C1426b.m6445a("PushManagerHandler", "Access Token:" + a, context.getApplicationContext());
                return g;
            }
            if (b == 0) {
                g.putExtra("secret_key", a);
            }
            return g;
        }
    }

    /* renamed from: a */
    public static Intent m6418a(Context context, int i) {
        Intent a = C1420g.m6417a(context);
        if (a == null) {
            return null;
        }
        a.putExtra("method", "method_bind");
        a.putExtra("bind_name", Build.MODEL);
        a.putExtra("bind_status", i);
        a.putExtra("push_sdk_version", C1328a.m6003a());
        a.setFlags(a.getFlags() | 32);
        if (VERSION.SDK_INT < 19) {
            return a;
        }
        if (C1549m.m6954a(context)) {
            a.putExtra("bind_notify_status", "1");
            return a;
        }
        a.putExtra("bind_notify_status", "0");
        return a;
    }

    /* renamed from: a */
    public static String m6419a(String str, String str2, String str3, String str4) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("appid", str2);
        jSONObject.put("channel_id", str3);
        jSONObject.put("user_id", str4);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("request_id", str);
        jSONObject2.put("response_params", jSONObject);
        return !(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : JSONObjectInstrumentation.toString(jSONObject2);
    }

    /* renamed from: a */
    public static void m6421a(Context context, String str) {
        try {
            if (f4989f != null && f4990g != null && !f4990g.isEmpty()) {
                f4989f.sendEmptyMessage(65553);
                Intent a = C1420g.m6418a(context, 0);
                if (a == null) {
                    C1425a.m6444e("PushManagerHandler", "bind for proxy err, intent is null !!!");
                    return;
                }
                int currentMode = ModeConfig.getInstance(context).getCurrentMode();
                if (TextUtils.isEmpty(str)) {
                    C1420g.m6435j(context);
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                JSONObject jSONObject2 = new JSONObject();
                if (currentMode == ModeConfig.MODE_I_HW) {
                    jSONObject2.put("huawei_token", str);
                    jSONObject.put("huawei", jSONObject2);
                    C1551o.m6965a(context, str);
                } else if (currentMode == ModeConfig.MODE_I_XM) {
                    jSONObject2.put("regid", str);
                    jSONObject.put("xiaomi", jSONObject2);
                    C1551o.m6968b(context, str);
                }
                a.putExtra("push_proxy", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject));
                C1420g.m6423a(context, a);
            }
        } catch (Exception e) {
            C1420g.m6435j(context);
            C1425a.m6440a("PushManagerHandler", e);
        }
    }

    /* renamed from: a */
    public static boolean m6422a(Context context, int i, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.baidu.pushservice.BIND_CACHE", 0);
        Long valueOf = Long.valueOf(sharedPreferences.getLong("currbindtime", 0));
        String string = sharedPreferences.getString("access_token", "");
        String string2 = sharedPreferences.getString("secret_key", "");
        Long valueOf2 = Long.valueOf(sharedPreferences.getLong("version_code", 0));
        if (Long.valueOf(System.currentTimeMillis()).longValue() - valueOf.longValue() > 43200000) {
            sharedPreferences.edit().clear().commit();
            return false;
        }
        if (i == 1) {
            if (!str.equals(string)) {
                return false;
            }
        } else if (i == 0 && !str.equals(string2)) {
            return false;
        }
        return ((long) C1578v.m7108d(context, context.getPackageName())) == valueOf2.longValue();
    }

    /* renamed from: a */
    public static boolean m6423a(Context context, Intent intent) {
        return C1463j.m6642a(context).mo13940a(intent);
    }

    /* renamed from: b */
    public static void m6424b(Context context) {
        Context applicationContext = context.getApplicationContext();
        if (TextUtils.isEmpty(f4987d)) {
            f4987d = C1550n.m6955a(applicationContext, "BD_PROXY_APPID_KEY");
        }
        if (TextUtils.isEmpty(f4988e)) {
            f4988e = C1550n.m6955a(applicationContext, "BD_PROXY_APPKEY_KEY");
        }
        if (TextUtils.isEmpty(f4987d) || TextUtils.isEmpty(f4988e)) {
            C1420g.m6430e(applicationContext);
            return;
        }
        C1578v.m7114e(applicationContext, f4987d, f4988e);
        C1420g.m6434i(applicationContext);
    }

    /* renamed from: b */
    public static void m6425b(Context context, int i) {
        String str = "errorCode:" + i;
        C1426b.m6447b("PushManagerHandler", str, context.getApplicationContext());
        if (context != null) {
            Editor edit;
            C1574s.m7018a(context, 0);
            if (C1578v.m7056E(context)) {
                edit = context.getSharedPreferences(context.getPackageName() + ".push_sync", 5).edit();
                edit.putLong("priority2", 0);
                edit.commit();
            }
            edit = context.getSharedPreferences("com.baidu.pushservice.BIND_CACHE", 0).edit();
            edit.putBoolean("bind_status", false);
            edit.commit();
        }
        Intent intent = new Intent();
        intent.putExtra("method", "method_bind");
        intent.putExtra("error_msg", i);
        intent.putExtra("content", str.getBytes());
        intent.putExtra("bind_status", 0);
        C1425a.m6441b("PushManagerHandler", "> sendResult to " + context.getPackageName() + " ,method:" + "method_bind" + " ,errorCode : " + i + " ,content : " + new String(str));
        C1578v.m7094b(context, intent, "com.baidu.android.pushservice.action.RECEIVE", context.getPackageName());
    }

    /* renamed from: b */
    public static void m6426b(final Context context, final int i, final String str) {
        ModeConfig.getInstance(context.getApplicationContext()).updateConfig(new C1346a() {
            /* renamed from: a */
            public void mo13627a() {
                if (i == 1) {
                    C1550n.m6956a(context, "com.baidu.android.pushservice.PushManager.LOGIN_TYPE", 1);
                    C1550n.m6958a(context, "com.baidu.android.pushservice.PushManager.LONGIN_VALUE", str);
                } else if (i == 0) {
                    C1550n.m6956a(context, "com.baidu.android.pushservice.PushManager.LOGIN_TYPE", 0);
                    C1550n.m6958a(context, "com.baidu.android.pushservice.PushManager.LONGIN_VALUE", str);
                }
                boolean isHuaweiProxyMode = ModeConfig.isHuaweiProxyMode(context);
                boolean isXiaomiProxyMode = ModeConfig.isXiaomiProxyMode(context);
                if (isHuaweiProxyMode) {
                    C1425a.m6442c("PushManagerHandler", "****curMode == ModeConfig.MODE_I_HW****");
                    C1328a.m6007b(context, false);
                    C1578v.m7076a(context, true, false);
                    C1420g.m6428c(context);
                } else if (isXiaomiProxyMode) {
                    C1425a.m6442c("PushManagerHandler", "****curMode == ModeConfig.MODE_I_XM****");
                    C1328a.m6007b(context, false);
                    C1578v.m7076a(context, true, false);
                    C1420g.m6424b(context);
                } else {
                    C1426b.m6445a("PushManagerHandler", "login type = " + i, context.getApplicationContext());
                    if (i == 3 || i == 4 || i == 1 || i == 0) {
                        C1425a.m6442c("PushManagerHandler", "loginType=" + i);
                        if (C1328a.m6006b() > 0) {
                            C1456u.m6614a(context, "039901", 2, str);
                        }
                        PushManager.bindPushService(context, i, str);
                    } else if (i == 2) {
                        PushManager.bind(context, 0);
                    } else {
                        C1426b.m6447b("PushManagerHandler", "Wrong login type, please check!", context.getApplicationContext());
                        if (C1328a.m6006b() > 0) {
                            C1456u.m6614a(context, "039901", -1, "");
                        }
                    }
                }
            }
        });
    }

    /* renamed from: b */
    public static void m6427b(Context context, Intent intent) {
        if (!C1420g.m6423a(context, intent)) {
            context.sendBroadcast(intent);
        }
    }

    /* renamed from: c */
    public static void m6428c(Context context) {
        Context applicationContext = context.getApplicationContext();
        C1578v.m7156z(applicationContext);
        C1420g.m6434i(applicationContext);
    }

    /* renamed from: d */
    public static void m6429d(Context context) {
        if (f4989f != null && f4990g != null && !f4990g.isEmpty()) {
            f4989f.sendEmptyMessage(65553);
            C1420g.m6430e(context);
        }
    }

    /* renamed from: e */
    public static void m6430e(Context context) {
        Intent intent = new Intent();
        String a = PushConstants.m5756a(30602);
        intent.setAction("com.baidu.android.pushservice.action.RECEIVE");
        intent.putExtra("method", "method_bind");
        intent.putExtra("error_msg", 30602);
        intent.putExtra("content", a.getBytes());
        intent.setFlags(32);
        C1578v.m7094b(context, intent, intent.getAction(), context.getPackageName());
    }

    /* renamed from: f */
    public static Intent m6431f(Context context) {
        return C1420g.m6432g(context) ? null : C1578v.m7113e(context);
    }

    /* renamed from: g */
    public static boolean m6432g(Context context) {
        if (context != null) {
            return false;
        }
        C1425a.m6443d("PushManagerHandler", "Context is null!");
        return true;
    }

    /* renamed from: i */
    private static void m6434i(Context context) {
        Context applicationContext = context.getApplicationContext();
        if (f4990g != null && f4990g.size() <= 100) {
            C1407b c1407b = new C1407b(applicationContext);
            f4990g.add(c1407b);
            if (f4989f == null) {
                f4989f = new C1406a(applicationContext);
            }
            f4989f.postDelayed(c1407b, 9000);
        }
    }

    /* renamed from: j */
    private static void m6435j(Context context) {
        String str = "errorCode:10011";
        C1426b.m6447b("PushManagerHandler", str, context.getApplicationContext());
        Intent intent = new Intent();
        intent.putExtra("method", "method_bind");
        intent.putExtra("error_msg", 10011);
        intent.putExtra("content", str.getBytes());
        intent.putExtra("bind_status", 0);
        C1425a.m6441b("PushManagerHandler", "> sendResult to " + context.getPackageName() + ", method:" + "method_bind" + ", errorCode : " + 10011 + ", content : " + str);
        C1578v.m7094b(context, intent, "com.baidu.android.pushservice.action.RECEIVE", context.getPackageName());
    }
}
