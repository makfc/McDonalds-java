package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.signin.internal.SignInResponse;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;

public class zzod implements zzog {
    private final Context mContext;
    private final com.google.android.gms.common.api.Api.zza<? extends zztv, zztw> zzalo;
    private final zzoh zzamB;
    private final com.google.android.gms.common.zzc zzamE;
    private ConnectionResult zzamF;
    private int zzamG;
    private int zzamH = 0;
    private int zzamI;
    private final Bundle zzamJ = new Bundle();
    private final Set<com.google.android.gms.common.api.Api.zzc> zzamK = new HashSet();
    private zztv zzamL;
    private int zzamM;
    private boolean zzamN;
    private boolean zzamO;
    private zzq zzamP;
    private boolean zzamQ;
    private boolean zzamR;
    private final zzg zzamS;
    private final Map<Api<?>, Integer> zzamT;
    private ArrayList<Future<?>> zzamU = new ArrayList();
    private final Lock zzamw;

    /* renamed from: com.google.android.gms.internal.zzod$1 */
    class C22431 implements Runnable {
        C22431() {
        }

        public void run() {
            zzod.this.zzamE.zzag(zzod.this.mContext);
        }
    }

    private static class zza implements com.google.android.gms.common.internal.zzd.zzf {
        private final Api<?> zzakT;
        private final WeakReference<zzod> zzamW;
        private final int zzamk;

        public zza(zzod zzod, Api<?> api, int i) {
            this.zzamW = new WeakReference(zzod);
            this.zzakT = api;
            this.zzamk = i;
        }

        public void zzh(@NonNull ConnectionResult connectionResult) {
            boolean z = false;
            zzod zzod = (zzod) this.zzamW.get();
            if (zzod != null) {
                if (Looper.myLooper() == zzod.zzamB.zzamm.getLooper()) {
                    z = true;
                }
                zzaa.zza(z, "onReportServiceBinding must be called on the GoogleApiClient handler thread");
                zzod.zzamw.lock();
                try {
                    if (zzod.zzbH(0)) {
                        if (!connectionResult.isSuccess()) {
                            zzod.zzb(connectionResult, this.zzakT, this.zzamk);
                        }
                        if (zzod.zzrY()) {
                            zzod.zzrZ();
                        }
                        zzod.zzamw.unlock();
                    }
                } finally {
                    zzod.zzamw.unlock();
                }
            }
        }
    }

    private abstract class zzf implements Runnable {
        private zzf() {
        }

        /* synthetic */ zzf(zzod zzod, C22431 c22431) {
            this();
        }

        @WorkerThread
        public void run() {
            zzod.this.zzamw.lock();
            try {
                if (!Thread.interrupted()) {
                    zzrX();
                    zzod.this.zzamw.unlock();
                }
            } catch (RuntimeException e) {
                zzod.this.zzamB.zza(e);
            } finally {
                zzod.this.zzamw.unlock();
            }
        }

        @WorkerThread
        public abstract void zzrX();
    }

    private class zzb extends zzf {
        private final Map<com.google.android.gms.common.api.Api.zze, zza> zzamX;

        public zzb(Map<com.google.android.gms.common.api.Api.zze, zza> map) {
            super(zzod.this, null);
            this.zzamX = map;
        }

        @WorkerThread
        public void zzrX() {
            int i;
            int i2 = 1;
            int i3 = 0;
            int i4 = 1;
            int i5 = 0;
            for (com.google.android.gms.common.api.Api.zze zze : this.zzamX.keySet()) {
                if (!zze.zzrg()) {
                    i = 0;
                    i4 = i5;
                } else if (((zza) this.zzamX.get(zze)).zzamk == 0) {
                    i = 1;
                    break;
                } else {
                    i = i4;
                    i4 = 1;
                }
                i5 = i4;
                i4 = i;
            }
            i2 = i5;
            i = 0;
            if (i2 != 0) {
                i3 = zzod.this.zzamE.isGooglePlayServicesAvailable(zzod.this.mContext);
            }
            if (i3 == 0 || (r0 == 0 && i4 == 0)) {
                if (zzod.this.zzamN) {
                    zzod.this.zzamL.connect();
                }
                for (com.google.android.gms.common.api.Api.zze zze2 : this.zzamX.keySet()) {
                    final com.google.android.gms.common.internal.zzd.zzf zzf = (com.google.android.gms.common.internal.zzd.zzf) this.zzamX.get(zze2);
                    if (!zze2.zzrg() || i3 == 0) {
                        zze2.zza(zzf);
                    } else {
                        zzod.this.zzamB.zza(new zza(zzod.this) {
                            public void zzrX() {
                                zzf.zzh(new ConnectionResult(16, null));
                            }
                        });
                    }
                }
                return;
            }
            final ConnectionResult connectionResult = new ConnectionResult(i3, null);
            zzod.this.zzamB.zza(new zza(zzod.this) {
                public void zzrX() {
                    zzod.this.zzg(connectionResult);
                }
            });
        }
    }

    private class zzc extends zzf {
        private final ArrayList<com.google.android.gms.common.api.Api.zze> zzanb;

        public zzc(ArrayList<com.google.android.gms.common.api.Api.zze> arrayList) {
            super(zzod.this, null);
            this.zzanb = arrayList;
        }

        @WorkerThread
        public void zzrX() {
            zzod.this.zzamB.zzamm.zzano = zzod.this.zzse();
            Iterator it = this.zzanb.iterator();
            while (it.hasNext()) {
                ((com.google.android.gms.common.api.Api.zze) it.next()).zza(zzod.this.zzamP, zzod.this.zzamB.zzamm.zzano);
            }
        }
    }

    private static class zzd extends com.google.android.gms.signin.internal.zzb {
        private final WeakReference<zzod> zzamW;

        zzd(zzod zzod) {
            this.zzamW = new WeakReference(zzod);
        }

        @BinderThread
        public void zzb(final SignInResponse signInResponse) {
            final zzod zzod = (zzod) this.zzamW.get();
            if (zzod != null) {
                zzod.zzamB.zza(new zza(zzod) {
                    public void zzrX() {
                        zzod.zza(signInResponse);
                    }
                });
            }
        }
    }

    private class zze implements ConnectionCallbacks, OnConnectionFailedListener {
        private zze() {
        }

        /* synthetic */ zze(zzod zzod, C22431 c22431) {
            this();
        }

        public void onConnected(Bundle bundle) {
            zzod.this.zzamL.zza(new zzd(zzod.this));
        }

        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            zzod.this.zzamw.lock();
            try {
                if (zzod.this.zzf(connectionResult)) {
                    zzod.this.zzsc();
                    zzod.this.zzrZ();
                } else {
                    zzod.this.zzg(connectionResult);
                }
                zzod.this.zzamw.unlock();
            } catch (Throwable th) {
                zzod.this.zzamw.unlock();
            }
        }

        public void onConnectionSuspended(int i) {
        }
    }

    public zzod(zzoh zzoh, zzg zzg, Map<Api<?>, Integer> map, com.google.android.gms.common.zzc zzc, com.google.android.gms.common.api.Api.zza<? extends zztv, zztw> zza, Lock lock, Context context) {
        this.zzamB = zzoh;
        this.zzamS = zzg;
        this.zzamT = map;
        this.zzamE = zzc;
        this.zzalo = zza;
        this.zzamw = lock;
        this.mContext = context;
    }

    private void zza(SignInResponse signInResponse) {
        if (zzbH(0)) {
            ConnectionResult zztR = signInResponse.zztR();
            if (zztR.isSuccess()) {
                ResolveAccountResponse zzIY = signInResponse.zzIY();
                ConnectionResult zztR2 = zzIY.zztR();
                if (zztR2.isSuccess()) {
                    this.zzamO = true;
                    this.zzamP = zzIY.zztQ();
                    this.zzamQ = zzIY.zztS();
                    this.zzamR = zzIY.zztT();
                    zzrZ();
                    return;
                }
                String valueOf = String.valueOf(zztR2);
                Log.wtf("GoogleApiClientConnecting", new StringBuilder(String.valueOf(valueOf).length() + 48).append("Sign-in succeeded with resolve account failure: ").append(valueOf).toString(), new Exception());
                zzg(zztR2);
            } else if (zzf(zztR)) {
                zzsc();
                zzrZ();
            } else {
                zzg(zztR);
            }
        }
    }

    private boolean zza(int i, int i2, ConnectionResult connectionResult) {
        return (i2 != 1 || zze(connectionResult)) ? this.zzamF == null || i < this.zzamG : false;
    }

    private void zzah(boolean z) {
        if (this.zzamL != null) {
            if (this.zzamL.isConnected() && z) {
                this.zzamL.zzIP();
            }
            this.zzamL.disconnect();
            this.zzamP = null;
        }
    }

    private void zzb(ConnectionResult connectionResult, Api<?> api, int i) {
        if (i != 2) {
            int priority = api.zzrb().getPriority();
            if (zza(priority, i, connectionResult)) {
                this.zzamF = connectionResult;
                this.zzamG = priority;
            }
        }
        this.zzamB.zzanC.put(api.zzre(), connectionResult);
    }

    private boolean zzbH(int i) {
        if (this.zzamH == i) {
            return true;
        }
        Log.i("GoogleApiClientConnecting", this.zzamB.zzamm.zzsl());
        String valueOf = String.valueOf(zzbI(this.zzamH));
        String valueOf2 = String.valueOf(zzbI(i));
        Log.wtf("GoogleApiClientConnecting", new StringBuilder((String.valueOf(valueOf).length() + 70) + String.valueOf(valueOf2).length()).append("GoogleApiClient connecting is in step ").append(valueOf).append(" but received callback for step ").append(valueOf2).toString(), new Exception());
        zzg(new ConnectionResult(8, null));
        return false;
    }

    private String zzbI(int i) {
        switch (i) {
            case 0:
                return "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
            case 1:
                return "STEP_GETTING_REMOTE_SERVICE";
            default:
                return "UNKNOWN";
        }
    }

    private boolean zze(ConnectionResult connectionResult) {
        return connectionResult.hasResolution() || this.zzamE.zzbB(connectionResult.getErrorCode()) != null;
    }

    private boolean zzf(ConnectionResult connectionResult) {
        return this.zzamM != 2 ? this.zzamM == 1 && !connectionResult.hasResolution() : true;
    }

    private void zzg(ConnectionResult connectionResult) {
        zzsd();
        zzah(!connectionResult.hasResolution());
        this.zzamB.zzi(connectionResult);
        this.zzamB.zzanG.zzd(connectionResult);
    }

    private boolean zzrY() {
        this.zzamI--;
        if (this.zzamI > 0) {
            return false;
        }
        if (this.zzamI < 0) {
            Log.i("GoogleApiClientConnecting", this.zzamB.zzamm.zzsl());
            Log.wtf("GoogleApiClientConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
            zzg(new ConnectionResult(8, null));
            return false;
        } else if (this.zzamF == null) {
            return true;
        } else {
            this.zzamB.zzanF = this.zzamG;
            zzg(this.zzamF);
            return false;
        }
    }

    private void zzrZ() {
        if (this.zzamI == 0) {
            if (!this.zzamN || this.zzamO) {
                zzsa();
            }
        }
    }

    private void zzsa() {
        ArrayList arrayList = new ArrayList();
        this.zzamH = 1;
        this.zzamI = this.zzamB.zzann.size();
        for (com.google.android.gms.common.api.Api.zzc zzc : this.zzamB.zzann.keySet()) {
            if (!this.zzamB.zzanC.containsKey(zzc)) {
                arrayList.add((com.google.android.gms.common.api.Api.zze) this.zzamB.zzann.get(zzc));
            } else if (zzrY()) {
                zzsb();
            }
        }
        if (!arrayList.isEmpty()) {
            this.zzamU.add(zzoi.zzsp().submit(new zzc(arrayList)));
        }
    }

    private void zzsb() {
        this.zzamB.zzsn();
        zzoi.zzsp().execute(new C22431());
        if (this.zzamL != null) {
            if (this.zzamQ) {
                this.zzamL.zza(this.zzamP, this.zzamR);
            }
            zzah(false);
        }
        for (com.google.android.gms.common.api.Api.zzc zzc : this.zzamB.zzanC.keySet()) {
            ((com.google.android.gms.common.api.Api.zze) this.zzamB.zzann.get(zzc)).disconnect();
        }
        this.zzamB.zzanG.zzk(this.zzamJ.isEmpty() ? null : this.zzamJ);
    }

    private void zzsc() {
        this.zzamN = false;
        this.zzamB.zzamm.zzano = Collections.emptySet();
        for (com.google.android.gms.common.api.Api.zzc zzc : this.zzamK) {
            if (!this.zzamB.zzanC.containsKey(zzc)) {
                this.zzamB.zzanC.put(zzc, new ConnectionResult(17, null));
            }
        }
    }

    private void zzsd() {
        Iterator it = this.zzamU.iterator();
        while (it.hasNext()) {
            ((Future) it.next()).cancel(true);
        }
        this.zzamU.clear();
    }

    private Set<Scope> zzse() {
        if (this.zzamS == null) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet(this.zzamS.zztv());
        Map zztx = this.zzamS.zztx();
        for (Api api : zztx.keySet()) {
            if (!this.zzamB.zzanC.containsKey(api.zzre())) {
                hashSet.addAll(((com.google.android.gms.common.internal.zzg.zza) zztx.get(api)).zzacF);
            }
        }
        return hashSet;
    }

    public void begin() {
        this.zzamB.zzanC.clear();
        this.zzamN = false;
        this.zzamF = null;
        this.zzamH = 0;
        this.zzamM = 2;
        this.zzamO = false;
        this.zzamQ = false;
        HashMap hashMap = new HashMap();
        int i = 0;
        for (Api api : this.zzamT.keySet()) {
            com.google.android.gms.common.api.Api.zze zze = (com.google.android.gms.common.api.Api.zze) this.zzamB.zzann.get(api.zzre());
            int intValue = ((Integer) this.zzamT.get(api)).intValue();
            int i2 = (api.zzrb().getPriority() == 1 ? 1 : 0) | i;
            if (zze.zzpd()) {
                this.zzamN = true;
                if (intValue < this.zzamM) {
                    this.zzamM = intValue;
                }
                if (intValue != 0) {
                    this.zzamK.add(api.zzre());
                }
            }
            hashMap.put(zze, new zza(this, api, intValue));
            i = i2;
        }
        if (i != 0) {
            this.zzamN = false;
        }
        if (this.zzamN) {
            this.zzamS.zzc(Integer.valueOf(this.zzamB.zzamm.getSessionId()));
            zze zze2 = new zze(this, null);
            this.zzamL = (zztv) this.zzalo.zza(this.mContext, this.zzamB.zzamm.getLooper(), this.zzamS, this.zzamS.zztB(), zze2, zze2);
        }
        this.zzamI = this.zzamB.zzann.size();
        this.zzamU.add(zzoi.zzsp().submit(new zzb(hashMap)));
    }

    public void connect() {
    }

    public boolean disconnect() {
        zzsd();
        zzah(true);
        this.zzamB.zzi(null);
        return true;
    }

    public void onConnected(Bundle bundle) {
        if (zzbH(1)) {
            if (bundle != null) {
                this.zzamJ.putAll(bundle);
            }
            if (zzrY()) {
                zzsb();
            }
        }
    }

    public void onConnectionSuspended(int i) {
        zzg(new ConnectionResult(8, null));
    }

    public void zza(ConnectionResult connectionResult, Api<?> api, int i) {
        if (zzbH(1)) {
            zzb(connectionResult, api, i);
            if (zzrY()) {
                zzsb();
            }
        }
    }

    public <A extends com.google.android.gms.common.api.Api.zzb, R extends Result, T extends com.google.android.gms.internal.zznt.zza<R, A>> T zzc(T t) {
        this.zzamB.zzamm.zzanh.add(t);
        return t;
    }

    public <A extends com.google.android.gms.common.api.Api.zzb, T extends com.google.android.gms.internal.zznt.zza<? extends Result, A>> T zzd(T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }
}
