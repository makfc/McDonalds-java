package com.ensighten.google.gson.reflect;

import com.ensighten.google.gson.internal.C$Gson$Preconditions;
import com.ensighten.google.gson.internal.C$Gson$Types;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

public class TypeToken<T> {
    final int hashCode;
    final Class<? super T> rawType;
    final Type type;

    protected TypeToken() {
        this.type = getSuperclassTypeParameter(getClass());
        this.rawType = C$Gson$Types.getRawType(this.type);
        this.hashCode = this.type.hashCode();
    }

    TypeToken(Type type) {
        this.type = C$Gson$Types.canonicalize((Type) C$Gson$Preconditions.checkNotNull(type));
        this.rawType = C$Gson$Types.getRawType(this.type);
        this.hashCode = this.type.hashCode();
    }

    static Type getSuperclassTypeParameter(Class<?> subclass) {
        Type genericSuperclass = subclass.getGenericSuperclass();
        if (!(genericSuperclass instanceof Class)) {
            return C$Gson$Types.canonicalize(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
        }
        throw new RuntimeException("Missing type parameter.");
    }

    private static boolean isAssignableFrom(Type from, GenericArrayType to) {
        Type genericComponentType = to.getGenericComponentType();
        if (!(genericComponentType instanceof ParameterizedType)) {
            return true;
        }
        if (from instanceof GenericArrayType) {
            from = ((GenericArrayType) from).getGenericComponentType();
        } else if (from instanceof Class) {
            from = (Class) from;
            while (from.isArray()) {
                from = from.getComponentType();
            }
        }
        return isAssignableFrom(from, (ParameterizedType) genericComponentType, new HashMap());
    }

    private static boolean isAssignableFrom(Type from, ParameterizedType to, Map<String, Type> typeVarMap) {
        Map<String, Type> typeVarMap2 = typeVarMap;
        while (from != null) {
            if (to.equals(from)) {
                return true;
            }
            ParameterizedType from2;
            Class rawType = C$Gson$Types.getRawType(from);
            if (from instanceof ParameterizedType) {
                from2 = (ParameterizedType) from;
            } else {
                from2 = null;
            }
            if (from2 != null) {
                Type[] actualTypeArguments = from2.getActualTypeArguments();
                TypeVariable[] typeParameters = rawType.getTypeParameters();
                for (int i = 0; i < actualTypeArguments.length; i++) {
                    Object obj = actualTypeArguments[i];
                    TypeVariable typeVariable = typeParameters[i];
                    while (obj instanceof TypeVariable) {
                        Type obj2 = (Type) typeVarMap2.get(((TypeVariable) obj2).getName());
                    }
                    typeVarMap2.put(typeVariable.getName(), obj2);
                }
                if (typeEquals(from2, to, typeVarMap2)) {
                    return true;
                }
            }
            for (Type isAssignableFrom : rawType.getGenericInterfaces()) {
                if (isAssignableFrom(isAssignableFrom, to, new HashMap(typeVarMap2))) {
                    return true;
                }
            }
            from = rawType.getGenericSuperclass();
            typeVarMap2 = new HashMap(typeVarMap2);
        }
        return false;
    }

    private static boolean typeEquals(ParameterizedType from, ParameterizedType to, Map<String, Type> typeVarMap) {
        if (!from.getRawType().equals(to.getRawType())) {
            return false;
        }
        Type[] actualTypeArguments = from.getActualTypeArguments();
        Type[] actualTypeArguments2 = to.getActualTypeArguments();
        for (int i = 0; i < actualTypeArguments.length; i++) {
            if (!matches(actualTypeArguments[i], actualTypeArguments2[i], typeVarMap)) {
                return false;
            }
        }
        return true;
    }

    private static AssertionError buildUnexpectedTypeError(Type token, Class<?>... expected) {
        StringBuilder stringBuilder = new StringBuilder("Unexpected type. Expected one of: ");
        for (Class name : expected) {
            stringBuilder.append(name.getName()).append(", ");
        }
        stringBuilder.append("but got: ").append(token.getClass().getName()).append(", for type token: ").append(token.toString()).append('.');
        return new AssertionError(stringBuilder.toString());
    }

    private static boolean matches(Type from, Type to, Map<String, Type> typeMap) {
        return to.equals(from) || ((from instanceof TypeVariable) && to.equals(typeMap.get(((TypeVariable) from).getName())));
    }

    public static TypeToken<?> get(Type type) {
        return new TypeToken(type);
    }

    public static <T> TypeToken<T> get(Class<T> type) {
        return new TypeToken(type);
    }

    public final Class<? super T> getRawType() {
        return this.rawType;
    }

    public final Type getType() {
        return this.type;
    }

    @Deprecated
    public boolean isAssignableFrom(Class<?> cls) {
        return isAssignableFrom((Type) cls);
    }

    @Deprecated
    public boolean isAssignableFrom(Type from) {
        if (from == null) {
            return false;
        }
        if (this.type.equals(from)) {
            return true;
        }
        if (this.type instanceof Class) {
            return this.rawType.isAssignableFrom(C$Gson$Types.getRawType(from));
        }
        if (this.type instanceof ParameterizedType) {
            return isAssignableFrom(from, (ParameterizedType) this.type, new HashMap());
        }
        if (this.type instanceof GenericArrayType) {
            return this.rawType.isAssignableFrom(C$Gson$Types.getRawType(from)) && isAssignableFrom(from, (GenericArrayType) this.type);
        } else {
            throw buildUnexpectedTypeError(this.type, Class.class, ParameterizedType.class, GenericArrayType.class);
        }
    }

    @Deprecated
    public boolean isAssignableFrom(TypeToken<?> token) {
        return isAssignableFrom(token.getType());
    }

    public final int hashCode() {
        return this.hashCode;
    }

    public final boolean equals(Object o) {
        return (o instanceof TypeToken) && C$Gson$Types.equals(this.type, ((TypeToken) o).type);
    }

    public final String toString() {
        return C$Gson$Types.typeToString(this.type);
    }
}
