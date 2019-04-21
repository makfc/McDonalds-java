package android.support.p001v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.annotation.RestrictTo;
import android.support.p000v4.content.res.ConfigurationHelper;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.widget.ExploreByTouchHelper;
import android.support.p001v7.appcompat.C0334R;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

@RestrictTo
/* renamed from: android.support.v7.widget.ButtonBarLayout */
public class ButtonBarLayout extends LinearLayout {
    private boolean mAllowStacking;
    private int mLastWidthSize = -1;

    public ButtonBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        boolean allowStackingDefault = ConfigurationHelper.getScreenHeightDp(getResources()) >= 320;
        TypedArray ta = context.obtainStyledAttributes(attrs, C0334R.styleable.ButtonBarLayout);
        this.mAllowStacking = ta.getBoolean(C0334R.styleable.ButtonBarLayout_allowStacking, allowStackingDefault);
        ta.recycle();
    }

    public void setAllowStacking(boolean allowStacking) {
        if (this.mAllowStacking != allowStacking) {
            this.mAllowStacking = allowStacking;
            if (!this.mAllowStacking && getOrientation() == 1) {
                setStacked(false);
            }
            requestLayout();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int initialWidthMeasureSpec;
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if (this.mAllowStacking) {
            if (widthSize > this.mLastWidthSize && isStacked()) {
                setStacked(false);
            }
            this.mLastWidthSize = widthSize;
        }
        boolean needsRemeasure = false;
        if (isStacked() || MeasureSpec.getMode(widthMeasureSpec) != 1073741824) {
            initialWidthMeasureSpec = widthMeasureSpec;
        } else {
            initialWidthMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize, ExploreByTouchHelper.INVALID_ID);
            needsRemeasure = true;
        }
        super.onMeasure(initialWidthMeasureSpec, heightMeasureSpec);
        if (this.mAllowStacking && !isStacked()) {
            boolean stack;
            if (VERSION.SDK_INT >= 11) {
                stack = (ViewCompat.getMeasuredWidthAndState(this) & ViewCompat.MEASURED_STATE_MASK) == 16777216;
            } else {
                int childWidthTotal = 0;
                for (int i = 0; i < getChildCount(); i++) {
                    childWidthTotal += getChildAt(i).getMeasuredWidth();
                }
                stack = (getPaddingLeft() + childWidthTotal) + getPaddingRight() > widthSize;
            }
            if (stack) {
                setStacked(true);
                needsRemeasure = true;
            }
        }
        if (needsRemeasure) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
        int minHeight = 0;
        int firstVisible = getNextVisibleChildIndex(0);
        if (firstVisible >= 0) {
            View firstButton = getChildAt(firstVisible);
            LayoutParams firstParams = (LayoutParams) firstButton.getLayoutParams();
            minHeight = 0 + (((getPaddingTop() + firstButton.getMeasuredHeight()) + firstParams.topMargin) + firstParams.bottomMargin);
            if (isStacked()) {
                int secondVisible = getNextVisibleChildIndex(firstVisible + 1);
                if (secondVisible >= 0) {
                    minHeight = (int) (((float) minHeight) + (((float) getChildAt(secondVisible).getPaddingTop()) + (16.0f * getResources().getDisplayMetrics().density)));
                }
            } else {
                minHeight += getPaddingBottom();
            }
        }
        if (ViewCompat.getMinimumHeight(this) != minHeight) {
            setMinimumHeight(minHeight);
        }
    }

    private int getNextVisibleChildIndex(int index) {
        int count = getChildCount();
        for (int i = index; i < count; i++) {
            if (getChildAt(i).getVisibility() == 0) {
                return i;
            }
        }
        return -1;
    }

    private void setStacked(boolean stacked) {
        setOrientation(stacked ? 1 : 0);
        setGravity(stacked ? 5 : 80);
        View spacer = findViewById(C0334R.C0333id.spacer);
        if (spacer != null) {
            spacer.setVisibility(stacked ? 8 : 4);
        }
        for (int i = getChildCount() - 2; i >= 0; i--) {
            bringChildToFront(getChildAt(i));
        }
    }

    private boolean isStacked() {
        return getOrientation() == 1;
    }
}
