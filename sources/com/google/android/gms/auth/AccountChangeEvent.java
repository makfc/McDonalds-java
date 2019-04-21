package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzz;

public class AccountChangeEvent extends AbstractSafeParcelable {
    public static final Creator<AccountChangeEvent> CREATOR = new zza();
    final int mVersion;
    final long zzaaQ;
    final String zzaaR;
    final int zzaaS;
    final int zzaaT;
    final String zzaaU;

    AccountChangeEvent(int i, long j, String str, int i2, int i3, String str2) {
        this.mVersion = i;
        this.zzaaQ = j;
        this.zzaaR = (String) zzaa.zzz(str);
        this.zzaaS = i2;
        this.zzaaT = i3;
        this.zzaaU = str2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AccountChangeEvent)) {
            return false;
        }
        AccountChangeEvent accountChangeEvent = (AccountChangeEvent) obj;
        return this.mVersion == accountChangeEvent.mVersion && this.zzaaQ == accountChangeEvent.zzaaQ && zzz.equal(this.zzaaR, accountChangeEvent.zzaaR) && this.zzaaS == accountChangeEvent.zzaaS && this.zzaaT == accountChangeEvent.zzaaT && zzz.equal(this.zzaaU, accountChangeEvent.zzaaU);
    }

    public int hashCode() {
        return zzz.hashCode(Integer.valueOf(this.mVersion), Long.valueOf(this.zzaaQ), this.zzaaR, Integer.valueOf(this.zzaaS), Integer.valueOf(this.zzaaT), this.zzaaU);
    }

    public String toString() {
        Object obj = "UNKNOWN";
        switch (this.zzaaS) {
            case 1:
                obj = "ADDED";
                break;
            case 2:
                obj = "REMOVED";
                break;
            case 3:
                obj = "RENAMED_FROM";
                break;
            case 4:
                obj = "RENAMED_TO";
                break;
        }
        String str = this.zzaaR;
        String str2 = this.zzaaU;
        return new StringBuilder(((String.valueOf(str).length() + 91) + String.valueOf(obj).length()) + String.valueOf(str2).length()).append("AccountChangeEvent {accountName = ").append(str).append(", changeType = ").append(obj).append(", changeData = ").append(str2).append(", eventIndex = ").append(this.zzaaT).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.zza(this, parcel, i);
    }
}
