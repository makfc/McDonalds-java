package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzc implements Creator<AuthAccountRequest> {
    static void zza(AuthAccountRequest authAccountRequest, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zzc(parcel, 1, authAccountRequest.mVersionCode);
        zzb.zza(parcel, 2, authAccountRequest.zzaqo, false);
        zzb.zza(parcel, 3, authAccountRequest.zzakD, i, false);
        zzb.zza(parcel, 4, authAccountRequest.zzaqp, false);
        zzb.zza(parcel, 5, authAccountRequest.zzaqq, false);
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzai */
    public AuthAccountRequest createFromParcel(Parcel parcel) {
        Integer num = null;
        int zzaq = zza.zzaq(parcel);
        int i = 0;
        Integer num2 = null;
        Scope[] scopeArr = null;
        IBinder iBinder = null;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    i = zza.zzg(parcel, zzap);
                    break;
                case 2:
                    iBinder = zza.zzr(parcel, zzap);
                    break;
                case 3:
                    scopeArr = (Scope[]) zza.zzb(parcel, zzap, Scope.CREATOR);
                    break;
                case 4:
                    num2 = zza.zzh(parcel, zzap);
                    break;
                case 5:
                    num = zza.zzh(parcel, zzap);
                    break;
                default:
                    zza.zzb(parcel, zzap);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaq) {
            return new AuthAccountRequest(i, iBinder, scopeArr, num2, num);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzbY */
    public AuthAccountRequest[] newArray(int i) {
        return new AuthAccountRequest[i];
    }
}
