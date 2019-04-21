package com.mcdonalds.app.ordering.menu;

import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentPagerAdapter;
import com.ensighten.Ensighten;
import java.util.ArrayList;
import java.util.List;

public class MenuPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments = new ArrayList();

    public MenuPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment) {
        Ensighten.evaluateEvent(this, "addFragment", new Object[]{fragment});
        this.mFragments.add(fragment);
    }

    public Fragment getItem(int position) {
        Ensighten.evaluateEvent(this, "getItem", new Object[]{new Integer(position)});
        return (Fragment) this.mFragments.get(position);
    }

    public int getCount() {
        Ensighten.evaluateEvent(this, "getCount", null);
        return this.mFragments.size();
    }
}
