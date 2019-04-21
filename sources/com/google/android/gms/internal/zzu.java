package com.google.android.gms.internal;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class zzu {
    protected static final Comparator<byte[]> zzav = new C22811();
    private List<byte[]> zzar;
    private List<byte[]> zzas;
    private int zzat;
    private final int zzau;

    /* renamed from: com.google.android.gms.internal.zzu$1 */
    class C22811 implements Comparator<byte[]> {
        C22811() {
        }

        /* renamed from: zza */
        public int compare(byte[] bArr, byte[] bArr2) {
            return bArr.length - bArr2.length;
        }
    }

    private synchronized void zzx() {
        while (this.zzat > this.zzau) {
            byte[] bArr = (byte[]) this.zzar.remove(0);
            this.zzas.remove(bArr);
            this.zzat -= bArr.length;
        }
    }

    public synchronized void zza(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length <= this.zzau) {
                this.zzar.add(bArr);
                int binarySearch = Collections.binarySearch(this.zzas, bArr, zzav);
                if (binarySearch < 0) {
                    binarySearch = (-binarySearch) - 1;
                }
                this.zzas.add(binarySearch, bArr);
                this.zzat += bArr.length;
                zzx();
            }
        }
    }

    /* JADX WARNING: Missing block: B:12:?, code skipped:
            r0 = new byte[r5];
     */
    public synchronized byte[] zzb(int r5) {
        /*
        r4 = this;
        monitor-enter(r4);
        r0 = 0;
        r1 = r0;
    L_0x0003:
        r0 = r4.zzas;	 Catch:{ all -> 0x002f }
        r0 = r0.size();	 Catch:{ all -> 0x002f }
        if (r1 >= r0) goto L_0x002c;
    L_0x000b:
        r0 = r4.zzas;	 Catch:{ all -> 0x002f }
        r0 = r0.get(r1);	 Catch:{ all -> 0x002f }
        r0 = (byte[]) r0;	 Catch:{ all -> 0x002f }
        r2 = r0.length;	 Catch:{ all -> 0x002f }
        if (r2 < r5) goto L_0x0028;
    L_0x0016:
        r2 = r4.zzat;	 Catch:{ all -> 0x002f }
        r3 = r0.length;	 Catch:{ all -> 0x002f }
        r2 = r2 - r3;
        r4.zzat = r2;	 Catch:{ all -> 0x002f }
        r2 = r4.zzas;	 Catch:{ all -> 0x002f }
        r2.remove(r1);	 Catch:{ all -> 0x002f }
        r1 = r4.zzar;	 Catch:{ all -> 0x002f }
        r1.remove(r0);	 Catch:{ all -> 0x002f }
    L_0x0026:
        monitor-exit(r4);
        return r0;
    L_0x0028:
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x0003;
    L_0x002c:
        r0 = new byte[r5];	 Catch:{ all -> 0x002f }
        goto L_0x0026;
    L_0x002f:
        r0 = move-exception;
        monitor-exit(r4);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzu.zzb(int):byte[]");
    }
}
