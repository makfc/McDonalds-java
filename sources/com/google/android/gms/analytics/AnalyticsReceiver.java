package com.google.android.gms.analytics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.analytics.internal.zzaj;

public final class AnalyticsReceiver extends BroadcastReceiver {
    private zzaj zzTG;

    private zzaj zzkl() {
        if (this.zzTG == null) {
            this.zzTG = new zzaj();
        }
        return this.zzTG;
    }

    @RequiresPermission
    public void onReceive(Context context, Intent intent) {
        zzkl().onReceive(context, intent);
    }
}
