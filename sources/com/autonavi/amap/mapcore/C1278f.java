package com.autonavi.amap.mapcore;

/* compiled from: VTMCDataCache */
/* renamed from: com.autonavi.amap.mapcore.f */
class C1278f {
    /* renamed from: a */
    byte[] f4577a;
    /* renamed from: b */
    String f4578b;
    /* renamed from: c */
    int f4579c;
    /* renamed from: d */
    String f4580d;
    /* renamed from: e */
    int f4581e;

    public C1278f(byte[] bArr) {
        try {
            this.f4579c = (int) (System.currentTimeMillis() / 1000);
            byte b = bArr[4];
            this.f4578b = new String(bArr, 5, b);
            int i = b + 5;
            int i2 = i + 1;
            b = bArr[i];
            this.f4580d = new String(bArr, i2, b);
            i = b + i2;
            this.f4581e = Convert.getInt(bArr, i);
            i += 4;
            this.f4577a = bArr;
        } catch (Exception e) {
            this.f4577a = null;
        }
    }

    /* renamed from: a */
    public void mo13430a(int i) {
        if (this.f4577a != null) {
            this.f4579c = (int) (System.currentTimeMillis() / 1000);
            int i2 = this.f4577a[4] + 5;
            Convert.writeInt(this.f4577a, this.f4577a[i2] + (i2 + 1), i);
            this.f4581e = i;
        }
    }
}
