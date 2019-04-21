package com.aps;

import com.aps.C1259h.C1256a;
import com.aps.C1259h.C1258c;
import com.facebook.stetho.common.Utf8Charset;
import java.io.File;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* renamed from: com.aps.r */
public class SimpleDiskCache {
    /* renamed from: a */
    private static List<File> f4527a = new ArrayList();
    /* renamed from: b */
    private C1259h f4528b;
    /* renamed from: c */
    private int f4529c;

    /* compiled from: SimpleDiskCache */
    /* renamed from: com.aps.r$a */
    private static class C1265a extends FilterOutputStream {
        /* renamed from: a */
        private final C1256a f4525a;
        /* renamed from: b */
        private boolean f4526b;

        private C1265a(OutputStream outputStream, C1256a c1256a) {
            super(outputStream);
            this.f4526b = false;
            this.f4525a = c1256a;
        }

        public void close() throws IOException {
            IOException e = null;
            try {
                super.close();
            } catch (IOException e2) {
                e = e2;
            }
            if (this.f4526b) {
                this.f4525a.mo13260b();
            } else {
                this.f4525a.mo13259a();
            }
            if (e != null) {
                throw e;
            }
        }

        public void flush() throws IOException {
            try {
                super.flush();
            } catch (IOException e) {
                this.f4526b = true;
                throw e;
            }
        }

        public void write(int i) throws IOException {
            try {
                super.write(i);
            } catch (IOException e) {
                this.f4526b = true;
                throw e;
            }
        }

        public void write(byte[] bArr) throws IOException {
            try {
                super.write(bArr);
            } catch (IOException e) {
                this.f4526b = true;
                throw e;
            }
        }

        public void write(byte[] bArr, int i, int i2) throws IOException {
            try {
                super.write(bArr, i, i2);
            } catch (IOException e) {
                this.f4526b = true;
                throw e;
            }
        }
    }

    private SimpleDiskCache(File file, int i, long j) throws IOException {
        this.f4529c = i;
        this.f4528b = C1259h.m5652a(file, i, 1, j);
    }

    /* renamed from: a */
    public static synchronized SimpleDiskCache m5703a(File file, int i, long j) throws IOException {
        SimpleDiskCache simpleDiskCache;
        synchronized (SimpleDiskCache.class) {
            if (f4527a.contains(file)) {
                throw new IllegalStateException("Cache dir " + file.getAbsolutePath() + " was used before.");
            }
            f4527a.add(file);
            simpleDiskCache = new SimpleDiskCache(file, i, j);
        }
        return simpleDiskCache;
    }

    /* renamed from: a */
    public void mo13294a() {
        try {
            if (f4527a != null) {
                f4527a.clear();
            }
            if (this.f4528b != null) {
                this.f4528b.close();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public Map<String, Serializable> mo13293a(String str) throws IOException {
        C1258c a = this.f4528b.mo13266a(m5705b(str));
        if (a == null) {
            return null;
        }
        try {
            Map<String, Serializable> a2 = m5704a(a);
            return a2;
        } finally {
            a.close();
        }
    }

    /* renamed from: a */
    public OutputStream mo13292a(String str, Map<String, ? extends Serializable> map) throws IOException {
        C1256a b = this.f4528b.mo13268b(m5705b(str));
        if (b == null) {
            return null;
        }
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(b.mo13258a(0));
            objectOutputStream.writeObject(map);
            return new C1265a(objectOutputStream, b);
        } catch (IOException e) {
            b.mo13260b();
            throw e;
        }
    }

    /* renamed from: b */
    public void mo13295b(String str, Map<String, ? extends Serializable> map) throws IOException {
        OutputStream outputStream = null;
        try {
            outputStream = mo13292a(str, map);
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0027  */
    /* renamed from: a */
    private java.util.Map<java.lang.String, java.io.Serializable> m5704a(com.aps.C1259h.C1258c r5) throws java.io.IOException {
        /*
        r4 = this;
        r2 = 0;
        r1 = new java.io.ObjectInputStream;	 Catch:{ ClassNotFoundException -> 0x001c, all -> 0x002b }
        r0 = new java.io.BufferedInputStream;	 Catch:{ ClassNotFoundException -> 0x001c, all -> 0x002b }
        r3 = 0;
        r3 = r5.mo13264a(r3);	 Catch:{ ClassNotFoundException -> 0x001c, all -> 0x002b }
        r0.<init>(r3);	 Catch:{ ClassNotFoundException -> 0x001c, all -> 0x002b }
        r1.<init>(r0);	 Catch:{ ClassNotFoundException -> 0x001c, all -> 0x002b }
        r0 = r1.readObject();	 Catch:{ ClassNotFoundException -> 0x002e }
        r0 = (java.util.Map) r0;	 Catch:{ ClassNotFoundException -> 0x002e }
        if (r1 == 0) goto L_0x001b;
    L_0x0018:
        r1.close();
    L_0x001b:
        return r0;
    L_0x001c:
        r0 = move-exception;
        r1 = r2;
    L_0x001e:
        r2 = new java.lang.RuntimeException;	 Catch:{ all -> 0x0024 }
        r2.<init>(r0);	 Catch:{ all -> 0x0024 }
        throw r2;	 Catch:{ all -> 0x0024 }
    L_0x0024:
        r0 = move-exception;
    L_0x0025:
        if (r1 == 0) goto L_0x002a;
    L_0x0027:
        r1.close();
    L_0x002a:
        throw r0;
    L_0x002b:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0025;
    L_0x002e:
        r0 = move-exception;
        goto L_0x001e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aps.SimpleDiskCache.m5704a(com.aps.h$c):java.util.Map");
    }

    /* renamed from: b */
    private String m5705b(String str) {
        return m5706c(str);
    }

    /* renamed from: c */
    private String m5706c(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes(Utf8Charset.NAME));
            return new BigInteger(1, instance.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError();
        } catch (UnsupportedEncodingException e2) {
            throw new AssertionError();
        }
    }
}
