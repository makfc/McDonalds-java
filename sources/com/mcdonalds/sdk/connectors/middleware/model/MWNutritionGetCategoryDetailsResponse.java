package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class MWNutritionGetCategoryDetailsResponse implements Serializable {
    @SerializedName("category")
    public MWCategoryDetailsCategory categoryDetailsCategory;

    public MWCategoryDetailsCategory getCategoryDetailsCategory() {
        return this.categoryDetailsCategory;
    }

    public void setCategoryDetailsCategory(MWCategoryDetailsCategory categoryDetailsCategory) {
        this.categoryDetailsCategory = categoryDetailsCategory;
    }
}
