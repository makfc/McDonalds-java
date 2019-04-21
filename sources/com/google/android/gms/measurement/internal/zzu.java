package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.MainThread;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.zztx;

public final class zzu {
    static zztx zzTN;
    static Boolean zzTO;
    static final Object zzrs = new Object();

    public static boolean zzU(Context context) {
        zzaa.zzz(context);
        if (zzTO != null) {
            return zzTO.booleanValue();
        }
        boolean zzb = zzal.zzb(context, "com.google.android.gms.measurement.AppMeasurementReceiver", false);
        zzTO = Boolean.valueOf(zzb);
        return zzb;
    }

    @MainThread
    public void onReceive(Context context, Intent intent) {
        zzx zzbd = zzx.zzbd(context);
        zzp zzFm = zzbd.zzFm();
        if (intent == null) {
            zzFm.zzFG().log("AppMeasurementReceiver called with null intent");
            return;
        }
        String action = intent.getAction();
        if (zzbd.zzFo().zzmW()) {
            zzFm.zzFL().zzj("Device AppMeasurementReceiver got", action);
        } else {
            zzFm.zzFL().zzj("Local AppMeasurementReceiver got", action);
        }
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            boolean zzV = zzae.zzV(context);
            Intent className = new Intent().setClassName(context, "com.google.android.gms.measurement.AppMeasurementService");
            className.setAction("com.google.android.gms.measurement.UPLOAD");
            synchronized (zzrs) {
                context.startService(className);
                if (zzV) {
                    try {
                        if (zzTN == null) {
                            zzTN = new zztx(context, 1, "AppMeasurement WakeLock");
                            zzTN.setReferenceCounted(false);
                        }
                        zzTN.acquire(1000);
                    } catch (SecurityException e) {
                        zzFm.zzFG().log("AppMeasurementService at risk of not starting. For more reliable app measurements, add the WAKE_LOCK permission to your manifest.");
                    }
                    return;
                }
            }
        }
    }
}
