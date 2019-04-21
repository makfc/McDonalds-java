package com.amap.api.services.geocoder;

import android.os.Handler;
import android.os.Message;

/* compiled from: GeocodeSearch */
/* renamed from: com.amap.api.services.geocoder.c */
class C1149c extends Handler {
    /* renamed from: a */
    final /* synthetic */ GeocodeSearch f3899a;

    C1149c(GeocodeSearch geocodeSearch) {
        this.f3899a = geocodeSearch;
    }

    public void handleMessage(Message message) {
        GeocodeResult geocodeResult = null;
        if (this.f3899a.f3869c != null) {
            if (message.what == 101) {
                RegeocodeResult regeocodeResult;
                if (message.arg2 == 0) {
                    regeocodeResult = (RegeocodeResult) message.obj;
                }
                this.f3899a.f3869c.onRegeocodeSearched(regeocodeResult, message.arg1);
                return;
            }
            if (message.arg2 == 0) {
                geocodeResult = (GeocodeResult) message.obj;
            }
            this.f3899a.f3869c.onGeocodeSearched(geocodeResult, message.arg1);
        }
    }
}
