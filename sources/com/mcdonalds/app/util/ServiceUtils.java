package com.mcdonalds.app.util;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.ensighten.Ensighten;
import com.google.gson.reflect.TypeToken;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.AdvertisablePromotion;
import com.mcdonalds.sdk.modules.models.FavoriteItem;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.modules.models.StoreFavoriteInfo;
import com.mcdonalds.sdk.modules.offers.OffersModule;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class ServiceUtils {
    private static final String FAVORITE_PRODUCTS_CACHE_KEY = (ServiceUtils.class.getSimpleName() + "FAVORITE_PRODUCTS_CACHE_KEY");
    private static final String FAVORITE_STORES_CACHE_KEY = (ServiceUtils.class.getSimpleName() + "FAVORITE_STORES_CACHE_KEY");
    private static final String OFFERS_CACHE_KEY = (ServiceUtils.class.getSimpleName() + LocalDataManager.OFFERS_CACHE_KEY);
    private static final String PROMOTIONS_CACHE_KEY = (ServiceUtils.class.getSimpleName() + "PROMOTIONS_CACHE_KEY");
    private static ServiceUtils SHARED_INSTANCE = null;
    private final String ADVERTISABLE_PROMOTIONS_ENABLED = "modules.ordering.advertisablePromotionsEnabled";
    private final List<AsyncListener<List<FavoriteItem>>> mFavoriteProductsDelayedListeners = new ArrayList();
    private boolean mFavoriteProductsFetchInProgress = false;
    private boolean mFavoriteProductsFetched = false;
    private final List<AsyncListener<List<StoreFavoriteInfo>>> mFavoriteStoresDelayedListeners = new ArrayList();
    private boolean mFavoriteStoresFetchInProgress = false;
    private boolean mFavoriteStoresFetched = false;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private String mLastAdvertisableStore;
    private String mLastOffersLocation;
    private final List<AsyncListener<List<Offer>>> mOffersDelayedListeners = new CopyOnWriteArrayList();
    private boolean mOffersFetchInProgress = false;
    private boolean mOffersFetched = false;
    private final List<AsyncListener<List<AdvertisablePromotion>>> mPromotionsDelayedListeners = new ArrayList();
    private boolean mPromotionsFetchInProgress = false;
    private boolean mPromotionsFetched = false;

    /* renamed from: com.mcdonalds.app.util.ServiceUtils$10 */
    class C245810 implements AsyncListener<List<AdvertisablePromotion>> {
        C245810() {
        }

        public void onResponse(List<AdvertisablePromotion> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception != null) {
                ServiceUtils.access$1002(ServiceUtils.this, false);
            } else {
                LocalDataManager.getSharedInstance().addObjectToCache(Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ServiceUtils", "access$1100", new Object[]{ServiceUtils.this}), response, 0);
                ServiceUtils.access$1002(ServiceUtils.this, true);
            }
            for (AsyncListener<List<AdvertisablePromotion>> listener : Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ServiceUtils", "access$1200", new Object[]{ServiceUtils.this})) {
                listener.onResponse(response, null, exception);
            }
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ServiceUtils", "access$1200", new Object[]{ServiceUtils.this}).clear();
            ServiceUtils.access$1302(ServiceUtils.this, false);
        }
    }

    /* renamed from: com.mcdonalds.app.util.ServiceUtils$1 */
    class C24591 implements AsyncListener<List<StoreFavoriteInfo>> {
        C24591() {
        }

        public void onResponse(List<StoreFavoriteInfo> storeFavoriteInfos, AsyncToken asyncToken, AsyncException e) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{storeFavoriteInfos, asyncToken, e});
            ServiceUtils.access$002(ServiceUtils.this, false);
            if (e == null) {
                LocalDataManager.getSharedInstance().addObjectToCache(Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ServiceUtils", "access$100", null), storeFavoriteInfos, TimeUnit.MINUTES.toMillis(15));
                ServiceUtils.access$202(ServiceUtils.this, true);
            } else {
                ServiceUtils.access$202(ServiceUtils.this, false);
            }
            for (AsyncListener<List<StoreFavoriteInfo>> listener : Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ServiceUtils", "access$300", new Object[]{ServiceUtils.this})) {
                listener.onResponse(storeFavoriteInfos, null, e);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.util.ServiceUtils$2 */
    class C24602 extends TypeToken<List<StoreFavoriteInfo>> {
        C24602() {
        }
    }

    /* renamed from: com.mcdonalds.app.util.ServiceUtils$4 */
    class C24624 implements AsyncListener<List<FavoriteItem>> {
        C24624() {
        }

        public void onResponse(List<FavoriteItem> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            ServiceUtils.access$402(ServiceUtils.this, false);
            for (AsyncListener<List<FavoriteItem>> listener : new ArrayList(Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ServiceUtils", "access$500", new Object[]{ServiceUtils.this}))) {
                listener.onResponse(response, null, exception);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.util.ServiceUtils$6 */
    class C24646 extends TypeToken<List<Offer>> {
        C24646() {
        }
    }

    /* renamed from: com.mcdonalds.app.util.ServiceUtils$8 */
    class C24668 extends TypeToken<List<AdvertisablePromotion>> {
        C24668() {
        }
    }

    static /* synthetic */ boolean access$002(ServiceUtils x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ServiceUtils", "access$002", new Object[]{x0, new Boolean(x1)});
        x0.mFavoriteStoresFetchInProgress = x1;
        return x1;
    }

    static /* synthetic */ boolean access$1002(ServiceUtils x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ServiceUtils", "access$1002", new Object[]{x0, new Boolean(x1)});
        x0.mPromotionsFetched = x1;
        return x1;
    }

    static /* synthetic */ boolean access$1302(ServiceUtils x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ServiceUtils", "access$1302", new Object[]{x0, new Boolean(x1)});
        x0.mPromotionsFetchInProgress = x1;
        return x1;
    }

    static /* synthetic */ boolean access$202(ServiceUtils x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ServiceUtils", "access$202", new Object[]{x0, new Boolean(x1)});
        x0.mFavoriteStoresFetched = x1;
        return x1;
    }

    static /* synthetic */ boolean access$402(ServiceUtils x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ServiceUtils", "access$402", new Object[]{x0, new Boolean(x1)});
        x0.mFavoriteProductsFetchInProgress = x1;
        return x1;
    }

    static /* synthetic */ boolean access$602(ServiceUtils x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ServiceUtils", "access$602", new Object[]{x0, new Boolean(x1)});
        x0.mOffersFetchInProgress = x1;
        return x1;
    }

    static /* synthetic */ boolean access$702(ServiceUtils x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ServiceUtils", "access$702", new Object[]{x0, new Boolean(x1)});
        x0.mOffersFetched = x1;
        return x1;
    }

    public static ServiceUtils getSharedInstance() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ServiceUtils", "getSharedInstance", null);
        if (SHARED_INSTANCE == null) {
            SHARED_INSTANCE = new ServiceUtils();
        }
        return SHARED_INSTANCE;
    }

    private ServiceUtils() {
    }

    public void fetchFavoriteLocations() {
        Ensighten.evaluateEvent(this, "fetchFavoriteLocations", null);
        if (!this.mFavoriteStoresFetchInProgress) {
            this.mFavoriteStoresFetchInProgress = true;
            CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
            if (customerModule == null || customerModule.getCurrentProfile() == null) {
                this.mFavoriteStoresFetchInProgress = false;
            } else {
                customerModule.retrieveFavoriteStores(customerModule.getCurrentProfile(), new C24591());
            }
        }
    }

    public AsyncToken retrieveFavoriteStores(CustomerProfile profile, @NonNull final AsyncListener<List<StoreFavoriteInfo>> listener) {
        Ensighten.evaluateEvent(this, "retrieveFavoriteStores", new Object[]{profile, listener});
        AsyncToken unused = new AsyncToken("retrieveFavoriteStoresFromCache");
        if (this.mFavoriteStoresFetchInProgress) {
            this.mFavoriteStoresDelayedListeners.add(listener);
        } else {
            if (this.mFavoriteStoresFetched) {
                final List<StoreFavoriteInfo> storeFavoriteInfo = (List) LocalDataManager.getSharedInstance().getObjectFromCache(FAVORITE_STORES_CACHE_KEY, new C24602().getType());
                if (storeFavoriteInfo == null) {
                    this.mFavoriteStoresFetched = false;
                } else {
                    this.mHandler.post(new Runnable() {
                        public void run() {
                            Ensighten.evaluateEvent(this, "run", null);
                            listener.onResponse(storeFavoriteInfo, null, null);
                        }
                    });
                }
            }
            if (!this.mFavoriteStoresFetched) {
                this.mFavoriteStoresDelayedListeners.add(listener);
                fetchFavoriteLocations();
            }
        }
        return unused;
    }

    public void removeFavoriteStoresCache() {
        Ensighten.evaluateEvent(this, "removeFavoriteStoresCache", null);
        LocalDataManager.getSharedInstance().remove(FAVORITE_STORES_CACHE_KEY);
        this.mFavoriteStoresFetched = false;
    }

    public AsyncToken retrieveFavoriteProducts(AsyncListener<List<FavoriteItem>> listener) {
        Ensighten.evaluateEvent(this, "retrieveFavoriteProducts", new Object[]{listener});
        AsyncToken unused = new AsyncToken("noFavoriteItemsCache");
        if (listener != null) {
            this.mFavoriteProductsDelayedListeners.add(listener);
        }
        if (!this.mFavoriteProductsFetchInProgress) {
            this.mFavoriteProductsFetchInProgress = true;
            CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
            if (customerModule == null || customerModule.getCurrentProfile() == null) {
                this.mFavoriteProductsFetchInProgress = false;
            } else {
                customerModule.updateFavoriteProducts(new C24624());
            }
        }
        return unused;
    }

    public void fetchOffers(final String username, final String storeId, Double latitude, Double longitude) {
        Ensighten.evaluateEvent(this, "fetchOffers", new Object[]{username, storeId, latitude, longitude});
        if (!this.mOffersFetchInProgress) {
            this.mOffersFetchInProgress = true;
            OffersModule offersModule = (OffersModule) ModuleManager.getModule(OffersModule.NAME);
            if (offersModule == null || TextUtils.isEmpty(username)) {
                this.mOffersFetchInProgress = false;
                return;
            }
            List<String> storeIds;
            if (TextUtils.isEmpty(storeId)) {
                storeIds = null;
            } else {
                storeIds = Arrays.asList(new String[]{storeId});
            }
            offersModule.getCustomerOffers(username, latitude, longitude, storeIds, new AsyncListener<List<Offer>>() {
                public void onResponse(List<Offer> response, AsyncToken token, AsyncException exception) {
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    ServiceUtils.access$602(ServiceUtils.this, false);
                    if (exception == null && ListUtils.isNotEmpty(response)) {
                        LocalDataManager.getSharedInstance().setCachedOffers(username, storeId, response);
                        ServiceUtils.access$702(ServiceUtils.this, true);
                    } else {
                        ServiceUtils.access$702(ServiceUtils.this, false);
                    }
                    for (AsyncListener<List<Offer>> listener : Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ServiceUtils", "access$800", new Object[]{ServiceUtils.this})) {
                        listener.onResponse(Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ServiceUtils", "access$900", new Object[]{ServiceUtils.this, response}), null, exception);
                    }
                }
            });
        }
    }

    public AsyncToken retrieveOffers(String username, String storeId, Double latitude, Double longitude, @NonNull final AsyncListener<List<Offer>> listener) {
        Ensighten.evaluateEvent(this, "retrieveOffers", new Object[]{username, storeId, latitude, longitude, listener});
        CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        AsyncToken unused = new AsyncToken("retrieveOffersFromCache");
        if (!(customerModule == null || customerModule.getCurrentProfile() == null || !customerModule.getCurrentProfile().isSubscribedToOffers())) {
            String offersLocation = latitude + "," + longitude;
            if (!(latitude == null || longitude == null || offersLocation.equals(this.mLastOffersLocation))) {
                removeOffersCache();
            }
            if (this.mOffersFetchInProgress) {
                this.mOffersDelayedListeners.add(listener);
            } else {
                if (this.mOffersFetched) {
                    Type type = new C24646().getType();
                    final List<Offer> offers = (List) LocalDataManager.getSharedInstance().getObjectFromCache(username + "_" + storeId + "_" + OFFERS_CACHE_KEY, type);
                    if (offers == null) {
                        this.mOffersFetched = false;
                    } else {
                        this.mHandler.post(new Runnable() {
                            public void run() {
                                Ensighten.evaluateEvent(this, "run", null);
                                listener.onResponse(Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ServiceUtils", "access$900", new Object[]{ServiceUtils.this, offers}), null, null);
                            }
                        });
                    }
                }
                if (!this.mOffersFetched) {
                    this.mOffersDelayedListeners.add(listener);
                    this.mLastOffersLocation = offersLocation;
                    fetchOffers(username, storeId, latitude, longitude);
                }
            }
        }
        return unused;
    }

    private List<Offer> filterOutNotValidOffers(List<Offer> input) {
        Ensighten.evaluateEvent(this, "filterOutNotValidOffers", new Object[]{input});
        if (input == null) {
            return null;
        }
        List<Offer> output = new ArrayList();
        Date now = new Date();
        for (Offer o : input) {
            if (now.after(o.getLocalValidFrom()) && now.before(o.getLocalValidThrough())) {
                output.add(o);
            }
        }
        return output;
    }

    public void removeOffersCache() {
        Ensighten.evaluateEvent(this, "removeOffersCache", null);
        LocalDataManager.getSharedInstance().remove(LocalDataManager.OFFERS_CACHE_KEY);
        this.mOffersFetched = false;
    }

    @Deprecated
    public AsyncToken retrieveAdvertisablePromotions(String userName, int storeId, final AsyncListener<List<AdvertisablePromotion>> listener) {
        Ensighten.evaluateEvent(this, "retrieveAdvertisablePromotions", new Object[]{userName, new Integer(storeId), listener});
        AsyncToken unused = new AsyncToken("retrieveAdvertisablePromotions");
        CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        boolean hasMobileOrdering;
        if (!ModuleManager.isModuleEnabled("ordering").booleanValue() || customerModule.getCurrentStore() == null) {
            hasMobileOrdering = false;
        } else {
            hasMobileOrdering = customerModule.getCurrentStore().hasMobileOrdering().booleanValue();
        }
        if (Configuration.getSharedInstance().getBooleanForKey("modules.ordering.advertisablePromotionsEnabled", false) && hasMobileOrdering) {
            if (!this.mPromotionsFetchInProgress) {
                String key = userName + "_" + storeId + "_" + PROMOTIONS_CACHE_KEY;
                if (!key.equals(this.mLastAdvertisableStore)) {
                    removeAdvertisableCache();
                }
                if (this.mPromotionsFetched) {
                    final List<AdvertisablePromotion> promotions = (List) LocalDataManager.getSharedInstance().getObjectFromCache(key, new C24668().getType());
                    if (promotions == null) {
                        this.mPromotionsFetched = false;
                    } else if (listener != null) {
                        this.mHandler.post(new Runnable() {
                            public void run() {
                                Ensighten.evaluateEvent(this, "run", null);
                                listener.onResponse(promotions, null, null);
                            }
                        });
                    }
                }
                if (!this.mPromotionsFetched) {
                    if (listener != null) {
                        this.mPromotionsDelayedListeners.add(listener);
                    }
                    fetchPromotions(userName, storeId);
                    this.mLastAdvertisableStore = key;
                }
            } else if (listener != null) {
                this.mPromotionsDelayedListeners.add(listener);
            }
        } else if (listener != null) {
            listener.onResponse(null, null, new AsyncException("No config param: \"modules.ordering.advertisablePromotionsEnabled\""));
        }
        return unused;
    }

    private void fetchPromotions(String userName, int storeId) {
        Ensighten.evaluateEvent(this, "fetchPromotions", new Object[]{userName, new Integer(storeId)});
        removeAdvertisableCache();
        if (!this.mPromotionsFetchInProgress) {
            this.mPromotionsFetchInProgress = true;
            OffersModule offersModule = (OffersModule) ModuleManager.getModule(OffersModule.NAME);
            if (offersModule == null || userName == null) {
                this.mPromotionsFetchInProgress = false;
            } else {
                offersModule.getAdvertisablePromotions(userName, storeId, new C245810());
            }
        }
    }

    public void removeAdvertisableCache() {
        Ensighten.evaluateEvent(this, "removeAdvertisableCache", null);
        LocalDataManager.getSharedInstance().remove(PROMOTIONS_CACHE_KEY);
        this.mPromotionsFetched = false;
    }

    public void updateArchivedOfferInCache(int offerId) {
        Ensighten.evaluateEvent(this, "updateArchivedOfferInCache", new Object[]{new Integer(offerId)});
        String username = ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getCurrentProfile().getUserName();
        Store store = OrderManager.getInstance().getCurrentStore();
        String storeId = store == null ? "" : String.valueOf(store.getStoreId());
        List<Offer> offers = LocalDataManager.getSharedInstance().getCachedOffers(username, storeId);
        if (offers != null) {
            for (Offer o : offers) {
                if (o.getOfferId().intValue() == offerId) {
                    o.setArchived(Boolean.valueOf(true));
                    break;
                }
            }
            LocalDataManager.getSharedInstance().setCachedOffers(username, storeId, offers);
        }
    }
}
