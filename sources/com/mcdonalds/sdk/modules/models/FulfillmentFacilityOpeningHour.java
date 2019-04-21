package com.mcdonalds.sdk.modules.models;

public class FulfillmentFacilityOpeningHour {
    private String mBreakfastFrom;
    private String mBreakfastTo;
    private String mFromTime;
    private String mFulfilmentFacilityCode;
    private String mToTime;
    private Integer mWeekDay;

    public String getFulfilmentFacilityCode() {
        return this.mFulfilmentFacilityCode;
    }

    public void setFulfilmentFacilityCode(String fulfilmentFacilityCode) {
        this.mFulfilmentFacilityCode = fulfilmentFacilityCode;
    }

    public Integer getWeekDay() {
        return this.mWeekDay;
    }

    public void setWeekDay(Integer weekDay) {
        this.mWeekDay = weekDay;
    }

    public String getFromTime() {
        return this.mFromTime;
    }

    public void setFromTime(String fromTime) {
        this.mFromTime = fromTime;
    }

    public String getToTime() {
        return this.mToTime;
    }

    public void setToTime(String toTime) {
        this.mToTime = toTime;
    }

    public String getBreakfastFrom() {
        return this.mBreakfastFrom;
    }

    public void setBreakfastFrom(String breakfastFrom) {
        this.mBreakfastFrom = breakfastFrom;
    }

    public String getBreakfastTo() {
        return this.mBreakfastTo;
    }

    public void setBreakfastTo(String breakfastTo) {
        this.mBreakfastTo = breakfastTo;
    }
}
