package com.amap.api.mapcore.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.autonavi.amap.mapcore.interfaces.GLTextureView;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.IGLSurfaceView;

/* renamed from: com.amap.api.mapcore.util.l */
public class AMapGLTextureView extends GLTextureView implements IGLSurfaceView {
    /* renamed from: a */
    private IAMapDelegate f2072a;

    public AMapGLTextureView(Context context) {
        this(context, null);
    }

    public AMapGLTextureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2072a = null;
        this.f2072a = new AMapDelegateImp(this, context);
    }

    /* renamed from: a */
    public IAMapDelegate mo9512a() {
        return this.f2072a;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return this.f2072a.onTouchEvent(motionEvent);
    }

    public void setZOrderOnTop(boolean z) {
    }

    /* Access modifiers changed, original: protected */
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (i == 8 || i == 4) {
            this.f2072a.onPause();
        } else if (i == 0) {
            this.f2072a.onResume();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onDetachedFromWindow() {
        this.f2072a.onPause();
        super.onDetachedFromWindow();
    }
}
