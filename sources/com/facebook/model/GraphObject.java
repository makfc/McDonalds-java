package com.facebook.model;

import com.facebook.FacebookGraphObjectException;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.newrelic.agent.android.agentdata.HexAttributes;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public interface GraphObject {

    public static final class Factory {
        private static final SimpleDateFormat[] dateFormats = new SimpleDateFormat[]{new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US), new SimpleDateFormat("yyyy-MM-dd", Locale.US)};
        private static final HashSet<Class<?>> verifiedGraphObjectClasses = new HashSet();

        private static final class GraphObjectListImpl<T> extends AbstractList<T> implements GraphObjectList<T> {
            private final Class<?> itemType;
            private final JSONArray state;

            public GraphObjectListImpl(JSONArray state, Class<?> itemType) {
                Validate.notNull(state, HexAttributes.HEX_ATTR_THREAD_STATE);
                Validate.notNull(itemType, "itemType");
                this.state = state;
                this.itemType = itemType;
            }

            public String toString() {
                return String.format("GraphObjectList{itemType=%s, state=%s}", new Object[]{this.itemType.getSimpleName(), this.state});
            }

            public void add(int location, T object) {
                if (location < 0) {
                    throw new IndexOutOfBoundsException();
                } else if (location < size()) {
                    throw new UnsupportedOperationException("Only adding items at the end of the list is supported.");
                } else {
                    put(location, object);
                }
            }

            public T set(int location, T object) {
                checkIndex(location);
                T result = get(location);
                put(location, object);
                return result;
            }

            public int hashCode() {
                return this.state.hashCode();
            }

            public boolean equals(Object obj) {
                if (obj == null) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                if (getClass() != obj.getClass()) {
                    return false;
                }
                return this.state.equals(((GraphObjectListImpl) obj).state);
            }

            public T get(int location) {
                checkIndex(location);
                return Factory.coerceValueToExpectedType(this.state.opt(location), this.itemType, null);
            }

            public int size() {
                return this.state.length();
            }

            public final <U extends GraphObject> GraphObjectList<U> castToListOf(Class<U> graphObjectClass) {
                if (!GraphObject.class.isAssignableFrom(this.itemType)) {
                    throw new FacebookGraphObjectException("Can't cast GraphObjectCollection of non-GraphObject type " + this.itemType);
                } else if (graphObjectClass.isAssignableFrom(this.itemType)) {
                    return this;
                } else {
                    return Factory.createList(this.state, graphObjectClass);
                }
            }

            public final JSONArray getInnerJSONArray() {
                return this.state;
            }

            public void clear() {
                throw new UnsupportedOperationException();
            }

            public boolean remove(Object o) {
                throw new UnsupportedOperationException();
            }

            public boolean removeAll(Collection<?> collection) {
                throw new UnsupportedOperationException();
            }

            public boolean retainAll(Collection<?> collection) {
                throw new UnsupportedOperationException();
            }

            private void checkIndex(int index) {
                if (index < 0 || index >= this.state.length()) {
                    throw new IndexOutOfBoundsException();
                }
            }

            private void put(int index, T obj) {
                try {
                    this.state.put(index, Factory.getUnderlyingJSONObject(obj));
                } catch (JSONException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        }

        private static abstract class ProxyBase<STATE> implements InvocationHandler {
            private static final String EQUALS_METHOD = "equals";
            private static final String TOSTRING_METHOD = "toString";
            protected final STATE state;

            protected ProxyBase(STATE state) {
                this.state = state;
            }

            /* Access modifiers changed, original: protected|final */
            public final Object throwUnexpectedMethodSignature(Method method) {
                throw new FacebookGraphObjectException(getClass().getName() + " got an unexpected method signature: " + method.toString());
            }

            /* Access modifiers changed, original: protected|final */
            public final Object proxyObjectMethods(Object proxy, Method method, Object[] args) throws Throwable {
                String methodName = method.getName();
                if (methodName.equals(EQUALS_METHOD)) {
                    Object other = args[0];
                    if (other == null) {
                        return Boolean.valueOf(false);
                    }
                    InvocationHandler handler = Proxy.getInvocationHandler(other);
                    if (!(handler instanceof GraphObjectProxy)) {
                        return Boolean.valueOf(false);
                    }
                    return Boolean.valueOf(this.state.equals(((GraphObjectProxy) handler).state));
                } else if (methodName.equals(TOSTRING_METHOD)) {
                    return toString();
                } else {
                    return method.invoke(this.state, args);
                }
            }
        }

        private static final class GraphObjectProxy extends ProxyBase<JSONObject> {
            private static final String CASTTOMAP_METHOD = "asMap";
            private static final String CAST_METHOD = "cast";
            private static final String CLEAR_METHOD = "clear";
            private static final String CONTAINSKEY_METHOD = "containsKey";
            private static final String CONTAINSVALUE_METHOD = "containsValue";
            private static final String ENTRYSET_METHOD = "entrySet";
            private static final String GETINNERJSONOBJECT_METHOD = "getInnerJSONObject";
            private static final String GETPROPERTYASLIST_METHOD = "getPropertyAsList";
            private static final String GETPROPERTYAS_METHOD = "getPropertyAs";
            private static final String GETPROPERTY_METHOD = "getProperty";
            private static final String GET_METHOD = "get";
            private static final String ISEMPTY_METHOD = "isEmpty";
            private static final String KEYSET_METHOD = "keySet";
            private static final String PUTALL_METHOD = "putAll";
            private static final String PUT_METHOD = "put";
            private static final String REMOVEPROPERTY_METHOD = "removeProperty";
            private static final String REMOVE_METHOD = "remove";
            private static final String SETPROPERTY_METHOD = "setProperty";
            private static final String SIZE_METHOD = "size";
            private static final String VALUES_METHOD = "values";
            private final Class<?> graphObjectClass;

            public GraphObjectProxy(JSONObject state, Class<?> graphObjectClass) {
                super(state);
                this.graphObjectClass = graphObjectClass;
            }

            public String toString() {
                return String.format("GraphObject{graphObjectClass=%s, state=%s}", new Object[]{this.graphObjectClass.getSimpleName(), this.state});
            }

            public final Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Class<?> declaringClass = method.getDeclaringClass();
                if (declaringClass == Object.class) {
                    return proxyObjectMethods(proxy, method, args);
                }
                if (declaringClass == Map.class) {
                    return proxyMapMethods(method, args);
                }
                if (declaringClass == GraphObject.class) {
                    return proxyGraphObjectMethods(proxy, method, args);
                }
                if (GraphObject.class.isAssignableFrom(declaringClass)) {
                    return proxyGraphObjectGettersAndSetters(method, args);
                }
                return throwUnexpectedMethodSignature(method);
            }

            private final Object proxyMapMethods(Method method, Object[] args) {
                String methodName = method.getName();
                if (methodName.equals(CLEAR_METHOD)) {
                    JsonUtil.jsonObjectClear((JSONObject) this.state);
                    return null;
                } else if (methodName.equals(CONTAINSKEY_METHOD)) {
                    return Boolean.valueOf(((JSONObject) this.state).has((String) args[0]));
                } else {
                    if (methodName.equals(CONTAINSVALUE_METHOD)) {
                        return Boolean.valueOf(JsonUtil.jsonObjectContainsValue((JSONObject) this.state, args[0]));
                    }
                    if (methodName.equals(ENTRYSET_METHOD)) {
                        return JsonUtil.jsonObjectEntrySet((JSONObject) this.state);
                    }
                    if (methodName.equals(GET_METHOD)) {
                        return ((JSONObject) this.state).opt((String) args[0]);
                    }
                    if (methodName.equals(ISEMPTY_METHOD)) {
                        return Boolean.valueOf(((JSONObject) this.state).length() == 0);
                    } else if (methodName.equals(KEYSET_METHOD)) {
                        return JsonUtil.jsonObjectKeySet((JSONObject) this.state);
                    } else {
                        if (methodName.equals(PUT_METHOD)) {
                            return setJSONProperty(args);
                        }
                        if (methodName.equals(PUTALL_METHOD)) {
                            Map<String, Object> map;
                            if (args[0] instanceof Map) {
                                map = args[0];
                            } else if (!(args[0] instanceof GraphObject)) {
                                return null;
                            } else {
                                map = ((GraphObject) args[0]).asMap();
                            }
                            JsonUtil.jsonObjectPutAll((JSONObject) this.state, map);
                            return null;
                        } else if (methodName.equals(REMOVE_METHOD)) {
                            ((JSONObject) this.state).remove((String) args[0]);
                            return null;
                        } else if (methodName.equals(SIZE_METHOD)) {
                            return Integer.valueOf(((JSONObject) this.state).length());
                        } else {
                            if (methodName.equals(VALUES_METHOD)) {
                                return JsonUtil.jsonObjectValues((JSONObject) this.state);
                            }
                            return throwUnexpectedMethodSignature(method);
                        }
                    }
                }
            }

            private final Object proxyGraphObjectMethods(Object proxy, Method method, Object[] args) {
                String methodName = method.getName();
                if (methodName.equals(CAST_METHOD)) {
                    Class<? extends GraphObject> graphObjectClass = args[0];
                    if (graphObjectClass == null || !graphObjectClass.isAssignableFrom(this.graphObjectClass)) {
                        return Factory.createGraphObjectProxy(graphObjectClass, (JSONObject) this.state);
                    }
                    return proxy;
                } else if (methodName.equals(GETINNERJSONOBJECT_METHOD)) {
                    return ((GraphObjectProxy) Proxy.getInvocationHandler(proxy)).state;
                } else {
                    if (methodName.equals(CASTTOMAP_METHOD)) {
                        return Factory.createGraphObjectProxyForMap((JSONObject) this.state);
                    }
                    if (methodName.equals(GETPROPERTY_METHOD)) {
                        return ((JSONObject) this.state).opt((String) args[0]);
                    }
                    if (methodName.equals(GETPROPERTYAS_METHOD)) {
                        return Factory.coerceValueToExpectedType(((JSONObject) this.state).opt((String) args[0]), args[1], null);
                    }
                    if (methodName.equals(GETPROPERTYASLIST_METHOD)) {
                        final Class<?> expectedType = (Class) args[1];
                        return Factory.coerceValueToExpectedType(((JSONObject) this.state).opt((String) args[0]), GraphObjectList.class, new ParameterizedType() {
                            public Type[] getActualTypeArguments() {
                                return new Type[]{expectedType};
                            }

                            public Type getOwnerType() {
                                return null;
                            }

                            public Type getRawType() {
                                return GraphObjectList.class;
                            }
                        });
                    } else if (methodName.equals(SETPROPERTY_METHOD)) {
                        return setJSONProperty(args);
                    } else {
                        if (!methodName.equals(REMOVEPROPERTY_METHOD)) {
                            return throwUnexpectedMethodSignature(method);
                        }
                        ((JSONObject) this.state).remove((String) args[0]);
                        return null;
                    }
                }
            }

            private Object createGraphObjectsFromParameters(CreateGraphObject createGraphObject, Object value) {
                if (createGraphObject == null || Utility.isNullOrEmpty(createGraphObject.value())) {
                    return value;
                }
                String propertyName = createGraphObject.value();
                GraphObject graphObject;
                if (List.class.isAssignableFrom(value.getClass())) {
                    GraphObjectList<GraphObject> graphObjects = Factory.createList(GraphObject.class);
                    for (Object obj : (List) value) {
                        graphObject = Factory.create();
                        graphObject.setProperty(propertyName, obj);
                        graphObjects.add(graphObject);
                    }
                    return graphObjects;
                }
                graphObject = Factory.create();
                graphObject.setProperty(propertyName, value);
                return graphObject;
            }

            private final Object proxyGraphObjectGettersAndSetters(Method method, Object[] args) throws JSONException {
                String key;
                String methodName = method.getName();
                int parameterCount = method.getParameterTypes().length;
                PropertyName propertyNameOverride = (PropertyName) method.getAnnotation(PropertyName.class);
                if (propertyNameOverride != null) {
                    key = propertyNameOverride.value();
                } else {
                    key = Factory.convertCamelCaseToLowercaseWithUnderscores(methodName.substring(3));
                }
                if (parameterCount == 0) {
                    Object value = ((JSONObject) this.state).opt(key);
                    Class<?> expectedType = method.getReturnType();
                    Type genericReturnType = method.getGenericReturnType();
                    ParameterizedType parameterizedReturnType = null;
                    if (genericReturnType instanceof ParameterizedType) {
                        parameterizedReturnType = (ParameterizedType) genericReturnType;
                    }
                    return Factory.coerceValueToExpectedType(value, expectedType, parameterizedReturnType);
                } else if (parameterCount != 1) {
                    return throwUnexpectedMethodSignature(method);
                } else {
                    ((JSONObject) this.state).putOpt(key, Factory.getUnderlyingJSONObject(createGraphObjectsFromParameters((CreateGraphObject) method.getAnnotation(CreateGraphObject.class), args[0])));
                    return null;
                }
            }

            private Object setJSONProperty(Object[] args) {
                try {
                    ((JSONObject) this.state).putOpt(args[0], Factory.getUnderlyingJSONObject(args[1]));
                    return null;
                } catch (JSONException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        }

        private Factory() {
        }

        public static GraphObject create(JSONObject json) {
            return create(json, GraphObject.class);
        }

        public static <T extends GraphObject> T create(JSONObject json, Class<T> graphObjectClass) {
            return createGraphObjectProxy(graphObjectClass, json);
        }

        public static GraphObject create() {
            return create(GraphObject.class);
        }

        public static <T extends GraphObject> T create(Class<T> graphObjectClass) {
            return createGraphObjectProxy(graphObjectClass, new JSONObject());
        }

        public static boolean hasSameId(GraphObject a, GraphObject b) {
            if (a == null || b == null || !a.asMap().containsKey("id") || !b.asMap().containsKey("id")) {
                return false;
            }
            if (a.equals(b)) {
                return true;
            }
            Object idA = a.getProperty("id");
            Object idB = b.getProperty("id");
            if (idA == null || idB == null || !(idA instanceof String) || !(idB instanceof String)) {
                return false;
            }
            return idA.equals(idB);
        }

        public static <T> GraphObjectList<T> createList(JSONArray array, Class<T> graphObjectClass) {
            return new GraphObjectListImpl(array, graphObjectClass);
        }

        public static <T> GraphObjectList<T> createList(Class<T> graphObjectClass) {
            return createList(new JSONArray(), graphObjectClass);
        }

        private static <T extends GraphObject> T createGraphObjectProxy(Class<T> graphObjectClass, JSONObject state) {
            verifyCanProxyClass(graphObjectClass);
            return (GraphObject) Proxy.newProxyInstance(GraphObject.class.getClassLoader(), new Class[]{graphObjectClass}, new GraphObjectProxy(state, graphObjectClass));
        }

        private static Map<String, Object> createGraphObjectProxyForMap(JSONObject state) {
            return (Map) Proxy.newProxyInstance(GraphObject.class.getClassLoader(), new Class[]{Map.class}, new GraphObjectProxy(state, Map.class));
        }

        private static synchronized <T extends GraphObject> boolean hasClassBeenVerified(Class<T> graphObjectClass) {
            boolean contains;
            synchronized (Factory.class) {
                contains = verifiedGraphObjectClasses.contains(graphObjectClass);
            }
            return contains;
        }

        private static synchronized <T extends GraphObject> void recordClassHasBeenVerified(Class<T> graphObjectClass) {
            synchronized (Factory.class) {
                verifiedGraphObjectClasses.add(graphObjectClass);
            }
        }

        private static <T extends GraphObject> void verifyCanProxyClass(Class<T> graphObjectClass) {
            if (!hasClassBeenVerified(graphObjectClass)) {
                if (graphObjectClass.isInterface()) {
                    for (Method method : graphObjectClass.getMethods()) {
                        String methodName = method.getName();
                        int parameterCount = method.getParameterTypes().length;
                        Class<?> returnType = method.getReturnType();
                        boolean hasPropertyNameOverride = method.isAnnotationPresent(PropertyName.class);
                        if (!method.getDeclaringClass().isAssignableFrom(GraphObject.class)) {
                            if (parameterCount == 1 && returnType == Void.TYPE) {
                                if (hasPropertyNameOverride) {
                                    if (Utility.isNullOrEmpty(((PropertyName) method.getAnnotation(PropertyName.class)).value())) {
                                    }
                                } else if (methodName.startsWith("set") && methodName.length() > 3) {
                                }
                            } else if (parameterCount == 0 && returnType != Void.TYPE) {
                                if (hasPropertyNameOverride) {
                                    if (Utility.isNullOrEmpty(((PropertyName) method.getAnnotation(PropertyName.class)).value())) {
                                    }
                                } else if (methodName.startsWith("get") && methodName.length() > 3) {
                                }
                            }
                            throw new FacebookGraphObjectException("Factory can't proxy method: " + method.toString());
                        }
                    }
                    recordClassHasBeenVerified(graphObjectClass);
                    return;
                }
                throw new FacebookGraphObjectException("Factory can only wrap interfaces, not class: " + graphObjectClass.getName());
            }
        }

        static <U> U coerceValueToExpectedType(Object value, Class<U> expectedType, ParameterizedType expectedTypeAsParameterizedType) {
            if (value != null) {
                Class<?> valueType = value.getClass();
                if (expectedType.isAssignableFrom(valueType)) {
                    return value;
                }
                if (expectedType.isPrimitive()) {
                    return value;
                }
                if (GraphObject.class.isAssignableFrom(expectedType)) {
                    Class<? extends GraphObject> graphObjectClass = expectedType;
                    if (JSONObject.class.isAssignableFrom(valueType)) {
                        return createGraphObjectProxy(graphObjectClass, (JSONObject) value);
                    }
                    if (GraphObject.class.isAssignableFrom(valueType)) {
                        return ((GraphObject) value).cast(graphObjectClass);
                    }
                    throw new FacebookGraphObjectException("Can't create GraphObject from " + valueType.getName());
                } else if (!Iterable.class.equals(expectedType) && !Collection.class.equals(expectedType) && !List.class.equals(expectedType) && !GraphObjectList.class.equals(expectedType)) {
                    if (String.class.equals(expectedType)) {
                        if (Double.class.isAssignableFrom(valueType) || Float.class.isAssignableFrom(valueType)) {
                            return String.format("%f", new Object[]{value});
                        } else if (Number.class.isAssignableFrom(valueType)) {
                            return String.format("%d", new Object[]{value});
                        }
                    } else if (Date.class.equals(expectedType) && String.class.isAssignableFrom(valueType)) {
                        SimpleDateFormat[] arr$ = dateFormats;
                        int len$ = arr$.length;
                        int i$ = 0;
                        while (i$ < len$) {
                            try {
                                U date = arr$[i$].parse((String) value);
                                if (date != null) {
                                    return date;
                                }
                                i$++;
                            } catch (ParseException e) {
                            }
                        }
                    }
                    throw new FacebookGraphObjectException("Can't convert type" + valueType.getName() + " to " + expectedType.getName());
                } else if (expectedTypeAsParameterizedType == null) {
                    throw new FacebookGraphObjectException("can't infer generic type of: " + expectedType.toString());
                } else {
                    Type[] actualTypeArguments = expectedTypeAsParameterizedType.getActualTypeArguments();
                    if (actualTypeArguments != null && actualTypeArguments.length == 1 && (actualTypeArguments[0] instanceof Class)) {
                        Class<?> collectionGenericArgument = actualTypeArguments[0];
                        if (JSONArray.class.isAssignableFrom(valueType)) {
                            return createList((JSONArray) value, collectionGenericArgument);
                        }
                        throw new FacebookGraphObjectException("Can't create Collection from " + valueType.getName());
                    }
                    throw new FacebookGraphObjectException("Expect collection properties to be of a type with exactly one generic parameter.");
                }
            } else if (Boolean.TYPE.equals(expectedType)) {
                return Boolean.valueOf(false);
            } else {
                if (Character.TYPE.equals(expectedType)) {
                    return Character.valueOf(0);
                }
                if (expectedType.isPrimitive()) {
                    return Integer.valueOf(0);
                }
                return null;
            }
        }

        static String convertCamelCaseToLowercaseWithUnderscores(String string) {
            return string.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase(Locale.US);
        }

        private static Object getUnderlyingJSONObject(Object obj) {
            if (obj == null) {
                return null;
            }
            Class<?> objClass = obj.getClass();
            if (GraphObject.class.isAssignableFrom(objClass)) {
                return ((GraphObject) obj).getInnerJSONObject();
            }
            if (GraphObjectList.class.isAssignableFrom(objClass)) {
                return ((GraphObjectList) obj).getInnerJSONArray();
            }
            if (!Iterable.class.isAssignableFrom(objClass)) {
                return obj;
            }
            Object jsonArray = new JSONArray();
            for (Object o : (Iterable) obj) {
                if (GraphObject.class.isAssignableFrom(o.getClass())) {
                    jsonArray.put(((GraphObject) o).getInnerJSONObject());
                } else {
                    jsonArray.put(o);
                }
            }
            return jsonArray;
        }
    }

    Map<String, Object> asMap();

    <T extends GraphObject> T cast(Class<T> cls);

    JSONObject getInnerJSONObject();

    Object getProperty(String str);

    <T extends GraphObject> T getPropertyAs(String str, Class<T> cls);

    <T extends GraphObject> GraphObjectList<T> getPropertyAsList(String str, Class<T> cls);

    void removeProperty(String str);

    void setProperty(String str, Object obj);
}
