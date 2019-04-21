package com.mcdonalds.sdk.connectors.middleware.response;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.connectors.middleware.model.MWRecipeForIdItem;
import java.io.Serializable;

public class MWGetRecipeForIdResponse implements Serializable {
    @SerializedName("item")
    private MWRecipeForIdItem mItem;

    public String toString() {
        return "MWGetRecipeForIdResponse{mItem=" + this.mItem + '}';
    }

    public MWRecipeForIdItem getItem() {
        return this.mItem;
    }

    public void setItem(MWRecipeForIdItem item) {
        this.mItem = item;
    }
}
