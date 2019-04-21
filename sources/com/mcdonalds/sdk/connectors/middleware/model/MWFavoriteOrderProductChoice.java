package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;

public class MWFavoriteOrderProductChoice {
    @SerializedName("ChoiceSolution")
    public MWFavoriteOrderProductChoiceSolution choiceSolution;
    @SerializedName("ProductCode")
    public int productCode;
    @SerializedName("Quantity")
    public int quantity;
}
