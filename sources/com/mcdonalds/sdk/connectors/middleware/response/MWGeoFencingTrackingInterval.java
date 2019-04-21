package com.mcdonalds.sdk.connectors.middleware.response;

import com.google.gson.annotations.SerializedName;

public class MWGeoFencingTrackingInterval {
    @SerializedName("distance_from")
    public Integer distanceFrom;
    @SerializedName("distance_to")
    public Integer distanceTo;
    @SerializedName("gps_accuracy_desired")
    public String gpsAccuracyDesired;
    @SerializedName("interval_off")
    public Integer intervalOff;
    @SerializedName("interval_on")
    public Integer intervalOn;
}
