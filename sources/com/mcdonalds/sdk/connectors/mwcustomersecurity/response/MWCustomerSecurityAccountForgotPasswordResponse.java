package com.mcdonalds.sdk.connectors.mwcustomersecurity.response;

import com.google.gson.annotations.SerializedName;

public class MWCustomerSecurityAccountForgotPasswordResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("statusCode")
    private String statusCode;
    @SerializedName("statusDescription")
    private String statusDescription;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescription() {
        return this.statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String toString() {
        return "MWCustomerSecurityAccountForgotPasswordResponse{status='" + this.status + '\'' + ", statusCode='" + this.statusCode + '\'' + ", statusDescription='" + this.statusDescription + '\'' + '}';
    }
}
