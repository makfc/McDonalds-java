package com.amap.api.mapcore.util;

import android.os.Handler;
import android.os.Message;

/* compiled from: UiSettingsDelegateImp */
/* renamed from: com.amap.api.mapcore.util.ay */
class C0750ay extends Handler {
    /* renamed from: a */
    final /* synthetic */ UiSettingsDelegateImp f1284a;

    C0750ay(UiSettingsDelegateImp uiSettingsDelegateImp) {
        this.f1284a = uiSettingsDelegateImp;
    }

    public void handleMessage(Message message) {
        if (message != null) {
            switch (message.what) {
                case 0:
                    this.f1284a.f1272b.showZoomControlsEnabled(this.f1284a.f1278h);
                    return;
                case 1:
                    this.f1284a.f1272b.showScaleEnabled(this.f1284a.f1280j);
                    return;
                case 2:
                    this.f1284a.f1272b.showCompassEnabled(this.f1284a.f1279i);
                    return;
                case 3:
                    this.f1284a.f1272b.showMyLocationButtonEnabled(this.f1284a.f1276f);
                    return;
                case 4:
                    this.f1284a.f1272b.showIndoorSwitchControlsEnabled(this.f1284a.f1283m);
                    return;
                default:
                    return;
            }
        }
    }
}
