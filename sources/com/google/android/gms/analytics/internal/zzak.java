package com.google.android.gms.analytics.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.zztx;

public final class zzak {
    private static Boolean zzTP;
    private final Context mContext;
    private final Handler mHandler = new Handler();
    private final zza zzZd;

    public interface zza {
        boolean callServiceStopSelfResult(int i);

        Context getContext();
    }

    public zzak(zza zza) {
        this.mContext = zza.getContext();
        zzaa.zzz(this.mContext);
        this.zzZd = zza;
    }

    public static boolean zzV(Context context) {
        zzaa.zzz(context);
        if (zzTP != null) {
            return zzTP.booleanValue();
        }
        boolean zzj = zzao.zzj(context, "com.google.android.gms.analytics.AnalyticsService");
        zzTP = Boolean.valueOf(zzj);
        return zzj;
    }

    private void zzkp() {
        try {
            synchronized (zzaj.zzrs) {
                zztx zztx = zzaj.zzTN;
                if (zztx != null && zztx.isHeld()) {
                    zztx.release();
                }
            }
        } catch (SecurityException e) {
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresPermission
    public void onCreate() {
        zzf zzX = zzf.zzX(this.mContext);
        zzaf zzlR = zzX.zzlR();
        if (zzX.zzlS().zzmW()) {
            zzlR.zzbG("Device AnalyticsService is starting up");
        } else {
            zzlR.zzbG("Local AnalyticsService is starting up");
        }
    }

    @RequiresPermission
    public void onDestroy() {
        zzf zzX = zzf.zzX(this.mContext);
        zzaf zzlR = zzX.zzlR();
        if (zzX.zzlS().zzmW()) {
            zzlR.zzbG("Device AnalyticsService is shutting down");
        } else {
            zzlR.zzbG("Local AnalyticsService is shutting down");
        }
    }

    @RequiresPermission
    public int onStartCommand(Intent intent, int i, final int i2) {
        zzkp();
        final zzf zzX = zzf.zzX(this.mContext);
        final zzaf zzlR = zzX.zzlR();
        if (intent == null) {
            zzlR.zzbJ("AnalyticsService started with null intent");
        } else {
            String action = intent.getAction();
            if (zzX.zzlS().zzmW()) {
                zzlR.zza("Device AnalyticsService called. startId, action", Integer.valueOf(i2), action);
            } else {
                zzlR.zza("Local AnalyticsService called. startId, action", Integer.valueOf(i2), action);
            }
            if ("com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(action)) {
                zzX.zzkw().zza(new zzw() {

                    /* renamed from: com.google.android.gms.analytics.internal.zzak$1$1 */
                    class C20711 implements Runnable {
                        C20711() {
                        }

                        public void run() {
                            if (!zzak.this.zzZd.callServiceStopSelfResult(i2)) {
                                return;
                            }
                            if (zzX.zzlS().zzmW()) {
                                zzlR.zzbG("Device AnalyticsService processed last dispatch request");
                            } else {
                                zzlR.zzbG("Local AnalyticsService processed last dispatch request");
                            }
                        }
                    }

                    public void zzd(Throwable th) {
                        zzak.this.mHandler.post(new C20711());
                    }
                });
            }
        }
        return 2;
    }
}
