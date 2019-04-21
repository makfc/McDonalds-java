package com.aps;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/* renamed from: com.aps.ae */
final class C1217ae implements Serializable {
    /* renamed from: a */
    protected byte f4209a = (byte) 0;
    /* renamed from: b */
    protected ArrayList f4210b = new ArrayList();
    /* renamed from: c */
    private byte f4211c = (byte) 3;

    C1217ae() {
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: a */
    public final Boolean mo13087a(DataOutputStream dataOutputStream) {
        try {
            dataOutputStream.writeByte(this.f4211c);
            dataOutputStream.writeByte(this.f4209a);
            for (int i = 0; i < this.f4210b.size(); i++) {
                C1218af c1218af = (C1218af) this.f4210b.get(i);
                dataOutputStream.writeByte(c1218af.f4212a);
                byte[] bArr = new byte[c1218af.f4212a];
                System.arraycopy(c1218af.f4213b, 0, bArr, 0, c1218af.f4212a < c1218af.f4213b.length ? c1218af.f4212a : c1218af.f4213b.length);
                dataOutputStream.write(bArr);
                dataOutputStream.writeDouble(c1218af.f4214c);
                dataOutputStream.writeInt(c1218af.f4215d);
                dataOutputStream.writeInt(c1218af.f4216e);
                dataOutputStream.writeDouble(c1218af.f4217f);
                dataOutputStream.writeByte(c1218af.f4218g);
                dataOutputStream.writeByte(c1218af.f4219h);
                bArr = new byte[c1218af.f4219h];
                System.arraycopy(c1218af.f4220i, 0, bArr, 0, c1218af.f4219h < c1218af.f4220i.length ? c1218af.f4219h : c1218af.f4220i.length);
                dataOutputStream.write(bArr);
                dataOutputStream.writeByte(c1218af.f4221j);
            }
            return Boolean.valueOf(true);
        } catch (IOException e) {
            return Boolean.valueOf(false);
        }
    }
}
