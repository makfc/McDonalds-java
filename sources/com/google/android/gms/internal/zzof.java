package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.auth.api.signin.internal.zzk;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzl;
import com.newrelic.agent.android.payload.PayloadController;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;

public final class zzof extends GoogleApiClient implements com.google.android.gms.internal.zzoo.zza {
    private final Context mContext;
    private final Looper zzakW;
    private final int zzall;
    private final GoogleApiAvailability zzaln;
    final com.google.android.gms.common.api.Api.zza<? extends zztv, zztw> zzalo;
    final zzg zzamS;
    final Map<Api<?>, Integer> zzamT;
    private final Lock zzamw;
    private final zzl zzanf;
    private zzoo zzang = null;
    final Queue<com.google.android.gms.internal.zznt.zza<?, ?>> zzanh = new LinkedList();
    private volatile boolean zzani;
    private long zzanj = PayloadController.PAYLOAD_REQUEUE_PERIOD_MS;
    private long zzank = 5000;
    private final zza zzanl;
    zzol zzanm;
    final Map<zzc<?>, zze> zzann;
    Set<Scope> zzano = new HashSet();
    private final zzov zzanp = new zzov();
    private final ArrayList<zznw> zzanq;
    private Integer zzanr = null;
    Set<zzpe> zzans = null;
    final zzpf zzant;
    private final com.google.android.gms.common.internal.zzl.zza zzanu = new C22471();

    /* renamed from: com.google.android.gms.internal.zzof$1 */
    class C22471 implements com.google.android.gms.common.internal.zzl.zza {
        C22471() {
        }

        public boolean isConnected() {
            return zzof.this.isConnected();
        }

        public Bundle zzqr() {
            return null;
        }
    }

    /* renamed from: com.google.android.gms.internal.zzof$2 */
    class C22482 implements ConnectionCallbacks {
        final /* synthetic */ zzof zzanv;
        final /* synthetic */ AtomicReference zzanw;
        final /* synthetic */ zzpb zzanx;

        public void onConnected(Bundle bundle) {
            this.zzanv.zza((GoogleApiClient) this.zzanw.get(), this.zzanx, true);
        }

        public void onConnectionSuspended(int i) {
        }
    }

    /* renamed from: com.google.android.gms.internal.zzof$3 */
    class C22493 implements OnConnectionFailedListener {
        final /* synthetic */ zzpb zzanx;

        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            this.zzanx.zzb(new Status(8));
        }
    }

    final class zza extends Handler {
        zza(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    zzof.this.zzsh();
                    return;
                case 2:
                    zzof.this.resume();
                    return;
                default:
                    Log.w("GoogleApiClientImpl", "Unknown message id: " + message.what);
                    return;
            }
        }
    }

    static class zzb extends com.google.android.gms.internal.zzol.zza {
        private WeakReference<zzof> zzanz;

        zzb(zzof zzof) {
            this.zzanz = new WeakReference(zzof);
        }

        public void zzrG() {
            zzof zzof = (zzof) this.zzanz.get();
            if (zzof != null) {
                zzof.resume();
            }
        }
    }

    public zzof(Context context, Lock lock, Looper looper, zzg zzg, GoogleApiAvailability googleApiAvailability, com.google.android.gms.common.api.Api.zza<? extends zztv, zztw> zza, Map<Api<?>, Integer> map, List<ConnectionCallbacks> list, List<OnConnectionFailedListener> list2, Map<zzc<?>, zze> map2, int i, int i2, ArrayList<zznw> arrayList) {
        this.mContext = context;
        this.zzamw = lock;
        this.zzanf = new zzl(looper, this.zzanu);
        this.zzakW = looper;
        this.zzanl = new zza(looper);
        this.zzaln = googleApiAvailability;
        this.zzall = i;
        if (this.zzall >= 0) {
            this.zzanr = Integer.valueOf(i2);
        }
        this.zzamT = map;
        this.zzann = map2;
        this.zzanq = arrayList;
        this.zzant = new zzpf(this.zzann);
        for (ConnectionCallbacks registerConnectionCallbacks : list) {
            this.zzanf.registerConnectionCallbacks(registerConnectionCallbacks);
        }
        for (OnConnectionFailedListener registerConnectionFailedListener : list2) {
            this.zzanf.registerConnectionFailedListener(registerConnectionFailedListener);
        }
        this.zzamS = zzg;
        this.zzalo = zza;
    }

    private void resume() {
        this.zzamw.lock();
        try {
            if (zzsf()) {
                zzsg();
            }
            this.zzamw.unlock();
        } catch (Throwable th) {
            this.zzamw.unlock();
        }
    }

    public static int zza(Iterable<zze> iterable, boolean z) {
        int i = 0;
        int i2 = 0;
        for (zze zze : iterable) {
            if (zze.zzpd()) {
                i2 = 1;
            }
            i = zze.zzps() ? 1 : i;
        }
        return i2 != 0 ? (i == 0 || !z) ? 1 : 2 : 3;
    }

    private void zza(final GoogleApiClient googleApiClient, final zzpb zzpb, final boolean z) {
        zzpl.zzasx.zzf(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            /* renamed from: zzo */
            public void onResult(@NonNull Status status) {
                zzk.zzab(zzof.this.mContext).zzpE();
                if (status.isSuccess() && zzof.this.isConnected()) {
                    zzof.this.reconnect();
                }
                zzpb.zzb(status);
                if (z) {
                    googleApiClient.disconnect();
                }
            }
        });
    }

    private void zzbJ(int i) {
        if (this.zzanr == null) {
            this.zzanr = Integer.valueOf(i);
        } else if (this.zzanr.intValue() != i) {
            String valueOf = String.valueOf(zzbK(i));
            String valueOf2 = String.valueOf(zzbK(this.zzanr.intValue()));
            throw new IllegalStateException(new StringBuilder((String.valueOf(valueOf).length() + 51) + String.valueOf(valueOf2).length()).append("Cannot use sign-in mode: ").append(valueOf).append(". Mode was already set to ").append(valueOf2).toString());
        }
        if (this.zzang == null) {
            Object obj = null;
            Object obj2 = null;
            for (zze zze : this.zzann.values()) {
                if (zze.zzpd()) {
                    obj2 = 1;
                }
                obj = zze.zzps() ? 1 : obj;
            }
            switch (this.zzanr.intValue()) {
                case 1:
                    if (obj2 == null) {
                        throw new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
                    } else if (obj != null) {
                        throw new IllegalStateException("Cannot use SIGN_IN_MODE_REQUIRED with GOOGLE_SIGN_IN_API. Use connect(SIGN_IN_MODE_OPTIONAL) instead.");
                    }
                    break;
                case 2:
                    if (obj2 != null) {
                        this.zzang = zznx.zza(this.mContext, this, this.zzamw, this.zzakW, this.zzaln, this.zzann, this.zzamS, this.zzamT, this.zzalo, this.zzanq);
                        return;
                    }
                    break;
            }
            this.zzang = new zzoh(this.mContext, this, this.zzamw, this.zzakW, this.zzaln, this.zzann, this.zzamS, this.zzamT, this.zzalo, this.zzanq, this);
        }
    }

    static String zzbK(int i) {
        switch (i) {
            case 1:
                return "SIGN_IN_MODE_REQUIRED";
            case 2:
                return "SIGN_IN_MODE_OPTIONAL";
            case 3:
                return "SIGN_IN_MODE_NONE";
            default:
                return "UNKNOWN";
        }
    }

    private void zzsg() {
        this.zzanf.zztJ();
        this.zzang.connect();
    }

    private void zzsh() {
        this.zzamw.lock();
        try {
            if (zzsj()) {
                zzsg();
            }
            this.zzamw.unlock();
        } catch (Throwable th) {
            this.zzamw.unlock();
        }
    }

    public ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit) {
        boolean z = false;
        if (Looper.myLooper() != Looper.getMainLooper()) {
            z = true;
        }
        zzaa.zza(z, "blockingConnect must not be called on the UI thread");
        zzaa.zzb((Object) timeUnit, (Object) "TimeUnit must not be null");
        this.zzamw.lock();
        try {
            if (this.zzanr == null) {
                this.zzanr = Integer.valueOf(zza(this.zzann.values(), false));
            } else if (this.zzanr.intValue() == 2) {
                throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            zzbJ(this.zzanr.intValue());
            this.zzanf.zztJ();
            ConnectionResult blockingConnect = this.zzang.blockingConnect(j, timeUnit);
            return blockingConnect;
        } finally {
            this.zzamw.unlock();
        }
    }

    public void connect() {
        boolean z = false;
        this.zzamw.lock();
        try {
            if (this.zzall >= 0) {
                if (this.zzanr != null) {
                    z = true;
                }
                zzaa.zza(z, "Sign-in mode should have been set explicitly by auto-manage.");
            } else if (this.zzanr == null) {
                this.zzanr = Integer.valueOf(zza(this.zzann.values(), false));
            } else if (this.zzanr.intValue() == 2) {
                throw new IllegalStateException("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            connect(this.zzanr.intValue());
        } finally {
            this.zzamw.unlock();
        }
    }

    public void connect(int i) {
        boolean z = true;
        this.zzamw.lock();
        if (!(i == 3 || i == 1 || i == 2)) {
            z = false;
        }
        try {
            zzaa.zzb(z, "Illegal sign-in mode: " + i);
            zzbJ(i);
            zzsg();
        } finally {
            this.zzamw.unlock();
        }
    }

    public void disconnect() {
        this.zzamw.lock();
        try {
            this.zzant.release();
            if (this.zzang != null) {
                this.zzang.disconnect();
            }
            this.zzanp.release();
            for (com.google.android.gms.internal.zznt.zza zza : this.zzanh) {
                zza.zza(null);
                zza.cancel();
            }
            this.zzanh.clear();
            if (this.zzang != null) {
                zzsj();
                this.zzanf.zztI();
                this.zzamw.unlock();
            }
        } finally {
            this.zzamw.unlock();
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append(str).append("mContext=").println(this.mContext);
        printWriter.append(str).append("mResuming=").print(this.zzani);
        printWriter.append(" mWorkQueue.size()=").print(this.zzanh.size());
        this.zzant.dump(printWriter);
        if (this.zzang != null) {
            this.zzang.dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    public Looper getLooper() {
        return this.zzakW;
    }

    public int getSessionId() {
        return System.identityHashCode(this);
    }

    public boolean hasConnectedApi(@NonNull Api<?> api) {
        zze zze = (zze) this.zzann.get(api.zzre());
        return zze != null && zze.isConnected();
    }

    public boolean isConnected() {
        return this.zzang != null && this.zzang.isConnected();
    }

    public boolean isConnecting() {
        return this.zzang != null && this.zzang.isConnecting();
    }

    public void reconnect() {
        disconnect();
        connect();
    }

    public void registerConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener) {
        this.zzanf.registerConnectionFailedListener(onConnectionFailedListener);
    }

    public void unregisterConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener) {
        this.zzanf.unregisterConnectionFailedListener(onConnectionFailedListener);
    }

    @NonNull
    public <C extends zze> C zza(@NonNull zzc<C> zzc) {
        Object obj = (zze) this.zzann.get(zzc);
        zzaa.zzb(obj, (Object) "Appropriate Api was not requested.");
        return obj;
    }

    public void zza(zzpe zzpe) {
        this.zzamw.lock();
        try {
            if (this.zzans == null) {
                this.zzans = new HashSet();
            }
            this.zzans.add(zzpe);
        } finally {
            this.zzamw.unlock();
        }
    }

    public boolean zza(@NonNull Api<?> api) {
        return this.zzann.containsKey(api.zzre());
    }

    /* Access modifiers changed, original: 0000 */
    public <C extends zze> C zzb(zzc<?> zzc) {
        Object obj = (zze) this.zzann.get(zzc);
        zzaa.zzb(obj, (Object) "Appropriate Api was not requested.");
        return obj;
    }

    public void zzb(zzpe zzpe) {
        this.zzamw.lock();
        try {
            if (this.zzans == null) {
                Log.wtf("GoogleApiClientImpl", "Attempted to remove pending transform when no transforms are registered.", new Exception());
            } else if (!this.zzans.remove(zzpe)) {
                Log.wtf("GoogleApiClientImpl", "Failed to remove pending transform - this may lead to memory leaks!", new Exception());
            } else if (!zzsk()) {
                this.zzang.zzrN();
            }
            this.zzamw.unlock();
        } catch (Throwable th) {
            this.zzamw.unlock();
        }
    }

    public <A extends com.google.android.gms.common.api.Api.zzb, R extends Result, T extends com.google.android.gms.internal.zznt.zza<R, A>> T zzc(@NonNull T t) {
        zzaa.zzb(t.zzre() != null, (Object) "This task can not be enqueued (it's probably a Batch or malformed)");
        boolean containsKey = this.zzann.containsKey(t.zzre());
        Object name = t.zzrl() != null ? t.zzrl().getName() : "the API";
        zzaa.zzb(containsKey, new StringBuilder(String.valueOf(name).length() + 65).append("GoogleApiClient is not configured to use ").append(name).append(" required for this call.").toString());
        this.zzamw.lock();
        try {
            if (this.zzang == null) {
                this.zzanh.add(t);
            } else {
                t = this.zzang.zzc(t);
                this.zzamw.unlock();
            }
            return t;
        } finally {
            this.zzamw.unlock();
        }
    }

    public void zzc(int i, boolean z) {
        if (i == 1 && !z) {
            zzsi();
        }
        this.zzant.zzsL();
        this.zzanf.zzcc(i);
        this.zzanf.zztI();
        if (i == 2) {
            zzsg();
        }
    }

    public <A extends com.google.android.gms.common.api.Api.zzb, T extends com.google.android.gms.internal.zznt.zza<? extends Result, A>> T zzd(@NonNull T t) {
        zzaa.zzb(t.zzre() != null, (Object) "This task can not be executed (it's probably a Batch or malformed)");
        boolean containsKey = this.zzann.containsKey(t.zzre());
        Object name = t.zzrl() != null ? t.zzrl().getName() : "the API";
        zzaa.zzb(containsKey, new StringBuilder(String.valueOf(name).length() + 65).append("GoogleApiClient is not configured to use ").append(name).append(" required for this call.").toString());
        this.zzamw.lock();
        try {
            if (this.zzang == null) {
                throw new IllegalStateException("GoogleApiClient is not connected yet.");
            }
            if (zzsf()) {
                this.zzanh.add(t);
                while (!this.zzanh.isEmpty()) {
                    com.google.android.gms.internal.zznt.zza zza = (com.google.android.gms.internal.zznt.zza) this.zzanh.remove();
                    this.zzant.zzg(zza);
                    zza.zzx(Status.zzaly);
                }
            } else {
                t = this.zzang.zzd(t);
                this.zzamw.unlock();
            }
            return t;
        } finally {
            this.zzamw.unlock();
        }
    }

    public void zzd(ConnectionResult connectionResult) {
        if (!this.zzaln.zzc(this.mContext, connectionResult.getErrorCode())) {
            zzsj();
        }
        if (!zzsf()) {
            this.zzanf.zzm(connectionResult);
            this.zzanf.zztI();
        }
    }

    public void zzk(Bundle bundle) {
        while (!this.zzanh.isEmpty()) {
            zzd((com.google.android.gms.internal.zznt.zza) this.zzanh.remove());
        }
        this.zzanf.zzm(bundle);
    }

    /* Access modifiers changed, original: 0000 */
    public boolean zzsf() {
        return this.zzani;
    }

    /* Access modifiers changed, original: 0000 */
    public void zzsi() {
        if (!zzsf()) {
            this.zzani = true;
            if (this.zzanm == null) {
                this.zzanm = this.zzaln.zza(this.mContext.getApplicationContext(), new zzb(this));
            }
            this.zzanl.sendMessageDelayed(this.zzanl.obtainMessage(1), this.zzanj);
            this.zzanl.sendMessageDelayed(this.zzanl.obtainMessage(2), this.zzank);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean zzsj() {
        if (!zzsf()) {
            return false;
        }
        this.zzani = false;
        this.zzanl.removeMessages(2);
        this.zzanl.removeMessages(1);
        if (this.zzanm != null) {
            this.zzanm.unregister();
            this.zzanm = null;
        }
        return true;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean zzsk() {
        boolean z = false;
        this.zzamw.lock();
        try {
            if (this.zzans != null) {
                if (!this.zzans.isEmpty()) {
                    z = true;
                }
                this.zzamw.unlock();
            }
            return z;
        } finally {
            this.zzamw.unlock();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public String zzsl() {
        StringWriter stringWriter = new StringWriter();
        dump("", null, new PrintWriter(stringWriter), null);
        return stringWriter.toString();
    }
}
