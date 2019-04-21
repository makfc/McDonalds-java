package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzl implements Callback {
    private final Handler mHandler;
    private final zza zzarI;
    private final ArrayList<ConnectionCallbacks> zzarJ = new ArrayList();
    final ArrayList<ConnectionCallbacks> zzarK = new ArrayList();
    private final ArrayList<OnConnectionFailedListener> zzarL = new ArrayList();
    private volatile boolean zzarM = false;
    private final AtomicInteger zzarN = new AtomicInteger(0);
    private boolean zzarO = false;
    private final Object zzpp = new Object();

    public interface zza {
        boolean isConnected();

        Bundle zzqr();
    }

    public zzl(Looper looper, zza zza) {
        this.zzarI = zza;
        this.mHandler = new Handler(looper, this);
    }

    public boolean handleMessage(Message message) {
        if (message.what == 1) {
            ConnectionCallbacks connectionCallbacks = (ConnectionCallbacks) message.obj;
            synchronized (this.zzpp) {
                if (this.zzarM && this.zzarI.isConnected() && this.zzarJ.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(this.zzarI.zzqr());
                }
            }
            return true;
        }
        Log.wtf("GmsClientEvents", "Don't know how to handle message: " + message.what, new Exception());
        return false;
    }

    public void registerConnectionCallbacks(ConnectionCallbacks connectionCallbacks) {
        zzaa.zzz(connectionCallbacks);
        synchronized (this.zzpp) {
            if (this.zzarJ.contains(connectionCallbacks)) {
                String valueOf = String.valueOf(connectionCallbacks);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(valueOf).length() + 62).append("registerConnectionCallbacks(): listener ").append(valueOf).append(" is already registered").toString());
            } else {
                this.zzarJ.add(connectionCallbacks);
            }
        }
        if (this.zzarI.isConnected()) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(1, connectionCallbacks));
        }
    }

    public void registerConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
        zzaa.zzz(onConnectionFailedListener);
        synchronized (this.zzpp) {
            if (this.zzarL.contains(onConnectionFailedListener)) {
                String valueOf = String.valueOf(onConnectionFailedListener);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(valueOf).length() + 67).append("registerConnectionFailedListener(): listener ").append(valueOf).append(" is already registered").toString());
            } else {
                this.zzarL.add(onConnectionFailedListener);
            }
        }
    }

    public void unregisterConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
        zzaa.zzz(onConnectionFailedListener);
        synchronized (this.zzpp) {
            if (!this.zzarL.remove(onConnectionFailedListener)) {
                String valueOf = String.valueOf(onConnectionFailedListener);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(valueOf).length() + 57).append("unregisterConnectionFailedListener(): listener ").append(valueOf).append(" not found").toString());
            }
        }
    }

    public void zzcc(int i) {
        boolean z = false;
        if (Looper.myLooper() == this.mHandler.getLooper()) {
            z = true;
        }
        zzaa.zza(z, "onUnintentionalDisconnection must only be called on the Handler thread");
        this.mHandler.removeMessages(1);
        synchronized (this.zzpp) {
            this.zzarO = true;
            ArrayList arrayList = new ArrayList(this.zzarJ);
            int i2 = this.zzarN.get();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ConnectionCallbacks connectionCallbacks = (ConnectionCallbacks) it.next();
                if (!this.zzarM || this.zzarN.get() != i2) {
                    break;
                } else if (this.zzarJ.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnectionSuspended(i);
                }
            }
            this.zzarK.clear();
            this.zzarO = false;
        }
    }

    public void zzm(Bundle bundle) {
        boolean z = true;
        zzaa.zza(Looper.myLooper() == this.mHandler.getLooper(), "onConnectionSuccess must only be called on the Handler thread");
        synchronized (this.zzpp) {
            zzaa.zzai(!this.zzarO);
            this.mHandler.removeMessages(1);
            this.zzarO = true;
            if (this.zzarK.size() != 0) {
                z = false;
            }
            zzaa.zzai(z);
            ArrayList arrayList = new ArrayList(this.zzarJ);
            int i = this.zzarN.get();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ConnectionCallbacks connectionCallbacks = (ConnectionCallbacks) it.next();
                if (!this.zzarM || !this.zzarI.isConnected() || this.zzarN.get() != i) {
                    break;
                } else if (!this.zzarK.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(bundle);
                }
            }
            this.zzarK.clear();
            this.zzarO = false;
        }
    }

    /* JADX WARNING: Missing block: B:28:?, code skipped:
            return;
     */
    public void zzm(com.google.android.gms.common.ConnectionResult r6) {
        /*
        r5 = this;
        r1 = 1;
        r0 = android.os.Looper.myLooper();
        r2 = r5.mHandler;
        r2 = r2.getLooper();
        if (r0 != r2) goto L_0x0046;
    L_0x000d:
        r0 = r1;
    L_0x000e:
        r2 = "onConnectionFailure must only be called on the Handler thread";
        com.google.android.gms.common.internal.zzaa.zza(r0, r2);
        r0 = r5.mHandler;
        r0.removeMessages(r1);
        r1 = r5.zzpp;
        monitor-enter(r1);
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x0054 }
        r2 = r5.zzarL;	 Catch:{ all -> 0x0054 }
        r0.<init>(r2);	 Catch:{ all -> 0x0054 }
        r2 = r5.zzarN;	 Catch:{ all -> 0x0054 }
        r2 = r2.get();	 Catch:{ all -> 0x0054 }
        r3 = r0.iterator();	 Catch:{ all -> 0x0054 }
    L_0x002c:
        r0 = r3.hasNext();	 Catch:{ all -> 0x0054 }
        if (r0 == 0) goto L_0x0057;
    L_0x0032:
        r0 = r3.next();	 Catch:{ all -> 0x0054 }
        r0 = (com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener) r0;	 Catch:{ all -> 0x0054 }
        r4 = r5.zzarM;	 Catch:{ all -> 0x0054 }
        if (r4 == 0) goto L_0x0044;
    L_0x003c:
        r4 = r5.zzarN;	 Catch:{ all -> 0x0054 }
        r4 = r4.get();	 Catch:{ all -> 0x0054 }
        if (r4 == r2) goto L_0x0048;
    L_0x0044:
        monitor-exit(r1);	 Catch:{ all -> 0x0054 }
    L_0x0045:
        return;
    L_0x0046:
        r0 = 0;
        goto L_0x000e;
    L_0x0048:
        r4 = r5.zzarL;	 Catch:{ all -> 0x0054 }
        r4 = r4.contains(r0);	 Catch:{ all -> 0x0054 }
        if (r4 == 0) goto L_0x002c;
    L_0x0050:
        r0.onConnectionFailed(r6);	 Catch:{ all -> 0x0054 }
        goto L_0x002c;
    L_0x0054:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0054 }
        throw r0;
    L_0x0057:
        monitor-exit(r1);	 Catch:{ all -> 0x0054 }
        goto L_0x0045;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.zzl.zzm(com.google.android.gms.common.ConnectionResult):void");
    }

    public void zztI() {
        this.zzarM = false;
        this.zzarN.incrementAndGet();
    }

    public void zztJ() {
        this.zzarM = true;
    }
}
