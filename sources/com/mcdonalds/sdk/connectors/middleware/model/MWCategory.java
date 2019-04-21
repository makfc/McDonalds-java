package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.Category;
import java.io.Serializable;

public class MWCategory implements Serializable {
    @SerializedName("DisplayCategoryID")
    public int displayCategoryID;
    @SerializedName("DisplayOrder")
    public int displayOrder;
    @SerializedName("DisplaySizeSelection")
    public int displaySizeSelection;

    public Category toCategory() {
        Category category = new Category();
        category.setID(this.displayCategoryID);
        category.setDisplayOrder(this.displayOrder);
        category.setDisplaySizeSelection(this.displaySizeSelection);
        return category;
    }
}
