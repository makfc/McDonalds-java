package com.google.api.client.util;

import java.util.HashSet;

public final class Sets {
    public static <E> HashSet<E> newHashSet() {
        return new HashSet();
    }

    private Sets() {
    }
}
