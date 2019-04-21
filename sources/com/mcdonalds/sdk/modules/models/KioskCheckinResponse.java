package com.mcdonalds.sdk.modules.models;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class KioskCheckinResponse implements Parcelable {
    public static final Creator<KioskCheckinResponse> CREATOR = new C40531();
    private final Bitmap mBarcode;
    private final String mRandomCode;

    /* renamed from: com.mcdonalds.sdk.modules.models.KioskCheckinResponse$1 */
    static class C40531 implements Creator<KioskCheckinResponse> {
        C40531() {
        }

        public KioskCheckinResponse createFromParcel(Parcel source) {
            return new KioskCheckinResponse(source);
        }

        public KioskCheckinResponse[] newArray(int size) {
            return new KioskCheckinResponse[size];
        }
    }

    public KioskCheckinResponse(Bitmap barcode, String randomCode) {
        this.mBarcode = barcode;
        this.mRandomCode = randomCode;
    }

    public Bitmap getBarcode() {
        return this.mBarcode;
    }

    public String getRandomCode() {
        return this.mRandomCode;
    }

    public String toString() {
        return "KioskCheckinResponse{mBarcode=\"" + this.mBarcode + "\"}";
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mBarcode, 0);
        dest.writeString(this.mRandomCode);
    }

    protected KioskCheckinResponse(Parcel in) {
        this.mBarcode = (Bitmap) in.readParcelable(Bitmap.class.getClassLoader());
        this.mRandomCode = in.readString();
    }
}
