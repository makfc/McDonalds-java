package com.amap.api.mapcore.util;

import android.util.Log;
import com.autonavi.amap.mapcore.interfaces.GLOverlay;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.amap.api.mapcore.util.s */
class CustomGLOverlayLayer {
    /* renamed from: a */
    C0867a f2129a = new C0867a();
    /* renamed from: b */
    private CopyOnWriteArrayList<GLOverlay> f2130b = new CopyOnWriteArrayList();

    /* compiled from: CustomGLOverlayLayer */
    /* renamed from: com.amap.api.mapcore.util.s$a */
    static class C0867a implements Serializable, Comparator<Object> {
        C0867a() {
        }

        public int compare(Object obj, Object obj2) {
            GLOverlay gLOverlay = (GLOverlay) obj;
            GLOverlay gLOverlay2 = (GLOverlay) obj2;
            if (!(gLOverlay == null || gLOverlay2 == null)) {
                try {
                    if (gLOverlay.getZIndex() > gLOverlay2.getZIndex()) {
                        return 1;
                    }
                    if (gLOverlay.getZIndex() < gLOverlay2.getZIndex()) {
                        return -1;
                    }
                } catch (Throwable th) {
                    SDKLogHandler.m2563a(th, "CustomGLOverlayLayer", "compare");
                    th.printStackTrace();
                }
            }
            return 0;
        }
    }

    CustomGLOverlayLayer() {
    }

    /* renamed from: a */
    public void mo9542a() {
        try {
            this.f2130b.clear();
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "CustomGLOverlayLayer", "clear");
            th.printStackTrace();
            Log.d("amapApi", "GLOverlayLayer clear erro" + th.getMessage());
        }
    }

    /* renamed from: a */
    public void mo9543a(GLOverlay gLOverlay) {
        mo9545b(gLOverlay);
        this.f2130b.add(gLOverlay);
        m2897b();
    }

    /* renamed from: b */
    public boolean mo9545b(GLOverlay gLOverlay) {
        if (this.f2130b.contains(gLOverlay)) {
            return this.f2130b.remove(gLOverlay);
        }
        return false;
    }

    /* renamed from: b */
    private void m2897b() {
        Object[] toArray = this.f2130b.toArray();
        Arrays.sort(toArray, this.f2129a);
        this.f2130b.clear();
        for (Object obj : toArray) {
            this.f2130b.add((GLOverlay) obj);
        }
    }

    /* renamed from: a */
    public void mo9544a(GL10 gl10) {
        Iterator it = this.f2130b.iterator();
        while (it.hasNext()) {
            ((GLOverlay) it.next()).onDrawFrame(gl10);
        }
    }
}
