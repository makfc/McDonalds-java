package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;

class zzdb extends zzda {
    private static final Object zzbqZ = new Object();
    private static zzdb zzbrl;
    private boolean connected = true;
    private Context zzbra;
    private zzav zzbrb;
    private volatile zzat zzbrc;
    private int zzbrd = 1800000;
    private boolean zzbre = true;
    private boolean zzbrf = false;
    private boolean zzbrg = true;
    private zzaw zzbrh = new C27331();
    private zza zzbri;
    private zzbs zzbrj;
    private boolean zzbrk = false;

    /* renamed from: com.google.android.gms.tagmanager.zzdb$1 */
    class C27331 implements zzaw {
        C27331() {
        }

        public void zzaE(boolean z) {
            zzdb.this.zzd(z, zzdb.this.connected);
        }
    }

    /* renamed from: com.google.android.gms.tagmanager.zzdb$2 */
    class C27342 implements Runnable {
        C27342() {
        }

        public void run() {
            zzdb.this.zzbrb.dispatch();
        }
    }

    public interface zza {
        void cancel();

        void zzKG();

        void zzv(long j);
    }

    private class zzb implements zza {
        private Handler handler;

        /* renamed from: com.google.android.gms.tagmanager.zzdb$zzb$1 */
        class C27351 implements Callback {
            C27351() {
            }

            public boolean handleMessage(Message message) {
                if (1 == message.what && zzdb.zzbqZ.equals(message.obj)) {
                    zzdb.this.dispatch();
                    if (!zzdb.this.isPowerSaveMode()) {
                        zzb.this.zzv((long) zzdb.this.zzbrd);
                    }
                }
                return true;
            }
        }

        private zzb() {
            this.handler = new Handler(zzdb.this.zzbra.getMainLooper(), new C27351());
        }

        /* synthetic */ zzb(zzdb zzdb, C27331 c27331) {
            this();
        }

        private Message obtainMessage() {
            return this.handler.obtainMessage(1, zzdb.zzbqZ);
        }

        public void cancel() {
            this.handler.removeMessages(1, zzdb.zzbqZ);
        }

        public void zzKG() {
            this.handler.removeMessages(1, zzdb.zzbqZ);
            this.handler.sendMessage(obtainMessage());
        }

        public void zzv(long j) {
            this.handler.removeMessages(1, zzdb.zzbqZ);
            this.handler.sendMessageDelayed(obtainMessage(), j);
        }
    }

    private zzdb() {
    }

    private boolean isPowerSaveMode() {
        return this.zzbrk || !this.connected || this.zzbrd <= 0;
    }

    public static zzdb zzKB() {
        if (zzbrl == null) {
            zzbrl = new zzdb();
        }
        return zzbrl;
    }

    private void zzKC() {
        this.zzbrj = new zzbs(this);
        this.zzbrj.zzbp(this.zzbra);
    }

    private void zzKD() {
        this.zzbri = new zzb(this, null);
        if (this.zzbrd > 0) {
            this.zzbri.zzv((long) this.zzbrd);
        }
    }

    private void zzmG() {
        if (isPowerSaveMode()) {
            this.zzbri.cancel();
            zzbn.m7502v("PowerSaveMode initiated.");
            return;
        }
        this.zzbri.zzv((long) this.zzbrd);
        zzbn.m7502v("PowerSaveMode terminated.");
    }

    public synchronized void dispatch() {
        if (this.zzbrf) {
            this.zzbrc.zzj(new C27342());
        } else {
            zzbn.m7502v("Dispatch call queued. Dispatch will run once initialization is complete.");
            this.zzbre = true;
        }
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized zzav zzKE() {
        if (this.zzbrb == null) {
            if (this.zzbra == null) {
                throw new IllegalStateException("Cant get a store unless we have a context");
            }
            this.zzbrb = new zzcf(this.zzbrh, this.zzbra);
        }
        if (this.zzbri == null) {
            zzKD();
        }
        this.zzbrf = true;
        if (this.zzbre) {
            dispatch();
            this.zzbre = false;
        }
        if (this.zzbrj == null && this.zzbrg) {
            zzKC();
        }
        return this.zzbrb;
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized void zza(Context context, zzat zzat) {
        if (this.zzbra == null) {
            this.zzbra = context.getApplicationContext();
            if (this.zzbrc == null) {
                this.zzbrc = zzat;
            }
        }
    }

    public synchronized void zzaF(boolean z) {
        zzd(this.zzbrk, z);
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized void zzd(boolean z, boolean z2) {
        boolean isPowerSaveMode = isPowerSaveMode();
        this.zzbrk = z;
        this.connected = z2;
        if (isPowerSaveMode() != isPowerSaveMode) {
            zzmG();
        }
    }

    public synchronized void zzlM() {
        if (!isPowerSaveMode()) {
            this.zzbri.zzKG();
        }
    }
}
