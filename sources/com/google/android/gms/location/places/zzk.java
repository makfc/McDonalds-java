package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzk implements Creator<PlaceRequest> {
    static void zza(PlaceRequest placeRequest, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zza(parcel, 2, placeRequest.zzCH(), i, false);
        zzb.zza(parcel, 3, placeRequest.getInterval());
        zzb.zzc(parcel, 4, placeRequest.getPriority());
        zzb.zza(parcel, 5, placeRequest.getExpirationTime());
        zzb.zzc(parcel, 1000, placeRequest.mVersionCode);
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzfn */
    public PlaceRequest createFromParcel(Parcel parcel) {
        int zzaq = zza.zzaq(parcel);
        int i = 0;
        PlaceFilter placeFilter = null;
        long j = PlaceRequest.zzaXa;
        int i2 = 102;
        long j2 = Long.MAX_VALUE;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 2:
                    placeFilter = (PlaceFilter) zza.zza(parcel, zzap, PlaceFilter.CREATOR);
                    break;
                case 3:
                    j = zza.zzi(parcel, zzap);
                    break;
                case 4:
                    i2 = zza.zzg(parcel, zzap);
                    break;
                case 5:
                    j2 = zza.zzi(parcel, zzap);
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
            return new PlaceRequest(i, placeFilter, j, i2, j2);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zziu */
    public PlaceRequest[] newArray(int i) {
        return new PlaceRequest[i];
    }
}
