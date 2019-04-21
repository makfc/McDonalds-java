package com.amap.api.services.geocoder;

public class RegeocodeResult {
    /* renamed from: a */
    private RegeocodeQuery f3887a;
    /* renamed from: b */
    private RegeocodeAddress f3888b;

    public RegeocodeResult(RegeocodeQuery regeocodeQuery, RegeocodeAddress regeocodeAddress) {
        this.f3887a = regeocodeQuery;
        this.f3888b = regeocodeAddress;
    }

    public RegeocodeQuery getRegeocodeQuery() {
        return this.f3887a;
    }

    public void setRegeocodeQuery(RegeocodeQuery regeocodeQuery) {
        this.f3887a = regeocodeQuery;
    }

    public RegeocodeAddress getRegeocodeAddress() {
        return this.f3888b;
    }

    public void setRegeocodeAddress(RegeocodeAddress regeocodeAddress) {
        this.f3888b = regeocodeAddress;
    }
}
