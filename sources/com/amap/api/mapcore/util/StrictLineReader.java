package com.amap.api.mapcore.util;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* renamed from: com.amap.api.mapcore.util.fo */
public class StrictLineReader implements Closeable {
    /* renamed from: a */
    private final InputStream f1983a;
    /* renamed from: b */
    private final Charset f1984b;
    /* renamed from: c */
    private byte[] f1985c;
    /* renamed from: d */
    private int f1986d;
    /* renamed from: e */
    private int f1987e;

    public StrictLineReader(InputStream inputStream, Charset charset) {
        this(inputStream, 8192, charset);
    }

    public StrictLineReader(InputStream inputStream, int i, Charset charset) {
        if (inputStream == null || charset == null) {
            throw new NullPointerException();
        } else if (i < 0) {
            throw new IllegalArgumentException("capacity <= 0");
        } else if (charset.equals(C0846fp.f1988a)) {
            this.f1983a = inputStream;
            this.f1984b = charset;
            this.f1985c = new byte[i];
        } else {
            throw new IllegalArgumentException("Unsupported encoding");
        }
    }

    public void close() throws IOException {
        synchronized (this.f1983a) {
            if (this.f1985c != null) {
                this.f1985c = null;
                this.f1983a.close();
            }
        }
    }

    /* renamed from: a */
    public String mo9411a() throws IOException {
        String str;
        synchronized (this.f1983a) {
            if (this.f1985c == null) {
                throw new IOException("LineReader is closed");
            }
            int i;
            if (this.f1986d >= this.f1987e) {
                m2796b();
            }
            int i2 = this.f1986d;
            while (i2 != this.f1987e) {
                if (this.f1985c[i2] == (byte) 10) {
                    int i3 = (i2 == this.f1986d || this.f1985c[i2 - 1] != (byte) 13) ? i2 : i2 - 1;
                    str = new String(this.f1985c, this.f1986d, i3 - this.f1986d, this.f1984b.name());
                    this.f1986d = i2 + 1;
                } else {
                    i2++;
                }
            }
            C08451 c08451 = new ByteArrayOutputStream((this.f1987e - this.f1986d) + 80) {
                public String toString() {
                    int i = (this.count <= 0 || this.buf[this.count - 1] != (byte) 13) ? this.count : this.count - 1;
                    try {
                        return new String(this.buf, 0, i, StrictLineReader.this.f1984b.name());
                    } catch (UnsupportedEncodingException e) {
                        throw new AssertionError(e);
                    }
                }
            };
            loop1:
            while (true) {
                c08451.write(this.f1985c, this.f1986d, this.f1987e - this.f1986d);
                this.f1987e = -1;
                m2796b();
                i = this.f1986d;
                while (i != this.f1987e) {
                    if (this.f1985c[i] == (byte) 10) {
                        break loop1;
                    }
                    i++;
                }
            }
            if (i != this.f1986d) {
                c08451.write(this.f1985c, this.f1986d, i - this.f1986d);
            }
            this.f1986d = i + 1;
            str = c08451.toString();
        }
        return str;
    }

    /* renamed from: b */
    private void m2796b() throws IOException {
        int read = this.f1983a.read(this.f1985c, 0, this.f1985c.length);
        if (read == -1) {
            throw new EOFException();
        }
        this.f1986d = 0;
        this.f1987e = read;
    }
}
