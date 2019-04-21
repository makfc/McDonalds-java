package com.mcdonalds.app.util;

import android.support.p000v4.view.ViewPager.OnPageChangeListener;
import com.ensighten.Ensighten;

public abstract class OnPageSelectListener implements OnPageChangeListener {
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Ensighten.evaluateEvent(this, "onPageScrolled", new Object[]{new Integer(position), new Float(positionOffset), new Integer(positionOffsetPixels)});
    }

    public void onPageScrollStateChanged(int state) {
        Ensighten.evaluateEvent(this, "onPageScrollStateChanged", new Object[]{new Integer(state)});
    }
}
