package com.google.android.gms.analytics;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.RequiresPermission;
import android.text.TextUtils;
import com.google.android.gms.analytics.internal.zzaf;
import com.google.android.gms.analytics.internal.zzao;
import com.google.android.gms.analytics.internal.zzf;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.zztx;

public class CampaignTrackingService extends Service {
    private static Boolean zzTP;
    private Handler mHandler;

    private Handler getHandler() {
        Handler handler = this.mHandler;
        if (handler != null) {
            return handler;
        }
        handler = new Handler(getMainLooper());
        this.mHandler = handler;
        return handler;
    }

    public static boolean zzV(Context context) {
        zzaa.zzz(context);
        if (zzTP != null) {
            return zzTP.booleanValue();
        }
        boolean zzj = zzao.zzj(context, "com.google.android.gms.analytics.CampaignTrackingService");
        zzTP = Boolean.valueOf(zzj);
        return zzj;
    }

    private void zzkp() {
        try {
            synchronized (CampaignTrackingReceiver.zzrs) {
                zztx zztx = CampaignTrackingReceiver.zzTN;
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
        super.onCreate();
        zzf.zzX(this).zzlR().zzbG("CampaignTrackingService is starting up");
    }

    @RequiresPermission
    public void onDestroy() {
        zzf.zzX(this).zzlR().zzbG("CampaignTrackingService is shutting down");
        super.onDestroy();
    }

    @RequiresPermission
    public int onStartCommand(Intent intent, int i, final int i2) {
        zzkp();
        zzf zzX = zzf.zzX(this);
        final zzaf zzlR = zzX.zzlR();
        Object obj = null;
        if (zzX.zzlS().zzmW()) {
            zzlR.zzbK("Unexpected installation campaign (package side)");
        } else {
            obj = intent.getStringExtra("referrer");
        }
        final Handler handler = getHandler();
        if (TextUtils.isEmpty(obj)) {
            if (!zzX.zzlS().zzmW()) {
                zzlR.zzbJ("No campaign found on com.android.vending.INSTALL_REFERRER \"referrer\" extra");
            }
            zzX.zzlT().zzf(new Runnable() {
                public void run() {
                    CampaignTrackingService.this.zza(zzlR, handler, i2);
                }
            });
        } else {
            int zzna = zzX.zzlS().zzna();
            if (obj.length() > zzna) {
                zzlR.zzc("Campaign data exceed the maximum supported size and will be clipped. size, limit", Integer.valueOf(obj.length()), Integer.valueOf(zzna));
                obj = obj.substring(0, zzna);
            }
            zzlR.zza("CampaignTrackingService called. startId, campaign", Integer.valueOf(i2), obj);
            zzX.zzkw().zza(obj, new Runnable() {
                public void run() {
                    CampaignTrackingService.this.zza(zzlR, handler, i2);
                }
            });
        }
        return 2;
    }

    /* Access modifiers changed, original: protected */
    public void zza(final zzaf zzaf, Handler handler, final int i) {
        handler.post(new Runnable() {
            public void run() {
                boolean stopSelfResult = CampaignTrackingService.this.stopSelfResult(i);
                if (stopSelfResult) {
                    zzaf.zza("Install campaign broadcast processed", Boolean.valueOf(stopSelfResult));
                }
            }
        });
    }
}
