package com.google.android.gms.common.api;

import com.google.android.gms.common.api.PendingResult.zza;
import com.google.android.gms.internal.zznv;

public final class Batch extends zznv<BatchResult> {
    private int zzakL;
    private boolean zzakM;
    private boolean zzakN;
    private final PendingResult<?>[] zzakO;
    private final Object zzpp;

    /* renamed from: com.google.android.gms.common.api.Batch$1 */
    class C21041 implements zza {
        final /* synthetic */ Batch zzakP;

        /* JADX WARNING: Missing block: B:29:?, code skipped:
            return;
     */
        public void zzt(com.google.android.gms.common.api.Status r6) {
            /*
            r5 = this;
            r0 = r5.zzakP;
            r1 = r0.zzpp;
            monitor-enter(r1);
            r0 = r5.zzakP;	 Catch:{ all -> 0x0039 }
            r0 = r0.isCanceled();	 Catch:{ all -> 0x0039 }
            if (r0 == 0) goto L_0x0011;
        L_0x000f:
            monitor-exit(r1);	 Catch:{ all -> 0x0039 }
        L_0x0010:
            return;
        L_0x0011:
            r0 = r6.isCanceled();	 Catch:{ all -> 0x0039 }
            if (r0 == 0) goto L_0x003c;
        L_0x0017:
            r0 = r5.zzakP;	 Catch:{ all -> 0x0039 }
            r2 = 1;
            r0.zzakN = r2;	 Catch:{ all -> 0x0039 }
        L_0x001d:
            r0 = r5.zzakP;	 Catch:{ all -> 0x0039 }
            r0.zzakL = r0.zzakL - 1;	 Catch:{ all -> 0x0039 }
            r0 = r5.zzakP;	 Catch:{ all -> 0x0039 }
            r0 = r0.zzakL;	 Catch:{ all -> 0x0039 }
            if (r0 != 0) goto L_0x0037;
        L_0x002a:
            r0 = r5.zzakP;	 Catch:{ all -> 0x0039 }
            r0 = r0.zzakN;	 Catch:{ all -> 0x0039 }
            if (r0 == 0) goto L_0x0049;
        L_0x0032:
            r0 = r5.zzakP;	 Catch:{ all -> 0x0039 }
            super.cancel();	 Catch:{ all -> 0x0039 }
        L_0x0037:
            monitor-exit(r1);	 Catch:{ all -> 0x0039 }
            goto L_0x0010;
        L_0x0039:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0039 }
            throw r0;
        L_0x003c:
            r0 = r6.isSuccess();	 Catch:{ all -> 0x0039 }
            if (r0 != 0) goto L_0x001d;
        L_0x0042:
            r0 = r5.zzakP;	 Catch:{ all -> 0x0039 }
            r2 = 1;
            r0.zzakM = r2;	 Catch:{ all -> 0x0039 }
            goto L_0x001d;
        L_0x0049:
            r0 = r5.zzakP;	 Catch:{ all -> 0x0039 }
            r0 = r0.zzakM;	 Catch:{ all -> 0x0039 }
            if (r0 == 0) goto L_0x0069;
        L_0x0051:
            r0 = new com.google.android.gms.common.api.Status;	 Catch:{ all -> 0x0039 }
            r2 = 13;
            r0.<init>(r2);	 Catch:{ all -> 0x0039 }
        L_0x0058:
            r2 = r5.zzakP;	 Catch:{ all -> 0x0039 }
            r3 = new com.google.android.gms.common.api.BatchResult;	 Catch:{ all -> 0x0039 }
            r4 = r5.zzakP;	 Catch:{ all -> 0x0039 }
            r4 = r4.zzakO;	 Catch:{ all -> 0x0039 }
            r3.<init>(r0, r4);	 Catch:{ all -> 0x0039 }
            r2.zzb(r3);	 Catch:{ all -> 0x0039 }
            goto L_0x0037;
        L_0x0069:
            r0 = com.google.android.gms.common.api.Status.zzalw;	 Catch:{ all -> 0x0039 }
            goto L_0x0058;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.Batch$C21041.zzt(com.google.android.gms.common.api.Status):void");
        }
    }

    public static final class Builder {
    }

    public void cancel() {
        super.cancel();
        for (PendingResult cancel : this.zzakO) {
            cancel.cancel();
        }
    }

    /* renamed from: createFailedResult */
    public BatchResult zzc(Status status) {
        return new BatchResult(status, this.zzakO);
    }
}
