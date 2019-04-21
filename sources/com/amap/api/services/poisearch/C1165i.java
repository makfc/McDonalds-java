package com.amap.api.services.poisearch;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/* compiled from: PoiSearch */
/* renamed from: com.amap.api.services.poisearch.i */
class C1165i extends Handler {
    /* renamed from: a */
    final /* synthetic */ PoiSearch f4027a;

    C1165i(PoiSearch poiSearch) {
        this.f4027a = poiSearch;
    }

    public void handleMessage(Message message) {
        if (this.f4027a.f4009e != null) {
            Bundle data;
            if (message.what == 100) {
                data = message.getData();
                if (data != null) {
                    this.f4027a.f4009e.onPoiSearched((PoiResult) message.obj, data.getInt("errorCode"));
                }
            } else if (message.what == 101) {
                data = message.getData();
                if (data != null) {
                    this.f4027a.f4009e.onPoiItemDetailSearched((PoiItemDetail) message.obj, data.getInt("errorCode"));
                }
            }
        }
    }
}
