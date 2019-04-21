package com.admaster.square.utils;

import android.support.p000v4.media.TransportMediator;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* compiled from: EncryptionUtil */
/* renamed from: com.admaster.square.utils.k */
public class C0492k extends FilterOutputStream {
    /* renamed from: a */
    private boolean f283a;
    /* renamed from: b */
    private int f284b;
    /* renamed from: c */
    private byte[] f285c;
    /* renamed from: d */
    private int f286d;
    /* renamed from: e */
    private int f287e;
    /* renamed from: f */
    private boolean f288f;
    /* renamed from: g */
    private byte[] f289g;
    /* renamed from: h */
    private boolean f290h;
    /* renamed from: i */
    private int f291i;
    /* renamed from: j */
    private byte[] f292j;

    public C0492k(OutputStream outputStream, int i) {
        int i2;
        boolean z = true;
        super(outputStream);
        this.f288f = (i & 8) != 0;
        if ((i & 1) == 0) {
            z = false;
        }
        this.f283a = z;
        if (this.f283a) {
            i2 = 3;
        } else {
            i2 = 4;
        }
        this.f286d = i2;
        this.f285c = new byte[this.f286d];
        this.f284b = 0;
        this.f287e = 0;
        this.f290h = false;
        this.f289g = new byte[4];
        this.f291i = i;
        this.f292j = EncryptionUtil.m432c(i);
    }

    public void write(int i) throws IOException {
        byte[] bArr;
        int i2;
        if (this.f290h) {
            this.out.write(i);
        } else if (this.f283a) {
            bArr = this.f285c;
            i2 = this.f284b;
            this.f284b = i2 + 1;
            bArr[i2] = (byte) i;
            if (this.f284b >= this.f286d) {
                this.out.write(EncryptionUtil.m431b(this.f289g, this.f285c, this.f286d, this.f291i));
                this.f287e += 4;
                if (this.f288f && this.f287e >= 76) {
                    this.out.write(10);
                    this.f287e = 0;
                }
                this.f284b = 0;
            }
        } else if (this.f292j[i & TransportMediator.KEYCODE_MEDIA_PAUSE] > (byte) -5) {
            bArr = this.f285c;
            i2 = this.f284b;
            this.f284b = i2 + 1;
            bArr[i2] = (byte) i;
            if (this.f284b >= this.f286d) {
                this.out.write(this.f289g, 0, EncryptionUtil.m427b(this.f285c, 0, this.f289g, 0, this.f291i));
                this.f284b = 0;
            }
        } else if (this.f292j[i & TransportMediator.KEYCODE_MEDIA_PAUSE] != (byte) -5) {
            throw new IOException("Invalid character in Base64 data.");
        }
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (this.f290h) {
            this.out.write(bArr, i, i2);
            return;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            write(bArr[i + i3]);
        }
    }

    /* renamed from: a */
    public void mo7822a() throws IOException {
        if (this.f284b <= 0) {
            return;
        }
        if (this.f283a) {
            this.out.write(EncryptionUtil.m431b(this.f289g, this.f285c, this.f284b, this.f291i));
            this.f284b = 0;
            return;
        }
        throw new IOException("Base64 input not properly padded.");
    }

    public void close() throws IOException {
        mo7822a();
        super.close();
        this.f285c = null;
        this.out = null;
    }
}
