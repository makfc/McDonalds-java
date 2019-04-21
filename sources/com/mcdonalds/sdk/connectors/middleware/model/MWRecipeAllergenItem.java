package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MWRecipeAllergenItem {
    @SerializedName("allergen")
    public List<MWAllergen> allergenList;
}
