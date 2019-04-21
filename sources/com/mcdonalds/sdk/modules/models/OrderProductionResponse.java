package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.modules.AppModel;

public class OrderProductionResponse extends AppModel implements Parcelable {
    public static final Creator<OrderProductionResponse> CREATOR = new C40701();
    private String mDisplayOrderNumber;
    private String mMajor;
    private String mMdsOrderNumber;
    private String mMinor;
    private PointOfDistribution mPointOfDistribution;

    /* renamed from: com.mcdonalds.sdk.modules.models.OrderProductionResponse$1 */
    static class C40701 implements Creator<OrderProductionResponse> {
        C40701() {
        }

        public OrderProductionResponse createFromParcel(Parcel source) {
            return new OrderProductionResponse(source);
        }

        public OrderProductionResponse[] newArray(int size) {
            return new OrderProductionResponse[size];
        }
    }

    public PointOfDistribution getPointOfDistribution() {
        return this.mPointOfDistribution;
    }

    public void setPointOfDistribution(PointOfDistribution pointOfDistribution) {
        this.mPointOfDistribution = pointOfDistribution;
    }

    public String getMajor() {
        return this.mMajor;
    }

    public void setMajor(String major) {
        this.mMajor = major;
    }

    public String getMinor() {
        return this.mMinor;
    }

    public void setMinor(String minor) {
        this.mMinor = minor;
    }

    public String getDisplayOrderNumber() {
        return this.mDisplayOrderNumber;
    }

    public void setDisplayOrderNumber(String displayOrderNumber) {
        this.mDisplayOrderNumber = displayOrderNumber;
    }

    public String getMdsOrderNumber() {
        return this.mMdsOrderNumber;
    }

    public void setMdsOrderNumber(String mdsOrderNumber) {
        this.mMdsOrderNumber = mdsOrderNumber;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mPointOfDistribution == null ? -1 : this.mPointOfDistribution.ordinal());
        dest.writeString(this.mMajor);
        dest.writeString(this.mMinor);
        dest.writeString(this.mDisplayOrderNumber);
        dest.writeString(this.mMdsOrderNumber);
    }

    protected OrderProductionResponse(Parcel in) {
        int tmpMPointOfDistribution = in.readInt();
        this.mPointOfDistribution = tmpMPointOfDistribution == -1 ? null : PointOfDistribution.values()[tmpMPointOfDistribution];
        this.mMajor = in.readString();
        this.mMinor = in.readString();
        this.mDisplayOrderNumber = in.readString();
        this.mMdsOrderNumber = in.readString();
    }
}
