package com.mcdonalds.app.widget;

import android.content.Context;
import android.support.p000v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.ensighten.Ensighten;
import java.util.Timer;
import java.util.TimerTask;

public class HeaderViewPager extends ViewPagerWithDuration {
    private boolean autoScrolling = false;
    private boolean mAutoScroll;
    private float mCurrX = 0.0f;
    private Timer mHeaderAutoScrollTimer;
    private float mStartX;
    private float mStartY;
    private int mTouchSlop;

    private static class HeaderAutoScrollTask extends TimerTask {
        HeaderViewPager mHeaderViewPager;

        /* renamed from: com.mcdonalds.app.widget.HeaderViewPager$HeaderAutoScrollTask$1 */
        class C38751 implements Runnable {
            C38751() {
            }

            public void run() {
                Ensighten.evaluateEvent(this, "run", null);
                if (HeaderAutoScrollTask.this.mHeaderViewPager.getAdapter() != null) {
                    int[] location = new int[2];
                    HeaderAutoScrollTask.this.mHeaderViewPager.getLocationOnScreen(location);
                    if (location[1] > 0) {
                        HeaderAutoScrollTask.this.mHeaderViewPager.setScrollDurationFactor(5.0d);
                        HeaderAutoScrollTask.this.mHeaderViewPager.setCurrentItem(HeaderAutoScrollTask.this.mHeaderViewPager.getCurrentItem() == HeaderAutoScrollTask.this.mHeaderViewPager.getAdapter().getCount() + -1 ? 0 : HeaderAutoScrollTask.this.mHeaderViewPager.getCurrentItem() + 1);
                        HeaderAutoScrollTask.this.mHeaderViewPager.setScrollDurationFactor(1.0d);
                    }
                }
            }
        }

        public HeaderAutoScrollTask(HeaderViewPager pager) {
            this.mHeaderViewPager = pager;
        }

        public void run() {
            Ensighten.evaluateEvent(this, "run", null);
            this.mHeaderViewPager.post(new C38751());
        }
    }

    public HeaderViewPager(Context context) {
        super(context);
        init(context);
    }

    public HeaderViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void setCurrentItem(int item) {
        Ensighten.evaluateEvent(this, "setCurrentItem", new Object[]{new Integer(item)});
        if (getAdapter().getCount() != 0) {
            super.setCurrentItem(getOffsetAmount() + (item % getAdapter().getCount()));
        }
    }

    public void setAdapter(PagerAdapter adapter) {
        Ensighten.evaluateEvent(this, "setAdapter", new Object[]{adapter});
        try {
            super.setAdapter(adapter);
        } catch (IllegalStateException e) {
        }
        setCurrentItem(0);
    }

    private void init(Context context) {
        Ensighten.evaluateEvent(this, "init", new Object[]{context});
        this.mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    public void setAutoScroll(boolean value, boolean isFirstTime) {
        Ensighten.evaluateEvent(this, "setAutoScroll", new Object[]{new Boolean(value), new Boolean(isFirstTime)});
        this.mAutoScroll = value;
        if (value) {
            scheduleAutoScrolling(isFirstTime);
        } else {
            unscheduleAutoScrolling();
        }
    }

    private void startUserInteraction() {
        Ensighten.evaluateEvent(this, "startUserInteraction", null);
        unscheduleAutoScrolling();
    }

    private void endUserInteraction() {
        Ensighten.evaluateEvent(this, "endUserInteraction", null);
        if (this.mAutoScroll) {
            scheduleAutoScrolling(true);
        }
    }

    public void onResume() {
        Ensighten.evaluateEvent(this, "onResume", null);
        endUserInteraction();
    }

    public void onPause() {
        Ensighten.evaluateEvent(this, "onPause", null);
        startUserInteraction();
    }

    /* Access modifiers changed, original: protected */
    public void onAttachedToWindow() {
        Ensighten.evaluateEvent(this, "onAttachedToWindow", null);
        super.onAttachedToWindow();
        if (this.mAutoScroll) {
            scheduleAutoScrolling(false);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onDetachedFromWindow() {
        Ensighten.evaluateEvent(this, "onDetachedFromWindow", null);
        super.onDetachedFromWindow();
        unscheduleAutoScrolling();
    }

    private void scheduleAutoScrolling(boolean delayFirst) {
        Ensighten.evaluateEvent(this, "scheduleAutoScrolling", new Object[]{new Boolean(delayFirst)});
        if (!this.autoScrolling) {
            this.mHeaderAutoScrollTimer = new Timer();
            this.mHeaderAutoScrollTimer.scheduleAtFixedRate(new HeaderAutoScrollTask(this), delayFirst ? 7500 : 5000, 5000);
            this.autoScrolling = true;
        }
    }

    private void unscheduleAutoScrolling() {
        Ensighten.evaluateEvent(this, "unscheduleAutoScrolling", null);
        if (this.mHeaderAutoScrollTimer != null) {
            this.mHeaderAutoScrollTimer.cancel();
            this.mHeaderAutoScrollTimer.purge();
        }
        this.autoScrolling = false;
    }

    public boolean onInterceptTouchEvent(MotionEvent event) {
        Ensighten.evaluateEvent(this, "onInterceptTouchEvent", new Object[]{event});
        try {
            int action = event.getAction();
            if (action == 0) {
                this.mCurrX = event.getX();
                this.mStartX = event.getX();
                this.mStartY = event.getY();
                startUserInteraction();
            } else if (Math.abs(event.getY() - this.mStartY) > Math.abs(event.getX() - this.mStartX)) {
                endUserInteraction();
            } else if (action != 2) {
                if (action == 1 || action == 3) {
                    endUserInteraction();
                } else {
                    startUserInteraction();
                }
            }
            return super.onInterceptTouchEvent(event);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        Ensighten.evaluateEvent(this, "onTouchEvent", new Object[]{event});
        if (event.getAction() != 2) {
            endUserInteraction();
        }
        return super.onTouchEvent(event);
    }

    private int getOffsetAmount() {
        Ensighten.evaluateEvent(this, "getOffsetAmount", null);
        return 0;
    }
}
