package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class MWCategoryDetailsCategory implements Serializable {
    @SerializedName("category_description")
    public String categoryDescription;
    @SerializedName("category_external_id")
    public int categoryExternalId;
    @SerializedName("category_id")
    public String categoryId;
    @SerializedName("items")
    public MWCategoryItems categoryItems;
    @SerializedName("category_marketing_name")
    public String categoryMarketingName;
    @SerializedName("category_name")
    public String categoryName;
    @SerializedName("category_type")
    public int categoryType;
    @SerializedName("id")
    /* renamed from: id */
    public String f6066id;
    @SerializedName("is_test_category")
    public boolean testCategory;
}
