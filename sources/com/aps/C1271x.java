package com.aps;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/* renamed from: com.aps.x */
final class C1271x implements Serializable {
    /* renamed from: a */
    protected short f4540a = (short) 0;
    /* renamed from: b */
    protected int f4541b = 0;
    /* renamed from: c */
    protected byte f4542c = (byte) 0;
    /* renamed from: d */
    protected byte f4543d = (byte) 0;
    /* renamed from: e */
    protected ArrayList f4544e = new ArrayList();
    /* renamed from: f */
    private byte f4545f = (byte) 2;

    C1271x() {
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: a */
    public final Boolean mo13299a(DataOutputStream dataOutputStream) {
        try {
            dataOutputStream.writeByte(this.f4545f);
            dataOutputStream.writeShort(this.f4540a);
            dataOutputStream.writeInt(this.f4541b);
            dataOutputStream.writeByte(this.f4542c);
            dataOutputStream.writeByte(this.f4543d);
            for (byte b = (byte) 0; b < this.f4543d; b++) {
                dataOutputStream.writeShort(((C1224al) this.f4544e.get(b)).f4243a);
                dataOutputStream.writeInt(((C1224al) this.f4544e.get(b)).f4244b);
                dataOutputStream.writeByte(((C1224al) this.f4544e.get(b)).f4245c);
            }
            return Boolean.valueOf(true);
        } catch (IOException e) {
            return Boolean.valueOf(false);
        }
    }
}
