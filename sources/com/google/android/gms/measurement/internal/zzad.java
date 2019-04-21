package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzd.zzb;
import com.google.android.gms.common.internal.zzd.zzc;
import com.google.android.gms.common.util.zze;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class zzad extends zzaa {
    private final zzf zzbfA;
    private final zzah zzbfB;
    private final List<Runnable> zzbfC = new ArrayList();
    private final zzf zzbfD;
    private final zza zzbfx;
    private zzm zzbfy;
    private Boolean zzbfz;

    /* renamed from: com.google.android.gms.measurement.internal.zzad$3 */
    class C26763 implements Runnable {
        C26763() {
        }

        public void run() {
            zzm zzc = zzad.this.zzbfy;
            if (zzc == null) {
                zzad.this.zzFm().zzFE().log("Failed to send measurementEnabled to service");
                return;
            }
            try {
                zzc.zzb(zzad.this.zzFe().zzfs(zzad.this.zzFm().zzFM()));
                zzad.this.zzmn();
            } catch (RemoteException e) {
                zzad.this.zzFm().zzFE().zzj("Failed to send measurementEnabled to AppMeasurementService", e);
            }
        }
    }

    /* renamed from: com.google.android.gms.measurement.internal.zzad$7 */
    class C26807 implements Runnable {
        C26807() {
        }

        public void run() {
            zzm zzc = zzad.this.zzbfy;
            if (zzc == null) {
                zzad.this.zzFm().zzFE().log("Discarding data. Failed to send app launch");
                return;
            }
            try {
                zzc.zza(zzad.this.zzFe().zzfs(zzad.this.zzFm().zzFM()));
                zzad.this.zzmn();
            } catch (RemoteException e) {
                zzad.this.zzFm().zzFE().zzj("Failed to send app launch to AppMeasurementService", e);
            }
        }
    }

    protected class zza implements ServiceConnection, zzb, zzc {
        private volatile boolean zzbfG;
        private volatile zzo zzbfH;

        /* renamed from: com.google.android.gms.measurement.internal.zzad$zza$4 */
        class C26844 implements Runnable {
            C26844() {
            }

            public void run() {
                zzad.this.onServiceDisconnected(new ComponentName(zzad.this.getContext(), "com.google.android.gms.measurement.AppMeasurementService"));
            }
        }

        protected zza() {
        }

        @MainThread
        public void onConnected(@Nullable Bundle bundle) {
            zzaa.zzdc("MeasurementServiceConnection.onConnected");
            synchronized (this) {
                try {
                    final zzm zzm = (zzm) this.zzbfH.zztm();
                    this.zzbfH = null;
                    zzad.this.zzFl().zzg(new Runnable() {
                        public void run() {
                            synchronized (zza.this) {
                                zza.this.zzbfG = false;
                                if (!zzad.this.isConnected()) {
                                    zzad.this.zzFm().zzFK().log("Connected to remote service");
                                    zzad.this.zza(zzm);
                                }
                            }
                        }
                    });
                } catch (DeadObjectException | IllegalStateException e) {
                    this.zzbfH = null;
                    this.zzbfG = false;
                }
            }
        }

        @MainThread
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            zzaa.zzdc("MeasurementServiceConnection.onConnectionFailed");
            zzp zzFY = zzad.this.zzbbl.zzFY();
            if (zzFY != null) {
                zzFY.zzFG().zzj("Service connection failed", connectionResult);
            }
            synchronized (this) {
                this.zzbfG = false;
                this.zzbfH = null;
            }
        }

        @MainThread
        public void onConnectionSuspended(int i) {
            zzaa.zzdc("MeasurementServiceConnection.onConnectionSuspended");
            zzad.this.zzFm().zzFK().log("Service connection suspended");
            zzad.this.zzFl().zzg(new C26844());
        }

        @MainThread
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            zzaa.zzdc("MeasurementServiceConnection.onServiceConnected");
            synchronized (this) {
                if (iBinder == null) {
                    this.zzbfG = false;
                    zzad.this.zzFm().zzFE().log("Service connected with null binder");
                    return;
                }
                zzm zzm = null;
                try {
                    String interfaceDescriptor = iBinder.getInterfaceDescriptor();
                    if ("com.google.android.gms.measurement.internal.IMeasurementService".equals(interfaceDescriptor)) {
                        zzm = com.google.android.gms.measurement.internal.zzm.zza.zzdv(iBinder);
                        zzad.this.zzFm().zzFL().log("Bound to IMeasurementService interface");
                    } else {
                        zzad.this.zzFm().zzFE().zzj("Got binder with a wrong descriptor", interfaceDescriptor);
                    }
                } catch (RemoteException e) {
                    zzad.this.zzFm().zzFE().log("Service connect failed to get IMeasurementService");
                }
                if (zzm == null) {
                    this.zzbfG = false;
                    try {
                        com.google.android.gms.common.stats.zzb.zzuH().zza(zzad.this.getContext(), zzad.this.zzbfx);
                    } catch (IllegalArgumentException e2) {
                    }
                } else {
                    zzad.this.zzFl().zzg(new Runnable() {
                        public void run() {
                            synchronized (zza.this) {
                                zza.this.zzbfG = false;
                                if (!zzad.this.isConnected()) {
                                    zzad.this.zzFm().zzFL().log("Connected to service");
                                    zzad.this.zza(zzm);
                                }
                            }
                        }
                    });
                }
            }
        }

        @MainThread
        public void onServiceDisconnected(final ComponentName componentName) {
            zzaa.zzdc("MeasurementServiceConnection.onServiceDisconnected");
            zzad.this.zzFm().zzFK().log("Service disconnected");
            zzad.this.zzFl().zzg(new Runnable() {
                public void run() {
                    zzad.this.onServiceDisconnected(componentName);
                }
            });
        }

        @WorkerThread
        public void zzGF() {
            zzad.this.zzkN();
            Context context = zzad.this.getContext();
            synchronized (this) {
                if (this.zzbfG) {
                    zzad.this.zzFm().zzFL().log("Connection attempt already in progress");
                } else if (this.zzbfH != null) {
                    zzad.this.zzFm().zzFL().log("Already awaiting connection attempt");
                } else {
                    this.zzbfH = new zzo(context, Looper.getMainLooper(), this, this);
                    zzad.this.zzFm().zzFL().log("Connecting to remote service");
                    this.zzbfG = true;
                    this.zzbfH.zztj();
                }
            }
        }

        @WorkerThread
        public void zzx(Intent intent) {
            zzad.this.zzkN();
            Context context = zzad.this.getContext();
            com.google.android.gms.common.stats.zzb zzuH = com.google.android.gms.common.stats.zzb.zzuH();
            synchronized (this) {
                if (this.zzbfG) {
                    zzad.this.zzFm().zzFL().log("Connection attempt already in progress");
                    return;
                }
                this.zzbfG = true;
                zzuH.zza(context, intent, zzad.this.zzbfx, 129);
            }
        }
    }

    protected zzad(zzx zzx) {
        super(zzx);
        this.zzbfB = new zzah(zzx.zzlQ());
        this.zzbfx = new zza();
        this.zzbfA = new zzf(zzx) {
            public void run() {
                zzad.this.zzmo();
            }
        };
        this.zzbfD = new zzf(zzx) {
            public void run() {
                zzad.this.zzFm().zzFG().log("Tasks have been queued for a long time");
            }
        };
    }

    @WorkerThread
    private void onServiceDisconnected(ComponentName componentName) {
        zzkN();
        if (this.zzbfy != null) {
            this.zzbfy = null;
            zzFm().zzFL().zzj("Disconnected from device MeasurementService", componentName);
            zzGD();
        }
    }

    private boolean zzGB() {
        List queryIntentServices = getContext().getPackageManager().queryIntentServices(new Intent().setClassName(getContext(), "com.google.android.gms.measurement.AppMeasurementService"), 65536);
        return queryIntentServices != null && queryIntentServices.size() > 0;
    }

    @WorkerThread
    private void zzGD() {
        zzkN();
        zzmC();
    }

    @WorkerThread
    private void zzGE() {
        zzkN();
        zzFm().zzFL().zzj("Processing queued up service tasks", Integer.valueOf(this.zzbfC.size()));
        for (Runnable zzg : this.zzbfC) {
            zzFl().zzg(zzg);
        }
        this.zzbfC.clear();
        this.zzbfD.cancel();
    }

    @WorkerThread
    private void zza(zzm zzm) {
        zzkN();
        zzaa.zzz(zzm);
        this.zzbfy = zzm;
        zzmn();
        zzGE();
    }

    @WorkerThread
    private void zzi(Runnable runnable) throws IllegalStateException {
        zzkN();
        if (isConnected()) {
            runnable.run();
        } else if (((long) this.zzbfC.size()) >= zzFo().zzEQ()) {
            zzFm().zzFE().log("Discarding data. Max runnable queue size reached");
        } else {
            this.zzbfC.add(runnable);
            if (!this.zzbbl.zzGh()) {
                this.zzbfD.zzv(60000);
            }
            zzmC();
        }
    }

    @WorkerThread
    private void zzmn() {
        zzkN();
        this.zzbfB.start();
        if (!this.zzbbl.zzGh()) {
            this.zzbfA.zzv(zzFo().zznr());
        }
    }

    @WorkerThread
    private void zzmo() {
        zzkN();
        if (isConnected()) {
            zzFm().zzFL().log("Inactivity, disconnecting from AppMeasurementService");
            disconnect();
        }
    }

    @WorkerThread
    public void disconnect() {
        zzkN();
        zzma();
        try {
            com.google.android.gms.common.stats.zzb.zzuH().zza(getContext(), this.zzbfx);
        } catch (IllegalArgumentException | IllegalStateException e) {
        }
        this.zzbfy = null;
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @WorkerThread
    public boolean isConnected() {
        zzkN();
        zzma();
        return this.zzbfy != null;
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

    /* Access modifiers changed, original: protected */
    @WorkerThread
    public void zzGA() {
        zzkN();
        zzma();
        zzi(new C26763());
    }

    /* Access modifiers changed, original: protected */
    @WorkerThread
    public boolean zzGC() {
        zzkN();
        zzma();
        if (zzFo().zzmW()) {
            return true;
        }
        zzFm().zzFL().log("Checking service availability");
        switch (com.google.android.gms.common.zzc.zzqV().isGooglePlayServicesAvailable(getContext())) {
            case 0:
                zzFm().zzFL().log("Service available");
                return true;
            case 1:
                zzFm().zzFL().log("Service missing");
                return false;
            case 2:
                zzFm().zzFK().log("Service container out of date");
                return true;
            case 3:
                zzFm().zzFG().log("Service disabled");
                return false;
            case 9:
                zzFm().zzFG().log("Service invalid");
                return false;
            case 18:
                zzFm().zzFG().log("Service updating");
                return true;
            default:
                return false;
        }
    }

    /* Access modifiers changed, original: protected */
    @WorkerThread
    public void zzGx() {
        zzkN();
        zzma();
        zzi(new C26807());
    }

    /* Access modifiers changed, original: protected */
    @WorkerThread
    public void zza(final UserAttributeParcel userAttributeParcel) {
        zzkN();
        zzma();
        zzi(new Runnable() {
            public void run() {
                zzm zzc = zzad.this.zzbfy;
                if (zzc == null) {
                    zzad.this.zzFm().zzFE().log("Discarding data. Failed to set user attribute");
                    return;
                }
                try {
                    zzc.zza(userAttributeParcel, zzad.this.zzFe().zzfs(zzad.this.zzFm().zzFM()));
                    zzad.this.zzmn();
                } catch (RemoteException e) {
                    zzad.this.zzFm().zzFE().zzj("Failed to send attribute to AppMeasurementService", e);
                }
            }
        });
    }

    /* Access modifiers changed, original: protected */
    @WorkerThread
    public void zza(final AtomicReference<List<UserAttributeParcel>> atomicReference, final boolean z) {
        zzkN();
        zzma();
        zzi(new Runnable() {
            /* JADX WARNING: Missing block: B:21:?, code skipped:
            return;
     */
            public void run() {
                /*
                r5 = this;
                r1 = r2;
                monitor-enter(r1);
                r0 = com.google.android.gms.measurement.internal.zzad.this;	 Catch:{ RemoteException -> 0x0046 }
                r0 = r0.zzbfy;	 Catch:{ RemoteException -> 0x0046 }
                if (r0 != 0) goto L_0x0021;
            L_0x000b:
                r0 = com.google.android.gms.measurement.internal.zzad.this;	 Catch:{ RemoteException -> 0x0046 }
                r0 = r0.zzFm();	 Catch:{ RemoteException -> 0x0046 }
                r0 = r0.zzFE();	 Catch:{ RemoteException -> 0x0046 }
                r2 = "Failed to get user properties";
                r0.log(r2);	 Catch:{ RemoteException -> 0x0046 }
                r0 = r2;	 Catch:{ all -> 0x0043 }
                r0.notify();	 Catch:{ all -> 0x0043 }
                monitor-exit(r1);	 Catch:{ all -> 0x0043 }
            L_0x0020:
                return;
            L_0x0021:
                r2 = r2;	 Catch:{ RemoteException -> 0x0046 }
                r3 = com.google.android.gms.measurement.internal.zzad.this;	 Catch:{ RemoteException -> 0x0046 }
                r3 = r3.zzFe();	 Catch:{ RemoteException -> 0x0046 }
                r4 = 0;
                r3 = r3.zzfs(r4);	 Catch:{ RemoteException -> 0x0046 }
                r4 = r3;	 Catch:{ RemoteException -> 0x0046 }
                r0 = r0.zza(r3, r4);	 Catch:{ RemoteException -> 0x0046 }
                r2.set(r0);	 Catch:{ RemoteException -> 0x0046 }
                r0 = com.google.android.gms.measurement.internal.zzad.this;	 Catch:{ RemoteException -> 0x0046 }
                r0.zzmn();	 Catch:{ RemoteException -> 0x0046 }
                r0 = r2;	 Catch:{ all -> 0x0043 }
                r0.notify();	 Catch:{ all -> 0x0043 }
            L_0x0041:
                monitor-exit(r1);	 Catch:{ all -> 0x0043 }
                goto L_0x0020;
            L_0x0043:
                r0 = move-exception;
                monitor-exit(r1);	 Catch:{ all -> 0x0043 }
                throw r0;
            L_0x0046:
                r0 = move-exception;
                r2 = com.google.android.gms.measurement.internal.zzad.this;	 Catch:{ all -> 0x005c }
                r2 = r2.zzFm();	 Catch:{ all -> 0x005c }
                r2 = r2.zzFE();	 Catch:{ all -> 0x005c }
                r3 = "Failed to get user properties";
                r2.zzj(r3, r0);	 Catch:{ all -> 0x005c }
                r0 = r2;	 Catch:{ all -> 0x0043 }
                r0.notify();	 Catch:{ all -> 0x0043 }
                goto L_0x0041;
            L_0x005c:
                r0 = move-exception;
                r2 = r2;	 Catch:{ all -> 0x0043 }
                r2.notify();	 Catch:{ all -> 0x0043 }
                throw r0;	 Catch:{ all -> 0x0043 }
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad$C26796.run():void");
            }
        });
    }

    /* Access modifiers changed, original: protected */
    @WorkerThread
    public void zzc(final EventParcel eventParcel, final String str) {
        zzaa.zzz(eventParcel);
        zzkN();
        zzma();
        zzi(new Runnable() {
            public void run() {
                zzm zzc = zzad.this.zzbfy;
                if (zzc == null) {
                    zzad.this.zzFm().zzFE().log("Discarding data. Failed to send event to service");
                    return;
                }
                try {
                    if (TextUtils.isEmpty(str)) {
                        zzc.zza(eventParcel, zzad.this.zzFe().zzfs(zzad.this.zzFm().zzFM()));
                    } else {
                        zzc.zza(eventParcel, str, zzad.this.zzFm().zzFM());
                    }
                    zzad.this.zzmn();
                } catch (RemoteException e) {
                    zzad.this.zzFm().zzFE().zzj("Failed to send event to AppMeasurementService", e);
                }
            }
        });
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

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public void zzmC() {
        zzkN();
        zzma();
        if (!isConnected()) {
            if (this.zzbfz == null) {
                this.zzbfz = zzFn().zzFS();
                if (this.zzbfz == null) {
                    zzFm().zzFL().log("State of service unknown");
                    this.zzbfz = Boolean.valueOf(zzGC());
                    zzFn().zzaw(this.zzbfz.booleanValue());
                }
            }
            if (this.zzbfz.booleanValue()) {
                zzFm().zzFL().log("Using measurement service");
                this.zzbfx.zzGF();
            } else if (!this.zzbbl.zzGh() && zzGB()) {
                zzFm().zzFL().log("Using local app measurement service");
                Intent intent = new Intent("com.google.android.gms.measurement.START");
                intent.setComponent(new ComponentName(getContext(), "com.google.android.gms.measurement.AppMeasurementService"));
                this.zzbfx.zzx(intent);
            } else if (zzFo().zzmX()) {
                zzFm().zzFL().log("Using direct local measurement implementation");
                zza(new zzy(this.zzbbl, true));
            } else {
                zzFm().zzFE().log("Not in main process. Unable to use local measurement implementation. Please register the AppMeasurementService service in the app manifest");
            }
        }
    }
}
