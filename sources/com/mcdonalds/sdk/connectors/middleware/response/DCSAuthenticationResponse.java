package com.mcdonalds.sdk.connectors.middleware.response;

import com.google.gson.annotations.SerializedName;

public class DCSAuthenticationResponse extends DCSResponse<DCSAuthenticationDetails> {

    public class DCSAuthenticationDetails extends DCSGetProfileDetails {
        @SerializedName("refreshToken")
        public String refreshToken;
        @SerializedName("token")
        public String token;
    }
}
