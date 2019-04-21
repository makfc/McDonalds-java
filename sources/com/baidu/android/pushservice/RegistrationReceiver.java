package com.baidu.android.pushservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.baidu.android.pushservice.p026j.C1281c;
import com.baidu.android.pushservice.p026j.C1462d;
import com.baidu.android.pushservice.p031c.C1332b;
import com.baidu.android.pushservice.p031c.C1338g;
import com.baidu.android.pushservice.p031c.C1339h;
import com.baidu.android.pushservice.p031c.C1341j;
import com.baidu.android.pushservice.p031c.C1343l;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.util.C1577u;
import com.baidu.android.pushservice.util.C1578v;

public class RegistrationReceiver extends BroadcastReceiver {
    /* renamed from: a */
    static void m5898a(Context context, C1339h c1339h) {
        Intent intent = new Intent();
        intent.setAction("com.baidu.android.pushservice.action.METHOD");
        intent.putExtra("method", "com.baidu.android.pushservice.action.UNBINDAPP");
        intent.putExtra("package_name", c1339h.mo13589c());
        intent.putExtra("app_id", c1339h.mo13584a());
        intent.putExtra("user_id", c1339h.f4739e);
        C1577u.m7045a(context, intent);
    }

    /* renamed from: e */
    private static void m5902e(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("r_sync_from");
        if (!context.getPackageName().equals(stringExtra)) {
            String stringExtra2 = intent.getStringExtra("r_sync_rdata_v2");
            if (!TextUtils.isEmpty(stringExtra2)) {
                C1425a.m6441b("RegistrationReceiver", "handleRegisterSync rdataV2: " + stringExtra2 + " from: " + stringExtra);
                C1332b.m6020a(context).mo13595a("r_v2", stringExtra2);
            }
        }
    }

    /* renamed from: f */
    private static void m5903f(Context context, Intent intent) {
        if (!context.getPackageName().equals(intent.getStringExtra("r_sync_from"))) {
            String stringExtra = intent.getStringExtra("r_sync_rdata_v2");
            if (!TextUtils.isEmpty(stringExtra)) {
                C1343l.m6060a(context).mo13608a("com.baidu.push.webr", stringExtra);
            }
        }
    }

    /* renamed from: g */
    private static void m5904g(Context context, Intent intent) {
        if (!context.getPackageName().equals(intent.getStringExtra("r_sync_from"))) {
            String stringExtra = intent.getStringExtra("r_sync_rdata_v2");
            if (!TextUtils.isEmpty(stringExtra)) {
                C1338g.m6051a(context).mo13608a("com.baidu.push.lappr", stringExtra);
            }
        }
    }

    /* renamed from: h */
    private static void m5905h(Context context, Intent intent) {
        if (!context.getPackageName().equals(intent.getStringExtra("r_sync_sdk_from"))) {
            String stringExtra = intent.getStringExtra("r_sync_rdata_v2");
            if (!TextUtils.isEmpty(stringExtra)) {
                C1341j.m6054a(context).mo13608a("com.baidu.push.sdkr", stringExtra);
            }
        }
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.intent.action.PACKAGE_REMOVED".equals(action)) {
            if (context.getPackageName().equals(C1578v.m7147u(context))) {
                action = intent.getData().getSchemeSpecificPart();
                boolean booleanExtra = intent.getBooleanExtra("android.intent.extra.REPLACING", false);
                C1425a.m6441b("RegistrationReceiver", "start for ACTION_PACKAGE_REMOVED，replacing：" + booleanExtra + " ,packageName: " + action);
                if (!booleanExtra) {
                    PushSettings.m5883c(context, action);
                }
                C1339h c = C1332b.m6020a(context).mo13599c(action);
                if (!booleanExtra && c != null && !context.getPackageName().equals(c.mo13589c())) {
                    C1425a.m6441b("RegistrationReceiver", "unregister registered push client : " + action);
                    m5898a(context, c);
                    return;
                }
                return;
            }
            C1425a.m6441b("RegistrationReceiver", "not hightest package return");
        } else if ("com.baidu.android.pushservice.action.BIND_SYNC".equals(action)) {
            final Intent intent2 = intent;
            final Context context2 = context;
            C1462d.m6637a().mo13938a(new C1281c("register_sync", (short) 99) {
                /* renamed from: a */
                public void mo13487a() {
                    if (intent2.hasExtra("r_sync_type")) {
                        switch (intent2.getIntExtra("r_sync_type", 0)) {
                            case 0:
                                RegistrationReceiver.m5902e(context2, intent2);
                                return;
                            case 1:
                                RegistrationReceiver.m5903f(context2, intent2);
                                return;
                            case 2:
                                RegistrationReceiver.m5904g(context2, intent2);
                                return;
                            case 3:
                                RegistrationReceiver.m5905h(context2, intent2);
                                return;
                            default:
                                return;
                        }
                    }
                    RegistrationReceiver.m5902e(context2, intent2);
                }
            });
        } else {
            C1577u.m7049b(context, intent);
        }
    }
}
