package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.Category;
import java.io.Serializable;

public class MWNutritionCategory implements Serializable {
    @SerializedName("category_description")
    public String categoryDescription;
    @SerializedName("category_external_id")
    public int categoryExternalId;
    @SerializedName("category_id")
    public String categoryId;
    @SerializedName("category_marketing_name")
    public String categoryMarketingName;
    @SerializedName("category_name")
    public String categoryName;
    @SerializedName("category_type")
    public int categoryType;
    @SerializedName("display_order")
    public int displayOrder;
    @SerializedName("do_not_show")
    public String doNotShow;
    @SerializedName("is_test_category")
    public boolean isTestCategory;

    public Category toCategory() {
        Category category = new Category();
        category.setName(this.categoryName);
        category.setCategoryDescription(this.categoryDescription);
        category.setID(Integer.parseInt(this.categoryId));
        category.setDisplayOrder(this.displayOrder);
        category.setExternalId(this.categoryExternalId);
        category.setMarketingName(this.categoryMarketingName);
        category.setTestCategory(this.isTestCategory);
        category.setType(this.categoryType);
        category.setDoNotShow(this.doNotShow);
        return category;
    }
}
