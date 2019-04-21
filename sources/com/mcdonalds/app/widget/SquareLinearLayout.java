package com.mcdonalds.app.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.ensighten.Ensighten;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;

public class SquareLinearLayout extends LinearLayout {
    private AttributeSet mAttrs;
    private Context mContext;

    public SquareLinearLayout(Context context) {
        super(context);
        this.mContext = context;
    }

    public SquareLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.mAttrs = attrs;
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Ensighten.evaluateEvent(this, "onMeasure", new Object[]{new Integer(widthMeasureSpec), new Integer(heightMeasureSpec)});
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int size = (widthMode != 1073741824 || widthSize <= 0) ? (heightMode != 1073741824 || heightSize <= 0) ? widthSize < heightSize ? widthSize : heightSize : heightSize : widthSize;
        int finalMeasureSpec = MeasureSpec.makeMeasureSpec(size, 1073741824);
        super.onMeasure(finalMeasureSpec, finalMeasureSpec);
    }

    /* Access modifiers changed, original: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        Ensighten.evaluateEvent(this, "onSizeChanged", new Object[]{new Integer(w), new Integer(h), new Integer(oldw), new Integer(oldh)});
        super.onSizeChanged(w, h, oldw, oldh);
        requestLayout();
    }

    public void setOnClickListener(@Nullable OnClickListener l) {
        Ensighten.evaluateEvent(this, "setOnClickListener", new Object[]{l});
        super.setOnClickListener(new DataLayerClickListener(l));
    }
}
