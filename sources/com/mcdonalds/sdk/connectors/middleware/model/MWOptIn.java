package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;

public class MWOptIn {
    @SerializedName("AcceptanceTimestamp")
    private String acceptanceTimestamp;
    @SerializedName("Status")
    private Boolean status;
    @SerializedName("Type")
    private String type;

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getAcceptanceTimestamp() {
        return this.acceptanceTimestamp;
    }

    public void setAcceptanceTimestamp(String acceptanceTimestamp) {
        this.acceptanceTimestamp = acceptanceTimestamp;
    }
}
