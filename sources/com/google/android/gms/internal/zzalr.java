package com.google.android.gms.internal;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public final class zzalr extends zzakr<Date> {
    public static final zzaks zzbXD = new C21951();
    private final DateFormat zzbYd = new SimpleDateFormat("MMM d, yyyy");

    /* renamed from: com.google.android.gms.internal.zzalr$1 */
    static class C21951 implements zzaks {
        C21951() {
        }

        public <T> zzakr<T> zza(zzajz zzajz, zzalv<T> zzalv) {
            return zzalv.zzWl() == Date.class ? new zzalr() : null;
        }
    }

    public synchronized void zza(zzaly zzaly, Date date) throws IOException {
        zzaly.zziU(date == null ? null : this.zzbYd.format(date));
    }
}
