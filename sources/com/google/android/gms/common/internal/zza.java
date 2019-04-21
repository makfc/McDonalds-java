package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Binder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.zze;

public class zza extends com.google.android.gms.common.internal.zzq.zza {
    int zzaqn;

    public static Account zza(zzq zzq) {
        Account account = null;
        if (zzq != null) {
            long clearCallingIdentity = Binder.clearCallingIdentity();
            try {
                account = zzq.getAccount();
            } catch (RemoteException e) {
                Log.w("AccountAccessor", "Remote account accessor probably died");
            } finally {
                Binder.restoreCallingIdentity(clearCallingIdentity);
            }
        }
        return account;
    }

    public boolean equals(Object obj) {
        Account account = null;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zza)) {
            return false;
        }
        zza zza = (zza) obj;
        return account.equals(account);
    }

    public Account getAccount() {
        int callingUid = Binder.getCallingUid();
        if (callingUid != this.zzaqn) {
            if (zze.zze(null, callingUid)) {
                this.zzaqn = callingUid;
            } else {
                throw new SecurityException("Caller is not GooglePlayServices");
            }
        }
        return null;
    }
}
