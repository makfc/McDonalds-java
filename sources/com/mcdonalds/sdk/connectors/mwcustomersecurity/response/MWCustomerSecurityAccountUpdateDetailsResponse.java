package com.mcdonalds.sdk.connectors.mwcustomersecurity.response;

import com.google.gson.annotations.SerializedName;

public class MWCustomerSecurityAccountUpdateDetailsResponse<T> {
    @SerializedName("status")
    private String status;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return "MWCustomerSecurityAccountDetailsResponse{status='" + this.status + '}';
    }
}
