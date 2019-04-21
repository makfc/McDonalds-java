package com.mcdonalds.sdk.modules.storelocator;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public enum StoreFeatureFilter {
    PlayLand,
    DriveThru,
    WiFi,
    GiftCards;

    public static List<StoreFeatureFilter> allFilters() {
        List<StoreFeatureFilter> filters = new ArrayList();
        filters.add(PlayLand);
        filters.add(DriveThru);
        filters.add(WiFi);
        filters.add(GiftCards);
        return filters;
    }
}
