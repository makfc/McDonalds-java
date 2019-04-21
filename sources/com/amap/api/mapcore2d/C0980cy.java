package com.amap.api.mapcore2d;

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

/* compiled from: StatisticsManager */
/* renamed from: com.amap.api.mapcore2d.cy */
public class C0980cy {
    /* renamed from: a */
    private static boolean f2796a = true;

    /* renamed from: b */
    private static byte[] m4070b(Context context) {
        byte[] c = C0980cy.m4071c(context);
        byte[] e = C0980cy.m4073e(context);
        byte[] bArr = new byte[(c.length + e.length)];
        System.arraycopy(c, 0, bArr, 0, c.length);
        System.arraycopy(e, 0, bArr, c.length, e.length);
        return C0980cy.m4068a(context, bArr);
    }

    /* renamed from: a */
    public static void m4066a(Context context) {
        try {
            if (C0980cy.m4075g(context)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date()));
                stringBuffer.append(" ");
                stringBuffer.append(UUID.randomUUID().toString());
                stringBuffer.append(" ");
                if (stringBuffer.length() == 53) {
                    byte[] a = C0978cw.m4050a(stringBuffer.toString());
                    byte[] b = C0980cy.m4070b(context);
                    byte[] bArr = new byte[(a.length + b.length)];
                    System.arraycopy(a, 0, bArr, 0, a.length);
                    System.arraycopy(b, 0, bArr, a.length, b.length);
                    C1021ea.m4260a().mo10270b(new C0986dc(C0978cw.m4054c(bArr), "2"));
                }
            }
        } catch (C0956cl e) {
            C0982da.m4076a(e, "StatisticsManager", "updateStaticsData");
        } catch (Throwable e2) {
            C0982da.m4076a(e2, "StatisticsManager", "updateStaticsData");
        }
    }

    /* renamed from: a */
    private static byte[] m4068a(Context context, byte[] bArr) {
        try {
            return C0966cp.m3944a(context, bArr);
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
    private static byte[] m4071c(Context context) {
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[0];
        try {
            C0978cw.m4047a(byteArrayOutputStream, "1.2.12.5");
            C0978cw.m4047a(byteArrayOutputStream, C0968cq.m3974q(context));
            C0978cw.m4047a(byteArrayOutputStream, C0968cq.m3966i(context));
            C0978cw.m4047a(byteArrayOutputStream, C0968cq.m3963f(context));
            C0978cw.m4047a(byteArrayOutputStream, Build.MANUFACTURER);
            C0978cw.m4047a(byteArrayOutputStream, Build.MODEL);
            C0978cw.m4047a(byteArrayOutputStream, Build.DEVICE);
            C0978cw.m4047a(byteArrayOutputStream, C0968cq.m3975r(context));
            C0978cw.m4047a(byteArrayOutputStream, C0957cm.m3903c(context));
            C0978cw.m4047a(byteArrayOutputStream, C0957cm.m3904d(context));
            C0978cw.m4047a(byteArrayOutputStream, C0957cm.m3906f(context));
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
    private static int m4072d(Context context) {
        try {
            File file = new File(C0985db.m4081a(context, C0985db.f2812e));
            if (file.exists()) {
                return file.list().length;
            }
            return 0;
        } catch (Throwable th) {
            C0982da.m4076a(th, "StatisticsManager", "getFileNum");
            return 0;
        }
    }

    /* renamed from: e */
    private static byte[] m4073e(Context context) {
        Throwable th;
        int i = 0;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[0];
        String a = C0985db.m4081a(context, C0985db.f2812e);
        C1017dw c1017dw = null;
        try {
            c1017dw = C1017dw.m4228a(new File(a), 1, 1, 10240);
            File file = new File(a);
            if (file != null && file.exists()) {
                String[] list = file.list();
                int length = list.length;
                while (i < length) {
                    String str = list[i];
                    if (str.contains(".0")) {
                        byteArrayOutputStream.write(C0980cy.m4069a(c1017dw, str.split("\\.")[0]));
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
            if (c1017dw != null) {
                try {
                    c1017dw.close();
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        } catch (IOException th3) {
            C0982da.m4076a(th3, "StatisticsManager", "getContent");
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            if (c1017dw != null) {
                c1017dw.close();
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
    private static byte[] m4069a(com.amap.api.mapcore2d.C1017dw r5, java.lang.String r6) {
        /*
        r2 = 0;
        r0 = 0;
        r1 = new byte[r0];
        r3 = r5.mo10257a(r6);	 Catch:{ Throwable -> 0x0024, all -> 0x003c }
        r0 = 0;
        r2 = r3.mo10252a(r0);	 Catch:{ Throwable -> 0x0061 }
        r0 = r2.available();	 Catch:{ Throwable -> 0x0061 }
        r0 = new byte[r0];	 Catch:{ Throwable -> 0x0061 }
        r2.read(r0);	 Catch:{ Throwable -> 0x0066 }
        r5.mo10263c(r6);	 Catch:{ Throwable -> 0x0066 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore2d.C0980cy.m4069a(com.amap.api.mapcore2d.dw, java.lang.String):byte[]");
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:13:0x0035=Splitter:B:13:0x0035, B:22:0x0045=Splitter:B:22:0x0045} */
    /* JADX WARNING: Removed duplicated region for block: B:42:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003a A:{SYNTHETIC, Splitter:B:16:0x003a} */
    /* JADX WARNING: Removed duplicated region for block: B:44:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004a A:{SYNTHETIC, Splitter:B:25:0x004a} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0054 A:{SYNTHETIC, Splitter:B:31:0x0054} */
    /* renamed from: a */
    private static void m4067a(android.content.Context r5, long r6) {
        /*
        r0 = "c.log";
        r0 = com.amap.api.mapcore2d.C0985db.m4081a(r5, r0);
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
        r0 = com.amap.api.mapcore2d.C0978cw.m4050a(r0);	 Catch:{ FileNotFoundException -> 0x0063, IOException -> 0x0061 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore2d.C0980cy.m4067a(android.content.Context, long):void");
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
    private static long m4074f(android.content.Context r7) {
        /*
        r0 = 0;
        r2 = "c.log";
        r2 = com.amap.api.mapcore2d.C0985db.m4081a(r7, r2);
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
        r2 = com.amap.api.mapcore2d.C0978cw.m4044a(r2);	 Catch:{ FileNotFoundException -> 0x008c, IOException -> 0x008a, Throwable -> 0x0088 }
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
        com.amap.api.mapcore2d.C0982da.m4076a(r2, r4, r5);	 Catch:{ all -> 0x0079 }
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
        com.amap.api.mapcore2d.C0982da.m4076a(r2, r4, r5);	 Catch:{ all -> 0x0079 }
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
        com.amap.api.mapcore2d.C0982da.m4076a(r2, r4, r6);	 Catch:{ all -> 0x0079 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore2d.C0980cy.m4074f(android.content.Context):long");
    }

    /* renamed from: g */
    private static boolean m4075g(Context context) {
        try {
            if (C0968cq.m3970m(context) != 1 || !f2796a || C0980cy.m4072d(context) < 100) {
                return false;
            }
            long f = C0980cy.m4074f(context);
            long time = new Date().getTime();
            if (time - f < 3600000) {
                return false;
            }
            C0980cy.m4067a(context, time);
            f2796a = false;
            return true;
        } catch (Throwable th) {
            C0982da.m4076a(th, "StatisticsManager", "isUpdate");
            return false;
        }
    }
}
