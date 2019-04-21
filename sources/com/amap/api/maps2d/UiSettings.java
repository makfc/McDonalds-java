package com.amap.api.maps2d;

import android.os.RemoteException;
import com.amap.api.mapcore2d.C0955ck;
import com.amap.api.mapcore2d.IUiSettingsDelegate;
import com.amap.api.maps2d.model.RuntimeRemoteException;

public final class UiSettings {
    /* renamed from: a */
    private final IUiSettingsDelegate f3375a;

    UiSettings(IUiSettingsDelegate iUiSettingsDelegate) {
        this.f3375a = iUiSettingsDelegate;
    }

    public void setScaleControlsEnabled(boolean z) {
        String str = "setScaleControlsEnabled";
        try {
            this.f3375a.mo9715a(z);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "UiSettings", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setZoomControlsEnabled(boolean z) {
        String str = "setZoomControlsEnabled";
        try {
            this.f3375a.mo9718b(z);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "UiSettings", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setCompassEnabled(boolean z) {
        String str = "setCompassEnabled";
        try {
            this.f3375a.mo9720c(z);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "UiSettings", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setMyLocationButtonEnabled(boolean z) {
        String str = "setMyLocationButtonEnabled";
        try {
            this.f3375a.mo9722d(z);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "UiSettings", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setScrollGesturesEnabled(boolean z) {
        String str = "setScrollGesturesEnabled";
        try {
            this.f3375a.mo9724e(z);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "UiSettings", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setZoomGesturesEnabled(boolean z) {
        String str = "setZoomGesturesEnabled";
        try {
            this.f3375a.mo9726f(z);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "UiSettings", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setAllGesturesEnabled(boolean z) {
        String str = "setAllGesturesEnabled";
        try {
            this.f3375a.mo9729g(z);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "UiSettings", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setLogoPosition(int i) {
        String str = "setLogoPosition";
        try {
            this.f3375a.mo9714a(i);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "UiSettings", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setZoomPosition(int i) {
        String str = "setZoomPosition";
        try {
            this.f3375a.mo9717b(i);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "UiSettings", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isScaleControlsEnabled() {
        String str = "isScaleControlsEnabled";
        try {
            return this.f3375a.mo9716a();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "UiSettings", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isZoomControlsEnabled() {
        String str = "isZoomControlsEnabled";
        try {
            return this.f3375a.mo9719b();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "UiSettings", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isCompassEnabled() {
        String str = "isCompassEnabled";
        try {
            return this.f3375a.mo9721c();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "UiSettings", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isMyLocationButtonEnabled() {
        String str = "isMyLocationButtonEnabled";
        try {
            return this.f3375a.mo9723d();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "UiSettings", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isScrollGesturesEnabled() {
        String str = "isScrollGestureEnabled";
        try {
            return this.f3375a.mo9725e();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "UiSettings", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isZoomGesturesEnabled() {
        String str = "isZoomGesturesEnabled";
        try {
            return this.f3375a.mo9727f();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "UiSettings", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public int getLogoPosition() {
        String str = "getLogoPosition";
        try {
            return this.f3375a.mo9728g();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "UiSettings", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public int getZoomPosition() {
        String str = "getZoomPosition";
        try {
            return this.f3375a.mo9730h();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "UiSettings", str);
            throw new RuntimeRemoteException(e);
        }
    }
}
