package com.google.android.gms.internal;

import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.clearcut.LogEventParcelable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.zzh;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class zznl implements com.google.android.gms.clearcut.zzc {
    private static final Object zzajR = new Object();
    private static final zze zzajT = new zze();
    private static final long zzajU = TimeUnit.MILLISECONDS.convert(2, TimeUnit.MINUTES);
    private GoogleApiClient zzaeN;
    private final zza zzajV;
    private final Object zzajW;
    private long zzajX;
    private final long zzajY;
    private ScheduledFuture<?> zzajZ;
    private final Runnable zzaka;
    private final com.google.android.gms.common.util.zze zzsd;

    /* renamed from: com.google.android.gms.internal.zznl$1 */
    class C22321 implements Runnable {
        C22321() {
        }

        public void run() {
            synchronized (zznl.this.zzajW) {
                if (0 <= zznl.this.zzsd.elapsedRealtime() && zznl.this.zzaeN != null) {
                    Log.i("ClearcutLoggerApiImpl", "disconnect managed GoogleApiClient");
                    zznl.this.zzaeN.disconnect();
                    zznl.this.zzaeN = null;
                }
            }
        }
    }

    /* renamed from: com.google.android.gms.internal.zznl$2 */
    class C22342 implements ThreadFactory {
        public Thread newThread(final Runnable runnable) {
            return new Thread(new Runnable() {
                public void run() {
                    Process.setThreadPriority(10);
                    runnable.run();
                }
            }, "ClearcutLoggerApiImpl");
        }
    }

    /* renamed from: com.google.android.gms.internal.zznl$3 */
    class C22353 implements Runnable {
        final /* synthetic */ GoogleApiClient zzagn;
        final /* synthetic */ zzc zzake;

        public void run() {
            this.zzagn.zzc(this.zzake);
        }
    }

    /* renamed from: com.google.android.gms.internal.zznl$4 */
    class C22364 implements com.google.android.gms.common.api.PendingResult.zza {
        public void zzt(Status status) {
            zznl.zzajT.zzqT();
        }
    }

    public interface zza {
    }

    public static class zzb implements zza {
    }

    static abstract class zzc<R extends Result> extends com.google.android.gms.internal.zznt.zza<R, zznm> {
    }

    static final class zzd extends zzc<Status> {
        private final LogEventParcelable zzakf;

        /* renamed from: com.google.android.gms.internal.zznl$zzd$1 */
        class C22371 extends com.google.android.gms.internal.zznn.zza {
            C22371() {
            }

            public void zzu(Status status) {
                zzd.this.zzb(status);
            }
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof zzd)) {
                return false;
            }
            return this.zzakf.equals(((zzd) obj).zzakf);
        }

        public String toString() {
            String valueOf = String.valueOf(this.zzakf);
            return new StringBuilder(String.valueOf(valueOf).length() + 12).append("MethodImpl(").append(valueOf).append(")").toString();
        }

        /* Access modifiers changed, original: protected */
        public void zza(zznm zznm) throws RemoteException {
            C22371 c22371 = new C22371();
            try {
                zznl.zza(this.zzakf);
                zznm.zza(c22371, this.zzakf);
            } catch (RuntimeException e) {
                Log.e("ClearcutLoggerApiImpl", "derived ClearcutLogger.MessageNanoProducer", e);
                zzx(new Status(10, "MessageProducer"));
            }
        }

        /* Access modifiers changed, original: protected */
        /* renamed from: zzb */
        public Status zzc(Status status) {
            return status;
        }
    }

    private static final class zze {
        private int mSize;

        private zze() {
            this.mSize = 0;
        }

        /* synthetic */ zze(C22321 c22321) {
            this();
        }

        public synchronized void zzqT() {
            if (this.mSize == 0) {
                throw new RuntimeException("too many decrements");
            }
            this.mSize--;
            if (this.mSize == 0) {
                notifyAll();
            }
        }
    }

    public zznl() {
        this(new zzh(), zzajU, new zzb());
    }

    public zznl(com.google.android.gms.common.util.zze zze, long j, zza zza) {
        this.zzajW = new Object();
        this.zzajX = 0;
        this.zzajZ = null;
        this.zzaeN = null;
        this.zzaka = new C22321();
        this.zzsd = zze;
        this.zzajY = j;
        this.zzajV = zza;
    }

    private static void zza(LogEventParcelable logEventParcelable) {
        if (logEventParcelable.zzajP != null && logEventParcelable.zzajO.zzcaM.length == 0) {
            logEventParcelable.zzajO.zzcaM = logEventParcelable.zzajP.zzqP();
        }
        if (logEventParcelable.zzajQ != null && logEventParcelable.zzajO.zzcaT.length == 0) {
            logEventParcelable.zzajO.zzcaT = logEventParcelable.zzajQ.zzqP();
        }
        logEventParcelable.zzajM = zzamj.toByteArray(logEventParcelable.zzajO);
    }
}
