package com.mcdonalds.app.ordering.methodselection;

import android.content.Context;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.storelocator.Store;
import java.util.ArrayList;
import java.util.List;

public class StorePagerAdapter extends PagerAdapter {
    private OnItemClickListener mOnItemClickListener;
    private OrderMethodSelectionPresenter mPresenter;
    private List<Store> mStores = new ArrayList();

    public interface OnItemClickListener {
        void onInfoButtonClicked(Store store);

        void onSelectButtonClicked(Store store);
    }

    public StorePagerAdapter(OrderMethodSelectionPresenter presenter) {
        this.mPresenter = presenter;
    }

    public void setStores(List<Store> stores) {
        Ensighten.evaluateEvent(this, "setStores", new Object[]{stores});
        this.mStores = stores;
        notifyDataSetChanged();
    }

    public List<Store> getStores() {
        Ensighten.evaluateEvent(this, "getStores", null);
        return this.mStores;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        Ensighten.evaluateEvent(this, "setOnItemClickListener", new Object[]{onItemClickListener});
        this.mOnItemClickListener = onItemClickListener;
    }

    public int getCount() {
        Ensighten.evaluateEvent(this, "getCount", null);
        return this.mStores.size();
    }

    public boolean isViewFromObject(View view, Object object) {
        Ensighten.evaluateEvent(this, "isViewFromObject", new Object[]{view, object});
        return view == object;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        Ensighten.evaluateEvent(this, "instantiateItem", new Object[]{container, new Integer(position)});
        Context context = container.getContext();
        View view = LayoutInflater.from(context).inflate(C2658R.layout.view_store_item, container, false);
        final Store store = (Store) this.mStores.get(position);
        TextView address = (TextView) view.findViewById(C2358R.C2357id.label_store_subtitle);
        TextView name = (TextView) view.findViewById(C2358R.C2357id.label_store_title);
        name.setTextColor(getTextColor(context, store));
        if (TextUtils.isEmpty(store.getStoreFavoriteName())) {
            name.setText(store.getPublicName());
        } else {
            name.setText(store.getStoreFavoriteName());
            address.setText(store.getPublicName());
        }
        String storeHoursString = UIUtils.getDailyStoreHoursStringByLocationId(context, store, 2);
        if (storeHoursString == null) {
            storeHoursString = UIUtils.getDailyStoreHoursString(context, store);
        }
        TextView hours = (TextView) view.findViewById(C2358R.C2357id.label_store_hours);
        if (TextUtils.isEmpty(storeHoursString)) {
            hours.setVisibility(8);
        } else {
            hours.setText(storeHoursString);
            hours.setVisibility(0);
        }
        ((TextView) view.findViewById(C2358R.C2357id.label_store_distance)).setText(getDistanceString(context, store));
        View icon = view.findViewById(C2358R.C2357id.container_my_mcdonalds);
        View selectButton = view.findViewById(C2358R.C2357id.button_eat_here);
        if (this.mPresenter.isCurrentStore(store)) {
            icon.setVisibility(0);
            selectButton.setVisibility(4);
        }
        if (this.mOnItemClickListener != null) {
            selectButton.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.StorePagerAdapter", "access$000", new Object[]{StorePagerAdapter.this}).onSelectButtonClicked(store);
                }
            });
            view.findViewById(C2358R.C2357id.info_button).setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.StorePagerAdapter", "access$000", new Object[]{StorePagerAdapter.this}).onInfoButtonClicked(store);
                }
            });
        }
        container.addView(view);
        return view;
    }

    private String getDistanceString(Context context, Store store) {
        Ensighten.evaluateEvent(this, "getDistanceString", new Object[]{context, store});
        String distanceString = UIUtils.distanceFromStore(context, store);
        if (distanceString == null) {
            return context.getString(C2658R.string.label_unknown_distance);
        }
        return distanceString;
    }

    private int getTextColor(Context context, Store store) {
        Ensighten.evaluateEvent(this, "getTextColor", new Object[]{context, store});
        if (store.getStoreFavoriteName() != null) {
            return ContextCompat.getColor(context, C2658R.color.mcd_red);
        }
        return ContextCompat.getColor(context, C2658R.color.mcd_black);
    }

    public int getItemPosition(Object object) {
        Ensighten.evaluateEvent(this, "getItemPosition", new Object[]{object});
        return -2;
    }

    public void destroyItem(ViewGroup container, int position, Object view) {
        Ensighten.evaluateEvent(this, "destroyItem", new Object[]{container, new Integer(position), view});
        container.removeView((View) view);
    }
}
