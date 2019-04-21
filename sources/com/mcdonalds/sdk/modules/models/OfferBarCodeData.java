package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class OfferBarCodeData implements Parcelable, Serializable {
    public static final Creator<OfferBarCodeData> CREATOR = new C40601();
    private String mBarCodeContent;
    private String mQrCode;
    private String mRandomCode;

    /* renamed from: com.mcdonalds.sdk.modules.models.OfferBarCodeData$1 */
    static class C40601 implements Creator<OfferBarCodeData> {
        C40601() {
        }

        public OfferBarCodeData createFromParcel(Parcel source) {
            return new OfferBarCodeData(source);
        }

        public OfferBarCodeData[] newArray(int size) {
            return new OfferBarCodeData[size];
        }
    }

    public String getQrCode() {
        return this.mQrCode;
    }

    public void setQrCode(String qrCode) {
        this.mQrCode = qrCode;
    }

    public String getRandomCode() {
        return this.mRandomCode;
    }

    public void setRandomCode(String randomCode) {
        this.mRandomCode = randomCode;
    }

    public String getBarCodeContent() {
        return this.mBarCodeContent;
    }

    public void setBarCodeContent(String barCodeContent) {
        this.mBarCodeContent = barCodeContent;
    }

    public String toString() {
        return "OfferBarCodeData{mQrCode=\"" + this.mQrCode + "\", mRandomCode=\"" + this.mRandomCode + "\", mBarCodeContent=\"" + this.mBarCodeContent + "\"}";
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mQrCode);
        dest.writeString(this.mRandomCode);
        dest.writeString(this.mBarCodeContent);
    }

    protected OfferBarCodeData(Parcel in) {
        this.mQrCode = in.readString();
        this.mRandomCode = in.readString();
        this.mBarCodeContent = in.readString();
    }
}
