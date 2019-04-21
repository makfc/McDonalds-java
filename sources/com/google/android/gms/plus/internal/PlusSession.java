package com.google.android.gms.plus.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzz;
import java.util.Arrays;

public class PlusSession extends AbstractSafeParcelable {
    public static final zzh CREATOR = new zzh();
    private final int mVersionCode;
    private final String zzaaR;
    private final String zzahC;
    private final String[] zzblD;
    private final String[] zzblE;
    private final String[] zzblF;
    private final String zzblG;
    private final String zzblH;
    private final String zzblI;
    private final PlusCommonExtras zzblJ;

    PlusSession(int i, String str, String[] strArr, String[] strArr2, String[] strArr3, String str2, String str3, String str4, String str5, PlusCommonExtras plusCommonExtras) {
        this.mVersionCode = i;
        this.zzaaR = str;
        this.zzblD = strArr;
        this.zzblE = strArr2;
        this.zzblF = strArr3;
        this.zzblG = str2;
        this.zzblH = str3;
        this.zzahC = str4;
        this.zzblI = str5;
        this.zzblJ = plusCommonExtras;
    }

    public PlusSession(String str, String[] strArr, String[] strArr2, String[] strArr3, String str2, String str3, String str4, PlusCommonExtras plusCommonExtras) {
        this.mVersionCode = 1;
        this.zzaaR = str;
        this.zzblD = strArr;
        this.zzblE = strArr2;
        this.zzblF = strArr3;
        this.zzblG = str2;
        this.zzblH = str3;
        this.zzahC = str4;
        this.zzblI = null;
        this.zzblJ = plusCommonExtras;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlusSession)) {
            return false;
        }
        PlusSession plusSession = (PlusSession) obj;
        return this.mVersionCode == plusSession.mVersionCode && zzz.equal(this.zzaaR, plusSession.zzaaR) && Arrays.equals(this.zzblD, plusSession.zzblD) && Arrays.equals(this.zzblE, plusSession.zzblE) && Arrays.equals(this.zzblF, plusSession.zzblF) && zzz.equal(this.zzblG, plusSession.zzblG) && zzz.equal(this.zzblH, plusSession.zzblH) && zzz.equal(this.zzahC, plusSession.zzahC) && zzz.equal(this.zzblI, plusSession.zzblI) && zzz.equal(this.zzblJ, plusSession.zzblJ);
    }

    public String getAccountName() {
        return this.zzaaR;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return zzz.hashCode(Integer.valueOf(this.mVersionCode), this.zzaaR, this.zzblD, this.zzblE, this.zzblF, this.zzblG, this.zzblH, this.zzahC, this.zzblI, this.zzblJ);
    }

    public String toString() {
        return zzz.zzy(this).zzg("versionCode", Integer.valueOf(this.mVersionCode)).zzg("accountName", this.zzaaR).zzg("requestedScopes", this.zzblD).zzg("visibleActivities", this.zzblE).zzg("requiredFeatures", this.zzblF).zzg("packageNameForAuth", this.zzblG).zzg("callingPackageName", this.zzblH).zzg("applicationName", this.zzahC).zzg("extra", this.zzblJ.toString()).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzh.zza(this, parcel, i);
    }

    public String[] zzIp() {
        return this.zzblD;
    }

    public String[] zzIq() {
        return this.zzblE;
    }

    public String[] zzIr() {
        return this.zzblF;
    }

    public String zzIs() {
        return this.zzblG;
    }

    public String zzIt() {
        return this.zzblH;
    }

    public String zzIu() {
        return this.zzblI;
    }

    public PlusCommonExtras zzIv() {
        return this.zzblJ;
    }

    public Bundle zzIw() {
        Bundle bundle = new Bundle();
        bundle.setClassLoader(PlusCommonExtras.class.getClassLoader());
        this.zzblJ.zzL(bundle);
        return bundle;
    }

    public String zzqg() {
        return this.zzahC;
    }
}
