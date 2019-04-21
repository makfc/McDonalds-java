package com.mcdonalds.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import com.ensighten.Ensighten;

public class ToggleButtonGroupTableLayout extends TableLayout implements OnClickListener {
    private RadioButton mActiveRadioButton;

    public ToggleButtonGroupTableLayout(Context context) {
        super(context);
    }

    public ToggleButtonGroupTableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void onClick(View v) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
        RadioButton rb = (RadioButton) v;
        if (this.mActiveRadioButton != null) {
            this.mActiveRadioButton.setChecked(false);
        }
        rb.setChecked(true);
        this.mActiveRadioButton = rb;
    }

    public void addView(View child, int index, LayoutParams params) {
        Ensighten.evaluateEvent(this, "addView", new Object[]{child, new Integer(index), params});
        super.addView(child, index, params);
        setChildrenOnClickListener((TableRow) child);
    }

    public void addView(View child, LayoutParams params) {
        Ensighten.evaluateEvent(this, "addView", new Object[]{child, params});
        super.addView(child, params);
        setChildrenOnClickListener((TableRow) child);
    }

    private void setChildrenOnClickListener(TableRow tr) {
        Ensighten.evaluateEvent(this, "setChildrenOnClickListener", new Object[]{tr});
        int c = tr.getChildCount();
        for (int i = 0; i < c; i++) {
            View v = tr.getChildAt(i);
            if (v instanceof RadioButton) {
                v.setOnClickListener(this);
            }
        }
    }

    public int getCheckedRadioButtonId() {
        Ensighten.evaluateEvent(this, "getCheckedRadioButtonId", null);
        if (this.mActiveRadioButton != null) {
            return this.mActiveRadioButton.getId();
        }
        return -1;
    }
}
