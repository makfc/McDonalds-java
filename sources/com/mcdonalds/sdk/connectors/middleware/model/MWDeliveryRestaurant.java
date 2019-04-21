package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.DeliveryStore;

@Deprecated
public class MWDeliveryRestaurant extends MWRestaurant {
    @SerializedName("NPVersion")
    public String NPVersion;
    @SerializedName("TODCutoffTime")
    public String TODCutoffTime;
    @SerializedName("DayPart")
    public int dayPart;
    @SerializedName("ExpectedDeliveryTime")
    public String expectedDeliveryTime;
    @SerializedName("ExternalStoreNumber")
    public String externalStoreNumber;
    @SerializedName("LargeOrderAllowed")
    public boolean largeOrderAllowed;
    @SerializedName("MinimumOrderValue")
    public double minimumOrderValue;
    @SerializedName("StoreCutoffTime")
    public String storeCutoffTime;
    @SerializedName("StoreStatus")
    public int storeStatus;

    @Deprecated
    public static DeliveryStore toDeliveryStore(MWDeliveryRestaurant store) {
        return (DeliveryStore) store.toStore(null);
    }
}
