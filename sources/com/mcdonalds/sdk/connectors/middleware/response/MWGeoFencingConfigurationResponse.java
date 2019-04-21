package com.mcdonalds.sdk.connectors.middleware.response;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class MWGeoFencingConfigurationResponse {
    @SerializedName("check_in_tracking_time_out")
    public Integer checkInTrackingTimeOut;
    @SerializedName("delay_in_notification")
    public Integer delayInNotification;
    @SerializedName("proximity_distance")
    public Integer proximityDistance;
    @SerializedName("track_stores_near_destination_of_order")
    public Boolean trackStoresNearDestinationOfOrder;
    @SerializedName("track_stores_near_origin_of_order")
    public Boolean trackStoresNearOriginOfOrder;
    @SerializedName("tracked_stores_boundary_radius")
    public Integer trackedStoresBoundaryRadius;
    @SerializedName("tracked_stores_count")
    public Integer trackedStoresCount;
    @SerializedName("tracking_interval")
    public List<MWGeoFencingTrackingInterval> trackingInterval = new ArrayList();
}
