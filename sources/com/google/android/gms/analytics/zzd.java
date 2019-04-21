package com.google.android.gms.analytics;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import android.util.LogPrinter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public final class zzd implements zzk {
    private static final Uri zzUj;
    private final LogPrinter zzUk = new LogPrinter(4, "GA/LogCatTransport");

    /* renamed from: com.google.android.gms.analytics.zzd$1 */
    class C20921 implements Comparator<zzg> {
        C20921() {
        }

        /* renamed from: zza */
        public int compare(zzg zzg, zzg zzg2) {
            return zzg.getClass().getCanonicalName().compareTo(zzg2.getClass().getCanonicalName());
        }
    }

    static {
        Builder builder = new Builder();
        builder.scheme("uri");
        builder.authority("local");
        zzUj = builder.build();
    }

    public void zzb(zze zze) {
        ArrayList<zzg> arrayList = new ArrayList(zze.zzkz());
        Collections.sort(arrayList, new C20921());
        StringBuilder stringBuilder = new StringBuilder();
        for (zzg obj : arrayList) {
            String obj2 = obj.toString();
            if (!TextUtils.isEmpty(obj2)) {
                if (stringBuilder.length() != 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(obj2);
            }
        }
        this.zzUk.println(stringBuilder.toString());
    }

    public Uri zzkn() {
        return zzUj;
    }
}
