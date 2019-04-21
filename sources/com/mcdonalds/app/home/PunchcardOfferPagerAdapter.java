package com.mcdonalds.app.home;

import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentStatePagerAdapter;
import com.ensighten.Ensighten;
import com.mcdonalds.app.offers.PunchcardPageFragment;
import com.mcdonalds.sdk.modules.models.Offer;

public class PunchcardOfferPagerAdapter extends FragmentStatePagerAdapter {
    private final int mCurrentPunch;
    private final Offer mOffer;
    private final int mTotalPunch;

    public PunchcardOfferPagerAdapter(int currentPunch, int totalPunch, FragmentManager supportFragmentManager, Offer offer) {
        super(supportFragmentManager);
        this.mCurrentPunch = currentPunch;
        this.mTotalPunch = totalPunch;
        this.mOffer = offer;
    }

    public Fragment getItem(int position) {
        Ensighten.evaluateEvent(this, "getItem", new Object[]{new Integer(position)});
        return PunchcardPageFragment.newInstance(Math.max(this.mCurrentPunch - (position * 10), 0), Math.min(this.mTotalPunch - (position * 10), 10), position, this.mOffer.getName());
    }

    public int getCount() {
        Ensighten.evaluateEvent(this, "getCount", null);
        int count = 0;
        if (this.mTotalPunch % 10 > 0) {
            count = 0 + 1;
        }
        return count + (this.mTotalPunch / 10);
    }
}
