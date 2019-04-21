package com.google.android.gms.analytics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.RequiresPermission;
import android.text.TextUtils;
import com.google.android.gms.analytics.internal.zzaf;
import com.google.android.gms.analytics.internal.zzao;
import com.google.android.gms.analytics.internal.zzf;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.zztx;

public class CampaignTrackingReceiver extends BroadcastReceiver {
    static zztx zzTN;
    static Boolean zzTO;
    static Object zzrs = new Object();

    public static boolean zzU(Context context) {
        zzaa.zzz(context);
        if (zzTO != null) {
            return zzTO.booleanValue();
        }
        boolean zzb = zzao.zzb(context, "com.google.android.gms.analytics.CampaignTrackingReceiver", true);
        zzTO = Boolean.valueOf(zzb);
        return zzb;
    }

    @RequiresPermission
    public void onReceive(Context context, Intent intent) {
        zzf zzX = zzf.zzX(context);
        zzaf zzlR = zzX.zzlR();
        if (intent == null) {
            zzlR.zzbJ("CampaignTrackingReceiver received null intent");
            return;
        }
        String stringExtra = intent.getStringExtra("referrer");
        String action = intent.getAction();
        zzlR.zza("CampaignTrackingReceiver received", action);
        if (!"com.android.vending.INSTALL_REFERRER".equals(action) || TextUtils.isEmpty(stringExtra)) {
            zzlR.zzbJ("CampaignTrackingReceiver received unexpected intent without referrer extra");
            return;
        }
        boolean zzV = CampaignTrackingService.zzV(context);
        if (!zzV) {
            zzlR.zzbJ("CampaignTrackingService not registered or disabled. Installation tracking not possible. See http://goo.gl/8Rd3yj for instructions.");
        }
        zzh(context, stringExtra);
        if (zzX.zzlS().zzmW()) {
            zzlR.zzbK("Received unexpected installation campaign on package side");
            return;
        }
        Class zzko = zzko();
        zzaa.zzz(zzko);
        Intent intent2 = new Intent(context, zzko);
        intent2.putExtra("referrer", stringExtra);
        synchronized (zzrs) {
            context.startService(intent2);
            if (zzV) {
                try {
                    if (zzTN == null) {
                        zzTN = new zztx(context, 1, "Analytics campaign WakeLock");
                        zzTN.setReferenceCounted(false);
                    }
                    zzTN.acquire(1000);
                } catch (SecurityException e) {
                    zzlR.zzbJ("CampaignTrackingService service at risk of not starting. For more reliable installation campaign reports, add the WAKE_LOCK permission to your manifest. See http://goo.gl/8Rd3yj for instructions.");
                }
                return;
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void zzh(Context context, String str) {
    }

    /* Access modifiers changed, original: protected */
    public Class<? extends CampaignTrackingService> zzko() {
        return CampaignTrackingService.class;
    }
}
