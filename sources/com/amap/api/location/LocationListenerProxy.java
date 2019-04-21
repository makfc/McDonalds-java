package com.amap.api.location;

import android.location.Location;
import android.os.Bundle;

/* renamed from: com.amap.api.location.h */
public class LocationListenerProxy implements AMapLocationListener {
    /* renamed from: a */
    private LocationManagerProxy f950a;
    /* renamed from: b */
    private AMapLocationListener f951b = null;

    public LocationListenerProxy(LocationManagerProxy locationManagerProxy) {
        this.f950a = locationManagerProxy;
    }

    /* renamed from: a */
    public boolean mo8425a(AMapLocationListener aMapLocationListener, long j, float f, String str) {
        this.f951b = aMapLocationListener;
        if (!LocationProviderProxy.AMapNetwork.equals(str)) {
            return false;
        }
        this.f950a.requestLocationUpdates(str, j, f, (AMapLocationListener) this);
        return true;
    }

    /* renamed from: a */
    public void mo8424a() {
        if (this.f950a != null) {
            this.f950a.removeUpdates((AMapLocationListener) this);
        }
        this.f951b = null;
    }

    public void onLocationChanged(Location location) {
        if (this.f951b != null) {
            this.f951b.onLocationChanged(location);
        }
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
        if (this.f951b != null) {
            this.f951b.onStatusChanged(str, i, bundle);
        }
    }

    public void onProviderEnabled(String str) {
        if (this.f951b != null) {
            this.f951b.onProviderEnabled(str);
        }
    }

    public void onProviderDisabled(String str) {
        if (this.f951b != null) {
            this.f951b.onProviderDisabled(str);
        }
    }

    public void onLocationChanged(AMapLocation aMapLocation) {
        if (this.f951b != null) {
            this.f951b.onLocationChanged(aMapLocation);
        }
    }
}
