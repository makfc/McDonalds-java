package com.google.api.client.util;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class ArrayMap<K, V> extends AbstractMap<K, V> implements Cloneable {
    private Object[] data;
    int size;

    final class Entry implements java.util.Map.Entry<K, V> {
        private int index;

        Entry(int index) {
            this.index = index;
        }

        public K getKey() {
            return ArrayMap.this.getKey(this.index);
        }

        public V getValue() {
            return ArrayMap.this.getValue(this.index);
        }

        public V setValue(V value) {
            return ArrayMap.this.set(this.index, value);
        }

        public int hashCode() {
            return getKey().hashCode() ^ getValue().hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof java.util.Map.Entry)) {
                return false;
            }
            java.util.Map.Entry<?, ?> other = (java.util.Map.Entry) obj;
            if (Objects.equal(getKey(), other.getKey()) && Objects.equal(getValue(), other.getValue())) {
                return true;
            }
            return false;
        }
    }

    final class EntryIterator implements Iterator<java.util.Map.Entry<K, V>> {
        private int nextIndex;
        private boolean removed;

        EntryIterator() {
        }

        public boolean hasNext() {
            return this.nextIndex < ArrayMap.this.size;
        }

        public java.util.Map.Entry<K, V> next() {
            int index = this.nextIndex;
            if (index == ArrayMap.this.size) {
                throw new NoSuchElementException();
            }
            this.nextIndex++;
            return new Entry(index);
        }

        public void remove() {
            int index = this.nextIndex - 1;
            if (this.removed || index < 0) {
                throw new IllegalArgumentException();
            }
            ArrayMap.this.remove(index);
            this.removed = true;
        }
    }

    final class EntrySet extends AbstractSet<java.util.Map.Entry<K, V>> {
        EntrySet() {
        }

        public Iterator<java.util.Map.Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        public int size() {
            return ArrayMap.this.size;
        }
    }

    public static <K, V> ArrayMap<K, V> create() {
        return new ArrayMap();
    }

    public final int size() {
        return this.size;
    }

    public final K getKey(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        }
        return this.data[index << 1];
    }

    public final V getValue(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        }
        return valueAtDataIndex((index << 1) + 1);
    }

    public final V set(int index, K key, V value) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        int minSize = index + 1;
        ensureCapacity(minSize);
        int dataIndex = index << 1;
        V result = valueAtDataIndex(dataIndex + 1);
        setData(dataIndex, key, value);
        if (minSize > this.size) {
            this.size = minSize;
        }
        return result;
    }

    public final V set(int index, V value) {
        int size = this.size;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        int valueDataIndex = (index << 1) + 1;
        V result = valueAtDataIndex(valueDataIndex);
        this.data[valueDataIndex] = value;
        return result;
    }

    public final V remove(int index) {
        return removeFromDataIndexOfKey(index << 1);
    }

    public final boolean containsKey(Object key) {
        return -2 != getDataIndexOfKey(key);
    }

    public final int getIndexOfKey(K key) {
        return getDataIndexOfKey(key) >> 1;
    }

    public final V get(Object key) {
        return valueAtDataIndex(getDataIndexOfKey(key) + 1);
    }

    public final V put(K key, V value) {
        int index = getIndexOfKey(key);
        if (index == -1) {
            index = this.size;
        }
        return set(index, key, value);
    }

    public final V remove(Object key) {
        return removeFromDataIndexOfKey(getDataIndexOfKey(key));
    }

    public final void ensureCapacity(int minCapacity) {
        if (minCapacity < 0) {
            throw new IndexOutOfBoundsException();
        }
        Object[] data = this.data;
        int minDataCapacity = minCapacity << 1;
        int oldDataCapacity = data == null ? 0 : data.length;
        if (minDataCapacity > oldDataCapacity) {
            int newDataCapacity = ((oldDataCapacity / 2) * 3) + 1;
            if (newDataCapacity % 2 != 0) {
                newDataCapacity++;
            }
            if (newDataCapacity < minDataCapacity) {
                newDataCapacity = minDataCapacity;
            }
            setDataCapacity(newDataCapacity);
        }
    }

    private void setDataCapacity(int newDataCapacity) {
        if (newDataCapacity == 0) {
            this.data = null;
            return;
        }
        int size = this.size;
        Object[] oldData = this.data;
        if (size == 0 || newDataCapacity != oldData.length) {
            Object[] newData = new Object[newDataCapacity];
            this.data = newData;
            if (size != 0) {
                System.arraycopy(oldData, 0, newData, 0, size << 1);
            }
        }
    }

    private void setData(int dataIndexOfKey, K key, V value) {
        Object[] data = this.data;
        data[dataIndexOfKey] = key;
        data[dataIndexOfKey + 1] = value;
    }

    private V valueAtDataIndex(int dataIndex) {
        if (dataIndex < 0) {
            return null;
        }
        return this.data[dataIndex];
    }

    private int getDataIndexOfKey(Object key) {
        int dataSize = this.size << 1;
        Object[] data = this.data;
        for (int i = 0; i < dataSize; i += 2) {
            Object k = data[i];
            if (key == null) {
                if (k == null) {
                    return i;
                }
            } else if (key.equals(k)) {
                return i;
            }
        }
        return -2;
    }

    private V removeFromDataIndexOfKey(int dataIndexOfKey) {
        int dataSize = this.size << 1;
        if (dataIndexOfKey < 0 || dataIndexOfKey >= dataSize) {
            return null;
        }
        V result = valueAtDataIndex(dataIndexOfKey + 1);
        Object[] data = this.data;
        int moved = (dataSize - dataIndexOfKey) - 2;
        if (moved != 0) {
            System.arraycopy(data, dataIndexOfKey + 2, data, dataIndexOfKey, moved);
        }
        this.size--;
        setData(dataSize - 2, null, null);
        return result;
    }

    public void clear() {
        this.size = 0;
        this.data = null;
    }

    public final boolean containsValue(Object value) {
        int dataSize = this.size << 1;
        Object[] data = this.data;
        int i = 1;
        while (i < dataSize) {
            Object v = data[i];
            if (value == null) {
                if (v != null) {
                    i += 2;
                }
            } else if (!value.equals(v)) {
                i += 2;
            }
            return true;
        }
        return false;
    }

    public final Set<java.util.Map.Entry<K, V>> entrySet() {
        return new EntrySet();
    }

    public ArrayMap<K, V> clone() {
        try {
            ArrayMap<K, V> result = (ArrayMap) super.clone();
            Object[] data = this.data;
            if (data == null) {
                return result;
            }
            int length = data.length;
            Object[] resultData = new Object[length];
            result.data = resultData;
            System.arraycopy(data, 0, resultData, 0, length);
            return result;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
