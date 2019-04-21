package com.mcdonalds.sdk.connectors.mwcustomersecurity.response;

import com.google.gson.annotations.SerializedName;

public class MWCustomerSecurityConfigServiceConfigurationResponse {
    @SerializedName("Delete_Type")
    String accountDeleteType;
    @SerializedName("Locale")
    String locale;
    @SerializedName("PP_Mobile")
    String ppMobileDate;
    @SerializedName("TnC_Mobile")
    String tncMobileDate;
    @SerializedName("Ver_Type")
    String verificationType;

    public String getLocale() {
        return this.locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getTncMobileDate() {
        return this.tncMobileDate;
    }

    public void setTncMobileDate(String tncMobileDate) {
        this.tncMobileDate = tncMobileDate;
    }

    public String getPpMobileDate() {
        return this.ppMobileDate;
    }

    public void setPpMobileDate(String ppMobileDate) {
        this.ppMobileDate = ppMobileDate;
    }

    public String getVerificationType() {
        return this.verificationType.toLowerCase();
    }

    public void setVerificationType(String verificationType) {
        this.verificationType = verificationType;
    }

    public String getAccountDeleteType() {
        return this.accountDeleteType;
    }

    public void setAccountDeleteType(String accountDeleteType) {
        this.accountDeleteType = accountDeleteType;
    }

    public String toString() {
        return "MWCustomerSecurityConfigServiceConfigurationResponse{locale='" + this.locale + '\'' + ", tncMobileDate='" + this.tncMobileDate + '\'' + ", ppMobileDate='" + this.ppMobileDate + '\'' + ", verificationType='" + this.verificationType + '\'' + ", accountDeleteType='" + this.accountDeleteType + '\'' + '}';
    }
}
