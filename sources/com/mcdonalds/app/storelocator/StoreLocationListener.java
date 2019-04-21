package com.mcdonalds.app.storelocator;

public interface StoreLocationListener {
    void clearZoomFlag();

    void onChange(StoreLocatorDataProvider storeLocatorDataProvider);

    void onCurrentStoreChange(StoreLocatorDataProvider storeLocatorDataProvider, String str);

    void onSelectedStoreChange(StoreLocatorDataProvider storeLocatorDataProvider, String str, StoreLocatorSection storeLocatorSection, boolean z);

    void refreshSelectedStore();
}
