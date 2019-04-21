package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;

public class AppMetadata extends AbstractSafeParcelable {
    public static final zzb CREATOR = new zzb();
    public final String packageName;
    public final int versionCode;
    public final String zzaUf;
    public final String zzbbK;
    public final String zzbbL;
    public final long zzbbM;
    public final long zzbbN;
    public final String zzbbO;
    public final boolean zzbbP;
    public final boolean zzbbQ;
    public final long zzbbR;
    public final String zzbbS;

    AppMetadata(int i, String str, String str2, String str3, String str4, long j, long j2, String str5, boolean z, boolean z2, long j3, String str6) {
        this.versionCode = i;
        this.packageName = str;
        this.zzbbK = str2;
        this.zzaUf = str3;
        if (i < 5) {
            j3 = -2147483648L;
        }
        this.zzbbR = j3;
        this.zzbbL = str4;
        this.zzbbM = j;
        this.zzbbN = j2;
        this.zzbbO = str5;
        if (i >= 3) {
            this.zzbbP = z;
        } else {
            this.zzbbP = true;
        }
        this.zzbbQ = z2;
        this.zzbbS = str6;
    }

    AppMetadata(String str, String str2, String str3, long j, String str4, long j2, long j3, String str5, boolean z, boolean z2, String str6) {
        zzaa.zzdl(str);
        this.versionCode = 6;
        this.packageName = str;
        if (TextUtils.isEmpty(str2)) {
            str2 = null;
        }
        this.zzbbK = str2;
        this.zzaUf = str3;
        this.zzbbR = j;
        this.zzbbL = str4;
        this.zzbbM = j2;
        this.zzbbN = j3;
        this.zzbbO = str5;
        this.zzbbP = z;
        this.zzbbQ = z2;
        this.zzbbS = str6;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }
}
