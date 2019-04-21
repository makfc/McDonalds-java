package com.mcdonalds.sdk.connectors.middlewarestorelocator;

import com.amap.api.services.district.DistrictSearchQuery;

public enum MiddlewareStoreLocatorLocationParam {
    LATITUDE("latitude"),
    LONGITUDE("longitude"),
    DISTANCE("distance"),
    CITY_TOWN("cityTown"),
    STATE_PROVINCE("stateProvince"),
    REGION("region"),
    ZIP_POSTAL("zipCode"),
    AREA("area"),
    COUNTY("county"),
    BRANCH("branch"),
    DISTRICT(DistrictSearchQuery.KEYWORDS_DISTRICT),
    SUBDIVISION("subdivision"),
    CROSS_STREETS("crossStreets");
    
    private String text;

    private MiddlewareStoreLocatorLocationParam(String text) {
        this.text = text;
    }

    public String toString() {
        return this.text;
    }
}
