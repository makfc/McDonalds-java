package com.bumptech.glide.util;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class LruCache<T, Y> {
    private final LinkedHashMap<T, Y> cache = new LinkedHashMap(100, 0.75f, true);
    private int currentSize = 0;
    private final int initialMaxSize;
    private int maxSize;

    public LruCache(int size) {
        this.initialMaxSize = size;
        this.maxSize = size;
    }

    /* Access modifiers changed, original: protected */
    public int getSize(Y y) {
        return 1;
    }

    /* Access modifiers changed, original: protected */
    public void onItemEvicted(T t, Y y) {
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    public int getCurrentSize() {
        return this.currentSize;
    }

    public Y get(T key) {
        return this.cache.get(key);
    }

    public Y put(T key, Y item) {
        if (getSize(item) >= this.maxSize) {
            onItemEvicted(key, item);
            return null;
        }
        Y result = this.cache.put(key, item);
        if (item != null) {
            this.currentSize += getSize(item);
        }
        if (result != null) {
            this.currentSize -= getSize(result);
        }
        evict();
        return result;
    }

    public Y remove(T key) {
        Y value = this.cache.remove(key);
        if (value != null) {
            this.currentSize -= getSize(value);
        }
        return value;
    }

    public void clearMemory() {
        trimToSize(0);
    }

    /* Access modifiers changed, original: protected */
    public void trimToSize(int size) {
        while (this.currentSize > size) {
            Entry<T, Y> last = (Entry) this.cache.entrySet().iterator().next();
            Y toRemove = last.getValue();
            this.currentSize -= getSize(toRemove);
            T key = last.getKey();
            this.cache.remove(key);
            onItemEvicted(key, toRemove);
        }
    }

    private void evict() {
        trimToSize(this.maxSize);
    }
}
