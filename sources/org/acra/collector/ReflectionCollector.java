package org.acra.collector;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

final class ReflectionCollector {
    ReflectionCollector() {
    }

    public static String collectConstants(Class<?> someClass, String prefix) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Field field : someClass.getFields()) {
            if (prefix != null && prefix.length() > 0) {
                stringBuilder.append(prefix).append('.');
            }
            stringBuilder.append(field.getName()).append("=");
            try {
                Object obj = field.get(null);
                if (obj != null) {
                    if (field.getType().isArray()) {
                        stringBuilder.append(Arrays.toString((Object[]) obj));
                    } else {
                        stringBuilder.append(obj.toString());
                    }
                }
            } catch (IllegalArgumentException e) {
                stringBuilder.append("N/A");
            } catch (IllegalAccessException e2) {
                stringBuilder.append("N/A");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static String collectStaticGettersResults(Class<?> someClass) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Method method : someClass.getMethods()) {
            if (method.getParameterTypes().length == 0 && ((method.getName().startsWith("get") || method.getName().startsWith("is")) && !method.getName().equals("getClass"))) {
                try {
                    stringBuilder.append(method.getName());
                    stringBuilder.append('=');
                    stringBuilder.append(method.invoke(null, null));
                    stringBuilder.append("\n");
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                }
            }
        }
        return stringBuilder.toString();
    }

    public static String collectConstants(Class<?> someClass) {
        return collectConstants(someClass, "");
    }
}
