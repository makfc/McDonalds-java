package com.mcdonalds.app.customer.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.ensighten.Ensighten;
import com.mcdonalds.sdk.services.log.MCDLog;

public class LocaleChangedReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Ensighten.evaluateEvent(this, "onReceive", new Object[]{context, intent});
        MCDLog.temp("Language change detected.");
    }
}
