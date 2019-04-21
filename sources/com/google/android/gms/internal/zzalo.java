package com.google.android.gms.internal;

import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public final class zzalo implements zzaks {
    private final zzakz zzbWa;
    private final boolean zzbXP;

    private final class zza<K, V> extends zzakr<Map<K, V>> {
        private final zzale<? extends Map<K, V>> zzbXH;
        private final zzakr<K> zzbXQ;
        private final zzakr<V> zzbXR;

        public zza(zzajz zzajz, Type type, zzakr<K> zzakr, Type type2, zzakr<V> zzakr2, zzale<? extends Map<K, V>> zzale) {
            this.zzbXQ = new zzalt(zzajz, zzakr, type);
            this.zzbXR = new zzalt(zzajz, zzakr2, type2);
            this.zzbXH = zzale;
        }

        private String zze(zzakf zzakf) {
            if (zzakf.zzVH()) {
                zzakl zzVL = zzakf.zzVL();
                if (zzVL.zzVO()) {
                    return String.valueOf(zzVL.zzVz());
                }
                if (zzVL.zzVN()) {
                    return Boolean.toString(zzVL.zzVE());
                }
                if (zzVL.zzVP()) {
                    return zzVL.zzVA();
                }
                throw new AssertionError();
            } else if (zzakf.zzVI()) {
                return SafeJsonPrimitive.NULL_STRING;
            } else {
                throw new AssertionError();
            }
        }

        public void zza(zzaly zzaly, Map<K, V> map) throws IOException {
            int i = 0;
            if (map == null) {
                zzaly.zzWk();
            } else if (zzalo.this.zzbXP) {
                ArrayList arrayList = new ArrayList(map.size());
                ArrayList arrayList2 = new ArrayList(map.size());
                int i2 = 0;
                for (Entry entry : map.entrySet()) {
                    zzakf zzaJ = this.zzbXQ.zzaJ(entry.getKey());
                    arrayList.add(zzaJ);
                    arrayList2.add(entry.getValue());
                    int i3 = (zzaJ.zzVF() || zzaJ.zzVG()) ? 1 : 0;
                    i2 = i3 | i2;
                }
                if (i2 != 0) {
                    zzaly.zzWg();
                    while (i < arrayList.size()) {
                        zzaly.zzWg();
                        zzalg.zzb((zzakf) arrayList.get(i), zzaly);
                        this.zzbXR.zza(zzaly, arrayList2.get(i));
                        zzaly.zzWh();
                        i++;
                    }
                    zzaly.zzWh();
                    return;
                }
                zzaly.zzWi();
                while (i < arrayList.size()) {
                    zzaly.zziT(zze((zzakf) arrayList.get(i)));
                    this.zzbXR.zza(zzaly, arrayList2.get(i));
                    i++;
                }
                zzaly.zzWj();
            } else {
                zzaly.zzWi();
                for (Entry entry2 : map.entrySet()) {
                    zzaly.zziT(String.valueOf(entry2.getKey()));
                    this.zzbXR.zza(zzaly, entry2.getValue());
                }
                zzaly.zzWj();
            }
        }
    }

    public zzalo(zzakz zzakz, boolean z) {
        this.zzbWa = zzakz;
        this.zzbXP = z;
    }

    private zzakr<?> zza(zzajz zzajz, Type type) {
        return (type == Boolean.TYPE || type == Boolean.class) ? zzalu.zzbYl : zzajz.zza(zzalv.zzl(type));
    }

    public <T> zzakr<T> zza(zzajz zzajz, zzalv<T> zzalv) {
        Type zzWm = zzalv.zzWm();
        if (!Map.class.isAssignableFrom(zzalv.zzWl())) {
            return null;
        }
        Type[] zzb = zzaky.zzb(zzWm, zzaky.zzf(zzWm));
        zzakr zza = zza(zzajz, zzb[0]);
        zzakr zza2 = zzajz.zza(zzalv.zzl(zzb[1]));
        zzale zzb2 = this.zzbWa.zzb(zzalv);
        return new zza(zzajz, zzb[0], zza, zzb[1], zza2, zzb2);
    }
}
