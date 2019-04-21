package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;

public class MWCustomerFavoriteOrderProduct {
    @SerializedName("Choices")
    public List<MWFavoriteOrderProductChoice> choices;
    @SerializedName("Customizations")
    public Map customizations;
    @SerializedName("ProductCode")
    public int productCode;
    @SerializedName("Quantity")
    public int quantity;
}
