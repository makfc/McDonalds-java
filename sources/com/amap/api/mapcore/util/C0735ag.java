package com.amap.api.mapcore.util;

/* compiled from: MapOverlayImageView */
/* renamed from: com.amap.api.mapcore.util.ag */
class C0735ag implements Runnable {
    /* renamed from: a */
    final /* synthetic */ MapOverlayImageView f998a;

    C0735ag(MapOverlayImageView mapOverlayImageView) {
        this.f998a = mapOverlayImageView;
    }

    public void run() {
        try {
            this.f998a.f986a.redrawInfoWindow();
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "MapOverlayImageView", "redrawInfoWindow post");
            th.printStackTrace();
        }
    }
}
