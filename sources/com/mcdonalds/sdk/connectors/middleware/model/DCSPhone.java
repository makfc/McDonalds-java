package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;

public class DCSPhone {
    public static final String TYPE_CELL = "cell";
    @SerializedName("activeInd")
    public String activeInd;
    @SerializedName("number")
    public String number;
    @SerializedName("primaryInd")
    public String primaryInd;
    @SerializedName("type")
    public String type;
}
