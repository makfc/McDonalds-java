package com.mcdonalds.sdk.connectors.middleware.response;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.connectors.middleware.model.DCSProfile;

public class DCSGetProfileDetails {
    @SerializedName("languageCode")
    public String languageCode;
    @SerializedName("marketCode")
    public String marketCode;
    @SerializedName("profile")
    public DCSProfile profile;
}
