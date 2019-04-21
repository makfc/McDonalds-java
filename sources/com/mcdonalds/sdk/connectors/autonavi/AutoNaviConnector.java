package com.mcdonalds.sdk.connectors.autonavi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.GeocodeSearch.OnGeocodeSearchListener;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.mcdonalds.sdk.AsyncCounter;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.C3883R;
import com.mcdonalds.sdk.connectors.BaseConnector;
import com.mcdonalds.sdk.connectors.StoreLocatorConnector;
import com.mcdonalds.sdk.connectors.autonavi.AutoNavi.Builder;
import com.mcdonalds.sdk.connectors.autonavi.AutoNavi.Methods;
import com.mcdonalds.sdk.connectors.storelocator.StoreLocatorConnectorQueryParameters;
import com.mcdonalds.sdk.modules.models.CustomerAddress;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.log.SafeLog;
import com.mcdonalds.sdk.services.network.RequestManager;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AutoNaviConnector extends BaseConnector implements StoreLocatorConnector {
    private static final String CONFIG = "connectors.AutoNavi.storeLocator";
    private static final String CONFIG_API_KEY = "connectors.AutoNavi.storeLocator.apiKey";
    public static final String CONFIG_BACKUP_LOCATION = "connectors.AutoNavi.storeLocator.backupUserLocationForKeywordSearch";
    private static final String CONFIG_BASE_URL = "connectors.AutoNavi.storeLocator.baseURL";
    private static final String CONFIG_INFO_MISSING_MSG = "Configuration missing data needed for Store Locator";
    private static final String CONFIG_MAPS_ENGINE_ENDPOINT = "connectors.AutoNavi.storeLocator.endPoint";
    private static final String CONFIG_TABLES_ID = "connectors.AutoNavi.storeLocator.tables";
    public static final String CONFIG_USE_KEYWORD_SEARCH = "connectors.AutoNavi.storeLocator.keywordSearchWithAPISearch";
    public static final int DEFAULT_SEARCH_LIMIT = 75;
    public static final int DEFAULT_SEARCH_RADIUS = 50000;
    private static final String LOG_TAG = AutoNaviConnector.class.getSimpleName();
    public static final String NAME = "autonavi";
    public static final String TOKEN_ALL_STORES = "storeLocator.stores";
    public static final String TOKEN_FILTERS = "storeLocator.filters";
    public static final String TOKEN_SINGLE_STORE = "storeLocator.singleStore";
    public static final String TOKEN_STORES_BY_ID = "storeLocator.storesById";
    public static final String URL_FORMAT = "{0}/{1}";
    private String mApiBaseUrl;
    private String mApiIdBaseUrl;
    private String mApiKey;
    private String mApiTables;

    public AutoNaviConnector(Context context) {
        AutoNaviStore.init(context);
        setContext(context);
        setConnection(RequestManager.register(context));
        setBaseUrl();
    }

    private void setBaseUrl() {
        Configuration config = Configuration.getSharedInstance();
        String base = (String) config.getValueForKey(CONFIG_BASE_URL);
        String endpoint = (String) config.getValueForKey(CONFIG_MAPS_ENGINE_ENDPOINT);
        this.mApiTables = (String) config.getValueForKey(CONFIG_TABLES_ID);
        this.mApiKey = (String) config.getValueForKey(CONFIG_API_KEY);
        this.mApiBaseUrl = MessageFormat.format("{0}/{1}", new Object[]{base, endpoint});
        this.mApiIdBaseUrl = MessageFormat.format("{0}/{1}", new Object[]{base, "datamanage/data/"});
    }

    public AsyncToken requestStores(StoreLocatorConnectorQueryParameters parameters, AsyncListener<List<Store>> listener) {
        AsyncToken token = new AsyncToken("storeLocator.stores");
        if (isConfigured()) {
            requestStores(parameters, listener, token);
        } else {
            throwNoConfigurationException(listener, token);
        }
        return token;
    }

    @NonNull
    private List<String> getApiFilters(@Nullable List<String> filters) {
        List<String> apiFilters = new ArrayList();
        if (filters != null) {
            int size = filters.size();
            for (int i = 0; i < size; i++) {
                String key = AutoNaviStore.getFilterKey((String) filters.get(i));
                if (key != null) {
                    apiFilters.add(key);
                }
            }
        }
        return apiFilters;
    }

    public AsyncToken requestStoreWithId(String storeId, AsyncListener<List<Store>> listener) {
        AsyncToken token = new AsyncToken("storeLocator.singleStore");
        if (isConfigured()) {
            Builder builder = new Builder(this.mApiIdBaseUrl, this.mApiTables, this.mApiKey);
            builder.setMethod(Methods.LIST);
            builder.setId(storeId);
            AutoNaviResponseListener responseListener = new AutoNaviResponseListener(token, listener);
            getNetworkConnection().processRequest(new AutoNaviRequest(builder.build()), responseListener);
        } else {
            throwNoConfigurationException(listener, token);
        }
        return token;
    }

    public AsyncToken requestStoresWithIds(List<String> storeIds, AsyncListener<List<Store>> listener) {
        AsyncToken token = new AsyncToken("storeLocator.storesById");
        if (isConfigured()) {
            final AsyncCounter<Store> counter = new AsyncCounter(storeIds.size(), listener);
            int size = storeIds.size();
            for (int i = 0; i < size; i++) {
                final String storeId = (String) storeIds.get(i);
                requestStoreWithId(storeId, new AsyncListener<List<Store>>() {
                    public void onResponse(List<Store> response, AsyncToken token, AsyncException exception) {
                        if (exception != null) {
                            counter.error(exception);
                        } else if (response.isEmpty()) {
                            counter.success(null);
                        } else {
                            Store found = null;
                            for (Store store : response) {
                                if (storeId.equals(Integer.toString(store.getStoreId()))) {
                                    found = store;
                                    break;
                                }
                            }
                            counter.success(found);
                        }
                    }
                });
            }
        } else {
            throwNoConfigurationException(listener, token);
        }
        return token;
    }

    public AsyncToken requestStoreFilters(AsyncListener<List<String>> listener) {
        AsyncToken token = new AsyncToken("storeLocator.filters");
        listener.onResponse(AutoNaviStore.getFilters(), token, null);
        return token;
    }

    public AsyncToken getDeliveryStore(CustomerAddress address, String username, Date deliveryTime, AsyncListener<Store> asyncListener) {
        SafeLog.m7745e(LOG_TAG, "getDeliveryStore is not supported in AutoNavi");
        return null;
    }

    public AsyncToken getDeliveryStore(CustomerAddress address, String username, Date deliveryTime, boolean isNormalOrder, AsyncListener<Store> asyncListener) {
        SafeLog.m7745e(LOG_TAG, "getDeliveryStore is not supported in AutoNavi");
        return null;
    }

    private void throwNoConfigurationException(AsyncListener<List<Store>> listener, AsyncToken token) {
        listener.onResponse(null, token, new AsyncException(CONFIG_INFO_MISSING_MSG));
    }

    private void requestStores(StoreLocatorConnectorQueryParameters parameters, AsyncListener<List<Store>> listener, AsyncToken token) {
        if (parameters.getSearchString() != null) {
            if (Configuration.getSharedInstance().getBooleanForKey(CONFIG_USE_KEYWORD_SEARCH)) {
                requestStoresByKeywordSearchString(parameters, listener, token);
            } else {
                requestStoresByGeocodeSearchString(parameters, listener, token);
            }
        } else if (parameters.getLatitude() == null || parameters.getLongitude() == null) {
            listener.onResponse(null, token, new AsyncException(getContext().getString(C3883R.string.invalid_parameters_for_store_search)));
        } else {
            requestStoresByLocation(parameters, listener, token);
        }
    }

    private void requestStoresByLocation(StoreLocatorConnectorQueryParameters parameters, AsyncListener<List<Store>> listener, AsyncToken token) {
        String center = String.format("%s,%s", new Object[]{parameters.getLongitude(), parameters.getLatitude()});
        Builder builder = new Builder(this.mApiBaseUrl, this.mApiTables, this.mApiKey);
        builder.setMethod(Methods.AROUND);
        builder.setCenter(center);
        builder.setLimit(75);
        builder.setRadius(DEFAULT_SEARCH_RADIUS);
        List<String> filters = getApiFilters(parameters.getFilters());
        if (!filters.isEmpty()) {
            builder.setFilters(filters);
        }
        AutoNaviResponseListener responseListener = new AutoNaviResponseListener(token, listener);
        getNetworkConnection().processRequest(new AutoNaviRequest(builder.build()), responseListener);
    }

    private void requestStoresByKeywordSearchString(StoreLocatorConnectorQueryParameters parameters, AsyncListener<List<Store>> listener, AsyncToken token) {
        String center = getCenter(parameters);
        String keywords = parameters.getSearchString();
        Builder builder = new Builder(this.mApiBaseUrl, this.mApiTables, this.mApiKey);
        builder.setMethod(Methods.AROUND);
        builder.setCenter(center);
        builder.setLimit(75);
        builder.setRadius(DEFAULT_SEARCH_RADIUS);
        builder.setKeywords(keywords);
        List<String> filters = getApiFilters(parameters.getFilters());
        if (!filters.isEmpty()) {
            builder.setFilters(filters);
        }
        AutoNaviResponseListener responseListener = new AutoNaviResponseListener(token, listener);
        getNetworkConnection().processRequest(new AutoNaviRequest(builder.build()), responseListener);
    }

    private void requestStoresByGeocodeSearchString(final StoreLocatorConnectorQueryParameters parameters, final AsyncListener<List<Store>> listener, final AsyncToken token) {
        GeocodeSearch geocodeSearch = new GeocodeSearch(getContext());
        geocodeSearch.setOnGeocodeSearchListener(new OnGeocodeSearchListener() {
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
            }

            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
                List<GeocodeAddress> addresses;
                if (geocodeResult != null) {
                    addresses = geocodeResult.getGeocodeAddressList();
                } else {
                    addresses = null;
                }
                if (addresses == null || addresses.isEmpty()) {
                    listener.onResponse(null, token, new AsyncException(AutoNaviConnector.this.getContext().getString(C3883R.string.not_able_to_find_address)));
                    return;
                }
                GeocodeAddress address = (GeocodeAddress) addresses.get(0);
                parameters.setLatitude(Double.valueOf(address.getLatLonPoint().getLatitude()));
                parameters.setLongitude(Double.valueOf(address.getLatLonPoint().getLongitude()));
                parameters.setSearchString(null);
                AutoNaviConnector.this.requestStores(parameters, listener);
            }
        });
        geocodeSearch.getFromLocationNameAsyn(new GeocodeQuery(parameters.getSearchString(), null));
    }

    private boolean isConfigured() {
        return (this.mApiTables == null || this.mApiKey == null || this.mApiBaseUrl == null) ? false : true;
    }

    private String getCenter(StoreLocatorConnectorQueryParameters parameters) {
        String lng;
        String lat;
        String backupLocation = Configuration.getSharedInstance().getStringForKey(CONFIG_BACKUP_LOCATION);
        if (parameters.getLongitude() != null && parameters.getLongitude().doubleValue() != 0.0d && parameters.getLatitude() != null && parameters.getLatitude().doubleValue() != 0.0d) {
            lng = String.valueOf(parameters.getLongitude());
            lat = String.valueOf(parameters.getLatitude());
        } else if (backupLocation == null || backupLocation.split(", ").length != 2) {
            lng = "0.0";
            lat = "0.0";
        } else {
            String[] location = backupLocation.split(", ");
            lng = location[0];
            lat = location[1];
        }
        return String.format("%s,%s", new Object[]{lng, lat});
    }
}
