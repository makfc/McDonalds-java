package com.amap.api.mapcore.util;

import android.location.Location;
import android.os.RemoteException;
import com.amap.api.maps.LocationSource.OnLocationChangedListener;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;

/* renamed from: com.amap.api.mapcore.util.m */
class AMapOnLocationChangedListener implements OnLocationChangedListener {
    /* renamed from: a */
    Location f2073a;
    /* renamed from: b */
    private IAMapDelegate f2074b;

    AMapOnLocationChangedListener(IAMapDelegate iAMapDelegate) {
        this.f2074b = iAMapDelegate;
    }

    public void onLocationChanged(Location location) {
        this.f2073a = location;
        try {
            if (this.f2074b.isMyLocationEnabled()) {
                this.f2074b.showMyLocationOverlay(location);
            }
        } catch (RemoteException e) {
            SDKLogHandler.m2563a(e, "AMapOnLocationChangedListener", "onLocationChanged");
            e.printStackTrace();
        }
    }
}
