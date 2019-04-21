package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class MWRecipePrice implements Serializable {
    @SerializedName("IsValid")
    public boolean isValid;
    @SerializedName("Price")
    public double price;
    @SerializedName("PriceTypeID")
    public int priceTypeID;
    @SerializedName("RecipeID")
    public int recipeID;
}
