package com.mcdonalds.app.storelocator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;
import com.ensighten.Ensighten;

public class ScrollDisabledListView extends ListView {
    private int mPosition;

    public ScrollDisabledListView(Context context) {
        super(context);
    }

    public ScrollDisabledListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollDisabledListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void forceLayout() {
        Ensighten.evaluateEvent(this, "forceLayout", null);
        layoutChildren();
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        Ensighten.evaluateEvent(this, "dispatchTouchEvent", new Object[]{ev});
        int actionMasked = ev.getActionMasked() & 255;
        if (actionMasked == 0) {
            this.mPosition = pointToPosition((int) ev.getX(), (int) ev.getY());
            return super.dispatchTouchEvent(ev);
        } else if (actionMasked == 2) {
            return true;
        } else {
            if (actionMasked == 1) {
                if (pointToPosition((int) ev.getX(), (int) ev.getY()) == this.mPosition) {
                    super.dispatchTouchEvent(ev);
                } else {
                    setPressed(false);
                    invalidate();
                    return true;
                }
            }
            return super.dispatchTouchEvent(ev);
        }
    }
}
