package com.mcdonalds.app.msa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.ensighten.Ensighten;

public class MSANotificationPublisher extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Ensighten.evaluateEvent(this, "onReceive", new Object[]{context, intent});
        if (context.getSharedPreferences(MSASettings.getPrefName(), 0).getBoolean("IS_MSA_TURNED_ON", false)) {
            String action = intent.getAction();
            if (action == null || !action.equals("android.intent.action.BOOT_COMPLETED")) {
                showAlarmScreen(context);
            }
            MSASettings.scheduleNextAlarm(context);
        }
    }

    private void showAlarmScreen(Context ctx) {
        Ensighten.evaluateEvent(this, "showAlarmScreen", new Object[]{ctx});
        Intent alarmFullScreen = new Intent(ctx, MSAAlarmActivity.class);
        alarmFullScreen.setFlags(268435456);
        ctx.startActivity(alarmFullScreen);
    }
}
