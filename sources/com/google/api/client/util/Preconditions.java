package com.google.api.client.util;

public final class Preconditions {
    public static void checkArgument(boolean expression) {
        com.google.api.client.repackaged.com.google.common.base.Preconditions.checkArgument(expression);
    }

    public static void checkArgument(boolean expression, Object errorMessage) {
        com.google.api.client.repackaged.com.google.common.base.Preconditions.checkArgument(expression, errorMessage);
    }

    public static void checkArgument(boolean expression, String errorMessageTemplate, Object... errorMessageArgs) {
        com.google.api.client.repackaged.com.google.common.base.Preconditions.checkArgument(expression, errorMessageTemplate, errorMessageArgs);
    }

    public static void checkState(boolean expression) {
        com.google.api.client.repackaged.com.google.common.base.Preconditions.checkState(expression);
    }

    public static <T> T checkNotNull(T reference) {
        return com.google.api.client.repackaged.com.google.common.base.Preconditions.checkNotNull(reference);
    }

    public static <T> T checkNotNull(T reference, Object errorMessage) {
        return com.google.api.client.repackaged.com.google.common.base.Preconditions.checkNotNull(reference, errorMessage);
    }

    public static <T> T checkNotNull(T reference, String errorMessageTemplate, Object... errorMessageArgs) {
        return com.google.api.client.repackaged.com.google.common.base.Preconditions.checkNotNull(reference, errorMessageTemplate, errorMessageArgs);
    }

    private Preconditions() {
    }
}
