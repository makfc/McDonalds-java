package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.AuthenticationParameters;

public class MWSocialLoginInfo {
    @SerializedName("AccessToken")
    public String accessToken;
    @SerializedName("emailAddress")
    public String emailAddress;
    @SerializedName("InternalID")
    public String internalID;
    @SerializedName("LoginType")
    public int loginType;
    @SerializedName("mobileNumber")
    public String mobileNumber;
    @SerializedName("SocialNetworkID")
    public int socialNetworkID;
    @SerializedName("UserName")
    public String userName;

    public MWSocialLoginInfo(AuthenticationParameters parameters) {
        if (parameters.isUsingSocialLogin()) {
            this.userName = parameters.getUserName();
            if (parameters.isAllowSocialLoginWithoutEmail()) {
                this.emailAddress = null;
            } else {
                this.emailAddress = parameters.getEmailAddress();
            }
            this.loginType = 1;
            this.socialNetworkID = parameters.getSocialServiceID();
            this.accessToken = parameters.getSocialAuthenticationToken();
            this.internalID = parameters.getSocialUserID();
            return;
        }
        throw new RuntimeException("AuthenticationParameters must be using social login");
    }
}
