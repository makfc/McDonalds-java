package com.amap.api.mapcore.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: Encrypt */
/* renamed from: com.amap.api.mapcore.util.dr */
public class C0821dr {
    /* renamed from: a */
    private static final char[] f1808a = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    /* renamed from: b */
    private static final byte[] f1809b = new byte[128];

    static {
        int i;
        for (i = 0; i < 128; i++) {
            f1809b[i] = (byte) -1;
        }
        for (i = 65; i <= 90; i++) {
            f1809b[i] = (byte) (i - 65);
        }
        for (i = 97; i <= 122; i++) {
            f1809b[i] = (byte) ((i - 97) + 26);
        }
        for (i = 48; i <= 57; i++) {
            f1809b[i] = (byte) ((i - 48) + 52);
        }
        f1809b[43] = (byte) 62;
        f1809b[47] = (byte) 63;
    }

    /* renamed from: a */
    public static String m2454a(byte[] bArr) {
        try {
            return C0821dr.m2460c(bArr);
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "Encrypt", "encodeBase64");
            return null;
        }
    }

    /* renamed from: a */
    public static String m2453a(String str) {
        return Utils.m2509a(C0821dr.m2458b(str));
    }

    /* renamed from: b */
    public static String m2457b(byte[] bArr) {
        try {
            return C0821dr.m2460c(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    static byte[] m2456a(byte[] bArr, byte[] bArr2) {
        try {
            return C0821dr.m2459b(bArr, bArr2);
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "Encrypt", "aesEncrypt");
            return null;
        }
    }

    /* renamed from: b */
    private static byte[] m2459b(byte[] bArr, byte[] bArr2) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        IvParameterSpec ivParameterSpec = new IvParameterSpec(Utils.m2514a());
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        try {
            instance.init(1, secretKeySpec, ivParameterSpec);
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return instance.doFinal(bArr2);
    }

    /* renamed from: a */
    static byte[] m2455a(byte[] bArr, Key key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        instance.init(1, key);
        return instance.doFinal(bArr);
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x0023 A:{SYNTHETIC, EDGE_INSN: B:40:0x0023->B:9:0x0023 ?: BREAK  } */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0077 A:{LOOP_END, LOOP:0: B:4:0x0013->B:31:0x0077} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0023 A:{SYNTHETIC, EDGE_INSN: B:37:0x0023->B:9:0x0023 ?: BREAK  } */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0023 A:{SYNTHETIC, EDGE_INSN: B:41:0x0023->B:9:0x0023 ?: BREAK  } */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0029 A:{LOOP_START, LOOP:2: B:11:0x0029->B:10:0x0028, PHI: r1 } */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0023 A:{SYNTHETIC, EDGE_INSN: B:38:0x0023->B:9:0x0023 ?: BREAK  } */
    /* renamed from: b */
    public static byte[] m2458b(java.lang.String r9) {
        /*
        r8 = 61;
        r0 = 0;
        r7 = -1;
        if (r9 != 0) goto L_0x0009;
    L_0x0006:
        r0 = new byte[r0];
    L_0x0008:
        return r0;
    L_0x0009:
        r2 = com.amap.api.mapcore.util.Utils.m2515a(r9);
        r3 = r2.length;
        r4 = new java.io.ByteArrayOutputStream;
        r4.<init>(r3);
    L_0x0013:
        if (r0 >= r3) goto L_0x0023;
    L_0x0015:
        r5 = f1809b;
        r1 = r0 + 1;
        r0 = r2[r0];
        r5 = r5[r0];
        if (r1 >= r3) goto L_0x0021;
    L_0x001f:
        if (r5 == r7) goto L_0x0084;
    L_0x0021:
        if (r5 != r7) goto L_0x0029;
    L_0x0023:
        r0 = r4.toByteArray();
        goto L_0x0008;
    L_0x0028:
        r1 = r0;
    L_0x0029:
        r6 = f1809b;
        r0 = r1 + 1;
        r1 = r2[r1];
        r6 = r6[r1];
        if (r0 >= r3) goto L_0x0035;
    L_0x0033:
        if (r6 == r7) goto L_0x0028;
    L_0x0035:
        if (r6 == r7) goto L_0x0023;
    L_0x0037:
        r1 = r5 << 2;
        r5 = r6 & 48;
        r5 = r5 >>> 4;
        r1 = r1 | r5;
        r4.write(r1);
    L_0x0041:
        r1 = r0 + 1;
        r0 = r2[r0];
        if (r0 != r8) goto L_0x004c;
    L_0x0047:
        r0 = r4.toByteArray();
        goto L_0x0008;
    L_0x004c:
        r5 = f1809b;
        r5 = r5[r0];
        if (r1 >= r3) goto L_0x0054;
    L_0x0052:
        if (r5 == r7) goto L_0x0082;
    L_0x0054:
        if (r5 == r7) goto L_0x0023;
    L_0x0056:
        r0 = r6 & 15;
        r0 = r0 << 4;
        r6 = r5 & 60;
        r6 = r6 >>> 2;
        r0 = r0 | r6;
        r4.write(r0);
    L_0x0062:
        r0 = r1 + 1;
        r1 = r2[r1];
        if (r1 != r8) goto L_0x006d;
    L_0x0068:
        r0 = r4.toByteArray();
        goto L_0x0008;
    L_0x006d:
        r6 = f1809b;
        r1 = r6[r1];
        if (r0 >= r3) goto L_0x0075;
    L_0x0073:
        if (r1 == r7) goto L_0x0080;
    L_0x0075:
        if (r1 == r7) goto L_0x0023;
    L_0x0077:
        r5 = r5 & 3;
        r5 = r5 << 6;
        r1 = r1 | r5;
        r4.write(r1);
        goto L_0x0013;
    L_0x0080:
        r1 = r0;
        goto L_0x0062;
    L_0x0082:
        r0 = r1;
        goto L_0x0041;
    L_0x0084:
        r0 = r1;
        goto L_0x0015;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.C0821dr.m2458b(java.lang.String):byte[]");
    }

    /* renamed from: c */
    private static String m2460c(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = bArr.length;
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            int i3 = bArr[i] & 255;
            if (i2 == length) {
                stringBuffer.append(f1808a[i3 >>> 2]);
                stringBuffer.append(f1808a[(i3 & 3) << 4]);
                stringBuffer.append("==");
                break;
            }
            int i4 = i2 + 1;
            i2 = bArr[i2] & 255;
            if (i4 == length) {
                stringBuffer.append(f1808a[i3 >>> 2]);
                stringBuffer.append(f1808a[((i3 & 3) << 4) | ((i2 & 240) >>> 4)]);
                stringBuffer.append(f1808a[(i2 & 15) << 2]);
                stringBuffer.append("=");
                break;
            }
            i = i4 + 1;
            i4 = bArr[i4] & 255;
            stringBuffer.append(f1808a[i3 >>> 2]);
            stringBuffer.append(f1808a[((i3 & 3) << 4) | ((i2 & 240) >>> 4)]);
            stringBuffer.append(f1808a[((i2 & 15) << 2) | ((i4 & 192) >>> 6)]);
            stringBuffer.append(f1808a[i4 & 63]);
        }
        return stringBuffer.toString();
    }
}
