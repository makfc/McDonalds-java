package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzm implements Creator<UserDataType> {
    static void zza(UserDataType userDataType, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zza(parcel, 1, userDataType.zzNR, false);
        zzb.zzc(parcel, 2, userDataType.zzaXs);
        zzb.zzc(parcel, 1000, userDataType.mVersionCode);
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzfo */
    public UserDataType createFromParcel(Parcel parcel) {
        int i = 0;
        int zzaq = zza.zzaq(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    str = zza.zzq(parcel, zzap);
                    break;
                case 2:
                    i = zza.zzg(parcel, zzap);
                    break;
                case 1000:
                    i2 = zza.zzg(parcel, zzap);
                    break;
                default:
                    zza.zzb(parcel, zzap);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaq) {
            return new UserDataType(i2, str, i);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zziv */
    public UserDataType[] newArray(int i) {
        return new UserDataType[i];
    }
}
