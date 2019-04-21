package com.mcdonalds.sdk.connectors.mwcustomersecurity.response.janrain;

import com.google.gson.annotations.SerializedName;
import java.util.Arrays;

public class MWCustomerSecurityJanrainAccountDetailsInvalidFieldsResponse {
    @SerializedName("emailAddress")
    String[] emailAddress;
    @SerializedName("forgotPasswordForm")
    String[] forgotPassword;
    @SerializedName("mobile")
    String[] mobile;
    @SerializedName("addressPostalCode")
    String[] postalCode;
    @SerializedName("signInForm")
    String[] signInForm;

    public String getSignInForm() {
        if (this.signInForm != null) {
            return this.signInForm[0];
        }
        return null;
    }

    public void setSignInForm(String signInForm) {
        this.signInForm[0] = signInForm;
    }

    public String getEmailAddress() {
        if (this.emailAddress != null) {
            return this.emailAddress[0];
        }
        return null;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress[0] = emailAddress;
    }

    public String getMobile() {
        if (this.mobile != null) {
            return this.mobile[0];
        }
        return null;
    }

    public void setMobile(String mobile) {
        this.mobile[0] = mobile;
    }

    public String getPostalCode() {
        if (this.postalCode != null) {
            return this.postalCode[0];
        }
        return null;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode[0] = postalCode;
    }

    public String getForgotPassword() {
        if (this.forgotPassword != null) {
            return this.forgotPassword[0];
        }
        return null;
    }

    public void setForgotPassword(String forgotPassword) {
        this.forgotPassword[0] = forgotPassword;
    }

    public String toString() {
        return "MWCustomerSecurityJanrainAccountDetailsInvalidFieldsResponse{signInForm=" + Arrays.toString(this.signInForm) + ", emailAddress=" + Arrays.toString(this.emailAddress) + ", mobile=" + Arrays.toString(this.mobile) + ", postalCode=" + Arrays.toString(this.postalCode) + ", forgotPassword=" + Arrays.toString(this.forgotPassword) + '}';
    }
}
