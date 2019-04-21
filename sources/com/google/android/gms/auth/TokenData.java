package com.google.android.gms.auth;

import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzz;
import java.util.List;

public class TokenData extends AbstractSafeParcelable {
    public static final zze CREATOR = new zze();
    final int mVersionCode;
    private final String zzabf;
    private final Long zzabg;
    private final boolean zzabh;
    private final boolean zzabi;
    private final List<String> zzabj;

    TokenData(int i, String str, Long l, boolean z, boolean z2, List<String> list) {
        this.mVersionCode = i;
        this.zzabf = zzaa.zzdl(str);
        this.zzabg = l;
        this.zzabh = z;
        this.zzabi = z2;
        this.zzabj = list;
    }

    @Nullable
    public static TokenData zzd(Bundle bundle, String str) {
        bundle.setClassLoader(TokenData.class.getClassLoader());
        Bundle bundle2 = bundle.getBundle(str);
        if (bundle2 == null) {
            return null;
        }
        bundle2.setClassLoader(TokenData.class.getClassLoader());
        return (TokenData) bundle2.getParcelable("TokenData");
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TokenData)) {
            return false;
        }
        TokenData tokenData = (TokenData) obj;
        return TextUtils.equals(this.zzabf, tokenData.zzabf) && zzz.equal(this.zzabg, tokenData.zzabg) && this.zzabh == tokenData.zzabh && this.zzabi == tokenData.zzabi && zzz.equal(this.zzabj, tokenData.zzabj);
    }

    public String getToken() {
        return this.zzabf;
    }

    public int hashCode() {
        return zzz.hashCode(this.zzabf, this.zzabg, Boolean.valueOf(this.zzabh), Boolean.valueOf(this.zzabi), this.zzabj);
    }

    public void writeToParcel(Parcel parcel, int i) {
        zze.zza(this, parcel, i);
    }

    @Nullable
    public Long zzoQ() {
        return this.zzabg;
    }

    public boolean zzoR() {
        return this.zzabh;
    }

    public boolean zzoS() {
        return this.zzabi;
    }

    @Nullable
    public List<String> zzoT() {
        return this.zzabj;
    }
}
