package com.ensighten.google.gson;

import com.ensighten.google.gson.internal.ConstructorConstructor;
import com.ensighten.google.gson.internal.Excluder;
import com.ensighten.google.gson.internal.Primitives;
import com.ensighten.google.gson.internal.Streams;
import com.ensighten.google.gson.internal.bind.ArrayTypeAdapter;
import com.ensighten.google.gson.internal.bind.CollectionTypeAdapterFactory;
import com.ensighten.google.gson.internal.bind.DateTypeAdapter;
import com.ensighten.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.ensighten.google.gson.internal.bind.JsonTreeReader;
import com.ensighten.google.gson.internal.bind.JsonTreeWriter;
import com.ensighten.google.gson.internal.bind.MapTypeAdapterFactory;
import com.ensighten.google.gson.internal.bind.ObjectTypeAdapter;
import com.ensighten.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.ensighten.google.gson.internal.bind.SqlDateTypeAdapter;
import com.ensighten.google.gson.internal.bind.TimeTypeAdapter;
import com.ensighten.google.gson.internal.bind.TypeAdapters;
import com.ensighten.google.gson.reflect.TypeToken;
import com.ensighten.google.gson.stream.JsonReader;
import com.ensighten.google.gson.stream.JsonToken;
import com.ensighten.google.gson.stream.JsonWriter;
import com.ensighten.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Gson {
    static final boolean DEFAULT_JSON_NON_EXECUTABLE = false;
    private static final String JSON_NON_EXECUTABLE_PREFIX = ")]}'\n";
    private final ThreadLocal<Map<TypeToken<?>, FutureTypeAdapter<?>>> calls;
    private final ConstructorConstructor constructorConstructor;
    final JsonDeserializationContext deserializationContext;
    private final List<TypeAdapterFactory> factories;
    private final boolean generateNonExecutableJson;
    private final boolean htmlSafe;
    private final boolean prettyPrinting;
    final JsonSerializationContext serializationContext;
    private final boolean serializeNulls;
    private final Map<TypeToken<?>, TypeAdapter<?>> typeTokenCache;

    /* renamed from: com.ensighten.google.gson.Gson$1 */
    class C17621 implements JsonDeserializationContext {
        C17621() {
        }

        public <T> T deserialize(JsonElement json, Type typeOfT) throws JsonParseException {
            return Gson.this.fromJson(json, typeOfT);
        }
    }

    /* renamed from: com.ensighten.google.gson.Gson$2 */
    class C17632 implements JsonSerializationContext {
        C17632() {
        }

        public JsonElement serialize(Object src) {
            return Gson.this.toJsonTree(src);
        }

        public JsonElement serialize(Object src, Type typeOfSrc) {
            return Gson.this.toJsonTree(src, typeOfSrc);
        }
    }

    /* renamed from: com.ensighten.google.gson.Gson$3 */
    class C17653 extends TypeAdapter<Number> {
        C17653() {
        }

        public Double read(JsonReader in) throws IOException {
            if (in.peek() != JsonToken.NULL) {
                return Double.valueOf(in.nextDouble());
            }
            in.nextNull();
            return null;
        }

        public void write(JsonWriter out, Number value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            Gson.this.checkValidFloatingPoint(value.doubleValue());
            out.value(value);
        }
    }

    /* renamed from: com.ensighten.google.gson.Gson$4 */
    class C17664 extends TypeAdapter<Number> {
        C17664() {
        }

        public Float read(JsonReader in) throws IOException {
            if (in.peek() != JsonToken.NULL) {
                return Float.valueOf((float) in.nextDouble());
            }
            in.nextNull();
            return null;
        }

        public void write(JsonWriter out, Number value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            Gson.this.checkValidFloatingPoint((double) value.floatValue());
            out.value(value);
        }
    }

    /* renamed from: com.ensighten.google.gson.Gson$5 */
    class C17675 extends TypeAdapter<Number> {
        C17675() {
        }

        public Number read(JsonReader in) throws IOException {
            if (in.peek() != JsonToken.NULL) {
                return Long.valueOf(in.nextLong());
            }
            in.nextNull();
            return null;
        }

        public void write(JsonWriter out, Number value) throws IOException {
            if (value == null) {
                out.nullValue();
            } else {
                out.value(value.toString());
            }
        }
    }

    static class FutureTypeAdapter<T> extends TypeAdapter<T> {
        private TypeAdapter<T> delegate;

        FutureTypeAdapter() {
        }

        public void setDelegate(TypeAdapter<T> typeAdapter) {
            if (this.delegate != null) {
                throw new AssertionError();
            }
            this.delegate = typeAdapter;
        }

        public T read(JsonReader in) throws IOException {
            if (this.delegate != null) {
                return this.delegate.read(in);
            }
            throw new IllegalStateException();
        }

        public void write(JsonWriter out, T value) throws IOException {
            if (this.delegate == null) {
                throw new IllegalStateException();
            }
            this.delegate.write(out, value);
        }
    }

    public Gson() {
        this(Excluder.DEFAULT, FieldNamingPolicy.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, LongSerializationPolicy.DEFAULT, Collections.emptyList());
    }

    Gson(Excluder excluder, FieldNamingStrategy fieldNamingPolicy, Map<Type, InstanceCreator<?>> instanceCreators, boolean serializeNulls, boolean complexMapKeySerialization, boolean generateNonExecutableGson, boolean htmlSafe, boolean prettyPrinting, boolean serializeSpecialFloatingPointValues, LongSerializationPolicy longSerializationPolicy, List<TypeAdapterFactory> typeAdapterFactories) {
        this.calls = new ThreadLocal();
        this.typeTokenCache = Collections.synchronizedMap(new HashMap());
        this.deserializationContext = new C17621();
        this.serializationContext = new C17632();
        this.constructorConstructor = new ConstructorConstructor(instanceCreators);
        this.serializeNulls = serializeNulls;
        this.generateNonExecutableJson = generateNonExecutableGson;
        this.htmlSafe = htmlSafe;
        this.prettyPrinting = prettyPrinting;
        ArrayList arrayList = new ArrayList();
        arrayList.add(TypeAdapters.JSON_ELEMENT_FACTORY);
        arrayList.add(ObjectTypeAdapter.FACTORY);
        arrayList.add(excluder);
        arrayList.addAll(typeAdapterFactories);
        arrayList.add(TypeAdapters.STRING_FACTORY);
        arrayList.add(TypeAdapters.INTEGER_FACTORY);
        arrayList.add(TypeAdapters.BOOLEAN_FACTORY);
        arrayList.add(TypeAdapters.BYTE_FACTORY);
        arrayList.add(TypeAdapters.SHORT_FACTORY);
        arrayList.add(TypeAdapters.newFactory(Long.TYPE, Long.class, longAdapter(longSerializationPolicy)));
        arrayList.add(TypeAdapters.newFactory(Double.TYPE, Double.class, doubleAdapter(serializeSpecialFloatingPointValues)));
        arrayList.add(TypeAdapters.newFactory(Float.TYPE, Float.class, floatAdapter(serializeSpecialFloatingPointValues)));
        arrayList.add(TypeAdapters.NUMBER_FACTORY);
        arrayList.add(TypeAdapters.CHARACTER_FACTORY);
        arrayList.add(TypeAdapters.STRING_BUILDER_FACTORY);
        arrayList.add(TypeAdapters.STRING_BUFFER_FACTORY);
        arrayList.add(TypeAdapters.newFactory(BigDecimal.class, TypeAdapters.BIG_DECIMAL));
        arrayList.add(TypeAdapters.newFactory(BigInteger.class, TypeAdapters.BIG_INTEGER));
        arrayList.add(TypeAdapters.URL_FACTORY);
        arrayList.add(TypeAdapters.URI_FACTORY);
        arrayList.add(TypeAdapters.UUID_FACTORY);
        arrayList.add(TypeAdapters.LOCALE_FACTORY);
        arrayList.add(TypeAdapters.INET_ADDRESS_FACTORY);
        arrayList.add(TypeAdapters.BIT_SET_FACTORY);
        arrayList.add(DateTypeAdapter.FACTORY);
        arrayList.add(TypeAdapters.CALENDAR_FACTORY);
        arrayList.add(TimeTypeAdapter.FACTORY);
        arrayList.add(SqlDateTypeAdapter.FACTORY);
        arrayList.add(TypeAdapters.TIMESTAMP_FACTORY);
        arrayList.add(ArrayTypeAdapter.FACTORY);
        arrayList.add(TypeAdapters.CLASS_FACTORY);
        arrayList.add(new CollectionTypeAdapterFactory(this.constructorConstructor));
        arrayList.add(new MapTypeAdapterFactory(this.constructorConstructor, complexMapKeySerialization));
        arrayList.add(new JsonAdapterAnnotationTypeAdapterFactory(this.constructorConstructor));
        arrayList.add(TypeAdapters.ENUM_FACTORY);
        arrayList.add(new ReflectiveTypeAdapterFactory(this.constructorConstructor, fieldNamingPolicy, excluder));
        this.factories = Collections.unmodifiableList(arrayList);
    }

    private static void assertFullConsumption(Object obj, JsonReader reader) {
        if (obj != null) {
            try {
                if (reader.peek() != JsonToken.END_DOCUMENT) {
                    throw new JsonIOException("JSON document was not fully consumed.");
                }
            } catch (MalformedJsonException e) {
                throw new JsonSyntaxException(e);
            } catch (IOException e2) {
                throw new JsonIOException(e2);
            }
        }
    }

    private TypeAdapter<Number> doubleAdapter(boolean serializeSpecialFloatingPointValues) {
        if (serializeSpecialFloatingPointValues) {
            return TypeAdapters.DOUBLE;
        }
        return new C17653();
    }

    private TypeAdapter<Number> floatAdapter(boolean serializeSpecialFloatingPointValues) {
        if (serializeSpecialFloatingPointValues) {
            return TypeAdapters.FLOAT;
        }
        return new C17664();
    }

    private void checkValidFloatingPoint(double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException(value + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    private TypeAdapter<Number> longAdapter(LongSerializationPolicy longSerializationPolicy) {
        if (longSerializationPolicy == LongSerializationPolicy.DEFAULT) {
            return TypeAdapters.LONG;
        }
        return new C17675();
    }

    public final <T> TypeAdapter<T> getAdapter(TypeToken<T> type) {
        TypeAdapter<T> typeAdapter = (TypeAdapter) this.typeTokenCache.get(type);
        if (typeAdapter == null) {
            Map map;
            Map map2 = (Map) this.calls.get();
            Object obj = null;
            if (map2 == null) {
                HashMap hashMap = new HashMap();
                this.calls.set(hashMap);
                map = hashMap;
                obj = 1;
            } else {
                map = map2;
            }
            FutureTypeAdapter typeAdapter2 = (FutureTypeAdapter) map.get(type);
            if (typeAdapter2 == null) {
                try {
                    FutureTypeAdapter futureTypeAdapter = new FutureTypeAdapter();
                    map.put(type, futureTypeAdapter);
                    for (TypeAdapterFactory create : this.factories) {
                        typeAdapter2 = create.create(this, type);
                        if (typeAdapter2 != null) {
                            futureTypeAdapter.setDelegate(typeAdapter2);
                            this.typeTokenCache.put(type, typeAdapter2);
                            map.remove(type);
                            if (obj != null) {
                                this.calls.remove();
                            }
                        }
                    }
                    throw new IllegalArgumentException("GSON cannot handle " + type);
                } catch (Throwable th) {
                    map.remove(type);
                    if (obj != null) {
                        this.calls.remove();
                    }
                }
            }
        }
        return typeAdapter2;
    }

    public final <T> TypeAdapter<T> getDelegateAdapter(TypeAdapterFactory skipPast, TypeToken<T> type) {
        Object obj = null;
        if (!this.factories.contains(skipPast)) {
            obj = 1;
        }
        Object obj2 = obj;
        for (TypeAdapterFactory typeAdapterFactory : this.factories) {
            if (obj2 != null) {
                TypeAdapter create = typeAdapterFactory.create(this, type);
                if (create != null) {
                    return create;
                }
            } else if (typeAdapterFactory == skipPast) {
                obj2 = 1;
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + type);
    }

    public final <T> TypeAdapter<T> getAdapter(Class<T> type) {
        return getAdapter(TypeToken.get((Class) type));
    }

    public final JsonElement toJsonTree(Object src) {
        if (src == null) {
            return JsonNull.INSTANCE;
        }
        return toJsonTree(src, src.getClass());
    }

    public final JsonElement toJsonTree(Object src, Type typeOfSrc) {
        JsonWriter jsonTreeWriter = new JsonTreeWriter();
        toJson(src, typeOfSrc, jsonTreeWriter);
        return jsonTreeWriter.get();
    }

    public final String toJson(Object src) {
        if (src == null) {
            return toJson(JsonNull.INSTANCE);
        }
        return toJson(src, src.getClass());
    }

    public final String toJson(Object src, Type typeOfSrc) {
        Appendable stringWriter = new StringWriter();
        toJson(src, typeOfSrc, stringWriter);
        return stringWriter.toString();
    }

    public final void toJson(Object src, Appendable writer) throws JsonIOException {
        if (src != null) {
            toJson(src, src.getClass(), writer);
        } else {
            toJson(JsonNull.INSTANCE, writer);
        }
    }

    public final void toJson(Object src, Type typeOfSrc, Appendable writer) throws JsonIOException {
        try {
            toJson(src, typeOfSrc, newJsonWriter(Streams.writerForAppendable(writer)));
        } catch (IOException e) {
            throw new JsonIOException(e);
        }
    }

    public final void toJson(Object src, Type typeOfSrc, JsonWriter writer) throws JsonIOException {
        TypeAdapter adapter = getAdapter(TypeToken.get(typeOfSrc));
        boolean isLenient = writer.isLenient();
        writer.setLenient(true);
        boolean isHtmlSafe = writer.isHtmlSafe();
        writer.setHtmlSafe(this.htmlSafe);
        boolean serializeNulls = writer.getSerializeNulls();
        writer.setSerializeNulls(this.serializeNulls);
        try {
            adapter.write(writer, src);
            writer.setLenient(isLenient);
            writer.setHtmlSafe(isHtmlSafe);
            writer.setSerializeNulls(serializeNulls);
        } catch (IOException e) {
            throw new JsonIOException(e);
        } catch (Throwable th) {
            writer.setLenient(isLenient);
            writer.setHtmlSafe(isHtmlSafe);
            writer.setSerializeNulls(serializeNulls);
        }
    }

    public final String toJson(JsonElement jsonElement) {
        Appendable stringWriter = new StringWriter();
        toJson(jsonElement, stringWriter);
        return stringWriter.toString();
    }

    public final void toJson(JsonElement jsonElement, Appendable writer) throws JsonIOException {
        try {
            toJson(jsonElement, newJsonWriter(Streams.writerForAppendable(writer)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private JsonWriter newJsonWriter(Writer writer) throws IOException {
        if (this.generateNonExecutableJson) {
            writer.write(JSON_NON_EXECUTABLE_PREFIX);
        }
        JsonWriter jsonWriter = new JsonWriter(writer);
        if (this.prettyPrinting) {
            jsonWriter.setIndent("  ");
        }
        jsonWriter.setSerializeNulls(this.serializeNulls);
        return jsonWriter;
    }

    public final void toJson(JsonElement jsonElement, JsonWriter writer) throws JsonIOException {
        boolean isLenient = writer.isLenient();
        writer.setLenient(true);
        boolean isHtmlSafe = writer.isHtmlSafe();
        writer.setHtmlSafe(this.htmlSafe);
        boolean serializeNulls = writer.getSerializeNulls();
        writer.setSerializeNulls(this.serializeNulls);
        try {
            Streams.write(jsonElement, writer);
            writer.setLenient(isLenient);
            writer.setHtmlSafe(isHtmlSafe);
            writer.setSerializeNulls(serializeNulls);
        } catch (IOException e) {
            throw new JsonIOException(e);
        } catch (Throwable th) {
            writer.setLenient(isLenient);
            writer.setHtmlSafe(isHtmlSafe);
            writer.setSerializeNulls(serializeNulls);
        }
    }

    public final <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
        return Primitives.wrap(classOfT).cast(fromJson(json, (Type) classOfT));
    }

    public final <T> T fromJson(String json, Type typeOfT) throws JsonSyntaxException {
        if (json == null) {
            return null;
        }
        return fromJson(new StringReader(json), typeOfT);
    }

    public final <T> T fromJson(Reader json, Class<T> classOfT) throws JsonSyntaxException, JsonIOException {
        JsonReader jsonReader = new JsonReader(json);
        Object fromJson = fromJson(jsonReader, (Type) classOfT);
        assertFullConsumption(fromJson, jsonReader);
        return Primitives.wrap(classOfT).cast(fromJson);
    }

    public final <T> T fromJson(Reader json, Type typeOfT) throws JsonIOException, JsonSyntaxException {
        JsonReader jsonReader = new JsonReader(json);
        Object fromJson = fromJson(jsonReader, typeOfT);
        assertFullConsumption(fromJson, jsonReader);
        return fromJson;
    }

    public final <T> T fromJson(JsonReader reader, Type typeOfT) throws JsonIOException, JsonSyntaxException {
        boolean z = true;
        boolean isLenient = reader.isLenient();
        reader.setLenient(true);
        try {
            reader.peek();
            z = false;
            Object read = getAdapter(TypeToken.get(typeOfT)).read(reader);
            reader.setLenient(isLenient);
            return read;
        } catch (EOFException e) {
            if (z) {
                reader.setLenient(isLenient);
                return null;
            }
            throw new JsonSyntaxException(e);
        } catch (IllegalStateException e2) {
            throw new JsonSyntaxException(e2);
        } catch (IOException e22) {
            throw new JsonSyntaxException(e22);
        } catch (Throwable th) {
            reader.setLenient(isLenient);
        }
    }

    public final <T> T fromJson(JsonElement json, Class<T> classOfT) throws JsonSyntaxException {
        return Primitives.wrap(classOfT).cast(fromJson(json, (Type) classOfT));
    }

    public final <T> T fromJson(JsonElement json, Type typeOfT) throws JsonSyntaxException {
        if (json == null) {
            return null;
        }
        return fromJson(new JsonTreeReader(json), typeOfT);
    }

    public final String toString() {
        return "{serializeNulls:" + this.serializeNulls + "factories:" + this.factories + ",instanceCreators:" + this.constructorConstructor + "}";
    }
}
