package com.google.android.gms.tagmanager;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.LinkedBlockingQueue;

class zzau extends Thread implements zzat {
    private static zzau zzbpm;
    private volatile boolean mClosed = false;
    private final Context mContext;
    private volatile boolean zzHx = false;
    private final LinkedBlockingQueue<Runnable> zzbpl = new LinkedBlockingQueue();
    private volatile zzav zzbpn;

    private zzau(Context context) {
        super("GAThread");
        if (context != null) {
            this.mContext = context.getApplicationContext();
        } else {
            this.mContext = context;
        }
        start();
    }

    static zzau zzbo(Context context) {
        if (zzbpm == null) {
            zzbpm = new zzau(context);
        }
        return zzbpm;
    }

    private String zze(Throwable th) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        th.printStackTrace(printStream);
        printStream.flush();
        return new String(byteArrayOutputStream.toByteArray());
    }

    public void run() {
        while (!this.mClosed) {
            try {
                Runnable runnable = (Runnable) this.zzbpl.take();
                if (!this.zzHx) {
                    runnable.run();
                }
            } catch (InterruptedException e) {
                zzbn.zzaV(e.toString());
            } catch (Throwable th) {
                String str = "Error on Google TagManager Thread: ";
                String valueOf = String.valueOf(zze(th));
                zzbn.m7501e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                zzbn.m7501e("Google TagManager is shutting down.");
                this.zzHx = true;
            }
        }
    }

    public void zzgA(String str) {
        zzk(str, System.currentTimeMillis());
    }

    public void zzj(Runnable runnable) {
        this.zzbpl.add(runnable);
    }

    /* Access modifiers changed, original: 0000 */
    public void zzk(String str, long j) {
        final long j2 = j;
        final String str2 = str;
        zzj(new Runnable() {
            public void run() {
                if (zzau.this.zzbpn == null) {
                    zzdb zzKB = zzdb.zzKB();
                    zzKB.zza(zzau.this.mContext, this);
                    zzau.this.zzbpn = zzKB.zzKE();
                }
                zzau.this.zzbpn.zzg(j2, str2);
            }
        });
    }
}
