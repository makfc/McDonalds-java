package com.google.android.gms.analytics.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.zztx;

public final class zzaj {
    static zztx zzTN;
    static Boolean zzTO;
    static Object zzrs = new Object();

    public static boolean zzU(Context context) {
        zzaa.zzz(context);
        if (zzTO != null) {
            return zzTO.booleanValue();
        }
        boolean zzb = zzao.zzb(context, "com.google.android.gms.analytics.AnalyticsReceiver", false);
        zzTO = Boolean.valueOf(zzb);
        return zzb;
    }

    @RequiresPermission
    public void onReceive(Context context, Intent intent) {
        zzf zzX = zzf.zzX(context);
        zzaf zzlR = zzX.zzlR();
        if (intent == null) {
            zzlR.zzbJ("AnalyticsReceiver called with null intent");
            return;
        }
        String action = intent.getAction();
        if (zzX.zzlS().zzmW()) {
            zzlR.zza("Device AnalyticsReceiver got", action);
        } else {
            zzlR.zza("Local AnalyticsReceiver got", action);
        }
        if ("com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(action)) {
            boolean zzV = zzak.zzV(context);
            Intent intent2 = new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
            intent2.setComponent(new ComponentName(context, "com.google.android.gms.analytics.AnalyticsService"));
            intent2.setAction("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
            synchronized (zzrs) {
                context.startService(intent2);
                if (zzV) {
                    try {
                        if (zzTN == null) {
                            zzTN = new zztx(context, 1, "Analytics WakeLock");
                            zzTN.setReferenceCounted(false);
                        }
                        zzTN.acquire(1000);
                    } catch (SecurityException e) {
                        zzlR.zzbJ("Analytics service at risk of not starting. For more reliable analytics, add the WAKE_LOCK permission to your manifest. See http://goo.gl/8Rd3yj for instructions.");
                    }
                    return;
                }
            }
        }
    }
}
