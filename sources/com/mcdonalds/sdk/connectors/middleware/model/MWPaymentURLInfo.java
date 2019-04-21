package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;

public class MWPaymentURLInfo {
    @SerializedName("CustomerId")
    Long mCustomerId;
    @SerializedName("URL")
    String mURL;

    public Long getCustomerId() {
        return this.mCustomerId;
    }

    public void setCustomerId(Long customerId) {
        this.mCustomerId = customerId;
    }

    public String getURL() {
        return this.mURL;
    }

    public void setURL(String URL) {
        this.mURL = URL;
    }
}
