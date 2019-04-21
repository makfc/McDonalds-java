package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.MainThread;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.zztx;

public final class zzae {
    private static Boolean zzTP;
    private final Context mContext;
    private final Handler mHandler = new Handler();
    private final zza zzbfL;

    public interface zza {
        boolean callServiceStopSelfResult(int i);

        Context getContext();
    }

    public zzae(zza zza) {
        this.mContext = zza.getContext();
        zzaa.zzz(this.mContext);
        this.zzbfL = zza;
    }

    private zzp zzFm() {
        return zzx.zzbd(this.mContext).zzFm();
    }

    public static boolean zzV(Context context) {
        zzaa.zzz(context);
        if (zzTP != null) {
            return zzTP.booleanValue();
        }
        boolean zzj = zzal.zzj(context, "com.google.android.gms.measurement.AppMeasurementService");
        zzTP = Boolean.valueOf(zzj);
        return zzj;
    }

    private void zzkp() {
        try {
            synchronized (zzu.zzrs) {
                zztx zztx = zzu.zzTN;
                if (zztx != null && zztx.isHeld()) {
                    zztx.release();
                }
            }
        } catch (SecurityException e) {
        }
    }

    @MainThread
    public IBinder onBind(Intent intent) {
        if (intent == null) {
            zzFm().zzFE().log("onBind called with null intent");
            return null;
        }
        String action = intent.getAction();
        if ("com.google.android.gms.measurement.START".equals(action)) {
            return new zzy(zzx.zzbd(this.mContext));
        }
        zzFm().zzFG().zzj("onBind received unknown action", action);
        return null;
    }

    @MainThread
    public void onCreate() {
        zzx zzbd = zzx.zzbd(this.mContext);
        zzp zzFm = zzbd.zzFm();
        if (zzbd.zzFo().zzmW()) {
            zzFm.zzFL().log("Device AppMeasurementService is starting up");
        } else {
            zzFm.zzFL().log("Local AppMeasurementService is starting up");
        }
    }

    @MainThread
    public void onDestroy() {
        zzx zzbd = zzx.zzbd(this.mContext);
        zzp zzFm = zzbd.zzFm();
        if (zzbd.zzFo().zzmW()) {
            zzFm.zzFL().log("Device AppMeasurementService is shutting down");
        } else {
            zzFm.zzFL().log("Local AppMeasurementService is shutting down");
        }
    }

    @MainThread
    public void onRebind(Intent intent) {
        if (intent == null) {
            zzFm().zzFE().log("onRebind called with null intent");
            return;
        }
        zzFm().zzFL().zzj("onRebind called. action", intent.getAction());
    }

    @MainThread
    public int onStartCommand(Intent intent, int i, final int i2) {
        zzkp();
        final zzx zzbd = zzx.zzbd(this.mContext);
        final zzp zzFm = zzbd.zzFm();
        if (intent == null) {
            zzFm.zzFG().log("AppMeasurementService started with null intent");
        } else {
            String action = intent.getAction();
            if (zzbd.zzFo().zzmW()) {
                zzFm.zzFL().zze("Device AppMeasurementService called. startId, action", Integer.valueOf(i2), action);
            } else {
                zzFm.zzFL().zze("Local AppMeasurementService called. startId, action", Integer.valueOf(i2), action);
            }
            if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
                zzbd.zzFl().zzg(new Runnable() {

                    /* renamed from: com.google.android.gms.measurement.internal.zzae$1$1 */
                    class C26851 implements Runnable {
                        C26851() {
                        }

                        public void run() {
                            if (!zzae.this.zzbfL.callServiceStopSelfResult(i2)) {
                                return;
                            }
                            if (zzbd.zzFo().zzmW()) {
                                zzFm.zzFL().log("Device AppMeasurementService processed last upload request");
                            } else {
                                zzFm.zzFL().log("Local AppMeasurementService processed last upload request");
                            }
                        }
                    }

                    public void run() {
                        zzbd.zzGq();
                        zzbd.zzGl();
                        zzae.this.mHandler.post(new C26851());
                    }
                });
            }
        }
        return 2;
    }

    @MainThread
    public boolean onUnbind(Intent intent) {
        if (intent == null) {
            zzFm().zzFE().log("onUnbind called with null intent");
        } else {
            zzFm().zzFL().zzj("onUnbind called for intent. action", intent.getAction());
        }
        return true;
    }
}
