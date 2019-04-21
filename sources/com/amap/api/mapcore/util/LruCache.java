package com.amap.api.mapcore.util;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/* renamed from: com.amap.api.mapcore.util.df */
public class LruCache<K, V> {
    /* renamed from: a */
    private final LinkedHashMap<K, V> f1705a;
    /* renamed from: b */
    private int f1706b;
    /* renamed from: c */
    private int f1707c;
    /* renamed from: d */
    private int f1708d;
    /* renamed from: e */
    private int f1709e;
    /* renamed from: f */
    private int f1710f;
    /* renamed from: g */
    private int f1711g;
    /* renamed from: h */
    private int f1712h;

    public LruCache(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.f1707c = i;
        this.f1705a = new LinkedHashMap(0, 0.75f, true);
    }

    /* JADX WARNING: Missing block: B:11:0x0022, code skipped:
            r1 = mo9232b(r5);
     */
    /* JADX WARNING: Missing block: B:12:0x0026, code skipped:
            if (r1 != null) goto L_0x002d;
     */
    /* JADX WARNING: Missing block: B:18:0x002d, code skipped:
            monitor-enter(r4);
     */
    /* JADX WARNING: Missing block: B:20:?, code skipped:
            r4.f1709e++;
            r0 = r4.f1705a.put(r5, r1);
     */
    /* JADX WARNING: Missing block: B:21:0x003a, code skipped:
            if (r0 == null) goto L_0x0049;
     */
    /* JADX WARNING: Missing block: B:22:0x003c, code skipped:
            r4.f1705a.put(r5, r0);
     */
    /* JADX WARNING: Missing block: B:23:0x0041, code skipped:
            monitor-exit(r4);
     */
    /* JADX WARNING: Missing block: B:24:0x0042, code skipped:
            if (r0 == null) goto L_0x0056;
     */
    /* JADX WARNING: Missing block: B:25:0x0044, code skipped:
            mo9231a(false, r5, r1, r0);
     */
    /* JADX WARNING: Missing block: B:27:?, code skipped:
            r4.f1706b += m2246c(r5, r1);
     */
    /* JADX WARNING: Missing block: B:31:0x0056, code skipped:
            m2245a(r4.f1707c);
     */
    /* JADX WARNING: Missing block: B:36:?, code skipped:
            return null;
     */
    /* JADX WARNING: Missing block: B:37:?, code skipped:
            return r0;
     */
    /* JADX WARNING: Missing block: B:38:?, code skipped:
            return r1;
     */
    /* renamed from: a */
    public final V mo9229a(K r5) {
        /*
        r4 = this;
        if (r5 != 0) goto L_0x000a;
    L_0x0002:
        r0 = new java.lang.NullPointerException;
        r1 = "key == null";
        r0.<init>(r1);
        throw r0;
    L_0x000a:
        monitor-enter(r4);
        r0 = r4.f1705a;	 Catch:{ all -> 0x002a }
        r0 = r0.get(r5);	 Catch:{ all -> 0x002a }
        if (r0 == 0) goto L_0x001b;
    L_0x0013:
        r1 = r4.f1711g;	 Catch:{ all -> 0x002a }
        r1 = r1 + 1;
        r4.f1711g = r1;	 Catch:{ all -> 0x002a }
        monitor-exit(r4);	 Catch:{ all -> 0x002a }
    L_0x001a:
        return r0;
    L_0x001b:
        r0 = r4.f1712h;	 Catch:{ all -> 0x002a }
        r0 = r0 + 1;
        r4.f1712h = r0;	 Catch:{ all -> 0x002a }
        monitor-exit(r4);	 Catch:{ all -> 0x002a }
        r1 = r4.mo9232b(r5);
        if (r1 != 0) goto L_0x002d;
    L_0x0028:
        r0 = 0;
        goto L_0x001a;
    L_0x002a:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x002a }
        throw r0;
    L_0x002d:
        monitor-enter(r4);
        r0 = r4.f1709e;	 Catch:{ all -> 0x0053 }
        r0 = r0 + 1;
        r4.f1709e = r0;	 Catch:{ all -> 0x0053 }
        r0 = r4.f1705a;	 Catch:{ all -> 0x0053 }
        r0 = r0.put(r5, r1);	 Catch:{ all -> 0x0053 }
        if (r0 == 0) goto L_0x0049;
    L_0x003c:
        r2 = r4.f1705a;	 Catch:{ all -> 0x0053 }
        r2.put(r5, r0);	 Catch:{ all -> 0x0053 }
    L_0x0041:
        monitor-exit(r4);	 Catch:{ all -> 0x0053 }
        if (r0 == 0) goto L_0x0056;
    L_0x0044:
        r2 = 0;
        r4.mo9231a(r2, r5, r1, r0);
        goto L_0x001a;
    L_0x0049:
        r2 = r4.f1706b;	 Catch:{ all -> 0x0053 }
        r3 = r4.m2246c(r5, r1);	 Catch:{ all -> 0x0053 }
        r2 = r2 + r3;
        r4.f1706b = r2;	 Catch:{ all -> 0x0053 }
        goto L_0x0041;
    L_0x0053:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0053 }
        throw r0;
    L_0x0056:
        r0 = r4.f1707c;
        r4.m2245a(r0);
        r0 = r1;
        goto L_0x001a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.LruCache.mo9229a(java.lang.Object):java.lang.Object");
    }

    /* renamed from: b */
    public final V mo9233b(K k, V v) {
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        Object put;
        synchronized (this) {
            this.f1708d++;
            this.f1706b += m2246c(k, v);
            put = this.f1705a.put(k, v);
            if (put != null) {
                this.f1706b -= m2246c(k, put);
            }
        }
        if (put != null) {
            mo9231a(false, k, put, v);
        }
        m2245a(this.f1707c);
        return put;
    }

    /* renamed from: a */
    private void m2245a(int i) {
        while (true) {
            Object key;
            Object value;
            synchronized (this) {
                if (!(this.f1706b >= 0 && this.f1705a.isEmpty() && this.f1706b == 0)) {
                }
                if (this.f1706b <= i) {
                    return;
                }
                Entry entry = null;
                for (Entry entry2 : this.f1705a.entrySet()) {
                }
                if (entry2 == null) {
                    return;
                }
                key = entry2.getKey();
                value = entry2.getValue();
                this.f1705a.remove(key);
                this.f1706b -= m2246c(key, value);
                this.f1710f++;
            }
            mo9231a(true, key, value, null);
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo9231a(boolean z, K k, V v, V v2) {
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public V mo9232b(K k) {
        return null;
    }

    /* renamed from: c */
    private int m2246c(K k, V v) {
        int a = mo9228a(k, v);
        if (a >= 0) {
            return a;
        }
        throw new IllegalStateException("Negative size: " + k + "=" + v);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public int mo9228a(K k, V v) {
        return 1;
    }

    /* renamed from: a */
    public final void mo9230a() {
        m2245a(-1);
    }

    public final synchronized String toString() {
        String format;
        int i = 0;
        synchronized (this) {
            int i2 = this.f1711g + this.f1712h;
            if (i2 != 0) {
                i = (this.f1711g * 100) / i2;
            }
            format = String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", new Object[]{Integer.valueOf(this.f1707c), Integer.valueOf(this.f1711g), Integer.valueOf(this.f1712h), Integer.valueOf(i)});
        }
        return format;
    }
}
