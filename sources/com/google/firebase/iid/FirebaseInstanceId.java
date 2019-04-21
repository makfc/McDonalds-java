package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.p000v4.util.ArrayMap;
import android.util.Base64;
import android.util.Log;
import com.google.firebase.FirebaseApp;
import java.io.IOException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class FirebaseInstanceId {
    private static Map<String, FirebaseInstanceId> zzaTZ = new ArrayMap();
    private static zze zzbSC;
    private final FirebaseApp zzbSD;
    private final zzd zzbSE;
    private final String zzbSF = zzUm();

    private FirebaseInstanceId(FirebaseApp firebaseApp, zzd zzd) {
        this.zzbSD = firebaseApp;
        this.zzbSE = zzd;
        if (this.zzbSF == null) {
            throw new IllegalStateException("IID failing to initialize, FirebaseApp is missing project ID");
        }
        FirebaseInstanceIdService.zza(this.zzbSD.getApplicationContext(), this);
    }

    public static FirebaseInstanceId getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    @Keep
    public static synchronized FirebaseInstanceId getInstance(@NonNull FirebaseApp firebaseApp) {
        FirebaseInstanceId firebaseInstanceId;
        synchronized (FirebaseInstanceId.class) {
            firebaseInstanceId = (FirebaseInstanceId) zzaTZ.get(firebaseApp.getOptions().getApplicationId());
            if (firebaseInstanceId == null) {
                zzd zzb = zzd.zzb(firebaseApp.getApplicationContext(), null);
                if (zzbSC == null) {
                    zzbSC = new zze(zzb.zzUs());
                }
                firebaseInstanceId = new FirebaseInstanceId(firebaseApp, zzb);
                zzaTZ.put(firebaseApp.getOptions().getApplicationId(), firebaseInstanceId);
            }
        }
        return firebaseInstanceId;
    }

    static String zza(KeyPair keyPair) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA1").digest(keyPair.getPublic().getEncoded());
            digest[0] = (byte) (((digest[0] & 15) + 112) & 255);
            return Base64.encodeToString(digest, 0, 8, 11);
        } catch (NoSuchAlgorithmException e) {
            Log.w("FirebaseInstanceId", "Unexpected error, device missing required alghorithms");
            return null;
        }
    }

    static void zza(Context context, zzg zzg) {
        zzg.zzCk();
        Intent intent = new Intent();
        intent.putExtra("CMD", "RST");
        context.sendBroadcast(FirebaseInstanceIdInternalReceiver.zzh(context, intent));
    }

    static int zzaU(Context context) {
        int i = 0;
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            String valueOf = String.valueOf(e);
            Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 38).append("Never happens: can't find own package ").append(valueOf).toString());
            return i;
        }
    }

    static String zzaV(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            String valueOf = String.valueOf(e);
            Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 38).append("Never happens: can't find own package ").append(valueOf).toString());
            return null;
        }
    }

    static void zzaW(Context context) {
        Intent intent = new Intent();
        intent.setPackage(context.getPackageName());
        intent.putExtra("CMD", "SYNC");
        context.sendBroadcast(FirebaseInstanceIdInternalReceiver.zzh(context, intent));
    }

    static String zzbw(Context context) {
        return getInstance().zzbSD.getOptions().getApplicationId();
    }

    static String zzp(byte[] bArr) {
        return Base64.encodeToString(bArr, 11);
    }

    public String getId() {
        return zza(this.zzbSE.zzCd());
    }

    @Nullable
    public String getToken() {
        String zzUn = zzUn();
        if (zzUn == null) {
            FirebaseInstanceIdService.zzbx(this.zzbSD.getApplicationContext());
        }
        return zzUn;
    }

    @WorkerThread
    public String getToken(String str, String str2) throws IOException {
        return this.zzbSE.getToken(str, str2, null);
    }

    /* Access modifiers changed, original: 0000 */
    public String zzUm() {
        String gcmSenderId = this.zzbSD.getOptions().getGcmSenderId();
        if (gcmSenderId != null) {
            return gcmSenderId;
        }
        gcmSenderId = this.zzbSD.getOptions().getApplicationId();
        if (!gcmSenderId.startsWith("1:")) {
            return gcmSenderId;
        }
        String[] split = gcmSenderId.split(":");
        if (split.length < 2) {
            return null;
        }
        gcmSenderId = split[1];
        return gcmSenderId.isEmpty() ? null : gcmSenderId;
    }

    /* Access modifiers changed, original: 0000 */
    @Nullable
    public String zzUn() {
        return this.zzbSE.zzUs().zzi("", this.zzbSF, "*");
    }

    /* Access modifiers changed, original: 0000 */
    public String zzUo() throws IOException {
        return getToken(this.zzbSF, "*");
    }

    /* Access modifiers changed, original: 0000 */
    public zze zzUp() {
        return zzbSC;
    }

    /* Access modifiers changed, original: 0000 */
    public void zziA(String str) throws IOException {
        if (getToken() == null) {
            throw new IOException("token not available");
        }
        Bundle bundle = new Bundle();
        String str2 = "gcm.topic";
        String valueOf = String.valueOf("/topics/");
        String valueOf2 = String.valueOf(str);
        bundle.putString(str2, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        zzd zzd = this.zzbSE;
        valueOf = getToken();
        String valueOf3 = String.valueOf("/topics/");
        valueOf2 = String.valueOf(str);
        zzd.zzb(valueOf, valueOf2.length() != 0 ? valueOf3.concat(valueOf2) : new String(valueOf3), bundle);
    }

    /* Access modifiers changed, original: 0000 */
    public void zziz(String str) throws IOException {
        if (getToken() == null) {
            throw new IOException("token not available");
        }
        Bundle bundle = new Bundle();
        String str2 = "gcm.topic";
        String valueOf = String.valueOf("/topics/");
        String valueOf2 = String.valueOf(str);
        bundle.putString(str2, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        zzd zzd = this.zzbSE;
        valueOf = getToken();
        String valueOf3 = String.valueOf("/topics/");
        valueOf2 = String.valueOf(str);
        zzd.getToken(valueOf, valueOf2.length() != 0 ? valueOf3.concat(valueOf2) : new String(valueOf3), bundle);
    }
}
