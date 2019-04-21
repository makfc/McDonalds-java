package com.amap.api.location;

import android.location.GpsStatus.Listener;

/* compiled from: IGPSManager */
/* renamed from: com.amap.api.location.g */
class C0731g implements Listener {
    /* renamed from: a */
    final /* synthetic */ IGPSManager f949a;

    C0731g(IGPSManager iGPSManager) {
        this.f949a = iGPSManager;
    }

    public void onGpsStatusChanged(int i) {
        this.f949a.m1483a(i, this.f949a.f940a.getGpsStatus(null));
    }
}
