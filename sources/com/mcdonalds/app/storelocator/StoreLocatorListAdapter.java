package com.mcdonalds.app.storelocator;

import android.content.Context;
import android.location.Location;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker.Utils;
import com.mcdonalds.app.p043ui.listview.ExpandableListItemAdapter;
import com.mcdonalds.app.p043ui.listview.ExpandableListItemAdapter.ViewHolder;
import com.mcdonalds.app.storelocator.StoreLocatorDataProvider.OfferState;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.log.MCDLog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StoreLocatorListAdapter extends ExpandableListItemAdapter<Store> {
    private Store mCurrentStore;
    private StoreLocatorDataProvider mDataProvider;
    private List<Store> mFavoriteStores = new ArrayList();
    private int mFavoritesStartIndex;
    private LayoutInflater mInflater;
    private PagerItemListener mListener;
    private List<Store> mNearByStores = new ArrayList();
    private int mNearbyStartIndex;
    private Boolean mOffersOnlyMode = Boolean.valueOf(false);

    public StoreLocatorListAdapter(Context context) {
        super(context, C2658R.layout.fragment_storelocator_expandable_list_item, C2358R.C2357id.expandablelistitem_card_title, C2358R.C2357id.expandablelistitem_card_content, new ArrayList());
        this.mInflater = LayoutInflater.from(context);
    }

    public View getTitleView(int position, View convertView, ViewGroup parent) {
        Ensighten.evaluateEvent(this, "getTitleView", new Object[]{new Integer(position), convertView, parent});
        if (convertView == null) {
            return this.mInflater.inflate(C2658R.layout.fragment_storelocator_expandable_title, parent, false);
        }
        return convertView;
    }

    public View getContentView(int position, View convertView, ViewGroup parent) {
        Ensighten.evaluateEvent(this, "getContentView", new Object[]{new Integer(position), convertView, parent});
        if (convertView == null) {
            return this.mInflater.inflate(C2658R.layout.fragment_storelocator_expandable_content, parent, false);
        }
        return convertView;
    }

    /* Access modifiers changed, original: protected */
    public ViewHolder createViewHolder(View view) {
        Ensighten.evaluateEvent(this, "createViewHolder", new Object[]{view});
        return new StoreItemViewHolder(this.mListener);
    }

    public void expand(int position) {
        Ensighten.evaluateEvent(this, "expand", new Object[]{new Integer(position)});
        if (position >= this.mAbsListView.getFirstVisiblePosition() && position <= this.mAbsListView.getLastVisiblePosition()) {
            Store store = (Store) getItem(position);
            StoreItemConfigurationHelper.configureStoreItem(getContext(), (StoreItemViewHolder) this.mAbsListView.getChildAt(position - this.mAbsListView.getFirstVisiblePosition()).getTag(), this.mDataProvider.stateForStore(Integer.valueOf(store.getStoreId()), getSection(position)), shouldHideOrderingWarning(store), this.mDataProvider.allowsFavoritingWithoutMobileOrdering(), this.mDataProvider.allowsSelectionWithoutMobileOrdering(), this.mDataProvider.getOfferState(Integer.valueOf(store.getStoreId()), getSection(position)));
        }
        super.expand(position);
    }

    public void storesUpdated() {
        Ensighten.evaluateEvent(this, "storesUpdated", null);
        updateSections();
        List<Store> stores = new ArrayList();
        if (this.mCurrentStore != null) {
            stores.add(this.mCurrentStore);
        }
        stores.addAll(this.mFavoriteStores);
        stores.addAll(this.mNearByStores);
        clear();
        addAll(stores);
        if (this.mDataProvider == null || this.mDataProvider.getSelectedStoreId() == null) {
            for (int i = 0; i < stores.size(); i++) {
                if (isExpanded(i)) {
                    collapse(i);
                }
            }
            return;
        }
        int position = getPosition(this.mDataProvider.getSelectedStoreId(), this.mDataProvider.getSelectedStoreSection());
        if (this.mDataProvider.stateForStore(this.mDataProvider.getSelectedStoreId(), this.mDataProvider.getSelectedStoreSection()).isExpandedState()) {
            if (!isExpanded(position)) {
                expand(position);
            }
        } else if (isExpanded(position)) {
            collapse(position);
        }
    }

    public void setCurrentStore(Store currentStore) {
        Ensighten.evaluateEvent(this, "setCurrentStore", new Object[]{currentStore});
        this.mCurrentStore = currentStore;
    }

    public void setOffersOnly(Boolean offersOnlyMode) {
        Ensighten.evaluateEvent(this, "setOffersOnly", new Object[]{offersOnlyMode});
        this.mOffersOnlyMode = offersOnlyMode;
    }

    public void setFavoriteStores(List<Store> favoriteStores) {
        Ensighten.evaluateEvent(this, "setFavoriteStores", new Object[]{favoriteStores});
        this.mFavoriteStores.clear();
        if (favoriteStores != null) {
            this.mFavoriteStores.addAll(favoriteStores);
            if (this.mDataProvider != null) {
                final Location location = this.mDataProvider.getLastLocation();
                if (location != null) {
                    Collections.sort(this.mFavoriteStores, new Comparator<Store>() {
                        public int compare(Store o1, Store o2) {
                            Ensighten.evaluateEvent(this, "compare", new Object[]{o1, o2});
                            if (((double) location.distanceTo(o1.toLocation())) < ((double) location.distanceTo(o2.toLocation()))) {
                                return -1;
                            }
                            return 1;
                        }
                    });
                }
            }
        }
    }

    public void setNearByStores(List<Store> nearByStores) {
        Ensighten.evaluateEvent(this, "setNearByStores", new Object[]{nearByStores});
        this.mNearByStores.clear();
        if (nearByStores != null) {
            this.mNearByStores.addAll(nearByStores);
        }
    }

    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = super.getView(position, convertView, viewGroup);
        Store store = (Store) getItem(position);
        StoreItemViewHolder viewHolder = (StoreItemViewHolder) view.getTag();
        if (viewHolder == null) {
            MCDLog.fatal("ViewHolder missing in StoreLocator List getView");
            view = null;
        } else {
            viewHolder.setStore(store);
            if (!viewHolder.hasBeenPopulated()) {
                viewHolder.populateFromView(view);
                viewHolder.setListener(this.mListener);
                if (this.mOffersOnlyMode.booleanValue()) {
                    viewHolder.getOrderHere().setVisibility(8);
                }
            }
            DataLayerClickListener.setDataLayerTag(convertView, StoreItemViewHolder.class, position);
            refreshContent(position, viewHolder);
        }
        Ensighten.getViewReturnValue(view, position);
        Ensighten.processView((Object) this, "getView");
        Ensighten.getViewReturnValue(null, -1);
        return view;
    }

    public void refreshContent(int position, StoreItemViewHolder viewHolder) {
        Ensighten.evaluateEvent(this, "refreshContent", new Object[]{new Integer(position), viewHolder});
        Store store = (Store) getItem(position);
        StoreItemViewState state = this.mDataProvider.stateForStore(Integer.valueOf(store.getStoreId()), getSection(position));
        boolean hideOrderingWarning = shouldHideOrderingWarning(store);
        StoreItemConfigurationHelper.configureStoreItem(getContext(), viewHolder, state, hideOrderingWarning, this.mDataProvider.allowsFavoritingWithoutMobileOrdering(), this.mDataProvider.allowsSelectionWithoutMobileOrdering(), this.mDataProvider.getOfferState(Integer.valueOf(store.getStoreId()), getSection(position)));
        updateStoreItemData(position, viewHolder, store);
    }

    public void updateStoreItemData(int position, StoreItemViewHolder viewHolder, Store store) {
        Ensighten.evaluateEvent(this, "updateStoreItemData", new Object[]{new Integer(position), viewHolder, store});
        if (this.mDataProvider.getOfferState(Integer.valueOf(store.getStoreId()), getSection(position)) != OfferState.NO_OFFER) {
            viewHolder.getOffersStatusIcon().setVisibility(0);
            if (this.mDataProvider.getSelectedStoreId() == null || store.getStoreId() != this.mDataProvider.getSelectedStoreId().intValue()) {
                viewHolder.getOffersStatusIcon().setImageResource(this.mDataProvider.getMapPinResID(Integer.valueOf(store.getStoreId())));
            } else {
                viewHolder.getOffersStatusIcon().setImageResource(this.mDataProvider.getSelectMapPinResID(Integer.valueOf(store.getStoreId())));
            }
        }
        viewHolder.setStore(store);
        viewHolder.setSection(getSection(position));
        if (getItemViewType(position) == 1) {
            configureSectionHeader(position, viewHolder);
        } else {
            viewHolder.getSectionHeader().setVisibility(8);
        }
        if (store.getStoreFavoriteName() == null || this.mFavoriteStores == null || this.mFavoriteStores.size() <= 0) {
            viewHolder.getStoreTitleLabel().setTextColor(getContext().getResources().getColor(C2658R.color.dark_gray_1));
            viewHolder.getStoreTitleLabel().setText(store.getPublicName());
            viewHolder.getStoreSubtitleLabel().setText("");
            if (store.getCity() != null) {
                viewHolder.getStoreSubtitleLabel().setText(store.getCity());
            }
        } else {
            viewHolder.getStoreTitleLabel().setTextColor(getContext().getResources().getColor(C2658R.color.mcd_red));
            viewHolder.getStoreTitleLabel().setText(store.getStoreFavoriteName());
            viewHolder.getStoreSubtitleLabel().setText(store.getPublicName());
        }
        if (this.mDataProvider.getSelectedStoreNickName() != null) {
            viewHolder.getNickName().setText(this.mDataProvider.getSelectedStoreNickName());
        } else if (store.getStoreFavoriteName() != null) {
            viewHolder.getNickName().setText(store.getStoreFavoriteName());
        } else {
            viewHolder.getNickName().setText("");
        }
        if (this.mDataProvider.getLastLocation() == null) {
            viewHolder.getStoreDistanceLabel().setVisibility(4);
        } else {
            String distance = UIUtils.distanceFromStore(getContext(), store);
            viewHolder.getStoreDistanceLabel().setVisibility(0);
            viewHolder.getStoreDistanceLabel().setText(distance);
        }
        viewHolder.getMyMcDonaldsIcon().setImageResource(C2358R.C2359drawable.icon_map_pin_small_red_yellow);
        viewHolder.getStoreHoursLabel().setVisibility(0);
        String dailyStoreHoursString = UIUtils.getDailyStoreHoursString(getContext(), store);
        if (!ListUtils.isEmpty(store.getStoreOperatingHours())) {
            String closeSting = Utils.getCloseStatus(store, getContext());
            if (!TextUtils.isEmpty(closeSting)) {
                dailyStoreHoursString = closeSting;
            }
        }
        if (TextUtils.isEmpty(dailyStoreHoursString)) {
            viewHolder.getStoreHoursLabel().setVisibility(8);
        } else {
            viewHolder.getStoreHoursLabel().setText(dailyStoreHoursString);
        }
    }

    public int getPosition(Integer storeId, StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "getPosition", new Object[]{storeId, section});
        if (section == null) {
            return -1;
        }
        int i;
        switch (section) {
            case Current:
                return 0;
            case Favorites:
                for (i = 0; i < this.mFavoriteStores.size(); i++) {
                    if (((Store) this.mFavoriteStores.get(i)).getStoreId() == storeId.intValue()) {
                        return i + this.mFavoritesStartIndex;
                    }
                }
                return -1;
            case Nearby:
                for (i = 0; i < this.mNearByStores.size(); i++) {
                    if (((Store) this.mNearByStores.get(i)).getStoreId() == storeId.intValue()) {
                        return i + this.mNearbyStartIndex;
                    }
                }
                return -1;
            default:
                return -1;
        }
    }

    public void setListener(PagerItemListener listener) {
        Ensighten.evaluateEvent(this, "setListener", new Object[]{listener});
        this.mListener = listener;
    }

    public void setDataProvider(StoreLocatorDataProvider dataProvider) {
        Ensighten.evaluateEvent(this, "setDataProvider", new Object[]{dataProvider});
        this.mDataProvider = dataProvider;
    }

    public boolean areAllItemsEnabled() {
        Ensighten.evaluateEvent(this, "areAllItemsEnabled", null);
        return true;
    }

    public boolean isEnabled(int position) {
        Ensighten.evaluateEvent(this, "isEnabled", new Object[]{new Integer(position)});
        return true;
    }

    public int getItemViewType(int position) {
        Ensighten.evaluateEvent(this, "getItemViewType", new Object[]{new Integer(position)});
        return isSectionHeader(position) ? 1 : 0;
    }

    public int getViewTypeCount() {
        Ensighten.evaluateEvent(this, "getViewTypeCount", null);
        return 2;
    }

    public StoreLocatorSection getSection(int i) {
        boolean hasCurrent;
        int offset = 1;
        Ensighten.evaluateEvent(this, "getSection", new Object[]{new Integer(i)});
        if (this.mCurrentStore != null) {
            hasCurrent = true;
        } else {
            hasCurrent = false;
        }
        if (!hasCurrent) {
            offset = 0;
        }
        if (hasCurrent && i == 0) {
            return StoreLocatorSection.Current;
        }
        if (this.mFavoritesStartIndex != -1 && i < this.mFavoriteStores.size() + offset) {
            return StoreLocatorSection.Favorites;
        }
        if (this.mNearbyStartIndex != -1) {
            return StoreLocatorSection.Nearby;
        }
        throw new RuntimeException("Invalid section");
    }

    private void updateSections() {
        boolean hasCurrent;
        int i;
        int offset = 1;
        int i2 = -1;
        Ensighten.evaluateEvent(this, "updateSections", null);
        if (this.mCurrentStore != null) {
            hasCurrent = true;
        } else {
            hasCurrent = false;
        }
        if (!hasCurrent) {
            offset = 0;
        }
        if (this.mFavoriteStores.isEmpty()) {
            i = -1;
        } else {
            i = offset;
        }
        this.mFavoritesStartIndex = i;
        if (!this.mNearByStores.isEmpty()) {
            i2 = this.mFavoriteStores.size() + offset;
        }
        this.mNearbyStartIndex = i2;
    }

    /* Access modifiers changed, original: protected */
    public boolean isSectionHeader(int position) {
        Ensighten.evaluateEvent(this, "isSectionHeader", new Object[]{new Integer(position)});
        switch (getSection(position)) {
            case Current:
                return true;
            case Favorites:
                if (this.mFavoriteStores.size() > 0) {
                    return position == this.mFavoritesStartIndex;
                } else {
                    return false;
                }
            case Nearby:
                if (this.mNearByStores.size() > 0) {
                    return position == this.mNearbyStartIndex;
                } else {
                    return false;
                }
            default:
                return false;
        }
    }

    private void configureSectionHeader(int position, StoreItemViewHolder viewHolder) {
        Ensighten.evaluateEvent(this, "configureSectionHeader", new Object[]{new Integer(position), viewHolder});
        String text = null;
        int iconId = -1;
        switch (getSection(position)) {
            case Current:
                text = getContext().getString(C2658R.string.sl_section_current);
                break;
            case Favorites:
                if (position == this.mFavoritesStartIndex) {
                    text = getContext().getString(C2658R.string.sl_section_favorites);
                    iconId = C2358R.C2359drawable.icon_favorites_gray_30_24;
                    break;
                }
                break;
            case Nearby:
                if (position == this.mNearbyStartIndex) {
                    text = getContext().getString(C2658R.string.sl_section_nearby);
                    break;
                }
                break;
        }
        if (text != null) {
            viewHolder.getSectionHeader().setVisibility(0);
            viewHolder.getSectionHeaderLabel().setText(text);
            if (iconId > -1) {
                viewHolder.getSectionIconView().setVisibility(0);
                viewHolder.getSectionIconView().setImageResource(iconId);
                return;
            }
            viewHolder.getSectionIconView().setVisibility(8);
            return;
        }
        viewHolder.getSectionHeader().setVisibility(8);
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
