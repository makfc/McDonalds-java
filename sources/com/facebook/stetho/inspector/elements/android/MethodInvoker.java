package com.facebook.stetho.inspector.elements.android;

import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.Util;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class MethodInvoker {
    private static final List<TypedMethodInvoker<?>> invokers = Arrays.asList(new TypedMethodInvoker[]{new StringMethodInvoker(), new CharSequenceMethodInvoker(), new IntegerMethodInvoker(), new FloatMethodInvoker(), new BooleanMethodInvoker()});

    private static abstract class TypedMethodInvoker<T> {
        private final Class<T> mArgType;

        public abstract T convertArgument(String str);

        TypedMethodInvoker(Class<T> argType) {
            this.mArgType = argType;
        }

        /* Access modifiers changed, original: 0000 */
        public boolean invoke(Object receiver, String methodName, String argument) {
            try {
                receiver.getClass().getMethod(methodName, new Class[]{this.mArgType}).invoke(receiver, new Object[]{convertArgument(argument)});
                return true;
            } catch (NoSuchMethodException e) {
            } catch (InvocationTargetException e2) {
                LogUtil.m7459w("InvocationTargetException: " + e2.getMessage());
            } catch (IllegalAccessException e3) {
                LogUtil.m7459w("IllegalAccessException: " + e3.getMessage());
            } catch (IllegalArgumentException e4) {
                LogUtil.m7459w("IllegalArgumentException: " + e4.getMessage());
            }
            return false;
        }
    }

    private static class BooleanMethodInvoker extends TypedMethodInvoker<Boolean> {
        BooleanMethodInvoker() {
            super(Boolean.TYPE);
        }

        /* Access modifiers changed, original: 0000 */
        public Boolean convertArgument(String argument) {
            return Boolean.valueOf(Boolean.parseBoolean(argument));
        }
    }

    private static class CharSequenceMethodInvoker extends TypedMethodInvoker<CharSequence> {
        CharSequenceMethodInvoker() {
            super(CharSequence.class);
        }

        /* Access modifiers changed, original: 0000 */
        public CharSequence convertArgument(String argument) {
            return argument;
        }
    }

    private static class FloatMethodInvoker extends TypedMethodInvoker<Float> {
        FloatMethodInvoker() {
            super(Float.TYPE);
        }

        /* Access modifiers changed, original: 0000 */
        public Float convertArgument(String argument) {
            return Float.valueOf(Float.parseFloat(argument));
        }
    }

    private static class IntegerMethodInvoker extends TypedMethodInvoker<Integer> {
        IntegerMethodInvoker() {
            super(Integer.TYPE);
        }

        /* Access modifiers changed, original: 0000 */
        public Integer convertArgument(String argument) {
            return Integer.valueOf(Integer.parseInt(argument));
        }
    }

    private static class StringMethodInvoker extends TypedMethodInvoker<String> {
        StringMethodInvoker() {
            super(String.class);
        }

        /* Access modifiers changed, original: 0000 */
        public String convertArgument(String argument) {
            return argument;
        }
    }

    public void invoke(Object receiver, String methodName, String argument) {
        Util.throwIfNull(receiver, methodName, argument);
        int size = invokers.size();
        int i = 0;
        while (i < size) {
            if (!((TypedMethodInvoker) invokers.get(i)).invoke(receiver, methodName, argument)) {
                i++;
            } else {
                return;
            }
        }
        LogUtil.m7459w("Method with name " + methodName + " not found for any of the MethodInvoker supported argument types.");
    }
}
