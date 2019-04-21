package com.amap.api.mapcore.util;

import android.content.Context;
import android.view.MotionEvent;

/* renamed from: com.amap.api.mapcore.util.be */
public class RotateGestureDetector extends TwoFingerGestureDetector {
    /* renamed from: l */
    private final C0755a f1338l;
    /* renamed from: m */
    private boolean f1339m;

    /* compiled from: RotateGestureDetector */
    /* renamed from: com.amap.api.mapcore.util.be$a */
    public interface C0755a {
        /* renamed from: a */
        boolean mo8802a(RotateGestureDetector rotateGestureDetector);

        /* renamed from: b */
        boolean mo8803b(RotateGestureDetector rotateGestureDetector);

        /* renamed from: c */
        void mo8804c(RotateGestureDetector rotateGestureDetector);
    }

    public RotateGestureDetector(Context context, C0755a c0755a) {
        super(context);
        this.f1338l = c0755a;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo8791a(int i, MotionEvent motionEvent) {
        switch (i) {
            case 2:
                if (this.f1339m) {
                    this.f1339m = mo8806d(motionEvent);
                    if (!this.f1339m) {
                        this.f1316b = this.f1338l.mo8803b(this);
                        return;
                    }
                    return;
                }
                return;
            case 5:
                mo8790a();
                this.f1317c = MotionEvent.obtain(motionEvent);
                this.f1321g = 0;
                mo8794b(motionEvent);
                this.f1339m = mo8806d(motionEvent);
                if (!this.f1339m) {
                    this.f1316b = this.f1338l.mo8803b(this);
                    return;
                }
                return;
            case 6:
                if (!this.f1339m) {
                }
                return;
            default:
                return;
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public void mo8793b(int i, MotionEvent motionEvent) {
        switch (i) {
            case 2:
                mo8794b(motionEvent);
                if (this.f1319e / this.f1320f > 0.67f && this.f1338l.mo8802a(this) && this.f1317c != null) {
                    this.f1317c.recycle();
                    this.f1317c = MotionEvent.obtain(motionEvent);
                    return;
                }
                return;
            case 3:
                if (!this.f1339m) {
                    this.f1338l.mo8804c(this);
                }
                mo8790a();
                return;
            case 6:
                mo8794b(motionEvent);
                if (!this.f1339m) {
                    this.f1338l.mo8804c(this);
                }
                mo8790a();
                return;
            default:
                return;
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo8790a() {
        super.mo8790a();
        this.f1339m = false;
    }

    /* renamed from: b */
    public float mo8807b() {
        return (float) (((Math.atan2((double) this.f1330i, (double) this.f1329h) - Math.atan2((double) this.f1332k, (double) this.f1331j)) * 180.0d) / 3.141592653589793d);
    }
}
