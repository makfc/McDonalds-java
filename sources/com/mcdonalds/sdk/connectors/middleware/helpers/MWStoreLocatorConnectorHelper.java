package com.mcdonalds.sdk.connectors.middleware.helpers;

import android.os.Handler;
import android.os.Looper;
import com.mcdonalds.sdk.AsyncCounter;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.StoreLocatorConnector;
import com.mcdonalds.sdk.connectors.middleware.MWException;
import com.mcdonalds.sdk.connectors.middleware.model.MWAddressElement;
import com.mcdonalds.sdk.connectors.middleware.model.MWRestaurant;
import com.mcdonalds.sdk.connectors.middleware.request.MWGetDeliveryStoreByAddressRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWGetStoreInformationRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWGetStoresByLocationRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWSearchStoresRequest;
import com.mcdonalds.sdk.connectors.middleware.response.MWGetDeliveryStoreByAddressResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWGetStoreInformationResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWGetStoresByLocationResponse;
import com.mcdonalds.sdk.connectors.storelocator.StoreLocatorConnectorQueryParameters;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.models.AddressElement;
import com.mcdonalds.sdk.modules.models.CustomerAddress;
import com.mcdonalds.sdk.modules.models.Facility;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.modules.storelocator.StoreLocatorModule;
import com.mcdonalds.sdk.services.network.SessionManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MWStoreLocatorConnectorHelper implements StoreLocatorConnector {
    private Map<String, Integer> mFiltersMap = new HashMap();
    private Handler mHandler;
    private MWConnectorShared mSharedData;

    public MWStoreLocatorConnectorHelper(MWConnectorShared sharedData) {
        this.mSharedData = sharedData;
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public AsyncToken requestStores(StoreLocatorConnectorQueryParameters parameters, final AsyncListener<List<Store>> requestListener) {
        final AsyncToken requestToken = new AsyncToken("MWConnector.requestStores");
        AsyncListener<MWGetStoresByLocationResponse> listener = new AsyncListener<MWGetStoresByLocationResponse>() {
            public void onResponse(MWGetStoresByLocationResponse response, AsyncToken token, AsyncException exception) {
                List<Store> stores = null;
                AsyncException localException = exception;
                if (!(response == null || response.getResultCode() == 1)) {
                    localException = MWException.fromErrorCode(response.getResultCode());
                }
                if (localException == null) {
                    stores = new ArrayList();
                    if (!(response == null || response.getData() == null)) {
                        List<MWRestaurant> restaurants = (List) response.getData();
                        int size = restaurants.size();
                        for (int i = 0; i < size; i++) {
                            stores.add(((MWRestaurant) restaurants.get(i)).toStore(MWStoreLocatorConnectorHelper.this.mSharedData.getContext()));
                        }
                    }
                }
                requestListener.onResponse(stores, requestToken, localException);
            }
        };
        if (parameters.getSearchString() != null) {
            this.mSharedData.getNetworkConnection().processRequest(new MWSearchStoresRequest(SessionManager.getInstance().getToken(), parameters.getSearchString(), Integer.valueOf(75), getFacilityIDs(parameters.getFilters())), listener);
        } else {
            this.mSharedData.getNetworkConnection().processRequest(new MWGetStoresByLocationRequest(SessionManager.getInstance().getToken(), parameters.getLatitude(), parameters.getLongitude(), Integer.valueOf(75), getFacilityIDs(parameters.getFilters())), listener);
        }
        return requestToken;
    }

    public AsyncToken requestStoreWithId(String storeId, final AsyncListener<List<Store>> requestListener) {
        final AsyncToken requestToken = new AsyncToken("requestStoreWithId");
        final Store cachedStore = (Store) this.mSharedData.getStoreCache().get(Integer.parseInt(storeId));
        if (cachedStore == null) {
            this.mSharedData.getNetworkConnection().processRequest(new MWGetStoreInformationRequest(SessionManager.getInstance().getToken(), Integer.valueOf(storeId)), new AsyncListener<MWGetStoreInformationResponse>() {
                public void onResponse(MWGetStoreInformationResponse response, AsyncToken token, AsyncException exception) {
                    List<Store> stores = null;
                    AsyncException localException = exception;
                    if (!(response == null || response.getResultCode() == 1)) {
                        localException = MWException.fromErrorCode(response.getResultCode());
                    }
                    if (localException == null) {
                        stores = new ArrayList();
                        if (!(response == null || response.getData() == null)) {
                            stores.add(((MWRestaurant) response.getData()).toStore(MWStoreLocatorConnectorHelper.this.mSharedData.getContext()));
                        }
                    }
                    requestListener.onResponse(stores, requestToken, localException);
                }
            });
        } else {
            this.mHandler.post(new Runnable() {
                public void run() {
                    requestListener.onResponse(Collections.singletonList(cachedStore), requestToken, null);
                }
            });
        }
        return requestToken;
    }

    public AsyncToken requestStoresWithIds(List<String> storeIdList, final AsyncListener<List<Store>> requestListener) {
        int i;
        final AsyncToken requestToken = new AsyncToken("requestStoreWithIds");
        if (storeIdList == null) {
            i = 0;
        } else {
            i = storeIdList.size();
        }
        final AsyncCounter<Store> counter = new AsyncCounter(i, new AsyncListener<List<Store>>() {
            public void onResponse(List<Store> response, AsyncToken token, AsyncException exception) {
                List<Store> ret = new ArrayList();
                if (response != null) {
                    int size = response.size();
                    for (int i = 0; i < size; i++) {
                        Store store = (Store) response.get(i);
                        if (store != null) {
                            ret.add(store);
                        }
                    }
                }
                requestListener.onResponse(ret, requestToken, exception);
            }
        });
        if (storeIdList != null) {
            int size = storeIdList.size();
            for (int i2 = 0; i2 < size; i2++) {
                requestStoreWithId((String) storeIdList.get(i2), new AsyncListener<List<Store>>() {
                    public void onResponse(List<Store> response, AsyncToken token, AsyncException exception) {
                        if (exception == null && response.size() == 1) {
                            counter.success(response.get(0));
                        } else {
                            counter.success(null);
                        }
                    }
                });
            }
        }
        return requestToken;
    }

    public AsyncToken requestStoreFilters(AsyncListener<List<String>> requestListener) {
        List<String> facilityNames = new ArrayList();
        List<Facility> facilities = ((StoreLocatorModule) ModuleManager.getModule(StoreLocatorModule.NAME)).getAllFacilities();
        if (facilities != null) {
            for (Facility facility : facilities) {
                this.mFiltersMap.put(facility.getName(), Integer.valueOf(facility.getID()));
                facilityNames.add(facility.getName());
            }
        }
        requestListener.onResponse(facilityNames, null, null);
        return null;
    }

    public AsyncToken getDeliveryStore(CustomerAddress address, String username, Date deliveryTime, AsyncListener<Store> listener) {
        return getDeliveryStore(address, username, deliveryTime, false, listener);
    }

    public AsyncToken getDeliveryStore(CustomerAddress address, String username, Date deliveryTime, boolean isNormalOrder, final AsyncListener<Store> listener) {
        final AsyncToken requestToken = new AsyncToken("MWConnector.getDeliveryStore");
        List<MWAddressElement> ecpAddressElements = new ArrayList();
        if (address.getAddressElements() != null) {
            List<AddressElement> elements = address.getAddressElements();
            int size = elements.size();
            for (int i = 0; i < size; i++) {
                ecpAddressElements.add(MWAddressElement.fromAddressElement((AddressElement) elements.get(i)));
            }
        }
        this.mSharedData.getNetworkConnection().processRequest(new MWGetDeliveryStoreByAddressRequest(SessionManager.getInstance().getToken(), username, deliveryTime, ecpAddressElements), new AsyncListener<MWGetDeliveryStoreByAddressResponse>() {
            public void onResponse(MWGetDeliveryStoreByAddressResponse response, AsyncToken token, AsyncException exception) {
                AsyncException localException = exception;
                if (localException == null && response.getResultCode() != 1) {
                    localException = MWException.fromErrorCode(response.getResultCode());
                }
                if (localException == null) {
                    listener.onResponse(((MWRestaurant) response.getData()).toStore(MWStoreLocatorConnectorHelper.this.mSharedData.getContext()), requestToken, null);
                } else {
                    listener.onResponse(null, requestToken, localException);
                }
            }
        });
        return requestToken;
    }

    private List<Integer> getFacilityIDs(List<String> facilityNames) {
        List<Integer> ret = new ArrayList();
        if (facilityNames != null) {
            int size = facilityNames.size();
            for (int i = 0; i < size; i++) {
                ret.add(this.mFiltersMap.get((String) facilityNames.get(i)));
            }
        }
        return ret;
    }
}
