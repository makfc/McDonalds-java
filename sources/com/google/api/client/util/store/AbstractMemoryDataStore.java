package com.google.api.client.util.store;

import com.google.api.client.util.IOUtils;
import com.google.api.client.util.Maps;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class AbstractMemoryDataStore<V extends Serializable> extends AbstractDataStore<V> {
    HashMap<String, byte[]> keyValueMap = Maps.newHashMap();
    private final Lock lock = new ReentrantLock();

    protected AbstractMemoryDataStore(DataStoreFactory dataStoreFactory, String id) {
        super(dataStoreFactory, id);
    }

    public final Set<String> keySet() throws IOException {
        this.lock.lock();
        try {
            Set<String> unmodifiableSet = Collections.unmodifiableSet(this.keyValueMap.keySet());
            return unmodifiableSet;
        } finally {
            this.lock.unlock();
        }
    }

    public final V get(String key) throws IOException {
        if (key == null) {
            return null;
        }
        this.lock.lock();
        try {
            V deserialize = IOUtils.deserialize((byte[]) this.keyValueMap.get(key));
            return deserialize;
        } finally {
            this.lock.unlock();
        }
    }

    public final DataStore<V> set(String key, V value) throws IOException {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(value);
        this.lock.lock();
        try {
            this.keyValueMap.put(key, IOUtils.serialize(value));
            save();
            return this;
        } finally {
            this.lock.unlock();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void save() throws IOException {
    }

    public String toString() {
        return DataStoreUtils.toString(this);
    }
}
