package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;

public class MWPromotionAction {
    @SerializedName("Type")
    public MWPromotionActionType type;
    @SerializedName("Value")
    public double value;
}
