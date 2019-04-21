package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.storelocator.MenuTypeCalendarItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MWOpeningHourItem implements Serializable {
    @SerializedName("BreakfastFrom")
    public String breakfastFrom;
    @SerializedName("BreakfastTo")
    public String breakfastTo;
    @SerializedName("DayOfTheWeek")
    public int dayOfTheWeek;
    @SerializedName("FromTime")
    public String fromTime;
    @SerializedName("ToTime")
    public String toTime;

    public List<MenuTypeCalendarItem> toStoreMenuTypeCalendarItem() {
        ArrayList<MenuTypeCalendarItem> items = new ArrayList();
        MenuTypeCalendarItem lunchMenuTypeCalendarItem = new MenuTypeCalendarItem();
        if (this.breakfastTo == null || this.breakfastTo.equals(this.toTime)) {
            lunchMenuTypeCalendarItem.setFromTime(this.fromTime);
        } else {
            lunchMenuTypeCalendarItem.setFromTime(this.breakfastTo);
        }
        if (this.fromTime.equals("00:00") && this.toTime.equals("23:59") && this.breakfastFrom != null) {
            lunchMenuTypeCalendarItem.setToTime(this.breakfastFrom);
        } else {
            lunchMenuTypeCalendarItem.setToTime(this.toTime);
        }
        lunchMenuTypeCalendarItem.setWeekDay(this.dayOfTheWeek);
        lunchMenuTypeCalendarItem.setMenuTypeId(1);
        items.add(lunchMenuTypeCalendarItem);
        MenuTypeCalendarItem breakFastMenuTypeCalendarItem = new MenuTypeCalendarItem();
        breakFastMenuTypeCalendarItem.setFromTime(this.breakfastFrom);
        if (this.breakfastTo != null) {
            breakFastMenuTypeCalendarItem.setToTime(this.breakfastTo);
        } else {
            breakFastMenuTypeCalendarItem.setToTime(this.toTime);
        }
        breakFastMenuTypeCalendarItem.setWeekDay(this.dayOfTheWeek);
        breakFastMenuTypeCalendarItem.setMenuTypeId(0);
        items.add(breakFastMenuTypeCalendarItem);
        return items;
    }
}
