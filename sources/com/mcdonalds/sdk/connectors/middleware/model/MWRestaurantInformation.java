package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class MWRestaurantInformation {
    @SerializedName("FulfilmentFacilityOpeningHours")
    public List<MWFulfillmentFacilityOpeningHour> fulfilmentFacilityOpeningHours = new ArrayList();
}
