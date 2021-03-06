package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class MWCategoryItems implements Serializable {
    @SerializedName("item")
    public List<MWMenuItem> categoryItemList;

    public String toString() {
        return "MWCategoryItems{mCategoryItemList=" + this.categoryItemList + '}';
    }
}
