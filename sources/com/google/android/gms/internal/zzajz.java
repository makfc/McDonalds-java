package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzajz {
    private final ThreadLocal<Map<zzalv<?>, zza<?>>> zzbVX;
    private final Map<zzalv<?>, zzakr<?>> zzbVY;
    private final List<zzaks> zzbVZ;
    private final zzakz zzbWa;
    private final boolean zzbWb;
    private final boolean zzbWc;
    private final boolean zzbWd;
    private final boolean zzbWe;
    final zzakd zzbWf;
    final zzakm zzbWg;

    /* renamed from: com.google.android.gms.internal.zzajz$1 */
    class C21581 implements zzakd {
        C21581() {
        }
    }

    /* renamed from: com.google.android.gms.internal.zzajz$2 */
    class C21592 implements zzakm {
        C21592() {
        }
    }

    /* renamed from: com.google.android.gms.internal.zzajz$3 */
    class C21603 extends zzakr<Number> {
        C21603() {
        }

        public void zza(zzaly zzaly, Number number) throws IOException {
            if (number == null) {
                zzaly.zzWk();
                return;
            }
            zzajz.this.zzo(number.doubleValue());
            zzaly.zza(number);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzajz$4 */
    class C21614 extends zzakr<Number> {
        C21614() {
        }

        public void zza(zzaly zzaly, Number number) throws IOException {
            if (number == null) {
                zzaly.zzWk();
                return;
            }
            zzajz.this.zzo((double) number.floatValue());
            zzaly.zza(number);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzajz$5 */
    class C21625 extends zzakr<Number> {
        C21625() {
        }

        public void zza(zzaly zzaly, Number number) throws IOException {
            if (number == null) {
                zzaly.zzWk();
            } else {
                zzaly.zziU(number.toString());
            }
        }
    }

    static class zza<T> extends zzakr<T> {
        private zzakr<T> zzbWi;

        zza() {
        }

        public void zza(zzakr<T> zzakr) {
            if (this.zzbWi != null) {
                throw new AssertionError();
            }
            this.zzbWi = zzakr;
        }

        public void zza(zzaly zzaly, T t) throws IOException {
            if (this.zzbWi == null) {
                throw new IllegalStateException();
            }
            this.zzbWi.zza(zzaly, t);
        }
    }

    public zzajz() {
        this(zzala.zzbWT, zzajx.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, zzakp.DEFAULT, Collections.emptyList());
    }

    zzajz(zzala zzala, zzajy zzajy, Map<Type, zzakb<?>> map, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, zzakp zzakp, List<zzaks> list) {
        this.zzbVX = new ThreadLocal();
        this.zzbVY = Collections.synchronizedMap(new HashMap());
        this.zzbWf = new C21581();
        this.zzbWg = new C21592();
        this.zzbWa = new zzakz(map);
        this.zzbWb = z;
        this.zzbWd = z3;
        this.zzbWc = z4;
        this.zzbWe = z5;
        ArrayList arrayList = new ArrayList();
        arrayList.add(zzalu.zzbYW);
        arrayList.add(zzalp.zzbXD);
        arrayList.add(zzala);
        arrayList.addAll(list);
        arrayList.add(zzalu.zzbYD);
        arrayList.add(zzalu.zzbYs);
        arrayList.add(zzalu.zzbYm);
        arrayList.add(zzalu.zzbYo);
        arrayList.add(zzalu.zzbYq);
        arrayList.add(zzalu.zza(Long.TYPE, Long.class, zza(zzakp)));
        arrayList.add(zzalu.zza(Double.TYPE, Double.class, zzaV(z6)));
        arrayList.add(zzalu.zza(Float.TYPE, Float.class, zzaW(z6)));
        arrayList.add(zzalu.zzbYx);
        arrayList.add(zzalu.zzbYz);
        arrayList.add(zzalu.zzbYF);
        arrayList.add(zzalu.zzbYH);
        arrayList.add(zzalu.zza(BigDecimal.class, zzalu.zzbYB));
        arrayList.add(zzalu.zza(BigInteger.class, zzalu.zzbYC));
        arrayList.add(zzalu.zzbYJ);
        arrayList.add(zzalu.zzbYL);
        arrayList.add(zzalu.zzbYP);
        arrayList.add(zzalu.zzbYU);
        arrayList.add(zzalu.zzbYN);
        arrayList.add(zzalu.zzbYj);
        arrayList.add(zzalk.zzbXD);
        arrayList.add(zzalu.zzbYS);
        arrayList.add(zzals.zzbXD);
        arrayList.add(zzalr.zzbXD);
        arrayList.add(zzalu.zzbYQ);
        arrayList.add(zzali.zzbXD);
        arrayList.add(zzalu.zzbYh);
        arrayList.add(new zzalj(this.zzbWa));
        arrayList.add(new zzalo(this.zzbWa, z2));
        arrayList.add(new zzall(this.zzbWa));
        arrayList.add(zzalu.zzbYX);
        arrayList.add(new zzalq(this.zzbWa, zzajy, zzala));
        this.zzbVZ = Collections.unmodifiableList(arrayList);
    }

    private zzakr<Number> zza(zzakp zzakp) {
        return zzakp == zzakp.DEFAULT ? zzalu.zzbYt : new C21625();
    }

    private zzakr<Number> zzaV(boolean z) {
        return z ? zzalu.zzbYv : new C21603();
    }

    private zzakr<Number> zzaW(boolean z) {
        return z ? zzalu.zzbYu : new C21614();
    }

    private void zzo(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new IllegalArgumentException(d + " is not a valid double value as per JSON specification. To override this" + " behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    public String toString() {
        return "{serializeNulls:" + this.zzbWb + "factories:" + this.zzbVZ + ",instanceCreators:" + this.zzbWa + "}";
    }

    public <T> zzakr<T> zza(zzaks zzaks, zzalv<T> zzalv) {
        Object obj = null;
        if (!this.zzbVZ.contains(zzaks)) {
            obj = 1;
        }
        Object obj2 = obj;
        for (zzaks zzaks2 : this.zzbVZ) {
            if (obj2 != null) {
                zzakr zza = zzaks2.zza(this, zzalv);
                if (zza != null) {
                    return zza;
                }
            } else if (zzaks2 == zzaks) {
                obj2 = 1;
            }
        }
        String valueOf = String.valueOf(zzalv);
        throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 22).append("GSON cannot serialize ").append(valueOf).toString());
    }

    public <T> zzakr<T> zza(zzalv<T> zzalv) {
        zzakr<T> zzakr = (zzakr) this.zzbVY.get(zzalv);
        if (zzakr == null) {
            Map map;
            Map map2 = (Map) this.zzbVX.get();
            Object obj = null;
            if (map2 == null) {
                HashMap hashMap = new HashMap();
                this.zzbVX.set(hashMap);
                map = hashMap;
                obj = 1;
            } else {
                map = map2;
            }
            zza zzakr2 = (zza) map.get(zzalv);
            if (zzakr2 == null) {
                try {
                    zza zza = new zza();
                    map.put(zzalv, zza);
                    for (zzaks zza2 : this.zzbVZ) {
                        zzakr2 = zza2.zza(this, zzalv);
                        if (zzakr2 != null) {
                            zza.zza(zzakr2);
                            this.zzbVY.put(zzalv, zzakr2);
                            map.remove(zzalv);
                            if (obj != null) {
                                this.zzbVX.remove();
                            }
                        }
                    }
                    String valueOf = String.valueOf(zzalv);
                    throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 19).append("GSON cannot handle ").append(valueOf).toString());
                } catch (Throwable th) {
                    map.remove(zzalv);
                    if (obj != null) {
                        this.zzbVX.remove();
                    }
                }
            }
        }
        return zzakr2;
    }

    public <T> zzakr<T> zzk(Class<T> cls) {
        return zza(zzalv.zzr(cls));
    }
}
