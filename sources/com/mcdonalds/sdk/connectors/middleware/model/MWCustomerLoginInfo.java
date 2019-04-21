package com.mcdonalds.sdk.connectors.middleware.model;

import android.support.annotation.NonNull;
import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.CustomerLoginInfo;

public class MWCustomerLoginInfo {
    @SerializedName("IsDefaultPhoneNumberTakenOver")
    private boolean isDefaultPhoneNumberTakenOver;
    @SerializedName("IsDefaultPhoneNumberVerified")
    private boolean isDefaultPhoneNumberVerified;
    @SerializedName("IsEmailAddressTakenOver")
    private boolean isEmailAddressTakenOver;
    @SerializedName("IsEmailAddressVerified")
    private boolean isEmailAddressVerified;

    public MWCustomerLoginInfo(boolean isEmailAddressTakenOver, boolean isEmailAddressVerified, boolean isDefaultPhoneNumberTakenOver, boolean isDefaultPhoneNumberVerified) {
        this.isEmailAddressTakenOver = isEmailAddressTakenOver;
        this.isEmailAddressVerified = isEmailAddressVerified;
        this.isDefaultPhoneNumberTakenOver = isDefaultPhoneNumberTakenOver;
        this.isDefaultPhoneNumberVerified = isDefaultPhoneNumberVerified;
    }

    public boolean isEmailAddressTakenOver() {
        return this.isEmailAddressTakenOver;
    }

    public boolean isEmailAddressVerified() {
        return this.isEmailAddressVerified;
    }

    public boolean isDefaultPhoneNumberTakenOver() {
        return this.isDefaultPhoneNumberTakenOver;
    }

    public boolean isDefaultPhoneNumberVerified() {
        return this.isDefaultPhoneNumberVerified;
    }

    public CustomerLoginInfo toCustomerLoginInfo() {
        return new CustomerLoginInfo(this.isEmailAddressTakenOver, this.isEmailAddressVerified, this.isDefaultPhoneNumberTakenOver, this.isDefaultPhoneNumberVerified);
    }

    public static MWCustomerLoginInfo fromCustomerLoginInfo(@NonNull CustomerLoginInfo info) {
        return new MWCustomerLoginInfo(info.isEmailAddressTakenOver(), info.isEmailAddressVerified(), info.isDefaultPhoneNumberTakenOver(), info.isDefaultPhoneNumberVerified());
    }
}
