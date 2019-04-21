package com.alipay.sdk.encrypt;

import com.newrelic.agent.android.util.SafeJsonPrimitive;

/* renamed from: com.alipay.sdk.encrypt.a */
public final class C0619a {
    /* renamed from: i */
    private static final byte[] f587i = new byte[128];
    /* renamed from: j */
    private static final char[] f588j = new char[64];

    static {
        int i;
        int i2 = 0;
        for (i = 0; i < 128; i++) {
            f587i[i] = (byte) -1;
        }
        for (i = 90; i >= 65; i--) {
            f587i[i] = (byte) (i - 65);
        }
        for (i = 122; i >= 97; i--) {
            f587i[i] = (byte) ((i - 97) + 26);
        }
        for (i = 57; i >= 48; i--) {
            f587i[i] = (byte) ((i - 48) + 52);
        }
        f587i[43] = (byte) 62;
        f587i[47] = (byte) 63;
        for (i = 0; i <= 25; i++) {
            f588j[i] = (char) (i + 65);
        }
        int i3 = 26;
        i = 0;
        while (i3 <= 51) {
            f588j[i3] = (char) (i + 97);
            i3++;
            i++;
        }
        i = 52;
        while (i <= 61) {
            f588j[i] = (char) (i2 + 48);
            i++;
            i2++;
        }
        f588j[62] = '+';
        f588j[63] = '/';
    }

    /* renamed from: a */
    private static boolean m895a(char c) {
        return c == SafeJsonPrimitive.NULL_CHAR || c == 13 || c == 10 || c == 9;
    }

    /* renamed from: b */
    private static boolean m897b(char c) {
        return c == '=';
    }

    /* renamed from: c */
    private static boolean m898c(char c) {
        return c < 128 && f587i[c] != (byte) -1;
    }

    /* renamed from: a */
    public static String m894a(byte[] bArr) {
        int i = 0;
        if (bArr == null) {
            return null;
        }
        int length = bArr.length * 8;
        if (length == 0) {
            return "";
        }
        byte b;
        byte b2;
        byte b3;
        int i2 = length % 24;
        int i3 = length / 24;
        char[] cArr = new char[((i2 != 0 ? i3 + 1 : i3) * 4)];
        int i4 = 0;
        int i5 = 0;
        while (i4 < i3) {
            length = i + 1;
            b = bArr[i];
            int i6 = length + 1;
            byte b4 = bArr[length];
            int i7 = i6 + 1;
            byte b5 = bArr[i6];
            byte b6 = (byte) (b4 & 15);
            byte b7 = (byte) (b & 3);
            if ((b & -128) == 0) {
                i6 = (byte) (b >> 2);
            } else {
                b2 = (byte) ((b >> 2) ^ 192);
            }
            if ((b4 & -128) == 0) {
                i = (byte) (b4 >> 4);
            } else {
                b = (byte) ((b4 >> 4) ^ 240);
            }
            if ((b5 & -128) == 0) {
                length = b5 >> 6;
            } else {
                length = (b5 >> 6) ^ 252;
            }
            b3 = (byte) length;
            int i8 = i5 + 1;
            cArr[i5] = f588j[i6];
            i6 = i8 + 1;
            cArr[i8] = f588j[i | (b7 << 4)];
            i5 = i6 + 1;
            cArr[i6] = f588j[b3 | (b6 << 2)];
            i = i5 + 1;
            cArr[i5] = f588j[b5 & 63];
            i4++;
            i5 = i;
            i = i7;
        }
        byte b8;
        if (i2 == 8) {
            b3 = bArr[i];
            b8 = (byte) (b3 & 3);
            i = i5 + 1;
            cArr[i5] = f588j[(b3 & -128) == 0 ? (byte) (b3 >> 2) : (byte) ((b3 >> 2) ^ 192)];
            length = i + 1;
            cArr[i] = f588j[b8 << 4];
            i3 = length + 1;
            cArr[length] = '=';
            length = i3 + 1;
            cArr[i3] = '=';
        } else if (i2 == 16) {
            b3 = bArr[i];
            b = bArr[i + 1];
            b2 = (byte) (b & 15);
            byte b9 = (byte) (b3 & 3);
            if ((b3 & -128) == 0) {
                i3 = (byte) (b3 >> 2);
            } else {
                b8 = (byte) ((b3 >> 2) ^ 192);
            }
            length = (b & -128) == 0 ? (byte) (b >> 4) : (byte) ((b >> 4) ^ 240);
            i = i5 + 1;
            cArr[i5] = f588j[i3];
            i3 = i + 1;
            cArr[i] = f588j[length | (b9 << 4)];
            length = i3 + 1;
            cArr[i3] = f588j[b2 << 2];
            i3 = length + 1;
            cArr[length] = '=';
        }
        return new String(cArr);
    }

    /* renamed from: a */
    public static byte[] m896a(String str) {
        if (str == null) {
            return null;
        }
        char[] toCharArray = str.toCharArray();
        int a = C0619a.m893a(toCharArray);
        if (a % 4 != 0) {
            return null;
        }
        int i = a / 4;
        if (i == 0) {
            return new byte[0];
        }
        int i2;
        char c;
        int i3;
        int i4;
        byte[] bArr = new byte[(i * 3)];
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i7 < i - 1) {
            i2 = i5 + 1;
            char c2 = toCharArray[i5];
            if (!C0619a.m898c(c2)) {
                return null;
            }
            i5 = i2 + 1;
            c = toCharArray[i2];
            if (!C0619a.m898c(c)) {
                return null;
            }
            i3 = i5 + 1;
            char c3 = toCharArray[i5];
            if (!C0619a.m898c(c3)) {
                return null;
            }
            i5 = i3 + 1;
            char c4 = toCharArray[i3];
            if (!C0619a.m898c(c4)) {
                return null;
            }
            byte b = f587i[c2];
            byte b2 = f587i[c];
            byte b3 = f587i[c3];
            byte b4 = f587i[c4];
            int i8 = i6 + 1;
            bArr[i6] = (byte) ((b << 2) | (b2 >> 4));
            i4 = i8 + 1;
            bArr[i8] = (byte) (((b2 & 15) << 4) | ((b3 >> 2) & 15));
            i6 = i4 + 1;
            bArr[i4] = (byte) ((b3 << 6) | b4);
            i7++;
        }
        i = i5 + 1;
        char c5 = toCharArray[i5];
        if (!C0619a.m898c(c5)) {
            return null;
        }
        i2 = i + 1;
        char c6 = toCharArray[i];
        if (!C0619a.m898c(c6)) {
            return null;
        }
        byte b5 = f587i[c5];
        byte b6 = f587i[c6];
        i4 = i2 + 1;
        c = toCharArray[i2];
        i3 = i4 + 1;
        char c7 = toCharArray[i4];
        byte[] bArr2;
        if (C0619a.m898c(c) && C0619a.m898c(c7)) {
            byte b7 = f587i[c];
            byte b8 = f587i[c7];
            i7 = i6 + 1;
            bArr[i6] = (byte) ((b5 << 2) | (b6 >> 4));
            i5 = i7 + 1;
            bArr[i7] = (byte) (((b6 & 15) << 4) | ((b7 >> 2) & 15));
            i6 = i5 + 1;
            bArr[i5] = (byte) ((b7 << 6) | b8);
            return bArr;
        } else if (C0619a.m897b(c) && C0619a.m897b(c7)) {
            if ((b6 & 15) != 0) {
                return null;
            }
            bArr2 = new byte[((i7 * 3) + 1)];
            System.arraycopy(bArr, 0, bArr2, 0, i7 * 3);
            bArr2[i6] = (byte) ((b5 << 2) | (b6 >> 4));
            return bArr2;
        } else if (C0619a.m897b(c) || !C0619a.m897b(c7)) {
            return null;
        } else {
            byte b9 = f587i[c];
            if ((b9 & 3) != 0) {
                return null;
            }
            bArr2 = new byte[((i7 * 3) + 2)];
            System.arraycopy(bArr, 0, bArr2, 0, i7 * 3);
            a = i6 + 1;
            bArr2[i6] = (byte) ((b5 << 2) | (b6 >> 4));
            bArr2[a] = (byte) (((b6 & 15) << 4) | ((b9 >> 2) & 15));
            return bArr2;
        }
    }

    /* renamed from: a */
    private static int m893a(char[] cArr) {
        int i = 0;
        if (cArr != null) {
            int length = cArr.length;
            int i2 = 0;
            while (i2 < length) {
                int i3;
                if (C0619a.m895a(cArr[i2])) {
                    i3 = i;
                } else {
                    i3 = i + 1;
                    cArr[i] = cArr[i2];
                }
                i2++;
                i = i3;
            }
        }
        return i;
    }
}
