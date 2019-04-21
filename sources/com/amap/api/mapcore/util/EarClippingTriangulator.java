package com.amap.api.mapcore.util;

/* renamed from: com.amap.api.mapcore.util.cz */
public class EarClippingTriangulator {
    /* renamed from: a */
    private final ShortArray f1698a = new ShortArray();
    /* renamed from: b */
    private short[] f1699b;
    /* renamed from: c */
    private float[] f1700c;
    /* renamed from: d */
    private int f1701d;
    /* renamed from: e */
    private final IntArray f1702e = new IntArray();
    /* renamed from: f */
    private final ShortArray f1703f = new ShortArray();

    /* renamed from: a */
    public ShortArray mo9225a(float[] fArr) {
        return mo9226a(fArr, 0, fArr.length);
    }

    /* renamed from: a */
    public ShortArray mo9226a(float[] fArr, int i, int i2) {
        int i3;
        this.f1700c = fArr;
        int i4 = i2 / 2;
        this.f1701d = i4;
        int i5 = i / 2;
        ShortArray shortArray = this.f1698a;
        shortArray.mo9276a();
        shortArray.mo9279c(i4);
        shortArray.f1746b = i4;
        short[] sArr = shortArray.f1745a;
        this.f1699b = sArr;
        if (EarClippingTriangulator.m2239b(fArr, i, i2)) {
            for (i3 = 0; i3 < i4; i3 = (short) (i3 + 1)) {
                sArr[i3] = (short) (i5 + i3);
            }
        } else {
            int i6 = i4 - 1;
            for (i3 = 0; i3 < i4; i3++) {
                sArr[i3] = (short) ((i5 + i6) - i3);
            }
        }
        IntArray intArray = this.f1702e;
        intArray.mo9268a();
        intArray.mo9271c(i4);
        for (i3 = 0; i3 < i4; i3++) {
            intArray.mo9269a(m2235a(i3));
        }
        shortArray = this.f1703f;
        shortArray.mo9276a();
        shortArray.mo9279c(Math.max(0, i4 - 2) * 3);
        m2236a();
        return shortArray;
    }

    /* renamed from: a */
    private void m2236a() {
        int[] iArr = this.f1702e.f1741a;
        while (this.f1701d > 3) {
            int b = m2237b();
            m2240c(b);
            int d = m2241d(b);
            if (b == this.f1701d) {
                b = 0;
            }
            iArr[d] = m2235a(d);
            iArr[b] = m2235a(b);
        }
        if (this.f1701d == 3) {
            ShortArray shortArray = this.f1703f;
            short[] sArr = this.f1699b;
            shortArray.mo9277a(sArr[0]);
            shortArray.mo9277a(sArr[1]);
            shortArray.mo9277a(sArr[2]);
        }
    }

    /* renamed from: a */
    private int m2235a(int i) {
        short[] sArr = this.f1699b;
        int i2 = sArr[m2241d(i)] * 2;
        int i3 = sArr[i] * 2;
        int i4 = sArr[m2242e(i)] * 2;
        float[] fArr = this.f1700c;
        return EarClippingTriangulator.m2234a(fArr[i2], fArr[i2 + 1], fArr[i3], fArr[i3 + 1], fArr[i4], fArr[i4 + 1]);
    }

    /* renamed from: b */
    private int m2237b() {
        int i;
        int i2 = this.f1701d;
        for (i = 0; i < i2; i++) {
            if (m2238b(i)) {
                return i;
            }
        }
        int[] iArr = this.f1702e.f1741a;
        for (i = 0; i < i2; i++) {
            if (iArr[i] != -1) {
                return i;
            }
        }
        return 0;
    }

    /* renamed from: b */
    private boolean m2238b(int i) {
        int[] iArr = this.f1702e.f1741a;
        if (iArr[i] == -1) {
            return false;
        }
        int d = m2241d(i);
        int e = m2242e(i);
        short[] sArr = this.f1699b;
        int i2 = sArr[d] * 2;
        int i3 = sArr[i] * 2;
        int i4 = sArr[e] * 2;
        float[] fArr = this.f1700c;
        float f = fArr[i2];
        float f2 = fArr[i2 + 1];
        float f3 = fArr[i3];
        float f4 = fArr[i3 + 1];
        float f5 = fArr[i4];
        float f6 = fArr[i4 + 1];
        e = m2242e(e);
        while (true) {
            int i5 = e;
            if (i5 == d) {
                return true;
            }
            if (iArr[i5] != 1) {
                i4 = sArr[i5] * 2;
                float f7 = fArr[i4];
                float f8 = fArr[i4 + 1];
                if (EarClippingTriangulator.m2234a(f5, f6, f, f2, f7, f8) >= 0 && EarClippingTriangulator.m2234a(f, f2, f3, f4, f7, f8) >= 0 && EarClippingTriangulator.m2234a(f3, f4, f5, f6, f7, f8) >= 0) {
                    return false;
                }
            }
            e = m2242e(i5);
        }
    }

    /* renamed from: c */
    private void m2240c(int i) {
        short[] sArr = this.f1699b;
        ShortArray shortArray = this.f1703f;
        shortArray.mo9277a(sArr[m2241d(i)]);
        shortArray.mo9277a(sArr[i]);
        shortArray.mo9277a(sArr[m2242e(i)]);
        this.f1698a.mo9278b(i);
        this.f1702e.mo9270b(i);
        this.f1701d--;
    }

    /* renamed from: d */
    private int m2241d(int i) {
        if (i == 0) {
            i = this.f1701d;
        }
        return i - 1;
    }

    /* renamed from: e */
    private int m2242e(int i) {
        return (i + 1) % this.f1701d;
    }

    /* renamed from: b */
    private static boolean m2239b(float[] fArr, int i, int i2) {
        if (i2 <= 2) {
            return false;
        }
        int i3 = (i + i2) - 3;
        float f = 0.0f;
        for (int i4 = i; i4 < i3; i4 += 2) {
            f += (fArr[i4] * fArr[i4 + 3]) - (fArr[i4 + 1] * fArr[i4 + 2]);
        }
        float f2 = fArr[(i + i2) - 2];
        float f3 = fArr[(i + i2) - 1];
        if (((f2 * fArr[i + 1]) + f) - (fArr[i] * f3) < 0.0f) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private static int m2234a(float f, float f2, float f3, float f4, float f5, float f6) {
        return (int) Math.signum((((f6 - f4) * f) + ((f2 - f6) * f3)) + ((f4 - f2) * f5));
    }
}
