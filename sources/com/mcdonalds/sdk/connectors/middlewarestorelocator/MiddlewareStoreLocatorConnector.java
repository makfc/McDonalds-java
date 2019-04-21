package com.mcdonalds.sdk.connectors.middlewarestorelocator;

import android.content.Context;
import android.content.res.Configuration;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.reflect.TypeToken;
import com.mcdonalds.sdk.AsyncCounter;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.McDonalds;
import com.mcdonalds.sdk.connectors.BaseConnector;
import com.mcdonalds.sdk.connectors.StoreLocatorConnector;
import com.mcdonalds.sdk.connectors.google.GeocodingAsyncTask;
import com.mcdonalds.sdk.connectors.google.GeocodingAsyncTaskListener;
import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middlewarestorelocator.model.MiddlewareStoreLocatorStore;
import com.mcdonalds.sdk.connectors.storelocator.StoreLocatorConnectorQueryParameters;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.models.CustomerAddress;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.services.network.RequestManager;
import com.mcdonalds.sdk.services.network.SessionManager;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;

public class MiddlewareStoreLocatorConnector extends BaseConnector implements StoreLocatorConnector {
    private static final String ACCEPT_LANGUAGE_HEADER_STRING = "Accept-Language";
    private static final String CONFIG_API_KEY = (CONFIG() + ".mcd_apikey");
    private static final String CONFIG_BASE_URL = (CONFIG() + ".baseUrl");
    private static final String CONFIG_DISTANCE = "modules.storeLocator.defaultSearchRadius";
    private static final String CONFIG_ENDPOINT_URL = (CONFIG() + ".endpoint");
    private static final String CONFIG_HEADER_MARKET_ID = (CONFIG() + ".headerMarketId");
    private static final String CONFIG_INFO_MISSING_MSG = "Configuration missing data needed for Store Locator";
    private static final String CONFIG_LOCALE = (CONFIG() + ".languageName");
    private static final String CONFIG_MARKET_ID = (CONFIG() + ".marketId");
    private static final String CONFIG_RESULT_SIZE = (CONFIG() + ".defaultResultSize");
    private static final String CONFIG_STORE_ID_TYPE = (CONFIG() + ".storeIdType");
    private static final String CONFIG_STORE_SEARCH_LANG = (CONFIG() + ".useDeviceLanguage");
    public static final String FILTER_CURRENT_LANGUAGE = "FILTER_CURRENT_LANGUAGE";
    private static final String HEADER_MARKET_ID = "MarketId";
    private static final String LOG_TAG = MiddlewareStoreLocatorConnector.class.getSimpleName();
    private static final String MIN_STORE_ID_DIGIT = "connectors.MiddlewareStoreLocator.storeLocator.minStoreIdDigit";
    public static final String NAME = "middlewarestorelocator";
    private static final String NEW_CONFIG = "connectors.MiddlewareStoreLocator.storeLocator";
    private static final String OLD_CONFIG = "connectors.MWStoreLocator.storeLocator";
    private static final String STORE_LOCATOR_CONNECTOR = "modules.storeLocator.connector";
    public static final String TOKEN_ALL_STORES = "storeLocator.stores";
    public static final String TOKEN_FILTERS = "storeLocator.filters";
    public static final String TOKEN_SINGLE_STORE = "storeLocator.singleStore";
    public static final String TOKEN_STORES_BY_ID = "storeLocator.storesById";
    public static final String URL_FORMAT = "{0}/{1}";
    private String mApiBaseUrl;
    private String mApiKey;
    private double mDistance;
    private List<String> mFilters;
    private ArrayList<AsyncListener<List<String>>> mFiltersListeners = new ArrayList();
    private HashMap<String, String> mFiltersMap;
    private boolean mIsGettingFilters = false;
    private String mLocale;
    private String mMarket;
    private double mResultSize;

    /* renamed from: com.mcdonalds.sdk.connectors.middlewarestorelocator.MiddlewareStoreLocatorConnector$1 */
    class C40081 implements AsyncListener<List<String>> {
        C40081() {
        }

        public void onResponse(List<String> list, AsyncToken token, AsyncException exception) {
            if (exception == null) {
                MiddlewareStoreLocatorStore.init(MiddlewareStoreLocatorConnector.this.mFiltersMap);
            }
        }
    }

    /* renamed from: com.mcdonalds.sdk.connectors.middlewarestorelocator.MiddlewareStoreLocatorConnector$5 */
    class C40125 extends TypeToken<String> {
        C40125() {
        }
    }

    /* renamed from: com.mcdonalds.sdk.connectors.middlewarestorelocator.MiddlewareStoreLocatorConnector$6 */
    class C40136 implements AsyncListener<List<String>> {
        C40136() {
        }

        public void onResponse(List<String> response, AsyncToken token, AsyncException exception) {
            MiddlewareStoreLocatorConnector.this.mIsGettingFilters = false;
            if (exception == null) {
                MiddlewareStoreLocatorConnector.this.mFilters = new ArrayList();
                MiddlewareStoreLocatorConnector.this.mFiltersMap = new HashMap();
                Context context = McDonalds.getContext();
                if (context != null) {
                    if (VERSION.SDK_INT >= 24) {
                        Configuration configuration = context.getResources().getConfiguration();
                        configuration.setLocale(new Locale(LocalDataManager.getSharedInstance().getDeviceLanguage()));
                        context = context.createConfigurationContext(configuration);
                    }
                    List<String> unusedFilters = (List) com.mcdonalds.sdk.services.configuration.Configuration.getSharedInstance().getValueForKey(MiddlewareStoreLocatorConnector.CONFIG() + ".unusedFilters");
                    TreeMap<String, String> treeMap = new TreeMap();
                    for (String filter : response) {
                        if (unusedFilters == null || unusedFilters.isEmpty() || !unusedFilters.contains(filter)) {
                            int resourceId = context.getResources().getIdentifier("store_locator_filter_" + filter.toLowerCase(), "string", context.getPackageName());
                            if (resourceId > 0) {
                                String fromFilter = context.getString(resourceId);
                                if (!TextUtils.isEmpty(fromFilter)) {
                                    treeMap.put(fromFilter, filter);
                                }
                            }
                        }
                    }
                    MiddlewareStoreLocatorConnector.this.mFilters.addAll(treeMap.keySet());
                    MiddlewareStoreLocatorConnector.this.mFiltersMap.putAll(treeMap);
                }
            }
            Iterator it = MiddlewareStoreLocatorConnector.this.mFiltersListeners.iterator();
            while (it.hasNext()) {
                ((AsyncListener) it.next()).onResponse(MiddlewareStoreLocatorConnector.this.mFilters, token, exception);
            }
            MiddlewareStoreLocatorConnector.this.mFiltersListeners.clear();
        }
    }

    /* renamed from: com.mcdonalds.sdk.connectors.middlewarestorelocator.MiddlewareStoreLocatorConnector$7 */
    class C40147 implements AsyncListener<List<String>> {
        C40147() {
        }

        public void onResponse(List<String> response, AsyncToken token, AsyncException exception) {
            if (response != null) {
                MiddlewareStoreLocatorConnector.this.getApiFilters(response);
            }
        }
    }

    private static String CONFIG() {
        String retString = NEW_CONFIG;
        if (((String) com.mcdonalds.sdk.services.configuration.Configuration.getSharedInstance().getValueForKey(STORE_LOCATOR_CONNECTOR)).equalsIgnoreCase("MWStoreLocator")) {
            return OLD_CONFIG;
        }
        return retString;
    }

    public MiddlewareStoreLocatorConnector(Context context) {
        setContext(context);
        setConnection(RequestManager.register(context));
        setBaseUrl();
        if (this.mFiltersMap == null || this.mFiltersMap.isEmpty()) {
            requestStoreFilters(new C40081());
        } else {
            MiddlewareStoreLocatorStore.init(this.mFiltersMap);
        }
    }

    private void setBaseUrl() {
        com.mcdonalds.sdk.services.configuration.Configuration config = com.mcdonalds.sdk.services.configuration.Configuration.getSharedInstance();
        String base = (String) config.getValueForKey(CONFIG_BASE_URL);
        String endpoint = (String) config.getValueForKey(CONFIG_ENDPOINT_URL);
        this.mApiBaseUrl = MessageFormat.format("{0}/{1}", new Object[]{base, endpoint});
        this.mApiKey = (String) config.getValueForKey(CONFIG_API_KEY);
        this.mMarket = (String) config.getValueForKey(CONFIG_MARKET_ID);
        this.mDistance = ((Double) config.getValueForKey(CONFIG_DISTANCE)).doubleValue();
        this.mResultSize = ((Double) config.getValueForKey(CONFIG_RESULT_SIZE)).doubleValue();
        this.mLocale = (String) config.getValueForKey(CONFIG_LOCALE);
    }

    public AsyncToken requestStores(StoreLocatorConnectorQueryParameters parameters, AsyncListener<List<Store>> listener) {
        AsyncToken asyncToken = new AsyncToken("storeLocator.stores");
        if (!isConfigured()) {
            throwNoConfigurationException(listener, asyncToken);
        } else if (parameters.getLatitude() != null && parameters.getLongitude() != null) {
            int distance = (int) ((this.mDistance + 0.5d) / 1000.0d);
            int resultSize = (int) (this.mResultSize + 0.5d);
            String locale = this.mLocale;
            if (com.mcdonalds.sdk.services.configuration.Configuration.getSharedInstance().getBooleanForKey(CONFIG_STORE_SEARCH_LANG)) {
                String[] arr = this.mLocale.split("-");
                if (arr.length > 1) {
                    locale = LocalDataManager.getSharedInstance().getDeviceLanguage() + "-" + arr[1];
                }
            }
            MiddlewareStoreLocator builder = new MiddlewareStoreLocator(this.mApiBaseUrl, this.mMarket, locale, resultSize);
            builder.searchByDistance(parameters.getLatitude().doubleValue(), parameters.getLongitude().doubleValue(), distance);
            List<String> filters = getApiFilters(parameters.getFilters());
            if (!filters.isEmpty()) {
                builder.setAttributes(filters);
            }
            MiddlewareStoreLocatorResponseListener middlewareStoreLocatorResponseListener = new MiddlewareStoreLocatorResponseListener(asyncToken, listener);
            middlewareStoreLocatorResponseListener.setOriginalLatLng(new LatLng(parameters.getLatitude().doubleValue(), parameters.getLongitude().doubleValue()));
            getNetworkConnection().processRequest(new MiddlewareStoreLocatorRequest(builder.build(), SessionManager.getInstance().getToken()), middlewareStoreLocatorResponseListener);
        } else if (Geocoder.isPresent()) {
            Location mockLocation = ModuleManager.getMockLocation();
            if (mockLocation == null) {
                GeocodingAsyncTask asyncTask = new GeocodingAsyncTask();
                Object[] objArr = new Object[3];
                objArr[0] = parameters.getSearchString();
                objArr[1] = getContext();
                final StoreLocatorConnectorQueryParameters storeLocatorConnectorQueryParameters = parameters;
                final AsyncListener<List<Store>> asyncListener = listener;
                final AsyncToken asyncToken2 = asyncToken;
                objArr[2] = new GeocodingAsyncTaskListener() {
                    public void onResponse(Double latitude, Double longitude, String errorMsg) {
                        if (errorMsg == null) {
                            storeLocatorConnectorQueryParameters.setSearchString(null);
                            storeLocatorConnectorQueryParameters.setLatitude(latitude);
                            storeLocatorConnectorQueryParameters.setLongitude(longitude);
                            MiddlewareStoreLocatorConnector.this.requestStores(storeLocatorConnectorQueryParameters, asyncListener);
                            return;
                        }
                        asyncListener.onResponse(null, asyncToken2, new AsyncException(errorMsg));
                    }
                };
                if (asyncTask instanceof AsyncTask) {
                    AsyncTaskInstrumentation.execute(asyncTask, objArr);
                } else {
                    asyncTask.execute(objArr);
                }
            } else {
                parameters.setSearchString(null);
                parameters.setLatitude(Double.valueOf(mockLocation.getLatitude()));
                parameters.setLongitude(Double.valueOf(mockLocation.getLongitude()));
                requestStores(parameters, listener);
            }
        } else {
            final AsyncListener<List<Store>> asyncListener2 = listener;
            final AsyncToken asyncToken3 = asyncToken;
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    asyncListener2.onResponse(null, asyncToken3, new AsyncException("Address lookup unavailable on this device"));
                }
            });
        }
        return asyncToken;
    }

    public AsyncToken requestStoreWithId(String storeId, AsyncListener<List<Store>> listener) {
        AsyncToken token = new AsyncToken("storeLocator.singleStore");
        if (isConfigured()) {
            MiddlewareStoreLocator builder = new MiddlewareStoreLocator(this.mApiBaseUrl, this.mMarket, this.mLocale, (int) (this.mResultSize + 0.5d));
            int minStoreIdDigit = com.mcdonalds.sdk.services.configuration.Configuration.getSharedInstance().getIntForKey(MIN_STORE_ID_DIGIT);
            if (minStoreIdDigit > 0) {
                try {
                    int i = Integer.parseInt(storeId);
                    Formatter f = new Formatter();
                    f.format("%0" + minStoreIdDigit + "d", new Object[]{Integer.valueOf(i)});
                    storeId = f.toString();
                } catch (NumberFormatException e) {
                }
            }
            builder.addSearchParam(MiddlewareStoreLocatorSearchParam.StoreId, storeId);
            String storeIdType = (String) com.mcdonalds.sdk.services.configuration.Configuration.getSharedInstance().getValueForKey(CONFIG_STORE_ID_TYPE);
            if (TextUtils.isEmpty(storeIdType)) {
                builder.addSearchParam(MiddlewareStoreLocatorSearchParam.StoreIdType, "NatlStrNumber");
            } else {
                builder.addSearchParam(MiddlewareStoreLocatorSearchParam.StoreIdType, storeIdType);
            }
            MiddlewareStoreLocatorResponseListener responseListener = new MiddlewareStoreLocatorResponseListener(token, listener);
            getNetworkConnection().processRequest(new MiddlewareStoreLocatorRequest(builder.build(), SessionManager.getInstance().getToken()), responseListener);
        } else {
            throwNoConfigurationException(listener, token);
        }
        return token;
    }

    public AsyncToken requestStoresWithIds(List<String> storeIds, AsyncListener<List<Store>> listener) {
        AsyncToken token = new AsyncToken("storeLocator.storesById");
        if (isConfigured()) {
            final AsyncCounter<Store> counter = new AsyncCounter(storeIds.size(), listener);
            AsyncListener<List<Store>> singleStoreListener = new AsyncListener<List<Store>>() {
                public void onResponse(List<Store> response, AsyncToken token, AsyncException exception) {
                    if (exception != null) {
                        counter.error(exception);
                    } else if (response.isEmpty()) {
                        counter.success(null);
                    } else {
                        counter.success(response.get(0));
                    }
                }
            };
            for (String id : storeIds) {
                requestStoreWithId(id, singleStoreListener);
            }
        } else {
            throwNoConfigurationException(listener, token);
        }
        return token;
    }

    public AsyncToken requestStoreFilters(AsyncListener<List<String>> listener) {
        AsyncToken token = new AsyncToken("storeLocator.filters");
        String languageResponse = (String) LocalDataManager.getSharedInstance().getObjectFromCache(FILTER_CURRENT_LANGUAGE, new C40125().getType());
        String currentLanguage = com.mcdonalds.sdk.services.configuration.Configuration.getSharedInstance().getCurrentLanguage();
        if (!currentLanguage.equals(languageResponse) || this.mFilters == null || this.mFilters.isEmpty()) {
            LocalDataManager.getSharedInstance().addObjectToCache(FILTER_CURRENT_LANGUAGE, currentLanguage, 0);
            this.mFiltersListeners.add(listener);
            if (!this.mIsGettingFilters) {
                if (isConfigured()) {
                    String url = ((String) com.mcdonalds.sdk.services.configuration.Configuration.getSharedInstance().getValueForKey(CONFIG_BASE_URL)) + "/restaurant/location/marketAttributes?market=" + this.mMarket;
                    MiddlewareStoreLocatorAttributesResponseListener responseListener = new MiddlewareStoreLocatorAttributesResponseListener(new C40136());
                    MiddlewareStoreLocatorAttributesRequest request = new MiddlewareStoreLocatorAttributesRequest(url);
                    request.addHeader(MiddlewareConnector.CONFIG_HEADER_API_KEY, this.mApiKey);
                    if (com.mcdonalds.sdk.services.configuration.Configuration.getSharedInstance().hasKey(CONFIG_HEADER_MARKET_ID)) {
                        String headerMarketId = com.mcdonalds.sdk.services.configuration.Configuration.getSharedInstance().getStringForKey(CONFIG_HEADER_MARKET_ID);
                        if (!TextUtils.isEmpty(headerMarketId)) {
                            request.addHeader(HEADER_MARKET_ID, headerMarketId);
                        }
                    } else {
                        request.addHeader(HEADER_MARKET_ID, this.mMarket);
                    }
                    request.addHeader(ACCEPT_LANGUAGE_HEADER_STRING, this.mLocale);
                    this.mIsGettingFilters = true;
                    getNetworkConnection().processRequest(request, responseListener);
                } else {
                    AsyncException exception = new AsyncException(CONFIG_INFO_MISSING_MSG);
                    Iterator it = this.mFiltersListeners.iterator();
                    while (it.hasNext()) {
                        ((AsyncListener) it.next()).onResponse(null, token, exception);
                    }
                    this.mFiltersListeners.clear();
                    this.mIsGettingFilters = false;
                }
            }
        } else {
            listener.onResponse(this.mFilters, token, null);
        }
        return token;
    }

    public AsyncToken getDeliveryStore(CustomerAddress address, String username, Date deliveryTime, AsyncListener<Store> asyncListener) {
        return null;
    }

    public AsyncToken getDeliveryStore(CustomerAddress address, String username, Date deliveryTime, boolean isNormalOrder, AsyncListener<Store> asyncListener) {
        return null;
    }

    @NonNull
    private List<String> getApiFilters(@Nullable List<String> filters) {
        List<String> apiFilters = new ArrayList();
        if (filters == null || this.mFiltersMap == null) {
            requestStoreFilters(new C40147());
        } else {
            int size = filters.size();
            for (int i = 0; i < size; i++) {
                String attribute = (String) this.mFiltersMap.get((String) filters.get(i));
                if (attribute != null) {
                    apiFilters.add(attribute);
                }
            }
        }
        return apiFilters;
    }

    private void throwNoConfigurationException(AsyncListener<List<Store>> listener, AsyncToken token) {
        listener.onResponse(null, token, new AsyncException(CONFIG_INFO_MISSING_MSG));
    }

    private boolean isConfigured() {
        return (this.mApiKey == null || this.mApiBaseUrl == null) ? false : true;
    }
}
