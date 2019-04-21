package com.aps;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.zip.GZIPInputStream;

/* renamed from: com.aps.bg */
public final class C1250bg {
    /* renamed from: a */
    private RandomAccessFile f4370a;
    /* renamed from: b */
    private C1222aj f4371b;
    /* renamed from: c */
    private File f4372c = null;

    protected C1250bg(C1222aj c1222aj) {
        this.f4371b = c1222aj;
    }

    /* renamed from: a */
    private static byte m5544a(byte[] bArr) {
        byte[] bArr2 = null;
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            byte[] bArr3 = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read = gZIPInputStream.read(bArr3, 0, bArr3.length);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr3, 0, read);
            }
            bArr2 = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            gZIPInputStream.close();
            byteArrayInputStream.close();
        } catch (Exception e) {
        }
        return bArr2[0];
    }

    /* renamed from: a */
    private static int m5545a(int i, int i2, int i3) {
        int i4 = ((i3 - 1) * 1500) + i;
        while (i4 >= i2) {
            i4 -= 1500;
        }
        return i4;
    }

    /* renamed from: a */
    private int m5546a(BitSet bitSet) {
        for (int i = 0; i < bitSet.length(); i++) {
            if (bitSet.get(i)) {
                return this.f4371b.mo13093a() + ((i * 1500) + 4);
            }
        }
        return 0;
    }

    /* renamed from: a */
    private ArrayList m5547a(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        while (i <= i2) {
            try {
                this.f4370a.seek((long) i);
                int readInt = this.f4370a.readInt();
                this.f4370a.readLong();
                if (readInt <= 0 || readInt > 1500) {
                    return null;
                }
                byte[] bArr = new byte[readInt];
                this.f4370a.read(bArr);
                byte a = C1250bg.m5544a(bArr);
                if (a != (byte) 3 && a != (byte) 4 && a != (byte) 41) {
                    return null;
                }
                arrayList.add(bArr);
                i += 1500;
            } catch (IOException e) {
            }
        }
        return arrayList;
    }

    /* renamed from: b */
    private BitSet m5548b() {
        BitSet bitSet = null;
        byte[] bArr = new byte[this.f4371b.mo13093a()];
        try {
            this.f4370a.read(bArr);
            return C1222aj.m5341b(bArr);
        } catch (IOException e) {
            return bitSet;
        }
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: a */
    public final int mo13187a() {
        int i = 0;
        synchronized (this) {
            this.f4372c = this.f4371b.mo13096b();
            try {
                if (this.f4372c != null) {
                    this.f4370a = new RandomAccessFile(this.f4371b.mo13096b(), "rw");
                    byte[] bArr = new byte[this.f4371b.mo13093a()];
                    this.f4370a.read(bArr);
                    BitSet b = C1222aj.m5341b(bArr);
                    for (int i2 = 0; i2 < b.size(); i2++) {
                        if (b.get(i2)) {
                            i++;
                        }
                    }
                }
                if (this.f4370a != null) {
                    try {
                        this.f4370a.close();
                    } catch (IOException e) {
                    }
                }
            } catch (FileNotFoundException e2) {
                if (this.f4370a != null) {
                    try {
                        this.f4370a.close();
                    } catch (IOException e3) {
                    }
                }
            } catch (IOException e4) {
                if (this.f4370a != null) {
                    try {
                        this.f4370a.close();
                    } catch (IOException e5) {
                    }
                }
            } catch (NullPointerException e6) {
                if (this.f4370a != null) {
                    try {
                        this.f4370a.close();
                    } catch (IOException e7) {
                    }
                }
            } catch (Throwable th) {
                if (this.f4370a != null) {
                    try {
                        this.f4370a.close();
                    } catch (IOException e8) {
                    }
                }
            }
            this.f4372c = null;
        }
        return i;
    }

    /* Access modifiers changed, original: protected|final|declared_synchronized */
    /* renamed from: a */
    public final synchronized C1221ai mo13188a(int i) {
        C1221ai c1221ai = null;
        synchronized (this) {
            if (this.f4371b != null) {
                synchronized (this) {
                    this.f4372c = this.f4371b.mo13096b();
                    if (this.f4372c == null) {
                    } else {
                        C1221ai c1221ai2;
                        try {
                            this.f4370a = new RandomAccessFile(this.f4372c, "rw");
                            BitSet b = m5548b();
                            if (b == null) {
                                this.f4372c.delete();
                                if (this.f4370a != null) {
                                    try {
                                        this.f4370a.close();
                                    } catch (Exception e) {
                                    }
                                }
                            } else {
                                int a = m5546a(b);
                                ArrayList a2 = m5547a(a, C1250bg.m5545a(a, (int) this.f4372c.length(), i));
                                if (a2 == null) {
                                    this.f4372c.delete();
                                    if (this.f4370a != null) {
                                        try {
                                            this.f4370a.close();
                                        } catch (Exception e2) {
                                        }
                                    }
                                } else {
                                    c1221ai2 = new C1221ai(this.f4372c, a2, new int[]{((a - this.f4371b.mo13093a()) - 4) / 1500, ((r2 - this.f4371b.mo13093a()) - 4) / 1500});
                                    if (this.f4370a != null) {
                                        try {
                                            this.f4370a.close();
                                        } catch (Exception e3) {
                                        }
                                    }
                                    if (c1221ai2 != null) {
                                        if (c1221ai2.mo13092c() > 100 && c1221ai2.mo13092c() < 5242880) {
                                            c1221ai = c1221ai2;
                                        }
                                    }
                                    this.f4372c.delete();
                                    this.f4372c = null;
                                }
                            }
                        } catch (FileNotFoundException e4) {
                            if (this.f4370a != null) {
                                try {
                                    this.f4370a.close();
                                    c1221ai2 = null;
                                } catch (Exception e5) {
                                    c1221ai2 = null;
                                }
                            }
                            c1221ai2 = null;
                        } catch (Exception e6) {
                            if (this.f4370a != null) {
                                try {
                                    this.f4370a.close();
                                    c1221ai2 = null;
                                } catch (Exception e7) {
                                    c1221ai2 = null;
                                }
                            }
                            c1221ai2 = null;
                        } catch (Throwable th) {
                            if (this.f4370a != null) {
                                try {
                                    this.f4370a.close();
                                } catch (Exception e8) {
                                }
                            }
                        }
                    }
                }
            }
        }
        return c1221ai;
    }

    /* Access modifiers changed, original: protected|final|declared_synchronized */
    /* renamed from: a */
    public final synchronized void mo13189a(C1221ai c1221ai) {
        BitSet bitSet = null;
        synchronized (this) {
            synchronized (this) {
                this.f4372c = c1221ai.f4228a;
                if (this.f4372c == null) {
                } else {
                    try {
                        this.f4370a = new RandomAccessFile(this.f4372c, "rw");
                        byte[] bArr = new byte[this.f4371b.mo13093a()];
                        this.f4370a.read(bArr);
                        bitSet = C1222aj.m5341b(bArr);
                        if (c1221ai.mo13091b()) {
                            for (int i = c1221ai.f4229b[0]; i <= c1221ai.f4229b[1]; i++) {
                                bitSet.set(i, false);
                            }
                            this.f4370a.seek(0);
                            this.f4370a.write(C1222aj.m5337a(bitSet));
                        }
                        if (this.f4370a != null) {
                            try {
                                this.f4370a.close();
                            } catch (IOException e) {
                            }
                        }
                    } catch (FileNotFoundException e2) {
                        if (this.f4370a != null) {
                            try {
                                this.f4370a.close();
                            } catch (IOException e3) {
                            }
                        }
                    } catch (IOException e4) {
                        if (this.f4370a != null) {
                            try {
                                this.f4370a.close();
                            } catch (IOException e5) {
                            }
                        }
                    } catch (Throwable th) {
                        if (this.f4370a != null) {
                            try {
                                this.f4370a.close();
                            } catch (IOException e6) {
                            }
                        }
                    }
                    if (bitSet.isEmpty()) {
                        this.f4372c.delete();
                    }
                    this.f4372c = null;
                }
            }
        }
        return;
    }
}
