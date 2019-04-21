package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.modules.AppModel;
import java.util.List;

public class AddressValidationResult extends AppModel implements Parcelable {
    public static final Creator<AddressValidationResult> CREATOR = new C40351();
    private List<AddressElementValidationRule> mInvalidAddressElements;
    private boolean mIsAddressValid;
    private int mResultCode;

    /* renamed from: com.mcdonalds.sdk.modules.models.AddressValidationResult$1 */
    static class C40351 implements Creator<AddressValidationResult> {
        C40351() {
        }

        public AddressValidationResult createFromParcel(Parcel source) {
            return new AddressValidationResult(source);
        }

        public AddressValidationResult[] newArray(int size) {
            return new AddressValidationResult[size];
        }
    }

    public int getResultCode() {
        return this.mResultCode;
    }

    public void setResultCode(int resultCode) {
        this.mResultCode = resultCode;
    }

    public boolean isAddressValid() {
        return this.mIsAddressValid;
    }

    public void setAddressValid(boolean isAddressValid) {
        this.mIsAddressValid = isAddressValid;
    }

    public List<AddressElementValidationRule> getInvalidAddressElements() {
        return this.mInvalidAddressElements;
    }

    public void setInvalidAddressElements(List<AddressElementValidationRule> invalidAddressElements) {
        this.mInvalidAddressElements = invalidAddressElements;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mResultCode);
        dest.writeByte(this.mIsAddressValid ? (byte) 1 : (byte) 0);
        dest.writeTypedList(this.mInvalidAddressElements);
    }

    protected AddressValidationResult(Parcel in) {
        this.mResultCode = in.readInt();
        this.mIsAddressValid = in.readByte() != (byte) 0;
        this.mInvalidAddressElements = in.createTypedArrayList(AddressElementValidationRule.CREATOR);
    }
}
