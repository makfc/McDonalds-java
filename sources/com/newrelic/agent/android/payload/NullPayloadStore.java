package com.newrelic.agent.android.payload;

import java.util.ArrayList;
import java.util.List;

public class NullPayloadStore<T> implements PayloadStore<T> {
    public boolean store(T t) {
        return true;
    }

    public List<T> fetchAll() {
        return new ArrayList();
    }

    public int count() {
        return 0;
    }

    public void clear() {
    }

    public void delete(T t) {
    }
}
