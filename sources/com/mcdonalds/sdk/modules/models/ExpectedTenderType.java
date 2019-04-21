package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.SerializedName;

public class ExpectedTenderType implements Parcelable {
    public static final Creator<ExpectedTenderType> CREATOR = new C25711();
    @SerializedName("paymentMethodId")
    public int paymentMethodId;
    @SerializedName("tenderType")
    public int tenderType;

    /* renamed from: com.mcdonalds.sdk.modules.models.ExpectedTenderType$1 */
    static class C25711 implements Creator<ExpectedTenderType> {
        C25711() {
        }

        public ExpectedTenderType createFromParcel(Parcel source) {
            return new ExpectedTenderType(source);
        }

        public ExpectedTenderType[] newArray(int size) {
            return new ExpectedTenderType[size];
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.paymentMethodId);
        dest.writeInt(this.tenderType);
    }

    protected ExpectedTenderType(Parcel in) {
        this.paymentMethodId = in.readInt();
        this.tenderType = in.readInt();
    }
}
