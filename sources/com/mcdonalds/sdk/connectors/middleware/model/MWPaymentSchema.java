package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class MWPaymentSchema implements Serializable {
    @SerializedName("AllowBalanceInquiry")
    private Boolean mAllowBalanceInquiry;
    @SerializedName("AllowCardUpdate")
    private Boolean mAllowCardUpdate;
    @SerializedName("CVVLength")
    private Integer mCVVLength;
    @SerializedName("DisplayImageName")
    private String mDisplayImageName;
    @SerializedName("Name")
    private String mName;
    @SerializedName("PaymentSchemaID")
    private Integer mPaymentSchemaID;
    @SerializedName("RequiresCVV")
    private Boolean mRequiresCVV;
    @SerializedName("StaticData")
    private List<Integer> mStaticData;
    @SerializedName("eArchCardAmountPreDefList")
    private List<Double> meArchCardAmountPreDefList;

    public Integer getPaymentSchemaID() {
        return this.mPaymentSchemaID;
    }

    public String getName() {
        return this.mName;
    }

    public String getDisplayImageName() {
        return this.mDisplayImageName;
    }

    public List<Integer> getStaticData() {
        return this.mStaticData;
    }

    public Integer getCVVLength() {
        return this.mCVVLength;
    }

    public Boolean getRequiresCVV() {
        return this.mRequiresCVV;
    }

    public Boolean getAllowCardUpdate() {
        return this.mAllowCardUpdate;
    }

    public Boolean getAllowBalanceInquiry() {
        return this.mAllowBalanceInquiry;
    }

    public List<Double> getMeArchCardAmountPreDefList() {
        return this.meArchCardAmountPreDefList;
    }
}
