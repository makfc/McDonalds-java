package com.google.android.gms.gcm;

public class zzc {
    public static final zzc zzaTm = new zzc(0, 30, 3600);
    public static final zzc zzaTn = new zzc(1, 30, 3600);
    private final int zzaTo;
    private final int zzaTp;
    private final int zzaTq;

    private zzc(int i, int i2, int i3) {
        this.zzaTo = i;
        this.zzaTp = i2;
        this.zzaTq = i3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzc)) {
            return false;
        }
        zzc zzc = (zzc) obj;
        return zzc.zzaTo == this.zzaTo && zzc.zzaTp == this.zzaTp && zzc.zzaTq == this.zzaTq;
    }

    public int hashCode() {
        return (((((this.zzaTo + 1) ^ 1000003) * 1000003) ^ this.zzaTp) * 1000003) ^ this.zzaTq;
    }

    public String toString() {
        int i = this.zzaTo;
        int i2 = this.zzaTp;
        return "policy=" + i + " initial_backoff=" + i2 + " maximum_backoff=" + this.zzaTq;
    }
}
