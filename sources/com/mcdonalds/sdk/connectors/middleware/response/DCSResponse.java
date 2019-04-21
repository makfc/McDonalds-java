package com.mcdonalds.sdk.connectors.middleware.response;

import com.google.gson.annotations.SerializedName;

public class DCSResponse<T> {
    @SerializedName("details")
    private T details;
    @SerializedName("status")
    private String status;
    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("statusDescription")
    public String statusDescription;

    public String getStatus() {
        return this.status;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getStatusDescription() {
        return this.statusDescription;
    }

    public T getDetails() {
        return this.details;
    }
}
