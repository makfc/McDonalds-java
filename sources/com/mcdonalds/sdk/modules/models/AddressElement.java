package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.modules.AppModel;
import java.util.List;

public class AddressElement extends AppModel implements Parcelable {
    public static final Creator<AddressElement> CREATOR = new C40321();
    private AddressElementType mAddressElementType;
    private List<AddressAliasValue> mValue;

    /* renamed from: com.mcdonalds.sdk.modules.models.AddressElement$1 */
    static class C40321 implements Creator<AddressElement> {
        C40321() {
        }

        public AddressElement createFromParcel(Parcel source) {
            return new AddressElement(source);
        }

        public AddressElement[] newArray(int size) {
            return new AddressElement[size];
        }
    }

    public AddressElementType getAddressElementType() {
        return this.mAddressElementType;
    }

    public void setAddressElementType(AddressElementType addressElementType) {
        this.mAddressElementType = addressElementType;
    }

    public List<AddressAliasValue> getValue() {
        return this.mValue;
    }

    public void setValue(List<AddressAliasValue> value) {
        this.mValue = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mAddressElementType == null ? -1 : this.mAddressElementType.ordinal());
        dest.writeTypedList(this.mValue);
    }

    protected AddressElement(Parcel in) {
        int tmpMAddressElementType = in.readInt();
        this.mAddressElementType = tmpMAddressElementType == -1 ? null : AddressElementType.values()[tmpMAddressElementType];
        this.mValue = in.createTypedArrayList(AddressAliasValue.CREATOR);
    }
}
