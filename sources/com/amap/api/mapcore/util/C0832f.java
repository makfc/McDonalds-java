package com.amap.api.mapcore.util;

import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;

/* compiled from: AMapDelegateImp */
/* renamed from: com.amap.api.mapcore.util.f */
class C0832f extends Handler {
    /* renamed from: a */
    final /* synthetic */ AMapDelegateImp f1915a;

    C0832f(AMapDelegateImp aMapDelegateImp) {
        this.f1915a = aMapDelegateImp;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        try {
            if (this.f1915a.f1587ac != null) {
                this.f1915a.f1587ac.onTouch((MotionEvent) message.obj);
            }
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "AMapDelegateImpGLSurfaceView", "onTouchHandler");
            th.printStackTrace();
        }
    }
}
