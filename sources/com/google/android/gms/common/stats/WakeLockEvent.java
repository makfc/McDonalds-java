package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import java.util.List;

public final class WakeLockEvent extends StatsEvent {
    public static final Creator<WakeLockEvent> CREATOR = new zzg();
    private final long mTimeout;
    final int mVersionCode;
    private final String zzatO;
    private final String zzatP;
    private final String zzatQ;
    private final int zzatR;
    private final List<String> zzatS;
    private final String zzatT;
    private int zzatU;
    private final String zzatV;
    private final float zzatW;
    private final long zzatc;
    private int zzatd;
    private final long zzatk;
    private long zzatm;

    WakeLockEvent(int i, long j, int i2, String str, int i3, List<String> list, String str2, long j2, int i4, String str3, String str4, float f, long j3, String str5) {
        this.mVersionCode = i;
        this.zzatc = j;
        this.zzatd = i2;
        this.zzatO = str;
        this.zzatP = str3;
        this.zzatQ = str5;
        this.zzatR = i3;
        this.zzatm = -1;
        this.zzatS = list;
        this.zzatT = str2;
        this.zzatk = j2;
        this.zzatU = i4;
        this.zzatV = str4;
        this.zzatW = f;
        this.mTimeout = j3;
    }

    public WakeLockEvent(long j, int i, String str, int i2, List<String> list, String str2, long j2, int i3, String str3, String str4, float f, long j3, String str5) {
        this(2, j, i, str, i2, list, str2, j2, i3, str3, str4, f, j3, str5);
    }

    public int getEventType() {
        return this.zzatd;
    }

    public long getTimeMillis() {
        return this.zzatc;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzg.zza(this, parcel, i);
    }

    public String zzuC() {
        return this.zzatT;
    }

    public long zzuD() {
        return this.zzatm;
    }

    public long zzuF() {
        return this.zzatk;
    }

    public String zzuG() {
        String valueOf = String.valueOf("\t");
        String valueOf2 = String.valueOf(zzuJ());
        String valueOf3 = String.valueOf("\t");
        int zzuM = zzuM();
        String valueOf4 = String.valueOf("\t");
        Object join = zzuN() == null ? "" : TextUtils.join(",", zzuN());
        String valueOf5 = String.valueOf("\t");
        int zzuO = zzuO();
        String valueOf6 = String.valueOf("\t");
        Object zzuK = zzuK() == null ? "" : zzuK();
        String valueOf7 = String.valueOf("\t");
        Object zzuP = zzuP() == null ? "" : zzuP();
        String valueOf8 = String.valueOf("\t");
        float zzuQ = zzuQ();
        String valueOf9 = String.valueOf("\t");
        Object zzuL = zzuL() == null ? "" : zzuL();
        return new StringBuilder(((((((((((((String.valueOf(valueOf).length() + 37) + String.valueOf(valueOf2).length()) + String.valueOf(valueOf3).length()) + String.valueOf(valueOf4).length()) + String.valueOf(join).length()) + String.valueOf(valueOf5).length()) + String.valueOf(valueOf6).length()) + String.valueOf(zzuK).length()) + String.valueOf(valueOf7).length()) + String.valueOf(zzuP).length()) + String.valueOf(valueOf8).length()) + String.valueOf(valueOf9).length()) + String.valueOf(zzuL).length()).append(valueOf).append(valueOf2).append(valueOf3).append(zzuM).append(valueOf4).append(join).append(valueOf5).append(zzuO).append(valueOf6).append(zzuK).append(valueOf7).append(zzuP).append(valueOf8).append(zzuQ).append(valueOf9).append(zzuL).toString();
    }

    public String zzuJ() {
        return this.zzatO;
    }

    public String zzuK() {
        return this.zzatP;
    }

    public String zzuL() {
        return this.zzatQ;
    }

    public int zzuM() {
        return this.zzatR;
    }

    public List<String> zzuN() {
        return this.zzatS;
    }

    public int zzuO() {
        return this.zzatU;
    }

    public String zzuP() {
        return this.zzatV;
    }

    public float zzuQ() {
        return this.zzatW;
    }

    public long zzuR() {
        return this.mTimeout;
    }
}
