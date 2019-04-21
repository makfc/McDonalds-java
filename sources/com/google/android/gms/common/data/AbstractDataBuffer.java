package com.google.android.gms.common.data;

import java.util.Iterator;

public abstract class AbstractDataBuffer<T> implements DataBuffer<T> {
    protected final DataHolder zzamz;

    protected AbstractDataBuffer(DataHolder dataHolder) {
        this.zzamz = dataHolder;
        if (this.zzamz != null) {
            this.zzamz.zzu(this);
        }
    }

    public int getCount() {
        return this.zzamz == null ? 0 : this.zzamz.getCount();
    }

    public Iterator<T> iterator() {
        return new zzb(this);
    }

    public void release() {
        if (this.zzamz != null) {
            this.zzamz.close();
        }
    }
}
