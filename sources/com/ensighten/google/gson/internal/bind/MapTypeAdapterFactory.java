package com.ensighten.google.gson.internal.bind;

import com.ensighten.google.gson.Gson;
import com.ensighten.google.gson.JsonElement;
import com.ensighten.google.gson.JsonPrimitive;
import com.ensighten.google.gson.JsonSyntaxException;
import com.ensighten.google.gson.TypeAdapter;
import com.ensighten.google.gson.TypeAdapterFactory;
import com.ensighten.google.gson.internal.C$Gson$Types;
import com.ensighten.google.gson.internal.ConstructorConstructor;
import com.ensighten.google.gson.internal.JsonReaderInternalAccess;
import com.ensighten.google.gson.internal.ObjectConstructor;
import com.ensighten.google.gson.internal.Streams;
import com.ensighten.google.gson.reflect.TypeToken;
import com.ensighten.google.gson.stream.JsonReader;
import com.ensighten.google.gson.stream.JsonToken;
import com.ensighten.google.gson.stream.JsonWriter;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public final class MapTypeAdapterFactory implements TypeAdapterFactory {
    private final boolean complexMapKeySerialization;
    private final ConstructorConstructor constructorConstructor;

    final class Adapter<K, V> extends TypeAdapter<Map<K, V>> {
        private final ObjectConstructor<? extends Map<K, V>> constructor;
        private final TypeAdapter<K> keyTypeAdapter;
        private final TypeAdapter<V> valueTypeAdapter;

        public Adapter(Gson context, Type keyType, TypeAdapter<K> keyTypeAdapter, Type valueType, TypeAdapter<V> valueTypeAdapter, ObjectConstructor<? extends Map<K, V>> constructor) {
            this.keyTypeAdapter = new TypeAdapterRuntimeTypeWrapper(context, keyTypeAdapter, keyType);
            this.valueTypeAdapter = new TypeAdapterRuntimeTypeWrapper(context, valueTypeAdapter, valueType);
            this.constructor = constructor;
        }

        public final Map<K, V> read(JsonReader in) throws IOException {
            JsonToken peek = in.peek();
            if (peek == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            Map<K, V> map = (Map) this.constructor.construct();
            Object read;
            if (peek == JsonToken.BEGIN_ARRAY) {
                in.beginArray();
                while (in.hasNext()) {
                    in.beginArray();
                    read = this.keyTypeAdapter.read(in);
                    if (map.put(read, this.valueTypeAdapter.read(in)) != null) {
                        throw new JsonSyntaxException("duplicate key: " + read);
                    }
                    in.endArray();
                }
                in.endArray();
                return map;
            }
            in.beginObject();
            while (in.hasNext()) {
                JsonReaderInternalAccess.INSTANCE.promoteNameToValue(in);
                read = this.keyTypeAdapter.read(in);
                if (map.put(read, this.valueTypeAdapter.read(in)) != null) {
                    throw new JsonSyntaxException("duplicate key: " + read);
                }
            }
            in.endObject();
            return map;
        }

        public final void write(JsonWriter out, Map<K, V> map) throws IOException {
            int i = 0;
            if (map == null) {
                out.nullValue();
            } else if (MapTypeAdapterFactory.this.complexMapKeySerialization) {
                ArrayList arrayList = new ArrayList(map.size());
                ArrayList arrayList2 = new ArrayList(map.size());
                int i2 = 0;
                for (Entry entry : map.entrySet()) {
                    int i3;
                    JsonElement toJsonTree = this.keyTypeAdapter.toJsonTree(entry.getKey());
                    arrayList.add(toJsonTree);
                    arrayList2.add(entry.getValue());
                    if (toJsonTree.isJsonArray() || toJsonTree.isJsonObject()) {
                        i3 = 1;
                    } else {
                        i3 = 0;
                    }
                    i2 = i3 | i2;
                }
                if (i2 != 0) {
                    out.beginArray();
                    while (i < arrayList.size()) {
                        out.beginArray();
                        Streams.write((JsonElement) arrayList.get(i), out);
                        this.valueTypeAdapter.write(out, arrayList2.get(i));
                        out.endArray();
                        i++;
                    }
                    out.endArray();
                    return;
                }
                out.beginObject();
                while (i < arrayList.size()) {
                    out.name(keyToString((JsonElement) arrayList.get(i)));
                    this.valueTypeAdapter.write(out, arrayList2.get(i));
                    i++;
                }
                out.endObject();
            } else {
                out.beginObject();
                for (Entry entry2 : map.entrySet()) {
                    out.name(String.valueOf(entry2.getKey()));
                    this.valueTypeAdapter.write(out, entry2.getValue());
                }
                out.endObject();
            }
        }

        private String keyToString(JsonElement keyElement) {
            if (keyElement.isJsonPrimitive()) {
                JsonPrimitive asJsonPrimitive = keyElement.getAsJsonPrimitive();
                if (asJsonPrimitive.isNumber()) {
                    return String.valueOf(asJsonPrimitive.getAsNumber());
                }
                if (asJsonPrimitive.isBoolean()) {
                    return Boolean.toString(asJsonPrimitive.getAsBoolean());
                }
                if (asJsonPrimitive.isString()) {
                    return asJsonPrimitive.getAsString();
                }
                throw new AssertionError();
            } else if (keyElement.isJsonNull()) {
                return SafeJsonPrimitive.NULL_STRING;
            } else {
                throw new AssertionError();
            }
        }
    }

    public MapTypeAdapterFactory(ConstructorConstructor constructorConstructor, boolean complexMapKeySerialization) {
        this.constructorConstructor = constructorConstructor;
        this.complexMapKeySerialization = complexMapKeySerialization;
    }

    public final <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Type type = typeToken.getType();
        if (!Map.class.isAssignableFrom(typeToken.getRawType())) {
            return null;
        }
        Type[] mapKeyAndValueTypes = C$Gson$Types.getMapKeyAndValueTypes(type, C$Gson$Types.getRawType(type));
        TypeAdapter keyAdapter = getKeyAdapter(gson, mapKeyAndValueTypes[0]);
        TypeAdapter adapter = gson.getAdapter(TypeToken.get(mapKeyAndValueTypes[1]));
        ObjectConstructor objectConstructor = this.constructorConstructor.get(typeToken);
        return new Adapter(gson, mapKeyAndValueTypes[0], keyAdapter, mapKeyAndValueTypes[1], adapter, objectConstructor);
    }

    private TypeAdapter<?> getKeyAdapter(Gson context, Type keyType) {
        return (keyType == Boolean.TYPE || keyType == Boolean.class) ? TypeAdapters.BOOLEAN_AS_STRING : context.getAdapter(TypeToken.get(keyType));
    }
}
