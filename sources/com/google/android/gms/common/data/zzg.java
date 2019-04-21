package com.google.android.gms.common.data;

import java.util.NoSuchElementException;

public class zzg<T> extends zzb<T> {
    private T zzapu;

    public T next() {
        if (hasNext()) {
            this.zzaoY++;
            if (this.zzaoY == 0) {
                this.zzapu = this.zzaoX.get(0);
                if (!(this.zzapu instanceof zzc)) {
                    String valueOf = String.valueOf(this.zzapu.getClass());
                    throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 44).append("DataBuffer reference of type ").append(valueOf).append(" is not movable").toString());
                }
            }
            ((zzc) this.zzapu).zzbN(this.zzaoY);
            return this.zzapu;
        }
        throw new NoSuchElementException("Cannot advance the iterator beyond " + this.zzaoY);
    }
}
