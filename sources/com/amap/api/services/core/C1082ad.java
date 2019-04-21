package com.amap.api.services.core;

import java.io.IOException;
import java.util.Arrays;

/* compiled from: Utils */
/* renamed from: com.amap.api.services.core.ad */
public class C1082ad {
    /* renamed from: a */
    public static String m4710a(String str) {
        if (str == null) {
            return null;
        }
        try {
            String[] split = str.split("&");
            Arrays.sort(split);
            StringBuffer stringBuffer = new StringBuffer();
            for (String append : split) {
                stringBuffer.append(append);
                stringBuffer.append("&");
            }
            String stringBuffer2 = stringBuffer.toString();
            if (stringBuffer2.length() > 1) {
                return (String) stringBuffer2.subSequence(0, stringBuffer2.length() - 1);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            C1099ax.m4800a(th, "Utils", "sortParams");
        }
        return str;
    }

    /* renamed from: a */
    public static byte[] m4712a(byte[] bArr) {
        try {
            return C1082ad.m4717f(bArr);
        } catch (IOException e) {
            C1099ax.m4800a(e, "Utils", "gZip");
            e.printStackTrace();
        } catch (Throwable e2) {
            C1099ax.m4800a(e2, "Utils", "gZip");
            e2.printStackTrace();
        }
        return new byte[0];
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0039  */
    /* renamed from: a */
    static java.security.PublicKey m4711a(android.content.Context r4) throws java.security.cert.CertificateException, java.security.spec.InvalidKeySpecException, java.security.NoSuchAlgorithmException, java.lang.NullPointerException, java.io.IOException {
        /*
        r0 = 0;
        r1 = 674; // 0x2a2 float:9.44E-43 double:3.33E-321;
        r1 = new byte[r1];
        r1 = {48, -126, 2, -98, 48, -126, 2, 7, -96, 3, 2, 1, 2, 2, 9, 0, -99, 15, 119, 58, 44, -19, -105, -40, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 5, 5, 0, 48, 104, 49, 11, 48, 9, 6, 3, 85, 4, 6, 19, 2, 67, 78, 49, 19, 48, 17, 6, 3, 85, 4, 8, 12, 10, 83, 111, 109, 101, 45, 83, 116, 97, 116, 101, 49, 16, 48, 14, 6, 3, 85, 4, 7, 12, 7, 66, 101, 105, 106, 105, 110, 103, 49, 17, 48, 15, 6, 3, 85, 4, 10, 12, 8, 65, 117, 116, 111, 110, 97, 118, 105, 49, 31, 48, 29, 6, 3, 85, 4, 3, 12, 22, 99, 111, 109, 46, 97, 117, 116, 111, 110, 97, 118, 105, 46, 97, 112, 105, 115, 101, 114, 118, 101, 114, 48, 30, 23, 13, 49, 51, 48, 56, 49, 53, 48, 55, 53, 54, 53, 53, 90, 23, 13, 50, 51, 48, 56, 49, 51, 48, 55, 53, 54, 53, 53, 90, 48, 104, 49, 11, 48, 9, 6, 3, 85, 4, 6, 19, 2, 67, 78, 49, 19, 48, 17, 6, 3, 85, 4, 8, 12, 10, 83, 111, 109, 101, 45, 83, 116, 97, 116, 101, 49, 16, 48, 14, 6, 3, 85, 4, 7, 12, 7, 66, 101, 105, 106, 105, 110, 103, 49, 17, 48, 15, 6, 3, 85, 4, 10, 12, 8, 65, 117, 116, 111, 110, 97, 118, 105, 49, 31, 48, 29, 6, 3, 85, 4, 3, 12, 22, 99, 111, 109, 46, 97, 117, 116, 111, 110, 97, 118, 105, 46, 97, 112, 105, 115, 101, 114, 118, 101, 114, 48, -127, -97, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 1, 5, 0, 3, -127, -115, 0, 48, -127, -119, 2, -127, -127, 0, -15, -27, -128, -56, 118, -59, 62, -127, 79, 125, -36, 121, 0, 63, -125, -30, 118, 5, -85, -121, 91, 39, 90, 123, 72, -126, -83, -41, -45, -77, -42, -120, -81, 23, -2, -121, -29, 123, -7, 22, -114, -20, -25, 74, 67, -43, 65, 124, -7, 11, -72, 38, -123, 16, -58, 80, 32, 58, -33, 14, 11, 36, 60, 13, -121, 100, 105, -32, 123, -31, 114, -101, -41, 12, 100, 33, -120, 63, 126, -123, 48, 55, 80, -116, 28, -10, 125, 59, -41, -95, -126, 118, -70, 43, -127, 9, 93, -100, 81, -19, -114, -41, 85, -103, -37, -116, 118, 72, 86, 125, -43, -92, -11, 63, 69, -38, -10, -65, 126, -53, -115, 60, 62, -86, -80, 1, 39, 19, 2, 3, 1, 0, 1, -93, 80, 48, 78, 48, 29, 6, 3, 85, 29, 14, 4, 22, 4, 20, -29, 63, 48, -79, -113, -13, 26, 85, 22, -27, 93, -5, 122, -103, -109, 14, -18, 6, -13, -109, 48, 31, 6, 3, 85, 29, 35, 4, 24, 48, 22, -128, 20, -29, 63, 48, -79, -113, -13, 26, 85, 22, -27, 93, -5, 122, -103, -109, 14, -18, 6, -13, -109, 48, 12, 6, 3, 85, 29, 19, 4, 5, 48, 3, 1, 1, -1, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 5, 5, 0, 3, -127, -127, 0, -32, -74, 55, -125, -58, -128, 15, -62, 100, -60, 3, -86, 81, 112, -61, -56, -69, -126, 8, 99, -100, -38, -108, -56, -122, 125, 19, -64, -61, 90, 85, -47, -8, -123, -103, 105, 77, -32, -65, -62, -28, 67, -28, -78, 116, -49, 120, -2, 33, 13, 47, 46, -5, -112, 3, -101, -125, -115, 92, -124, 58, 80, 107, -67, 82, 6, -63, 39, -90, -1, 85, -58, 82, -115, 119, 13, -4, -32, 0, -98, 100, -41, 94, -75, 75, -103, 126, -80, 85, 40, -27, 60, 105, 28, -27, -21, -15, -98, 103, -88, -109, 35, -119, -27, -26, -122, 113, 63, 35, -33, 70, 23, 33, -23, 66, 108, 56, 112, 46, -85, -123, -123, 33, 118, 27, 96, -7, -103};
        r3 = new java.io.ByteArrayInputStream;	 Catch:{ Throwable -> 0x0027, all -> 0x0034 }
        r3.<init>(r1);	 Catch:{ Throwable -> 0x0027, all -> 0x0034 }
        r1 = "X.509";
        r1 = java.security.cert.CertificateFactory.getInstance(r1);	 Catch:{ Throwable -> 0x0051 }
        r2 = "RSA";
        r2 = java.security.KeyFactory.getInstance(r2);	 Catch:{ Throwable -> 0x0051 }
        r1 = r1.generateCertificate(r3);	 Catch:{ Throwable -> 0x0054 }
        if (r3 == 0) goto L_0x0022;
    L_0x001f:
        r3.close();
    L_0x0022:
        if (r1 == 0) goto L_0x0026;
    L_0x0024:
        if (r2 != 0) goto L_0x003d;
    L_0x0026:
        return r0;
    L_0x0027:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
    L_0x002a:
        r1.printStackTrace();	 Catch:{ all -> 0x004f }
        if (r3 == 0) goto L_0x0056;
    L_0x002f:
        r3.close();
        r1 = r0;
        goto L_0x0022;
    L_0x0034:
        r1 = move-exception;
        r3 = r0;
        r0 = r1;
    L_0x0037:
        if (r3 == 0) goto L_0x003c;
    L_0x0039:
        r3.close();
    L_0x003c:
        throw r0;
    L_0x003d:
        r0 = new java.security.spec.X509EncodedKeySpec;
        r1 = r1.getPublicKey();
        r1 = r1.getEncoded();
        r0.<init>(r1);
        r0 = r2.generatePublic(r0);
        goto L_0x0026;
    L_0x004f:
        r0 = move-exception;
        goto L_0x0037;
    L_0x0051:
        r1 = move-exception;
        r2 = r0;
        goto L_0x002a;
    L_0x0054:
        r1 = move-exception;
        goto L_0x002a;
    L_0x0056:
        r1 = r0;
        goto L_0x0022;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.core.C1082ad.m4711a(android.content.Context):java.security.PublicKey");
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0056 A:{SYNTHETIC, Splitter:B:27:0x0056} */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x005b A:{SYNTHETIC, Splitter:B:30:0x005b} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0075 A:{SYNTHETIC, Splitter:B:39:0x0075} */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x007a A:{SYNTHETIC, Splitter:B:42:0x007a} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0075 A:{SYNTHETIC, Splitter:B:39:0x0075} */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x007a A:{SYNTHETIC, Splitter:B:42:0x007a} */
    /* JADX WARNING: Missing block: B:21:0x0042, code skipped:
            r1.printStackTrace();
     */
    /* renamed from: b */
    public static byte[] m4713b(byte[] r6) {
        /*
        r0 = 0;
        if (r6 == 0) goto L_0x0006;
    L_0x0003:
        r1 = r6.length;
        if (r1 != 0) goto L_0x0007;
    L_0x0006:
        return r0;
    L_0x0007:
        r2 = new java.io.ByteArrayOutputStream;	 Catch:{ Throwable -> 0x0046, all -> 0x006f }
        r2.<init>();	 Catch:{ Throwable -> 0x0046, all -> 0x006f }
        r3 = new java.util.zip.ZipOutputStream;	 Catch:{ Throwable -> 0x00d1, all -> 0x00cb }
        r3.<init>(r2);	 Catch:{ Throwable -> 0x00d1, all -> 0x00cb }
        r1 = new java.util.zip.ZipEntry;	 Catch:{ Throwable -> 0x00d5 }
        r4 = "log";
        r1.<init>(r4);	 Catch:{ Throwable -> 0x00d5 }
        r3.putNextEntry(r1);	 Catch:{ Throwable -> 0x00d5 }
        r3.write(r6);	 Catch:{ Throwable -> 0x00d5 }
        r3.closeEntry();	 Catch:{ Throwable -> 0x00d5 }
        r3.finish();	 Catch:{ Throwable -> 0x00d5 }
        r0 = r2.toByteArray();	 Catch:{ Throwable -> 0x00d5 }
        if (r3 == 0) goto L_0x002d;
    L_0x002a:
        r3.close();	 Catch:{ Throwable -> 0x00b7 }
    L_0x002d:
        if (r2 == 0) goto L_0x0006;
    L_0x002f:
        r2.close();	 Catch:{ Throwable -> 0x0033 }
        goto L_0x0006;
    L_0x0033:
        r1 = move-exception;
        r2 = com.amap.api.services.core.C1099ax.m4801b();
        if (r2 == 0) goto L_0x0042;
    L_0x003a:
        r3 = "Utils";
        r4 = "zip2";
        r2.mo12039b(r1, r3, r4);
    L_0x0042:
        r1.printStackTrace();
        goto L_0x0006;
    L_0x0046:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
    L_0x0049:
        r1.printStackTrace();	 Catch:{ all -> 0x00cf }
        r4 = "Utils";
        r5 = "zip";
        com.amap.api.services.core.C1099ax.m4800a(r1, r4, r5);	 Catch:{ all -> 0x00cf }
        if (r3 == 0) goto L_0x0059;
    L_0x0056:
        r3.close();	 Catch:{ Throwable -> 0x00a4 }
    L_0x0059:
        if (r2 == 0) goto L_0x0006;
    L_0x005b:
        r2.close();	 Catch:{ Throwable -> 0x005f }
        goto L_0x0006;
    L_0x005f:
        r1 = move-exception;
        r2 = com.amap.api.services.core.C1099ax.m4801b();
        if (r2 == 0) goto L_0x0042;
    L_0x0066:
        r3 = "Utils";
        r4 = "zip2";
        r2.mo12039b(r1, r3, r4);
        goto L_0x0042;
    L_0x006f:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r0 = r1;
    L_0x0073:
        if (r3 == 0) goto L_0x0078;
    L_0x0075:
        r3.close();	 Catch:{ Throwable -> 0x007e }
    L_0x0078:
        if (r2 == 0) goto L_0x007d;
    L_0x007a:
        r2.close();	 Catch:{ Throwable -> 0x0091 }
    L_0x007d:
        throw r0;
    L_0x007e:
        r1 = move-exception;
        r3 = com.amap.api.services.core.C1099ax.m4801b();
        if (r3 == 0) goto L_0x008d;
    L_0x0085:
        r4 = "Utils";
        r5 = "zip1";
        r3.mo12039b(r1, r4, r5);
    L_0x008d:
        r1.printStackTrace();
        goto L_0x0078;
    L_0x0091:
        r1 = move-exception;
        r2 = com.amap.api.services.core.C1099ax.m4801b();
        if (r2 == 0) goto L_0x00a0;
    L_0x0098:
        r3 = "Utils";
        r4 = "zip2";
        r2.mo12039b(r1, r3, r4);
    L_0x00a0:
        r1.printStackTrace();
        goto L_0x007d;
    L_0x00a4:
        r1 = move-exception;
        r3 = com.amap.api.services.core.C1099ax.m4801b();
        if (r3 == 0) goto L_0x00b3;
    L_0x00ab:
        r4 = "Utils";
        r5 = "zip1";
        r3.mo12039b(r1, r4, r5);
    L_0x00b3:
        r1.printStackTrace();
        goto L_0x0059;
    L_0x00b7:
        r1 = move-exception;
        r3 = com.amap.api.services.core.C1099ax.m4801b();
        if (r3 == 0) goto L_0x00c6;
    L_0x00be:
        r4 = "Utils";
        r5 = "zip1";
        r3.mo12039b(r1, r4, r5);
    L_0x00c6:
        r1.printStackTrace();
        goto L_0x002d;
    L_0x00cb:
        r1 = move-exception;
        r3 = r0;
        r0 = r1;
        goto L_0x0073;
    L_0x00cf:
        r0 = move-exception;
        goto L_0x0073;
    L_0x00d1:
        r1 = move-exception;
        r3 = r0;
        goto L_0x0049;
    L_0x00d5:
        r1 = move-exception;
        goto L_0x0049;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.core.C1082ad.m4713b(byte[]):byte[]");
    }

    /* renamed from: c */
    static String m4714c(byte[] bArr) {
        try {
            return C1082ad.m4716e(bArr);
        } catch (Throwable th) {
            C1099ax.m4800a(th, "Utils", "HexString");
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: d */
    static String m4715d(byte[] bArr) {
        try {
            return C1082ad.m4716e(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: e */
    private static String m4716e(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        if (bArr == null) {
            return null;
        }
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() == 1) {
                toHexString = '0' + toHexString;
            }
            stringBuilder.append(toHexString);
        }
        return stringBuilder.toString();
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:19:0x002a=Splitter:B:19:0x002a, B:31:0x003c=Splitter:B:31:0x003c} */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x002e A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0033 A:{SYNTHETIC} */
    /* renamed from: f */
    private static byte[] m4717f(byte[] r4) throws java.io.IOException, java.lang.Throwable {
        /*
        r0 = 0;
        if (r4 != 0) goto L_0x0004;
    L_0x0003:
        return r0;
    L_0x0004:
        r2 = new java.io.ByteArrayOutputStream;	 Catch:{ IOException -> 0x0025, Throwable -> 0x0037, all -> 0x0043 }
        r2.<init>();	 Catch:{ IOException -> 0x0025, Throwable -> 0x0037, all -> 0x0043 }
        r1 = new java.util.zip.GZIPOutputStream;	 Catch:{ IOException -> 0x0055, Throwable -> 0x004e, all -> 0x0049 }
        r1.<init>(r2);	 Catch:{ IOException -> 0x0055, Throwable -> 0x004e, all -> 0x0049 }
        r1.write(r4);	 Catch:{ IOException -> 0x005a, Throwable -> 0x0053 }
        r1.finish();	 Catch:{ IOException -> 0x005a, Throwable -> 0x0053 }
        r0 = r2.toByteArray();	 Catch:{ IOException -> 0x005a, Throwable -> 0x0053 }
        if (r1 == 0) goto L_0x001d;
    L_0x001a:
        r1.close();	 Catch:{ Throwable -> 0x0041 }
    L_0x001d:
        if (r2 == 0) goto L_0x0003;
    L_0x001f:
        r2.close();	 Catch:{ Throwable -> 0x0023 }
        goto L_0x0003;
    L_0x0023:
        r0 = move-exception;
        throw r0;
    L_0x0025:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r0 = r1;
        r1 = r3;
    L_0x002a:
        throw r0;	 Catch:{ all -> 0x002b }
    L_0x002b:
        r0 = move-exception;
    L_0x002c:
        if (r1 == 0) goto L_0x0031;
    L_0x002e:
        r1.close();	 Catch:{ Throwable -> 0x003d }
    L_0x0031:
        if (r2 == 0) goto L_0x0036;
    L_0x0033:
        r2.close();	 Catch:{ Throwable -> 0x003f }
    L_0x0036:
        throw r0;
    L_0x0037:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r0 = r1;
        r1 = r3;
    L_0x003c:
        throw r0;	 Catch:{ all -> 0x002b }
    L_0x003d:
        r0 = move-exception;
        throw r0;
    L_0x003f:
        r0 = move-exception;
        throw r0;
    L_0x0041:
        r0 = move-exception;
        throw r0;
    L_0x0043:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r0 = r1;
        r1 = r3;
        goto L_0x002c;
    L_0x0049:
        r1 = move-exception;
        r3 = r1;
        r1 = r0;
        r0 = r3;
        goto L_0x002c;
    L_0x004e:
        r1 = move-exception;
        r3 = r1;
        r1 = r0;
        r0 = r3;
        goto L_0x003c;
    L_0x0053:
        r0 = move-exception;
        goto L_0x003c;
    L_0x0055:
        r1 = move-exception;
        r3 = r1;
        r1 = r0;
        r0 = r3;
        goto L_0x002a;
    L_0x005a:
        r0 = move-exception;
        goto L_0x002a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.core.C1082ad.m4717f(byte[]):byte[]");
    }
}
