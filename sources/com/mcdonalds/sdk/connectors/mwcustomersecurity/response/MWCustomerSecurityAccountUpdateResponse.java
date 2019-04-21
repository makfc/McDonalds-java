package com.mcdonalds.sdk.connectors.mwcustomersecurity.response;

import com.google.gson.annotations.SerializedName;

public class MWCustomerSecurityAccountUpdateResponse<T> {
    @SerializedName("details")
    private MWCustomerSecurityAccountUpdateDetailsResponse details;
    @SerializedName("status")
    private String status;
    @SerializedName("statusCode")
    private String statusCode;
    @SerializedName("statusDescription")
    private String statusDescription;

    public MWCustomerSecurityAccountUpdateDetailsResponse getDetails() {
        return this.details;
    }

    public void setDetails(MWCustomerSecurityAccountUpdateDetailsResponse details) {
        this.details = details;
    }

    public String getStatusDescription() {
        return this.statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return "MWCustomerSecurityAuthenticationDetailsResponse{status='" + this.status + '\'' + ", statusCode='" + this.statusCode + '\'' + ", statusDescription='" + this.statusDescription + '}';
    }
}
