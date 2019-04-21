package com.google.android.gms.playlog.internal;

import java.util.ArrayList;

public class zzb {
    private final ArrayList<zza> zzbkS;
    private int zzbkT;

    public static class zza {
        public final PlayLoggerContext zzbkU;
        public final LogEvent zzbkV;
    }

    public zzb() {
        this(100);
    }

    public zzb(int i) {
        this.zzbkS = new ArrayList();
        this.zzbkT = i;
    }

    public void clear() {
        this.zzbkS.clear();
    }

    public boolean isEmpty() {
        return this.zzbkS.isEmpty();
    }

    public ArrayList<zza> zzIg() {
        return this.zzbkS;
    }
}
