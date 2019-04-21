package com.google.android.gms.common.stats;

import android.os.SystemClock;
import android.support.p000v4.util.SimpleArrayMap;
import android.util.Log;

public class zze {
    private final long zzatL;
    private final int zzatM;
    private final SimpleArrayMap<String, Long> zzatN;

    public zze() {
        this.zzatL = 60000;
        this.zzatM = 10;
        this.zzatN = new SimpleArrayMap(10);
    }

    public zze(int i, long j) {
        this.zzatL = j;
        this.zzatM = i;
        this.zzatN = new SimpleArrayMap();
    }

    private void zzb(long j, long j2) {
        for (int size = this.zzatN.size() - 1; size >= 0; size--) {
            if (j2 - ((Long) this.zzatN.valueAt(size)).longValue() > j) {
                this.zzatN.removeAt(size);
            }
        }
    }

    public Long zzdr(String str) {
        Long l;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = this.zzatL;
        synchronized (this) {
            while (this.zzatN.size() >= this.zzatM) {
                zzb(j, elapsedRealtime);
                j /= 2;
                Log.w("ConnectionTracker", "The max capacity " + this.zzatM + " is not enough. Current durationThreshold is: " + j);
            }
            l = (Long) this.zzatN.put(str, Long.valueOf(elapsedRealtime));
        }
        return l;
    }

    public boolean zzds(String str) {
        boolean z;
        synchronized (this) {
            z = this.zzatN.remove(str) != null;
        }
        return z;
    }
}
