package com.google.android.gms.analytics.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import com.google.android.gms.common.internal.zzaa;

class zzag extends BroadcastReceiver {
    static final String zzYP = zzag.class.getName();
    private final zzf zzWg;
    private boolean zzYQ;
    private boolean zzYR;

    zzag(zzf zzf) {
        zzaa.zzz(zzf);
        this.zzWg = zzf;
    }

    private Context getContext() {
        return this.zzWg.getContext();
    }

    private zzb zzkw() {
        return this.zzWg.zzkw();
    }

    private zzaf zzlR() {
        return this.zzWg.zzlR();
    }

    private void zzob() {
        zzlR();
        zzkw();
    }

    public boolean isConnected() {
        if (!this.zzYQ) {
            this.zzWg.zzlR().zzbJ("Connectivity unknown. Receiver not registered");
        }
        return this.zzYR;
    }

    public boolean isRegistered() {
        return this.zzYQ;
    }

    public void onReceive(Context context, Intent intent) {
        zzob();
        String action = intent.getAction();
        this.zzWg.zzlR().zza("NetworkBroadcastReceiver received action", action);
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            boolean zzod = zzod();
            if (this.zzYR != zzod) {
                this.zzYR = zzod;
                zzkw().zzR(zzod);
            }
        } else if (!"com.google.analytics.RADIO_POWERED".equals(action)) {
            this.zzWg.zzlR().zzd("NetworkBroadcastReceiver received unknown action", action);
        } else if (!intent.hasExtra(zzYP)) {
            zzkw().zzlM();
        }
    }

    public void unregister() {
        if (isRegistered()) {
            this.zzWg.zzlR().zzbG("Unregistering connectivity change receiver");
            this.zzYQ = false;
            this.zzYR = false;
            try {
                getContext().unregisterReceiver(this);
            } catch (IllegalArgumentException e) {
                zzlR().zze("Failed to unregister the network broadcast receiver", e);
            }
        }
    }

    public void zzoa() {
        zzob();
        if (!this.zzYQ) {
            Context context = getContext();
            context.registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            IntentFilter intentFilter = new IntentFilter("com.google.analytics.RADIO_POWERED");
            intentFilter.addCategory(context.getPackageName());
            context.registerReceiver(this, intentFilter);
            this.zzYR = zzod();
            this.zzWg.zzlR().zza("Registering connectivity change receiver. Network connected", Boolean.valueOf(this.zzYR));
            this.zzYQ = true;
        }
    }

    public void zzoc() {
        if (VERSION.SDK_INT > 10) {
            Context context = getContext();
            Intent intent = new Intent("com.google.analytics.RADIO_POWERED");
            intent.addCategory(context.getPackageName());
            intent.putExtra(zzYP, true);
            context.sendOrderedBroadcast(intent, null);
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean zzod() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) getContext().getSystemService("connectivity")).getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } catch (SecurityException e) {
            return false;
        }
    }
}
