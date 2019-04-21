package com.mcdonalds.app.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Checkable;
import android.widget.RelativeLayout;
import com.ensighten.Ensighten;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;

public class CheckableRelativeLayout extends RelativeLayout implements Checkable {
    private static final int[] CHECKED_STATE_SET = new int[]{16842912};
    private boolean mChecked;
    private OnCheckedChangeListener mOnCheckedChangeListener;

    public interface OnCheckedChangeListener {
        void onCheckedChanged(CheckableRelativeLayout checkableRelativeLayout, boolean z);
    }

    public CheckableRelativeLayout(Context context) {
        super(context, null);
    }

    public CheckableRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckableRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
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

    public void setOnClickListener(@Nullable OnClickListener l) {
        Ensighten.evaluateEvent(this, "setOnClickListener", new Object[]{l});
        super.setOnClickListener(new DataLayerClickListener(l));
    }
}
