package com.amap.api.maps2d.model;

import android.os.RemoteException;
import com.amap.api.mapcore2d.C0955ck;
import com.amap.api.mapcore2d.IOverlayDelegate;
import com.amap.api.mapcore2d.IPolygonDelegate;
import com.amap.api.services.poisearch.PoiSearch.SearchBound;
import java.util.List;

public final class Polygon {
    /* renamed from: a */
    private IPolygonDelegate f3439a;

    public Polygon(IPolygonDelegate iPolygonDelegate) {
        this.f3439a = iPolygonDelegate;
    }

    public void remove() {
        String str = "remove";
        try {
            if (this.f3439a != null) {
                this.f3439a.mo9653b();
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, SearchBound.POLYGON_SHAPE, str);
            throw new RuntimeRemoteException(e);
        }
    }

    public String getId() {
        String str = "getId";
        try {
            if (this.f3439a == null) {
                return null;
            }
            return this.f3439a.mo9654c();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, SearchBound.POLYGON_SHAPE, str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setPoints(List<LatLng> list) {
        String str = "setPoints";
        try {
            if (this.f3439a != null) {
                this.f3439a.mo9660a((List) list);
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, SearchBound.POLYGON_SHAPE, str);
            throw new RuntimeRemoteException(e);
        }
    }

    public List<LatLng> getPoints() {
        String str = "getPoints";
        try {
            if (this.f3439a == null) {
                return null;
            }
            return this.f3439a.mo9666i();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, SearchBound.POLYGON_SHAPE, str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setStrokeWidth(float f) {
        String str = "setStrokeWidth";
        try {
            if (this.f3439a != null) {
                this.f3439a.mo9662b(f);
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, SearchBound.POLYGON_SHAPE, str);
            throw new RuntimeRemoteException(e);
        }
    }

    public float getStrokeWidth() {
        String str = "getStrokeWidth";
        try {
            if (this.f3439a == null) {
                return 0.0f;
            }
            return this.f3439a.mo9664g();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, SearchBound.POLYGON_SHAPE, str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setStrokeColor(int i) {
        String str = "setStrokeColor";
        try {
            if (this.f3439a != null) {
                this.f3439a.mo9663b(i);
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, SearchBound.POLYGON_SHAPE, str);
            throw new RuntimeRemoteException(e);
        }
    }

    public int getStrokeColor() {
        String str = "getStrokeColor";
        try {
            if (this.f3439a == null) {
                return 0;
            }
            return this.f3439a.mo9667j();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, SearchBound.POLYGON_SHAPE, str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setFillColor(int i) {
        String str = "setFillColor";
        try {
            if (this.f3439a != null) {
                this.f3439a.mo9659a(i);
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, SearchBound.POLYGON_SHAPE, str);
            throw new RuntimeRemoteException(e);
        }
    }

    public int getFillColor() {
        String str = "getFillColor";
        try {
            if (this.f3439a == null) {
                return 0;
            }
            return this.f3439a.mo9665h();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, SearchBound.POLYGON_SHAPE, str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setZIndex(float f) {
        String str = "setZIndex";
        try {
            if (this.f3439a != null) {
                this.f3439a.mo9648a(f);
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, SearchBound.POLYGON_SHAPE, str);
            throw new RuntimeRemoteException(e);
        }
    }

    public float getZIndex() {
        String str = "getZIndex";
        try {
            if (this.f3439a == null) {
                return 0.0f;
            }
            return this.f3439a.mo9655d();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, SearchBound.POLYGON_SHAPE, str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setVisible(boolean z) {
        String str = "setVisible";
        try {
            if (this.f3439a != null) {
                this.f3439a.mo9650a(z);
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, SearchBound.POLYGON_SHAPE, str);
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isVisible() {
        try {
            if (this.f3439a == null) {
                return true;
            }
            return this.f3439a.mo9656e();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean contains(LatLng latLng) {
        String str = "contains";
        try {
            if (this.f3439a == null) {
                return false;
            }
            return this.f3439a.mo9661a(latLng);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, SearchBound.POLYGON_SHAPE, str);
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean equals(Object obj) {
        String str = "equeals";
        if (!(obj instanceof Polygon)) {
            return false;
        }
        try {
            if (this.f3439a != null) {
                return this.f3439a.mo9652a((IOverlayDelegate) ((Polygon) obj).f3439a);
            }
            return false;
        } catch (RemoteException e) {
            C0955ck.m3888a(e, SearchBound.POLYGON_SHAPE, str);
            return false;
        }
    }

    public int hashCode() {
        String str = "hashCode";
        try {
            if (this.f3439a == null) {
                return 0;
            }
            return this.f3439a.mo9657f();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, SearchBound.POLYGON_SHAPE, str);
            return super.hashCode();
        }
    }
}
