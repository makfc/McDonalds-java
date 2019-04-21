package com.ensighten.google.gson;

import com.ensighten.google.gson.internal.C$Gson$Preconditions;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;

public final class FieldAttributes {
    private final Field field;

    public FieldAttributes(Field f) {
        C$Gson$Preconditions.checkNotNull(f);
        this.field = f;
    }

    public final Class<?> getDeclaringClass() {
        return this.field.getDeclaringClass();
    }

    public final String getName() {
        return this.field.getName();
    }

    public final Type getDeclaredType() {
        return this.field.getGenericType();
    }

    public final Class<?> getDeclaredClass() {
        return this.field.getType();
    }

    public final <T extends Annotation> T getAnnotation(Class<T> annotation) {
        return this.field.getAnnotation(annotation);
    }

    public final Collection<Annotation> getAnnotations() {
        return Arrays.asList(this.field.getAnnotations());
    }

    public final boolean hasModifier(int modifier) {
        return (this.field.getModifiers() & modifier) != 0;
    }

    /* Access modifiers changed, original: final */
    public final Object get(Object instance) throws IllegalAccessException {
        return this.field.get(instance);
    }

    /* Access modifiers changed, original: final */
    public final boolean isSynthetic() {
        return this.field.isSynthetic();
    }
}
