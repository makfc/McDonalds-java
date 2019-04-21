package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class MWFullMenu implements Serializable {
    @SerializedName("items")
    public MWFullMenuItems fullMenuItems;
}
