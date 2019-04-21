package com.tencent.wxop.stat.common;

/* renamed from: com.tencent.wxop.stat.common.g */
public class C4429g {
    /* renamed from: a */
    static final /* synthetic */ boolean f7121a = (!C4429g.class.desiredAssertionStatus());

    private C4429g() {
    }

    /* renamed from: a */
    public static byte[] m8089a(byte[] bArr, int i) {
        return C4429g.m8090a(bArr, 0, bArr.length, i);
    }

    /* renamed from: a */
    public static byte[] m8090a(byte[] bArr, int i, int i2, int i3) {
        C4431i c4431i = new C4431i(i3, new byte[((i2 * 3) / 4)]);
        if (!c4431i.mo33967a(bArr, i, i2, true)) {
            throw new IllegalArgumentException("bad base-64");
        } else if (c4431i.f7123b == c4431i.f7122a.length) {
            return c4431i.f7122a;
        } else {
            byte[] bArr2 = new byte[c4431i.f7123b];
            System.arraycopy(c4431i.f7122a, 0, bArr2, 0, c4431i.f7123b);
            return bArr2;
        }
    }

    /* renamed from: b */
    public static byte[] m8091b(byte[] bArr, int i) {
        return C4429g.m8092b(bArr, 0, bArr.length, i);
    }

    /* renamed from: b */
    public static byte[] m8092b(byte[] bArr, int i, int i2, int i3) {
        C4432j c4432j = new C4432j(i3, null);
        int i4 = (i2 / 3) * 4;
        if (!c4432j.f7133d) {
            switch (i2 % 3) {
                case 1:
                    i4 += 2;
                    break;
                case 2:
                    i4 += 3;
                    break;
            }
        } else if (i2 % 3 > 0) {
            i4 += 4;
        }
        if (c4432j.f7134e && i2 > 0) {
            i4 += (c4432j.f7135f ? 2 : 1) * (((i2 - 1) / 57) + 1);
        }
        c4432j.f7122a = new byte[i4];
        c4432j.mo33968a(bArr, i, i2, true);
        if (f7121a || c4432j.f7123b == i4) {
            return c4432j.f7122a;
        }
        throw new AssertionError();
    }
}
