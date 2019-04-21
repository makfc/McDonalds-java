package com.mcdonalds.app.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.p000v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.ensighten.Ensighten;

public class ParentSwipingViewPager extends ViewPager {
    private float mCurrX = 0.0f;
    private float mCurrY = 0.0f;
    private boolean mIsDisallowIntercept;
    private float mStartX;
    private float mStartY;
    private int mTouchSlop;

    public ParentSwipingViewPager(Context context) {
        super(context);
        init();
    }

    public ParentSwipingViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @TargetApi(3)
    private void init() {
        Ensighten.evaluateEvent(this, "init", null);
        this.mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    public boolean onInterceptTouchEvent(MotionEvent event) {
        Ensighten.evaluateEvent(this, "onInterceptTouchEvent", new Object[]{event});
        try {
            super.onInterceptTouchEvent(event);
            if (event.getAction() == 0) {
                this.mCurrX = event.getX();
                this.mCurrY = event.getY();
                this.mStartX = event.getX();
                this.mStartY = event.getY();
            }
            return super.onInterceptTouchEvent(event);
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        Ensighten.evaluateEvent(this, "requestDisallowInterceptTouchEvent", new Object[]{new Boolean(disallowIntercept)});
        this.mIsDisallowIntercept = disallowIntercept;
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        Ensighten.evaluateEvent(this, "dispatchTouchEvent", new Object[]{ev});
        if (ev.getPointerCount() <= 1 || !this.mIsDisallowIntercept) {
            return super.dispatchTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }
}
