package com.amap.api.mapcore.util;

/* renamed from: com.amap.api.mapcore.util.ch */
public interface IDownloadListener {

    /* compiled from: IDownloadListener */
    /* renamed from: com.amap.api.mapcore.util.ch$a */
    public enum C0758a {
        amap_exception(-1),
        network_exception(-1),
        file_io_exception(0),
        success_no_exception(1),
        cancel_no_exception(2);
        
        /* renamed from: f */
        private int f1359f;

        private C0758a(int i) {
            this.f1359f = i;
        }
    }

    /* renamed from: a */
    void mo8842a(long j, long j2);

    /* renamed from: a */
    void mo8843a(C0758a c0758a);

    /* renamed from: m */
    void mo8844m();

    /* renamed from: n */
    void mo8845n();

    /* renamed from: o */
    void mo8846o();
}
