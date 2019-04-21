package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class MWMenuItemRelatedItems implements Serializable {
    @SerializedName("related_item")
    public List<MWMenuItemRelatedItem> menuItemRelatedItemList;
}
