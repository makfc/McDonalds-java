package com.mcdonalds.sdk.modules.storelocator;

import java.io.Serializable;

public class MenuTypeCalendarItem implements Serializable {
    private String mFromTime;
    private int mMenuTypeId;
    private String mToTime;
    private int mWeekDay;

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

    public int getWeekDay() {
        return this.mWeekDay;
    }

    public void setWeekDay(int mWeekDay) {
        this.mWeekDay = mWeekDay;
    }

    public int getMenuTypeId() {
        return this.mMenuTypeId;
    }

    public void setMenuTypeId(int mMenuTypeId) {
        this.mMenuTypeId = mMenuTypeId;
    }
}
