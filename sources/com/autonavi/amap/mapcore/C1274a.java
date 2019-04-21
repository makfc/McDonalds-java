package com.autonavi.amap.mapcore;

/* compiled from: ConnectionManager */
/* renamed from: com.autonavi.amap.mapcore.a */
class C1274a implements Runnable {
    /* renamed from: a */
    public BaseMapLoader f4564a = null;

    public C1274a(BaseMapLoader baseMapLoader) {
        this.f4564a = baseMapLoader;
    }

    public void run() {
        try {
            this.f4564a.doRequest();
        } catch (Throwable th) {
        }
    }
}
