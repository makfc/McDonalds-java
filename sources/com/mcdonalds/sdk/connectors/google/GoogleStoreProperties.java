package com.mcdonalds.sdk.connectors.google;

import com.google.gson.annotations.SerializedName;

public class GoogleStoreProperties {
    @SerializedName("AddressLine")
    private String mAddressLine;
    @SerializedName("applicationURL")
    private String mApplicationURL;
    @SerializedName("PrimaryCity")
    private String mCity;
    @SerializedName("CountryRegion")
    private String mCountryRegion;
    @SerializedName("distance")
    private double mDistance;
    @SerializedName("DriveThru")
    private String mDriveThru;
    @SerializedName("EntityID")
    private String mEntityID;
    @SerializedName("GiftCards")
    private String mGiftCards;
    @SerializedName("gx_id")
    private String mGx_id;
    @SerializedName("MobileOffers")
    private String mMobileOffers;
    @SerializedName("MobileOrdering")
    private String mMobileOrdering;
    @SerializedName("NatlStrNumber")
    private String mNatlStrNumber;
    @SerializedName("PhoneNumber")
    private String mPhoneNumber;
    @SerializedName("PlayLand")
    private String mPlayLand;
    @SerializedName("PostalCode")
    private String mPostalCode;
    @SerializedName("Region")
    private String mRegion;
    @SerializedName("SiteIdNumber")
    private String mSiteIdNumber;
    @SerializedName("StoreType")
    private String mStoreType;
    @SerializedName("storeURL")
    private String mStoreURL;
    @SerializedName("Subdivision")
    private String mSubdivision;
    @SerializedName("WiFi")
    private String mWifi;

    public String getAddressLine() {
        return this.mAddressLine;
    }

    public void setAddressLine(String addressLine) {
        this.mAddressLine = addressLine;
    }

    public String getEntityID() {
        return this.mEntityID;
    }

    public void setEntityID(String entityID) {
        this.mEntityID = entityID;
    }

    public String getCity() {
        return this.mCity;
    }

    public void setCity(String city) {
        this.mCity = city;
    }

    public String getSubdivision() {
        return this.mSubdivision;
    }

    public void setSubdivision(String subdivision) {
        this.mSubdivision = subdivision;
    }

    public String getPostalCode() {
        return this.mPostalCode;
    }

    public void setPostalCode(String postalCode) {
        this.mPostalCode = postalCode;
    }

    public String getCountryRegion() {
        return this.mCountryRegion;
    }

    public void setCountryRegion(String countryRegion) {
        this.mCountryRegion = countryRegion;
    }

    public String getStoreType() {
        return this.mStoreType;
    }

    public void setStoreType(String storeType) {
        this.mStoreType = storeType;
    }

    public String getPlayLand() {
        return this.mPlayLand;
    }

    public void setPlayLand(String playLand) {
        this.mPlayLand = playLand;
    }

    public String getDriveThru() {
        return this.mDriveThru;
    }

    public void setDriveThru(String driveThru) {
        this.mDriveThru = driveThru;
    }

    public String getWifi() {
        return this.mWifi;
    }

    public void setWifi(String wifi) {
        this.mWifi = wifi;
    }

    public String getGiftCards() {
        return this.mGiftCards;
    }

    public void setGiftCards(String giftCards) {
        this.mGiftCards = giftCards;
    }

    public String getMobileOffers() {
        return this.mMobileOffers;
    }

    public void setMobileOffers(String mobileOffers) {
        this.mMobileOffers = mobileOffers;
    }

    public String getMobileOrdering() {
        return this.mMobileOrdering;
    }

    public void setMobileOrdering(String mobileOrdering) {
        this.mMobileOrdering = mobileOrdering;
    }

    public String getStoreURL() {
        return this.mStoreURL;
    }

    public void setStoreURL(String storeURL) {
        this.mStoreURL = storeURL;
    }

    public String getApplicationURL() {
        return this.mApplicationURL;
    }

    public void setApplicationURL(String applicationURL) {
        this.mApplicationURL = applicationURL;
    }

    public String getPhoneNumber() {
        return this.mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.mPhoneNumber = phoneNumber;
    }

    public String getNatlStrNumber() {
        return this.mNatlStrNumber;
    }

    public void setNatlStrNumber(String natlStrNumber) {
        this.mNatlStrNumber = natlStrNumber;
    }

    public String getSiteIdNumber() {
        return this.mSiteIdNumber;
    }

    public void setSiteIdNumber(String siteIdNumber) {
        this.mSiteIdNumber = siteIdNumber;
    }

    public String getRegion() {
        return this.mRegion;
    }

    public void setRegion(String region) {
        this.mRegion = region;
    }

    public String getGxId() {
        return this.mGx_id;
    }

    public void setGxId(String gx_id) {
        this.mGx_id = gx_id;
    }

    public double getDistance() {
        return this.mDistance;
    }

    public void setDistance(double distance) {
        this.mDistance = distance;
    }
}
