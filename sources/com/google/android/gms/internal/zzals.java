package com.google.android.gms.internal;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public final class zzals extends zzakr<Time> {
    public static final zzaks zzbXD = new C21961();
    private final DateFormat zzbYd = new SimpleDateFormat("hh:mm:ss a");

    /* renamed from: com.google.android.gms.internal.zzals$1 */
    static class C21961 implements zzaks {
        C21961() {
        }

        public <T> zzakr<T> zza(zzajz zzajz, zzalv<T> zzalv) {
            return zzalv.zzWl() == Time.class ? new zzals() : null;
        }
    }

    public synchronized void zza(zzaly zzaly, Time time) throws IOException {
        zzaly.zziU(time == null ? null : this.zzbYd.format(time));
    }
}
