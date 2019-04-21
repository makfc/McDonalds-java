package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.modules.AppModel;

public class TimeRestriction extends AppModel implements Parcelable {
    public static final Creator<TimeRestriction> CREATOR = new C40911();
    private String mFromTime;
    private String mToTime;

    /* renamed from: com.mcdonalds.sdk.modules.models.TimeRestriction$1 */
    static class C40911 implements Creator<TimeRestriction> {
        C40911() {
        }

        public TimeRestriction createFromParcel(Parcel source) {
            return new TimeRestriction(source);
        }

        public TimeRestriction[] newArray(int size) {
            return new TimeRestriction[size];
        }
    }

    public TimeRestriction(String fromTime, String toTime) {
        this.mFromTime = fromTime;
        this.mToTime = toTime;
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

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mFromTime);
        dest.writeString(this.mToTime);
    }

    protected TimeRestriction(Parcel in) {
        this.mFromTime = in.readString();
        this.mToTime = in.readString();
    }
}
