package com.google.android.gms.internal;

import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.sql.Timestamp;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

public final class zzalu {
    public static final zzakr<String> zzbYA = new C22255();
    public static final zzakr<BigDecimal> zzbYB = new C22266();
    public static final zzakr<BigInteger> zzbYC = new C22277();
    public static final zzaks zzbYD = zza(String.class, zzbYA);
    public static final zzakr<StringBuilder> zzbYE = new C22288();
    public static final zzaks zzbYF = zza(StringBuilder.class, zzbYE);
    public static final zzakr<StringBuffer> zzbYG = new C22299();
    public static final zzaks zzbYH = zza(StringBuffer.class, zzbYG);
    public static final zzakr<URL> zzbYI = new C219710();
    public static final zzaks zzbYJ = zza(URL.class, zzbYI);
    public static final zzakr<URI> zzbYK = new C219811();
    public static final zzaks zzbYL = zza(URI.class, zzbYK);
    public static final zzakr<InetAddress> zzbYM = new C220013();
    public static final zzaks zzbYN = zzb(InetAddress.class, zzbYM);
    public static final zzakr<UUID> zzbYO = new C220114();
    public static final zzaks zzbYP = zza(UUID.class, zzbYO);
    public static final zzaks zzbYQ = new C220315();
    public static final zzakr<Calendar> zzbYR = new C220416();
    public static final zzaks zzbYS = zzb(Calendar.class, GregorianCalendar.class, zzbYR);
    public static final zzakr<Locale> zzbYT = new C220517();
    public static final zzaks zzbYU = zza(Locale.class, zzbYT);
    public static final zzakr<zzakf> zzbYV = new C220618();
    public static final zzaks zzbYW = zzb(zzakf.class, zzbYV);
    public static final zzaks zzbYX = new C220719();
    public static final zzakr<Class> zzbYg = new C22081();
    public static final zzaks zzbYh = zza(Class.class, zzbYg);
    public static final zzakr<BitSet> zzbYi = new C219912();
    public static final zzaks zzbYj = zza(BitSet.class, zzbYi);
    public static final zzakr<Boolean> zzbYk = new C221223();
    public static final zzakr<Boolean> zzbYl = new C221627();
    public static final zzaks zzbYm = zza(Boolean.TYPE, Boolean.class, zzbYk);
    public static final zzakr<Number> zzbYn = new C221728();
    public static final zzaks zzbYo = zza(Byte.TYPE, Byte.class, zzbYn);
    public static final zzakr<Number> zzbYp = new C221829();
    public static final zzaks zzbYq = zza(Short.TYPE, Short.class, zzbYp);
    public static final zzakr<Number> zzbYr = new C222030();
    public static final zzaks zzbYs = zza(Integer.TYPE, Integer.class, zzbYr);
    public static final zzakr<Number> zzbYt = new C222131();
    public static final zzakr<Number> zzbYu = new C222232();
    public static final zzakr<Number> zzbYv = new C22192();
    public static final zzakr<Number> zzbYw = new C22233();
    public static final zzaks zzbYx = zza(Number.class, zzbYw);
    public static final zzakr<Character> zzbYy = new C22244();
    public static final zzaks zzbYz = zza(Character.TYPE, Character.class, zzbYy);

    /* renamed from: com.google.android.gms.internal.zzalu$10 */
    static class C219710 extends zzakr<URL> {
        C219710() {
        }

        public void zza(zzaly zzaly, URL url) throws IOException {
            zzaly.zziU(url == null ? null : url.toExternalForm());
        }
    }

    /* renamed from: com.google.android.gms.internal.zzalu$11 */
    static class C219811 extends zzakr<URI> {
        C219811() {
        }

        public void zza(zzaly zzaly, URI uri) throws IOException {
            zzaly.zziU(uri == null ? null : uri.toASCIIString());
        }
    }

    /* renamed from: com.google.android.gms.internal.zzalu$12 */
    static class C219912 extends zzakr<BitSet> {
        C219912() {
        }

        public void zza(zzaly zzaly, BitSet bitSet) throws IOException {
            if (bitSet == null) {
                zzaly.zzWk();
                return;
            }
            zzaly.zzWg();
            for (int i = 0; i < bitSet.length(); i++) {
                zzaly.zzaN((long) (bitSet.get(i) ? 1 : 0));
            }
            zzaly.zzWh();
        }
    }

    /* renamed from: com.google.android.gms.internal.zzalu$13 */
    static class C220013 extends zzakr<InetAddress> {
        C220013() {
        }

        public void zza(zzaly zzaly, InetAddress inetAddress) throws IOException {
            zzaly.zziU(inetAddress == null ? null : inetAddress.getHostAddress());
        }
    }

    /* renamed from: com.google.android.gms.internal.zzalu$14 */
    static class C220114 extends zzakr<UUID> {
        C220114() {
        }

        public void zza(zzaly zzaly, UUID uuid) throws IOException {
            zzaly.zziU(uuid == null ? null : uuid.toString());
        }
    }

    /* renamed from: com.google.android.gms.internal.zzalu$15 */
    static class C220315 implements zzaks {
        C220315() {
        }

        public <T> zzakr<T> zza(zzajz zzajz, zzalv<T> zzalv) {
            if (zzalv.zzWl() != Timestamp.class) {
                return null;
            }
            final zzakr zzk = zzajz.zzk(Date.class);
            return new zzakr<Timestamp>() {
                public void zza(zzaly zzaly, Timestamp timestamp) throws IOException {
                    zzk.zza(zzaly, timestamp);
                }
            };
        }
    }

    /* renamed from: com.google.android.gms.internal.zzalu$16 */
    static class C220416 extends zzakr<Calendar> {
        C220416() {
        }

        public void zza(zzaly zzaly, Calendar calendar) throws IOException {
            if (calendar == null) {
                zzaly.zzWk();
                return;
            }
            zzaly.zzWi();
            zzaly.zziT("year");
            zzaly.zzaN((long) calendar.get(1));
            zzaly.zziT("month");
            zzaly.zzaN((long) calendar.get(2));
            zzaly.zziT("dayOfMonth");
            zzaly.zzaN((long) calendar.get(5));
            zzaly.zziT("hourOfDay");
            zzaly.zzaN((long) calendar.get(11));
            zzaly.zziT("minute");
            zzaly.zzaN((long) calendar.get(12));
            zzaly.zziT("second");
            zzaly.zzaN((long) calendar.get(13));
            zzaly.zzWj();
        }
    }

    /* renamed from: com.google.android.gms.internal.zzalu$17 */
    static class C220517 extends zzakr<Locale> {
        C220517() {
        }

        public void zza(zzaly zzaly, Locale locale) throws IOException {
            zzaly.zziU(locale == null ? null : locale.toString());
        }
    }

    /* renamed from: com.google.android.gms.internal.zzalu$18 */
    static class C220618 extends zzakr<zzakf> {
        C220618() {
        }

        public void zza(zzaly zzaly, zzakf zzakf) throws IOException {
            if (zzakf == null || zzakf.zzVI()) {
                zzaly.zzWk();
            } else if (zzakf.zzVH()) {
                zzakl zzVL = zzakf.zzVL();
                if (zzVL.zzVO()) {
                    zzaly.zza(zzVL.zzVz());
                } else if (zzVL.zzVN()) {
                    zzaly.zzaX(zzVL.zzVE());
                } else {
                    zzaly.zziU(zzVL.zzVA());
                }
            } else if (zzakf.zzVF()) {
                zzaly.zzWg();
                Iterator it = zzakf.zzVK().iterator();
                while (it.hasNext()) {
                    zza(zzaly, (zzakf) it.next());
                }
                zzaly.zzWh();
            } else if (zzakf.zzVG()) {
                zzaly.zzWi();
                for (Entry entry : zzakf.zzVJ().entrySet()) {
                    zzaly.zziT((String) entry.getKey());
                    zza(zzaly, (zzakf) entry.getValue());
                }
                zzaly.zzWj();
            } else {
                String valueOf = String.valueOf(zzakf.getClass());
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 15).append("Couldn't write ").append(valueOf).toString());
            }
        }
    }

    /* renamed from: com.google.android.gms.internal.zzalu$19 */
    static class C220719 implements zzaks {
        C220719() {
        }

        public <T> zzakr<T> zza(zzajz zzajz, zzalv<T> zzalv) {
            Class zzWl = zzalv.zzWl();
            if (!Enum.class.isAssignableFrom(zzWl) || zzWl == Enum.class) {
                return null;
            }
            if (!zzWl.isEnum()) {
                zzWl = zzWl.getSuperclass();
            }
            return new zza(zzWl);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzalu$1 */
    static class C22081 extends zzakr<Class> {
        C22081() {
        }

        public void zza(zzaly zzaly, Class cls) throws IOException {
            if (cls == null) {
                zzaly.zzWk();
            } else {
                String valueOf = String.valueOf(cls.getName());
                throw new UnsupportedOperationException(new StringBuilder(String.valueOf(valueOf).length() + 76).append("Attempted to serialize java.lang.Class: ").append(valueOf).append(". Forgot to register a type adapter?").toString());
            }
        }
    }

    /* renamed from: com.google.android.gms.internal.zzalu$20 */
    static class C220920 implements zzaks {
        final /* synthetic */ zzalv zzbXc;
        final /* synthetic */ zzakr zzbZa;

        public <T> zzakr<T> zza(zzajz zzajz, zzalv<T> zzalv) {
            return zzalv.equals(this.zzbXc) ? this.zzbZa : null;
        }
    }

    /* renamed from: com.google.android.gms.internal.zzalu$23 */
    static class C221223 extends zzakr<Boolean> {
        C221223() {
        }

        public void zza(zzaly zzaly, Boolean bool) throws IOException {
            if (bool == null) {
                zzaly.zzWk();
            } else {
                zzaly.zzaX(bool.booleanValue());
            }
        }
    }

    /* renamed from: com.google.android.gms.internal.zzalu$26 */
    static /* synthetic */ class C221526 {
        static final /* synthetic */ int[] zzbXT = new int[zzalx.values().length];

        static {
            try {
                zzbXT[zzalx.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                zzbXT[zzalx.BOOLEAN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                zzbXT[zzalx.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                zzbXT[zzalx.NULL.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                zzbXT[zzalx.BEGIN_ARRAY.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                zzbXT[zzalx.BEGIN_OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                zzbXT[zzalx.END_DOCUMENT.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                zzbXT[zzalx.NAME.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                zzbXT[zzalx.END_OBJECT.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                zzbXT[zzalx.END_ARRAY.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
        }
    }

    /* renamed from: com.google.android.gms.internal.zzalu$27 */
    static class C221627 extends zzakr<Boolean> {
        C221627() {
        }

        public void zza(zzaly zzaly, Boolean bool) throws IOException {
            zzaly.zziU(bool == null ? SafeJsonPrimitive.NULL_STRING : bool.toString());
        }
    }

    /* renamed from: com.google.android.gms.internal.zzalu$28 */
    static class C221728 extends zzakr<Number> {
        C221728() {
        }

        public void zza(zzaly zzaly, Number number) throws IOException {
            zzaly.zza(number);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzalu$29 */
    static class C221829 extends zzakr<Number> {
        C221829() {
        }

        public void zza(zzaly zzaly, Number number) throws IOException {
            zzaly.zza(number);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzalu$2 */
    static class C22192 extends zzakr<Number> {
        C22192() {
        }

        public void zza(zzaly zzaly, Number number) throws IOException {
            zzaly.zza(number);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzalu$30 */
    static class C222030 extends zzakr<Number> {
        C222030() {
        }

        public void zza(zzaly zzaly, Number number) throws IOException {
            zzaly.zza(number);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzalu$31 */
    static class C222131 extends zzakr<Number> {
        C222131() {
        }

        public void zza(zzaly zzaly, Number number) throws IOException {
            zzaly.zza(number);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzalu$32 */
    static class C222232 extends zzakr<Number> {
        C222232() {
        }

        public void zza(zzaly zzaly, Number number) throws IOException {
            zzaly.zza(number);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzalu$3 */
    static class C22233 extends zzakr<Number> {
        C22233() {
        }

        public void zza(zzaly zzaly, Number number) throws IOException {
            zzaly.zza(number);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzalu$4 */
    static class C22244 extends zzakr<Character> {
        C22244() {
        }

        public void zza(zzaly zzaly, Character ch) throws IOException {
            zzaly.zziU(ch == null ? null : String.valueOf(ch));
        }
    }

    /* renamed from: com.google.android.gms.internal.zzalu$5 */
    static class C22255 extends zzakr<String> {
        C22255() {
        }

        public void zza(zzaly zzaly, String str) throws IOException {
            zzaly.zziU(str);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzalu$6 */
    static class C22266 extends zzakr<BigDecimal> {
        C22266() {
        }

        public void zza(zzaly zzaly, BigDecimal bigDecimal) throws IOException {
            zzaly.zza(bigDecimal);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzalu$7 */
    static class C22277 extends zzakr<BigInteger> {
        C22277() {
        }

        public void zza(zzaly zzaly, BigInteger bigInteger) throws IOException {
            zzaly.zza(bigInteger);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzalu$8 */
    static class C22288 extends zzakr<StringBuilder> {
        C22288() {
        }

        public void zza(zzaly zzaly, StringBuilder stringBuilder) throws IOException {
            zzaly.zziU(stringBuilder == null ? null : stringBuilder.toString());
        }
    }

    /* renamed from: com.google.android.gms.internal.zzalu$9 */
    static class C22299 extends zzakr<StringBuffer> {
        C22299() {
        }

        public void zza(zzaly zzaly, StringBuffer stringBuffer) throws IOException {
            zzaly.zziU(stringBuffer == null ? null : stringBuffer.toString());
        }
    }

    private static final class zza<T extends Enum<T>> extends zzakr<T> {
        private final Map<String, T> zzbZh = new HashMap();
        private final Map<T, String> zzbZi = new HashMap();

        public zza(Class<T> cls) {
            try {
                for (Enum enumR : (Enum[]) cls.getEnumConstants()) {
                    String name = enumR.name();
                    zzaku zzaku = (zzaku) cls.getField(name).getAnnotation(zzaku.class);
                    if (zzaku != null) {
                        name = zzaku.value();
                        for (Object put : zzaku.zzVR()) {
                            this.zzbZh.put(put, enumR);
                        }
                    }
                    String str = name;
                    this.zzbZh.put(str, enumR);
                    this.zzbZi.put(enumR, str);
                }
            } catch (NoSuchFieldException e) {
                throw new AssertionError();
            }
        }

        public void zza(zzaly zzaly, T t) throws IOException {
            zzaly.zziU(t == null ? null : (String) this.zzbZi.get(t));
        }
    }

    public static <TT> zzaks zza(final Class<TT> cls, final zzakr<TT> zzakr) {
        return new zzaks() {
            public String toString() {
                String valueOf = String.valueOf(cls.getName());
                String valueOf2 = String.valueOf(zzakr);
                return new StringBuilder((String.valueOf(valueOf).length() + 23) + String.valueOf(valueOf2).length()).append("Factory[type=").append(valueOf).append(",adapter=").append(valueOf2).append("]").toString();
            }

            public <T> zzakr<T> zza(zzajz zzajz, zzalv<T> zzalv) {
                return zzalv.zzWl() == cls ? zzakr : null;
            }
        };
    }

    public static <TT> zzaks zza(final Class<TT> cls, final Class<TT> cls2, final zzakr<? super TT> zzakr) {
        return new zzaks() {
            public String toString() {
                String valueOf = String.valueOf(cls2.getName());
                String valueOf2 = String.valueOf(cls.getName());
                String valueOf3 = String.valueOf(zzakr);
                return new StringBuilder(((String.valueOf(valueOf).length() + 24) + String.valueOf(valueOf2).length()) + String.valueOf(valueOf3).length()).append("Factory[type=").append(valueOf).append("+").append(valueOf2).append(",adapter=").append(valueOf3).append("]").toString();
            }

            public <T> zzakr<T> zza(zzajz zzajz, zzalv<T> zzalv) {
                Class zzWl = zzalv.zzWl();
                return (zzWl == cls || zzWl == cls2) ? zzakr : null;
            }
        };
    }

    public static <TT> zzaks zzb(final Class<TT> cls, final zzakr<TT> zzakr) {
        return new zzaks() {
            public String toString() {
                String valueOf = String.valueOf(cls.getName());
                String valueOf2 = String.valueOf(zzakr);
                return new StringBuilder((String.valueOf(valueOf).length() + 32) + String.valueOf(valueOf2).length()).append("Factory[typeHierarchy=").append(valueOf).append(",adapter=").append(valueOf2).append("]").toString();
            }

            public <T> zzakr<T> zza(zzajz zzajz, zzalv<T> zzalv) {
                return cls.isAssignableFrom(zzalv.zzWl()) ? zzakr : null;
            }
        };
    }

    public static <TT> zzaks zzb(final Class<TT> cls, final Class<? extends TT> cls2, final zzakr<? super TT> zzakr) {
        return new zzaks() {
            public String toString() {
                String valueOf = String.valueOf(cls.getName());
                String valueOf2 = String.valueOf(cls2.getName());
                String valueOf3 = String.valueOf(zzakr);
                return new StringBuilder(((String.valueOf(valueOf).length() + 24) + String.valueOf(valueOf2).length()) + String.valueOf(valueOf3).length()).append("Factory[type=").append(valueOf).append("+").append(valueOf2).append(",adapter=").append(valueOf3).append("]").toString();
            }

            public <T> zzakr<T> zza(zzajz zzajz, zzalv<T> zzalv) {
                Class zzWl = zzalv.zzWl();
                return (zzWl == cls || zzWl == cls2) ? zzakr : null;
            }
        };
    }
}
