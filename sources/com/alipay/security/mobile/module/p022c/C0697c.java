package com.alipay.security.mobile.module.p022c;

import android.os.Environment;
import java.io.File;

/* renamed from: com.alipay.security.mobile.module.c.c */
public final class C0697c {
    /* renamed from: a */
    public static String m1250a(String str) {
        try {
            if (C0697c.m1251a()) {
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), str);
                if (file.exists()) {
                    file.delete();
                    return "";
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    /* renamed from: a */
    public static boolean m1251a() {
        String externalStorageState = Environment.getExternalStorageState();
        return externalStorageState != null && externalStorageState.length() > 0 && ((externalStorageState.equals("mounted") || externalStorageState.equals("mounted_ro")) && Environment.getExternalStorageDirectory() != null);
    }
}
