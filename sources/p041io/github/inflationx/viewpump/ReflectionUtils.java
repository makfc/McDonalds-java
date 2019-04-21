package p041io.github.inflationx.viewpump;

import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: io.github.inflationx.viewpump.ReflectionUtils */
public class ReflectionUtils {
    private static final String TAG = ReflectionUtils.class.getSimpleName();

    static Field getField(Class clazz, String fieldName) {
        try {
            Field f = clazz.getDeclaredField(fieldName);
            f.setAccessible(true);
            return f;
        } catch (NoSuchFieldException e) {
            return null;
        }
    }

    static Object getValue(Field field, Object obj) {
        try {
            return field.get(obj);
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    static void setValue(Field field, Object obj, Object value) {
        try {
            field.set(obj, value);
        } catch (IllegalAccessException e) {
        }
    }

    public static Method getMethod(Class clazz, String methodName) {
        for (Method method : clazz.getMethods()) {
            if (method.getName().equals(methodName)) {
                method.setAccessible(true);
                return method;
            }
        }
        return null;
    }

    public static void invokeMethod(Object object, Method method, Object... args) {
        if (method != null) {
            try {
                method.invoke(object, args);
            } catch (IllegalAccessException e) {
                Log.d(TAG, "Can't access method using reflection", e);
            } catch (InvocationTargetException e2) {
                Log.d(TAG, "Can't invoke method using reflection", e2);
            }
        }
    }
}
