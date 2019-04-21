package com.mcdonalds.sdk.modules.models;

import com.mcdonalds.sdk.connectors.middleware.response.MWGeoFencingConfigurationResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWGeoFencingTrackingInterval;
import java.util.ArrayList;
import java.util.List;

public class GeoFencingConfiguration {
    public Integer mCheckInTrackingTimeOut;
    public Integer mDelayInNotification;
    public Integer mProximityDistance;
    public Boolean mTrackStoresNearDestinationOfOrder;
    public Boolean mTrackStoresNearOriginOfOrder;
    public Integer mTrackedStoresBoundaryRadius;
    public Integer mTrackedStoresCount;
    public List<GeoFencingTrackingInterval> mTrackingInterval = new ArrayList();

    private GeoFencingConfiguration() {
    }

    public static GeoFencingConfiguration fromMWGeoFencingConfiguration(MWGeoFencingConfigurationResponse configurationResponse) {
        if (configurationResponse == null) {
            return null;
        }
        GeoFencingConfiguration geoFencingConfiguration = new GeoFencingConfiguration();
        geoFencingConfiguration.setProximityDistance(configurationResponse.proximityDistance);
        geoFencingConfiguration.setCheckInTrackingTimeOut(configurationResponse.checkInTrackingTimeOut);
        geoFencingConfiguration.setTrackedStoresCount(configurationResponse.trackedStoresCount);
        geoFencingConfiguration.setTrackedStoresBoundaryRadius(configurationResponse.trackedStoresBoundaryRadius);
        geoFencingConfiguration.setTrackStoresNearOriginOfOrder(configurationResponse.trackStoresNearOriginOfOrder);
        geoFencingConfiguration.setTrackStoresNearDestinationOfOrder(configurationResponse.trackStoresNearDestinationOfOrder);
        geoFencingConfiguration.setDelayInNotification(configurationResponse.delayInNotification);
        if (configurationResponse.trackingInterval == null) {
            return geoFencingConfiguration;
        }
        for (MWGeoFencingTrackingInterval trackingInterval : configurationResponse.trackingInterval) {
            geoFencingConfiguration.mTrackingInterval.add(GeoFencingTrackingInterval.fromMWGeoFencingTimeInterval(trackingInterval));
        }
        return geoFencingConfiguration;
    }

    public List<GeoFencingTrackingInterval> getTrackingInterval() {
        return this.mTrackingInterval;
    }

    public void setTrackingInterval(List<GeoFencingTrackingInterval> trackingInterval) {
        this.mTrackingInterval = trackingInterval;
    }

    public Integer getProximityDistance() {
        return this.mProximityDistance;
    }

    public void setProximityDistance(Integer proximityDistance) {
        this.mProximityDistance = proximityDistance;
    }

    public Integer getCheckInTrackingTimeOut() {
        return this.mCheckInTrackingTimeOut;
    }

    public void setCheckInTrackingTimeOut(Integer checkInTrackingTimeOut) {
        this.mCheckInTrackingTimeOut = checkInTrackingTimeOut;
    }

    public Integer getTrackedStoresCount() {
        return this.mTrackedStoresCount;
    }

    public void setTrackedStoresCount(Integer trackedStoresCount) {
        this.mTrackedStoresCount = trackedStoresCount;
    }

    public Integer getTrackedStoresBoundaryRadius() {
        return this.mTrackedStoresBoundaryRadius;
    }

    public void setTrackedStoresBoundaryRadius(Integer trackedStoresBoundaryRadius) {
        this.mTrackedStoresBoundaryRadius = trackedStoresBoundaryRadius;
    }

    public Boolean getTrackStoresNearOriginOfOrder() {
        return this.mTrackStoresNearOriginOfOrder;
    }

    public void setTrackStoresNearOriginOfOrder(Boolean trackStoresNearOriginOfOrder) {
        this.mTrackStoresNearOriginOfOrder = trackStoresNearOriginOfOrder;
    }

    public Boolean getTrackStoresNearDestinationOfOrder() {
        return this.mTrackStoresNearDestinationOfOrder;
    }

    public void setTrackStoresNearDestinationOfOrder(Boolean trackStoresNearDestinationOfOrder) {
        this.mTrackStoresNearDestinationOfOrder = trackStoresNearDestinationOfOrder;
    }

    public Integer getDelayInNotification() {
        return this.mDelayInNotification;
    }

    public void setDelayInNotification(Integer delayInNotification) {
        this.mDelayInNotification = delayInNotification;
    }
}
