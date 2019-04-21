package com.google.android.gms.common.images;

public final class Size {
    private final int zzpi;
    private final int zzpj;

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Size)) {
            return false;
        }
        Size size = (Size) obj;
        if (!(this.zzpi == size.zzpi && this.zzpj == size.zzpj)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return this.zzpj ^ ((this.zzpi << 16) | (this.zzpi >>> 16));
    }

    public String toString() {
        int i = this.zzpi;
        return i + "x" + this.zzpj;
    }
}
