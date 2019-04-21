package com.mcdonalds.sdk.connectors.mwcustomersecurity.response.janrain;

import com.google.gson.annotations.SerializedName;

public class MWCustomerSecurityJanrainAccountDetailsResponse {
    @SerializedName("capture_user")
    MWCustomerSecurityJanrainAccountDetailsCapturedUserResponse capturedUserResponse;
    @SerializedName("invalid_fields")
    MWCustomerSecurityJanrainAccountDetailsInvalidFieldsResponse invalidFields;
    @SerializedName("stat")
    String status;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MWCustomerSecurityJanrainAccountDetailsInvalidFieldsResponse getInvalidFields() {
        return this.invalidFields;
    }

    public void setInvalidFields(MWCustomerSecurityJanrainAccountDetailsInvalidFieldsResponse invalidFields) {
        this.invalidFields = invalidFields;
    }

    public MWCustomerSecurityJanrainAccountDetailsCapturedUserResponse getCapturedUserResponse() {
        return this.capturedUserResponse;
    }

    public void setCapturedUserResponse(MWCustomerSecurityJanrainAccountDetailsCapturedUserResponse capturedUserResponse) {
        this.capturedUserResponse = capturedUserResponse;
    }

    public String toString() {
        return "MWCustomerSecurityJanrainAccountDetailsResponse{status='" + this.status + '\'' + ", invalidFields=" + this.invalidFields + ", capturedUserResponse=" + this.capturedUserResponse + '}';
    }
}
