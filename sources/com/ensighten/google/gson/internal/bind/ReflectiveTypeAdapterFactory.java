package com.ensighten.google.gson.internal.bind;

import com.ensighten.google.gson.FieldNamingStrategy;
import com.ensighten.google.gson.Gson;
import com.ensighten.google.gson.JsonSyntaxException;
import com.ensighten.google.gson.TypeAdapter;
import com.ensighten.google.gson.TypeAdapterFactory;
import com.ensighten.google.gson.annotations.JsonAdapter;
import com.ensighten.google.gson.annotations.SerializedName;
import com.ensighten.google.gson.internal.C$Gson$Types;
import com.ensighten.google.gson.internal.ConstructorConstructor;
import com.ensighten.google.gson.internal.Excluder;
import com.ensighten.google.gson.internal.ObjectConstructor;
import com.ensighten.google.gson.internal.Primitives;
import com.ensighten.google.gson.reflect.TypeToken;
import com.ensighten.google.gson.stream.JsonReader;
import com.ensighten.google.gson.stream.JsonToken;
import com.ensighten.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ReflectiveTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor constructorConstructor;
    private final Excluder excluder;
    private final FieldNamingStrategy fieldNamingPolicy;

    static abstract class BoundField {
        final boolean deserialized;
        final String name;
        final boolean serialized;

        public abstract void read(JsonReader jsonReader, Object obj) throws IOException, IllegalAccessException;

        public abstract void write(JsonWriter jsonWriter, Object obj) throws IOException, IllegalAccessException;

        public abstract boolean writeField(Object obj) throws IOException, IllegalAccessException;

        protected BoundField(String name, boolean serialized, boolean deserialized) {
            this.name = name;
            this.serialized = serialized;
            this.deserialized = deserialized;
        }
    }

    public static final class Adapter<T> extends TypeAdapter<T> {
        private final Map<String, BoundField> boundFields;
        private final ObjectConstructor<T> constructor;

        /* synthetic */ Adapter(ObjectConstructor x0, Map x1, C18021 x2) {
            this(x0, x1);
        }

        private Adapter(ObjectConstructor<T> constructor, Map<String, BoundField> boundFields) {
            this.constructor = constructor;
            this.boundFields = boundFields;
        }

        public final T read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            T construct = this.constructor.construct();
            try {
                in.beginObject();
                while (in.hasNext()) {
                    BoundField boundField = (BoundField) this.boundFields.get(in.nextName());
                    if (boundField == null || !boundField.deserialized) {
                        in.skipValue();
                    } else {
                        boundField.read(in, construct);
                    }
                }
                in.endObject();
                return construct;
            } catch (IllegalStateException e) {
                throw new JsonSyntaxException(e);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }

        public final void write(JsonWriter out, T value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.beginObject();
            try {
                for (BoundField boundField : this.boundFields.values()) {
                    if (boundField.writeField(value)) {
                        out.name(boundField.name);
                        boundField.write(out, value);
                    }
                }
                out.endObject();
            } catch (IllegalAccessException e) {
                throw new AssertionError();
            }
        }
    }

    public ReflectiveTypeAdapterFactory(ConstructorConstructor constructorConstructor, FieldNamingStrategy fieldNamingPolicy, Excluder excluder) {
        this.constructorConstructor = constructorConstructor;
        this.fieldNamingPolicy = fieldNamingPolicy;
        this.excluder = excluder;
    }

    static boolean excludeField(Field f, boolean serialize, Excluder excluder) {
        return (excluder.excludeClass(f.getType(), serialize) || excluder.excludeField(f, serialize)) ? false : true;
    }

    static String getFieldName(FieldNamingStrategy fieldNamingPolicy, Field f) {
        SerializedName serializedName = (SerializedName) f.getAnnotation(SerializedName.class);
        return serializedName == null ? fieldNamingPolicy.translateName(f) : serializedName.value();
    }

    public final boolean excludeField(Field f, boolean serialize) {
        return excludeField(f, serialize, this.excluder);
    }

    private String getFieldName(Field f) {
        return getFieldName(this.fieldNamingPolicy, f);
    }

    public final <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class rawType = type.getRawType();
        if (Object.class.isAssignableFrom(rawType)) {
            return new Adapter(this.constructorConstructor.get(type), getBoundFields(gson, type, rawType), null);
        }
        return null;
    }

    private BoundField createBoundField(Gson context, Field field, String name, TypeToken<?> fieldType, boolean serialize, boolean deserialize) {
        final boolean isPrimitive = Primitives.isPrimitive(fieldType.getRawType());
        final Gson gson = context;
        final Field field2 = field;
        final TypeToken<?> typeToken = fieldType;
        return new BoundField(name, serialize, deserialize) {
            final TypeAdapter<?> typeAdapter = ReflectiveTypeAdapterFactory.this.getFieldAdapter(gson, field2, typeToken);

            /* Access modifiers changed, original: 0000 */
            public void write(JsonWriter writer, Object value) throws IOException, IllegalAccessException {
                new TypeAdapterRuntimeTypeWrapper(gson, this.typeAdapter, typeToken.getType()).write(writer, field2.get(value));
            }

            /* Access modifiers changed, original: 0000 */
            public void read(JsonReader reader, Object value) throws IOException, IllegalAccessException {
                Object read = this.typeAdapter.read(reader);
                if (read != null || !isPrimitive) {
                    field2.set(value, read);
                }
            }

            public boolean writeField(Object value) throws IOException, IllegalAccessException {
                if (this.serialized && field2.get(value) != value) {
                    return true;
                }
                return false;
            }
        };
    }

    private TypeAdapter<?> getFieldAdapter(Gson gson, Field field, TypeToken<?> fieldType) {
        JsonAdapter jsonAdapter = (JsonAdapter) field.getAnnotation(JsonAdapter.class);
        if (jsonAdapter != null) {
            TypeAdapter typeAdapter = JsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(this.constructorConstructor, gson, fieldType, jsonAdapter);
            if (typeAdapter != null) {
                return typeAdapter;
            }
        }
        return gson.getAdapter((TypeToken) fieldType);
    }

    private Map<String, BoundField> getBoundFields(Gson context, TypeToken<?> type, Class<?> raw) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (raw.isInterface()) {
            return linkedHashMap;
        }
        Type type2 = type.getType();
        while (raw != Object.class) {
            for (Field field : raw.getDeclaredFields()) {
                boolean excludeField = excludeField(field, true);
                boolean excludeField2 = excludeField(field, false);
                if (excludeField || excludeField2) {
                    field.setAccessible(true);
                    BoundField createBoundField = createBoundField(context, field, getFieldName(field), TypeToken.get(C$Gson$Types.resolve(type.getType(), raw, field.getGenericType())), excludeField, excludeField2);
                    createBoundField = (BoundField) linkedHashMap.put(createBoundField.name, createBoundField);
                    if (createBoundField != null) {
                        throw new IllegalArgumentException(type2 + " declares multiple JSON fields named " + createBoundField.name);
                    }
                }
            }
            type = TypeToken.get(C$Gson$Types.resolve(type.getType(), raw, raw.getGenericSuperclass()));
            raw = type.getRawType();
        }
        return linkedHashMap;
    }
}
