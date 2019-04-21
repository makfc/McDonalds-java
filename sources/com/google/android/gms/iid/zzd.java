package com.google.android.gms.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.util.zzx;
import com.newrelic.agent.android.agentdata.HexAttributes;
import java.io.File;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class zzd {
    SharedPreferences zzaUD;
    Context zzov;

    public zzd(Context context) {
        this(context, "com.google.android.gms.appid");
    }

    public zzd(Context context, String str) {
        this.zzov = context;
        this.zzaUD = context.getSharedPreferences(str, 4);
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf("-no-backup");
        zzeC(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
    }

    private void zzeC(String str) {
        File file = new File(zzx.getNoBackupFilesDir(this.zzov), str);
        if (!file.exists()) {
            try {
                if (file.createNewFile() && !isEmpty()) {
                    Log.i("InstanceID/Store", "App restored, clearing state");
                    InstanceIDListenerService.zza(this.zzov, this);
                }
            } catch (IOException e) {
                if (Log.isLoggable("InstanceID/Store", 3)) {
                    String str2 = "InstanceID/Store";
                    String str3 = "Error creating file in no backup dir: ";
                    String valueOf = String.valueOf(e.getMessage());
                    Log.d(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
                }
            }
        }
    }

    private String zzh(String str, String str2, String str3) {
        String valueOf = String.valueOf("|T|");
        return new StringBuilder((((String.valueOf(str).length() + 1) + String.valueOf(valueOf).length()) + String.valueOf(str2).length()) + String.valueOf(str3).length()).append(str).append(valueOf).append(str2).append("|").append(str3).toString();
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized String get(String str) {
        return this.zzaUD.getString(str, null);
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized String get(String str, String str2) {
        SharedPreferences sharedPreferences;
        String valueOf;
        sharedPreferences = this.zzaUD;
        valueOf = String.valueOf("|S|");
        return sharedPreferences.getString(new StringBuilder(((String.valueOf(str).length() + 0) + String.valueOf(valueOf).length()) + String.valueOf(str2).length()).append(str).append(valueOf).append(str2).toString(), null);
    }

    public boolean isEmpty() {
        return this.zzaUD.getAll().isEmpty();
    }

    public synchronized void zzCk() {
        this.zzaUD.edit().clear().commit();
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized void zza(Editor editor, String str, String str2, String str3) {
        String valueOf = String.valueOf("|S|");
        editor.putString(new StringBuilder(((String.valueOf(str).length() + 0) + String.valueOf(valueOf).length()) + String.valueOf(str2).length()).append(str).append(valueOf).append(str2).toString(), str3);
    }

    public synchronized void zza(String str, String str2, String str3, String str4, String str5) {
        String zzh = zzh(str, str2, str3);
        Editor edit = this.zzaUD.edit();
        edit.putString(zzh, str4);
        edit.putString(HexAttributes.HEX_ATTR_APP_VERSION, str5);
        edit.putString("lastToken", Long.toString(System.currentTimeMillis() / 1000));
        edit.commit();
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized KeyPair zzd(String str, long j) {
        KeyPair zzCc;
        zzCc = zza.zzCc();
        Editor edit = this.zzaUD.edit();
        zza(edit, str, "|P|", InstanceID.zzp(zzCc.getPublic().getEncoded()));
        zza(edit, str, "|K|", InstanceID.zzp(zzCc.getPrivate().getEncoded()));
        zza(edit, str, "cre", Long.toString(j));
        edit.commit();
        return zzCc;
    }

    public synchronized void zzeD(String str) {
        Editor edit = this.zzaUD.edit();
        for (String str2 : this.zzaUD.getAll().keySet()) {
            if (str2.startsWith(str)) {
                edit.remove(str2);
            }
        }
        edit.commit();
    }

    public KeyPair zzeE(String str) {
        return zzeH(str);
    }

    /* Access modifiers changed, original: 0000 */
    public void zzeF(String str) {
        zzeD(String.valueOf(str).concat("|"));
    }

    public void zzeG(String str) {
        zzeD(String.valueOf(str).concat("|T|"));
    }

    /* Access modifiers changed, original: 0000 */
    public KeyPair zzeH(String str) {
        String str2 = get(str, "|P|");
        String str3 = get(str, "|K|");
        if (str3 == null) {
            return null;
        }
        try {
            byte[] decode = Base64.decode(str2, 8);
            byte[] decode2 = Base64.decode(str3, 8);
            KeyFactory instance = KeyFactory.getInstance("RSA");
            return new KeyPair(instance.generatePublic(new X509EncodedKeySpec(decode)), instance.generatePrivate(new PKCS8EncodedKeySpec(decode2)));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            str2 = String.valueOf(e);
            Log.w("InstanceID/Store", new StringBuilder(String.valueOf(str2).length() + 19).append("Invalid key stored ").append(str2).toString());
            InstanceIDListenerService.zza(this.zzov, this);
            return null;
        }
    }

    public synchronized String zzi(String str, String str2, String str3) {
        return this.zzaUD.getString(zzh(str, str2, str3), null);
    }

    public synchronized void zzj(String str, String str2, String str3) {
        String zzh = zzh(str, str2, str3);
        Editor edit = this.zzaUD.edit();
        edit.remove(zzh);
        edit.commit();
    }
}
