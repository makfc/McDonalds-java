package com.google.api.client.util.store;

import java.io.IOException;
import java.io.Serializable;

public class MemoryDataStoreFactory extends AbstractDataStoreFactory {

    static class InstanceHolder {
        static final MemoryDataStoreFactory INSTANCE = new MemoryDataStoreFactory();

        InstanceHolder() {
        }
    }

    static class MemoryDataStore<V extends Serializable> extends AbstractMemoryDataStore<V> {
        MemoryDataStore(MemoryDataStoreFactory dataStore, String id) {
            super(dataStore, id);
        }
    }

    /* Access modifiers changed, original: protected */
    public <V extends Serializable> DataStore<V> createDataStore(String id) throws IOException {
        return new MemoryDataStore(this, id);
    }
}
