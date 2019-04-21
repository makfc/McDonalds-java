package com.google.android.gms.analytics;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.analytics.internal.zzf;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.zzlw;
import java.util.ListIterator;

public class zza extends zzh<zza> {
    private final zzf zzTE;
    private boolean zzTF;

    public zza(zzf zzf) {
        super(zzf.zzlT(), zzf.zzlQ());
        this.zzTE = zzf;
    }

    public void enableAdvertisingIdCollection(boolean z) {
        this.zzTF = z;
    }

    /* Access modifiers changed, original: protected */
    public void zza(zze zze) {
        zzlw zzlw = (zzlw) zze.zzb(zzlw.class);
        if (TextUtils.isEmpty(zzlw.zzku())) {
            zzlw.setClientId(this.zzTE.zzmh().zzmP());
        }
        if (this.zzTF && TextUtils.isEmpty(zzlw.zzls())) {
            com.google.android.gms.analytics.internal.zza zzmg = this.zzTE.zzmg();
            zzlw.zzbw(zzmg.zzlE());
            zzlw.zzN(zzmg.zzlt());
        }
    }

    public void zzbf(String str) {
        zzaa.zzdl(str);
        zzbg(str);
        zzkK().add(new zzb(this.zzTE, str));
    }

    public void zzbg(String str) {
        Uri zzbh = zzb.zzbh(str);
        ListIterator listIterator = zzkK().listIterator();
        while (listIterator.hasNext()) {
            if (zzbh.equals(((zzk) listIterator.next()).zzkn())) {
                listIterator.remove();
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public zzf zzkj() {
        return this.zzTE;
    }

    public zze zzkk() {
        zze zzky = zzkJ().zzky();
        zzky.zza(this.zzTE.zzlY().zzmx());
        zzky.zza(this.zzTE.zzlZ().zznE());
        zzd(zzky);
        return zzky;
    }
}
