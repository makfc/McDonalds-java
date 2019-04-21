package com.amap.api.mapcore2d;

import android.graphics.Color;
import android.os.RemoteException;
import com.amap.api.mapcore2d.C0885ah.C0884a;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CircleOptions;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;

/* compiled from: MyLocationOverlay */
/* renamed from: com.amap.api.mapcore2d.bb */
class C0926bb {
    /* renamed from: a */
    private IAMapDelegate f2533a;
    /* renamed from: b */
    private IMarkerDelegate f2534b;
    /* renamed from: c */
    private ICircleDelegate f2535c;
    /* renamed from: d */
    private MyLocationStyle f2536d;
    /* renamed from: e */
    private LatLng f2537e;
    /* renamed from: f */
    private double f2538f;

    C0926bb(IAMapDelegate iAMapDelegate) {
        this.f2533a = iAMapDelegate;
    }

    /* renamed from: a */
    public void mo10060a(MyLocationStyle myLocationStyle) {
        String str = "setMyLocationStyle";
        this.f2536d = myLocationStyle;
        if (this.f2534b != null || this.f2535c != null) {
            try {
                mo10057a();
            } catch (RemoteException e) {
                C0955ck.m3888a(e, "MyLocationOverlay", str);
            }
            m3600d();
        }
    }

    /* renamed from: a */
    public void mo10059a(LatLng latLng, double d) {
        String str = "setCentAndRadius";
        this.f2537e = latLng;
        this.f2538f = d;
        if (this.f2534b == null && this.f2535c == null) {
            m3598b();
        }
        if (this.f2534b != null) {
            this.f2534b.mo9612b(latLng);
            try {
                this.f2535c.mo10310a(latLng);
                if (d != -1.0d) {
                    this.f2535c.mo10308a(d);
                }
            } catch (RemoteException e) {
                C0955ck.m3888a(e, "MyLocationOverlay", str);
            }
        }
    }

    /* renamed from: b */
    private void m3598b() {
        if (this.f2536d == null) {
            m3599c();
        } else {
            m3600d();
        }
    }

    /* renamed from: a */
    public void mo10057a() throws RemoteException {
        if (this.f2535c != null) {
            this.f2533a.mo9969a(this.f2535c.mo9654c());
            this.f2535c = null;
        }
        if (this.f2534b != null) {
            this.f2533a.mo9976b(this.f2534b.mo9634d());
            this.f2534b = null;
        }
    }

    /* renamed from: c */
    private void m3599c() {
        String str = "defaultLocStyle";
        try {
            this.f2535c = this.f2533a.mo9942a(new CircleOptions().strokeWidth(1.0f).fillColor(Color.argb(20, 0, 0, 180)).strokeColor(Color.argb(255, 0, 0, 220)).center(new LatLng(0.0d, 0.0d)));
            this.f2535c.mo10308a(200.0d);
            this.f2534b = this.f2533a.mo9970b(new MarkerOptions().anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromAsset(C0884a.marker_gps_no_sharing2d.name() + ".png")).position(new LatLng(0.0d, 0.0d)));
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "MyLocationOverlay", str);
        }
    }

    /* renamed from: d */
    private void m3600d() {
        if (this.f2536d != null) {
            try {
                this.f2535c = this.f2533a.mo9942a(new CircleOptions().strokeWidth(this.f2536d.getStrokeWidth()).fillColor(this.f2536d.getRadiusFillColor()).strokeColor(this.f2536d.getStrokeColor()).center(new LatLng(0.0d, 0.0d)));
                if (this.f2537e != null) {
                    this.f2535c.mo10310a(this.f2537e);
                }
                this.f2535c.mo10308a(this.f2538f);
                this.f2534b = this.f2533a.mo9970b(new MarkerOptions().anchor(this.f2536d.getAnchorU(), this.f2536d.getAnchorV()).icon(this.f2536d.getMyLocationIcon()).position(new LatLng(0.0d, 0.0d)));
                if (this.f2537e != null) {
                    this.f2534b.mo9612b(this.f2537e);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public void mo10058a(float f) {
        String str = "setRotateAngle";
        if (this.f2534b != null) {
            try {
                this.f2534b.mo9618a(f);
            } catch (RemoteException e) {
                C0955ck.m3888a(e, "MyLocationOverlay", str);
            }
        }
    }
}
