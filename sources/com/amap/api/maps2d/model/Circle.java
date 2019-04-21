package com.amap.api.maps2d.model;

import android.os.RemoteException;
import com.amap.api.mapcore2d.C0955ck;
import com.amap.api.mapcore2d.ICircleDelegate;
import com.amap.api.mapcore2d.IOverlayDelegate;

public final class Circle {
    /* renamed from: a */
    private final ICircleDelegate f3384a;

    public Circle(ICircleDelegate iCircleDelegate) {
        this.f3384a = iCircleDelegate;
    }

    public void remove() {
        String str = "remove";
        try {
            if (this.f3384a != null) {
                this.f3384a.mo9653b();
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Circle", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public String getId() {
        String str = "getId";
        try {
            if (this.f3384a == null) {
                return "";
            }
            return this.f3384a.mo9654c();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Circle", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setCenter(LatLng latLng) {
        String str = "setCenter";
        try {
            if (this.f3384a != null) {
                this.f3384a.mo10310a(latLng);
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Circle", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public LatLng getCenter() {
        String str = "getCenter";
        try {
            if (this.f3384a == null) {
                return null;
            }
            return this.f3384a.mo10314g();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Circle", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setRadius(double d) {
        String str = "setRadius";
        try {
            if (this.f3384a != null) {
                this.f3384a.mo10308a(d);
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Circle", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public double getRadius() {
        String str = "getRadius";
        try {
            if (this.f3384a == null) {
                return 0.0d;
            }
            return this.f3384a.mo10315h();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Circle", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setStrokeWidth(float f) {
        String str = "setStrokeWidth";
        try {
            if (this.f3384a != null) {
                this.f3384a.mo10311b(f);
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Circle", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public float getStrokeWidth() {
        String str = "getStrokeWidth";
        try {
            if (this.f3384a == null) {
                return 0.0f;
            }
            return this.f3384a.mo10316i();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Circle", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setStrokeColor(int i) {
        String str = "setStrokeColor";
        try {
            if (this.f3384a != null) {
                this.f3384a.mo10309a(i);
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Circle", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public int getStrokeColor() {
        String str = "getStrokeColor";
        try {
            if (this.f3384a == null) {
                return 0;
            }
            return this.f3384a.mo10317j();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Circle", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setFillColor(int i) {
        String str = "setFillColor";
        try {
            if (this.f3384a != null) {
                this.f3384a.mo10312b(i);
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Circle", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public int getFillColor() {
        String str = "getFillColor";
        try {
            if (this.f3384a == null) {
                return 0;
            }
            return this.f3384a.mo10318k();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Circle", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setZIndex(float f) {
        String str = "setZIndex";
        try {
            if (this.f3384a != null) {
                this.f3384a.mo9648a(f);
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Circle", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public float getZIndex() {
        String str = "getZIndex";
        try {
            if (this.f3384a == null) {
                return 0.0f;
            }
            return this.f3384a.mo9655d();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Circle", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setVisible(boolean z) {
        String str = "setVisible";
        try {
            if (this.f3384a != null) {
                this.f3384a.mo9650a(z);
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Circle", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isVisible() {
        String str = "isVisible";
        try {
            if (this.f3384a == null) {
                return false;
            }
            return this.f3384a.mo9656e();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Circle", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean contains(LatLng latLng) {
        String str = "contains";
        try {
            if (this.f3384a == null) {
                return false;
            }
            return this.f3384a.mo10313b(latLng);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Circle", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean equals(Object obj) {
        String str = "equals";
        if (!(obj instanceof Circle)) {
            return false;
        }
        try {
            if (this.f3384a != null) {
                return this.f3384a.mo9652a((IOverlayDelegate) ((Circle) obj).f3384a);
            }
            return false;
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Circle", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public int hashCode() {
        String str = "hashCode";
        try {
            if (this.f3384a == null) {
                return 0;
            }
            return this.f3384a.mo9657f();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Circle", str);
            throw new RuntimeRemoteException(e);
        }
    }
}
