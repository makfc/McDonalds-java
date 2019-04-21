package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class MWPrice implements Serializable {
    public static final int TYPE_DELIVERY = 3;
    public static final int TYPE_EAT_IN = 1;
    public static final int TYPE_TAKE_OUT = 2;
    @SerializedName("IsValid")
    public Boolean isValid;
    @SerializedName("Price")
    public Double price;
    @SerializedName("PriceTypeID")
    public Integer priceTypeID;
}
