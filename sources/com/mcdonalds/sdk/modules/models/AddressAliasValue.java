package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.modules.AppModel;

public class AddressAliasValue extends AppModel implements Parcelable {
    public static final Creator<AddressAliasValue> CREATOR = new C40311();
    private String mAlias;
    private AddressAliasType mAliasType;

    /* renamed from: com.mcdonalds.sdk.modules.models.AddressAliasValue$1 */
    static class C40311 implements Creator<AddressAliasValue> {
        C40311() {
        }

        public AddressAliasValue createFromParcel(Parcel source) {
            return new AddressAliasValue(source);
        }

        public AddressAliasValue[] newArray(int size) {
            return new AddressAliasValue[size];
        }
    }

    public AddressAliasType getAliasType() {
        return this.mAliasType;
    }

    public void setAliasType(AddressAliasType aliasType) {
        this.mAliasType = aliasType;
    }

    public String getAlias() {
        return this.mAlias;
    }

    public void setAlias(String alias) {
        this.mAlias = alias;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mAliasType == null ? -1 : this.mAliasType.ordinal());
        dest.writeString(this.mAlias);
    }

    protected AddressAliasValue(Parcel in) {
        int tmpMAliasType = in.readInt();
        this.mAliasType = tmpMAliasType == -1 ? null : AddressAliasType.values()[tmpMAliasType];
        this.mAlias = in.readString();
    }
}
