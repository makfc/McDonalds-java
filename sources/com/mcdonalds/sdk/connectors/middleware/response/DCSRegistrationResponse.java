package com.mcdonalds.sdk.connectors.middleware.response;

import com.google.gson.annotations.SerializedName;

public class DCSRegistrationResponse extends DCSResponse<DCSRegistrationDetails> {

    public class DCSRegistrationDetails {
        @SerializedName("refreshToken")
        public String refreshToken;
        @SerializedName("token")
        public String token;
    }
}
