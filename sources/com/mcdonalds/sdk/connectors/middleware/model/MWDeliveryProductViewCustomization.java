package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class MWDeliveryProductViewCustomization {
    @SerializedName("Comments")
    public List<Object> Comments = new ArrayList();
    @SerializedName("Extras")
    public List<Object> Extras = new ArrayList();
    @SerializedName("Ingredients")
    public List<Object> Ingredients = new ArrayList();
}
