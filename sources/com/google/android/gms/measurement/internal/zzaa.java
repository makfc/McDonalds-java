package com.google.android.gms.measurement.internal;

abstract class zzaa extends zzz {
    private boolean zzWh;

    zzaa(zzx zzx) {
        super(zzx);
        this.zzbbl.zzb(this);
    }

    public final void initialize() {
        if (this.zzWh) {
            throw new IllegalStateException("Can't initialize twice");
        }
        zzkO();
        this.zzbbl.zzGp();
        this.zzWh = true;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isInitialized() {
        return this.zzWh;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean zzGu() {
        return false;
    }

    public abstract void zzkO();

    /* Access modifiers changed, original: protected */
    public void zzma() {
        if (!isInitialized()) {
            throw new IllegalStateException("Not initialized");
        }
    }
}
