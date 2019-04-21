package com.google.android.gms.analytics.internal;

public abstract class zzd extends zzc {
    private boolean zzWh;

    protected zzd(zzf zzf) {
        super(zzf);
    }

    public void initialize() {
        zzkO();
        this.zzWh = true;
    }

    public boolean isInitialized() {
        return this.zzWh;
    }

    public abstract void zzkO();

    /* Access modifiers changed, original: protected */
    public void zzma() {
        if (!isInitialized()) {
            throw new IllegalStateException("Not initialized");
        }
    }
}
