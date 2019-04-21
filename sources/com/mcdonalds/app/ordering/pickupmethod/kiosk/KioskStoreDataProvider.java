package com.mcdonalds.app.ordering.pickupmethod.kiosk;

import android.location.Location;
import com.ensighten.Ensighten;
import com.mcdonalds.app.storelocator.StoreItemViewState;
import com.mcdonalds.app.storelocator.StoreLocatorDataProvider;
import com.mcdonalds.app.storelocator.StoreLocatorDataProvider.OfferState;
import com.mcdonalds.app.storelocator.StoreLocatorSection;
import com.mcdonalds.sdk.modules.storelocator.Store;
import java.util.List;

public class KioskStoreDataProvider implements StoreLocatorDataProvider {
    public Store getCurrentStore() {
        Ensighten.evaluateEvent(this, "getCurrentStore", null);
        return null;
    }

    public Store getSelectedStore() {
        Ensighten.evaluateEvent(this, "getSelectedStore", null);
        return null;
    }

    public List<Store> getFavoriteStores() {
        Ensighten.evaluateEvent(this, "getFavoriteStores", null);
        return null;
    }

    public List<Store> getNearByStores() {
        Ensighten.evaluateEvent(this, "getNearByStores", null);
        return null;
    }

    public Integer getSelectedStoreId() {
        Ensighten.evaluateEvent(this, "getSelectedStoreId", null);
        return null;
    }

    public StoreLocatorSection getSelectedStoreSection() {
        Ensighten.evaluateEvent(this, "getSelectedStoreSection", null);
        return null;
    }

    public String getSelectedStoreNickName() {
        Ensighten.evaluateEvent(this, "getSelectedStoreNickName", null);
        return null;
    }

    public StoreItemViewState stateForStore(Integer storeId, StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "stateForStore", new Object[]{storeId, section});
        return null;
    }

    public boolean locationServicesEnabled() {
        Ensighten.evaluateEvent(this, "locationServicesEnabled", null);
        return false;
    }

    public Location getLastLocation() {
        Ensighten.evaluateEvent(this, "getLastLocation", null);
        return null;
    }

    public boolean isCurrentStoreSelectionMode() {
        Ensighten.evaluateEvent(this, "isCurrentStoreSelectionMode", null);
        return false;
    }

    public boolean allowsSelectionWithoutMobileOrdering() {
        Ensighten.evaluateEvent(this, "allowsSelectionWithoutMobileOrdering", null);
        return false;
    }

    public boolean allowsFavoritingWithoutMobileOrdering() {
        Ensighten.evaluateEvent(this, "allowsFavoritingWithoutMobileOrdering", null);
        return false;
    }

    public int getSelectMapPinResID(Integer storeId) {
        Ensighten.evaluateEvent(this, "getSelectMapPinResID", new Object[]{storeId});
        return 0;
    }

    public int getMapPinResID(Integer storeId) {
        Ensighten.evaluateEvent(this, "getMapPinResID", new Object[]{storeId});
        return 0;
    }

    public OfferState getOfferState(Integer storeId, StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "getOfferState", new Object[]{storeId, section});
        return null;
    }
}
