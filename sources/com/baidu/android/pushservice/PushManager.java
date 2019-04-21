package com.baidu.android.pushservice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import com.baidu.android.pushservice.config.ModeConfig;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p036h.C1426b;
import com.baidu.android.pushservice.p037i.C1456u;
import com.baidu.android.pushservice.util.C1577u;
import com.baidu.android.pushservice.util.C1578v;
import org.json.JSONException;

@SuppressLint({"WorldReadableFiles"})
public class PushManager {
    public static void bind(Context context, int i) {
        Intent a = C1420g.m6418a(context, i);
        if (a != null) {
            C1426b.m6445a("PushManager", "a bind intent send", context.getApplicationContext());
            C1420g.m6423a(context, a);
            C1578v.m7095b("Bind by selfEventHandler", context);
        }
    }

    public static void bindPushService(Context context, int i, String str) {
        C1578v.m7095b("startWork at time of " + System.currentTimeMillis(), context);
        C1577u.m7044a(context);
        if (i == 4) {
            lightAppBind(context, 0, str);
        } else if (i == 3) {
            webAppBind(context, 0, str);
        } else {
            bind(context, 0);
        }
    }

    private static void lightAppBind(Context context, int i, String str) {
        Intent f = C1420g.m6431f(context);
        if (f != null) {
            f.putExtra("method", "method_deal_lapp_bind_intent");
            f.putExtra("bind_name", Build.MODEL);
            f.putExtra("bind_status", i);
            f.putExtra("push_sdk_version", C1328a.m6003a());
            f.putExtra("secret_key", str);
            f.setFlags(f.getFlags() | 32);
            C1420g.m6427b(context, f);
        }
    }

    public static void startWork(Context context, int i, String str) {
        if (!C1420g.m6432g(context)) {
            C1420g.f4984a = i;
            C1420g.f4985b = str;
            C1457i.m6623a(context);
            boolean a = C1578v.m7084a(context, str, false);
            C1426b.m6445a("PushManager", "startWork from " + context.getPackageName() + " check: " + a, context.getApplicationContext());
            C1578v.m7095b("startWork from " + context.getPackageName() + " check: " + a, context);
            if (a) {
                startWork(context, i, str, true);
            } else {
                C1420g.m6425b(context, 10101);
            }
        }
    }

    public static void startWork(Context context, int i, String str, boolean z) {
        if (!C1420g.m6432g(context)) {
            if (ModeConfig.isProxyMode(context)) {
                C1578v.m7052A(context);
                C1578v.m7053B(context);
                C1425a.m6442c("PushManager", "proxyMode, uploadPushAPPInfo & uploadPushLBSAPPListInfo");
            }
            C1328a.m6007b(context, true);
            C1578v.m7076a(context, true, true);
            C1578v.m7095b("startWork from" + context.getPackageName() + " at time of " + System.currentTimeMillis(), context);
            if (z) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("com.baidu.pushservice.BIND_CACHE", 0);
                boolean z2 = sharedPreferences.getBoolean("bind_status", false);
                String string = sharedPreferences.getString("request_id", "");
                String string2 = sharedPreferences.getString("appid", "");
                String string3 = sharedPreferences.getString("channel_id", "");
                String string4 = sharedPreferences.getString("user_id", "");
                String str2 = null;
                try {
                    str2 = C1420g.m6419a(string, string2, string3, string4);
                } catch (JSONException e) {
                    C1426b.m6447b("PushManager", "error " + e.getMessage(), context.getApplicationContext());
                }
                boolean a = C1420g.m6422a(context, i, str);
                if (z2 && a && str2 != null) {
                    Intent intent = new Intent();
                    intent.putExtra("method", "method_bind");
                    intent.putExtra("error_msg", 0);
                    intent.putExtra("content", str2.getBytes());
                    intent.putExtra("bind_status", 0);
                    C1426b.m6445a("PushManager", "new startWork> sendResult to " + context.getPackageName() + " ,method:" + "method_bind" + " ,errorCode : " + 0 + " ,content : " + new String(str2), context.getApplicationContext());
                    C1578v.m7094b(context, intent, "com.baidu.android.pushservice.action.RECEIVE", context.getPackageName());
                    if (C1328a.m6006b() > 0) {
                        C1456u.m6614a(context, "039901", 1, str2);
                        return;
                    }
                    return;
                }
            }
            C1420g.m6426b(context, i, str);
        }
    }

    private static void webAppBind(Context context, int i, String str) {
        Intent f = C1420g.m6431f(context);
        if (f != null) {
            f.putExtra("method", "method_deal_webapp_bind_intent");
            f.putExtra("bind_name", Build.MODEL);
            f.putExtra("bind_status", i);
            f.putExtra("push_sdk_version", C1328a.m6003a());
            f.putExtra("secret_key", str);
            f.setFlags(f.getFlags() | 32);
            C1420g.m6427b(context, f);
        }
    }
}
