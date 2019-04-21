package com.mcdonalds.app.widget;

import android.content.Context;
import android.support.p000v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.animation.Interpolator;
import com.ensighten.Ensighten;
import java.lang.reflect.Field;

public class ViewPagerWithDuration extends ParentSwipingViewPager {
    private ScrollerWithDuration mScroller = null;

    public ViewPagerWithDuration(Context context) {
        super(context);
        postInitViewPager();
    }

    public ViewPagerWithDuration(Context context, AttributeSet attrs) {
        super(context, attrs);
        postInitViewPager();
    }

    private void postInitViewPager() {
        Ensighten.evaluateEvent(this, "postInitViewPager", null);
        try {
            Field scroller = ViewPager.class.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            Field interpolator = ViewPager.class.getDeclaredField("sInterpolator");
            interpolator.setAccessible(true);
            this.mScroller = new ScrollerWithDuration(getContext(), (Interpolator) interpolator.get(null));
            scroller.set(this, this.mScroller);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        }
    }

    public void setScrollDurationFactor(double scrollFactor) {
        Ensighten.evaluateEvent(this, "setScrollDurationFactor", new Object[]{new Double(scrollFactor)});
        this.mScroller.setScrollDurationFactor(scrollFactor);
    }
}
