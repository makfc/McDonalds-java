package com.mcdonalds.sdk.modules.storelocator;

import java.util.List;

public class PODLocation {
    private Integer locationID;
    private List<Integer> paymentMethods;
    private Boolean saleTypeEatIn;
    private Boolean saleTypeOther;
    private Boolean saleTypeTakeOut;
    private List<OpeningHour> storeAreaOpeningHours;

    public void setLocationID(Integer locationID) {
        this.locationID = locationID;
    }

    public Integer getLocationID() {
        return this.locationID;
    }

    public void setPaymentMethods(List<Integer> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public List<Integer> getPaymentMethods() {
        return this.paymentMethods;
    }

    public void setSaleTypeEatIn(Boolean saleTypeEatIn) {
        this.saleTypeEatIn = saleTypeEatIn;
    }

    public Boolean getSaleTypeEatIn() {
        return this.saleTypeEatIn;
    }

    public void setSaleTypeOther(Boolean saleTypeOther) {
        this.saleTypeOther = saleTypeOther;
    }

    public Boolean getSaleTypeOther() {
        return this.saleTypeOther;
    }

    public void setSaleTypeTakeOut(Boolean saleTypeTakeOut) {
        this.saleTypeTakeOut = saleTypeTakeOut;
    }

    public Boolean getSaleTypeTakeOut() {
        return this.saleTypeTakeOut;
    }

    public void setStoreAreaOpeningHours(List<OpeningHour> storeAreaOpeningHours) {
        this.storeAreaOpeningHours = storeAreaOpeningHours;
    }

    public List<OpeningHour> getStoreAreaOpeningHours() {
        return this.storeAreaOpeningHours;
    }
}
