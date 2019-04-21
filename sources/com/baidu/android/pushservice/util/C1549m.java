package com.baidu.android.pushservice.util;

import android.annotation.SuppressLint;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import com.baidu.android.pushservice.p036h.C1425a;

/* renamed from: com.baidu.android.pushservice.util.m */
public class C1549m {
    @SuppressLint({"NewApi"})
    /* renamed from: a */
    public static boolean m6954a(Context context) {
        AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String packageName = context.getApplicationContext().getPackageName();
        int i = applicationInfo.uid;
        try {
            return ((Integer) Class.forName(AppOpsManager.class.getName()).getMethod("checkOpNoThrow", new Class[]{Integer.TYPE, Integer.TYPE, String.class}).invoke(appOpsManager, new Object[]{Integer.valueOf(((Integer) Class.forName(AppOpsManager.class.getName()).getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class)).intValue()), Integer.valueOf(i), packageName})).intValue() == 0;
        } catch (Exception e) {
            C1425a.m6444e("NotificationsUtils", "error = " + e.getMessage());
            return false;
        }
    }
}
