package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.location.places.internal.AutocompletePredictionEntity.SubstringEntity;
import java.util.List;

public class zza implements Creator<AutocompletePredictionEntity> {
    static void zza(AutocompletePredictionEntity autocompletePredictionEntity, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zza(parcel, 1, autocompletePredictionEntity.zzaXu, false);
        zzb.zza(parcel, 2, autocompletePredictionEntity.zzaWV, false);
        zzb.zza(parcel, 3, autocompletePredictionEntity.zzaWr, false);
        zzb.zzc(parcel, 4, autocompletePredictionEntity.zzaXv, false);
        zzb.zzc(parcel, 5, autocompletePredictionEntity.zzaXw);
        zzb.zza(parcel, 6, autocompletePredictionEntity.zzaXx, false);
        zzb.zzc(parcel, 7, autocompletePredictionEntity.zzaXy, false);
        zzb.zzc(parcel, 1000, autocompletePredictionEntity.mVersionCode);
        zzb.zza(parcel, 8, autocompletePredictionEntity.zzaXz, false);
        zzb.zzc(parcel, 9, autocompletePredictionEntity.zzaXA, false);
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzfp */
    public AutocompletePredictionEntity createFromParcel(Parcel parcel) {
        int i = 0;
        List list = null;
        int zzaq = com.google.android.gms.common.internal.safeparcel.zza.zzaq(parcel);
        String str = null;
        List list2 = null;
        String str2 = null;
        List list3 = null;
        String str3 = null;
        List list4 = null;
        String str4 = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzaq) {
            int zzap = com.google.android.gms.common.internal.safeparcel.zza.zzap(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzcj(zzap)) {
                case 1:
                    str3 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzap);
                    break;
                case 2:
                    str4 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzap);
                    break;
                case 3:
                    list4 = com.google.android.gms.common.internal.safeparcel.zza.zzD(parcel, zzap);
                    break;
                case 4:
                    list3 = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzap, SubstringEntity.CREATOR);
                    break;
                case 5:
                    i = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzap);
                    break;
                case 6:
                    str2 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzap);
                    break;
                case 7:
                    list2 = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzap, SubstringEntity.CREATOR);
                    break;
                case 8:
                    str = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzap);
                    break;
                case 9:
                    list = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzap, SubstringEntity.CREATOR);
                    break;
                case 1000:
                    i2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzap);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzap);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaq) {
            return new AutocompletePredictionEntity(i2, str4, list4, i, str3, list3, str2, list2, str, list);
        }
        throw new com.google.android.gms.common.internal.safeparcel.zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zziw */
    public AutocompletePredictionEntity[] newArray(int i) {
        return new AutocompletePredictionEntity[i];
    }
}
