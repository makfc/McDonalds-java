package com.tencent.wxop.stat.common;

import java.io.File;

/* renamed from: com.tencent.wxop.stat.common.o */
class C4437o {
    /* renamed from: a */
    private static int f7163a = -1;

    /* renamed from: a */
    public static boolean m8148a() {
        if (f7163a == 1) {
            return true;
        }
        if (f7163a == 0) {
            return false;
        }
        String[] strArr = new String[]{"/bin", "/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        int i = 0;
        while (i < 6) {
            try {
                if (new File(strArr[i] + "su").exists()) {
                    f7163a = 1;
                    return true;
                }
                i++;
            } catch (Exception e) {
            }
        }
        f7163a = 0;
        return false;
    }
}
