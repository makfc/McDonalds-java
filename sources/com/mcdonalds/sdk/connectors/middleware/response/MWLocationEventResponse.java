package com.mcdonalds.sdk.connectors.middleware.response;

import com.google.gson.annotations.SerializedName;

public class MWLocationEventResponse {
    @SerializedName("body")
    private String body;
    @SerializedName("status")
    private String status;
    @SerializedName("statusCode")
    private String statusCode;
    @SerializedName("statusDescription")
    private String statusDescription;

    public String toString() {
        return "MWLocationEventResponse{status='" + this.status + '\'' + ", statusCode='" + this.statusCode + '\'' + ", statusDescription='" + this.statusDescription + '\'' + '}';
    }
}
