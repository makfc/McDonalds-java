package com.amap.api.mapcore.util;

import com.newrelic.agent.android.tracing.ActivityTrace;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/* renamed from: com.amap.api.mapcore.util.fk */
public final class DiskLruCache implements Closeable {
    /* renamed from: a */
    static final Pattern f1964a = Pattern.compile("[a-z0-9_-]{1,120}");
    /* renamed from: q */
    private static final OutputStream f1965q = new C0844fm();
    /* renamed from: b */
    final ThreadPoolExecutor f1966b = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
    /* renamed from: c */
    private final File f1967c;
    /* renamed from: d */
    private final File f1968d;
    /* renamed from: e */
    private final File f1969e;
    /* renamed from: f */
    private final File f1970f;
    /* renamed from: g */
    private final int f1971g;
    /* renamed from: h */
    private long f1972h;
    /* renamed from: i */
    private final int f1973i;
    /* renamed from: j */
    private long f1974j = 0;
    /* renamed from: k */
    private Writer f1975k;
    /* renamed from: l */
    private final LinkedHashMap<String, C0842c> f1976l = new LinkedHashMap(0, 0.75f, true);
    /* renamed from: m */
    private int f1977m;
    /* renamed from: n */
    private FileOperationListener f1978n;
    /* renamed from: o */
    private long f1979o = 0;
    /* renamed from: p */
    private final Callable<Void> f1980p = new C0843fl(this);

    /* compiled from: DiskLruCache */
    /* renamed from: com.amap.api.mapcore.util.fk$a */
    public final class C0840a {
        /* renamed from: b */
        private final C0842c f1949b;
        /* renamed from: c */
        private final boolean[] f1950c;
        /* renamed from: d */
        private boolean f1951d;
        /* renamed from: e */
        private boolean f1952e;

        /* compiled from: DiskLruCache */
        /* renamed from: com.amap.api.mapcore.util.fk$a$a */
        private class C0839a extends FilterOutputStream {
            /* synthetic */ C0839a(C0840a c0840a, OutputStream outputStream, C0843fl c0843fl) {
                this(outputStream);
            }

            private C0839a(OutputStream outputStream) {
                super(outputStream);
            }

            public void write(int i) {
                try {
                    this.out.write(i);
                } catch (IOException e) {
                    C0840a.this.f1951d = true;
                }
            }

            public void write(byte[] bArr, int i, int i2) {
                try {
                    this.out.write(bArr, i, i2);
                } catch (IOException e) {
                    C0840a.this.f1951d = true;
                }
            }

            public void close() {
                try {
                    this.out.close();
                } catch (IOException e) {
                    C0840a.this.f1951d = true;
                }
            }

            public void flush() {
                try {
                    this.out.flush();
                } catch (IOException e) {
                    C0840a.this.f1951d = true;
                }
            }
        }

        /* synthetic */ C0840a(DiskLruCache diskLruCache, C0842c c0842c, C0843fl c0843fl) {
            this(c0842c);
        }

        private C0840a(C0842c c0842c) {
            this.f1949b = c0842c;
            this.f1950c = c0842c.f1961d ? null : new boolean[DiskLruCache.this.f1973i];
        }

        /* renamed from: a */
        public OutputStream mo9391a(int i) throws IOException {
            if (i < 0 || i >= DiskLruCache.this.f1973i) {
                throw new IllegalArgumentException("Expected index " + i + " to " + "be greater than 0 and less than the maximum value count " + "of " + DiskLruCache.this.f1973i);
            }
            OutputStream d;
            synchronized (DiskLruCache.this) {
                if (this.f1949b.f1962e != this) {
                    throw new IllegalStateException();
                }
                OutputStream fileOutputStream;
                if (!this.f1949b.f1961d) {
                    this.f1950c[i] = true;
                }
                File b = this.f1949b.mo9398b(i);
                try {
                    fileOutputStream = new FileOutputStream(b);
                } catch (FileNotFoundException e) {
                    DiskLruCache.this.f1967c.mkdirs();
                    try {
                        fileOutputStream = new FileOutputStream(b);
                    } catch (FileNotFoundException e2) {
                        d = DiskLruCache.f1965q;
                    }
                }
                d = new C0839a(this, fileOutputStream, null);
            }
            return d;
        }

        /* renamed from: a */
        public void mo9392a() throws IOException {
            if (this.f1951d) {
                DiskLruCache.this.m2769a(this, false);
                DiskLruCache.this.mo9405c(this.f1949b.f1959b);
            } else {
                DiskLruCache.this.m2769a(this, true);
            }
            this.f1952e = true;
        }

        /* renamed from: b */
        public void mo9393b() throws IOException {
            DiskLruCache.this.m2769a(this, false);
        }
    }

    /* compiled from: DiskLruCache */
    /* renamed from: com.amap.api.mapcore.util.fk$b */
    public final class C0841b implements Closeable {
        /* renamed from: b */
        private final String f1954b;
        /* renamed from: c */
        private final long f1955c;
        /* renamed from: d */
        private final InputStream[] f1956d;
        /* renamed from: e */
        private final long[] f1957e;

        /* synthetic */ C0841b(DiskLruCache diskLruCache, String str, long j, InputStream[] inputStreamArr, long[] jArr, C0843fl c0843fl) {
            this(str, j, inputStreamArr, jArr);
        }

        private C0841b(String str, long j, InputStream[] inputStreamArr, long[] jArr) {
            this.f1954b = str;
            this.f1955c = j;
            this.f1956d = inputStreamArr;
            this.f1957e = jArr;
        }

        /* renamed from: a */
        public InputStream mo9394a(int i) {
            return this.f1956d[i];
        }

        public void close() {
            for (Closeable a : this.f1956d) {
                C0846fp.m2798a(a);
            }
        }
    }

    /* compiled from: DiskLruCache */
    /* renamed from: com.amap.api.mapcore.util.fk$c */
    private final class C0842c {
        /* renamed from: b */
        private final String f1959b;
        /* renamed from: c */
        private final long[] f1960c;
        /* renamed from: d */
        private boolean f1961d;
        /* renamed from: e */
        private C0840a f1962e;
        /* renamed from: f */
        private long f1963f;

        /* synthetic */ C0842c(DiskLruCache diskLruCache, String str, C0843fl c0843fl) {
            this(str);
        }

        private C0842c(String str) {
            this.f1959b = str;
            this.f1960c = new long[DiskLruCache.this.f1973i];
        }

        /* renamed from: a */
        public String mo9397a() throws IOException {
            StringBuilder stringBuilder = new StringBuilder();
            for (long append : this.f1960c) {
                stringBuilder.append(SafeJsonPrimitive.NULL_CHAR).append(append);
            }
            return stringBuilder.toString();
        }

        /* renamed from: a */
        private void m2755a(String[] strArr) throws IOException {
            if (strArr.length != DiskLruCache.this.f1973i) {
                throw m2757b(strArr);
            }
            int i = 0;
            while (i < strArr.length) {
                try {
                    this.f1960c[i] = Long.parseLong(strArr[i]);
                    i++;
                } catch (NumberFormatException e) {
                    throw m2757b(strArr);
                }
            }
        }

        /* renamed from: b */
        private IOException m2757b(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        /* renamed from: a */
        public File mo9396a(int i) {
            return new File(DiskLruCache.this.f1967c, this.f1959b + "." + i);
        }

        /* renamed from: b */
        public File mo9398b(int i) {
            return new File(DiskLruCache.this.f1967c, this.f1959b + "." + i + ".tmp");
        }
    }

    /* renamed from: a */
    public void mo9400a(FileOperationListener fileOperationListener) {
        this.f1978n = fileOperationListener;
    }

    private DiskLruCache(File file, int i, int i2, long j) {
        this.f1967c = file;
        this.f1971g = i;
        this.f1968d = new File(file, "journal");
        this.f1969e = new File(file, "journal.tmp");
        this.f1970f = new File(file, "journal.bkp");
        this.f1973i = i2;
        this.f1972h = j;
    }

    /* renamed from: a */
    public static DiskLruCache m2767a(File file, int i, int i2, long j) throws IOException {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i2 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        } else {
            File file2 = new File(file, "journal.bkp");
            if (file2.exists()) {
                File file3 = new File(file, "journal");
                if (file3.exists()) {
                    file2.delete();
                } else {
                    DiskLruCache.m2772a(file2, file3, false);
                }
            }
            DiskLruCache diskLruCache = new DiskLruCache(file, i, i2, j);
            if (diskLruCache.f1968d.exists()) {
                try {
                    diskLruCache.m2779e();
                    diskLruCache.m2782f();
                    diskLruCache.f1975k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(diskLruCache.f1968d, true), C0846fp.f1988a));
                    return diskLruCache;
                } catch (IOException e) {
                    diskLruCache.mo9404c();
                }
            }
            file.mkdirs();
            diskLruCache = new DiskLruCache(file, i, i2, j);
            diskLruCache.m2783g();
            return diskLruCache;
        }
    }

    /* renamed from: e */
    private void m2779e() throws IOException {
        Closeable strictLineReader = new StrictLineReader(new FileInputStream(this.f1968d), C0846fp.f1988a);
        int i;
        try {
            String a = strictLineReader.mo9411a();
            String a2 = strictLineReader.mo9411a();
            String a3 = strictLineReader.mo9411a();
            String a4 = strictLineReader.mo9411a();
            String a5 = strictLineReader.mo9411a();
            if ("libcore.io.DiskLruCache".equals(a) && "1".equals(a2) && Integer.toString(this.f1971g).equals(a3) && Integer.toString(this.f1973i).equals(a4) && "".equals(a5)) {
                i = 0;
                while (true) {
                    m2777d(strictLineReader.mo9411a());
                    i++;
                }
            } else {
                throw new IOException("unexpected journal header: [" + a + ", " + a2 + ", " + a4 + ", " + a5 + "]");
            }
        } catch (EOFException e) {
            this.f1977m = i - this.f1976l.size();
            C0846fp.m2798a(strictLineReader);
        } catch (Throwable th) {
            C0846fp.m2798a(strictLineReader);
        }
    }

    /* renamed from: d */
    private void m2777d(String str) throws IOException {
        int indexOf = str.indexOf(32);
        if (indexOf == -1) {
            throw new IOException("unexpected journal line: " + str);
        }
        Object substring;
        int i = indexOf + 1;
        int indexOf2 = str.indexOf(32, i);
        if (indexOf2 == -1) {
            String substring2 = str.substring(i);
            if (indexOf == "REMOVE".length() && str.startsWith("REMOVE")) {
                this.f1976l.remove(substring2);
                return;
            }
            String substring3 = substring2;
        } else {
            substring3 = str.substring(i, indexOf2);
        }
        C0842c c0842c = (C0842c) this.f1976l.get(substring3);
        if (c0842c == null) {
            c0842c = new C0842c(this, substring3, null);
            this.f1976l.put(substring3, c0842c);
        }
        if (indexOf2 != -1 && indexOf == "CLEAN".length() && str.startsWith("CLEAN")) {
            String[] split = str.substring(indexOf2 + 1).split(" ");
            c0842c.f1961d = true;
            c0842c.f1962e = null;
            c0842c.m2755a(split);
        } else if (indexOf2 == -1 && indexOf == "DIRTY".length() && str.startsWith("DIRTY")) {
            c0842c.f1962e = new C0840a(this, c0842c, null);
        } else if (indexOf2 != -1 || indexOf != "READ".length() || !str.startsWith("READ")) {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    /* renamed from: f */
    private void m2782f() throws IOException {
        DiskLruCache.m2771a(this.f1969e);
        Iterator it = this.f1976l.values().iterator();
        while (it.hasNext()) {
            C0842c c0842c = (C0842c) it.next();
            int i;
            if (c0842c.f1962e == null) {
                for (i = 0; i < this.f1973i; i++) {
                    this.f1974j += c0842c.f1960c[i];
                }
            } else {
                c0842c.f1962e = null;
                for (i = 0; i < this.f1973i; i++) {
                    DiskLruCache.m2771a(c0842c.mo9396a(i));
                    DiskLruCache.m2771a(c0842c.mo9398b(i));
                }
                it.remove();
            }
        }
    }

    /* renamed from: g */
    private synchronized void m2783g() throws IOException {
        if (this.f1975k != null) {
            this.f1975k.close();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f1969e), C0846fp.f1988a));
        try {
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write("\n");
            bufferedWriter.write("1");
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f1971g));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f1973i));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (C0842c c0842c : this.f1976l.values()) {
                if (c0842c.f1962e != null) {
                    bufferedWriter.write("DIRTY " + c0842c.f1959b + 10);
                } else {
                    bufferedWriter.write("CLEAN " + c0842c.f1959b + c0842c.mo9397a() + 10);
                }
            }
            bufferedWriter.close();
            if (this.f1968d.exists()) {
                DiskLruCache.m2772a(this.f1968d, this.f1970f, true);
            }
            DiskLruCache.m2772a(this.f1969e, this.f1968d, false);
            this.f1970f.delete();
            this.f1975k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f1968d, true), C0846fp.f1988a));
        } catch (Throwable th) {
            bufferedWriter.close();
        }
    }

    /* renamed from: a */
    private static void m2771a(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    /* renamed from: a */
    private static void m2772a(File file, File file2, boolean z) throws IOException {
        if (z) {
            DiskLruCache.m2771a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    /* renamed from: a */
    public synchronized C0841b mo9399a(String str) throws IOException {
        C0841b c0841b = null;
        synchronized (this) {
            m2785i();
            m2780e(str);
            C0842c c0842c = (C0842c) this.f1976l.get(str);
            if (c0842c != null) {
                if (c0842c.f1961d) {
                    InputStream[] inputStreamArr = new InputStream[this.f1973i];
                    int i = 0;
                    while (i < this.f1973i) {
                        try {
                            inputStreamArr[i] = new FileInputStream(c0842c.mo9396a(i));
                            i++;
                        } catch (FileNotFoundException e) {
                            int i2 = 0;
                            while (i2 < this.f1973i && inputStreamArr[i2] != null) {
                                C0846fp.m2798a(inputStreamArr[i2]);
                                i2++;
                            }
                        }
                    }
                    this.f1977m++;
                    this.f1975k.append("READ " + str + 10);
                    if (m2784h()) {
                        this.f1966b.submit(this.f1980p);
                    }
                    c0841b = new C0841b(this, str, c0842c.f1963f, inputStreamArr, c0842c.f1960c, null);
                }
            }
        }
        return c0841b;
    }

    /* renamed from: b */
    public C0840a mo9402b(String str) throws IOException {
        return m2766a(str, -1);
    }

    /* renamed from: a */
    private synchronized C0840a m2766a(String str, long j) throws IOException {
        C0840a c0840a;
        m2785i();
        m2780e(str);
        C0842c c0842c = (C0842c) this.f1976l.get(str);
        if (j == -1 || (c0842c != null && c0842c.f1963f == j)) {
            C0842c c0842c2;
            if (c0842c == null) {
                c0842c = new C0842c(this, str, null);
                this.f1976l.put(str, c0842c);
                c0842c2 = c0842c;
            } else if (c0842c.f1962e != null) {
                c0840a = null;
            } else {
                c0842c2 = c0842c;
            }
            c0840a = new C0840a(this, c0842c2, null);
            c0842c2.f1962e = c0840a;
            this.f1975k.write("DIRTY " + str + 10);
            this.f1975k.flush();
        } else {
            c0840a = null;
        }
        return c0840a;
    }

    /* renamed from: a */
    private synchronized void m2769a(C0840a c0840a, boolean z) throws IOException {
        int i = 0;
        synchronized (this) {
            C0842c a = c0840a.f1949b;
            if (a.f1962e != c0840a) {
                throw new IllegalStateException();
            }
            if (z) {
                if (!a.f1961d) {
                    int i2 = 0;
                    while (i2 < this.f1973i) {
                        if (!c0840a.f1950c[i2]) {
                            c0840a.mo9393b();
                            throw new IllegalStateException("Newly created entry didn't create value for index " + i2);
                        } else if (!a.mo9398b(i2).exists()) {
                            c0840a.mo9393b();
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
            }
            while (i < this.f1973i) {
                File b = a.mo9398b(i);
                if (!z) {
                    DiskLruCache.m2771a(b);
                } else if (b.exists()) {
                    File a2 = a.mo9396a(i);
                    b.renameTo(a2);
                    long j = a.f1960c[i];
                    long length = a2.length();
                    a.f1960c[i] = length;
                    this.f1974j = (this.f1974j - j) + length;
                }
                i++;
            }
            this.f1977m++;
            a.f1962e = null;
            if ((a.f1961d | z) != 0) {
                a.f1961d = true;
                this.f1975k.write("CLEAN " + a.f1959b + a.mo9397a() + 10);
                if (z) {
                    long j2 = this.f1979o;
                    this.f1979o = 1 + j2;
                    a.f1963f = j2;
                }
            } else {
                this.f1976l.remove(a.f1959b);
                this.f1975k.write("REMOVE " + a.f1959b + 10);
            }
            this.f1975k.flush();
            if (this.f1974j > this.f1972h || m2784h()) {
                this.f1966b.submit(this.f1980p);
            }
        }
    }

    /* renamed from: h */
    private boolean m2784h() {
        return this.f1977m >= ActivityTrace.MAX_TRACES && this.f1977m >= this.f1976l.size();
    }

    /* renamed from: c */
    public synchronized boolean mo9405c(String str) throws IOException {
        boolean z;
        int i = 0;
        synchronized (this) {
            m2785i();
            m2780e(str);
            C0842c c0842c = (C0842c) this.f1976l.get(str);
            if (c0842c == null || c0842c.f1962e != null) {
                z = false;
            } else {
                while (i < this.f1973i) {
                    File a = c0842c.mo9396a(i);
                    if (!a.exists() || a.delete()) {
                        this.f1974j -= c0842c.f1960c[i];
                        c0842c.f1960c[i] = 0;
                        i++;
                    } else {
                        throw new IOException("failed to delete " + a);
                    }
                }
                this.f1977m++;
                this.f1975k.append("REMOVE " + str + 10);
                this.f1976l.remove(str);
                if (m2784h()) {
                    this.f1966b.submit(this.f1980p);
                }
                z = true;
            }
        }
        return z;
    }

    /* renamed from: a */
    public synchronized boolean mo9401a() {
        return this.f1975k == null;
    }

    /* renamed from: i */
    private void m2785i() {
        if (this.f1975k == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    /* renamed from: b */
    public synchronized void mo9403b() throws IOException {
        m2785i();
        m2786j();
        this.f1975k.flush();
    }

    public synchronized void close() throws IOException {
        if (this.f1975k != null) {
            Iterator it = new ArrayList(this.f1976l.values()).iterator();
            while (it.hasNext()) {
                C0842c c0842c = (C0842c) it.next();
                if (c0842c.f1962e != null) {
                    c0842c.f1962e.mo9393b();
                }
            }
            m2786j();
            this.f1975k.close();
            this.f1975k = null;
        }
    }

    /* renamed from: j */
    private void m2786j() throws IOException {
        while (this.f1974j > this.f1972h) {
            String str = (String) ((Entry) this.f1976l.entrySet().iterator().next()).getKey();
            mo9405c(str);
            if (this.f1978n != null) {
                this.f1978n.mo9323a(str);
            }
        }
    }

    /* renamed from: c */
    public void mo9404c() throws IOException {
        close();
        C0846fp.m2799a(this.f1967c);
    }

    /* renamed from: e */
    private void m2780e(String str) {
        if (!f1964a.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
        }
    }
}
