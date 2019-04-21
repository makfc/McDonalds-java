package com.mcdonalds.sdk.connectors.middlewarestorelocator.model;

import com.google.gson.annotations.SerializedName;

public class MiddlewareStoreLocatorLocation {
    @SerializedName("lat")
    public double latitude;
    @SerializedName("lon")
    public double longitude;
}
