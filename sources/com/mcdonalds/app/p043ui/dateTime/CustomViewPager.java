package com.mcdonalds.app.p043ui.dateTime;

import android.content.Context;
import android.support.p000v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.widget.DatePicker;
import android.widget.TimePicker;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;

/* renamed from: com.mcdonalds.app.ui.dateTime.CustomViewPager */
public class CustomViewPager extends ViewPager {
    private DatePicker mDatePicker;
    private TimePicker mTimePicker;
    private float mTouchSlop;
    /* renamed from: x1 */
    private float f6669x1;
    /* renamed from: x2 */
    private float f6670x2;
    /* renamed from: y1 */
    private float f6671y1;
    /* renamed from: y2 */
    private float f6672y2;

    public CustomViewPager(Context context) {
        super(context);
        init(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        Ensighten.evaluateEvent(this, "init", new Object[]{context});
        this.mTouchSlop = (float) ViewConfiguration.get(context).getScaledPagingTouchSlop();
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Ensighten.evaluateEvent(this, "onMeasure", new Object[]{new Integer(widthMeasureSpec), new Integer(heightMeasureSpec)});
        int height = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, 0));
            int h = child.getMeasuredHeight();
            if (h > height) {
                height = h;
            }
        }
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(height, 1073741824));
        this.mDatePicker = (DatePicker) findViewById(C2358R.C2357id.datePicker);
        this.mTimePicker = (TimePicker) findViewById(C2358R.C2357id.timePicker);
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        Ensighten.evaluateEvent(this, "dispatchTouchEvent", new Object[]{event});
        switch (event.getAction()) {
            case 0:
                this.f6669x1 = event.getX();
                this.f6671y1 = event.getY();
                break;
            case 2:
                this.f6670x2 = event.getX();
                this.f6672y2 = event.getY();
                if (isScrollingHorizontal(this.f6669x1, this.f6671y1, this.f6670x2, this.f6672y2)) {
                    return super.dispatchTouchEvent(event);
                }
                break;
        }
        switch (getCurrentItem()) {
            case 0:
                if (this.mDatePicker != null) {
                    this.mDatePicker.dispatchTouchEvent(event);
                    break;
                }
                break;
            case 1:
                if (this.mTimePicker != null) {
                    this.mTimePicker.dispatchTouchEvent(event);
                    break;
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private boolean isScrollingHorizontal(float x1, float y1, float x2, float y2) {
        Ensighten.evaluateEvent(this, "isScrollingHorizontal", new Object[]{new Float(x1), new Float(y1), new Float(x2), new Float(y2)});
        float deltaX = x2 - x1;
        float deltaY = y2 - y1;
        if (Math.abs(deltaX) <= this.mTouchSlop || Math.abs(deltaX) <= Math.abs(deltaY)) {
            return false;
        }
        return true;
    }
}
