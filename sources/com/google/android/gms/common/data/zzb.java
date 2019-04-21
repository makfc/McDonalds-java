package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.zzaa;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class zzb<T> implements Iterator<T> {
    protected final DataBuffer<T> zzaoX;
    protected int zzaoY = -1;

    public zzb(DataBuffer<T> dataBuffer) {
        this.zzaoX = (DataBuffer) zzaa.zzz(dataBuffer);
    }

    public boolean hasNext() {
        return this.zzaoY < this.zzaoX.getCount() + -1;
    }

    public T next() {
        if (hasNext()) {
            DataBuffer dataBuffer = this.zzaoX;
            int i = this.zzaoY + 1;
            this.zzaoY = i;
            return dataBuffer.get(i);
        }
        throw new NoSuchElementException("Cannot advance the iterator beyond " + this.zzaoY);
    }

    public void remove() {
        throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
    }
}
