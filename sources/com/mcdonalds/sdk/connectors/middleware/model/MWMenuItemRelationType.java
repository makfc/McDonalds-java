package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class MWMenuItemRelationType implements Serializable {
    @SerializedName("related_items")
    public MWMenuItemRelatedItems menuItemRelatedItems;
    @SerializedName("type")
    public String type;
}
