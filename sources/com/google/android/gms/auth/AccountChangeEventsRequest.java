package com.google.android.gms.auth;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class AccountChangeEventsRequest extends AbstractSafeParcelable {
    public static final Creator<AccountChangeEventsRequest> CREATOR = new zzb();
    final int mVersion;
    Account zzZB;
    @Deprecated
    String zzaaR;
    int zzaaT;

    public AccountChangeEventsRequest() {
        this.mVersion = 1;
    }

    AccountChangeEventsRequest(int i, int i2, String str, Account account) {
        this.mVersion = i;
        this.zzaaT = i2;
        this.zzaaR = str;
        if (account != null || TextUtils.isEmpty(str)) {
            this.zzZB = account;
        } else {
            this.zzZB = new Account(str, "com.google");
        }
    }

    @Deprecated
    public AccountChangeEventsRequest setAccountName(String str) {
        this.zzaaR = str;
        return this;
    }

    public AccountChangeEventsRequest setEventIndex(int i) {
        this.zzaaT = i;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }
}
