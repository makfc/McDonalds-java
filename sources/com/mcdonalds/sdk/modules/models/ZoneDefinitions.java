package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.modules.AppModel;

public class ZoneDefinitions extends AppModel implements Parcelable {
    public static final Creator<ZoneDefinitions> CREATOR = new C40921();
    private String mColor;
    private String mDefinition;
    private boolean mEnable;
    private String mIds;
    private int mZoneId;

    /* renamed from: com.mcdonalds.sdk.modules.models.ZoneDefinitions$1 */
    static class C40921 implements Creator<ZoneDefinitions> {
        C40921() {
        }

        public ZoneDefinitions createFromParcel(Parcel in) {
            return new ZoneDefinitions(in);
        }

        public ZoneDefinitions[] newArray(int size) {
            return new ZoneDefinitions[size];
        }
    }

    public String getIds() {
        return this.mIds;
    }

    public void setIds(String ids) {
        this.mIds = ids;
    }

    public int getZoneId() {
        return this.mZoneId;
    }

    public void setZoneId(int zoneId) {
        this.mZoneId = zoneId;
    }

    public boolean isEnable() {
        return this.mEnable;
    }

    public void setEnable(boolean enable) {
        this.mEnable = enable;
    }

    public String getDefinition() {
        return this.mDefinition;
    }

    public void setDefinition(String definition) {
        this.mDefinition = definition;
    }

    public String getColor() {
        return this.mColor;
    }

    public void setColor(String color) {
        this.mColor = color;
    }

    protected ZoneDefinitions(Parcel in) {
        this.mColor = in.readString();
        this.mDefinition = in.readString();
        this.mEnable = in.readByte() != (byte) 0;
        this.mZoneId = in.readInt();
        this.mIds = in.readString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mColor);
        dest.writeString(this.mDefinition);
        dest.writeByte(this.mEnable ? (byte) 1 : (byte) 0);
        dest.writeInt(this.mZoneId);
        dest.writeString(this.mIds);
    }

    public int describeContents() {
        return 0;
    }
}
