package com.amap.api.location;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.SystemClock;

/* compiled from: IGPSManager */
/* renamed from: com.amap.api.location.e */
class C0729e implements LocationListener {
    /* renamed from: a */
    final /* synthetic */ IGPSManager f947a;

    C0729e(IGPSManager iGPSManager) {
        this.f947a = iGPSManager;
    }

    public void onLocationChanged(Location location) {
        this.f947a.f944e.mo8361b(true);
        this.f947a.f944e.f842e = SystemClock.elapsedRealtime();
    }

    public void onProviderDisabled(String str) {
        if ("gps".equals(str)) {
            this.f947a.f944e.mo8361b(false);
        }
    }

    public void onProviderEnabled(String str) {
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
        if (i == 0 || i == 1) {
            this.f947a.f944e.mo8361b(false);
        }
    }
}
