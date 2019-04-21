package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zzo implements Creator<PlaceLocalization> {
    static void zza(PlaceLocalization placeLocalization, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zza(parcel, 1, placeLocalization.name, false);
        zzb.zza(parcel, 2, placeLocalization.address, false);
        zzb.zza(parcel, 3, placeLocalization.zzaYe, false);
        zzb.zza(parcel, 4, placeLocalization.zzaYf, false);
        zzb.zzb(parcel, 5, placeLocalization.zzaYg, false);
        zzb.zzc(parcel, 1000, placeLocalization.versionCode);
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzfs */
    public PlaceLocalization createFromParcel(Parcel parcel) {
        List list = null;
        int zzaq = zza.zzaq(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    str4 = zza.zzq(parcel, zzap);
                    break;
                case 2:
                    str3 = zza.zzq(parcel, zzap);
                    break;
                case 3:
                    str2 = zza.zzq(parcel, zzap);
                    break;
                case 4:
                    str = zza.zzq(parcel, zzap);
                    break;
                case 5:
                    list = zza.zzE(parcel, zzap);
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
            return new PlaceLocalization(i, str4, str3, str2, str, list);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zziA */
    public PlaceLocalization[] newArray(int i) {
        return new PlaceLocalization[i];
    }
}
