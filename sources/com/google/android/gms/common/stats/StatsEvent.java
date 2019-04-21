package com.google.android.gms.common.stats;

import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public abstract class StatsEvent extends AbstractSafeParcelable {
    public abstract int getEventType();

    public abstract long getTimeMillis();

    public String toString() {
        long timeMillis = getTimeMillis();
        String valueOf = String.valueOf("\t");
        int eventType = getEventType();
        String valueOf2 = String.valueOf("\t");
        long zzuD = zzuD();
        String valueOf3 = String.valueOf(zzuG());
        return new StringBuilder(((String.valueOf(valueOf).length() + 51) + String.valueOf(valueOf2).length()) + String.valueOf(valueOf3).length()).append(timeMillis).append(valueOf).append(eventType).append(valueOf2).append(zzuD).append(valueOf3).toString();
    }

    public abstract long zzuD();

    public abstract String zzuG();
}
