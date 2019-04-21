package com.google.android.gms.plus.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzf implements Creator<PlusCommonExtras> {
    static void zza(PlusCommonExtras plusCommonExtras, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zza(parcel, 1, plusCommonExtras.zzIn(), false);
        zzb.zza(parcel, 2, plusCommonExtras.zzIo(), false);
        zzb.zzc(parcel, 1000, plusCommonExtras.getVersionCode());
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzgV */
    public PlusCommonExtras createFromParcel(Parcel parcel) {
        String str = null;
        int zzaq = zza.zzaq(parcel);
        int i = 0;
        String str2 = null;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    str2 = zza.zzq(parcel, zzap);
                    break;
                case 2:
                    str = zza.zzq(parcel, zzap);
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
            return new PlusCommonExtras(i, str2, str);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzks */
    public PlusCommonExtras[] newArray(int i) {
        return new PlusCommonExtras[i];
    }
}
