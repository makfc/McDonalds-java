package com.aps;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/* renamed from: com.aps.ac */
final class C1215ac implements Serializable {
    /* renamed from: a */
    protected byte[] f4197a = new byte[16];
    /* renamed from: b */
    protected byte[] f4198b = new byte[16];
    /* renamed from: c */
    protected byte[] f4199c = new byte[16];
    /* renamed from: d */
    protected short f4200d = (short) 0;
    /* renamed from: e */
    protected short f4201e = (short) 0;
    /* renamed from: f */
    protected byte f4202f = (byte) 0;
    /* renamed from: g */
    protected byte[] f4203g = new byte[16];
    /* renamed from: h */
    protected byte[] f4204h = new byte[32];
    /* renamed from: i */
    protected short f4205i = (short) 0;
    /* renamed from: j */
    protected ArrayList f4206j = new ArrayList();
    /* renamed from: k */
    private byte f4207k = (byte) 41;
    /* renamed from: l */
    private short f4208l = (short) 0;

    C1215ac() {
    }

    /* renamed from: a */
    private Boolean m5323a(DataOutputStream dataOutputStream) {
        Boolean.valueOf(true);
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream2 = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream2.flush();
            dataOutputStream2.write(this.f4197a);
            dataOutputStream2.write(this.f4198b);
            dataOutputStream2.write(this.f4199c);
            dataOutputStream2.writeShort(this.f4200d);
            dataOutputStream2.writeShort(this.f4201e);
            dataOutputStream2.writeByte(this.f4202f);
            this.f4203g[15] = (byte) 0;
            dataOutputStream2.write(C1222aj.m5339a(this.f4203g, this.f4203g.length));
            this.f4204h[31] = (byte) 0;
            dataOutputStream2.write(C1222aj.m5339a(this.f4204h, this.f4204h.length));
            dataOutputStream2.writeShort(this.f4205i);
            for (short s = (short) 0; s < this.f4205i; s = (short) (s + 1)) {
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream3 = new DataOutputStream(byteArrayOutputStream2);
                dataOutputStream3.flush();
                C1273z c1273z = (C1273z) this.f4206j.get(s);
                if (!(c1273z.f4553c == null || c1273z.f4553c.mo13085a(dataOutputStream3).booleanValue())) {
                    Boolean.valueOf(false);
                }
                if (!(c1273z.f4554d == null || c1273z.f4554d.mo13299a(dataOutputStream3).booleanValue())) {
                    Boolean.valueOf(false);
                }
                if (!(c1273z.f4555e == null || c1273z.f4555e.mo13098a(dataOutputStream3).booleanValue())) {
                    Boolean.valueOf(false);
                }
                if (!(c1273z.f4556f == null || c1273z.f4556f.mo13088a(dataOutputStream3).booleanValue())) {
                    Boolean.valueOf(false);
                }
                if (!(c1273z.f4557g == null || c1273z.f4557g.mo13087a(dataOutputStream3).booleanValue())) {
                    Boolean.valueOf(false);
                }
                c1273z.f4551a = Integer.valueOf(byteArrayOutputStream2.size() + 4).shortValue();
                dataOutputStream2.writeShort(c1273z.f4551a);
                dataOutputStream2.writeInt(c1273z.f4552b);
                dataOutputStream2.write(byteArrayOutputStream2.toByteArray());
            }
            this.f4208l = Integer.valueOf(byteArrayOutputStream.size()).shortValue();
            dataOutputStream.writeByte(this.f4207k);
            dataOutputStream.writeShort(this.f4208l);
            dataOutputStream.write(byteArrayOutputStream.toByteArray());
            return Boolean.valueOf(true);
        } catch (IOException e) {
            return Boolean.valueOf(false);
        }
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: a */
    public final byte[] mo13086a() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        m5323a(new DataOutputStream(byteArrayOutputStream));
        return byteArrayOutputStream.toByteArray();
    }
}
