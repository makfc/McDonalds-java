package com.google.android.gms.location.places;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.util.zzf;
import java.util.Set;

public final class UserDataType extends AbstractSafeParcelable {
    public static final zzm CREATOR = new zzm();
    public static final UserDataType zzaXo = zzw("test_type", 1);
    public static final UserDataType zzaXp = zzw("labeled_place", 6);
    public static final UserDataType zzaXq = zzw("here_content", 7);
    public static final Set<UserDataType> zzaXr = zzf.zza(zzaXo, zzaXp, zzaXq);
    final int mVersionCode;
    final String zzNR;
    final int zzaXs;

    UserDataType(int i, String str, int i2) {
        zzaa.zzdl(str);
        this.mVersionCode = i;
        this.zzNR = str;
        this.zzaXs = i2;
    }

    private static UserDataType zzw(String str, int i) {
        return new UserDataType(0, str, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UserDataType)) {
            return false;
        }
        UserDataType userDataType = (UserDataType) obj;
        return this.zzNR.equals(userDataType.zzNR) && this.zzaXs == userDataType.zzaXs;
    }

    public int hashCode() {
        return this.zzNR.hashCode();
    }

    public String toString() {
        return this.zzNR;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzm.zza(this, parcel, i);
    }
}
