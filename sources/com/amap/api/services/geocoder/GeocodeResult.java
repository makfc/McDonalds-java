package com.amap.api.services.geocoder;

import java.util.ArrayList;
import java.util.List;

public class GeocodeResult {
    /* renamed from: a */
    private GeocodeQuery f3861a;
    /* renamed from: b */
    private List<GeocodeAddress> f3862b = new ArrayList();

    public GeocodeResult(GeocodeQuery geocodeQuery, List<GeocodeAddress> list) {
        this.f3861a = geocodeQuery;
        this.f3862b = list;
    }

    public GeocodeQuery getGeocodeQuery() {
        return this.f3861a;
    }

    public void setGeocodeQuery(GeocodeQuery geocodeQuery) {
        this.f3861a = geocodeQuery;
    }

    public List<GeocodeAddress> getGeocodeAddressList() {
        return this.f3862b;
    }

    public void setGeocodeAddressList(List<GeocodeAddress> list) {
        this.f3862b = list;
    }
}
