package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IInterface;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzd.zzb;
import com.google.android.gms.common.internal.zzd.zzc;
import com.google.android.gms.common.internal.zzl.zza;
import java.util.Set;

public abstract class zzk<T extends IInterface> extends zzd<T> implements zze, zza {
    private final Account zzZB;
    private final Set<Scope> zzacF;
    private final zzg zzamS;

    protected zzk(Context context, Looper looper, int i, zzg zzg, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, zzm.zzav(context), GoogleApiAvailability.getInstance(), i, zzg, (ConnectionCallbacks) zzaa.zzz(connectionCallbacks), (OnConnectionFailedListener) zzaa.zzz(onConnectionFailedListener));
    }

    protected zzk(Context context, Looper looper, zzm zzm, GoogleApiAvailability googleApiAvailability, int i, zzg zzg, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, zzm, googleApiAvailability, i, zza(connectionCallbacks), zza(onConnectionFailedListener), zzg.zztz());
        this.zzamS = zzg;
        this.zzZB = zzg.getAccount();
        this.zzacF = zza(zzg.zztw());
    }

    @Nullable
    private static zzb zza(final ConnectionCallbacks connectionCallbacks) {
        return connectionCallbacks == null ? null : new zzb() {
            public void onConnected(@Nullable Bundle bundle) {
                connectionCallbacks.onConnected(bundle);
            }

            public void onConnectionSuspended(int i) {
                connectionCallbacks.onConnectionSuspended(i);
            }
        };
    }

    @Nullable
    private static zzc zza(final OnConnectionFailedListener onConnectionFailedListener) {
        return onConnectionFailedListener == null ? null : new zzc() {
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        };
    }

    private Set<Scope> zza(@NonNull Set<Scope> set) {
        Set<Scope> zzb = zzb(set);
        for (Scope contains : zzb) {
            if (!set.contains(contains)) {
                throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
            }
        }
        return zzb;
    }

    public final Account getAccount() {
        return this.zzZB;
    }

    /* Access modifiers changed, original: protected */
    @NonNull
    public Set<Scope> zzb(@NonNull Set<Scope> set) {
        return set;
    }

    /* Access modifiers changed, original: protected|final */
    public final zzg zztH() {
        return this.zzamS;
    }

    /* Access modifiers changed, original: protected|final */
    public final Set<Scope> zzto() {
        return this.zzacF;
    }
}
