package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MWCustomerFavoriteOrderProductResponse {
    @SerializedName("Choices")
    public List<MWFavoriteOrderProductChoice> choices;
    @SerializedName("Customizations")
    public List customizations;
    @SerializedName("ProductCode")
    public int productCode;
    @SerializedName("PromoQuantity")
    public int promoQuantity;
    @SerializedName("Quantity")
    public int quantity;
}
