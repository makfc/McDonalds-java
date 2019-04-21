package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zzg implements Creator<PlaceFilter> {
    static void zza(PlaceFilter placeFilter, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zza(parcel, 1, placeFilter.zzaWy, false);
        zzb.zza(parcel, 3, placeFilter.zzaWO);
        zzb.zzc(parcel, 4, placeFilter.zzaWz, false);
        zzb.zzb(parcel, 6, placeFilter.zzaWx, false);
        zzb.zzc(parcel, 1000, placeFilter.mVersionCode);
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzfj */
    public PlaceFilter createFromParcel(Parcel parcel) {
        boolean z = false;
        List list = null;
        int zzaq = zza.zzaq(parcel);
        List list2 = null;
        List list3 = null;
        int i = 0;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    list3 = zza.zzD(parcel, zzap);
                    break;
                case 3:
                    z = zza.zzc(parcel, zzap);
                    break;
                case 4:
                    list = zza.zzc(parcel, zzap, UserDataType.CREATOR);
                    break;
                case 6:
                    list2 = zza.zzE(parcel, zzap);
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
            return new PlaceFilter(i, list3, z, list2, list);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzip */
    public PlaceFilter[] newArray(int i) {
        return new PlaceFilter[i];
    }
}
