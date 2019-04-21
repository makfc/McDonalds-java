package com.amap.api.mapcore2d;

import android.location.Location;
import android.os.RemoteException;
import com.amap.api.maps2d.LocationSource.OnLocationChangedListener;

/* compiled from: AMapOnLocationChangedListener */
/* renamed from: com.amap.api.mapcore2d.c */
class C0946c implements OnLocationChangedListener {
    /* renamed from: a */
    Location f2659a;
    /* renamed from: b */
    private IAMapDelegate f2660b;

    C0946c(IAMapDelegate iAMapDelegate) {
        this.f2660b = iAMapDelegate;
    }

    public void onLocationChanged(Location location) {
        String str = "onLocationChanged";
        this.f2659a = location;
        try {
            if (this.f2660b.mo9995n()) {
                this.f2660b.mo9950a(location);
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMapOnLocationChangedListener", str);
        }
    }
}
