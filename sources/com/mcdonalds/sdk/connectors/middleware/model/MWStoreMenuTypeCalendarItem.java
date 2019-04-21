package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.storelocator.MenuTypeCalendarItem;
import java.io.Serializable;

public class MWStoreMenuTypeCalendarItem implements Serializable {
    @SerializedName("EndTime")
    public String endTime;
    @SerializedName("MenuTypeID")
    public int menuTypeID;
    @SerializedName("StartTime")
    public String startTime;
    @SerializedName("Weekday")
    public int weekday;

    public MenuTypeCalendarItem toStoreMenuTypeCalendarItem() {
        MenuTypeCalendarItem menuTypeCalendarItem = new MenuTypeCalendarItem();
        menuTypeCalendarItem.setFromTime(this.startTime);
        menuTypeCalendarItem.setToTime(this.endTime);
        menuTypeCalendarItem.setMenuTypeId(this.menuTypeID);
        menuTypeCalendarItem.setWeekDay(this.weekday);
        return menuTypeCalendarItem;
    }
}
