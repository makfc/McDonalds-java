package com.google.android.gms.location;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zzf implements Creator<LocationResult> {
    static void zza(LocationResult locationResult, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zzc(parcel, 1, locationResult.getLocations(), false);
        zzb.zzc(parcel, 1000, locationResult.getVersionCode());
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzeW */
    public LocationResult createFromParcel(Parcel parcel) {
        int zzaq = zza.zzaq(parcel);
        int i = 0;
        List list = LocationResult.zzaVt;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    list = zza.zzc(parcel, zzap, Location.CREATOR);
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
            return new LocationResult(i, list);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzhX */
    public LocationResult[] newArray(int i) {
        return new LocationResult[i];
    }
}
