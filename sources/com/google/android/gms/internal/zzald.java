package com.google.android.gms.internal;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

public final class zzald<K, V> extends AbstractMap<K, V> implements Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = (!zzald.class.desiredAssertionStatus());
    private static final Comparator<Comparable> zzbXf = new C21791();
    int modCount;
    int size;
    Comparator<? super K> zzbIf;
    zzd<K, V> zzbXg;
    final zzd<K, V> zzbXh;
    private zza zzbXi;
    private zzb zzbXj;

    /* renamed from: com.google.android.gms.internal.zzald$1 */
    static class C21791 implements Comparator<Comparable> {
        C21791() {
        }

        /* renamed from: zza */
        public int compare(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    }

    private abstract class zzc<T> implements Iterator<T> {
        zzd<K, V> zzbXn;
        zzd<K, V> zzbXo;
        int zzbXp;

        private zzc() {
            this.zzbXn = zzald.this.zzbXh.zzbXn;
            this.zzbXo = null;
            this.zzbXp = zzald.this.modCount;
        }

        /* synthetic */ zzc(zzald zzald, C21791 c21791) {
            this();
        }

        public final boolean hasNext() {
            return this.zzbXn != zzald.this.zzbXh;
        }

        public final void remove() {
            if (this.zzbXo == null) {
                throw new IllegalStateException();
            }
            zzald.this.zza(this.zzbXo, true);
            this.zzbXo = null;
            this.zzbXp = zzald.this.modCount;
        }

        /* Access modifiers changed, original: final */
        public final zzd<K, V> zzVV() {
            zzd zzd = this.zzbXn;
            if (zzd == zzald.this.zzbXh) {
                throw new NoSuchElementException();
            } else if (zzald.this.modCount != this.zzbXp) {
                throw new ConcurrentModificationException();
            } else {
                this.zzbXn = zzd.zzbXn;
                this.zzbXo = zzd;
                return zzd;
            }
        }
    }

    class zza extends AbstractSet<Entry<K, V>> {

        /* renamed from: com.google.android.gms.internal.zzald$zza$1 */
        class C21801 extends zzc<Entry<K, V>> {
            C21801() {
                super(zzald.this, null);
            }

            public Entry<K, V> next() {
                return zzVV();
            }
        }

        zza() {
        }

        public void clear() {
            zzald.this.clear();
        }

        public boolean contains(Object obj) {
            return (obj instanceof Entry) && zzald.this.zzc((Entry) obj) != null;
        }

        public Iterator<Entry<K, V>> iterator() {
            return new C21801();
        }

        public boolean remove(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            zzd zzc = zzald.this.zzc((Entry) obj);
            if (zzc == null) {
                return false;
            }
            zzald.this.zza(zzc, true);
            return true;
        }

        public int size() {
            return zzald.this.size;
        }
    }

    final class zzb extends AbstractSet<K> {

        /* renamed from: com.google.android.gms.internal.zzald$zzb$1 */
        class C21811 extends zzc<K> {
            C21811() {
                super(zzald.this, null);
            }

            public K next() {
                return zzVV().zzbIt;
            }
        }

        zzb() {
        }

        public void clear() {
            zzald.this.clear();
        }

        public boolean contains(Object obj) {
            return zzald.this.containsKey(obj);
        }

        public Iterator<K> iterator() {
            return new C21811();
        }

        public boolean remove(Object obj) {
            return zzald.this.zzaN(obj) != null;
        }

        public int size() {
            return zzald.this.size;
        }
    }

    static final class zzd<K, V> implements Entry<K, V> {
        int height;
        final K zzbIt;
        V zzbIu;
        zzd<K, V> zzbXn;
        zzd<K, V> zzbXq;
        zzd<K, V> zzbXr;
        zzd<K, V> zzbXs;
        zzd<K, V> zzbXt;

        zzd() {
            this.zzbIt = null;
            this.zzbXt = this;
            this.zzbXn = this;
        }

        zzd(zzd<K, V> zzd, K k, zzd<K, V> zzd2, zzd<K, V> zzd3) {
            this.zzbXq = zzd;
            this.zzbIt = k;
            this.height = 1;
            this.zzbXn = zzd2;
            this.zzbXt = zzd3;
            zzd3.zzbXn = this;
            zzd2.zzbXt = this;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            if (this.zzbIt == null) {
                if (entry.getKey() != null) {
                    return false;
                }
            } else if (!this.zzbIt.equals(entry.getKey())) {
                return false;
            }
            if (this.zzbIu == null) {
                if (entry.getValue() != null) {
                    return false;
                }
            } else if (!this.zzbIu.equals(entry.getValue())) {
                return false;
            }
            return true;
        }

        public K getKey() {
            return this.zzbIt;
        }

        public V getValue() {
            return this.zzbIu;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = this.zzbIt == null ? 0 : this.zzbIt.hashCode();
            if (this.zzbIu != null) {
                i = this.zzbIu.hashCode();
            }
            return hashCode ^ i;
        }

        public V setValue(V v) {
            Object obj = this.zzbIu;
            this.zzbIu = v;
            return obj;
        }

        public String toString() {
            String valueOf = String.valueOf(this.zzbIt);
            String valueOf2 = String.valueOf(this.zzbIu);
            return new StringBuilder((String.valueOf(valueOf).length() + 1) + String.valueOf(valueOf2).length()).append(valueOf).append("=").append(valueOf2).toString();
        }

        public zzd<K, V> zzVW() {
            zzd<K, V> thisR;
            for (zzd<K, V> zzd = this.zzbXr; zzd != null; zzd = zzd.zzbXr) {
                thisR = zzd;
            }
            return thisR;
        }

        public zzd<K, V> zzVX() {
            zzd<K, V> thisR;
            for (zzd<K, V> zzd = this.zzbXs; zzd != null; zzd = zzd.zzbXs) {
                thisR = zzd;
            }
            return thisR;
        }
    }

    public zzald() {
        this(zzbXf);
    }

    public zzald(Comparator<? super K> comparator) {
        Comparator comparator2;
        this.size = 0;
        this.modCount = 0;
        this.zzbXh = new zzd();
        if (comparator2 == null) {
            comparator2 = zzbXf;
        }
        this.zzbIf = comparator2;
    }

    private boolean equal(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private void zza(zzd<K, V> zzd) {
        int i = 0;
        zzd zzd2 = zzd.zzbXr;
        zzd zzd3 = zzd.zzbXs;
        zzd zzd4 = zzd3.zzbXr;
        zzd zzd5 = zzd3.zzbXs;
        zzd.zzbXs = zzd4;
        if (zzd4 != null) {
            zzd4.zzbXq = zzd;
        }
        zza((zzd) zzd, zzd3);
        zzd3.zzbXr = zzd;
        zzd.zzbXq = zzd3;
        zzd.height = Math.max(zzd2 != null ? zzd2.height : 0, zzd4 != null ? zzd4.height : 0) + 1;
        int i2 = zzd.height;
        if (zzd5 != null) {
            i = zzd5.height;
        }
        zzd3.height = Math.max(i2, i) + 1;
    }

    private void zza(zzd<K, V> zzd, zzd<K, V> zzd2) {
        zzd zzd3 = zzd.zzbXq;
        zzd.zzbXq = null;
        if (zzd2 != null) {
            zzd2.zzbXq = zzd3;
        }
        if (zzd3 == null) {
            this.zzbXg = zzd2;
        } else if (zzd3.zzbXr == zzd) {
            zzd3.zzbXr = zzd2;
        } else if ($assertionsDisabled || zzd3.zzbXs == zzd) {
            zzd3.zzbXs = zzd2;
        } else {
            throw new AssertionError();
        }
    }

    private void zzb(zzd<K, V> zzd) {
        int i = 0;
        zzd zzd2 = zzd.zzbXr;
        zzd zzd3 = zzd.zzbXs;
        zzd zzd4 = zzd2.zzbXr;
        zzd zzd5 = zzd2.zzbXs;
        zzd.zzbXr = zzd5;
        if (zzd5 != null) {
            zzd5.zzbXq = zzd;
        }
        zza((zzd) zzd, zzd2);
        zzd2.zzbXs = zzd;
        zzd.zzbXq = zzd2;
        zzd.height = Math.max(zzd3 != null ? zzd3.height : 0, zzd5 != null ? zzd5.height : 0) + 1;
        int i2 = zzd.height;
        if (zzd4 != null) {
            i = zzd4.height;
        }
        zzd2.height = Math.max(i2, i) + 1;
    }

    private void zzb(zzd<K, V> zzd, boolean z) {
        zzd zzd2;
        while (zzd2 != null) {
            zzd zzd3 = zzd2.zzbXr;
            zzd zzd4 = zzd2.zzbXs;
            int i = zzd3 != null ? zzd3.height : 0;
            int i2 = zzd4 != null ? zzd4.height : 0;
            int i3 = i - i2;
            zzd zzd5;
            if (i3 == -2) {
                zzd3 = zzd4.zzbXr;
                zzd5 = zzd4.zzbXs;
                i2 = (zzd3 != null ? zzd3.height : 0) - (zzd5 != null ? zzd5.height : 0);
                if (i2 == -1 || (i2 == 0 && !z)) {
                    zza(zzd2);
                } else if ($assertionsDisabled || i2 == 1) {
                    zzb(zzd4);
                    zza(zzd2);
                } else {
                    throw new AssertionError();
                }
                if (z) {
                    return;
                }
            } else if (i3 == 2) {
                zzd4 = zzd3.zzbXr;
                zzd5 = zzd3.zzbXs;
                i2 = (zzd4 != null ? zzd4.height : 0) - (zzd5 != null ? zzd5.height : 0);
                if (i2 == 1 || (i2 == 0 && !z)) {
                    zzb(zzd2);
                } else if ($assertionsDisabled || i2 == -1) {
                    zza(zzd3);
                    zzb(zzd2);
                } else {
                    throw new AssertionError();
                }
                if (z) {
                    return;
                }
            } else if (i3 == 0) {
                zzd2.height = i + 1;
                if (z) {
                    return;
                }
            } else if ($assertionsDisabled || i3 == -1 || i3 == 1) {
                zzd2.height = Math.max(i, i2) + 1;
                if (!z) {
                    return;
                }
            } else {
                throw new AssertionError();
            }
            zzd2 = zzd2.zzbXq;
        }
    }

    public void clear() {
        this.zzbXg = null;
        this.size = 0;
        this.modCount++;
        zzd zzd = this.zzbXh;
        zzd.zzbXt = zzd;
        zzd.zzbXn = zzd;
    }

    public boolean containsKey(Object obj) {
        return zzaM(obj) != null;
    }

    public Set<Entry<K, V>> entrySet() {
        zza zza = this.zzbXi;
        if (zza != null) {
            return zza;
        }
        zza = new zza();
        this.zzbXi = zza;
        return zza;
    }

    public V get(Object obj) {
        zzd zzaM = zzaM(obj);
        return zzaM != null ? zzaM.zzbIu : null;
    }

    public Set<K> keySet() {
        zzb zzb = this.zzbXj;
        if (zzb != null) {
            return zzb;
        }
        zzb = new zzb();
        this.zzbXj = zzb;
        return zzb;
    }

    public V put(K k, V v) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        zzd zza = zza((Object) k, true);
        Object obj = zza.zzbIu;
        zza.zzbIu = v;
        return obj;
    }

    public V remove(Object obj) {
        zzd zzaN = zzaN(obj);
        return zzaN != null ? zzaN.zzbIu : null;
    }

    public int size() {
        return this.size;
    }

    /* Access modifiers changed, original: 0000 */
    public zzd<K, V> zza(K k, boolean z) {
        zzd zzd;
        int i;
        Comparator comparator = this.zzbIf;
        zzd<K, V> zzd2 = this.zzbXg;
        if (zzd2 != null) {
            Comparable comparable = comparator == zzbXf ? (Comparable) k : null;
            while (true) {
                int compareTo = comparable != null ? comparable.compareTo(zzd2.zzbIt) : comparator.compare(k, zzd2.zzbIt);
                if (compareTo == 0) {
                    return zzd2;
                }
                zzd<K, V> zzd3 = compareTo < 0 ? zzd2.zzbXr : zzd2.zzbXs;
                if (zzd3 == null) {
                    int i2 = compareTo;
                    zzd = zzd2;
                    i = i2;
                    break;
                }
                zzd2 = zzd3;
            }
        } else {
            zzd<K, V> zzd4 = zzd2;
            i = 0;
        }
        if (!z) {
            return null;
        }
        zzd<K, V> zzd5;
        zzd zzd6 = this.zzbXh;
        if (zzd4 != null) {
            zzd5 = new zzd(zzd4, k, zzd6, zzd6.zzbXt);
            if (i < 0) {
                zzd4.zzbXr = zzd5;
            } else {
                zzd4.zzbXs = zzd5;
            }
            zzb(zzd4, true);
        } else if (comparator != zzbXf || (k instanceof Comparable)) {
            zzd5 = new zzd(zzd4, k, zzd6, zzd6.zzbXt);
            this.zzbXg = zzd5;
        } else {
            throw new ClassCastException(String.valueOf(k.getClass().getName()).concat(" is not Comparable"));
        }
        this.size++;
        this.modCount++;
        return zzd5;
    }

    /* Access modifiers changed, original: 0000 */
    public void zza(zzd<K, V> zzd, boolean z) {
        int i = 0;
        if (z) {
            zzd.zzbXt.zzbXn = zzd.zzbXn;
            zzd.zzbXn.zzbXt = zzd.zzbXt;
        }
        zzd zzd2 = zzd.zzbXr;
        zzd zzd3 = zzd.zzbXs;
        zzd zzd4 = zzd.zzbXq;
        if (zzd2 == null || zzd3 == null) {
            if (zzd2 != null) {
                zza((zzd) zzd, zzd2);
                zzd.zzbXr = null;
            } else if (zzd3 != null) {
                zza((zzd) zzd, zzd3);
                zzd.zzbXs = null;
            } else {
                zza((zzd) zzd, null);
            }
            zzb(zzd4, false);
            this.size--;
            this.modCount++;
            return;
        }
        int i2;
        zzd2 = zzd2.height > zzd3.height ? zzd2.zzVX() : zzd3.zzVW();
        zza(zzd2, false);
        zzd4 = zzd.zzbXr;
        if (zzd4 != null) {
            i2 = zzd4.height;
            zzd2.zzbXr = zzd4;
            zzd4.zzbXq = zzd2;
            zzd.zzbXr = null;
        } else {
            i2 = 0;
        }
        zzd4 = zzd.zzbXs;
        if (zzd4 != null) {
            i = zzd4.height;
            zzd2.zzbXs = zzd4;
            zzd4.zzbXq = zzd2;
            zzd.zzbXs = null;
        }
        zzd2.height = Math.max(i2, i) + 1;
        zza((zzd) zzd, zzd2);
    }

    /* Access modifiers changed, original: 0000 */
    public zzd<K, V> zzaM(Object obj) {
        zzd<K, V> zzd = null;
        if (obj == null) {
            return zzd;
        }
        try {
            return zza(obj, false);
        } catch (ClassCastException e) {
            return zzd;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public zzd<K, V> zzaN(Object obj) {
        zzd zzaM = zzaM(obj);
        if (zzaM != null) {
            zza(zzaM, true);
        }
        return zzaM;
    }

    /* Access modifiers changed, original: 0000 */
    public zzd<K, V> zzc(Entry<?, ?> entry) {
        zzd zzaM = zzaM(entry.getKey());
        Object obj = (zzaM == null || !equal(zzaM.zzbIu, entry.getValue())) ? null : 1;
        return obj != null ? zzaM : null;
    }
}
