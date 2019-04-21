package com.amap.api.mapcore.util;

import java.net.Proxy;

/* renamed from: com.amap.api.mapcore.util.fr */
public class DownloadManager {
    /* renamed from: a */
    private HttpUrlUtil f1991a;
    /* renamed from: b */
    private Request f1992b;

    /* compiled from: DownloadManager */
    /* renamed from: com.amap.api.mapcore.util.fr$a */
    public interface C0799a {
        /* renamed from: a */
        void mo9184a(Throwable th);

        /* renamed from: a */
        void mo9185a(byte[] bArr, long j);

        /* renamed from: d */
        void mo9186d();

        /* renamed from: e */
        void mo9187e();
    }

    public DownloadManager(Request request) {
        this(request, 0, -1);
    }

    public DownloadManager(Request request, long j, long j2) {
        Proxy proxy;
        this.f1992b = request;
        if (request.f1399i == null) {
            proxy = null;
        } else {
            proxy = request.f1399i;
        }
        this.f1991a = new HttpUrlUtil(this.f1992b.f1397g, this.f1992b.f1398h, proxy);
        this.f1991a.mo9425b(j2);
        this.f1991a.mo9423a(j);
    }

    /* renamed from: a */
    public void mo9418a(C0799a c0799a) {
        this.f1991a.mo9424a(this.f1992b.mo8901a(), this.f1992b.mo8907c(), this.f1992b.mo8905b(), c0799a);
    }

    /* renamed from: a */
    public void mo9417a() {
        this.f1991a.mo9422a();
    }
}
