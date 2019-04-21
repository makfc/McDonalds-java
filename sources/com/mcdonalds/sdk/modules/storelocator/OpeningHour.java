package com.mcdonalds.sdk.modules.storelocator;

@Deprecated
public class OpeningHour {
    private String mBreakfastFrom;
    private String mBreakfastTo;
    private Integer mDayOfTheWeek;
    private String mFromTime;
    private String mToTime;

    public Integer getDayOfTheWeek() {
        return this.mDayOfTheWeek;
    }

    public void setDayOfTheWeek(Integer mDayOfTheWeek) {
        this.mDayOfTheWeek = mDayOfTheWeek;
    }

    public String getFromTime() {
        return this.mFromTime;
    }

    public void setFromTime(String mFromTime) {
        this.mFromTime = mFromTime;
    }

    public String getToTime() {
        return this.mToTime;
    }

    public void setToTime(String mToTime) {
        this.mToTime = mToTime;
    }

    public String getBreakfastFrom() {
        return this.mBreakfastFrom;
    }

    public void setBreakfastFrom(String mBreakfastFrom) {
        this.mBreakfastFrom = mBreakfastFrom;
    }

    public String getBreakfastTo() {
        return this.mBreakfastTo;
    }

    public void setBreakfastTo(String mBreakfastTo) {
        this.mBreakfastTo = mBreakfastTo;
    }
}
