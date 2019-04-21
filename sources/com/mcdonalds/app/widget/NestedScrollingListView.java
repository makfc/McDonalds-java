package com.mcdonalds.app.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.p000v4.view.NestedScrollingChild;
import android.support.p000v4.view.NestedScrollingChildHelper;
import android.util.AttributeSet;
import android.widget.ListView;
import com.ensighten.Ensighten;

public class NestedScrollingListView extends ListView implements NestedScrollingChild {
    private NestedScrollingChildHelper mNestedScrollingChildHelper;

    public NestedScrollingListView(Context context) {
        super(context);
        initHelper();
    }

    public NestedScrollingListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHelper();
    }

    public NestedScrollingListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initHelper();
    }

    @TargetApi(21)
    public NestedScrollingListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initHelper();
    }

    private void initHelper() {
        Ensighten.evaluateEvent(this, "initHelper", null);
        this.mNestedScrollingChildHelper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
    }

    public void setNestedScrollingEnabled(boolean enabled) {
        Ensighten.evaluateEvent(this, "setNestedScrollingEnabled", new Object[]{new Boolean(enabled)});
        this.mNestedScrollingChildHelper.setNestedScrollingEnabled(enabled);
    }

    public boolean isNestedScrollingEnabled() {
        Ensighten.evaluateEvent(this, "isNestedScrollingEnabled", null);
        return this.mNestedScrollingChildHelper.isNestedScrollingEnabled();
    }

    public boolean startNestedScroll(int axes) {
        Ensighten.evaluateEvent(this, "startNestedScroll", new Object[]{new Integer(axes)});
        return this.mNestedScrollingChildHelper.startNestedScroll(axes);
    }

    public void stopNestedScroll() {
        Ensighten.evaluateEvent(this, "stopNestedScroll", null);
        this.mNestedScrollingChildHelper.stopNestedScroll();
    }

    public boolean hasNestedScrollingParent() {
        Ensighten.evaluateEvent(this, "hasNestedScrollingParent", null);
        return this.mNestedScrollingChildHelper.hasNestedScrollingParent();
    }

    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        Ensighten.evaluateEvent(this, "dispatchNestedScroll", new Object[]{new Integer(dxConsumed), new Integer(dyConsumed), new Integer(dxUnconsumed), new Integer(dyUnconsumed), offsetInWindow});
        return this.mNestedScrollingChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        Ensighten.evaluateEvent(this, "dispatchNestedPreScroll", new Object[]{new Integer(dx), new Integer(dy), consumed, offsetInWindow});
        return this.mNestedScrollingChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        Ensighten.evaluateEvent(this, "dispatchNestedFling", new Object[]{new Float(velocityX), new Float(velocityY), new Boolean(consumed)});
        return this.mNestedScrollingChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        Ensighten.evaluateEvent(this, "dispatchNestedPreFling", new Object[]{new Float(velocityX), new Float(velocityY)});
        return this.mNestedScrollingChildHelper.dispatchNestedPreFling(velocityX, velocityY);
    }
}
