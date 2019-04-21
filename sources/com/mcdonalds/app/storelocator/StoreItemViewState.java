package com.mcdonalds.app.storelocator;

import com.ensighten.Ensighten;

public enum StoreItemViewState {
    Normal,
    Favorite,
    Offer,
    Current,
    CurrentAndFavorite,
    ExpandedPlaceOrder,
    ExpandedFavoritePlaceOrder,
    ExpandedNickname,
    ExpandedSelectCurrent;

    public boolean isExpandedState() {
        Ensighten.evaluateEvent(this, "isExpandedState", null);
        return this == ExpandedPlaceOrder || this == ExpandedFavoritePlaceOrder || this == ExpandedNickname || this == ExpandedSelectCurrent;
    }
}
