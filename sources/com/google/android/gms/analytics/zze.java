package com.google.android.gms.analytics;

import com.google.android.gms.common.internal.zzaa;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class zze {
    private final zzh zzUm;
    private boolean zzUn;
    private long zzUo;
    private long zzUp;
    private long zzUq;
    private long zzUr;
    private long zzUs;
    private boolean zzUt;
    private final Map<Class<? extends zzg>, zzg> zzUu;
    private final List<zzk> zzUv;
    private final com.google.android.gms.common.util.zze zzsd;

    zze(zze zze) {
        this.zzUm = zze.zzUm;
        this.zzsd = zze.zzsd;
        this.zzUo = zze.zzUo;
        this.zzUp = zze.zzUp;
        this.zzUq = zze.zzUq;
        this.zzUr = zze.zzUr;
        this.zzUs = zze.zzUs;
        this.zzUv = new ArrayList(zze.zzUv);
        this.zzUu = new HashMap(zze.zzUu.size());
        for (Entry entry : zze.zzUu.entrySet()) {
            zzg zzc = zzc((Class) entry.getKey());
            ((zzg) entry.getValue()).zzb(zzc);
            this.zzUu.put((Class) entry.getKey(), zzc);
        }
    }

    zze(zzh zzh, com.google.android.gms.common.util.zze zze) {
        zzaa.zzz(zzh);
        zzaa.zzz(zze);
        this.zzUm = zzh;
        this.zzsd = zze;
        this.zzUr = 1800000;
        this.zzUs = 3024000000L;
        this.zzUu = new HashMap();
        this.zzUv = new ArrayList();
    }

    private static <T extends zzg> T zzc(Class<T> cls) {
        try {
            return (zzg) cls.newInstance();
        } catch (InstantiationException e) {
            throw new IllegalArgumentException("dataType doesn't have default constructor", e);
        } catch (IllegalAccessException e2) {
            throw new IllegalArgumentException("dataType default constructor is not accessible", e2);
        }
    }

    public <T extends zzg> T zza(Class<T> cls) {
        return (zzg) this.zzUu.get(cls);
    }

    public void zza(zzg zzg) {
        zzaa.zzz(zzg);
        Class cls = zzg.getClass();
        if (cls.getSuperclass() != zzg.class) {
            throw new IllegalArgumentException();
        }
        zzg.zzb(zzb(cls));
    }

    public <T extends zzg> T zzb(Class<T> cls) {
        zzg zzg = (zzg) this.zzUu.get(cls);
        if (zzg != null) {
            return zzg;
        }
        zzg = zzc(cls);
        this.zzUu.put(cls, zzg);
        return zzg;
    }

    public List<zzk> zzkA() {
        return this.zzUv;
    }

    public long zzkB() {
        return this.zzUo;
    }

    public void zzkC() {
        zzkG().zze(this);
    }

    public boolean zzkD() {
        return this.zzUn;
    }

    /* Access modifiers changed, original: 0000 */
    public void zzkE() {
        this.zzUq = this.zzsd.elapsedRealtime();
        if (this.zzUp != 0) {
            this.zzUo = this.zzUp;
        } else {
            this.zzUo = this.zzsd.currentTimeMillis();
        }
        this.zzUn = true;
    }

    /* Access modifiers changed, original: 0000 */
    public zzh zzkF() {
        return this.zzUm;
    }

    /* Access modifiers changed, original: 0000 */
    public zzi zzkG() {
        return this.zzUm.zzkG();
    }

    /* Access modifiers changed, original: 0000 */
    public boolean zzkH() {
        return this.zzUt;
    }

    /* Access modifiers changed, original: 0000 */
    public void zzkI() {
        this.zzUt = true;
    }

    public zze zzky() {
        return new zze(this);
    }

    public Collection<zzg> zzkz() {
        return this.zzUu.values();
    }

    public void zzn(long j) {
        this.zzUp = j;
    }
}
