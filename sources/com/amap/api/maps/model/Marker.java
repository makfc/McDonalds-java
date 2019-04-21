package com.amap.api.maps.model;

import android.os.RemoteException;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IMarkerDelegate;
import java.util.ArrayList;

public final class Marker {
    /* renamed from: a */
    private IMarkerDelegate f3189a;

    public Marker(IMarkerDelegate iMarkerDelegate) {
        this.f3189a = iMarkerDelegate;
    }

    public void setPeriod(int i) {
        try {
            this.f3189a.setPeriod(i);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getPeriod() {
        try {
            return this.f3189a.getPeriod();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setIcons(ArrayList<BitmapDescriptor> arrayList) {
        try {
            this.f3189a.setIcons(arrayList);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public ArrayList<BitmapDescriptor> getIcons() {
        try {
            return this.f3189a.getIcons();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void remove() {
        try {
            this.f3189a.remove();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void destroy() {
        try {
            if (this.f3189a != null) {
                this.f3189a.destroy();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public String getId() {
        try {
            return this.f3189a.getId();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setPerspective(boolean z) {
        try {
            this.f3189a.setPerspective(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isPerspective() {
        try {
            return this.f3189a.isPerspective();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setPosition(LatLng latLng) {
        try {
            this.f3189a.setPosition(latLng);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public LatLng getPosition() {
        try {
            return this.f3189a.getPosition();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setTitle(String str) {
        try {
            this.f3189a.setTitle(str);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getTitle() {
        try {
            return this.f3189a.getTitle();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setSnippet(String str) {
        try {
            this.f3189a.setSnippet(str);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getSnippet() {
        try {
            return this.f3189a.getSnippet();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setIcon(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor != null) {
            try {
                this.f3189a.setIcon(bitmapDescriptor);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
    }

    public void setAnchor(float f, float f2) {
        try {
            this.f3189a.setAnchor(f, f2);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setDraggable(boolean z) {
        try {
            this.f3189a.setDraggable(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isDraggable() {
        return this.f3189a.isDraggable();
    }

    public void showInfoWindow() {
        try {
            this.f3189a.showInfoWindow();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void hideInfoWindow() {
        try {
            this.f3189a.hideInfoWindow();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isInfoWindowShown() {
        return this.f3189a.isInfoWindowShown();
    }

    public void setVisible(boolean z) {
        try {
            this.f3189a.setVisible(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isVisible() {
        try {
            return this.f3189a.isVisible();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean equals(Object obj) {
        try {
            if (obj instanceof Marker) {
                return this.f3189a.equalsRemote(((Marker) obj).f3189a);
            }
            return false;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int hashCode() {
        return this.f3189a.hashCodeRemote();
    }

    public void setObject(Object obj) {
        this.f3189a.setObject(obj);
    }

    public Object getObject() {
        return this.f3189a.getObject();
    }

    public void setRotateAngle(float f) {
        try {
            this.f3189a.setRotateAngle(f);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public float getRotateAngle() {
        return this.f3189a.getRotateAngle();
    }

    public void setToTop() {
        try {
            this.f3189a.set2Top();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setGeoPoint(IPoint iPoint) {
        this.f3189a.setGeoPoint(iPoint);
    }

    public IPoint getGeoPoint() {
        return this.f3189a.getGeoPoint();
    }

    public void setFlat(boolean z) {
        try {
            this.f3189a.setFlat(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isFlat() {
        return this.f3189a.isFlat();
    }

    public void setPositionByPixels(int i, int i2) {
        this.f3189a.setPositionByPixels(i, i2);
    }

    public void setZIndex(float f) {
        this.f3189a.setZIndex(f);
    }

    public float getZIndex() {
        return this.f3189a.getZIndex();
    }
}
