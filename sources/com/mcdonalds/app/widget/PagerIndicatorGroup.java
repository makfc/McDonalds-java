package com.mcdonalds.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.ensighten.Ensighten;

public class PagerIndicatorGroup extends RadioGroup {
    private final Context mContext;

    public PagerIndicatorGroup(Context context) {
        super(context);
        this.mContext = context;
    }

    public PagerIndicatorGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    private int getResourceId(int index) {
        Ensighten.evaluateEvent(this, "getResourceId", new Object[]{new Integer(index)});
        return getResources().getIdentifier("page_indicator_" + index, "id", this.mContext.getPackageName());
    }

    public void select(int index) {
        Ensighten.evaluateEvent(this, "select", new Object[]{new Integer(index)});
        check(getResourceId(index));
    }

    private void hideAll() {
        Ensighten.evaluateEvent(this, "hideAll", null);
        for (int i = 0; i < getChildCount(); i++) {
            RadioButton radio = (RadioButton) findViewById(getResourceId(i));
            if (radio != null) {
                radio.setVisibility(8);
            }
        }
    }

    public void setCount(int count) {
        Ensighten.evaluateEvent(this, "setCount", new Object[]{new Integer(count)});
        hideAll();
        setVisibility(0);
        for (int i = 0; i < count; i++) {
            RadioButton radio = (RadioButton) findViewById(getResourceId(i));
            if (radio != null) {
                radio.setVisibility(0);
            }
        }
    }
}
