package com.baidu.android.pushservice.util;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.baidu.android.pushservice.util.j */
public class C1546j {
    /* renamed from: a */
    byte[] f5399a = new byte[8];
    /* renamed from: b */
    private DataInputStream f5400b;

    public C1546j(InputStream inputStream) {
        this.f5400b = new DataInputStream(inputStream);
    }

    /* renamed from: a */
    private int m6937a(int i) throws IOException {
        int i2 = 0;
        while (i2 < i) {
            int read = this.f5400b.read(this.f5399a, i2, i - i2);
            if (read == -1) {
                return read;
            }
            i2 += read;
        }
        return i2;
    }

    /* renamed from: a */
    public void mo14066a() throws IOException {
        this.f5400b.close();
    }

    /* renamed from: a */
    public final void mo14067a(byte[] bArr) throws IOException {
        this.f5400b.readFully(bArr, 0, bArr.length);
    }

    /* renamed from: b */
    public final int mo14068b() throws IOException {
        if (m6937a(4) >= 0) {
            return ((((this.f5399a[3] & 255) << 24) | ((this.f5399a[2] & 255) << 16)) | ((this.f5399a[1] & 255) << 8)) | (this.f5399a[0] & 255);
        }
        throw new EOFException();
    }

    /* renamed from: c */
    public final short mo14069c() throws IOException {
        if (m6937a(2) >= 0) {
            return (short) (((this.f5399a[1] & 255) << 8) | (this.f5399a[0] & 255));
        }
        throw new EOFException();
    }

    /* renamed from: d */
    public final long mo14070d() throws IOException {
        if (m6937a(8) < 0) {
            throw new EOFException();
        }
        return (((long) (((((this.f5399a[3] & 255) << 24) | ((this.f5399a[2] & 255) << 16)) | ((this.f5399a[1] & 255) << 8)) | (this.f5399a[0] & 255))) & 4294967295L) | ((((long) (((((this.f5399a[7] & 255) << 24) | ((this.f5399a[6] & 255) << 16)) | ((this.f5399a[5] & 255) << 8)) | (this.f5399a[4] & 255))) & 4294967295L) << 32);
    }
}
