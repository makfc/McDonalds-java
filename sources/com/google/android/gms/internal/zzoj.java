package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.p000v4.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Api.zzh;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzag;
import com.google.android.gms.common.internal.zzd.zzf;
import com.google.android.gms.common.internal.zzg;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class zzoj implements Callback {
    private static zzoj zzanL;
    private static final Object zzrs = new Object();
    private final Context mContext;
    private final Handler mHandler;
    private final GoogleApiAvailability zzaln;
    private long zzanK;
    private int zzanM;
    private final SparseArray<zzc<?>> zzanO;
    private final Map<zznq<?>, zzc<?>> zzanP;
    private zzny zzanQ;
    private final Set<zznq<?>> zzanR;
    private final ReferenceQueue<com.google.android.gms.common.api.zzc<?>> zzanS;
    private final SparseArray<zza> zzanT;
    private zzb zzanU;
    private long zzanj;
    private long zzank;

    private final class zza extends PhantomReference<com.google.android.gms.common.api.zzc<?>> {
        private final int zzalC;

        public zza(com.google.android.gms.common.api.zzc zzc, int i, ReferenceQueue<com.google.android.gms.common.api.zzc<?>> referenceQueue) {
            super(zzc, referenceQueue);
            this.zzalC = i;
        }

        public void zzst() {
            zzoj.this.mHandler.sendMessage(zzoj.this.mHandler.obtainMessage(2, this.zzalC, 2));
        }
    }

    private static final class zzb extends Thread {
        private final ReferenceQueue<com.google.android.gms.common.api.zzc<?>> zzanS;
        private final SparseArray<zza> zzanT;
        private final AtomicBoolean zzanW = new AtomicBoolean();

        public zzb(ReferenceQueue<com.google.android.gms.common.api.zzc<?>> referenceQueue, SparseArray<zza> sparseArray) {
            super("GoogleApiCleanup");
            this.zzanS = referenceQueue;
            this.zzanT = sparseArray;
        }

        public void run() {
            this.zzanW.set(true);
            Process.setThreadPriority(10);
            while (this.zzanW.get()) {
                try {
                    zza zza = (zza) this.zzanS.remove();
                    this.zzanT.remove(zza.zzalC);
                    zza.zzst();
                } catch (InterruptedException e) {
                } finally {
                    this.zzanW.set(false);
                }
            }
        }
    }

    private class zzc<O extends ApiOptions> implements ConnectionCallbacks, OnConnectionFailedListener {
        private final zznq<O> zzakV;
        private final Queue<zznp> zzanX = new LinkedList();
        private final zze zzanY;
        private final com.google.android.gms.common.api.Api.zzb zzanZ;
        private boolean zzani;
        private final SparseArray<zzpf> zzaoa = new SparseArray();
        private final Set<zzns> zzaob = new HashSet();
        private final SparseArray<Map<Object, com.google.android.gms.internal.zznt.zza>> zzaoc = new SparseArray();
        private ConnectionResult zzaod = null;

        @WorkerThread
        public zzc(com.google.android.gms.common.api.zzc<O> zzc) {
            this.zzanY = zzb((com.google.android.gms.common.api.zzc) zzc);
            if (this.zzanY instanceof zzag) {
                this.zzanZ = ((zzag) this.zzanY).zztX();
            } else {
                this.zzanZ = this.zzanY;
            }
            this.zzakV = zzc.zzrn();
        }

        @WorkerThread
        private void connect() {
            if (!this.zzanY.isConnected() && !this.zzanY.isConnecting()) {
                if (this.zzanY.zzrg() && zzoj.this.zzanM != 0) {
                    zzoj.this.zzanM = zzoj.this.zzaln.isGooglePlayServicesAvailable(zzoj.this.mContext);
                    if (zzoj.this.zzanM != 0) {
                        onConnectionFailed(new ConnectionResult(zzoj.this.zzanM, null));
                        return;
                    }
                }
                this.zzanY.zza(new zzd(this.zzanY, this.zzakV));
            }
        }

        @WorkerThread
        private void resume() {
            if (this.zzani) {
                connect();
            }
        }

        @WorkerThread
        private zze zzb(com.google.android.gms.common.api.zzc zzc) {
            Api zzrl = zzc.zzrl();
            if (!zzrl.zzrf()) {
                return zzc.zzrl().zzrc().zza(zzc.getApplicationContext(), zzoj.this.mHandler.getLooper(), zzg.zzau(zzc.getApplicationContext()), zzc.zzrm(), this, this);
            }
            zzh zzrd = zzrl.zzrd();
            return new zzag(zzc.getApplicationContext(), zzoj.this.mHandler.getLooper(), zzrd.zzri(), this, this, zzg.zzau(zzc.getApplicationContext()), zzrd.zzr(zzc.zzrm()));
        }

        @WorkerThread
        private void zzc(zznp zznp) {
            zznp.zza(this.zzaoa);
            Map map;
            if (zznp.zzagd == 3) {
                try {
                    Map map2;
                    map = (Map) this.zzaoc.get(zznp.zzalC);
                    if (map == null) {
                        ArrayMap arrayMap = new ArrayMap(1);
                        this.zzaoc.put(zznp.zzalC, arrayMap);
                        map2 = arrayMap;
                    } else {
                        map2 = map;
                    }
                    com.google.android.gms.internal.zznt.zza zza = ((com.google.android.gms.internal.zznp.zza) zznp).zzalD;
                    map2.put(((zzot) zza).zzsG(), zza);
                } catch (ClassCastException e) {
                    throw new IllegalStateException("Listener registration methods must implement ListenerApiMethod");
                }
            } else if (zznp.zzagd == 4) {
                try {
                    map = (Map) this.zzaoc.get(zznp.zzalC);
                    zzot zzot = (zzot) ((com.google.android.gms.internal.zznp.zza) zznp).zzalD;
                    if (map != null) {
                        map.remove(zzot.zzsG());
                    } else {
                        Log.w("GoogleApiManager", "Received call to unregister a listener without a matching registration call.");
                    }
                } catch (ClassCastException e2) {
                    throw new IllegalStateException("Listener unregistration methods must implement ListenerApiMethod");
                }
            }
            try {
                zznp.zzb(this.zzanZ);
            } catch (DeadObjectException e3) {
                this.zzanY.disconnect();
                onConnectionSuspended(1);
            }
        }

        @WorkerThread
        private void zzj(ConnectionResult connectionResult) {
            for (zzns zza : this.zzaob) {
                zza.zza(this.zzakV, connectionResult);
            }
            this.zzaob.clear();
        }

        @WorkerThread
        private void zzsh() {
            if (this.zzani) {
                zzsx();
                zzz(zzoj.this.zzaln.isGooglePlayServicesAvailable(zzoj.this.mContext) == 18 ? new Status(8, "Connection timed out while waiting for Google Play services update to complete.") : new Status(8, "API failed to connect while resuming due to an unknown error."));
                this.zzanY.disconnect();
            }
        }

        @WorkerThread
        private void zzsx() {
            if (this.zzani) {
                zzoj.this.mHandler.removeMessages(9, this.zzakV);
                zzoj.this.mHandler.removeMessages(8, this.zzakV);
                this.zzani = false;
            }
        }

        private void zzsy() {
            zzoj.this.mHandler.removeMessages(10, this.zzakV);
            zzoj.this.mHandler.sendMessageDelayed(zzoj.this.mHandler.obtainMessage(10, this.zzakV), zzoj.this.zzanK);
        }

        private void zzsz() {
            if (this.zzanY.isConnected() && this.zzaoc.size() == 0) {
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= this.zzaoa.size()) {
                        this.zzanY.disconnect();
                        return;
                    } else if (((zzpf) this.zzaoa.get(this.zzaoa.keyAt(i2))).zzsM()) {
                        zzsy();
                        return;
                    } else {
                        i = i2 + 1;
                    }
                }
            }
        }

        @WorkerThread
        private void zzz(Status status) {
            for (zznp zzv : this.zzanX) {
                zzv.zzv(status);
            }
            this.zzanX.clear();
        }

        /* Access modifiers changed, original: 0000 */
        public boolean isConnected() {
            return this.zzanY.isConnected();
        }

        @WorkerThread
        public void onConnected(@Nullable Bundle bundle) {
            zzsv();
            zzj(ConnectionResult.zzakj);
            zzsx();
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < this.zzaoc.size()) {
                    for (com.google.android.gms.internal.zznt.zza zzb : ((Map) this.zzaoc.get(this.zzaoc.keyAt(i2))).values()) {
                        try {
                            zzb.zzb(this.zzanZ);
                        } catch (DeadObjectException e) {
                            this.zzanY.disconnect();
                            onConnectionSuspended(1);
                        }
                    }
                    i = i2 + 1;
                } else {
                    zzsu();
                    zzsy();
                    return;
                }
            }
        }

        /* JADX WARNING: Missing block: B:19:0x004e, code skipped:
            if (r5.zzanV.zzc(r6, r0) != false) goto L_?;
     */
        /* JADX WARNING: Missing block: B:21:0x0056, code skipped:
            if (r6.getErrorCode() != 18) goto L_0x005b;
     */
        /* JADX WARNING: Missing block: B:22:0x0058, code skipped:
            r5.zzani = true;
     */
        /* JADX WARNING: Missing block: B:24:0x005d, code skipped:
            if (r5.zzani == false) goto L_0x007d;
     */
        /* JADX WARNING: Missing block: B:25:0x005f, code skipped:
            com.google.android.gms.internal.zzoj.zza(r5.zzanV).sendMessageDelayed(android.os.Message.obtain(com.google.android.gms.internal.zzoj.zza(r5.zzanV), 8, r5.zzakV), com.google.android.gms.internal.zzoj.zzb(r5.zzanV));
     */
        /* JADX WARNING: Missing block: B:26:0x007d, code skipped:
            r2 = java.lang.String.valueOf(r5.zzakV.zzrz());
            zzz(new com.google.android.gms.common.api.Status(17, new java.lang.StringBuilder(java.lang.String.valueOf(r2).length() + 38).append("API: ").append(r2).append(" is not available on this device.").toString()));
     */
        /* JADX WARNING: Missing block: B:29:?, code skipped:
            return;
     */
        /* JADX WARNING: Missing block: B:30:?, code skipped:
            return;
     */
        /* JADX WARNING: Missing block: B:31:?, code skipped:
            return;
     */
        @android.support.annotation.WorkerThread
        public void onConnectionFailed(@android.support.annotation.NonNull com.google.android.gms.common.ConnectionResult r6) {
            /*
            r5 = this;
            r5.zzsv();
            r0 = com.google.android.gms.internal.zzoj.this;
            r1 = -1;
            r0.zzanM = r1;
            r5.zzj(r6);
            r0 = r5.zzaoa;
            r1 = 0;
            r0 = r0.keyAt(r1);
            r1 = r5.zzanX;
            r1 = r1.isEmpty();
            if (r1 == 0) goto L_0x001e;
        L_0x001b:
            r5.zzaod = r6;
        L_0x001d:
            return;
        L_0x001e:
            r1 = com.google.android.gms.internal.zzoj.zzrs;
            monitor-enter(r1);
            r2 = com.google.android.gms.internal.zzoj.this;	 Catch:{ all -> 0x0044 }
            r2 = null;	 Catch:{ all -> 0x0044 }
            if (r2 == 0) goto L_0x0047;
        L_0x002b:
            r2 = com.google.android.gms.internal.zzoj.this;	 Catch:{ all -> 0x0044 }
            r2 = r2.zzanR;	 Catch:{ all -> 0x0044 }
            r3 = r5.zzakV;	 Catch:{ all -> 0x0044 }
            r2 = r2.contains(r3);	 Catch:{ all -> 0x0044 }
            if (r2 == 0) goto L_0x0047;
        L_0x0039:
            r2 = com.google.android.gms.internal.zzoj.this;	 Catch:{ all -> 0x0044 }
            r2 = null;	 Catch:{ all -> 0x0044 }
            r2.zzb(r6, r0);	 Catch:{ all -> 0x0044 }
            monitor-exit(r1);	 Catch:{ all -> 0x0044 }
            goto L_0x001d;
        L_0x0044:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0044 }
            throw r0;
        L_0x0047:
            monitor-exit(r1);	 Catch:{ all -> 0x0044 }
            r1 = com.google.android.gms.internal.zzoj.this;
            r0 = r1.zzc(r6, r0);
            if (r0 != 0) goto L_0x001d;
        L_0x0050:
            r0 = r6.getErrorCode();
            r1 = 18;
            if (r0 != r1) goto L_0x005b;
        L_0x0058:
            r0 = 1;
            r5.zzani = r0;
        L_0x005b:
            r0 = r5.zzani;
            if (r0 == 0) goto L_0x007d;
        L_0x005f:
            r0 = com.google.android.gms.internal.zzoj.this;
            r0 = r0.mHandler;
            r1 = com.google.android.gms.internal.zzoj.this;
            r1 = r1.mHandler;
            r2 = 8;
            r3 = r5.zzakV;
            r1 = android.os.Message.obtain(r1, r2, r3);
            r2 = com.google.android.gms.internal.zzoj.this;
            r2 = r2.zzank;
            r0.sendMessageDelayed(r1, r2);
            goto L_0x001d;
        L_0x007d:
            r0 = new com.google.android.gms.common.api.Status;
            r1 = 17;
            r2 = r5.zzakV;
            r2 = r2.zzrz();
            r2 = java.lang.String.valueOf(r2);
            r3 = new java.lang.StringBuilder;
            r4 = java.lang.String.valueOf(r2);
            r4 = r4.length();
            r4 = r4 + 38;
            r3.<init>(r4);
            r4 = "API: ";
            r3 = r3.append(r4);
            r2 = r3.append(r2);
            r3 = " is not available on this device.";
            r2 = r2.append(r3);
            r2 = r2.toString();
            r0.<init>(r1, r2);
            r5.zzz(r0);
            goto L_0x001d;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzoj$zzc.onConnectionFailed(com.google.android.gms.common.ConnectionResult):void");
        }

        @WorkerThread
        public void onConnectionSuspended(int i) {
            zzsv();
            this.zzani = true;
            zzoj.this.mHandler.sendMessageDelayed(Message.obtain(zzoj.this.mHandler, 8, this.zzakV), zzoj.this.zzank);
            zzoj.this.mHandler.sendMessageDelayed(Message.obtain(zzoj.this.mHandler, 9, this.zzakV), zzoj.this.zzanj);
            zzoj.this.zzanM = -1;
        }

        @WorkerThread
        public void zzb(zznp zznp) {
            if (this.zzanY.isConnected()) {
                zzc(zznp);
                zzsy();
                return;
            }
            this.zzanX.add(zznp);
            if (this.zzaod == null || !this.zzaod.hasResolution()) {
                connect();
            } else {
                onConnectionFailed(this.zzaod);
            }
        }

        @WorkerThread
        public void zzb(zzns zzns) {
            this.zzaob.add(zzns);
        }

        @WorkerThread
        public void zzbL(int i) {
            this.zzaoa.put(i, new zzpf(this.zzakV.zzre(), this.zzanY));
        }

        @WorkerThread
        public void zzf(int i, boolean z) {
            Iterator it = this.zzanX.iterator();
            while (it.hasNext()) {
                zznp zznp = (zznp) it.next();
                if (zznp.zzalC == i && zznp.zzagd != 1 && zznp.cancel()) {
                    it.remove();
                }
            }
            ((zzpf) this.zzaoa.get(i)).release();
            this.zzaoc.delete(i);
            if (!z) {
                this.zzaoa.remove(i);
                zzoj.this.zzanT.remove(i);
                if (this.zzaoa.size() == 0 && this.zzanX.isEmpty()) {
                    zzsx();
                    this.zzanY.disconnect();
                    zzoj.this.zzanP.remove(this.zzakV);
                    synchronized (zzoj.zzrs) {
                        zzoj.this.zzanR.remove(this.zzakV);
                    }
                }
            }
        }

        @WorkerThread
        public void zzsu() {
            while (this.zzanY.isConnected() && !this.zzanX.isEmpty()) {
                zzc((zznp) this.zzanX.remove());
            }
        }

        @WorkerThread
        public void zzsv() {
            this.zzaod = null;
        }

        /* Access modifiers changed, original: 0000 */
        public ConnectionResult zzsw() {
            return this.zzaod;
        }
    }

    private class zzd implements zzf {
        private final zznq<?> zzakV;
        private final zze zzanY;

        public zzd(zze zze, zznq<?> zznq) {
            this.zzanY = zze;
            this.zzakV = zznq;
        }

        @WorkerThread
        public void zzh(@NonNull ConnectionResult connectionResult) {
            if (connectionResult.isSuccess()) {
                this.zzanY.zza(null, Collections.emptySet());
            } else {
                ((zzc) zzoj.this.zzanP.get(this.zzakV)).onConnectionFailed(connectionResult);
            }
        }
    }

    @WorkerThread
    private void zza(com.google.android.gms.common.api.zzc<?> zzc, int i) {
        zznq zzrn = zzc.zzrn();
        if (!this.zzanP.containsKey(zzrn)) {
            this.zzanP.put(zzrn, new zzc(zzc));
        }
        zzc zzc2 = (zzc) this.zzanP.get(zzrn);
        zzc2.zzbL(i);
        this.zzanO.put(i, zzc2);
        zzc2.connect();
        this.zzanT.put(i, new zza(zzc, i, this.zzanS));
        if (this.zzanU == null || !this.zzanU.zzanW.get()) {
            this.zzanU = new zzb(this.zzanS, this.zzanT);
            this.zzanU.start();
        }
    }

    @WorkerThread
    private void zza(zznp zznp) {
        ((zzc) this.zzanO.get(zznp.zzalC)).zzb(zznp);
    }

    @WorkerThread
    private void zze(int i, boolean z) {
        zzc zzc = (zzc) this.zzanO.get(i);
        if (zzc != null) {
            if (!z) {
                this.zzanO.delete(i);
            }
            zzc.zzf(i, z);
            return;
        }
        Log.wtf("GoogleApiManager", "onRelease received for unknown instance: " + i, new Exception());
    }

    public static zzoj zzsq() {
        zzoj zzoj;
        synchronized (zzrs) {
            zzoj = zzanL;
        }
        return zzoj;
    }

    @WorkerThread
    private void zzsr() {
        for (zzc zzc : this.zzanP.values()) {
            zzc.zzsv();
            zzc.connect();
        }
    }

    @WorkerThread
    public boolean handleMessage(Message message) {
        boolean z = false;
        switch (message.what) {
            case 1:
                zza((zzns) message.obj);
                break;
            case 2:
            case 7:
                int i = message.arg1;
                if (message.arg2 == 1) {
                    z = true;
                }
                zze(i, z);
                break;
            case 3:
                zzsr();
                break;
            case 4:
                zza((zznp) message.obj);
                break;
            case 5:
                if (this.zzanO.get(message.arg1) != null) {
                    ((zzc) this.zzanO.get(message.arg1)).zzz(new Status(17, "Error resolution was canceled by the user."));
                    break;
                }
                break;
            case 6:
                zza((com.google.android.gms.common.api.zzc) message.obj, message.arg1);
                break;
            case 8:
                if (this.zzanP.containsKey(message.obj)) {
                    ((zzc) this.zzanP.get(message.obj)).resume();
                    break;
                }
                break;
            case 9:
                if (this.zzanP.containsKey(message.obj)) {
                    ((zzc) this.zzanP.get(message.obj)).zzsh();
                    break;
                }
                break;
            case 10:
                if (this.zzanP.containsKey(message.obj)) {
                    ((zzc) this.zzanP.get(message.obj)).zzsz();
                    break;
                }
                break;
            default:
                Log.w("GoogleApiManager", "Unknown message id: " + message.what);
                return false;
        }
        return true;
    }

    public void zza(ConnectionResult connectionResult, int i) {
        if (!zzc(connectionResult, i)) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(5, i, 0));
        }
    }

    public <O extends ApiOptions> void zza(com.google.android.gms.common.api.zzc<O> zzc, int i, com.google.android.gms.internal.zznt.zza<? extends Result, com.google.android.gms.common.api.Api.zzb> zza) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, new com.google.android.gms.internal.zznp.zza(zzc.getInstanceId(), i, zza)));
    }

    @WorkerThread
    public void zza(zzns zzns) {
        for (zznq zznq : zzns.zzrC()) {
            zzc zzc = (zzc) this.zzanP.get(zznq);
            if (zzc == null) {
                zzns.cancel();
                return;
            } else if (zzc.isConnected()) {
                zzns.zza(zznq, ConnectionResult.zzakj);
            } else if (zzc.zzsw() != null) {
                zzns.zza(zznq, zzc.zzsw());
            } else {
                zzc.zzb(zzns);
            }
        }
    }

    public void zza(zzny zzny) {
        synchronized (zzrs) {
            if (zzny == null) {
                this.zzanQ = null;
                this.zzanR.clear();
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean zzc(ConnectionResult connectionResult, int i) {
        if (!connectionResult.hasResolution() && !this.zzaln.isUserResolvableError(connectionResult.getErrorCode())) {
            return false;
        }
        this.zzaln.zza(this.mContext, connectionResult, i);
        return true;
    }

    public void zzd(int i, boolean z) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(7, i, z ? 1 : 2));
    }

    public void zzrA() {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3));
    }
}
