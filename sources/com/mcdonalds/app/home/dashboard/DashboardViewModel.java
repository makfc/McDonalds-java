package com.mcdonalds.app.home.dashboard;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.SparseArray;
import com.ensighten.Ensighten;
import com.facebook.internal.NativeProtocol;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.home.HomeListItem;
import com.mcdonalds.app.model.Promo;
import com.mcdonalds.app.model.PromoResponse;
import com.mcdonalds.app.net.JsonGetRequest;
import com.mcdonalds.app.offers.OfferSection;
import com.mcdonalds.app.offers.OfferSection.OfferSections;
import com.mcdonalds.app.ordering.OrderUtils;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.FavoriteStoreUtils;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.app.util.OfferUtils;
import com.mcdonalds.app.util.SafeLog;
import com.mcdonalds.app.util.ServiceUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncCounter;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.delivery.DeliveryModule;
import com.mcdonalds.sdk.modules.models.CustomerOrder;
import com.mcdonalds.sdk.modules.models.DeliveryStatusResponse;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.modules.models.Offer.OfferType;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderOffer;
import com.mcdonalds.sdk.modules.models.StoreFavoriteInfo;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.modules.storelocator.StoreLocatorModule;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.services.network.RequestManagerServiceConnection;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class DashboardViewModel {
    private static final String LOG_TAG = DashboardViewModel.class.getSimpleName();
    private static DashboardViewModel sInstance;
    private List<Store> mALittleFurtherAwayStores = new ArrayList();
    private List<Offer> mALittleFurtherOffers;
    private List<Offer> mActiveOffers = new ArrayList();
    private boolean mAddedToOrder;
    private Context mContext;
    private List<Offer> mCurrentStoreOffers;
    private CustomerModule mCustomerModule = ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME));
    private Double mDefaultRadius;
    private SparseArray<StoreFavoriteInfo> mFavoriteInfoArray;
    private List<Offer> mFavoriteStoreOffers;
    private List<Store> mFavoriteStores = new ArrayList();
    private boolean mLoadingPromos;
    private boolean mMySurprisesMode;
    private List<Offer> mNearbyOffers;
    private boolean mNearbyOffersEnabled;
    private Double mNearbyRadius;
    private List<Store> mNearbyStores = new ArrayList();
    private ArrayList<OfferSection> mOfferSections;
    final Comparator<Offer> mOffersComparator = new C23875();
    private int mOffersCount;
    private List<Promo> mPromoList;
    private AsyncListener<List<Promo>> mPromosExternalListener;
    private AsyncListener<PromoResponse> mPromosResponseListener = new C23897();
    private StoreLocatorModule mStoreLocatorModule;

    /* renamed from: com.mcdonalds.app.home.dashboard.DashboardViewModel$5 */
    class C23875 implements Comparator<Offer> {
        C23875() {
        }

        public int compare(Offer offer1, Offer offer2) {
            Ensighten.evaluateEvent(this, "compare", new Object[]{offer1, offer2});
            if (offer1.isPunchCard() && !offer2.isPunchCard()) {
                return -1;
            }
            if (!offer1.isPunchCard() && offer2.isPunchCard()) {
                return 1;
            }
            if (offer1.isPunchCardType() && !offer2.isPunchCardType()) {
                return -1;
            }
            if (!offer1.isPunchCardType() && offer2.isPunchCardType()) {
                return 1;
            }
            if (Configuration.getSharedInstance().getBooleanForKey("interface.offers.sortOffersByDescendingLocalValidFrom")) {
                return offer2.getLocalValidFrom().compareTo(offer1.getLocalValidFrom());
            }
            return offer1.getLocalValidFrom().compareTo(offer2.getLocalValidFrom());
        }
    }

    /* renamed from: com.mcdonalds.app.home.dashboard.DashboardViewModel$7 */
    class C23897 implements AsyncListener<PromoResponse> {

        /* renamed from: com.mcdonalds.app.home.dashboard.DashboardViewModel$7$1 */
        class C23901 extends TypeToken<List<Promo>> {
            C23901() {
            }
        }

        C23897() {
        }

        public void onResponse(PromoResponse response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            DashboardViewModel.access$1402(DashboardViewModel.this, false);
            if (exception != null) {
                DashboardViewModel.access$1602(DashboardViewModel.this, (List) LocalDataManager.getSharedInstance().getObjectFromCache("PROMO_LIST", new C23901().getType()));
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "access$1600", new Object[]{DashboardViewModel.this}) != null) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "access$1700", new Object[]{DashboardViewModel.this}).onResponse(Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "access$1600", new Object[]{DashboardViewModel.this}), null, null);
                } else {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "access$1700", new Object[]{DashboardViewModel.this}).onResponse(null, null, exception);
                }
            } else if (response != null) {
                SafeLog.m7737d(Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "access$1500", null), response.toString());
                if (response.getPromos() != null) {
                    DashboardViewModel.access$1602(DashboardViewModel.this, response.getPromos());
                    LocalDataManager.getSharedInstance().addObjectToCache("PROMO_LIST", response.getPromos(), 50000);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "access$1700", new Object[]{DashboardViewModel.this}).onResponse(Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "access$1600", new Object[]{DashboardViewModel.this}), null, null);
                }
            }
        }
    }

    private class GetCustomerAddressTask extends AsyncTask<String, Integer, Address> implements TraceFieldInterface {
        public Trace _nr_trace;
        private AsyncCounter<List> mAsyncCounter;
        private Context mContext;

        public void _nr_setTrace(Trace trace) {
            try {
                this._nr_trace = trace;
            } catch (Exception e) {
            }
        }

        public GetCustomerAddressTask(Context context, AsyncCounter<List> asyncCounter) {
            this.mContext = context;
            this.mAsyncCounter = asyncCounter;
        }

        /* Access modifiers changed, original: protected|varargs */
        public Address doInBackground(String... params) {
            Ensighten.evaluateEvent(this, "doInBackground", new Object[]{params});
            try {
                List<Address> results = new Geocoder(this.mContext, Locale.US).getFromLocationName(params[0], 1);
                if (ListUtils.isNotEmpty(results)) {
                    return (Address) results.get(0);
                }
            } catch (IOException ioe) {
                SafeLog.m7738e(Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "access$1500", null), "geocoding error", ioe);
            }
            return null;
        }

        /* Access modifiers changed, original: protected */
        public void onPostExecute(Address address) {
            Ensighten.evaluateEvent(this, "onPostExecute", new Object[]{address});
            super.onPostExecute(address);
            if (address != null) {
                DashboardViewModel.access$1900(DashboardViewModel.this, Double.valueOf(address.getLatitude()), Double.valueOf(address.getLongitude()), this.mAsyncCounter);
            } else {
                DashboardViewModel.access$1900(DashboardViewModel.this, null, null, this.mAsyncCounter);
            }
        }
    }

    static /* synthetic */ List access$1102(DashboardViewModel x0, List x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "access$1102", new Object[]{x0, x1});
        x0.mActiveOffers = x1;
        return x1;
    }

    static /* synthetic */ void access$1200(DashboardViewModel x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "access$1200", new Object[]{x0});
        x0.clearExistingOffers();
    }

    static /* synthetic */ void access$1300(DashboardViewModel x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "access$1300", new Object[]{x0});
        x0.processOffers();
    }

    static /* synthetic */ boolean access$1402(DashboardViewModel x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "access$1402", new Object[]{x0, new Boolean(x1)});
        x0.mLoadingPromos = x1;
        return x1;
    }

    static /* synthetic */ List access$1602(DashboardViewModel x0, List x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "access$1602", new Object[]{x0, x1});
        x0.mPromoList = x1;
        return x1;
    }

    static /* synthetic */ void access$1900(DashboardViewModel x0, Double x1, Double x2, AsyncCounter x3) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "access$1900", new Object[]{x0, x1, x2, x3});
        x0.updateCustomerOffers(x1, x2, x3);
    }

    static /* synthetic */ void access$400(DashboardViewModel x0, List x1, List x2, List x3, AsyncListener x4) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "access$400", new Object[]{x0, x1, x2, x3, x4});
        x0.updateFilteredOffersAndStores(x1, x2, x3, x4);
    }

    static /* synthetic */ List access$502(DashboardViewModel x0, List x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "access$502", new Object[]{x0, x1});
        x0.mNearbyStores = x1;
        return x1;
    }

    static /* synthetic */ SparseArray access$602(DashboardViewModel x0, SparseArray x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "access$602", new Object[]{x0, x1});
        x0.mFavoriteInfoArray = x1;
        return x1;
    }

    static /* synthetic */ void access$800(DashboardViewModel x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "access$800", new Object[]{x0});
        x0.refreshCurrentAndNearbyWithFavorites();
    }

    static /* synthetic */ void access$900(DashboardViewModel x0, List x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "access$900", new Object[]{x0, x1});
        x0.setFavoriteStores(x1);
    }

    private DashboardViewModel() {
    }

    public static DashboardViewModel getInstance(Context context) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "getInstance", new Object[]{context});
        if (sInstance == null) {
            sInstance = new DashboardViewModel();
            sInstance.init(context);
        }
        return sInstance;
    }

    public static void destroy() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "destroy", null);
        sInstance = null;
    }

    public void init(Context context) {
        Ensighten.evaluateEvent(this, "init", new Object[]{context});
        this.mContext = context;
        this.mNearbyRadius = (Double) Configuration.getSharedInstance().getValueForKey("modules.offers.nearbySearchRadius");
        this.mDefaultRadius = (Double) Configuration.getSharedInstance().getValueForKey("modules.storeLocator.defaultSearchRadius");
        this.mMySurprisesMode = Configuration.getSharedInstance().getBooleanForKey("interface.dashboard.useMySurprises");
        this.mStoreLocatorModule = (StoreLocatorModule) ModuleManager.getModule(StoreLocatorModule.NAME);
        resetOfferCount();
        getHomeListItems();
        loadOfferSections();
    }

    public Boolean isSubscribedToOffers() {
        Ensighten.evaluateEvent(this, "isSubscribedToOffers", null);
        if (this.mCustomerModule.getCurrentProfile() != null) {
            return Boolean.valueOf(this.mCustomerModule.getCurrentProfile().isSubscribedToOffers());
        }
        return Boolean.valueOf(true);
    }

    public Boolean isEmailVerified() {
        Ensighten.evaluateEvent(this, "isEmailVerified", null);
        if (this.mCustomerModule.getCurrentProfile() != null) {
            return Boolean.valueOf(this.mCustomerModule.getCurrentProfile().isEmailVerified());
        }
        return Boolean.valueOf(true);
    }

    public Boolean isMobileVerified() {
        Ensighten.evaluateEvent(this, "isMobileVerified", null);
        if (this.mCustomerModule.getCurrentProfile() != null) {
            return Boolean.valueOf(this.mCustomerModule.getCurrentProfile().isMobileVerified());
        }
        return Boolean.valueOf(true);
    }

    public void getOffers(final AsyncListener<List<Offer>> asyncListener, final AsyncListener<List<Store>> favStoreAsyncListener) {
        int requestCount = 2;
        boolean z = true;
        Ensighten.evaluateEvent(this, "getOffers", new Object[]{asyncListener, favStoreAsyncListener});
        resetOfferCount();
        this.mNearbyOffersEnabled = false;
        if (!this.mMySurprisesMode) {
            Iterator it = this.mOfferSections.iterator();
            while (it.hasNext()) {
                OfferSection offerSection = (OfferSection) it.next();
                if (!this.mNearbyOffersEnabled) {
                    if (!(OfferSections.NEAR.getName().equals(offerSection.getSectionType()) && offerSection.isEnabled())) {
                        z = false;
                    }
                    this.mNearbyOffersEnabled = z;
                }
            }
        }
        if (this.mAddedToOrder) {
            requestCount = 0;
        }
        if (this.mNearbyOffersEnabled) {
            requestCount++;
        }
        final AsyncCounter<List> asyncCounter = new AsyncCounter(requestCount, new AsyncListener<List>() {
            public void onResponse(List response, AsyncToken token, AsyncException exception) {
                List list = null;
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                if (ListUtils.isEmpty(DashboardViewModel.this.getActiveOffers())) {
                    asyncListener.onResponse(null, null, exception);
                } else if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "access$000", new Object[]{DashboardViewModel.this})) {
                    asyncListener.onResponse(null, null, null);
                } else {
                    DashboardViewModel dashboardViewModel = DashboardViewModel.this;
                    List activeOffers = DashboardViewModel.this.getActiveOffers();
                    if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "access$100", new Object[]{DashboardViewModel.this})) {
                        list = Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "access$200", new Object[]{DashboardViewModel.this});
                    }
                    DashboardViewModel.access$400(dashboardViewModel, activeOffers, list, Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "access$300", new Object[]{DashboardViewModel.this}), asyncListener);
                }
            }
        });
        if (requestCount == 0) {
            requestCustomerOffers(asyncCounter);
            return;
        }
        if (this.mNearbyOffersEnabled) {
            if (this.mDefaultRadius == null) {
                this.mDefaultRadius = Double.valueOf(16100.0d);
            }
            this.mStoreLocatorModule.getStoresNearCurrentLocationWithinRadius(this.mDefaultRadius, null, new AsyncListener<List<Store>>() {
                public void onResponse(List<Store> response, AsyncToken token, AsyncException exception) {
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    DashboardViewModel.access$502(DashboardViewModel.this, response);
                    asyncCounter.success(null);
                }
            });
        }
        ServiceUtils.getSharedInstance().retrieveFavoriteStores(this.mCustomerModule.getCurrentProfile(), new AsyncListener<List<StoreFavoriteInfo>>() {

            /* renamed from: com.mcdonalds.app.home.dashboard.DashboardViewModel$3$1 */
            class C23851 implements AsyncListener<List<Store>> {
                C23851() {
                }

                public void onResponse(List<Store> response, AsyncToken token, AsyncException exception) {
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    if (exception != null) {
                        asyncCounter.error(exception);
                        favStoreAsyncListener.onResponse(null, null, exception);
                    } else if (response != null) {
                        for (Store store : response) {
                            StoreFavoriteInfo info = (StoreFavoriteInfo) Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "access$600", new Object[]{DashboardViewModel.this}).get(store.getStoreId());
                            if (info != null) {
                                store.setStoreFavoriteId(Integer.valueOf(info.getFavoriteId()));
                                store.setStoreFavoriteName(info.getFavoriteNickName());
                            }
                        }
                        List<Store> availableFavoriteStores = FavoriteStoreUtils.checkIfFavoriteStoresAreOpen(Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "access$600", new Object[]{DashboardViewModel.this}), response);
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "access$700", new Object[]{DashboardViewModel.this}).setFavoriteStores(availableFavoriteStores);
                        DashboardViewModel.access$800(DashboardViewModel.this);
                        DashboardViewModel.access$900(DashboardViewModel.this, availableFavoriteStores);
                        asyncCounter.success(null);
                        favStoreAsyncListener.onResponse(null, null, null);
                    }
                }
            }

            public void onResponse(List<StoreFavoriteInfo> storeFavoriteInfoList, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{storeFavoriteInfoList, token, exception});
                if (exception == null) {
                    List<String> favoriteStoreIds = new ArrayList();
                    DashboardViewModel.access$602(DashboardViewModel.this, new SparseArray());
                    for (StoreFavoriteInfo info : storeFavoriteInfoList) {
                        Integer storeId = Integer.valueOf(info.getStoreId());
                        favoriteStoreIds.add(Integer.toString(info.getStoreId()));
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "access$600", new Object[]{DashboardViewModel.this}).put(storeId.intValue(), info);
                    }
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "access$1000", new Object[]{DashboardViewModel.this}).getStoresForIds(new ArrayList(favoriteStoreIds), new C23851());
                    return;
                }
                asyncCounter.error(exception);
            }
        });
        requestCustomerOffers(asyncCounter);
    }

    private void refreshCurrentAndNearbyWithFavorites() {
        Ensighten.evaluateEvent(this, "refreshCurrentAndNearbyWithFavorites", null);
        if (this.mCustomerModule != null) {
            Store current = OrderManager.getInstance().getCurrentStore();
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

    private void updateFavoriteInfo(Store store) {
        Ensighten.evaluateEvent(this, "updateFavoriteInfo", new Object[]{store});
        Store favorite = null;
        for (Store favStore : this.mCustomerModule.getFavoriteStores()) {
            if (store.getStoreId() == favStore.getStoreId()) {
                favorite = favStore;
                break;
            }
        }
        if (favorite != null) {
            if (favorite.getStoreFavoriteName() == null) {
                StoreFavoriteInfo info = (StoreFavoriteInfo) this.mFavoriteInfoArray.get(favorite.getStoreId());
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

    private void requestCustomerOffers(AsyncCounter<List> counter) {
        Ensighten.evaluateEvent(this, "requestCustomerOffers", new Object[]{counter});
        Location location = AppUtils.getUserLocation();
        if (!this.mAddedToOrder) {
            if (this.mMySurprisesMode) {
                updateCustomerOffers(null, null, counter);
            } else if (location != null) {
                updateCustomerOffers(Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()), counter);
            } else {
                if (TextUtils.isEmpty(this.mCustomerModule.getCurrentProfile() != null ? this.mCustomerModule.getCurrentProfile().getZipCode() : "")) {
                    updateCustomerOffers(null, null, counter);
                    return;
                }
                GetCustomerAddressTask task = new GetCustomerAddressTask(this.mContext, counter);
                String[] strArr = new String[]{zipCode};
                if (task instanceof AsyncTask) {
                    AsyncTaskInstrumentation.execute(task, strArr);
                } else {
                    task.execute(strArr);
                }
            }
        }
    }

    private void clearExistingOffers() {
        Ensighten.evaluateEvent(this, "clearExistingOffers", null);
        if (this.mALittleFurtherOffers != null) {
            this.mALittleFurtherOffers.clear();
        }
        if (this.mCurrentStoreOffers != null) {
            this.mCurrentStoreOffers.clear();
        }
        if (this.mNearbyOffers != null) {
            this.mNearbyOffers.clear();
        }
        if (this.mFavoriteStoreOffers != null) {
            this.mFavoriteStoreOffers.clear();
        }
    }

    private void updateCustomerOffers(Double latitude, Double longitude, final AsyncCounter<List> asyncCounter) {
        Ensighten.evaluateEvent(this, "updateCustomerOffers", new Object[]{latitude, longitude, asyncCounter});
        CustomerProfile profile = this.mCustomerModule.getCurrentProfile();
        Store store = OrderManager.getInstance().getCurrentStore();
        ServiceUtils.getSharedInstance().retrieveOffers(profile.getUserName(), store == null ? "" : String.valueOf(store.getStoreId()), latitude, longitude, new AsyncListener<List<Offer>>() {
            public void onResponse(List<Offer> offerList, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{offerList, token, exception});
                if (exception == null) {
                    if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "access$1100", new Object[]{DashboardViewModel.this}) == null) {
                        DashboardViewModel.access$1102(DashboardViewModel.this, new ArrayList());
                    }
                    DashboardViewModel.access$1200(DashboardViewModel.this);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "access$1100", new Object[]{DashboardViewModel.this}).clear();
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "access$1100", new Object[]{DashboardViewModel.this}).addAll(offerList);
                    DashboardViewModel.access$1300(DashboardViewModel.this);
                    asyncCounter.success(null);
                    return;
                }
                asyncCounter.error(exception);
            }
        });
    }

    private void processOffers() {
        Ensighten.evaluateEvent(this, "processOffers", null);
        Iterator<Offer> i = this.mActiveOffers.iterator();
        while (i.hasNext()) {
            Offer offer = (Offer) i.next();
            if (offer.isArchived().booleanValue() || offer.isExpired().booleanValue()) {
                i.remove();
            }
        }
        DataLayerManager.getInstance().setOffers(this.mActiveOffers);
        Collections.sort(this.mActiveOffers, this.mOffersComparator);
    }

    public int getOfferCount() {
        Ensighten.evaluateEvent(this, "getOfferCount", null);
        return this.mOffersCount;
    }

    private void resetOfferCount() {
        Ensighten.evaluateEvent(this, "resetOfferCount", null);
        this.mOffersCount = Configuration.getSharedInstance().getIntForKey("interface.dashboard.maxVisibleOffers");
        if (this.mOffersCount == 0) {
            this.mOffersCount = 6;
        }
    }

    public void getCustomerLastOrder(final AsyncListener<CustomerOrder> orderAsyncListener) {
        Ensighten.evaluateEvent(this, "getCustomerLastOrder", new Object[]{orderAsyncListener});
        if (ModuleManager.isModuleEnabled("ordering").booleanValue()) {
            this.mCustomerModule.getRecentOrders(this.mCustomerModule.getCurrentProfile(), Integer.valueOf(OrderUtils.getNumberOfRecentOrder()), new AsyncListener<List<CustomerOrder>>() {
                public void onResponse(List<CustomerOrder> response, AsyncToken token, AsyncException exception) {
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    if (exception != null) {
                        orderAsyncListener.onResponse(null, null, exception);
                    } else if (response != null && response.size() > 0) {
                        List<CustomerOrder> filteredOrders = OfferUtils.filterIfFinalized(response);
                        if (ListUtils.isEmpty(filteredOrders)) {
                            orderAsyncListener.onResponse(null, null, null);
                        } else {
                            orderAsyncListener.onResponse(filteredOrders.get(0), null, null);
                        }
                    }
                }
            });
        } else {
            orderAsyncListener.onResponse(null, null, new AsyncException("Ordering is not enabled!"));
        }
    }

    public String getCustomerFirstName() {
        Ensighten.evaluateEvent(this, "getCustomerFirstName", null);
        if (this.mCustomerModule.getCurrentProfile() != null) {
            return this.mCustomerModule.getCurrentProfile().getFirstName();
        }
        return null;
    }

    public boolean isLoggedIn() {
        Ensighten.evaluateEvent(this, "isLoggedIn", null);
        return this.mCustomerModule.isLoggedIn();
    }

    public String getCurrentStoreId() {
        Ensighten.evaluateEvent(this, "getCurrentStoreId", null);
        if (this.mCustomerModule == null || this.mCustomerModule.getCurrentStore() == null) {
            return null;
        }
        return String.valueOf(this.mCustomerModule.getCurrentStore().getStoreId());
    }

    public String getCurrentStoreName() {
        Ensighten.evaluateEvent(this, "getCurrentStoreName", null);
        Store current = this.mCustomerModule.getCurrentStore();
        if (current == null) {
            return null;
        }
        if (!this.mCustomerModule.isLoggedIn()) {
            current.setStoreFavoriteName(null);
            current.setStoreFavoriteId(null);
            this.mCustomerModule.setCurrentStore(current);
            DataLayerManager.getInstance().setStore(current);
        }
        String favoriteName = current.getStoreFavoriteName();
        return favoriteName != null ? favoriteName : current.getAddress1();
    }

    public List<HomeListItem> getHomeListItems() {
        Ensighten.evaluateEvent(this, "getHomeListItems", null);
        List<HomeListItem> homeListItems = new ArrayList();
        List<Map<String, Object>> items = (List) Configuration.getSharedInstance().getValueForKey("interface.dashboard.items");
        if (items != null) {
            for (Map<String, Object> item : items) {
                homeListItems.add(new HomeListItem(UIUtils.getDrawableIdByName(this.mContext, (String) item.get("itemImage")), UIUtils.getStringByName(this.mContext, (String) item.get("itemName")), UIUtils.getStringByName(this.mContext, (String) item.get("itemDescription")), (String) item.get("itemLink"), (Map) item.get("itemAttr"), (String) item.get("itemName")));
            }
        }
        return homeListItems;
    }

    private void loadOfferSections() {
        Ensighten.evaluateEvent(this, "loadOfferSections", null);
        this.mOfferSections = new ArrayList();
        for (LinkedTreeMap map : (List) Configuration.getSharedInstance().getValueForKey("interface.dashboard.offerSections")) {
            this.mOfferSections.add(new OfferSection(UIUtils.getStringByName(this.mContext, (String) map.get("sectionTitle")), (String) map.get("sectionType"), ((Boolean) map.get("enabled")).booleanValue()));
        }
    }

    public void getPromoList(AsyncListener<List<Promo>> asyncListener, RequestManagerServiceConnection connection) {
        Ensighten.evaluateEvent(this, "getPromoList", new Object[]{asyncListener, connection});
        if (!this.mLoadingPromos) {
            this.mPromosExternalListener = asyncListener;
            if (this.mPromoList != null) {
                this.mPromosExternalListener.onResponse(this.mPromoList, null, null);
                return;
            }
            String promos = Configuration.getSharedInstance().getValueForKey("interface.dashboard.promos");
            if (promos instanceof String) {
                connection.processRequest(new JsonGetRequest(promos, PromoResponse.class), this.mPromosResponseListener);
                this.mLoadingPromos = true;
            } else if (promos instanceof List) {
                for (LinkedTreeMap<String, String> map : (List) Configuration.getSharedInstance().getValueForKey("interface.dashboard.promos")) {
                    this.mPromoList.add(new Promo((String) map.get(NativeProtocol.IMAGE_URL_KEY), null, (String) map.get("itemLink")));
                }
                this.mPromosExternalListener.onResponse(this.mPromoList, null, null);
            }
        }
    }

    public List<OrderOffer> getCurrentOrderOffers() {
        Ensighten.evaluateEvent(this, "getCurrentOrderOffers", null);
        Order currentOrder = OrderingManager.getInstance().getCurrentOrder();
        List<OrderOffer> orderOffers = new ArrayList();
        if (currentOrder.getOffers() != null) {
            for (OrderOffer orderOffer : currentOrder.getOffers()) {
                orderOffers.add(orderOffer);
            }
        }
        return orderOffers;
    }

    public boolean verifyIsAddedToOrder() {
        Ensighten.evaluateEvent(this, "verifyIsAddedToOrder", null);
        Order order = OrderingManager.getInstance().getCurrentOrder();
        boolean z = order != null && order.hasOffers();
        this.mAddedToOrder = z;
        return this.mAddedToOrder;
    }

    private void updateFilteredOffersAndStores(List<Offer> offerList, List<Store> allStores, List<Store> favoriteStores, AsyncListener<List<Offer>> asyncListener) {
        List arrayList;
        Ensighten.evaluateEvent(this, "updateFilteredOffersAndStores", new Object[]{offerList, allStores, favoriteStores, asyncListener});
        processStoresResponse(allStores);
        List<Offer> activeOffers = getActiveOffers();
        if (Configuration.getSharedInstance().getBooleanForKey("interface.dashboard.disallowNearYouTabToShowOffers")) {
            this.mNearbyOffers = new ArrayList();
            this.mALittleFurtherOffers = new ArrayList();
        } else {
            this.mNearbyOffers = offersInStore(activeOffers, getNearbyStores());
            createALittleFurtherAwayList();
        }
        if (OrderManager.getInstance().getCurrentStore() == null) {
            arrayList = new ArrayList();
        } else {
            arrayList = offersInStore(getActiveOffers(), Collections.singletonList(OrderManager.getInstance().getCurrentStore()));
        }
        this.mCurrentStoreOffers = arrayList;
        this.mFavoriteStoreOffers = offersInStore(activeOffers, getFavoriteStores());
        asyncListener.onResponse(null, null, null);
    }

    private void createALittleFurtherAwayList() {
        Ensighten.evaluateEvent(this, "createALittleFurtherAwayList", null);
        this.mALittleFurtherOffers = new ArrayList();
        Set<Integer> nearbyIds = new HashSet();
        for (Offer offer : getNearbyOffers()) {
            nearbyIds.add(offer.getOfferId());
        }
        for (Offer offer2 : getActiveOffers()) {
            if (!nearbyIds.contains(offer2.getOfferId())) {
                this.mALittleFurtherOffers.add(offer2);
            }
        }
    }

    private void processStoresResponse(List<Store> response) {
        Ensighten.evaluateEvent(this, "processStoresResponse", new Object[]{response});
        this.mNearbyStores = new ArrayList();
        if (response != null) {
            for (Store store : response) {
                if (store.getDistance() <= this.mNearbyRadius.doubleValue()) {
                    this.mNearbyStores.add(store);
                } else if (store.getDistance() <= this.mDefaultRadius.doubleValue()) {
                    this.mALittleFurtherAwayStores.add(store);
                }
            }
        }
    }

    private List<Store> getNearbyStores() {
        Ensighten.evaluateEvent(this, "getNearbyStores", null);
        return this.mNearbyStores;
    }

    public List<Offer> getActiveOffers() {
        Ensighten.evaluateEvent(this, "getActiveOffers", null);
        return this.mActiveOffers;
    }

    private List<Store> getFavoriteStores() {
        Ensighten.evaluateEvent(this, "getFavoriteStores", null);
        return this.mFavoriteStores;
    }

    private void setFavoriteStores(List<Store> favoriteStores) {
        Ensighten.evaluateEvent(this, "setFavoriteStores", new Object[]{favoriteStores});
        this.mFavoriteStores = favoriteStores;
    }

    public List<Offer> getNearbyOffers() {
        Ensighten.evaluateEvent(this, "getNearbyOffers", null);
        List<Offer> result = filterMsa(filterForPickup(this.mNearbyOffers));
        if (Configuration.getSharedInstance().getBooleanForKey("interface.offers.hidePunchcardInNearSection")) {
            return filterOutPunchCard(result);
        }
        return result;
    }

    public List<Offer> getALittleFurtherOffers() {
        Ensighten.evaluateEvent(this, "getALittleFurtherOffers", null);
        List<Offer> result = filterMsa(filterForPickup(this.mALittleFurtherOffers));
        if (Configuration.getSharedInstance().getBooleanForKey("interface.offers.hidePunchcardInNearSection")) {
            return filterOutPunchCard(result);
        }
        return result;
    }

    public List<Offer> getCurrentStoreOffers() {
        Ensighten.evaluateEvent(this, "getCurrentStoreOffers", null);
        return filterMsa(this.mCurrentStoreOffers);
    }

    public List<Offer> getCurrentStoreDeliveryOffers() {
        Ensighten.evaluateEvent(this, "getCurrentStoreDeliveryOffers", null);
        return filterForDelivery(getCurrentStoreOffers());
    }

    public List<Offer> getCurrentStorePickupOffers() {
        Ensighten.evaluateEvent(this, "getCurrentStorePickupOffers", null);
        return filterForPickup(getCurrentStoreOffers());
    }

    public List<Offer> getFavoriteStoreOffers() {
        Ensighten.evaluateEvent(this, "getFavoriteStoreOffers", null);
        if (Configuration.getSharedInstance().getBooleanForKey("interface.offers.hideFavouriteOffers")) {
            return new ArrayList();
        }
        return filterMsa(this.mFavoriteStoreOffers);
    }

    public List<Offer> getFavoriteStoreDeliveryOffers() {
        Ensighten.evaluateEvent(this, "getFavoriteStoreDeliveryOffers", null);
        return filterForDelivery(getFavoriteStoreOffers());
    }

    public List<Offer> getFavoriteStorePickupOffers() {
        Ensighten.evaluateEvent(this, "getFavoriteStorePickupOffers", null);
        return filterForPickup(getFavoriteStoreOffers());
    }

    private List<Offer> filterForDelivery(List<Offer> offers) {
        Ensighten.evaluateEvent(this, "filterForDelivery", new Object[]{offers});
        if (offers == null) {
            return null;
        }
        List<Offer> deliveryOffers = new ArrayList();
        for (Offer o : offers) {
            if (o.isDeliveryOffer()) {
                deliveryOffers.add(o);
            }
        }
        return deliveryOffers;
    }

    private List<Offer> filterForPickup(List<Offer> offers) {
        Ensighten.evaluateEvent(this, "filterForPickup", new Object[]{offers});
        if (offers == null) {
            return null;
        }
        List<Offer> pickupOffers = new ArrayList();
        for (Offer o : offers) {
            if (o.isPickupOffer()) {
                pickupOffers.add(o);
            }
        }
        return pickupOffers;
    }

    private List<Offer> offersInStore(List<Offer> offers, List<? extends Store> stores) {
        Ensighten.evaluateEvent(this, "offersInStore", new Object[]{offers, stores});
        List<Offer> offersInStores = new ArrayList();
        if (!(stores == null || offers == null)) {
            for (Offer offer : offers) {
                for (Store store : stores) {
                    if (store != null && store.hasMobileOffers()) {
                        List<Store> storesWithOffer = new ArrayList();
                        if (offer.getRestaurants() != null && offer.getRestaurants().size() != 0) {
                            for (Integer restaurantId : offer.getRestaurants()) {
                                if (restaurantId.equals(Integer.valueOf(store.getStoreId()))) {
                                    storesWithOffer.add(store);
                                    break;
                                }
                            }
                        }
                        storesWithOffer.add(store);
                        if (storesWithOffer.size() > 0) {
                            offersInStores.add(offer);
                            break;
                        }
                    }
                }
            }
        }
        return offersInStores;
    }

    private List<Offer> filterMsa(List<Offer> input) {
        Ensighten.evaluateEvent(this, "filterMsa", new Object[]{input});
        if (input == null) {
            return null;
        }
        List<Offer> output = new ArrayList();
        for (Offer o : input) {
            if (o.getOfferType().ordinal() != 7 || o.isExpirationChanged().booleanValue()) {
                output.add(o);
            }
        }
        return output;
    }

    private List<Offer> filterOutPunchCard(List<Offer> input) {
        Ensighten.evaluateEvent(this, "filterOutPunchCard", new Object[]{input});
        if (input == null) {
            return null;
        }
        List<Offer> output = new ArrayList();
        for (Offer o : input) {
            if (!(o.getOfferType() == OfferType.OFFER_TYPE_FREQUENCY || o.getOfferType() == OfferType.OFFER_TYPE_RENEWABLE_FREQUENCY)) {
                output.add(o);
            }
        }
        return output;
    }

    public void removeOfferFromOrder(OrderOffer orderOffer, AsyncListener<Boolean> asyncListener) {
        Ensighten.evaluateEvent(this, "removeOfferFromOrder", new Object[]{orderOffer, asyncListener});
        Order order = OrderingManager.getInstance().getCurrentOrder();
        order.removeOffer(orderOffer);
        boolean removed = false;
        if (order.hasOffers()) {
            for (OrderOffer offer : order.getOffers()) {
                removed = offer.getOffer().isPunchCard();
            }
        } else {
            removed = true;
        }
        asyncListener.onResponse(Boolean.valueOf(removed), null, null);
    }

    public void loadScheduledOrders(final AsyncListener<List<HomeListItem>> listener) {
        Ensighten.evaluateEvent(this, "loadScheduledOrders", new Object[]{listener});
        if (Configuration.getSharedInstance().hasKey("modules.delivery")) {
            DeliveryModule module = (DeliveryModule) ModuleManager.getModule(DeliveryModule.NAME);
            if (module != null) {
                module.getDeliveryStatus(new AsyncListener<List<DeliveryStatusResponse>>() {
                    public void onResponse(List<DeliveryStatusResponse> response, AsyncToken token, AsyncException exception) {
                        Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                        List<HomeListItem> items = new ArrayList();
                        String title = Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "access$1800", new Object[]{DashboardViewModel.this}).getString(C2658R.string.scheduled_order);
                        String prefix = Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardViewModel", "access$1800", new Object[]{DashboardViewModel.this}).getString(C2658R.string.estimated_delivery);
                        DateFormat format = new SimpleDateFormat("MM/dd/yy, hh:mm aaa");
                        for (DeliveryStatusResponse status : response) {
                            HomeListItem item = new HomeListItem(C2358R.C2359drawable.ic_mcd_order_icon_red, title, String.format("%s %s", new Object[]{prefix, format.format(status.getTimestamp())}), "order/track/" + status.getOrderNumber(), null);
                            if (status.getStatus() != 4) {
                                items.add(item);
                            }
                        }
                        listener.onResponse(items, null, null);
                    }
                });
            }
        }
    }
}
