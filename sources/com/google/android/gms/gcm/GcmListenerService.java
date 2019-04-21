package com.google.android.gms.gcm;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import com.mcdonalds.sdk.modules.notification.PushConstants;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.Iterator;

public abstract class GcmListenerService extends Service {
    private int zzaSJ;
    private int zzaSK = 0;
    private final Object zzpp = new Object();

    private void zzBL() {
        synchronized (this.zzpp) {
            this.zzaSK--;
            if (this.zzaSK == 0) {
                zzhE(this.zzaSJ);
            }
        }
    }

    @TargetApi(11)
    private void zzl(final Intent intent) {
        if (VERSION.SDK_INT >= 11) {
            AsyncTask.THREAD_POOL_EXECUTOR.execute(new Runnable() {
                public void run() {
                    GcmListenerService.this.zzm(intent);
                }
            });
            return;
        }
        C21402 c21402 = new TraceFieldInterface() {
            public Trace _nr_trace;

            public void _nr_setTrace(Trace trace) {
                try {
                    this._nr_trace = trace;
                } catch (Exception e) {
                }
            }

            /* Access modifiers changed, original: protected|synthetic */
            public /* synthetic */ Object doInBackground(Object[] objArr) {
                try {
                    TraceMachine.enterMethod(this._nr_trace, "GcmListenerService$2#doInBackground", null);
                } catch (NoSuchFieldError e) {
                    while (true) {
                        TraceMachine.enterMethod(null, "GcmListenerService$2#doInBackground", null);
                    }
                }
                Void zza = zza((Void[]) objArr);
                TraceMachine.exitMethod();
                TraceMachine.unloadTraceContext(this);
                return zza;
            }

            /* Access modifiers changed, original: protected|varargs */
            public Void zza(Void... voidArr) {
                GcmListenerService.this.zzm(intent);
                return null;
            }
        };
        Void[] voidArr = new Void[0];
        if (c21402 instanceof AsyncTask) {
            AsyncTaskInstrumentation.execute(c21402, voidArr);
        } else {
            c21402.execute(voidArr);
        }
    }

    private void zzm(android.content.Intent r5) {
        /*
        r4 = this;
        r1 = r5.getAction();	 Catch:{ all -> 0x003d }
        r0 = -1;
        r2 = r1.hashCode();	 Catch:{ all -> 0x003d }
        switch(r2) {
            case 366519424: goto L_0x002f;
            default: goto L_0x000c;
        };	 Catch:{ all -> 0x003d }
    L_0x000c:
        switch(r0) {
            case 0: goto L_0x0039;
            default: goto L_0x000f;
        };	 Catch:{ all -> 0x003d }
    L_0x000f:
        r1 = "GcmListenerService";
        r2 = "Unknown intent action: ";
        r0 = r5.getAction();	 Catch:{ all -> 0x003d }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ all -> 0x003d }
        r3 = r0.length();	 Catch:{ all -> 0x003d }
        if (r3 == 0) goto L_0x0042;
    L_0x0021:
        r0 = r2.concat(r0);	 Catch:{ all -> 0x003d }
    L_0x0025:
        android.util.Log.d(r1, r0);	 Catch:{ all -> 0x003d }
    L_0x0028:
        r4.zzBL();	 Catch:{ all -> 0x003d }
        android.support.p000v4.content.WakefulBroadcastReceiver.completeWakefulIntent(r5);
        return;
    L_0x002f:
        r2 = "com.google.android.c2dm.intent.RECEIVE";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x003d }
        if (r1 == 0) goto L_0x000c;
    L_0x0037:
        r0 = 0;
        goto L_0x000c;
    L_0x0039:
        r4.zzn(r5);	 Catch:{ all -> 0x003d }
        goto L_0x0028;
    L_0x003d:
        r0 = move-exception;
        android.support.p000v4.content.WakefulBroadcastReceiver.completeWakefulIntent(r5);
        throw r0;
    L_0x0042:
        r0 = new java.lang.String;	 Catch:{ all -> 0x003d }
        r0.<init>(r2);	 Catch:{ all -> 0x003d }
        goto L_0x0025;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.gcm.GcmListenerService.zzm(android.content.Intent):void");
    }

    private void zzn(Intent intent) {
        String stringExtra = intent.getStringExtra("message_type");
        if (stringExtra == null) {
            stringExtra = "gcm";
        }
        Object obj = -1;
        switch (stringExtra.hashCode()) {
            case -2062414158:
                if (stringExtra.equals("deleted_messages")) {
                    obj = 1;
                    break;
                }
                break;
            case 102161:
                if (stringExtra.equals("gcm")) {
                    obj = null;
                    break;
                }
                break;
            case 814694033:
                if (stringExtra.equals("send_error")) {
                    obj = 3;
                    break;
                }
                break;
            case 814800675:
                if (stringExtra.equals("send_event")) {
                    obj = 2;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                zzo(intent);
                return;
            case 1:
                onDeletedMessages();
                return;
            case 2:
                onMessageSent(intent.getStringExtra("google.message_id"));
                return;
            case 3:
                onSendError(intent.getStringExtra("google.message_id"), intent.getStringExtra("error"));
                return;
            default:
                String str = "GcmListenerService";
                String str2 = "Received message with unknown type: ";
                stringExtra = String.valueOf(stringExtra);
                Log.w(str, stringExtra.length() != 0 ? str2.concat(stringExtra) : new String(str2));
                return;
        }
    }

    private void zzo(Intent intent) {
        Bundle extras = intent.getExtras();
        extras.remove("message_type");
        extras.remove(PushConstants.SUPPORT_WAKE_LOCK_ID);
        if (zza.zzA(extras)) {
            if (zza.zzaR(this)) {
                zza.zzB(extras);
            } else {
                zza.zzaQ(this).zzC(extras);
                return;
            }
        }
        String string = extras.getString(PushConstants.FROM_ID);
        extras.remove(PushConstants.FROM_ID);
        zzz(extras);
        onMessageReceived(string, extras);
    }

    static void zzz(Bundle bundle) {
        Iterator it = bundle.keySet().iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str != null && str.startsWith("google.c.")) {
                it.remove();
            }
        }
    }

    public final IBinder onBind(Intent intent) {
        return null;
    }

    public void onDeletedMessages() {
    }

    public void onMessageReceived(String str, Bundle bundle) {
    }

    public void onMessageSent(String str) {
    }

    public void onSendError(String str, String str2) {
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        synchronized (this.zzpp) {
            this.zzaSJ = i2;
            this.zzaSK++;
        }
        if (intent == null) {
            zzBL();
            return 2;
        }
        zzl(intent);
        return 3;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean zzhE(int i) {
        return stopSelfResult(i);
    }
}
