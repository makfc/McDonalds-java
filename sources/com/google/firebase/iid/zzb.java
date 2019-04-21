package com.google.firebase.iid;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.iid.MessengerCompat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class zzb extends Service {
    private int zzaSJ;
    private int zzaSK = 0;
    MessengerCompat zzaUg = new MessengerCompat(new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            int zzc = MessengerCompat.zzc(message);
            zzf.zzaX(zzb.this);
            zzb.this.getPackageManager();
            if (zzc == zzf.zzaUs || zzc == zzf.zzaUr) {
                zzb.this.zzm((Intent) message.obj);
                return;
            }
            int i = zzf.zzaUr;
            Log.w("FirebaseInstanceId", "Message from unexpected caller " + zzc + " mine=" + i + " appid=" + zzf.zzaUs);
        }
    });
    final ExecutorService zzbqx = Executors.newSingleThreadExecutor();
    private final Object zzpp = new Object();

    public final IBinder onBind(Intent intent) {
        return (intent == null || !"com.google.firebase.INSTANCE_ID_EVENT".equals(intent.getAction())) ? null : this.zzaUg.getBinder();
    }

    /* JADX WARNING: Failed to extract finally block: empty outs */
    /* JADX WARNING: Missing block: B:27:?, code skipped:
            return r0;
     */
    public final int onStartCommand(android.content.Intent r4, int r5, int r6) {
        /*
        r3 = this;
        r1 = r3.zzpp;
        monitor-enter(r1);
        r3.zzaSJ = r6;	 Catch:{ all -> 0x0017 }
        r0 = r3.zzaSK;	 Catch:{ all -> 0x0017 }
        r0 = r0 + 1;
        r3.zzaSK = r0;	 Catch:{ all -> 0x0017 }
        monitor-exit(r1);	 Catch:{ all -> 0x0017 }
        r1 = r3.zzz(r4);
        if (r1 != 0) goto L_0x001a;
    L_0x0012:
        r3.zzBL();
        r0 = 2;
    L_0x0016:
        return r0;
    L_0x0017:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0017 }
        throw r0;
    L_0x001a:
        r0 = r3.zzA(r1);	 Catch:{ all -> 0x002a }
        r2 = "from";
        r2 = r1.getStringExtra(r2);
        if (r2 == 0) goto L_0x0016;
    L_0x0026:
        android.support.p000v4.content.WakefulBroadcastReceiver.completeWakefulIntent(r1);
        goto L_0x0016;
    L_0x002a:
        r0 = move-exception;
        r2 = "from";
        r2 = r1.getStringExtra(r2);
        if (r2 == 0) goto L_0x0036;
    L_0x0033:
        android.support.p000v4.content.WakefulBroadcastReceiver.completeWakefulIntent(r1);
    L_0x0036:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzb.onStartCommand(android.content.Intent, int, int):int");
    }

    /* Access modifiers changed, original: protected */
    public int zzA(final Intent intent) {
        this.zzbqx.execute(new Runnable() {
            public void run() {
                zzb.this.zzm(intent);
                zzb.this.zzBL();
            }
        });
        return 3;
    }

    /* Access modifiers changed, original: protected */
    public void zzBL() {
        synchronized (this.zzpp) {
            this.zzaSK--;
            if (this.zzaSK == 0) {
                zzhE(this.zzaSJ);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean zzhE(int i) {
        return stopSelfResult(i);
    }

    public abstract void zzm(Intent intent);

    public abstract Intent zzz(Intent intent);
}
