package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.support.annotation.WorkerThread;
import android.support.p000v4.util.ArrayMap;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.internal.zzamb;
import com.google.android.gms.internal.zzamc;
import com.google.android.gms.internal.zzso.zza;
import com.google.android.gms.internal.zzso.zzb;
import com.google.android.gms.internal.zzso.zzc;
import com.google.android.gms.measurement.AppMeasurement;
import java.io.IOException;
import java.util.Map;

public class zzv extends zzaa {
    private final Map<String, Map<String, String>> zzbec = new ArrayMap();
    private final Map<String, Map<String, Boolean>> zzbed = new ArrayMap();
    private final Map<String, Map<String, Boolean>> zzbee = new ArrayMap();
    private final Map<String, zzb> zzbef = new ArrayMap();
    private final Map<String, String> zzbeg = new ArrayMap();

    zzv(zzx zzx) {
        super(zzx);
    }

    private Map<String, String> zza(zzb zzb) {
        ArrayMap arrayMap = new ArrayMap();
        if (!(zzb == null || zzb.zzbgN == null)) {
            for (zzc zzc : zzb.zzbgN) {
                if (zzc != null) {
                    arrayMap.put(zzc.zzaB, zzc.value);
                }
            }
        }
        return arrayMap;
    }

    private void zza(String str, zzb zzb) {
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        if (!(zzb == null || zzb.zzbgO == null)) {
            for (zza zza : zzb.zzbgO) {
                if (zza != null) {
                    String str2 = (String) AppMeasurement.zza.zzbbm.get(zza.name);
                    if (str2 != null) {
                        zza.name = str2;
                    }
                    arrayMap.put(zza.name, zza.zzbgJ);
                    arrayMap2.put(zza.name, zza.zzbgK);
                }
            }
        }
        this.zzbed.put(str, arrayMap);
        this.zzbee.put(str, arrayMap2);
    }

    @WorkerThread
    private zzb zze(String str, byte[] bArr) {
        if (bArr == null) {
            return new zzb();
        }
        zzamb zzN = zzamb.zzN(bArr);
        zzb zzb = new zzb();
        try {
            zzb zzb2 = (zzb) zzb.mergeFrom(zzN);
            zzFm().zzFL().zze("Parsed config. version, gmp_app_id", zzb.zzbgL, zzb.zzbbK);
            return zzb;
        } catch (IOException e) {
            zzFm().zzFG().zze("Unable to merge remote config", str, e);
            return null;
        }
    }

    @WorkerThread
    private void zzfx(String str) {
        zzma();
        zzkN();
        zzaa.zzdl(str);
        if (!this.zzbef.containsKey(str)) {
            byte[] zzfm = zzFh().zzfm(str);
            if (zzfm == null) {
                this.zzbec.put(str, null);
                this.zzbed.put(str, null);
                this.zzbee.put(str, null);
                this.zzbef.put(str, null);
                this.zzbeg.put(str, null);
                return;
            }
            zzb zze = zze(str, zzfm);
            this.zzbec.put(str, zza(zze));
            zza(str, zze);
            this.zzbef.put(str, zze);
            this.zzbeg.put(str, null);
        }
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public /* bridge */ /* synthetic */ void zzFb() {
        super.zzFb();
    }

    public /* bridge */ /* synthetic */ zzc zzFc() {
        return super.zzFc();
    }

    public /* bridge */ /* synthetic */ zzac zzFd() {
        return super.zzFd();
    }

    public /* bridge */ /* synthetic */ zzn zzFe() {
        return super.zzFe();
    }

    public /* bridge */ /* synthetic */ zzg zzFf() {
        return super.zzFf();
    }

    public /* bridge */ /* synthetic */ zzad zzFg() {
        return super.zzFg();
    }

    public /* bridge */ /* synthetic */ zze zzFh() {
        return super.zzFh();
    }

    public /* bridge */ /* synthetic */ zzal zzFi() {
        return super.zzFi();
    }

    public /* bridge */ /* synthetic */ zzv zzFj() {
        return super.zzFj();
    }

    public /* bridge */ /* synthetic */ zzaf zzFk() {
        return super.zzFk();
    }

    public /* bridge */ /* synthetic */ zzw zzFl() {
        return super.zzFl();
    }

    public /* bridge */ /* synthetic */ zzp zzFm() {
        return super.zzFm();
    }

    public /* bridge */ /* synthetic */ zzt zzFn() {
        return super.zzFn();
    }

    public /* bridge */ /* synthetic */ zzd zzFo() {
        return super.zzFo();
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public String zzU(String str, String str2) {
        zzkN();
        zzfx(str);
        Map map = (Map) this.zzbec.get(str);
        return map != null ? (String) map.get(str2) : null;
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public boolean zzV(String str, String str2) {
        zzkN();
        zzfx(str);
        Map map = (Map) this.zzbed.get(str);
        if (map == null) {
            return false;
        }
        Boolean bool = (Boolean) map.get(str2);
        return bool == null ? false : bool.booleanValue();
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public boolean zzW(String str, String str2) {
        zzkN();
        zzfx(str);
        Map map = (Map) this.zzbee.get(str);
        if (map == null) {
            return false;
        }
        Boolean bool = (Boolean) map.get(str2);
        return bool == null ? false : bool.booleanValue();
    }

    /* Access modifiers changed, original: protected */
    @WorkerThread
    public boolean zzb(String str, byte[] bArr, String str2) {
        zzma();
        zzkN();
        zzaa.zzdl(str);
        zzb zze = zze(str, bArr);
        if (zze == null) {
            return false;
        }
        zza(str, zze);
        this.zzbef.put(str, zze);
        this.zzbeg.put(str, str2);
        this.zzbec.put(str, zza(zze));
        zzFc().zza(str, zze.zzbgP);
        try {
            zze.zzbgP = null;
            byte[] bArr2 = new byte[zze.getSerializedSize()];
            zze.writeTo(zzamc.zzO(bArr2));
            bArr = bArr2;
        } catch (IOException e) {
            zzFm().zzFG().zzj("Unable to serialize reduced-size config.  Storing full config instead.", e);
        }
        zzFh().zzd(str, bArr);
        return true;
    }

    /* Access modifiers changed, original: protected */
    @WorkerThread
    public void zzfA(String str) {
        zzkN();
        this.zzbeg.put(str, null);
    }

    /* Access modifiers changed, original: protected */
    @WorkerThread
    public zzb zzfy(String str) {
        zzma();
        zzkN();
        zzaa.zzdl(str);
        zzfx(str);
        return (zzb) this.zzbef.get(str);
    }

    /* Access modifiers changed, original: protected */
    @WorkerThread
    public String zzfz(String str) {
        zzkN();
        return (String) this.zzbeg.get(str);
    }

    public /* bridge */ /* synthetic */ void zzkN() {
        super.zzkN();
    }

    /* Access modifiers changed, original: protected */
    public void zzkO() {
    }

    public /* bridge */ /* synthetic */ void zzlP() {
        super.zzlP();
    }

    public /* bridge */ /* synthetic */ zze zzlQ() {
        return super.zzlQ();
    }
}
