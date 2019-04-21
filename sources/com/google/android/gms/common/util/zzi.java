package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

public final class zzi {
    private static Boolean zzaub;
    private static Boolean zzauc;
    private static Boolean zzaud;

    @TargetApi(20)
    public static boolean zzaB(Context context) {
        if (zzaud == null) {
            boolean z = zzs.zzvf() && context.getPackageManager().hasSystemFeature("android.hardware.type.watch");
            zzaud = Boolean.valueOf(z);
        }
        return zzaud.booleanValue();
    }

    public static boolean zzb(Resources resources) {
        boolean z = false;
        if (resources == null) {
            return false;
        }
        if (zzaub == null) {
            boolean z2 = (resources.getConfiguration().screenLayout & 15) > 3;
            if ((zzs.zzuX() && z2) || zzc(resources)) {
                z = true;
            }
            zzaub = Boolean.valueOf(z);
        }
        return zzaub.booleanValue();
    }

    @TargetApi(13)
    private static boolean zzc(Resources resources) {
        if (zzauc == null) {
            Configuration configuration = resources.getConfiguration();
            boolean z = zzs.zzuZ() && (configuration.screenLayout & 15) <= 3 && configuration.smallestScreenWidthDp >= 600;
            zzauc = Boolean.valueOf(z);
        }
        return zzauc.booleanValue();
    }
}
