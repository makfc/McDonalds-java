package com.mcdonalds.app.nutrition;

import com.ensighten.Ensighten;

public class DayPart {
    private String mEndTime;
    private String mIcon;
    private String mImage;
    private String mStartTime;
    private String mTitle;

    public String toString() {
        Ensighten.evaluateEvent(this, "toString", null);
        return "DayPart{mTitle=\"" + this.mTitle + "\", mImage=\"" + this.mImage + "\", mStartTime=\"" + this.mStartTime + "\", mEndTime=\"" + this.mEndTime + "\", mIcon=\"" + this.mIcon + "\"}";
    }
}
