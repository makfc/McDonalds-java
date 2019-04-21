package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.zzc;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class zzoh implements zzoo {
    private final Context mContext;
    final com.google.android.gms.common.api.Api.zza<? extends zztv, zztw> zzalo;
    private final zzc zzamE;
    final zzg zzamS;
    final Map<Api<?>, Integer> zzamT;
    final zzof zzamm;
    private final Lock zzamw;
    private final Condition zzanA;
    private final zzb zzanB;
    final Map<Api.zzc<?>, ConnectionResult> zzanC = new HashMap();
    private volatile zzog zzanD;
    private ConnectionResult zzanE = null;
    int zzanF;
    final com.google.android.gms.internal.zzoo.zza zzanG;
    final Map<Api.zzc<?>, zze> zzann;

    static abstract class zza {
        private final zzog zzanH;

        protected zza(zzog zzog) {
            this.zzanH = zzog;
        }

        public final void zzd(zzoh zzoh) {
            zzoh.zzamw.lock();
            try {
                if (zzoh.zzanD == this.zzanH) {
                    zzrX();
                    zzoh.zzamw.unlock();
                }
            } finally {
                zzoh.zzamw.unlock();
            }
        }

        public abstract void zzrX();
    }

    final class zzb extends Handler {
        zzb(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    ((zza) message.obj).zzd(zzoh.this);
                    return;
                case 2:
                    throw ((RuntimeException) message.obj);
                default:
                    Log.w("GACStateManager", "Unknown message id: " + message.what);
                    return;
            }
        }
    }

    public zzoh(Context context, zzof zzof, Lock lock, Looper looper, zzc zzc, Map<Api.zzc<?>, zze> map, zzg zzg, Map<Api<?>, Integer> map2, com.google.android.gms.common.api.Api.zza<? extends zztv, zztw> zza, ArrayList<zznw> arrayList, com.google.android.gms.internal.zzoo.zza zza2) {
        this.mContext = context;
        this.zzamw = lock;
        this.zzamE = zzc;
        this.zzann = map;
        this.zzamS = zzg;
        this.zzamT = map2;
        this.zzalo = zza;
        this.zzamm = zzof;
        this.zzanG = zza2;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((zznw) it.next()).zza(this);
        }
        this.zzanB = new zzb(looper);
        this.zzanA = lock.newCondition();
        this.zzanD = new zzoe(this);
    }

    public ConnectionResult blockingConnect(long j, TimeUnit timeUnit) {
        connect();
        long toNanos = timeUnit.toNanos(j);
        while (isConnecting()) {
            if (toNanos <= 0) {
                try {
                    disconnect();
                    return new ConnectionResult(14, null);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return new ConnectionResult(15, null);
                }
            }
            toNanos = this.zzanA.awaitNanos(toNanos);
        }
        return isConnected() ? ConnectionResult.zzakj : this.zzanE != null ? this.zzanE : new ConnectionResult(13, null);
    }

    public void connect() {
        this.zzanD.connect();
    }

    public void disconnect() {
        if (this.zzanD.disconnect()) {
            this.zzanC.clear();
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String concat = String.valueOf(str).concat("  ");
        for (Api api : this.zzamT.keySet()) {
            printWriter.append(str).append(api.getName()).println(":");
            ((zze) this.zzann.get(api.zzre())).dump(concat, fileDescriptor, printWriter, strArr);
        }
    }

    public boolean isConnected() {
        return this.zzanD instanceof zzoc;
    }

    public boolean isConnecting() {
        return this.zzanD instanceof zzod;
    }

    public void onConnected(@Nullable Bundle bundle) {
        this.zzamw.lock();
        try {
            this.zzanD.onConnected(bundle);
        } finally {
            this.zzamw.unlock();
        }
    }

    public void onConnectionSuspended(int i) {
        this.zzamw.lock();
        try {
            this.zzanD.onConnectionSuspended(i);
        } finally {
            this.zzamw.unlock();
        }
    }

    public void zza(@NonNull ConnectionResult connectionResult, @NonNull Api<?> api, int i) {
        this.zzamw.lock();
        try {
            this.zzanD.zza(connectionResult, api, i);
        } finally {
            this.zzamw.unlock();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void zza(zza zza) {
        this.zzanB.sendMessage(this.zzanB.obtainMessage(1, zza));
    }

    /* Access modifiers changed, original: 0000 */
    public void zza(RuntimeException runtimeException) {
        this.zzanB.sendMessage(this.zzanB.obtainMessage(2, runtimeException));
    }

    public <A extends com.google.android.gms.common.api.Api.zzb, R extends Result, T extends com.google.android.gms.internal.zznt.zza<R, A>> T zzc(@NonNull T t) {
        t.zzrI();
        return this.zzanD.zzc(t);
    }

    public <A extends com.google.android.gms.common.api.Api.zzb, T extends com.google.android.gms.internal.zznt.zza<? extends Result, A>> T zzd(@NonNull T t) {
        t.zzrI();
        return this.zzanD.zzd(t);
    }

    /* Access modifiers changed, original: 0000 */
    public void zzi(ConnectionResult connectionResult) {
        this.zzamw.lock();
        try {
            this.zzanE = connectionResult;
            this.zzanD = new zzoe(this);
            this.zzanD.begin();
            this.zzanA.signalAll();
        } finally {
            this.zzamw.unlock();
        }
    }

    public void zzrN() {
        if (isConnected()) {
            ((zzoc) this.zzanD).zzrW();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void zzsm() {
        this.zzamw.lock();
        try {
            this.zzanD = new zzod(this, this.zzamS, this.zzamT, this.zzamE, this.zzalo, this.zzamw, this.mContext);
            this.zzanD.begin();
            this.zzanA.signalAll();
        } finally {
            this.zzamw.unlock();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void zzsn() {
        this.zzamw.lock();
        try {
            this.zzamm.zzsj();
            this.zzanD = new zzoc(this);
            this.zzanD.begin();
            this.zzanA.signalAll();
        } finally {
            this.zzamw.unlock();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void zzso() {
        for (zze disconnect : this.zzann.values()) {
            disconnect.disconnect();
        }
    }
}
