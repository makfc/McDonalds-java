package com.google.android.gms.internal;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public final class zzalm extends zzalw {
    private static final Reader zzbXI = new C21891();
    private static final Object zzbXJ = new Object();
    private final List<Object> zzbXK;

    /* renamed from: com.google.android.gms.internal.zzalm$1 */
    static class C21891 extends Reader {
        C21891() {
        }

        public void close() throws IOException {
            throw new AssertionError();
        }

        public int read(char[] cArr, int i, int i2) throws IOException {
            throw new AssertionError();
        }
    }

    public void close() throws IOException {
        this.zzbXK.clear();
        this.zzbXK.add(zzbXJ);
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}
