package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class MWDefaultCategory implements Serializable {
    @SerializedName("category")
    public MWDefaultCategoryCategory category;
}
