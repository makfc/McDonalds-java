package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzs implements Creator<PlacesParams> {
    static void zza(PlacesParams placesParams, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zza(parcel, 1, placesParams.zzaYn, false);
        zzb.zza(parcel, 2, placesParams.zzaYo, false);
        zzb.zza(parcel, 3, placesParams.zzaYp, false);
        zzb.zza(parcel, 4, placesParams.zzaXm, false);
        zzb.zzc(parcel, 6, placesParams.zzaYq);
        zzb.zzc(parcel, 7, placesParams.zzaYr);
        zzb.zzc(parcel, 1000, placesParams.versionCode);
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzft */
    public PlacesParams createFromParcel(Parcel parcel) {
        int i = 0;
        String str = null;
        int zzaq = zza.zzaq(parcel);
        int i2 = 0;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        int i3 = 0;
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
                case 6:
                    i2 = zza.zzg(parcel, zzap);
                    break;
                case 7:
                    i = zza.zzg(parcel, zzap);
                    break;
                case 1000:
                    i3 = zza.zzg(parcel, zzap);
                    break;
                default:
                    zza.zzb(parcel, zzap);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaq) {
            return new PlacesParams(i3, str4, str3, str2, str, i2, i);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zziB */
    public PlacesParams[] newArray(int i) {
        return new PlacesParams[i];
    }
}
