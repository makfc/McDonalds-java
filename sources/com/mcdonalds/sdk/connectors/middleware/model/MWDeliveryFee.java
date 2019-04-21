package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;

public class MWDeliveryFee {
    @SerializedName("Price")
    public String price;
    @SerializedName("ProductCode")
    public String productCode;
    @SerializedName("Tax")
    public String tax;
}
