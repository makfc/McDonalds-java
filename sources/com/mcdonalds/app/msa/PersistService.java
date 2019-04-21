package com.mcdonalds.app.msa;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import com.ensighten.Ensighten;

public class PersistService extends Service {
    public IBinder onBind(Intent intent) {
        Ensighten.evaluateEvent(this, "onBind", new Object[]{intent});
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Ensighten.evaluateEvent(this, "onStartCommand", new Object[]{intent, new Integer(flags), new Integer(startId)});
        MSASettings.startNextAlarm(this);
        return 1;
    }

    public void onTaskRemoved(Intent intent) {
        Ensighten.evaluateEvent(this, "onTaskRemoved", new Object[]{intent});
        Intent restartService = new Intent(getApplicationContext(), getClass());
        restartService.setPackage(getPackageName());
        ((AlarmManager) getApplicationContext().getSystemService("alarm")).set(3, SystemClock.elapsedRealtime() + 10000, PendingIntent.getService(getApplicationContext(), 1, restartService, 1073741824));
    }
}
