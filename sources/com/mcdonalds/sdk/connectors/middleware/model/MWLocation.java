package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class MWLocation implements Serializable {
    @SerializedName("LocationID")
    private int locationID;
    @SerializedName("PaymentMethods")
    private List<Integer> paymentMethods;
    @SerializedName("SaleTypeEatIn")
    private boolean saleTypeEatIn;
    @SerializedName("SaleTypeOther")
    private boolean saleTypeOther;
    @SerializedName("SaleTypeTakeOut")
    private boolean saleTypeTakeOut;
    @SerializedName("StoreAreaOpeningHours")
    private List<MWOpeningHourItem> storeAreaOpeningHours;

    public int getLocationID() {
        return this.locationID;
    }

    public List<Integer> getPaymentMethods() {
        return this.paymentMethods;
    }

    public List<MWOpeningHourItem> getStoreAreaOpeningHours() {
        return this.storeAreaOpeningHours;
    }
}
