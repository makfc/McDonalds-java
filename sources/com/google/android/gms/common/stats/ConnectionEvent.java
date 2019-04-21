package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class ConnectionEvent extends StatsEvent {
    public static final Creator<ConnectionEvent> CREATOR = new zza();
    final int mVersionCode;
    private final long zzatc;
    private int zzatd;
    private final String zzate;
    private final String zzatf;
    private final String zzatg;
    private final String zzath;
    private final String zzati;
    private final String zzatj;
    private final long zzatk;
    private final long zzatl;
    private long zzatm;

    ConnectionEvent(int i, long j, int i2, String str, String str2, String str3, String str4, String str5, String str6, long j2, long j3) {
        this.mVersionCode = i;
        this.zzatc = j;
        this.zzatd = i2;
        this.zzate = str;
        this.zzatf = str2;
        this.zzatg = str3;
        this.zzath = str4;
        this.zzatm = -1;
        this.zzati = str5;
        this.zzatj = str6;
        this.zzatk = j2;
        this.zzatl = j3;
    }

    public ConnectionEvent(long j, int i, String str, String str2, String str3, String str4, String str5, String str6, long j2, long j3) {
        this(1, j, i, str, str2, str3, str4, str5, str6, j2, j3);
    }

    public int getEventType() {
        return this.zzatd;
    }

    public long getTimeMillis() {
        return this.zzatc;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.zza(this, parcel, i);
    }

    public String zzuA() {
        return this.zzath;
    }

    public String zzuB() {
        return this.zzati;
    }

    public String zzuC() {
        return this.zzatj;
    }

    public long zzuD() {
        return this.zzatm;
    }

    public long zzuE() {
        return this.zzatl;
    }

    public long zzuF() {
        return this.zzatk;
    }

    public String zzuG() {
        String valueOf = String.valueOf("\t");
        String valueOf2 = String.valueOf(zzux());
        String valueOf3 = String.valueOf(zzuy());
        String valueOf4 = String.valueOf("\t");
        String valueOf5 = String.valueOf(zzuz());
        String valueOf6 = String.valueOf(zzuA());
        String valueOf7 = String.valueOf("\t");
        Object obj = this.zzati == null ? "" : this.zzati;
        String valueOf8 = String.valueOf("\t");
        return new StringBuilder(((((((((String.valueOf(valueOf).length() + 22) + String.valueOf(valueOf2).length()) + String.valueOf(valueOf3).length()) + String.valueOf(valueOf4).length()) + String.valueOf(valueOf5).length()) + String.valueOf(valueOf6).length()) + String.valueOf(valueOf7).length()) + String.valueOf(obj).length()) + String.valueOf(valueOf8).length()).append(valueOf).append(valueOf2).append("/").append(valueOf3).append(valueOf4).append(valueOf5).append("/").append(valueOf6).append(valueOf7).append(obj).append(valueOf8).append(zzuE()).toString();
    }

    public String zzux() {
        return this.zzate;
    }

    public String zzuy() {
        return this.zzatf;
    }

    public String zzuz() {
        return this.zzatg;
    }
}
