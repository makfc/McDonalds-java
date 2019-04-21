package com.amap.api.mapcore2d;

import android.graphics.Point;
import android.os.RemoteException;
import com.amap.api.mapcore2d.CameraUpdateFactoryDelegate.C1036a;
import com.amap.api.maps2d.model.CameraPosition;

/* compiled from: AMapCallback */
/* renamed from: com.amap.api.mapcore2d.a */
class C0883a {
    /* renamed from: a */
    private AMapDelegateImpGLSurfaceView f2227a;
    /* renamed from: b */
    private int f2228b;

    public C0883a(AMapDelegateImpGLSurfaceView aMapDelegateImpGLSurfaceView) {
        this.f2227a = aMapDelegateImpGLSurfaceView;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo9608a(CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate) throws RemoteException {
        try {
            if (this.f2227a != null && this.f2227a.mo10007D() != null) {
                float f = this.f2227a.mo9985f();
                CameraPosition cameraPosition;
                if (cameraUpdateFactoryDelegate.f2993a == C1036a.scrollBy) {
                    this.f2227a.f2481b.mo9791b((int) cameraUpdateFactoryDelegate.f2994b, (int) cameraUpdateFactoryDelegate.f2995c);
                    this.f2227a.postInvalidate();
                } else if (cameraUpdateFactoryDelegate.f2993a == C1036a.zoomIn) {
                    this.f2227a.mo10007D().mo9794c();
                } else if (cameraUpdateFactoryDelegate.f2993a == C1036a.zoomOut) {
                    this.f2227a.mo10007D().mo9796d();
                } else if (cameraUpdateFactoryDelegate.f2993a == C1036a.zoomTo) {
                    this.f2227a.mo10007D().mo9793c(cameraUpdateFactoryDelegate.f2996d);
                } else if (cameraUpdateFactoryDelegate.f2993a == C1036a.zoomBy) {
                    float a = this.f2227a.mo10016a(cameraUpdateFactoryDelegate.f2997e + f);
                    Point point = cameraUpdateFactoryDelegate.f3003k;
                    float f2 = a - f;
                    if (point != null) {
                        this.f2227a.mo10018a(f2, point, false);
                    } else {
                        this.f2227a.mo10007D().mo9793c(a);
                    }
                } else if (cameraUpdateFactoryDelegate.f2993a == C1036a.newCameraPosition) {
                    cameraPosition = cameraUpdateFactoryDelegate.f2998f;
                    this.f2227a.mo10007D().mo9784a(new GeoPoint((int) (cameraPosition.target.latitude * 1000000.0d), (int) (cameraPosition.target.longitude * 1000000.0d)), cameraPosition.zoom);
                } else if (cameraUpdateFactoryDelegate.f2993a == C1036a.changeCenter) {
                    cameraPosition = cameraUpdateFactoryDelegate.f2998f;
                    this.f2227a.mo10007D().mo9783a(new GeoPoint((int) (cameraPosition.target.latitude * 1000000.0d), (int) (cameraPosition.target.longitude * 1000000.0d)));
                    CameraChangeFinishObserver.m4312a().mo10303b();
                } else if (cameraUpdateFactoryDelegate.f2993a == C1036a.newLatLngBounds || cameraUpdateFactoryDelegate.f2993a == C1036a.newLatLngBoundsWithSize) {
                    this.f2227a.mo10020a(cameraUpdateFactoryDelegate, false, -1);
                } else {
                    cameraUpdateFactoryDelegate.f3004l = true;
                }
                if (f != ((float) this.f2228b) && this.f2227a.mo9997q().mo9716a()) {
                    this.f2227a.mo10015N();
                }
            }
        } catch (Exception e) {
            C0955ck.m3888a(e, "AMapCallback", "runCameraUpdate");
        }
    }
}
