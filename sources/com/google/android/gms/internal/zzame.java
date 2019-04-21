package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class zzame<M extends zzamd<M>, T> {
    public final int tag;
    protected final int type;
    protected final Class<T> zzbSs;
    protected final boolean zzcab;

    private zzame(int i, Class<T> cls, int i2, boolean z) {
        this.type = i;
        this.zzbSs = cls;
        this.tag = i2;
        this.zzcab = z;
    }

    private T zzW(List<zzaml> list) {
        int i;
        int i2 = 0;
        List arrayList = new ArrayList();
        for (i = 0; i < list.size(); i++) {
            zzaml zzaml = (zzaml) list.get(i);
            if (zzaml.zzcak.length != 0) {
                zza(zzaml, arrayList);
            }
        }
        i = arrayList.size();
        if (i == 0) {
            return null;
        }
        Object cast = this.zzbSs.cast(Array.newInstance(this.zzbSs.getComponentType(), i));
        while (i2 < i) {
            Array.set(cast, i2, arrayList.get(i2));
            i2++;
        }
        return cast;
    }

    private T zzX(List<zzaml> list) {
        if (list.isEmpty()) {
            return null;
        }
        return this.zzbSs.cast(zzV(zzamb.zzN(((zzaml) list.get(list.size() - 1)).zzcak)));
    }

    public static <M extends zzamd<M>, T extends zzamj> zzame<M, T> zza(int i, Class<T> cls, long j) {
        return new zzame(i, cls, (int) j, false);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzame)) {
            return false;
        }
        zzame zzame = (zzame) obj;
        return this.type == zzame.type && this.zzbSs == zzame.zzbSs && this.tag == zzame.tag && this.zzcab == zzame.zzcab;
    }

    public int hashCode() {
        return (this.zzcab ? 1 : 0) + ((((((this.type + 1147) * 31) + this.zzbSs.hashCode()) * 31) + this.tag) * 31);
    }

    /* Access modifiers changed, original: protected */
    public Object zzV(zzamb zzamb) {
        String valueOf;
        Class componentType = this.zzcab ? this.zzbSs.getComponentType() : this.zzbSs;
        try {
            zzamj zzamj;
            switch (this.type) {
                case 10:
                    zzamj = (zzamj) componentType.newInstance();
                    zzamb.zza(zzamj, zzamm.zzoo(this.tag));
                    return zzamj;
                case 11:
                    zzamj = (zzamj) componentType.newInstance();
                    zzamb.zza(zzamj);
                    return zzamj;
                default:
                    throw new IllegalArgumentException("Unknown type " + this.type);
            }
        } catch (InstantiationException e) {
            valueOf = String.valueOf(componentType);
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 33).append("Error creating instance of class ").append(valueOf).toString(), e);
        } catch (IllegalAccessException e2) {
            valueOf = String.valueOf(componentType);
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 33).append("Error creating instance of class ").append(valueOf).toString(), e2);
        } catch (IOException e3) {
            throw new IllegalArgumentException("Error reading extension field", e3);
        }
    }

    /* Access modifiers changed, original: final */
    public final T zzV(List<zzaml> list) {
        return list == null ? null : this.zzcab ? zzW(list) : zzX(list);
    }

    /* Access modifiers changed, original: protected */
    public void zza(zzaml zzaml, List<Object> list) {
        list.add(zzV(zzamb.zzN(zzaml.zzcak)));
    }

    /* Access modifiers changed, original: 0000 */
    public void zza(Object obj, zzamc zzamc) throws IOException {
        if (this.zzcab) {
            zzc(obj, zzamc);
        } else {
            zzb(obj, zzamc);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public int zzaP(Object obj) {
        return this.zzcab ? zzaQ(obj) : zzaR(obj);
    }

    /* Access modifiers changed, original: protected */
    public int zzaQ(Object obj) {
        int i = 0;
        int length = Array.getLength(obj);
        for (int i2 = 0; i2 < length; i2++) {
            if (Array.get(obj, i2) != null) {
                i += zzaR(Array.get(obj, i2));
            }
        }
        return i;
    }

    /* Access modifiers changed, original: protected */
    public int zzaR(Object obj) {
        int zzoo = zzamm.zzoo(this.tag);
        switch (this.type) {
            case 10:
                return zzamc.zzb(zzoo, (zzamj) obj);
            case 11:
                return zzamc.zzc(zzoo, (zzamj) obj);
            default:
                throw new IllegalArgumentException("Unknown type " + this.type);
        }
    }

    /* Access modifiers changed, original: protected */
    public void zzb(Object obj, zzamc zzamc) {
        try {
            zzamc.zzog(this.tag);
            switch (this.type) {
                case 10:
                    zzamj zzamj = (zzamj) obj;
                    int zzoo = zzamm.zzoo(this.tag);
                    zzamc.zzb(zzamj);
                    zzamc.zzI(zzoo, 4);
                    return;
                case 11:
                    zzamc.zzc((zzamj) obj);
                    return;
                default:
                    throw new IllegalArgumentException("Unknown type " + this.type);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        throw new IllegalStateException(e);
    }

    /* Access modifiers changed, original: protected */
    public void zzc(Object obj, zzamc zzamc) {
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            Object obj2 = Array.get(obj, i);
            if (obj2 != null) {
                zzb(obj2, zzamc);
            }
        }
    }
}
