package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzaf.zzj;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class zzct implements zze {
    private boolean mClosed;
    private final Context mContext;
    private final ScheduledExecutorService zzajS;
    private final String zzbnR;
    private String zzboo;
    private zzbm<zzj> zzbqr;
    private zzs zzbqs;
    private final zza zzbqu;
    private ScheduledFuture<?> zzbqv;

    interface zzb {
        ScheduledExecutorService zzKo();
    }

    /* renamed from: com.google.android.gms.tagmanager.zzct$1 */
    class C27251 implements zzb {
        C27251() {
        }

        public ScheduledExecutorService zzKo() {
            return Executors.newSingleThreadScheduledExecutor();
        }
    }

    interface zza {
        zzcs zza(zzs zzs);
    }

    /* renamed from: com.google.android.gms.tagmanager.zzct$2 */
    class C27262 implements zza {
        C27262() {
        }

        public zzcs zza(zzs zzs) {
            return new zzcs(zzct.this.mContext, zzct.this.zzbnR, zzs);
        }
    }

    public zzct(Context context, String str, zzs zzs) {
        this(context, str, zzs, null, null);
    }

    zzct(Context context, String str, zzs zzs, zzb zzb, zza zza) {
        this.zzbqs = zzs;
        this.mContext = context;
        this.zzbnR = str;
        if (zzb == null) {
            zzb = new C27251();
        }
        this.zzajS = zzb.zzKo();
        if (zza == null) {
            this.zzbqu = new C27262();
        } else {
            this.zzbqu = zza;
        }
    }

    private synchronized void zzKn() {
        if (this.mClosed) {
            throw new IllegalStateException("called method after closed");
        }
    }

    private zzcs zzgG(String str) {
        zzcs zza = this.zzbqu.zza(this.zzbqs);
        zza.zza(this.zzbqr);
        zza.zzgq(this.zzboo);
        zza.zzgF(str);
        return zza;
    }

    public synchronized void release() {
        zzKn();
        if (this.zzbqv != null) {
            this.zzbqv.cancel(false);
        }
        this.zzajS.shutdown();
        this.mClosed = true;
    }

    public synchronized void zza(zzbm<zzj> zzbm) {
        zzKn();
        this.zzbqr = zzbm;
    }

    public synchronized void zzf(long j, String str) {
        String str2 = this.zzbnR;
        zzbn.m7502v(new StringBuilder(String.valueOf(str2).length() + 55).append("loadAfterDelay: containerId=").append(str2).append(" delay=").append(j).toString());
        zzKn();
        if (this.zzbqr == null) {
            throw new IllegalStateException("callback must be set before loadAfterDelay() is called.");
        }
        if (this.zzbqv != null) {
            this.zzbqv.cancel(false);
        }
        this.zzbqv = this.zzajS.schedule(zzgG(str), j, TimeUnit.MILLISECONDS);
    }

    public synchronized void zzgq(String str) {
        zzKn();
        this.zzboo = str;
    }
}
