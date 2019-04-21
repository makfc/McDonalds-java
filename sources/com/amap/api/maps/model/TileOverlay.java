package com.amap.api.maps.model;

import com.autonavi.amap.mapcore.interfaces.ITileOverlayDelegate;

public final class TileOverlay {
    /* renamed from: a */
    private ITileOverlayDelegate f3275a;

    public TileOverlay(ITileOverlayDelegate iTileOverlayDelegate) {
        this.f3275a = iTileOverlayDelegate;
    }

    public void remove() {
        this.f3275a.remove();
    }

    public void clearTileCache() {
        this.f3275a.clearTileCache();
    }

    public String getId() {
        return this.f3275a.getId();
    }

    public void setZIndex(float f) {
        this.f3275a.setZIndex(f);
    }

    public float getZIndex() {
        return this.f3275a.getZIndex();
    }

    public void setVisible(boolean z) {
        this.f3275a.setVisible(z);
    }

    public boolean isVisible() {
        return this.f3275a.isVisible();
    }

    public boolean equals(Object obj) {
        if (obj instanceof TileOverlay) {
            return this.f3275a.equalsRemote(((TileOverlay) obj).f3275a);
        }
        return false;
    }

    public int hashCode() {
        return this.f3275a.hashCodeRemote();
    }
}
