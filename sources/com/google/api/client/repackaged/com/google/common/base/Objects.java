package com.google.api.client.repackaged.com.google.common.base;

import com.google.api.client.repackaged.com.google.common.annotations.GwtCompatible;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nullable;

@GwtCompatible
public final class Objects {

    public static final class ToStringHelper {
        private final String className;
        private boolean omitNullValues;
        private final List<ValueHolder> valueHolders;

        private static final class ValueHolder {
            final StringBuilder builder;
            boolean isNull;

            private ValueHolder() {
                this.builder = new StringBuilder();
            }
        }

        private ToStringHelper(String className) {
            this.valueHolders = new LinkedList();
            this.omitNullValues = false;
            this.className = (String) Preconditions.checkNotNull(className);
        }

        public ToStringHelper add(String name, @Nullable Object value) {
            Preconditions.checkNotNull(name);
            addHolder(value).builder.append(name).append('=').append(value);
            return this;
        }

        public String toString() {
            boolean omitNullValuesSnapshot = this.omitNullValues;
            boolean needsSeparator = false;
            StringBuilder builder = new StringBuilder(32).append(this.className).append('{');
            for (ValueHolder valueHolder : this.valueHolders) {
                if (!omitNullValuesSnapshot || !valueHolder.isNull) {
                    if (needsSeparator) {
                        builder.append(", ");
                    } else {
                        needsSeparator = true;
                    }
                    builder.append(valueHolder.builder);
                }
            }
            return builder.append('}').toString();
        }

        private ValueHolder addHolder() {
            ValueHolder valueHolder = new ValueHolder();
            this.valueHolders.add(valueHolder);
            return valueHolder;
        }

        private ValueHolder addHolder(@Nullable Object value) {
            ValueHolder valueHolder = addHolder();
            valueHolder.isNull = value == null;
            return valueHolder;
        }
    }

    private Objects() {
    }

    public static boolean equal(@Nullable Object a, @Nullable Object b) {
        return a == b || (a != null && a.equals(b));
    }

    public static ToStringHelper toStringHelper(Object self) {
        return new ToStringHelper(simpleName(self.getClass()));
    }

    private static String simpleName(Class<?> clazz) {
        String name = clazz.getName().replaceAll("\\$[0-9]+", "\\$");
        int start = name.lastIndexOf(36);
        if (start == -1) {
            start = name.lastIndexOf(46);
        }
        return name.substring(start + 1);
    }
}
