package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.zzaa;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;

public class zzk {
    private static final Lock zzadb = new ReentrantLock();
    private static zzk zzadc;
    private final Lock zzadd = new ReentrantLock();
    private final SharedPreferences zzade;

    zzk(Context context) {
        this.zzade = context.getSharedPreferences("com.google.android.gms.signin", 0);
    }

    public static zzk zzab(Context context) {
        zzaa.zzz(context);
        zzadb.lock();
        try {
            if (zzadc == null) {
                zzadc = new zzk(context.getApplicationContext());
            }
            zzk zzk = zzadc;
            return zzk;
        } finally {
            zzadb.unlock();
        }
    }

    private String zzw(String str, String str2) {
        String valueOf = String.valueOf(":");
        return new StringBuilder(((String.valueOf(str).length() + 0) + String.valueOf(valueOf).length()) + String.valueOf(str2).length()).append(str).append(valueOf).append(str2).toString();
    }

    /* Access modifiers changed, original: 0000 */
    public GoogleSignInAccount zzcq(String str) {
        GoogleSignInAccount googleSignInAccount = null;
        if (TextUtils.isEmpty(str)) {
            return googleSignInAccount;
        }
        String zzcs = zzcs(zzw("googleSignInAccount", str));
        if (zzcs == null) {
            return googleSignInAccount;
        }
        try {
            return GoogleSignInAccount.zzcm(zzcs);
        } catch (JSONException e) {
            return googleSignInAccount;
        }
    }

    /* Access modifiers changed, original: protected */
    public String zzcs(String str) {
        this.zzadd.lock();
        try {
            String string = this.zzade.getString(str, null);
            return string;
        } finally {
            this.zzadd.unlock();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void zzct(String str) {
        if (!TextUtils.isEmpty(str)) {
            zzcu(zzw("googleSignInAccount", str));
            zzcu(zzw("googleSignInOptions", str));
        }
    }

    /* Access modifiers changed, original: protected */
    public void zzcu(String str) {
        this.zzadd.lock();
        try {
            this.zzade.edit().remove(str).apply();
        } finally {
            this.zzadd.unlock();
        }
    }

    public GoogleSignInAccount zzpC() {
        return zzcq(zzcs("defaultGoogleSignInAccount"));
    }

    public void zzpE() {
        String zzcs = zzcs("defaultGoogleSignInAccount");
        zzcu("defaultGoogleSignInAccount");
        zzct(zzcs);
    }
}
