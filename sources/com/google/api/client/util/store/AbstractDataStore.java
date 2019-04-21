package com.google.api.client.util.store;

import com.google.api.client.util.Preconditions;
import java.io.Serializable;

public abstract class AbstractDataStore<V extends Serializable> implements DataStore<V> {
    private final DataStoreFactory dataStoreFactory;
    /* renamed from: id */
    private final String f6521id;

    protected AbstractDataStore(DataStoreFactory dataStoreFactory, String id) {
        this.dataStoreFactory = (DataStoreFactory) Preconditions.checkNotNull(dataStoreFactory);
        this.f6521id = (String) Preconditions.checkNotNull(id);
    }
}
