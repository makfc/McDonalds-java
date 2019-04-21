package com.mcdonalds.app.nutrition;

import com.ensighten.Ensighten;
import com.google.gson.annotations.SerializedName;

public class CategoryImage {
    @SerializedName("category")
    private int category;
    @SerializedName("url")
    private String url;

    public String getUrl() {
        Ensighten.evaluateEvent(this, "getUrl", null);
        return this.url;
    }

    public int getCategory() {
        Ensighten.evaluateEvent(this, "getCategory", null);
        return this.category;
    }
}
