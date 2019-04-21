package com.mcdonalds.app.model;

import com.ensighten.Ensighten;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;

public class Promo implements Serializable {
    @SerializedName("associatedRecipes")
    private List<String> mAssociatedRecipes;
    @SerializedName("categoryId")
    private Integer mCategoryId;
    @SerializedName("itemLink")
    private String mItemLink;
    @SerializedName("offerId")
    private String mOfferId;
    @SerializedName("url")
    private String mUrl;

    public Promo(String url, List<String> associatedRecipes, String itemLink) {
        this(url, associatedRecipes, itemLink, null);
    }

    public Promo(String url, List<String> associatedRecipes, String itemLink, String offerId) {
        this.mUrl = url;
        this.mAssociatedRecipes = associatedRecipes;
        this.mItemLink = itemLink;
        this.mOfferId = offerId;
    }

    public String getUrl() {
        Ensighten.evaluateEvent(this, "getUrl", null);
        return this.mUrl;
    }

    public List<String> getAssociatedRecipes() {
        Ensighten.evaluateEvent(this, "getAssociatedRecipes", null);
        return this.mAssociatedRecipes;
    }

    public String getItemLink() {
        Ensighten.evaluateEvent(this, "getItemLink", null);
        return this.mItemLink;
    }

    public Integer getCategoryId() {
        Ensighten.evaluateEvent(this, "getCategoryId", null);
        return this.mCategoryId;
    }

    public String getOfferId() {
        Ensighten.evaluateEvent(this, "getOfferId", null);
        return this.mOfferId;
    }

    public String toString() {
        boolean z = false;
        Ensighten.evaluateEvent(this, "toString", null);
        String str = "[Promo url = {0} recipes = {1}, item = {2} category = {3}]";
        Object[] objArr = new Object[4];
        objArr[0] = this.mUrl;
        if (this.mAssociatedRecipes != null) {
            z = true;
        }
        objArr[1] = Boolean.valueOf(z);
        objArr[2] = this.mItemLink;
        objArr[3] = this.mCategoryId;
        return MessageFormat.format(str, objArr);
    }
}
