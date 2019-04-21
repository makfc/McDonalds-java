package com.baidu.android.pushservice.util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* renamed from: com.baidu.android.pushservice.util.k */
public class C1547k {
    /* renamed from: a */
    byte[] f5401a = new byte[8];
    /* renamed from: b */
    private DataOutputStream f5402b;

    public C1547k(OutputStream outputStream) {
        this.f5402b = new DataOutputStream(outputStream);
    }

    /* renamed from: a */
    public void mo14071a() throws IOException {
        this.f5402b.close();
    }

    /* renamed from: a */
    public final void mo14072a(int i) throws IOException {
        this.f5401a[1] = (byte) (i >> 8);
        this.f5401a[0] = (byte) i;
        this.f5402b.write(this.f5401a, 0, 2);
    }

    /* renamed from: a */
    public final void mo14073a(long j) throws IOException {
        this.f5401a[7] = (byte) ((int) (j >> 56));
        this.f5401a[6] = (byte) ((int) (j >> 48));
        this.f5401a[5] = (byte) ((int) (j >> 40));
        this.f5401a[4] = (byte) ((int) (j >> 32));
        this.f5401a[3] = (byte) ((int) (j >> 24));
        this.f5401a[2] = (byte) ((int) (j >> 16));
        this.f5401a[1] = (byte) ((int) (j >> 8));
        this.f5401a[0] = (byte) ((int) j);
        this.f5402b.write(this.f5401a, 0, 8);
    }

    /* renamed from: a */
    public void mo14074a(byte[] bArr) throws IOException {
        this.f5402b.write(bArr);
    }

    /* renamed from: b */
    public final void mo14075b(int i) throws IOException {
        this.f5401a[3] = (byte) (i >> 24);
        this.f5401a[2] = (byte) (i >> 16);
        this.f5401a[1] = (byte) (i >> 8);
        this.f5401a[0] = (byte) i;
        this.f5402b.write(this.f5401a, 0, 4);
    }
}
