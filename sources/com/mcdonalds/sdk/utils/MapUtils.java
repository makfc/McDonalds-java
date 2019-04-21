package com.mcdonalds.sdk.utils;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapUtils {
    public static Map<String, Object> copy(Map<String, Object> original) {
        Map<String, Object> result = new LinkedHashMap();
        for (Entry<String, Object> entry : original.entrySet()) {
            String entryKey = (String) entry.getKey();
            Map<String, Object> entryValue = entry.getValue();
            if (entryValue instanceof Map) {
                result.put(entryKey, copy(entryValue));
            } else {
                result.put(entryKey, entryValue);
            }
        }
        return result;
    }

    public static Map<String, Object> merge(Map<String, Object> oldMap, Map<String, Object> newMap) {
        if (newMap == null) {
            return oldMap;
        }
        Map<String, Object> result = copy(oldMap);
        for (String key : newMap.keySet()) {
            Object newValue = newMap.get(key);
            if (newValue == null) {
                result.remove(key);
            } else if (newValue instanceof Map) {
                Object oldValue = oldMap.get(key);
                if (oldValue instanceof Map) {
                    result.put(key, merge((Map) oldValue, (Map) newValue));
                } else {
                    result.put(key, newValue);
                }
            } else {
                result.put(key, newValue);
            }
        }
        return result;
    }

    public static <T> T get(Map<String, ?> map, String key) {
        if (key != null) {
            return get((Map) map, key.split("\\."));
        }
        return null;
    }

    private static <T> T get(Map<String, ?> map, String[] args) {
        if (args == null || map == null) {
            return null;
        }
        T result = map.get(args[0]);
        if (args.length <= 1 || !(result instanceof Map)) {
            return result;
        }
        return get((Map) result, (String[]) Arrays.copyOfRange(args, 1, args.length));
    }

    public static <T> void put(Map<String, Object> map, String key, T value) {
        if (key != null) {
            put((Map) map, key.split("\\."), (Object) value);
        }
    }

    private static <T> void put(Map<String, Object> map, String[] keyArray, T value) {
        if (keyArray != null && map != null) {
            String key = keyArray[0];
            Object result = map.get(key);
            if (keyArray.length > 1) {
                String[] subKeyArray = (String[]) Arrays.copyOfRange(keyArray, 1, keyArray.length);
                if (result == null || !(result instanceof Map)) {
                    Map subMap = new LinkedHashMap();
                    put(subMap, subKeyArray, (Object) value);
                    map.put(key, subMap);
                    return;
                }
                put((Map) result, subKeyArray, (Object) value);
            } else if (value == null || !(value instanceof Map)) {
                map.put(key, value);
            } else {
                Object currentObject = get((Map) map, key);
                if (currentObject == null || !(currentObject instanceof Map)) {
                    map.put(key, value);
                } else {
                    map.put(key, merge((Map) currentObject, (Map) value));
                }
            }
        }
    }

    public static boolean mapEquals(Map<String, Object> left, Map<String, Object> right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        boolean result = true;
        if (left.size() != right.size()) {
            result = false;
        }
        for (String leftKey : left.keySet()) {
            Map<String, Object> leftValue = left.get(leftKey);
            Map<String, Object> rightValue = right.get(leftKey);
            if (leftValue == null) {
                if (right.get(leftKey) != null || !right.containsKey(leftKey)) {
                    result = false;
                }
            } else if ((leftValue instanceof Map) && (rightValue instanceof Map)) {
                if (!mapEquals(leftValue, rightValue)) {
                    result = false;
                }
            } else if (!leftValue.equals(rightValue)) {
                result = false;
            }
        }
        return result;
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static Map<String, Object> flatten(Map<String, Object> bigMap) {
        Map<String, Object> result = new LinkedHashMap();
        for (Entry<String, Object> entry : bigMap.entrySet()) {
            if (entry.getValue() instanceof Map) {
                result.putAll(flatten((String) entry.getKey(), (Map) entry.getValue()));
            } else if (entry.getValue() != null) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    private static Map<String, Object> flatten(String rootKey, Map<String, Object> subMap) {
        Map<String, Object> result = new LinkedHashMap();
        for (Entry<String, Object> entry : subMap.entrySet()) {
            if (entry.getValue() instanceof Map) {
                result.putAll(flatten((String) entry.getKey(), (Map) entry.getValue()));
            } else if (entry.getValue() != null) {
                result.put(rootKey + "." + ((String) entry.getKey()), entry.getValue());
            }
        }
        return result;
    }
}
