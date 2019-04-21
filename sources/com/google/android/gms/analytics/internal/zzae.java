package com.google.android.gms.analytics.internal;

import android.util.Log;
import com.google.android.gms.analytics.Logger;

@Deprecated
public class zzae {
    private static volatile Logger zzYL;

    static {
        setLogger(new zzs());
    }

    public static Logger getLogger() {
        return zzYL;
    }

    public static void setLogger(Logger logger) {
        zzYL = logger;
    }

    /* renamed from: v */
    public static void m7466v(String str) {
        zzaf zznZ = zzaf.zznZ();
        if (zznZ != null) {
            zznZ.zzbG(str);
        } else if (zzX(0)) {
            Log.v((String) zzy.zzXF.get(), str);
        }
        Logger logger = zzYL;
        if (logger != null) {
            logger.verbose(str);
        }
    }

    public static boolean zzX(int i) {
        return getLogger() != null && getLogger().getLogLevel() <= i;
    }

    public static void zzaV(String str) {
        zzaf zznZ = zzaf.zznZ();
        if (zznZ != null) {
            zznZ.zzbI(str);
        } else if (zzX(1)) {
            Log.i((String) zzy.zzXF.get(), str);
        }
        Logger logger = zzYL;
        if (logger != null) {
            logger.info(str);
        }
    }

    public static void zzaW(String str) {
        zzaf zznZ = zzaf.zznZ();
        if (zznZ != null) {
            zznZ.zzbJ(str);
        } else if (zzX(2)) {
            Log.w((String) zzy.zzXF.get(), str);
        }
        Logger logger = zzYL;
        if (logger != null) {
            logger.warn(str);
        }
    }

    public static void zzf(String str, Object obj) {
        zzaf zznZ = zzaf.zznZ();
        if (zznZ != null) {
            zznZ.zze(str, obj);
        } else if (zzX(3)) {
            String stringBuilder;
            if (obj != null) {
                String valueOf = String.valueOf(obj);
                stringBuilder = new StringBuilder((String.valueOf(str).length() + 1) + String.valueOf(valueOf).length()).append(str).append(":").append(valueOf).toString();
            } else {
                stringBuilder = str;
            }
            Log.e((String) zzy.zzXF.get(), stringBuilder);
        }
        Logger logger = zzYL;
        if (logger != null) {
            logger.error(str);
        }
    }
}
