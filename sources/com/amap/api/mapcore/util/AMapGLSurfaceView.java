package com.amap.api.mapcore.util;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.IGLSurfaceView;

/* renamed from: com.amap.api.mapcore.util.k */
public class AMapGLSurfaceView extends GLSurfaceView implements IGLSurfaceView {
    /* renamed from: a */
    private IAMapDelegate f2024a;

    public AMapGLSurfaceView(Context context) {
        this(context, null);
    }

    public AMapGLSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2024a = null;
        this.f2024a = new AMapDelegateImp(this, context);
    }

    /* renamed from: a */
    public IAMapDelegate mo9447a() {
        return this.f2024a;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return this.f2024a.onTouchEvent(motionEvent);
    }

    /* Access modifiers changed, original: protected */
    public void onWindowVisibilityChanged(int i) {
        if (i == 8 || i == 4) {
            this.f2024a.onPause();
        } else if (i == 0) {
            this.f2024a.onResume();
        }
        super.onWindowVisibilityChanged(i);
    }

    /* Access modifiers changed, original: protected */
    public void onDetachedFromWindow() {
        this.f2024a.onPause();
        super.onDetachedFromWindow();
    }
}
