package com.amap.api.mapcore.util;

import android.content.Context;
import android.support.p000v4.view.MotionEventCompat;
import android.view.MotionEvent;

/* renamed from: com.amap.api.mapcore.util.bc */
public abstract class BaseGestureDetector {
    /* renamed from: a */
    protected final Context f1315a;
    /* renamed from: b */
    protected boolean f1316b;
    /* renamed from: c */
    protected MotionEvent f1317c;
    /* renamed from: d */
    protected MotionEvent f1318d;
    /* renamed from: e */
    protected float f1319e;
    /* renamed from: f */
    protected float f1320f;
    /* renamed from: g */
    protected long f1321g;

    /* renamed from: a */
    public abstract void mo8791a(int i, MotionEvent motionEvent);

    /* renamed from: b */
    public abstract void mo8793b(int i, MotionEvent motionEvent);

    public BaseGestureDetector(Context context) {
        this.f1315a = context;
    }

    /* renamed from: a */
    public boolean mo8792a(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (this.f1316b) {
            mo8793b(action, motionEvent);
        } else {
            mo8791a(action, motionEvent);
        }
        return true;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public void mo8794b(MotionEvent motionEvent) {
        if (this.f1317c != null) {
            MotionEvent motionEvent2 = this.f1317c;
            if (this.f1318d != null) {
                this.f1318d.recycle();
                this.f1318d = null;
            }
            this.f1318d = MotionEvent.obtain(motionEvent);
            this.f1321g = motionEvent.getEventTime() - motionEvent2.getEventTime();
            this.f1319e = motionEvent.getPressure(mo8795c(motionEvent));
            this.f1320f = motionEvent2.getPressure(mo8795c(motionEvent2));
        }
    }

    /* renamed from: c */
    public final int mo8795c(MotionEvent motionEvent) {
        return (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo8790a() {
        if (this.f1317c != null) {
            this.f1317c.recycle();
            this.f1317c = null;
        }
        if (this.f1318d != null) {
            this.f1318d.recycle();
            this.f1318d = null;
        }
        this.f1316b = false;
    }
}
