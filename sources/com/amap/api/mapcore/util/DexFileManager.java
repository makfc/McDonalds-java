package com.amap.api.mapcore.util;

import android.content.Context;
import com.amap.api.mapcore.util.DynamicSDKFile.C0835a;
import java.io.File;
import java.util.List;

/* renamed from: com.amap.api.mapcore.util.fa */
public class DexFileManager {

    /* compiled from: DexFileManager */
    /* renamed from: com.amap.api.mapcore.util.fa$a */
    public static class C0834a {
        /* renamed from: a */
        public static void m2689a(DBOperation dBOperation, DynamicSDKFile dynamicSDKFile, String str) {
            dBOperation.mo9326a((Object) dynamicSDKFile, str);
        }

        /* renamed from: a */
        public static DynamicSDKFile m2687a(DBOperation dBOperation, String str) {
            List b = dBOperation.mo9332b(DynamicSDKFile.m2722b(str), DynamicSDKFile.class);
            if (b == null || b.size() <= 0) {
                return null;
            }
            return (DynamicSDKFile) b.get(0);
        }

        /* renamed from: a */
        public static List<DynamicSDKFile> m2688a(DBOperation dBOperation, String str, String str2) {
            return dBOperation.mo9332b(DynamicSDKFile.m2723b(str, str2), DynamicSDKFile.class);
        }
    }

    /* renamed from: a */
    static String m2694a(String str) {
        return str + ".dex";
    }

    /* renamed from: a */
    static String m2693a(Context context, String str, String str2) {
        return C0822ds.m2464b(str + str2 + C0820dq.m2443q(context)) + ".jar";
    }

    /* renamed from: b */
    static String m2702b(Context context, String str, String str2) {
        return DexFileManager.m2692a(context, DexFileManager.m2693a(context, str, str2));
    }

    /* renamed from: a */
    static String m2692a(Context context, String str) {
        return DexFileManager.m2690a(context) + File.separator + str;
    }

    /* renamed from: a */
    static String m2690a(Context context) {
        return context.getFilesDir().getAbsolutePath() + File.separator + "dex";
    }

    /* renamed from: a */
    static boolean m2701a(String str, String str2) {
        String a = C0822ds.m2461a(str);
        if (a == null || !a.equalsIgnoreCase(str2)) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    static void m2696a(Context context, DBOperation dBOperation, String str) {
        DexFileManager.m2703b(context, dBOperation, str);
        DexFileManager.m2703b(context, dBOperation, DexFileManager.m2694a(str));
    }

    /* renamed from: b */
    static void m2703b(Context context, DBOperation dBOperation, String str) {
        File file = new File(DexFileManager.m2692a(context, str));
        if (file.exists()) {
            file.delete();
        }
        dBOperation.mo9328a(DynamicSDKFile.m2722b(str), DynamicSDKFile.class);
    }

    /* renamed from: c */
    static void m2704c(final Context context, final String str, final String str2) {
        new Thread() {
            public void run() {
                try {
                    DBOperation dBOperation = new DBOperation(context, DynamicFileDBCreator.m2706a());
                    List<DynamicSDKFile> b = dBOperation.mo9332b(DynamicSDKFile.m2719a(str), DynamicSDKFile.class);
                    if (b != null && b.size() > 0) {
                        for (DynamicSDKFile dynamicSDKFile : b) {
                            if (!str2.equalsIgnoreCase(dynamicSDKFile.mo9375c())) {
                                DexFileManager.m2703b(context, dBOperation, dynamicSDKFile.mo9373a());
                            }
                        }
                    }
                } catch (Throwable th) {
                    BasicLogHandler.m2542a(th, "DexFileManager", "clearUnSuitableVersion");
                }
            }
        }.start();
    }

    /* renamed from: a */
    public static void m2697a(DBOperation dBOperation, Context context, SDKInfo sDKInfo) {
        String a = sDKInfo.mo9292a();
        String a2 = DexFileManager.m2693a(context, a, sDKInfo.mo9294b());
        DynamicSDKFile a3 = C0834a.m2687a(dBOperation, a2);
        if (a3 != null) {
            DexFileManager.m2696a(context, dBOperation, a2);
            List b = dBOperation.mo9332b(DynamicSDKFile.m2720a(a, a3.mo9377d()), DynamicSDKFile.class);
            if (b != null && b.size() > 0) {
                DynamicSDKFile dynamicSDKFile = (DynamicSDKFile) b.get(0);
                dynamicSDKFile.mo9376c("errorstatus");
                C0834a.m2689a(dBOperation, dynamicSDKFile, DynamicSDKFile.m2722b(dynamicSDKFile.mo9373a()));
                File file = new File(DexFileManager.m2692a(context, dynamicSDKFile.mo9373a()));
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0040 A:{SYNTHETIC, Splitter:B:19:0x0040} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0045 A:{SYNTHETIC, Splitter:B:22:0x0045} */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0040 A:{SYNTHETIC, Splitter:B:19:0x0040} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0045 A:{SYNTHETIC, Splitter:B:22:0x0045} */
    /* renamed from: a */
    static void m2695a(android.content.Context r5, com.amap.api.mapcore.util.DBOperation r6, com.amap.api.mapcore.util.SDKInfo r7, com.amap.api.mapcore.util.DynamicSDKFile r8, java.lang.String r9) throws java.lang.Throwable {
        /*
        r2 = 0;
        r0 = r7.mo9292a();	 Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x0069, Throwable -> 0x006e, all -> 0x007b }
        r1 = r8.mo9373a();	 Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x0069, Throwable -> 0x006e, all -> 0x007b }
        com.amap.api.mapcore.util.DexFileManager.m2696a(r5, r6, r1);	 Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x0069, Throwable -> 0x006e, all -> 0x007b }
        r1 = new java.io.File;	 Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x0069, Throwable -> 0x006e, all -> 0x007b }
        r1.<init>(r9);	 Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x0069, Throwable -> 0x006e, all -> 0x007b }
        r3 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x0069, Throwable -> 0x006e, all -> 0x007b }
        r3.<init>(r1);	 Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x0069, Throwable -> 0x006e, all -> 0x007b }
        r4 = new java.io.File;	 Catch:{ FileNotFoundException -> 0x008e, IOException -> 0x0086, Throwable -> 0x0081 }
        r1 = r7.mo9294b();	 Catch:{ FileNotFoundException -> 0x008e, IOException -> 0x0086, Throwable -> 0x0081 }
        r0 = com.amap.api.mapcore.util.DexFileManager.m2702b(r5, r0, r1);	 Catch:{ FileNotFoundException -> 0x008e, IOException -> 0x0086, Throwable -> 0x0081 }
        r4.<init>(r0);	 Catch:{ FileNotFoundException -> 0x008e, IOException -> 0x0086, Throwable -> 0x0081 }
        r1 = new java.io.FileOutputStream;	 Catch:{ FileNotFoundException -> 0x008e, IOException -> 0x0086, Throwable -> 0x0081 }
        r0 = 1;
        r1.<init>(r4, r0);	 Catch:{ FileNotFoundException -> 0x008e, IOException -> 0x0086, Throwable -> 0x0081 }
        r0 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r0 = new byte[r0];	 Catch:{ FileNotFoundException -> 0x0038, IOException -> 0x0088, Throwable -> 0x0083, all -> 0x007e }
    L_0x002d:
        r2 = r3.read(r0);	 Catch:{ FileNotFoundException -> 0x0038, IOException -> 0x0088, Throwable -> 0x0083, all -> 0x007e }
        if (r2 <= 0) goto L_0x0049;
    L_0x0033:
        r4 = 0;
        r1.write(r0, r4, r2);	 Catch:{ FileNotFoundException -> 0x0038, IOException -> 0x0088, Throwable -> 0x0083, all -> 0x007e }
        goto L_0x002d;
    L_0x0038:
        r0 = move-exception;
        r2 = r3;
    L_0x003a:
        throw r0;	 Catch:{ all -> 0x003b }
    L_0x003b:
        r0 = move-exception;
        r3 = r2;
        r2 = r1;
    L_0x003e:
        if (r3 == 0) goto L_0x0043;
    L_0x0040:
        r3.close();	 Catch:{ IOException -> 0x0071 }
    L_0x0043:
        if (r2 == 0) goto L_0x0048;
    L_0x0045:
        r2.close();	 Catch:{ IOException -> 0x0076 }
    L_0x0048:
        throw r0;
    L_0x0049:
        r0 = r8.mo9373a();	 Catch:{ FileNotFoundException -> 0x0038, IOException -> 0x0088, Throwable -> 0x0083, all -> 0x007e }
        r0 = com.amap.api.mapcore.util.DynamicSDKFile.m2722b(r0);	 Catch:{ FileNotFoundException -> 0x0038, IOException -> 0x0088, Throwable -> 0x0083, all -> 0x007e }
        com.amap.api.mapcore.util.DexFileManager.C0834a.m2689a(r6, r8, r0);	 Catch:{ FileNotFoundException -> 0x0038, IOException -> 0x0088, Throwable -> 0x0083, all -> 0x007e }
        if (r3 == 0) goto L_0x0059;
    L_0x0056:
        r3.close();	 Catch:{ IOException -> 0x005f }
    L_0x0059:
        if (r1 == 0) goto L_0x005e;
    L_0x005b:
        r1.close();	 Catch:{ IOException -> 0x0064 }
    L_0x005e:
        return;
    L_0x005f:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0059;
    L_0x0064:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x005e;
    L_0x0069:
        r0 = move-exception;
        r3 = r2;
    L_0x006b:
        throw r0;	 Catch:{ all -> 0x006c }
    L_0x006c:
        r0 = move-exception;
        goto L_0x003e;
    L_0x006e:
        r0 = move-exception;
        r3 = r2;
    L_0x0070:
        throw r0;	 Catch:{ all -> 0x006c }
    L_0x0071:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0043;
    L_0x0076:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0048;
    L_0x007b:
        r0 = move-exception;
        r3 = r2;
        goto L_0x003e;
    L_0x007e:
        r0 = move-exception;
        r2 = r1;
        goto L_0x003e;
    L_0x0081:
        r0 = move-exception;
        goto L_0x0070;
    L_0x0083:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0070;
    L_0x0086:
        r0 = move-exception;
        goto L_0x006b;
    L_0x0088:
        r0 = move-exception;
        r2 = r1;
        goto L_0x006b;
    L_0x008b:
        r0 = move-exception;
        r1 = r2;
        goto L_0x003a;
    L_0x008e:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
        goto L_0x003a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.DexFileManager.m2695a(android.content.Context, com.amap.api.mapcore.util.ek, com.amap.api.mapcore.util.dv, com.amap.api.mapcore.util.fd, java.lang.String):void");
    }

    /* renamed from: a */
    static boolean m2699a(Context context, DBOperation dBOperation, String str, SDKInfo sDKInfo) {
        return DexFileManager.m2700a(dBOperation, str, DexFileManager.m2692a(context, str), sDKInfo);
    }

    /* renamed from: a */
    static boolean m2700a(DBOperation dBOperation, String str, String str2, SDKInfo sDKInfo) {
        DynamicSDKFile a = C0834a.m2687a(dBOperation, str);
        if (a != null && sDKInfo.mo9294b().equals(a.mo9375c()) && DexFileManager.m2701a(str2, a.mo9374b())) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    static String m2691a(Context context, DBOperation dBOperation, SDKInfo sDKInfo) {
        List b = dBOperation.mo9332b(DynamicSDKFile.m2723b(sDKInfo.mo9292a(), "copy"), DynamicSDKFile.class);
        if (b == null || b.size() == 0) {
            return null;
        }
        DexFileManager.m2698a(b);
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= b.size()) {
                return null;
            }
            DynamicSDKFile dynamicSDKFile = (DynamicSDKFile) b.get(i2);
            if (DexFileManager.m2699a(context, dBOperation, dynamicSDKFile.mo9373a(), sDKInfo)) {
                try {
                    DexFileManager.m2695a(context, dBOperation, sDKInfo, new C0835a(DexFileManager.m2693a(context, sDKInfo.mo9292a(), sDKInfo.mo9294b()), dynamicSDKFile.mo9374b(), sDKInfo.mo9292a(), sDKInfo.mo9294b(), dynamicSDKFile.mo9377d()).mo9371a("usedex").mo9372a(), DexFileManager.m2692a(context, dynamicSDKFile.mo9373a()));
                    return dynamicSDKFile.mo9377d();
                } catch (Throwable th) {
                    BasicLogHandler.m2542a(th, "DexFileManager", "loadAvailableDynamicSDKFile");
                }
            } else {
                DexFileManager.m2703b(context, dBOperation, dynamicSDKFile.mo9373a());
                i = i2 + 1;
            }
        }
    }

    /* renamed from: a */
    static void m2698a(List<DynamicSDKFile> list) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < list.size() - 1) {
                i = i2 + 1;
                while (true) {
                    int i3 = i;
                    if (i3 >= list.size()) {
                        break;
                    }
                    DynamicSDKFile dynamicSDKFile = (DynamicSDKFile) list.get(i2);
                    DynamicSDKFile dynamicSDKFile2 = (DynamicSDKFile) list.get(i3);
                    if (C0836ff.m2731a(dynamicSDKFile2.mo9377d(), dynamicSDKFile.mo9377d()) > 0) {
                        list.set(i2, dynamicSDKFile2);
                        list.set(i3, dynamicSDKFile);
                    }
                    i = i3 + 1;
                }
                i = i2 + 1;
            } else {
                return;
            }
        }
    }
}
