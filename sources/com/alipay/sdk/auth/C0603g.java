package com.alipay.sdk.auth;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import com.alipay.sdk.widget.C0664a;

/* renamed from: com.alipay.sdk.auth.g */
public class C0603g {
    /* renamed from: c */
    private static C0664a f542c = null;
    /* renamed from: d */
    private static String f543d = null;

    /* renamed from: a */
    public static void m841a(Activity activity, String str) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
        }
    }
}
