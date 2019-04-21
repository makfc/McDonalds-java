package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.FulfillmentFacilityOpeningHour;
import java.util.ArrayList;
import java.util.List;

public class MWFulfillmentFacilityOpeningHour {
    @SerializedName("BreakfastFrom")
    public String breakfastFrom;
    @SerializedName("BreakfastTo")
    public String breakfastTo;
    @SerializedName("FromTime")
    public String fromTime;
    @SerializedName("FulfilmentFacilityCode")
    public String fulfilmentFacilityCode;
    @SerializedName("ToTime")
    public String toTime;
    @SerializedName("WeekDay")
    public Integer weekDay;

    public static List<FulfillmentFacilityOpeningHour> toFulfillmentFacilityOpeningHours(List<MWFulfillmentFacilityOpeningHour> fulfillmentFacilityOpeningHours) {
        ArrayList<FulfillmentFacilityOpeningHour> fulfillmentFacilityOpeningHourList = null;
        if (!(fulfillmentFacilityOpeningHours == null || fulfillmentFacilityOpeningHours.isEmpty())) {
            fulfillmentFacilityOpeningHourList = new ArrayList();
            for (MWFulfillmentFacilityOpeningHour fFacilityOpeningHour : fulfillmentFacilityOpeningHours) {
                fulfillmentFacilityOpeningHourList.add(fFacilityOpeningHour.toFulfillmentFacilityOpeningHour());
            }
        }
        return fulfillmentFacilityOpeningHourList;
    }

    public FulfillmentFacilityOpeningHour toFulfillmentFacilityOpeningHour() {
        FulfillmentFacilityOpeningHour fulfillmentFacilityOpeningHour = new FulfillmentFacilityOpeningHour();
        fulfillmentFacilityOpeningHour.setBreakfastFrom(this.breakfastFrom);
        fulfillmentFacilityOpeningHour.setBreakfastTo(this.breakfastTo);
        fulfillmentFacilityOpeningHour.setFromTime(this.fromTime);
        fulfillmentFacilityOpeningHour.setToTime(this.toTime);
        fulfillmentFacilityOpeningHour.setWeekDay(this.weekDay);
        fulfillmentFacilityOpeningHour.setFulfilmentFacilityCode(this.fulfilmentFacilityCode);
        return fulfillmentFacilityOpeningHour;
    }
}
