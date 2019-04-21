package com.amap.api.maps2d.model;

import android.os.RemoteException;
import com.amap.api.mapcore2d.C0955ck;
import com.amap.api.mapcore2d.IGroundOverlayDelegate;

public final class GroundOverlay {
    /* renamed from: a */
    private IGroundOverlayDelegate f3393a;

    public GroundOverlay(IGroundOverlayDelegate iGroundOverlayDelegate) {
        this.f3393a = iGroundOverlayDelegate;
    }

    public void remove() {
        String str = "remove";
        try {
            if (this.f3393a != null) {
                this.f3393a.mo9653b();
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "GroundOverlay", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public String getId() {
        String str = "getId";
        try {
            if (this.f3393a == null) {
                return "";
            }
            return this.f3393a.mo9654c();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "GroundOverlay", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setPosition(LatLng latLng) {
        String str = "setPosition";
        try {
            if (this.f3393a != null) {
                this.f3393a.mo10345a(latLng);
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "GroundOverlay", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public LatLng getPosition() {
        String str = "getPosition";
        try {
            if (this.f3393a == null) {
                return null;
            }
            return this.f3393a.mo10350h();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "GroundOverlay", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setDimensions(float f) {
        String str = "setDimensions";
        try {
            if (this.f3393a != null) {
                this.f3393a.mo10347b(f);
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "GroundOverlay", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setImage(BitmapDescriptor bitmapDescriptor) {
        String str = "setImage";
        try {
            if (this.f3393a != null) {
                this.f3393a.mo10344a(bitmapDescriptor);
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "GroundOverlay", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setDimensions(float f, float f2) {
        String str = "setDimensions";
        try {
            if (this.f3393a != null) {
                this.f3393a.mo10343a(f, f2);
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "GroundOverlay", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public float getWidth() {
        String str = "getWidth";
        try {
            if (this.f3393a == null) {
                return 0.0f;
            }
            return this.f3393a.mo10351i();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "GroundOverlay", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public float getHeight() {
        String str = "getHeight";
        try {
            if (this.f3393a == null) {
                return 0.0f;
            }
            return this.f3393a.mo10352j();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "GroundOverlay", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setPositionFromBounds(LatLngBounds latLngBounds) {
        String str = "setPositionFromBounds";
        try {
            if (this.f3393a != null) {
                this.f3393a.mo10346a(latLngBounds);
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "GroundOverlay", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public LatLngBounds getBounds() {
        String str = "getBounds";
        try {
            if (this.f3393a == null) {
                return null;
            }
            return this.f3393a.mo10353k();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "GroundOverlay", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setBearing(float f) {
        String str = "setBearing";
        try {
            if (this.f3393a != null) {
                this.f3393a.mo10348c(f);
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "GroundOverlay", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public float getBearing() {
        String str = "getBearing";
        try {
            if (this.f3393a == null) {
                return 0.0f;
            }
            return this.f3393a.mo10354m();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "GroundOverlay", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setZIndex(float f) {
        String str = "setZIndex";
        try {
            if (this.f3393a != null) {
                this.f3393a.mo9648a(f);
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "GroundOverlay", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public float getZIndex() {
        String str = "getZIndex";
        try {
            if (this.f3393a == null) {
                return 0.0f;
            }
            return this.f3393a.mo9655d();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "GroundOverlay", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setVisible(boolean z) {
        String str = "setVisible";
        try {
            if (this.f3393a != null) {
                this.f3393a.mo9650a(z);
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "GroundOverlay", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isVisible() {
        String str = "isVisible";
        try {
            if (this.f3393a == null) {
                return false;
            }
            return this.f3393a.mo9656e();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "GroundOverlay", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setTransparency(float f) {
        String str = "setTransparency";
        try {
            if (this.f3393a != null) {
                this.f3393a.mo10349d(f);
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "GroundOverlay", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public float getTransparency() {
        String str = "getTransparency";
        try {
            if (this.f3393a == null) {
                return 0.0f;
            }
            return this.f3393a.mo10355n();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "GroundOverlay", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean equals(Object obj) {
        String str = "equals";
        if (!(obj instanceof GroundOverlay)) {
            return false;
        }
        try {
            throw new RemoteException();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "GroundOverlay", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public int hashCode() {
        if (this.f3393a == null) {
            return 0;
        }
        return this.f3393a.hashCode();
    }
}
