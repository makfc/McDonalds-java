package com.amap.api.mapcore2d;

import android.text.TextUtils;
import com.facebook.stetho.common.Utf8Charset;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

/* compiled from: Utils */
/* renamed from: com.amap.api.mapcore2d.cw */
public class C0978cw {
    /* renamed from: a */
    public static void m4047a(ByteArrayOutputStream byteArrayOutputStream, String str) {
        C0978cw.m4046a(byteArrayOutputStream, (byte) str.length(), C0978cw.m4050a(str));
    }

    /* renamed from: a */
    public static String m4044a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        try {
            return new String(bArr, Utf8Charset.NAME);
        } catch (UnsupportedEncodingException e) {
            return new String(bArr);
        }
    }

    /* renamed from: a */
    public static byte[] m4050a(String str) {
        if (TextUtils.isEmpty(str)) {
            return new byte[0];
        }
        try {
            return str.getBytes(Utf8Charset.NAME);
        } catch (UnsupportedEncodingException e) {
            return str.getBytes();
        }
    }

    /* renamed from: a */
    public static void m4046a(ByteArrayOutputStream byteArrayOutputStream, byte b, byte[] bArr) {
        int i = 1;
        try {
            byteArrayOutputStream.write(new byte[]{b});
            int i2 = b > (byte) 0 ? 1 : 0;
            if ((b & 255) >= 255) {
                i = 0;
            }
            if ((i & i2) != 0) {
                byteArrayOutputStream.write(bArr);
            } else if ((b & 255) == 255) {
                byteArrayOutputStream.write(bArr, 0, 255);
            }
        } catch (IOException e) {
            C0982da.m4076a(e, "Utils", "writeField");
        }
    }

    /* renamed from: b */
    public static String m4051b(String str) {
        if (str == null) {
            return null;
        }
        String b = C0969cr.m3988b(C0978cw.m4050a(str));
        return ((char) ((b.length() % 26) + 65)) + b;
    }

    /* renamed from: c */
    public static String m4053c(String str) {
        if (str.length() < 2) {
            return "";
        }
        return C0969cr.m3984a(str.substring(1));
    }

    /* renamed from: a */
    public static boolean m4048a(JSONObject jSONObject, String str) {
        return jSONObject != null && jSONObject.has(str);
    }

    /* renamed from: a */
    public static byte[] m4049a() {
        int i = 0;
        try {
            String[] split = new StringBuffer("16,16,18,77,15,911,121,77,121,911,38,77,911,99,86,67,611,96,48,77,84,911,38,67,021,301,86,67,611,98,48,77,511,77,48,97,511,58,48,97,511,84,501,87,511,96,48,77,221,911,38,77,121,37,86,67,25,301,86,67,021,96,86,67,021,701,86,67,35,56,86,67,611,37,221,87").reverse().toString().split(",");
            byte[] bArr = new byte[split.length];
            for (int i2 = 0; i2 < split.length; i2++) {
                bArr[i2] = Byte.parseByte(split[i2]);
            }
            split = new StringBuffer(new String(C0969cr.m3989b(new String(bArr)))).reverse().toString().split(",");
            byte[] bArr2 = new byte[split.length];
            while (i < split.length) {
                bArr2[i] = Byte.parseByte(split[i]);
                i++;
            }
            return bArr2;
        } catch (Throwable th) {
            C0982da.m4076a(th, "Utils", "getIV");
            return new byte[16];
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0033 A:{SYNTHETIC, Splitter:B:22:0x0033} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0038 A:{SYNTHETIC, Splitter:B:25:0x0038} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0047 A:{SYNTHETIC, Splitter:B:32:0x0047} */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x004c A:{SYNTHETIC, Splitter:B:35:0x004c} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0033 A:{SYNTHETIC, Splitter:B:22:0x0033} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0038 A:{SYNTHETIC, Splitter:B:25:0x0038} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0047 A:{SYNTHETIC, Splitter:B:32:0x0047} */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x004c A:{SYNTHETIC, Splitter:B:35:0x004c} */
    /* renamed from: a */
    public static java.lang.String m4042a(java.lang.Throwable r4) {
        /*
        r0 = 0;
        r3 = new java.io.StringWriter;	 Catch:{ Throwable -> 0x002b, all -> 0x0041 }
        r3.<init>();	 Catch:{ Throwable -> 0x002b, all -> 0x0041 }
        r2 = new java.io.PrintWriter;	 Catch:{ Throwable -> 0x006c, all -> 0x0066 }
        r2.<init>(r3);	 Catch:{ Throwable -> 0x006c, all -> 0x0066 }
        r4.printStackTrace(r2);	 Catch:{ Throwable -> 0x006f }
        r1 = r4.getCause();	 Catch:{ Throwable -> 0x006f }
    L_0x0012:
        if (r1 == 0) goto L_0x001c;
    L_0x0014:
        r1.printStackTrace(r2);	 Catch:{ Throwable -> 0x006f }
        r1 = r1.getCause();	 Catch:{ Throwable -> 0x006f }
        goto L_0x0012;
    L_0x001c:
        r0 = r3.toString();	 Catch:{ Throwable -> 0x006f }
        if (r3 == 0) goto L_0x0025;
    L_0x0022:
        r3.close();	 Catch:{ Throwable -> 0x005f }
    L_0x0025:
        if (r2 == 0) goto L_0x002a;
    L_0x0027:
        r2.close();	 Catch:{ Throwable -> 0x0064 }
    L_0x002a:
        return r0;
    L_0x002b:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
    L_0x002e:
        r1.printStackTrace();	 Catch:{ all -> 0x006a }
        if (r3 == 0) goto L_0x0036;
    L_0x0033:
        r3.close();	 Catch:{ Throwable -> 0x005a }
    L_0x0036:
        if (r2 == 0) goto L_0x002a;
    L_0x0038:
        r2.close();	 Catch:{ Throwable -> 0x003c }
        goto L_0x002a;
    L_0x003c:
        r1 = move-exception;
    L_0x003d:
        r1.printStackTrace();
        goto L_0x002a;
    L_0x0041:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r0 = r1;
    L_0x0045:
        if (r3 == 0) goto L_0x004a;
    L_0x0047:
        r3.close();	 Catch:{ Throwable -> 0x0050 }
    L_0x004a:
        if (r2 == 0) goto L_0x004f;
    L_0x004c:
        r2.close();	 Catch:{ Throwable -> 0x0055 }
    L_0x004f:
        throw r0;
    L_0x0050:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x004a;
    L_0x0055:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x004f;
    L_0x005a:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0036;
    L_0x005f:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0025;
    L_0x0064:
        r1 = move-exception;
        goto L_0x003d;
    L_0x0066:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x0045;
    L_0x006a:
        r0 = move-exception;
        goto L_0x0045;
    L_0x006c:
        r1 = move-exception;
        r2 = r0;
        goto L_0x002e;
    L_0x006f:
        r1 = move-exception;
        goto L_0x002e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore2d.C0978cw.m4042a(java.lang.Throwable):java.lang.String");
    }

    /* renamed from: a */
    public static String m4043a(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        Object obj = 1;
        try {
            for (Entry entry : map.entrySet()) {
                if (obj != null) {
                    obj = null;
                    stringBuffer.append((String) entry.getKey()).append("=").append((String) entry.getValue());
                } else {
                    stringBuffer.append("&").append((String) entry.getKey()).append("=").append((String) entry.getValue());
                }
                obj = obj;
            }
        } catch (Throwable th) {
            C0982da.m4076a(th, "Utils", "assembleParams");
        }
        return stringBuffer.toString();
    }

    /* renamed from: d */
    public static String m4055d(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return "";
            }
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
            return str;
        } catch (Throwable th) {
            C0982da.m4076a(th, "Utils", "sortParams");
        }
    }

    /* renamed from: b */
    public static byte[] m4052b(byte[] bArr) {
        try {
            return C0978cw.m4059g(bArr);
        } catch (Throwable th) {
            C0982da.m4076a(th, "Utils", "gZip");
            return new byte[0];
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0060 A:{SYNTHETIC, Splitter:B:35:0x0060} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0065 A:{SYNTHETIC, Splitter:B:38:0x0065} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004a A:{SYNTHETIC, Splitter:B:25:0x004a} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x004f A:{SYNTHETIC, Splitter:B:28:0x004f} */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0060 A:{SYNTHETIC, Splitter:B:35:0x0060} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0065 A:{SYNTHETIC, Splitter:B:38:0x0065} */
    /* renamed from: c */
    public static byte[] m4054c(byte[] r6) {
        /*
        r0 = 0;
        if (r6 == 0) goto L_0x0006;
    L_0x0003:
        r1 = r6.length;
        if (r1 != 0) goto L_0x0007;
    L_0x0006:
        return r0;
    L_0x0007:
        r2 = new java.io.ByteArrayOutputStream;	 Catch:{ Throwable -> 0x003d, all -> 0x005a }
        r2.<init>();	 Catch:{ Throwable -> 0x003d, all -> 0x005a }
        r3 = new java.util.zip.ZipOutputStream;	 Catch:{ Throwable -> 0x0097, all -> 0x0091 }
        r3.<init>(r2);	 Catch:{ Throwable -> 0x0097, all -> 0x0091 }
        r1 = new java.util.zip.ZipEntry;	 Catch:{ Throwable -> 0x009a }
        r4 = "log";
        r1.<init>(r4);	 Catch:{ Throwable -> 0x009a }
        r3.putNextEntry(r1);	 Catch:{ Throwable -> 0x009a }
        r3.write(r6);	 Catch:{ Throwable -> 0x009a }
        r3.closeEntry();	 Catch:{ Throwable -> 0x009a }
        r3.finish();	 Catch:{ Throwable -> 0x009a }
        r0 = r2.toByteArray();	 Catch:{ Throwable -> 0x009a }
        if (r3 == 0) goto L_0x002d;
    L_0x002a:
        r3.close();	 Catch:{ Throwable -> 0x0087 }
    L_0x002d:
        if (r2 == 0) goto L_0x0006;
    L_0x002f:
        r2.close();	 Catch:{ Throwable -> 0x0033 }
        goto L_0x0006;
    L_0x0033:
        r1 = move-exception;
        r2 = "Utils";
        r3 = "zip2";
    L_0x0039:
        com.amap.api.mapcore2d.C0982da.m4076a(r1, r2, r3);
        goto L_0x0006;
    L_0x003d:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
    L_0x0040:
        r4 = "Utils";
        r5 = "zip";
        com.amap.api.mapcore2d.C0982da.m4076a(r1, r4, r5);	 Catch:{ all -> 0x0095 }
        if (r3 == 0) goto L_0x004d;
    L_0x004a:
        r3.close();	 Catch:{ Throwable -> 0x007d }
    L_0x004d:
        if (r2 == 0) goto L_0x0006;
    L_0x004f:
        r2.close();	 Catch:{ Throwable -> 0x0053 }
        goto L_0x0006;
    L_0x0053:
        r1 = move-exception;
        r2 = "Utils";
        r3 = "zip2";
        goto L_0x0039;
    L_0x005a:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r0 = r1;
    L_0x005e:
        if (r3 == 0) goto L_0x0063;
    L_0x0060:
        r3.close();	 Catch:{ Throwable -> 0x0069 }
    L_0x0063:
        if (r2 == 0) goto L_0x0068;
    L_0x0065:
        r2.close();	 Catch:{ Throwable -> 0x0073 }
    L_0x0068:
        throw r0;
    L_0x0069:
        r1 = move-exception;
        r3 = "Utils";
        r4 = "zip1";
        com.amap.api.mapcore2d.C0982da.m4076a(r1, r3, r4);
        goto L_0x0063;
    L_0x0073:
        r1 = move-exception;
        r2 = "Utils";
        r3 = "zip2";
        com.amap.api.mapcore2d.C0982da.m4076a(r1, r2, r3);
        goto L_0x0068;
    L_0x007d:
        r1 = move-exception;
        r3 = "Utils";
        r4 = "zip1";
        com.amap.api.mapcore2d.C0982da.m4076a(r1, r3, r4);
        goto L_0x004d;
    L_0x0087:
        r1 = move-exception;
        r3 = "Utils";
        r4 = "zip1";
        com.amap.api.mapcore2d.C0982da.m4076a(r1, r3, r4);
        goto L_0x002d;
    L_0x0091:
        r1 = move-exception;
        r3 = r0;
        r0 = r1;
        goto L_0x005e;
    L_0x0095:
        r0 = move-exception;
        goto L_0x005e;
    L_0x0097:
        r1 = move-exception;
        r3 = r0;
        goto L_0x0040;
    L_0x009a:
        r1 = move-exception;
        goto L_0x0040;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore2d.C0978cw.m4054c(byte[]):byte[]");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0055 A:{SYNTHETIC, Splitter:B:29:0x0055} */
    /* renamed from: a */
    static java.security.PublicKey m4045a(android.content.Context r5) throws java.security.cert.CertificateException, java.security.spec.InvalidKeySpecException, java.security.NoSuchAlgorithmException, java.lang.NullPointerException, java.io.IOException {
        /*
        r0 = 0;
        r1 = 674; // 0x2a2 float:9.44E-43 double:3.33E-321;
        r1 = new byte[r1];
        r1 = {48, -126, 2, -98, 48, -126, 2, 7, -96, 3, 2, 1, 2, 2, 9, 0, -99, 15, 119, 58, 44, -19, -105, -40, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 5, 5, 0, 48, 104, 49, 11, 48, 9, 6, 3, 85, 4, 6, 19, 2, 67, 78, 49, 19, 48, 17, 6, 3, 85, 4, 8, 12, 10, 83, 111, 109, 101, 45, 83, 116, 97, 116, 101, 49, 16, 48, 14, 6, 3, 85, 4, 7, 12, 7, 66, 101, 105, 106, 105, 110, 103, 49, 17, 48, 15, 6, 3, 85, 4, 10, 12, 8, 65, 117, 116, 111, 110, 97, 118, 105, 49, 31, 48, 29, 6, 3, 85, 4, 3, 12, 22, 99, 111, 109, 46, 97, 117, 116, 111, 110, 97, 118, 105, 46, 97, 112, 105, 115, 101, 114, 118, 101, 114, 48, 30, 23, 13, 49, 51, 48, 56, 49, 53, 48, 55, 53, 54, 53, 53, 90, 23, 13, 50, 51, 48, 56, 49, 51, 48, 55, 53, 54, 53, 53, 90, 48, 104, 49, 11, 48, 9, 6, 3, 85, 4, 6, 19, 2, 67, 78, 49, 19, 48, 17, 6, 3, 85, 4, 8, 12, 10, 83, 111, 109, 101, 45, 83, 116, 97, 116, 101, 49, 16, 48, 14, 6, 3, 85, 4, 7, 12, 7, 66, 101, 105, 106, 105, 110, 103, 49, 17, 48, 15, 6, 3, 85, 4, 10, 12, 8, 65, 117, 116, 111, 110, 97, 118, 105, 49, 31, 48, 29, 6, 3, 85, 4, 3, 12, 22, 99, 111, 109, 46, 97, 117, 116, 111, 110, 97, 118, 105, 46, 97, 112, 105, 115, 101, 114, 118, 101, 114, 48, -127, -97, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 1, 5, 0, 3, -127, -115, 0, 48, -127, -119, 2, -127, -127, 0, -15, -27, -128, -56, 118, -59, 62, -127, 79, 125, -36, 121, 0, 63, -125, -30, 118, 5, -85, -121, 91, 39, 90, 123, 72, -126, -83, -41, -45, -77, -42, -120, -81, 23, -2, -121, -29, 123, -7, 22, -114, -20, -25, 74, 67, -43, 65, 124, -7, 11, -72, 38, -123, 16, -58, 80, 32, 58, -33, 14, 11, 36, 60, 13, -121, 100, 105, -32, 123, -31, 114, -101, -41, 12, 100, 33, -120, 63, 126, -123, 48, 55, 80, -116, 28, -10, 125, 59, -41, -95, -126, 118, -70, 43, -127, 9, 93, -100, 81, -19, -114, -41, 85, -103, -37, -116, 118, 72, 86, 125, -43, -92, -11, 63, 69, -38, -10, -65, 126, -53, -115, 60, 62, -86, -80, 1, 39, 19, 2, 3, 1, 0, 1, -93, 80, 48, 78, 48, 29, 6, 3, 85, 29, 14, 4, 22, 4, 20, -29, 63, 48, -79, -113, -13, 26, 85, 22, -27, 93, -5, 122, -103, -109, 14, -18, 6, -13, -109, 48, 31, 6, 3, 85, 29, 35, 4, 24, 48, 22, -128, 20, -29, 63, 48, -79, -113, -13, 26, 85, 22, -27, 93, -5, 122, -103, -109, 14, -18, 6, -13, -109, 48, 12, 6, 3, 85, 29, 19, 4, 5, 48, 3, 1, 1, -1, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 5, 5, 0, 3, -127, -127, 0, -32, -74, 55, -125, -58, -128, 15, -62, 100, -60, 3, -86, 81, 112, -61, -56, -69, -126, 8, 99, -100, -38, -108, -56, -122, 125, 19, -64, -61, 90, 85, -47, -8, -123, -103, 105, 77, -32, -65, -62, -28, 67, -28, -78, 116, -49, 120, -2, 33, 13, 47, 46, -5, -112, 3, -101, -125, -115, 92, -124, 58, 80, 107, -67, 82, 6, -63, 39, -90, -1, 85, -58, 82, -115, 119, 13, -4, -32, 0, -98, 100, -41, 94, -75, 75, -103, 126, -80, 85, 40, -27, 60, 105, 28, -27, -21, -15, -98, 103, -88, -109, 35, -119, -27, -26, -122, 113, 63, 35, -33, 70, 23, 33, -23, 66, 108, 56, 112, 46, -85, -123, -123, 33, 118, 27, 96, -7, -103};
        r2 = new java.io.ByteArrayInputStream;	 Catch:{ Throwable -> 0x0043, all -> 0x0050 }
        r2.<init>(r1);	 Catch:{ Throwable -> 0x0043, all -> 0x0050 }
        r1 = "X.509";
        r1 = java.security.cert.CertificateFactory.getInstance(r1);	 Catch:{ Throwable -> 0x0062 }
        r3 = "RSA";
        r3 = java.security.KeyFactory.getInstance(r3);	 Catch:{ Throwable -> 0x0062 }
        r1 = r1.generateCertificate(r2);	 Catch:{ Throwable -> 0x0062 }
        if (r1 == 0) goto L_0x0021;
    L_0x001f:
        if (r3 != 0) goto L_0x0027;
    L_0x0021:
        if (r2 == 0) goto L_0x0026;
    L_0x0023:
        r2.close();	 Catch:{ Throwable -> 0x005e }
    L_0x0026:
        return r0;
    L_0x0027:
        r4 = new java.security.spec.X509EncodedKeySpec;	 Catch:{ Throwable -> 0x0062 }
        r1 = r1.getPublicKey();	 Catch:{ Throwable -> 0x0062 }
        r1 = r1.getEncoded();	 Catch:{ Throwable -> 0x0062 }
        r4.<init>(r1);	 Catch:{ Throwable -> 0x0062 }
        r0 = r3.generatePublic(r4);	 Catch:{ Throwable -> 0x0062 }
        if (r2 == 0) goto L_0x0026;
    L_0x003a:
        r2.close();	 Catch:{ Throwable -> 0x003e }
        goto L_0x0026;
    L_0x003e:
        r1 = move-exception;
    L_0x003f:
        r1.printStackTrace();
        goto L_0x0026;
    L_0x0043:
        r1 = move-exception;
        r2 = r0;
    L_0x0045:
        r1.printStackTrace();	 Catch:{ all -> 0x0060 }
        if (r2 == 0) goto L_0x0026;
    L_0x004a:
        r2.close();	 Catch:{ Throwable -> 0x004e }
        goto L_0x0026;
    L_0x004e:
        r1 = move-exception;
        goto L_0x003f;
    L_0x0050:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x0053:
        if (r2 == 0) goto L_0x0058;
    L_0x0055:
        r2.close();	 Catch:{ Throwable -> 0x0059 }
    L_0x0058:
        throw r0;
    L_0x0059:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0058;
    L_0x005e:
        r1 = move-exception;
        goto L_0x003f;
    L_0x0060:
        r0 = move-exception;
        goto L_0x0053;
    L_0x0062:
        r1 = move-exception;
        goto L_0x0045;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore2d.C0978cw.m4045a(android.content.Context):java.security.PublicKey");
    }

    /* renamed from: d */
    static String m4056d(byte[] bArr) {
        try {
            return C0978cw.m4058f(bArr);
        } catch (Throwable th) {
            C0982da.m4076a(th, "Utils", "HexString");
            return null;
        }
    }

    /* renamed from: e */
    static String m4057e(byte[] bArr) {
        try {
            return C0978cw.m4058f(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: f */
    public static String m4058f(byte[] bArr) {
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

    /* JADX WARNING: Removed duplicated region for block: B:23:0x002e A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0033 A:{SYNTHETIC} */
    /* renamed from: g */
    private static byte[] m4059g(byte[] r4) throws java.io.IOException, java.lang.Throwable {
        /*
        r0 = 0;
        if (r4 != 0) goto L_0x0004;
    L_0x0003:
        return r0;
    L_0x0004:
        r2 = new java.io.ByteArrayOutputStream;	 Catch:{ Throwable -> 0x0025, all -> 0x003d }
        r2.<init>();	 Catch:{ Throwable -> 0x0025, all -> 0x003d }
        r1 = new java.util.zip.GZIPOutputStream;	 Catch:{ Throwable -> 0x0048, all -> 0x0043 }
        r1.<init>(r2);	 Catch:{ Throwable -> 0x0048, all -> 0x0043 }
        r1.write(r4);	 Catch:{ Throwable -> 0x004d }
        r1.finish();	 Catch:{ Throwable -> 0x004d }
        r0 = r2.toByteArray();	 Catch:{ Throwable -> 0x004d }
        if (r1 == 0) goto L_0x001d;
    L_0x001a:
        r1.close();	 Catch:{ Throwable -> 0x003b }
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
        r1.close();	 Catch:{ Throwable -> 0x0037 }
    L_0x0031:
        if (r2 == 0) goto L_0x0036;
    L_0x0033:
        r2.close();	 Catch:{ Throwable -> 0x0039 }
    L_0x0036:
        throw r0;
    L_0x0037:
        r0 = move-exception;
        throw r0;
    L_0x0039:
        r0 = move-exception;
        throw r0;
    L_0x003b:
        r0 = move-exception;
        throw r0;
    L_0x003d:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r0 = r1;
        r1 = r3;
        goto L_0x002c;
    L_0x0043:
        r1 = move-exception;
        r3 = r1;
        r1 = r0;
        r0 = r3;
        goto L_0x002c;
    L_0x0048:
        r1 = move-exception;
        r3 = r1;
        r1 = r0;
        r0 = r3;
        goto L_0x002a;
    L_0x004d:
        r0 = move-exception;
        goto L_0x002a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore2d.C0978cw.m4059g(byte[]):byte[]");
    }

    /* renamed from: a */
    public static String m4041a(long j) {
        String str = null;
        try {
            return new SimpleDateFormat("yyyyMMdd HH:mm:ss:SSS", Locale.CHINA).format(new Date(j));
        } catch (Throwable th) {
            th.printStackTrace();
            return str;
        }
    }
}
