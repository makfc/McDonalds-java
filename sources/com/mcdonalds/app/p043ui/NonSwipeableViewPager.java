package com.mcdonalds.app.p043ui;

import android.content.Context;
import android.support.p000v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.ensighten.Ensighten;

/* renamed from: com.mcdonalds.app.ui.NonSwipeableViewPager */
public class NonSwipeableViewPager extends ViewPager {
    public NonSwipeableViewPager(Context context) {
        super(context);
    }

    public NonSwipeableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Ensighten.evaluateEvent(this, "onInterceptTouchEvent", new Object[]{ev});
        return false;
    }

    public boolean onTouchEvent(MotionEvent ev) {
        Ensighten.evaluateEvent(this, "onTouchEvent", new Object[]{ev});
        return false;
    }
}
