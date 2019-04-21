package com.aps;

import android.os.Handler;
import android.os.Message;

/* renamed from: com.aps.av */
final class C1234av extends Handler {
    /* renamed from: a */
    private /* synthetic */ C1233au f4283a;

    C1234av(C1233au c1233au) {
        this.f4283a = c1233au;
    }

    public final void handleMessage(Message message) {
        try {
            switch (message.what) {
                case 1:
                    if (this.f4283a.f4282a.f4159E != null) {
                        this.f4283a.f4282a.f4159E.mo13148a((String) message.obj);
                        return;
                    }
                    return;
                default:
                    return;
            }
        } catch (Exception e) {
        }
    }
}
