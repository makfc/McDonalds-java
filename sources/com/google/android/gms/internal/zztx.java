package com.google.android.gms.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.WorkSource;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.common.util.zzw;
import com.google.android.gms.common.util.zzz;

public class zztx {
    private static boolean DEBUG = false;
    private static String TAG = "WakeLock";
    private static String zzbnr = "*gcore*:";
    private final Context mContext;
    private WorkSource zzaUG;
    private final String zzatO;
    private final String zzatQ;
    private final WakeLock zzbns;
    private final int zzbnt;
    private final String zzbnu;
    private boolean zzbnv;
    private int zzbnw;
    private int zzbnx;

    public zztx(Context context, int i, String str) {
        this(context, i, str, null, context == null ? null : context.getPackageName());
    }

    @SuppressLint({"UnwrappedWakeLock"})
    public zztx(Context context, int i, String str, String str2, String str3) {
        this(context, i, str, str2, str3, null);
    }

    @SuppressLint({"UnwrappedWakeLock"})
    public zztx(Context context, int i, String str, String str2, String str3, String str4) {
        this.zzbnv = true;
        zzaa.zzh(str, "Wake lock name can NOT be empty");
        this.zzbnt = i;
        this.zzbnu = str2;
        this.zzatQ = str4;
        this.mContext = context.getApplicationContext();
        if ("com.google.android.gms".equals(context.getPackageName())) {
            this.zzatO = str;
        } else {
            String valueOf = String.valueOf(zzbnr);
            String valueOf2 = String.valueOf(str);
            this.zzatO = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        }
        this.zzbns = ((PowerManager) context.getSystemService("power")).newWakeLock(i, str);
        if (zzz.zzaF(this.mContext)) {
            if (zzw.zzdv(str3)) {
                str3 = context.getPackageName();
            }
            this.zzaUG = zzz.zzn(context, str3);
            zzc(this.zzaUG);
        }
    }

    /* JADX WARNING: Missing block: B:7:0x0015, code skipped:
            if (r0 == false) goto L_0x0017;
     */
    /* JADX WARNING: Missing block: B:11:0x001e, code skipped:
            if (r9.zzbnx == 1) goto L_0x0020;
     */
    private void zzgd(java.lang.String r10) {
        /*
        r9 = this;
        r0 = r9.zzge(r10);
        r5 = r9.zzo(r10, r0);
        monitor-enter(r9);
        r1 = r9.zzbnv;	 Catch:{ all -> 0x0045 }
        if (r1 == 0) goto L_0x0017;
    L_0x000d:
        r1 = r9.zzbnw;	 Catch:{ all -> 0x0045 }
        r1 = r1 + -1;
        r9.zzbnw = r1;	 Catch:{ all -> 0x0045 }
        if (r1 == 0) goto L_0x0020;
    L_0x0015:
        if (r0 != 0) goto L_0x0020;
    L_0x0017:
        r0 = r9.zzbnv;	 Catch:{ all -> 0x0045 }
        if (r0 != 0) goto L_0x0043;
    L_0x001b:
        r0 = r9.zzbnx;	 Catch:{ all -> 0x0045 }
        r1 = 1;
        if (r0 != r1) goto L_0x0043;
    L_0x0020:
        r0 = com.google.android.gms.common.stats.zzh.zzuS();	 Catch:{ all -> 0x0045 }
        r1 = r9.mContext;	 Catch:{ all -> 0x0045 }
        r2 = r9.zzbns;	 Catch:{ all -> 0x0045 }
        r2 = com.google.android.gms.common.stats.zzf.zza(r2, r5);	 Catch:{ all -> 0x0045 }
        r3 = 8;
        r4 = r9.zzatO;	 Catch:{ all -> 0x0045 }
        r6 = r9.zzatQ;	 Catch:{ all -> 0x0045 }
        r7 = r9.zzbnt;	 Catch:{ all -> 0x0045 }
        r8 = r9.zzaUG;	 Catch:{ all -> 0x0045 }
        r8 = com.google.android.gms.common.util.zzz.zzb(r8);	 Catch:{ all -> 0x0045 }
        r0.zza(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ all -> 0x0045 }
        r0 = r9.zzbnx;	 Catch:{ all -> 0x0045 }
        r0 = r0 + -1;
        r9.zzbnx = r0;	 Catch:{ all -> 0x0045 }
    L_0x0043:
        monitor-exit(r9);	 Catch:{ all -> 0x0045 }
        return;
    L_0x0045:
        r0 = move-exception;
        monitor-exit(r9);	 Catch:{ all -> 0x0045 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zztx.zzgd(java.lang.String):void");
    }

    private boolean zzge(String str) {
        return (TextUtils.isEmpty(str) || str.equals(this.zzbnu)) ? false : true;
    }

    /* JADX WARNING: Missing block: B:7:0x0015, code skipped:
            if (r0 == false) goto L_0x0017;
     */
    /* JADX WARNING: Missing block: B:11:0x001d, code skipped:
            if (r12.zzbnx == 0) goto L_0x001f;
     */
    private void zzj(java.lang.String r13, long r14) {
        /*
        r12 = this;
        r0 = r12.zzge(r13);
        r6 = r12.zzo(r13, r0);
        monitor-enter(r12);
        r1 = r12.zzbnv;	 Catch:{ all -> 0x0044 }
        if (r1 == 0) goto L_0x0017;
    L_0x000d:
        r1 = r12.zzbnw;	 Catch:{ all -> 0x0044 }
        r2 = r1 + 1;
        r12.zzbnw = r2;	 Catch:{ all -> 0x0044 }
        if (r1 == 0) goto L_0x001f;
    L_0x0015:
        if (r0 != 0) goto L_0x001f;
    L_0x0017:
        r0 = r12.zzbnv;	 Catch:{ all -> 0x0044 }
        if (r0 != 0) goto L_0x0042;
    L_0x001b:
        r0 = r12.zzbnx;	 Catch:{ all -> 0x0044 }
        if (r0 != 0) goto L_0x0042;
    L_0x001f:
        r1 = com.google.android.gms.common.stats.zzh.zzuS();	 Catch:{ all -> 0x0044 }
        r2 = r12.mContext;	 Catch:{ all -> 0x0044 }
        r0 = r12.zzbns;	 Catch:{ all -> 0x0044 }
        r3 = com.google.android.gms.common.stats.zzf.zza(r0, r6);	 Catch:{ all -> 0x0044 }
        r4 = 7;
        r5 = r12.zzatO;	 Catch:{ all -> 0x0044 }
        r7 = r12.zzatQ;	 Catch:{ all -> 0x0044 }
        r8 = r12.zzbnt;	 Catch:{ all -> 0x0044 }
        r0 = r12.zzaUG;	 Catch:{ all -> 0x0044 }
        r9 = com.google.android.gms.common.util.zzz.zzb(r0);	 Catch:{ all -> 0x0044 }
        r10 = r14;
        r1.zza(r2, r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ all -> 0x0044 }
        r0 = r12.zzbnx;	 Catch:{ all -> 0x0044 }
        r0 = r0 + 1;
        r12.zzbnx = r0;	 Catch:{ all -> 0x0044 }
    L_0x0042:
        monitor-exit(r12);	 Catch:{ all -> 0x0044 }
        return;
    L_0x0044:
        r0 = move-exception;
        monitor-exit(r12);	 Catch:{ all -> 0x0044 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zztx.zzj(java.lang.String, long):void");
    }

    private String zzo(String str, boolean z) {
        return this.zzbnv ? z ? str : this.zzbnu : this.zzbnu;
    }

    public void acquire(long j) {
        if (!zzs.zzva() && this.zzbnv) {
            String str = TAG;
            String str2 = "Do not acquire with timeout on reference counted WakeLocks before ICS. wakelock: ";
            String valueOf = String.valueOf(this.zzatO);
            Log.wtf(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
        zzj(null, j);
        this.zzbns.acquire(j);
    }

    public boolean isHeld() {
        return this.zzbns.isHeld();
    }

    public void release() {
        zzgd(null);
        this.zzbns.release();
    }

    public void setReferenceCounted(boolean z) {
        this.zzbns.setReferenceCounted(z);
        this.zzbnv = z;
    }

    public void zzc(WorkSource workSource) {
        if (workSource != null && zzz.zzaF(this.mContext)) {
            if (this.zzaUG != null) {
                this.zzaUG.add(workSource);
            } else {
                this.zzaUG = workSource;
            }
            this.zzbns.setWorkSource(this.zzaUG);
        }
    }
}
