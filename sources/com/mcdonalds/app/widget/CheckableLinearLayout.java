package com.mcdonalds.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import android.widget.LinearLayout;
import com.ensighten.Ensighten;

public class CheckableLinearLayout extends LinearLayout implements Checkable {
    private static final int[] CHECKED_STATE_SET = new int[]{16842912};
    private boolean mChecked;
    private OnCheckedChangeListener mOnCheckedChangeListener;

    public interface OnCheckedChangeListener {
        void onCheckedChanged(CheckableLinearLayout checkableLinearLayout, boolean z);
    }

    public CheckableLinearLayout(Context context) {
        super(context, null);
    }

    public CheckableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckableLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        Ensighten.evaluateEvent(this, "setOnCheckedChangeListener", new Object[]{onCheckedChangeListener});
        this.mOnCheckedChangeListener = onCheckedChangeListener;
    }

    public void setChecked(boolean checked) {
        Ensighten.evaluateEvent(this, "setChecked", new Object[]{new Boolean(checked)});
        this.mChecked = checked;
        refreshDrawableState();
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child instanceof Checkable) {
                ((Checkable) child).setChecked(this.mChecked);
            }
        }
        if (this.mOnCheckedChangeListener != null) {
            this.mOnCheckedChangeListener.onCheckedChanged(this, this.mChecked);
        }
    }

    public boolean isChecked() {
        Ensighten.evaluateEvent(this, "isChecked", null);
        return this.mChecked;
    }

    public void toggle() {
        Ensighten.evaluateEvent(this, "toggle", null);
        setChecked(!this.mChecked);
    }

    /* Access modifiers changed, original: protected */
    public int[] onCreateDrawableState(int extraSpace) {
        Ensighten.evaluateEvent(this, "onCreateDrawableState", new Object[]{new Integer(extraSpace)});
        int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }
}
