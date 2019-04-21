package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MWPromotionProductSet {
    @SerializedName("Alias")
    public String alias;
    @SerializedName("Products")
    public List<MWProductView> products;
    @SerializedName("Quantity")
    public int quantity;
}
