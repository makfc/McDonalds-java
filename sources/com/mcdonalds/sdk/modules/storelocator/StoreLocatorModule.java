package com.mcdonalds.sdk.modules.storelocator;

import android.content.Context;
import android.content.res.Configuration;
import android.location.Address;
import android.location.Location;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.maps.model.LatLng;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.C3883R;
import com.mcdonalds.sdk.connectors.ConnectorManager;
import com.mcdonalds.sdk.connectors.StoreLocatorConnector;
import com.mcdonalds.sdk.connectors.storelocator.StoreLocatorConnectorQueryParameters;
import com.mcdonalds.sdk.modules.BaseModule;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.Facility;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.services.data.repository.FacilityRepository;
import com.mcdonalds.sdk.services.location.LocationServicesManager;
import com.mcdonalds.sdk.services.location.providers.LocationProvider.LocationUpdateListener;
import com.mcdonalds.sdk.utils.ListUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class StoreLocatorModule extends BaseModule implements ConnectionCallbacks, OnConnectionFailedListener {
    private static final String AUTONAVI_CONNECTOR_VALUE = "AutoNavi";
    private static final long DEFAULT_LOCATION_SEARCH_TIMEOUT = 15000;
    public static final int DEFAULT_MAX_RESULTS = 75;
    public static final String NAME = "storeLocator";
    private static final String STORE_LOCATOR_CONNECTOR = "modules.storeLocator.connector";
    private static final String TAG = "modules.StoreLocator";
    private final List<AsyncToken> mActiveTokens = new ArrayList();
    private final String mConnectorImpl;
    private final Map<AsyncToken, AsyncToken> mConnectorTokenMap = new HashMap();
    private Context mContext;
    private final Handler mHandler;
    private List<String> mLastFiltersQueried;
    private Location mLastKnownLocation;
    private LatLng mLastLatLngQueried;
    private List<Store> mLastRetrievedStores = new ArrayList();
    private Timer mLocationTimer;
    private final Map<String, Store> mObjectCache = new HashMap();
    private final Map<AsyncToken, AsyncToken> mTokenMap = new HashMap();

    public enum NativeMapsSDK {
        Google,
        AutoNavi,
        AutoNavi2D
    }

    public StoreLocatorModule(Context context) {
        this.mContext = context;
        if (VERSION.SDK_INT >= 24) {
            Configuration configuration = context.getResources().getConfiguration();
            configuration.setLocale(new Locale(LocalDataManager.getSharedInstance().getDeviceLanguage()));
            this.mContext = this.mContext.createConfigurationContext(configuration);
        }
        String connector = (String) com.mcdonalds.sdk.services.configuration.Configuration.getSharedInstance().getValueForKey(STORE_LOCATOR_CONNECTOR);
        if (connector.equalsIgnoreCase("MWStoreLocator")) {
            connector = "MiddlewareStoreLocator";
        }
        this.mConnectorImpl = connector;
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public AsyncToken getStoreForId(String key, AsyncListener<List<Store>> listener) {
        AsyncToken requestToken = new AsyncToken("getStoreForId");
        this.mActiveTokens.add(requestToken);
        AsyncToken connectorToken = ((StoreLocatorConnector) ConnectorManager.getConnector(this.mConnectorImpl)).requestStoreWithId(key, new StoreLocatorConnectorAsyncListener(this, null, listener));
        this.mTokenMap.put(requestToken, connectorToken);
        this.mConnectorTokenMap.put(connectorToken, requestToken);
        return requestToken;
    }

    public AsyncToken getStoresForIds(List<String> keys, @NonNull final AsyncListener<List<Store>> listener) {
        final AsyncToken requestToken = new AsyncToken("getStoresForId");
        final List<Store> cacheHits = new ArrayList();
        List<String> remainingKeys = new ArrayList(keys);
        for (String key : keys) {
            if (this.mObjectCache.containsKey(key)) {
                cacheHits.add(this.mObjectCache.get(key));
                remainingKeys.remove(key);
            }
        }
        if (remainingKeys.size() > 0) {
            this.mActiveTokens.add(requestToken);
            AsyncToken connectorToken = ((StoreLocatorConnector) ConnectorManager.getConnector(this.mConnectorImpl)).requestStoresWithIds(remainingKeys, new StoreLocatorConnectorAsyncListener(this, cacheHits, listener));
            this.mTokenMap.put(requestToken, connectorToken);
            this.mConnectorTokenMap.put(connectorToken, requestToken);
        } else {
            this.mHandler.post(new Runnable() {
                public void run() {
                    listener.onResponse(cacheHits, requestToken, null);
                }
            });
        }
        return requestToken;
    }

    public AsyncToken getStoresNearCurrentLocationWithinRadius(Double radius, List<String> filters, @NonNull AsyncListener<List<Store>> listener) {
        final AsyncToken requestToken = new AsyncToken("getStoresNearCurrentLocationWithinRadius");
        this.mActiveTokens.add(requestToken);
        this.mLastKnownLocation = LocationServicesManager.getInstance().getCurrentUserLocation();
        Store currentStore = ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getCurrentStore();
        if (!(currentStore == null || LocationServicesManager.isLocationEnabled(this.mContext))) {
            this.mLastKnownLocation = new Location(currentStore.getName());
            this.mLastKnownLocation.setLatitude(currentStore.getLatitude());
            this.mLastKnownLocation.setLongitude(currentStore.getLongitude());
        }
        if (this.mLastKnownLocation != null) {
            commonStoresWithLocation(Double.valueOf(this.mLastKnownLocation.getLatitude()), Double.valueOf(this.mLastKnownLocation.getLongitude()), radius, filters, requestToken, listener);
        } else {
            final Handler handler = new Handler(Looper.getMainLooper());
            final AsyncListener<List<Store>> asyncListener = listener;
            TimerTask task = new TimerTask() {

                /* renamed from: com.mcdonalds.sdk.modules.storelocator.StoreLocatorModule$2$1 */
                class C40981 implements Runnable {
                    C40981() {
                    }

                    public void run() {
                        asyncListener.onResponse(null, requestToken, new AsyncException(StoreLocatorModule.this.mContext.getString(C3883R.string.error_no_location_try_address)));
                        StoreLocatorModule.this.mLocationTimer = null;
                    }
                }

                public void run() {
                    handler.post(new C40981());
                }
            };
            this.mLocationTimer = new Timer();
            this.mLocationTimer.schedule(task, DEFAULT_LOCATION_SEARCH_TIMEOUT);
            final Double d = radius;
            final List<String> list = filters;
            final AsyncListener<List<Store>> asyncListener2 = listener;
            LocationServicesManager.getInstance().requestSingleUpdate(new LocationUpdateListener() {
                public void onLocationChanged(Location location) {
                    StoreLocatorModule.this.mLastKnownLocation = location;
                    if (StoreLocatorModule.this.mActiveTokens.contains(requestToken) && StoreLocatorModule.this.mLocationTimer != null) {
                        StoreLocatorModule.this.commonStoresWithLocation(Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()), d, list, requestToken, asyncListener2);
                        StoreLocatorModule.this.mLocationTimer.cancel();
                        StoreLocatorModule.this.mLocationTimer = null;
                    }
                }
            });
        }
        return requestToken;
    }

    public AsyncToken getStoresNearLatLongWithinRadius(Double latitude, Double longitude, Double radius, List<String> filters, @NonNull AsyncListener<List<Store>> listener) {
        AsyncToken requestToken = new AsyncToken("getStoresNearCurrentLocationWithinRadius");
        this.mActiveTokens.add(requestToken);
        commonStoresWithLocation(latitude, longitude, radius, filters, requestToken, listener);
        return requestToken;
    }

    public AsyncToken getStoresNearAddressWithinRadius(String query, Double radius, List<String> filters, @NonNull AsyncListener<List<Store>> listener) {
        AsyncToken requestToken = new AsyncToken("getStoresNearAddressWithinRadius");
        this.mActiveTokens.add(requestToken);
        StoreLocatorConnectorQueryParameters parameters = new StoreLocatorConnectorQueryParameters();
        parameters.setMaxResults(75);
        parameters.setSearchString(query);
        parameters.setDistance(radius);
        parameters.setFilters(filters);
        AsyncToken connectorToken = ((StoreLocatorConnector) ConnectorManager.getConnector(this.mConnectorImpl)).requestStores(parameters, new StoreLocatorConnectorAsyncListener(this, listener));
        this.mTokenMap.put(requestToken, connectorToken);
        this.mConnectorTokenMap.put(connectorToken, requestToken);
        return requestToken;
    }

    public AsyncToken getStoresNearAddressWithinRadius(Address address, Double radius, List<String> list, @NonNull AsyncListener<List<Store>> asyncListener) {
        return null;
    }

    public AsyncToken getAvailableStoreFeatures(@NonNull final AsyncListener<List<String>> listener) {
        final AsyncToken requestToken = new AsyncToken("storeFeatures");
        this.mActiveTokens.add(requestToken);
        AsyncToken connectorToken = ((StoreLocatorConnector) ConnectorManager.getConnector(this.mConnectorImpl)).requestStoreFilters(new AsyncListener<List<String>>() {
            public void onResponse(List<String> response, AsyncToken token, AsyncException exception) {
                if (exception == null) {
                    listener.onResponse(response, requestToken, null);
                } else {
                    listener.onResponse(null, null, exception);
                }
                StoreLocatorModule.this.removeToken(requestToken);
            }
        });
        this.mTokenMap.put(requestToken, connectorToken);
        this.mConnectorTokenMap.put(connectorToken, requestToken);
        return requestToken;
    }

    public NativeMapsSDK getPreferredMapsSDK() {
        NativeMapsSDK ret = NativeMapsSDK.Google;
        if (this.mConnectorImpl.equals(AUTONAVI_CONNECTOR_VALUE)) {
            return !System.getProperty("os.arch").toLowerCase().contains("86") ? NativeMapsSDK.AutoNavi : NativeMapsSDK.AutoNavi2D;
        } else {
            return ret;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public Boolean isCancelled(Object token) {
        return Boolean.valueOf(!this.mActiveTokens.contains(token));
    }

    /* Access modifiers changed, original: 0000 */
    public void cacheStores(List<Store> stores) {
        if (stores != null && !stores.isEmpty()) {
            for (Store store : stores) {
                this.mObjectCache.put(Integer.toString(store.getStoreId()), store);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public AsyncToken moduleToken(Object connectorToken) {
        return (AsyncToken) this.mConnectorTokenMap.get(connectorToken);
    }

    private void commonStoresWithLocation(Double latitude, Double longitude, Double radius, List<String> filters, AsyncToken requestToken, @NonNull AsyncListener<List<Store>> listener) {
        final StoreLocatorConnectorQueryParameters parameters = new StoreLocatorConnectorQueryParameters();
        parameters.setMaxResults(75);
        parameters.setDistance(radius);
        Location mockLocation = ModuleManager.getMockLocation();
        if (mockLocation == null) {
            parameters.setLatitude(latitude);
            parameters.setLongitude(longitude);
        } else {
            parameters.setLatitude(Double.valueOf(mockLocation.getLatitude()));
            parameters.setLongitude(Double.valueOf(mockLocation.getLongitude()));
        }
        parameters.setFilters(filters);
        final AsyncListener<List<Store>> asyncListener;
        if (hasStoresRetrieved(parameters.getLatitude().doubleValue(), parameters.getLongitude().doubleValue(), filters)) {
            asyncListener = listener;
            final AsyncToken asyncToken = requestToken;
            this.mHandler.post(new Runnable() {
                public void run() {
                    asyncListener.onResponse(StoreLocatorModule.this.mLastRetrievedStores, asyncToken, null);
                }
            });
            return;
        }
        asyncListener = listener;
        AsyncToken connectorToken = ((StoreLocatorConnector) ConnectorManager.getConnector(this.mConnectorImpl)).requestStores(parameters, new StoreLocatorConnectorAsyncListener(this, new AsyncListener<List<Store>>() {
            public void onResponse(List<Store> response, AsyncToken token, AsyncException exception) {
                StoreLocatorModule.this.mLastRetrievedStores = response;
                StoreLocatorModule.this.mLastLatLngQueried = new LatLng(parameters.getLatitude().doubleValue(), parameters.getLongitude().doubleValue());
                StoreLocatorModule.this.mLastFiltersQueried = parameters.getFilters();
                asyncListener.onResponse(response, token, exception);
            }
        }));
        this.mTokenMap.put(requestToken, connectorToken);
        this.mConnectorTokenMap.put(connectorToken, requestToken);
    }

    public Location getLastKnownLocation() {
        return this.mLastKnownLocation;
    }

    private boolean hasStoresRetrieved(double latitude, double longitude, List<String> filters) {
        return !ListUtils.isEmpty(this.mLastRetrievedStores) && this.mLastLatLngQueried.latitude == latitude && this.mLastLatLngQueried.longitude == longitude && (this.mLastFiltersQueried == filters || (this.mLastFiltersQueried != null && this.mLastFiltersQueried.equals(filters)));
    }

    /* Access modifiers changed, original: 0000 */
    public void removeToken(Object requestToken) {
        if (this.mActiveTokens.contains(requestToken)) {
            this.mActiveTokens.remove(requestToken);
        }
        if (this.mTokenMap.containsKey(requestToken)) {
            AsyncToken connectorToken = (AsyncToken) this.mTokenMap.get(requestToken);
            if (this.mConnectorTokenMap.containsKey(connectorToken)) {
                this.mConnectorTokenMap.remove(connectorToken);
            }
            this.mTokenMap.remove(requestToken);
        }
    }

    public void onConnected(Bundle dataBundle) {
    }

    public void onConnectionSuspended(int i) {
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    public List<Facility> getAllFacilities() {
        return FacilityRepository.getAll(this.mContext);
    }

    public SparseArray<Facility> getFacilityMap() {
        return FacilityRepository.getFacilityMap(this.mContext);
    }
}
