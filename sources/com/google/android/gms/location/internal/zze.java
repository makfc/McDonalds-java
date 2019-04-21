package com.google.android.gms.location.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zze implements Creator<FusedLocationProviderResult> {
    static void zza(FusedLocationProviderResult fusedLocationProviderResult, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zza(parcel, 1, fusedLocationProviderResult.getStatus(), i, false);
        zzb.zzc(parcel, 1000, fusedLocationProviderResult.getVersionCode());
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzfb */
    public FusedLocationProviderResult createFromParcel(Parcel parcel) {
        int zzaq = zza.zzaq(parcel);
        int i = 0;
        Status status = null;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    status = (Status) zza.zza(parcel, zzap, Status.CREATOR);
                    break;
                case 1000:
                    i = zza.zzg(parcel, zzap);
                    break;
                default:
                    zza.zzb(parcel, zzap);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaq) {
            return new FusedLocationProviderResult(i, status);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzie */
    public FusedLocationProviderResult[] newArray(int i) {
        return new FusedLocationProviderResult[i];
    }
}
