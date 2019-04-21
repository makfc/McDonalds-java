package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.connectors.middleware.response.MWGetRecipeForIdResponse;
import java.io.Serializable;

public class MWGetItemByExternalIdResponse implements Serializable {
    @SerializedName("items")
    public MWGetRecipeForIdResponse item;
}
