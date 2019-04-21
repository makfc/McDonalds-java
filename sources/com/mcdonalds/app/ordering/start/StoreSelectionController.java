package com.mcdonalds.app.ordering.start;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.SparseArray;
import com.ensighten.Ensighten;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.storelocator.PagerItemListener;
import com.mcdonalds.app.storelocator.StoreDetailsActivity;
import com.mcdonalds.app.storelocator.StoreDetailsFragment;
import com.mcdonalds.app.storelocator.StoreItemViewState;
import com.mcdonalds.app.storelocator.StoreLocationListener;
import com.mcdonalds.app.storelocator.StoreLocatorDataProvider;
import com.mcdonalds.app.storelocator.StoreLocatorDataProvider.OfferState;
import com.mcdonalds.app.storelocator.StoreLocatorInteractionListener;
import com.mcdonalds.app.storelocator.StoreLocatorSection;
import com.mcdonalds.app.storelocator.maps.model.LatLng;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.FavoriteStoreUtils;
import com.mcdonalds.app.util.ServiceUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.StoreFavoriteInfo;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.modules.storelocator.StoreLocatorModule;
import com.mcdonalds.sdk.services.log.MCDLog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StoreSelectionController implements PagerItemListener, StoreLocatorDataProvider, StoreLocatorInteractionListener {
    private static List<String> sNicknames = null;
    private Context mContext;
    private CustomerModule mCustomerModule;
    private List<Store> mFavoriteStores;
    private List<StoreLocationListener> mListeners;
    private List<Store> mNearbyStores;
    private Store mOriginalCurrentStore;
    private StoreLocatorModule mStoreLocatorModule;

    /* renamed from: com.mcdonalds.app.ordering.start.StoreSelectionController$1 */
    class C36721 implements AsyncListener<List<StoreFavoriteInfo>> {
        C36721() {
        }

        public void onResponse(List<StoreFavoriteInfo> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.start.StoreSelectionController", "access$000", new Object[]{StoreSelectionController.this}) == null) {
                return;
            }
            if (exception == null) {
                List<String> favoriteStoreIds = new ArrayList();
                final SparseArray<StoreFavoriteInfo> favoriteInfoArray = new SparseArray();
                for (StoreFavoriteInfo info : response) {
                    Integer storeId = Integer.valueOf(info.getStoreId());
                    favoriteStoreIds.add(Integer.toString(info.getStoreId()));
                    favoriteInfoArray.put(storeId.intValue(), info);
                }
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.start.StoreSelectionController", "access$400", new Object[]{StoreSelectionController.this}).getStoresForIds(favoriteStoreIds, new AsyncListener<List<Store>>() {
                    public void onResponse(List<Store> response, AsyncToken token, AsyncException exception) {
                        Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                        if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.start.StoreSelectionController", "access$000", new Object[]{StoreSelectionController.this}) == null) {
                            return;
                        }
                        if (exception == null) {
                            for (Store store : response) {
                                StoreFavoriteInfo info = (StoreFavoriteInfo) favoriteInfoArray.get(store.getStoreId());
                                if (info != null) {
                                    store.setStoreFavoriteId(Integer.valueOf(info.getFavoriteId()));
                                    store.setStoreFavoriteName(info.getFavoriteNickName());
                                }
                            }
                            List<Store> availableFavoriteStores = FavoriteStoreUtils.checkIfFavoriteStoresAreOpen(favoriteInfoArray, response);
                            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.start.StoreSelectionController", "access$100", new Object[]{StoreSelectionController.this}).setFavoriteStores(availableFavoriteStores);
                            StoreSelectionController.access$202(StoreSelectionController.this, availableFavoriteStores);
                            List<Store> checkStores = new ArrayList(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.start.StoreSelectionController", "access$200", new Object[]{StoreSelectionController.this}));
                            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.start.StoreSelectionController", "access$300", new Object[]{StoreSelectionController.this}) != null) {
                                checkStores.add(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.start.StoreSelectionController", "access$300", new Object[]{StoreSelectionController.this}));
                                return;
                            }
                            return;
                        }
                        UIUtils.stopActivityIndicator();
                        AsyncException.report(exception);
                    }
                });
                return;
            }
            UIUtils.stopActivityIndicator();
            AsyncException.report(exception);
        }
    }

    static /* synthetic */ List access$202(StoreSelectionController x0, List x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.start.StoreSelectionController", "access$202", new Object[]{x0, x1});
        x0.mFavoriteStores = x1;
        return x1;
    }

    static /* synthetic */ void access$500(StoreSelectionController x0, List x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.start.StoreSelectionController", "access$500", new Object[]{x0, x1});
        x0.updateFavoriteStores(x1);
    }

    static /* synthetic */ void access$600(StoreSelectionController x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.start.StoreSelectionController", "access$600", new Object[]{x0});
        x0.notifyObservers();
    }

    public StoreSelectionController(Context context) {
        this.mContext = context;
        refresh();
    }

    public void refresh() {
        Ensighten.evaluateEvent(this, "refresh", null);
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        this.mStoreLocatorModule = (StoreLocatorModule) ModuleManager.getModule(StoreLocatorModule.NAME);
        refreshStores();
    }

    private void refreshStores() {
        Ensighten.evaluateEvent(this, "refreshStores", null);
        this.mOriginalCurrentStore = this.mCustomerModule.getCurrentStore();
        if (this.mCustomerModule.isLoggedIn()) {
            UIUtils.startActivityIndicator(this.mContext, (int) C2658R.string.progress_start_order);
            ServiceUtils.getSharedInstance().retrieveFavoriteStores(this.mCustomerModule.getCurrentProfile(), new C36721());
            UIUtils.stopActivityIndicator();
            return;
        }
        notifyObservers();
    }

    public void setNearbyStores(List<Store> stores) {
        Ensighten.evaluateEvent(this, "setNearbyStores", new Object[]{stores});
        this.mNearbyStores = stores;
    }

    private void notifyObservers() {
        Ensighten.evaluateEvent(this, "notifyObservers", null);
        if (this.mListeners != null) {
            for (StoreLocationListener listener : this.mListeners) {
                listener.onChange(this);
            }
        }
    }

    public void addListener(StoreLocationListener listener) {
        Ensighten.evaluateEvent(this, "addListener", new Object[]{listener});
        if (this.mListeners == null) {
            this.mListeners = new ArrayList();
        }
        if (!this.mListeners.contains(listener)) {
            this.mListeners.add(listener);
            listener.onChange(this);
        }
    }

    public Store getCurrentStore() {
        Ensighten.evaluateEvent(this, "getCurrentStore", null);
        return this.mCustomerModule == null ? null : this.mCustomerModule.getCurrentStore();
    }

    public List<Store> getFavoriteStores() {
        Ensighten.evaluateEvent(this, "getFavoriteStores", null);
        return this.mFavoriteStores;
    }

    public List<Store> getNearByStores() {
        Ensighten.evaluateEvent(this, "getNearByStores", null);
        return null;
    }

    public Store getSelectedStore() {
        Ensighten.evaluateEvent(this, "getSelectedStore", null);
        return null;
    }

    public boolean locationServicesEnabled() {
        Ensighten.evaluateEvent(this, "locationServicesEnabled", null);
        return false;
    }

    public Location getLastLocation() {
        Ensighten.evaluateEvent(this, "getLastLocation", null);
        return null;
    }

    public boolean allowsSelectionWithoutMobileOrdering() {
        Ensighten.evaluateEvent(this, "allowsSelectionWithoutMobileOrdering", null);
        return false;
    }

    public boolean allowsFavoritingWithoutMobileOrdering() {
        Ensighten.evaluateEvent(this, "allowsFavoritingWithoutMobileOrdering", null);
        return false;
    }

    public OfferState getOfferState(Integer storeId, StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "getOfferState", new Object[]{storeId, section});
        return OfferState.NO_OFFER;
    }

    public void updateCurrentStore(Integer storeId, StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "updateCurrentStore", new Object[]{storeId, section});
        Store previousStore = getCurrentStore();
        Store currentStore = getStore(storeId);
        this.mCustomerModule.setCurrentStore(currentStore);
        DataLayerManager.getInstance().setStore(currentStore);
        for (StoreLocationListener listener : this.mListeners) {
            listener.onCurrentStoreChange(this, Integer.toString(previousStore.getStoreId()));
        }
    }

    private Store getStore(Integer storeId) {
        Ensighten.evaluateEvent(this, "getStore", new Object[]{storeId});
        if (storeId.equals(Integer.valueOf(this.mOriginalCurrentStore.getStoreId()))) {
            return this.mOriginalCurrentStore;
        }
        for (Store store : this.mFavoriteStores) {
            if (storeId.equals(Integer.valueOf(store.getStoreId()))) {
                return store;
            }
        }
        return null;
    }

    public void selectStore(Integer storeId, StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "selectStore", new Object[]{storeId, section});
        if (!storeId.equals(Integer.valueOf(getCurrentStore().getStoreId()))) {
            Store selected = getStore(storeId);
            if (selected != null) {
                AnalyticsUtils.trackStoreSelection(selected);
            }
            updateCurrentStore(storeId, section);
        }
    }

    public void nicknameClicked(Integer storeId, StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "nicknameClicked", new Object[]{storeId, section});
    }

    public void addStoreToFavorites(Integer storeId, StoreLocatorSection section, String nickName, final AsyncListener<Boolean> successListener) {
        Ensighten.evaluateEvent(this, "addStoreToFavorites", new Object[]{storeId, section, nickName, successListener});
        final Store store = getStore(storeId, section);
        if (store != null) {
            store.setStoreFavoriteName(nickName);
            this.mCustomerModule.addFavoriteStores(Collections.singletonList(store), this.mCustomerModule.getCurrentProfile(), new AsyncListener<List<Store>>() {
                public void onResponse(List<Store> response, AsyncToken token, AsyncException exception) {
                    boolean z = true;
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    if (exception != null) {
                        UIUtils.stopActivityIndicator();
                        AsyncException.report(exception);
                        store.setStoreFavoriteName(null);
                    } else {
                        UIUtils.stopActivityIndicator();
                        StoreSelectionController.access$500(StoreSelectionController.this, response);
                        StoreSelectionController.access$600(StoreSelectionController.this);
                    }
                    if (successListener != null) {
                        AsyncListener asyncListener = successListener;
                        if (exception != null) {
                            z = false;
                        }
                        asyncListener.onResponse(Boolean.valueOf(z), null, exception);
                    }
                }
            });
        }
    }

    public void removeStoreFromFavorites(Integer storeId, StoreLocatorSection section, final AsyncListener<Boolean> successListener) {
        Ensighten.evaluateEvent(this, "removeStoreFromFavorites", new Object[]{storeId, section, successListener});
        if (section != StoreLocatorSection.Favorites) {
            throw new RuntimeException("Attempting to remove non-favorite");
        }
        Store store = getStore(storeId, section);
        if (store != null) {
            this.mCustomerModule.deleteFavoriteStores(Collections.singletonList(store.getStoreFavoriteId()), this.mCustomerModule.getCurrentProfile(), new AsyncListener<List<Store>>() {
                public void onResponse(List<Store> response, AsyncToken token, AsyncException exception) {
                    boolean z = true;
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    if (exception != null) {
                        AsyncException.report(exception);
                    } else {
                        StoreSelectionController.access$500(StoreSelectionController.this, response);
                        StoreSelectionController.access$600(StoreSelectionController.this);
                    }
                    if (successListener != null) {
                        AsyncListener asyncListener = successListener;
                        if (exception != null) {
                            z = false;
                        }
                        asyncListener.onResponse(Boolean.valueOf(z), null, exception);
                    }
                }
            });
        }
    }

    public void renameStoreInFavorites(Integer storeId, StoreLocatorSection section, String nickName, final AsyncListener<Boolean> successListener) {
        Ensighten.evaluateEvent(this, "renameStoreInFavorites", new Object[]{storeId, section, nickName, successListener});
        if (section == StoreLocatorSection.Favorites || section == StoreLocatorSection.Current) {
            final Store store = getStore(storeId, section);
            if (store != null) {
                final String previousNickName = store.getStoreFavoriteName();
                store.setStoreFavoriteName(nickName);
                this.mCustomerModule.renameFavoriteStores(Collections.singletonList(store), this.mCustomerModule.getCurrentProfile(), new AsyncListener<List<Store>>() {
                    public void onResponse(List<Store> response, AsyncToken token, AsyncException exception) {
                        boolean z = true;
                        Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                        if (exception != null) {
                            UIUtils.stopActivityIndicator();
                            AsyncException.report(exception);
                            store.setStoreFavoriteName(previousNickName);
                        } else {
                            UIUtils.stopActivityIndicator();
                            StoreSelectionController.access$500(StoreSelectionController.this, response);
                            StoreSelectionController.access$600(StoreSelectionController.this);
                        }
                        if (successListener != null) {
                            AsyncListener asyncListener = successListener;
                            if (exception != null) {
                                z = false;
                            }
                            asyncListener.onResponse(Boolean.valueOf(z), null, exception);
                        }
                    }
                });
                return;
            }
            return;
        }
        MCDLog.error("StoreLocatorController", "Attempting to rename non-favorite");
    }

    private void updateFavoriteStores(List<Store> stores) {
        Ensighten.evaluateEvent(this, "updateFavoriteStores", new Object[]{stores});
        this.mFavoriteStores = new ArrayList(stores);
        notifyObservers();
    }

    public void displayStoreDetails(Integer storeId, StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "displayStoreDetails", new Object[]{storeId, section});
        Store store = getStore(storeId, section);
        if (store != null) {
            Bundle bundle = new Bundle();
            bundle.putInt("extra_store_section", section.ordinal());
            bundle.putParcelable("extra_store_detail", store);
            if (this.mContext instanceof URLNavigationActivity) {
                this.mContext.startActivity(StoreDetailsActivity.class, StoreDetailsFragment.NAME, bundle);
                return;
            }
            return;
        }
        AsyncException.report(this.mContext.getString(C2658R.string.dialog_store_details_unavailable));
    }

    public void requestUpdateStoresByCurrentLocation(boolean showActivityIndicator) {
        Ensighten.evaluateEvent(this, "requestUpdateStoresByCurrentLocation", new Object[]{new Boolean(showActivityIndicator)});
    }

    public void requestUpdateStoresByAddress(String address, boolean showActivityIndicator) {
        Ensighten.evaluateEvent(this, "requestUpdateStoresByAddress", new Object[]{address, new Boolean(showActivityIndicator)});
    }

    public void requestUpdateStoresByScrolledLocation(LatLng newLocation) {
        Ensighten.evaluateEvent(this, "requestUpdateStoresByScrolledLocation", new Object[]{newLocation});
    }

    public Integer getSelectedStoreId() {
        Ensighten.evaluateEvent(this, "getSelectedStoreId", null);
        return null;
    }

    public StoreLocatorSection getSelectedStoreSection() {
        Ensighten.evaluateEvent(this, "getSelectedStoreSection", null);
        return null;
    }

    public String getSelectedStoreNickName() {
        Ensighten.evaluateEvent(this, "getSelectedStoreNickName", null);
        return null;
    }

    public StoreItemViewState stateForStore(Integer storeId, StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "stateForStore", new Object[]{storeId, section});
        if (getCurrentStore() == null || !storeId.equals(Integer.valueOf(getCurrentStore().getStoreId()))) {
            return StoreItemViewState.Favorite;
        }
        return StoreItemViewState.CurrentAndFavorite;
    }

    public boolean isCurrentStoreSelectionMode() {
        Ensighten.evaluateEvent(this, "isCurrentStoreSelectionMode", null);
        return false;
    }

    public void eatHereClicked(Integer storeId, StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "eatHereClicked", new Object[]{storeId, section});
        selectStore(storeId, section);
    }

    public void myMcDonaldsClicked(Integer storeId, StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "myMcDonaldsClicked", new Object[]{storeId, section});
    }

    public void saveToFavoritesClicked(Integer storeId, StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "saveToFavoritesClicked", new Object[]{storeId, section});
    }

    public void addToFavoritesClicked(Integer storeId, StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "addToFavoritesClicked", new Object[]{storeId, section});
    }

    public void infoClicked(Integer storeId, StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "infoClicked", new Object[]{storeId, section});
        AnalyticsUtils.trackOnClickEvent("/restaurant-locator", "Info Icon");
        displayStoreDetails(storeId, section);
    }

    public void skipClicked(Integer storeId, StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "skipClicked", new Object[]{storeId, section});
        AnalyticsUtils.trackSkipFavoritesButton();
    }

    public void placeOrderClicked(Integer storeId, StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "placeOrderClicked", new Object[]{storeId, section});
    }

    public void removeFromFavoritesClicked(Integer storeId, StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "removeFromFavoritesClicked", new Object[]{storeId, section});
    }

    public int getSelectMapPinResID(Integer storeId) {
        Ensighten.evaluateEvent(this, "getSelectMapPinResID", new Object[]{storeId});
        return 0;
    }

    public int getMapPinResID(Integer storeId) {
        Ensighten.evaluateEvent(this, "getMapPinResID", new Object[]{storeId});
        return 0;
    }

    private Store getStore(Integer storeId, StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "getStore", new Object[]{storeId, section});
        switch (section) {
            case Current:
                return getCurrentStore();
            case Favorites:
                for (Store favStore : this.mFavoriteStores) {
                    if (storeId.equals(Integer.valueOf(favStore.getStoreId()))) {
                        return favStore;
                    }
                }
                return null;
            case Nearby:
                if (this.mNearbyStores == null || this.mNearbyStores.isEmpty()) {
                    return null;
                }
                for (Store nearbyStore : this.mNearbyStores) {
                    if (storeId.equals(Integer.valueOf(nearbyStore.getStoreId()))) {
                        return nearbyStore;
                    }
                }
                return null;
            default:
                return null;
        }
    }
}
