package com.mcdonalds.sdk.connectors.middleware.response;

import com.google.gson.annotations.SerializedName;

public class DCSApplicationSecurityResponse extends DCSResponse<DCSApplicationSecurityDetails> {

    public class DCSApplicationSecurityDetails {
        @SerializedName("token")
        public String token;
    }
}
