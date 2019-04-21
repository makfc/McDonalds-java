package com.mcdonalds.app.storelocator;

public interface PagerItemListener {
    void addToFavoritesClicked(Integer num, StoreLocatorSection storeLocatorSection);

    void eatHereClicked(Integer num, StoreLocatorSection storeLocatorSection);

    void infoClicked(Integer num, StoreLocatorSection storeLocatorSection);

    void myMcDonaldsClicked(Integer num, StoreLocatorSection storeLocatorSection);

    void nicknameClicked(Integer num, StoreLocatorSection storeLocatorSection);

    void placeOrderClicked(Integer num, StoreLocatorSection storeLocatorSection);

    void removeFromFavoritesClicked(Integer num, StoreLocatorSection storeLocatorSection);

    void saveToFavoritesClicked(Integer num, StoreLocatorSection storeLocatorSection);

    void skipClicked(Integer num, StoreLocatorSection storeLocatorSection);
}
