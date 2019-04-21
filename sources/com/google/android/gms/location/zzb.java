package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.location.internal.ParcelableGeofence;
import java.util.List;

public class zzb implements Creator<GeofencingRequest> {
    static void zza(GeofencingRequest geofencingRequest, Parcel parcel, int i) {
        int zzar = com.google.android.gms.common.internal.safeparcel.zzb.zzar(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, geofencingRequest.zzCq(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 2, geofencingRequest.getInitialTrigger());
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1000, geofencingRequest.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzeU */
    public GeofencingRequest createFromParcel(Parcel parcel) {
        int i = 0;
        int zzaq = zza.zzaq(parcel);
        List list = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    list = zza.zzc(parcel, zzap, ParcelableGeofence.CREATOR);
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
            return new GeofencingRequest(i2, list, i);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzhT */
    public GeofencingRequest[] newArray(int i) {
        return new GeofencingRequest[i];
    }
}
