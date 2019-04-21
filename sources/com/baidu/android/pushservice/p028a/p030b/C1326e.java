package com.baidu.android.pushservice.p028a.p030b;

import android.content.Context;

/* renamed from: com.baidu.android.pushservice.a.b.e */
public class C1326e {
    /* renamed from: a */
    static int m5987a(Context context, String str) {
        return context.getApplicationContext().getResources().getIdentifier(str, "id", context.getApplicationContext().getPackageName());
    }

    /* renamed from: a */
    static boolean m5988a(Context context) {
        return (C1326e.m5989b(context, "advertise_advance_bigstyle_layout") == 0 || !C1326e.m5991c(context) || C1326e.m5987a(context, "notification_big_icon") == 0 || C1326e.m5987a(context, "notification_download_btn") == 0 || C1326e.m5987a(context, "notification_detail_btn") == 0) ? false : true;
    }

    /* renamed from: b */
    public static int m5989b(Context context, String str) {
        return context.getApplicationContext().getResources().getIdentifier(str, "layout", context.getApplicationContext().getPackageName());
    }

    /* renamed from: b */
    static boolean m5990b(Context context) {
        return (C1326e.m5989b(context, "advertise_advance_picture_layout") == 0 || !C1326e.m5991c(context) || C1326e.m5987a(context, "notification_big_icon") == 0) ? false : true;
    }

    /* renamed from: c */
    static boolean m5991c(Context context) {
        return (C1326e.m5989b(context, "advertise_normal_layout") == 0 || C1326e.m5987a(context, "notification_icon") == 0 || C1326e.m5987a(context, "notification_title") == 0 || C1326e.m5987a(context, "notification_content") == 0 || C1326e.m5987a(context, "notification_img") == 0 || C1326e.m5987a(context, "notification_when") == 0) ? false : true;
    }
}
