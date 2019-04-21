package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzd.zzf;
import com.google.android.gms.common.internal.zzk;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.internal.zztv;
import com.google.android.gms.internal.zztw;
import com.google.android.gms.signin.internal.zze.zza;

public class zzg extends zzk<zze> implements zztv {
    private final com.google.android.gms.common.internal.zzg zzamS;
    private Integer zzarr;
    private final boolean zzbnn;
    private final Bundle zzbno;

    public zzg(Context context, Looper looper, boolean z, com.google.android.gms.common.internal.zzg zzg, Bundle bundle, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 44, zzg, connectionCallbacks, onConnectionFailedListener);
        this.zzbnn = z;
        this.zzamS = zzg;
        this.zzbno = bundle;
        this.zzarr = zzg.zztC();
    }

    public zzg(Context context, Looper looper, boolean z, com.google.android.gms.common.internal.zzg zzg, zztw zztw, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, z, zzg, zza(zzg), connectionCallbacks, onConnectionFailedListener);
    }

    private ResolveAccountRequest zzIW() {
        Account zztk = this.zzamS.zztk();
        GoogleSignInAccount googleSignInAccount = null;
        if ("<<default account>>".equals(zztk.name)) {
            googleSignInAccount = com.google.android.gms.auth.api.signin.internal.zzk.zzab(getContext()).zzpC();
        }
        return new ResolveAccountRequest(zztk, this.zzarr.intValue(), googleSignInAccount);
    }

    public static Bundle zza(com.google.android.gms.common.internal.zzg zzg) {
        zztw zztB = zzg.zztB();
        Integer zztC = zzg.zztC();
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", zzg.getAccount());
        if (zztC != null) {
            bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", zztC.intValue());
        }
        if (zztB != null) {
            bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", zztB.zzIQ());
            bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", zztB.zzpk());
            bundle.putString("com.google.android.gms.signin.internal.serverClientId", zztB.zzpn());
            bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
            bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", zztB.zzpm());
            bundle.putString("com.google.android.gms.signin.internal.hostedDomain", zztB.zzpo());
            bundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", zztB.zzIR());
        }
        return bundle;
    }

    public void connect() {
        zza((zzf) new zzi());
    }

    public void zzIP() {
        try {
            ((zze) zztm()).zzkJ(this.zzarr.intValue());
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
        }
    }

    public void zza(zzq zzq, boolean z) {
        try {
            ((zze) zztm()).zza(zzq, this.zzarr.intValue(), z);
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
        }
    }

    public void zza(zzd zzd) {
        zzaa.zzb((Object) zzd, (Object) "Expecting a valid ISignInCallbacks");
        try {
            ((zze) zztm()).zza(new SignInRequest(zzIW()), zzd);
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
            try {
                zzd.zzb(new SignInResponse(8));
            } catch (RemoteException e2) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", e);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: zzef */
    public zze zzab(IBinder iBinder) {
        return zza.zzee(iBinder);
    }

    /* Access modifiers changed, original: protected */
    public String zzhT() {
        return "com.google.android.gms.signin.service.START";
    }

    /* Access modifiers changed, original: protected */
    public String zzhU() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }

    /* Access modifiers changed, original: protected */
    public Bundle zzoO() {
        if (!getContext().getPackageName().equals(this.zzamS.zzty())) {
            this.zzbno.putString("com.google.android.gms.signin.internal.realClientPackageName", this.zzamS.zzty());
        }
        return this.zzbno;
    }

    public boolean zzpd() {
        return this.zzbnn;
    }
}
