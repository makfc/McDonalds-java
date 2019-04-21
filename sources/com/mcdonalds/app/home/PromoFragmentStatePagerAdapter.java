package com.mcdonalds.app.home;

import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentStatePagerAdapter;
import com.ensighten.Ensighten;
import com.mcdonalds.app.model.Promo;
import com.mcdonalds.app.util.ImageViewFragment;
import com.mcdonalds.app.util.ImageViewFragment.OnClickListener;
import java.util.ArrayList;
import java.util.List;

public class PromoFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    private final ImageViewFragment[] imageViewFragmentCache;
    private final OnClickListener mOnClickListener;
    private final List<Promo> mPromoList;

    public PromoFragmentStatePagerAdapter(List<Promo> promoList, FragmentManager supportFragmentManager, OnClickListener onClickListener) {
        super(supportFragmentManager);
        this.mPromoList = new ArrayList(promoList.size());
        this.mPromoList.addAll(promoList);
        this.imageViewFragmentCache = new ImageViewFragment[promoList.size()];
        this.mOnClickListener = onClickListener;
    }

    public Fragment getItem(int position) {
        Ensighten.evaluateEvent(this, "getItem", new Object[]{new Integer(position)});
        Promo promo = (Promo) this.mPromoList.get(position);
        if (this.imageViewFragmentCache[position] == null) {
            this.imageViewFragmentCache[position] = ImageViewFragment.newInstance(promo);
            this.imageViewFragmentCache[position].refreshImage(true);
            if (this.mOnClickListener != null) {
                this.imageViewFragmentCache[position].setOnClickListener(this.mOnClickListener);
            }
        }
        return this.imageViewFragmentCache[position];
    }

    public int getCount() {
        Ensighten.evaluateEvent(this, "getCount", null);
        return this.mPromoList.size();
    }
}
