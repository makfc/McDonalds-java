package com.amap.api.maps2d.model;

import com.amap.api.mapcore2d.ITileOverlayDelegate;

public final class TileOverlay {
    /* renamed from: a */
    private ITileOverlayDelegate f3478a;

    public TileOverlay(ITileOverlayDelegate iTileOverlayDelegate) {
        this.f3478a = iTileOverlayDelegate;
    }

    public void remove() {
        this.f3478a.mo9700a();
    }

    public void clearTileCache() {
        this.f3478a.mo9705b();
    }

    public String getId() {
        return this.f3478a.mo9707c();
    }

    public void setZIndex(float f) {
        this.f3478a.mo9701a(f);
    }

    public float getZIndex() {
        return this.f3478a.mo9708d();
    }

    public void setVisible(boolean z) {
        this.f3478a.mo9703a(z);
    }

    public boolean isVisible() {
        return this.f3478a.mo9709e();
    }

    public boolean equals(Object obj) {
        return this.f3478a.mo9704a(this.f3478a);
    }

    public int hashCode() {
        return this.f3478a.mo9710f();
    }
}
