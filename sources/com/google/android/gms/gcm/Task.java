package com.google.android.gms.gcm;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public abstract class Task implements Parcelable {
    private final Bundle mExtras;
    private final String mTag;
    private final String zzaTr;
    private final boolean zzaTs;
    private final boolean zzaTt;
    private final int zzaTu;
    private final boolean zzaTv;
    private final zzc zzaTw;

    public static abstract class Builder {
        protected boolean isPersisted;
        protected zzc zzaTx = zzc.zzaTm;
    }

    @Deprecated
    Task(Parcel parcel) {
        boolean z = true;
        Log.e("Task", "Constructing a Task object using a parcel.");
        this.zzaTr = parcel.readString();
        this.mTag = parcel.readString();
        this.zzaTs = parcel.readInt() == 1;
        if (parcel.readInt() != 1) {
            z = false;
        }
        this.zzaTt = z;
        this.zzaTu = 2;
        this.zzaTv = false;
        this.zzaTw = zzc.zzaTm;
        this.mExtras = null;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = 1;
        parcel.writeString(this.zzaTr);
        parcel.writeString(this.mTag);
        parcel.writeInt(this.zzaTs ? 1 : 0);
        if (!this.zzaTt) {
            i2 = 0;
        }
        parcel.writeInt(i2);
    }
}
