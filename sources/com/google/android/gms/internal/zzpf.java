package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.RemoteException;
import android.support.p000v4.util.ArrayMap;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zzd;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public class zzpf {
    private static final com.google.android.gms.internal.zznt.zza<?, ?>[] zzaoJ = new com.google.android.gms.internal.zznt.zza[0];
    private final Map<zzc<?>, zze> zzann;
    final Set<com.google.android.gms.internal.zznt.zza<?, ?>> zzaoK;
    private final zzb zzaoL;

    interface zzb {
        void zzh(com.google.android.gms.internal.zznt.zza<?, ?> zza);
    }

    /* renamed from: com.google.android.gms.internal.zzpf$1 */
    class C22541 implements zzb {
        C22541() {
        }

        public void zzh(com.google.android.gms.internal.zznt.zza<?, ?> zza) {
            zzpf.this.zzaoK.remove(zza);
            if (zza.zzrv() != null && null != null) {
                null.remove(zza.zzrv().intValue());
            }
        }
    }

    private static class zza implements DeathRecipient, zzb {
        private final WeakReference<com.google.android.gms.internal.zznt.zza<?, ?>> zzaoN;
        private final WeakReference<zzd> zzaoO;
        private final WeakReference<IBinder> zzaoP;

        private zza(com.google.android.gms.internal.zznt.zza<?, ?> zza, zzd zzd, IBinder iBinder) {
            this.zzaoO = new WeakReference(zzd);
            this.zzaoN = new WeakReference(zza);
            this.zzaoP = new WeakReference(iBinder);
        }

        /* synthetic */ zza(com.google.android.gms.internal.zznt.zza zza, zzd zzd, IBinder iBinder, C22541 c22541) {
            this(zza, zzd, iBinder);
        }

        private void zzst() {
            com.google.android.gms.internal.zznt.zza zza = (com.google.android.gms.internal.zznt.zza) this.zzaoN.get();
            zzd zzd = (zzd) this.zzaoO.get();
            if (!(zzd == null || zza == null)) {
                zzd.remove(zza.zzrv().intValue());
            }
            IBinder iBinder = (IBinder) this.zzaoP.get();
            if (this.zzaoP != null) {
                iBinder.unlinkToDeath(this, 0);
            }
        }

        public void binderDied() {
            zzst();
        }

        public void zzh(com.google.android.gms.internal.zznt.zza<?, ?> zza) {
            zzst();
        }
    }

    public zzpf(zzc<?> zzc, zze zze) {
        this.zzaoK = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap()));
        this.zzaoL = new C22541();
        this.zzann = new ArrayMap();
        this.zzann.put(zzc, zze);
    }

    public zzpf(Map<zzc<?>, zze> map) {
        this.zzaoK = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap()));
        this.zzaoL = new C22541();
        this.zzann = map;
    }

    private static void zza(com.google.android.gms.internal.zznt.zza<?, ?> zza, zzd zzd, IBinder iBinder) {
        if (zza.isReady()) {
            zza.zza(new zza(zza, zzd, iBinder, null));
        } else if (iBinder == null || !iBinder.isBinderAlive()) {
            zza.zza(null);
            zza.cancel();
            zzd.remove(zza.zzrv().intValue());
        } else {
            zzb zza2 = new zza(zza, zzd, iBinder, null);
            zza.zza(zza2);
            try {
                iBinder.linkToDeath(zza2, 0);
            } catch (RemoteException e) {
                zza.cancel();
                zzd.remove(zza.zzrv().intValue());
            }
        }
    }

    public void dump(PrintWriter printWriter) {
        printWriter.append(" mUnconsumedApiCalls.size()=").println(this.zzaoK.size());
    }

    public void release() {
        for (com.google.android.gms.internal.zznt.zza zza : (com.google.android.gms.internal.zznt.zza[]) this.zzaoK.toArray(zzaoJ)) {
            zza.zza(null);
            if (zza.zzrv() != null) {
                zza.zzrD();
                zza(zza, null, ((zze) this.zzann.get(zza.zzre())).zzrh());
                this.zzaoK.remove(zza);
            } else if (zza.zzrH()) {
                this.zzaoK.remove(zza);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public <A extends com.google.android.gms.common.api.Api.zzb> void zzg(com.google.android.gms.internal.zznt.zza<? extends Result, A> zza) {
        this.zzaoK.add(zza);
        zza.zza(this.zzaoL);
    }

    public void zzsL() {
        for (com.google.android.gms.internal.zznt.zza zzy : (com.google.android.gms.internal.zznt.zza[]) this.zzaoK.toArray(zzaoJ)) {
            zzy.zzy(new Status(8, "The connection to Google Play services was lost"));
        }
    }

    public boolean zzsM() {
        for (com.google.android.gms.internal.zznt.zza isReady : (com.google.android.gms.internal.zznt.zza[]) this.zzaoK.toArray(zzaoJ)) {
            if (!isReady.isReady()) {
                return true;
            }
        }
        return false;
    }
}
