package com.mcdonalds.sdk.connectors.middlewarestorelocator;

public enum MiddlewareStoreLocatorSearchParam {
    Location("locationCriteria"),
    Attribute("storeAttributes"),
    PageSize("pageSize"),
    StoreIdType("storeUniqueIdType"),
    StoreId("storeUniqueId"),
    Market("market"),
    Locale("local"),
    Query("query");
    
    private String text;

    private MiddlewareStoreLocatorSearchParam(String text) {
        this.text = text;
    }

    public String toString() {
        return this.text;
    }
}
