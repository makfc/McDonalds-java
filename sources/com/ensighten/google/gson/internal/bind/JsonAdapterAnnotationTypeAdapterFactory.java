package com.ensighten.google.gson.internal.bind;

import com.ensighten.google.gson.Gson;
import com.ensighten.google.gson.TypeAdapter;
import com.ensighten.google.gson.TypeAdapterFactory;
import com.ensighten.google.gson.annotations.JsonAdapter;
import com.ensighten.google.gson.internal.ConstructorConstructor;
import com.ensighten.google.gson.reflect.TypeToken;

public final class JsonAdapterAnnotationTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor constructorConstructor;

    public JsonAdapterAnnotationTypeAdapterFactory(ConstructorConstructor constructorConstructor) {
        this.constructorConstructor = constructorConstructor;
    }

    static TypeAdapter<?> getTypeAdapter(ConstructorConstructor constructorConstructor, Gson gson, TypeToken<?> fieldType, JsonAdapter annotation) {
        Class value = annotation.value();
        if (TypeAdapter.class.isAssignableFrom(value)) {
            return (TypeAdapter) constructorConstructor.get(TypeToken.get(value)).construct();
        }
        if (TypeAdapterFactory.class.isAssignableFrom(value)) {
            return ((TypeAdapterFactory) constructorConstructor.get(TypeToken.get(value)).construct()).create(gson, fieldType);
        }
        throw new IllegalArgumentException("@JsonAdapter value must be TypeAdapter or TypeAdapterFactory reference.");
    }

    public final <T> TypeAdapter<T> create(Gson gson, TypeToken<T> targetType) {
        JsonAdapter jsonAdapter = (JsonAdapter) targetType.getRawType().getAnnotation(JsonAdapter.class);
        if (jsonAdapter == null) {
            return null;
        }
        return getTypeAdapter(this.constructorConstructor, gson, targetType, jsonAdapter);
    }
}
