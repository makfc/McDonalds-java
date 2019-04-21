package com.amap.api.maps2d.model;

import android.os.RemoteException;
import com.amap.api.mapcore2d.C0955ck;
import com.amap.api.mapcore2d.IOverlayDelegate;
import com.amap.api.mapcore2d.IPolylineDelegate;
import java.util.List;

public class Polyline {
    /* renamed from: a */
    private final IPolylineDelegate f3447a;

    public Polyline(IPolylineDelegate iPolylineDelegate) {
        this.f3447a = iPolylineDelegate;
    }

    public void remove() {
        String str = "remove";
        try {
            if (this.f3447a != null) {
                this.f3447a.mo9653b();
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Polyline", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public String getId() {
        String str = "getId";
        try {
            if (this.f3447a == null) {
                return "";
            }
            return this.f3447a.mo9654c();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Polyline", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setPoints(List<LatLng> list) {
        String str = "setPoints";
        try {
            if (this.f3447a != null) {
                this.f3447a.mo9669a((List) list);
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Polyline", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public List<LatLng> getPoints() {
        String str = "getPoints";
        try {
            if (this.f3447a == null) {
                return null;
            }
            return this.f3447a.mo9675i();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Polyline", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setWidth(float f) {
        String str = "setWidth";
        try {
            if (this.f3447a != null) {
                this.f3447a.mo9670b(f);
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Polyline", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public float getWidth() {
        String str = "getWidth";
        try {
            if (this.f3447a == null) {
                return 0.0f;
            }
            return this.f3447a.mo9673g();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Polyline", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setColor(int i) {
        String str = "setColor";
        try {
            if (this.f3447a != null) {
                this.f3447a.mo9668a(i);
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Polyline", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public int getColor() {
        String str = "getColor";
        try {
            if (this.f3447a == null) {
                return 0;
            }
            return this.f3447a.mo9674h();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Polyline", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setZIndex(float f) {
        String str = "setZIndex";
        try {
            if (this.f3447a != null) {
                this.f3447a.mo9648a(f);
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Polyline", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public float getZIndex() {
        String str = "getZIndex";
        try {
            if (this.f3447a == null) {
                return 0.0f;
            }
            return this.f3447a.mo9655d();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Polyline", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setVisible(boolean z) {
        String str = "setVisible";
        try {
            if (this.f3447a != null) {
                this.f3447a.mo9650a(z);
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Polyline", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isVisible() {
        String str = "isVisible";
        try {
            if (this.f3447a == null) {
                return false;
            }
            return this.f3447a.mo9656e();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Polyline", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setGeodesic(boolean z) {
        String str = "setGeodesic";
        try {
            if (this.f3447a != null && this.f3447a.mo9677k() != z) {
                List points = getPoints();
                this.f3447a.mo9672c(z);
                setPoints(points);
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Polyline", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isGeodesic() {
        if (this.f3447a == null) {
            return false;
        }
        return this.f3447a.mo9677k();
    }

    public void setDottedLine(boolean z) {
        if (this.f3447a != null) {
            this.f3447a.mo9671b(z);
        }
    }

    public boolean isDottedLine() {
        if (this.f3447a == null) {
            return false;
        }
        return this.f3447a.mo9676j();
    }

    public boolean equals(Object obj) {
        String str = "equals";
        if (!(obj instanceof Polyline)) {
            return false;
        }
        try {
            if (this.f3447a != null) {
                return this.f3447a.mo9652a((IOverlayDelegate) ((Polyline) obj).f3447a);
            }
            return false;
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Polyline", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public int hashCode() {
        String str = "hashCode";
        try {
            if (this.f3447a == null) {
                return 0;
            }
            return this.f3447a.mo9657f();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Polyline", str);
            throw new RuntimeRemoteException(e);
        }
    }
}
