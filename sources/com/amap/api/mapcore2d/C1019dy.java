package com.amap.api.mapcore2d;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* compiled from: StrictLineReader */
/* renamed from: com.amap.api.mapcore2d.dy */
public class C1019dy implements Closeable {
    /* renamed from: a */
    private final InputStream f2888a;
    /* renamed from: b */
    private final Charset f2889b;
    /* renamed from: c */
    private byte[] f2890c;
    /* renamed from: d */
    private int f2891d;
    /* renamed from: e */
    private int f2892e;

    public C1019dy(InputStream inputStream, Charset charset) {
        this(inputStream, 8192, charset);
    }

    public C1019dy(InputStream inputStream, int i, Charset charset) {
        if (inputStream == null || charset == null) {
            throw new NullPointerException();
        } else if (i < 0) {
            throw new IllegalArgumentException("capacity <= 0");
        } else if (charset.equals(C1020dz.f2893a)) {
            this.f2888a = inputStream;
            this.f2889b = charset;
            this.f2890c = new byte[i];
        } else {
            throw new IllegalArgumentException("Unsupported encoding");
        }
    }

    public void close() throws IOException {
        synchronized (this.f2888a) {
            if (this.f2890c != null) {
                this.f2890c = null;
                this.f2888a.close();
            }
        }
    }

    /* renamed from: a */
    public String mo10266a() throws IOException {
        String str;
        synchronized (this.f2888a) {
            if (this.f2890c == null) {
                throw new IOException("LineReader is closed");
            }
            int i;
            if (this.f2891d >= this.f2892e) {
                m4256b();
            }
            int i2 = this.f2891d;
            while (i2 != this.f2892e) {
                if (this.f2890c[i2] == (byte) 10) {
                    int i3 = (i2 == this.f2891d || this.f2890c[i2 - 1] != (byte) 13) ? i2 : i2 - 1;
                    str = new String(this.f2890c, this.f2891d, i3 - this.f2891d, this.f2889b.name());
                    this.f2891d = i2 + 1;
                } else {
                    i2++;
                }
            }
            C10181 c10181 = new ByteArrayOutputStream((this.f2892e - this.f2891d) + 80) {
                public String toString() {
                    int i = (this.count <= 0 || this.buf[this.count - 1] != (byte) 13) ? this.count : this.count - 1;
                    try {
                        return new String(this.buf, 0, i, C1019dy.this.f2889b.name());
                    } catch (UnsupportedEncodingException e) {
                        throw new AssertionError(e);
                    }
                }
            };
            loop1:
            while (true) {
                c10181.write(this.f2890c, this.f2891d, this.f2892e - this.f2891d);
                this.f2892e = -1;
                m4256b();
                i = this.f2891d;
                while (i != this.f2892e) {
                    if (this.f2890c[i] == (byte) 10) {
                        break loop1;
                    }
                    i++;
                }
            }
            if (i != this.f2891d) {
                c10181.write(this.f2890c, this.f2891d, i - this.f2891d);
            }
            this.f2891d = i + 1;
            str = c10181.toString();
        }
        return str;
    }

    /* renamed from: b */
    private void m4256b() throws IOException {
        int read = this.f2888a.read(this.f2890c, 0, this.f2890c.length);
        if (read == -1) {
            throw new EOFException();
        }
        this.f2891d = 0;
        this.f2892e = read;
    }
}
