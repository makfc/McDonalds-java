package com.mcdonalds.app.widget;

import android.content.Context;
import android.support.p000v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.ensighten.Ensighten;

public class ScrollToggleViewPager extends ViewPager {
    private boolean isPagingEnabled = true;

    public ScrollToggleViewPager(Context context) {
        super(context);
    }

    public ScrollToggleViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean onTouchEvent(MotionEvent event) {
        Ensighten.evaluateEvent(this, "onTouchEvent", new Object[]{event});
        return this.isPagingEnabled && super.onTouchEvent(event);
    }

    public boolean onInterceptTouchEvent(MotionEvent event) {
        Ensighten.evaluateEvent(this, "onInterceptTouchEvent", new Object[]{event});
        return this.isPagingEnabled && super.onInterceptTouchEvent(event);
    }

    public void setPagingEnabled(boolean b) {
        Ensighten.evaluateEvent(this, "setPagingEnabled", new Object[]{new Boolean(b)});
        this.isPagingEnabled = b;
    }
}
