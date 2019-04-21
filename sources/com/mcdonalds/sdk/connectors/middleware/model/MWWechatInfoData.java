package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;

public class MWWechatInfoData {
    @SerializedName("AccessToken")
    public String accessToken;
    @SerializedName("InternalID")
    public String internalID;
}
