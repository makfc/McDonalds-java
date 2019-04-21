package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class zzalq implements zzaks {
    private final zzakz zzbWa;
    private final zzala zzbWj;
    private final zzajy zzbWl;

    static abstract class zzb {
        final String name;
        final boolean zzbYb;
        final boolean zzbYc;

        protected zzb(String str, boolean z, boolean z2) {
            this.name = str;
            this.zzbYb = z;
            this.zzbYc = z2;
        }

        public abstract void zza(zzaly zzaly, Object obj) throws IOException, IllegalAccessException;

        public abstract boolean zzaO(Object obj) throws IOException, IllegalAccessException;
    }

    public static final class zza<T> extends zzakr<T> {
        private final zzale<T> zzbXH;
        private final Map<String, zzb> zzbYa;

        private zza(zzale<T> zzale, Map<String, zzb> map) {
            this.zzbXH = zzale;
            this.zzbYa = map;
        }

        /* synthetic */ zza(zzale zzale, Map map, C21941 c21941) {
            this(zzale, map);
        }

        public void zza(zzaly zzaly, T t) throws IOException {
            if (t == null) {
                zzaly.zzWk();
                return;
            }
            zzaly.zzWi();
            try {
                for (zzb zzb : this.zzbYa.values()) {
                    if (zzb.zzaO(t)) {
                        zzaly.zziT(zzb.name);
                        zzb.zza(zzaly, t);
                    }
                }
                zzaly.zzWj();
            } catch (IllegalAccessException e) {
                throw new AssertionError();
            }
        }
    }

    public zzalq(zzakz zzakz, zzajy zzajy, zzala zzala) {
        this.zzbWa = zzakz;
        this.zzbWl = zzajy;
        this.zzbWj = zzala;
    }

    private zzakr<?> zza(zzajz zzajz, Field field, zzalv<?> zzalv) {
        zzakt zzakt = (zzakt) field.getAnnotation(zzakt.class);
        if (zzakt != null) {
            zzakr zza = zzall.zza(this.zzbWa, zzajz, zzalv, zzakt);
            if (zza != null) {
                return zza;
            }
        }
        return zzajz.zza((zzalv) zzalv);
    }

    private zzb zza(zzajz zzajz, Field field, String str, zzalv<?> zzalv, boolean z, boolean z2) {
        final boolean zzk = zzalf.zzk(zzalv.zzWl());
        final zzajz zzajz2 = zzajz;
        final Field field2 = field;
        final zzalv<?> zzalv2 = zzalv;
        return new zzb(str, z, z2) {
            final zzakr<?> zzbXU = zzalq.this.zza(zzajz2, field2, zzalv2);

            /* Access modifiers changed, original: 0000 */
            public void zza(zzaly zzaly, Object obj) throws IOException, IllegalAccessException {
                new zzalt(zzajz2, this.zzbXU, zzalv2.zzWm()).zza(zzaly, field2.get(obj));
            }

            public boolean zzaO(Object obj) throws IOException, IllegalAccessException {
                return this.zzbYb && field2.get(obj) != obj;
            }
        };
    }

    static List<String> zza(zzajy zzajy, Field field) {
        zzaku zzaku = (zzaku) field.getAnnotation(zzaku.class);
        LinkedList linkedList = new LinkedList();
        if (zzaku == null) {
            linkedList.add(zzajy.zzb(field));
        } else {
            linkedList.add(zzaku.value());
            for (Object add : zzaku.zzVR()) {
                linkedList.add(add);
            }
        }
        return linkedList;
    }

    private Map<String, zzb> zza(zzajz zzajz, zzalv<?> zzalv, Class<?> cls) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type zzWm = zzalv.zzWm();
        Class cls2;
        while (cls2 != Object.class) {
            for (Field field : cls2.getDeclaredFields()) {
                boolean zza = zza(field, true);
                boolean zza2 = zza(field, false);
                if (zza || zza2) {
                    field.setAccessible(true);
                    Type zza3 = zzaky.zza(zzalv.zzWm(), cls2, field.getGenericType());
                    List zzc = zzc(field);
                    zzb zzb = null;
                    int i = 0;
                    while (i < zzc.size()) {
                        String str = (String) zzc.get(i);
                        if (i != 0) {
                            zza = false;
                        }
                        zzb zzb2 = (zzb) linkedHashMap.put(str, zza(zzajz, field, str, zzalv.zzl(zza3), zza, zza2));
                        if (zzb != null) {
                            zzb2 = zzb;
                        }
                        i++;
                        zzb = zzb2;
                    }
                    if (zzb != null) {
                        String valueOf = String.valueOf(zzWm);
                        String str2 = zzb.name;
                        throw new IllegalArgumentException(new StringBuilder((String.valueOf(valueOf).length() + 37) + String.valueOf(str2).length()).append(valueOf).append(" declares multiple JSON fields named ").append(str2).toString());
                    }
                }
            }
            zzalv zzalv2 = zzalv.zzl(zzaky.zza(zzalv2.zzWm(), cls2, cls2.getGenericSuperclass()));
            cls2 = zzalv2.zzWl();
        }
        return linkedHashMap;
    }

    static boolean zza(Field field, boolean z, zzala zzala) {
        return (zzala.zza(field.getType(), z) || zzala.zza(field, z)) ? false : true;
    }

    private List<String> zzc(Field field) {
        return zza(this.zzbWl, field);
    }

    public <T> zzakr<T> zza(zzajz zzajz, zzalv<T> zzalv) {
        Class zzWl = zzalv.zzWl();
        return !Object.class.isAssignableFrom(zzWl) ? null : new zza(this.zzbWa.zzb(zzalv), zza(zzajz, (zzalv) zzalv, zzWl), null);
    }

    public boolean zza(Field field, boolean z) {
        return zza(field, z, this.zzbWj);
    }
}
