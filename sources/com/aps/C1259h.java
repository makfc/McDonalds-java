package com.aps;

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

/* compiled from: DiskLruCache */
/* renamed from: com.aps.h */
public final class C1259h implements Closeable {
    /* renamed from: a */
    static final Pattern f4465a = Pattern.compile("[a-z0-9_-]{1,120}");
    /* renamed from: p */
    private static final OutputStream f4466p = new C1261j();
    /* renamed from: b */
    final ThreadPoolExecutor f4467b = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
    /* renamed from: c */
    private final File f4468c;
    /* renamed from: d */
    private final File f4469d;
    /* renamed from: e */
    private final File f4470e;
    /* renamed from: f */
    private final File f4471f;
    /* renamed from: g */
    private final int f4472g;
    /* renamed from: h */
    private long f4473h;
    /* renamed from: i */
    private final int f4474i;
    /* renamed from: j */
    private long f4475j = 0;
    /* renamed from: k */
    private Writer f4476k;
    /* renamed from: l */
    private final LinkedHashMap<String, C1257b> f4477l = new LinkedHashMap(0, 0.75f, true);
    /* renamed from: m */
    private int f4478m;
    /* renamed from: n */
    private long f4479n = 0;
    /* renamed from: o */
    private final Callable<Void> f4480o = new C1260i(this);

    /* compiled from: DiskLruCache */
    /* renamed from: com.aps.h$a */
    public final class C1256a {
        /* renamed from: b */
        private final C1257b f4450b;
        /* renamed from: c */
        private final boolean[] f4451c;
        /* renamed from: d */
        private boolean f4452d;
        /* renamed from: e */
        private boolean f4453e;

        /* compiled from: DiskLruCache */
        /* renamed from: com.aps.h$a$a */
        private class C1255a extends FilterOutputStream {
            /* synthetic */ C1255a(C1256a c1256a, OutputStream outputStream, C1260i c1260i) {
                this(outputStream);
            }

            private C1255a(OutputStream outputStream) {
                super(outputStream);
            }

            public void write(int i) {
                try {
                    this.out.write(i);
                } catch (IOException e) {
                    C1256a.this.f4452d = true;
                }
            }

            public void write(byte[] bArr, int i, int i2) {
                try {
                    this.out.write(bArr, i, i2);
                } catch (IOException e) {
                    C1256a.this.f4452d = true;
                }
            }

            public void close() {
                try {
                    this.out.close();
                } catch (IOException e) {
                    C1256a.this.f4452d = true;
                }
            }

            public void flush() {
                try {
                    this.out.flush();
                } catch (IOException e) {
                    C1256a.this.f4452d = true;
                }
            }
        }

        /* synthetic */ C1256a(C1259h c1259h, C1257b c1257b, C1260i c1260i) {
            this(c1257b);
        }

        private C1256a(C1257b c1257b) {
            this.f4450b = c1257b;
            this.f4451c = c1257b.f4457d ? null : new boolean[C1259h.this.f4474i];
        }

        /* renamed from: a */
        public OutputStream mo13258a(int i) throws IOException {
            if (i < 0 || i >= C1259h.this.f4474i) {
                throw new IllegalArgumentException("Expected index " + i + " to " + "be greater than 0 and less than the maximum value count " + "of " + C1259h.this.f4474i);
            }
            OutputStream b;
            synchronized (C1259h.this) {
                if (this.f4450b.f4458e != this) {
                    throw new IllegalStateException();
                }
                OutputStream fileOutputStream;
                if (!this.f4450b.f4457d) {
                    this.f4451c[i] = true;
                }
                File b2 = this.f4450b.mo13263b(i);
                try {
                    fileOutputStream = new FileOutputStream(b2);
                } catch (FileNotFoundException e) {
                    C1259h.this.f4468c.mkdirs();
                    try {
                        fileOutputStream = new FileOutputStream(b2);
                    } catch (FileNotFoundException e2) {
                        b = C1259h.f4466p;
                    }
                }
                b = new C1255a(this, fileOutputStream, null);
            }
            return b;
        }

        /* renamed from: a */
        public void mo13259a() throws IOException {
            if (this.f4452d) {
                C1259h.this.m5654a(this, false);
                C1259h.this.mo13269c(this.f4450b.f4455b);
            } else {
                C1259h.this.m5654a(this, true);
            }
            this.f4453e = true;
        }

        /* renamed from: b */
        public void mo13260b() throws IOException {
            C1259h.this.m5654a(this, false);
        }
    }

    /* compiled from: DiskLruCache */
    /* renamed from: com.aps.h$b */
    private final class C1257b {
        /* renamed from: b */
        private final String f4455b;
        /* renamed from: c */
        private final long[] f4456c;
        /* renamed from: d */
        private boolean f4457d;
        /* renamed from: e */
        private C1256a f4458e;
        /* renamed from: f */
        private long f4459f;

        /* synthetic */ C1257b(C1259h c1259h, String str, C1260i c1260i) {
            this(str);
        }

        private C1257b(String str) {
            this.f4455b = str;
            this.f4456c = new long[C1259h.this.f4474i];
        }

        /* renamed from: a */
        public String mo13262a() throws IOException {
            StringBuilder stringBuilder = new StringBuilder();
            for (long append : this.f4456c) {
                stringBuilder.append(SafeJsonPrimitive.NULL_CHAR).append(append);
            }
            return stringBuilder.toString();
        }

        /* renamed from: a */
        private void m5639a(String[] strArr) throws IOException {
            if (strArr.length != C1259h.this.f4474i) {
                throw m5641b(strArr);
            }
            int i = 0;
            while (i < strArr.length) {
                try {
                    this.f4456c[i] = Long.parseLong(strArr[i]);
                    i++;
                } catch (NumberFormatException e) {
                    throw m5641b(strArr);
                }
            }
        }

        /* renamed from: b */
        private IOException m5641b(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        /* renamed from: a */
        public File mo13261a(int i) {
            return new File(C1259h.this.f4468c, this.f4455b + "." + i);
        }

        /* renamed from: b */
        public File mo13263b(int i) {
            return new File(C1259h.this.f4468c, this.f4455b + "." + i + ".tmp");
        }
    }

    /* compiled from: DiskLruCache */
    /* renamed from: com.aps.h$c */
    public final class C1258c implements Closeable {
        /* renamed from: b */
        private final String f4461b;
        /* renamed from: c */
        private final long f4462c;
        /* renamed from: d */
        private final InputStream[] f4463d;
        /* renamed from: e */
        private final long[] f4464e;

        /* synthetic */ C1258c(C1259h c1259h, String str, long j, InputStream[] inputStreamArr, long[] jArr, C1260i c1260i) {
            this(str, j, inputStreamArr, jArr);
        }

        private C1258c(String str, long j, InputStream[] inputStreamArr, long[] jArr) {
            this.f4461b = str;
            this.f4462c = j;
            this.f4463d = inputStreamArr;
            this.f4464e = jArr;
        }

        /* renamed from: a */
        public InputStream mo13264a(int i) {
            return this.f4463d[i];
        }

        public void close() {
            for (Closeable a : this.f4463d) {
                C1268u.m5720a(a);
            }
        }
    }

    private C1259h(File file, int i, int i2, long j) {
        this.f4468c = file;
        this.f4472g = i;
        this.f4469d = new File(file, "journal");
        this.f4470e = new File(file, "journal.tmp");
        this.f4471f = new File(file, "journal.bkp");
        this.f4474i = i2;
        this.f4473h = j;
    }

    /* renamed from: a */
    public static C1259h m5652a(File file, int i, int i2, long j) throws IOException {
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
                    C1259h.m5657a(file2, file3, false);
                }
            }
            C1259h c1259h = new C1259h(file, i, i2, j);
            if (c1259h.f4469d.exists()) {
                try {
                    c1259h.m5660c();
                    c1259h.m5662d();
                    c1259h.f4476k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(c1259h.f4469d, true), C1268u.f4537a));
                    return c1259h;
                } catch (IOException e) {
                    System.out.println("DiskLruCache " + file + " is corrupt: " + e.getMessage() + ", removing");
                    c1259h.mo13267a();
                }
            }
            file.mkdirs();
            c1259h = new C1259h(file, i, i2, j);
            c1259h.m5666e();
            return c1259h;
        }
    }

    /* renamed from: c */
    private void m5660c() throws IOException {
        Closeable c1267t = new C1267t(new FileInputStream(this.f4469d), C1268u.f4537a);
        int i;
        try {
            String a = c1267t.mo13297a();
            String a2 = c1267t.mo13297a();
            String a3 = c1267t.mo13297a();
            String a4 = c1267t.mo13297a();
            String a5 = c1267t.mo13297a();
            if ("libcore.io.DiskLruCache".equals(a) && "1".equals(a2) && Integer.toString(this.f4472g).equals(a3) && Integer.toString(this.f4474i).equals(a4) && "".equals(a5)) {
                i = 0;
                while (true) {
                    m5664d(c1267t.mo13297a());
                    i++;
                }
            } else {
                throw new IOException("unexpected journal header: [" + a + ", " + a2 + ", " + a4 + ", " + a5 + "]");
            }
        } catch (EOFException e) {
            this.f4478m = i - this.f4477l.size();
            C1268u.m5720a(c1267t);
        } catch (Throwable th) {
            C1268u.m5720a(c1267t);
        }
    }

    /* renamed from: d */
    private void m5664d(String str) throws IOException {
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
                this.f4477l.remove(substring2);
                return;
            }
            String substring3 = substring2;
        } else {
            substring3 = str.substring(i, indexOf2);
        }
        C1257b c1257b = (C1257b) this.f4477l.get(substring3);
        if (c1257b == null) {
            c1257b = new C1257b(this, substring3, null);
            this.f4477l.put(substring3, c1257b);
        }
        if (indexOf2 != -1 && indexOf == "CLEAN".length() && str.startsWith("CLEAN")) {
            String[] split = str.substring(indexOf2 + 1).split(" ");
            c1257b.f4457d = true;
            c1257b.f4458e = null;
            c1257b.m5639a(split);
        } else if (indexOf2 == -1 && indexOf == "DIRTY".length() && str.startsWith("DIRTY")) {
            c1257b.f4458e = new C1256a(this, c1257b, null);
        } else if (indexOf2 != -1 || indexOf != "READ".length() || !str.startsWith("READ")) {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    /* renamed from: d */
    private void m5662d() throws IOException {
        C1259h.m5656a(this.f4470e);
        Iterator it = this.f4477l.values().iterator();
        while (it.hasNext()) {
            C1257b c1257b = (C1257b) it.next();
            int i;
            if (c1257b.f4458e == null) {
                for (i = 0; i < this.f4474i; i++) {
                    this.f4475j += c1257b.f4456c[i];
                }
            } else {
                c1257b.f4458e = null;
                for (i = 0; i < this.f4474i; i++) {
                    C1259h.m5656a(c1257b.mo13261a(i));
                    C1259h.m5656a(c1257b.mo13263b(i));
                }
                it.remove();
            }
        }
    }

    /* renamed from: e */
    private synchronized void m5666e() throws IOException {
        if (this.f4476k != null) {
            this.f4476k.close();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f4470e), C1268u.f4537a));
        try {
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write("\n");
            bufferedWriter.write("1");
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f4472g));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f4474i));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (C1257b c1257b : this.f4477l.values()) {
                if (c1257b.f4458e != null) {
                    bufferedWriter.write("DIRTY " + c1257b.f4455b + 10);
                } else {
                    bufferedWriter.write("CLEAN " + c1257b.f4455b + c1257b.mo13262a() + 10);
                }
            }
            bufferedWriter.close();
            if (this.f4469d.exists()) {
                C1259h.m5657a(this.f4469d, this.f4471f, true);
            }
            C1259h.m5657a(this.f4470e, this.f4469d, false);
            this.f4471f.delete();
            this.f4476k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f4469d, true), C1268u.f4537a));
        } catch (Throwable th) {
            bufferedWriter.close();
        }
    }

    /* renamed from: a */
    private static void m5656a(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    /* renamed from: a */
    private static void m5657a(File file, File file2, boolean z) throws IOException {
        if (z) {
            C1259h.m5656a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    /* renamed from: a */
    public synchronized C1258c mo13266a(String str) throws IOException {
        C1258c c1258c = null;
        synchronized (this) {
            if (this.f4476k != null) {
                m5670g();
                m5667e(str);
                C1257b c1257b = (C1257b) this.f4477l.get(str);
                if (c1257b != null && c1257b.f4457d) {
                    InputStream[] inputStreamArr = new InputStream[this.f4474i];
                    int i = 0;
                    while (i < this.f4474i) {
                        try {
                            inputStreamArr[i] = new FileInputStream(c1257b.mo13261a(i));
                            i++;
                        } catch (FileNotFoundException e) {
                            int i2 = 0;
                            while (i2 < this.f4474i && inputStreamArr[i2] != null) {
                                C1268u.m5720a(inputStreamArr[i2]);
                                i2++;
                            }
                        }
                    }
                    this.f4478m++;
                    this.f4476k.append("READ " + str + 10);
                    if (m5669f()) {
                        this.f4467b.submit(this.f4480o);
                    }
                    c1258c = new C1258c(this, str, c1257b.f4459f, inputStreamArr, c1257b.f4456c, null);
                }
            }
        }
        return c1258c;
    }

    /* renamed from: b */
    public C1256a mo13268b(String str) throws IOException {
        return m5651a(str, -1);
    }

    /* renamed from: a */
    private synchronized C1256a m5651a(String str, long j) throws IOException {
        C1256a c1256a;
        if (this.f4476k == null) {
            c1256a = null;
        } else {
            m5670g();
            m5667e(str);
            C1257b c1257b = (C1257b) this.f4477l.get(str);
            if (j == -1 || (c1257b != null && c1257b.f4459f == j)) {
                C1257b c1257b2;
                if (c1257b == null) {
                    c1257b = new C1257b(this, str, null);
                    this.f4477l.put(str, c1257b);
                    c1257b2 = c1257b;
                } else if (c1257b.f4458e != null) {
                    c1256a = null;
                } else {
                    c1257b2 = c1257b;
                }
                c1256a = new C1256a(this, c1257b2, null);
                c1257b2.f4458e = c1256a;
                this.f4476k.write("DIRTY " + str + 10);
                this.f4476k.flush();
            } else {
                c1256a = null;
            }
        }
        return c1256a;
    }

    /* renamed from: a */
    private synchronized void m5654a(C1256a c1256a, boolean z) throws IOException {
        int i = 0;
        synchronized (this) {
            C1257b a = c1256a.f4450b;
            if (a.f4458e != c1256a) {
                throw new IllegalStateException();
            }
            if (z) {
                if (!a.f4457d) {
                    int i2 = 0;
                    while (i2 < this.f4474i) {
                        if (!c1256a.f4451c[i2]) {
                            c1256a.mo13260b();
                            throw new IllegalStateException("Newly created entry didn't create value for index " + i2);
                        } else if (!a.mo13263b(i2).exists()) {
                            c1256a.mo13260b();
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
            }
            while (i < this.f4474i) {
                File b = a.mo13263b(i);
                if (!z) {
                    C1259h.m5656a(b);
                } else if (b.exists()) {
                    File a2 = a.mo13261a(i);
                    b.renameTo(a2);
                    long j = a.f4456c[i];
                    long length = a2.length();
                    a.f4456c[i] = length;
                    this.f4475j = (this.f4475j - j) + length;
                }
                i++;
            }
            this.f4478m++;
            a.f4458e = null;
            if ((a.f4457d | z) != 0) {
                a.f4457d = true;
                this.f4476k.write("CLEAN " + a.f4455b + a.mo13262a() + 10);
                if (z) {
                    long j2 = this.f4479n;
                    this.f4479n = 1 + j2;
                    a.f4459f = j2;
                }
            } else {
                this.f4477l.remove(a.f4455b);
                this.f4476k.write("REMOVE " + a.f4455b + 10);
            }
            this.f4476k.flush();
            if (this.f4475j > this.f4473h || m5669f()) {
                this.f4467b.submit(this.f4480o);
            }
        }
    }

    /* renamed from: f */
    private boolean m5669f() {
        return this.f4478m >= ActivityTrace.MAX_TRACES && this.f4478m >= this.f4477l.size();
    }

    /* renamed from: c */
    public synchronized boolean mo13269c(String str) throws IOException {
        boolean z;
        int i = 0;
        synchronized (this) {
            m5670g();
            m5667e(str);
            C1257b c1257b = (C1257b) this.f4477l.get(str);
            if (c1257b == null || c1257b.f4458e != null) {
                z = false;
            } else {
                while (i < this.f4474i) {
                    File a = c1257b.mo13261a(i);
                    if (!a.exists() || a.delete()) {
                        this.f4475j -= c1257b.f4456c[i];
                        c1257b.f4456c[i] = 0;
                        i++;
                    } else {
                        throw new IOException("failed to delete " + a);
                    }
                }
                this.f4478m++;
                this.f4476k.append("REMOVE " + str + 10);
                this.f4477l.remove(str);
                if (m5669f()) {
                    this.f4467b.submit(this.f4480o);
                }
                z = true;
            }
        }
        return z;
    }

    /* renamed from: g */
    private void m5670g() {
        if (this.f4476k == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void close() throws IOException {
        if (this.f4476k != null) {
            Iterator it = new ArrayList(this.f4477l.values()).iterator();
            while (it.hasNext()) {
                C1257b c1257b = (C1257b) it.next();
                if (c1257b.f4458e != null) {
                    c1257b.f4458e.mo13260b();
                }
            }
            m5671h();
            this.f4476k.close();
            this.f4476k = null;
        }
    }

    /* renamed from: h */
    private void m5671h() throws IOException {
        while (this.f4475j > this.f4473h) {
            mo13269c((String) ((Entry) this.f4477l.entrySet().iterator().next()).getKey());
        }
    }

    /* renamed from: a */
    public void mo13267a() throws IOException {
        close();
        C1268u.m5721a(this.f4468c);
    }

    /* renamed from: e */
    private void m5667e(String str) {
        if (!f4465a.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
        }
    }
}
