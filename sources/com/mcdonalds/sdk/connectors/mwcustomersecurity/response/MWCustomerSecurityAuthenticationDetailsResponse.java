package com.mcdonalds.sdk.connectors.mwcustomersecurity.response;

import com.google.gson.annotations.SerializedName;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

public class MWCustomerSecurityAuthenticationDetailsResponse<T> {
    @SerializedName("addressCountry")
    private String addressCountry;
    @SerializedName("birthdate")
    private String birthDate;
    @SerializedName("emailAddress")
    private String emailAddress;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("gender")
    private String gender;
    @SerializedName("InvalidFields")
    T invalidFields;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("lastUpdated")
    private String lastUpdated;
    @SerializedName("mobilePhone")
    private String mobilePhone;
    @SerializedName("optin")
    private Map<String, String> optIn;
    @SerializedName("status")
    private String status;
    @SerializedName("uuid")
    private String uuid;

    public String getAddressCountry() {
        return this.addressCountry;
    }

    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }

    public String getBirthDate() {
        return this.birthDate;
    }

    public Calendar getBirthdateCalendar() {
        if (this.birthDate == null) {
            return null;
        }
        Calendar birthdate = new GregorianCalendar();
        String[] fields = this.birthDate.split("-");
        birthdate.set(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), Integer.parseInt(fields[2]));
        return birthdate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public T getInvalidFields() {
        return this.invalidFields;
    }

    public void setInvalidFields(T invalidFields) {
        this.invalidFields = invalidFields;
    }

    public String getLastUpdated() {
        return this.lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobilePhone() {
        return this.mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Map<String, String> getOptIn() {
        return this.optIn;
    }

    public void setOptIn(Map<String, String> optIn) {
        this.optIn = optIn;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String toString() {
        return "MWCustomerSecurityAuthenticationDetailsResponse{status='" + this.status + '\'' + ", firstName='" + this.firstName + '\'' + ", lastName='" + this.lastName + '\'' + ", birthDate='" + this.birthDate + '\'' + ", gender='" + this.gender + '\'' + ", addressCountry='" + this.addressCountry + '\'' + ", emailAddress='" + this.emailAddress + '\'' + ", mobilePhone='" + this.mobilePhone + '\'' + ", uuid='" + this.uuid + '\'' + ", lastUpdated='" + this.lastUpdated + '\'' + ", optIn=" + this.optIn + ", invalidFields=" + this.invalidFields + '}';
    }
}
