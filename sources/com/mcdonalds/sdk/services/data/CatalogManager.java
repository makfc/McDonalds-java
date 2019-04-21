package com.mcdonalds.sdk.services.data;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.ConnectorManager;
import com.mcdonalds.sdk.connectors.CustomerConnector;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.AdvertisablePromotionEntity;
import com.mcdonalds.sdk.modules.models.Catalog;
import com.mcdonalds.sdk.modules.models.Category;
import com.mcdonalds.sdk.modules.models.Ingredient;
import com.mcdonalds.sdk.modules.models.MarketCatalog;
import com.mcdonalds.sdk.modules.models.Pod;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.modules.models.Product.ProductType;
import com.mcdonalds.sdk.modules.models.ProductDimension;
import com.mcdonalds.sdk.modules.models.StoreCatalog;
import com.mcdonalds.sdk.modules.models.StoreProduct;
import com.mcdonalds.sdk.modules.models.StoreProductCategory;
import com.mcdonalds.sdk.modules.offers.OffersModule;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.database.Transaction;
import com.mcdonalds.sdk.services.data.provider.Contract;
import com.mcdonalds.sdk.services.data.provider.Contract.SocialNetworks;
import com.mcdonalds.sdk.services.data.repository.AdvertisablePromotionRepository;
import com.mcdonalds.sdk.services.data.repository.ProductRepository;
import com.mcdonalds.sdk.services.data.sync.CatalogVersionType;
import com.mcdonalds.sdk.services.data.sync.SyncAdapter;
import com.mcdonalds.sdk.utils.ListUtils;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

public class CatalogManager {
    public static final String CACHE_CATALOG_EXPIRATION = "catalog_expiration";
    public static final String CACHE_MARKET_CATALOG = "cache_market_catalog";
    private static final String CATALOG_COMPLETED_FLAG = "catalog_completed";
    public static final String CATALOG_INFO = "catalog_info-9";
    public static final String CONFIG_CATALOG_EXPIRATION_TIME = "catalogExpirationTime";
    private static final String CUSTOMER_CONNECTOR_KEY = "modules.customer.connector";
    public static final String FIRST_CATALOG_LOAD = "first_catalog_load";
    private static final String MAX_STORE_CACHE_SIZE_KEY = "modules.customer.maxStoreCacheSize";
    private static final String STORE_PRODUCT_PERF_FIX = "Catalog.storeProductPerfFix";
    private static int SYNC_STATUS = 1;
    public static final int SYNC_STATUS_ERROR = 2;
    public static final int SYNC_STATUS_IDLE = 1;
    public static final int SYNC_STATUS_IN_PROGRESS = 0;
    public static final String VERSION_0 = "0001-01-01T00:00:00";
    private Catalog mCatalog;
    private Context mContext;
    private HashSet<ProductDimension> mDimensions;
    private HashSet<Ingredient> mIngredients;
    private HashSet<Pod> mPods;
    private Transaction mTransaction;

    private interface AsyncPersistListener {
        void onComplete();
    }

    private static class NonBlockingCatalogUpdater {
        private Context mContext;
        private CustomerConnector mCustomerConnector;
        private boolean mIsStoreInfoRequired;
        private int mStoreID;
        private String mUserName;

        /* renamed from: com.mcdonalds.sdk.services.data.CatalogManager$NonBlockingCatalogUpdater$1 */
        class C41121 implements AsyncListener<Store> {
            C41121() {
            }

            public void onResponse(Store response, AsyncToken token, AsyncException exception) {
                NonBlockingCatalogUpdater.this.finishExecute();
            }
        }

        /* renamed from: com.mcdonalds.sdk.services.data.CatalogManager$NonBlockingCatalogUpdater$2 */
        class C41132 implements AsyncListener<List<AdvertisablePromotionEntity>> {
            C41132() {
            }

            public void onResponse(List<AdvertisablePromotionEntity> response, AsyncToken token, AsyncException exception) {
                NonBlockingCatalogUpdater.this.persistAdvertisablePromotions(NonBlockingCatalogUpdater.this.mStoreID, response);
            }
        }

        /* renamed from: com.mcdonalds.sdk.services.data.CatalogManager$NonBlockingCatalogUpdater$3 */
        class C41153 implements AsyncListener<Catalog> {

            /* renamed from: com.mcdonalds.sdk.services.data.CatalogManager$NonBlockingCatalogUpdater$3$1 */
            class C41141 implements AsyncPersistListener {
                C41141() {
                }

                public void onComplete() {
                    NonBlockingCatalogUpdater.this.updateAdvertisablePromotions();
                }
            }

            C41153() {
            }

            public void onResponse(Catalog response, AsyncToken token, AsyncException exception) {
                if (exception != null) {
                    CatalogManager.updateStatus(NonBlockingCatalogUpdater.this.mContext, 2);
                } else if (response == null) {
                    CatalogManager.updateStatus(NonBlockingCatalogUpdater.this.mContext, 1);
                } else {
                    new CatalogManager(response, NonBlockingCatalogUpdater.this.mContext, null).asyncPersist(new C41141());
                }
            }
        }

        NonBlockingCatalogUpdater(Context context, int storeId, boolean isStoreInfoRequired) {
            this.mContext = context;
            this.mStoreID = storeId;
            this.mIsStoreInfoRequired = isStoreInfoRequired;
        }

        public void execute() {
            CatalogManager.updateStatus(this.mContext, 0);
            if (!LocalDataManager.getSharedInstance().hasObjectInCache(CatalogManager.CACHE_CATALOG_EXPIRATION)) {
                CatalogManager.clearMarketCache(this.mContext);
                CatalogManager.clearStoreCache(this.mContext);
                LocalDataManager.getSharedInstance().deleteObjectFromCache(CatalogManager.CACHE_MARKET_CATALOG);
            }
            this.mCustomerConnector = (CustomerConnector) ConnectorManager.getConnector((String) Configuration.getSharedInstance().getValueForKey(CatalogManager.CUSTOMER_CONNECTOR_KEY));
            CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
            CustomerProfile profile = customerModule.getCurrentProfile();
            this.mUserName = profile == null ? null : profile.getUserName();
            boolean isDelivery = false;
            if (this.mStoreID == 0) {
                Store store = customerModule.getCurrentStore();
                if (store != null) {
                    this.mStoreID = store.getStoreId();
                }
            } else {
                isDelivery = true;
            }
            if (!this.mIsStoreInfoRequired && (this.mStoreID == 0 || !ListUtils.isEmpty(LocalDataManager.getSharedInstance().getStoreCatalogTimestamps(this.mStoreID)))) {
                finishExecute();
            } else if (isDelivery) {
                finishExecute();
            } else {
                customerModule.updateCurrentStoreInfo(new C41121());
            }
        }

        private void updateAdvertisablePromotions() {
            ((OffersModule) ModuleManager.getModule(OffersModule.NAME)).getAdvertisablePromotionEntities(this.mUserName, this.mStoreID, new C41132());
        }

        private void persistAdvertisablePromotions(int storeId, List<AdvertisablePromotionEntity> advertisablePromotionEntities) {
            AdvertisablePromotionRepository.cleanAdvertisablePromotionsForStore(this.mContext, storeId);
            Transaction transaction = new Transaction(this.mContext);
            try {
                transaction.insert((Collection) advertisablePromotionEntities);
                transaction.commit();
            } finally {
                transaction.finish();
            }
        }

        private void finishExecute() {
            this.mCustomerConnector.getCatalogUpdated(this.mUserName, this.mStoreID != 0 ? String.valueOf(this.mStoreID) : null, new C41153());
        }
    }

    /* synthetic */ CatalogManager(Catalog x0, Context x1, C41111 x2) {
        this(x0, x1);
    }

    public static void updateCatalog(int storeId, Context context, boolean isStoreIdRequired) {
        if (ModuleManager.isModuleEnabled("ordering").booleanValue() && ModuleManager.isModuleEnabled(CustomerModule.NAME).booleanValue()) {
            SYNC_STATUS = 1;
            new NonBlockingCatalogUpdater(context, storeId, isStoreIdRequired).execute();
        }
    }

    public static int getSyncStatus() {
        return SYNC_STATUS;
    }

    public static boolean hasCatalogDownloaded(Context context) {
        Store store = OrderManager.getInstance().getCurrentStore();
        if (store == null) {
            return false;
        }
        return LocalDataManager.getSharedInstance().isStoreCatalogDownloaded(String.valueOf(store.getStoreId()));
    }

    public static boolean hasCatalogDownloaded(Context context, int storeId) {
        return context.getSharedPreferences(getStoreVersionsCacheKey(storeId), 0).getBoolean(CATALOG_COMPLETED_FLAG, false);
    }

    public static String getStoreVersionsCacheKey(int storeId) {
        return String.format("%s-%d-%d", new Object[]{Configuration.getSharedInstance().getConfigName(), Integer.valueOf(9), Integer.valueOf(storeId)});
    }

    public static void markMarketCatalogDirty(Context context) {
        LocalDataManager.getSharedInstance().deleteObjectFromCache(CACHE_MARKET_CATALOG);
        context.getSharedPreferences(Configuration.getSharedInstance().getConfigName(), 0).edit().clear().apply();
    }

    public static boolean hasMarketCatalogDownloaded(Context context) {
        return LocalDataManager.getSharedInstance().hasObjectInCache(CACHE_MARKET_CATALOG);
    }

    public static void markStoreCatalogDirty(Context context, int storeId) {
        context.getSharedPreferences(getStoreVersionsCacheKey(storeId), 0).edit().remove(CATALOG_COMPLETED_FLAG).commit();
        LocalDataManager.getSharedInstance().cleanStoreCatalogTimestamps(storeId);
    }

    public static void requestSync() {
        Bundle settingsBundle = new Bundle();
        settingsBundle.putBoolean("force", true);
        settingsBundle.putBoolean("expedited", true);
        Store store = OrderManager.getInstance().getCurrentStore();
        if (store != null && ListUtils.isEmpty(store.getMenuTypeCalendar())) {
            settingsBundle.putBoolean(SyncAdapter.SYNC_OPTION_STORE_INFO_REQUIRED, true);
        }
        ContentResolver.requestSync(((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getSyncAccount(), "com.mcdonalds.gma.hongkong.provider", settingsBundle);
    }

    public static void requestSocialNetworkSync() {
        Bundle settingsBundle = new Bundle();
        settingsBundle.putBoolean("force", true);
        settingsBundle.putBoolean("expedited", true);
        settingsBundle.putBoolean(SyncAdapter.SYNC_OPTION_SOCIAL_NETWORK, true);
        ContentResolver.requestSync(((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getSyncAccount(), "com.mcdonalds.gma.hongkong.provider", settingsBundle);
    }

    private CatalogManager(Catalog catalog, Context context) {
        this.mCatalog = catalog;
        this.mContext = context;
        this.mPods = new HashSet();
        this.mDimensions = new HashSet();
        this.mIngredients = new HashSet();
    }

    private void asyncPersist(final AsyncPersistListener listener) {
        C41111 c41111 = new TraceFieldInterface() {
            public Trace _nr_trace;

            public void _nr_setTrace(Trace trace) {
                try {
                    this._nr_trace = trace;
                } catch (Exception e) {
                }
            }

            /* Access modifiers changed, original: protected|varargs */
            public Void doInBackground(Void... params) {
                Log.d("CatalogManager", "Updating db started");
                CatalogManager.this.persist();
                Log.d("CatalogManager", "Updating db ended");
                return null;
            }

            /* Access modifiers changed, original: protected */
            public void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                CatalogManager.this.mContext.getContentResolver().notifyChange(Contract.CONTENT_URI, null, false);
                if (listener != null) {
                    listener.onComplete();
                }
            }
        };
        Void[] voidArr = new Void[0];
        if (c41111 instanceof AsyncTask) {
            AsyncTaskInstrumentation.execute(c41111, voidArr);
        } else {
            c41111.execute(voidArr);
        }
    }

    private synchronized void persist() {
        if (this.mCatalog == null) {
            Log.d("catalog", "mCatalog==null");
        } else {
            MarketCatalog marketCatalog = this.mCatalog.getMarketCatalog();
            List<StoreCatalog> storeCatalogs = this.mCatalog.getStoreCatalog();
            if (marketCatalog != null && !marketCatalog.isEmptyCatalog()) {
                for (StoreCatalog storeCatalog : storeCatalogs) {
                    ProductRepository.cleanStoreProducts(this.mContext, storeCatalog.getStoreId());
                }
                try {
                    this.mTransaction = new Transaction(this.mContext);
                    this.mTransaction.insert(marketCatalog.getCategories());
                    this.mTransaction.insert(marketCatalog.getFacilities());
                    this.mTransaction.insert(marketCatalog.getMenuTypes());
                    this.mTransaction.insert(marketCatalog.getPaymentMethods());
                    this.mTransaction.insert(marketCatalog.getFeedBackTypes());
                    Collection storesToAdd = new ArrayList();
                    for (StoreCatalog storeCatalog2 : storeCatalogs) {
                        storesToAdd.add(persistStoreData(storeCatalog2));
                    }
                    this.mTransaction.insert(storesToAdd);
                    this.mTransaction.insert(marketCatalog.getPromotions());
                    this.mTransaction.insert(marketCatalog.getTenderTypes());
                    this.mTransaction.insert(marketCatalog.getSocialNetworks());
                    this.mTransaction.commit();
                } catch (SQLiteException e) {
                    updateStatus(this.mContext, 2);
                } finally {
                    this.mTransaction.finish();
                    cacheCatalogVersions();
                    ProductRepository.cleanOrphanedProducts(this.mContext);
                    updateStatus(this.mContext, 1);
                    this.mContext.getContentResolver().notifyChange(SocialNetworks.CONTENT_URI, null, false);
                }
            } else if (!ListUtils.isEmpty(storeCatalogs)) {
                setStoreCatalogDownloaded();
            }
        }
    }

    private StoreCatalog persistStoreData(StoreCatalog storeCatalog) {
        Collection facilities = storeCatalog.getFacilities();
        if (facilities != null) {
            this.mTransaction.insert(facilities);
        }
        List<Product> products = storeCatalog.getProducts();
        if (products != null) {
            Collection productsToAdd = new ArrayList();
            List<StoreProduct> storeProductsToAdd = new ArrayList();
            List<StoreProductCategory> storeProductCategoriesToAdd = new ArrayList();
            for (int i = 0; i < products.size(); i++) {
                Product product = (Product) products.get(i);
                persistRecipe(product, storeCatalog.getStoreId());
                productsToAdd.add(product);
                StoreProduct storeProduct = product.getStoreProduct();
                storeProductsToAdd.add(storeProduct);
                if (storeProduct.getStoreProductCategories() != null) {
                    storeProductCategoriesToAdd.addAll(storeProduct.getStoreProductCategories());
                }
            }
            this.mTransaction.insert(productsToAdd);
            if (Configuration.getSharedInstance().getBooleanForKey(STORE_PRODUCT_PERF_FIX)) {
                this.mTransaction.insertFast(storeProductsToAdd, false);
            } else {
                this.mTransaction.insert(storeProductsToAdd, false);
            }
            this.mTransaction.insert(storeProductCategoriesToAdd, false);
            this.mTransaction.insert(this.mPods);
            this.mTransaction.insert(this.mDimensions);
            this.mTransaction.insert(this.mIngredients);
        }
        Collection promotions = storeCatalog.getPromotions();
        if (promotions != null) {
            this.mTransaction.insert(promotions);
        }
        return storeCatalog;
    }

    private void persistRecipe(Product product, int storeId) {
        StoreProduct storeProduct = new StoreProduct();
        storeProduct.setProductId(product.getExternalId().intValue());
        storeProduct.setStoreId(storeId);
        if (product.getPriceEatIn() > 0.0d) {
            storeProduct.setPriceEatIn(product.getPriceEatIn());
        }
        if (product.getPriceTakeOut() > 0.0d) {
            storeProduct.setPriceTakeOut(product.getPriceTakeOut());
        }
        if (product.getPriceDelivery() > 0.0d) {
            storeProduct.setPriceDelivery(product.getPriceDelivery());
        }
        if (product.getEnergy().doubleValue() > 0.0d) {
            storeProduct.setEnergy(product.getEnergy());
            storeProduct.setKCal(product.getKCal().doubleValue());
        }
        if (ProductType.Choice.equals(product.getProductType())) {
            storeProduct.setBasePriceEatIn(product.getBasePriceEatIn());
            storeProduct.setBasePriceTakeOut(product.getBasePriceTakeOut());
            storeProduct.setBasePriceDelivery(product.getBasePriceDelivery());
        }
        List<Category> categories = product.getCategories();
        if (categories != null) {
            List<StoreProductCategory> storeProductCategories = new ArrayList();
            for (Category category : categories) {
                StoreProductCategory storeProductCategory = new StoreProductCategory();
                storeProductCategory.setProductId(storeProduct.getProductId());
                storeProductCategory.setStoreId(storeProduct.getStoreId());
                storeProductCategory.setCategoryId(category.getID());
                storeProductCategory.setDisplayOrder(category.getDisplayOrder());
                storeProductCategory.setDisplaySizeSelection(category.getDisplaySizeSelection());
                storeProductCategories.add(storeProductCategory);
            }
            storeProduct.setStoreProductCategories(storeProductCategories);
        }
        storeProduct.setMenuTypes(product.getMenuTypes());
        storeProduct.setPODs(product.getPODObjects());
        storeProduct.setDimensions(product.getDimensions());
        storeProduct.setIngredients(product.getIngredients());
        storeProduct.setChoices(product.getChoices());
        storeProduct.setExtras(product.getExtras());
        storeProduct.setHasChoice(product.hasChoice());
        storeProduct.setHasNonSingleChoiceChoice(product.hasNonSingleChoiceChoice());
        storeProduct.setSingleChoice(product.isSingleChoice());
        product.setStoreProduct(storeProduct);
        if (product.getPODObjects() != null) {
            this.mPods.addAll(product.getPODObjects());
        }
        if (product.getDimensions() != null) {
            this.mDimensions.addAll(product.getDimensions());
        }
        if (product.getIngredients() != null) {
            this.mIngredients.addAll(product.getIngredients());
        }
        if (product.getExtras() != null) {
            this.mIngredients.addAll(product.getExtras());
        }
        if (product.getChoices() != null) {
            this.mIngredients.addAll(product.getChoices());
        }
    }

    private void setStoreCatalogDownloaded() {
        StoreCatalog store = (this.mCatalog.getStoreCatalog() == null || this.mCatalog.getStoreCatalog().size() <= 0) ? null : (StoreCatalog) this.mCatalog.getStoreCatalog().get(0);
        if (store != null) {
            String storeId = String.valueOf(store.getStoreId());
            LocalDataManager.getSharedInstance().setStoreCatalogDownloaded(storeId, true);
            Log.d("catalog", "store: " + storeId + " downloaded");
        }
    }

    private void cacheCatalogVersions() {
        String marketName = Configuration.getSharedInstance().getConfigName();
        if (this.mCatalog != null) {
            MarketCatalog market = this.mCatalog.getMarketCatalog();
            if (market.getNamesVersion() != null) {
                LocalDataManager.getSharedInstance().setMarketCatalogVersions(marketName, CatalogVersionType.Names.getName(), market.getNamesVersion());
            }
            if (market.getRecipesVersion() != null) {
                LocalDataManager.getSharedInstance().setMarketCatalogVersions(marketName, CatalogVersionType.Recipes.getName(), market.getRecipesVersion());
            }
            if (market.getCategoriesVersion() != null) {
                LocalDataManager.getSharedInstance().setMarketCatalogVersions(marketName, CatalogVersionType.DisplayCategory.getName(), market.getCategoriesVersion());
            }
            if (market.getPaymentMethodsVersion() != null) {
                LocalDataManager.getSharedInstance().setMarketCatalogVersions(marketName, CatalogVersionType.PaymentMethod.getName(), market.getPaymentMethodsVersion());
            }
            if (market.getPromotionsVersion() != null) {
                LocalDataManager.getSharedInstance().setMarketCatalogVersions(marketName, CatalogVersionType.Promotion.getName(), market.getPromotionsVersion());
            }
            if (market.getFeedBackTypesVersion() != null) {
                LocalDataManager.getSharedInstance().setMarketCatalogVersions(marketName, CatalogVersionType.FeedbackTypeName.getName(), market.getFeedBackTypesVersion());
            }
            if (market.getTenderTypesVersion() != null) {
                LocalDataManager.getSharedInstance().setMarketCatalogVersions(marketName, CatalogVersionType.TenderType.getName(), market.getTenderTypesVersion());
            }
            if (market.getMenuTypesVersion() != null) {
                LocalDataManager.getSharedInstance().setMarketCatalogVersions(marketName, CatalogVersionType.MenuType.getName(), market.getMenuTypesVersion());
            }
            if (market.getSocialNetworksVersion() != null) {
                LocalDataManager.getSharedInstance().setMarketCatalogVersions(marketName, CatalogVersionType.SocialMedia.getName(), market.getSocialNetworksVersion());
            }
            LocalDataManager.getSharedInstance().setMarketCatalogVersions(marketName, CatalogVersionType.StaticData.getName(), market.getCategoriesVersion());
            LocalDataManager.getSharedInstance().setMarketCatalogVersions(marketName, CatalogVersionType.Language.getName(), market.getCategoriesVersion());
            StoreCatalog store = (this.mCatalog.getStoreCatalog() == null || this.mCatalog.getStoreCatalog().size() <= 0) ? null : (StoreCatalog) this.mCatalog.getStoreCatalog().get(0);
            if (store != null) {
                String storeId = String.valueOf(store.getStoreId());
                Editor storeCacheEditor = this.mContext.getSharedPreferences(getStoreVersionsCacheKey(store.getStoreId()), 0).edit();
                LocalDataManager.getSharedInstance().setStoreCatalogVersion(storeId, CatalogVersionType.Products.getName(), store.getProductsVersion());
                LocalDataManager.getSharedInstance().setStoreCatalogVersion(storeId, CatalogVersionType.ProductPrices.getName(), store.getProductPricesVersion());
                LocalDataManager.getSharedInstance().setStoreCatalogDownloaded(storeId, true);
                if (store.getProductsVersion() == null || store.getProductPricesVersion() == null) {
                    storeCacheEditor.putBoolean(CATALOG_COMPLETED_FLAG, false);
                } else {
                    storeCacheEditor.putBoolean(CATALOG_COMPLETED_FLAG, true);
                }
                storeCacheEditor.apply();
                checkStoreCache(store.getStoreId(), this.mContext);
            }
            if (!LocalDataManager.getSharedInstance().hasObjectInCache(CACHE_CATALOG_EXPIRATION)) {
                LocalDataManager.getSharedInstance().addObjectToCache(CACHE_CATALOG_EXPIRATION, CACHE_CATALOG_EXPIRATION, TimeUnit.HOURS.toMillis(Configuration.getSharedInstance().getLongForKey(CONFIG_CATALOG_EXPIRATION_TIME)));
            }
        }
    }

    public static void reloadCatalog(Context context) {
        clearAllCatalogData(context);
        Store store = OrderManager.getInstance().getCurrentStore();
        int storeId = 0;
        if (store != null) {
            storeId = store.getStoreId();
        }
        updateCatalog(storeId, context, false);
    }

    public static boolean isFirstCatalogLoad(Context context) {
        return LocalDataManager.getSharedInstance().isFirstCatalogLoad();
    }

    public static void setFirstCatalogLoad(Context context, boolean status) {
        LocalDataManager.getSharedInstance().setFirstCatalogLoad(status);
    }

    public static void clearAllCatalogData(Context context) {
        Transaction transaction = new Transaction(context);
        try {
            transaction.clearAllData();
            transaction.commit();
            setFirstCatalogLoad(context, true);
            LocalDataManager.getSharedInstance().setLastActive(0);
            LocalDataManager.getSharedInstance().clearCatalogPreference();
            LocalDataManager.getSharedInstance().deleteObjectFromCache(CACHE_MARKET_CATALOG);
        } finally {
            transaction.finish();
        }
    }

    private static void updateStatus(Context context, int status) {
        SYNC_STATUS = status;
        context.getContentResolver().update(Contract.CONTENT_URI, null, null, null);
        context.getContentResolver().notifyChange(Contract.CONTENT_URI, null, false);
    }

    public static void checkStoreCache(int storeId, Context context) {
        int i;
        SharedPreferences storePreferences = context.getSharedPreferences("Stores", 0);
        StringTokenizer st = new StringTokenizer(storePreferences.getString("stores", ""), ",");
        int MAX_STORES = Configuration.getSharedInstance().hasKey(MAX_STORE_CACHE_SIZE_KEY) ? Configuration.getSharedInstance().getIntForKey(MAX_STORE_CACHE_SIZE_KEY) : 5;
        int[] savedList = new int[MAX_STORES];
        int tokenCount = st.countTokens();
        for (i = 0; i < tokenCount; i++) {
            savedList[i] = Integer.parseInt(st.nextToken());
        }
        StringBuilder str = new StringBuilder();
        str.append(storeId).append(",");
        int count = 1;
        int removeStoreId = -1;
        for (i = 0; i < tokenCount; i++) {
            if (storeId != savedList[i] && count < MAX_STORES) {
                str.append(savedList[i]).append(",");
                count++;
            } else if (storeId != savedList[i]) {
                removeStoreId = savedList[i];
            }
        }
        if (removeStoreId != -1) {
            markStoreCatalogDirty(context, removeStoreId);
            context.getSharedPreferences(getStoreVersionsCacheKey(storeId), 0).edit().clear().apply();
            ProductRepository.cleanStoreProducts(context, removeStoreId);
            ProductRepository.cleanOrphanedProducts(context);
        }
        storePreferences.edit().putString("stores", str.toString()).apply();
    }

    public static void clearStoreCache(Context context) {
        SharedPreferences storePreferences = context.getSharedPreferences("Stores", 0);
        StringTokenizer st = new StringTokenizer(storePreferences.getString("stores", ""), ",");
        int tokenCount = st.countTokens();
        for (int i = 0; i < tokenCount; i++) {
            int storeID = Integer.parseInt(st.nextToken());
            markStoreCatalogDirty(context, storeID);
            context.getSharedPreferences(getStoreVersionsCacheKey(storeID), 0).edit().clear().apply();
            storePreferences.edit().clear().apply();
        }
    }

    public static void clearMarketCache(Context context) {
        context.getSharedPreferences(Configuration.getSharedInstance().getConfigName(), 0).edit().clear().apply();
    }
}
