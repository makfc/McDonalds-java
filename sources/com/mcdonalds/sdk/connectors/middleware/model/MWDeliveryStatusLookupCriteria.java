package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;

public class MWDeliveryStatusLookupCriteria {
    @SerializedName("OrderNumber")
    public String orderNumber;
    @SerializedName("UserName")
    public String username;
}
