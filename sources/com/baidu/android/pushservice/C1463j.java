package com.baidu.android.pushservice;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.baidu.android.pushservice.message.C1512k;
import com.baidu.android.pushservice.message.PublicMsg;
import com.baidu.android.pushservice.message.p040a.C1483b;
import com.baidu.android.pushservice.message.p040a.C1489g;
import com.baidu.android.pushservice.p026j.C1462d;
import com.baidu.android.pushservice.p031c.C1331a;
import com.baidu.android.pushservice.p031c.C1332b;
import com.baidu.android.pushservice.p031c.C1337f;
import com.baidu.android.pushservice.p031c.C1338g;
import com.baidu.android.pushservice.p031c.C1339h;
import com.baidu.android.pushservice.p031c.C1340i;
import com.baidu.android.pushservice.p031c.C1341j;
import com.baidu.android.pushservice.p031c.C1342k;
import com.baidu.android.pushservice.p031c.C1343l;
import com.baidu.android.pushservice.p033e.C1361a;
import com.baidu.android.pushservice.p033e.C1364aa;
import com.baidu.android.pushservice.p033e.C1368ac;
import com.baidu.android.pushservice.p033e.C1369ad;
import com.baidu.android.pushservice.p033e.C1372f;
import com.baidu.android.pushservice.p033e.C1374h;
import com.baidu.android.pushservice.p033e.C1375i;
import com.baidu.android.pushservice.p033e.C1376j;
import com.baidu.android.pushservice.p033e.C1377k;
import com.baidu.android.pushservice.p033e.C1378l;
import com.baidu.android.pushservice.p033e.C1379m;
import com.baidu.android.pushservice.p033e.C1380n;
import com.baidu.android.pushservice.p033e.C1381o;
import com.baidu.android.pushservice.p033e.C1382p;
import com.baidu.android.pushservice.p033e.C1383q;
import com.baidu.android.pushservice.p033e.C1384r;
import com.baidu.android.pushservice.p033e.C1390w;
import com.baidu.android.pushservice.p033e.C1391x;
import com.baidu.android.pushservice.p033e.C1392y;
import com.baidu.android.pushservice.p033e.C1393z;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p036h.C1426b;
import com.baidu.android.pushservice.p037i.C1451p;
import com.baidu.android.pushservice.p037i.C1456u;
import com.baidu.android.pushservice.util.C1568q;
import com.baidu.android.pushservice.util.C1578v;
import com.facebook.internal.NativeProtocol;
import com.newrelic.agent.android.util.SafeJsonPrimitive;

/* renamed from: com.baidu.android.pushservice.j */
public class C1463j {
    /* renamed from: c */
    private static C1463j f5155c;
    /* renamed from: a */
    private Context f5156a;
    /* renamed from: b */
    private C1456u f5157b;

    private C1463j(Context context) {
        this.f5156a = context;
        C1332b.m6020a(context);
        C1475k.m6721a(context);
        C1462d.m6637a();
    }

    /* renamed from: A */
    private void m6640A(Intent intent) {
        if (this.f5157b == null) {
            this.f5157b = new C1456u(this.f5156a);
        }
        this.f5157b.mo13930a(intent.getBooleanExtra("force_send", false), null);
    }

    /* renamed from: B */
    private void m6641B(Intent intent) {
        PushSettings.m5875a(this.f5156a, 0);
    }

    /* renamed from: a */
    public static C1463j m6642a(Context context) {
        if (f5155c == null) {
            f5155c = new C1463j(context);
        }
        return f5155c;
    }

    /* renamed from: b */
    private void m6643b(Intent intent) {
        C1378l c1378l = new C1378l(intent);
        String stringExtra = intent.getStringExtra("bind_name");
        int intExtra = intent.getIntExtra("bind_status", 0);
        int intExtra2 = intent.getIntExtra("push_sdk_version", 0);
        C1426b.m6445a("RegistrationService", "<<< METHOD_BIND ", this.f5156a);
        C1425a.m6442c("RegistrationService", "packageName:" + c1378l.f4833e + ", bindName:" + stringExtra + ", bindStatus:" + intExtra);
        C1425a.m6442c("RegistrationService", "accessToken:" + c1378l.f4832d);
        C1425a.m6442c("RegistrationService", "apiKey:" + c1378l.f4837i);
        C1578v.m7095b("RegistrationService#handleBind#METHOD_BIND request arrive at " + System.currentTimeMillis(), this.f5156a);
        String e = C1332b.m6020a(this.f5156a).mo13601e(c1378l.f4833e);
        if (TextUtils.isEmpty(c1378l.f4837i) || !C1332b.m6020a(this.f5156a).mo13598b(c1378l.f4833e, c1378l.f4837i) || TextUtils.isEmpty(e)) {
            boolean a;
            C1578v.m7095b("RegistrationService#handleBind#METHOD_BIND request start at " + System.currentTimeMillis(), this.f5156a);
            if (C1328a.m6006b() > 0) {
                C1456u.m6614a(this.f5156a, "039902", 0, e);
            }
            if (intent.hasExtra("bind_notify_status")) {
                String stringExtra2 = intent.getStringExtra("bind_notify_status");
                a = mo13941a(new C1372f(c1378l, this.f5156a, intExtra, stringExtra, intExtra2, stringExtra2));
                C1425a.m6442c("RegistrationService", " notifystatus = " + stringExtra2);
                C1578v.m7095b("submitApiProcessor for bind=" + c1378l.toString(), this.f5156a);
            } else {
                a = mo13941a(new C1372f(c1378l, this.f5156a, intExtra, stringExtra, intExtra2));
                C1578v.m7095b("submitApiProcessor for bind=" + c1378l.toString(), this.f5156a);
            }
            if (!a) {
                new Thread(new C1372f(c1378l, this.f5156a, intExtra, stringExtra, intExtra2)).start();
                C1425a.m6442c("RegistrationService", "submitApiProcessor failed bind " + c1378l.toString());
                C1578v.m7095b("submitApiProcessor failed bind " + c1378l.toString(), this.f5156a);
                return;
            }
            return;
        }
        Intent intent2 = new Intent();
        intent2.putExtra("method", c1378l.f4829a);
        intent2.putExtra("error_msg", 0);
        intent2.putExtra("content", e.getBytes());
        intent2.putExtra("bind_status", intExtra);
        C1425a.m6442c("RegistrationService", "> sendResult to " + c1378l.f4833e + " ,method:" + c1378l.f4829a + " ,errorCode : " + 0 + " ,content : " + e);
        if (C1328a.m6006b() > 0) {
            C1456u.m6614a(this.f5156a, "039902", 2, e);
        }
        C1578v.m7094b(this.f5156a, intent2, "com.baidu.android.pushservice.action.RECEIVE", c1378l.f4833e);
        C1578v.m7095b("RegistrationService#handleBind#returned by cacheContent = " + e, this.f5156a);
        C1425a.m6443d("RegistrationService", "Already binded, no need to bind anymore");
    }

    /* renamed from: c */
    private void m6644c(Intent intent) {
        C1343l.m6060a(this.f5156a).mo13604a((C1331a) new C1342k(intent.getStringExtra("secret_key")), true);
        C1378l c1378l = new C1378l(intent);
        String stringExtra = intent.getStringExtra("bind_name");
        int intExtra = intent.getIntExtra("bind_status", 0);
        int intExtra2 = intent.getIntExtra("push_sdk_version", 0);
        C1426b.m6445a("RegistrationService", "<<< METHOD_WEB_APP_BIND ", this.f5156a);
        C1425a.m6442c("RegistrationService", "packageName:" + c1378l.f4833e + ", bindName:" + stringExtra + ", bindStatus:" + intExtra);
        C1425a.m6442c("RegistrationService", "accessToken:" + c1378l.f4832d);
        C1425a.m6442c("RegistrationService", "apiKey:" + c1378l.f4837i);
        mo13941a(new C1372f(c1378l, this.f5156a, intExtra, stringExtra, intExtra2));
    }

    /* renamed from: d */
    private void m6645d(Intent intent) {
        C1338g.m6051a(this.f5156a).mo13604a((C1331a) new C1337f(intent.getStringExtra("secret_key")), true);
        C1378l c1378l = new C1378l(intent);
        String stringExtra = intent.getStringExtra("bind_name");
        int intExtra = intent.getIntExtra("bind_status", 0);
        int intExtra2 = intent.getIntExtra("push_sdk_version", 0);
        C1426b.m6445a("RegistrationService", "<<< METHOD_LAPP_BIND ", this.f5156a);
        C1425a.m6442c("RegistrationService", "packageName:" + c1378l.f4833e + ", bindName:" + stringExtra + ", bindStatus:" + intExtra);
        C1425a.m6442c("RegistrationService", "accessToken:" + c1378l.f4832d);
        C1425a.m6442c("RegistrationService", "apiKey:" + c1378l.f4837i);
        mo13941a(new C1372f(c1378l, this.f5156a, intExtra, stringExtra, intExtra2));
    }

    /* renamed from: e */
    private void m6646e(Intent intent) {
        String stringExtra = intent.getStringExtra("secret_key");
        C1337f c1337f = (C1337f) C1338g.m6051a(this.f5156a).mo13610c(stringExtra);
        Intent intent2 = new Intent();
        intent2.setAction("com.baidu.android.pushservice.action.lapp.RECEIVE");
        intent2.putExtra("method", "method_get_lapp_bind_state");
        intent2.putExtra("secret_key", stringExtra);
        if (c1337f != null) {
            intent2.putExtra("lapp_bind_state", true);
        } else {
            intent2.putExtra("lapp_bind_state", false);
        }
        this.f5156a.sendBroadcast(intent2);
    }

    /* renamed from: f */
    private void m6647f(Intent intent) {
        C1378l c1378l = new C1378l(intent);
        String stringExtra = intent.getStringExtra("bind_name");
        int intExtra = intent.getIntExtra("bind_status", 0);
        int intExtra2 = intent.getIntExtra("push_sdk_version", 0);
        int intExtra3 = intent.getIntExtra("sdk_client_version", 0);
        C1340i c1340i = new C1340i(c1378l.f4837i, c1378l.f4833e);
        c1340i.mo13585a(intExtra3);
        C1341j.m6054a(this.f5156a).mo13604a((C1331a) c1340i, true);
        C1426b.m6445a("RegistrationService", "<<< METHOD_SDK_BIND ", this.f5156a);
        C1425a.m6442c("RegistrationService", "packageName:" + c1378l.f4833e + ", bindName:" + stringExtra + ", bindStatus:" + intExtra);
        C1425a.m6442c("RegistrationService", "accessToken:" + c1378l.f4832d);
        mo13941a(new C1372f(c1378l, this.f5156a, intExtra, stringExtra, intExtra2));
    }

    /* renamed from: g */
    private void m6648g(Intent intent) {
        C1378l c1378l = new C1378l(intent);
        C1426b.m6445a("RegistrationService", "<<< METHOD_UNBIND ", this.f5156a);
        C1425a.m6442c("RegistrationService", "packageName:" + c1378l.f4833e);
        C1425a.m6442c("RegistrationService", "accessToken:" + c1378l.f4832d);
        C1425a.m6442c("RegistrationService", "apiKey:" + c1378l.f4837i);
        if (!(TextUtils.isEmpty(c1378l.f4833e) || TextUtils.isEmpty(c1378l.f4837i))) {
            C1339h c = C1332b.m6020a(this.f5156a).mo13599c(c1378l.f4833e);
            if (!(c == null || TextUtils.isEmpty(c.mo13584a()))) {
                c1378l.f4834f = c.mo13584a();
            }
            C1332b.m6020a(this.f5156a).mo13602f(c1378l.f4833e);
        }
        mo13941a(new C1368ac(c1378l, this.f5156a));
    }

    /* renamed from: h */
    private void m6649h(Intent intent) {
        C1378l c1378l = new C1378l(intent);
        C1426b.m6445a("RegistrationService", "<<< METHOD_SDK_UNBIND ", this.f5156a);
        C1425a.m6442c("RegistrationService", "packageName:" + c1378l.f4833e);
        C1425a.m6442c("RegistrationService", "accessToken:" + c1378l.f4832d);
        C1425a.m6442c("RegistrationService", "apiKey:" + c1378l.f4837i);
        mo13941a(new C1368ac(c1378l, this.f5156a));
    }

    /* renamed from: i */
    private void m6650i(Intent intent) {
        C1378l c1378l = new C1378l(intent);
        C1425a.m6442c("RegistrationService", "<<< METHOD_LAPP_UNBIND ");
        C1425a.m6442c("RegistrationService", "packageName:" + c1378l.f4833e);
        C1425a.m6442c("RegistrationService", "accessToken:" + c1378l.f4832d);
        C1425a.m6442c("RegistrationService", "apiKey:" + c1378l.f4837i);
        mo13941a(new C1368ac(c1378l, this.f5156a));
    }

    /* renamed from: j */
    private boolean m6651j(Intent intent) {
        String stringExtra = intent.getStringExtra("package_name");
        String stringExtra2 = intent.getStringExtra("app_id");
        if (TextUtils.isEmpty(stringExtra2)) {
            C1339h c = C1332b.m6020a(this.f5156a).mo13599c(stringExtra);
            if (c != null) {
                stringExtra2 = c.mo13584a();
            }
        }
        String stringExtra3 = intent.getStringExtra("user_id");
        C1425a.m6442c("RegistrationService", "<<< METHOD_UNBIND_APP ");
        C1425a.m6442c("RegistrationService", "packageName:" + stringExtra);
        C1425a.m6442c("RegistrationService", "appid:" + stringExtra2);
        C1425a.m6442c("RegistrationService", "userid:" + stringExtra3);
        PushSettings.m5883c(this.f5156a, stringExtra);
        C1378l c1378l = new C1378l();
        c1378l.f4829a = "com.baidu.android.pushservice.action.UNBINDAPP";
        if (!TextUtils.isEmpty(stringExtra)) {
            c1378l.f4833e = stringExtra;
        }
        if (!(TextUtils.isEmpty(stringExtra2) || SafeJsonPrimitive.NULL_STRING.equals(stringExtra2))) {
            c1378l.f4834f = stringExtra2;
        }
        if (!(TextUtils.isEmpty(stringExtra3) || SafeJsonPrimitive.NULL_STRING.equals(stringExtra3))) {
            c1378l.f4835g = stringExtra3;
        }
        if (!TextUtils.isEmpty(c1378l.f4833e)) {
            C1339h c2 = C1332b.m6020a(this.f5156a).mo13599c(c1378l.f4833e);
            if (!(c2 == null || TextUtils.isEmpty(c2.mo13584a()))) {
                c1378l.f4834f = c2.mo13584a();
            }
            C1332b.m6020a(this.f5156a).mo13602f(c1378l.f4833e);
        }
        return mo13941a(new C1369ad(c1378l, this.f5156a));
    }

    /* renamed from: k */
    private void m6652k(Intent intent) {
        C1378l c1378l = new C1378l(intent);
        int intExtra = intent.getIntExtra("fetch_type", 1);
        int intExtra2 = intent.getIntExtra("fetch_num", 1);
        C1426b.m6445a("RegistrationService", "<<< METHOD_FETCH ", this.f5156a);
        C1425a.m6442c("RegistrationService", "packageName:" + c1378l.f4833e);
        C1425a.m6442c("RegistrationService", "accessToken:" + c1378l.f4832d);
        mo13941a(new C1379m(c1378l, this.f5156a, intExtra, intExtra2));
    }

    /* renamed from: l */
    private void m6653l(Intent intent) {
        C1378l c1378l = new C1378l(intent);
        C1426b.m6445a("RegistrationService", "<<< METHOD_COUNT ", this.f5156a);
        C1425a.m6442c("RegistrationService", "packageName:" + c1378l.f4833e);
        C1425a.m6442c("RegistrationService", "accessToken:" + c1378l.f4832d);
        mo13941a(new C1374h(c1378l, this.f5156a));
    }

    /* renamed from: m */
    private void m6654m(Intent intent) {
        C1378l c1378l = new C1378l(intent);
        String[] stringArrayExtra = intent.getStringArrayExtra("msg_ids");
        C1426b.m6445a("RegistrationService", "<<< METHOD_DELETE ", this.f5156a);
        C1425a.m6442c("RegistrationService", "packageName:" + c1378l.f4833e);
        C1425a.m6442c("RegistrationService", "accessToken:" + c1378l.f4832d);
        mo13941a(new C1377k(c1378l, this.f5156a, stringArrayExtra));
    }

    /* renamed from: n */
    private void m6655n(Intent intent) {
        C1378l c1378l = new C1378l(intent);
        String stringExtra = intent.getStringExtra("gid");
        C1426b.m6445a("RegistrationService", "<<< ACTION_GBIND ", this.f5156a);
        C1425a.m6442c("RegistrationService", "packageName:" + c1378l.f4833e + ", gid:" + stringExtra);
        C1425a.m6442c("RegistrationService", "accessToken:" + c1378l.f4832d);
        mo13941a(new C1381o(c1378l, this.f5156a, stringExtra));
    }

    /* renamed from: o */
    private void m6656o(Intent intent) {
        C1378l c1378l = new C1378l(intent);
        String stringExtra = intent.getStringExtra("tags");
        C1426b.m6445a("RegistrationService", "<<< ACTION_SET_TAGS ", this.f5156a);
        C1425a.m6442c("RegistrationService", "packageName:" + c1378l.f4833e + ", gid:" + stringExtra);
        C1425a.m6442c("RegistrationService", "accessToken:" + c1378l.f4832d);
        mo13941a(new C1364aa(c1378l, this.f5156a, stringExtra));
    }

    /* renamed from: p */
    private void m6657p(Intent intent) {
        C1378l c1378l = new C1378l(intent);
        String stringExtra = intent.getStringExtra("tags");
        C1426b.m6445a("RegistrationService", "<<< ACTION_GBIND ", this.f5156a);
        C1425a.m6442c("RegistrationService", "packageName:" + c1378l.f4833e + ", gid:" + stringExtra);
        C1425a.m6442c("RegistrationService", "accessToken:" + c1378l.f4832d);
        mo13941a(new C1376j(c1378l, this.f5156a, stringExtra));
    }

    /* renamed from: q */
    private void m6658q(Intent intent) {
        C1378l c1378l = new C1378l(intent);
        String stringExtra = intent.getStringExtra("gid");
        C1426b.m6445a("RegistrationService", "<<< ACTION_GUNBIND ", this.f5156a);
        C1425a.m6442c("RegistrationService", "packageName:" + c1378l.f4833e + ", gid:" + stringExtra);
        C1425a.m6442c("RegistrationService", "accessToken:" + c1378l.f4832d);
        mo13941a(new C1384r(c1378l, this.f5156a, stringExtra));
    }

    /* renamed from: r */
    private void m6659r(Intent intent) {
        C1378l c1378l = new C1378l(intent);
        String stringExtra = intent.getStringExtra("gid");
        C1426b.m6445a("RegistrationService", "<<< METHOD_GINFO ", this.f5156a);
        C1425a.m6442c("RegistrationService", "packageName:" + c1378l.f4833e + ", gid:" + stringExtra);
        C1425a.m6442c("RegistrationService", "accessToken:" + c1378l.f4832d);
        mo13941a(new C1382p(c1378l, this.f5156a, stringExtra));
    }

    /* renamed from: s */
    private void m6660s(Intent intent) {
        C1378l c1378l = new C1378l(intent);
        C1426b.m6445a("RegistrationService", "<<< METHOD_LISTTAGS ", this.f5156a);
        C1425a.m6442c("RegistrationService", "packageName:" + c1378l.f4833e);
        C1425a.m6442c("RegistrationService", "accessToken:" + c1378l.f4832d);
        mo13941a(new C1390w(c1378l, this.f5156a));
    }

    /* renamed from: t */
    private void m6661t(Intent intent) {
        C1378l c1378l = new C1378l(intent);
        C1426b.m6445a("RegistrationService", "<<< METHOD_GLIST ", this.f5156a);
        C1425a.m6442c("RegistrationService", "packageName:" + c1378l.f4833e);
        C1425a.m6442c("RegistrationService", "accessToken:" + c1378l.f4832d);
        mo13941a(new C1383q(c1378l, this.f5156a));
    }

    /* renamed from: u */
    private void m6662u(Intent intent) {
        C1378l c1378l = new C1378l(intent);
        String stringExtra = intent.getStringExtra("gid");
        int intExtra = intent.getIntExtra("group_fetch_type", 1);
        int intExtra2 = intent.getIntExtra("group_fetch_num", 1);
        C1426b.m6445a("RegistrationService", "<<< METHOD_FETCHGMSG ", this.f5156a);
        C1425a.m6442c("RegistrationService", "packageName:" + c1378l.f4833e);
        C1425a.m6442c("RegistrationService", "accessToken:" + c1378l.f4832d);
        C1425a.m6442c("RegistrationService", "gid:" + stringExtra);
        C1425a.m6442c("RegistrationService", "fetchType:" + intExtra);
        C1425a.m6442c("RegistrationService", "fetchNum:" + intExtra2);
        mo13941a(new C1380n(c1378l, this.f5156a, stringExtra, intExtra, intExtra2));
    }

    /* renamed from: v */
    private void m6663v(Intent intent) {
        C1378l c1378l = new C1378l(intent);
        String stringExtra = intent.getStringExtra("gid");
        C1426b.m6445a("RegistrationService", "<<< METHOD_COUNTGMSG ", this.f5156a);
        C1425a.m6442c("RegistrationService", "packageName:" + c1378l.f4833e);
        C1425a.m6442c("RegistrationService", "accessToken:" + c1378l.f4832d);
        C1425a.m6442c("RegistrationService", "gid:" + stringExtra);
        mo13941a(new C1375i(c1378l, this.f5156a, stringExtra));
    }

    /* renamed from: w */
    private void m6664w(Intent intent) {
        C1378l c1378l = new C1378l(intent);
        C1426b.m6445a("RegistrationService", "<<< METHOD_ONLINE ", this.f5156a);
        C1425a.m6442c("RegistrationService", "packageName:" + c1378l.f4833e);
        C1425a.m6442c("RegistrationService", "accessToken:" + c1378l.f4832d);
        mo13941a(new C1391x(c1378l, this.f5156a));
    }

    /* renamed from: x */
    private void m6665x(Intent intent) {
        C1378l c1378l = new C1378l(intent);
        C1426b.m6445a("RegistrationService", "<<< METHOD_SEND ", this.f5156a);
        C1425a.m6442c("RegistrationService", "packageName:" + c1378l.f4833e);
        C1425a.m6442c("RegistrationService", "accessToken:" + c1378l.f4832d);
        mo13941a(new C1392y(c1378l, this.f5156a, intent.getStringExtra("push_ msg")));
    }

    /* renamed from: y */
    private void m6666y(Intent intent) {
        C1378l c1378l = new C1378l(intent);
        C1426b.m6445a("RegistrationService", "<<< METHOD_SEND_MSG_TO_USER ", this.f5156a);
        C1425a.m6442c("RegistrationService", "packageName:" + c1378l.f4833e);
        C1425a.m6442c("RegistrationService", "accessToken:" + c1378l.f4832d);
        mo13941a(new C1393z(c1378l, this.f5156a, intent.getStringExtra("app_id"), intent.getStringExtra("user_id"), intent.getStringExtra("push_ msg_key"), intent.getStringExtra("push_ msg")));
    }

    /* renamed from: z */
    private void m6667z(Intent intent) {
        C1425a.m6442c("RegistrationService", "<<< handleSendAppStat ");
        if (this.f5157b == null) {
            this.f5157b = new C1456u(this.f5156a);
        }
        this.f5157b.mo13929a();
        this.f5157b.mo13930a(false, null);
    }

    /* renamed from: a */
    public boolean mo13940a(Intent intent) {
        String str = null;
        if (intent == null || TextUtils.isEmpty(intent.getAction())) {
            return false;
        }
        C1425a.m6442c("RegistrationService", "RegistrationSerice handleIntent : " + intent);
        String action = intent.getAction();
        C1578v.m7095b("handleIntent#action = " + action, this.f5156a);
        String stringExtra;
        if ("com.baidu.android.pushservice.action.OPENDEBUGMODE".equals(action)) {
            PushSettings.enableDebugMode(this.f5156a, true);
            C1426b.m6445a("RegistrationService", "<<<debugMode is open", this.f5156a);
            return true;
        } else if ("com.baidu.android.pushservice.action.CLOSEDEBUGMODE".equals(action)) {
            PushSettings.enableDebugMode(this.f5156a, false);
            C1426b.m6445a("RegistrationService", "<<<debugMode is close", this.f5156a);
            return true;
        } else if ("com.baidu.pushservice.action.publicmsg.CLICK_V2".equals(action) || "com.baidu.pushservice.action.publicmsg.DELETE_V2".equals(action)) {
            ((PublicMsg) intent.getParcelableExtra("public_msg")).handle(this.f5156a, action, intent.getData().getHost());
            return true;
        } else if ("com.baidu.android.pushservice.action.privatenotification.CLICK".equals(action) || "com.baidu.android.pushservice.action.privatenotification.DELETE".equals(action)) {
            ((PublicMsg) intent.getParcelableExtra("public_msg")).handlePrivateNotification(this.f5156a, action, intent.getStringExtra("msg_id"), intent.getStringExtra("app_id"));
            return true;
        } else if ("com.baidu.android.pushservice.action.passthrough.notification.CLICK".equals(action) || "com.baidu.android.pushservice.action.passthrough.notification.DELETE".equals(action) || "com.baidu.android.pushservice.action.passthrough.notification.NOTIFIED".equals(action)) {
            C1578v.m7095b("push_passthrough: receive  click delete and notified action", this.f5156a);
            C1425a.m6442c("RegistrationService", "handle passthrough notification " + action);
            stringExtra = intent.hasExtra("app_id") ? intent.getStringExtra("app_id") : null;
            if (intent.hasExtra("msg_id")) {
                str = intent.getStringExtra("msg_id");
            }
            C1451p.m6591a(this.f5156a, str, stringExtra, action);
            return true;
        } else if ("com.baidu.android.pushservice.action.adnotification.ADCLICK".equals(action) || "com.baidu.android.pushservice.action.adnotification.ADDELETE".equals(action) || "com.baidu.android.pushservice.action.adnotification.ADCLICKFAILED".equals(action)) {
            C1578v.m7095b("pushadvertise: receive  click or delete action", this.f5156a);
            ((PublicMsg) intent.getParcelableExtra("ad_msg")).handleADNotification(this.f5156a, action, intent.getStringExtra("msg_id"), intent.getStringExtra("app_id"), intent.getStringExtra("action_type"), intent.getStringExtra("click_url"), intent.getStringExtra("advertise_Style"));
            return true;
        } else {
            if ("com.baidu.android.pushservice.action.adnotification.ADSHOW".equals(action)) {
                if (C1328a.m6006b() > 0) {
                    C1578v.m7095b("pushadvertise: receive show action  com.baidu.android.pushservice.action.adnotification.ADSHOW", this.f5156a);
                }
                ((PublicMsg) intent.getParcelableExtra("ad_msg")).handleADShowNotification(this.f5156a, intent.getStringExtra("message_id"), intent.getStringExtra("app_id"), intent.getStringExtra("action_type"), intent.getStringExtra("advertisestyle"));
            }
            if ("com.baidu.android.pushservice.action.setadswitch.ADFAILED".equals(action)) {
                if (C1328a.m6006b() > 0) {
                    C1578v.m7095b("pushadvertise: receive  action  com.baidu.android.pushservice.action.setadswitch.ADFAILED", this.f5156a);
                }
                PublicMsg.insertADSetEnableFailed(this.f5156a, "010504", intent.getIntExtra("ad_status", 0), intent.getStringExtra("app_id"), intent.getStringExtra("channel_id"), intent.getStringExtra("cuid"), intent.getShortExtra("sdkversion", (short) 0));
            }
            if ("com.baidu.android.pushservice.action.ADACKERROR".equals(action)) {
                if (C1328a.m6006b() > 0) {
                    C1578v.m7095b("pushadvertise: receive  action  com.baidu.android.pushservice.action.ADACKERROR", this.f5156a);
                }
                PublicMsg.insertADSendACKFailed(this.f5156a, "010505", intent.getStringExtra("app_id"), intent.getStringExtra("channel_id"), intent.getStringExtra("cuid"), intent.getStringExtra("ad_id"), intent.getIntExtra(NativeProtocol.BRIDGE_ARG_ERROR_CODE, 0), intent.getStringExtra("error_msg"));
            }
            if ("com.baidu.android.pushservice.action.media.CLICK".equals(action) || "com.baidu.android.pushservice.action.media.DELETE".equals(action)) {
                ((PublicMsg) intent.getParcelableExtra("public_msg")).handleRichMediaNotification(this.f5156a, action, intent.getStringExtra("app_id"));
                return true;
            } else if ("com.baidu.android.pushservice.action.lightapp.notification.CLICK".equals(action)) {
                C1489g.m6756a(this.f5156a, intent);
                return true;
            } else if ("com.baidu.android.pushservice.action.lightapp.notification.DELETE".equals(action)) {
                C1489g.m6765b(this.f5156a, intent);
                return true;
            } else {
                if ("com.baidu.android.pushservice.action.alarm.message".equals(action)) {
                    C1512k c1512k = (C1512k) intent.getSerializableExtra("tinyMessageHead");
                    byte[] byteArrayExtra = intent.getByteArrayExtra("msgBody");
                    c1512k.mo13998a(false);
                    if (C1568q.m7005c(this.f5156a, c1512k.mo14009g()).f5062f == 0) {
                        C1568q.m7008d(this.f5156a, c1512k.mo14009g());
                        C1425a.m6442c("RegistrationService", "message is invalid ");
                        return true;
                    }
                    C1483b c1483b = new C1483b(this.f5156a);
                    if (c1483b != null) {
                        c1483b.mo13966a(c1512k, byteArrayExtra);
                        C1425a.m6442c("RegistrationService", "handle message that is not alarm message ");
                    }
                }
                if ("com.baidu.pushservice.action.TOKEN".equals(action)) {
                    C1426b.m6445a("RegistrationService", "<<< ACTION_TOKEN ", this.f5156a);
                    if (C1475k.m6721a(this.f5156a).mo13950c()) {
                        return true;
                    }
                    C1475k.m6721a(this.f5156a).mo13947a(this.f5156a, true, null);
                    return true;
                } else if (!"com.baidu.android.pushservice.action.METHOD".equals(action)) {
                    return false;
                } else {
                    boolean z;
                    stringExtra = intent.getStringExtra("method");
                    if ("method_bind".equals(stringExtra)) {
                        m6643b(intent);
                        z = true;
                    } else if ("method_webapp_bind_from_deeplink".equals(stringExtra)) {
                        PushManager.startWork(this.f5156a, 3, intent.getStringExtra("com.baidu.pushservice.webapp.apikey"));
                        z = true;
                    } else if ("method_deal_webapp_bind_intent".equals(stringExtra)) {
                        m6644c(intent);
                        z = true;
                    } else if ("method_deal_lapp_bind_intent".equals(stringExtra)) {
                        m6645d(intent);
                        z = true;
                    } else if ("method_get_lapp_bind_state".equals(stringExtra)) {
                        m6646e(intent);
                        z = true;
                    } else if ("method_sdk_bind".equals(stringExtra)) {
                        m6647f(intent);
                        z = true;
                    } else if ("method_unbind".equals(stringExtra)) {
                        m6648g(intent);
                        z = true;
                    } else if ("method_sdk_unbind".equals(stringExtra)) {
                        m6649h(intent);
                        z = true;
                    } else if ("method_lapp_unbind".equals(stringExtra)) {
                        m6650i(intent);
                        z = true;
                    } else if ("com.baidu.android.pushservice.action.UNBINDAPP".equals(stringExtra)) {
                        m6651j(intent);
                        z = true;
                    } else if ("method_fetch".equals(stringExtra)) {
                        m6652k(intent);
                        z = true;
                    } else if ("method_count".equals(stringExtra)) {
                        m6653l(intent);
                        z = true;
                    } else if ("method_delete".equals(stringExtra)) {
                        m6654m(intent);
                        z = true;
                    } else if ("method_gbind".equals(stringExtra)) {
                        m6655n(intent);
                        z = true;
                    } else if ("method_set_tags".equals(stringExtra) || "method_set_sdk_tags".equals(stringExtra) || "method_set_lapp_tags".equals(stringExtra)) {
                        m6656o(intent);
                        z = true;
                    } else if ("method_del_tags".equals(stringExtra) || "method_del_sdk_tags".equals(stringExtra) || "method_del_lapp_tags".equals(stringExtra)) {
                        m6657p(intent);
                        z = true;
                    } else if ("method_gunbind".equals(stringExtra)) {
                        m6658q(intent);
                        z = true;
                    } else if ("method_ginfo".equals(stringExtra)) {
                        m6659r(intent);
                        z = true;
                    } else if ("method_glist".equals(stringExtra)) {
                        m6661t(intent);
                        z = true;
                    } else if ("method_listtags".equals(stringExtra) || "method_list_sdk_tags".equals(stringExtra) || "method_list_lapp_tags".equals(stringExtra)) {
                        m6660s(intent);
                        z = true;
                    } else if ("method_fetchgmsg".equals(stringExtra)) {
                        m6662u(intent);
                        z = true;
                    } else if ("method_countgmsg".equals(stringExtra)) {
                        m6663v(intent);
                        z = true;
                    } else if ("method_online".equals(stringExtra)) {
                        m6664w(intent);
                        z = true;
                    } else if ("method_send".equals(stringExtra)) {
                        m6665x(intent);
                        z = true;
                    } else if ("com.baidu.android.pushservice.action.SEND_APPSTAT".equals(stringExtra)) {
                        m6667z(intent);
                        z = true;
                    } else if ("com.baidu.android.pushservice.action.SEND_LBS".equals(stringExtra)) {
                        m6640A(intent);
                        z = true;
                    } else if ("com.baidu.android.pushservice.action.ENBALE_APPSTAT".equals(stringExtra)) {
                        m6641B(intent);
                        z = true;
                    } else if ("method_send_msg_to_user".equals(stringExtra)) {
                        m6666y(intent);
                        z = true;
                    } else {
                        z = false;
                    }
                    return z;
                }
            }
        }
    }

    /* renamed from: a */
    public boolean mo13941a(C1361a c1361a) {
        try {
            C1462d.m6637a().mo13938a(c1361a);
            return true;
        } catch (Exception e) {
            C1426b.m6446a("RegistrationService", e, this.f5156a);
            return false;
        }
    }
}
