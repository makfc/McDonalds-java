package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.modules.AppModel;

public class OfferProductOption extends AppModel implements Parcelable {
    public static final Creator<OfferProductOption> CREATOR = new C40631();
    private String mLongName;
    private String mName;
    private String mProductCode;
    private String mShortName;

    /* renamed from: com.mcdonalds.sdk.modules.models.OfferProductOption$1 */
    static class C40631 implements Creator<OfferProductOption> {
        C40631() {
        }

        public OfferProductOption createFromParcel(Parcel source) {
            return new OfferProductOption(source);
        }

        public OfferProductOption[] newArray(int size) {
            return new OfferProductOption[size];
        }
    }

    public String getLongName() {
        return this.mLongName;
    }

    public void setLongName(String longName) {
        this.mLongName = longName;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getProductCode() {
        return this.mProductCode;
    }

    public void setProductCode(String productCode) {
        this.mProductCode = productCode;
    }

    public String getShortName() {
        return this.mShortName;
    }

    public void setShortName(String shortName) {
        this.mShortName = shortName;
    }

    public OfferProductOption clone() {
        OfferProductOption newOfferProductOption = new OfferProductOption();
        newOfferProductOption.setProductCode(getProductCode());
        newOfferProductOption.setLongName(getLongName());
        newOfferProductOption.setLongName(getName());
        newOfferProductOption.setShortName(getShortName());
        return newOfferProductOption;
    }

    public String toString() {
        return "OfferProductOption{mLongName=\"" + this.mLongName + "\", mName=\"" + this.mName + "\", mProductCode=\"" + this.mProductCode + "\", mShortName=\"" + this.mShortName + "\"}";
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mLongName);
        dest.writeString(this.mName);
        dest.writeString(this.mProductCode);
        dest.writeString(this.mShortName);
    }

    protected OfferProductOption(Parcel in) {
        this.mLongName = in.readString();
        this.mName = in.readString();
        this.mProductCode = in.readString();
        this.mShortName = in.readString();
    }
}
