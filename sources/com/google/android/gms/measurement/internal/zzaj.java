package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzaj implements Creator<UserAttributeParcel> {
    static void zza(UserAttributeParcel userAttributeParcel, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zzc(parcel, 1, userAttributeParcel.versionCode);
        zzb.zza(parcel, 2, userAttributeParcel.name, false);
        zzb.zza(parcel, 3, userAttributeParcel.zzbgc);
        zzb.zza(parcel, 4, userAttributeParcel.zzbgd, false);
        zzb.zza(parcel, 5, userAttributeParcel.zzbge, false);
        zzb.zza(parcel, 6, userAttributeParcel.zzasH, false);
        zzb.zza(parcel, 7, userAttributeParcel.zzbcr, false);
        zzb.zza(parcel, 8, userAttributeParcel.zzbgf, false);
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzfU */
    public UserAttributeParcel createFromParcel(Parcel parcel) {
        Double d = null;
        int zzaq = zza.zzaq(parcel);
        int i = 0;
        long j = 0;
        String str = null;
        String str2 = null;
        Float f = null;
        Long l = null;
        String str3 = null;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    i = zza.zzg(parcel, zzap);
                    break;
                case 2:
                    str3 = zza.zzq(parcel, zzap);
                    break;
                case 3:
                    j = zza.zzi(parcel, zzap);
                    break;
                case 4:
                    l = zza.zzj(parcel, zzap);
                    break;
                case 5:
                    f = zza.zzm(parcel, zzap);
                    break;
                case 6:
                    str2 = zza.zzq(parcel, zzap);
                    break;
                case 7:
                    str = zza.zzq(parcel, zzap);
                    break;
                case 8:
                    d = zza.zzo(parcel, zzap);
                    break;
                default:
                    zza.zzb(parcel, zzap);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaq) {
            return new UserAttributeParcel(i, str3, j, l, f, str2, str, d);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzje */
    public UserAttributeParcel[] newArray(int i) {
        return new UserAttributeParcel[i];
    }
}
