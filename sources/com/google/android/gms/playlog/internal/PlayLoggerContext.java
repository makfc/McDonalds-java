package com.google.android.gms.playlog.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzz;

public class PlayLoggerContext extends AbstractSafeParcelable {
    public static final zze CREATOR = new zze();
    public final String packageName;
    public final int versionCode;
    public final int zzbkZ;
    public final int zzbla;
    public final String zzblb;
    public final String zzblc;
    public final boolean zzbld;
    public final String zzble;
    public final boolean zzblf;
    public final int zzblg;

    public PlayLoggerContext(int i, String str, int i2, int i3, String str2, String str3, boolean z, String str4, boolean z2, int i4) {
        this.versionCode = i;
        this.packageName = str;
        this.zzbkZ = i2;
        this.zzbla = i3;
        this.zzblb = str2;
        this.zzblc = str3;
        this.zzbld = z;
        this.zzble = str4;
        this.zzblf = z2;
        this.zzblg = i4;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlayLoggerContext)) {
            return false;
        }
        PlayLoggerContext playLoggerContext = (PlayLoggerContext) obj;
        return this.versionCode == playLoggerContext.versionCode && this.packageName.equals(playLoggerContext.packageName) && this.zzbkZ == playLoggerContext.zzbkZ && this.zzbla == playLoggerContext.zzbla && zzz.equal(this.zzble, playLoggerContext.zzble) && zzz.equal(this.zzblb, playLoggerContext.zzblb) && zzz.equal(this.zzblc, playLoggerContext.zzblc) && this.zzbld == playLoggerContext.zzbld && this.zzblf == playLoggerContext.zzblf && this.zzblg == playLoggerContext.zzblg;
    }

    public int hashCode() {
        return zzz.hashCode(Integer.valueOf(this.versionCode), this.packageName, Integer.valueOf(this.zzbkZ), Integer.valueOf(this.zzbla), this.zzble, this.zzblb, this.zzblc, Boolean.valueOf(this.zzbld), Boolean.valueOf(this.zzblf), Integer.valueOf(this.zzblg));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PlayLoggerContext[");
        stringBuilder.append("versionCode=").append(this.versionCode).append(',');
        stringBuilder.append("package=").append(this.packageName).append(',');
        stringBuilder.append("packageVersionCode=").append(this.zzbkZ).append(',');
        stringBuilder.append("logSource=").append(this.zzbla).append(',');
        stringBuilder.append("logSourceName=").append(this.zzble).append(',');
        stringBuilder.append("uploadAccount=").append(this.zzblb).append(',');
        stringBuilder.append("loggingId=").append(this.zzblc).append(',');
        stringBuilder.append("logAndroidId=").append(this.zzbld).append(',');
        stringBuilder.append("isAnonymous=").append(this.zzblf).append(',');
        stringBuilder.append("qosTier=").append(this.zzblg);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zze.zza(this, parcel, i);
    }
}
