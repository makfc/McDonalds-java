package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zzc implements Creator<CheckServerAuthResult> {
    static void zza(CheckServerAuthResult checkServerAuthResult, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zzc(parcel, 1, checkServerAuthResult.mVersionCode);
        zzb.zza(parcel, 2, checkServerAuthResult.zzbnk);
        zzb.zzc(parcel, 3, checkServerAuthResult.zzbnl, false);
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzhl */
    public CheckServerAuthResult createFromParcel(Parcel parcel) {
        boolean z = false;
        int zzaq = zza.zzaq(parcel);
        List list = null;
        int i = 0;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    i = zza.zzg(parcel, zzap);
                    break;
                case 2:
                    z = zza.zzc(parcel, zzap);
                    break;
                case 3:
                    list = zza.zzc(parcel, zzap, Scope.CREATOR);
                    break;
                default:
                    zza.zzb(parcel, zzap);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaq) {
            return new CheckServerAuthResult(i, z, list);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzkI */
    public CheckServerAuthResult[] newArray(int i) {
        return new CheckServerAuthResult[i];
    }
}
