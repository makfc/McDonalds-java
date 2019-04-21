package com.google.android.gms.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public final class zzakz {
    private final Map<Type, zzakb<?>> zzbWm;

    /* renamed from: com.google.android.gms.internal.zzakz$10 */
    class C216610 implements zzale<T> {
        C216610() {
        }

        public T zzVT() {
            return new LinkedList();
        }
    }

    /* renamed from: com.google.android.gms.internal.zzakz$11 */
    class C216711 implements zzale<T> {
        C216711() {
        }

        public T zzVT() {
            return new ArrayList();
        }
    }

    /* renamed from: com.google.android.gms.internal.zzakz$12 */
    class C216812 implements zzale<T> {
        C216812() {
        }

        public T zzVT() {
            return new TreeMap();
        }
    }

    /* renamed from: com.google.android.gms.internal.zzakz$2 */
    class C21702 implements zzale<T> {
        C21702() {
        }

        public T zzVT() {
            return new LinkedHashMap();
        }
    }

    /* renamed from: com.google.android.gms.internal.zzakz$3 */
    class C21713 implements zzale<T> {
        C21713() {
        }

        public T zzVT() {
            return new zzald();
        }
    }

    /* renamed from: com.google.android.gms.internal.zzakz$7 */
    class C21757 implements zzale<T> {
        C21757() {
        }

        public T zzVT() {
            return new TreeSet();
        }
    }

    /* renamed from: com.google.android.gms.internal.zzakz$9 */
    class C21779 implements zzale<T> {
        C21779() {
        }

        public T zzVT() {
            return new LinkedHashSet();
        }
    }

    public zzakz(Map<Type, zzakb<?>> map) {
        this.zzbWm = map;
    }

    private <T> zzale<T> zzc(final Type type, Class<? super T> cls) {
        return Collection.class.isAssignableFrom(cls) ? SortedSet.class.isAssignableFrom(cls) ? new C21757() : EnumSet.class.isAssignableFrom(cls) ? new zzale<T>() {
            public T zzVT() {
                String str;
                String valueOf;
                if (type instanceof ParameterizedType) {
                    Type type = ((ParameterizedType) type).getActualTypeArguments()[0];
                    if (type instanceof Class) {
                        return EnumSet.noneOf((Class) type);
                    }
                    str = "Invalid EnumSet type: ";
                    valueOf = String.valueOf(type.toString());
                    throw new zzakg(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                }
                str = "Invalid EnumSet type: ";
                valueOf = String.valueOf(type.toString());
                throw new zzakg(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
        } : Set.class.isAssignableFrom(cls) ? new C21779() : Queue.class.isAssignableFrom(cls) ? new C216610() : new C216711() : Map.class.isAssignableFrom(cls) ? SortedMap.class.isAssignableFrom(cls) ? new C216812() : (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(zzalv.zzl(((ParameterizedType) type).getActualTypeArguments()[0]).zzWl())) ? new C21713() : new C21702() : null;
    }

    private <T> zzale<T> zzd(final Type type, final Class<? super T> cls) {
        return new zzale<T>() {
            private final zzalh zzbWP = zzalh.zzVY();

            public T zzVT() {
                try {
                    return this.zzbWP.zzf(cls);
                } catch (Exception e) {
                    String valueOf = String.valueOf(type);
                    throw new RuntimeException(new StringBuilder(String.valueOf(valueOf).length() + 116).append("Unable to invoke no-args constructor for ").append(valueOf).append(". ").append("Register an InstanceCreator with Gson for this type may fix this problem.").toString(), e);
                }
            }
        };
    }

    private <T> zzale<T> zzl(Class<? super T> cls) {
        try {
            final Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return new zzale<T>() {
                public T zzVT() {
                    String valueOf;
                    try {
                        return declaredConstructor.newInstance(null);
                    } catch (InstantiationException e) {
                        valueOf = String.valueOf(declaredConstructor);
                        throw new RuntimeException(new StringBuilder(String.valueOf(valueOf).length() + 30).append("Failed to invoke ").append(valueOf).append(" with no args").toString(), e);
                    } catch (InvocationTargetException e2) {
                        valueOf = String.valueOf(declaredConstructor);
                        throw new RuntimeException(new StringBuilder(String.valueOf(valueOf).length() + 30).append("Failed to invoke ").append(valueOf).append(" with no args").toString(), e2.getTargetException());
                    } catch (IllegalAccessException e3) {
                        throw new AssertionError(e3);
                    }
                }
            };
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public String toString() {
        return this.zzbWm.toString();
    }

    public <T> zzale<T> zzb(zzalv<T> zzalv) {
        final Type zzWm = zzalv.zzWm();
        Class zzWl = zzalv.zzWl();
        final zzakb zzakb = (zzakb) this.zzbWm.get(zzWm);
        if (zzakb != null) {
            return new zzale<T>() {
                public T zzVT() {
                    return zzakb.zza(zzWm);
                }
            };
        }
        zzakb = (zzakb) this.zzbWm.get(zzWl);
        if (zzakb != null) {
            return new zzale<T>() {
                public T zzVT() {
                    return zzakb.zza(zzWm);
                }
            };
        }
        zzale zzl = zzl(zzWl);
        if (zzl != null) {
            return zzl;
        }
        zzl = zzc(zzWm, zzWl);
        return zzl == null ? zzd(zzWm, zzWl) : zzl;
    }
}
