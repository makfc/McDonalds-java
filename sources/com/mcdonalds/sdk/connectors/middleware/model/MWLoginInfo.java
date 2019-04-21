package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class MWLoginInfo implements Serializable {
    @SerializedName("AccessToken")
    public String accessToken;
    @SerializedName("InternalID")
    public String internalID;
    @SerializedName("LoginType")
    public int loginType;
    @SerializedName("Password")
    public String password;
    @SerializedName("SocialNetworkID")
    public String socialNetworkId;
    @SerializedName("UserName")
    public String username;
}
