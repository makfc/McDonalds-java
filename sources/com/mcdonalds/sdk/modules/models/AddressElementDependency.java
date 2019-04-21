package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.modules.AppModel;
import java.util.ArrayList;
import java.util.List;

public class AddressElementDependency extends AppModel implements Parcelable {
    public static final Creator<AddressElementDependency> CREATOR = new C40331();
    private List<AddressElementType> mAddressElementCanFilteredBy;
    private AddressElementType mAddressElementType;
    private List<AddressElementType> mAddressToBeCleared;

    /* renamed from: com.mcdonalds.sdk.modules.models.AddressElementDependency$1 */
    static class C40331 implements Creator<AddressElementDependency> {
        C40331() {
        }

        public AddressElementDependency createFromParcel(Parcel source) {
            return new AddressElementDependency(source);
        }

        public AddressElementDependency[] newArray(int size) {
            return new AddressElementDependency[size];
        }
    }

    public AddressElementType getAddressElementType() {
        return this.mAddressElementType;
    }

    public void setAddressElementType(AddressElementType addressElementType) {
        this.mAddressElementType = addressElementType;
    }

    public List<AddressElementType> getAddressElementCanFilteredBy() {
        return this.mAddressElementCanFilteredBy;
    }

    public void setAddressElementCanFilteredBy(List<AddressElementType> addressElementCanFilteredBy) {
        this.mAddressElementCanFilteredBy = addressElementCanFilteredBy;
    }

    public List<AddressElementType> getAddressToBeCleared() {
        return this.mAddressToBeCleared;
    }

    public void setAddressToBeCleared(List<AddressElementType> addressToBeCleared) {
        this.mAddressToBeCleared = addressToBeCleared;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mAddressElementType == null ? -1 : this.mAddressElementType.ordinal());
        dest.writeList(this.mAddressElementCanFilteredBy);
        dest.writeList(this.mAddressToBeCleared);
    }

    protected AddressElementDependency(Parcel in) {
        int tmpMAddressElementType = in.readInt();
        this.mAddressElementType = tmpMAddressElementType == -1 ? null : AddressElementType.values()[tmpMAddressElementType];
        this.mAddressElementCanFilteredBy = new ArrayList();
        in.readList(this.mAddressElementCanFilteredBy, AddressElementType.class.getClassLoader());
        this.mAddressToBeCleared = new ArrayList();
        in.readList(this.mAddressToBeCleared, AddressElementType.class.getClassLoader());
    }
}
