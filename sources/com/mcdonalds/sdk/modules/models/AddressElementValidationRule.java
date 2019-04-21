package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class AddressElementValidationRule implements Parcelable {
    public static final Creator<AddressElementValidationRule> CREATOR = new C40341();
    private AddressElementType mAddressElementType;
    private int mDisplayOrder;
    private int mValidationLength;
    private String mValidationRegex;
    private int mValidationType;

    /* renamed from: com.mcdonalds.sdk.modules.models.AddressElementValidationRule$1 */
    static class C40341 implements Creator<AddressElementValidationRule> {
        C40341() {
        }

        public AddressElementValidationRule createFromParcel(Parcel source) {
            return new AddressElementValidationRule(source);
        }

        public AddressElementValidationRule[] newArray(int size) {
            return new AddressElementValidationRule[size];
        }
    }

    public AddressElementType getAddressElementType() {
        return this.mAddressElementType;
    }

    public void setAddressElementType(AddressElementType addressElementType) {
        this.mAddressElementType = addressElementType;
    }

    public int getDisplayOrder() {
        return this.mDisplayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.mDisplayOrder = displayOrder;
    }

    public int getValidationType() {
        return this.mValidationType;
    }

    public void setValidationType(int validationType) {
        this.mValidationType = validationType;
    }

    public int getValidationLength() {
        return this.mValidationLength;
    }

    public void setValidationLength(int validationLength) {
        this.mValidationLength = validationLength;
    }

    public String getValidationRegex() {
        return this.mValidationRegex;
    }

    public void setValidationRegex(String validationRegex) {
        this.mValidationRegex = validationRegex;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mAddressElementType == null ? -1 : this.mAddressElementType.ordinal());
        dest.writeInt(this.mDisplayOrder);
        dest.writeInt(this.mValidationType);
        dest.writeInt(this.mValidationLength);
        dest.writeString(this.mValidationRegex);
    }

    protected AddressElementValidationRule(Parcel in) {
        int tmpMAddressElementType = in.readInt();
        this.mAddressElementType = tmpMAddressElementType == -1 ? null : AddressElementType.values()[tmpMAddressElementType];
        this.mDisplayOrder = in.readInt();
        this.mValidationType = in.readInt();
        this.mValidationLength = in.readInt();
        this.mValidationRegex = in.readString();
    }
}
