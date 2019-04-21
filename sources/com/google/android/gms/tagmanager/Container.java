package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzabt;
import com.google.android.gms.internal.zzabt.zzc;
import com.google.android.gms.internal.zzabt.zzg;
import com.google.android.gms.internal.zzaf.zzf;
import com.google.android.gms.internal.zzaf.zzi;
import com.google.android.gms.internal.zzaf.zzj;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Container {
    private final Context mContext;
    private final String zzbnR;
    private final DataLayer zzbnS;
    private zzcw zzbnT;
    private Map<String, FunctionCallMacroCallback> zzbnU = new HashMap();
    private Map<String, FunctionCallTagCallback> zzbnV = new HashMap();
    private volatile long zzbnW;
    private volatile String zzbnX = "";

    public interface FunctionCallMacroCallback {
        Object getValue(String str, Map<String, Object> map);
    }

    public interface FunctionCallTagCallback {
        void execute(String str, Map<String, Object> map);
    }

    private class zza implements com.google.android.gms.tagmanager.zzt.zza {
        private zza() {
        }

        public Object zzd(String str, Map<String, Object> map) {
            FunctionCallMacroCallback zzgj = Container.this.zzgj(str);
            return zzgj == null ? null : zzgj.getValue(str, map);
        }
    }

    private class zzb implements com.google.android.gms.tagmanager.zzt.zza {
        private zzb() {
        }

        public Object zzd(String str, Map<String, Object> map) {
            FunctionCallTagCallback zzgk = Container.this.zzgk(str);
            if (zzgk != null) {
                zzgk.execute(str, map);
            }
            return zzdl.zzKS();
        }
    }

    Container(Context context, DataLayer dataLayer, String str, long j, zzc zzc) {
        this.mContext = context;
        this.zzbnS = dataLayer;
        this.zzbnR = str;
        this.zzbnW = j;
        zza(zzc);
    }

    Container(Context context, DataLayer dataLayer, String str, long j, zzj zzj) {
        this.mContext = context;
        this.zzbnS = dataLayer;
        this.zzbnR = str;
        this.zzbnW = j;
        zza(zzj.zzjG);
        if (zzj.zzjF != null) {
            zza(zzj.zzjF);
        }
    }

    private synchronized zzcw zzJl() {
        return this.zzbnT;
    }

    private void zza(zzc zzc) {
        this.zzbnX = zzc.getVersion();
        zzc zzc2 = zzc;
        zza(new zzcw(this.mContext, zzc2, this.zzbnS, new zza(), new zzb(), zzgm(this.zzbnX)));
        if (getBoolean("_gtm.loadEventEnabled")) {
            this.zzbnS.pushEvent("gtm.load", DataLayer.mapOf("gtm.id", this.zzbnR));
        }
    }

    private void zza(zzf zzf) {
        if (zzf == null) {
            throw new NullPointerException();
        }
        try {
            zza(zzabt.zzb(zzf));
        } catch (zzg e) {
            String valueOf = String.valueOf(zzf);
            String valueOf2 = String.valueOf(e.toString());
            zzbn.m7501e(new StringBuilder((String.valueOf(valueOf).length() + 46) + String.valueOf(valueOf2).length()).append("Not loading resource: ").append(valueOf).append(" because it is invalid: ").append(valueOf2).toString());
        }
    }

    private synchronized void zza(zzcw zzcw) {
        this.zzbnT = zzcw;
    }

    private void zza(zzi[] zziArr) {
        ArrayList arrayList = new ArrayList();
        for (Object add : zziArr) {
            arrayList.add(add);
        }
        zzJl().zzJ(arrayList);
    }

    public boolean getBoolean(String str) {
        zzcw zzJl = zzJl();
        if (zzJl == null) {
            zzbn.m7501e("getBoolean called for closed container.");
            return zzdl.zzKQ().booleanValue();
        }
        try {
            return zzdl.zzk((com.google.android.gms.internal.zzag.zza) zzJl.zzgH(str).getObject()).booleanValue();
        } catch (Exception e) {
            String valueOf = String.valueOf(e.getMessage());
            zzbn.m7501e(new StringBuilder(String.valueOf(valueOf).length() + 66).append("Calling getBoolean() threw an exception: ").append(valueOf).append(" Returning default value.").toString());
            return zzdl.zzKQ().booleanValue();
        }
    }

    public String getContainerId() {
        return this.zzbnR;
    }

    public long getLastRefreshTime() {
        return this.zzbnW;
    }

    public boolean isDefault() {
        return getLastRefreshTime() == 0;
    }

    /* Access modifiers changed, original: 0000 */
    public void release() {
        this.zzbnT = null;
    }

    public String zzJk() {
        return this.zzbnX;
    }

    /* Access modifiers changed, original: 0000 */
    public FunctionCallMacroCallback zzgj(String str) {
        FunctionCallMacroCallback functionCallMacroCallback;
        synchronized (this.zzbnU) {
            functionCallMacroCallback = (FunctionCallMacroCallback) this.zzbnU.get(str);
        }
        return functionCallMacroCallback;
    }

    public FunctionCallTagCallback zzgk(String str) {
        FunctionCallTagCallback functionCallTagCallback;
        synchronized (this.zzbnV) {
            functionCallTagCallback = (FunctionCallTagCallback) this.zzbnV.get(str);
        }
        return functionCallTagCallback;
    }

    public void zzgl(String str) {
        zzJl().zzgl(str);
    }

    /* Access modifiers changed, original: 0000 */
    public zzai zzgm(String str) {
        if (zzci.zzKh().zzKi().equals(zza.CONTAINER_DEBUG)) {
        }
        return new zzbv();
    }
}
