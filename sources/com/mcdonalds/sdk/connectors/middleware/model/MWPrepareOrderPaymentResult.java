package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;

public class MWPrepareOrderPaymentResult {
    @SerializedName("CVVLength")
    private Integer mCVVLength;
    @SerializedName("PaymentDataId")
    private Integer mPaymentDataId;
    @SerializedName("PaymentUrl")
    private String mPaymentUrl;
    @SerializedName("RequireCVV")
    private Boolean mRequireCVV;
    @SerializedName("RequiresPassword")
    private Boolean mRequiresPassword;

    public String getPaymentUrl() {
        return this.mPaymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.mPaymentUrl = paymentUrl;
    }

    public Boolean getRequireCVV() {
        return this.mRequireCVV;
    }

    public void setRequireCVV(Boolean requireCVV) {
        this.mRequireCVV = requireCVV;
    }

    public Integer getCVVLength() {
        return this.mCVVLength;
    }

    public void setCVVLength(Integer CVVLength) {
        this.mCVVLength = CVVLength;
    }

    public Boolean getRequiresPassword() {
        return this.mRequiresPassword;
    }

    public void setRequiresPassword(Boolean requiresPassword) {
        this.mRequiresPassword = requiresPassword;
    }

    public Integer getPaymentDataId() {
        return this.mPaymentDataId;
    }

    public void setPaymentDataId(Integer paymentDataId) {
        this.mPaymentDataId = paymentDataId;
    }
}
