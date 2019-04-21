package com.amap.api.services.busline;

import android.os.Handler;
import android.os.Message;

/* compiled from: BusLineSearch */
/* renamed from: com.amap.api.services.busline.b */
class C1072b extends Handler {
    /* renamed from: a */
    final /* synthetic */ BusLineSearch f3578a;

    C1072b(BusLineSearch busLineSearch) {
        this.f3578a = busLineSearch;
    }

    public void handleMessage(Message message) {
        if (this.f3578a.f3550c != null) {
            BusLineResult busLineResult = null;
            if (message.what == 0) {
                busLineResult = (BusLineResult) message.obj;
            }
            this.f3578a.f3550c.onBusLineSearched(busLineResult, message.what);
        }
    }
}
