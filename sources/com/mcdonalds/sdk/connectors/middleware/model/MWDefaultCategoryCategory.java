package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.Category;
import java.io.Serializable;

public class MWDefaultCategoryCategory implements Serializable {
    @SerializedName("id")
    public String defaultCategoryId;
    @SerializedName("name")
    public String defaultCategoryName;

    public Category toCategory() {
        Category category = new Category();
        category.setName(this.defaultCategoryName);
        category.setID(Integer.parseInt(this.defaultCategoryId));
        return category;
    }
}
