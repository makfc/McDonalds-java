package com.ensighten.google.gson.internal;

import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

/* renamed from: com.ensighten.google.gson.internal.$Gson$Types */
public final class C$Gson$Types {
    static final Type[] EMPTY_TYPE_ARRAY = new Type[0];

    /* renamed from: com.ensighten.google.gson.internal.$Gson$Types$GenericArrayTypeImpl */
    static final class GenericArrayTypeImpl implements Serializable, GenericArrayType {
        private static final long serialVersionUID = 0;
        private final Type componentType;

        public GenericArrayTypeImpl(Type componentType) {
            this.componentType = C$Gson$Types.canonicalize(componentType);
        }

        public final Type getGenericComponentType() {
            return this.componentType;
        }

        public final boolean equals(Object o) {
            return (o instanceof GenericArrayType) && C$Gson$Types.equals(this, (GenericArrayType) o);
        }

        public final int hashCode() {
            return this.componentType.hashCode();
        }

        public final String toString() {
            return C$Gson$Types.typeToString(this.componentType) + "[]";
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.$Gson$Types$ParameterizedTypeImpl */
    static final class ParameterizedTypeImpl implements Serializable, ParameterizedType {
        private static final long serialVersionUID = 0;
        private final Type ownerType;
        private final Type rawType;
        private final Type[] typeArguments;

        public ParameterizedTypeImpl(Type ownerType, Type rawType, Type... typeArguments) {
            int i = 0;
            if (rawType instanceof Class) {
                boolean z;
                Class cls = (Class) rawType;
                int i2 = (Modifier.isStatic(cls.getModifiers()) || cls.getEnclosingClass() == null) ? 1 : 0;
                if (ownerType == null && i2 == 0) {
                    z = false;
                } else {
                    z = true;
                }
                C$Gson$Preconditions.checkArgument(z);
            }
            this.ownerType = ownerType == null ? null : C$Gson$Types.canonicalize(ownerType);
            this.rawType = C$Gson$Types.canonicalize(rawType);
            this.typeArguments = (Type[]) typeArguments.clone();
            while (i < this.typeArguments.length) {
                C$Gson$Preconditions.checkNotNull(this.typeArguments[i]);
                C$Gson$Types.checkNotPrimitive(this.typeArguments[i]);
                this.typeArguments[i] = C$Gson$Types.canonicalize(this.typeArguments[i]);
                i++;
            }
        }

        public final Type[] getActualTypeArguments() {
            return (Type[]) this.typeArguments.clone();
        }

        public final Type getRawType() {
            return this.rawType;
        }

        public final Type getOwnerType() {
            return this.ownerType;
        }

        public final boolean equals(Object other) {
            return (other instanceof ParameterizedType) && C$Gson$Types.equals(this, (ParameterizedType) other);
        }

        public final int hashCode() {
            return (Arrays.hashCode(this.typeArguments) ^ this.rawType.hashCode()) ^ C$Gson$Types.hashCodeOrZero(this.ownerType);
        }

        public final String toString() {
            StringBuilder stringBuilder = new StringBuilder((this.typeArguments.length + 1) * 30);
            stringBuilder.append(C$Gson$Types.typeToString(this.rawType));
            if (this.typeArguments.length == 0) {
                return stringBuilder.toString();
            }
            stringBuilder.append("<").append(C$Gson$Types.typeToString(this.typeArguments[0]));
            for (int i = 1; i < this.typeArguments.length; i++) {
                stringBuilder.append(", ").append(C$Gson$Types.typeToString(this.typeArguments[i]));
            }
            return stringBuilder.append(">").toString();
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.$Gson$Types$WildcardTypeImpl */
    static final class WildcardTypeImpl implements Serializable, WildcardType {
        private static final long serialVersionUID = 0;
        private final Type lowerBound;
        private final Type upperBound;

        public WildcardTypeImpl(Type[] upperBounds, Type[] lowerBounds) {
            boolean z;
            boolean z2 = true;
            C$Gson$Preconditions.checkArgument(lowerBounds.length <= 1);
            if (upperBounds.length == 1) {
                z = true;
            } else {
                z = false;
            }
            C$Gson$Preconditions.checkArgument(z);
            if (lowerBounds.length == 1) {
                C$Gson$Preconditions.checkNotNull(lowerBounds[0]);
                C$Gson$Types.checkNotPrimitive(lowerBounds[0]);
                if (upperBounds[0] != Object.class) {
                    z2 = false;
                }
                C$Gson$Preconditions.checkArgument(z2);
                this.lowerBound = C$Gson$Types.canonicalize(lowerBounds[0]);
                this.upperBound = Object.class;
                return;
            }
            C$Gson$Preconditions.checkNotNull(upperBounds[0]);
            C$Gson$Types.checkNotPrimitive(upperBounds[0]);
            this.lowerBound = null;
            this.upperBound = C$Gson$Types.canonicalize(upperBounds[0]);
        }

        public final Type[] getUpperBounds() {
            return new Type[]{this.upperBound};
        }

        public final Type[] getLowerBounds() {
            if (this.lowerBound == null) {
                return C$Gson$Types.EMPTY_TYPE_ARRAY;
            }
            return new Type[]{this.lowerBound};
        }

        public final boolean equals(Object other) {
            return (other instanceof WildcardType) && C$Gson$Types.equals(this, (WildcardType) other);
        }

        public final int hashCode() {
            return (this.lowerBound != null ? this.lowerBound.hashCode() + 31 : 1) ^ (this.upperBound.hashCode() + 31);
        }

        public final String toString() {
            if (this.lowerBound != null) {
                return "? super " + C$Gson$Types.typeToString(this.lowerBound);
            }
            if (this.upperBound == Object.class) {
                return "?";
            }
            return "? extends " + C$Gson$Types.typeToString(this.upperBound);
        }
    }

    private C$Gson$Types() {
    }

    public static ParameterizedType newParameterizedTypeWithOwner(Type ownerType, Type rawType, Type... typeArguments) {
        return new ParameterizedTypeImpl(ownerType, rawType, typeArguments);
    }

    public static GenericArrayType arrayOf(Type componentType) {
        return new GenericArrayTypeImpl(componentType);
    }

    public static WildcardType subtypeOf(Type bound) {
        return new WildcardTypeImpl(new Type[]{bound}, EMPTY_TYPE_ARRAY);
    }

    public static WildcardType supertypeOf(Type bound) {
        return new WildcardTypeImpl(new Type[]{Object.class}, new Type[]{bound});
    }

    public static Type canonicalize(Type type) {
        if (type instanceof Class) {
            GenericArrayTypeImpl genericArrayTypeImpl;
            Class cls = (Class) type;
            if (cls.isArray()) {
                genericArrayTypeImpl = new GenericArrayTypeImpl(C$Gson$Types.canonicalize(cls.getComponentType()));
            } else {
                Object genericArrayTypeImpl2 = cls;
            }
            return genericArrayTypeImpl2;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return new ParameterizedTypeImpl(parameterizedType.getOwnerType(), parameterizedType.getRawType(), parameterizedType.getActualTypeArguments());
        } else if (type instanceof GenericArrayType) {
            return new GenericArrayTypeImpl(((GenericArrayType) type).getGenericComponentType());
        } else {
            if (!(type instanceof WildcardType)) {
                return type;
            }
            WildcardType wildcardType = (WildcardType) type;
            return new WildcardTypeImpl(wildcardType.getUpperBounds(), wildcardType.getLowerBounds());
        }
    }

    public static Class<?> getRawType(Type type) {
        while (!(type instanceof Class)) {
            if (type instanceof ParameterizedType) {
                Type rawType = ((ParameterizedType) type).getRawType();
                C$Gson$Preconditions.checkArgument(rawType instanceof Class);
                return (Class) rawType;
            } else if (type instanceof GenericArrayType) {
                return Array.newInstance(C$Gson$Types.getRawType(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
            } else {
                if (type instanceof TypeVariable) {
                    return Object.class;
                }
                if (type instanceof WildcardType) {
                    type = ((WildcardType) type).getUpperBounds()[0];
                } else {
                    throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + (type == null ? SafeJsonPrimitive.NULL_STRING : type.getClass().getName()));
                }
            }
        }
        return (Class) type;
    }

    static boolean equal(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }

    public static boolean equals(Type a, Type b) {
        while (a != b) {
            if (a instanceof Class) {
                return a.equals(b);
            }
            if (a instanceof ParameterizedType) {
                if (!(b instanceof ParameterizedType)) {
                    return false;
                }
                ParameterizedType parameterizedType = (ParameterizedType) a;
                ParameterizedType parameterizedType2 = (ParameterizedType) b;
                if (C$Gson$Types.equal(parameterizedType.getOwnerType(), parameterizedType2.getOwnerType()) && parameterizedType.getRawType().equals(parameterizedType2.getRawType()) && Arrays.equals(parameterizedType.getActualTypeArguments(), parameterizedType2.getActualTypeArguments())) {
                    return true;
                }
                return false;
            } else if (a instanceof GenericArrayType) {
                if (!(b instanceof GenericArrayType)) {
                    return false;
                }
                GenericArrayType genericArrayType = (GenericArrayType) b;
                a = ((GenericArrayType) a).getGenericComponentType();
                b = genericArrayType.getGenericComponentType();
            } else if (a instanceof WildcardType) {
                if (!(b instanceof WildcardType)) {
                    return false;
                }
                WildcardType wildcardType = (WildcardType) a;
                WildcardType wildcardType2 = (WildcardType) b;
                if (Arrays.equals(wildcardType.getUpperBounds(), wildcardType2.getUpperBounds()) && Arrays.equals(wildcardType.getLowerBounds(), wildcardType2.getLowerBounds())) {
                    return true;
                }
                return false;
            } else if (!(a instanceof TypeVariable)) {
                return false;
            } else {
                if (!(b instanceof TypeVariable)) {
                    return false;
                }
                TypeVariable typeVariable = (TypeVariable) a;
                TypeVariable typeVariable2 = (TypeVariable) b;
                if (typeVariable.getGenericDeclaration() == typeVariable2.getGenericDeclaration() && typeVariable.getName().equals(typeVariable2.getName())) {
                    return true;
                }
                return false;
            }
        }
        return true;
    }

    private static int hashCodeOrZero(Object o) {
        return o != null ? o.hashCode() : 0;
    }

    public static String typeToString(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }

    static Type getGenericSupertype(Type context, Class<?> rawType, Class<?> toResolve) {
        while (toResolve != rawType) {
            if (toResolve.isInterface()) {
                Class[] interfaces = rawType.getInterfaces();
                int length = interfaces.length;
                for (int i = 0; i < length; i++) {
                    if (interfaces[i] == toResolve) {
                        return rawType.getGenericInterfaces()[i];
                    }
                    if (toResolve.isAssignableFrom(interfaces[i])) {
                        context = rawType.getGenericInterfaces()[i];
                        rawType = interfaces[i];
                        break;
                    }
                }
            }
            if (!rawType.isInterface()) {
                while (rawType != Object.class) {
                    Class<?> rawType2 = rawType.getSuperclass();
                    if (rawType2 == toResolve) {
                        return rawType.getGenericSuperclass();
                    }
                    if (toResolve.isAssignableFrom(rawType2)) {
                        context = rawType.getGenericSuperclass();
                        rawType = rawType2;
                    } else {
                        rawType = rawType2;
                    }
                }
            }
            return toResolve;
        }
        return context;
    }

    static Type getSupertype(Type context, Class<?> contextRawType, Class<?> supertype) {
        C$Gson$Preconditions.checkArgument(supertype.isAssignableFrom(contextRawType));
        return C$Gson$Types.resolve(context, contextRawType, C$Gson$Types.getGenericSupertype(context, contextRawType, supertype));
    }

    public static Type getArrayComponentType(Type array) {
        return array instanceof GenericArrayType ? ((GenericArrayType) array).getGenericComponentType() : ((Class) array).getComponentType();
    }

    public static Type getCollectionElementType(Type context, Class<?> contextRawType) {
        Type supertype = C$Gson$Types.getSupertype(context, contextRawType, Collection.class);
        if (supertype instanceof WildcardType) {
            supertype = ((WildcardType) supertype).getUpperBounds()[0];
        }
        if (supertype instanceof ParameterizedType) {
            return ((ParameterizedType) supertype).getActualTypeArguments()[0];
        }
        return Object.class;
    }

    public static Type[] getMapKeyAndValueTypes(Type context, Class<?> contextRawType) {
        if (context == Properties.class) {
            return new Type[]{String.class, String.class};
        }
        Type supertype = C$Gson$Types.getSupertype(context, contextRawType, Map.class);
        if (supertype instanceof ParameterizedType) {
            return ((ParameterizedType) supertype).getActualTypeArguments();
        }
        return new Type[]{Object.class, Object.class};
    }

    public static Type resolve(Type context, Class<?> contextRawType, Type toResolve) {
        while (toResolve instanceof TypeVariable) {
            Type type = (TypeVariable) toResolve;
            toResolve = C$Gson$Types.resolveTypeVariable(context, contextRawType, type);
            if (toResolve == type) {
                return toResolve;
            }
        }
        Type genericComponentType;
        Type resolve;
        Type[] actualTypeArguments;
        Type[] typeArr;
        if ((toResolve instanceof Class) && ((Class) toResolve).isArray()) {
            Class cls = (Class) toResolve;
            Class componentType = cls.getComponentType();
            Class resolve2 = C$Gson$Types.resolve(context, contextRawType, componentType);
            if (componentType != resolve2) {
                return C$Gson$Types.arrayOf(resolve2);
            }
            return cls;
        } else if (toResolve instanceof GenericArrayType) {
            GenericArrayType genericArrayType = (GenericArrayType) toResolve;
            genericComponentType = genericArrayType.getGenericComponentType();
            resolve = C$Gson$Types.resolve(context, contextRawType, genericComponentType);
            if (genericComponentType != resolve) {
                return C$Gson$Types.arrayOf(resolve);
            }
            return genericArrayType;
        } else if (toResolve instanceof ParameterizedType) {
            int i;
            ParameterizedType parameterizedType = (ParameterizedType) toResolve;
            genericComponentType = parameterizedType.getOwnerType();
            Type resolve3 = C$Gson$Types.resolve(context, contextRawType, genericComponentType);
            if (resolve3 != genericComponentType) {
                i = 1;
            } else {
                i = 0;
            }
            actualTypeArguments = parameterizedType.getActualTypeArguments();
            int length = actualTypeArguments.length;
            int i2 = i;
            typeArr = actualTypeArguments;
            for (int i3 = 0; i3 < length; i3++) {
                Type resolve4 = C$Gson$Types.resolve(context, contextRawType, typeArr[i3]);
                if (resolve4 != typeArr[i3]) {
                    if (i2 == 0) {
                        typeArr = (Type[]) typeArr.clone();
                        i2 = 1;
                    }
                    typeArr[i3] = resolve4;
                }
            }
            if (i2 != 0) {
                return C$Gson$Types.newParameterizedTypeWithOwner(resolve3, parameterizedType.getRawType(), typeArr);
            }
            return parameterizedType;
        } else if (!(toResolve instanceof WildcardType)) {
            return toResolve;
        } else {
            WildcardType wildcardType = (WildcardType) toResolve;
            typeArr = wildcardType.getLowerBounds();
            actualTypeArguments = wildcardType.getUpperBounds();
            if (typeArr.length == 1) {
                resolve = C$Gson$Types.resolve(context, contextRawType, typeArr[0]);
                if (resolve != typeArr[0]) {
                    return C$Gson$Types.supertypeOf(resolve);
                }
                return wildcardType;
            } else if (actualTypeArguments.length != 1) {
                return wildcardType;
            } else {
                genericComponentType = C$Gson$Types.resolve(context, contextRawType, actualTypeArguments[0]);
                if (genericComponentType != actualTypeArguments[0]) {
                    return C$Gson$Types.subtypeOf(genericComponentType);
                }
                return wildcardType;
            }
        }
    }

    static Type resolveTypeVariable(Type context, Class<?> contextRawType, TypeVariable<?> unknown) {
        Class declaringClassOf = C$Gson$Types.declaringClassOf(unknown);
        if (declaringClassOf == null) {
            return unknown;
        }
        Type genericSupertype = C$Gson$Types.getGenericSupertype(context, contextRawType, declaringClassOf);
        if (!(genericSupertype instanceof ParameterizedType)) {
            return unknown;
        }
        return ((ParameterizedType) genericSupertype).getActualTypeArguments()[C$Gson$Types.indexOf(declaringClassOf.getTypeParameters(), unknown)];
    }

    private static int indexOf(Object[] array, Object toFind) {
        for (int i = 0; i < array.length; i++) {
            if (toFind.equals(array[i])) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    private static Class<?> declaringClassOf(TypeVariable<?> typeVariable) {
        GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
        return genericDeclaration instanceof Class ? (Class) genericDeclaration : null;
    }

    private static void checkNotPrimitive(Type type) {
        boolean z = ((type instanceof Class) && ((Class) type).isPrimitive()) ? false : true;
        C$Gson$Preconditions.checkArgument(z);
    }
}
