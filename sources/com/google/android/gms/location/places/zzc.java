package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zzc implements Creator<AutocompleteFilter> {
    static void zza(AutocompleteFilter autocompleteFilter, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zza(parcel, 1, autocompleteFilter.zzaWu);
        zzb.zza(parcel, 2, autocompleteFilter.zzaWv, false);
        zzb.zzc(parcel, 1000, autocompleteFilter.mVersionCode);
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzfg */
    public AutocompleteFilter createFromParcel(Parcel parcel) {
        boolean z = false;
        int zzaq = zza.zzaq(parcel);
        List list = null;
        int i = 0;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    z = zza.zzc(parcel, zzap);
                    break;
                case 2:
                    list = zza.zzD(parcel, zzap);
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
            return new AutocompleteFilter(i, z, list);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzim */
    public AutocompleteFilter[] newArray(int i) {
        return new AutocompleteFilter[i];
    }
}
