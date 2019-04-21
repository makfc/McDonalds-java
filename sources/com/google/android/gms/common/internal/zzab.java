package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzab implements Creator<ResolveAccountRequest> {
    static void zza(ResolveAccountRequest resolveAccountRequest, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zzc(parcel, 1, resolveAccountRequest.mVersionCode);
        zzb.zza(parcel, 2, resolveAccountRequest.getAccount(), i, false);
        zzb.zzc(parcel, 3, resolveAccountRequest.getSessionId());
        zzb.zza(parcel, 4, resolveAccountRequest.zztP(), i, false);
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzal */
    public ResolveAccountRequest createFromParcel(Parcel parcel) {
        GoogleSignInAccount googleSignInAccount = null;
        int i = 0;
        int zzaq = zza.zzaq(parcel);
        Account account = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzaq) {
            int i3;
            Account account2;
            int zzg;
            GoogleSignInAccount googleSignInAccount2;
            int zzap = zza.zzap(parcel);
            GoogleSignInAccount googleSignInAccount3;
            switch (zza.zzcj(zzap)) {
                case 1:
                    googleSignInAccount3 = googleSignInAccount;
                    i3 = i;
                    account2 = account;
                    zzg = zza.zzg(parcel, zzap);
                    googleSignInAccount2 = googleSignInAccount3;
                    break;
                case 2:
                    zzg = i2;
                    int i4 = i;
                    account2 = (Account) zza.zza(parcel, zzap, Account.CREATOR);
                    googleSignInAccount2 = googleSignInAccount;
                    i3 = i4;
                    break;
                case 3:
                    account2 = account;
                    zzg = i2;
                    googleSignInAccount3 = googleSignInAccount;
                    i3 = zza.zzg(parcel, zzap);
                    googleSignInAccount2 = googleSignInAccount3;
                    break;
                case 4:
                    googleSignInAccount2 = (GoogleSignInAccount) zza.zza(parcel, zzap, GoogleSignInAccount.CREATOR);
                    i3 = i;
                    account2 = account;
                    zzg = i2;
                    break;
                default:
                    zza.zzb(parcel, zzap);
                    googleSignInAccount2 = googleSignInAccount;
                    i3 = i;
                    account2 = account;
                    zzg = i2;
                    break;
            }
            i2 = zzg;
            account = account2;
            i = i3;
            googleSignInAccount = googleSignInAccount2;
        }
        if (parcel.dataPosition() == zzaq) {
            return new ResolveAccountRequest(i2, account, i, googleSignInAccount);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzcf */
    public ResolveAccountRequest[] newArray(int i) {
        return new ResolveAccountRequest[i];
    }
}
