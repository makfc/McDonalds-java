package com.mcdonalds.sdk.modules.customer;

import com.mcdonalds.sdk.modules.AppModel;

public class OptIn extends AppModel {
    private String mAcceptanceTimestamp;
    private Boolean mStatus;
    private String mType;

    public String getType() {
        return this.mType;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public Boolean getStatus() {
        return this.mStatus;
    }

    public void setStatus(Boolean status) {
        this.mStatus = status;
    }

    public String getAcceptanceTimestamp() {
        return this.mAcceptanceTimestamp;
    }

    public void setAcceptanceTimestamp(String mAcceptanceTimestamp) {
        this.mAcceptanceTimestamp = mAcceptanceTimestamp;
    }
}
