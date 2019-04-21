package com.crashlytics.android.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.concurrent.atomic.AtomicBoolean;

class DevicePowerStateListener {
    private static final IntentFilter FILTER_BATTERY_CHANGED = new IntentFilter("android.intent.action.BATTERY_CHANGED");
    private static final IntentFilter FILTER_POWER_CONNECTED = new IntentFilter("android.intent.action.ACTION_POWER_CONNECTED");
    private static final IntentFilter FILTER_POWER_DISCONNECTED = new IntentFilter("android.intent.action.ACTION_POWER_DISCONNECTED");
    private final Context context;
    private boolean isPowerConnected;
    private final BroadcastReceiver powerConnectedReceiver = new C16731();
    private final BroadcastReceiver powerDisconnectedReceiver = new C16742();
    private final AtomicBoolean receiversRegistered = new AtomicBoolean(false);

    /* renamed from: com.crashlytics.android.core.DevicePowerStateListener$1 */
    class C16731 extends BroadcastReceiver {
        C16731() {
        }

        public void onReceive(Context context, Intent intent) {
            DevicePowerStateListener.this.isPowerConnected = true;
        }
    }

    /* renamed from: com.crashlytics.android.core.DevicePowerStateListener$2 */
    class C16742 extends BroadcastReceiver {
        C16742() {
        }

        public void onReceive(Context context, Intent intent) {
            DevicePowerStateListener.this.isPowerConnected = false;
        }
    }

    public DevicePowerStateListener(Context context) {
        this.context = context;
    }

    public void initialize() {
        boolean z = true;
        int status = -1;
        if (!this.receiversRegistered.getAndSet(true)) {
            Intent statusIntent = this.context.registerReceiver(null, FILTER_BATTERY_CHANGED);
            if (statusIntent != null) {
                status = statusIntent.getIntExtra("status", -1);
            }
            if (!(status == 2 || status == 5)) {
                z = false;
            }
            this.isPowerConnected = z;
            this.context.registerReceiver(this.powerConnectedReceiver, FILTER_POWER_CONNECTED);
            this.context.registerReceiver(this.powerDisconnectedReceiver, FILTER_POWER_DISCONNECTED);
        }
    }

    public boolean isPowerConnected() {
        return this.isPowerConnected;
    }

    public void dispose() {
        if (this.receiversRegistered.getAndSet(false)) {
            this.context.unregisterReceiver(this.powerConnectedReceiver);
            this.context.unregisterReceiver(this.powerDisconnectedReceiver);
        }
    }
}
