package com.mcdonalds.app.storelocator;

import android.location.Location;
import com.mcdonalds.sdk.modules.storelocator.Store;
import java.util.List;

public interface StoreLocatorDataProvider {

    public enum OfferState {
        VALID_OFFER,
        INVALID_OFFER,
        INVALID_PUNCHCARD_OFFER,
        NO_OFFER
    }

    boolean allowsFavoritingWithoutMobileOrdering();

    boolean allowsSelectionWithoutMobileOrdering();

    Store getCurrentStore();

    List<Store> getFavoriteStores();

    Location getLastLocation();

    int getMapPinResID(Integer num);

    List<Store> getNearByStores();

    OfferState getOfferState(Integer num, StoreLocatorSection storeLocatorSection);

    int getSelectMapPinResID(Integer num);

    Store getSelectedStore();

    Integer getSelectedStoreId();

    String getSelectedStoreNickName();

    StoreLocatorSection getSelectedStoreSection();

    boolean isCurrentStoreSelectionMode();

    boolean locationServicesEnabled();

    StoreItemViewState stateForStore(Integer num, StoreLocatorSection storeLocatorSection);
}
