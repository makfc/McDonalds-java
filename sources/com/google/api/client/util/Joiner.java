package com.google.api.client.util;

public final class Joiner {
    private final com.google.api.client.repackaged.com.google.common.base.Joiner wrapped;

    /* renamed from: on */
    public static Joiner m7512on(char separator) {
        return new Joiner(com.google.api.client.repackaged.com.google.common.base.Joiner.m7507on(separator));
    }

    private Joiner(com.google.api.client.repackaged.com.google.common.base.Joiner wrapped) {
        this.wrapped = wrapped;
    }

    public final String join(Iterable<?> parts) {
        return this.wrapped.join((Iterable) parts);
    }
}
