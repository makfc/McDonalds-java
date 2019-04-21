package com.aps;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;

/* renamed from: com.aps.ak */
final class C1223ak implements Serializable {
    /* renamed from: a */
    protected int f4236a = 0;
    /* renamed from: b */
    protected int f4237b = 0;
    /* renamed from: c */
    protected short f4238c = (short) 0;
    /* renamed from: d */
    protected short f4239d = (short) 0;
    /* renamed from: e */
    protected int f4240e = 0;
    /* renamed from: f */
    protected byte f4241f = (byte) 0;
    /* renamed from: g */
    private byte f4242g = (byte) 4;

    C1223ak() {
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: a */
    public final Boolean mo13098a(DataOutputStream dataOutputStream) {
        Boolean valueOf = Boolean.valueOf(false);
        try {
            dataOutputStream.writeByte(this.f4242g);
            dataOutputStream.writeInt(this.f4236a);
            dataOutputStream.writeInt(this.f4237b);
            dataOutputStream.writeShort(this.f4238c);
            dataOutputStream.writeShort(this.f4239d);
            dataOutputStream.writeInt(this.f4240e);
            dataOutputStream.writeByte(this.f4241f);
            return Boolean.valueOf(true);
        } catch (IOException e) {
            return valueOf;
        }
    }
}
