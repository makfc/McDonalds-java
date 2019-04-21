package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.modules.AppModel;
import java.util.ArrayList;
import java.util.List;

public class GetAddressElementsResult extends AppModel implements Parcelable {
    public static final Creator<GetAddressElementsResult> CREATOR = new C40511();
    private List<AddressElementDependency> mAddressElementDependencies;
    private List<AddressElementType> mAddressElementTypes;
    private List<AddressElementValidationRule> mAddressElementValidationRules;

    /* renamed from: com.mcdonalds.sdk.modules.models.GetAddressElementsResult$1 */
    static class C40511 implements Creator<GetAddressElementsResult> {
        C40511() {
        }

        public GetAddressElementsResult createFromParcel(Parcel source) {
            return new GetAddressElementsResult(source);
        }

        public GetAddressElementsResult[] newArray(int size) {
            return new GetAddressElementsResult[size];
        }
    }

    public List<AddressElementType> getAddressElementTypes() {
        return this.mAddressElementTypes;
    }

    public void setAddressElementTypes(List<AddressElementType> addressElementTypes) {
        this.mAddressElementTypes = addressElementTypes;
    }

    public List<AddressElementDependency> getAddressElementDependencies() {
        return this.mAddressElementDependencies;
    }

    public void setAddressElementDependencies(List<AddressElementDependency> addressElementDependencies) {
        this.mAddressElementDependencies = addressElementDependencies;
    }

    public List<AddressElementValidationRule> getAddressElementValidationRules() {
        return this.mAddressElementValidationRules;
    }

    public void setAddressElementValidationRules(List<AddressElementValidationRule> addressElementValidationRules) {
        this.mAddressElementValidationRules = addressElementValidationRules;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.mAddressElementTypes);
        dest.writeTypedList(this.mAddressElementDependencies);
        dest.writeTypedList(this.mAddressElementValidationRules);
    }

    protected GetAddressElementsResult(Parcel in) {
        this.mAddressElementTypes = new ArrayList();
        in.readList(this.mAddressElementTypes, AddressElementType.class.getClassLoader());
        this.mAddressElementDependencies = in.createTypedArrayList(AddressElementDependency.CREATOR);
        this.mAddressElementValidationRules = in.createTypedArrayList(AddressElementValidationRule.CREATOR);
    }
}
