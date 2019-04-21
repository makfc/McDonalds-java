package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

public class zzb implements Creator<Flag> {
    static void zza(Flag flag, Parcel parcel, int i) {
        int zzar = com.google.android.gms.common.internal.safeparcel.zzb.zzar(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, flag.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, flag.name, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, flag.zzbkF);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, flag.zzaTF);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 5, flag.zzaTH);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 6, flag.zzasH, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 7, flag.zzbkG, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 8, flag.zzbkH);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 9, flag.zzbkI);
        com.google.android.gms.common.internal.safeparcel.zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzgS */
    public Flag createFromParcel(Parcel parcel) {
        byte[] bArr = null;
        int i = 0;
        int zzaq = zza.zzaq(parcel);
        long j = 0;
        double d = 0.0d;
        int i2 = 0;
        String str = null;
        boolean z = false;
        String str2 = null;
        int i3 = 0;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    i3 = zza.zzg(parcel, zzap);
                    break;
                case 2:
                    str2 = zza.zzq(parcel, zzap);
                    break;
                case 3:
                    j = zza.zzi(parcel, zzap);
                    break;
                case 4:
                    z = zza.zzc(parcel, zzap);
                    break;
                case 5:
                    d = zza.zzn(parcel, zzap);
                    break;
                case 6:
                    str = zza.zzq(parcel, zzap);
                    break;
                case 7:
                    bArr = zza.zzt(parcel, zzap);
                    break;
                case 8:
                    i2 = zza.zzg(parcel, zzap);
                    break;
                case 9:
                    i = zza.zzg(parcel, zzap);
                    break;
                default:
                    zza.zzb(parcel, zzap);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaq) {
            return new Flag(i3, str2, j, z, d, str, bArr, i2, i);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzko */
    public Flag[] newArray(int i) {
        return new Flag[i];
    }
}
