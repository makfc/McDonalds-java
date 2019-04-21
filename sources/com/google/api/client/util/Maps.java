package com.google.api.client.util;

import java.util.HashMap;

public final class Maps {
    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap();
    }

    private Maps() {
    }
}
