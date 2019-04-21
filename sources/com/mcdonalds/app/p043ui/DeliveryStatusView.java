package com.mcdonalds.app.p043ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.p000v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.gma.hongkong.C2658R;

/* renamed from: com.mcdonalds.app.ui.DeliveryStatusView */
public class DeliveryStatusView extends LinearLayout {
    private boolean mCancelled;
    private boolean mCompleted;
    private Drawable mCompletedIconDrawable;
    private TextView mCompletionTime;
    private ImageView mIcon;
    private Drawable mIconDrawable;
    private String mLabelText;
    private TextView mPlaceholderSubtitle;
    private String mSubtitleText;
    private String mTimeText;
    private TextView mTitle;

    public DeliveryStatusView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, C2358R.styleable.DeliveryStatusView, 0, 0);
        try {
            this.mIconDrawable = ta.getDrawable(1);
            this.mCompletedIconDrawable = ta.getDrawable(2);
            this.mLabelText = ta.getString(0);
            this.mSubtitleText = ta.getString(3);
            this.mTimeText = ta.getString(4);
            this.mCompleted = ta.getBoolean(5, false);
        } finally {
            ta.recycle();
            init();
        }
    }

    /* Access modifiers changed, original: protected */
    public void dispatchDraw(Canvas canvas) {
        Ensighten.evaluateEvent(this, "dispatchDraw", new Object[]{canvas});
        super.dispatchDraw(canvas);
        draw();
    }

    private void draw() {
        Ensighten.evaluateEvent(this, "draw", null);
        if (this.mLabelText != null) {
            this.mTitle.setText(this.mLabelText);
        }
        if (this.mSubtitleText != null) {
            this.mPlaceholderSubtitle.setText(this.mSubtitleText);
        }
        if (this.mTimeText != null) {
            this.mCompletionTime.setText(this.mTimeText);
        }
        setCompleted();
    }

    private void init() {
        Ensighten.evaluateEvent(this, "init", null);
        DeliveryStatusView.inflate(getContext(), C2658R.layout.delivery_status_item, this);
        this.mIcon = (ImageView) findViewById(C2358R.C2357id.status_image);
        this.mTitle = (TextView) findViewById(C2358R.C2357id.status_title);
        this.mPlaceholderSubtitle = (TextView) findViewById(C2358R.C2357id.placeholder_subtitle);
        this.mCompletionTime = (TextView) findViewById(C2358R.C2357id.completion_time);
        if (isInEditMode()) {
            draw();
        }
    }

    public void setCompleted(boolean completed) {
        Ensighten.evaluateEvent(this, "setCompleted", new Object[]{new Boolean(completed)});
        this.mCompleted = completed;
        refresh();
    }

    public void setCancelled(boolean cancelled) {
        Ensighten.evaluateEvent(this, "setCancelled", new Object[]{new Boolean(cancelled)});
        this.mCancelled = cancelled;
    }

    public boolean isCompleted() {
        Ensighten.evaluateEvent(this, "isCompleted", null);
        return this.mCompleted;
    }

    public boolean isCancelled() {
        Ensighten.evaluateEvent(this, "isCancelled", null);
        return this.mCancelled;
    }

    public void setCompletionTime(String completionTime) {
        Ensighten.evaluateEvent(this, "setCompletionTime", new Object[]{completionTime});
        this.mTimeText = completionTime;
        this.mCompletionTime.setVisibility(0);
        this.mCompletionTime.setTypeface(null, 1);
        refresh();
    }

    private void refresh() {
        Ensighten.evaluateEvent(this, "refresh", null);
        invalidate();
        requestLayout();
    }

    private void setCompleted() {
        int color;
        Drawable icon;
        Ensighten.evaluateEvent(this, "setCompleted", null);
        if (this.mCompleted) {
            color = ContextCompat.getColor(getContext(), C2658R.color.mcdgreenswitch_color);
            icon = this.mCompletedIconDrawable;
            this.mPlaceholderSubtitle.setVisibility(8);
        } else {
            color = ContextCompat.getColor(getContext(), C2658R.color.mcd_light_grey);
            icon = this.mIconDrawable;
            if (this.mPlaceholderSubtitle.getText() == null || this.mPlaceholderSubtitle.getText().toString().isEmpty()) {
                this.mPlaceholderSubtitle.setVisibility(8);
            } else {
                this.mPlaceholderSubtitle.setVisibility(0);
            }
            if (this.mCompletionTime.getText() == null || this.mCompletionTime.getText().toString().isEmpty()) {
                this.mCompletionTime.setVisibility(8);
            } else {
                this.mCompletionTime.setVisibility(0);
                this.mCompletionTime.setTypeface(null, 0);
            }
        }
        if (isCancelled()) {
            color = ContextCompat.getColor(getContext(), C2658R.color.mcd_red);
        }
        if (icon != null) {
            this.mIcon.setImageDrawable(icon);
        }
        this.mTitle.setTextColor(color);
        this.mCompletionTime.setTextColor(color);
    }
}
