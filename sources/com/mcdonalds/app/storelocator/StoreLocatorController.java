package com.mcdonalds.app.storelocator;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.ContentObserver;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.p000v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import com.amap.api.location.LocationManagerProxy;
import com.ensighten.Ensighten;
import com.mcdonalds.app.MainActivity;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.customer.SignInActivity;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.menu.MenuActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker.Utils;
import com.mcdonalds.app.storelocator.StoreLocatorDataProvider.OfferState;
import com.mcdonalds.app.storelocator.maps.model.LatLng;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.ConfigurationUtils;
import com.mcdonalds.app.util.FavoriteStoreUtils;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.app.util.ServiceUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.StoreFavoriteInfo;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.modules.storelocator.StoreLocatorModule;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.DataPasser;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.services.data.provider.Contract.CurrentStore;
import com.mcdonalds.sdk.services.location.LocationServicesManager;
import com.mcdonalds.sdk.services.log.MCDLog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StoreLocatorController implements PagerItemListener, StoreLocatorDataProvider, StoreLocatorInteractionListener {
    private static int MAP_ICON_RES_ID = 0;
    private static int SELECTED_MAP_ICON_RES_ID = 0;
    private Double DEFAULT_RADIUS = Double.valueOf(0.0d);
    private List<String> activeFilters = new ArrayList();
    private boolean isFilterForMobileOrderNeeded = false;
    private boolean mAllowsFavoritingWithoutMobileOrdering = true;
    private boolean mAllowsSelectionWithoutMobileOrdering = true;
    private boolean mAutoSelectClosestStore;
    private boolean mCurrentStoreSelectionMode;
    private CustomerModule mCustomerModule;
    private boolean mDismissOnPlaceOrder;
    private SparseArray<Store> mFavoriteMap;
    private SparseArray<StoreFavoriteInfo> mFavoritesData;
    private boolean mFetchedModules;
    private boolean mIgnoreUserLocation = true;
    private boolean mIsRefreshing;
    private Store mLastFavorite;
    private final List<StoreLocationListener> mListeners = new ArrayList();
    private boolean mLocationError;
    private LocationManager mLocationManager;
    private boolean mMapOnly;
    private URLNavigationFragment mNavigationFragment;
    protected SparseArray<Store> mNearByMap;
    private List<Store> mNearbyFilteredStores = null;
    private String mNearbySearchAddress;
    private List<Store> mNearbyStores = null;
    private OrderingModule mOrderingModule;
    private Integer mSelectedStoreId;
    private String mSelectedStoreNickname;
    private StoreLocatorSection mSelectedStoreSection;
    private StoreItemViewState mSelectedStoreState;
    private StoreLocatorModule mStoreLocatorModule;

    /* renamed from: com.mcdonalds.app.storelocator.StoreLocatorController$10 */
    class C371110 implements AsyncListener<List<Store>> {

        /* renamed from: com.mcdonalds.app.storelocator.StoreLocatorController$10$1 */
        class C37121 implements OnClickListener {
            C37121() {
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialogInterface, new Integer(i)});
                StoreLocatorController.this.notifyObservers();
            }
        }

        C371110() {
        }

        public void onResponse(List<Store> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$500", new Object[]{StoreLocatorController.this}) != null) {
                if (exception == null) {
                    StoreLocatorController.access$1302(StoreLocatorController.this, response);
                    StoreLocatorController.access$1200(StoreLocatorController.this, response);
                    if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$1300", new Object[]{StoreLocatorController.this}).isEmpty()) {
                        String notFound = Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$500", new Object[]{StoreLocatorController.this}).getString(C2658R.string.sl_no_stores_found);
                        UIUtils.showGlobalAlertDialog(Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$500", new Object[]{StoreLocatorController.this}), notFound, notFound, new C37121());
                        DataLayerManager.getInstance().recordError("No stores");
                    } else {
                        StoreLocatorController.this.notifyObservers();
                        if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$800", new Object[]{StoreLocatorController.this}) != null) {
                            StoreLocatorController.access$900(StoreLocatorController.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$1300", new Object[]{StoreLocatorController.this}));
                        }
                    }
                } else {
                    AsyncException.report(exception);
                }
            }
            UIUtils.stopActivityIndicator();
            StoreLocatorController.this.notifyObservers();
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.StoreLocatorController$13 */
    class C371613 implements AsyncListener<List<Store>> {
        C371613() {
        }

        public void onResponse(List<Store> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$500", new Object[]{StoreLocatorController.this}) == null) {
                return;
            }
            if (exception == null) {
                StoreLocatorController.this.notifyObservers();
                return;
            }
            StoreLocatorController.access$1602(StoreLocatorController.this, false);
            AsyncException.report(exception);
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.StoreLocatorController$5 */
    class C37225 implements OnClickListener {
        C37225() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            if (which == -1 && Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$500", new Object[]{StoreLocatorController.this}) != null) {
                StoreLocatorController.access$600(StoreLocatorController.this);
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$500", new Object[]{StoreLocatorController.this}).finish();
            }
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.StoreLocatorController$6 */
    class C37236 implements AsyncListener<List<Store>> {
        C37236() {
        }

        public void onResponse(List<Store> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$500", new Object[]{StoreLocatorController.this}) != null && exception == null && !response.isEmpty()) {
                Store newStore = (Store) response.get(0);
                int currentStoreId = Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$700", new Object[]{StoreLocatorController.this}).getCurrentStore().getStoreId();
                for (Store store : response) {
                    if (currentStoreId == store.getStoreId()) {
                        newStore = store;
                        break;
                    }
                }
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$700", new Object[]{StoreLocatorController.this}).setCurrentStore(newStore);
                DataLayerManager.getInstance().setStore(newStore);
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$800", new Object[]{StoreLocatorController.this}) != null) {
                    StoreLocatorController.access$900(StoreLocatorController.this, response);
                } else {
                    StoreLocatorController.this.notifyObservers();
                }
            }
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.StoreLocatorController$7 */
    class C37247 implements AsyncListener<List<StoreFavoriteInfo>> {

        /* renamed from: com.mcdonalds.app.storelocator.StoreLocatorController$7$1 */
        class C37251 implements AsyncListener<List<Store>> {
            C37251() {
            }

            public void onResponse(List<Store> response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                if (exception == null) {
                    for (Store store : response) {
                        StoreFavoriteInfo info = (StoreFavoriteInfo) Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$300", new Object[]{StoreLocatorController.this}).get(store.getStoreId());
                        if (info != null) {
                            store.setStoreFavoriteId(Integer.valueOf(info.getFavoriteId()));
                            store.setStoreFavoriteName(info.getFavoriteNickName());
                        }
                    }
                    List<Store> availableFavoriteStores = FavoriteStoreUtils.checkIfFavoriteStoresAreOpen(Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$300", new Object[]{StoreLocatorController.this}), response);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$700", new Object[]{StoreLocatorController.this}).setFavoriteStores(availableFavoriteStores);
                    StoreLocatorController.access$000(StoreLocatorController.this, availableFavoriteStores);
                    if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$800", new Object[]{StoreLocatorController.this}) != null) {
                        StoreLocatorController.access$900(StoreLocatorController.this, response);
                    } else {
                        StoreLocatorController.this.notifyObservers();
                    }
                }
            }
        }

        C37247() {
        }

        public void onResponse(List<StoreFavoriteInfo> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$500", new Object[]{StoreLocatorController.this}) == null) {
                return;
            }
            if (exception == null) {
                Set<String> favoriteStoreIds = new HashSet();
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$300", new Object[]{StoreLocatorController.this}).clear();
                for (StoreFavoriteInfo info : response) {
                    Integer storeId = Integer.valueOf(info.getStoreId());
                    favoriteStoreIds.add(storeId.toString());
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$300", new Object[]{StoreLocatorController.this}).put(storeId.intValue(), info);
                }
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$1000", new Object[]{StoreLocatorController.this}).getStoresForIds(new ArrayList(favoriteStoreIds), new C37251());
                return;
            }
            AsyncException.report(exception);
            StoreLocatorController.this.notifyObservers();
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.StoreLocatorController$8 */
    class C37268 implements AsyncListener<List<Store>> {

        /* renamed from: com.mcdonalds.app.storelocator.StoreLocatorController$8$1 */
        class C37271 implements OnClickListener {
            C37271() {
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialogInterface, new Integer(i)});
                StoreLocatorController.this.notifyObservers();
            }
        }

        C37268() {
        }

        public void onResponse(List<Store> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$500", new Object[]{StoreLocatorController.this}) != null) {
                if (exception == null) {
                    StoreLocatorController.access$1102(StoreLocatorController.this, false);
                    StoreLocatorController.access$1200(StoreLocatorController.this, response);
                    if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$1300", new Object[]{StoreLocatorController.this}).isEmpty()) {
                        String notFound = Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$500", new Object[]{StoreLocatorController.this}).getString(C2658R.string.sl_no_stores_found);
                        UIUtils.showGlobalAlertDialog(Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$500", new Object[]{StoreLocatorController.this}), notFound, notFound, new C37271());
                        DataLayerManager.getInstance().recordError("No stores");
                    } else if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$800", new Object[]{StoreLocatorController.this}) != null) {
                        StoreLocatorController.access$900(StoreLocatorController.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$1300", new Object[]{StoreLocatorController.this}));
                    } else {
                        StoreLocatorController.this.notifyObservers();
                    }
                } else {
                    AsyncException.report(exception);
                    StoreLocatorController.access$1102(StoreLocatorController.this, true);
                    for (StoreLocationListener listener : Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$1400", new Object[]{StoreLocatorController.this})) {
                        if (listener instanceof StoreLocatorMapFragment) {
                            listener.clearZoomFlag();
                        }
                    }
                }
            }
            UIUtils.stopActivityIndicator();
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.StoreLocatorController$9 */
    class C37289 implements AsyncListener<List<Store>> {
        C37289() {
        }

        public void onResponse(List<Store> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$500", new Object[]{StoreLocatorController.this}) == null) {
                UIUtils.stopActivityIndicator();
            } else if (exception == null) {
                StoreLocatorController.access$1102(StoreLocatorController.this, false);
                StoreLocatorController.access$1200(StoreLocatorController.this, response);
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$1300", new Object[]{StoreLocatorController.this}).isEmpty()) {
                    UIUtils.stopActivityIndicator();
                    StoreLocatorController.this.notifyObservers();
                    DataLayerManager.getInstance().recordError("No stores");
                } else {
                    if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$800", new Object[]{StoreLocatorController.this}) != null) {
                        StoreLocatorController.access$900(StoreLocatorController.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$1300", new Object[]{StoreLocatorController.this}));
                    }
                    UIUtils.stopActivityIndicator();
                }
            } else {
                UIUtils.stopActivityIndicator();
                AsyncException.report(exception);
                StoreLocatorController.access$1102(StoreLocatorController.this, true);
            }
            StoreLocatorController.this.notifyObservers();
        }
    }

    static /* synthetic */ void access$000(StoreLocatorController x0, List x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$000", new Object[]{x0, x1});
        x0.updateFavoriteStores(x1);
    }

    static /* synthetic */ boolean access$1102(StoreLocatorController x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$1102", new Object[]{x0, new Boolean(x1)});
        x0.mLocationError = x1;
        return x1;
    }

    static /* synthetic */ void access$1200(StoreLocatorController x0, List x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$1200", new Object[]{x0, x1});
        x0.updateNearbyStoresWithSearchResponse(x1);
    }

    static /* synthetic */ List access$1302(StoreLocatorController x0, List x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$1302", new Object[]{x0, x1});
        x0.mNearbyStores = x1;
        return x1;
    }

    static /* synthetic */ boolean access$1602(StoreLocatorController x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$1602", new Object[]{x0, new Boolean(x1)});
        x0.mIsRefreshing = x1;
        return x1;
    }

    static /* synthetic */ void access$200(StoreLocatorController x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$200", new Object[]{x0});
        x0.clearSelection();
    }

    static /* synthetic */ void access$400(StoreLocatorController x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$400", new Object[]{x0});
        x0.refreshCurrentAndNearbyWithFavorites();
    }

    static /* synthetic */ void access$600(StoreLocatorController x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$600", new Object[]{x0});
        x0.startMainActivity();
    }

    static /* synthetic */ void access$900(StoreLocatorController x0, List x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$900", new Object[]{x0, x1});
        x0.updateMobileOrderingStatus(x1);
    }

    public StoreLocatorController(URLNavigationFragment urlNavigationFragment, boolean mapOnly, boolean currentStoreSelectionMode, boolean autoSelectClosestStore, String nearbySearchAddress, boolean dismissOnPlaceOrder) {
        this.mNavigationFragment = urlNavigationFragment;
        this.mLocationManager = (LocationManager) this.mNavigationFragment.getNavigationActivity().getSystemService(LocationManagerProxy.KEY_LOCATION_CHANGED);
        this.mMapOnly = mapOnly;
        this.mCurrentStoreSelectionMode = currentStoreSelectionMode;
        this.mAutoSelectClosestStore = autoSelectClosestStore;
        this.mNearbySearchAddress = nearbySearchAddress;
        this.mDismissOnPlaceOrder = dismissOnPlaceOrder;
        this.mFavoritesData = new SparseArray();
        this.DEFAULT_RADIUS = Double.valueOf(Configuration.getSharedInstance().getDoubleForKey("modules.storeLocator.defaultSearchRadius"));
    }

    private URLNavigationActivity getNavigationActivity() {
        Ensighten.evaluateEvent(this, "getNavigationActivity", null);
        return this.mNavigationFragment.getNavigationActivity();
    }

    public void start(boolean hasAccessFineLocationPermission) {
        Ensighten.evaluateEvent(this, "start", new Object[]{new Boolean(hasAccessFineLocationPermission)});
        this.mStoreLocatorModule = (StoreLocatorModule) ModuleManager.getModule(StoreLocatorModule.NAME);
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        this.mOrderingModule = (OrderingModule) ModuleManager.getModule("ordering");
        this.mFetchedModules = true;
        if (hasAccessFineLocationPermission) {
            refreshStores();
        }
    }

    public void nickNameChangedOnStoreId(String nickName, int storeId) {
        Ensighten.evaluateEvent(this, "nickNameChangedOnStoreId", new Object[]{nickName, new Integer(storeId)});
        Store currentStore = getCurrentStore();
        if (currentStore.getStoreId() == storeId) {
            currentStore.setStoreFavoriteName(nickName);
            this.mCustomerModule.setCurrentStore(currentStore);
            DataLayerManager.getInstance().setStore(currentStore);
        }
        Store favorite = (Store) this.mFavoriteMap.get(storeId);
        if (favorite != null) {
            favorite.setStoreFavoriteName(nickName);
            this.mFavoriteMap.put(currentStore.getStoreId(), favorite);
        }
        StoreFavoriteInfo info = (StoreFavoriteInfo) this.mFavoritesData.get(storeId);
        if (info != null) {
            info.setFavoriteNickName(nickName);
            this.mFavoritesData.put(currentStore.getStoreId(), info);
        }
        List<Store> favoriteStores = getFavoriteStores();
        for (Store favoriteStore : favoriteStores) {
            if (favoriteStore.getStoreId() == storeId) {
                favoriteStore.setStoreFavoriteName(nickName);
                this.mCustomerModule.setFavoriteStores(favoriteStores);
                break;
            }
        }
        if (this.mNearbyStores != null) {
            for (Store nearbyStore : this.mNearbyStores) {
                if (nearbyStore.getStoreId() == storeId) {
                    nearbyStore.setStoreFavoriteName(nickName);
                }
            }
        }
        for (StoreLocationListener listener : this.mListeners) {
            listener.refreshSelectedStore();
        }
    }

    public void nickNameSelected(String nickName) {
        Ensighten.evaluateEvent(this, "nickNameSelected", new Object[]{nickName});
        this.mSelectedStoreNickname = nickName;
        Store currentStore = getCurrentStore();
        currentStore.setStoreFavoriteName(nickName);
        this.mCustomerModule.setCurrentStore(currentStore);
        Store favorite = (Store) this.mFavoriteMap.get(currentStore.getStoreId());
        if (favorite != null) {
            favorite.setStoreFavoriteName(nickName);
            this.mFavoriteMap.put(currentStore.getStoreId(), favorite);
        }
        StoreFavoriteInfo info = (StoreFavoriteInfo) this.mFavoritesData.get(currentStore.getStoreId());
        if (info != null) {
            info.setFavoriteNickName(nickName);
            this.mFavoritesData.put(currentStore.getStoreId(), info);
        }
        List<Store> favoriteStores = getFavoriteStores();
        for (Store favoriteStore : favoriteStores) {
            if (favoriteStore.getStoreId() == currentStore.getStoreId()) {
                favoriteStore.setStoreFavoriteName(nickName);
                this.mCustomerModule.setFavoriteStores(favoriteStores);
                break;
            }
        }
        if (this.mNearbyStores != null) {
            for (Store nearbyStore : this.mNearbyStores) {
                if (nearbyStore.getStoreId() == currentStore.getStoreId()) {
                    nearbyStore.setStoreFavoriteName(nickName);
                }
            }
        }
        for (StoreLocationListener listener : this.mListeners) {
            listener.refreshSelectedStore();
        }
    }

    public void addListener(StoreLocationListener listener) {
        Ensighten.evaluateEvent(this, "addListener", new Object[]{listener});
        if (this.mListeners.contains(listener)) {
            throw new RuntimeException("Adding LocationListener multiple times!");
        }
        this.mListeners.add(listener);
        Log.d("ADD", "onChange... listener added from class " + listener.getClass().getSimpleName());
    }

    public void removeListener(StoreLocationListener listener) {
        Ensighten.evaluateEvent(this, "removeListener", new Object[]{listener});
        if (this.mListeners.contains(listener)) {
            this.mListeners.remove(listener);
            Log.d("REMOVE", "onChange... listener removed from class " + listener.getClass().getSimpleName());
            return;
        }
        throw new RuntimeException("Removing LocationListener that does not exist!");
    }

    public Store getCurrentStore() {
        Ensighten.evaluateEvent(this, "getCurrentStore", null);
        return this.mCustomerModule == null ? null : this.mCustomerModule.getCurrentStore();
    }

    public Store getSelectedStore() {
        Ensighten.evaluateEvent(this, "getSelectedStore", null);
        if (this.mSelectedStoreId != null) {
            return getStore(this.mSelectedStoreId, this.mSelectedStoreSection);
        }
        return null;
    }

    public List<Store> getFavoriteStores() {
        Ensighten.evaluateEvent(this, "getFavoriteStores", null);
        if (this.mFavoriteMap == null) {
            this.mFavoriteMap = new SparseArray();
            if (!(this.mCustomerModule == null || this.mCustomerModule.getFavoriteStores() == null)) {
                for (Store store : this.mCustomerModule.getFavoriteStores()) {
                    this.mFavoriteMap.put(store.getStoreId(), store);
                }
            }
        }
        if (this.mCustomerModule == null) {
            return null;
        }
        return this.mCustomerModule.getFavoriteStores();
    }

    public List<Store> getNearByStores() {
        Ensighten.evaluateEvent(this, "getNearByStores", null);
        if (!this.isFilterForMobileOrderNeeded) {
            return this.mNearbyStores;
        }
        this.mNearbyFilteredStores = new ArrayList();
        if (ListUtils.isNotEmpty(this.mNearbyStores)) {
            for (Store store : this.mNearbyStores) {
                if (store.hasMobileOrdering().booleanValue()) {
                    this.mNearbyFilteredStores.add(store);
                }
            }
        }
        return this.mNearbyFilteredStores;
    }

    public Integer getSelectedStoreId() {
        Ensighten.evaluateEvent(this, "getSelectedStoreId", null);
        return this.mSelectedStoreId;
    }

    public StoreLocatorSection getSelectedStoreSection() {
        Ensighten.evaluateEvent(this, "getSelectedStoreSection", null);
        return this.mSelectedStoreSection;
    }

    public String getSelectedStoreNickName() {
        Ensighten.evaluateEvent(this, "getSelectedStoreNickName", null);
        return this.mSelectedStoreNickname;
    }

    public void setSelectedStoreNickName(String nickName) {
        Ensighten.evaluateEvent(this, "setSelectedStoreNickName", new Object[]{nickName});
        this.mSelectedStoreNickname = nickName;
    }

    public StoreItemViewState stateForStore(Integer storeId, StoreLocatorSection section) {
        Store currentStore = null;
        Ensighten.evaluateEvent(this, "stateForStore", new Object[]{storeId, section});
        if (section == null) {
            return StoreItemViewState.Normal;
        }
        if (storeId.equals(this.mSelectedStoreId)) {
            return this.mSelectedStoreState;
        }
        switch (section) {
            case Current:
                if (this.mFavoriteMap == null || this.mFavoriteMap.get(storeId.intValue()) == null) {
                    return StoreItemViewState.Current;
                }
                return StoreItemViewState.CurrentAndFavorite;
            case Favorites:
                if (this.mCustomerModule != null) {
                    currentStore = this.mCustomerModule.getCurrentStore();
                }
                if (currentStore == null || !storeId.equals(Integer.valueOf(currentStore.getStoreId()))) {
                    return StoreItemViewState.Favorite;
                }
                return StoreItemViewState.CurrentAndFavorite;
            case Nearby:
                if (this.mCustomerModule != null) {
                    currentStore = this.mCustomerModule.getCurrentStore();
                }
                if (currentStore == null || !storeId.equals(Integer.valueOf(currentStore.getStoreId()))) {
                    return StoreItemViewState.Normal;
                }
                return StoreItemViewState.Current;
            default:
                return null;
        }
    }

    public boolean locationServicesEnabled() {
        Ensighten.evaluateEvent(this, "locationServicesEnabled", null);
        return LocationServicesManager.isLocationEnabled(this.mNavigationFragment.getContext());
    }

    public Location getLastLocation() {
        Ensighten.evaluateEvent(this, "getLastLocation", null);
        return AppUtils.getUserLocation();
    }

    public boolean allowsSelectionWithoutMobileOrdering() {
        Ensighten.evaluateEvent(this, "allowsSelectionWithoutMobileOrdering", null);
        return this.mAllowsSelectionWithoutMobileOrdering || this.mCurrentStoreSelectionMode;
    }

    public boolean allowsFavoritingWithoutMobileOrdering() {
        Ensighten.evaluateEvent(this, "allowsFavoritingWithoutMobileOrdering", null);
        return this.mAllowsFavoritingWithoutMobileOrdering || this.mCurrentStoreSelectionMode;
    }

    public void setAllowsSelectionWithoutMobileOrdering(boolean allowsSelectionWithoutMobileOrdering) {
        Ensighten.evaluateEvent(this, "setAllowsSelectionWithoutMobileOrdering", new Object[]{new Boolean(allowsSelectionWithoutMobileOrdering)});
        this.mAllowsSelectionWithoutMobileOrdering = allowsSelectionWithoutMobileOrdering;
    }

    /* Access modifiers changed, original: protected */
    public void notifyObservers() {
        Store preferredSelectedStore = null;
        Ensighten.evaluateEvent(this, "notifyObservers", null);
        this.mIsRefreshing = false;
        for (StoreLocationListener listener : this.mListeners) {
            listener.onChange(this);
        }
        if (this.mAutoSelectClosestStore) {
            if (ListUtils.isNotEmpty(this.mNearbyStores)) {
                preferredSelectedStore = (Store) this.mNearbyStores.get(0);
            }
            if (preferredSelectedStore != null) {
                selectStore(Integer.valueOf(preferredSelectedStore.getStoreId()), StoreLocatorSection.Nearby, true);
            }
        }
    }

    public void displayFilters(String searchText) {
        Ensighten.evaluateEvent(this, "displayFilters", new Object[]{searchText});
        Bundle extras = new Bundle();
        extras.putInt(URLNavigationActivity.CONTROLLER_PASSER_KEY, DataPasser.getInstance().putData(this));
        extras.putString("search_text", searchText);
        startStoreLocatorFilters(extras);
    }

    public void updateCurrentStore(Integer storeId, StoreLocatorSection section) {
        Store newCurrentStore;
        Ensighten.evaluateEvent(this, "updateCurrentStore", new Object[]{storeId, section});
        Store previousStore = this.mCustomerModule.getCurrentStore();
        if (section == StoreLocatorSection.Favorites) {
            newCurrentStore = (Store) this.mFavoriteMap.get(storeId.intValue());
        } else {
            newCurrentStore = (Store) this.mNearByMap.get(storeId.intValue());
        }
        UIUtils.startActivityIndicator(this.mNavigationFragment.getContext(), (int) C2658R.string.label_progress_loading);
        this.mNavigationFragment.getActivity().getContentResolver().registerContentObserver(CurrentStore.CONTENT_URI, false, new ContentObserver(new Handler()) {
            public void onChange(boolean selfChange) {
                Ensighten.evaluateEvent(this, "onChange", new Object[]{new Boolean(selfChange)});
                super.onChange(selfChange, null);
            }

            public void onChange(boolean selfChange, Uri uri) {
                Ensighten.evaluateEvent(this, "onChange", new Object[]{new Boolean(selfChange), uri});
                if (uri != null && uri.equals(CurrentStore.CONTENT_URI)) {
                    UIUtils.stopActivityIndicator();
                }
            }
        });
        this.mCustomerModule.setCurrentStore(newCurrentStore);
        DataLayerManager.getInstance().setStore(newCurrentStore);
        if (OrderingManager.getInstance().isOrderingAvailable() && !OrderingManager.getInstance().getCurrentOrder().isDelivery()) {
            OrderingManager.getInstance().getCurrentOrder().setStoreId(Integer.toString(newCurrentStore.getStoreId()));
        }
        LocalDataManager.getSharedInstance().set(LocalDataManager.PREF_FIRST_TIME_ORDERING, true);
        String IdString = previousStore == null ? null : Integer.toString(previousStore.getStoreId());
        for (StoreLocationListener listener : this.mListeners) {
            listener.onCurrentStoreChange(this, IdString);
        }
    }

    public void selectStore(Integer storeId, StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "selectStore", new Object[]{storeId, section});
        selectStore(storeId, section, isMapOnly());
    }

    public void selectStore(Integer storeId, StoreLocatorSection section, boolean expand) {
        String previousStoreIdString = null;
        boolean isCurrent = true;
        Ensighten.evaluateEvent(this, "selectStore", new Object[]{storeId, section, new Boolean(expand)});
        Integer previousID = this.mSelectedStoreId;
        if (!(previousID != null || this.mCustomerModule == null || this.mCustomerModule.getCurrentStore() == null)) {
            previousID = Integer.valueOf(this.mCustomerModule.getCurrentStore().getStoreId());
        }
        StoreLocatorSection previousSection = this.mSelectedStoreSection;
        this.mSelectedStoreId = storeId;
        this.mSelectedStoreSection = section;
        if (storeId == null) {
            this.mSelectedStoreState = null;
        } else {
            this.mSelectedStoreState = StoreItemViewState.Normal;
            Store store = getStore(storeId, section);
            if (store != null) {
                boolean isFavorite;
                if (store.getStoreFavoriteId() != null) {
                    isFavorite = true;
                } else {
                    isFavorite = false;
                }
                if (!expand) {
                    if (this.mCustomerModule.getCurrentStore() == null || !storeId.equals(Integer.valueOf(this.mCustomerModule.getCurrentStore().getStoreId()))) {
                        isCurrent = false;
                    }
                    if (isFavorite) {
                        this.mSelectedStoreState = isCurrent ? StoreItemViewState.CurrentAndFavorite : StoreItemViewState.Favorite;
                    } else {
                        this.mSelectedStoreState = isCurrent ? StoreItemViewState.Current : StoreItemViewState.Normal;
                    }
                } else if (isFavorite) {
                    this.mSelectedStoreState = this.mCurrentStoreSelectionMode ? StoreItemViewState.ExpandedSelectCurrent : StoreItemViewState.ExpandedFavoritePlaceOrder;
                } else {
                    this.mSelectedStoreState = this.mCurrentStoreSelectionMode ? StoreItemViewState.ExpandedSelectCurrent : StoreItemViewState.ExpandedPlaceOrder;
                }
            }
        }
        if (previousID != null) {
            previousStoreIdString = previousID.toString();
        }
        for (StoreLocationListener listener : this.mListeners) {
            listener.onSelectedStoreChange(this, previousStoreIdString, previousSection, expand);
        }
    }

    public void addStoreToFavorites(Integer storeId, StoreLocatorSection section, String nickName, final AsyncListener<Boolean> successListener) {
        Ensighten.evaluateEvent(this, "addStoreToFavorites", new Object[]{storeId, section, nickName, successListener});
        if (section == StoreLocatorSection.Favorites || !(this.mFavoriteMap == null || this.mFavoriteMap.get(storeId.intValue()) == null)) {
            UIUtils.stopActivityIndicator();
            if (successListener != null) {
                successListener.onResponse(Boolean.valueOf(true), null, null);
            }
            notifyObservers();
            return;
        }
        Store favorite;
        if (this.mLastFavorite == null || !storeId.equals(Integer.valueOf(this.mLastFavorite.getStoreId()))) {
            favorite = getStore(storeId, section);
        } else {
            favorite = this.mLastFavorite;
        }
        if (favorite != null) {
            favorite.setStoreFavoriteName(nickName);
            List<Store> storesToAdd = new ArrayList();
            storesToAdd.add(favorite);
            ServiceUtils.getSharedInstance().removeFavoriteStoresCache();
            this.mCustomerModule.addFavoriteStores(storesToAdd, this.mCustomerModule.getCurrentProfile(), new AsyncListener<List<Store>>() {
                public void onResponse(List<Store> response, AsyncToken token, AsyncException exception) {
                    boolean z = true;
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    UIUtils.stopActivityIndicator();
                    if (exception != null) {
                        AsyncException.report(exception);
                        favorite.setStoreFavoriteName(null);
                    } else {
                        StoreLocatorController.access$000(StoreLocatorController.this, response);
                        Store original = (Store) Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$100", new Object[]{StoreLocatorController.this}).get(favorite.getStoreId());
                        if (original != null) {
                            favorite.setHasMobileOrdering(original.hasMobileOrdering());
                        }
                        StoreLocatorController.access$200(StoreLocatorController.this);
                        StoreLocatorController.this.notifyObservers();
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
        MCDLog.temp("FAVORITE STORE null");
    }

    public void removeStoreFromFavorites(Integer storeId, StoreLocatorSection section, final AsyncListener<Boolean> successListener) {
        Ensighten.evaluateEvent(this, "removeStoreFromFavorites", new Object[]{storeId, section, successListener});
        if (section != StoreLocatorSection.Favorites && this.mFavoriteMap.get(storeId.intValue()) == null) {
            Store store = getStore(storeId, section);
            if (store != null) {
                store.setStoreFavoriteName(null);
                store.setStoreFavoriteId(null);
                if (successListener != null) {
                    successListener.onResponse(Boolean.valueOf(true), null, null);
                }
                notifyObservers();
            }
        } else if (this.mFavoriteMap.get(storeId.intValue()) != null) {
            final Store favorite = (Store) this.mFavoriteMap.get(storeId.intValue());
            this.mLastFavorite = favorite;
            List<Integer> ids = new ArrayList();
            ids.add(favorite.getStoreFavoriteId());
            ServiceUtils.getSharedInstance().removeFavoriteStoresCache();
            this.mCustomerModule.deleteFavoriteStores(ids, this.mCustomerModule.getCurrentProfile(), new AsyncListener<List<Store>>() {
                public void onResponse(List<Store> response, AsyncToken token, AsyncException exception) {
                    boolean z = true;
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    UIUtils.stopActivityIndicator();
                    if (exception != null) {
                        AsyncException.report(exception);
                    } else {
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$100", new Object[]{StoreLocatorController.this}).remove(favorite.getStoreId());
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$300", new Object[]{StoreLocatorController.this}).remove(favorite.getStoreId());
                        StoreLocatorController.access$400(StoreLocatorController.this);
                        StoreLocatorController.access$200(StoreLocatorController.this);
                        StoreLocatorController.this.notifyObservers();
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

    public void renameStoreInFavorites(Integer storeId, StoreLocatorSection section, String nickName, AsyncListener<Boolean> successListener) {
        Ensighten.evaluateEvent(this, "renameStoreInFavorites", new Object[]{storeId, section, nickName, successListener});
        if (section != StoreLocatorSection.Favorites && section != StoreLocatorSection.Current && getStore(storeId, section).getStoreFavoriteName() == null) {
            MCDLog.error("StoreLocatorController", "Attempting to rename non-favorite");
        } else if (this.mFavoriteMap.get(storeId.intValue()) != null) {
            final Store favoriteStore = (Store) this.mFavoriteMap.get(storeId.intValue());
            final String previousNickName = favoriteStore.getStoreFavoriteName();
            favoriteStore.setStoreFavoriteName(nickName);
            ServiceUtils.getSharedInstance().removeFavoriteStoresCache();
            final Integer num = storeId;
            final AsyncListener<Boolean> asyncListener = successListener;
            this.mCustomerModule.renameFavoriteStores(Arrays.asList(new Store[]{favoriteStore}), this.mCustomerModule.getCurrentProfile(), new AsyncListener<List<Store>>() {
                public void onResponse(List<Store> response, AsyncToken token, AsyncException exception) {
                    boolean z = true;
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    UIUtils.stopActivityIndicator();
                    if (exception != null) {
                        AsyncException.report(exception);
                        favoriteStore.setStoreFavoriteName(previousNickName);
                    } else {
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$100", new Object[]{StoreLocatorController.this}).remove(num.intValue());
                        StoreLocatorController.access$000(StoreLocatorController.this, response);
                        StoreLocatorController.access$200(StoreLocatorController.this);
                        StoreLocatorController.this.notifyObservers();
                    }
                    if (asyncListener != null) {
                        AsyncListener asyncListener = asyncListener;
                        if (exception != null) {
                            z = false;
                        }
                        asyncListener.onResponse(Boolean.valueOf(z), null, exception);
                    }
                }
            });
        }
    }

    private void clearSelection() {
        Ensighten.evaluateEvent(this, "clearSelection", null);
        this.mSelectedStoreId = null;
        this.mSelectedStoreSection = null;
        this.mSelectedStoreNickname = null;
    }

    private void updateFavoriteStores(List<Store> stores) {
        Ensighten.evaluateEvent(this, "updateFavoriteStores", new Object[]{stores});
        SparseArray<Store> storeMap = new SparseArray();
        for (Store store : stores) {
            if (this.mFavoriteMap != null) {
                Store existingStore = (Store) this.mFavoriteMap.get(store.getStoreId());
                if (existingStore != null) {
                    store.setHasMobileOrdering(existingStore.hasMobileOrdering());
                }
            }
            storeMap.put(store.getStoreId(), store);
        }
        this.mFavoriteMap = storeMap;
        refreshCurrentAndNearbyWithFavorites();
    }

    public void displayStoreDetails(Integer storeId, StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "displayStoreDetails", new Object[]{storeId, section});
        Store store = getStore(storeId, section);
        if (store != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("extra_store_detail", store);
            bundle.putInt("extra_store_section", section.ordinal());
            bundle.putBoolean("extra_store_controller", true);
            StoresManager.getInstance().setController(this);
            this.mNavigationFragment.startActivityForResult(StoreDetailsActivity.class, JiceArgs.EVENT_CHECK_IN, bundle, 29);
            return;
        }
        AsyncException.report(getNavigationActivity().getString(C2658R.string.dialog_store_details_unavailable));
    }

    public void beginOrderForStore(Integer storeId, StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "beginOrderForStore", new Object[]{storeId, section});
        if (getNavigationActivity() == null) {
            return;
        }
        if (this.mDismissOnPlaceOrder) {
            this.mNavigationFragment.getActivity().setResult(-1);
            this.mNavigationFragment.getActivity().finish();
            return;
        }
        startOrderActivity();
    }

    public void requestUpdateStoresByCurrentLocation(boolean showActivityIndicator) {
        Ensighten.evaluateEvent(this, "requestUpdateStoresByCurrentLocation", new Object[]{new Boolean(showActivityIndicator)});
        updateStoresByLocation(showActivityIndicator);
    }

    public void requestUpdateStoresByCurrentStore(boolean showActivityIndicator) {
        Ensighten.evaluateEvent(this, "requestUpdateStoresByCurrentStore", new Object[]{new Boolean(showActivityIndicator)});
        updateStoresByCurrentStore(showActivityIndicator);
    }

    public void requestUpdateStoresByAddress(String address, boolean showActivityIndicator) {
        Ensighten.evaluateEvent(this, "requestUpdateStoresByAddress", new Object[]{address, new Boolean(showActivityIndicator)});
        updateStoresByAddress(address, showActivityIndicator);
    }

    public void requestUpdateStoresByScrolledLocation(LatLng newLocation) {
        Ensighten.evaluateEvent(this, "requestUpdateStoresByScrolledLocation", new Object[]{newLocation});
        updateStoresByLatLng(newLocation);
    }

    public List<String> getNickNames() {
        Ensighten.evaluateEvent(this, "getNickNames", null);
        List<String> presets = (List) Configuration.getSharedInstance().getValueForKey("interface.customer.presetStoreNames");
        List<String> nicknames = new ArrayList();
        if (presets != null) {
            for (String preset : presets) {
                nicknames.add(UIUtils.getStringByName(getNavigationActivity(), preset));
            }
        }
        List<String> availableNames = new ArrayList(nicknames);
        if (!(this.mCustomerModule == null || this.mCustomerModule.getFavoriteStores() == null)) {
            for (Store store : this.mCustomerModule.getFavoriteStores()) {
                availableNames.remove(store.getStoreFavoriteName());
            }
        }
        return availableNames;
    }

    public int getSelectMapPinResID(Integer storeId) {
        Ensighten.evaluateEvent(this, "getSelectMapPinResID", new Object[]{storeId});
        return getSelectedMapIconResID();
    }

    public int getMapPinResID(Integer storeId) {
        Ensighten.evaluateEvent(this, "getMapPinResID", new Object[]{storeId});
        return getMapIconResID();
    }

    private int getSelectedMapIconResID() {
        Ensighten.evaluateEvent(this, "getSelectedMapIconResID", null);
        if (SELECTED_MAP_ICON_RES_ID == 0) {
            SELECTED_MAP_ICON_RES_ID = getDrawableResIDByString("interface.storelocator.selectedMapIcon", "map_icon_red");
        }
        return SELECTED_MAP_ICON_RES_ID;
    }

    private int getMapIconResID() {
        Ensighten.evaluateEvent(this, "getMapIconResID", null);
        if (MAP_ICON_RES_ID == 0) {
            MAP_ICON_RES_ID = getDrawableResIDByString("interface.storelocator.unselectedMapIcon", "map_icon_gray");
        }
        return MAP_ICON_RES_ID;
    }

    /* Access modifiers changed, original: protected */
    public int getDrawableResIDByString(String configKey, String defaultResString) {
        Ensighten.evaluateEvent(this, "getDrawableResIDByString", new Object[]{configKey, defaultResString});
        String resString = (String) Configuration.getSharedInstance().getValueForKey(configKey);
        if (resString == null) {
            resString = defaultResString;
        }
        return UIUtils.getDrawableIdByName(getNavigationActivity(), resString);
    }

    public void eatHereClicked(Integer storeId, StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "eatHereClicked", new Object[]{storeId, section});
        Store store = getStore(storeId, section);
        if (store != null) {
            AnalyticsUtils.trackStoreSelection(store);
            if (ListUtils.isEmpty(store.getStoreOperatingHours()) || TextUtils.isEmpty(Utils.getCloseStatus(store, this.mNavigationFragment.getContext()))) {
                updateCurrentStore(storeId, section);
                selectStore(storeId, section, shouldExpand(store));
                return;
            }
            MCDAlertDialogBuilder.withContext(getNavigationActivity()).setPositiveButton((int) C2658R.string.f6083ok, null).setMessage((int) C2658R.string.store_closed_title).create().show();
        }
    }

    public void myMcDonaldsClicked(Integer storeId, StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "myMcDonaldsClicked", new Object[]{storeId, section});
        Store store = getStore(storeId, section);
        if (store != null) {
            selectStore(storeId, section, shouldExpand(store));
        }
    }

    private boolean shouldExpand(Store store) {
        Ensighten.evaluateEvent(this, "shouldExpand", new Object[]{store});
        return store.canBeFavorited() || this.mCurrentStoreSelectionMode || store.getStoreFavoriteId() != null || isMapOnly();
    }

    public void saveToFavoritesClicked(Integer storeId, StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "saveToFavoritesClicked", new Object[]{storeId, section});
        if (!this.mCurrentStoreSelectionMode) {
            this.mSelectedStoreState = StoreItemViewState.ExpandedNickname;
            setupNickname();
        } else if (getNavigationActivity() != null) {
            MCDAlertDialogBuilder.withContext(getNavigationActivity()).setPositiveButton((int) C2658R.string.f6083ok, new C37225()).setTitle((int) C2658R.string.restaurant_saved).setMessage((int) C2658R.string.youre_all_set).create().show();
            trackAddFavorite(storeId);
            if (this.mCustomerModule != null) {
                Store newStore = getStore(storeId, section);
                this.mCustomerModule.setCurrentStore(newStore);
                DataLayerManager.getInstance().setStore(newStore);
            }
            LocalBroadcastManager.getInstance(getNavigationActivity()).sendBroadcast(new Intent("com.mcdonalds.app.REMOVE_FIND_STORE"));
        }
    }

    private void trackAddFavorite(Integer storeId) {
        Ensighten.evaluateEvent(this, "trackAddFavorite", new Object[]{storeId});
        AnalyticsUtils.trackOnClickEvent("/firstload", "Restaurant Saved Alert");
        SparseArray customArgs = new SparseArray();
        customArgs.put(1, String.valueOf(storeId));
        if (this.mCustomerModule == null || this.mCustomerModule.getCurrentProfile() == null) {
            customArgs.put(2, "Guest");
        } else {
            customArgs.put(2, String.valueOf(this.mCustomerModule.getCurrentProfile().getCustomerId()));
        }
        AnalyticsUtils.trackOnClickEvent("/firstload", "Continue", customArgs);
    }

    public void addToFavoritesClicked(Integer storeId, StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "addToFavoritesClicked", new Object[]{storeId, section});
        Store store = getStore(storeId, section);
        AnalyticsUtils.trackOnClickEvent("/restaurant-locator", "Save to favorites");
        if (this.mSelectedStoreNickname == null) {
            return;
        }
        if (!this.mCustomerModule.isLoggedIn()) {
            Bundle arguments = new Bundle();
            arguments.putBoolean("EXTRA_SAVING_FAVORITE", true);
            arguments.putInt("StoreLocatorFragment.SAVING_FAVORITE_ID", storeId.intValue());
            arguments.putSerializable("saving_fav_section", section);
            arguments.putSerializable("StoreLocatorFragment.SAVING_FAVORITE_NICKNAME", this.mSelectedStoreNickname);
            startSignInActivity(arguments);
        } else if (store == null || store.getStoreFavoriteName() == null) {
            UIUtils.startActivityIndicator(getNavigationActivity(), getNavigationActivity().getString(C2658R.string.saving_favorite));
            addStoreToFavorites(storeId, section, this.mSelectedStoreNickname, null);
        } else {
            UIUtils.startActivityIndicator(getNavigationActivity(), getNavigationActivity().getString(C2658R.string.renaming_favorite_store));
            if (this.mFavoriteMap.get(storeId.intValue()) == null) {
                addStoreToFavorites(storeId, section, this.mSelectedStoreNickname, null);
            } else {
                renameStoreInFavorites(storeId, section, this.mSelectedStoreNickname, null);
            }
        }
    }

    public void infoClicked(Integer storeId, StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "infoClicked", new Object[]{storeId, section});
        AnalyticsUtils.trackOnClickEvent("/restaurant-locator", "Info Icon");
        displayStoreDetails(storeId, section);
    }

    public void skipClicked(Integer storeId, StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "skipClicked", new Object[]{storeId, section});
        AnalyticsUtils.trackSkipFavoritesButton();
        selectStore(null, null);
    }

    public void placeOrderClicked(Integer storeId, StoreLocatorSection section) {
        AnalyticsArgs args;
        Ensighten.evaluateEvent(this, "placeOrderClicked", new Object[]{storeId, section});
        if (this.mCurrentStoreSelectionMode) {
            args = new ArgBuilder().setCategory("/firstload").setAction("On click").setLabel("Choose another").build();
        } else {
            SparseArray<String> customArgs = new SparseArray();
            customArgs.put(1, storeId.toString());
            args = new ArgBuilder().setCategory("/restaurant-locator").setAction("On click").setLabel("Place order").setCustom(customArgs).build();
        }
        Analytics.track(AnalyticType.Event, args);
        if (this.mCurrentStoreSelectionMode) {
            selectStore(null, null);
            requestUpdateStoresByCurrentLocation(true);
            return;
        }
        beginOrderForStore(storeId, section);
    }

    private void startOrderActivity() {
        Ensighten.evaluateEvent(this, "startOrderActivity", null);
        OrderingManager.getInstance().getCurrentOrder().setIsDelivery(false);
        LocalDataManager.getSharedInstance().setFirstTimeOrdering(false);
        if (this.mNavigationFragment.getNavigationActivity() instanceof MenuActivity) {
            this.mNavigationFragment.showFragment("menu_grid");
        } else {
            this.mNavigationFragment.startActivity(MenuActivity.class, "menu_grid");
        }
    }

    private void startMainActivity() {
        Ensighten.evaluateEvent(this, "startMainActivity", null);
        if (this.mNavigationFragment.getNavigationActivity() instanceof MainActivity) {
            this.mNavigationFragment.showFragment("dashboard");
        } else {
            this.mNavigationFragment.startActivity(MainActivity.class, "dashboard");
        }
    }

    private void startSignInActivity(Bundle arguments) {
        Ensighten.evaluateEvent(this, "startSignInActivity", new Object[]{arguments});
        this.mNavigationFragment.startActivity(SignInActivity.class, JiceArgs.EVENT_CHECK_IN, arguments);
    }

    private void startStoreLocatorFilters(Bundle bundle) {
        Ensighten.evaluateEvent(this, "startStoreLocatorFilters", new Object[]{bundle});
        this.mNavigationFragment.startActivityForResult(StoreLocatorFiltersActivity.class, bundle, 30001);
    }

    public void nicknameClicked(Integer storeId, StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "nicknameClicked", new Object[]{storeId, section});
        Activity activity = this.mNavigationFragment.getActivity();
        activity.startActivityForResult(new Intent(activity, StoreNicknamingActivity.class), 22);
    }

    public void removeFromFavoritesClicked(Integer storeId, StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "removeFromFavoritesClicked", new Object[]{storeId, section});
        AnalyticsUtils.trackOnClickEvent("/restaurant-locator", "Remove from favorites");
        removeStoreFromFavorites(storeId, section, null);
    }

    public OfferState getOfferState(Integer storeId, StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "getOfferState", new Object[]{storeId, section});
        return OfferState.NO_OFFER;
    }

    private void refreshStores() {
        Ensighten.evaluateEvent(this, "refreshStores", null);
        if (!this.mIsRefreshing) {
            this.mIsRefreshing = true;
            refreshCurrentStoreAndFavorites(true);
            updateStoreStatus();
        }
    }

    private void updateStoreStatus() {
        Ensighten.evaluateEvent(this, "updateStoreStatus", null);
        Bundle args = getNavigationActivity().getIntent().getExtras();
        if (args != null && args.getBoolean("EXTRA_SAVING_FAVORITE")) {
            this.mSelectedStoreId = Integer.valueOf(args.getInt("StoreLocatorFragment.SAVING_FAVORITE_ID"));
            this.mSelectedStoreState = StoreItemViewState.ExpandedNickname;
            setupNickname();
            args.remove("EXTRA_SAVING_FAVORITE");
            args.remove("StoreLocatorFragment.SAVING_FAVORITE_ID");
        }
    }

    private void setupNickname() {
        Ensighten.evaluateEvent(this, "setupNickname", null);
        Store store = getSelectedStore();
        if (store != null) {
            if (store.getStoreFavoriteName() == null) {
                List<String> names = getNickNames();
                if (ListUtils.isNotEmpty(names)) {
                    this.mSelectedStoreNickname = (String) names.get(0);
                }
            }
            for (StoreLocationListener listener : this.mListeners) {
                listener.refreshSelectedStore();
            }
        }
    }

    private void refreshCurrentStoreAndFavorites(boolean showActivityIndicator) {
        Ensighten.evaluateEvent(this, "refreshCurrentStoreAndFavorites", new Object[]{new Boolean(showActivityIndicator)});
        ArrayList<String> storeIDs = new ArrayList();
        String homeStoreIdString = null;
        if (!(this.mCustomerModule == null || this.mCustomerModule.getCurrentStore() == null)) {
            homeStoreIdString = String.valueOf(this.mCustomerModule.getCurrentStore().getStoreId());
        }
        if (homeStoreIdString != null) {
            this.mStoreLocatorModule.getStoreForId(homeStoreIdString, new C37236());
        }
        if (!(this.mCustomerModule == null || this.mCustomerModule.getCurrentProfile() == null || !this.mCustomerModule.isLoggedIn())) {
            ServiceUtils.getSharedInstance().retrieveFavoriteStores(this.mCustomerModule.getCurrentProfile(), new C37247());
        }
        if (this.mNearbySearchAddress != null) {
            updateStoresByAddress(this.mNearbySearchAddress, showActivityIndicator);
        } else {
            updateStoresByLocation(showActivityIndicator);
        }
    }

    /* Access modifiers changed, original: protected */
    public void updateStoresByLatLng(LatLng newLocation) {
        Ensighten.evaluateEvent(this, "updateStoresByLatLng", new Object[]{newLocation});
        UIUtils.startActivityIndicator(getNavigationActivity(), (int) C2658R.string.sl_retrieve_stores);
        this.mStoreLocatorModule.getStoresNearLatLongWithinRadius(Double.valueOf(newLocation.latitude), Double.valueOf(newLocation.longitude), this.DEFAULT_RADIUS, this.activeFilters, new C37268());
    }

    /* Access modifiers changed, original: protected */
    public void updateStoresByLocation(boolean showActivityIndicator) {
        Ensighten.evaluateEvent(this, "updateStoresByLocation", new Object[]{new Boolean(showActivityIndicator)});
        if (showActivityIndicator) {
            UIUtils.startActivityIndicator(getNavigationActivity(), (int) C2658R.string.sl_retrieve_stores);
        }
        this.mStoreLocatorModule.getStoresNearCurrentLocationWithinRadius(this.DEFAULT_RADIUS, this.activeFilters, new C37289());
    }

    private void updateStoresByCurrentStore(boolean showActivityIndicator) {
        Ensighten.evaluateEvent(this, "updateStoresByCurrentStore", new Object[]{new Boolean(showActivityIndicator)});
        if (this.mCustomerModule.getCurrentStore() != null) {
            if (showActivityIndicator) {
                UIUtils.startActivityIndicator(getNavigationActivity(), (int) C2658R.string.sl_retrieve_stores);
            }
            this.mStoreLocatorModule.getStoresNearLatLongWithinRadius(Double.valueOf(this.mCustomerModule.getCurrentStore().getLatitude()), Double.valueOf(this.mCustomerModule.getCurrentStore().getLongitude()), this.DEFAULT_RADIUS, this.activeFilters, new C371110());
            return;
        }
        getNavigationActivity().finish();
    }

    private void updateStoresByAddress(final String address, boolean showActivityIndicator) {
        Ensighten.evaluateEvent(this, "updateStoresByAddress", new Object[]{address, new Boolean(showActivityIndicator)});
        if (showActivityIndicator) {
            UIUtils.startActivityIndicator(getNavigationActivity(), (int) C2658R.string.sl_retrieve_stores);
        }
        this.mIgnoreUserLocation = true;
        if (this.mStoreLocatorModule == null || address == null) {
            UIUtils.stopActivityIndicator();
        } else {
            this.mStoreLocatorModule.getStoresNearAddressWithinRadius(address, this.DEFAULT_RADIUS, this.activeFilters, new AsyncListener<List<Store>>() {

                /* renamed from: com.mcdonalds.app.storelocator.StoreLocatorController$11$1 */
                class C37141 implements OnClickListener {
                    C37141() {
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        Ensighten.evaluateEvent(this, "onClick", new Object[]{dialogInterface, new Integer(i)});
                        StoreLocatorController.this.notifyObservers();
                    }
                }

                public void onResponse(List<Store> response, AsyncToken token, AsyncException exception) {
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$500", new Object[]{StoreLocatorController.this}) != null) {
                        if (exception == null) {
                            if (response != null) {
                                DataLayerManager.getInstance().setSearchTerm(address, Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$1500", new Object[]{StoreLocatorController.this}), null, response.size());
                            }
                            StoreLocatorController.access$1102(StoreLocatorController.this, false);
                            StoreLocatorController.access$1200(StoreLocatorController.this, response);
                            if (response.isEmpty()) {
                                String notFound = Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$500", new Object[]{StoreLocatorController.this}).getString(C2658R.string.sl_no_stores_found);
                                UIUtils.showGlobalAlertDialog(Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$500", new Object[]{StoreLocatorController.this}), Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$500", new Object[]{StoreLocatorController.this}).getString(C2658R.string.sl_no_stores_found_title), notFound, new C37141());
                                DataLayerManager.getInstance().recordError("No stores");
                            } else if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$800", new Object[]{StoreLocatorController.this}) != null) {
                                StoreLocatorController.access$900(StoreLocatorController.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorController", "access$1300", new Object[]{StoreLocatorController.this}));
                            } else {
                                StoreLocatorController.this.notifyObservers();
                            }
                        } else {
                            AsyncException.report(exception);
                            StoreLocatorController.access$1102(StoreLocatorController.this, true);
                        }
                    }
                    UIUtils.stopActivityIndicator();
                }
            });
        }
    }

    public void sortStoresByDistance(List<Store> stores) {
        Ensighten.evaluateEvent(this, "sortStoresByDistance", new Object[]{stores});
        final Location location = getLastLocation();
        if (location != null && stores != null) {
            Collections.sort(stores, new Comparator<Store>() {
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

    private void updateNearbyStoresWithSearchResponse(List<Store> nearbySearchResponse) {
        Store currentStore;
        Ensighten.evaluateEvent(this, "updateNearbyStoresWithSearchResponse", new Object[]{nearbySearchResponse});
        if (nearbySearchResponse.isEmpty() && this.mCustomerModule.getCurrentStore() != null) {
            currentStore = this.mCustomerModule.getCurrentStore();
            this.mNearbyStores = new ArrayList();
        } else if (nearbySearchResponse.isEmpty() || this.mCustomerModule.getCurrentStore() != null) {
            this.mNearbyStores = new ArrayList(nearbySearchResponse);
        } else {
            Store newStore = (Store) nearbySearchResponse.get(0);
            this.mCustomerModule.setCurrentStore(newStore);
            DataLayerManager.getInstance().setStore(newStore);
            this.mNearbyStores = new ArrayList(nearbySearchResponse);
        }
        if (ConfigurationUtils.shouldFilterStoreResultsUsingGeneralStatus()) {
            List<Store> openStores = new ArrayList();
            for (Store store : this.mNearbyStores) {
                int day = Calendar.getInstance().get(7);
                if (day - 1 < store.getStoreOperatingHours().size()) {
                    String[] operatingHours = (String[]) store.getStoreOperatingHours().get(day - 1);
                    if (!(!store.isGeneralStatusIsOpen() || TextUtils.isEmpty(operatingHours[0]) || TextUtils.isEmpty(operatingHours[1]))) {
                        openStores.add(store);
                    }
                }
            }
            this.mNearbyStores.clear();
            this.mNearbyStores.addAll(openStores);
        }
        this.mNearByMap = new SparseArray();
        for (Store store2 : this.mNearbyStores) {
            this.mNearByMap.put(store2.getStoreId(), store2);
            currentStore = this.mCustomerModule.getCurrentStore();
            if (currentStore != null && currentStore.equals(store2)) {
                store2.setStoreHours(currentStore.getStoreHours());
            }
        }
        refreshCurrentAndNearbyWithFavorites();
    }

    /* Access modifiers changed, original: protected */
    public Store getStore(Integer storeId, StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "getStore", new Object[]{storeId, section});
        if (section == StoreLocatorSection.Current) {
            return getCurrentStore();
        }
        if (section == StoreLocatorSection.Favorites && this.mFavoriteMap != null) {
            return (Store) this.mFavoriteMap.get(storeId.intValue());
        }
        if (section != StoreLocatorSection.Nearby || this.mNearByMap == null) {
            return null;
        }
        return (Store) this.mNearByMap.get(storeId.intValue());
    }

    private void updateMobileOrderingStatus(List<Store> stores) {
        Ensighten.evaluateEvent(this, "updateMobileOrderingStatus", new Object[]{stores});
        if (this.mOrderingModule != null && stores != null) {
            List<Store> unknownStatusStores = new ArrayList();
            for (Store store : stores) {
                if (store.hasMobileOrdering() == null) {
                    unknownStatusStores.add(store);
                }
            }
            if (unknownStatusStores.isEmpty()) {
                notifyObservers();
            } else {
                this.mOrderingModule.checkStoreListForOrderingSupport(unknownStatusStores, getLastLocation(), new C371613());
            }
        }
    }

    private void refreshCurrentAndNearbyWithFavorites() {
        Ensighten.evaluateEvent(this, "refreshCurrentAndNearbyWithFavorites", null);
        if (this.mFavoriteMap != null) {
            if (this.mCustomerModule != null) {
                Store current = this.mCustomerModule.getCurrentStore();
                if (current != null) {
                    updateFavoriteInfo(current);
                }
            }
            if (this.mNearbyStores != null) {
                for (Store nearby : this.mNearbyStores) {
                    updateFavoriteInfo(nearby);
                }
            }
        }
    }

    private void updateFavoriteInfo(Store store) {
        Ensighten.evaluateEvent(this, "updateFavoriteInfo", new Object[]{store});
        Store favorite = (Store) this.mFavoriteMap.get(store.getStoreId());
        if (favorite != null) {
            if (favorite.getStoreFavoriteName() == null) {
                StoreFavoriteInfo info = (StoreFavoriteInfo) this.mFavoritesData.get(favorite.getStoreId());
                if (info != null) {
                    favorite.setStoreFavoriteId(Integer.valueOf(info.getFavoriteId()));
                    favorite.setStoreFavoriteName(info.getFavoriteNickName());
                }
            }
            store.setStoreFavoriteName(favorite.getStoreFavoriteName());
            store.setStoreFavoriteId(favorite.getStoreFavoriteId());
            favorite.setDistance(store.getDistance());
            favorite.setStoreHours(store.getStoreHours());
            return;
        }
        store.setStoreFavoriteId(null);
        store.setStoreFavoriteName(null);
    }

    public void filterNearbyBasedOnMobileOrdering() {
        Ensighten.evaluateEvent(this, "filterNearbyBasedOnMobileOrdering", null);
        this.isFilterForMobileOrderNeeded = true;
        notifyObservers();
    }

    public void undoFilterBasedOnMobileOrdering() {
        Ensighten.evaluateEvent(this, "undoFilterBasedOnMobileOrdering", null);
        this.isFilterForMobileOrderNeeded = false;
        notifyObservers();
    }

    public boolean isMapOnly() {
        Ensighten.evaluateEvent(this, "isMapOnly", null);
        return this.mMapOnly;
    }

    public boolean shouldIgnoreUserLocation() {
        Ensighten.evaluateEvent(this, "shouldIgnoreUserLocation", null);
        return this.mIgnoreUserLocation;
    }

    public void setIgnoreUserLocation(boolean ignore) {
        Ensighten.evaluateEvent(this, "setIgnoreUserLocation", new Object[]{new Boolean(ignore)});
        this.mIgnoreUserLocation = ignore;
    }

    public void setMapOnly(boolean mapOnly) {
        Ensighten.evaluateEvent(this, "setMapOnly", new Object[]{new Boolean(mapOnly)});
        this.mMapOnly = mapOnly;
    }

    public boolean isCurrentStoreSelectionMode() {
        Ensighten.evaluateEvent(this, "isCurrentStoreSelectionMode", null);
        return this.mCurrentStoreSelectionMode;
    }

    public boolean isAutoSelectClosestStore() {
        Ensighten.evaluateEvent(this, "isAutoSelectClosestStore", null);
        return this.mAutoSelectClosestStore;
    }

    public StoreLocatorModule getStoreLocatorModule() {
        Ensighten.evaluateEvent(this, "getStoreLocatorModule", null);
        return this.mStoreLocatorModule;
    }

    public List<String> getActiveFilters() {
        Ensighten.evaluateEvent(this, "getActiveFilters", null);
        return this.activeFilters;
    }

    public void setActiveFilters(List<String> newfilters) {
        Ensighten.evaluateEvent(this, "setActiveFilters", new Object[]{newfilters});
        this.activeFilters = newfilters;
    }
}
