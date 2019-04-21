package com.mcdonalds.sdk.connectors.mwcustomersecurity.response.janrain;

import com.google.gson.annotations.SerializedName;

public class MWCustomerSecurityJanrainAccountDetailsPrimaryAddressResponse<T> {
    @SerializedName("country")
    String addressCountry;
    @SerializedName("mobile")
    String mobilePhone;
    @SerializedName("zip")
    String zipCode;

    public String getAddressCountry() {
        return this.addressCountry;
    }

    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getMobilePhone() {
        return this.mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String toString() {
        return "MWCustomerSecurityJanrainAccountDetailsPrimaryAddressResponse{addressCountry='" + this.addressCountry + '\'' + ", zipCode='" + this.zipCode + '\'' + ", mobilePhone='" + this.mobilePhone + '\'' + '}';
    }
}
