package com.amap.api.maps2d.model;

import android.os.RemoteException;
import com.amap.api.mapcore2d.C0955ck;
import com.amap.api.mapcore2d.IMarkerDelegate;
import java.util.ArrayList;

public final class Marker {
    /* renamed from: a */
    IMarkerDelegate f3412a;

    public Marker(MarkerOptions markerOptions) {
    }

    public Marker(IMarkerDelegate iMarkerDelegate) {
        this.f3412a = iMarkerDelegate;
    }

    public void setPosition(LatLng latLng) {
        this.f3412a.mo9612b(latLng);
    }

    public LatLng getPosition() {
        return this.f3412a.mo9615t();
    }

    public float getZIndex() {
        return this.f3412a.mo9613r();
    }

    public void setZIndex(float f) {
        this.f3412a.mo9610b(f);
    }

    public void remove() {
        String str = "remove";
        try {
            this.f3412a.mo9628a();
        } catch (Exception e) {
            C0955ck.m3888a(e, "Marker", str);
        }
    }

    public void setObject(Object obj) {
        this.f3412a.mo9609a(obj);
    }

    public Object getObject() {
        if (this.f3412a != null) {
            return this.f3412a.mo9616u();
        }
        return null;
    }

    public void setPeriod(int i) {
        String str = "setPeriod";
        try {
            this.f3412a.mo9620a(i);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Marker", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public int getPeriod() {
        String str = "getPeriod";
        try {
            return this.f3412a.mo9645o();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Marker", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setIcons(ArrayList<BitmapDescriptor> arrayList) {
        String str = "setIcons";
        try {
            this.f3412a.mo9626a((ArrayList) arrayList);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Marker", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public ArrayList<BitmapDescriptor> getIcons() {
        String str = "getIcons";
        try {
            return this.f3412a.mo9646p();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Marker", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void destroy() {
        String str = "destroy";
        try {
            if (this.f3412a != null) {
                this.f3412a.mo9642l();
            }
        } catch (Exception e) {
            C0955ck.m3888a(e, "Marker", str);
        }
    }

    public String getId() {
        return this.f3412a.mo9634d();
    }

    public void setTitle(String str) {
        this.f3412a.mo9625a(str);
    }

    public String getTitle() {
        return this.f3412a.mo9636f();
    }

    public void setSnippet(String str) {
        this.f3412a.mo9631b(str);
    }

    public String getSnippet() {
        return this.f3412a.mo9637g();
    }

    public void setIcon(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor != null) {
            this.f3412a.mo9623a(bitmapDescriptor);
        }
    }

    public void setAnchor(float f, float f2) {
        this.f3412a.mo9619a(f, f2);
    }

    public void setDraggable(boolean z) {
        this.f3412a.mo9627a(z);
    }

    public boolean isDraggable() {
        return this.f3412a.mo9638h();
    }

    public void showInfoWindow() {
        if (this.f3412a != null) {
            this.f3412a.mo9639i();
        }
    }

    public void hideInfoWindow() {
        this.f3412a.mo9640j();
    }

    public boolean isInfoWindowShown() {
        return this.f3412a.mo9641k();
    }

    public void setVisible(boolean z) {
        this.f3412a.mo9632b(z);
    }

    public boolean isVisible() {
        return this.f3412a.mo9614s();
    }

    public void setRotateAngle(float f) {
        String str = "setRotateAngle";
        try {
            this.f3412a.mo9618a(f);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Marker", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof Marker) {
            return this.f3412a.mo9629a(((Marker) obj).f3412a);
        }
        return false;
    }

    public int hashCode() {
        return this.f3412a.mo9643m();
    }

    public void setPositionByPixels(int i, int i2) {
        String str = "setPositionByPixels";
        try {
            this.f3412a.mo9621a(i, i2);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Marker", str);
            e.printStackTrace();
        }
    }
}
