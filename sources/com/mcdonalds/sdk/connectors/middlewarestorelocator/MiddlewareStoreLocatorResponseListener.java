package com.mcdonalds.sdk.connectors.middlewarestorelocator;

import android.location.Location;
import com.google.android.gms.maps.model.LatLng;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.middlewarestorelocator.model.MiddlewareStoreLocatorLocation;
import com.mcdonalds.sdk.connectors.middlewarestorelocator.model.MiddlewareStoreLocatorStore;
import com.mcdonalds.sdk.modules.storelocator.Store;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

public class MiddlewareStoreLocatorResponseListener implements AsyncListener<MiddlewareStoreLocatorStore[]> {
    private LatLng mOriginalLatLng;
    private AsyncListener<List<Store>> mStoresListener;
    private AsyncToken mToken;

    public MiddlewareStoreLocatorResponseListener(AsyncToken token, AsyncListener<List<Store>> storesListener) {
        this.mStoresListener = storesListener;
        this.mToken = token;
    }

    public void onResponse(MiddlewareStoreLocatorStore[] response, AsyncToken token, AsyncException exception) {
        if (exception != null) {
            this.mStoresListener.onResponse(null, this.mToken, exception);
            return;
        }
        List<Store> stores = new ArrayList();
        if (response != null) {
            if (this.mOriginalLatLng != null) {
                stores.addAll(getStoresSortedByDistance(response));
            } else {
                for (MiddlewareStoreLocatorStore store : response) {
                    stores.add(store.toStore());
                }
            }
        }
        this.mStoresListener.onResponse(stores, this.mToken, null);
    }

    private Collection<Store> getStoresSortedByDistance(MiddlewareStoreLocatorStore[] response) {
        TreeMap<Float, Store> storeMap = new TreeMap();
        Location original = new Location("");
        original.setLatitude(this.mOriginalLatLng.latitude);
        original.setLongitude(this.mOriginalLatLng.longitude);
        for (MiddlewareStoreLocatorStore mwStore : response) {
            MiddlewareStoreLocatorLocation location = mwStore.address.location;
            Location newLocation = new Location("");
            newLocation.setLatitude(location.latitude);
            newLocation.setLongitude(location.longitude);
            float distance = original.distanceTo(newLocation);
            Store store = mwStore.toStore();
            store.setDistance((double) distance);
            storeMap.put(Float.valueOf(distance), store);
        }
        return storeMap.values();
    }

    public void setOriginalLatLng(LatLng latLng) {
        this.mOriginalLatLng = latLng;
    }
}
