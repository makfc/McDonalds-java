package com.mcdonalds.app.storelocator;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.app.p043ui.listview.ExpandableListItemAdapter;
import com.mcdonalds.app.p043ui.listview.ExpandableListItemAdapter.ViewHolder;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.log.MCDLog;
import java.util.ArrayList;

public class StoreLocatorPagerListAdapter extends ExpandableListItemAdapter<Store> {
    private StoreLocatorDataProvider mDataProvider;
    private int mExpandRetryCount;
    private boolean mIsOffer;
    private PagerItemListener mListener;
    private boolean mOffersOnlyMode = false;
    private StoreLocatorSection mSection;
    private Store mStore;

    public StoreLocatorPagerListAdapter(Context context) {
        super(context, C2658R.layout.fragment_storelocator_expandable_list_item, C2358R.C2357id.expandablelistitem_card_title, C2358R.C2357id.expandablelistitem_card_content, new ArrayList());
    }

    public void setDataProvider(StoreLocatorDataProvider dataProvider) {
        Ensighten.evaluateEvent(this, "setDataProvider", new Object[]{dataProvider});
        this.mDataProvider = dataProvider;
        this.mIsOffer = this.mDataProvider instanceof OffersStoreLocatorController;
    }

    public void setListener(PagerItemListener listener) {
        Ensighten.evaluateEvent(this, "setListener", new Object[]{listener});
        this.mListener = listener;
    }

    public void setStore(Store store) {
        Ensighten.evaluateEvent(this, "setStore", new Object[]{store});
        this.mStore = store;
    }

    public void setSection(StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "setSection", new Object[]{section});
        this.mSection = section;
    }

    public View getTitleView(int position, View convertView, ViewGroup parent) {
        Ensighten.evaluateEvent(this, "getTitleView", new Object[]{new Integer(position), convertView, parent});
        if (convertView == null) {
            return ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(C2658R.layout.fragment_storelocator_expandable_title, parent, false);
        }
        return convertView;
    }

    public View getContentView(int position, View convertView, ViewGroup parent) {
        Ensighten.evaluateEvent(this, "getContentView", new Object[]{new Integer(position), convertView, parent});
        if (convertView == null) {
            return ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(C2658R.layout.fragment_storelocator_expandable_content, parent, false);
        }
        return convertView;
    }

    /* Access modifiers changed, original: protected */
    public ViewHolder createViewHolder(View view) {
        Ensighten.evaluateEvent(this, "createViewHolder", new Object[]{view});
        return new StoreItemViewHolder();
    }

    public void expand(int position) {
        Ensighten.evaluateEvent(this, "expand", new Object[]{new Integer(position)});
        if (position < this.mAbsListView.getFirstVisiblePosition() || position > this.mAbsListView.getLastVisiblePosition()) {
            retryExpand(position);
            return;
        }
        StoreItemConfigurationHelper.configureStoreItem(getContext(), (StoreItemViewHolder) this.mAbsListView.getChildAt(position - this.mAbsListView.getFirstVisiblePosition()).getTag(), this.mDataProvider.stateForStore(Integer.valueOf(this.mStore.getStoreId()), this.mSection), shouldHideOrderingWarning(this.mStore), this.mDataProvider.allowsFavoritingWithoutMobileOrdering(), this.mDataProvider.allowsSelectionWithoutMobileOrdering(), this.mDataProvider.getOfferState(Integer.valueOf(this.mStore.getStoreId()), this.mSection));
        super.expand(position);
    }

    private void retryExpand(final int position) {
        Ensighten.evaluateEvent(this, "retryExpand", new Object[]{new Integer(position)});
        this.mExpandRetryCount++;
        if (this.mExpandRetryCount < 2) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    Ensighten.evaluateEvent(this, "run", null);
                    StoreLocatorPagerListAdapter.this.expand(position);
                }
            }, 100);
        } else {
            this.mExpandRetryCount = 0;
        }
    }

    public void storesUpdated() {
        Ensighten.evaluateEvent(this, "storesUpdated", null);
        clear();
        if (this.mStore != null) {
            add(this.mStore);
        }
        notifyDataSetChanged();
    }

    public void setOffersOnly(Boolean offersOnlyMode) {
        Ensighten.evaluateEvent(this, "setOffersOnly", new Object[]{offersOnlyMode});
        this.mOffersOnlyMode = offersOnlyMode.booleanValue();
    }

    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = super.getView(position, convertView, viewGroup);
        Store store = (Store) getItem(position);
        StoreItemViewHolder viewHolder = (StoreItemViewHolder) view.getTag();
        if (viewHolder == null) {
            MCDLog.fatal("ViewHolder missing in StoreLocator List getView");
            view = null;
        } else {
            viewHolder.populateFromView(view);
            viewHolder.setListener(this.mListener);
            DataLayerClickListener.setDataLayerTag(convertView, StoreItemViewHolder.class, position);
            viewHolder.setStore(store);
            viewHolder.setSection(this.mSection);
            viewHolder.getSectionHeader().setVisibility(8);
            if (store.getStoreFavoriteName() != null) {
                viewHolder.getStoreTitleLabel().setTextColor(getContext().getResources().getColor(C2658R.color.mcd_red));
                viewHolder.getStoreTitleLabel().setText(store.getStoreFavoriteName());
                viewHolder.getStoreSubtitleLabel().setText(store.getAddress1());
            } else {
                viewHolder.getStoreTitleLabel().setTextColor(getContext().getResources().getColor(C2658R.color.dark_gray_1));
                viewHolder.getStoreTitleLabel().setText(store.getPublicName());
                if (store.getCity() != null) {
                    viewHolder.getStoreSubtitleLabel().setText(store.getCity());
                }
            }
            if (this.mDataProvider.getSelectedStoreNickName() != null) {
                viewHolder.getNickName().setText(this.mDataProvider.getSelectedStoreNickName());
            } else if (store.getStoreFavoriteName() != null) {
                viewHolder.getNickName().setText(store.getStoreFavoriteName());
            } else {
                viewHolder.getNickName().setText("");
            }
            if (store.getStoreFavoriteName() != null) {
                viewHolder.getNickName().setText(store.getStoreFavoriteName());
            }
            viewHolder.getStoreDistanceLabel().setText(UIUtils.distanceFromStore(getContext(), store));
            viewHolder.getMyMcDonaldsIcon().setImageResource(C2358R.C2359drawable.icon_map_pin_large_red_yellow);
            String dailyStoreHoursString = UIUtils.getDailyStoreHoursString(getContext(), store);
            if (TextUtils.isEmpty(dailyStoreHoursString)) {
                viewHolder.getStoreHoursLabel().setVisibility(8);
            } else {
                viewHolder.getStoreHoursLabel().setText(dailyStoreHoursString);
            }
            if (this.mDataProvider.isCurrentStoreSelectionMode()) {
                viewHolder.getSelectStoreButton().setText(C2658R.string.button_select);
                viewHolder.getChooseAnotherLocation().setVisibility(0);
                viewHolder.getOrderHere().setVisibility(8);
                viewHolder.getSaveToFavoritesButton().setText(C2658R.string.continue_button);
            }
            if (this.mOffersOnlyMode) {
                viewHolder.getOrderHere().setVisibility(8);
            }
            refreshContent(position, viewHolder);
        }
        Ensighten.getViewReturnValue(view, position);
        Ensighten.processView((Object) this, "getView");
        Ensighten.getViewReturnValue(null, -1);
        return view;
    }

    public void refreshContent(int position, StoreItemViewHolder viewHolder) {
        Ensighten.evaluateEvent(this, "refreshContent", new Object[]{new Integer(position), viewHolder});
        StoreItemViewState expandedState = this.mDataProvider.stateForStore(Integer.valueOf(this.mStore.getStoreId()), this.mSection);
        boolean hideOrderingWarning = shouldHideOrderingWarning(this.mStore);
        StoreItemConfigurationHelper.configureStoreItem(getContext(), viewHolder, expandedState, hideOrderingWarning, this.mDataProvider.allowsFavoritingWithoutMobileOrdering(), this.mDataProvider.allowsSelectionWithoutMobileOrdering(), this.mDataProvider.getOfferState(Integer.valueOf(this.mStore.getStoreId()), this.mSection));
        viewHolder.getOffersStatusIcon().setVisibility(8);
        viewHolder.getStoreHoursLabel().setVisibility(0);
    }

    public boolean areAllItemsEnabled() {
        Ensighten.evaluateEvent(this, "areAllItemsEnabled", null);
        return true;
    }

    public boolean isEnabled(int position) {
        Ensighten.evaluateEvent(this, "isEnabled", new Object[]{new Integer(position)});
        return true;
    }

    private boolean shouldHideOrderingWarning(Store store) {
        Ensighten.evaluateEvent(this, "shouldHideOrderingWarning", new Object[]{store});
        if (!ModuleManager.getSharedInstance().isOrderingAvailable()) {
            return true;
        }
        if (store.hasMobileOrdering() != null) {
            return store.hasMobileOrdering().booleanValue();
        }
        return false;
    }
}
