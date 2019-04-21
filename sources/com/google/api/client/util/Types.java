package com.google.api.client.util;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class Types {
    public static ParameterizedType getSuperParameterizedType(Type type, Class<?> superClass) {
        if ((type instanceof Class) || (type instanceof ParameterizedType)) {
            while (type != null && type != Object.class) {
                Class<?> rawType;
                if (type instanceof Class) {
                    rawType = (Class) type;
                } else {
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    rawType = getRawClass(parameterizedType);
                    if (rawType == superClass) {
                        return parameterizedType;
                    }
                    if (superClass.isInterface()) {
                        for (Type interfaceType : rawType.getGenericInterfaces()) {
                            if (superClass.isAssignableFrom(interfaceType instanceof Class ? (Class) interfaceType : getRawClass((ParameterizedType) interfaceType))) {
                                type = interfaceType;
                                break;
                            }
                        }
                    }
                }
                type = rawType.getGenericSuperclass();
            }
        }
        return null;
    }

    public static boolean isAssignableToOrFrom(Class<?> classToCheck, Class<?> anotherClass) {
        return classToCheck.isAssignableFrom(anotherClass) || anotherClass.isAssignableFrom(classToCheck);
    }

    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (IllegalAccessException e) {
            throw handleExceptionForNewInstance(e, clazz);
        } catch (InstantiationException e2) {
            throw handleExceptionForNewInstance(e2, clazz);
        }
    }

    private static IllegalArgumentException handleExceptionForNewInstance(Exception e, Class<?> clazz) {
        StringBuilder buf = new StringBuilder("unable to create new instance of class ").append(clazz.getName());
        ArrayList<String> reasons = new ArrayList();
        if (clazz.isArray()) {
            reasons.add("because it is an array");
        } else if (clazz.isPrimitive()) {
            reasons.add("because it is primitive");
        } else if (clazz == Void.class) {
            reasons.add("because it is void");
        } else {
            if (Modifier.isInterface(clazz.getModifiers())) {
                reasons.add("because it is an interface");
            } else if (Modifier.isAbstract(clazz.getModifiers())) {
                reasons.add("because it is abstract");
            }
            if (!(clazz.getEnclosingClass() == null || Modifier.isStatic(clazz.getModifiers()))) {
                reasons.add("because it is not static");
            }
            if (Modifier.isPublic(clazz.getModifiers())) {
                try {
                    clazz.getConstructor(new Class[0]);
                } catch (NoSuchMethodException e2) {
                    reasons.add("because it has no accessible default constructor");
                }
            } else {
                reasons.add("possibly because it is not public");
            }
        }
        boolean and = false;
        Iterator i$ = reasons.iterator();
        while (i$.hasNext()) {
            String reason = (String) i$.next();
            if (and) {
                buf.append(" and");
            } else {
                and = true;
            }
            buf.append(" ").append(reason);
        }
        return new IllegalArgumentException(buf.toString(), e);
    }

    public static boolean isArray(Type type) {
        return (type instanceof GenericArrayType) || ((type instanceof Class) && ((Class) type).isArray());
    }

    public static Type getArrayComponentType(Type array) {
        return array instanceof GenericArrayType ? ((GenericArrayType) array).getGenericComponentType() : ((Class) array).getComponentType();
    }

    public static Class<?> getRawClass(ParameterizedType parameterType) {
        return (Class) parameterType.getRawType();
    }

    public static Type getBound(WildcardType wildcardType) {
        Type[] lowerBounds = wildcardType.getLowerBounds();
        if (lowerBounds.length != 0) {
            return lowerBounds[0];
        }
        return wildcardType.getUpperBounds()[0];
    }

    public static Type resolveTypeVariable(List<Type> context, TypeVariable<?> typeVariable) {
        GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
        if (genericDeclaration instanceof Class) {
            Class<?> rawGenericDeclaration = (Class) genericDeclaration;
            int contextIndex = context.size();
            ParameterizedType parameterizedType = null;
            while (parameterizedType == null) {
                contextIndex--;
                if (contextIndex < 0) {
                    break;
                }
                parameterizedType = getSuperParameterizedType((Type) context.get(contextIndex), rawGenericDeclaration);
            }
            if (parameterizedType != null) {
                TypeVariable<?>[] typeParameters = genericDeclaration.getTypeParameters();
                int index = 0;
                while (index < typeParameters.length && !typeParameters[index].equals(typeVariable)) {
                    index++;
                }
                Type result = parameterizedType.getActualTypeArguments()[index];
                if (result instanceof TypeVariable) {
                    Type resolve = resolveTypeVariable(context, (TypeVariable) result);
                    if (resolve != null) {
                        return resolve;
                    }
                }
                return result;
            }
        }
        return null;
    }

    public static Class<?> getRawArrayComponentType(List<Type> context, Type componentType) {
        if (componentType instanceof TypeVariable) {
            componentType = resolveTypeVariable(context, (TypeVariable) componentType);
        }
        if (componentType instanceof GenericArrayType) {
            return Array.newInstance(getRawArrayComponentType(context, getArrayComponentType(componentType)), 0).getClass();
        }
        if (componentType instanceof Class) {
            return (Class) componentType;
        }
        if (componentType instanceof ParameterizedType) {
            return getRawClass((ParameterizedType) componentType);
        }
        boolean z;
        if (componentType == null) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "wildcard type is not supported: %s", componentType);
        return Object.class;
    }

    public static Type getIterableParameter(Type iterableType) {
        return getActualParameterAtPosition(iterableType, Iterable.class, 0);
    }

    public static Type getMapValueParameter(Type mapType) {
        return getActualParameterAtPosition(mapType, Map.class, 1);
    }

    private static Type getActualParameterAtPosition(Type type, Class<?> superClass, int position) {
        ParameterizedType parameterizedType = getSuperParameterizedType(type, superClass);
        if (parameterizedType == null) {
            return null;
        }
        Type valueType = parameterizedType.getActualTypeArguments()[position];
        if (valueType instanceof TypeVariable) {
            Type resolve = resolveTypeVariable(Arrays.asList(new Type[]{type}), (TypeVariable) valueType);
            if (resolve != null) {
                return resolve;
            }
        }
        return valueType;
    }

    public static <T> Iterable<T> iterableOf(final Object value) {
        if (value instanceof Iterable) {
            return (Iterable) value;
        }
        Class<?> valueClass = value.getClass();
        Preconditions.checkArgument(valueClass.isArray(), "not an array or Iterable: %s", valueClass);
        if (valueClass.getComponentType().isPrimitive()) {
            return new Iterable<T>() {

                /* renamed from: com.google.api.client.util.Types$1$1 */
                class C27731 implements Iterator<T> {
                    int index = 0;
                    final int length = Array.getLength(value);

                    C27731() {
                    }

                    public boolean hasNext() {
                        return this.index < this.length;
                    }

                    public T next() {
                        if (hasNext()) {
                            Object obj = value;
                            int i = this.index;
                            this.index = i + 1;
                            return Array.get(obj, i);
                        }
                        throw new NoSuchElementException();
                    }

                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                }

                public Iterator<T> iterator() {
                    return new C27731();
                }
            };
        }
        return Arrays.asList((Object[]) value);
    }

    public static Object toArray(Collection<?> collection, Class<?> componentType) {
        if (!componentType.isPrimitive()) {
            return collection.toArray((Object[]) Array.newInstance(componentType, collection.size()));
        }
        Object newInstance = Array.newInstance(componentType, collection.size());
        int index = 0;
        for (Object value : collection) {
            int index2 = index + 1;
            Array.set(newInstance, index, value);
            index = index2;
        }
        return newInstance;
    }

    private Types() {
    }
}
