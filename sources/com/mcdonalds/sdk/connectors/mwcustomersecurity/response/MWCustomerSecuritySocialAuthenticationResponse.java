package com.mcdonalds.sdk.connectors.mwcustomersecurity.response;

import com.google.gson.annotations.SerializedName;

public class MWCustomerSecuritySocialAuthenticationResponse extends MWCustomerSecurityAuthenticationResponse {
    @SerializedName("authToken")
    private String authToken;

    public String getAuthToken() {
        return this.authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String toString() {
        return "MWCustomerSecuritySocialAuthenticationResponse{authToken='" + this.authToken + '\'' + '}';
    }
}
