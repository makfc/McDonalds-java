package com.mcdonalds.app.widget;

import android.content.Context;
import android.support.p000v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import com.ensighten.Ensighten;

public class WrapHeightViewPager extends ViewPager {
    public WrapHeightViewPager(Context context) {
        super(context);
    }

    public WrapHeightViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Ensighten.evaluateEvent(this, "onMeasure", new Object[]{new Integer(widthMeasureSpec), new Integer(heightMeasureSpec)});
        if (getChildCount() > 0) {
            View v = getChildAt(0);
            v.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, 0));
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(v.getMeasuredHeight(), 1073741824);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
