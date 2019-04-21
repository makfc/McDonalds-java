package com.tencent.wxop.stat.common;

import android.util.Base64;

/* renamed from: com.tencent.wxop.stat.common.f */
public class C4428f {
    /* renamed from: a */
    static byte[] m8084a() {
        return Base64.decode("MDNhOTc2NTExZTJjYmUzYTdmMjY4MDhmYjdhZjNjMDU=", 0);
    }

    /* renamed from: a */
    public static byte[] m8085a(byte[] bArr) {
        return C4428f.m8086a(bArr, C4428f.m8084a());
    }

    /* renamed from: a */
    static byte[] m8086a(byte[] bArr, byte[] bArr2) {
        int i = 0;
        int[] iArr = new int[256];
        int[] iArr2 = new int[256];
        int length = bArr2.length;
        if (length <= 0 || length > 256) {
            throw new IllegalArgumentException("key must be between 1 and 256 bytes");
        }
        int i2;
        int i3;
        for (i2 = 0; i2 < 256; i2++) {
            iArr[i2] = i2;
            iArr2[i2] = bArr2[i2 % length];
        }
        i2 = 0;
        for (length = 0; length < 256; length++) {
            i2 = ((i2 + iArr[length]) + iArr2[length]) & 255;
            i3 = iArr[length];
            iArr[length] = iArr[i2];
            iArr[i2] = i3;
        }
        byte[] bArr3 = new byte[bArr.length];
        i2 = 0;
        length = 0;
        while (i < bArr.length) {
            i2 = (i2 + 1) & 255;
            length = (length + iArr[i2]) & 255;
            i3 = iArr[i2];
            iArr[i2] = iArr[length];
            iArr[length] = i3;
            bArr3[i] = (byte) (iArr[(iArr[i2] + iArr[length]) & 255] ^ bArr[i]);
            i++;
        }
        return bArr3;
    }

    /* renamed from: b */
    public static byte[] m8087b(byte[] bArr) {
        return C4428f.m8088b(bArr, C4428f.m8084a());
    }

    /* renamed from: b */
    static byte[] m8088b(byte[] bArr, byte[] bArr2) {
        return C4428f.m8086a(bArr, bArr2);
    }
}
