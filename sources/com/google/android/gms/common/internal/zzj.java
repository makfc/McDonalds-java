package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzj implements Creator<GetServiceRequest> {
    static void zza(GetServiceRequest getServiceRequest, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zzc(parcel, 1, getServiceRequest.version);
        zzb.zzc(parcel, 2, getServiceRequest.zzary);
        zzb.zzc(parcel, 3, getServiceRequest.zzarz);
        zzb.zza(parcel, 4, getServiceRequest.zzarA, false);
        zzb.zza(parcel, 5, getServiceRequest.zzarB, false);
        zzb.zza(parcel, 6, getServiceRequest.zzarC, i, false);
        zzb.zza(parcel, 7, getServiceRequest.zzarD, false);
        zzb.zza(parcel, 8, getServiceRequest.zzarE, i, false);
        zzb.zza(parcel, 9, getServiceRequest.zzarF);
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzak */
    public GetServiceRequest createFromParcel(Parcel parcel) {
        int i = 0;
        Account account = null;
        int zzaq = zza.zzaq(parcel);
        long j = 0;
        Bundle bundle = null;
        Scope[] scopeArr = null;
        IBinder iBinder = null;
        String str = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    i3 = zza.zzg(parcel, zzap);
                    break;
                case 2:
                    i2 = zza.zzg(parcel, zzap);
                    break;
                case 3:
                    i = zza.zzg(parcel, zzap);
                    break;
                case 4:
                    str = zza.zzq(parcel, zzap);
                    break;
                case 5:
                    iBinder = zza.zzr(parcel, zzap);
                    break;
                case 6:
                    scopeArr = (Scope[]) zza.zzb(parcel, zzap, Scope.CREATOR);
                    break;
                case 7:
                    bundle = zza.zzs(parcel, zzap);
                    break;
                case 8:
                    account = (Account) zza.zza(parcel, zzap, Account.CREATOR);
                    break;
                case 9:
                    j = zza.zzi(parcel, zzap);
                    break;
                default:
                    zza.zzb(parcel, zzap);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaq) {
            return new GetServiceRequest(i3, i2, i, str, iBinder, scopeArr, bundle, account, j);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzcb */
    public GetServiceRequest[] newArray(int i) {
        return new GetServiceRequest[i];
    }
}
