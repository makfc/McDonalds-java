package com.amap.api.services.core;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* compiled from: StrictLineReader */
/* renamed from: com.amap.api.services.core.bn */
public class C1118bn implements Closeable {
    /* renamed from: a */
    private final InputStream f3765a;
    /* renamed from: b */
    private final Charset f3766b;
    /* renamed from: c */
    private byte[] f3767c;
    /* renamed from: d */
    private int f3768d;
    /* renamed from: e */
    private int f3769e;

    public C1118bn(InputStream inputStream, Charset charset) {
        this(inputStream, 8192, charset);
    }

    public C1118bn(InputStream inputStream, int i, Charset charset) {
        if (inputStream == null || charset == null) {
            throw new NullPointerException();
        } else if (i < 0) {
            throw new IllegalArgumentException("capacity <= 0");
        } else if (charset.equals(C1119bo.f3770a)) {
            this.f3765a = inputStream;
            this.f3766b = charset;
            this.f3767c = new byte[i];
        } else {
            throw new IllegalArgumentException("Unsupported encoding");
        }
    }

    public void close() throws IOException {
        synchronized (this.f3765a) {
            if (this.f3767c != null) {
                this.f3767c = null;
                this.f3765a.close();
            }
        }
    }

    /* renamed from: a */
    public String mo12087a() throws IOException {
        String str;
        synchronized (this.f3765a) {
            if (this.f3767c == null) {
                throw new IOException("LineReader is closed");
            }
            int i;
            if (this.f3768d >= this.f3769e) {
                m4939b();
            }
            int i2 = this.f3768d;
            while (i2 != this.f3769e) {
                if (this.f3767c[i2] == (byte) 10) {
                    int i3 = (i2 == this.f3768d || this.f3767c[i2 - 1] != (byte) 13) ? i2 : i2 - 1;
                    str = new String(this.f3767c, this.f3768d, i3 - this.f3768d, this.f3766b.name());
                    this.f3768d = i2 + 1;
                } else {
                    i2++;
                }
            }
            C11171 c11171 = new ByteArrayOutputStream((this.f3769e - this.f3768d) + 80) {
                public String toString() {
                    int i = (this.count <= 0 || this.buf[this.count - 1] != (byte) 13) ? this.count : this.count - 1;
                    try {
                        return new String(this.buf, 0, i, C1118bn.this.f3766b.name());
                    } catch (UnsupportedEncodingException e) {
                        throw new AssertionError(e);
                    }
                }
            };
            loop1:
            while (true) {
                c11171.write(this.f3767c, this.f3768d, this.f3769e - this.f3768d);
                this.f3769e = -1;
                m4939b();
                i = this.f3768d;
                while (i != this.f3769e) {
                    if (this.f3767c[i] == (byte) 10) {
                        break loop1;
                    }
                    i++;
                }
            }
            if (i != this.f3768d) {
                c11171.write(this.f3767c, this.f3768d, i - this.f3768d);
            }
            this.f3768d = i + 1;
            str = c11171.toString();
        }
        return str;
    }

    /* renamed from: b */
    private void m4939b() throws IOException {
        int read = this.f3765a.read(this.f3767c, 0, this.f3767c.length);
        if (read == -1) {
            throw new EOFException();
        }
        this.f3768d = 0;
        this.f3769e = read;
    }
}
