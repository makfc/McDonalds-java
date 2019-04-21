package com.aps;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/* renamed from: com.aps.ag */
final class C1219ag implements Serializable {
    /* renamed from: a */
    protected byte f4222a = (byte) 0;
    /* renamed from: b */
    protected ArrayList f4223b = new ArrayList();
    /* renamed from: c */
    private byte f4224c = (byte) 8;

    C1219ag() {
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: a */
    public final Boolean mo13088a(DataOutputStream dataOutputStream) {
        try {
            dataOutputStream.writeByte(this.f4224c);
            dataOutputStream.writeByte(this.f4222a);
            for (byte b = (byte) 0; b < this.f4222a; b++) {
                C1220ah c1220ah = (C1220ah) this.f4223b.get(b);
                dataOutputStream.write(c1220ah.f4225a);
                dataOutputStream.writeShort(c1220ah.f4226b);
                dataOutputStream.write(C1222aj.m5339a(c1220ah.f4227c, c1220ah.f4227c.length));
            }
            return Boolean.valueOf(true);
        } catch (IOException e) {
            return Boolean.valueOf(false);
        }
    }
}
