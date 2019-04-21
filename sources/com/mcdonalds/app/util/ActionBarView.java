package com.mcdonalds.app.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.graphics.drawable.Drawable;
import android.support.p000v4.internal.view.SupportMenu;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;

public class ActionBarView extends View {
    private int mExampleColor = SupportMenu.CATEGORY_MASK;
    private float mExampleDimension = 0.0f;
    private Drawable mExampleDrawable;
    private String mExampleString;
    private float mTextHeight;
    private TextPaint mTextPaint;
    private float mTextWidth;

    public ActionBarView(Context context) {
        super(context);
        init(null, 0);
    }

    public ActionBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ActionBarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        Ensighten.evaluateEvent(this, "init", new Object[]{attrs, new Integer(defStyle)});
        TypedArray a = getContext().obtainStyledAttributes(attrs, C2358R.styleable.ActionBarView, defStyle, 0);
        this.mExampleString = a.getString(0);
        this.mExampleColor = a.getColor(2, this.mExampleColor);
        this.mExampleDimension = a.getDimension(1, this.mExampleDimension);
        if (a.hasValue(3)) {
            this.mExampleDrawable = a.getDrawable(3);
            this.mExampleDrawable.setCallback(this);
        }
        a.recycle();
        this.mTextPaint = new TextPaint();
        this.mTextPaint.setFlags(1);
        this.mTextPaint.setTextAlign(Align.LEFT);
        invalidateTextPaintAndMeasurements();
    }

    private void invalidateTextPaintAndMeasurements() {
        Ensighten.evaluateEvent(this, "invalidateTextPaintAndMeasurements", null);
        this.mTextPaint.setTextSize(this.mExampleDimension);
        this.mTextPaint.setColor(this.mExampleColor);
        this.mTextWidth = this.mTextPaint.measureText(this.mExampleString);
        this.mTextHeight = this.mTextPaint.getFontMetrics().bottom;
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        Ensighten.evaluateEvent(this, "onDraw", new Object[]{canvas});
        super.onDraw(canvas);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int contentWidth = (getWidth() - paddingLeft) - paddingRight;
        int contentHeight = (getHeight() - paddingTop) - getPaddingBottom();
        canvas.drawText(this.mExampleString, ((float) paddingLeft) + ((((float) contentWidth) - this.mTextWidth) / 2.0f), ((float) paddingTop) + ((((float) contentHeight) + this.mTextHeight) / 2.0f), this.mTextPaint);
        if (this.mExampleDrawable != null) {
            this.mExampleDrawable.setBounds(paddingLeft, paddingTop, paddingLeft + contentWidth, paddingTop + contentHeight);
            this.mExampleDrawable.draw(canvas);
        }
    }

    public String getExampleString() {
        Ensighten.evaluateEvent(this, "getExampleString", null);
        return this.mExampleString;
    }

    public void setExampleString(String exampleString) {
        Ensighten.evaluateEvent(this, "setExampleString", new Object[]{exampleString});
        this.mExampleString = exampleString;
        invalidateTextPaintAndMeasurements();
    }

    public int getExampleColor() {
        Ensighten.evaluateEvent(this, "getExampleColor", null);
        return this.mExampleColor;
    }

    public void setExampleColor(int exampleColor) {
        Ensighten.evaluateEvent(this, "setExampleColor", new Object[]{new Integer(exampleColor)});
        this.mExampleColor = exampleColor;
        invalidateTextPaintAndMeasurements();
    }

    public float getExampleDimension() {
        Ensighten.evaluateEvent(this, "getExampleDimension", null);
        return this.mExampleDimension;
    }

    public void setExampleDimension(float exampleDimension) {
        Ensighten.evaluateEvent(this, "setExampleDimension", new Object[]{new Float(exampleDimension)});
        this.mExampleDimension = exampleDimension;
        invalidateTextPaintAndMeasurements();
    }

    public Drawable getExampleDrawable() {
        Ensighten.evaluateEvent(this, "getExampleDrawable", null);
        return this.mExampleDrawable;
    }

    public void setExampleDrawable(Drawable exampleDrawable) {
        Ensighten.evaluateEvent(this, "setExampleDrawable", new Object[]{exampleDrawable});
        this.mExampleDrawable = exampleDrawable;
    }
}
