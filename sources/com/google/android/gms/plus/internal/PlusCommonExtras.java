package com.google.android.gms.plus.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.common.internal.zzz;

@KeepName
public class PlusCommonExtras extends AbstractSafeParcelable {
    public static final zzf CREATOR = new zzf();
    private final int mVersionCode;
    private String zzblA;
    private String zzblB;

    public PlusCommonExtras() {
        this.mVersionCode = 1;
        this.zzblA = "";
        this.zzblB = "";
    }

    PlusCommonExtras(int i, String str, String str2) {
        this.mVersionCode = i;
        this.zzblA = str;
        this.zzblB = str2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlusCommonExtras)) {
            return false;
        }
        PlusCommonExtras plusCommonExtras = (PlusCommonExtras) obj;
        return this.mVersionCode == plusCommonExtras.mVersionCode && zzz.equal(this.zzblA, plusCommonExtras.zzblA) && zzz.equal(this.zzblB, plusCommonExtras.zzblB);
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return zzz.hashCode(Integer.valueOf(this.mVersionCode), this.zzblA, this.zzblB);
    }

    public String toString() {
        return zzz.zzy(this).zzg("versionCode", Integer.valueOf(this.mVersionCode)).zzg("Gpsrc", this.zzblA).zzg("ClientCallingPackage", this.zzblB).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzf.zza(this, parcel, i);
    }

    public String zzIn() {
        return this.zzblA;
    }

    public String zzIo() {
        return this.zzblB;
    }

    public void zzL(Bundle bundle) {
        bundle.putByteArray("android.gms.plus.internal.PlusCommonExtras.extraPlusCommon", zzc.zza(this));
    }
}
