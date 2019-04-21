package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class LocationAvailabilityCreator implements Creator<LocationAvailability> {
    static void zza(LocationAvailability locationAvailability, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zzc(parcel, 1, locationAvailability.zzaVk);
        zzb.zzc(parcel, 2, locationAvailability.zzaVl);
        zzb.zza(parcel, 3, locationAvailability.zzaVm);
        zzb.zzc(parcel, 4, locationAvailability.zzaVn);
        zzb.zzc(parcel, 1000, locationAvailability.getVersionCode());
        zzb.zzJ(parcel, zzar);
    }

    public LocationAvailability createFromParcel(Parcel parcel) {
        int i = 1;
        int zzaq = zza.zzaq(parcel);
        int i2 = 0;
        int i3 = 1000;
        long j = 0;
        int i4 = 1;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    i4 = zza.zzg(parcel, zzap);
                    break;
                case 2:
                    i = zza.zzg(parcel, zzap);
                    break;
                case 3:
                    j = zza.zzi(parcel, zzap);
                    break;
                case 4:
                    i3 = zza.zzg(parcel, zzap);
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
            return new LocationAvailability(i2, i3, i4, i, j);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    public LocationAvailability[] newArray(int i) {
        return new LocationAvailability[i];
    }
}
