package com.amap.api.services.route;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/* compiled from: RouteSearch */
/* renamed from: com.amap.api.services.route.m */
class C1184m extends Handler {
    /* renamed from: a */
    final /* synthetic */ RouteSearch f4132a;

    C1184m(RouteSearch routeSearch) {
        this.f4132a = routeSearch;
    }

    public void handleMessage(Message message) {
        if (this.f4132a.f4116b != null) {
            Bundle data;
            if (message.what == 10) {
                data = message.getData();
                if (data != null) {
                    this.f4132a.f4116b.onBusRouteSearched((BusRouteResult) message.obj, data.getInt("errorCode"));
                }
            } else if (message.what == 11) {
                data = message.getData();
                if (data != null) {
                    this.f4132a.f4116b.onDriveRouteSearched((DriveRouteResult) message.obj, data.getInt("errorCode"));
                }
            } else if (message.what == 12) {
                data = message.getData();
                if (data != null) {
                    this.f4132a.f4116b.onWalkRouteSearched((WalkRouteResult) message.obj, data.getInt("errorCode"));
                }
            }
        }
    }
}
