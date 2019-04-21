package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class LocationRequestCreator implements Creator<LocationRequest> {
    static void zza(LocationRequest locationRequest, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zzc(parcel, 1, locationRequest.mPriority);
        zzb.zza(parcel, 2, locationRequest.zzaVo);
        zzb.zza(parcel, 3, locationRequest.zzaVp);
        zzb.zza(parcel, 4, locationRequest.zzaIe);
        zzb.zza(parcel, 5, locationRequest.zzaUT);
        zzb.zzc(parcel, 6, locationRequest.zzaVq);
        zzb.zza(parcel, 7, locationRequest.zzaVr);
        zzb.zzc(parcel, 1000, locationRequest.getVersionCode());
        zzb.zza(parcel, 8, locationRequest.zzaVs);
        zzb.zzJ(parcel, zzar);
    }

    public LocationRequest createFromParcel(Parcel parcel) {
        int zzaq = zza.zzaq(parcel);
        int i = 0;
        int i2 = 102;
        long j = 3600000;
        long j2 = 600000;
        boolean z = false;
        long j3 = Long.MAX_VALUE;
        int i3 = Integer.MAX_VALUE;
        float f = 0.0f;
        long j4 = 0;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    i2 = zza.zzg(parcel, zzap);
                    break;
                case 2:
                    j = zza.zzi(parcel, zzap);
                    break;
                case 3:
                    j2 = zza.zzi(parcel, zzap);
                    break;
                case 4:
                    z = zza.zzc(parcel, zzap);
                    break;
                case 5:
                    j3 = zza.zzi(parcel, zzap);
                    break;
                case 6:
                    i3 = zza.zzg(parcel, zzap);
                    break;
                case 7:
                    f = zza.zzl(parcel, zzap);
                    break;
                case 8:
                    j4 = zza.zzi(parcel, zzap);
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
            return new LocationRequest(i, i2, j, j2, z, j3, i3, f, j4);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    public LocationRequest[] newArray(int i) {
        return new LocationRequest[i];
    }
}
