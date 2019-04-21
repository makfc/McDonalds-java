package com.aps;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* compiled from: StrictLineReader */
/* renamed from: com.aps.t */
class C1267t implements Closeable {
    /* renamed from: a */
    private final InputStream f4532a;
    /* renamed from: b */
    private final Charset f4533b;
    /* renamed from: c */
    private byte[] f4534c;
    /* renamed from: d */
    private int f4535d;
    /* renamed from: e */
    private int f4536e;

    public C1267t(InputStream inputStream, Charset charset) {
        this(inputStream, 8192, charset);
    }

    public C1267t(InputStream inputStream, int i, Charset charset) {
        if (inputStream == null || charset == null) {
            throw new NullPointerException();
        } else if (i < 0) {
            throw new IllegalArgumentException("capacity <= 0");
        } else if (charset.equals(C1268u.f4537a)) {
            this.f4532a = inputStream;
            this.f4533b = charset;
            this.f4534c = new byte[i];
        } else {
            throw new IllegalArgumentException("Unsupported encoding");
        }
    }

    public void close() throws IOException {
        synchronized (this.f4532a) {
            if (this.f4534c != null) {
                this.f4534c = null;
                this.f4532a.close();
            }
        }
    }

    /* renamed from: a */
    public String mo13297a() throws IOException {
        String str;
        synchronized (this.f4532a) {
            if (this.f4534c == null) {
                throw new IOException("LineReader is closed");
            }
            int i;
            if (this.f4535d >= this.f4536e) {
                m5718b();
            }
            int i2 = this.f4535d;
            while (i2 != this.f4536e) {
                if (this.f4534c[i2] == (byte) 10) {
                    int i3 = (i2 == this.f4535d || this.f4534c[i2 - 1] != (byte) 13) ? i2 : i2 - 1;
                    str = new String(this.f4534c, this.f4535d, i3 - this.f4535d, this.f4533b.name());
                    this.f4535d = i2 + 1;
                } else {
                    i2++;
                }
            }
            C12661 c12661 = new ByteArrayOutputStream((this.f4536e - this.f4535d) + 80) {
                public String toString() {
                    int i = (this.count <= 0 || this.buf[this.count - 1] != (byte) 13) ? this.count : this.count - 1;
                    try {
                        return new String(this.buf, 0, i, C1267t.this.f4533b.name());
                    } catch (UnsupportedEncodingException e) {
                        throw new AssertionError(e);
                    }
                }
            };
            loop1:
            while (true) {
                c12661.write(this.f4534c, this.f4535d, this.f4536e - this.f4535d);
                this.f4536e = -1;
                m5718b();
                i = this.f4535d;
                while (i != this.f4536e) {
                    if (this.f4534c[i] == (byte) 10) {
                        break loop1;
                    }
                    i++;
                }
            }
            if (i != this.f4535d) {
                c12661.write(this.f4534c, this.f4535d, i - this.f4535d);
            }
            this.f4535d = i + 1;
            str = c12661.toString();
        }
        return str;
    }

    /* renamed from: b */
    private void m5718b() throws IOException {
        int read = this.f4532a.read(this.f4534c, 0, this.f4534c.length);
        if (read == -1) {
            throw new EOFException();
        }
        this.f4535d = 0;
        this.f4536e = read;
    }
}
