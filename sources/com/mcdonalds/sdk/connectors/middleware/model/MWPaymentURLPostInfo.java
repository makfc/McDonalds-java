package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;

public class MWPaymentURLPostInfo {
    @SerializedName("Body")
    String mBody;
    @SerializedName("CustomerID")
    Long mCustomerId;
    @SerializedName("Method")
    String mMethod;
    @SerializedName("URL")
    String mURL;

    public Long getCustomerId() {
        return this.mCustomerId;
    }

    public String getURL() {
        return this.mURL;
    }

    public String getMethod() {
        return this.mMethod;
    }

    public String getBody() {
        return this.mBody;
    }
}
