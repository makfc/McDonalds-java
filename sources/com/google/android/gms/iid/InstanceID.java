package com.google.android.gms.iid;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import com.newrelic.agent.android.agentdata.HexAttributes;
import java.io.IOException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class InstanceID {
    static Map<String, InstanceID> zzaTZ = new HashMap();
    private static zzd zzaUa;
    private static zzc zzaUb;
    static String zzaUf;
    Context mContext;
    KeyPair zzaUc;
    String zzaUd = "";
    long zzaUe;

    protected InstanceID(Context context, String str, Bundle bundle) {
        this.mContext = context.getApplicationContext();
        this.zzaUd = str;
    }

    public static InstanceID getInstance(Context context) {
        return zza(context, null);
    }

    public static synchronized InstanceID zza(Context context, Bundle bundle) {
        InstanceID instanceID;
        synchronized (InstanceID.class) {
            Object obj;
            String string = bundle == null ? "" : bundle.getString("subtype");
            if (string == null) {
                obj = "";
            } else {
                String obj2 = string;
            }
            Context applicationContext = context.getApplicationContext();
            if (zzaUa == null) {
                zzaUa = new zzd(applicationContext);
                zzaUb = new zzc(applicationContext);
            }
            zzaUf = Integer.toString(zzaU(applicationContext));
            instanceID = (InstanceID) zzaTZ.get(obj2);
            if (instanceID == null) {
                instanceID = new InstanceID(applicationContext, obj2, bundle);
                zzaTZ.put(obj2, instanceID);
            }
        }
        return instanceID;
    }

    static String zza(KeyPair keyPair) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA1").digest(keyPair.getPublic().getEncoded());
            digest[0] = (byte) (((digest[0] & 15) + 112) & 255);
            return Base64.encodeToString(digest, 0, 8, 11);
        } catch (NoSuchAlgorithmException e) {
            Log.w("InstanceID", "Unexpected error, device missing required alghorithms");
            return null;
        }
    }

    static int zzaU(Context context) {
        int i = 0;
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            String valueOf = String.valueOf(e);
            Log.w("InstanceID", new StringBuilder(String.valueOf(valueOf).length() + 38).append("Never happens: can't find own package ").append(valueOf).toString());
            return i;
        }
    }

    static String zzaV(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            String valueOf = String.valueOf(e);
            Log.w("InstanceID", new StringBuilder(String.valueOf(valueOf).length() + 38).append("Never happens: can't find own package ").append(valueOf).toString());
            return null;
        }
    }

    static String zzp(byte[] bArr) {
        return Base64.encodeToString(bArr, 11);
    }

    public void deleteToken(String str, String str2) throws IOException {
        zzb(str, str2, null);
    }

    public String getToken(String str, String str2, Bundle bundle) throws IOException {
        Object obj = null;
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        Object obj2 = 1;
        String zzi = zzCh() ? null : zzaUa.zzi(this.zzaUd, str, str2);
        if (zzi == null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            if (bundle.getString("ttl") != null) {
                obj2 = null;
            }
            if (!"jwt".equals(bundle.getString("type"))) {
                obj = obj2;
            }
            zzi = zzc(str, str2, bundle);
            if (!(zzi == null || obj == null)) {
                zzaUa.zza(this.zzaUd, str, str2, zzi, zzaUf);
            }
        }
        return zzi;
    }

    /* Access modifiers changed, original: 0000 */
    public KeyPair zzCd() {
        if (this.zzaUc == null) {
            this.zzaUc = zzaUa.zzeE(this.zzaUd);
        }
        if (this.zzaUc == null) {
            this.zzaUe = System.currentTimeMillis();
            this.zzaUc = zzaUa.zzd(this.zzaUd, this.zzaUe);
        }
        return this.zzaUc;
    }

    public void zzCe() {
        this.zzaUe = 0;
        zzaUa.zzeF(this.zzaUd);
        this.zzaUc = null;
    }

    public zzd zzCf() {
        return zzaUa;
    }

    public zzc zzCg() {
        return zzaUb;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean zzCh() {
        String str = zzaUa.get(HexAttributes.HEX_ATTR_APP_VERSION);
        if (str == null || !str.equals(zzaUf)) {
            return true;
        }
        str = zzaUa.get("lastToken");
        if (str == null) {
            return true;
        }
        return (System.currentTimeMillis() / 1000) - Long.valueOf(Long.parseLong(str)).longValue() > 604800;
    }

    public void zzb(String str, String str2, Bundle bundle) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        zzaUa.zzj(this.zzaUd, str, str2);
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("sender", str);
        if (str2 != null) {
            bundle.putString("scope", str2);
        }
        bundle.putString("subscription", str);
        bundle.putString("delete", "1");
        bundle.putString("X-delete", "1");
        bundle.putString("subtype", "".equals(this.zzaUd) ? str : this.zzaUd);
        String str3 = "X-subtype";
        if (!"".equals(this.zzaUd)) {
            str = this.zzaUd;
        }
        bundle.putString(str3, str);
        zzaUb.zzs(zzaUb.zza(bundle, zzCd()));
    }

    public String zzc(String str, String str2, Bundle bundle) throws IOException {
        if (str2 != null) {
            bundle.putString("scope", str2);
        }
        bundle.putString("sender", str);
        String str3 = "".equals(this.zzaUd) ? str : this.zzaUd;
        if (!bundle.containsKey("legacy.register")) {
            bundle.putString("subscription", str);
            bundle.putString("subtype", str3);
            bundle.putString("X-subscription", str);
            bundle.putString("X-subtype", str3);
        }
        return zzaUb.zzs(zzaUb.zza(bundle, zzCd()));
    }
}
