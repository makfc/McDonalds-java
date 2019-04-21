package com.facebook;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SharedPreferencesTokenCachingStrategy extends TokenCachingStrategy {
    private static final String DEFAULT_CACHE_KEY = "com.facebook.SharedPreferencesTokenCachingStrategy.DEFAULT_KEY";
    private static final String JSON_VALUE = "value";
    private static final String JSON_VALUE_ENUM_TYPE = "enumType";
    private static final String JSON_VALUE_TYPE = "valueType";
    private static final String TAG = SharedPreferencesTokenCachingStrategy.class.getSimpleName();
    private static final String TYPE_BOOLEAN = "bool";
    private static final String TYPE_BOOLEAN_ARRAY = "bool[]";
    private static final String TYPE_BYTE = "byte";
    private static final String TYPE_BYTE_ARRAY = "byte[]";
    private static final String TYPE_CHAR = "char";
    private static final String TYPE_CHAR_ARRAY = "char[]";
    private static final String TYPE_DOUBLE = "double";
    private static final String TYPE_DOUBLE_ARRAY = "double[]";
    private static final String TYPE_ENUM = "enum";
    private static final String TYPE_FLOAT = "float";
    private static final String TYPE_FLOAT_ARRAY = "float[]";
    private static final String TYPE_INTEGER = "int";
    private static final String TYPE_INTEGER_ARRAY = "int[]";
    private static final String TYPE_LONG = "long";
    private static final String TYPE_LONG_ARRAY = "long[]";
    private static final String TYPE_SHORT = "short";
    private static final String TYPE_SHORT_ARRAY = "short[]";
    private static final String TYPE_STRING = "string";
    private static final String TYPE_STRING_LIST = "stringList";
    private SharedPreferences cache;
    private String cacheKey;

    public SharedPreferencesTokenCachingStrategy(Context context) {
        this(context, null);
    }

    public SharedPreferencesTokenCachingStrategy(Context context, String cacheKey) {
        Validate.notNull(context, "context");
        if (Utility.isNullOrEmpty(cacheKey)) {
            cacheKey = DEFAULT_CACHE_KEY;
        }
        this.cacheKey = cacheKey;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        this.cache = context.getSharedPreferences(this.cacheKey, 0);
    }

    public Bundle load() {
        Bundle settings = new Bundle();
        for (String key : this.cache.getAll().keySet()) {
            try {
                deserializeKey(key, settings);
            } catch (JSONException e) {
                Logger.log(LoggingBehavior.CACHE, 5, TAG, "Error reading cached value for key: '" + key + "' -- " + e);
                return null;
            }
        }
        return settings;
    }

    public void save(Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        Editor editor = this.cache.edit();
        for (String key : bundle.keySet()) {
            try {
                serializeKey(key, bundle, editor);
            } catch (JSONException e) {
                Logger.log(LoggingBehavior.CACHE, 5, TAG, "Error processing value for key: '" + key + "' -- " + e);
                return;
            }
        }
        editor.apply();
    }

    public void clear() {
        this.cache.edit().clear().apply();
    }

    private void serializeKey(String key, Bundle bundle, Editor editor) throws JSONException {
        List<String> value = bundle.get(key);
        if (value != null) {
            String supportedType = null;
            JSONArray jsonArray = null;
            JSONObject json = new JSONObject();
            if (value instanceof Byte) {
                supportedType = TYPE_BYTE;
                json.put("value", ((Byte) value).intValue());
            } else if (value instanceof Short) {
                supportedType = TYPE_SHORT;
                json.put("value", ((Short) value).intValue());
            } else if (value instanceof Integer) {
                supportedType = TYPE_INTEGER;
                json.put("value", ((Integer) value).intValue());
            } else if (value instanceof Long) {
                supportedType = TYPE_LONG;
                json.put("value", ((Long) value).longValue());
            } else if (value instanceof Float) {
                supportedType = TYPE_FLOAT;
                json.put("value", ((Float) value).doubleValue());
            } else if (value instanceof Double) {
                supportedType = TYPE_DOUBLE;
                json.put("value", ((Double) value).doubleValue());
            } else if (value instanceof Boolean) {
                supportedType = TYPE_BOOLEAN;
                json.put("value", ((Boolean) value).booleanValue());
            } else if (value instanceof Character) {
                supportedType = TYPE_CHAR;
                json.put("value", value.toString());
            } else if (value instanceof String) {
                supportedType = TYPE_STRING;
                json.put("value", (String) value);
            } else if (value instanceof Enum) {
                supportedType = TYPE_ENUM;
                json.put("value", value.toString());
                json.put(JSON_VALUE_ENUM_TYPE, value.getClass().getName());
            } else {
                jsonArray = new JSONArray();
                if (value instanceof byte[]) {
                    supportedType = TYPE_BYTE_ARRAY;
                    for (byte v : (byte[]) value) {
                        jsonArray.put(v);
                    }
                } else if (value instanceof short[]) {
                    supportedType = TYPE_SHORT_ARRAY;
                    for (short v2 : (short[]) value) {
                        jsonArray.put(v2);
                    }
                } else if (value instanceof int[]) {
                    supportedType = TYPE_INTEGER_ARRAY;
                    for (int v3 : (int[]) value) {
                        jsonArray.put(v3);
                    }
                } else if (value instanceof long[]) {
                    supportedType = TYPE_LONG_ARRAY;
                    for (long v4 : (long[]) value) {
                        jsonArray.put(v4);
                    }
                } else if (value instanceof float[]) {
                    supportedType = TYPE_FLOAT_ARRAY;
                    for (float v5 : (float[]) value) {
                        jsonArray.put((double) v5);
                    }
                } else if (value instanceof double[]) {
                    supportedType = TYPE_DOUBLE_ARRAY;
                    for (double v6 : (double[]) value) {
                        jsonArray.put(v6);
                    }
                } else if (value instanceof boolean[]) {
                    supportedType = TYPE_BOOLEAN_ARRAY;
                    for (boolean v7 : (boolean[]) value) {
                        jsonArray.put(v7);
                    }
                } else if (value instanceof char[]) {
                    supportedType = TYPE_CHAR_ARRAY;
                    for (char v8 : (char[]) value) {
                        jsonArray.put(String.valueOf(v8));
                    }
                } else if (value instanceof List) {
                    supportedType = TYPE_STRING_LIST;
                    for (String v9 : value) {
                        String v92;
                        if (v92 == null) {
                            v92 = JSONObject.NULL;
                        }
                        jsonArray.put(v92);
                    }
                } else {
                    jsonArray = null;
                }
            }
            if (supportedType != null) {
                json.put(JSON_VALUE_TYPE, supportedType);
                if (jsonArray != null) {
                    json.putOpt("value", jsonArray);
                }
                editor.putString(key, !(json instanceof JSONObject) ? json.toString() : JSONObjectInstrumentation.toString(json));
            }
        }
    }

    private void deserializeKey(String key, Bundle bundle) throws JSONException {
        JSONObject json = JSONObjectInstrumentation.init(this.cache.getString(key, "{}"));
        String valueType = json.getString(JSON_VALUE_TYPE);
        JSONArray jsonArray;
        int i;
        String charString;
        if (valueType.equals(TYPE_BOOLEAN)) {
            bundle.putBoolean(key, json.getBoolean("value"));
        } else if (valueType.equals(TYPE_BOOLEAN_ARRAY)) {
            jsonArray = json.getJSONArray("value");
            boolean[] array = new boolean[jsonArray.length()];
            for (i = 0; i < array.length; i++) {
                array[i] = jsonArray.getBoolean(i);
            }
            bundle.putBooleanArray(key, array);
        } else if (valueType.equals(TYPE_BYTE)) {
            bundle.putByte(key, (byte) json.getInt("value"));
        } else if (valueType.equals(TYPE_BYTE_ARRAY)) {
            jsonArray = json.getJSONArray("value");
            byte[] array2 = new byte[jsonArray.length()];
            for (i = 0; i < array2.length; i++) {
                array2[i] = (byte) jsonArray.getInt(i);
            }
            bundle.putByteArray(key, array2);
        } else if (valueType.equals(TYPE_SHORT)) {
            bundle.putShort(key, (short) json.getInt("value"));
        } else if (valueType.equals(TYPE_SHORT_ARRAY)) {
            jsonArray = json.getJSONArray("value");
            short[] array3 = new short[jsonArray.length()];
            for (i = 0; i < array3.length; i++) {
                array3[i] = (short) jsonArray.getInt(i);
            }
            bundle.putShortArray(key, array3);
        } else if (valueType.equals(TYPE_INTEGER)) {
            bundle.putInt(key, json.getInt("value"));
        } else if (valueType.equals(TYPE_INTEGER_ARRAY)) {
            jsonArray = json.getJSONArray("value");
            int[] array4 = new int[jsonArray.length()];
            for (i = 0; i < array4.length; i++) {
                array4[i] = jsonArray.getInt(i);
            }
            bundle.putIntArray(key, array4);
        } else if (valueType.equals(TYPE_LONG)) {
            bundle.putLong(key, json.getLong("value"));
        } else if (valueType.equals(TYPE_LONG_ARRAY)) {
            jsonArray = json.getJSONArray("value");
            long[] array5 = new long[jsonArray.length()];
            for (i = 0; i < array5.length; i++) {
                array5[i] = jsonArray.getLong(i);
            }
            bundle.putLongArray(key, array5);
        } else if (valueType.equals(TYPE_FLOAT)) {
            bundle.putFloat(key, (float) json.getDouble("value"));
        } else if (valueType.equals(TYPE_FLOAT_ARRAY)) {
            jsonArray = json.getJSONArray("value");
            float[] array6 = new float[jsonArray.length()];
            for (i = 0; i < array6.length; i++) {
                array6[i] = (float) jsonArray.getDouble(i);
            }
            bundle.putFloatArray(key, array6);
        } else if (valueType.equals(TYPE_DOUBLE)) {
            bundle.putDouble(key, json.getDouble("value"));
        } else if (valueType.equals(TYPE_DOUBLE_ARRAY)) {
            jsonArray = json.getJSONArray("value");
            double[] array7 = new double[jsonArray.length()];
            for (i = 0; i < array7.length; i++) {
                array7[i] = jsonArray.getDouble(i);
            }
            bundle.putDoubleArray(key, array7);
        } else if (valueType.equals(TYPE_CHAR)) {
            charString = json.getString("value");
            if (charString != null && charString.length() == 1) {
                bundle.putChar(key, charString.charAt(0));
            }
        } else if (valueType.equals(TYPE_CHAR_ARRAY)) {
            jsonArray = json.getJSONArray("value");
            char[] array8 = new char[jsonArray.length()];
            for (i = 0; i < array8.length; i++) {
                charString = jsonArray.getString(i);
                if (charString != null && charString.length() == 1) {
                    array8[i] = charString.charAt(0);
                }
            }
            bundle.putCharArray(key, array8);
        } else if (valueType.equals(TYPE_STRING)) {
            bundle.putString(key, json.getString("value"));
        } else if (valueType.equals(TYPE_STRING_LIST)) {
            jsonArray = json.getJSONArray("value");
            int numStrings = jsonArray.length();
            ArrayList<String> stringList = new ArrayList(numStrings);
            for (i = 0; i < numStrings; i++) {
                Object jsonStringValue = jsonArray.get(i);
                if (jsonStringValue == JSONObject.NULL) {
                    jsonStringValue = null;
                } else {
                    String jsonStringValue2 = (String) jsonStringValue;
                }
                stringList.add(i, jsonStringValue);
            }
            bundle.putStringArrayList(key, stringList);
        } else if (valueType.equals(TYPE_ENUM)) {
            try {
                bundle.putSerializable(key, Enum.valueOf(Class.forName(json.getString(JSON_VALUE_ENUM_TYPE)), json.getString("value")));
            } catch (ClassNotFoundException | IllegalArgumentException e) {
            }
        }
    }
}
