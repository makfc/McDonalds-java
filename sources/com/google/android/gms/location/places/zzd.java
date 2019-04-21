package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zzd implements Creator<NearbyAlertFilter> {
    static void zza(NearbyAlertFilter nearbyAlertFilter, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zzb(parcel, 1, nearbyAlertFilter.zzaWx, false);
        zzb.zza(parcel, 2, nearbyAlertFilter.zzaWy, false);
        zzb.zzc(parcel, 3, nearbyAlertFilter.zzaWz, false);
        zzb.zza(parcel, 4, nearbyAlertFilter.zzaWA, false);
        zzb.zza(parcel, 5, nearbyAlertFilter.zzaWB);
        zzb.zzc(parcel, 1000, nearbyAlertFilter.mVersionCode);
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzfh */
    public NearbyAlertFilter createFromParcel(Parcel parcel) {
        boolean z = false;
        String str = null;
        int zzaq = zza.zzaq(parcel);
        List list = null;
        List list2 = null;
        List list3 = null;
        int i = 0;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    list3 = zza.zzE(parcel, zzap);
                    break;
                case 2:
                    list2 = zza.zzD(parcel, zzap);
                    break;
                case 3:
                    list = zza.zzc(parcel, zzap, UserDataType.CREATOR);
                    break;
                case 4:
                    str = zza.zzq(parcel, zzap);
                    break;
                case 5:
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
            return new NearbyAlertFilter(i, list3, list2, list, str, z);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzin */
    public NearbyAlertFilter[] newArray(int i) {
        return new NearbyAlertFilter[i];
    }
}
