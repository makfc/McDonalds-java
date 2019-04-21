package com.mcdonalds.app.storelocator;

import android.content.Context;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentStatePagerAdapter;
import com.ensighten.Ensighten;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.sdk.modules.storelocator.Store;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StoreLocatorPagerAdapter extends FragmentStatePagerAdapter {
    private final Context mContext;
    private final StoreLocatorDataProvider mDataProvider;
    private final List<StoreLocatorPagerFragment> mFragments = new ArrayList();
    private boolean mIncludesCurrent;
    private final StoreLocatorInteractionListener mInteractionListener;
    private final PagerItemListener mItemListener;
    private List<Store> mStores;

    public StoreLocatorPagerAdapter(Context context, FragmentManager fragmentManager, StoreLocatorDataProvider dataProvider, StoreLocatorInteractionListener interactionListener, PagerItemListener pagerItemListener) {
        super(fragmentManager);
        this.mContext = context;
        this.mDataProvider = dataProvider;
        this.mInteractionListener = interactionListener;
        this.mItemListener = pagerItemListener;
    }

    public List<? extends Store> getStores() {
        Ensighten.evaluateEvent(this, "getStores", null);
        return this.mStores != null ? this.mStores : new ArrayList();
    }

    public int getPosition(Integer storeId) {
        Ensighten.evaluateEvent(this, "getPosition", new Object[]{storeId});
        if (ListUtils.isNotEmpty(this.mStores)) {
            for (int i = 0; i < this.mStores.size(); i++) {
                Store store = (Store) this.mStores.get(i);
                if (storeId != null && store.getStoreId() == storeId.intValue()) {
                    return i;
                }
            }
        }
        return -1;
    }

    public void setCurrentStore(Store currentStore) {
        Ensighten.evaluateEvent(this, "setCurrentStore", new Object[]{currentStore});
        if (this.mStores == null) {
            this.mStores = new ArrayList();
        }
        removeDuplicatedAndNonFavoriteStores(currentStore.getStoreId());
        this.mStores.add(0, currentStore);
        refreshFragments();
        notifyDataSetChanged();
    }

    private void removeDuplicatedAndNonFavoriteStores(int currentStoreId) {
        Ensighten.evaluateEvent(this, "removeDuplicatedAndNonFavoriteStores", new Object[]{new Integer(currentStoreId)});
        Iterator<Store> iterator = this.mStores.iterator();
        while (iterator.hasNext()) {
            Store store = (Store) iterator.next();
            if (store.getStoreId() == currentStoreId) {
                iterator.remove();
            } else if (store.getStoreFavoriteId() == null) {
                iterator.remove();
            }
        }
    }

    public void setStores(List<? extends Store> stores) {
        Ensighten.evaluateEvent(this, "setStores", new Object[]{stores});
        if (stores == null || stores.size() == 0) {
            this.mStores = null;
        } else {
            this.mStores = new ArrayList(stores);
        }
        refreshFragments();
    }

    private void refreshFragments() {
        Ensighten.evaluateEvent(this, "refreshFragments", null);
        this.mFragments.clear();
        if (this.mStores != null) {
            boolean first = true;
            for (Store store : this.mStores) {
                StoreLocatorPagerFragment fragment = new StoreLocatorPagerFragment();
                fragment.setStore(store);
                if (first && this.mIncludesCurrent) {
                    fragment.setSection(StoreLocatorSection.Current);
                    first = false;
                } else {
                    fragment.setSection(StoreLocatorSection.Favorites);
                }
                fragment.setDataProvider(this.mDataProvider);
                fragment.setListener(this.mItemListener);
                this.mFragments.add(fragment);
            }
        }
    }

    public boolean includesCurrent() {
        Ensighten.evaluateEvent(this, "includesCurrent", null);
        return this.mIncludesCurrent;
    }

    public void setIncludesCurrent(boolean includesCurrent) {
        Ensighten.evaluateEvent(this, "setIncludesCurrent", new Object[]{new Boolean(includesCurrent)});
        this.mIncludesCurrent = includesCurrent;
    }

    public Store getStore(int position) {
        Ensighten.evaluateEvent(this, "getStore", new Object[]{new Integer(position)});
        StoreLocatorPagerFragment fragment = (StoreLocatorPagerFragment) this.mFragments.get(position);
        if (fragment != null) {
            return fragment.getStore();
        }
        return null;
    }

    public int getCount() {
        Ensighten.evaluateEvent(this, "getCount", null);
        return this.mFragments.size();
    }

    public StoreLocatorPagerFragment getItem(int position) {
        if (ListUtils.isNotEmpty(this.mFragments)) {
            return (StoreLocatorPagerFragment) this.mFragments.get(position);
        }
        return null;
    }

    public int getItemPosition(Object object) {
        Ensighten.evaluateEvent(this, "getItemPosition", new Object[]{object});
        return -2;
    }

    public float getPageWidth(int position) {
        Ensighten.evaluateEvent(this, "getPageWidth", new Object[]{new Integer(position)});
        if (getCount() > 1) {
            return 0.96f;
        }
        return 1.0f;
    }

    public void clearStoresState(int except) {
        Ensighten.evaluateEvent(this, "clearStoresState", new Object[]{new Integer(except)});
        if (ListUtils.isNotEmpty(this.mFragments)) {
            for (int i = 0; i < this.mFragments.size(); i++) {
                if (i != except) {
                    ((StoreLocatorPagerFragment) this.mFragments.get(i)).setExpanded(false);
                }
            }
        }
    }
}
