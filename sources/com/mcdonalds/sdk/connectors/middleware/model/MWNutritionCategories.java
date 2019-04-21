package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class MWNutritionCategories implements Serializable {
    @SerializedName("category")
    public List<MWNutritionCategory> categoryList;
}
