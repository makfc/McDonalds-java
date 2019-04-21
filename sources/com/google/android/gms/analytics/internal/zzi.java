package com.google.android.gms.analytics.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.stats.zzb;
import java.util.Collections;

public class zzi extends zzd {
    private final zza zzWD = new zza();
    private zzac zzWE;
    private final zzt zzWF;
    private zzal zzWG;

    protected class zza implements ServiceConnection {
        private volatile zzac zzWI;
        private volatile boolean zzWJ;

        protected zza() {
        }

        /* JADX WARNING: Exception block dominator not found, dom blocks: [B:3:0x0008, B:9:0x0015] */
        /* JADX WARNING: Missing block: B:27:?, code skipped:
            r4.zzWH.zzbK("Service connect failed to get IAnalyticsService");
     */
        /* JADX WARNING: Missing block: B:30:?, code skipped:
            notifyAll();
     */
        public void onServiceConnected(android.content.ComponentName r5, android.os.IBinder r6) {
            /*
            r4 = this;
            r0 = "AnalyticsServiceConnection.onServiceConnected";
            com.google.android.gms.common.internal.zzaa.zzdc(r0);
            monitor-enter(r4);
            if (r6 != 0) goto L_0x0014;
        L_0x0008:
            r0 = com.google.android.gms.analytics.internal.zzi.this;	 Catch:{ all -> 0x005a }
            r1 = "Service connected with null binder";
            r0.zzbK(r1);	 Catch:{ all -> 0x005a }
            r4.notifyAll();	 Catch:{ all -> 0x0046 }
            monitor-exit(r4);	 Catch:{ all -> 0x0046 }
        L_0x0013:
            return;
        L_0x0014:
            r0 = 0;
            r1 = r6.getInterfaceDescriptor();	 Catch:{ RemoteException -> 0x0051 }
            r2 = "com.google.android.gms.analytics.internal.IAnalyticsService";
            r2 = r2.equals(r1);	 Catch:{ RemoteException -> 0x0051 }
            if (r2 == 0) goto L_0x0049;
        L_0x0021:
            r0 = com.google.android.gms.analytics.internal.zzac.zza.zzak(r6);	 Catch:{ RemoteException -> 0x0051 }
            r1 = com.google.android.gms.analytics.internal.zzi.this;	 Catch:{ RemoteException -> 0x0051 }
            r2 = "Bound to IAnalyticsService interface";
            r1.zzbG(r2);	 Catch:{ RemoteException -> 0x0051 }
        L_0x002c:
            if (r0 != 0) goto L_0x005f;
        L_0x002e:
            r0 = com.google.android.gms.common.stats.zzb.zzuH();	 Catch:{ IllegalArgumentException -> 0x007c }
            r1 = com.google.android.gms.analytics.internal.zzi.this;	 Catch:{ IllegalArgumentException -> 0x007c }
            r1 = r1.getContext();	 Catch:{ IllegalArgumentException -> 0x007c }
            r2 = com.google.android.gms.analytics.internal.zzi.this;	 Catch:{ IllegalArgumentException -> 0x007c }
            r2 = r2.zzWD;	 Catch:{ IllegalArgumentException -> 0x007c }
            r0.zza(r1, r2);	 Catch:{ IllegalArgumentException -> 0x007c }
        L_0x0041:
            r4.notifyAll();	 Catch:{ all -> 0x0046 }
            monitor-exit(r4);	 Catch:{ all -> 0x0046 }
            goto L_0x0013;
        L_0x0046:
            r0 = move-exception;
            monitor-exit(r4);	 Catch:{ all -> 0x0046 }
            throw r0;
        L_0x0049:
            r2 = com.google.android.gms.analytics.internal.zzi.this;	 Catch:{ RemoteException -> 0x0051 }
            r3 = "Got binder with a wrong descriptor";
            r2.zze(r3, r1);	 Catch:{ RemoteException -> 0x0051 }
            goto L_0x002c;
        L_0x0051:
            r1 = move-exception;
            r1 = com.google.android.gms.analytics.internal.zzi.this;	 Catch:{ all -> 0x005a }
            r2 = "Service connect failed to get IAnalyticsService";
            r1.zzbK(r2);	 Catch:{ all -> 0x005a }
            goto L_0x002c;
        L_0x005a:
            r0 = move-exception;
            r4.notifyAll();	 Catch:{ all -> 0x0046 }
            throw r0;	 Catch:{ all -> 0x0046 }
        L_0x005f:
            r1 = r4.zzWJ;	 Catch:{ all -> 0x005a }
            if (r1 != 0) goto L_0x0079;
        L_0x0063:
            r1 = com.google.android.gms.analytics.internal.zzi.this;	 Catch:{ all -> 0x005a }
            r2 = "onServiceConnected received after the timeout limit";
            r1.zzbJ(r2);	 Catch:{ all -> 0x005a }
            r1 = com.google.android.gms.analytics.internal.zzi.this;	 Catch:{ all -> 0x005a }
            r1 = r1.zzlT();	 Catch:{ all -> 0x005a }
            r2 = new com.google.android.gms.analytics.internal.zzi$zza$1;	 Catch:{ all -> 0x005a }
            r2.<init>(r0);	 Catch:{ all -> 0x005a }
            r1.zzf(r2);	 Catch:{ all -> 0x005a }
            goto L_0x0041;
        L_0x0079:
            r4.zzWI = r0;	 Catch:{ all -> 0x005a }
            goto L_0x0041;
        L_0x007c:
            r0 = move-exception;
            goto L_0x0041;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.internal.zzi$zza.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
        }

        public void onServiceDisconnected(final ComponentName componentName) {
            zzaa.zzdc("AnalyticsServiceConnection.onServiceDisconnected");
            zzi.this.zzlT().zzf(new Runnable() {
                public void run() {
                    zzi.this.onServiceDisconnected(componentName);
                }
            });
        }

        public zzac zzmp() {
            zzac zzac = null;
            zzi.this.zzkN();
            Intent intent = new Intent("com.google.android.gms.analytics.service.START");
            intent.setComponent(new ComponentName("com.google.android.gms", "com.google.android.gms.analytics.service.AnalyticsService"));
            Context context = zzi.this.getContext();
            intent.putExtra("app_package_name", context.getPackageName());
            zzb zzuH = zzb.zzuH();
            synchronized (this) {
                this.zzWI = null;
                this.zzWJ = true;
                boolean zza = zzuH.zza(context, intent, zzi.this.zzWD, 129);
                zzi.this.zza("Bind to service requested", Boolean.valueOf(zza));
                if (zza) {
                    try {
                        wait(zzi.this.zzlS().zzns());
                    } catch (InterruptedException e) {
                        zzi.this.zzbJ("Wait for service connect was interrupted");
                    }
                    this.zzWJ = false;
                    zzac = this.zzWI;
                    this.zzWI = null;
                    if (zzac == null) {
                        zzi.this.zzbK("Successfully bound to service but never got onServiceConnected callback");
                    }
                } else {
                    this.zzWJ = false;
                }
            }
            return zzac;
        }
    }

    protected zzi(zzf zzf) {
        super(zzf);
        this.zzWG = new zzal(zzf.zzlQ());
        this.zzWF = new zzt(zzf) {
            public void run() {
                zzi.this.zzmo();
            }
        };
    }

    private void onDisconnect() {
        zzkw().zzlL();
    }

    private void onServiceDisconnected(ComponentName componentName) {
        zzkN();
        if (this.zzWE != null) {
            this.zzWE = null;
            zza("Disconnected from device AnalyticsService", componentName);
            onDisconnect();
        }
    }

    private void zza(zzac zzac) {
        zzkN();
        this.zzWE = zzac;
        zzmn();
        zzkw().onServiceConnected();
    }

    private void zzmn() {
        this.zzWG.start();
        this.zzWF.zzv(zzlS().zznr());
    }

    private void zzmo() {
        zzkN();
        if (isConnected()) {
            zzbG("Inactivity, disconnecting from device AnalyticsService");
            disconnect();
        }
    }

    public boolean connect() {
        zzkN();
        zzma();
        if (this.zzWE != null) {
            return true;
        }
        zzac zzmp = this.zzWD.zzmp();
        if (zzmp == null) {
            return false;
        }
        this.zzWE = zzmp;
        zzmn();
        return true;
    }

    public void disconnect() {
        zzkN();
        zzma();
        try {
            zzb.zzuH().zza(getContext(), this.zzWD);
        } catch (IllegalArgumentException | IllegalStateException e) {
        }
        if (this.zzWE != null) {
            this.zzWE = null;
            onDisconnect();
        }
    }

    public boolean isConnected() {
        zzkN();
        zzma();
        return this.zzWE != null;
    }

    public boolean zzb(zzab zzab) {
        zzaa.zzz(zzab);
        zzkN();
        zzma();
        zzac zzac = this.zzWE;
        if (zzac == null) {
            return false;
        }
        try {
            zzac.zza(zzab.zzm(), zzab.zznT(), zzab.zznV() ? zzlS().zznk() : zzlS().zznl(), Collections.emptyList());
            zzmn();
            return true;
        } catch (RemoteException e) {
            zzbG("Failed to send hits to AnalyticsService");
            return false;
        }
    }

    /* Access modifiers changed, original: protected */
    public void zzkO() {
    }

    public boolean zzmm() {
        zzkN();
        zzma();
        zzac zzac = this.zzWE;
        if (zzac == null) {
            return false;
        }
        try {
            zzac.zzlI();
            zzmn();
            return true;
        } catch (RemoteException e) {
            zzbG("Failed to clear hits from AnalyticsService");
            return false;
        }
    }
}
