package com.amap.api.location.core;

/* renamed from: com.amap.api.location.core.b */
public class Base64Util {
    /* renamed from: a */
    private static final char[] f908a = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    /* renamed from: b */
    private static byte[] f909b = new byte[]{(byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 62, (byte) -1, (byte) -1, (byte) -1, (byte) 63, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 58, (byte) 59, (byte) 60, (byte) 61, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, (byte) 11, (byte) 12, (byte) 13, (byte) 14, (byte) 15, (byte) 16, (byte) 17, (byte) 18, (byte) 19, (byte) 20, (byte) 21, (byte) 22, (byte) 23, (byte) 24, (byte) 25, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 26, (byte) 27, (byte) 28, (byte) 29, (byte) 30, (byte) 31, (byte) 32, (byte) 33, (byte) 34, (byte) 35, (byte) 36, (byte) 37, (byte) 38, (byte) 39, (byte) 40, (byte) 41, (byte) 42, (byte) 43, (byte) 44, (byte) 45, (byte) 46, (byte) 47, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1};

    private Base64Util() {
    }

    /* renamed from: a */
    public static String m1419a(byte[] bArr) {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            int length = bArr.length;
            int i = 0;
            while (i < length) {
                int i2 = i + 1;
                int i3 = bArr[i] & 255;
                if (i2 == length) {
                    stringBuffer.append(f908a[i3 >>> 2]);
                    stringBuffer.append(f908a[(i3 & 3) << 4]);
                    stringBuffer.append("==");
                    break;
                }
                int i4 = i2 + 1;
                i2 = bArr[i2] & 255;
                if (i4 == length) {
                    stringBuffer.append(f908a[i3 >>> 2]);
                    stringBuffer.append(f908a[((i3 & 3) << 4) | ((i2 & 240) >>> 4)]);
                    stringBuffer.append(f908a[(i2 & 15) << 2]);
                    stringBuffer.append("=");
                    break;
                }
                i = i4 + 1;
                i4 = bArr[i4] & 255;
                stringBuffer.append(f908a[i3 >>> 2]);
                stringBuffer.append(f908a[((i3 & 3) << 4) | ((i2 & 240) >>> 4)]);
                stringBuffer.append(f908a[((i2 & 15) << 2) | ((i4 & 192) >>> 6)]);
                stringBuffer.append(f908a[i4 & 63]);
            }
            return stringBuffer.toString();
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x0021 A:{SYNTHETIC, EDGE_INSN: B:40:0x0021->B:8:0x0021 ?: BREAK  } */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0076 A:{Catch:{ Throwable -> 0x0080 }} */
    /* renamed from: a */
    public static byte[] m1420a(java.lang.String r9) {
        /*
        r8 = 61;
        r7 = -1;
        r0 = "UTF-8";
        r2 = r9.getBytes(r0);	 Catch:{ Throwable -> 0x0080 }
        r3 = r2.length;	 Catch:{ Throwable -> 0x0080 }
        r4 = new java.io.ByteArrayOutputStream;	 Catch:{ Throwable -> 0x0080 }
        r4.<init>(r3);	 Catch:{ Throwable -> 0x0080 }
        r0 = 0;
    L_0x0010:
        if (r0 >= r3) goto L_0x0021;
    L_0x0012:
        r1 = r0;
        r5 = f909b;	 Catch:{ Throwable -> 0x0080 }
        r0 = r1 + 1;
        r1 = r2[r1];	 Catch:{ Throwable -> 0x0080 }
        r5 = r5[r1];	 Catch:{ Throwable -> 0x0080 }
        if (r0 >= r3) goto L_0x001f;
    L_0x001d:
        if (r5 == r7) goto L_0x0012;
    L_0x001f:
        if (r5 != r7) goto L_0x0026;
    L_0x0021:
        r0 = r4.toByteArray();	 Catch:{ Throwable -> 0x0080 }
    L_0x0025:
        return r0;
    L_0x0026:
        r1 = r0;
        r6 = f909b;	 Catch:{ Throwable -> 0x0080 }
        r0 = r1 + 1;
        r1 = r2[r1];	 Catch:{ Throwable -> 0x0080 }
        r6 = r6[r1];	 Catch:{ Throwable -> 0x0080 }
        if (r0 >= r3) goto L_0x0033;
    L_0x0031:
        if (r6 == r7) goto L_0x0026;
    L_0x0033:
        if (r6 == r7) goto L_0x0021;
    L_0x0035:
        r1 = r5 << 2;
        r5 = r6 & 48;
        r5 = r5 >>> 4;
        r1 = r1 | r5;
        r4.write(r1);	 Catch:{ Throwable -> 0x0080 }
    L_0x003f:
        r1 = r0;
        r0 = r1 + 1;
        r1 = r2[r1];	 Catch:{ Throwable -> 0x0080 }
        if (r1 != r8) goto L_0x004b;
    L_0x0046:
        r0 = r4.toByteArray();	 Catch:{ Throwable -> 0x0080 }
        goto L_0x0025;
    L_0x004b:
        r5 = f909b;	 Catch:{ Throwable -> 0x0080 }
        r5 = r5[r1];	 Catch:{ Throwable -> 0x0080 }
        if (r0 >= r3) goto L_0x0053;
    L_0x0051:
        if (r5 == r7) goto L_0x003f;
    L_0x0053:
        if (r5 == r7) goto L_0x0021;
    L_0x0055:
        r1 = r6 & 15;
        r1 = r1 << 4;
        r6 = r5 & 60;
        r6 = r6 >>> 2;
        r1 = r1 | r6;
        r4.write(r1);	 Catch:{ Throwable -> 0x0080 }
    L_0x0061:
        r1 = r0 + 1;
        r0 = r2[r0];	 Catch:{ Throwable -> 0x0080 }
        if (r0 != r8) goto L_0x006c;
    L_0x0067:
        r0 = r4.toByteArray();	 Catch:{ Throwable -> 0x0080 }
        goto L_0x0025;
    L_0x006c:
        r6 = f909b;	 Catch:{ Throwable -> 0x0080 }
        r0 = r6[r0];	 Catch:{ Throwable -> 0x0080 }
        if (r1 >= r3) goto L_0x0074;
    L_0x0072:
        if (r0 == r7) goto L_0x0086;
    L_0x0074:
        if (r0 == r7) goto L_0x0021;
    L_0x0076:
        r5 = r5 & 3;
        r5 = r5 << 6;
        r0 = r0 | r5;
        r4.write(r0);	 Catch:{ Throwable -> 0x0080 }
        r0 = r1;
        goto L_0x0010;
    L_0x0080:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = 0;
        goto L_0x0025;
    L_0x0086:
        r0 = r1;
        goto L_0x0061;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.location.core.Base64Util.m1420a(java.lang.String):byte[]");
    }
}
