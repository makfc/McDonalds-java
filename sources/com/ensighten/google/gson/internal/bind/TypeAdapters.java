package com.ensighten.google.gson.internal.bind;

import com.ensighten.google.gson.Gson;
import com.ensighten.google.gson.JsonArray;
import com.ensighten.google.gson.JsonElement;
import com.ensighten.google.gson.JsonIOException;
import com.ensighten.google.gson.JsonNull;
import com.ensighten.google.gson.JsonObject;
import com.ensighten.google.gson.JsonPrimitive;
import com.ensighten.google.gson.JsonSyntaxException;
import com.ensighten.google.gson.TypeAdapter;
import com.ensighten.google.gson.TypeAdapterFactory;
import com.ensighten.google.gson.annotations.SerializedName;
import com.ensighten.google.gson.internal.LazilyParsedNumber;
import com.ensighten.google.gson.reflect.TypeToken;
import com.ensighten.google.gson.stream.JsonReader;
import com.ensighten.google.gson.stream.JsonToken;
import com.ensighten.google.gson.stream.JsonWriter;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
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
import java.util.StringTokenizer;
import java.util.UUID;

public final class TypeAdapters {
    public static final TypeAdapter<BigDecimal> BIG_DECIMAL = new C180914();
    public static final TypeAdapter<BigInteger> BIG_INTEGER = new C181015();
    public static final TypeAdapter<BitSet> BIT_SET = new C18272();
    public static final TypeAdapterFactory BIT_SET_FACTORY = newFactory(BitSet.class, BIT_SET);
    public static final TypeAdapter<Boolean> BOOLEAN = new C18313();
    public static final TypeAdapter<Boolean> BOOLEAN_AS_STRING = new C18324();
    public static final TypeAdapterFactory BOOLEAN_FACTORY = newFactory(Boolean.TYPE, Boolean.class, BOOLEAN);
    public static final TypeAdapter<Number> BYTE = new C18335();
    public static final TypeAdapterFactory BYTE_FACTORY = newFactory(Byte.TYPE, Byte.class, BYTE);
    public static final TypeAdapter<Calendar> CALENDAR = new C182023();
    public static final TypeAdapterFactory CALENDAR_FACTORY = newFactoryForMultipleTypes(Calendar.class, GregorianCalendar.class, CALENDAR);
    public static final TypeAdapter<Character> CHARACTER = new C180712();
    public static final TypeAdapterFactory CHARACTER_FACTORY = newFactory(Character.TYPE, Character.class, CHARACTER);
    public static final TypeAdapter<Class> CLASS = new C18151();
    public static final TypeAdapterFactory CLASS_FACTORY = newFactory(Class.class, CLASS);
    public static final TypeAdapter<Number> DOUBLE = new C180510();
    public static final TypeAdapterFactory ENUM_FACTORY = new C182326();
    public static final TypeAdapter<Number> FLOAT = new C18379();
    public static final TypeAdapter<InetAddress> INET_ADDRESS = new C181620();
    public static final TypeAdapterFactory INET_ADDRESS_FACTORY = newTypeHierarchyFactory(InetAddress.class, INET_ADDRESS);
    public static final TypeAdapter<Number> INTEGER = new C18357();
    public static final TypeAdapterFactory INTEGER_FACTORY = newFactory(Integer.TYPE, Integer.class, INTEGER);
    public static final TypeAdapter<JsonElement> JSON_ELEMENT = new C182225();
    public static final TypeAdapterFactory JSON_ELEMENT_FACTORY = newTypeHierarchyFactory(JsonElement.class, JSON_ELEMENT);
    public static final TypeAdapter<Locale> LOCALE = new C182124();
    public static final TypeAdapterFactory LOCALE_FACTORY = newFactory(Locale.class, LOCALE);
    public static final TypeAdapter<Number> LONG = new C18368();
    public static final TypeAdapter<Number> NUMBER = new C180611();
    public static final TypeAdapterFactory NUMBER_FACTORY = newFactory(Number.class, NUMBER);
    public static final TypeAdapter<Number> SHORT = new C18346();
    public static final TypeAdapterFactory SHORT_FACTORY = newFactory(Short.TYPE, Short.class, SHORT);
    public static final TypeAdapter<String> STRING = new C180813();
    public static final TypeAdapter<StringBuffer> STRING_BUFFER = new C181217();
    public static final TypeAdapterFactory STRING_BUFFER_FACTORY = newFactory(StringBuffer.class, STRING_BUFFER);
    public static final TypeAdapter<StringBuilder> STRING_BUILDER = new C181116();
    public static final TypeAdapterFactory STRING_BUILDER_FACTORY = newFactory(StringBuilder.class, STRING_BUILDER);
    public static final TypeAdapterFactory STRING_FACTORY = newFactory(String.class, STRING);
    public static final TypeAdapterFactory TIMESTAMP_FACTORY = new C181922();
    public static final TypeAdapter<URI> URI = new C181419();
    public static final TypeAdapterFactory URI_FACTORY = newFactory(URI.class, URI);
    public static final TypeAdapter<URL> URL = new C181318();
    public static final TypeAdapterFactory URL_FACTORY = newFactory(URL.class, URL);
    public static final TypeAdapter<UUID> UUID = new C181721();
    public static final TypeAdapterFactory UUID_FACTORY = newFactory(UUID.class, UUID);

    /* renamed from: com.ensighten.google.gson.internal.bind.TypeAdapters$10 */
    static class C180510 extends TypeAdapter<Number> {
        C180510() {
        }

        public final Number read(JsonReader in) throws IOException {
            if (in.peek() != JsonToken.NULL) {
                return Double.valueOf(in.nextDouble());
            }
            in.nextNull();
            return null;
        }

        public final void write(JsonWriter out, Number value) throws IOException {
            out.value(value);
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.bind.TypeAdapters$11 */
    static class C180611 extends TypeAdapter<Number> {
        C180611() {
        }

        public final Number read(JsonReader in) throws IOException {
            JsonToken peek = in.peek();
            switch (C183032.$SwitchMap$com$ensighten$google$gson$stream$JsonToken[peek.ordinal()]) {
                case 1:
                    return new LazilyParsedNumber(in.nextString());
                case 4:
                    in.nextNull();
                    return null;
                default:
                    throw new JsonSyntaxException("Expecting number, got: " + peek);
            }
        }

        public final void write(JsonWriter out, Number value) throws IOException {
            out.value(value);
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.bind.TypeAdapters$12 */
    static class C180712 extends TypeAdapter<Character> {
        C180712() {
        }

        public final Character read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            String nextString = in.nextString();
            if (nextString.length() == 1) {
                return Character.valueOf(nextString.charAt(0));
            }
            throw new JsonSyntaxException("Expecting character, got: " + nextString);
        }

        public final void write(JsonWriter out, Character value) throws IOException {
            out.value(value == null ? null : String.valueOf(value));
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.bind.TypeAdapters$13 */
    static class C180813 extends TypeAdapter<String> {
        C180813() {
        }

        public final String read(JsonReader in) throws IOException {
            JsonToken peek = in.peek();
            if (peek == JsonToken.NULL) {
                in.nextNull();
                return null;
            } else if (peek == JsonToken.BOOLEAN) {
                return Boolean.toString(in.nextBoolean());
            } else {
                return in.nextString();
            }
        }

        public final void write(JsonWriter out, String value) throws IOException {
            out.value(value);
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.bind.TypeAdapters$14 */
    static class C180914 extends TypeAdapter<BigDecimal> {
        C180914() {
        }

        public final BigDecimal read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            try {
                return new BigDecimal(in.nextString());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        public final void write(JsonWriter out, BigDecimal value) throws IOException {
            out.value((Number) value);
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.bind.TypeAdapters$15 */
    static class C181015 extends TypeAdapter<BigInteger> {
        C181015() {
        }

        public final BigInteger read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            try {
                return new BigInteger(in.nextString());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        public final void write(JsonWriter out, BigInteger value) throws IOException {
            out.value((Number) value);
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.bind.TypeAdapters$16 */
    static class C181116 extends TypeAdapter<StringBuilder> {
        C181116() {
        }

        public final StringBuilder read(JsonReader in) throws IOException {
            if (in.peek() != JsonToken.NULL) {
                return new StringBuilder(in.nextString());
            }
            in.nextNull();
            return null;
        }

        public final void write(JsonWriter out, StringBuilder value) throws IOException {
            out.value(value == null ? null : value.toString());
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.bind.TypeAdapters$17 */
    static class C181217 extends TypeAdapter<StringBuffer> {
        C181217() {
        }

        public final StringBuffer read(JsonReader in) throws IOException {
            if (in.peek() != JsonToken.NULL) {
                return new StringBuffer(in.nextString());
            }
            in.nextNull();
            return null;
        }

        public final void write(JsonWriter out, StringBuffer value) throws IOException {
            out.value(value == null ? null : value.toString());
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.bind.TypeAdapters$18 */
    static class C181318 extends TypeAdapter<URL> {
        C181318() {
        }

        public final URL read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            String nextString = in.nextString();
            if (SafeJsonPrimitive.NULL_STRING.equals(nextString)) {
                return null;
            }
            return new URL(nextString);
        }

        public final void write(JsonWriter out, URL value) throws IOException {
            out.value(value == null ? null : value.toExternalForm());
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.bind.TypeAdapters$19 */
    static class C181419 extends TypeAdapter<URI> {
        C181419() {
        }

        public final URI read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            try {
                String nextString = in.nextString();
                if (SafeJsonPrimitive.NULL_STRING.equals(nextString)) {
                    return null;
                }
                return new URI(nextString);
            } catch (URISyntaxException e) {
                throw new JsonIOException(e);
            }
        }

        public final void write(JsonWriter out, URI value) throws IOException {
            out.value(value == null ? null : value.toASCIIString());
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.bind.TypeAdapters$1 */
    static class C18151 extends TypeAdapter<Class> {
        C18151() {
        }

        public final void write(JsonWriter out, Class value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + value.getName() + ". Forgot to register a type adapter?");
        }

        public final Class read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.bind.TypeAdapters$20 */
    static class C181620 extends TypeAdapter<InetAddress> {
        C181620() {
        }

        public final InetAddress read(JsonReader in) throws IOException {
            if (in.peek() != JsonToken.NULL) {
                return InetAddress.getByName(in.nextString());
            }
            in.nextNull();
            return null;
        }

        public final void write(JsonWriter out, InetAddress value) throws IOException {
            out.value(value == null ? null : value.getHostAddress());
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.bind.TypeAdapters$21 */
    static class C181721 extends TypeAdapter<UUID> {
        C181721() {
        }

        public final UUID read(JsonReader in) throws IOException {
            if (in.peek() != JsonToken.NULL) {
                return UUID.fromString(in.nextString());
            }
            in.nextNull();
            return null;
        }

        public final void write(JsonWriter out, UUID value) throws IOException {
            out.value(value == null ? null : value.toString());
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.bind.TypeAdapters$22 */
    static class C181922 implements TypeAdapterFactory {
        C181922() {
        }

        public final <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (typeToken.getRawType() != Timestamp.class) {
                return null;
            }
            final TypeAdapter adapter = gson.getAdapter(Date.class);
            return new TypeAdapter<Timestamp>() {
                public Timestamp read(JsonReader in) throws IOException {
                    Date date = (Date) adapter.read(in);
                    return date != null ? new Timestamp(date.getTime()) : null;
                }

                public void write(JsonWriter out, Timestamp value) throws IOException {
                    adapter.write(out, value);
                }
            };
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.bind.TypeAdapters$23 */
    static class C182023 extends TypeAdapter<Calendar> {
        private static final String DAY_OF_MONTH = "dayOfMonth";
        private static final String HOUR_OF_DAY = "hourOfDay";
        private static final String MINUTE = "minute";
        private static final String MONTH = "month";
        private static final String SECOND = "second";
        private static final String YEAR = "year";

        C182023() {
        }

        public final Calendar read(JsonReader in) throws IOException {
            int i = 0;
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            in.beginObject();
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (in.peek() != JsonToken.END_OBJECT) {
                String nextName = in.nextName();
                int nextInt = in.nextInt();
                if (YEAR.equals(nextName)) {
                    i6 = nextInt;
                } else if (MONTH.equals(nextName)) {
                    i5 = nextInt;
                } else if (DAY_OF_MONTH.equals(nextName)) {
                    i4 = nextInt;
                } else if (HOUR_OF_DAY.equals(nextName)) {
                    i3 = nextInt;
                } else if (MINUTE.equals(nextName)) {
                    i2 = nextInt;
                } else if (SECOND.equals(nextName)) {
                    i = nextInt;
                }
            }
            in.endObject();
            return new GregorianCalendar(i6, i5, i4, i3, i2, i);
        }

        public final void write(JsonWriter out, Calendar value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.beginObject();
            out.name(YEAR);
            out.value((long) value.get(1));
            out.name(MONTH);
            out.value((long) value.get(2));
            out.name(DAY_OF_MONTH);
            out.value((long) value.get(5));
            out.name(HOUR_OF_DAY);
            out.value((long) value.get(11));
            out.name(MINUTE);
            out.value((long) value.get(12));
            out.name(SECOND);
            out.value((long) value.get(13));
            out.endObject();
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.bind.TypeAdapters$24 */
    static class C182124 extends TypeAdapter<Locale> {
        C182124() {
        }

        public final Locale read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            String nextToken;
            String nextToken2;
            String nextToken3;
            StringTokenizer stringTokenizer = new StringTokenizer(in.nextString(), "_");
            if (stringTokenizer.hasMoreElements()) {
                nextToken = stringTokenizer.nextToken();
            } else {
                nextToken = null;
            }
            if (stringTokenizer.hasMoreElements()) {
                nextToken2 = stringTokenizer.nextToken();
            } else {
                nextToken2 = null;
            }
            if (stringTokenizer.hasMoreElements()) {
                nextToken3 = stringTokenizer.nextToken();
            } else {
                nextToken3 = null;
            }
            if (nextToken2 == null && nextToken3 == null) {
                return new Locale(nextToken);
            }
            if (nextToken3 == null) {
                return new Locale(nextToken, nextToken2);
            }
            return new Locale(nextToken, nextToken2, nextToken3);
        }

        public final void write(JsonWriter out, Locale value) throws IOException {
            out.value(value == null ? null : value.toString());
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.bind.TypeAdapters$25 */
    static class C182225 extends TypeAdapter<JsonElement> {
        C182225() {
        }

        public final JsonElement read(JsonReader in) throws IOException {
            JsonElement jsonArray;
            switch (C183032.$SwitchMap$com$ensighten$google$gson$stream$JsonToken[in.peek().ordinal()]) {
                case 1:
                    return new JsonPrimitive(new LazilyParsedNumber(in.nextString()));
                case 2:
                    return new JsonPrimitive(Boolean.valueOf(in.nextBoolean()));
                case 3:
                    return new JsonPrimitive(in.nextString());
                case 4:
                    in.nextNull();
                    return JsonNull.INSTANCE;
                case 5:
                    jsonArray = new JsonArray();
                    in.beginArray();
                    while (in.hasNext()) {
                        jsonArray.add(read(in));
                    }
                    in.endArray();
                    return jsonArray;
                case 6:
                    jsonArray = new JsonObject();
                    in.beginObject();
                    while (in.hasNext()) {
                        jsonArray.add(in.nextName(), read(in));
                    }
                    in.endObject();
                    return jsonArray;
                default:
                    throw new IllegalArgumentException();
            }
        }

        public final void write(JsonWriter out, JsonElement value) throws IOException {
            if (value == null || value.isJsonNull()) {
                out.nullValue();
            } else if (value.isJsonPrimitive()) {
                JsonPrimitive asJsonPrimitive = value.getAsJsonPrimitive();
                if (asJsonPrimitive.isNumber()) {
                    out.value(asJsonPrimitive.getAsNumber());
                } else if (asJsonPrimitive.isBoolean()) {
                    out.value(asJsonPrimitive.getAsBoolean());
                } else {
                    out.value(asJsonPrimitive.getAsString());
                }
            } else if (value.isJsonArray()) {
                out.beginArray();
                Iterator it = value.getAsJsonArray().iterator();
                while (it.hasNext()) {
                    write(out, (JsonElement) it.next());
                }
                out.endArray();
            } else if (value.isJsonObject()) {
                out.beginObject();
                for (Entry entry : value.getAsJsonObject().entrySet()) {
                    out.name((String) entry.getKey());
                    write(out, (JsonElement) entry.getValue());
                }
                out.endObject();
            } else {
                throw new IllegalArgumentException("Couldn't write " + value.getClass());
            }
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.bind.TypeAdapters$26 */
    static class C182326 implements TypeAdapterFactory {
        C182326() {
        }

        public final <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            Class rawType = typeToken.getRawType();
            if (!Enum.class.isAssignableFrom(rawType) || rawType == Enum.class) {
                return null;
            }
            if (!rawType.isEnum()) {
                rawType = rawType.getSuperclass();
            }
            return new EnumTypeAdapter(rawType);
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.bind.TypeAdapters$2 */
    static class C18272 extends TypeAdapter<BitSet> {
        C18272() {
        }

        public final BitSet read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            BitSet bitSet = new BitSet();
            in.beginArray();
            JsonToken peek = in.peek();
            int i = 0;
            while (peek != JsonToken.END_ARRAY) {
                boolean z;
                switch (C183032.$SwitchMap$com$ensighten$google$gson$stream$JsonToken[peek.ordinal()]) {
                    case 1:
                        if (in.nextInt() == 0) {
                            z = false;
                            break;
                        }
                        z = true;
                        break;
                    case 2:
                        z = in.nextBoolean();
                        break;
                    case 3:
                        String nextString = in.nextString();
                        try {
                            if (Integer.parseInt(nextString) == 0) {
                                z = false;
                                break;
                            }
                            z = true;
                            break;
                        } catch (NumberFormatException e) {
                            throw new JsonSyntaxException("Error: Expecting: bitset number value (1, 0), Found: " + nextString);
                        }
                    default:
                        throw new JsonSyntaxException("Invalid bitset value type: " + peek);
                }
                if (z) {
                    bitSet.set(i);
                }
                i++;
                peek = in.peek();
            }
            in.endArray();
            return bitSet;
        }

        public final void write(JsonWriter out, BitSet src) throws IOException {
            if (src == null) {
                out.nullValue();
                return;
            }
            out.beginArray();
            for (int i = 0; i < src.length(); i++) {
                int i2;
                if (src.get(i)) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                out.value((long) i2);
            }
            out.endArray();
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.bind.TypeAdapters$32 */
    static /* synthetic */ class C183032 {
        static final /* synthetic */ int[] $SwitchMap$com$ensighten$google$gson$stream$JsonToken = new int[JsonToken.values().length];

        static {
            try {
                $SwitchMap$com$ensighten$google$gson$stream$JsonToken[JsonToken.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$ensighten$google$gson$stream$JsonToken[JsonToken.BOOLEAN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$ensighten$google$gson$stream$JsonToken[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$ensighten$google$gson$stream$JsonToken[JsonToken.NULL.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$ensighten$google$gson$stream$JsonToken[JsonToken.BEGIN_ARRAY.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$ensighten$google$gson$stream$JsonToken[JsonToken.BEGIN_OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$ensighten$google$gson$stream$JsonToken[JsonToken.END_DOCUMENT.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$ensighten$google$gson$stream$JsonToken[JsonToken.NAME.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$ensighten$google$gson$stream$JsonToken[JsonToken.END_OBJECT.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$ensighten$google$gson$stream$JsonToken[JsonToken.END_ARRAY.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.bind.TypeAdapters$3 */
    static class C18313 extends TypeAdapter<Boolean> {
        C18313() {
        }

        public final Boolean read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            } else if (in.peek() == JsonToken.STRING) {
                return Boolean.valueOf(Boolean.parseBoolean(in.nextString()));
            } else {
                return Boolean.valueOf(in.nextBoolean());
            }
        }

        public final void write(JsonWriter out, Boolean value) throws IOException {
            if (value == null) {
                out.nullValue();
            } else {
                out.value(value.booleanValue());
            }
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.bind.TypeAdapters$4 */
    static class C18324 extends TypeAdapter<Boolean> {
        C18324() {
        }

        public final Boolean read(JsonReader in) throws IOException {
            if (in.peek() != JsonToken.NULL) {
                return Boolean.valueOf(in.nextString());
            }
            in.nextNull();
            return null;
        }

        public final void write(JsonWriter out, Boolean value) throws IOException {
            out.value(value == null ? SafeJsonPrimitive.NULL_STRING : value.toString());
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.bind.TypeAdapters$5 */
    static class C18335 extends TypeAdapter<Number> {
        C18335() {
        }

        public final Number read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            try {
                return Byte.valueOf((byte) in.nextInt());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        public final void write(JsonWriter out, Number value) throws IOException {
            out.value(value);
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.bind.TypeAdapters$6 */
    static class C18346 extends TypeAdapter<Number> {
        C18346() {
        }

        public final Number read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            try {
                return Short.valueOf((short) in.nextInt());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        public final void write(JsonWriter out, Number value) throws IOException {
            out.value(value);
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.bind.TypeAdapters$7 */
    static class C18357 extends TypeAdapter<Number> {
        C18357() {
        }

        public final Number read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            try {
                return Integer.valueOf(in.nextInt());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        public final void write(JsonWriter out, Number value) throws IOException {
            out.value(value);
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.bind.TypeAdapters$8 */
    static class C18368 extends TypeAdapter<Number> {
        C18368() {
        }

        public final Number read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            try {
                return Long.valueOf(in.nextLong());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        public final void write(JsonWriter out, Number value) throws IOException {
            out.value(value);
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.bind.TypeAdapters$9 */
    static class C18379 extends TypeAdapter<Number> {
        C18379() {
        }

        public final Number read(JsonReader in) throws IOException {
            if (in.peek() != JsonToken.NULL) {
                return Float.valueOf((float) in.nextDouble());
            }
            in.nextNull();
            return null;
        }

        public final void write(JsonWriter out, Number value) throws IOException {
            out.value(value);
        }
    }

    static final class EnumTypeAdapter<T extends Enum<T>> extends TypeAdapter<T> {
        private final Map<T, String> constantToName = new HashMap();
        private final Map<String, T> nameToConstant = new HashMap();

        public EnumTypeAdapter(Class<T> classOfT) {
            try {
                for (Enum enumR : (Enum[]) classOfT.getEnumConstants()) {
                    Object value;
                    String name = enumR.name();
                    SerializedName serializedName = (SerializedName) classOfT.getField(name).getAnnotation(SerializedName.class);
                    if (serializedName != null) {
                        value = serializedName.value();
                    } else {
                        String value2 = name;
                    }
                    this.nameToConstant.put(value2, enumR);
                    this.constantToName.put(enumR, value2);
                }
            } catch (NoSuchFieldException e) {
                throw new AssertionError();
            }
        }

        public final T read(JsonReader in) throws IOException {
            if (in.peek() != JsonToken.NULL) {
                return (Enum) this.nameToConstant.get(in.nextString());
            }
            in.nextNull();
            return null;
        }

        public final void write(JsonWriter out, T value) throws IOException {
            out.value(value == null ? null : (String) this.constantToName.get(value));
        }
    }

    private TypeAdapters() {
    }

    public static <TT> TypeAdapterFactory newFactory(final TypeToken<TT> type, final TypeAdapter<TT> typeAdapter) {
        return new TypeAdapterFactory() {
            public final <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                return typeToken.equals(type) ? typeAdapter : null;
            }
        };
    }

    public static <TT> TypeAdapterFactory newFactory(final Class<TT> type, final TypeAdapter<TT> typeAdapter) {
        return new TypeAdapterFactory() {
            public final <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                return typeToken.getRawType() == type ? typeAdapter : null;
            }

            public final String toString() {
                return "Factory[type=" + type.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }

    public static <TT> TypeAdapterFactory newFactory(final Class<TT> unboxed, final Class<TT> boxed, final TypeAdapter<? super TT> typeAdapter) {
        return new TypeAdapterFactory() {
            public final <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                Class rawType = typeToken.getRawType();
                return (rawType == unboxed || rawType == boxed) ? typeAdapter : null;
            }

            public final String toString() {
                return "Factory[type=" + boxed.getName() + "+" + unboxed.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }

    public static <TT> TypeAdapterFactory newFactoryForMultipleTypes(final Class<TT> base, final Class<? extends TT> sub, final TypeAdapter<? super TT> typeAdapter) {
        return new TypeAdapterFactory() {
            public final <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                Class rawType = typeToken.getRawType();
                return (rawType == base || rawType == sub) ? typeAdapter : null;
            }

            public final String toString() {
                return "Factory[type=" + base.getName() + "+" + sub.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }

    public static <TT> TypeAdapterFactory newTypeHierarchyFactory(final Class<TT> clazz, final TypeAdapter<TT> typeAdapter) {
        return new TypeAdapterFactory() {
            public final <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                return clazz.isAssignableFrom(typeToken.getRawType()) ? typeAdapter : null;
            }

            public final String toString() {
                return "Factory[typeHierarchy=" + clazz.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }
}
