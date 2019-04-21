package com.mcdonalds.sdk.connectors.middleware.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.Looper;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnectorUtils;
import com.mcdonalds.sdk.connectors.middleware.helpers.MergeCatalogBackgroundTask.MergeCatalogBackgroundTaskListener;
import com.mcdonalds.sdk.connectors.middleware.model.MWCatalog;
import com.mcdonalds.sdk.connectors.middleware.model.MWCatalogVersion;
import com.mcdonalds.sdk.connectors.middleware.model.MWCatalogVersionItem;
import com.mcdonalds.sdk.connectors.middleware.model.MWMarket;
import com.mcdonalds.sdk.connectors.middleware.model.MWSocialNetwork;
import com.mcdonalds.sdk.connectors.middleware.model.MWStore;
import com.mcdonalds.sdk.connectors.middleware.model.MWStoreCatalogVersion;
import com.mcdonalds.sdk.connectors.middleware.request.MWGetCatalogUpdatedRequest;
import com.mcdonalds.sdk.connectors.middleware.response.MWJSONResponse;
import com.mcdonalds.sdk.connectors.utils.SerializableSparseArray;
import com.mcdonalds.sdk.modules.models.Catalog;
import com.mcdonalds.sdk.modules.models.SocialNetwork;
import com.mcdonalds.sdk.modules.models.StoreCatalog;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.CatalogManager;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.services.data.sync.CatalogVersionType;
import com.mcdonalds.sdk.services.log.MCDLog;
import com.mcdonalds.sdk.services.network.SessionManager;
import com.mcdonalds.sdk.utils.ListUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MWCatalogManager {
    public static final String CACHE_KEY_CATALOGS_UPDATED = "CACHE_KEY_CATALOGS_UPDATED";
    public static final String CONFIG_SERIALIZE_MARKET_CATALOG = "connectors.Middleware.serializeCachingMarketCatalog";
    public static final long DAY_IN_MILLISECONDS = TimeUnit.DAYS.toMillis(1);
    public static final String ECP_VERSION_KEY = "connectors.Middleware.eCPVersion";
    public static final long MARKET_CATALOG_CACHE_EXPIRATION = TimeUnit.HOURS.toMillis(1);
    private static final List<CatalogVersionType> MARKET_TYPES = Arrays.asList(new CatalogVersionType[]{CatalogVersionType.DisplayCategory, CatalogVersionType.Names, CatalogVersionType.Recipes, CatalogVersionType.PaymentMethod, CatalogVersionType.StaticData, CatalogVersionType.Promotion, CatalogVersionType.Language, CatalogVersionType.FeedbackTypeName, CatalogVersionType.TenderType, CatalogVersionType.MenuType, CatalogVersionType.SocialMedia});
    private String MARKET_LAST_UPDATE_KEY = "market_last_update_key";
    private AsyncException mAsyncException;
    private boolean mCatalogUpdateInProgress = false;
    private AsyncListener<Catalog> mConnectorListener;
    private AsyncToken mConnectorToken;
    private final List<CatalogVersionType> mCurrentUpdateTypes;
    private final List<Runnable> mDelayedCatalogUpdateTasks = new ArrayList();
    private final List<Runnable> mDelayedTasks = new ArrayList();
    private boolean mFetchMarketTypes = false;
    private boolean mFetchStoreTypes = false;
    private final Handler mHandler;
    private final MWConnectorShared mSharedData;
    private SerializableSparseArray<MWSocialNetwork> mSocialNetworkTypes = null;
    private Integer mStoreId;
    private String mUsername;

    /* renamed from: com.mcdonalds.sdk.connectors.middleware.helpers.MWCatalogManager$3 */
    class C24783 implements AsyncListener<MWJSONResponse<String>> {
        C24783() {
        }

        public void onResponse(MWJSONResponse<String> response, AsyncToken token, AsyncException exception) {
            AsyncException localException = MiddlewareConnectorUtils.exceptionFromResults(response, exception);
            if (localException != null) {
                MWCatalogManager.this.mAsyncException = localException;
                MWCatalogManager.this.finishUpdate();
            } else if (response == null || response.getData() == null) {
                MWCatalogManager.this.finishUpdate();
            } else {
                new MergeCatalogBackgroundTask((String) response.getData(), new MarketCatalogRequestListener(MWCatalogManager.this, null)).execute();
            }
        }
    }

    private class MarketCatalogRequestListener implements MergeCatalogBackgroundTaskListener {
        MWMarket mCachedMarketCatalog;
        Catalog mCatalog;

        private MarketCatalogRequestListener() {
        }

        /* synthetic */ MarketCatalogRequestListener(MWCatalogManager x0, C24761 x1) {
            this();
        }

        public void onPerformBackgroundTask(MWCatalog catalog, boolean isCachedMarketCatalog) {
            this.mCatalog = new Catalog();
            MWMarket mwMarket = catalog.market;
            if (!isCachedMarketCatalog) {
                mwMarket = MWCatalogManager.this.cacheMarketCatalog(mwMarket);
            }
            this.mCachedMarketCatalog = mwMarket;
            if (this.mCachedMarketCatalog != null) {
                this.mCatalog.setMarketCatalog(this.mCachedMarketCatalog.toMarketCatalog(MWCatalogManager.this.mSharedData.getContext()));
                this.mCatalog.setStoreCatalog(new ArrayList());
            }
        }

        public void onPerformPostExecute(MWCatalog catalog) {
            MWCatalogManager.this.updateMarketLastUpdateTimestamp();
            if (MWCatalogManager.this.mFetchStoreTypes) {
                MWCatalogManager.this.finishMarketUpdate(this.mCatalog, this.mCachedMarketCatalog);
            } else {
                MWCatalogManager.this.finishUpdate(this.mCatalog);
            }
        }
    }

    private class StoresCatalogRequestListener implements MergeCatalogBackgroundTaskListener {
        private MWMarket mCachedMarketCatalog;
        private Catalog mProcessedMarketCatalog;

        StoresCatalogRequestListener(Catalog processedMarketCatalog, MWMarket cachedMarketCatalog) {
            this.mProcessedMarketCatalog = processedMarketCatalog;
            this.mCachedMarketCatalog = cachedMarketCatalog;
        }

        public void onPerformBackgroundTask(MWCatalog catalog, boolean isCachedMarketCatalog) {
            List<StoreCatalog> storeCatalogs = new ArrayList();
            for (MWStore mwStore : catalog.stores) {
                storeCatalogs.add(mwStore.toStoreCatalog(this.mCachedMarketCatalog));
            }
            this.mProcessedMarketCatalog.setStoreCatalog(storeCatalogs);
        }

        public void onPerformPostExecute(MWCatalog catalog) {
            MWCatalogManager.this.mAsyncException = null;
            MWCatalogManager.this.finishUpdate(this.mProcessedMarketCatalog);
        }
    }

    public MWCatalogManager(MWConnectorShared shared) {
        this.mSharedData = shared;
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mCurrentUpdateTypes = new ArrayList();
    }

    public void updateSocialNetworkCatalog(final AsyncToken connectorToken, final AsyncListener<List<SocialNetwork>> connectorListener) {
        boolean fetchFromNetwork = this.mSocialNetworkTypes == null || this.mSocialNetworkTypes.size() == 0;
        if (fetchFromNetwork) {
            MWCatalogVersion version = new MWCatalogVersion();
            MWCatalogVersionItem versionItem = new MWCatalogVersionItem();
            versionItem.type = CatalogVersionType.SocialMedia;
            versionItem.version = CatalogManager.VERSION_0;
            version.market.add(versionItem);
            this.mSharedData.getNetworkConnection().processRequest(new MWGetCatalogUpdatedRequest(SessionManager.getInstance().getToken(), version, null), new AsyncListener<MWJSONResponse<String>>() {
                public void onResponse(MWJSONResponse<String> response, AsyncToken token, AsyncException exception) {
                    AsyncException localException = MiddlewareConnectorUtils.exceptionFromResults(response, exception);
                    if (localException != null) {
                        connectorListener.onResponse(null, connectorToken, localException);
                    } else if (response != null) {
                        MCDLog.temp("Social Networks");
                        MWCatalog catalog = new MergeCatalog((String) response.getData()).processData();
                        List<SocialNetwork> socialNetworks = new ArrayList();
                        if (catalog.market.socialNetworks != null && catalog.market.socialNetworks.size() > 0) {
                            if (MWCatalogManager.this.mSocialNetworkTypes == null) {
                                MWCatalogManager.this.mSocialNetworkTypes = new SerializableSparseArray();
                            }
                            int size = catalog.market.socialNetworks.size();
                            for (int i = 0; i < size; i++) {
                                MWSocialNetwork socialNetwork = (MWSocialNetwork) catalog.market.socialNetworks.get(i);
                                MWCatalogManager.this.mSocialNetworkTypes.put(socialNetwork.socialNetworkID.intValue(), socialNetwork);
                                socialNetworks.add(socialNetwork.toSocialNetwork());
                            }
                        }
                        connectorListener.onResponse(socialNetworks, connectorToken, null);
                    }
                }
            });
            return;
        }
        List<SocialNetwork> socialNetworks = new ArrayList();
        for (int i = 0; i < this.mSocialNetworkTypes.size(); i++) {
            socialNetworks.add(((MWSocialNetwork) this.mSocialNetworkTypes.get(this.mSocialNetworkTypes.keyAt(i))).toSocialNetwork());
        }
        connectorListener.onResponse(socialNetworks, connectorToken, null);
    }

    @Deprecated
    public void commonUpdateAllCatalogs(String username, String storeId, AsyncToken connectorToken, AsyncListener<Catalog> connectorListener) {
        updateCatalogs(username, storeId, connectorToken, connectorListener);
    }

    public void updateCatalogs(String username, String storeId, AsyncToken connectorToken, AsyncListener<Catalog> connectorListener) {
        final String str = username;
        final AsyncToken asyncToken = connectorToken;
        final AsyncListener<Catalog> asyncListener = connectorListener;
        final String str2 = storeId;
        Runnable task = new Runnable() {
            public void run() {
                Integer num = null;
                MWCatalogManager.this.mCatalogUpdateInProgress = true;
                MWCatalogManager.this.mUsername = str;
                MWCatalogManager.this.mConnectorToken = asyncToken;
                MWCatalogManager.this.mConnectorListener = asyncListener;
                MWCatalogManager.this.mAsyncException = null;
                MWCatalogManager mWCatalogManager = MWCatalogManager.this;
                if (str2 != null) {
                    num = Integer.valueOf(str2);
                }
                mWCatalogManager.mStoreId = num;
                MCDLog.temp("Update Catalog Store: " + (MWCatalogManager.this.mStoreId == null ? "<none/null>" : MWCatalogManager.this.mStoreId));
                MWCatalogManager.this.mCurrentUpdateTypes.clear();
                MWCatalogManager.this.mFetchMarketTypes = false;
                MWCatalogManager.this.mFetchStoreTypes = false;
                Context context = MWCatalogManager.this.mSharedData.getContext();
                boolean firstCall = CatalogManager.isFirstCatalogLoad(context);
                String marketName = Configuration.getSharedInstance().getConfigName();
                if (firstCall || MWCatalogManager.this.isMarketCatalogsOutdated(context, marketName)) {
                    if (MWCatalogManager.this.isMarketCatalogsOutdated(context, marketName)) {
                        CatalogManager.clearAllCatalogData(MWCatalogManager.this.mSharedData.getContext());
                    }
                    MWCatalogManager.this.mFetchMarketTypes = true;
                    MWCatalogManager.this.mCurrentUpdateTypes.addAll(MWCatalogManager.desiredMarketCatalogTypes());
                    CatalogManager.setFirstCatalogLoad(MWCatalogManager.this.mSharedData.getContext(), false);
                }
                if (MWCatalogManager.this.mStoreId != null) {
                    List<CatalogVersionType> storeCatalogTypes = MWCatalogManager.desiredStoreCatalogTypes();
                    if (MWCatalogManager.this.isStoreCatalogsOutdated(MWCatalogManager.this.mSharedData.getContext(), MWCatalogManager.this.mStoreId.intValue(), storeCatalogTypes)) {
                        MWCatalogManager.this.mFetchStoreTypes = true;
                        MWCatalogManager.this.mCurrentUpdateTypes.addAll(storeCatalogTypes);
                    }
                }
                MWCatalogManager.this.updateCatalogs();
            }
        };
        if (this.mCatalogUpdateInProgress) {
            this.mDelayedCatalogUpdateTasks.add(task);
        } else {
            this.mHandler.post(task);
        }
    }

    private void updateCatalogs() {
        if (this.mCurrentUpdateTypes.isEmpty()) {
            MCDLog.temp("Catalog Manager: SKIPPING update, no catalog types need updating");
            finishUpdate();
            return;
        }
        performSingleUpdate();
    }

    private void performSingleUpdate() {
        if (this.mFetchMarketTypes) {
            performSingleMarketUpdate();
        } else if (this.mFetchStoreTypes) {
            new MergeCatalogBackgroundTask(new MarketCatalogRequestListener(this, null)).execute();
        }
    }

    private void performSingleMarketUpdate() {
        MWCatalogVersion queryVersion = new MWCatalogVersion();
        String marketName = Configuration.getSharedInstance().getConfigName();
        int size = this.mCurrentUpdateTypes.size();
        for (int i = 0; i < size; i++) {
            CatalogVersionType type = (CatalogVersionType) this.mCurrentUpdateTypes.get(i);
            MWCatalogVersionItem version = new MWCatalogVersionItem();
            version.type = type;
            if (MARKET_TYPES.contains(type)) {
                version.version = LocalDataManager.getSharedInstance().getMarketCatalogVersions(marketName, type.getName());
                queryVersion.market.add(version);
            }
        }
        this.mSharedData.getNetworkConnection().processRequest(new MWGetCatalogUpdatedRequest(SessionManager.getInstance().getToken(), queryVersion, this.mUsername), new C24783());
    }

    private void finishMarketUpdate(Catalog catalog, MWMarket cachedMarkedCatalog) {
        if (catalog == null || cachedMarkedCatalog == null) {
            if (this.mAsyncException == null) {
                this.mAsyncException = new AsyncException();
            }
            finishUpdate();
            return;
        }
        performSingleStoreUpdate(catalog, cachedMarkedCatalog);
    }

    private void performSingleStoreUpdate(final Catalog parsedMarketCatalog, final MWMarket cachedMarketCatalog) {
        if (this.mStoreId != null) {
            MWCatalogVersion queryVersion = new MWCatalogVersion();
            MWStoreCatalogVersion storeCatalogVersion = new MWStoreCatalogVersion();
            storeCatalogVersion.storeId = this.mStoreId.toString();
            int size = this.mCurrentUpdateTypes.size();
            for (int i = 0; i < size; i++) {
                CatalogVersionType type = (CatalogVersionType) this.mCurrentUpdateTypes.get(i);
                MWCatalogVersionItem version = new MWCatalogVersionItem();
                version.type = type;
                if (!MARKET_TYPES.contains(type)) {
                    version.version = CatalogManager.VERSION_0;
                    storeCatalogVersion.catalog.add(version);
                }
            }
            queryVersion.store.add(storeCatalogVersion);
            this.mSharedData.getNetworkConnection().processRequest(new MWGetCatalogUpdatedRequest(SessionManager.getInstance().getToken(), queryVersion, this.mUsername), new AsyncListener<MWJSONResponse<String>>() {
                public void onResponse(MWJSONResponse<String> response, AsyncToken token, AsyncException exception) {
                    AsyncException localException = MiddlewareConnectorUtils.exceptionFromResults(response, exception);
                    if (localException != null) {
                        MWCatalogManager.this.mAsyncException = localException;
                        MWCatalogManager.this.finishUpdate();
                    } else if (response == null) {
                        MWCatalogManager.this.finishUpdate();
                    } else {
                        new MergeCatalogBackgroundTask((String) response.getData(), new StoresCatalogRequestListener(parsedMarketCatalog, cachedMarketCatalog)).execute();
                    }
                }
            });
        }
    }

    private MWMarket cacheMarketCatalog(MWMarket marketCatalog) {
        boolean serialize = Configuration.getSharedInstance().getBooleanForKey(CONFIG_SERIALIZE_MARKET_CATALOG, false);
        MWMarket cachedMarketCatalog = getCachedMarketCatalog(serialize);
        if (cachedMarketCatalog == null) {
            LocalDataManager.getSharedInstance().addObjectToCache(CatalogManager.CACHE_MARKET_CATALOG, marketCatalog, 0, serialize);
            return marketCatalog;
        } else if (marketCatalog == null) {
            return null;
        } else {
            if (marketCatalog.recipes != null) {
                ListUtils.union(cachedMarketCatalog.recipes, marketCatalog.recipes);
            }
            if (marketCatalog.names != null) {
                ListUtils.union(cachedMarketCatalog.names, marketCatalog.names);
            }
            if (marketCatalog.menuTypes != null) {
                ListUtils.union(cachedMarketCatalog.menuTypes, marketCatalog.menuTypes);
            }
            LocalDataManager.getSharedInstance().updateObjectInCache(CatalogManager.CACHE_MARKET_CATALOG, cachedMarketCatalog, serialize);
            return cachedMarketCatalog;
        }
    }

    private MWMarket getCachedMarketCatalog(boolean serialize) {
        return (MWMarket) LocalDataManager.getSharedInstance().getObjectFromCache(CatalogManager.CACHE_MARKET_CATALOG, MWMarket.class, serialize);
    }

    private boolean hasCachedMarketCatalog(boolean serialize) {
        return LocalDataManager.getSharedInstance().hasObjectInCache(CatalogManager.CACHE_MARKET_CATALOG, serialize);
    }

    private void finishUpdate() {
        finishUpdate(null);
    }

    private void finishUpdate(Catalog catalog) {
        this.mCatalogUpdateInProgress = false;
        if (this.mConnectorListener != null) {
            this.mConnectorListener.onResponse(catalog, this.mConnectorToken, this.mAsyncException);
        }
        this.mConnectorToken = null;
        this.mConnectorListener = null;
        if (this.mDelayedCatalogUpdateTasks.isEmpty()) {
            int size = this.mDelayedTasks.size();
            for (int i = 0; i < size; i++) {
                this.mHandler.post((Runnable) this.mDelayedTasks.get(i));
            }
            this.mDelayedTasks.clear();
            return;
        }
        Runnable task = (Runnable) this.mDelayedCatalogUpdateTasks.get(0);
        this.mDelayedCatalogUpdateTasks.remove(task);
        this.mHandler.post(task);
    }

    public static synchronized List<CatalogVersionType> desiredMarketCatalogTypes() {
        List<CatalogVersionType> types;
        synchronized (MWCatalogManager.class) {
            types = new ArrayList();
            types.add(CatalogVersionType.DisplayCategory);
            if (!MiddlewareConnectorUtils.isUsingECP3()) {
                types.add(CatalogVersionType.Recipes);
                types.add(CatalogVersionType.Names);
            }
            types.add(CatalogVersionType.PaymentMethod);
            types.add(CatalogVersionType.StaticData);
            types.add(CatalogVersionType.Promotion);
            types.add(CatalogVersionType.Language);
            types.add(CatalogVersionType.FeedbackTypeName);
            types.add(CatalogVersionType.TenderType);
            types.add(CatalogVersionType.MenuType);
        }
        return types;
    }

    public static synchronized List<CatalogVersionType> desiredStoreCatalogTypes() {
        List asList;
        synchronized (MWCatalogManager.class) {
            asList = Arrays.asList(new CatalogVersionType[]{CatalogVersionType.Products, CatalogVersionType.ProductPrices});
        }
        return asList;
    }

    private boolean isMarketCatalogsOutdated(Context context, String marketName) {
        if (!hasCachedMarketCatalog(Configuration.getSharedInstance().getBooleanForKey(CONFIG_SERIALIZE_MARKET_CATALOG, false))) {
            return true;
        }
        if (new Date().getTime() - getMarketLastUpdateTimestamp(context, marketName) <= 7200000) {
            return false;
        }
        return true;
    }

    private long getMarketLastUpdateTimestamp(Context context, String marketName) {
        return context.getSharedPreferences(marketName, 0).getLong(this.MARKET_LAST_UPDATE_KEY, 0);
    }

    private void updateMarketLastUpdateTimestamp() {
        Editor editor = getMarketSharedPreferences(this.mSharedData.getContext()).edit();
        editor.putLong(this.MARKET_LAST_UPDATE_KEY, new Date().getTime());
        editor.apply();
    }

    private SharedPreferences getMarketSharedPreferences(Context context) {
        return context.getSharedPreferences(Configuration.getSharedInstance().getConfigName(), 0);
    }

    private boolean isStoreCatalogsOutdated(Context context, int storeId, List<CatalogVersionType> catalogVersionTypes) {
        List<MWCatalogVersionItem> timestamps = LocalDataManager.getSharedInstance().getStoreCatalogTimestamps(storeId);
        CatalogManager.checkStoreCache(storeId, context);
        if (ListUtils.isEmpty(timestamps)) {
            return true;
        }
        for (CatalogVersionType sharedPrefItem : catalogVersionTypes) {
            String versionTimestamp = LocalDataManager.getSharedInstance().getStoreCatalogVersion(String.valueOf(storeId), sharedPrefItem.getName());
            for (MWCatalogVersionItem objCacheItem : timestamps) {
                if (sharedPrefItem == objCacheItem.type && !versionTimestamp.equals(objCacheItem.version)) {
                    return true;
                }
            }
        }
        if (CatalogManager.hasCatalogDownloaded(context, storeId)) {
            return false;
        }
        return true;
    }
}
