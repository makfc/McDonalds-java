package com.google.android.gms.internal;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public final class zzalk extends zzakr<Date> {
    public static final zzaks zzbXD = new C21881();
    private final DateFormat zzbVN = DateFormat.getDateTimeInstance(2, 2, Locale.US);
    private final DateFormat zzbVO = DateFormat.getDateTimeInstance(2, 2);
    private final DateFormat zzbVP = zzVZ();

    /* renamed from: com.google.android.gms.internal.zzalk$1 */
    static class C21881 implements zzaks {
        C21881() {
        }

        public <T> zzakr<T> zza(zzajz zzajz, zzalv<T> zzalv) {
            return zzalv.zzWl() == Date.class ? new zzalk() : null;
        }
    }

    private static DateFormat zzVZ() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat;
    }

    public synchronized void zza(zzaly zzaly, Date date) throws IOException {
        if (date == null) {
            zzaly.zzWk();
        } else {
            zzaly.zziU(this.zzbVN.format(date));
        }
    }
}
