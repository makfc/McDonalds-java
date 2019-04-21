package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;

public class MWAppParameter {
    @SerializedName("Name")
    public String name;
    @SerializedName("Value")
    public String value;
}
