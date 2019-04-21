package com.baidu.android.pushservice.p028a.p029a;

import android.net.Uri;

/* renamed from: com.baidu.android.pushservice.a.a.e */
public final class C1312e {
    /* renamed from: a */
    public static boolean m5924a(Uri uri) {
        if (uri != null) {
            String scheme = uri.getScheme();
            if (scheme != null && (scheme.equalsIgnoreCase("http") || scheme.equalsIgnoreCase("https"))) {
                return true;
            }
        }
        return false;
    }
}
