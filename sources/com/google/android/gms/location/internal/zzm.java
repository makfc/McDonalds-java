package com.google.android.gms.location.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.location.LocationRequest;
import java.util.List;

public class zzm implements Creator<LocationRequestInternal> {
    static void zza(LocationRequestInternal locationRequestInternal, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zza(parcel, 1, locationRequestInternal.zzaIc, i, false);
        zzb.zza(parcel, 4, locationRequestInternal.zzaUF);
        zzb.zzc(parcel, 5, locationRequestInternal.zzaWf, false);
        zzb.zza(parcel, 6, locationRequestInternal.mTag, false);
        zzb.zza(parcel, 7, locationRequestInternal.zzaWg);
        zzb.zzc(parcel, 1000, locationRequestInternal.getVersionCode());
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzfc */
    public LocationRequestInternal createFromParcel(Parcel parcel) {
        String str = null;
        boolean z = false;
        int zzaq = zza.zzaq(parcel);
        boolean z2 = true;
        List list = LocationRequestInternal.zzaWe;
        LocationRequest locationRequest = null;
        int i = 0;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    locationRequest = (LocationRequest) zza.zza(parcel, zzap, LocationRequest.CREATOR);
                    break;
                case 4:
                    z2 = zza.zzc(parcel, zzap);
                    break;
                case 5:
                    list = zza.zzc(parcel, zzap, ClientIdentity.CREATOR);
                    break;
                case 6:
                    str = zza.zzq(parcel, zzap);
                    break;
                case 7:
                    z = zza.zzc(parcel, zzap);
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
            return new LocationRequestInternal(i, locationRequest, z2, list, str, z);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzig */
    public LocationRequestInternal[] newArray(int i) {
        return new LocationRequestInternal[i];
    }
}
