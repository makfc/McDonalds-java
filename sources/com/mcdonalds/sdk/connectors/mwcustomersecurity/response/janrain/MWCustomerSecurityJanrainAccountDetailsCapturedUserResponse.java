package com.mcdonalds.sdk.connectors.mwcustomersecurity.response.janrain;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MWCustomerSecurityJanrainAccountDetailsCapturedUserResponse<T> {
    @SerializedName("birthday")
    String birthDate;
    @SerializedName("deactivateAccount")
    String deactivateAccount;
    @SerializedName("email")
    String emailAddress;
    @SerializedName("emailVerified")
    String emailVerified;
    @SerializedName("givenName")
    String firstName;
    @SerializedName("gender")
    String gender;
    @SerializedName("familyName")
    String lastName;
    @SerializedName("lastUpdated")
    String lastUpdated;
    @SerializedName("primaryAddress")
    MWCustomerSecurityJanrainAccountDetailsPrimaryAddressResponse primaryAddressResponse;
    @SerializedName("ppMobile")
    String privacyPolicyVersion;
    @SerializedName("smsVerified")
    String smsVerified;
    @SerializedName("tncMobile")
    String termsAndConditionVersion;
    @SerializedName("uuid")
    String uuid;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return this.birthDate;
    }

    public Calendar getBirthdateCalendar() {
        if (this.birthDate != null) {
            Calendar birthdate = null;
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(this.birthDate);
                birthdate = Calendar.getInstance();
                birthdate.setTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (birthdate != null) {
                return birthdate;
            }
        }
        return null;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public MWCustomerSecurityJanrainAccountDetailsPrimaryAddressResponse getPrimaryAddressResponse() {
        return this.primaryAddressResponse;
    }

    public void setPrimaryAddressResponse(MWCustomerSecurityJanrainAccountDetailsPrimaryAddressResponse primaryAddressResponse) {
        this.primaryAddressResponse = primaryAddressResponse;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getLastUpdated() {
        return this.lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getEmailVerified() {
        return this.emailVerified;
    }

    public void setEmailVerified(String emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getSmsVerified() {
        return this.smsVerified;
    }

    public void setSmsVerified(String smsVerified) {
        this.smsVerified = smsVerified;
    }

    public String getTermsAndConditionVersion() {
        return this.termsAndConditionVersion;
    }

    public void setTermsAndConditionVersion(String termsAndConditionVersion) {
        this.termsAndConditionVersion = termsAndConditionVersion;
    }

    public String getPrivacyPolicyVersion() {
        return this.privacyPolicyVersion;
    }

    public void setPrivacyPolicyVersion(String privacyPolicyVersion) {
        this.privacyPolicyVersion = privacyPolicyVersion;
    }

    public boolean isDeactivateAccount() {
        return !TextUtils.isEmpty(this.deactivateAccount);
    }

    public void setDeactivateAccount(String deactivateAccount) {
        this.deactivateAccount = deactivateAccount;
    }

    public String toString() {
        return "MWCustomerSecurityJanrainAccountDetailsCapturedUserResponse{firstName='" + this.firstName + '\'' + ", lastName='" + this.lastName + '\'' + ", birthDate='" + this.birthDate + '\'' + ", gender='" + this.gender + '\'' + ", primaryAddressResponse=" + this.primaryAddressResponse + ", emailAddress='" + this.emailAddress + '\'' + ", uuid='" + this.uuid + '\'' + ", lastUpdated='" + this.lastUpdated + '\'' + ", emailVerified='" + this.emailVerified + '\'' + ", smsVerified='" + this.smsVerified + '\'' + ", termsAndConditionVersion='" + this.termsAndConditionVersion + '\'' + ", privacyPolicyVersion='" + this.privacyPolicyVersion + '\'' + ", deactivateAccount='" + this.deactivateAccount + '\'' + '}';
    }
}
