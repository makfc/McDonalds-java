package com.mcdonalds.sdk.connectors.middlewarestorelocator.model;

import com.google.gson.annotations.SerializedName;

public class MiddlewareStoreLocatorAddress {
    @SerializedName("addressLine1")
    public String addressLine1;
    @SerializedName("cityTown")
    public String city;
    @SerializedName("country")
    public String country;
    @SerializedName("location")
    public MiddlewareStoreLocatorLocation location;
    @SerializedName("region")
    public String region;
    @SerializedName("subdivision")
    public String state;
    @SerializedName("postalZip")
    public String zip;

    public String toString() {
        return "MWLocatorAddress{mAddressLine1=\"" + this.addressLine1 + "\", mCity=\"" + this.city + "\", mZip=\"" + this.zip + "\", mCountry=\"" + this.country + "\", mRegion=\"" + this.region + "\", mState=\"" + this.state + "\"}";
    }
}
