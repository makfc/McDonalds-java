package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class OfferCategory implements Parcelable {
    public static final Creator<OfferCategory> CREATOR = new C40611();
    private String mDescription;
    private Integer mId;

    /* renamed from: com.mcdonalds.sdk.modules.models.OfferCategory$1 */
    static class C40611 implements Creator<OfferCategory> {
        C40611() {
        }

        public OfferCategory createFromParcel(Parcel source) {
            return new OfferCategory(source);
        }

        public OfferCategory[] newArray(int size) {
            return new OfferCategory[size];
        }
    }

    public String getDescription() {
        return this.mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public Integer getId() {
        return this.mId;
    }

    public void setId(Integer mId) {
        this.mId = mId;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mDescription);
        dest.writeValue(this.mId);
    }

    protected OfferCategory(Parcel in) {
        this.mDescription = in.readString();
        this.mId = (Integer) in.readValue(Integer.class.getClassLoader());
    }
}
