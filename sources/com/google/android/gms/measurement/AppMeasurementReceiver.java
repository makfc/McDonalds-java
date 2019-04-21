package com.google.android.gms.measurement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.MainThread;
import com.google.android.gms.measurement.internal.zzu;

public final class AppMeasurementReceiver extends BroadcastReceiver {
    private zzu zzbbp;

    private zzu zzDY() {
        if (this.zzbbp == null) {
            this.zzbbp = new zzu();
        }
        return this.zzbbp;
    }

    @MainThread
    public void onReceive(Context context, Intent intent) {
        zzDY().onReceive(context, intent);
    }
}
