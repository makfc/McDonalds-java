package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;

public class MWOfferAction {
    @SerializedName("DiscountType")
    public int discountType;
    @SerializedName("PriceFromCode")
    public String priceFromCode;
    @SerializedName("Type")
    public int type;
    @SerializedName("Value")
    public double value;
}
