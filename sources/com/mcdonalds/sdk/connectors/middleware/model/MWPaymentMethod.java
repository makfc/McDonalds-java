package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.PaymentMethod;
import com.mcdonalds.sdk.modules.models.PaymentMethod.PaymentMode;
import java.io.Serializable;
import java.util.List;

public class MWPaymentMethod implements Serializable {
    @SerializedName("AcceptsOneTimePayment")
    public Boolean acceptsOneTimePayment;
    @SerializedName("CVVThresholdAmount")
    public Double cVVThresholdAmount;
    @SerializedName("DisplayImageName")
    public String displayImageName;
    @SerializedName("IsEnabled")
    public Boolean isEnabled;
    @SerializedName("IsValid")
    public Boolean isValid;
    @SerializedName("minTransactionAmount")
    public Integer minTransactionAmount;
    @SerializedName("PaymentLabels")
    public List<MWPaymentLabel> paymentLabels;
    @SerializedName("PaymentMethodID")
    public Integer paymentMethodID;
    @SerializedName("PaymentMode")
    public Integer paymentMode;
    @SerializedName("PaymentReturnURL")
    public String paymentReturnURL;
    @SerializedName("PaymentSchemas")
    public List<MWPaymentSchema> paymentSchemas;
    @SerializedName("PaymentType")
    public Integer paymentType;
    @SerializedName("Rank")
    public Integer rank;
    @SerializedName("RegistrationReturnURL")
    public String registrationReturnURL;
    @SerializedName("RegistrationType")
    public Integer registrationType;
    @SerializedName("RequiresPwd")
    public Boolean requiresPwd;
    @SerializedName("StaticsData")
    public List<Integer> staticsData;
    @SerializedName("thresholdAmount")
    public Double thresholdAmount;

    @Deprecated
    public static PaymentMethod toPaymentMethod(MWPaymentMethod mwPaymentMethod) {
        return mwPaymentMethod.toPaymentMethod();
    }

    public PaymentMethod toPaymentMethod() {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setID(this.paymentMethodID);
        paymentMethod.setPaymentType(this.paymentType);
        paymentMethod.setRegistrationType(this.registrationType);
        paymentMethod.setRegistrationReturnURL(this.registrationReturnURL);
        if (this.paymentLabels != null) {
            paymentMethod.setName(((MWPaymentLabel) this.paymentLabels.get(0)).name);
        }
        if (this.paymentMode != null) {
            paymentMethod.setPaymentMode(PaymentMode.values()[this.paymentMode.intValue()]);
        } else {
            Thread.dumpStack();
        }
        return paymentMethod;
    }

    @Deprecated
    public List<Integer> getStaticsData() {
        return this.staticsData;
    }

    @Deprecated
    public void setStaticsData(List<Integer> staticsData) {
        this.staticsData = staticsData;
    }

    @Deprecated
    public List<MWPaymentLabel> getPaymentLabels() {
        return this.paymentLabels;
    }

    @Deprecated
    public void setPaymentLabels(List<MWPaymentLabel> paymentLabels) {
        this.paymentLabels = paymentLabels;
    }

    public Integer getPaymentMethodID() {
        return this.paymentMethodID;
    }

    @Deprecated
    public void setPaymentMethodID(Integer paymentMethodID) {
        this.paymentMethodID = paymentMethodID;
    }

    @Deprecated
    public Boolean getIsEnabled() {
        return this.isEnabled;
    }

    @Deprecated
    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Deprecated
    public String getDisplayImageName() {
        return this.displayImageName;
    }

    @Deprecated
    public void setDisplayImageName(String displayImageName) {
        this.displayImageName = displayImageName;
    }

    @Deprecated
    public Boolean getIsValid() {
        return this.isValid;
    }

    @Deprecated
    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    @Deprecated
    public Integer getPaymentMode() {
        return this.paymentMode;
    }

    @Deprecated
    public void setPaymentMode(Integer paymentMode) {
        this.paymentMode = paymentMode;
    }

    @Deprecated
    public Integer getRegistrationType() {
        return this.registrationType;
    }

    @Deprecated
    public void setRegistrationType(Integer registrationType) {
        this.registrationType = registrationType;
    }

    @Deprecated
    public Integer getPaymentType() {
        return this.paymentType;
    }

    @Deprecated
    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    @Deprecated
    public String getRegistrationReturnURL() {
        return this.registrationReturnURL;
    }

    @Deprecated
    public void setRegistrationReturnURL(String registrationReturnURL) {
        this.registrationReturnURL = registrationReturnURL;
    }

    @Deprecated
    public String getPaymentReturnURL() {
        return this.paymentReturnURL;
    }

    @Deprecated
    public void setPaymentReturnURL(String paymentReturnURL) {
        this.paymentReturnURL = paymentReturnURL;
    }

    @Deprecated
    public List<MWPaymentSchema> getPaymentSchemas() {
        return this.paymentSchemas;
    }

    @Deprecated
    public void setPaymentSchemas(List<MWPaymentSchema> paymentSchemas) {
        this.paymentSchemas = paymentSchemas;
    }

    @Deprecated
    public Boolean getAcceptsOneTimePayment() {
        return this.acceptsOneTimePayment;
    }

    @Deprecated
    public void setAcceptsOneTimePayment(Boolean acceptsOneTimePayment) {
        this.acceptsOneTimePayment = acceptsOneTimePayment;
    }

    @Deprecated
    public Boolean getRequiresPwd() {
        return this.requiresPwd;
    }

    @Deprecated
    public void setRequiresPwd(Boolean requiresPwd) {
        this.requiresPwd = requiresPwd;
    }

    @Deprecated
    public Integer getRank() {
        return this.rank;
    }

    @Deprecated
    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Deprecated
    public Integer getMinTransactionAmount() {
        return this.minTransactionAmount;
    }

    @Deprecated
    public void setMinTransactionAmount(Integer minTransactionAmount) {
        this.minTransactionAmount = minTransactionAmount;
    }

    @Deprecated
    public Double getThresholdAmount() {
        return this.thresholdAmount;
    }

    @Deprecated
    public void setThresholdAmount(Double thresholdAmount) {
        this.thresholdAmount = thresholdAmount;
    }

    @Deprecated
    public Double getCVVThresholdAmount() {
        return this.cVVThresholdAmount;
    }

    @Deprecated
    public void setCVVThresholdAmount(Double CVVThresholdAmount) {
        this.cVVThresholdAmount = CVVThresholdAmount;
    }
}
