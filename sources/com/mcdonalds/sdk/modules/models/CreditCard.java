package com.mcdonalds.sdk.modules.models;

import com.mcdonalds.sdk.modules.AppModel;

public class CreditCard extends AppModel {
    public static final String TYPE_AMERICAN_EXPRESS = "003";
    public static final String TYPE_JCB = "007";
    public static final String TYPE_MASTER_CARD = "002";
    public static final String TYPE_VISA = "001";
    private String mAddressCity;
    private String mAddressCountry;
    private String mAddressPhone;
    private String mAddressPostalCode;
    private String mAddressState;
    private String mAddressStreet;
    private String mCardExpiryDate;
    private String mCardNumber;
    private String mCardSecurityCode;
    private String mCardType;
    private String mEmail;
    private String mForename;
    private String mNickname;
    private String mSurname;

    public String getForename() {
        return this.mForename;
    }

    public void setForename(String mForename) {
        this.mForename = mForename;
    }

    public String getSurname() {
        return this.mSurname;
    }

    public void setSurname(String mSurname) {
        this.mSurname = mSurname;
    }

    public String getEmail() {
        return this.mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getAddressStreet() {
        return this.mAddressStreet;
    }

    public void setAddressStreet(String mAddressStreet) {
        this.mAddressStreet = mAddressStreet;
    }

    public String getAddressCity() {
        return this.mAddressCity;
    }

    public void setAddressCity(String mAddressCity) {
        this.mAddressCity = mAddressCity;
    }

    public String getAddressState() {
        return this.mAddressState;
    }

    public void setAddressState(String mAddressState) {
        this.mAddressState = mAddressState;
    }

    public String getAddressCountry() {
        return this.mAddressCountry;
    }

    public void setAddressCountry(String mAddressCountry) {
        this.mAddressCountry = mAddressCountry;
    }

    public String getAddressPhone() {
        return this.mAddressPhone;
    }

    public void setAddressPhone(String mAddressPhone) {
        this.mAddressPhone = mAddressPhone;
    }

    public String getAddressPostalCode() {
        return this.mAddressPostalCode;
    }

    public void setAddressPostalCode(String mAddressPostalCode) {
        this.mAddressPostalCode = mAddressPostalCode;
    }

    public String getNickname() {
        return this.mNickname;
    }

    public void setNickname(String mNickname) {
        this.mNickname = mNickname;
    }

    public String getCardType() {
        return this.mCardType;
    }

    public void setCardType(String mCardType) {
        this.mCardType = mCardType;
    }

    public String getCardNumber() {
        return this.mCardNumber;
    }

    public void setCardNumber(String mCardNumber) {
        this.mCardNumber = mCardNumber;
    }

    public String getCardExpiryDate() {
        return this.mCardExpiryDate;
    }

    public void setCardExpiryDate(String mCardExpiryDate) {
        this.mCardExpiryDate = mCardExpiryDate;
    }

    public String getCardSecurityCode() {
        return this.mCardSecurityCode;
    }

    public void setCardSecurityCode(String mCardSecurityCode) {
        this.mCardSecurityCode = mCardSecurityCode;
    }
}
