package com.ensighten.google.gson.internal.bind;

import com.ensighten.google.gson.Gson;
import com.ensighten.google.gson.TypeAdapter;
import com.ensighten.google.gson.TypeAdapterFactory;
import com.ensighten.google.gson.internal.C$Gson$Types;
import com.ensighten.google.gson.reflect.TypeToken;
import com.ensighten.google.gson.stream.JsonReader;
import com.ensighten.google.gson.stream.JsonToken;
import com.ensighten.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;

public final class ArrayTypeAdapter<E> extends TypeAdapter<Object> {
    public static final TypeAdapterFactory FACTORY = new C17951();
    private final Class<E> componentType;
    private final TypeAdapter<E> componentTypeAdapter;

    /* renamed from: com.ensighten.google.gson.internal.bind.ArrayTypeAdapter$1 */
    static class C17951 implements TypeAdapterFactory {
        C17951() {
        }

        public final <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            Type type = typeToken.getType();
            if (!(type instanceof GenericArrayType) && (!(type instanceof Class) || !((Class) type).isArray())) {
                return null;
            }
            type = C$Gson$Types.getArrayComponentType(type);
            return new ArrayTypeAdapter(gson, gson.getAdapter(TypeToken.get(type)), C$Gson$Types.getRawType(type));
        }
    }

    public ArrayTypeAdapter(Gson context, TypeAdapter<E> componentTypeAdapter, Class<E> componentType) {
        this.componentTypeAdapter = new TypeAdapterRuntimeTypeWrapper(context, componentTypeAdapter, componentType);
        this.componentType = componentType;
    }

    public final Object read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        in.beginArray();
        while (in.hasNext()) {
            arrayList.add(this.componentTypeAdapter.read(in));
        }
        in.endArray();
        Object newInstance = Array.newInstance(this.componentType, arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            Array.set(newInstance, i, arrayList.get(i));
        }
        return newInstance;
    }

    public final void write(JsonWriter out, Object array) throws IOException {
        if (array == null) {
            out.nullValue();
            return;
        }
        out.beginArray();
        int length = Array.getLength(array);
        for (int i = 0; i < length; i++) {
            this.componentTypeAdapter.write(out, Array.get(array, i));
        }
        out.endArray();
    }
}
