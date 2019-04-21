package com.google.android.gms.signin.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zza implements Creator<AuthAccountResult> {
    static void zza(AuthAccountResult authAccountResult, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zzc(parcel, 1, authAccountResult.mVersionCode);
        zzb.zzc(parcel, 2, authAccountResult.zzIT());
        zzb.zza(parcel, 3, authAccountResult.zzIU(), i, false);
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzhk */
    public AuthAccountResult createFromParcel(Parcel parcel) {
        int i = 0;
        int zzaq = com.google.android.gms.common.internal.safeparcel.zza.zzaq(parcel);
        Intent intent = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzaq) {
            int zzap = com.google.android.gms.common.internal.safeparcel.zza.zzap(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzcj(zzap)) {
                case 1:
                    i2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzap);
                    break;
                case 2:
                    i = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzap);
                    break;
                case 3:
                    intent = (Intent) com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzap, Intent.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzap);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaq) {
            return new AuthAccountResult(i2, i, intent);
        }
        throw new com.google.android.gms.common.internal.safeparcel.zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzkH */
    public AuthAccountResult[] newArray(int i) {
        return new AuthAccountResult[i];
    }
}
