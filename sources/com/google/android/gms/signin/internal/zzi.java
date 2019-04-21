package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzi implements Creator<SignInResponse> {
    static void zza(SignInResponse signInResponse, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zzc(parcel, 1, signInResponse.mVersionCode);
        zzb.zza(parcel, 2, signInResponse.zztR(), i, false);
        zzb.zza(parcel, 3, signInResponse.zzIY(), i, false);
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzho */
    public SignInResponse createFromParcel(Parcel parcel) {
        ResolveAccountResponse resolveAccountResponse = null;
        int zzaq = zza.zzaq(parcel);
        int i = 0;
        ConnectionResult connectionResult = null;
        while (parcel.dataPosition() < zzaq) {
            ConnectionResult connectionResult2;
            int zzg;
            ResolveAccountResponse resolveAccountResponse2;
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    ResolveAccountResponse resolveAccountResponse3 = resolveAccountResponse;
                    connectionResult2 = connectionResult;
                    zzg = zza.zzg(parcel, zzap);
                    resolveAccountResponse2 = resolveAccountResponse3;
                    break;
                case 2:
                    zzg = i;
                    ConnectionResult connectionResult3 = (ConnectionResult) zza.zza(parcel, zzap, ConnectionResult.CREATOR);
                    resolveAccountResponse2 = resolveAccountResponse;
                    connectionResult2 = connectionResult3;
                    break;
                case 3:
                    resolveAccountResponse2 = (ResolveAccountResponse) zza.zza(parcel, zzap, ResolveAccountResponse.CREATOR);
                    connectionResult2 = connectionResult;
                    zzg = i;
                    break;
                default:
                    zza.zzb(parcel, zzap);
                    resolveAccountResponse2 = resolveAccountResponse;
                    connectionResult2 = connectionResult;
                    zzg = i;
                    break;
            }
            i = zzg;
            connectionResult = connectionResult2;
            resolveAccountResponse = resolveAccountResponse2;
        }
        if (parcel.dataPosition() == zzaq) {
            return new SignInResponse(i, connectionResult, resolveAccountResponse);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzkM */
    public SignInResponse[] newArray(int i) {
        return new SignInResponse[i];
    }
}
