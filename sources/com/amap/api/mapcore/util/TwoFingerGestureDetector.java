package com.amap.api.mapcore.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/* renamed from: com.amap.api.mapcore.util.bf */
public abstract class TwoFingerGestureDetector extends BaseGestureDetector {
    /* renamed from: h */
    protected float f1329h;
    /* renamed from: i */
    protected float f1330i;
    /* renamed from: j */
    protected float f1331j;
    /* renamed from: k */
    protected float f1332k;
    /* renamed from: l */
    private final float f1333l;
    /* renamed from: m */
    private float f1334m;
    /* renamed from: n */
    private float f1335n;
    /* renamed from: o */
    private float f1336o;
    /* renamed from: p */
    private float f1337p;

    public TwoFingerGestureDetector(Context context) {
        super(context);
        this.f1333l = (float) ViewConfiguration.get(context).getScaledEdgeSlop();
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public void mo8794b(MotionEvent motionEvent) {
        super.mo8794b(motionEvent);
        if (this.f1317c != null) {
            MotionEvent motionEvent2 = this.f1317c;
            int pointerCount = this.f1317c.getPointerCount();
            int pointerCount2 = motionEvent.getPointerCount();
            if (pointerCount2 == 2 && pointerCount2 == pointerCount) {
                this.f1336o = -1.0f;
                this.f1337p = -1.0f;
                float x = motionEvent2.getX(0);
                float y = motionEvent2.getY(0);
                float x2 = motionEvent2.getX(1);
                float y2 = motionEvent2.getY(1) - y;
                this.f1329h = x2 - x;
                this.f1330i = y2;
                y2 = motionEvent.getX(0);
                x = motionEvent.getY(0);
                x = motionEvent.getY(1) - x;
                this.f1331j = motionEvent.getX(1) - y2;
                this.f1332k = x;
            }
        }
    }

    /* renamed from: c */
    public float mo8805c() {
        if (this.f1336o == -1.0f) {
            float f = this.f1331j;
            float f2 = this.f1332k;
            this.f1336o = (float) Math.sqrt((double) ((f * f) + (f2 * f2)));
        }
        return this.f1336o;
    }

    /* renamed from: a */
    protected static float m1752a(MotionEvent motionEvent, int i) {
        float rawX = motionEvent.getRawX() - motionEvent.getX();
        if (i < motionEvent.getPointerCount()) {
            return rawX + motionEvent.getX(i);
        }
        return 0.0f;
    }

    /* renamed from: b */
    protected static float m1753b(MotionEvent motionEvent, int i) {
        float rawY = motionEvent.getRawY() - motionEvent.getY();
        if (i < motionEvent.getPointerCount()) {
            return rawY + motionEvent.getY(i);
        }
        return 0.0f;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: d */
    public boolean mo8806d(MotionEvent motionEvent) {
        DisplayMetrics displayMetrics = this.f1315a.getResources().getDisplayMetrics();
        this.f1334m = ((float) displayMetrics.widthPixels) - this.f1333l;
        this.f1335n = ((float) displayMetrics.heightPixels) - this.f1333l;
        float f = this.f1333l;
        float f2 = this.f1334m;
        float f3 = this.f1335n;
        float rawX = motionEvent.getRawX();
        float rawY = motionEvent.getRawY();
        float a = TwoFingerGestureDetector.m1752a(motionEvent, 1);
        float b = TwoFingerGestureDetector.m1753b(motionEvent, 1);
        boolean z = rawX < f || rawY < f || rawX > f2 || rawY > f3;
        boolean z2;
        if (a < f || b < f || a > f2 || b > f3) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z || z2) {
            return true;
        }
        return false;
    }
}
