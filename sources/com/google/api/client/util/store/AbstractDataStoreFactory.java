package com.google.api.client.util.store;

import com.google.api.client.util.Maps;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

public abstract class AbstractDataStoreFactory implements DataStoreFactory {
    private static final Pattern ID_PATTERN = Pattern.compile("\\w{1,30}");
    private final Map<String, DataStore<? extends Serializable>> dataStoreMap = Maps.newHashMap();
    private final Lock lock = new ReentrantLock();

    public abstract <V extends Serializable> DataStore<V> createDataStore(String str) throws IOException;

    public final <V extends Serializable> DataStore<V> getDataStore(String id) throws IOException {
        Preconditions.checkArgument(ID_PATTERN.matcher(id).matches(), "%s does not match pattern %s", id, ID_PATTERN);
        this.lock.lock();
        try {
            DataStore<V> dataStore = (DataStore) this.dataStoreMap.get(id);
            if (dataStore == null) {
                dataStore = createDataStore(id);
                this.dataStoreMap.put(id, dataStore);
            }
            this.lock.unlock();
            return dataStore;
        } catch (Throwable th) {
            this.lock.unlock();
        }
    }
}
