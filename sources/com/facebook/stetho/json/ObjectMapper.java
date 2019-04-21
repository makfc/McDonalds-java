package com.facebook.stetho.json;

import com.facebook.stetho.common.ExceptionUtil;
import com.facebook.stetho.json.annotation.JsonProperty;
import com.facebook.stetho.json.annotation.JsonValue;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ObjectMapper {
    public <T> T convertValue(Object fromValue, Class<T> toValueType) throws IllegalArgumentException {
        if (fromValue == null) {
            return null;
        }
        if (toValueType != Object.class && toValueType.isAssignableFrom(fromValue.getClass())) {
            return fromValue;
        }
        try {
            if (fromValue instanceof JSONObject) {
                return _convertFromJSONObject((JSONObject) fromValue, toValueType);
            }
            if (toValueType == JSONObject.class) {
                return _convertToJSONObject(fromValue);
            }
            throw new IllegalArgumentException("Expecting either fromValue or toValueType to be a JSONObject");
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(e);
        } catch (IllegalAccessException e2) {
            throw new IllegalArgumentException(e2);
        } catch (InstantiationException e3) {
            throw new IllegalArgumentException(e3);
        } catch (JSONException e4) {
            throw new IllegalArgumentException(e4);
        } catch (InvocationTargetException e5) {
            throw ExceptionUtil.propagate(e5.getCause());
        }
    }

    private <T> T _convertFromJSONObject(JSONObject jsonObject, Class<T> type) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, JSONException {
        Constructor<T> constructor = type.getDeclaredConstructor((Class[]) null);
        constructor.setAccessible(true);
        T instance = constructor.newInstance(new Object[0]);
        Field[] fields = type.getFields();
        int i = 0;
        while (i < fields.length) {
            Field field = fields[i];
            Object value = jsonObject.opt(field.getName());
            Object setValue = getValueForField(field, value);
            try {
                field.set(instance, getValueForField(field, value));
                i++;
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Class: " + type.getSimpleName() + " " + "Field: " + field.getName() + " type " + setValue.getClass().getName(), e);
            }
        }
        return instance;
    }

    private Object getValueForField(Field field, Object value) throws JSONException {
        if (value == null) {
            return value;
        }
        try {
            if (value == JSONObject.NULL) {
                return null;
            }
            if (value.getClass() == field.getType()) {
                return value;
            }
            if (value instanceof JSONObject) {
                return convertValue(value, field.getType());
            }
            if (field.getType().isEnum()) {
                return getEnumValue((String) value, field.getType().asSubclass(Enum.class));
            }
            if (value instanceof JSONArray) {
                return convertArrayToList(field, (JSONArray) value);
            }
            if (!(value instanceof Number)) {
                return value;
            }
            Number numberValue = (Number) value;
            Class<?> clazz = field.getType();
            if (clazz == Integer.class || clazz == Integer.TYPE) {
                return Integer.valueOf(numberValue.intValue());
            }
            if (clazz == Long.class || clazz == Long.TYPE) {
                return Long.valueOf(numberValue.longValue());
            }
            if (clazz == Double.class || clazz == Double.TYPE) {
                return Double.valueOf(numberValue.doubleValue());
            }
            if (clazz == Float.class || clazz == Float.TYPE) {
                return Float.valueOf(numberValue.floatValue());
            }
            if (clazz == Byte.class || clazz == Byte.TYPE) {
                return Byte.valueOf(numberValue.byteValue());
            }
            if (clazz == Short.class || clazz == Short.TYPE) {
                return Short.valueOf(numberValue.shortValue());
            }
            throw new IllegalArgumentException("Not setup to handle class " + clazz.getName());
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Unable to set value for field " + field.getName(), e);
        }
    }

    private Enum getEnumValue(String value, Class<? extends Enum> clazz) {
        Method method = getJsonValueMethod(clazz);
        if (method != null) {
            return getEnumByMethod(value, clazz, method);
        }
        return Enum.valueOf(clazz, value);
    }

    private Enum getEnumByMethod(String value, Class<? extends Enum> clazz, Method method) {
        Enum[] enumValues = (Enum[]) clazz.getEnumConstants();
        int i = 0;
        while (i < enumValues.length) {
            Enum enumValue = enumValues[i];
            try {
                Object o = method.invoke(enumValue, new Object[0]);
                if (o != null && o.toString().equals(value)) {
                    return enumValue;
                }
                i++;
            } catch (Exception ex) {
                throw new IllegalArgumentException(ex);
            }
        }
        throw new IllegalArgumentException("No enum constant " + clazz.getName() + "." + value);
    }

    private List<Object> convertArrayToList(Field field, JSONArray array) throws IllegalAccessException, JSONException {
        if (List.class.isAssignableFrom(field.getType())) {
            Type[] types = ((ParameterizedType) field.getGenericType()).getActualTypeArguments();
            if (types.length != 1) {
                throw new IllegalArgumentException("Only able to handle a single type in a list " + field.getName());
            }
            Class arrayClass = types[0];
            List<Object> objectList = new ArrayList();
            for (int i = 0; i < array.length(); i++) {
                if (arrayClass.isEnum()) {
                    objectList.add(getEnumValue(array.getString(i), arrayClass));
                } else if (canDirectlySerializeClass(arrayClass)) {
                    objectList.add(array.get(i));
                } else {
                    JSONObject jsonObject = array.getJSONObject(i);
                    if (jsonObject == null) {
                        objectList.add(null);
                    } else {
                        objectList.add(convertValue(jsonObject, arrayClass));
                    }
                }
            }
            return objectList;
        }
        throw new IllegalArgumentException("only know how to deserialize List<?> on field " + field.getName());
    }

    private JSONObject _convertToJSONObject(Object fromValue) throws JSONException, InvocationTargetException, IllegalAccessException {
        JSONObject jsonObject = new JSONObject();
        Field[] fields = fromValue.getClass().getFields();
        for (int i = 0; i < fields.length; i++) {
            JsonProperty property = (JsonProperty) fields[i].getAnnotation(JsonProperty.class);
            if (property != null) {
                Object value = fields[i].get(fromValue);
                Class clazz = fields[i].getType();
                if (value != null) {
                    clazz = value.getClass();
                }
                String name = fields[i].getName();
                if (property.required() && value == null) {
                    value = JSONObject.NULL;
                } else if (value != JSONObject.NULL) {
                    value = getJsonValue(value, clazz, fields[i]);
                }
                jsonObject.put(name, value);
            }
        }
        return jsonObject;
    }

    private Object getJsonValue(Object value, Class<?> clazz, Field field) throws InvocationTargetException, IllegalAccessException {
        if (value == null) {
            return null;
        }
        if (List.class.isAssignableFrom(clazz)) {
            return convertListToJsonArray(value);
        }
        Method m = getJsonValueMethod(clazz);
        if (m != null) {
            return m.invoke(value, new Object[0]);
        }
        if (canDirectlySerializeClass(clazz)) {
            return value;
        }
        return convertValue(value, JSONObject.class);
    }

    private JSONArray convertListToJsonArray(Object value) throws InvocationTargetException, IllegalAccessException {
        JSONArray array = new JSONArray();
        for (Object obj : (List) value) {
            array.put(obj != null ? getJsonValue(obj, obj.getClass(), null) : null);
        }
        return array;
    }

    @Nullable
    private static Method getJsonValueMethod(Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getAnnotation(JsonValue.class) != null) {
                return methods[i];
            }
        }
        return null;
    }

    private static boolean canDirectlySerializeClass(Class clazz) {
        return isWrapperOrPrimitiveType(clazz) || clazz.equals(String.class);
    }

    private static boolean isWrapperOrPrimitiveType(Class<?> clazz) {
        return clazz.isPrimitive() || clazz.equals(Boolean.class) || clazz.equals(Integer.class) || clazz.equals(Character.class) || clazz.equals(Byte.class) || clazz.equals(Short.class) || clazz.equals(Double.class) || clazz.equals(Long.class) || clazz.equals(Float.class);
    }
}
