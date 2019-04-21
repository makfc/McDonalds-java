package com.baidu.android.pushservice.message.p040a;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.baidu.android.pushservice.C1344c;
import com.baidu.android.pushservice.C1358d;
import com.baidu.android.pushservice.PushService;
import com.baidu.android.pushservice.message.PublicMsg;
import com.baidu.android.pushservice.p032d.C1355a;
import com.baidu.android.pushservice.p035g.C1418d;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p037i.C1442i;
import com.baidu.android.pushservice.p037i.C1450o;
import com.baidu.android.pushservice.p037i.C1456u;
import com.baidu.android.pushservice.p037i.p038a.C1432b;
import com.baidu.android.pushservice.util.C1577u;
import com.baidu.android.pushservice.util.C1578v;
import com.facebook.internal.NativeProtocol;
import com.mcdonalds.sdk.modules.notification.PushConstants;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.android.pushservice.message.a.g */
public class C1489g {
    /* renamed from: a */
    public static int m6753a(Context context, String str, String str2, byte[] bArr, PublicMsg publicMsg) {
        if (C1418d.m6363a(context).mo13805a(str)) {
            return 6;
        }
        try {
            JSONObject init = JSONObjectInstrumentation.init(new String(bArr));
            if (!init.isNull("lightapp_ctrl_keys")) {
                JSONObject jSONObject = init.getJSONObject("lightapp_ctrl_keys");
                Object obj = 1;
                int i = 1;
                if (!jSONObject.isNull("app_type") && jSONObject.getInt("app_type") == 1) {
                    return 0;
                }
                Object obj2;
                if (!jSONObject.isNull("is_inner") && jSONObject.getInt("is_inner") == 1) {
                    i = 2;
                }
                if (!jSONObject.isNull("display_in_notification_bar") && jSONObject.getInt("display_in_notification_bar") == 0) {
                    obj = null;
                }
                if (jSONObject.isNull("enter_msg_center") || jSONObject.getInt("enter_msg_center") != 0) {
                    int obj22 = 1;
                } else {
                    obj22 = null;
                }
                if (obj != null && C1489g.m6768b(context, str, i)) {
                    boolean z = true;
                    if (!jSONObject.isNull("is_merge") && jSONObject.getInt("is_merge") == 0) {
                        z = false;
                    }
                    C1489g.m6758a(context, publicMsg, str2, str, i, z);
                }
                if (obj22 != null) {
                    C1418d.m6363a(context).mo13790a(str2, str, publicMsg.mTitle, publicMsg.mDescription, publicMsg.mUrl, i, 1);
                }
                Intent intent = new Intent();
                intent.setAction("com.baidu.android.push.lightapp.NEWMSG");
                jSONObject = new JSONObject();
                jSONObject.put("id", str2);
                jSONObject.put(PushConstants.TITLE_KEY, publicMsg.mTitle);
                jSONObject.put("content", publicMsg.mDescription);
                jSONObject.put("link", publicMsg.mUrl);
                jSONObject.put("status", "1");
                jSONObject.put("type", i);
                jSONObject.put("time", System.currentTimeMillis());
                jSONObject.put("appid", str);
                init = new JSONObject();
                init.put("value", jSONObject);
                intent.putExtra("com.baidu.android.push.lightapp.NEWMSG.EXTRA", !(init instanceof JSONObject) ? init.toString() : JSONObjectInstrumentation.toString(init));
                intent.setFlags(32);
                context.sendBroadcast(intent);
            }
        } catch (JSONException e) {
            C1425a.m6444e("NotificationHandler", "error " + e.getMessage());
        }
        return 1;
    }

    /* renamed from: a */
    public static ComponentName m6754a(Context context, PublicMsg publicMsg, String str, String str2) {
        Intent intent = new Intent();
        intent.putExtra("ad_msg", publicMsg);
        intent.putExtra("pushService_package_name", context.getPackageName());
        intent.putExtra("message_id", str);
        intent.putExtra("app_id", str2);
        intent.putExtra("service_name", "com.baidu.android.pushservice.PushService");
        return C1578v.m7102c(context, intent, "com.baidu.android.pushservice.action.advertise.notification.SHOW", publicMsg.mPkgName);
    }

    /* renamed from: a */
    public static String m6755a(Context context, String str, int i) {
        ArrayList b = C1418d.m6363a(context).mo13812b(str, i);
        if (b.size() <= 0) {
            return null;
        }
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= b.size()) {
                return null;
            }
            String str2 = (String) b.get(i3);
            if (!TextUtils.isEmpty(str2) && C1578v.m7106c(context, str2)) {
                if (!str2.startsWith("com.baidu.searchbox") && !str2.startsWith("com.baidu.voiceassistant")) {
                    return str2;
                }
                try {
                    int i4 = context.getPackageManager().getPackageInfo(str2, 1).versionCode;
                    if (i4 <= 16787720) {
                        C1425a.m6442c("NotificationHandler", " lightapp msg not blocked by searchbox " + i4);
                        return str2;
                    }
                } catch (NameNotFoundException e) {
                    C1425a.m6444e("NotificationHandler", " searchbox not found ");
                }
            }
            i2 = i3 + 1;
        }
    }

    /* renamed from: a */
    public static void m6756a(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("appid");
        String stringExtra2 = intent.getStringExtra("msgid");
        String stringExtra3 = intent.getStringExtra(NativeProtocol.IMAGE_URL_KEY);
        String stringExtra4 = intent.getStringExtra("host");
        C1425a.m6442c("NotificationHandler", "Lightapp notify clicked, appid=" + stringExtra + " host=" + stringExtra4 + " msgid=" + stringExtra2 + " url=" + stringExtra3);
        Intent intent2 = new Intent();
        String str = "other";
        if (!stringExtra3.contains("?")) {
            stringExtra3 = stringExtra3 + "?";
        }
        String str2 = stringExtra3 + "&msg_from_type=sdk_push_msg";
        if (TextUtils.isEmpty(stringExtra4) || !C1578v.m7106c(context, stringExtra4)) {
            C1425a.m6442c("NotificationHandler", "open by browser");
            Intent intent3 = new Intent("android.intent.action.VIEW");
            stringExtra3 = "browser";
            stringExtra4 = str2 + "&ref_id=" + stringExtra3;
            if (C1578v.m7106c(context, "com.baidu.browser.apps")) {
                intent3.setPackage("com.baidu.browser.apps");
            } else if (C1578v.m7106c(context, "com.android.browser")) {
                intent3.setPackage("com.android.browser");
            }
            intent3.setData(Uri.parse(stringExtra4));
            intent3.addFlags(268435456);
            context.startActivity(intent3);
        } else {
            intent2.setPackage(stringExtra4);
            C1442i f = C1418d.m6363a(context).mo13823f(stringExtra4);
            if (stringExtra4.startsWith("com.baidu.searchbox") || stringExtra4.startsWith("com.baidu.voiceassistant")) {
                str = "searchbox";
                str2 = str2 + "&ref_id=" + str;
                intent2.setAction("com.baidu.searchbox.action.aloader.VIEW");
                intent2.setPackage(stringExtra4);
                intent2.setClassName(stringExtra4, "com.baidu.searchbox.aloaderhost.ALoaderActivity");
                intent2.setFlags(335544320);
                intent2.putExtra("isBackToLauncher", false);
                intent2.putExtra("EXTRA_URL_NEW_WINDOW", true);
                intent2.putExtra("src", "notification_bar");
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("appid", stringExtra);
                    jSONObject.put(NativeProtocol.IMAGE_URL_KEY, str2);
                } catch (JSONException e) {
                    C1425a.m6444e("NotificationHandler", "error " + e.getMessage());
                }
                intent2.putExtra("pageId", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject));
                C1425a.m6442c("NotificationHandler", "open by searchbox");
                context.startActivity(intent2);
                stringExtra3 = str;
            } else if (f != null && !TextUtils.isEmpty(f.f5090a)) {
                stringExtra3 = stringExtra4.startsWith("com.baidu.searchbox") ? "searchbox" : stringExtra4.startsWith("com.baidu.netdisk") ? "netdisk" : "runtime_other";
                intent2.putExtra("_lightapp_url", str2 + "&ref_id=" + stringExtra3);
                intent2.addFlags(268566528);
                intent2.setAction(Long.toString(System.currentTimeMillis()));
                intent2.putExtra("fromPush", true);
                intent2.putExtra("appid", stringExtra);
                intent2.putExtra("_runtime_act_impl", "com.baidu.lappgui.LappHostActivity");
                intent2.putExtra("host_appid", f.f5090a);
                intent2.putExtra("host_version", f.f5091b);
                intent2.setPackage(stringExtra4);
                intent2.setClassName(stringExtra4, "com.baidu.sumeru.lightapp.activity.LightAppPlayerActivity");
                context.startActivity(intent2);
            } else if (stringExtra4.equals("com.baidu.netdisk")) {
                stringExtra3 = "netdisk";
                intent2.putExtra("_lightapp_url", str2 + "&ref_id=" + stringExtra3);
                intent2.setFlags(268435456);
                intent2.setPackage(stringExtra4);
                context.startActivity(intent2);
            } else {
                stringExtra3 = "other";
                str = str2 + "&ref_id=" + stringExtra3;
                intent2 = new Intent("android.intent.action.VIEW");
                intent2.setData(Uri.parse(str));
                intent2.setPackage(stringExtra4);
                intent2.addFlags(268435456);
                context.startActivity(intent2);
            }
        }
        C1489g.m6762a(context, stringExtra2, stringExtra, "010201", stringExtra3);
        C1418d.m6363a(context).mo13814b(stringExtra2);
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    public static void m6757a(Context context, PublicMsg publicMsg, String str) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        Intent intent = new Intent(context, PushService.class);
        intent.setAction("com.baidu.pushservice.action.publicmsg.CLICK_V2");
        intent.setData(Uri.parse("content://" + str));
        intent.putExtra("public_msg", publicMsg);
        Intent intent2 = new Intent(context, PushService.class);
        intent2.setAction("com.baidu.pushservice.action.publicmsg.DELETE_V2");
        intent2.setData(Uri.parse("content://" + str));
        intent2.putExtra("public_msg", publicMsg);
        intent.setClass(context, PushService.class);
        intent2.setClass(context, PushService.class);
        PendingIntent service = PendingIntent.getService(context, 0, intent, 0);
        PendingIntent service2 = PendingIntent.getService(context, 0, intent2, 0);
        if (VERSION.SDK_INT >= 16) {
            notificationManager.notify(C1578v.m7091b(str), new Builder(context).setContentTitle(publicMsg.mTitle).setContentText(publicMsg.mDescription).setSmallIcon(17301569).setTicker(publicMsg.mTitle).setSound(RingtoneManager.getDefaultUri(2)).setDeleteIntent(service2).setContentIntent(service).setAutoCancel(true).build());
            return;
        }
        Notification notification = new Notification();
        notification.icon = 17301569;
        notification.tickerText = publicMsg.mTitle;
        notification.setLatestEventInfo(context, publicMsg.mTitle, publicMsg.mDescription, service);
        notification.sound = RingtoneManager.getDefaultUri(2);
        notification.deleteIntent = service2;
        notification.flags |= 16;
        notificationManager.notify(C1578v.m7091b(str), notification);
    }

    /* renamed from: a */
    private static void m6758a(Context context, PublicMsg publicMsg, String str, String str2, int i, boolean z) {
        if (!C1418d.m6363a(context).mo13820c(str2, i)) {
            Intent intent = new Intent("com.baidu.android.pushservice.action.lightapp.notification.CLICK");
            intent.setClass(context, PushService.class);
            intent.putExtra(NativeProtocol.IMAGE_URL_KEY, publicMsg.mUrl);
            intent.putExtra("appid", publicMsg.mAppId);
            intent.putExtra("msgid", str);
            intent.putExtra("host", C1489g.m6755a(context, str2, i));
            intent.setData(Uri.parse("content://" + str));
            Intent intent2 = new Intent("com.baidu.android.pushservice.action.lightapp.notification.DELETE");
            intent2.putExtra("appid", publicMsg.mAppId);
            intent2.putExtra("msgid", str);
            intent2.setClass(context, PushService.class);
            intent2.setData(Uri.parse("content://" + str));
            PendingIntent service = PendingIntent.getService(context, 0, intent, 0);
            PendingIntent service2 = PendingIntent.getService(context, 0, intent2, 0);
            C1344c a = C1344c.m6064a();
            a.mo13623b(16);
            a.mo13625c(3);
            a.mo13621a(publicMsg.mTitle);
            a.mo13624b(publicMsg.mDescription);
            a.mo13626c(C1418d.m6363a(context).mo13822e(str2));
            a.mo13622a(C1578v.m7136o(context, intent.getPackage()));
            a.mo13620a(context, service, service2, publicMsg, z);
            String str3 = publicMsg.mAppId;
            if (!z) {
                str3 = publicMsg.mMsgId;
            }
            C1355a.m6114a().mo13674a(publicMsg.mMsgId, publicMsg.mAppId, str3);
        }
    }

    /* renamed from: a */
    public static void m6759a(Context context, PublicMsg publicMsg, String str, String str2, int i, byte[] bArr, byte[] bArr2) {
        Intent intent = new Intent();
        intent.putExtra("public_msg", publicMsg);
        intent.putExtra("pushService_package_name", context.getPackageName());
        intent.putExtra("service_name", "com.baidu.android.pushservice.PushService");
        intent.putExtra("notify_type", "private");
        intent.putExtra("message_id", str);
        intent.putExtra("app_id", str2);
        intent.putExtra("baidu_message_type", i);
        if (C1578v.m7127k(context, publicMsg.mPkgName) > 45) {
            intent.putExtra("baidu_message_body", bArr2);
            intent.putExtra("baidu_message_secur_info", bArr);
        }
        C1578v.m7094b(context, intent, "com.baidu.android.pushservice.action.notification.SHOW", publicMsg.mPkgName);
    }

    /* renamed from: a */
    public static void m6760a(Context context, String str) {
        try {
            Intent intent = new Intent("com.baidu.android.pushservice.action.METHOD");
            intent.putExtra("method", "com.baidu.android.pushservice.action.UNBINDAPP");
            intent.putExtra("app_id", str);
            C1577u.m7045a(context, intent);
        } catch (Exception e) {
            C1425a.m6444e("NotificationHandler", "unbind exception" + C1425a.m6437a(e));
        }
    }

    /* renamed from: a */
    public static void m6761a(Context context, String str, PublicMsg publicMsg, String str2, int i, byte[] bArr, byte[] bArr2) {
        Intent intent = new Intent();
        intent.putExtra("public_msg", publicMsg);
        intent.putExtra("notify_type", "rich_media");
        intent.putExtra("app_id", str);
        intent.putExtra("message_id", str2);
        intent.putExtra("pushService_package_name", context.getPackageName());
        intent.putExtra("baidu_message_type", i);
        intent.putExtra("service_name", "com.baidu.android.pushservice.PushService");
        if (C1578v.m7127k(context, publicMsg.mPkgName) > 45) {
            intent.putExtra("baidu_message_body", bArr2);
            intent.putExtra("baidu_message_secur_info", bArr);
        }
        C1425a.m6442c("NotificationHandler", "richMedia Intent content： public_msg=" + publicMsg + ", " + "notify_type" + "=" + "rich_media" + ", " + "appid=" + str + ", " + "message_id=" + str2 + ", " + "pushService_package_name=" + context.getPackageName() + ", " + "service_name=com.baidu.android.pushservice.PushService" + "pMsg.mPkgName=" + publicMsg.mPkgName);
        C1578v.m7094b(context, intent, "com.baidu.android.pushservice.action.notification.SHOW", publicMsg.mPkgName);
    }

    /* renamed from: a */
    private static void m6762a(Context context, String str, String str2, String str3, String str4) {
        C1450o c1450o = new C1450o();
        c1450o.f5036f = str3;
        c1450o.f5119a = str;
        c1450o.f5037g = System.currentTimeMillis();
        c1450o.f5038h = C1432b.m6486c(context);
        c1450o.f5121c = C1498m.MSG_TYPE_MULTI_PRIVATE_NOTIFICATION.mo13970a();
        c1450o.f5040j = str2;
        if (str4 != null) {
            c1450o.f5122d = str4;
        }
        C1456u.m6613a(context, c1450o);
    }

    /* renamed from: a */
    public static void m6763a(Context context, String str, String str2, String str3, String str4, String str5) {
        Intent intent = new Intent();
        intent.setData(Uri.parse(str4));
        intent.setAction("android.intent.action.VIEW");
        intent.addFlags(268435456);
        PendingIntent activity = PendingIntent.getActivity(context, 0, intent, 0);
        C1358d c1358d = new C1358d(str3);
        c1358d.mo13714b(16);
        c1358d.mo13716c(3);
        c1358d.mo13712a(str);
        c1358d.mo13715b(str2);
        c1358d.mo13713a(C1578v.m7136o(context, intent.getPackage()));
        c1358d.mo13711a(context, activity, str5);
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:? A:{SYNTHETIC, RETURN, ORIG_RETURN} */
    /* renamed from: a */
    public static boolean m6764a(android.content.Context r7, com.baidu.android.pushservice.message.PublicMsg r8) {
        /*
        r1 = 1;
        r2 = 0;
        r0 = r8.mNetType;
        if (r0 != r1) goto L_0x0049;
    L_0x0006:
        r0 = com.baidu.android.pushservice.util.C1548l.m6950c(r7);
        if (r0 == 0) goto L_0x009c;
    L_0x000c:
        r3 = "NotificationHandler";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "network type : ";
        r4 = r4.append(r5);
        r5 = r0.getTypeName();
        r6 = java.util.Locale.getDefault();
        r5 = r5.toLowerCase(r6);
        r4 = r4.append(r5);
        r4 = r4.toString();
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r3, r4);
        r3 = "wifi";
        r0 = r0.getTypeName();
        r4 = java.util.Locale.getDefault();
        r0 = r0.toLowerCase(r4);
        r0 = r3.equals(r0);
        if (r0 == 0) goto L_0x009c;
    L_0x0045:
        r0 = r1;
    L_0x0046:
        if (r0 != 0) goto L_0x0049;
    L_0x0048:
        return r2;
    L_0x0049:
        r0 = r8.mSupportAppname;
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 == 0) goto L_0x005a;
    L_0x0051:
        r0 = "NotificationHandler";
        r2 = ">>> isNeedShowNotification supportapp = null";
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r0, r2);
        r2 = r1;
        goto L_0x0048;
    L_0x005a:
        r0 = r7.getPackageManager();
        r3 = r8.mSupportAppname;	 Catch:{ NameNotFoundException -> 0x0090 }
        r4 = 0;
        r0 = r0.getPackageInfo(r3, r4);	 Catch:{ NameNotFoundException -> 0x0090 }
        if (r0 == 0) goto L_0x009a;
    L_0x0067:
        r0 = "NotificationHandler";
        r3 = new java.lang.StringBuilder;	 Catch:{ NameNotFoundException -> 0x0090 }
        r3.<init>();	 Catch:{ NameNotFoundException -> 0x0090 }
        r4 = ">>> isNeedShowNotification supportapp found \r\n pckname = ";
        r3 = r3.append(r4);	 Catch:{ NameNotFoundException -> 0x0090 }
        r4 = r8.mSupportAppname;	 Catch:{ NameNotFoundException -> 0x0090 }
        r3 = r3.append(r4);	 Catch:{ NameNotFoundException -> 0x0090 }
        r3 = r3.toString();	 Catch:{ NameNotFoundException -> 0x0090 }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r0, r3);	 Catch:{ NameNotFoundException -> 0x0090 }
        r0 = r1;
    L_0x0082:
        r3 = r8.mIsSupportApp;
        if (r3 == 0) goto L_0x0088;
    L_0x0086:
        if (r0 != 0) goto L_0x008e;
    L_0x0088:
        r3 = r8.mIsSupportApp;
        if (r3 != 0) goto L_0x0048;
    L_0x008c:
        if (r0 != 0) goto L_0x0048;
    L_0x008e:
        r2 = r1;
        goto L_0x0048;
    L_0x0090:
        r0 = move-exception;
        r3 = "NotificationHandler";
        r0 = r0.getMessage();
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r3, r0);
    L_0x009a:
        r0 = r2;
        goto L_0x0082;
    L_0x009c:
        r0 = r2;
        goto L_0x0046;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.message.p040a.C1489g.m6764a(android.content.Context, com.baidu.android.pushservice.message.PublicMsg):boolean");
    }

    /* renamed from: b */
    public static void m6765b(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("appid");
        String stringExtra2 = intent.getStringExtra("msgid");
        C1425a.m6442c("NotificationHandler", "Lightapp notify delete appid=" + stringExtra);
        C1489g.m6762a(context, stringExtra2, stringExtra, "010202", null);
    }

    /* renamed from: b */
    private static void m6766b(Context context, PublicMsg publicMsg, String str) {
        Intent intent = new Intent();
        intent.setData(Uri.parse(publicMsg.mUrl));
        if (C1578v.m7132m(context, "com.baidu.searchbox")) {
            intent.setAction("com.baidu.searchbox.action.VIEW");
            intent.setClassName("com.baidu.searchbox", "com.baidu.searchbox.MainActivity");
            intent.setAction("com.baidu.searchbox.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.addFlags(268435456);
            intent.putExtra("EXTRA_URL_NEW_WINDOW", true);
        } else if (C1578v.m7132m(context, "com.baidu.browser.apps")) {
            intent.setAction("android.intent.action.VIEW");
            intent.setClassName("com.baidu.browser.apps", "com.baidu.browser.framework.BdBrowserActivity");
        } else {
            intent.setAction("android.intent.action.VIEW");
            intent.addFlags(268435456);
        }
        String str2 = "";
        if (publicMsg.mCustomContent != null) {
            try {
                str2 = JSONObjectInstrumentation.init(publicMsg.mCustomContent).getString("iconUrl");
            } catch (JSONException e) {
                C1425a.m6444e("NotificationHandler", "error " + e.getMessage());
            }
        }
        PendingIntent activity = PendingIntent.getActivity(context, 0, intent, 0);
        C1358d c1358d = new C1358d(str2);
        c1358d.mo13714b(16);
        c1358d.mo13716c(3);
        c1358d.mo13712a(publicMsg.mTitle);
        c1358d.mo13710a(context.getApplicationInfo().icon);
        c1358d.mo13715b(publicMsg.mDescription);
        c1358d.mo13713a(C1578v.m7136o(context, intent.getPackage()));
        c1358d.mo13711a(context, activity, str);
    }

    /* renamed from: b */
    public static void m6767b(Context context, PublicMsg publicMsg, String str, String str2) {
        if (publicMsg.mUrl != null) {
            C1489g.m6766b(context, publicMsg, str);
        }
    }

    /* renamed from: b */
    private static boolean m6768b(Context context, String str, int i) {
        C1418d a = C1418d.m6363a(context);
        if (!a.mo13804a()) {
            return true;
        }
        ArrayList a2 = a.mo13794a(str, i);
        if (a2.size() <= 0) {
            return false;
        }
        a.mo13803a(a2);
        return true;
    }
}
