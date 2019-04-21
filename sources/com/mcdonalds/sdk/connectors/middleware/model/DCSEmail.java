package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;

public class DCSEmail {
    public static final String TYPE_PERSONAL = "personal";
    public static final String TYPE_WORK = "work";
    @SerializedName("activeInd")
    public String activeInd;
    @SerializedName("emailAddress")
    public String emailAddress;
    @SerializedName("primaryInd")
    public String primaryInd;
    @SerializedName("type")
    public String type;
}
