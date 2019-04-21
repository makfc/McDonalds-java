package com.amap.api.mapcore2d;

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
/* renamed from: com.amap.api.mapcore2d.dw */
public final class C1017dw implements Closeable {
    /* renamed from: a */
    static final Pattern f2870a = Pattern.compile("[a-z0-9_-]{1,120}");
    /* renamed from: q */
    private static final OutputStream f2871q = new C10122();
    /* renamed from: b */
    final ThreadPoolExecutor f2872b = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
    /* renamed from: c */
    private final File f2873c;
    /* renamed from: d */
    private final File f2874d;
    /* renamed from: e */
    private final File f2875e;
    /* renamed from: f */
    private final File f2876f;
    /* renamed from: g */
    private final int f2877g;
    /* renamed from: h */
    private long f2878h;
    /* renamed from: i */
    private final int f2879i;
    /* renamed from: j */
    private long f2880j = 0;
    /* renamed from: k */
    private Writer f2881k;
    /* renamed from: l */
    private final LinkedHashMap<String, C1016c> f2882l = new LinkedHashMap(0, 0.75f, true);
    /* renamed from: m */
    private int f2883m;
    /* renamed from: n */
    private C0996dx f2884n;
    /* renamed from: o */
    private long f2885o = 0;
    /* renamed from: p */
    private final Callable<Void> f2886p = new C10111();

    /* compiled from: DiskLruCache */
    /* renamed from: com.amap.api.mapcore2d.dw$1 */
    class C10111 implements Callable<Void> {
        C10111() {
        }

        /* renamed from: a */
        public Void call() throws Exception {
            synchronized (C1017dw.this) {
                if (C1017dw.this.f2881k == null) {
                } else {
                    C1017dw.this.m4247j();
                    if (C1017dw.this.m4245h()) {
                        C1017dw.this.m4244g();
                        C1017dw.this.f2883m = 0;
                    }
                }
            }
            return null;
        }
    }

    /* compiled from: DiskLruCache */
    /* renamed from: com.amap.api.mapcore2d.dw$2 */
    static class C10122 extends OutputStream {
        C10122() {
        }

        public void write(int i) throws IOException {
        }
    }

    /* compiled from: DiskLruCache */
    /* renamed from: com.amap.api.mapcore2d.dw$a */
    public final class C1014a {
        /* renamed from: b */
        private final C1016c f2855b;
        /* renamed from: c */
        private final boolean[] f2856c;
        /* renamed from: d */
        private boolean f2857d;
        /* renamed from: e */
        private boolean f2858e;

        /* compiled from: DiskLruCache */
        /* renamed from: com.amap.api.mapcore2d.dw$a$a */
        private class C1013a extends FilterOutputStream {
            /* synthetic */ C1013a(C1014a c1014a, OutputStream outputStream, C10111 c10111) {
                this(outputStream);
            }

            private C1013a(OutputStream outputStream) {
                super(outputStream);
            }

            public void write(int i) {
                try {
                    this.out.write(i);
                } catch (IOException e) {
                    C1014a.this.f2857d = true;
                }
            }

            public void write(byte[] bArr, int i, int i2) {
                try {
                    this.out.write(bArr, i, i2);
                } catch (IOException e) {
                    C1014a.this.f2857d = true;
                }
            }

            public void close() {
                try {
                    this.out.close();
                } catch (IOException e) {
                    C1014a.this.f2857d = true;
                }
            }

            public void flush() {
                try {
                    this.out.flush();
                } catch (IOException e) {
                    C1014a.this.f2857d = true;
                }
            }
        }

        /* synthetic */ C1014a(C1017dw c1017dw, C1016c c1016c, C10111 c10111) {
            this(c1016c);
        }

        private C1014a(C1016c c1016c) {
            this.f2855b = c1016c;
            this.f2856c = c1016c.f2867d ? null : new boolean[C1017dw.this.f2879i];
        }

        /* renamed from: a */
        public OutputStream mo10249a(int i) throws IOException {
            if (i < 0 || i >= C1017dw.this.f2879i) {
                throw new IllegalArgumentException("Expected index " + i + " to " + "be greater than 0 and less than the maximum value count " + "of " + C1017dw.this.f2879i);
            }
            OutputStream d;
            synchronized (C1017dw.this) {
                if (this.f2855b.f2868e != this) {
                    throw new IllegalStateException();
                }
                OutputStream fileOutputStream;
                if (!this.f2855b.f2867d) {
                    this.f2856c[i] = true;
                }
                File b = this.f2855b.mo10256b(i);
                try {
                    fileOutputStream = new FileOutputStream(b);
                } catch (FileNotFoundException e) {
                    C1017dw.this.f2873c.mkdirs();
                    try {
                        fileOutputStream = new FileOutputStream(b);
                    } catch (FileNotFoundException e2) {
                        d = C1017dw.f2871q;
                    }
                }
                d = new C1013a(this, fileOutputStream, null);
            }
            return d;
        }

        /* renamed from: a */
        public void mo10250a() throws IOException {
            if (this.f2857d) {
                C1017dw.this.m4230a(this, false);
                C1017dw.this.mo10263c(this.f2855b.f2865b);
            } else {
                C1017dw.this.m4230a(this, true);
            }
            this.f2858e = true;
        }

        /* renamed from: b */
        public void mo10251b() throws IOException {
            C1017dw.this.m4230a(this, false);
        }
    }

    /* compiled from: DiskLruCache */
    /* renamed from: com.amap.api.mapcore2d.dw$b */
    public final class C1015b implements Closeable {
        /* renamed from: b */
        private final String f2860b;
        /* renamed from: c */
        private final long f2861c;
        /* renamed from: d */
        private final InputStream[] f2862d;
        /* renamed from: e */
        private final long[] f2863e;

        /* synthetic */ C1015b(C1017dw c1017dw, String str, long j, InputStream[] inputStreamArr, long[] jArr, C10111 c10111) {
            this(str, j, inputStreamArr, jArr);
        }

        private C1015b(String str, long j, InputStream[] inputStreamArr, long[] jArr) {
            this.f2860b = str;
            this.f2861c = j;
            this.f2862d = inputStreamArr;
            this.f2863e = jArr;
        }

        /* renamed from: a */
        public InputStream mo10252a(int i) {
            return this.f2862d[i];
        }

        public void close() {
            for (Closeable a : this.f2862d) {
                C1020dz.m4258a(a);
            }
        }
    }

    /* compiled from: DiskLruCache */
    /* renamed from: com.amap.api.mapcore2d.dw$c */
    private final class C1016c {
        /* renamed from: b */
        private final String f2865b;
        /* renamed from: c */
        private final long[] f2866c;
        /* renamed from: d */
        private boolean f2867d;
        /* renamed from: e */
        private C1014a f2868e;
        /* renamed from: f */
        private long f2869f;

        /* synthetic */ C1016c(C1017dw c1017dw, String str, C10111 c10111) {
            this(str);
        }

        private C1016c(String str) {
            this.f2865b = str;
            this.f2866c = new long[C1017dw.this.f2879i];
        }

        /* renamed from: a */
        public String mo10255a() throws IOException {
            StringBuilder stringBuilder = new StringBuilder();
            for (long append : this.f2866c) {
                stringBuilder.append(SafeJsonPrimitive.NULL_CHAR).append(append);
            }
            return stringBuilder.toString();
        }

        /* renamed from: a */
        private void m4216a(String[] strArr) throws IOException {
            if (strArr.length != C1017dw.this.f2879i) {
                throw m4218b(strArr);
            }
            int i = 0;
            while (i < strArr.length) {
                try {
                    this.f2866c[i] = Long.parseLong(strArr[i]);
                    i++;
                } catch (NumberFormatException e) {
                    throw m4218b(strArr);
                }
            }
        }

        /* renamed from: b */
        private IOException m4218b(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        /* renamed from: a */
        public File mo10254a(int i) {
            return new File(C1017dw.this.f2873c, this.f2865b + "." + i);
        }

        /* renamed from: b */
        public File mo10256b(int i) {
            return new File(C1017dw.this.f2873c, this.f2865b + "." + i + ".tmp");
        }
    }

    /* renamed from: a */
    public void mo10258a(C0996dx c0996dx) {
        this.f2884n = c0996dx;
    }

    private C1017dw(File file, int i, int i2, long j) {
        this.f2873c = file;
        this.f2877g = i;
        this.f2874d = new File(file, "journal");
        this.f2875e = new File(file, "journal.tmp");
        this.f2876f = new File(file, "journal.bkp");
        this.f2879i = i2;
        this.f2878h = j;
    }

    /* renamed from: a */
    public static C1017dw m4228a(File file, int i, int i2, long j) throws IOException {
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
                    C1017dw.m4233a(file2, file3, false);
                }
            }
            C1017dw c1017dw = new C1017dw(file, i, i2, j);
            if (c1017dw.f2874d.exists()) {
                try {
                    c1017dw.m4240e();
                    c1017dw.m4243f();
                    c1017dw.f2881k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(c1017dw.f2874d, true), C1020dz.f2893a));
                    return c1017dw;
                } catch (IOException e) {
                    c1017dw.mo10262c();
                }
            }
            file.mkdirs();
            c1017dw = new C1017dw(file, i, i2, j);
            c1017dw.m4244g();
            return c1017dw;
        }
    }

    /* renamed from: e */
    private void m4240e() throws IOException {
        Closeable c1019dy = new C1019dy(new FileInputStream(this.f2874d), C1020dz.f2893a);
        int i;
        try {
            String a = c1019dy.mo10266a();
            String a2 = c1019dy.mo10266a();
            String a3 = c1019dy.mo10266a();
            String a4 = c1019dy.mo10266a();
            String a5 = c1019dy.mo10266a();
            if ("libcore.io.DiskLruCache".equals(a) && "1".equals(a2) && Integer.toString(this.f2877g).equals(a3) && Integer.toString(this.f2879i).equals(a4) && "".equals(a5)) {
                i = 0;
                while (true) {
                    m4238d(c1019dy.mo10266a());
                    i++;
                }
            } else {
                throw new IOException("unexpected journal header: [" + a + ", " + a2 + ", " + a4 + ", " + a5 + "]");
            }
        } catch (EOFException e) {
            this.f2883m = i - this.f2882l.size();
            C1020dz.m4258a(c1019dy);
        } catch (Throwable th) {
            C1020dz.m4258a(c1019dy);
        }
    }

    /* renamed from: d */
    private void m4238d(String str) throws IOException {
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
                this.f2882l.remove(substring2);
                return;
            }
            String substring3 = substring2;
        } else {
            substring3 = str.substring(i, indexOf2);
        }
        C1016c c1016c = (C1016c) this.f2882l.get(substring3);
        if (c1016c == null) {
            c1016c = new C1016c(this, substring3, null);
            this.f2882l.put(substring3, c1016c);
        }
        if (indexOf2 != -1 && indexOf == "CLEAN".length() && str.startsWith("CLEAN")) {
            String[] split = str.substring(indexOf2 + 1).split(" ");
            c1016c.f2867d = true;
            c1016c.f2868e = null;
            c1016c.m4216a(split);
        } else if (indexOf2 == -1 && indexOf == "DIRTY".length() && str.startsWith("DIRTY")) {
            c1016c.f2868e = new C1014a(this, c1016c, null);
        } else if (indexOf2 != -1 || indexOf != "READ".length() || !str.startsWith("READ")) {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    /* renamed from: f */
    private void m4243f() throws IOException {
        C1017dw.m4232a(this.f2875e);
        Iterator it = this.f2882l.values().iterator();
        while (it.hasNext()) {
            C1016c c1016c = (C1016c) it.next();
            int i;
            if (c1016c.f2868e == null) {
                for (i = 0; i < this.f2879i; i++) {
                    this.f2880j += c1016c.f2866c[i];
                }
            } else {
                c1016c.f2868e = null;
                for (i = 0; i < this.f2879i; i++) {
                    C1017dw.m4232a(c1016c.mo10254a(i));
                    C1017dw.m4232a(c1016c.mo10256b(i));
                }
                it.remove();
            }
        }
    }

    /* renamed from: g */
    private synchronized void m4244g() throws IOException {
        if (this.f2881k != null) {
            this.f2881k.close();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f2875e), C1020dz.f2893a));
        try {
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write("\n");
            bufferedWriter.write("1");
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f2877g));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f2879i));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (C1016c c1016c : this.f2882l.values()) {
                if (c1016c.f2868e != null) {
                    bufferedWriter.write("DIRTY " + c1016c.f2865b + 10);
                } else {
                    bufferedWriter.write("CLEAN " + c1016c.f2865b + c1016c.mo10255a() + 10);
                }
            }
            bufferedWriter.close();
            if (this.f2874d.exists()) {
                C1017dw.m4233a(this.f2874d, this.f2876f, true);
            }
            C1017dw.m4233a(this.f2875e, this.f2874d, false);
            this.f2876f.delete();
            this.f2881k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f2874d, true), C1020dz.f2893a));
        } catch (Throwable th) {
            bufferedWriter.close();
        }
    }

    /* renamed from: a */
    private static void m4232a(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    /* renamed from: a */
    private static void m4233a(File file, File file2, boolean z) throws IOException {
        if (z) {
            C1017dw.m4232a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    /* renamed from: a */
    public synchronized C1015b mo10257a(String str) throws IOException {
        C1015b c1015b = null;
        synchronized (this) {
            m4246i();
            m4241e(str);
            C1016c c1016c = (C1016c) this.f2882l.get(str);
            if (c1016c != null) {
                if (c1016c.f2867d) {
                    InputStream[] inputStreamArr = new InputStream[this.f2879i];
                    int i = 0;
                    while (i < this.f2879i) {
                        try {
                            inputStreamArr[i] = new FileInputStream(c1016c.mo10254a(i));
                            i++;
                        } catch (FileNotFoundException e) {
                            int i2 = 0;
                            while (i2 < this.f2879i && inputStreamArr[i2] != null) {
                                C1020dz.m4258a(inputStreamArr[i2]);
                                i2++;
                            }
                        }
                    }
                    this.f2883m++;
                    this.f2881k.append("READ " + str + 10);
                    if (m4245h()) {
                        this.f2872b.submit(this.f2886p);
                    }
                    c1015b = new C1015b(this, str, c1016c.f2869f, inputStreamArr, c1016c.f2866c, null);
                }
            }
        }
        return c1015b;
    }

    /* renamed from: b */
    public C1014a mo10260b(String str) throws IOException {
        return m4227a(str, -1);
    }

    /* renamed from: a */
    private synchronized C1014a m4227a(String str, long j) throws IOException {
        C1014a c1014a;
        m4246i();
        m4241e(str);
        C1016c c1016c = (C1016c) this.f2882l.get(str);
        if (j == -1 || (c1016c != null && c1016c.f2869f == j)) {
            C1016c c1016c2;
            if (c1016c == null) {
                c1016c = new C1016c(this, str, null);
                this.f2882l.put(str, c1016c);
                c1016c2 = c1016c;
            } else if (c1016c.f2868e != null) {
                c1014a = null;
            } else {
                c1016c2 = c1016c;
            }
            c1014a = new C1014a(this, c1016c2, null);
            c1016c2.f2868e = c1014a;
            this.f2881k.write("DIRTY " + str + 10);
            this.f2881k.flush();
        } else {
            c1014a = null;
        }
        return c1014a;
    }

    /* renamed from: a */
    private synchronized void m4230a(C1014a c1014a, boolean z) throws IOException {
        int i = 0;
        synchronized (this) {
            C1016c a = c1014a.f2855b;
            if (a.f2868e != c1014a) {
                throw new IllegalStateException();
            }
            if (z) {
                if (!a.f2867d) {
                    int i2 = 0;
                    while (i2 < this.f2879i) {
                        if (!c1014a.f2856c[i2]) {
                            c1014a.mo10251b();
                            throw new IllegalStateException("Newly created entry didn't create value for index " + i2);
                        } else if (!a.mo10256b(i2).exists()) {
                            c1014a.mo10251b();
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
            }
            while (i < this.f2879i) {
                File b = a.mo10256b(i);
                if (!z) {
                    C1017dw.m4232a(b);
                } else if (b.exists()) {
                    File a2 = a.mo10254a(i);
                    b.renameTo(a2);
                    long j = a.f2866c[i];
                    long length = a2.length();
                    a.f2866c[i] = length;
                    this.f2880j = (this.f2880j - j) + length;
                }
                i++;
            }
            this.f2883m++;
            a.f2868e = null;
            if ((a.f2867d | z) != 0) {
                a.f2867d = true;
                this.f2881k.write("CLEAN " + a.f2865b + a.mo10255a() + 10);
                if (z) {
                    long j2 = this.f2885o;
                    this.f2885o = 1 + j2;
                    a.f2869f = j2;
                }
            } else {
                this.f2882l.remove(a.f2865b);
                this.f2881k.write("REMOVE " + a.f2865b + 10);
            }
            this.f2881k.flush();
            if (this.f2880j > this.f2878h || m4245h()) {
                this.f2872b.submit(this.f2886p);
            }
        }
    }

    /* renamed from: h */
    private boolean m4245h() {
        return this.f2883m >= ActivityTrace.MAX_TRACES && this.f2883m >= this.f2882l.size();
    }

    /* renamed from: c */
    public synchronized boolean mo10263c(String str) throws IOException {
        boolean z;
        int i = 0;
        synchronized (this) {
            m4246i();
            m4241e(str);
            C1016c c1016c = (C1016c) this.f2882l.get(str);
            if (c1016c == null || c1016c.f2868e != null) {
                z = false;
            } else {
                while (i < this.f2879i) {
                    File a = c1016c.mo10254a(i);
                    if (!a.exists() || a.delete()) {
                        this.f2880j -= c1016c.f2866c[i];
                        c1016c.f2866c[i] = 0;
                        i++;
                    } else {
                        throw new IOException("failed to delete " + a);
                    }
                }
                this.f2883m++;
                this.f2881k.append("REMOVE " + str + 10);
                this.f2882l.remove(str);
                if (m4245h()) {
                    this.f2872b.submit(this.f2886p);
                }
                z = true;
            }
        }
        return z;
    }

    /* renamed from: a */
    public synchronized boolean mo10259a() {
        return this.f2881k == null;
    }

    /* renamed from: i */
    private void m4246i() {
        if (this.f2881k == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    /* renamed from: b */
    public synchronized void mo10261b() throws IOException {
        m4246i();
        m4247j();
        this.f2881k.flush();
    }

    public synchronized void close() throws IOException {
        if (this.f2881k != null) {
            Iterator it = new ArrayList(this.f2882l.values()).iterator();
            while (it.hasNext()) {
                C1016c c1016c = (C1016c) it.next();
                if (c1016c.f2868e != null) {
                    c1016c.f2868e.mo10251b();
                }
            }
            m4247j();
            this.f2881k.close();
            this.f2881k = null;
        }
    }

    /* renamed from: j */
    private void m4247j() throws IOException {
        while (this.f2880j > this.f2878h) {
            String str = (String) ((Entry) this.f2882l.entrySet().iterator().next()).getKey();
            mo10263c(str);
            if (this.f2884n != null) {
                this.f2884n.mo10202a(str);
            }
        }
    }

    /* renamed from: c */
    public void mo10262c() throws IOException {
        close();
        C1020dz.m4259a(this.f2873c);
    }

    /* renamed from: e */
    private void m4241e(String str) {
        if (!f2870a.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
        }
    }
}
