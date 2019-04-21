package com.amap.api.mapcore.util;

@EntityClass(a = "e")
/* renamed from: com.amap.api.mapcore.util.ew */
public class UpdateLogInfo {
    @EntityField(a = "c1", b = 2)
    /* renamed from: a */
    private int f1894a;
    @EntityField(a = "c2", b = 2)
    /* renamed from: b */
    private int f1895b;
    @EntityField(a = "c3", b = 2)
    /* renamed from: c */
    private int f1896c;

    /* renamed from: a */
    public void mo9355a(boolean z) {
        this.f1894a = z ? 1 : 0;
    }

    /* renamed from: a */
    public boolean mo9356a() {
        return this.f1894a == 1;
    }

    /* renamed from: b */
    public void mo9357b(boolean z) {
        this.f1895b = z ? 1 : 0;
    }

    /* renamed from: b */
    public boolean mo9358b() {
        return this.f1895b == 1;
    }

    /* renamed from: c */
    public void mo9359c(boolean z) {
        this.f1896c = z ? 1 : 0;
    }

    /* renamed from: c */
    public boolean mo9360c() {
        return this.f1896c == 1;
    }
}
