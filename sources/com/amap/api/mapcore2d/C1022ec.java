package com.amap.api.mapcore2d;

import java.net.Proxy;

/* compiled from: DownloadManager */
/* renamed from: com.amap.api.mapcore2d.ec */
public class C1022ec {
    /* renamed from: a */
    private C1024ed f2896a;
    /* renamed from: b */
    private C0931eg f2897b;

    /* compiled from: DownloadManager */
    /* renamed from: com.amap.api.mapcore2d.ec$a */
    public interface C0973a {
        /* renamed from: a */
        void mo10164a(Throwable th);

        /* renamed from: a */
        void mo10165a(byte[] bArr, long j);

        /* renamed from: b */
        void mo10166b();

        /* renamed from: c */
        void mo10167c();
    }

    public C1022ec(C0931eg c0931eg) {
        this(c0931eg, 0, -1);
    }

    public C1022ec(C0931eg c0931eg, long j, long j2) {
        Proxy proxy;
        this.f2897b = c0931eg;
        if (c0931eg.f2563e == null) {
            proxy = null;
        } else {
            proxy = c0931eg.f2563e;
        }
        this.f2896a = new C1024ed(this.f2897b.f2561c, this.f2897b.f2562d, proxy);
        this.f2896a.mo10279b(j2);
        this.f2896a.mo10277a(j);
    }

    /* renamed from: a */
    public void mo10272a(C0973a c0973a) {
        this.f2896a.mo10278a(this.f2897b.mo10073g(), this.f2897b.mo10071e(), this.f2897b.mo10072f(), c0973a);
    }
}
