package com.amap.api.services.core;

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
/* renamed from: com.amap.api.services.core.bj */
public final class C1114bj implements Closeable {
    /* renamed from: a */
    static final Pattern f3746a = Pattern.compile("[a-z0-9_-]{1,120}");
    /* renamed from: q */
    private static final OutputStream f3747q = new C1116bl();
    /* renamed from: b */
    final ThreadPoolExecutor f3748b = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
    /* renamed from: c */
    private final File f3749c;
    /* renamed from: d */
    private final File f3750d;
    /* renamed from: e */
    private final File f3751e;
    /* renamed from: f */
    private final File f3752f;
    /* renamed from: g */
    private final int f3753g;
    /* renamed from: h */
    private long f3754h;
    /* renamed from: i */
    private final int f3755i;
    /* renamed from: j */
    private long f3756j = 0;
    /* renamed from: k */
    private Writer f3757k;
    /* renamed from: l */
    private final LinkedHashMap<String, C1113c> f3758l = new LinkedHashMap(0, 0.75f, true);
    /* renamed from: m */
    private int f3759m;
    /* renamed from: n */
    private C1101bm f3760n;
    /* renamed from: o */
    private long f3761o = 0;
    /* renamed from: p */
    private final Callable<Void> f3762p = new C1115bk(this);

    /* compiled from: DiskLruCache */
    /* renamed from: com.amap.api.services.core.bj$a */
    public final class C1111a {
        /* renamed from: b */
        private final C1113c f3731b;
        /* renamed from: c */
        private final boolean[] f3732c;
        /* renamed from: d */
        private boolean f3733d;
        /* renamed from: e */
        private boolean f3734e;

        /* compiled from: DiskLruCache */
        /* renamed from: com.amap.api.services.core.bj$a$a */
        private class C1110a extends FilterOutputStream {
            /* synthetic */ C1110a(C1111a c1111a, OutputStream outputStream, C1115bk c1115bk) {
                this(outputStream);
            }

            private C1110a(OutputStream outputStream) {
                super(outputStream);
            }

            public void write(int i) {
                try {
                    this.out.write(i);
                } catch (IOException e) {
                    C1111a.this.f3733d = true;
                }
            }

            public void write(byte[] bArr, int i, int i2) {
                try {
                    this.out.write(bArr, i, i2);
                } catch (IOException e) {
                    C1111a.this.f3733d = true;
                }
            }

            public void close() {
                try {
                    this.out.close();
                } catch (IOException e) {
                    C1111a.this.f3733d = true;
                }
            }

            public void flush() {
                try {
                    this.out.flush();
                } catch (IOException e) {
                    C1111a.this.f3733d = true;
                }
            }
        }

        /* synthetic */ C1111a(C1114bj c1114bj, C1113c c1113c, C1115bk c1115bk) {
            this(c1113c);
        }

        private C1111a(C1113c c1113c) {
            this.f3731b = c1113c;
            this.f3732c = c1113c.f3743d ? null : new boolean[C1114bj.this.f3755i];
        }

        /* renamed from: a */
        public OutputStream mo12067a(int i) throws IOException {
            if (i < 0 || i >= C1114bj.this.f3755i) {
                throw new IllegalArgumentException("Expected index " + i + " to " + "be greater than 0 and less than the maximum value count " + "of " + C1114bj.this.f3755i);
            }
            OutputStream d;
            synchronized (C1114bj.this) {
                if (this.f3731b.f3744e != this) {
                    throw new IllegalStateException();
                }
                OutputStream fileOutputStream;
                if (!this.f3731b.f3743d) {
                    this.f3732c[i] = true;
                }
                File b = this.f3731b.mo12074b(i);
                try {
                    fileOutputStream = new FileOutputStream(b);
                } catch (FileNotFoundException e) {
                    C1114bj.this.f3749c.mkdirs();
                    try {
                        fileOutputStream = new FileOutputStream(b);
                    } catch (FileNotFoundException e2) {
                        d = C1114bj.f3747q;
                    }
                }
                d = new C1110a(this, fileOutputStream, null);
            }
            return d;
        }

        /* renamed from: a */
        public void mo12068a() throws IOException {
            if (this.f3733d) {
                C1114bj.this.m4912a(this, false);
                C1114bj.this.mo12081c(this.f3731b.f3741b);
            } else {
                C1114bj.this.m4912a(this, true);
            }
            this.f3734e = true;
        }

        /* renamed from: b */
        public void mo12069b() throws IOException {
            C1114bj.this.m4912a(this, false);
        }
    }

    /* compiled from: DiskLruCache */
    /* renamed from: com.amap.api.services.core.bj$b */
    public final class C1112b implements Closeable {
        /* renamed from: b */
        private final String f3736b;
        /* renamed from: c */
        private final long f3737c;
        /* renamed from: d */
        private final InputStream[] f3738d;
        /* renamed from: e */
        private final long[] f3739e;

        /* synthetic */ C1112b(C1114bj c1114bj, String str, long j, InputStream[] inputStreamArr, long[] jArr, C1115bk c1115bk) {
            this(str, j, inputStreamArr, jArr);
        }

        private C1112b(String str, long j, InputStream[] inputStreamArr, long[] jArr) {
            this.f3736b = str;
            this.f3737c = j;
            this.f3738d = inputStreamArr;
            this.f3739e = jArr;
        }

        /* renamed from: a */
        public InputStream mo12070a(int i) {
            return this.f3738d[i];
        }

        public void close() {
            for (Closeable a : this.f3738d) {
                C1119bo.m4941a(a);
            }
        }
    }

    /* compiled from: DiskLruCache */
    /* renamed from: com.amap.api.services.core.bj$c */
    private final class C1113c {
        /* renamed from: b */
        private final String f3741b;
        /* renamed from: c */
        private final long[] f3742c;
        /* renamed from: d */
        private boolean f3743d;
        /* renamed from: e */
        private C1111a f3744e;
        /* renamed from: f */
        private long f3745f;

        /* synthetic */ C1113c(C1114bj c1114bj, String str, C1115bk c1115bk) {
            this(str);
        }

        private C1113c(String str) {
            this.f3741b = str;
            this.f3742c = new long[C1114bj.this.f3755i];
        }

        /* renamed from: a */
        public String mo12073a() throws IOException {
            StringBuilder stringBuilder = new StringBuilder();
            for (long append : this.f3742c) {
                stringBuilder.append(SafeJsonPrimitive.NULL_CHAR).append(append);
            }
            return stringBuilder.toString();
        }

        /* renamed from: a */
        private void m4898a(String[] strArr) throws IOException {
            if (strArr.length != C1114bj.this.f3755i) {
                throw m4900b(strArr);
            }
            int i = 0;
            while (i < strArr.length) {
                try {
                    this.f3742c[i] = Long.parseLong(strArr[i]);
                    i++;
                } catch (NumberFormatException e) {
                    throw m4900b(strArr);
                }
            }
        }

        /* renamed from: b */
        private IOException m4900b(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        /* renamed from: a */
        public File mo12072a(int i) {
            return new File(C1114bj.this.f3749c, this.f3741b + "." + i);
        }

        /* renamed from: b */
        public File mo12074b(int i) {
            return new File(C1114bj.this.f3749c, this.f3741b + "." + i + ".tmp");
        }
    }

    /* renamed from: a */
    public void mo12076a(C1101bm c1101bm) {
        this.f3760n = c1101bm;
    }

    private C1114bj(File file, int i, int i2, long j) {
        this.f3749c = file;
        this.f3753g = i;
        this.f3750d = new File(file, "journal");
        this.f3751e = new File(file, "journal.tmp");
        this.f3752f = new File(file, "journal.bkp");
        this.f3755i = i2;
        this.f3754h = j;
    }

    /* renamed from: a */
    public static C1114bj m4910a(File file, int i, int i2, long j) throws IOException {
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
                    C1114bj.m4915a(file2, file3, false);
                }
            }
            C1114bj c1114bj = new C1114bj(file, i, i2, j);
            if (c1114bj.f3750d.exists()) {
                try {
                    c1114bj.m4922e();
                    c1114bj.m4925f();
                    c1114bj.f3757k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(c1114bj.f3750d, true), C1119bo.f3770a));
                    return c1114bj;
                } catch (IOException e) {
                    System.out.println("DiskLruCache " + file + " is corrupt: " + e.getMessage() + ", removing");
                    c1114bj.mo12080c();
                }
            }
            file.mkdirs();
            c1114bj = new C1114bj(file, i, i2, j);
            c1114bj.m4926g();
            return c1114bj;
        }
    }

    /* renamed from: e */
    private void m4922e() throws IOException {
        Closeable c1118bn = new C1118bn(new FileInputStream(this.f3750d), C1119bo.f3770a);
        int i;
        try {
            String a = c1118bn.mo12087a();
            String a2 = c1118bn.mo12087a();
            String a3 = c1118bn.mo12087a();
            String a4 = c1118bn.mo12087a();
            String a5 = c1118bn.mo12087a();
            if ("libcore.io.DiskLruCache".equals(a) && "1".equals(a2) && Integer.toString(this.f3753g).equals(a3) && Integer.toString(this.f3755i).equals(a4) && "".equals(a5)) {
                i = 0;
                while (true) {
                    m4920d(c1118bn.mo12087a());
                    i++;
                }
            } else {
                throw new IOException("unexpected journal header: [" + a + ", " + a2 + ", " + a4 + ", " + a5 + "]");
            }
        } catch (EOFException e) {
            this.f3759m = i - this.f3758l.size();
            C1119bo.m4941a(c1118bn);
        } catch (Throwable th) {
            C1119bo.m4941a(c1118bn);
        }
    }

    /* renamed from: d */
    private void m4920d(String str) throws IOException {
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
                this.f3758l.remove(substring2);
                return;
            }
            String substring3 = substring2;
        } else {
            substring3 = str.substring(i, indexOf2);
        }
        C1113c c1113c = (C1113c) this.f3758l.get(substring3);
        if (c1113c == null) {
            c1113c = new C1113c(this, substring3, null);
            this.f3758l.put(substring3, c1113c);
        }
        if (indexOf2 != -1 && indexOf == "CLEAN".length() && str.startsWith("CLEAN")) {
            String[] split = str.substring(indexOf2 + 1).split(" ");
            c1113c.f3743d = true;
            c1113c.f3744e = null;
            c1113c.m4898a(split);
        } else if (indexOf2 == -1 && indexOf == "DIRTY".length() && str.startsWith("DIRTY")) {
            c1113c.f3744e = new C1111a(this, c1113c, null);
        } else if (indexOf2 != -1 || indexOf != "READ".length() || !str.startsWith("READ")) {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    /* renamed from: f */
    private void m4925f() throws IOException {
        C1114bj.m4914a(this.f3751e);
        Iterator it = this.f3758l.values().iterator();
        while (it.hasNext()) {
            C1113c c1113c = (C1113c) it.next();
            int i;
            if (c1113c.f3744e == null) {
                for (i = 0; i < this.f3755i; i++) {
                    this.f3756j += c1113c.f3742c[i];
                }
            } else {
                c1113c.f3744e = null;
                for (i = 0; i < this.f3755i; i++) {
                    C1114bj.m4914a(c1113c.mo12072a(i));
                    C1114bj.m4914a(c1113c.mo12074b(i));
                }
                it.remove();
            }
        }
    }

    /* renamed from: g */
    private synchronized void m4926g() throws IOException {
        if (this.f3757k != null) {
            this.f3757k.close();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f3751e), C1119bo.f3770a));
        try {
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write("\n");
            bufferedWriter.write("1");
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f3753g));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f3755i));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (C1113c c1113c : this.f3758l.values()) {
                if (c1113c.f3744e != null) {
                    bufferedWriter.write("DIRTY " + c1113c.f3741b + 10);
                } else {
                    bufferedWriter.write("CLEAN " + c1113c.f3741b + c1113c.mo12073a() + 10);
                }
            }
            bufferedWriter.close();
            if (this.f3750d.exists()) {
                C1114bj.m4915a(this.f3750d, this.f3752f, true);
            }
            C1114bj.m4915a(this.f3751e, this.f3750d, false);
            this.f3752f.delete();
            this.f3757k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f3750d, true), C1119bo.f3770a));
        } catch (Throwable th) {
            bufferedWriter.close();
        }
    }

    /* renamed from: a */
    private static void m4914a(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    /* renamed from: a */
    private static void m4915a(File file, File file2, boolean z) throws IOException {
        if (z) {
            C1114bj.m4914a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    /* renamed from: a */
    public synchronized C1112b mo12075a(String str) throws IOException {
        C1112b c1112b = null;
        synchronized (this) {
            m4928i();
            m4923e(str);
            C1113c c1113c = (C1113c) this.f3758l.get(str);
            if (c1113c != null) {
                if (c1113c.f3743d) {
                    InputStream[] inputStreamArr = new InputStream[this.f3755i];
                    int i = 0;
                    while (i < this.f3755i) {
                        try {
                            inputStreamArr[i] = new FileInputStream(c1113c.mo12072a(i));
                            i++;
                        } catch (FileNotFoundException e) {
                            int i2 = 0;
                            while (i2 < this.f3755i && inputStreamArr[i2] != null) {
                                C1119bo.m4941a(inputStreamArr[i2]);
                                i2++;
                            }
                        }
                    }
                    this.f3759m++;
                    this.f3757k.append("READ " + str + 10);
                    if (m4927h()) {
                        this.f3748b.submit(this.f3762p);
                    }
                    c1112b = new C1112b(this, str, c1113c.f3745f, inputStreamArr, c1113c.f3742c, null);
                }
            }
        }
        return c1112b;
    }

    /* renamed from: b */
    public C1111a mo12078b(String str) throws IOException {
        return m4909a(str, -1);
    }

    /* renamed from: a */
    private synchronized C1111a m4909a(String str, long j) throws IOException {
        C1111a c1111a;
        m4928i();
        m4923e(str);
        C1113c c1113c = (C1113c) this.f3758l.get(str);
        if (j == -1 || (c1113c != null && c1113c.f3745f == j)) {
            C1113c c1113c2;
            if (c1113c == null) {
                c1113c = new C1113c(this, str, null);
                this.f3758l.put(str, c1113c);
                c1113c2 = c1113c;
            } else if (c1113c.f3744e != null) {
                c1111a = null;
            } else {
                c1113c2 = c1113c;
            }
            c1111a = new C1111a(this, c1113c2, null);
            c1113c2.f3744e = c1111a;
            this.f3757k.write("DIRTY " + str + 10);
            this.f3757k.flush();
        } else {
            c1111a = null;
        }
        return c1111a;
    }

    /* renamed from: a */
    private synchronized void m4912a(C1111a c1111a, boolean z) throws IOException {
        int i = 0;
        synchronized (this) {
            C1113c a = c1111a.f3731b;
            if (a.f3744e != c1111a) {
                throw new IllegalStateException();
            }
            if (z) {
                if (!a.f3743d) {
                    int i2 = 0;
                    while (i2 < this.f3755i) {
                        if (!c1111a.f3732c[i2]) {
                            c1111a.mo12069b();
                            throw new IllegalStateException("Newly created entry didn't create value for index " + i2);
                        } else if (!a.mo12074b(i2).exists()) {
                            c1111a.mo12069b();
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
            }
            while (i < this.f3755i) {
                File b = a.mo12074b(i);
                if (!z) {
                    C1114bj.m4914a(b);
                } else if (b.exists()) {
                    File a2 = a.mo12072a(i);
                    b.renameTo(a2);
                    long j = a.f3742c[i];
                    long length = a2.length();
                    a.f3742c[i] = length;
                    this.f3756j = (this.f3756j - j) + length;
                }
                i++;
            }
            this.f3759m++;
            a.f3744e = null;
            if ((a.f3743d | z) != 0) {
                a.f3743d = true;
                this.f3757k.write("CLEAN " + a.f3741b + a.mo12073a() + 10);
                if (z) {
                    long j2 = this.f3761o;
                    this.f3761o = 1 + j2;
                    a.f3745f = j2;
                }
            } else {
                this.f3758l.remove(a.f3741b);
                this.f3757k.write("REMOVE " + a.f3741b + 10);
            }
            this.f3757k.flush();
            if (this.f3756j > this.f3754h || m4927h()) {
                this.f3748b.submit(this.f3762p);
            }
        }
    }

    /* renamed from: h */
    private boolean m4927h() {
        return this.f3759m >= ActivityTrace.MAX_TRACES && this.f3759m >= this.f3758l.size();
    }

    /* renamed from: c */
    public synchronized boolean mo12081c(String str) throws IOException {
        boolean z;
        int i = 0;
        synchronized (this) {
            m4928i();
            m4923e(str);
            C1113c c1113c = (C1113c) this.f3758l.get(str);
            if (c1113c == null || c1113c.f3744e != null) {
                z = false;
            } else {
                while (i < this.f3755i) {
                    File a = c1113c.mo12072a(i);
                    if (!a.exists() || a.delete()) {
                        this.f3756j -= c1113c.f3742c[i];
                        c1113c.f3742c[i] = 0;
                        i++;
                    } else {
                        throw new IOException("failed to delete " + a);
                    }
                }
                this.f3759m++;
                this.f3757k.append("REMOVE " + str + 10);
                this.f3758l.remove(str);
                if (m4927h()) {
                    this.f3748b.submit(this.f3762p);
                }
                z = true;
            }
        }
        return z;
    }

    /* renamed from: a */
    public synchronized boolean mo12077a() {
        return this.f3757k == null;
    }

    /* renamed from: i */
    private void m4928i() {
        if (this.f3757k == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    /* renamed from: b */
    public synchronized void mo12079b() throws IOException {
        m4928i();
        m4929j();
        this.f3757k.flush();
    }

    public synchronized void close() throws IOException {
        if (this.f3757k != null) {
            Iterator it = new ArrayList(this.f3758l.values()).iterator();
            while (it.hasNext()) {
                C1113c c1113c = (C1113c) it.next();
                if (c1113c.f3744e != null) {
                    c1113c.f3744e.mo12069b();
                }
            }
            m4929j();
            this.f3757k.close();
            this.f3757k = null;
        }
    }

    /* renamed from: j */
    private void m4929j() throws IOException {
        while (this.f3756j > this.f3754h) {
            String str = (String) ((Entry) this.f3758l.entrySet().iterator().next()).getKey();
            mo12081c(str);
            if (this.f3760n != null) {
                this.f3760n.mo12046a(str);
            }
        }
    }

    /* renamed from: c */
    public void mo12080c() throws IOException {
        close();
        C1119bo.m4942a(this.f3749c);
    }

    /* renamed from: e */
    private void m4923e(String str) {
        if (!f3746a.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
        }
    }
}
