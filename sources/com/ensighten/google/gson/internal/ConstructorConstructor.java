package com.ensighten.google.gson.internal;

import com.ensighten.google.gson.InstanceCreator;
import com.ensighten.google.gson.JsonIOException;
import com.ensighten.google.gson.reflect.TypeToken;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public final class ConstructorConstructor {
    private final Map<Type, InstanceCreator<?>> instanceCreators;

    /* renamed from: com.ensighten.google.gson.internal.ConstructorConstructor$10 */
    class C177110 implements ObjectConstructor<T> {
        C177110() {
        }

        public T construct() {
            return new LinkedHashMap();
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.ConstructorConstructor$11 */
    class C177211 implements ObjectConstructor<T> {
        C177211() {
        }

        public T construct() {
            return new LinkedTreeMap();
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.ConstructorConstructor$4 */
    class C17774 implements ObjectConstructor<T> {
        C17774() {
        }

        public T construct() {
            return new TreeSet();
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.ConstructorConstructor$6 */
    class C17796 implements ObjectConstructor<T> {
        C17796() {
        }

        public T construct() {
            return new LinkedHashSet();
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.ConstructorConstructor$7 */
    class C17807 implements ObjectConstructor<T> {
        C17807() {
        }

        public T construct() {
            return new LinkedList();
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.ConstructorConstructor$8 */
    class C17818 implements ObjectConstructor<T> {
        C17818() {
        }

        public T construct() {
            return new ArrayList();
        }
    }

    /* renamed from: com.ensighten.google.gson.internal.ConstructorConstructor$9 */
    class C17829 implements ObjectConstructor<T> {
        C17829() {
        }

        public T construct() {
            return new TreeMap();
        }
    }

    public ConstructorConstructor(Map<Type, InstanceCreator<?>> instanceCreators) {
        this.instanceCreators = instanceCreators;
    }

    public final <T> ObjectConstructor<T> get(TypeToken<T> typeToken) {
        final Type type = typeToken.getType();
        Class rawType = typeToken.getRawType();
        final InstanceCreator instanceCreator = (InstanceCreator) this.instanceCreators.get(type);
        if (instanceCreator != null) {
            return new ObjectConstructor<T>() {
                public T construct() {
                    return instanceCreator.createInstance(type);
                }
            };
        }
        instanceCreator = (InstanceCreator) this.instanceCreators.get(rawType);
        if (instanceCreator != null) {
            return new ObjectConstructor<T>() {
                public T construct() {
                    return instanceCreator.createInstance(type);
                }
            };
        }
        ObjectConstructor<T> newDefaultConstructor = newDefaultConstructor(rawType);
        if (newDefaultConstructor != null) {
            return newDefaultConstructor;
        }
        newDefaultConstructor = newDefaultImplementationConstructor(type, rawType);
        return newDefaultConstructor == null ? newUnsafeAllocator(type, rawType) : newDefaultConstructor;
    }

    private <T> ObjectConstructor<T> newDefaultConstructor(Class<? super T> rawType) {
        try {
            final Constructor declaredConstructor = rawType.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return new ObjectConstructor<T>() {
                public T construct() {
                    try {
                        return declaredConstructor.newInstance(null);
                    } catch (InstantiationException e) {
                        throw new RuntimeException("Failed to invoke " + declaredConstructor + " with no args", e);
                    } catch (InvocationTargetException e2) {
                        throw new RuntimeException("Failed to invoke " + declaredConstructor + " with no args", e2.getTargetException());
                    } catch (IllegalAccessException e3) {
                        throw new AssertionError(e3);
                    }
                }
            };
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    private <T> ObjectConstructor<T> newDefaultImplementationConstructor(final Type type, Class<? super T> rawType) {
        if (Collection.class.isAssignableFrom(rawType)) {
            if (SortedSet.class.isAssignableFrom(rawType)) {
                return new C17774();
            }
            if (EnumSet.class.isAssignableFrom(rawType)) {
                return new ObjectConstructor<T>() {
                    public T construct() {
                        if (type instanceof ParameterizedType) {
                            Type type = ((ParameterizedType) type).getActualTypeArguments()[0];
                            if (type instanceof Class) {
                                return EnumSet.noneOf((Class) type);
                            }
                            throw new JsonIOException("Invalid EnumSet type: " + type.toString());
                        }
                        throw new JsonIOException("Invalid EnumSet type: " + type.toString());
                    }
                };
            }
            if (Set.class.isAssignableFrom(rawType)) {
                return new C17796();
            }
            if (Queue.class.isAssignableFrom(rawType)) {
                return new C17807();
            }
            return new C17818();
        } else if (!Map.class.isAssignableFrom(rawType)) {
            return null;
        } else {
            if (SortedMap.class.isAssignableFrom(rawType)) {
                return new C17829();
            }
            if (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(TypeToken.get(((ParameterizedType) type).getActualTypeArguments()[0]).getRawType())) {
                return new C177211();
            }
            return new C177110();
        }
    }

    private <T> ObjectConstructor<T> newUnsafeAllocator(final Type type, final Class<? super T> rawType) {
        return new ObjectConstructor<T>() {
            private final UnsafeAllocator unsafeAllocator = UnsafeAllocator.create();

            public T construct() {
                try {
                    return this.unsafeAllocator.newInstance(rawType);
                } catch (Exception e) {
                    throw new RuntimeException("Unable to invoke no-args constructor for " + type + ". Register an InstanceCreator with Gson for this type may fix this problem.", e);
                }
            }
        };
    }

    public final String toString() {
        return this.instanceCreators.toString();
    }
}
