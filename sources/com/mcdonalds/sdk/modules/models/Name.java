package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.modules.AppModel;

public class Name extends AppModel implements Parcelable {
    public static final Creator<Name> CREATOR = new C40551();
    private String mLongName;
    private String mName;
    private String mShortName;

    /* renamed from: com.mcdonalds.sdk.modules.models.Name$1 */
    static class C40551 implements Creator<Name> {
        C40551() {
        }

        public Name createFromParcel(Parcel source) {
            return new Name(source);
        }

        public Name[] newArray(int size) {
            return new Name[size];
        }
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getShortName() {
        return this.mShortName;
    }

    public void setShortName(String shortName) {
        this.mShortName = shortName;
    }

    public String getLongName() {
        return this.mLongName;
    }

    public void setLongName(String longName) {
        this.mLongName = longName;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mName);
        dest.writeString(this.mShortName);
        dest.writeString(this.mLongName);
    }

    protected Name(Parcel in) {
        this.mName = in.readString();
        this.mShortName = in.readString();
        this.mLongName = in.readString();
    }
}
