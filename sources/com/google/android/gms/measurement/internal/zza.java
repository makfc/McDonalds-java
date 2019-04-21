package com.google.android.gms.measurement.internal;

import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzaa;

class zza {
    private final String zzPx;
    private String zzVe;
    private String zzauO;
    private long zzbbA;
    private boolean zzbbB;
    private long zzbbC;
    private long zzbbD;
    private long zzbbE;
    private long zzbbF;
    private long zzbbG;
    private boolean zzbbH;
    private long zzbbI;
    private long zzbbJ;
    private final zzx zzbbl;
    private String zzbbr;
    private String zzbbs;
    private String zzbbt;
    private long zzbbu;
    private long zzbbv;
    private long zzbbw;
    private long zzbbx;
    private String zzbby;
    private long zzbbz;

    @WorkerThread
    zza(zzx zzx, String str) {
        zzaa.zzz(zzx);
        zzaa.zzdl(str);
        this.zzbbl = zzx;
        this.zzPx = str;
        this.zzbbl.zzkN();
    }

    @WorkerThread
    public void setAppVersion(String str) {
        this.zzbbl.zzkN();
        this.zzbbH = (!zzal.zzZ(this.zzVe, str) ? 1 : 0) | this.zzbbH;
        this.zzVe = str;
    }

    @WorkerThread
    public void setMeasurementEnabled(boolean z) {
        this.zzbbl.zzkN();
        this.zzbbH = (this.zzbbB != z ? 1 : 0) | this.zzbbH;
        this.zzbbB = z;
    }

    @WorkerThread
    public void zzEa() {
        this.zzbbl.zzkN();
        this.zzbbH = false;
    }

    @WorkerThread
    public String zzEb() {
        this.zzbbl.zzkN();
        return this.zzbbr;
    }

    @WorkerThread
    public String zzEc() {
        this.zzbbl.zzkN();
        return this.zzbbs;
    }

    @WorkerThread
    public String zzEd() {
        this.zzbbl.zzkN();
        return this.zzbbt;
    }

    @WorkerThread
    public long zzEe() {
        this.zzbbl.zzkN();
        return this.zzbbv;
    }

    @WorkerThread
    public long zzEf() {
        this.zzbbl.zzkN();
        return this.zzbbw;
    }

    @WorkerThread
    public long zzEg() {
        this.zzbbl.zzkN();
        return this.zzbbx;
    }

    @WorkerThread
    public String zzEh() {
        this.zzbbl.zzkN();
        return this.zzbby;
    }

    @WorkerThread
    public long zzEi() {
        this.zzbbl.zzkN();
        return this.zzbbz;
    }

    @WorkerThread
    public long zzEj() {
        this.zzbbl.zzkN();
        return this.zzbbA;
    }

    @WorkerThread
    public boolean zzEk() {
        this.zzbbl.zzkN();
        return this.zzbbB;
    }

    @WorkerThread
    public long zzEl() {
        this.zzbbl.zzkN();
        return this.zzbbu;
    }

    @WorkerThread
    public long zzEm() {
        this.zzbbl.zzkN();
        return this.zzbbI;
    }

    @WorkerThread
    public long zzEn() {
        this.zzbbl.zzkN();
        return this.zzbbJ;
    }

    @WorkerThread
    public void zzEo() {
        this.zzbbl.zzkN();
        long j = this.zzbbu + 1;
        if (j > 2147483647L) {
            this.zzbbl.zzFm().zzFG().log("Bundle index overflow");
            j = 0;
        }
        this.zzbbH = true;
        this.zzbbu = j;
    }

    @WorkerThread
    public long zzEp() {
        this.zzbbl.zzkN();
        return this.zzbbC;
    }

    @WorkerThread
    public long zzEq() {
        this.zzbbl.zzkN();
        return this.zzbbD;
    }

    @WorkerThread
    public long zzEr() {
        this.zzbbl.zzkN();
        return this.zzbbE;
    }

    @WorkerThread
    public long zzEs() {
        this.zzbbl.zzkN();
        return this.zzbbF;
    }

    @WorkerThread
    public long zzEt() {
        this.zzbbl.zzkN();
        return this.zzbbG;
    }

    @WorkerThread
    public void zzR(long j) {
        this.zzbbl.zzkN();
        this.zzbbH = (this.zzbbv != j ? 1 : 0) | this.zzbbH;
        this.zzbbv = j;
    }

    @WorkerThread
    public void zzS(long j) {
        this.zzbbl.zzkN();
        this.zzbbH = (this.zzbbw != j ? 1 : 0) | this.zzbbH;
        this.zzbbw = j;
    }

    @WorkerThread
    public void zzT(long j) {
        this.zzbbl.zzkN();
        this.zzbbH = (this.zzbbx != j ? 1 : 0) | this.zzbbH;
        this.zzbbx = j;
    }

    @WorkerThread
    public void zzU(long j) {
        this.zzbbl.zzkN();
        this.zzbbH = (this.zzbbz != j ? 1 : 0) | this.zzbbH;
        this.zzbbz = j;
    }

    @WorkerThread
    public void zzV(long j) {
        this.zzbbl.zzkN();
        this.zzbbH = (this.zzbbA != j ? 1 : 0) | this.zzbbH;
        this.zzbbA = j;
    }

    @WorkerThread
    public void zzW(long j) {
        int i = 1;
        zzaa.zzaj(j >= 0);
        this.zzbbl.zzkN();
        boolean z = this.zzbbH;
        if (this.zzbbu == j) {
            i = 0;
        }
        this.zzbbH = z | i;
        this.zzbbu = j;
    }

    @WorkerThread
    public void zzX(long j) {
        this.zzbbl.zzkN();
        this.zzbbH = (this.zzbbI != j ? 1 : 0) | this.zzbbH;
        this.zzbbI = j;
    }

    @WorkerThread
    public void zzY(long j) {
        this.zzbbl.zzkN();
        this.zzbbH = (this.zzbbJ != j ? 1 : 0) | this.zzbbH;
        this.zzbbJ = j;
    }

    @WorkerThread
    public void zzZ(long j) {
        this.zzbbl.zzkN();
        this.zzbbH = (this.zzbbC != j ? 1 : 0) | this.zzbbH;
        this.zzbbC = j;
    }

    @WorkerThread
    public void zzaa(long j) {
        this.zzbbl.zzkN();
        this.zzbbH = (this.zzbbD != j ? 1 : 0) | this.zzbbH;
        this.zzbbD = j;
    }

    @WorkerThread
    public void zzab(long j) {
        this.zzbbl.zzkN();
        this.zzbbH = (this.zzbbE != j ? 1 : 0) | this.zzbbH;
        this.zzbbE = j;
    }

    @WorkerThread
    public void zzac(long j) {
        this.zzbbl.zzkN();
        this.zzbbH = (this.zzbbF != j ? 1 : 0) | this.zzbbH;
        this.zzbbF = j;
    }

    @WorkerThread
    public void zzad(long j) {
        this.zzbbl.zzkN();
        this.zzbbH = (this.zzbbG != j ? 1 : 0) | this.zzbbH;
        this.zzbbG = j;
    }

    @WorkerThread
    public void zzeV(String str) {
        this.zzbbl.zzkN();
        this.zzbbH = (!zzal.zzZ(this.zzauO, str) ? 1 : 0) | this.zzbbH;
        this.zzauO = str;
    }

    @WorkerThread
    public void zzeW(String str) {
        this.zzbbl.zzkN();
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.zzbbH = (!zzal.zzZ(this.zzbbr, str) ? 1 : 0) | this.zzbbH;
        this.zzbbr = str;
    }

    @WorkerThread
    public void zzeX(String str) {
        this.zzbbl.zzkN();
        this.zzbbH = (!zzal.zzZ(this.zzbbs, str) ? 1 : 0) | this.zzbbH;
        this.zzbbs = str;
    }

    @WorkerThread
    public void zzeY(String str) {
        this.zzbbl.zzkN();
        this.zzbbH = (!zzal.zzZ(this.zzbbt, str) ? 1 : 0) | this.zzbbH;
        this.zzbbt = str;
    }

    @WorkerThread
    public void zzeZ(String str) {
        this.zzbbl.zzkN();
        this.zzbbH = (!zzal.zzZ(this.zzbby, str) ? 1 : 0) | this.zzbbH;
        this.zzbby = str;
    }

    @WorkerThread
    public String zziC() {
        this.zzbbl.zzkN();
        return this.zzPx;
    }

    @WorkerThread
    public String zzkV() {
        this.zzbbl.zzkN();
        return this.zzVe;
    }

    @WorkerThread
    public String zzvx() {
        this.zzbbl.zzkN();
        return this.zzauO;
    }
}
