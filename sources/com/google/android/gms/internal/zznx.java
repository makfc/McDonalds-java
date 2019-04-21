package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzg;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

final class zznx implements zzoo {
    private final Context mContext;
    private final Looper zzakW;
    private final zzof zzamm;
    private final zzoh zzamn;
    private final zzoh zzamo;
    private final Map<zzc<?>, zzoh> zzamp;
    private final Set<zzpa> zzamq = Collections.newSetFromMap(new WeakHashMap());
    private final zze zzamr;
    private Bundle zzams;
    private ConnectionResult zzamt = null;
    private ConnectionResult zzamu = null;
    private boolean zzamv = false;
    private final Lock zzamw;
    private int zzamx = 0;

    /* renamed from: com.google.android.gms.internal.zznx$1 */
    class C22401 implements Runnable {
        final /* synthetic */ zznx zzamy;

        public void run() {
            this.zzamy.zzamw.lock();
            try {
                this.zzamy.zzrQ();
            } finally {
                this.zzamy.zzamw.unlock();
            }
        }
    }

    private class zza implements com.google.android.gms.internal.zzoo.zza {
        private zza() {
        }

        /* synthetic */ zza(zznx zznx, C22401 c22401) {
            this();
        }

        public void zzc(int i, boolean z) {
            zznx.this.zzamw.lock();
            try {
                if (zznx.this.zzamv || zznx.this.zzamu == null || !zznx.this.zzamu.isSuccess()) {
                    zznx.this.zzamv = false;
                    zznx.this.zzb(i, z);
                    return;
                }
                zznx.this.zzamv = true;
                zznx.this.zzamo.onConnectionSuspended(i);
                zznx.this.zzamw.unlock();
            } finally {
                zznx.this.zzamw.unlock();
            }
        }

        public void zzd(@NonNull ConnectionResult connectionResult) {
            zznx.this.zzamw.lock();
            try {
                zznx.this.zzamt = connectionResult;
                zznx.this.zzrQ();
            } finally {
                zznx.this.zzamw.unlock();
            }
        }

        public void zzk(@Nullable Bundle bundle) {
            zznx.this.zzamw.lock();
            try {
                zznx.this.zzj(bundle);
                zznx.this.zzamt = ConnectionResult.zzakj;
                zznx.this.zzrQ();
            } finally {
                zznx.this.zzamw.unlock();
            }
        }
    }

    private class zzb implements com.google.android.gms.internal.zzoo.zza {
        private zzb() {
        }

        /* synthetic */ zzb(zznx zznx, C22401 c22401) {
            this();
        }

        public void zzc(int i, boolean z) {
            zznx.this.zzamw.lock();
            try {
                if (zznx.this.zzamv) {
                    zznx.this.zzamv = false;
                    zznx.this.zzb(i, z);
                    return;
                }
                zznx.this.zzamv = true;
                zznx.this.zzamn.onConnectionSuspended(i);
                zznx.this.zzamw.unlock();
            } finally {
                zznx.this.zzamw.unlock();
            }
        }

        public void zzd(@NonNull ConnectionResult connectionResult) {
            zznx.this.zzamw.lock();
            try {
                zznx.this.zzamu = connectionResult;
                zznx.this.zzrQ();
            } finally {
                zznx.this.zzamw.unlock();
            }
        }

        public void zzk(@Nullable Bundle bundle) {
            zznx.this.zzamw.lock();
            try {
                zznx.this.zzamu = ConnectionResult.zzakj;
                zznx.this.zzrQ();
            } finally {
                zznx.this.zzamw.unlock();
            }
        }
    }

    private zznx(Context context, zzof zzof, Lock lock, Looper looper, com.google.android.gms.common.zzc zzc, Map<zzc<?>, zze> map, Map<zzc<?>, zze> map2, zzg zzg, com.google.android.gms.common.api.Api.zza<? extends zztv, zztw> zza, zze zze, ArrayList<zznw> arrayList, ArrayList<zznw> arrayList2, Map<Api<?>, Integer> map3, Map<Api<?>, Integer> map4) {
        this.mContext = context;
        this.zzamm = zzof;
        this.zzamw = lock;
        this.zzakW = looper;
        this.zzamr = zze;
        this.zzamn = new zzoh(context, this.zzamm, lock, looper, zzc, map2, null, map4, null, arrayList2, new zza(this, null));
        this.zzamo = new zzoh(context, this.zzamm, lock, looper, zzc, map, zzg, map3, zza, arrayList, new zzb(this, null));
        ArrayMap arrayMap = new ArrayMap();
        for (zzc put : map2.keySet()) {
            arrayMap.put(put, this.zzamn);
        }
        for (zzc put2 : map.keySet()) {
            arrayMap.put(put2, this.zzamo);
        }
        this.zzamp = Collections.unmodifiableMap(arrayMap);
    }

    public static zznx zza(Context context, zzof zzof, Lock lock, Looper looper, com.google.android.gms.common.zzc zzc, Map<zzc<?>, zze> map, zzg zzg, Map<Api<?>, Integer> map2, com.google.android.gms.common.api.Api.zza<? extends zztv, zztw> zza, ArrayList<zznw> arrayList) {
        zze zze = null;
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        for (Entry entry : map.entrySet()) {
            zze zze2 = (zze) entry.getValue();
            if (zze2.zzps()) {
                zze = zze2;
            }
            if (zze2.zzpd()) {
                arrayMap.put((zzc) entry.getKey(), zze2);
            } else {
                arrayMap2.put((zzc) entry.getKey(), zze2);
            }
        }
        zzaa.zza(!arrayMap.isEmpty(), "CompositeGoogleApiClient should not be used without any APIs that require sign-in.");
        ArrayMap arrayMap3 = new ArrayMap();
        ArrayMap arrayMap4 = new ArrayMap();
        for (Api api : map2.keySet()) {
            zzc zzre = api.zzre();
            if (arrayMap.containsKey(zzre)) {
                arrayMap3.put(api, (Integer) map2.get(api));
            } else if (arrayMap2.containsKey(zzre)) {
                arrayMap4.put(api, (Integer) map2.get(api));
            } else {
                throw new IllegalStateException("Each API in the apiTypeMap must have a corresponding client in the clients map.");
            }
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            zznw zznw = (zznw) it.next();
            if (arrayMap3.containsKey(zznw.zzakT)) {
                arrayList2.add(zznw);
            } else if (arrayMap4.containsKey(zznw.zzakT)) {
                arrayList3.add(zznw);
            } else {
                throw new IllegalStateException("Each ClientCallbacks must have a corresponding API in the apiTypeMap");
            }
        }
        return new zznx(context, zzof, lock, looper, zzc, arrayMap, arrayMap2, zzg, zza, zze, arrayList2, arrayList3, arrayMap3, arrayMap4);
    }

    private void zzb(int i, boolean z) {
        this.zzamm.zzc(i, z);
        this.zzamu = null;
        this.zzamt = null;
    }

    private void zzb(ConnectionResult connectionResult) {
        switch (this.zzamx) {
            case 1:
                break;
            case 2:
                this.zzamm.zzd(connectionResult);
                break;
            default:
                Log.wtf("CompositeGAC", "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new Exception());
                break;
        }
        zzrS();
        this.zzamx = 0;
    }

    private static boolean zzc(ConnectionResult connectionResult) {
        return connectionResult != null && connectionResult.isSuccess();
    }

    private boolean zze(com.google.android.gms.internal.zznt.zza<? extends Result, ? extends com.google.android.gms.common.api.Api.zzb> zza) {
        zzc zzre = zza.zzre();
        zzaa.zzb(this.zzamp.containsKey(zzre), (Object) "GoogleApiClient is not configured to use the API required for this call.");
        return ((zzoh) this.zzamp.get(zzre)).equals(this.zzamo);
    }

    private void zzj(Bundle bundle) {
        if (this.zzams == null) {
            this.zzams = bundle;
        } else if (bundle != null) {
            this.zzams.putAll(bundle);
        }
    }

    private void zzrP() {
        this.zzamu = null;
        this.zzamt = null;
        this.zzamn.connect();
        this.zzamo.connect();
    }

    private void zzrQ() {
        if (zzc(this.zzamt)) {
            if (zzc(this.zzamu) || zzrT()) {
                zzrR();
            } else if (this.zzamu == null) {
            } else {
                if (this.zzamx == 1) {
                    zzrS();
                    return;
                }
                zzb(this.zzamu);
                this.zzamn.disconnect();
            }
        } else if (this.zzamt != null && zzc(this.zzamu)) {
            this.zzamo.disconnect();
            zzb(this.zzamt);
        } else if (this.zzamt != null && this.zzamu != null) {
            ConnectionResult connectionResult = this.zzamt;
            if (this.zzamo.zzanF < this.zzamn.zzanF) {
                connectionResult = this.zzamu;
            }
            zzb(connectionResult);
        }
    }

    private void zzrR() {
        switch (this.zzamx) {
            case 1:
                break;
            case 2:
                this.zzamm.zzk(this.zzams);
                break;
            default:
                Log.wtf("CompositeGAC", "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new AssertionError());
                break;
        }
        zzrS();
        this.zzamx = 0;
    }

    private void zzrS() {
        for (zzpa zzpr : this.zzamq) {
            zzpr.zzpr();
        }
        this.zzamq.clear();
    }

    private boolean zzrT() {
        return this.zzamu != null && this.zzamu.getErrorCode() == 4;
    }

    @Nullable
    private PendingIntent zzrU() {
        return this.zzamr == null ? null : PendingIntent.getActivity(this.mContext, this.zzamm.getSessionId(), this.zzamr.zzpt(), 134217728);
    }

    public ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public void connect() {
        this.zzamx = 2;
        this.zzamv = false;
        zzrP();
    }

    public void disconnect() {
        this.zzamu = null;
        this.zzamt = null;
        this.zzamx = 0;
        this.zzamn.disconnect();
        this.zzamo.disconnect();
        zzrS();
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append(str).append("authClient").println(":");
        this.zzamo.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
        printWriter.append(str).append("anonClient").println(":");
        this.zzamn.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
    }

    public boolean isConnected() {
        boolean z = true;
        this.zzamw.lock();
        try {
            if (!(this.zzamn.isConnected() && (zzrO() || zzrT() || this.zzamx == 1))) {
                z = false;
            }
            this.zzamw.unlock();
            return z;
        } catch (Throwable th) {
            this.zzamw.unlock();
        }
    }

    public boolean isConnecting() {
        this.zzamw.lock();
        try {
            boolean z = this.zzamx == 2;
            this.zzamw.unlock();
            return z;
        } catch (Throwable th) {
            this.zzamw.unlock();
        }
    }

    public <A extends com.google.android.gms.common.api.Api.zzb, R extends Result, T extends com.google.android.gms.internal.zznt.zza<R, A>> T zzc(@NonNull T t) {
        if (!zze((com.google.android.gms.internal.zznt.zza) t)) {
            return this.zzamn.zzc((com.google.android.gms.internal.zznt.zza) t);
        }
        if (!zzrT()) {
            return this.zzamo.zzc((com.google.android.gms.internal.zznt.zza) t);
        }
        t.zzx(new Status(4, null, zzrU()));
        return t;
    }

    public <A extends com.google.android.gms.common.api.Api.zzb, T extends com.google.android.gms.internal.zznt.zza<? extends Result, A>> T zzd(@NonNull T t) {
        if (!zze((com.google.android.gms.internal.zznt.zza) t)) {
            return this.zzamn.zzd(t);
        }
        if (!zzrT()) {
            return this.zzamo.zzd(t);
        }
        t.zzx(new Status(4, null, zzrU()));
        return t;
    }

    public void zzrN() {
        this.zzamn.zzrN();
        this.zzamo.zzrN();
    }

    public boolean zzrO() {
        return this.zzamo.isConnected();
    }
}
