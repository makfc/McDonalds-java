package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.ArrayDeque;
import java.util.Queue;

class zzg<TResult> {
    private Queue<zzf<TResult>> zzbwv;
    private boolean zzbww;
    private final Object zzpp = new Object();

    zzg() {
    }

    /* JADX WARNING: Missing block: B:10:0x0011, code skipped:
            r1 = r2.zzpp;
     */
    /* JADX WARNING: Missing block: B:11:0x0013, code skipped:
            monitor-enter(r1);
     */
    /* JADX WARNING: Missing block: B:13:?, code skipped:
            r0 = (com.google.android.gms.tasks.zzf) r2.zzbwv.poll();
     */
    /* JADX WARNING: Missing block: B:14:0x001c, code skipped:
            if (r0 != null) goto L_0x0029;
     */
    /* JADX WARNING: Missing block: B:15:0x001e, code skipped:
            r2.zzbww = false;
     */
    /* JADX WARNING: Missing block: B:16:0x0021, code skipped:
            monitor-exit(r1);
     */
    /* JADX WARNING: Missing block: B:26:?, code skipped:
            monitor-exit(r1);
     */
    /* JADX WARNING: Missing block: B:27:0x002a, code skipped:
            r0.onComplete(r3);
     */
    /* JADX WARNING: Missing block: B:32:?, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:33:?, code skipped:
            return;
     */
    public void zza(@android.support.annotation.NonNull com.google.android.gms.tasks.Task<TResult> r3) {
        /*
        r2 = this;
        r1 = r2.zzpp;
        monitor-enter(r1);
        r0 = r2.zzbwv;	 Catch:{ all -> 0x0026 }
        if (r0 == 0) goto L_0x000b;
    L_0x0007:
        r0 = r2.zzbww;	 Catch:{ all -> 0x0026 }
        if (r0 == 0) goto L_0x000d;
    L_0x000b:
        monitor-exit(r1);	 Catch:{ all -> 0x0026 }
    L_0x000c:
        return;
    L_0x000d:
        r0 = 1;
        r2.zzbww = r0;	 Catch:{ all -> 0x0026 }
        monitor-exit(r1);	 Catch:{ all -> 0x0026 }
    L_0x0011:
        r1 = r2.zzpp;
        monitor-enter(r1);
        r0 = r2.zzbwv;	 Catch:{ all -> 0x0023 }
        r0 = r0.poll();	 Catch:{ all -> 0x0023 }
        r0 = (com.google.android.gms.tasks.zzf) r0;	 Catch:{ all -> 0x0023 }
        if (r0 != 0) goto L_0x0029;
    L_0x001e:
        r0 = 0;
        r2.zzbww = r0;	 Catch:{ all -> 0x0023 }
        monitor-exit(r1);	 Catch:{ all -> 0x0023 }
        goto L_0x000c;
    L_0x0023:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0023 }
        throw r0;
    L_0x0026:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0026 }
        throw r0;
    L_0x0029:
        monitor-exit(r1);	 Catch:{ all -> 0x0023 }
        r0.onComplete(r3);
        goto L_0x0011;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tasks.zzg.zza(com.google.android.gms.tasks.Task):void");
    }

    public void zza(@NonNull zzf<TResult> zzf) {
        synchronized (this.zzpp) {
            if (this.zzbwv == null) {
                this.zzbwv = new ArrayDeque();
            }
            this.zzbwv.add(zzf);
        }
    }
}
