package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class MWNutrientFacts implements Serializable {
    @SerializedName("nutrient")
    public List<MWNutrient> nutrientList;
}
