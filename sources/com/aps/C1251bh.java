package com.aps;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.BitSet;

/* renamed from: com.aps.bh */
public final class C1251bh {
    /* renamed from: a */
    private RandomAccessFile f4373a;
    /* renamed from: b */
    private C1222aj f4374b;
    /* renamed from: c */
    private String f4375c = "";
    /* renamed from: d */
    private File f4376d = null;

    protected C1251bh(C1222aj c1222aj) {
        this.f4374b = c1222aj;
    }

    /* Access modifiers changed, original: protected|final|declared_synchronized */
    /* renamed from: a */
    public final synchronized void mo13190a(long j, byte[] bArr) {
        int i = 0;
        synchronized (this) {
            this.f4376d = this.f4374b.mo13094a(j);
            if (this.f4376d != null) {
                try {
                    this.f4373a = new RandomAccessFile(this.f4376d, "rw");
                    byte[] bArr2 = new byte[this.f4374b.mo13093a()];
                    int readInt = this.f4373a.read(bArr2) == -1 ? 0 : this.f4373a.readInt();
                    BitSet b = C1222aj.m5341b(bArr2);
                    int a = (this.f4374b.mo13093a() + 4) + (readInt * 1500);
                    if (readInt < 0 || readInt > (this.f4374b.mo13093a() << 3)) {
                        this.f4373a.close();
                        this.f4376d.delete();
                        if (this.f4373a != null) {
                            try {
                                this.f4373a.close();
                            } catch (IOException e) {
                            }
                        }
                    } else {
                        this.f4373a.seek((long) a);
                        byte[] a2 = C1222aj.m5338a(bArr);
                        this.f4373a.writeInt(a2.length);
                        this.f4373a.writeLong(j);
                        this.f4373a.write(a2);
                        b.set(readInt, true);
                        this.f4373a.seek(0);
                        this.f4373a.write(C1222aj.m5337a(b));
                        readInt++;
                        if (readInt != (this.f4374b.mo13093a() << 3)) {
                            i = readInt;
                        }
                        this.f4373a.writeInt(i);
                        if (!this.f4375c.equalsIgnoreCase(this.f4376d.getName())) {
                            this.f4375c = this.f4376d.getName();
                        }
                        this.f4376d.length();
                        if (this.f4373a != null) {
                            try {
                                this.f4373a.close();
                            } catch (IOException e2) {
                            }
                        }
                        this.f4376d = null;
                    }
                } catch (FileNotFoundException e3) {
                    if (this.f4373a != null) {
                        try {
                            this.f4373a.close();
                        } catch (IOException e4) {
                        }
                    }
                } catch (IOException e5) {
                    if (this.f4373a != null) {
                        try {
                            this.f4373a.close();
                        } catch (IOException e6) {
                        }
                    }
                } catch (Throwable th) {
                    if (this.f4373a != null) {
                        try {
                            this.f4373a.close();
                        } catch (IOException e7) {
                        }
                    }
                }
            }
        }
        return;
    }
}
