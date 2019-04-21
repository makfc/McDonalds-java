package com.amap.api.services.busline;

import android.os.Handler;
import android.os.Message;

/* compiled from: BusStationSearch */
/* renamed from: com.amap.api.services.busline.d */
class C1074d extends Handler {
    /* renamed from: a */
    final /* synthetic */ BusStationSearch f3579a;

    C1074d(BusStationSearch busStationSearch) {
        this.f3579a = busStationSearch;
    }

    public void handleMessage(Message message) {
        if (this.f3579a.f3573c != null) {
            BusStationResult busStationResult = null;
            if (message.what == 0) {
                busStationResult = (BusStationResult) message.obj;
            }
            this.f3579a.f3573c.onBusStationSearched(busStationResult, message.what);
        }
    }
}
