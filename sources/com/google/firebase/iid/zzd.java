package com.google.firebase.iid;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.newrelic.agent.android.agentdata.HexAttributes;
import java.io.IOException;
import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

public class zzd {
    static Map<String, zzd> zzaTZ = new HashMap();
    static String zzaUf;
    private static zzg zzbSO;
    private static zzf zzbSP;
    Context mContext;
    KeyPair zzaUc;
    String zzaUd = "";
    long zzaUe;

    protected zzd(Context context, String str, Bundle bundle) {
        this.mContext = context.getApplicationContext();
        this.zzaUd = str;
    }

    public static synchronized zzd zzb(Context context, Bundle bundle) {
        zzd zzd;
        synchronized (zzd.class) {
            Object obj;
            String string = bundle == null ? "" : bundle.getString("subtype");
            if (string == null) {
                obj = "";
            } else {
                String obj2 = string;
            }
            Context applicationContext = context.getApplicationContext();
            if (zzbSO == null) {
                zzbSO = new zzg(applicationContext);
                zzbSP = new zzf(applicationContext);
            }
            zzaUf = Integer.toString(FirebaseInstanceId.zzaU(applicationContext));
            zzd = (zzd) zzaTZ.get(obj2);
            if (zzd == null) {
                zzd = new zzd(applicationContext, obj2, bundle);
                zzaTZ.put(obj2, zzd);
            }
        }
        return zzd;
    }

    public String getToken(String str, String str2, Bundle bundle) throws IOException {
        Object obj = null;
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        Object obj2 = 1;
        String zzi = zzCh() ? null : zzbSO.zzi(this.zzaUd, str, str2);
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
                zzbSO.zza(this.zzaUd, str, str2, zzi, zzaUf);
            }
        }
        return zzi;
    }

    /* Access modifiers changed, original: 0000 */
    public KeyPair zzCd() {
        if (this.zzaUc == null) {
            this.zzaUc = zzbSO.zzeE(this.zzaUd);
        }
        if (this.zzaUc == null) {
            this.zzaUe = System.currentTimeMillis();
            this.zzaUc = zzbSO.zzd(this.zzaUd, this.zzaUe);
        }
        return this.zzaUc;
    }

    public void zzCe() {
        this.zzaUe = 0;
        zzbSO.zzeF(this.zzaUd);
        this.zzaUc = null;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean zzCh() {
        String str = zzbSO.get(HexAttributes.HEX_ATTR_APP_VERSION);
        if (str == null || !str.equals(zzaUf)) {
            return true;
        }
        str = zzbSO.get("lastToken");
        if (str == null) {
            return true;
        }
        return (System.currentTimeMillis() / 1000) - Long.valueOf(Long.parseLong(str)).longValue() > 604800;
    }

    public zzg zzUs() {
        return zzbSO;
    }

    public zzf zzUt() {
        return zzbSP;
    }

    public void zzb(String str, String str2, Bundle bundle) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        zzbSO.zzj(this.zzaUd, str, str2);
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
        zzbSP.zzs(zzbSP.zza(bundle, zzCd()));
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
        return zzbSP.zzs(zzbSP.zza(bundle, zzCd()));
    }
}
