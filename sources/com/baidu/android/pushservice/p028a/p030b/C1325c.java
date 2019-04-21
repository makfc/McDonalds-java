package com.baidu.android.pushservice.p028a.p030b;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.p000v4.view.ViewCompat;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.baidu.android.pushservice.C1328a;
import com.baidu.android.pushservice.p028a.p029a.C1307a.C1306a;
import com.baidu.android.pushservice.p028a.p029a.C1311d;
import com.baidu.android.pushservice.p028a.p029a.C1312e;
import com.baidu.android.pushservice.p035g.C1419f;
import com.baidu.android.pushservice.p036h.C1426b;
import com.baidu.android.pushservice.util.C1533a;
import com.baidu.android.pushservice.util.C1549m;
import com.baidu.android.pushservice.util.C1578v;
import com.mcdonalds.sdk.connectors.google.GoogleConnector;
import com.mcdonalds.sdk.connectors.middleware.model.DCSPreference;
import com.mcdonalds.sdk.connectors.middleware.model.DCSSubscription;

/* renamed from: com.baidu.android.pushservice.a.b.c */
public class C1325c {

    /* renamed from: com.baidu.android.pushservice.a.b.c$a */
    public enum C1324a {
        NORMAL_BLACK_BACKGROUND(1),
        NORMAL_WHITE_BACKGROUND(2),
        NORMAL_DARK_BACKGROUND(3),
        ADVANCED_BIGPIC(4),
        ADVANCED_DETAIL(5),
        CUSTOM_DEFAULT(0);
        
        /* renamed from: g */
        private int f4703g;

        private C1324a(int i) {
            this.f4703g = i;
        }

        /* renamed from: a */
        static C1324a m5970a(int i) {
            switch (i) {
                case 1:
                    return NORMAL_BLACK_BACKGROUND;
                case 2:
                    return NORMAL_WHITE_BACKGROUND;
                case 3:
                    return NORMAL_DARK_BACKGROUND;
                case 4:
                    return ADVANCED_BIGPIC;
                case 5:
                    return ADVANCED_DETAIL;
                default:
                    return CUSTOM_DEFAULT;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x005b  */
    /* renamed from: a */
    public static void m5972a(int r8, android.content.Context r9, java.lang.String r10, java.lang.String r11, java.lang.String r12, java.lang.String r13, android.app.PendingIntent r14, android.content.Intent r15) {
        /*
        r4 = 0;
        r0 = android.text.TextUtils.isEmpty(r11);
        if (r0 != 0) goto L_0x0011;
    L_0x0007:
        r3 = r11.trim();
        r0 = android.text.TextUtils.isEmpty(r3);
        if (r0 == 0) goto L_0x002d;
    L_0x0011:
        r0 = "AdvertiseNotificationManager";
        r1 = "showNotification the contentTitle is invalid, the notification will not pop Up";
        r2 = r9.getApplicationContext();
        com.baidu.android.pushservice.p036h.C1426b.m6447b(r0, r1, r2);
        r0 = "10";
        com.baidu.android.pushservice.util.C1578v.m7064a(r9, r15, r0);
        r0 = "click_url";
        r0 = r15.getStringExtra(r0);
        r1 = "10";
        com.baidu.android.pushservice.util.C1533a.m6888a(r9, r0, r1);
    L_0x002c:
        return;
    L_0x002d:
        r0 = r3.length();
        r1 = 32;
        if (r0 <= r1) goto L_0x003b;
    L_0x0035:
        r0 = 31;
        r3 = r3.substring(r4, r0);
    L_0x003b:
        r0 = android.text.TextUtils.isEmpty(r10);
        if (r0 != 0) goto L_0x004b;
    L_0x0041:
        r2 = r10.trim();
        r0 = android.text.TextUtils.isEmpty(r2);
        if (r0 == 0) goto L_0x0068;
    L_0x004b:
        r2 = " ";
    L_0x004d:
        r0 = com.baidu.android.pushservice.p028a.p030b.C1325c.m5978a(r12);
        if (r0 != 0) goto L_0x0079;
    L_0x0053:
        r4 = "null";
    L_0x0055:
        r0 = com.baidu.android.pushservice.p028a.p030b.C1325c.m5978a(r13);
        if (r0 != 0) goto L_0x0077;
    L_0x005b:
        r5 = "null";
    L_0x005d:
        r0 = com.baidu.android.pushservice.p028a.p030b.C1325c.C1324a.m5970a(r8);
        r1 = r9;
        r6 = r14;
        r7 = r15;
        com.baidu.android.pushservice.p028a.p030b.C1325c.m5974a(r0, r1, r2, r3, r4, r5, r6, r7);
        goto L_0x002c;
    L_0x0068:
        r0 = r2.length();
        r1 = 64;
        if (r0 <= r1) goto L_0x004d;
    L_0x0070:
        r0 = 63;
        r2 = r2.substring(r4, r0);
        goto L_0x004d;
    L_0x0077:
        r5 = r13;
        goto L_0x005d;
    L_0x0079:
        r4 = r12;
        goto L_0x0055;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p028a.p030b.C1325c.m5972a(int, android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String, android.app.PendingIntent, android.content.Intent):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0059  */
    /* renamed from: a */
    public static void m5973a(int r9, android.content.Context r10, java.lang.String r11, java.lang.String r12, java.lang.String r13, java.lang.String r14, java.lang.String r15, android.app.PendingIntent r16, android.content.Intent... r17) {
        /*
        r0 = android.text.TextUtils.isEmpty(r12);
        if (r0 != 0) goto L_0x0010;
    L_0x0006:
        r3 = r12.trim();
        r0 = android.text.TextUtils.isEmpty(r3);
        if (r0 == 0) goto L_0x0032;
    L_0x0010:
        r0 = "AdvertiseNotificationManager";
        r1 = "showNotification the contentTitle is invalid, the notification will not pop Up";
        r2 = r10.getApplicationContext();
        com.baidu.android.pushservice.p036h.C1426b.m6447b(r0, r1, r2);
        r0 = 0;
        r0 = r17[r0];
        r1 = "10";
        com.baidu.android.pushservice.util.C1578v.m7064a(r10, r0, r1);
        r0 = 0;
        r0 = r17[r0];
        r1 = "click_url";
        r0 = r0.getStringExtra(r1);
        r1 = "10";
        com.baidu.android.pushservice.util.C1533a.m6888a(r10, r0, r1);
    L_0x0031:
        return;
    L_0x0032:
        r0 = r3.length();
        r1 = 32;
        if (r0 <= r1) goto L_0x0041;
    L_0x003a:
        r0 = 0;
        r1 = 31;
        r3 = r3.substring(r0, r1);
    L_0x0041:
        r0 = android.text.TextUtils.isEmpty(r11);
        if (r0 != 0) goto L_0x0051;
    L_0x0047:
        r2 = r11.trim();
        r0 = android.text.TextUtils.isEmpty(r2);
        if (r0 == 0) goto L_0x0066;
    L_0x0051:
        r2 = " ";
    L_0x0053:
        r0 = com.baidu.android.pushservice.p028a.p030b.C1325c.m5978a(r15);
        if (r0 != 0) goto L_0x0076;
    L_0x0059:
        r0 = 1;
        r1 = 0;
        r7 = r17[r1];
        r1 = r10;
        r4 = r13;
        r5 = r14;
        r6 = r16;
        com.baidu.android.pushservice.p028a.p030b.C1325c.m5972a(r0, r1, r2, r3, r4, r5, r6, r7);
        goto L_0x0031;
    L_0x0066:
        r0 = r2.length();
        r1 = 64;
        if (r0 <= r1) goto L_0x0053;
    L_0x006e:
        r0 = 0;
        r1 = 63;
        r2 = r2.substring(r0, r1);
        goto L_0x0053;
    L_0x0076:
        r0 = com.baidu.android.pushservice.p028a.p030b.C1325c.m5978a(r13);
        if (r0 != 0) goto L_0x0096;
    L_0x007c:
        r4 = "null";
    L_0x007e:
        r0 = com.baidu.android.pushservice.p028a.p030b.C1325c.m5978a(r14);
        if (r0 != 0) goto L_0x0094;
    L_0x0084:
        r5 = "null";
    L_0x0086:
        r0 = com.baidu.android.pushservice.p028a.p030b.C1325c.C1324a.m5970a(r9);
        r1 = r10;
        r6 = r15;
        r7 = r16;
        r8 = r17;
        com.baidu.android.pushservice.p028a.p030b.C1325c.m5975a(r0, r1, r2, r3, r4, r5, r6, r7, r8);
        goto L_0x0031;
    L_0x0094:
        r5 = r14;
        goto L_0x0086;
    L_0x0096:
        r4 = r13;
        goto L_0x007e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p028a.p030b.C1325c.m5973a(int, android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, android.app.PendingIntent, android.content.Intent[]):void");
    }

    /* renamed from: a */
    private static void m5974a(C1324a c1324a, final Context context, String str, String str2, String str3, String str4, PendingIntent pendingIntent, final Intent intent) {
        final C1317d b = C1325c.m5979b(c1324a, context, str2, str);
        final String stringExtra = intent.getStringExtra("msg_id");
        b.mo13571a(intent);
        b.mo13570a(pendingIntent);
        C1311d.m5919a().mo13561a(context, new C1306a() {
            /* renamed from: a */
            public void mo13554a(Bitmap... bitmapArr) {
                Bitmap a = C1419f.m6410a(context, bitmapArr[0]);
                Bitmap c = C1419f.m6416c(context, bitmapArr[1]);
                b.mo13574b(a);
                b.mo13577c(c);
                NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
                Notification a2 = b.mo13567a();
                a2.flags |= 16;
                notificationManager.notify(stringExtra, 0, b.mo13567a());
                intent.putExtra("message_id", stringExtra);
                C1578v.m7064a(context, intent, DCSSubscription.ID_MOBILE_NOTIFICATION);
                if (VERSION.SDK_INT >= 19 && !C1549m.m6954a(context)) {
                    C1533a.m6888a(context, intent.getStringExtra("click_url"), DCSPreference.ID_FOOD_PREFERENCE_BREAKFAST);
                }
                C1426b.m6445a("AdvertiseNotificationManager", "notify normal advertise  notification, msgid = " + stringExtra, context.getApplicationContext());
                if (C1328a.m6006b() > 0) {
                    C1578v.m7095b("pushadvertise: mNotificationManager  show advertise notification  second", context);
                }
            }
        }, Uri.parse(str3), Uri.parse(str4));
    }

    /* renamed from: a */
    private static void m5975a(C1324a c1324a, Context context, String str, String str2, String str3, String str4, String str5, PendingIntent pendingIntent, Intent... intentArr) {
        C1317d c1317d = null;
        if (C1324a.ADVANCED_BIGPIC == c1324a) {
            c1317d = C1325c.m5979b(C1324a.ADVANCED_BIGPIC, context, str2, str);
        } else if (C1324a.ADVANCED_DETAIL == c1324a) {
            c1317d = C1325c.m5979b(C1324a.ADVANCED_DETAIL, context, str2, str);
        }
        final String stringExtra = intentArr[0].getStringExtra("msg_id");
        for (Intent a : intentArr) {
            c1317d.mo13571a(a);
        }
        c1317d.mo13570a(pendingIntent);
        final Context context2 = context;
        final String str6 = str2;
        final String str7 = str;
        final Intent[] intentArr2 = intentArr;
        C1311d.m5919a().mo13561a(context, new C1306a() {
            /* renamed from: a */
            public void mo13554a(Bitmap... bitmapArr) {
                Bitmap b;
                C1317d c1317d = c1317d;
                if (bitmapArr[2] != null || (c1317d instanceof C1327g)) {
                    b = C1419f.m6415b(context2, bitmapArr[2]);
                    if (c1317d instanceof C1320b) {
                        ((C1320b) c1317d).mo13580a(b);
                    } else if (c1317d instanceof C1319a) {
                        ((C1319a) c1317d).mo13579a(b);
                    }
                } else {
                    c1317d = C1325c.m5979b(C1324a.NORMAL_BLACK_BACKGROUND, context2, str6, str7);
                    c1317d.mo13571a(intentArr2[0]);
                }
                Bitmap a = C1419f.m6410a(context2, bitmapArr[0]);
                b = C1419f.m6416c(context2, bitmapArr[1]);
                c1317d.mo13574b(a);
                c1317d.mo13577c(b);
                NotificationManager notificationManager = (NotificationManager) context2.getSystemService("notification");
                Notification a2 = c1317d.mo13567a();
                a2.flags |= 16;
                notificationManager.notify(stringExtra, 0, c1317d.mo13567a());
                intentArr2[0].putExtra("message_id", stringExtra);
                C1578v.m7064a(context2, intentArr2[0], DCSSubscription.ID_MOBILE_NOTIFICATION);
                if (VERSION.SDK_INT >= 19 && !C1549m.m6954a(context2)) {
                    C1533a.m6888a(context2, intentArr2[0].getStringExtra("click_url"), DCSPreference.ID_FOOD_PREFERENCE_BREAKFAST);
                }
                C1426b.m6445a("AdvertiseNotificationManager", "notify advanced advertise  notification, msgid = " + stringExtra, context2.getApplicationContext());
                if (C1328a.m6006b() > 0) {
                    C1578v.m7095b("pushadvertise: mNotificationManager  show advertise notification  first ", context2);
                }
            }
        }, Uri.parse(str3), Uri.parse(str4), Uri.parse(str5));
    }

    /* renamed from: a */
    private static void m5976a(C1317d c1317d, String str, String str2) {
        c1317d.mo13569a(System.currentTimeMillis());
        c1317d.mo13572a(str);
        c1317d.mo13575b(str2);
    }

    /* renamed from: a */
    private static boolean m5977a() {
        return Build.MANUFACTURER.contains("HUAWEI") && Build.MODEL.contains("PE");
    }

    /* renamed from: a */
    private static boolean m5978a(String str) {
        return (TextUtils.isEmpty(str) || TextUtils.isEmpty(str.trim())) ? false : C1312e.m5924a(Uri.parse(str));
    }

    /* renamed from: b */
    private static C1317d m5979b(C1324a c1324a, Context context, String str, String str2) {
        RemoteViews remoteViews = null;
        if (C1325c.m5980b()) {
            return new C1327g(context, str, str2);
        }
        C1317d c1317d;
        Object obj;
        if ((c1324a == C1324a.ADVANCED_BIGPIC || c1324a == C1324a.ADVANCED_DETAIL) && C1325c.m5981c()) {
            c1324a = C1324a.NORMAL_BLACK_BACKGROUND;
        }
        int b;
        if (C1325c.m5984f()) {
            b = C1326e.m5989b(context, "advertise_normal_layout_xiaomi");
            if (b != 0) {
                remoteViews = new RemoteViews(context.getPackageName(), b);
            }
        } else if (C1325c.m5986h()) {
            b = C1326e.m5989b(context, "advertise_normal_layout_xiaomi_1s");
            if (b != 0) {
                remoteViews = new RemoteViews(context.getPackageName(), b);
            }
        } else if (C1325c.m5977a()) {
            b = C1326e.m5989b(context, "advertise_normal_layout_honor");
            if (b != 0) {
                remoteViews = new RemoteViews(context.getPackageName(), b);
            }
        }
        if (remoteViews == null) {
            remoteViews = new RemoteViews(context.getPackageName(), C1326e.m5989b(context, "advertise_normal_layout"));
        }
        int obj2;
        C1318f c1318f;
        Object c1317d2;
        int b2;
        RemoteViews remoteViews2;
        switch (c1324a) {
            case NORMAL_BLACK_BACKGROUND:
                if (!C1326e.m5991c(context)) {
                    c1317d2 = null;
                    obj2 = 1;
                    break;
                }
                c1318f = new C1318f(remoteViews, context);
                if (!C1325c.m5982d()) {
                    c1318f.mo13573b(-1);
                    c1318f.mo13568a(-9934744);
                    c1318f.mo13576c((int) ViewCompat.MEASURED_STATE_MASK);
                    c1317d2 = c1318f;
                    obj2 = null;
                    break;
                }
                c1318f.mo13573b(-16645630);
                c1318f.mo13568a(-9605779);
                c1318f.mo13576c(-986896);
                c1317d2 = c1318f;
                obj2 = null;
                break;
            case NORMAL_DARK_BACKGROUND:
                if (!C1326e.m5991c(context)) {
                    c1317d2 = null;
                    obj2 = 1;
                    break;
                }
                c1318f = new C1318f(remoteViews, context);
                if (!C1325c.m5982d()) {
                    c1318f.mo13573b(-1);
                    c1318f.mo13568a(-9605779);
                    c1318f.mo13576c(-13948117);
                    c1317d2 = c1318f;
                    obj2 = null;
                    break;
                }
                c1318f.mo13573b(-16645630);
                c1318f.mo13568a(-9605779);
                c1318f.mo13576c(-986896);
                c1317d2 = c1318f;
                obj2 = null;
                break;
            case NORMAL_WHITE_BACKGROUND:
                if (!C1326e.m5991c(context)) {
                    c1317d2 = null;
                    obj2 = 1;
                    break;
                }
                c1318f = new C1318f(remoteViews, context);
                c1318f.mo13573b(-16645630);
                c1318f.mo13568a(-9605779);
                c1318f.mo13576c(-986896);
                c1317d2 = c1318f;
                obj2 = null;
                break;
            case ADVANCED_BIGPIC:
                if (!C1326e.m5990b(context)) {
                    c1317d2 = null;
                    obj2 = 1;
                    break;
                }
                if (C1325c.m5984f()) {
                    b2 = C1326e.m5989b(context, "advertise_advance_picture_layout_xiaomi");
                    if (b2 != 0) {
                        remoteViews2 = new RemoteViews(context.getPackageName(), b2);
                    }
                    remoteViews2 = null;
                } else {
                    if (C1325c.m5977a()) {
                        b2 = C1326e.m5989b(context, "advertise_advance_picture_layout_honor");
                        if (b2 != 0) {
                            remoteViews2 = new RemoteViews(context.getPackageName(), b2);
                        }
                    }
                    remoteViews2 = null;
                }
                if (remoteViews2 == null) {
                    remoteViews2 = new RemoteViews(context.getPackageName(), C1326e.m5989b(context, "advertise_advance_picture_layout"));
                }
                C1320b c1320b = new C1320b(remoteViews, remoteViews2, context);
                if (!C1325c.m5982d()) {
                    c1320b.mo13573b(-1);
                    c1320b.mo13568a(-9934744);
                    c1320b.mo13576c((int) ViewCompat.MEASURED_STATE_MASK);
                    c1317d2 = c1320b;
                    obj2 = null;
                    break;
                }
                c1320b.mo13573b(-16645630);
                c1320b.mo13568a(-9605779);
                c1320b.mo13576c(-986896);
                c1317d2 = c1320b;
                obj2 = null;
                break;
            case ADVANCED_DETAIL:
                if (!C1326e.m5988a(context)) {
                    c1317d2 = null;
                    obj2 = 1;
                    break;
                }
                if (C1325c.m5984f()) {
                    b2 = C1326e.m5989b(context, "advertise_advance_bigstyle_layout_xiaomi");
                    if (b2 != 0) {
                        remoteViews2 = new RemoteViews(context.getPackageName(), b2);
                    }
                    remoteViews2 = null;
                } else if (C1325c.m5986h()) {
                    b2 = C1326e.m5989b(context, "advertise_advance_bigstyle_layout_xiaomi_1s");
                    if (b2 != 0) {
                        remoteViews2 = new RemoteViews(context.getPackageName(), b2);
                    }
                    remoteViews2 = null;
                } else if (C1325c.m5982d()) {
                    b2 = C1326e.m5989b(context, "advertise_advance_bigstyle_layout_bbk");
                    if (b2 != 0) {
                        remoteViews2 = new RemoteViews(context.getPackageName(), b2);
                    }
                    remoteViews2 = null;
                } else if (C1325c.m5983e()) {
                    b2 = C1326e.m5989b(context, "advertise_advance_bigstyle_layout_9250");
                    if (b2 != 0) {
                        remoteViews2 = new RemoteViews(context.getPackageName(), b2);
                    }
                    remoteViews2 = null;
                } else if (C1325c.m5985g()) {
                    b2 = C1326e.m5989b(context, "advertise_advance_bigstyle_layout_2k");
                    if (b2 != 0) {
                        remoteViews2 = new RemoteViews(context.getPackageName(), b2);
                    }
                    remoteViews2 = null;
                } else {
                    if (C1325c.m5977a()) {
                        b2 = C1326e.m5989b(context, "advertise_advance_bigstyle_layout_honor");
                        if (b2 != 0) {
                            remoteViews2 = new RemoteViews(context.getPackageName(), b2);
                        }
                    }
                    remoteViews2 = null;
                }
                if (remoteViews2 == null) {
                    remoteViews2 = new RemoteViews(context.getPackageName(), C1326e.m5989b(context, "advertise_advance_bigstyle_layout"));
                }
                C1319a c1319a = new C1319a(remoteViews, remoteViews2, context);
                if (!C1325c.m5982d()) {
                    c1319a.mo13573b(-1);
                    c1319a.mo13568a(-9934744);
                    c1319a.mo13576c((int) ViewCompat.MEASURED_STATE_MASK);
                    c1317d2 = c1319a;
                    obj2 = null;
                    break;
                }
                c1317d2 = c1319a;
                obj2 = null;
                break;
            default:
                c1317d2 = new C1327g(context, str, str2);
                obj2 = null;
                break;
        }
        if (obj2 != null) {
            c1317d2 = new C1327g(context, str, str2);
        }
        C1325c.m5976a(c1317d2, str, str2);
        return c1317d2;
    }

    /* renamed from: b */
    private static boolean m5980b() {
        return VERSION.SDK_INT < 15;
    }

    /* renamed from: c */
    private static boolean m5981c() {
        return VERSION.SDK_INT < 16;
    }

    /* renamed from: d */
    private static boolean m5982d() {
        return TextUtils.isEmpty(Build.MANUFACTURER) ? false : Build.MANUFACTURER.toLowerCase().contains("bbk") || Build.MANUFACTURER.toLowerCase().startsWith("vivo");
    }

    /* renamed from: e */
    private static boolean m5983e() {
        return Build.MANUFACTURER.contains("Baidu") && Build.MODEL.contains("9250");
    }

    /* renamed from: f */
    private static boolean m5984f() {
        return Build.MANUFACTURER.contains("Xiaomi") && (Build.MODEL.contains("MI 4") || Build.MODEL.contains("MI 3"));
    }

    /* renamed from: g */
    private static boolean m5985g() {
        return Build.MANUFACTURER.contains("motorola") && Build.BRAND.contains(GoogleConnector.NAME) && Build.MODEL.contains("Nexus 6");
    }

    /* renamed from: h */
    private static boolean m5986h() {
        return Build.MANUFACTURER.contains("Xiaomi") && Build.MODEL.contains("HM 1S");
    }
}
