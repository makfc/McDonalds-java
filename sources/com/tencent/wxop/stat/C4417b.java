package com.tencent.wxop.stat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* renamed from: com.tencent.wxop.stat.b */
class C4417b extends BroadcastReceiver {
    /* renamed from: a */
    final /* synthetic */ C4389a f7073a;

    C4417b(C4389a c4389a) {
        this.f7073a = c4389a;
    }

    public void onReceive(Context context, Intent intent) {
        if (this.f7073a.f6994e != null) {
            this.f7073a.f6994e.mo33966a(new C4422c(this));
        }
    }
}
