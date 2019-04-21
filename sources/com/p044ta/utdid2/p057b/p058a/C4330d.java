package com.p044ta.utdid2.p057b.p058a;

import com.p044ta.utdid2.p057b.p058a.C4326b.C4324a;
import com.p044ta.utdid2.p057b.p058a.C4326b.C4325b;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;

/* renamed from: com.ta.utdid2.b.a.d */
public class C4330d {
    /* renamed from: b */
    private static final Object f6747b = new Object();
    /* renamed from: a */
    private File f6748a;
    /* renamed from: a */
    private final Object f6749a = new Object();
    /* renamed from: a */
    private HashMap<File, C4329a> f6750a = new HashMap();

    /* renamed from: com.ta.utdid2.b.a.d$a */
    private static final class C4329a implements C4326b {
        /* renamed from: c */
        private static final Object f6740c = new Object();
        /* renamed from: a */
        private Map f6741a;
        /* renamed from: a */
        private WeakHashMap<C4325b, Object> f6742a;
        /* renamed from: b */
        private final int f6743b;
        /* renamed from: b */
        private final File f6744b;
        /* renamed from: c */
        private final File f6745c;
        /* renamed from: g */
        private boolean f6746g = false;

        /* renamed from: com.ta.utdid2.b.a.d$a$a */
        public final class C4328a implements C4324a {
            /* renamed from: b */
            private final Map<String, Object> f6738b = new HashMap();
            /* renamed from: h */
            private boolean f6739h = false;

            /* renamed from: a */
            public C4324a mo33720a(String str, String str2) {
                synchronized (this) {
                    this.f6738b.put(str, str2);
                }
                return this;
            }

            /* renamed from: a */
            public C4324a mo33718a(String str, int i) {
                synchronized (this) {
                    this.f6738b.put(str, Integer.valueOf(i));
                }
                return this;
            }

            /* renamed from: a */
            public C4324a mo33719a(String str, long j) {
                synchronized (this) {
                    this.f6738b.put(str, Long.valueOf(j));
                }
                return this;
            }

            /* renamed from: a */
            public C4324a mo33717a(String str, float f) {
                synchronized (this) {
                    this.f6738b.put(str, Float.valueOf(f));
                }
                return this;
            }

            /* renamed from: a */
            public C4324a mo33721a(String str, boolean z) {
                synchronized (this) {
                    this.f6738b.put(str, Boolean.valueOf(z));
                }
                return this;
            }

            /* renamed from: a */
            public C4324a mo33716a(String str) {
                synchronized (this) {
                    this.f6738b.put(str, this);
                }
                return this;
            }

            /* renamed from: b */
            public C4324a mo33722b() {
                synchronized (this) {
                    this.f6739h = true;
                }
                return this;
            }

            public boolean commit() {
                List list;
                String str;
                boolean a;
                Object obj = null;
                synchronized (C4330d.m7800a()) {
                    if (C4329a.this.f6741a.size() > 0) {
                        obj = 1;
                    }
                    Set hashSet;
                    if (obj != null) {
                        ArrayList arrayList = new ArrayList();
                        hashSet = new HashSet(C4329a.this.f6741a.keySet());
                        list = arrayList;
                    } else {
                        hashSet = null;
                        list = null;
                    }
                    synchronized (this) {
                        if (this.f6739h) {
                            C4329a.this.f6741a.clear();
                            this.f6739h = false;
                        }
                        for (Entry entry : this.f6738b.entrySet()) {
                            str = (String) entry.getKey();
                            C4328a value = entry.getValue();
                            if (value == this) {
                                C4329a.this.f6741a.remove(str);
                            } else {
                                C4329a.this.f6741a.put(str, value);
                            }
                            if (obj != null) {
                                list.add(str);
                            }
                        }
                        this.f6738b.clear();
                    }
                    a = C4329a.this.f6741a;
                    if (a) {
                        C4329a.this.mo33734a(true);
                    }
                }
                if (obj != null) {
                    for (int size = list.size() - 1; size >= 0; size--) {
                        str = (String) list.get(size);
                        for (C4325b c4325b : hashSet) {
                            if (c4325b != null) {
                                c4325b.mo33724a(C4329a.this, str);
                            }
                        }
                    }
                }
                return a;
            }
        }

        C4329a(File file, int i, Map map) {
            this.f6744b = file;
            this.f6745c = C4330d.m7801a(file);
            this.f6743b = i;
            if (map == null) {
                map = new HashMap();
            }
            this.f6741a = map;
            this.f6742a = new WeakHashMap();
        }

        /* renamed from: a */
        public boolean m8665a() {
            if (this.f6744b == null || !new File(this.f6744b.getAbsolutePath()).exists()) {
                return false;
            }
            return true;
        }

        /* renamed from: a */
        public void mo33734a(boolean z) {
            synchronized (this) {
                this.f6746g = z;
            }
        }

        /* renamed from: c */
        public boolean mo33735c() {
            boolean z;
            synchronized (this) {
                z = this.f6746g;
            }
            return z;
        }

        /* renamed from: a */
        public void mo33733a(Map map) {
            if (map != null) {
                synchronized (this) {
                    this.f6741a = map;
                }
            }
        }

        public Map<String, ?> getAll() {
            HashMap hashMap;
            synchronized (this) {
                hashMap = new HashMap(this.f6741a);
            }
            return hashMap;
        }

        public String getString(String str, String str2) {
            String str3;
            synchronized (this) {
                str3 = (String) this.f6741a.get(str);
                if (str3 == null) {
                    str3 = str2;
                }
            }
            return str3;
        }

        public long getLong(String str, long j) {
            synchronized (this) {
                Long l = (Long) this.f6741a.get(str);
                if (l != null) {
                    j = l.longValue();
                }
            }
            return j;
        }

        /* renamed from: a */
        public C4324a mo33725a() {
            return new C4328a();
        }

        /* renamed from: a */
        private FileOutputStream m7790a(File file) {
            try {
                return new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                if (!file.getParentFile().mkdir()) {
                    return null;
                }
                try {
                    return new FileOutputStream(file);
                } catch (FileNotFoundException e2) {
                    return null;
                }
            }
        }

        /* renamed from: d */
        private boolean m7794d() {
            if (this.f6744b.exists()) {
                if (this.f6745c.exists()) {
                    this.f6744b.delete();
                } else if (!this.f6744b.renameTo(this.f6745c)) {
                    return false;
                }
            }
            try {
                OutputStream a = m7790a(this.f6744b);
                if (a == null) {
                    return false;
                }
                C4331e.m7813a(this.f6741a, a);
                a.close();
                this.f6745c.delete();
                return true;
            } catch (Exception e) {
                if (!(this.f6744b.exists() && this.f6744b.delete())) {
                }
                return false;
            }
        }
    }

    public C4330d(String str) {
        if (str == null || str.length() <= 0) {
            throw new RuntimeException("Directory can not be empty");
        }
        this.f6748a = new File(str);
    }

    /* renamed from: a */
    private File m7802a(File file, String str) {
        if (str.indexOf(File.separatorChar) < 0) {
            return new File(file, str);
        }
        throw new IllegalArgumentException("File " + str + " contains a path separator");
    }

    /* renamed from: a */
    private File m7800a() {
        File file;
        synchronized (this.f6749a) {
            file = this.f6748a;
        }
        return file;
    }

    /* renamed from: b */
    private File m7805b(String str) {
        return m7802a(m7800a(), str + ".xml");
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x006f A:{SYNTHETIC, Splitter:B:48:0x006f} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x008c A:{SYNTHETIC, Splitter:B:68:0x008c} */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x008c A:{SYNTHETIC, Splitter:B:68:0x008c} */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x006a A:{SYNTHETIC, Splitter:B:45:0x006a} */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x006f A:{SYNTHETIC, Splitter:B:48:0x006f} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0094 A:{SYNTHETIC, Splitter:B:74:0x0094} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x00ba A:{ExcHandler: all (th java.lang.Throwable), Splitter:B:19:0x003c, PHI: r2 } */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0078 A:{SYNTHETIC, Splitter:B:53:0x0078} */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x006f A:{SYNTHETIC, Splitter:B:48:0x006f} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0084 A:{SYNTHETIC, Splitter:B:61:0x0084} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:87:0x00ba, code skipped:
            r0 = th;
     */
    /* renamed from: a */
    public com.p044ta.utdid2.p057b.p058a.C4326b mo33736a(java.lang.String r6, int r7) {
        /*
        r5 = this;
        r1 = 0;
        r4 = r5.m7805b(r6);
        r2 = f6747b;
        monitor-enter(r2);
        r0 = r5.f6750a;	 Catch:{ all -> 0x0055 }
        r0 = r0.get(r4);	 Catch:{ all -> 0x0055 }
        r0 = (com.p044ta.utdid2.p057b.p058a.C4330d.C4329a) r0;	 Catch:{ all -> 0x0055 }
        if (r0 == 0) goto L_0x001a;
    L_0x0012:
        r3 = r0.mo33735c();	 Catch:{ all -> 0x0055 }
        if (r3 != 0) goto L_0x001a;
    L_0x0018:
        monitor-exit(r2);	 Catch:{ all -> 0x0055 }
    L_0x0019:
        return r0;
    L_0x001a:
        monitor-exit(r2);	 Catch:{ all -> 0x0055 }
        r2 = com.p044ta.utdid2.p057b.p058a.C4330d.m7801a(r4);
        r3 = r2.exists();
        if (r3 == 0) goto L_0x002b;
    L_0x0025:
        r4.delete();
        r2.renameTo(r4);
    L_0x002b:
        r2 = r4.exists();
        if (r2 == 0) goto L_0x0048;
    L_0x0031:
        r2 = r4.canRead();
        if (r2 == 0) goto L_0x0048;
    L_0x0037:
        r2 = new java.io.FileInputStream;	 Catch:{ XmlPullParserException -> 0x0058, Exception -> 0x0090, all -> 0x00b7 }
        r2.<init>(r4);	 Catch:{ XmlPullParserException -> 0x0058, Exception -> 0x0090, all -> 0x00b7 }
        r1 = com.p044ta.utdid2.p057b.p058a.C4331e.m7809a(r2);	 Catch:{ XmlPullParserException -> 0x00c6, Exception -> 0x00bf, all -> 0x00ba }
        r2.close();	 Catch:{ XmlPullParserException -> 0x00c6, Exception -> 0x00bf, all -> 0x00ba }
        if (r2 == 0) goto L_0x0048;
    L_0x0045:
        r2.close();	 Catch:{ Throwable -> 0x00af }
    L_0x0048:
        r2 = f6747b;
        monitor-enter(r2);
        if (r0 == 0) goto L_0x009a;
    L_0x004d:
        r0.mo33733a(r1);	 Catch:{ all -> 0x0052 }
    L_0x0050:
        monitor-exit(r2);	 Catch:{ all -> 0x0052 }
        goto L_0x0019;
    L_0x0052:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0052 }
        throw r0;
    L_0x0055:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0055 }
        throw r0;
    L_0x0058:
        r2 = move-exception;
        r2 = r1;
    L_0x005a:
        r3 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x0075, all -> 0x0080 }
        r3.<init>(r4);	 Catch:{ Exception -> 0x0075, all -> 0x0080 }
        r2 = r3.available();	 Catch:{ Exception -> 0x00c3, all -> 0x00c1 }
        r2 = new byte[r2];	 Catch:{ Exception -> 0x00c3, all -> 0x00c1 }
        r3.read(r2);	 Catch:{ Exception -> 0x00c3, all -> 0x00c1 }
        if (r3 == 0) goto L_0x006d;
    L_0x006a:
        r3.close();	 Catch:{ Throwable -> 0x00b1, all -> 0x00bc }
    L_0x006d:
        if (r3 == 0) goto L_0x0048;
    L_0x006f:
        r3.close();	 Catch:{ Throwable -> 0x0073 }
        goto L_0x0048;
    L_0x0073:
        r2 = move-exception;
        goto L_0x0048;
    L_0x0075:
        r3 = move-exception;
    L_0x0076:
        if (r2 == 0) goto L_0x00c8;
    L_0x0078:
        r2.close();	 Catch:{ Throwable -> 0x007d, all -> 0x00ba }
        r3 = r2;
        goto L_0x006d;
    L_0x007d:
        r3 = move-exception;
        r3 = r2;
        goto L_0x006d;
    L_0x0080:
        r0 = move-exception;
        r3 = r2;
    L_0x0082:
        if (r3 == 0) goto L_0x0087;
    L_0x0084:
        r3.close();	 Catch:{ Throwable -> 0x00b3 }
    L_0x0087:
        throw r0;	 Catch:{ all -> 0x0088 }
    L_0x0088:
        r0 = move-exception;
        r2 = r3;
    L_0x008a:
        if (r2 == 0) goto L_0x008f;
    L_0x008c:
        r2.close();	 Catch:{ Throwable -> 0x00b5 }
    L_0x008f:
        throw r0;
    L_0x0090:
        r2 = move-exception;
        r2 = r1;
    L_0x0092:
        if (r2 == 0) goto L_0x0048;
    L_0x0094:
        r2.close();	 Catch:{ Throwable -> 0x0098 }
        goto L_0x0048;
    L_0x0098:
        r2 = move-exception;
        goto L_0x0048;
    L_0x009a:
        r0 = r5.f6750a;	 Catch:{ all -> 0x0052 }
        r0 = r0.get(r4);	 Catch:{ all -> 0x0052 }
        r0 = (com.p044ta.utdid2.p057b.p058a.C4330d.C4329a) r0;	 Catch:{ all -> 0x0052 }
        if (r0 != 0) goto L_0x0050;
    L_0x00a4:
        r0 = new com.ta.utdid2.b.a.d$a;	 Catch:{ all -> 0x0052 }
        r0.<init>(r4, r7, r1);	 Catch:{ all -> 0x0052 }
        r1 = r5.f6750a;	 Catch:{ all -> 0x0052 }
        r1.put(r4, r0);	 Catch:{ all -> 0x0052 }
        goto L_0x0050;
    L_0x00af:
        r2 = move-exception;
        goto L_0x0048;
    L_0x00b1:
        r2 = move-exception;
        goto L_0x006d;
    L_0x00b3:
        r1 = move-exception;
        goto L_0x0087;
    L_0x00b5:
        r1 = move-exception;
        goto L_0x008f;
    L_0x00b7:
        r0 = move-exception;
        r2 = r1;
        goto L_0x008a;
    L_0x00ba:
        r0 = move-exception;
        goto L_0x008a;
    L_0x00bc:
        r0 = move-exception;
        r2 = r3;
        goto L_0x008a;
    L_0x00bf:
        r3 = move-exception;
        goto L_0x0092;
    L_0x00c1:
        r0 = move-exception;
        goto L_0x0082;
    L_0x00c3:
        r2 = move-exception;
        r2 = r3;
        goto L_0x0076;
    L_0x00c6:
        r3 = move-exception;
        goto L_0x005a;
    L_0x00c8:
        r3 = r2;
        goto L_0x006d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p044ta.utdid2.p057b.p058a.C4330d.mo33736a(java.lang.String, int):com.ta.utdid2.b.a.b");
    }

    /* renamed from: a */
    private static File m7801a(File file) {
        return new File(file.getPath() + ".bak");
    }
}
