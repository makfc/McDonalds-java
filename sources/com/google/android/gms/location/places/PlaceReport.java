package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.common.internal.zzz.zza;

public class PlaceReport extends AbstractSafeParcelable {
    public static final Creator<PlaceReport> CREATOR = new zzj();
    private final String mTag;
    final int mVersionCode;
    private final String zzVg;
    private final String zzaWV;

    PlaceReport(int i, String str, String str2, String str3) {
        this.mVersionCode = i;
        this.zzaWV = str;
        this.mTag = str2;
        this.zzVg = str3;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlaceReport)) {
            return false;
        }
        PlaceReport placeReport = (PlaceReport) obj;
        return zzz.equal(this.zzaWV, placeReport.zzaWV) && zzz.equal(this.mTag, placeReport.mTag) && zzz.equal(this.zzVg, placeReport.zzVg);
    }

    public String getPlaceId() {
        return this.zzaWV;
    }

    public String getSource() {
        return this.zzVg;
    }

    public String getTag() {
        return this.mTag;
    }

    public int hashCode() {
        return zzz.hashCode(this.zzaWV, this.mTag, this.zzVg);
    }

    public String toString() {
        zza zzy = zzz.zzy(this);
        zzy.zzg("placeId", this.zzaWV);
        zzy.zzg("tag", this.mTag);
        if (!"unknown".equals(this.zzVg)) {
            zzy.zzg("source", this.zzVg);
        }
        return zzy.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzj.zza(this, parcel, i);
    }
}
