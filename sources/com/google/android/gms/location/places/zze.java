package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zze implements Creator<NearbyAlertRequest> {
    static void zza(NearbyAlertRequest nearbyAlertRequest, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zzc(parcel, 1, nearbyAlertRequest.zzCC());
        zzb.zzc(parcel, 2, nearbyAlertRequest.zzCG());
        zzb.zza(parcel, 3, nearbyAlertRequest.zzCH(), i, false);
        zzb.zza(parcel, 4, nearbyAlertRequest.zzCI(), i, false);
        zzb.zza(parcel, 5, nearbyAlertRequest.zzCJ());
        zzb.zzc(parcel, 6, nearbyAlertRequest.zzCK());
        zzb.zzc(parcel, 7, nearbyAlertRequest.getPriority());
        zzb.zzc(parcel, 1000, nearbyAlertRequest.getVersionCode());
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzfi */
    public NearbyAlertRequest createFromParcel(Parcel parcel) {
        NearbyAlertFilter nearbyAlertFilter = null;
        int i = 0;
        int zzaq = zza.zzaq(parcel);
        int i2 = -1;
        int i3 = 110;
        boolean z = false;
        PlaceFilter placeFilter = null;
        int i4 = 0;
        int i5 = 0;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    i4 = zza.zzg(parcel, zzap);
                    break;
                case 2:
                    i2 = zza.zzg(parcel, zzap);
                    break;
                case 3:
                    placeFilter = (PlaceFilter) zza.zza(parcel, zzap, PlaceFilter.CREATOR);
                    break;
                case 4:
                    nearbyAlertFilter = (NearbyAlertFilter) zza.zza(parcel, zzap, NearbyAlertFilter.CREATOR);
                    break;
                case 5:
                    z = zza.zzc(parcel, zzap);
                    break;
                case 6:
                    i = zza.zzg(parcel, zzap);
                    break;
                case 7:
                    i3 = zza.zzg(parcel, zzap);
                    break;
                case 1000:
                    i5 = zza.zzg(parcel, zzap);
                    break;
                default:
                    zza.zzb(parcel, zzap);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaq) {
            return new NearbyAlertRequest(i5, i4, i2, placeFilter, nearbyAlertFilter, z, i, i3);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzio */
    public NearbyAlertRequest[] newArray(int i) {
        return new NearbyAlertRequest[i];
    }
}
