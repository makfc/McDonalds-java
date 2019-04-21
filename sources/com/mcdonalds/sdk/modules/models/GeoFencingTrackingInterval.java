package com.mcdonalds.sdk.modules.models;

import com.mcdonalds.sdk.connectors.middleware.response.MWGeoFencingTrackingInterval;

public class GeoFencingTrackingInterval {
    public Integer mDistanceFrom;
    public Integer mDistanceTo;
    public String mGPSAccuracyDesired;
    public Integer mIntervalOff;
    public Integer mIntervalOn;

    public GeoFencingTrackingInterval(String gpsAccuracy, Integer intervalOff) {
        this.mGPSAccuracyDesired = gpsAccuracy;
        this.mIntervalOff = intervalOff;
    }

    public static GeoFencingTrackingInterval fromMWGeoFencingTimeInterval(MWGeoFencingTrackingInterval timeInterval) {
        if (timeInterval == null) {
            return null;
        }
        GeoFencingTrackingInterval geoFencingTrackingInterval = new GeoFencingTrackingInterval();
        geoFencingTrackingInterval.setDistanceFrom(timeInterval.distanceFrom);
        geoFencingTrackingInterval.setDistanceTo(timeInterval.distanceTo);
        geoFencingTrackingInterval.setIntervalOn(timeInterval.intervalOn);
        geoFencingTrackingInterval.setIntervalOff(timeInterval.intervalOff);
        geoFencingTrackingInterval.setGPSAccuracyDesired(timeInterval.gpsAccuracyDesired);
        return geoFencingTrackingInterval;
    }

    public Integer getDistanceFrom() {
        return this.mDistanceFrom;
    }

    public void setDistanceFrom(Integer distanceFrom) {
        this.mDistanceFrom = distanceFrom;
    }

    public Integer getDistanceTo() {
        return this.mDistanceTo;
    }

    public void setDistanceTo(Integer distanceTo) {
        this.mDistanceTo = distanceTo;
    }

    public Integer getIntervalOn() {
        return this.mIntervalOn;
    }

    public void setIntervalOn(Integer intervalOn) {
        this.mIntervalOn = intervalOn;
    }

    public Integer getIntervalOff() {
        return this.mIntervalOff;
    }

    public void setIntervalOff(Integer intervalOff) {
        this.mIntervalOff = intervalOff;
    }

    public String getGPSAccuracyDesired() {
        return this.mGPSAccuracyDesired;
    }

    public void setGPSAccuracyDesired(String gpsAccuracyDesired) {
        this.mGPSAccuracyDesired = gpsAccuracyDesired;
    }
}
