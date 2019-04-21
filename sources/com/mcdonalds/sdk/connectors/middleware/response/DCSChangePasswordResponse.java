package com.mcdonalds.sdk.connectors.middleware.response;

import com.google.gson.annotations.SerializedName;

public class DCSChangePasswordResponse extends DCSResponse<DCSChangePasswordDetails> {

    public class DCSChangePasswordDetails {
        @SerializedName("status")
        public String status;
        @SerializedName("statusCode")
        public String statusCode;
        @SerializedName("statusDescription")
        public String statusDescription;
    }
}
