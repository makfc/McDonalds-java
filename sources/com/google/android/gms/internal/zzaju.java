package com.google.android.gms.internal;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

final class zzaju implements zzake<Date>, zzakn<Date> {
    private final DateFormat zzbVN;
    private final DateFormat zzbVO;
    private final DateFormat zzbVP;

    zzaju() {
        this(DateFormat.getDateTimeInstance(2, 2, Locale.US), DateFormat.getDateTimeInstance(2, 2));
    }

    zzaju(DateFormat dateFormat, DateFormat dateFormat2) {
        this.zzbVN = dateFormat;
        this.zzbVO = dateFormat2;
        this.zzbVP = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        this.zzbVP.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(zzaju.class.getSimpleName());
        stringBuilder.append('(').append(this.zzbVO.getClass().getSimpleName()).append(')');
        return stringBuilder.toString();
    }

    public zzakf zza(Date date, Type type, zzakm zzakm) {
        zzakl zzakl;
        synchronized (this.zzbVO) {
            zzakl = new zzakl(this.zzbVN.format(date));
        }
        return zzakl;
    }
}
