package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.internal.zzabs;
import com.google.android.gms.internal.zzaf.zzj;
import com.google.android.gms.internal.zznv;

public class zzp extends zznv<ContainerHolder> {
    private final Context mContext;
    private final Looper zzakW;
    private final String zzbnR;
    private long zzbnW;
    private final TagManager zzbod;
    private final zzd zzbog;
    private final zzck zzboh;
    private final int zzboi;
    private zzf zzboj;
    private zzabs zzbok;
    private volatile zzo zzbol;
    private volatile boolean zzbom;
    private zzj zzbon;
    private String zzboo;
    private zze zzbop;
    private zza zzboq;
    private final com.google.android.gms.common.util.zze zzsd;

    interface zze extends Releasable {
        void zza(zzbm<zzj> zzbm);

        void zzf(long j, String str);

        void zzgq(String str);
    }

    interface zzf extends Releasable {
        void zzJu();

        void zza(zzbm<com.google.android.gms.internal.zzabr.zza> zzbm);

        void zzb(com.google.android.gms.internal.zzabr.zza zza);

        com.google.android.gms.internal.zzabt.zzc zzkN(int i);
    }

    /* renamed from: com.google.android.gms.tagmanager.zzp$1 */
    class C27401 {
    }

    /* renamed from: com.google.android.gms.tagmanager.zzp$2 */
    class C27412 implements com.google.android.gms.tagmanager.zzo.zza {
        final /* synthetic */ zzp zzbor;

        public String zzJm() {
            return this.zzbor.zzJm();
        }

        public void zzJo() {
            zzbn.zzaW("Refresh ignored: container loaded as default only.");
        }

        public void zzgn(String str) {
            this.zzbor.zzgn(str);
        }
    }

    interface zza {
        boolean zzb(Container container);
    }

    private class zzb implements zzbm<com.google.android.gms.internal.zzabr.zza> {
        private zzb() {
        }

        /* synthetic */ zzb(zzp zzp, C27401 c27401) {
            this();
        }

        public void zzJt() {
        }

        /* renamed from: zza */
        public void onSuccess(com.google.android.gms.internal.zzabr.zza zza) {
            zzj zzj;
            if (zza.zzbwa != null) {
                zzj = zza.zzbwa;
            } else {
                com.google.android.gms.internal.zzaf.zzf zzf = zza.zzjG;
                zzj = new zzj();
                zzj.zzjG = zzf;
                zzj.zzjF = null;
                zzj.zzjH = zzf.version;
            }
            zzp.this.zza(zzj, zza.zzbvZ, true);
        }

        public void zza(com.google.android.gms.tagmanager.zzbm.zza zza) {
            if (!zzp.this.zzbom) {
                zzp.this.zzap(0);
            }
        }
    }

    private class zzc implements zzbm<zzj> {
        private zzc() {
        }

        /* synthetic */ zzc(zzp zzp, C27401 c27401) {
            this();
        }

        public void zzJt() {
        }

        public void zza(com.google.android.gms.tagmanager.zzbm.zza zza) {
            synchronized (zzp.this) {
                if (!zzp.this.isReady()) {
                    if (zzp.this.zzbol != null) {
                        zzp.this.zzb(zzp.this.zzbol);
                    } else {
                        zzp.this.zzb(zzp.this.zzc(Status.zzalz));
                    }
                }
            }
            zzp.this.zzap(3600000);
        }

        /* JADX WARNING: Missing block: B:19:?, code skipped:
            return;
     */
        /* renamed from: zzb */
        public void onSuccess(com.google.android.gms.internal.zzaf.zzj r6) {
            /*
            r5 = this;
            r1 = com.google.android.gms.tagmanager.zzp.this;
            monitor-enter(r1);
            r0 = r6.zzjG;	 Catch:{ all -> 0x0067 }
            if (r0 != 0) goto L_0x002a;
        L_0x0007:
            r0 = com.google.android.gms.tagmanager.zzp.this;	 Catch:{ all -> 0x0067 }
            r0 = r0.zzbon;	 Catch:{ all -> 0x0067 }
            r0 = r0.zzjG;	 Catch:{ all -> 0x0067 }
            if (r0 != 0) goto L_0x0020;
        L_0x0011:
            r0 = "Current resource is null; network resource is also null";
            com.google.android.gms.tagmanager.zzbn.m7501e(r0);	 Catch:{ all -> 0x0067 }
            r0 = com.google.android.gms.tagmanager.zzp.this;	 Catch:{ all -> 0x0067 }
            r2 = 3600000; // 0x36ee80 float:5.044674E-39 double:1.7786363E-317;
            r0.zzap(r2);	 Catch:{ all -> 0x0067 }
            monitor-exit(r1);	 Catch:{ all -> 0x0067 }
        L_0x001f:
            return;
        L_0x0020:
            r0 = com.google.android.gms.tagmanager.zzp.this;	 Catch:{ all -> 0x0067 }
            r0 = r0.zzbon;	 Catch:{ all -> 0x0067 }
            r0 = r0.zzjG;	 Catch:{ all -> 0x0067 }
            r6.zzjG = r0;	 Catch:{ all -> 0x0067 }
        L_0x002a:
            r0 = com.google.android.gms.tagmanager.zzp.this;	 Catch:{ all -> 0x0067 }
            r2 = com.google.android.gms.tagmanager.zzp.this;	 Catch:{ all -> 0x0067 }
            r2 = r2.zzsd;	 Catch:{ all -> 0x0067 }
            r2 = r2.currentTimeMillis();	 Catch:{ all -> 0x0067 }
            r4 = 0;
            r0.zza(r6, r2, r4);	 Catch:{ all -> 0x0067 }
            r0 = com.google.android.gms.tagmanager.zzp.this;	 Catch:{ all -> 0x0067 }
            r2 = r0.zzbnW;	 Catch:{ all -> 0x0067 }
            r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0067 }
            r4 = 58;
            r0.<init>(r4);	 Catch:{ all -> 0x0067 }
            r4 = "setting refresh time to current time: ";
            r0 = r0.append(r4);	 Catch:{ all -> 0x0067 }
            r0 = r0.append(r2);	 Catch:{ all -> 0x0067 }
            r0 = r0.toString();	 Catch:{ all -> 0x0067 }
            com.google.android.gms.tagmanager.zzbn.m7502v(r0);	 Catch:{ all -> 0x0067 }
            r0 = com.google.android.gms.tagmanager.zzp.this;	 Catch:{ all -> 0x0067 }
            r0 = r0.zzJs();	 Catch:{ all -> 0x0067 }
            if (r0 != 0) goto L_0x0065;
        L_0x0060:
            r0 = com.google.android.gms.tagmanager.zzp.this;	 Catch:{ all -> 0x0067 }
            r0.zza(r6);	 Catch:{ all -> 0x0067 }
        L_0x0065:
            monitor-exit(r1);	 Catch:{ all -> 0x0067 }
            goto L_0x001f;
        L_0x0067:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0067 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzp$zzc.onSuccess(com.google.android.gms.internal.zzaf$zzj):void");
        }
    }

    private class zzd implements com.google.android.gms.tagmanager.zzo.zza {
        private zzd() {
        }

        /* synthetic */ zzd(zzp zzp, C27401 c27401) {
            this();
        }

        public String zzJm() {
            return zzp.this.zzJm();
        }

        public void zzJo() {
            if (zzp.this.zzboh.zznY()) {
                zzp.this.zzap(0);
            }
        }

        public void zzgn(String str) {
            zzp.this.zzgn(str);
        }
    }

    zzp(Context context, TagManager tagManager, Looper looper, String str, int i, zzf zzf, zze zze, zzabs zzabs, com.google.android.gms.common.util.zze zze2, zzck zzck) {
        super(looper == null ? Looper.getMainLooper() : looper);
        this.mContext = context;
        this.zzbod = tagManager;
        if (looper == null) {
            looper = Looper.getMainLooper();
        }
        this.zzakW = looper;
        this.zzbnR = str;
        this.zzboi = i;
        this.zzboj = zzf;
        this.zzbop = zze;
        this.zzbok = zzabs;
        this.zzbog = new zzd(this, null);
        this.zzbon = new zzj();
        this.zzsd = zze2;
        this.zzboh = zzck;
        if (zzJs()) {
            zzgn(zzci.zzKh().zzKj());
        }
    }

    public zzp(Context context, TagManager tagManager, Looper looper, String str, int i, zzs zzs) {
        this(context, tagManager, looper, str, i, new zzcu(context, str), new zzct(context, str, zzs), new zzabs(context), zzh.zzuW(), new zzbl(30, 900000, 5000, "refreshing", zzh.zzuW()));
        this.zzbok.zzhn(zzs.zzJv());
    }

    private boolean zzJs() {
        zzci zzKh = zzci.zzKh();
        return (zzKh.zzKi() == zza.CONTAINER || zzKh.zzKi() == zza.CONTAINER_DEBUG) && this.zzbnR.equals(zzKh.getContainerId());
    }

    private synchronized void zza(zzj zzj) {
        if (this.zzboj != null) {
            com.google.android.gms.internal.zzabr.zza zza = new com.google.android.gms.internal.zzabr.zza();
            zza.zzbvZ = this.zzbnW;
            zza.zzjG = new com.google.android.gms.internal.zzaf.zzf();
            zza.zzbwa = zzj;
            this.zzboj.zzb(zza);
        }
    }

    private synchronized void zza(zzj zzj, long j, boolean z) {
        if (z) {
            boolean z2 = this.zzbom;
        }
        if (!isReady() || this.zzbol == null) {
        }
        this.zzbon = zzj;
        this.zzbnW = j;
        zzap(Math.max(0, Math.min(43200000, (this.zzbnW + 43200000) - this.zzsd.currentTimeMillis())));
        Container container = new Container(this.mContext, this.zzbod.getDataLayer(), this.zzbnR, j, zzj);
        if (this.zzbol == null) {
            this.zzbol = new zzo(this.zzbod, this.zzakW, container, this.zzbog);
        } else {
            this.zzbol.zza(container);
        }
        if (!isReady() && this.zzboq.zzb(container)) {
            zzb(this.zzbol);
        }
    }

    private void zzaD(final boolean z) {
        this.zzboj.zza(new zzb(this, null));
        this.zzbop.zza(new zzc(this, null));
        com.google.android.gms.internal.zzabt.zzc zzkN = this.zzboj.zzkN(this.zzboi);
        if (zzkN != null) {
            this.zzbol = new zzo(this.zzbod, this.zzakW, new Container(this.mContext, this.zzbod.getDataLayer(), this.zzbnR, 0, zzkN), this.zzbog);
        }
        this.zzboq = new zza() {
            public boolean zzb(Container container) {
                return z ? container.getLastRefreshTime() + 43200000 >= zzp.this.zzsd.currentTimeMillis() : !container.isDefault();
            }
        };
        if (zzJs()) {
            this.zzbop.zzf(0, "");
        } else {
            this.zzboj.zzJu();
        }
    }

    private synchronized void zzap(long j) {
        if (this.zzbop == null) {
            zzbn.zzaW("Refresh requested, but no network load scheduler.");
        } else {
            this.zzbop.zzf(j, this.zzbon.zzjH);
        }
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized String zzJm() {
        return this.zzboo;
    }

    public void zzJr() {
        zzaD(true);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: zzbx */
    public ContainerHolder zzc(Status status) {
        if (this.zzbol != null) {
            return this.zzbol;
        }
        if (status == Status.zzalz) {
            zzbn.m7501e("timer expired: setting result to failure");
        }
        return new zzo(status);
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized void zzgn(String str) {
        this.zzboo = str;
        if (this.zzbop != null) {
            this.zzbop.zzgq(str);
        }
    }
}
