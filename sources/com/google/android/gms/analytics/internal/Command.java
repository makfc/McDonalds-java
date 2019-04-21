package com.google.android.gms.analytics.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Command implements Parcelable {
    @Deprecated
    public static final Creator<Command> CREATOR = new C20691();
    private String mValue;
    private String zzBc;
    private String zzXo;

    /* renamed from: com.google.android.gms.analytics.internal.Command$1 */
    class C20691 implements Creator<Command> {
        C20691() {
        }

        @Deprecated
        /* renamed from: zzau */
        public Command[] newArray(int i) {
            return new Command[i];
        }

        @Deprecated
        /* renamed from: zzs */
        public Command createFromParcel(Parcel parcel) {
            return new Command(parcel);
        }
    }

    @Deprecated
    Command(Parcel parcel) {
        readFromParcel(parcel);
    }

    @Deprecated
    private void readFromParcel(Parcel parcel) {
        this.zzBc = parcel.readString();
        this.zzXo = parcel.readString();
        this.mValue = parcel.readString();
    }

    @Deprecated
    public int describeContents() {
        return 0;
    }

    public String getId() {
        return this.zzBc;
    }

    public String getValue() {
        return this.mValue;
    }

    @Deprecated
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.zzBc);
        parcel.writeString(this.zzXo);
        parcel.writeString(this.mValue);
    }
}
