package com.google.api.client.util;

public final class Objects {

    public static final class ToStringHelper {
        private final com.google.api.client.repackaged.com.google.common.base.Objects.ToStringHelper wrapped;

        ToStringHelper(com.google.api.client.repackaged.com.google.common.base.Objects.ToStringHelper wrapped) {
            this.wrapped = wrapped;
        }

        public ToStringHelper add(String name, Object value) {
            this.wrapped.add(name, value);
            return this;
        }

        public String toString() {
            return this.wrapped.toString();
        }
    }

    public static boolean equal(Object a, Object b) {
        return com.google.api.client.repackaged.com.google.common.base.Objects.equal(a, b);
    }

    public static ToStringHelper toStringHelper(Object self) {
        return new ToStringHelper(com.google.api.client.repackaged.com.google.common.base.Objects.toStringHelper(self));
    }

    private Objects() {
    }
}
