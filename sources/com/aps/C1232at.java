package com.aps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* renamed from: com.aps.at */
final class C1232at extends BroadcastReceiver {
    C1232at(C1213aa c1213aa) {
    }

    public final void onReceive(Context context, Intent intent) {
        if (intent != null) {
            try {
                if (intent.getAction().equals("android.location.GPS_FIX_CHANGE")) {
                    C1213aa.f4146b = false;
                }
            } catch (Exception e) {
            }
        }
    }
}
