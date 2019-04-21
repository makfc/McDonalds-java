package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.util.ArrayMap;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.ApiOptions.NotRequiredOptions;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Api.zzh;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzag;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzg.zza;
import com.google.android.gms.internal.zznr;
import com.google.android.gms.internal.zznt;
import com.google.android.gms.internal.zznw;
import com.google.android.gms.internal.zzof;
import com.google.android.gms.internal.zzop;
import com.google.android.gms.internal.zzpe;
import com.google.android.gms.internal.zztu;
import com.google.android.gms.internal.zztv;
import com.google.android.gms.internal.zztw;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public abstract class GoogleApiClient {
    private static final Set<GoogleApiClient> zzalc = Collections.newSetFromMap(new WeakHashMap());

    public static final class Builder {
        private final Context mContext;
        private Account zzZB;
        private String zzaaN;
        private Looper zzakW;
        private final Set<Scope> zzald;
        private final Set<Scope> zzale;
        private int zzalf;
        private View zzalg;
        private String zzalh;
        private final Map<Api<?>, zza> zzali;
        private final Map<Api<?>, ApiOptions> zzalj;
        private zzop zzalk;
        private int zzall;
        private OnConnectionFailedListener zzalm;
        private GoogleApiAvailability zzaln;
        private Api.zza<? extends zztv, zztw> zzalo;
        private final ArrayList<ConnectionCallbacks> zzalp;
        private final ArrayList<OnConnectionFailedListener> zzalq;

        public Builder(@NonNull Context context) {
            this.zzald = new HashSet();
            this.zzale = new HashSet();
            this.zzali = new ArrayMap();
            this.zzalj = new ArrayMap();
            this.zzall = -1;
            this.zzaln = GoogleApiAvailability.getInstance();
            this.zzalo = zztu.zzaaA;
            this.zzalp = new ArrayList();
            this.zzalq = new ArrayList();
            this.mContext = context;
            this.zzakW = context.getMainLooper();
            this.zzaaN = context.getPackageName();
            this.zzalh = context.getClass().getName();
        }

        public Builder(@NonNull Context context, @NonNull ConnectionCallbacks connectionCallbacks, @NonNull OnConnectionFailedListener onConnectionFailedListener) {
            this(context);
            zzaa.zzb((Object) connectionCallbacks, (Object) "Must provide a connected listener");
            this.zzalp.add(connectionCallbacks);
            zzaa.zzb((Object) onConnectionFailedListener, (Object) "Must provide a connection failed listener");
            this.zzalq.add(onConnectionFailedListener);
        }

        private static <C extends zze, O> C zza(Api.zza<C, O> zza, Object obj, Context context, Looper looper, zzg zzg, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return zza.zza(context, looper, zzg, obj, connectionCallbacks, onConnectionFailedListener);
        }

        private static <C extends Api.zzg, O> zzag zza(zzh<C, O> zzh, Object obj, Context context, Looper looper, zzg zzg, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzag(context, looper, zzh.zzri(), connectionCallbacks, onConnectionFailedListener, zzg, zzh.zzr(obj));
        }

        private void zze(GoogleApiClient googleApiClient) {
            zznr.zza(this.zzalk).zza(this.zzall, googleApiClient, this.zzalm);
        }

        private GoogleApiClient zzru() {
            String valueOf;
            String valueOf2;
            zzg zzrt = zzrt();
            Api api = null;
            Map zztx = zzrt.zztx();
            ArrayMap arrayMap = new ArrayMap();
            ArrayMap arrayMap2 = new ArrayMap();
            ArrayList arrayList = new ArrayList();
            Api api2 = null;
            for (Api api3 : this.zzalj.keySet()) {
                Api api32;
                Api api4;
                Object obj = this.zzalj.get(api32);
                int i = 0;
                if (zztx.get(api32) != null) {
                    i = ((zza) zztx.get(api32)).zzars ? 1 : 2;
                }
                arrayMap.put(api32, Integer.valueOf(i));
                ConnectionCallbacks zznw = new zznw(api32, i);
                arrayList.add(zznw);
                Api api5;
                if (api32.zzrf()) {
                    zzh zzrd = api32.zzrd();
                    api5 = zzrd.getPriority() == 1 ? api32 : api2;
                    obj = zza(zzrd, obj, this.mContext, this.zzakW, zzrt, zznw, (OnConnectionFailedListener) zznw);
                    api4 = api5;
                } else {
                    Api.zza zzrc = api32.zzrc();
                    api5 = zzrc.getPriority() == 1 ? api32 : api2;
                    zze zza = zza(zzrc, obj, this.mContext, this.zzakW, zzrt, zznw, (OnConnectionFailedListener) zznw);
                    api4 = api5;
                }
                arrayMap2.put(api32.zzre(), obj);
                if (!obj.zzps()) {
                    api32 = api;
                } else if (api != null) {
                    valueOf = String.valueOf(api32.getName());
                    valueOf2 = String.valueOf(api.getName());
                    throw new IllegalStateException(new StringBuilder((String.valueOf(valueOf).length() + 21) + String.valueOf(valueOf2).length()).append(valueOf).append(" cannot be used with ").append(valueOf2).toString());
                }
                api2 = api4;
                api = api32;
            }
            if (api != null) {
                if (api2 != null) {
                    valueOf = String.valueOf(api.getName());
                    valueOf2 = String.valueOf(api2.getName());
                    throw new IllegalStateException(new StringBuilder((String.valueOf(valueOf).length() + 21) + String.valueOf(valueOf2).length()).append(valueOf).append(" cannot be used with ").append(valueOf2).toString());
                }
                zzaa.zza(this.zzZB == null, "Must not set an account in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead", api.getName());
                zzaa.zza(this.zzald.equals(this.zzale), "Must not set scopes in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead.", api.getName());
            }
            return new zzof(this.mContext, new ReentrantLock(), this.zzakW, zzrt, this.zzaln, this.zzalo, arrayMap, this.zzalp, this.zzalq, arrayMap2, this.zzall, zzof.zza(arrayMap2.values(), true), arrayList);
        }

        public Builder addApi(@NonNull Api<? extends NotRequiredOptions> api) {
            zzaa.zzb((Object) api, (Object) "Api must not be null");
            this.zzalj.put(api, null);
            List zzp = api.zzrb().zzp(null);
            this.zzale.addAll(zzp);
            this.zzald.addAll(zzp);
            return this;
        }

        public Builder addConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks) {
            zzaa.zzb((Object) connectionCallbacks, (Object) "Listener must not be null");
            this.zzalp.add(connectionCallbacks);
            return this;
        }

        public Builder addOnConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener) {
            zzaa.zzb((Object) onConnectionFailedListener, (Object) "Listener must not be null");
            this.zzalq.add(onConnectionFailedListener);
            return this;
        }

        public Builder addScope(@NonNull Scope scope) {
            zzaa.zzb((Object) scope, (Object) "Scope must not be null");
            this.zzald.add(scope);
            return this;
        }

        public GoogleApiClient build() {
            zzaa.zzb(!this.zzalj.isEmpty(), (Object) "must call addApi() to add at least one API");
            GoogleApiClient zzru = zzru();
            synchronized (GoogleApiClient.zzalc) {
                GoogleApiClient.zzalc.add(zzru);
            }
            if (this.zzall >= 0) {
                zze(zzru);
            }
            return zzru;
        }

        public zzg zzrt() {
            zztw zztw = zztw.zzbnf;
            if (this.zzalj.containsKey(zztu.API)) {
                zztw = (zztw) this.zzalj.get(zztu.API);
            }
            return new zzg(this.zzZB, this.zzald, this.zzali, this.zzalf, this.zzalg, this.zzaaN, this.zzalh, zztw);
        }
    }

    public interface ConnectionCallbacks {
        void onConnected(@Nullable Bundle bundle);

        void onConnectionSuspended(int i);
    }

    public interface OnConnectionFailedListener {
        void onConnectionFailed(@NonNull ConnectionResult connectionResult);
    }

    public abstract ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit);

    public abstract void connect();

    public void connect(int i) {
        throw new UnsupportedOperationException();
    }

    public abstract void disconnect();

    public abstract void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public Looper getLooper() {
        throw new UnsupportedOperationException();
    }

    public abstract boolean hasConnectedApi(@NonNull Api<?> api);

    public abstract boolean isConnected();

    public abstract boolean isConnecting();

    public abstract void registerConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener);

    public abstract void unregisterConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener);

    @NonNull
    public <C extends zze> C zza(@NonNull zzc<C> zzc) {
        throw new UnsupportedOperationException();
    }

    public void zza(zzpe zzpe) {
        throw new UnsupportedOperationException();
    }

    public boolean zza(@NonNull Api<?> api) {
        throw new UnsupportedOperationException();
    }

    public void zzb(zzpe zzpe) {
        throw new UnsupportedOperationException();
    }

    public <A extends zzb, R extends Result, T extends zznt.zza<R, A>> T zzc(@NonNull T t) {
        throw new UnsupportedOperationException();
    }

    public <A extends zzb, T extends zznt.zza<? extends Result, A>> T zzd(@NonNull T t) {
        throw new UnsupportedOperationException();
    }
}
