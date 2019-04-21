package com.mcdonalds.app.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.p000v4.view.PagerAdapter;
import android.support.p000v4.view.ViewPager;
import android.support.p000v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.ensighten.Ensighten;
import com.mcdonalds.gma.hongkong.C2658R;

public class PagerIndicator extends RadioGroup {
    RadioButton[] mIndicators;
    private OnPageChangeListener mOnPageChangeListener = new C38772();

    /* renamed from: com.mcdonalds.app.widget.PagerIndicator$2 */
    class C38772 implements OnPageChangeListener {
        C38772() {
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            Ensighten.evaluateEvent(this, "onPageScrolled", new Object[]{new Integer(position), new Float(positionOffset), new Integer(positionOffsetPixels)});
        }

        public void onPageSelected(int position) {
            Ensighten.evaluateEvent(this, "onPageSelected", new Object[]{new Integer(position)});
            PagerIndicator.this.mIndicators[position].setChecked(true);
        }

        public void onPageScrollStateChanged(int state) {
            Ensighten.evaluateEvent(this, "onPageScrollStateChanged", new Object[]{new Integer(state)});
        }
    }

    static /* synthetic */ void access$000(PagerIndicator x0, ViewPager x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.PagerIndicator", "access$000", new Object[]{x0, x1});
        x0.buildFromPager(x1);
    }

    public PagerIndicator(Context context) {
        super(context);
    }

    public PagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setupWithViewPager(final ViewPager pager) {
        Ensighten.evaluateEvent(this, "setupWithViewPager", new Object[]{pager});
        buildFromPager(pager);
        pager.addOnPageChangeListener(this.mOnPageChangeListener);
        pager.getAdapter().registerDataSetObserver(new DataSetObserver() {
            public void onChanged() {
                Ensighten.evaluateEvent(this, "onChanged", null);
                PagerIndicator.access$000(PagerIndicator.this, pager);
            }
        });
    }

    public void setupOneDotPager(ViewPager pager) {
        Ensighten.evaluateEvent(this, "setupOneDotPager", new Object[]{pager});
        removeAllViews();
        RadioButton indicator = (RadioButton) LayoutInflater.from(getContext()).inflate(C2658R.layout.pager_indicator_dot, this, false);
        addView(indicator);
        indicator.setChecked(true);
    }

    private void buildFromPager(ViewPager pager) {
        Ensighten.evaluateEvent(this, "buildFromPager", new Object[]{pager});
        removeAllViews();
        PagerAdapter adapter = pager.getAdapter();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        int count = adapter.getCount();
        this.mIndicators = new RadioButton[count];
        int selected = pager.getCurrentItem();
        for (int i = 0; i < count; i++) {
            RadioButton indicator = (RadioButton) inflater.inflate(C2658R.layout.pager_indicator_dot, this, false);
            addView(indicator);
            if (i == selected) {
                indicator.setChecked(true);
            }
            this.mIndicators[i] = indicator;
        }
    }
}
