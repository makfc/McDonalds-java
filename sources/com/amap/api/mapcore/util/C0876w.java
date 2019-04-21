package com.amap.api.mapcore.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: GLOverlayLayer */
/* renamed from: com.amap.api.mapcore.util.w */
class C0876w implements Runnable {
    /* renamed from: a */
    final /* synthetic */ GLOverlayLayer f2176a;

    C0876w(GLOverlayLayer gLOverlayLayer) {
        this.f2176a = gLOverlayLayer;
    }

    public synchronized void run() {
        try {
            synchronized (this.f2176a) {
                ArrayList arrayList = new ArrayList(this.f2176a.f2172d);
                Collections.sort(arrayList, this.f2176a.f2171b);
                this.f2176a.f2172d = new CopyOnWriteArrayList(arrayList);
            }
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "MapOverlayImageView", "changeOverlayIndex");
        }
        return;
    }
}
