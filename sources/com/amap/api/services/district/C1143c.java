package com.amap.api.services.district;

import android.os.Handler;
import android.os.Message;

/* compiled from: DistrictSearch */
/* renamed from: com.amap.api.services.district.c */
class C1143c extends Handler {
    /* renamed from: a */
    final /* synthetic */ DistrictSearch f3846a;

    C1143c(DistrictSearch districtSearch) {
        this.f3846a = districtSearch;
    }

    public void handleMessage(Message message) {
        if (this.f3846a.f3837d != null) {
            this.f3846a.f3837d.onDistrictSearched((DistrictResult) message.obj);
        }
    }
}
