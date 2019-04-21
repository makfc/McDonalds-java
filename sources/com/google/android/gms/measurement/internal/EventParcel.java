package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public final class EventParcel extends AbstractSafeParcelable {
    public static final zzk CREATOR = new zzk();
    public final String name;
    public final int versionCode;
    public final EventParams zzbcq;
    public final String zzbcr;
    public final long zzbcs;

    EventParcel(int i, String str, EventParams eventParams, String str2, long j) {
        this.versionCode = i;
        this.name = str;
        this.zzbcq = eventParams;
        this.zzbcr = str2;
        this.zzbcs = j;
    }

    public EventParcel(String str, EventParams eventParams, String str2, long j) {
        this.versionCode = 1;
        this.name = str;
        this.zzbcq = eventParams;
        this.zzbcr = str2;
        this.zzbcs = j;
    }

    public String toString() {
        String str = this.zzbcr;
        String str2 = this.name;
        String valueOf = String.valueOf(this.zzbcq);
        return new StringBuilder(((String.valueOf(str).length() + 21) + String.valueOf(str2).length()) + String.valueOf(valueOf).length()).append("origin=").append(str).append(",name=").append(str2).append(",params=").append(valueOf).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzk.zza(this, parcel, i);
    }
}
