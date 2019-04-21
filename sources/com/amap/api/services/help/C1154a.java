package com.amap.api.services.help;

import android.os.Handler;
import android.os.Message;
import java.util.List;

/* compiled from: Inputtips */
/* renamed from: com.amap.api.services.help.a */
class C1154a extends Handler {
    /* renamed from: a */
    final /* synthetic */ Inputtips f3909a;

    C1154a(Inputtips inputtips) {
        this.f3909a = inputtips;
    }

    public void handleMessage(Message message) {
        if (this.f3909a.f3905c != null) {
            List list = null;
            if (message.what == 0) {
                list = (List) message.obj;
            }
            this.f3909a.f3905c.onGetInputtips(list, message.what);
        }
    }
}
