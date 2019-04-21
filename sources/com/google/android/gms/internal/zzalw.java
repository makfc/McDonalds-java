package com.google.android.gms.internal;

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;

public class zzalw implements Closeable {
    private static final char[] zzbZl = ")]}'\n".toCharArray();
    /* renamed from: in */
    private final Reader f6041in;
    private int pos;
    private int zzbZo;
    private int zzbZp;
    private int zzbZq;
    private int[] zzbZu;
    private int zzbZv;

    /* renamed from: com.google.android.gms.internal.zzalw$1 */
    static class C21901 extends zzalb {
        C21901() {
        }
    }

    static {
        zzalb.zzbXe = new C21901();
    }

    private int getColumnNumber() {
        return (this.pos - this.zzbZp) + 1;
    }

    private int getLineNumber() {
        return this.zzbZo + 1;
    }

    public void close() throws IOException {
        this.zzbZq = 0;
        this.zzbZu[0] = 8;
        this.zzbZv = 1;
        this.f6041in.close();
    }

    public String toString() {
        String valueOf = String.valueOf(getClass().getSimpleName());
        int lineNumber = getLineNumber();
        return new StringBuilder(String.valueOf(valueOf).length() + 39).append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(getColumnNumber()).toString();
    }
}
