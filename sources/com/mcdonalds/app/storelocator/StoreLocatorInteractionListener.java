package com.mcdonalds.app.storelocator;

import com.mcdonalds.app.storelocator.maps.model.LatLng;
import com.mcdonalds.sdk.AsyncListener;

public interface StoreLocatorInteractionListener {
    void addStoreToFavorites(Integer num, StoreLocatorSection storeLocatorSection, String str, AsyncListener<Boolean> asyncListener);

    void removeStoreFromFavorites(Integer num, StoreLocatorSection storeLocatorSection, AsyncListener<Boolean> asyncListener);

    void renameStoreInFavorites(Integer num, StoreLocatorSection storeLocatorSection, String str, AsyncListener<Boolean> asyncListener);

    void requestUpdateStoresByAddress(String str, boolean z);

    void requestUpdateStoresByCurrentLocation(boolean z);

    void requestUpdateStoresByScrolledLocation(LatLng latLng);

    void selectStore(Integer num, StoreLocatorSection storeLocatorSection);
}
