package com.autonavi.amap.mapcore;

/* compiled from: VMapDataCache */
/* renamed from: com.autonavi.amap.mapcore.e */
class C1277e {
    /* renamed from: a */
    String f4573a;
    /* renamed from: b */
    int f4574b;
    /* renamed from: c */
    int f4575c;
    /* renamed from: d */
    int f4576d = 0;

    public C1277e(String str, int i) {
        if (str != null) {
            try {
                if (str.length() != 0) {
                    this.f4574b = (int) (System.currentTimeMillis() / 1000);
                    this.f4575c = i;
                    this.f4573a = str;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                this.f4573a = null;
            }
        }
    }
}
