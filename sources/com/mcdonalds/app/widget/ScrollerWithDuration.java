package com.mcdonalds.app.widget;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import com.ensighten.Ensighten;

public class ScrollerWithDuration extends Scroller {
    private double mScrollFactor = 1.0d;

    public ScrollerWithDuration(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public void setScrollDurationFactor(double scrollFactor) {
        Ensighten.evaluateEvent(this, "setScrollDurationFactor", new Object[]{new Double(scrollFactor)});
        this.mScrollFactor = scrollFactor;
    }

    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        Ensighten.evaluateEvent(this, "startScroll", new Object[]{new Integer(startX), new Integer(startY), new Integer(dx), new Integer(dy), new Integer(duration)});
        super.startScroll(startX, startY, dx, dy, (int) (((double) duration) * this.mScrollFactor));
    }
}
