package com.mcdonalds.app.nutrition;

import com.ensighten.Ensighten;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CategoryImagesResponse {
    @SerializedName("categories")
    private List<CategoryImage> categories;

    public List<CategoryImage> getCategories() {
        Ensighten.evaluateEvent(this, "getCategories", null);
        return this.categories;
    }
}
