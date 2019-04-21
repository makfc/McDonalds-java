package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.os.SystemClock;

public final class zzj {
    private static IntentFilter zzauf = new IntentFilter("android.intent.action.BATTERY_CHANGED");
    private static long zzaug;
    private static float zzauh = Float.NaN;

    @TargetApi(20)
    public static int zzaD(Context context) {
        int i = 1;
        if (context == null || context.getApplicationContext() == null) {
            return -1;
        }
        Intent registerReceiver = context.getApplicationContext().registerReceiver(null, zzauf);
        int i2 = ((registerReceiver == null ? 0 : registerReceiver.getIntExtra("plugged", 0)) & 7) != 0 ? 1 : 0;
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager == null) {
            return -1;
        }
        int i3 = (zzb(powerManager) ? 1 : 0) << 1;
        if (i2 == 0) {
            i = 0;
        }
        return i3 | i;
    }

    public static synchronized float zzaE(Context context) {
        float f;
        synchronized (zzj.class) {
            if (SystemClock.elapsedRealtime() - zzaug >= 60000 || Float.isNaN(zzauh)) {
                Intent registerReceiver = context.getApplicationContext().registerReceiver(null, zzauf);
                if (registerReceiver != null) {
                    zzauh = ((float) registerReceiver.getIntExtra("level", -1)) / ((float) registerReceiver.getIntExtra("scale", -1));
                }
                zzaug = SystemClock.elapsedRealtime();
                f = zzauh;
            } else {
                f = zzauh;
            }
        }
        return f;
    }

    @TargetApi(20)
    public static boolean zzb(PowerManager powerManager) {
        return zzs.zzvf() ? powerManager.isInteractive() : powerManager.isScreenOn();
    }
}
