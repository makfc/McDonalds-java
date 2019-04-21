package com.google.android.gms.measurement.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.zzaa;

class zzr extends BroadcastReceiver {
    static final String zzYP = zzr.class.getName();
    private boolean zzYQ;
    private boolean zzYR;
    private final zzx zzbbl;

    zzr(zzx zzx) {
        zzaa.zzz(zzx);
        this.zzbbl = zzx;
    }

    private Context getContext() {
        return this.zzbbl.getContext();
    }

    private zzp zzFm() {
        return this.zzbbl.zzFm();
    }

    @WorkerThread
    public boolean isRegistered() {
        this.zzbbl.zzkN();
        return this.zzYQ;
    }

    @MainThread
    public void onReceive(Context context, Intent intent) {
        this.zzbbl.zzma();
        String action = intent.getAction();
        zzFm().zzFL().zzj("NetworkBroadcastReceiver received action", action);
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            final boolean zzod = this.zzbbl.zzGb().zzod();
            if (this.zzYR != zzod) {
                this.zzYR = zzod;
                this.zzbbl.zzFl().zzg(new Runnable() {
                    public void run() {
                        zzr.this.zzbbl.zzR(zzod);
                    }
                });
                return;
            }
            return;
        }
        zzFm().zzFG().zzj("NetworkBroadcastReceiver received unknown action", action);
    }

    @WorkerThread
    public void unregister() {
        this.zzbbl.zzma();
        this.zzbbl.zzkN();
        if (isRegistered()) {
            zzFm().zzFL().log("Unregistering connectivity change receiver");
            this.zzYQ = false;
            this.zzYR = false;
            try {
                getContext().unregisterReceiver(this);
            } catch (IllegalArgumentException e) {
                zzFm().zzFE().zzj("Failed to unregister the network broadcast receiver", e);
            }
        }
    }

    @WorkerThread
    public void zzoa() {
        this.zzbbl.zzma();
        this.zzbbl.zzkN();
        if (!this.zzYQ) {
            getContext().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            this.zzYR = this.zzbbl.zzGb().zzod();
            zzFm().zzFL().zzj("Registering connectivity change receiver. Network connected", Boolean.valueOf(this.zzYR));
            this.zzYQ = true;
        }
    }
}
