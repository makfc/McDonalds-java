package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzac implements Creator<ResolveAccountResponse> {
    static void zza(ResolveAccountResponse resolveAccountResponse, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zzc(parcel, 1, resolveAccountResponse.mVersionCode);
        zzb.zza(parcel, 2, resolveAccountResponse.zzaqo, false);
        zzb.zza(parcel, 3, resolveAccountResponse.zztR(), i, false);
        zzb.zza(parcel, 4, resolveAccountResponse.zztS());
        zzb.zza(parcel, 5, resolveAccountResponse.zztT());
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzam */
    public ResolveAccountResponse createFromParcel(Parcel parcel) {
        ConnectionResult connectionResult = null;
        boolean z = false;
        int zzaq = zza.zzaq(parcel);
        boolean z2 = false;
        IBinder iBinder = null;
        int i = 0;
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
                    connectionResult = (ConnectionResult) zza.zza(parcel, zzap, ConnectionResult.CREATOR);
                    break;
                case 4:
                    z2 = zza.zzc(parcel, zzap);
                    break;
                case 5:
                    z = zza.zzc(parcel, zzap);
                    break;
                default:
                    zza.zzb(parcel, zzap);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaq) {
            return new ResolveAccountResponse(i, iBinder, connectionResult, z2, z);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzcg */
    public ResolveAccountResponse[] newArray(int i) {
        return new ResolveAccountResponse[i];
    }
}
