package com.google.android.gms.auth;

import android.accounts.Account;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzm;
import com.google.android.gms.common.zze;
import com.google.android.gms.internal.zzmx;
import java.io.IOException;
import java.util.List;

public class zzd {
    public static final String KEY_ANDROID_PACKAGE_NAME = (VERSION.SDK_INT >= 14 ? "androidPackageName" : "androidPackageName");
    public static final String KEY_CALLER_UID = (VERSION.SDK_INT >= 11 ? "callerUid" : "callerUid");
    private static final ComponentName zzaaV = new ComponentName("com.google.android.gms", "com.google.android.gms.auth.GetToken");
    private static final ComponentName zzaaW = new ComponentName("com.google.android.gms", "com.google.android.gms.recovery.RecoveryService");

    private interface zza<T> {
        T zzas(IBinder iBinder) throws RemoteException, IOException, GoogleAuthException;
    }

    /* renamed from: com.google.android.gms.auth.zzd$2 */
    class C20962 implements zza<Void> {
        final /* synthetic */ String zzaba;
        final /* synthetic */ Bundle zzabb;

        /* renamed from: zzat */
        public Void zzas(IBinder iBinder) throws RemoteException, IOException, GoogleAuthException {
            Bundle bundle = (Bundle) zzd.zzn(com.google.android.gms.internal.zzbn.zza.zza(iBinder).zza(this.zzaba, this.zzabb));
            String string = bundle.getString("Error");
            if (bundle.getBoolean("booleanResult")) {
                return null;
            }
            throw new GoogleAuthException(string);
        }
    }

    /* renamed from: com.google.android.gms.auth.zzd$3 */
    class C20973 implements zza<List<AccountChangeEvent>> {
        final /* synthetic */ String zzabc;
        final /* synthetic */ int zzabd;

        /* renamed from: zzau */
        public List<AccountChangeEvent> zzas(IBinder iBinder) throws RemoteException, IOException, GoogleAuthException {
            return ((AccountChangeEventsResponse) zzd.zzn(com.google.android.gms.internal.zzbn.zza.zza(iBinder).zza(new AccountChangeEventsRequest().setAccountName(this.zzabc).setEventIndex(this.zzabd)))).getEvents();
        }
    }

    /* renamed from: com.google.android.gms.auth.zzd$4 */
    class C20984 implements zza<Bundle> {
        final /* synthetic */ Account zzaaX;

        /* renamed from: zzav */
        public Bundle zzas(IBinder iBinder) throws RemoteException, IOException, GoogleAuthException {
            return (Bundle) zzd.zzn(com.google.android.gms.internal.zzbn.zza.zza(iBinder).zza(this.zzaaX));
        }
    }

    zzd() {
    }

    public static String getToken(Context context, Account account, String str) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return getToken(context, account, str, new Bundle());
    }

    public static String getToken(Context context, Account account, String str, Bundle bundle) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return zzc(context, account, str, bundle).getToken();
    }

    @Deprecated
    public static String getToken(Context context, String str, String str2) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return getToken(context, new Account(str, "com.google"), str2);
    }

    private static <T> T zza(Context context, ComponentName componentName, zza<T> zza) throws IOException, GoogleAuthException {
        com.google.android.gms.common.zza zza2 = new com.google.android.gms.common.zza();
        zzm zzav = zzm.zzav(context);
        if (zzav.zza(componentName, zza2, "GoogleAuthUtil")) {
            try {
                Object zzas = zza.zzas(zza2.zzqU());
                zzav.zzb(componentName, zza2, "GoogleAuthUtil");
                return zzas;
            } catch (RemoteException | InterruptedException e) {
                Log.i("GoogleAuthUtil", "Error on service connection.", e);
                throw new IOException("Error on service connection.", e);
            } catch (Throwable th) {
                zzav.zzb(componentName, zza2, "GoogleAuthUtil");
            }
        } else {
            throw new IOException("Could not bind to service.");
        }
    }

    private static void zzaa(Context context) throws GoogleAuthException {
        try {
            zze.zzaa(context.getApplicationContext());
        } catch (GooglePlayServicesRepairableException e) {
            throw new GooglePlayServicesAvailabilityException(e.getConnectionStatusCode(), e.getMessage(), e.getIntent());
        } catch (GooglePlayServicesNotAvailableException e2) {
            throw new GoogleAuthException(e2.getMessage());
        }
    }

    public static TokenData zzc(Context context, final Account account, final String str, Bundle bundle) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        zzaa.zzdd("Calling this from your main thread can lead to deadlock");
        zzaa.zzh(str, "Scope cannot be empty or null.");
        zzaa.zzb((Object) account, (Object) "Account cannot be null.");
        zzaa(context);
        final Bundle bundle2 = bundle == null ? new Bundle() : new Bundle(bundle);
        String str2 = context.getApplicationInfo().packageName;
        bundle2.putString("clientPackageName", str2);
        if (TextUtils.isEmpty(bundle2.getString(KEY_ANDROID_PACKAGE_NAME))) {
            bundle2.putString(KEY_ANDROID_PACKAGE_NAME, str2);
        }
        bundle2.putLong("service_connection_start_time_millis", SystemClock.elapsedRealtime());
        return (TokenData) zza(context, zzaaV, new zza<TokenData>() {
            /* renamed from: zzar */
            public TokenData zzas(IBinder iBinder) throws RemoteException, IOException, GoogleAuthException {
                Bundle bundle = (Bundle) zzd.zzn(com.google.android.gms.internal.zzbn.zza.zza(iBinder).zza(account, str, bundle2));
                TokenData zzd = TokenData.zzd(bundle, "tokenDetails");
                if (zzd != null) {
                    return zzd;
                }
                String string = bundle.getString("Error");
                Intent intent = (Intent) bundle.getParcelable("userRecoveryIntent");
                zzmx zzcv = zzmx.zzcv(string);
                if (zzmx.zza(zzcv)) {
                    throw new UserRecoverableAuthException(string, intent);
                } else if (zzmx.zzb(zzcv)) {
                    throw new IOException(string);
                } else {
                    throw new GoogleAuthException(string);
                }
            }
        });
    }

    private static <T> T zzn(T t) throws IOException {
        if (t != null) {
            return t;
        }
        Log.w("GoogleAuthUtil", "Binder call returned null.");
        throw new IOException("Service unavailable.");
    }
}
