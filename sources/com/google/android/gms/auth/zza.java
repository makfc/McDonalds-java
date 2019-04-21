package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zza implements Creator<AccountChangeEvent> {
    static void zza(AccountChangeEvent accountChangeEvent, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zzc(parcel, 1, accountChangeEvent.mVersion);
        zzb.zza(parcel, 2, accountChangeEvent.zzaaQ);
        zzb.zza(parcel, 3, accountChangeEvent.zzaaR, false);
        zzb.zzc(parcel, 4, accountChangeEvent.zzaaS);
        zzb.zzc(parcel, 5, accountChangeEvent.zzaaT);
        zzb.zza(parcel, 6, accountChangeEvent.zzaaU, false);
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzB */
    public AccountChangeEvent createFromParcel(Parcel parcel) {
        String str = null;
        int i = 0;
        int zzaq = com.google.android.gms.common.internal.safeparcel.zza.zzaq(parcel);
        long j = 0;
        int i2 = 0;
        String str2 = null;
        int i3 = 0;
        while (parcel.dataPosition() < zzaq) {
            int zzap = com.google.android.gms.common.internal.safeparcel.zza.zzap(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzcj(zzap)) {
                case 1:
                    i3 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzap);
                    break;
                case 2:
                    j = com.google.android.gms.common.internal.safeparcel.zza.zzi(parcel, zzap);
                    break;
                case 3:
                    str2 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzap);
                    break;
                case 4:
                    i2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzap);
                    break;
                case 5:
                    i = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzap);
                    break;
                case 6:
                    str = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzap);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzap);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaq) {
            return new AccountChangeEvent(i3, j, str2, i2, i, str);
        }
        throw new com.google.android.gms.common.internal.safeparcel.zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzaI */
    public AccountChangeEvent[] newArray(int i) {
        return new AccountChangeEvent[i];
    }
}
