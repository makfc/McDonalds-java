package com.google.api.client.util.store;

import java.io.IOException;
import java.io.Serializable;
import java.util.Set;

public interface DataStore<V extends Serializable> {
    V get(String str) throws IOException;

    Set<String> keySet() throws IOException;

    DataStore<V> set(String str, V v) throws IOException;
}
