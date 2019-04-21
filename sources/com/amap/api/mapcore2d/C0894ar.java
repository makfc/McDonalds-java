package com.amap.api.mapcore2d;

import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: MapMessageQueue */
/* renamed from: com.amap.api.mapcore2d.ar */
class C0894ar {
    /* renamed from: a */
    AMapDelegateImpGLSurfaceView f2279a;
    /* renamed from: b */
    private CopyOnWriteArrayList<CameraUpdateFactoryDelegate> f2280b = new CopyOnWriteArrayList();
    /* renamed from: c */
    private CopyOnWriteArrayList<MapMessage> f2281c = new CopyOnWriteArrayList();

    public C0894ar(AMapDelegateImpGLSurfaceView aMapDelegateImpGLSurfaceView) {
        this.f2279a = aMapDelegateImpGLSurfaceView;
    }

    /* renamed from: a */
    public void mo9815a(CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate) {
        this.f2280b.add(cameraUpdateFactoryDelegate);
    }
}
