package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.LinearInterpolator;

/* renamed from: com.amap.api.mapcore.util.o */
class BlankView extends View {
    /* renamed from: a */
    private Paint f2094a = new Paint();

    public BlankView(Context context) {
        super(context);
        this.f2094a.setAntiAlias(true);
        this.f2094a.setColor(Color.argb(255, 235, 235, 235));
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        canvas.drawRect(0.0f, 0.0f, (float) getWidth(), (float) getHeight(), this.f2094a);
    }

    /* renamed from: a */
    public void mo9523a(boolean z) {
        if (z) {
            setVisibility(0);
            return;
        }
        setVisibility(8);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setInterpolator(new LinearInterpolator());
        alphaAnimation.setDuration(200);
        clearAnimation();
        startAnimation(alphaAnimation);
    }
}
