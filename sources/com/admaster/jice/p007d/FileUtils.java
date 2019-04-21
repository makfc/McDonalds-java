package com.admaster.jice.p007d;

import android.text.TextUtils;
import java.io.File;

/* renamed from: com.admaster.jice.d.a */
public class FileUtils {
    /* JADX WARNING: Removed duplicated region for block: B:22:0x003a A:{SYNTHETIC, Splitter:B:22:0x003a} */
    /* renamed from: a */
    public static boolean m213a(java.lang.String r4, byte[] r5) {
        /*
        r0 = android.text.TextUtils.isEmpty(r4);
        if (r0 == 0) goto L_0x0008;
    L_0x0006:
        r0 = 0;
    L_0x0007:
        return r0;
    L_0x0008:
        r2 = 0;
        r0 = new java.io.File;	 Catch:{ FileNotFoundException -> 0x002c, IOException -> 0x003e }
        r0.<init>(r4);	 Catch:{ FileNotFoundException -> 0x002c, IOException -> 0x003e }
        r1 = r0.getAbsolutePath();	 Catch:{ FileNotFoundException -> 0x002c, IOException -> 0x003e }
        com.admaster.jice.p007d.FileUtils.m214b(r1);	 Catch:{ FileNotFoundException -> 0x002c, IOException -> 0x003e }
        r1 = new java.io.FileOutputStream;	 Catch:{ FileNotFoundException -> 0x002c, IOException -> 0x003e }
        r1.<init>(r0);	 Catch:{ FileNotFoundException -> 0x002c, IOException -> 0x003e }
        r1.write(r5);	 Catch:{ FileNotFoundException -> 0x0051, IOException -> 0x004e }
        r1.flush();	 Catch:{ FileNotFoundException -> 0x0051, IOException -> 0x004e }
        if (r1 == 0) goto L_0x0025;
    L_0x0022:
        r1.close();	 Catch:{ IOException -> 0x0027 }
    L_0x0025:
        r0 = 1;
        goto L_0x0007;
    L_0x0027:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0025;
    L_0x002c:
        r0 = move-exception;
        r1 = r2;
    L_0x002e:
        r2 = new java.lang.RuntimeException;	 Catch:{ all -> 0x0036 }
        r3 = "FileNotFoundException";
        r2.<init>(r3, r0);	 Catch:{ all -> 0x0036 }
        throw r2;	 Catch:{ all -> 0x0036 }
    L_0x0036:
        r0 = move-exception;
        r2 = r1;
    L_0x0038:
        if (r2 == 0) goto L_0x003d;
    L_0x003a:
        r2.close();	 Catch:{ IOException -> 0x0049 }
    L_0x003d:
        throw r0;
    L_0x003e:
        r0 = move-exception;
    L_0x003f:
        r1 = new java.lang.RuntimeException;	 Catch:{ all -> 0x0047 }
        r3 = "IOException";
        r1.<init>(r3, r0);	 Catch:{ all -> 0x0047 }
        throw r1;	 Catch:{ all -> 0x0047 }
    L_0x0047:
        r0 = move-exception;
        goto L_0x0038;
    L_0x0049:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x003d;
    L_0x004e:
        r0 = move-exception;
        r2 = r1;
        goto L_0x003f;
    L_0x0051:
        r0 = move-exception;
        goto L_0x002e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.admaster.jice.p007d.FileUtils.m213a(java.lang.String, byte[]):boolean");
    }

    /* renamed from: a */
    public static String m212a(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        int lastIndexOf = str.lastIndexOf(File.separator);
        return lastIndexOf == -1 ? "" : str.substring(0, lastIndexOf);
    }

    /* renamed from: b */
    public static boolean m214b(String str) {
        String a = FileUtils.m212a(str);
        if (TextUtils.isEmpty(a)) {
            return false;
        }
        File file = new File(a);
        if (file.exists() && file.isDirectory()) {
            return true;
        }
        return file.mkdirs();
    }

    /* renamed from: c */
    public static boolean m215c(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            File file = new File(str);
            if (file.exists() && file.isFile()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
