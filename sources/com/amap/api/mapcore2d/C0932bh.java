package com.amap.api.mapcore2d;

import com.amap.api.maps2d.AMapException;

/* compiled from: ProtocalHandler */
/* renamed from: com.amap.api.mapcore2d.bh */
abstract class C0932bh<T, V> extends C0931eg {
    /* renamed from: a */
    protected T f2564a;
    /* renamed from: b */
    private int f2565b;
    /* renamed from: f */
    private int f2566f;

    /* renamed from: a */
    public abstract V mo10077a(byte[] bArr) throws AMapException;

    public C0932bh(T t) {
        this();
        this.f2564a = t;
    }

    public C0932bh() {
        this.f2565b = 1;
        this.f2566f = 0;
        this.f2565b = 1;
        this.f2566f = 2;
    }

    /* renamed from: a */
    public V mo10076a() throws AMapException {
        if (this.f2564a != null) {
            return mo10123d();
        }
        return null;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public byte[] mo10078b() throws AMapException {
        int i = 0;
        String str = "getData";
        while (i < this.f2565b) {
            try {
                return C1029ef.m4280a(false).mo10286d(this);
            } catch (C0956cl e) {
                i++;
                if (i < this.f2565b) {
                    try {
                        Thread.sleep((long) (this.f2566f * 1000));
                        C0955ck.m3888a(e, "ProtocalHandler", str);
                    } catch (InterruptedException e2) {
                        throw new AMapException(e.getMessage());
                    }
                }
                throw new AMapException(e.mo10154a());
            }
        }
        return null;
    }

    /* renamed from: d */
    private V mo10123d() throws AMapException {
        String str = "GetDataMayThrow";
        V v = null;
        try {
            return mo10122b(mo10078b());
        } catch (AMapException e) {
            mo10079c();
            throw new AMapException(e.getErrorMessage());
        } catch (Throwable th) {
            C0955ck.m3888a(th, "ProtocalHandler", str);
            return v;
        }
    }

    /* renamed from: b */
    private V mo10122b(byte[] bArr) throws AMapException {
        return mo10077a(bArr);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: c */
    public V mo10079c() {
        return null;
    }
}
