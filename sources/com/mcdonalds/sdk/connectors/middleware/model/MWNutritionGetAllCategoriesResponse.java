package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class MWNutritionGetAllCategoriesResponse implements Serializable {
    @SerializedName("categories")
    public MWNutritionCategories categories;
    @SerializedName("error")
    public MWDefaultError error;
}
