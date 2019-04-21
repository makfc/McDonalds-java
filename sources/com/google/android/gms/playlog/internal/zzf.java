package com.google.android.gms.playlog.internal;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.common.internal.zzd;
import com.google.android.gms.playlog.internal.zzb.zza;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class zzf extends zzd<zza> {
    private final String zzZC;
    private final zzd zzblh;
    private final zzb zzbli;
    private boolean zzblj;
    private final Object zzpp;

    private void zzIi() {
        zzb.zzai(!this.zzblj);
        if (!this.zzbli.isEmpty()) {
            PlayLoggerContext playLoggerContext = null;
            try {
                List arrayList = new ArrayList();
                Iterator it = this.zzbli.zzIg().iterator();
                while (it.hasNext()) {
                    PlayLoggerContext playLoggerContext2;
                    zza zza = (zza) it.next();
                    if (zza.zzbkU.equals(playLoggerContext)) {
                        arrayList.add(zza.zzbkV);
                        playLoggerContext2 = playLoggerContext;
                    } else {
                        if (!arrayList.isEmpty()) {
                            ((zza) zztm()).zza(this.zzZC, playLoggerContext, arrayList);
                            arrayList.clear();
                        }
                        PlayLoggerContext playLoggerContext3 = zza.zzbkU;
                        arrayList.add(zza.zzbkV);
                        playLoggerContext2 = playLoggerContext3;
                    }
                    playLoggerContext = playLoggerContext2;
                }
                if (!arrayList.isEmpty()) {
                    ((zza) zztm()).zza(this.zzZC, playLoggerContext, arrayList);
                }
                this.zzbli.clear();
            } catch (RemoteException e) {
                Log.e("PlayLoggerImpl", "Couldn't send cached log events to AndroidLog service.  Retaining in memory cache.");
            }
        }
    }

    public void stop() {
        synchronized (this.zzpp) {
            this.zzblh.zzaA(false);
            disconnect();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void zzaB(boolean z) {
        synchronized (this.zzpp) {
            boolean z2 = this.zzblj;
            this.zzblj = z;
            if (z2 && !this.zzblj) {
                zzIi();
            }
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: zzdR */
    public zza zzab(IBinder iBinder) {
        return zza.zza.zzdQ(iBinder);
    }

    /* Access modifiers changed, original: protected */
    public String zzhT() {
        return "com.google.android.gms.playlog.service.START";
    }

    /* Access modifiers changed, original: protected */
    public String zzhU() {
        return "com.google.android.gms.playlog.internal.IPlayLogService";
    }
}
