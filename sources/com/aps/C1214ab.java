package com.aps;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;

/* renamed from: com.aps.ab */
final class C1214ab implements Serializable {
    /* renamed from: a */
    protected int f4186a = 0;
    /* renamed from: b */
    protected int f4187b = 0;
    /* renamed from: c */
    protected int f4188c = 0;
    /* renamed from: d */
    protected int f4189d = 0;
    /* renamed from: e */
    protected int f4190e = 0;
    /* renamed from: f */
    protected short f4191f = (short) 0;
    /* renamed from: g */
    protected byte f4192g = (byte) 0;
    /* renamed from: h */
    protected byte f4193h = (byte) 0;
    /* renamed from: i */
    protected long f4194i = 0;
    /* renamed from: j */
    protected long f4195j = 0;
    /* renamed from: k */
    private byte f4196k = (byte) 1;

    C1214ab() {
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: a */
    public final Boolean mo13085a(DataOutputStream dataOutputStream) {
        Boolean valueOf = Boolean.valueOf(false);
        if (dataOutputStream == null) {
            return valueOf;
        }
        try {
            dataOutputStream.writeByte(this.f4196k);
            dataOutputStream.writeInt(this.f4186a);
            dataOutputStream.writeInt(this.f4187b);
            dataOutputStream.writeInt(this.f4188c);
            dataOutputStream.writeInt(this.f4189d);
            dataOutputStream.writeInt(this.f4190e);
            dataOutputStream.writeShort(this.f4191f);
            dataOutputStream.writeByte(this.f4192g);
            dataOutputStream.writeByte(this.f4193h);
            dataOutputStream.writeLong(this.f4194i);
            dataOutputStream.writeLong(this.f4195j);
            return Boolean.valueOf(true);
        } catch (IOException e) {
            return valueOf;
        }
    }
}
