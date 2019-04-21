package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zza implements Creator<Configuration> {
    static void zza(Configuration configuration, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zzc(parcel, 1, configuration.mVersionCode);
        zzb.zzc(parcel, 2, configuration.zzbkB);
        zzb.zza(parcel, 3, configuration.zzbkC, i, false);
        zzb.zza(parcel, 4, configuration.zzbkD, false);
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzgR */
    public Configuration createFromParcel(Parcel parcel) {
        String[] strArr = null;
        int i = 0;
        int zzaq = com.google.android.gms.common.internal.safeparcel.zza.zzaq(parcel);
        Flag[] flagArr = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzaq) {
            Flag[] flagArr2;
            int i3;
            String[] strArr2;
            int zzap = com.google.android.gms.common.internal.safeparcel.zza.zzap(parcel);
            String[] strArr3;
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzcj(zzap)) {
                case 1:
                    strArr3 = strArr;
                    flagArr2 = flagArr;
                    i3 = i;
                    i = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzap);
                    strArr2 = strArr3;
                    break;
                case 2:
                    i = i2;
                    Flag[] flagArr3 = flagArr;
                    i3 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzap);
                    strArr2 = strArr;
                    flagArr2 = flagArr3;
                    break;
                case 3:
                    i3 = i;
                    i = i2;
                    strArr3 = strArr;
                    flagArr2 = (Flag[]) com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzap, Flag.CREATOR);
                    strArr2 = strArr3;
                    break;
                case 4:
                    strArr2 = com.google.android.gms.common.internal.safeparcel.zza.zzC(parcel, zzap);
                    flagArr2 = flagArr;
                    i3 = i;
                    i = i2;
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzap);
                    strArr2 = strArr;
                    flagArr2 = flagArr;
                    i3 = i;
                    i = i2;
                    break;
            }
            i2 = i;
            i = i3;
            flagArr = flagArr2;
            strArr = strArr2;
        }
        if (parcel.dataPosition() == zzaq) {
            return new Configuration(i2, i, flagArr, strArr);
        }
        throw new com.google.android.gms.common.internal.safeparcel.zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzkn */
    public Configuration[] newArray(int i) {
        return new Configuration[i];
    }
}
