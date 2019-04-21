package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Build;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* renamed from: com.amap.api.mapcore.util.dz */
public class StatisticsManager {
    /* renamed from: a */
    private static boolean f1840a = true;

    /* renamed from: b */
    private static byte[] m2536b(Context context) {
        byte[] c = StatisticsManager.m2537c(context);
        byte[] e = StatisticsManager.m2539e(context);
        byte[] bArr = new byte[(c.length + e.length)];
        System.arraycopy(c, 0, bArr, 0, c.length);
        System.arraycopy(e, 0, bArr, c.length, e.length);
        return StatisticsManager.m2534a(context, bArr);
    }

    /* renamed from: a */
    public static void m2532a(Context context) {
        try {
            if (StatisticsManager.m2541g(context)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date()));
                stringBuffer.append(" ");
                stringBuffer.append(UUID.randomUUID().toString());
                stringBuffer.append(" ");
                if (stringBuffer.length() == 53) {
                    byte[] a = Utils.m2515a(stringBuffer.toString());
                    byte[] b = StatisticsManager.m2536b(context);
                    byte[] bArr = new byte[(a.length + b.length)];
                    System.arraycopy(a, 0, bArr, 0, a.length);
                    System.arraycopy(b, 0, bArr, a.length, b.length);
                    BaseNetManager.m2800a().mo9415b(new LogUpdateRequest(Utils.m2520c(bArr), "2"));
                }
            }
        } catch (AMapCoreException e) {
            BasicLogHandler.m2542a(e, "StatisticsManager", "updateStaticsData");
        } catch (Throwable e2) {
            BasicLogHandler.m2542a(e2, "StatisticsManager", "updateStaticsData");
        }
    }

    /* renamed from: a */
    private static byte[] m2534a(Context context, byte[] bArr) {
        try {
            return ClientInfo.m2404a(context, bArr);
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e2) {
            e2.printStackTrace();
        } catch (NoSuchAlgorithmException e3) {
            e3.printStackTrace();
        } catch (IOException e4) {
            e4.printStackTrace();
        } catch (InvalidKeyException e5) {
            e5.printStackTrace();
        } catch (NoSuchPaddingException e6) {
            e6.printStackTrace();
        } catch (IllegalBlockSizeException e7) {
            e7.printStackTrace();
        } catch (BadPaddingException e8) {
            e8.printStackTrace();
        }
        return null;
    }

    /* renamed from: c */
    private static byte[] m2537c(Context context) {
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[0];
        try {
            Utils.m2512a(byteArrayOutputStream, "1.2.12.5");
            Utils.m2512a(byteArrayOutputStream, C0820dq.m2443q(context));
            Utils.m2512a(byteArrayOutputStream, C0820dq.m2435i(context));
            Utils.m2512a(byteArrayOutputStream, C0820dq.m2432f(context));
            Utils.m2512a(byteArrayOutputStream, Build.MANUFACTURER);
            Utils.m2512a(byteArrayOutputStream, Build.MODEL);
            Utils.m2512a(byteArrayOutputStream, Build.DEVICE);
            Utils.m2512a(byteArrayOutputStream, C0820dq.m2444r(context));
            Utils.m2512a(byteArrayOutputStream, AppInfo.m2384c(context));
            Utils.m2512a(byteArrayOutputStream, AppInfo.m2385d(context));
            Utils.m2512a(byteArrayOutputStream, AppInfo.m2387f(context));
            byteArrayOutputStream.write(new byte[]{(byte) 0});
            bArr = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th = th2;
                th.printStackTrace();
                return bArr;
            }
        } catch (Throwable th3) {
            th = th3;
        }
        return bArr;
    }

    /* renamed from: d */
    private static int m2538d(Context context) {
        try {
            File file = new File(Log.m2547a(context, Log.f1857e));
            if (file.exists()) {
                return file.list().length;
            }
            return 0;
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "StatisticsManager", "getFileNum");
            return 0;
        }
    }

    /* renamed from: e */
    private static byte[] m2539e(Context context) {
        Throwable th;
        int i = 0;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[0];
        String a = Log.m2547a(context, Log.f1857e);
        DiskLruCache diskLruCache = null;
        try {
            diskLruCache = DiskLruCache.m2767a(new File(a), 1, 1, 10240);
            File file = new File(a);
            if (file != null && file.exists()) {
                String[] list = file.list();
                int length = list.length;
                while (i < length) {
                    String str = list[i];
                    if (str.contains(".0")) {
                        byteArrayOutputStream.write(StatisticsManager.m2535a(diskLruCache, str.split("\\.")[0]));
                    }
                    i++;
                }
            }
            bArr = byteArrayOutputStream.toByteArray();
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (diskLruCache != null) {
                try {
                    diskLruCache.close();
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        } catch (IOException th3) {
            BasicLogHandler.m2542a(th3, "StatisticsManager", "getContent");
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            if (diskLruCache != null) {
                diskLruCache.close();
            }
        } catch (Throwable th4) {
            th3 = th4;
        }
        return bArr;
        th3.printStackTrace();
        return bArr;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x002e A:{SYNTHETIC, Splitter:B:20:0x002e} */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0033 A:{SYNTHETIC, Splitter:B:23:0x0033} */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x002e A:{SYNTHETIC, Splitter:B:20:0x002e} */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0033 A:{SYNTHETIC, Splitter:B:23:0x0033} */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0040 A:{SYNTHETIC, Splitter:B:30:0x0040} */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0045 A:{SYNTHETIC, Splitter:B:33:0x0045} */
    /* renamed from: a */
    private static byte[] m2535a(com.amap.api.mapcore.util.DiskLruCache r5, java.lang.String r6) {
        /*
        r2 = 0;
        r0 = 0;
        r1 = new byte[r0];
        r3 = r5.mo9399a(r6);	 Catch:{ Throwable -> 0x0024, all -> 0x003c }
        r0 = 0;
        r2 = r3.mo9394a(r0);	 Catch:{ Throwable -> 0x0061 }
        r0 = r2.available();	 Catch:{ Throwable -> 0x0061 }
        r0 = new byte[r0];	 Catch:{ Throwable -> 0x0061 }
        r2.read(r0);	 Catch:{ Throwable -> 0x0066 }
        r5.mo9405c(r6);	 Catch:{ Throwable -> 0x0066 }
        if (r2 == 0) goto L_0x001e;
    L_0x001b:
        r2.close();	 Catch:{ Throwable -> 0x0058 }
    L_0x001e:
        if (r3 == 0) goto L_0x0023;
    L_0x0020:
        r3.close();	 Catch:{ Throwable -> 0x005d }
    L_0x0023:
        return r0;
    L_0x0024:
        r0 = move-exception;
        r3 = r2;
        r4 = r1;
        r1 = r0;
        r0 = r4;
    L_0x0029:
        r1.printStackTrace();	 Catch:{ all -> 0x005f }
        if (r2 == 0) goto L_0x0031;
    L_0x002e:
        r2.close();	 Catch:{ Throwable -> 0x0053 }
    L_0x0031:
        if (r3 == 0) goto L_0x0023;
    L_0x0033:
        r3.close();	 Catch:{ Throwable -> 0x0037 }
        goto L_0x0023;
    L_0x0037:
        r1 = move-exception;
    L_0x0038:
        r1.printStackTrace();
        goto L_0x0023;
    L_0x003c:
        r0 = move-exception;
        r3 = r2;
    L_0x003e:
        if (r2 == 0) goto L_0x0043;
    L_0x0040:
        r2.close();	 Catch:{ Throwable -> 0x0049 }
    L_0x0043:
        if (r3 == 0) goto L_0x0048;
    L_0x0045:
        r3.close();	 Catch:{ Throwable -> 0x004e }
    L_0x0048:
        throw r0;
    L_0x0049:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0043;
    L_0x004e:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0048;
    L_0x0053:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0031;
    L_0x0058:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x001e;
    L_0x005d:
        r1 = move-exception;
        goto L_0x0038;
    L_0x005f:
        r0 = move-exception;
        goto L_0x003e;
    L_0x0061:
        r0 = move-exception;
        r4 = r0;
        r0 = r1;
        r1 = r4;
        goto L_0x0029;
    L_0x0066:
        r1 = move-exception;
        goto L_0x0029;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.StatisticsManager.m2535a(com.amap.api.mapcore.util.fk, java.lang.String):byte[]");
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:13:0x0035=Splitter:B:13:0x0035, B:22:0x0045=Splitter:B:22:0x0045} */
    /* JADX WARNING: Removed duplicated region for block: B:42:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003a A:{SYNTHETIC, Splitter:B:16:0x003a} */
    /* JADX WARNING: Removed duplicated region for block: B:44:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004a A:{SYNTHETIC, Splitter:B:25:0x004a} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0054 A:{SYNTHETIC, Splitter:B:31:0x0054} */
    /* renamed from: a */
    private static void m2533a(android.content.Context r5, long r6) {
        /*
        r0 = "c.log";
        r0 = com.amap.api.mapcore.util.Log.m2547a(r5, r0);
        r3 = new java.io.File;
        r3.<init>(r0);
        r0 = r3.getParentFile();
        r0 = r0.exists();
        if (r0 != 0) goto L_0x001c;
    L_0x0015:
        r0 = r3.getParentFile();
        r0.mkdirs();
    L_0x001c:
        r2 = 0;
        r1 = new java.io.FileOutputStream;	 Catch:{ FileNotFoundException -> 0x0033, IOException -> 0x0043, all -> 0x0050 }
        r1.<init>(r3);	 Catch:{ FileNotFoundException -> 0x0033, IOException -> 0x0043, all -> 0x0050 }
        r0 = java.lang.String.valueOf(r6);	 Catch:{ FileNotFoundException -> 0x0063, IOException -> 0x0061 }
        r0 = com.amap.api.mapcore.util.Utils.m2515a(r0);	 Catch:{ FileNotFoundException -> 0x0063, IOException -> 0x0061 }
        r1.write(r0);	 Catch:{ FileNotFoundException -> 0x0063, IOException -> 0x0061 }
        if (r1 == 0) goto L_0x0032;
    L_0x002f:
        r1.close();	 Catch:{ Throwable -> 0x005d }
    L_0x0032:
        return;
    L_0x0033:
        r0 = move-exception;
        r1 = r2;
    L_0x0035:
        r0.printStackTrace();	 Catch:{ all -> 0x005f }
        if (r1 == 0) goto L_0x0032;
    L_0x003a:
        r1.close();	 Catch:{ Throwable -> 0x003e }
        goto L_0x0032;
    L_0x003e:
        r0 = move-exception;
    L_0x003f:
        r0.printStackTrace();
        goto L_0x0032;
    L_0x0043:
        r0 = move-exception;
        r1 = r2;
    L_0x0045:
        r0.printStackTrace();	 Catch:{ all -> 0x005f }
        if (r1 == 0) goto L_0x0032;
    L_0x004a:
        r1.close();	 Catch:{ Throwable -> 0x004e }
        goto L_0x0032;
    L_0x004e:
        r0 = move-exception;
        goto L_0x003f;
    L_0x0050:
        r0 = move-exception;
        r1 = r2;
    L_0x0052:
        if (r1 == 0) goto L_0x0057;
    L_0x0054:
        r1.close();	 Catch:{ Throwable -> 0x0058 }
    L_0x0057:
        throw r0;
    L_0x0058:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0057;
    L_0x005d:
        r0 = move-exception;
        goto L_0x003f;
    L_0x005f:
        r0 = move-exception;
        goto L_0x0052;
    L_0x0061:
        r0 = move-exception;
        goto L_0x0045;
    L_0x0063:
        r0 = move-exception;
        goto L_0x0035;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.StatisticsManager.m2533a(android.content.Context, long):void");
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:15:0x0038=Splitter:B:15:0x0038, B:23:0x0049=Splitter:B:23:0x0049, B:31:0x005a=Splitter:B:31:0x005a} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0052 A:{SYNTHETIC, Splitter:B:26:0x0052} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0063 A:{SYNTHETIC, Splitter:B:34:0x0063} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x006e A:{SYNTHETIC, Splitter:B:39:0x006e} */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:15:0x0038, B:34:0x0063] */
    /* JADX WARNING: Missing block: B:42:0x0074, code skipped:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:44:?, code skipped:
            r2.printStackTrace();
     */
    /* JADX WARNING: Missing block: B:45:0x0079, code skipped:
            r0 = th;
     */
    /* renamed from: f */
    private static long m2540f(android.content.Context r7) {
        /*
        r0 = 0;
        r2 = "c.log";
        r2 = com.amap.api.mapcore.util.Log.m2547a(r7, r2);
        r5 = new java.io.File;
        r5.<init>(r2);
        r2 = r5.exists();
        if (r2 != 0) goto L_0x0014;
    L_0x0013:
        return r0;
    L_0x0014:
        r4 = 0;
        r3 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x0036, IOException -> 0x0047, Throwable -> 0x0058, all -> 0x0085 }
        r3.<init>(r5);	 Catch:{ FileNotFoundException -> 0x0036, IOException -> 0x0047, Throwable -> 0x0058, all -> 0x0085 }
        r2 = r3.available();	 Catch:{ FileNotFoundException -> 0x008c, IOException -> 0x008a, Throwable -> 0x0088 }
        r2 = new byte[r2];	 Catch:{ FileNotFoundException -> 0x008c, IOException -> 0x008a, Throwable -> 0x0088 }
        r3.read(r2);	 Catch:{ FileNotFoundException -> 0x008c, IOException -> 0x008a, Throwable -> 0x0088 }
        r2 = com.amap.api.mapcore.util.Utils.m2509a(r2);	 Catch:{ FileNotFoundException -> 0x008c, IOException -> 0x008a, Throwable -> 0x0088 }
        r0 = java.lang.Long.parseLong(r2);	 Catch:{ FileNotFoundException -> 0x008c, IOException -> 0x008a, Throwable -> 0x0088 }
        if (r3 == 0) goto L_0x0013;
    L_0x002d:
        r3.close();	 Catch:{ Throwable -> 0x0031 }
        goto L_0x0013;
    L_0x0031:
        r2 = move-exception;
    L_0x0032:
        r2.printStackTrace();
        goto L_0x0013;
    L_0x0036:
        r2 = move-exception;
        r3 = r4;
    L_0x0038:
        r4 = "StatisticsManager";
        r5 = "getUpdateTime";
        com.amap.api.mapcore.util.BasicLogHandler.m2542a(r2, r4, r5);	 Catch:{ all -> 0x0079 }
        if (r3 == 0) goto L_0x0013;
    L_0x0041:
        r3.close();	 Catch:{ Throwable -> 0x0045 }
        goto L_0x0013;
    L_0x0045:
        r2 = move-exception;
        goto L_0x0032;
    L_0x0047:
        r2 = move-exception;
        r3 = r4;
    L_0x0049:
        r4 = "StatisticsManager";
        r5 = "getUpdateTime";
        com.amap.api.mapcore.util.BasicLogHandler.m2542a(r2, r4, r5);	 Catch:{ all -> 0x0079 }
        if (r3 == 0) goto L_0x0013;
    L_0x0052:
        r3.close();	 Catch:{ Throwable -> 0x0056 }
        goto L_0x0013;
    L_0x0056:
        r2 = move-exception;
        goto L_0x0032;
    L_0x0058:
        r2 = move-exception;
        r3 = r4;
    L_0x005a:
        r4 = "StatisticsManager";
        r6 = "getUpdateTime";
        com.amap.api.mapcore.util.BasicLogHandler.m2542a(r2, r4, r6);	 Catch:{ all -> 0x0079 }
        if (r5 == 0) goto L_0x006c;
    L_0x0063:
        r2 = r5.exists();	 Catch:{ Throwable -> 0x0074 }
        if (r2 == 0) goto L_0x006c;
    L_0x0069:
        r5.delete();	 Catch:{ Throwable -> 0x0074 }
    L_0x006c:
        if (r3 == 0) goto L_0x0013;
    L_0x006e:
        r3.close();	 Catch:{ Throwable -> 0x0072 }
        goto L_0x0013;
    L_0x0072:
        r2 = move-exception;
        goto L_0x0032;
    L_0x0074:
        r2 = move-exception;
        r2.printStackTrace();	 Catch:{ all -> 0x0079 }
        goto L_0x006c;
    L_0x0079:
        r0 = move-exception;
    L_0x007a:
        if (r3 == 0) goto L_0x007f;
    L_0x007c:
        r3.close();	 Catch:{ Throwable -> 0x0080 }
    L_0x007f:
        throw r0;
    L_0x0080:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x007f;
    L_0x0085:
        r0 = move-exception;
        r3 = r4;
        goto L_0x007a;
    L_0x0088:
        r2 = move-exception;
        goto L_0x005a;
    L_0x008a:
        r2 = move-exception;
        goto L_0x0049;
    L_0x008c:
        r2 = move-exception;
        goto L_0x0038;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.StatisticsManager.m2540f(android.content.Context):long");
    }

    /* renamed from: g */
    private static boolean m2541g(Context context) {
        try {
            if (C0820dq.m2439m(context) != 1 || !f1840a || StatisticsManager.m2538d(context) < 100) {
                return false;
            }
            long f = StatisticsManager.m2540f(context);
            long time = new Date().getTime();
            if (time - f < 3600000) {
                return false;
            }
            StatisticsManager.m2533a(context, time);
            f1840a = false;
            return true;
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "StatisticsManager", "isUpdate");
            return false;
        }
    }
}
