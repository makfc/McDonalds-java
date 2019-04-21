package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class MWLoginToAdd implements Serializable {
    @SerializedName("AccessToken")
    public String accessToken;
    @SerializedName("InternalID")
    public String internalID;
    @SerializedName("LoginType")
    public int loginType;
    @SerializedName("SocialNetworkID")
    public int socialNetworkId;
    @SerializedName("UserName")
    public String username;
}
