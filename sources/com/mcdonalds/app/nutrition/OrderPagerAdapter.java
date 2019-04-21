package com.mcdonalds.app.nutrition;

import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentStatePagerAdapter;
import com.ensighten.Ensighten;
import com.mcdonalds.sdk.modules.models.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderPagerAdapter extends FragmentStatePagerAdapter {
    private List<Order> mOrders = new ArrayList();

    public OrderPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setOrders(List<Order> orders) {
        Ensighten.evaluateEvent(this, "setOrders", new Object[]{orders});
        this.mOrders = new ArrayList(orders);
        notifyDataSetChanged();
    }

    public Fragment getItem(int position) {
        Ensighten.evaluateEvent(this, "getItem", new Object[]{new Integer(position)});
        Order order = (Order) this.mOrders.get(position);
        RecentsOrderFragment fragment = new RecentsOrderFragment();
        fragment.setOrder(order);
        return fragment;
    }

    public int getCount() {
        Ensighten.evaluateEvent(this, "getCount", null);
        int size = this.mOrders.size();
        return size > 3 ? 3 : size;
    }

    public int getItemPosition(Object object) {
        Ensighten.evaluateEvent(this, "getItemPosition", new Object[]{object});
        return -2;
    }
}
