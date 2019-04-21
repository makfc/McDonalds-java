package com.ensighten.google.gson.internal.bind;

import com.ensighten.google.gson.Gson;
import com.ensighten.google.gson.TypeAdapter;
import com.ensighten.google.gson.internal.bind.ReflectiveTypeAdapterFactory.Adapter;
import com.ensighten.google.gson.reflect.TypeToken;
import com.ensighten.google.gson.stream.JsonReader;
import com.ensighten.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

final class TypeAdapterRuntimeTypeWrapper<T> extends TypeAdapter<T> {
    private final Gson context;
    private final TypeAdapter<T> delegate;
    private final Type type;

    TypeAdapterRuntimeTypeWrapper(Gson context, TypeAdapter<T> delegate, Type type) {
        this.context = context;
        this.delegate = delegate;
        this.type = type;
    }

    public final T read(JsonReader in) throws IOException {
        return this.delegate.read(in);
    }

    public final void write(JsonWriter out, T value) throws IOException {
        TypeAdapter typeAdapter = this.delegate;
        Type runtimeTypeIfMoreSpecific = getRuntimeTypeIfMoreSpecific(this.type, value);
        if (runtimeTypeIfMoreSpecific != this.type) {
            typeAdapter = this.context.getAdapter(TypeToken.get(runtimeTypeIfMoreSpecific));
            if ((typeAdapter instanceof Adapter) && !(this.delegate instanceof Adapter)) {
                typeAdapter = this.delegate;
            }
        }
        typeAdapter.write(out, value);
    }

    private Type getRuntimeTypeIfMoreSpecific(Type type, Object value) {
        if (value == null) {
            return type;
        }
        if (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class)) {
            return value.getClass();
        }
        return type;
    }
}
