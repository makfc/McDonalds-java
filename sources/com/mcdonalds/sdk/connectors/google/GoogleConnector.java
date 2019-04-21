package com.mcdonalds.sdk.connectors.google;

import android.content.Context;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.iid.InstanceID;
import com.mcdonalds.sdk.AsyncCounter;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.C3883R;
import com.mcdonalds.sdk.connectors.BaseConnector;
import com.mcdonalds.sdk.connectors.NotificationConnector;
import com.mcdonalds.sdk.connectors.StoreLocatorConnector;
import com.mcdonalds.sdk.connectors.middleware.model.DCSProfile;
import com.mcdonalds.sdk.connectors.storelocator.StoreLocatorConnectorQueryParameters;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.models.CustomerAddress;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.network.RequestManager;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoogleConnector extends BaseConnector implements NotificationConnector, StoreLocatorConnector {
    private static final String CONFIG_INFO_MISSING_MSG = "Configuration missing data needed for Store Locator";
    static final String GCM_SENDER_ID = "connectors.Google.gcmSenderId";
    static final String GOOGLE = "connectors.Google.storeLocator";
    static final String GOOGLE_API_KEY = "connectors.Google.storeLocator.apiKey";
    static final String GOOGLE_API_METHOD = "connectors.Google.storeLocator.apiMethod";
    static final String GOOGLE_BASE_URL = "connectors.Google.storeLocator.baseURL";
    static final String GOOGLE_MAPS_ENGINE_ENDPOINT = "connectors.Google.storeLocator.endPoint";
    static final String GOOGLE_TABLES_ID = "connectors.Google.storeLocator.tables";
    public static final String NAME = "google";
    private Map<String, GoogleAPIFilter> mFiltersMap;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public GoogleConnector(Context context) {
        setContext(context);
        setConnection(RequestManager.register(getContext()));
        refreshFilters();
    }

    private void refreshFilters() {
        this.mFiltersMap = new HashMap(6);
        this.mFiltersMap.put(getContext().getString(C3883R.string.sl_feature_wifi), GoogleAPIFilter.WiFi);
        this.mFiltersMap.put(getContext().getString(C3883R.string.sl_feature_giftcards), GoogleAPIFilter.GiftCards);
        this.mFiltersMap.put(getContext().getString(C3883R.string.sl_feature_playland), GoogleAPIFilter.PlayLand);
        this.mFiltersMap.put(getContext().getString(C3883R.string.sl_feature_drivethru), GoogleAPIFilter.DriveThru);
        this.mFiltersMap.put(getContext().getString(C3883R.string.facility_mobile_offers), GoogleAPIFilter.MobileOffers);
        this.mFiltersMap.put(getContext().getString(C3883R.string.facility_mobile_ordering), GoogleAPIFilter.MobileOrdering);
    }

    public AsyncToken requestStores(final StoreLocatorConnectorQueryParameters parameters, final AsyncListener<List<Store>> requestListener) {
        final AsyncToken requestToken = new AsyncToken("requestStores");
        if (isInvalidConfig()) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    requestListener.onResponse(null, requestToken, new AsyncException(GoogleConnector.CONFIG_INFO_MISSING_MSG));
                }
            });
        } else if (parameters.getSearchString() == null) {
            getNetworkConnection().processRequest(new GoogleStoreLocatorRequestProvider(parameters, this.mFiltersMap), new GoogleStoreLocatorListener(getContext(), requestToken, requestListener));
        } else if (Geocoder.isPresent()) {
            Location mockLocation = ModuleManager.getMockLocation();
            if (mockLocation == null) {
                GeocodingAsyncTask asyncTask = new GeocodingAsyncTask();
                Object[] objArr = new Object[]{parameters.getSearchString(), getContext(), new GeocodingAsyncTaskListener() {
                    public void onResponse(Double latitude, Double longitude, String errorMsg) {
                        if (errorMsg == null) {
                            parameters.setSearchString(null);
                            parameters.setLatitude(latitude);
                            parameters.setLongitude(longitude);
                            GoogleConnector.this.requestStores(parameters, requestListener);
                            return;
                        }
                        requestListener.onResponse(null, requestToken, new AsyncException(errorMsg));
                    }
                }};
                if (asyncTask instanceof AsyncTask) {
                    AsyncTaskInstrumentation.execute(asyncTask, objArr);
                } else {
                    asyncTask.execute(objArr);
                }
            } else {
                parameters.setSearchString(null);
                parameters.setLatitude(Double.valueOf(mockLocation.getLatitude()));
                parameters.setLongitude(Double.valueOf(mockLocation.getLongitude()));
                requestStores(parameters, requestListener);
            }
        } else {
            this.mHandler.post(new Runnable() {
                public void run() {
                    requestListener.onResponse(null, requestToken, new AsyncException("Address lookup unavailable on this device"));
                }
            });
        }
        return requestToken;
    }

    public AsyncToken requestStoreWithId(String storeId, final AsyncListener<List<Store>> requestListener) {
        final AsyncToken requestToken = new AsyncToken("requestStores");
        if (isInvalidConfig()) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    requestListener.onResponse(null, requestToken, new AsyncException(GoogleConnector.CONFIG_INFO_MISSING_MSG));
                }
            });
        } else {
            StoreLocatorConnectorQueryParameters parameters = new StoreLocatorConnectorQueryParameters();
            parameters.setStoreIds(Collections.singletonList(storeId));
            getNetworkConnection().processRequest(new GoogleStoreLocatorRequestProvider(parameters, this.mFiltersMap), new GoogleStoreLocatorListener(getContext(), requestToken, requestListener));
        }
        return requestToken;
    }

    public AsyncToken requestStoresWithIds(List<String> storeIdList, AsyncListener<List<Store>> requestListener) {
        AsyncToken token = new AsyncToken(getClass().getSimpleName() + ".requestStoresWithIds");
        final AsyncCounter<Store> asyncCounter = new AsyncCounter(storeIdList.size(), requestListener);
        int size = storeIdList.size();
        for (int i = 0; i < size; i++) {
            requestStoreWithId((String) storeIdList.get(i), new AsyncListener<List<Store>>() {
                public void onResponse(List<Store> response, AsyncToken token, AsyncException exception) {
                    if (exception != null) {
                        asyncCounter.error(exception);
                    } else if (response.isEmpty()) {
                        asyncCounter.success(null);
                    } else {
                        asyncCounter.success(response.get(0));
                    }
                }
            });
        }
        return token;
    }

    public AsyncToken requestStoreFilters(final AsyncListener<List<String>> requestListener) {
        final AsyncToken token = new AsyncToken("GoogleConnector.requestFilters");
        this.mHandler.post(new Runnable() {
            public void run() {
                GoogleConnector.this.refreshFilters();
                requestListener.onResponse(Arrays.asList(GoogleConnector.this.mFiltersMap.keySet().toArray()), token, null);
            }
        });
        return token;
    }

    public AsyncToken getDeliveryStore(CustomerAddress address, String username, Date deliveryTime, AsyncListener<Store> asyncListener) {
        throw new AsyncException("getDeliveryStore not supported by Google Connector");
    }

    public AsyncToken getDeliveryStore(CustomerAddress address, String username, Date deliveryTime, boolean isNormalOrder, AsyncListener<Store> asyncListener) {
        throw new AsyncException("getDeliveryStore not supported by Google Connector");
    }

    public static Store googleStoreToStore(GoogleStore googleStore, Context resourceContext) {
        Store store = new Store();
        GoogleStoreProperties props = googleStore.getProperties();
        store.setStoreId(Integer.parseInt(props.getNatlStrNumber()));
        store.setLatitude(googleStore.getGeometry().getLatitude().doubleValue());
        store.setLongitude(googleStore.getGeometry().getLongitude().doubleValue());
        store.setDistance(props.getDistance());
        store.setAddress1(props.getAddressLine());
        store.setCity(props.getCity());
        store.setState(props.getSubdivision());
        store.setPostalCode(props.getPostalCode());
        store.setCountry(props.getCountryRegion());
        store.setStoreType(props.getStoreType());
        store.setStoreURL(props.getStoreURL());
        store.setPhoneNumber(props.getPhoneNumber());
        List<String> facilityNames = new ArrayList();
        if (isTrueValue(props.getWifi())) {
            facilityNames.add(resourceContext.getString(C3883R.string.sl_feature_wifi));
        }
        if (isTrueValue(props.getPlayLand())) {
            facilityNames.add(resourceContext.getString(C3883R.string.sl_feature_playland));
        }
        if (isTrueValue(props.getDriveThru())) {
            facilityNames.add(resourceContext.getString(C3883R.string.sl_feature_drivethru));
        }
        if (isTrueValue(props.getGiftCards())) {
            facilityNames.add(resourceContext.getString(C3883R.string.sl_feature_giftcards));
        }
        if (isTrueValue(props.getMobileOffers())) {
            facilityNames.add(resourceContext.getString(C3883R.string.facility_mobile_offers));
            store.setHasMobileOffers(Boolean.valueOf(true));
        }
        if (isTrueValue(props.getMobileOrdering())) {
            facilityNames.add(resourceContext.getString(C3883R.string.facility_mobile_ordering));
            store.setHasMobileOrdering(Boolean.valueOf(true));
        }
        store.setFacilityNames(facilityNames);
        return store;
    }

    private static boolean isTrueValue(String value) {
        return DCSProfile.INDICATOR_TRUE.equals(value);
    }

    private boolean isInvalidConfig() {
        Configuration config = Configuration.getSharedInstance();
        if (config.getValueForKey(GOOGLE) == null || config.getValueForKey(GOOGLE_BASE_URL) == null || config.getValueForKey(GOOGLE_MAPS_ENGINE_ENDPOINT) == null || config.getValueForKey(GOOGLE_TABLES_ID) == null || config.getValueForKey(GOOGLE_API_METHOD) == null || config.getValueForKey(GOOGLE_API_KEY) == null) {
            return true;
        }
        return false;
    }

    public String register() {
        try {
            return InstanceID.getInstance(getContext()).getToken(Configuration.getSharedInstance().getStringForKey(GCM_SENDER_ID), "GCM", null);
        } catch (IOException e) {
            return "";
        }
    }

    public void unregister() {
        try {
            InstanceID.getInstance(getContext()).deleteToken(Configuration.getSharedInstance().getStringForKey(GCM_SENDER_ID), "GCM");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
