package com.mcdonalds.sdk.connectors.mwcustomersecurity.response;

import com.google.gson.annotations.SerializedName;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MWCustomerSecurityAccountDetailsResponse<T> {
    @SerializedName("addressCountry")
    String addressCountry;
    @SerializedName("birthDate")
    String birthDate;
    @SerializedName("emailAddress")
    String emailAddress;
    @SerializedName("firstName")
    String firstName;
    @SerializedName("gender")
    String gender;
    @SerializedName("invalidFields")
    T invalidFields;
    @SerializedName("lastName")
    String lastName;
    @SerializedName("lastUpdated")
    String lastUpdated;
    @SerializedName("mobilePhone")
    String mobilePhone;
    @SerializedName("status")
    String status;
    @SerializedName("token")
    String token;
    @SerializedName("uuid")
    String uuid;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddressCountry() {
        return this.addressCountry;
    }

    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMobilePhone() {
        return this.mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLastUpdated() {
        return this.lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public T getInvalidFields() {
        return this.invalidFields;
    }

    public void setInvalidFields(T invalidFields) {
        this.invalidFields = invalidFields;
    }

    public String toString() {
        return "MWCustomerSecurityAccountDetailsResponse{status='" + this.status + '\'' + ", firstName='" + this.firstName + '\'' + ", lastName='" + this.lastName + '\'' + ", birthDate='" + this.birthDate + '\'' + ", gender='" + this.gender + '\'' + ", addressCountry='" + this.addressCountry + '\'' + ", emailAddress='" + this.emailAddress + '\'' + ", mobilePhone='" + this.mobilePhone + '\'' + ", uuid='" + this.uuid + '\'' + ", token='" + this.token + '\'' + ", lastUpdated='" + this.lastUpdated + '\'' + ", invalidFields=" + this.invalidFields + '}';
    }
}
