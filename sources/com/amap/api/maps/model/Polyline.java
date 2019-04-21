package com.amap.api.maps.model;

import android.os.RemoteException;
import com.autonavi.amap.mapcore.interfaces.IPolylineDelegate;
import java.util.List;

public class Polyline {
    /* renamed from: a */
    private final IPolylineDelegate f3239a;

    public Polyline(IPolylineDelegate iPolylineDelegate) {
        this.f3239a = iPolylineDelegate;
    }

    public void remove() {
        try {
            this.f3239a.remove();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getId() {
        try {
            return this.f3239a.getId();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setPoints(List<LatLng> list) {
        try {
            this.f3239a.setPoints(list);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public List<LatLng> getPoints() {
        try {
            return this.f3239a.getPoints();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setGeodesic(boolean z) {
        try {
            if (this.f3239a.isGeodesic() != z) {
                List points = getPoints();
                this.f3239a.setGeodesic(z);
                setPoints(points);
            }
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isGeodesic() {
        return this.f3239a.isGeodesic();
    }

    public void setDottedLine(boolean z) {
        this.f3239a.setDottedLine(z);
    }

    public boolean isDottedLine() {
        return this.f3239a.isDottedLine();
    }

    public void setWidth(float f) {
        try {
            this.f3239a.setWidth(f);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public float getWidth() {
        try {
            return this.f3239a.getWidth();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setColor(int i) {
        try {
            this.f3239a.setColor(i);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getColor() {
        try {
            return this.f3239a.getColor();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setZIndex(float f) {
        try {
            this.f3239a.setZIndex(f);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public float getZIndex() {
        try {
            return this.f3239a.getZIndex();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setVisible(boolean z) {
        try {
            this.f3239a.setVisible(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isVisible() {
        try {
            return this.f3239a.isVisible();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Polyline)) {
            return false;
        }
        try {
            return this.f3239a.equalsRemote(((Polyline) obj).f3239a);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int hashCode() {
        try {
            return this.f3239a.hashCodeRemote();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public LatLng getNearestLatLng(LatLng latLng) {
        return this.f3239a.getNearestLatLng(latLng);
    }

    public void setTransparency(float f) {
        this.f3239a.setTransparency(f);
    }
}
